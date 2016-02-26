package com.siims.szb.vipcard.action.b2c;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 会员卡领取
 * @author libo
 * 2015-09-20
 */
@Namespace("/vipcard/b2c")
public class VipCardCollarAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	ConsumerVipCardData card;
	VipCardConfigData config;
	
	/**
	 * 会员信息填写的跳转页面
	 * @author libo
	 * 2015-09-21
	 */
	@Action(value = "infowrite", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/fillout-VIPInfo.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 查询某个Type有多少个可用的规格
	 * 并返回具体信息
	 * @author libo
	 * 2015-09-21
	 */
	@Action(value = "queryconfig")
	public void queryconfig() {
		String json;
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String typeId = request.getParameter("typeId");
		System.out.println("typeId: " + typeId);
		if(typeId == null || typeId.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		List<VipCardConfigData> list = ServiceContext.get(VipCardConfigService.class).getConfigDataByTypeId(typeId);
		if(list.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"暂无规格\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		json = "{\"SUCCESS\":true, \"DATA\":[";
		boolean flag = false;
		for(int i = 0; i < list.size(); i++) {
			
			VipCardConfigData config = list.get(i);
			if(config.getIsdelete() == 1)
				continue;

			String context = "充值满"+config.getPrice()+",";
			if(config.getType() == 0) {
				context += "无优惠";
			}
			else if(config.getType() == 1) {
				context += config.getBenefit()+"折优惠";
			}
			else if(config.getType() == 2) {
				context += "返现"+config.getBenefit()+"元";
			} 
			else{
				context += "消费"+config.getBenefit()+"次";
			}
			if(config.getIsdelete() == 1) {
				context += "(已停售)";
			}
			if(flag)
				json += ",";
			json += "{\"CONTEXT\":\""+context+"\", \"CONFIGID\":\""+config.getId()+"\"}";
			flag = true;
		}
		json += "], \"MSG\": \"查找成功\"}";
		System.out.println("Json: "+json);
		ActionUtil.sendJson(json);
		return;
	}
	
	/**
	 * 添加一条会员卡记录
	 * @author libo
	 * 2015-09-22
	 */
	@Action(value = "addconsumervipcard")
	public void addConsumerVipCard() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String json;
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try{
			//获取参数以及检查
			String name = request.getParameter("name");
			System.out.println("name:"+name);
			String phone = request.getParameter("phone");
			String configId = request.getParameter("configid");
			String password = request.getParameter("password");
			if(name == null || phone == null || configId == null || password == null) {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			String openid = (String) request.getSession().getAttribute("vipcardopenid");
			//String openid = (String)session.getAttribute("vipcardopenid");
			if(openid == null) {
				json = "{\"SUCCESS\":false, \"MSG\":\"没有登录的用户\"}";
				ActionUtil.sendJson(json);
				return;
			}
			System.out.println("拿到的openid:"+openid);
			VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(configId);
			if(config == null) {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			//添加
			double lastmoney = 0;
			if(config.getType() <= 1) {
				lastmoney = config.getPrice();
			}
			else if(config.getType() == 2) {
				lastmoney = config.getPrice() + config.getBenefit();
			}
			else if(config.getType() == 3) {
				lastmoney = config.getBenefit();
			}
			String cardId = ServiceContext.get(ConsumerVipCardService.class).addConsumerVipCardData(config.getTypeId(), configId, openid, name, phone, password, lastmoney);
			if(cardId == null) {
				json = "{\"SUCCESS\":false, \"MSG\":\"添加失败\"}";
				ActionUtil.sendJson(json);
				return;
			}
			json = "{\"SUCCESS\":true, \"MSG\":\"添加成功\", \"ID\":\""+cardId+"\"}";
			ActionUtil.sendJson(json);
			return;
		}
		catch(Exception e) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
	}
	
	/**
	 * 用户确认信息
	 * 确认后跳转到支付接口
	 * @author libo
	 * 2015-09-22
	 */
	@Action(value = "confirmandpay", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/confirm-VIPInfo.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String waitForPay() {
		return SUCCESS;
	}
	
	/**
	 * 用户确认页面查询用户新办的卡的信息
	 * @author libo
	 * 2015-10-21
	 */
	@Action(value = "queryconsumercard")
	public void queryConsumerCard(){
		String vipcardId = request.getParameter("vipcardId");
		ConsumerVipCardData vipcard = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(vipcardId);
		String json;
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(vipcard == null) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(vipcard.getVipCardConfigId());
		VipCardTypeData typeData = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(config.getTypeId());
		
		json = "{\"SUCCESS\":true,";
		json += "\"NAME\":\""+vipcard.getConsumerName()+"\",";
		json += "\"NUMBER\":\""+new DecimalFormat("00000").format(vipcard.getCardNumber())+"\",";
		json += "\"ACCOUNT\":\""+vipcard.getConsumerPhone()+"\",";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		json += "\"TIME\":\""+format.format(vipcard.getCreateTime())+"\",";
		json += "\"PRICE\":"+config.getPrice()+",";
		String context = "";
		if(config.getType() == 0) {
			context += "无优惠";
		}
		else if(config.getType() == 1) {
			context += config.getBenefit()+"折优惠";
		}
		else if(config.getType() == 2) {
			context += "返现"+config.getBenefit()+"元";
		} 
		else{
			context += "消费"+(int)config.getBenefit().doubleValue()+"次";
		}
		if(config.getIsdelete() == 1) {
			context += "(已停售)";
		}
		json += "\"STOREID\":\""+typeData.getStoreUserId()+"\",";
		json += "\"YOUHUI\":\""+context+"\"}";
		ActionUtil.sendJson(json);
		return;
	}
	
	/**
	 * 供支付页面调用的Action
	 * 更新卡的状态为已支付
	 * @author libo
	 * 2015-09-22
	 * @throws IOException 
	 */
	@Action(value = "paysuccessfromalipay")
	public void paySuccess() throws IOException {
		String cardId = request.getParameter("cardId");
		int type = 1;
		ConsumerVipCardData card = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(cardId);
		if(card == null)
			return;
		card.setIspay(1);
		ServiceContext.get(ConsumerVipCardService.class).updateConsumerVipCardData(card);
		ServiceContext.get(VipCardBillService.class).addVipCardBillData(cardId, card.getLastMoney(), card.getLastMoney(), 0 , null, null, type);
		response.getWriter().write("success");
	}

	public ConsumerVipCardData getCard() {
		return card;
	}

	public void setCard(ConsumerVipCardData card) {
		this.card = card;
	}

	public VipCardConfigData getConfig() {
		return config;
	}

	public void setConfig(VipCardConfigData config) {
		this.config = config;
	}
	
	public String storename;
	public String storeid;
	public String openid;
	public String lastmoney;
	public String benefit;
	
	@Action(value = "paysuccess", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/VIP-card-suc.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String paysuccess() {
		String cardId = request.getParameter("cardId");
		
		ConsumerVipCardData card = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(cardId);
		
		String typeId = card.getVipCardTypeId();
		VipCardTypeData type = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(typeId);
		
		StoreInfoData store = ServiceContext.get(StorePersistence.class).getStoreInfoByStoreId(type.getStoreUserId());
		
		storeid = store.getId();
		storename = store.getName();
		openid = (String) request.getSession().getAttribute("vipcardopenid");
		
		VipCardConfigData configData = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(card.getVipCardConfigId());
		String config="";
		if(configData.getType() == 0)
			config = "无优惠";
		else if(configData.getType() == 1) {
			config = configData.getBenefit() +"折";
		}
		else if(configData.getType() == 2) {
			config = "返现" + configData.getBenefit() +"元";
		}
		else if(configData.getType() == 3) {
			config = "消费" + (int)configData.getBenefit().doubleValue() +"次";
		}
		benefit = config;
		
		if(configData.getType() == 3) {
			lastmoney = card.getLastMoney()+"次";
		}
		else
			lastmoney = card.getLastMoney()+"元";
		
		
		return SUCCESS;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLastmoney() {
		return lastmoney;
	}

	public void setLastmoney(String lastmoney) {
		this.lastmoney = lastmoney;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	
	
	
	
}

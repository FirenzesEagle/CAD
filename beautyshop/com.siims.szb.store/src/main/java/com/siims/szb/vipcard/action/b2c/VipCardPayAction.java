package com.siims.szb.vipcard.action.b2c;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 会员卡支付Action
 * @author libo
 * 2015-09-21
 */
@Namespace("/vipcard/b2c")
public class VipCardPayAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 会员卡支付跳转页面
	 * @author libo
	 * 2015-09-21
	 */
	@Action(value = "vipcardpay1", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/VIPCard-pay-byTimes.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 会员卡支付查询会员卡信息
	 * @author libo
	 * 2015-10-22
	 */
	@Action(value = "queryvipcardpay")
	public void queryVipcardPay() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String json;
		String vipcardId = request.getParameter("vipcardId");
		if(vipcardId == null || vipcardId.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		ConsumerVipCardData vipcard = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(vipcardId);
		if(vipcard == null || vipcard.getIspay()==0 || vipcard.getIsDelete()==1) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(vipcard.getVipCardConfigId());
		json = "{\"SUCCESS\":true,";
		json += "\"TYPE\":"+config.getType()+",";
		
		//如果是打折，则带上折扣信息
		if(config.getType() == 1) {
			json += "\"DISCOUNT\":"+config.getBenefit()+",";
		}
		json += "\"LASTMONEY\":"+vipcard.getLastMoney()+"}";
		ActionUtil.sendJson(json);
		return;
	}
	
	/**
	 * 会员卡支付支付Action
	 * @author libo
	 * 2015-09-21
	 */
	@Action(value = "vipcardpay2")
	public void pay(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpServletRequest request = ServletActionContext.getRequest();
		String json;
		
		//获取参数并检查
		try{
			String vipcardId = request.getParameter("vipcardId");
			if(vipcardId == null || vipcardId.equals("")) {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			
			String password = request.getParameter("password");
			if(password == null || password.equals("")) {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			
			double money = Double.valueOf(request.getParameter("money"));
			int consumetype = Integer.valueOf(request.getParameter("consumetype"));
			
			ConsumerVipCardData data = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(vipcardId);
			if(data == null) {
				json = "{\"SUCCESS\":false, \"MSG\":\"无此会员卡记录\"}";
				ActionUtil.sendJson(json);
				return;
			}
			
			//密码正确性检查
			if(!data.getPassword().equals(password)) {
				json = "{\"SUCCESS\":false, \"MSG\":\"密码错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			
			//余额检查
			if(data.getLastMoney() < money) {
				json = "{\"SUCCESS\":false, \"MSG\":\"余额不足\"}";
				ActionUtil.sendJson(json);
				return;
			}
			
			data.setLastMoney(data.getLastMoney() - money);
			ServiceContext.get(ConsumerVipCardService.class).updateConsumerVipCardData(data);
			ServiceContext.get(VipCardBillService.class).addVipCardBillData(vipcardId, money, data.getLastMoney(), consumetype, null, null, null);
			json = "{\"SUCCESS\":true, \"MSG\":\"消费成功\",";
			
			//带上商铺名字和钱数或者次数
			VipCardTypeData typeData = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(data.getVipCardTypeId());
			//调用ldk的接口查询店铺名字
			List<StoreInfoData> stores = ServiceContext.get(StoreService.class).getStoreByUserId(typeData.getStoreUserId());
			if(stores == null || stores.size() == 0) 
				json += "\"STORENAME\":\"暂时变量\",";
			else
				json += "\"STORENAME\":\""+stores.get(0).getName()+"\",";
		
			json += "\"OPENID\":\""+data.getConsumerWeixinOpenId()+"\",";
			VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(data.getVipCardConfigId());
			if(config.getType() == 3) {
				json += "\"CONSUME\":\""+(int)money+"次\"}";
			}
			else
				json += "\"CONSUME\":\""+money+"元\"}";
			
			ActionUtil.sendJson(json);
			return;
		}
		catch(Exception e){
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
	}
	
	/**
	 * 商品订单会员卡密码判断
	 * @author libo
	 * 2015-11-16
	 */
	@Action(value = "vipcardcheckpassword")
	public void vipcardcheckpassword() {
		String cardid = request.getParameter("cardId");
		String password = request.getParameter("password");
		
		String json;
		
		if(cardid == null || password == null || cardid.equals("") || password.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		ConsumerVipCardData vipcard = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(cardid);
		//System.out.println("old: "+vipcard.getPassword()+" and you: " + password);
		if(!password.equals(vipcard.getPassword())) {
			json = "{\"SUCCESS\":false, \"MSG\":\"密码错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		json = "{\"SUCCESS\": true, \"MSG\":\"密码正确\"}";
		ActionUtil.sendJson(json);
		return;
		
		
	}
	
	/**
	 * 会员卡支付成功
	 * @author libo
	 * 2015-10-23
	 * @return
	 */
	@Action(value = "vipcardpaysuccess", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/VIPCard-pay-suc.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String vipcardPaySuccess() {
		return SUCCESS;
	}
	
	
}

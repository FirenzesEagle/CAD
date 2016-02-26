package com.siims.szb.vipcard.action.b2c;

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
import com.siims.szb.szbldk.service.StoreService;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * C端我的会员卡列表，带搜索
 * @author libo
 * 2015-09-18
 */
@Namespace("/vipcard/b2c")
public class VipCardMyCardsAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跳转Action到B2C的我的的会员卡
	 * 查询由另外一个Action来操作
	 * @author libo
	 * 2015-09-19
	 */
	@Action(value = "querymycards", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/my-VIPCard.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	
	/**
	 * 根据用户的WEIXINOPENID查询他所拥有的会员卡列表<br/>
	 * 搜索功能由JS实现
	 * @author libo
	 * 2015-09-18
	 */
	@Action(value = "querymycardsmethod")
	public void queryMyCards() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String json;
		//取openid并放入session
		String openid = request.getParameter("openid");
		if(openid == null || openid.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		//ActionContext.getContext().getSession().put("vipcardopenid", openid);
		session.setAttribute("vipcardopenid", openid);
		System.out.println("已设置"+openid);
		//查询
		List<ConsumerVipCardData> list = ServiceContext.get(ConsumerVipCardService.class).queryConsumerVipCardDataByOpenId(openid);
		if(list.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"暂无会员卡\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//生成json字符串
		json = "{\"SUCCESS\":true, \"DATA\":[";
		for(int i = 0; i < list.size(); i++) {
			ConsumerVipCardData card = list.get(i);
			
			//确定会员卡的唯一ID
			json += "{\"VIPCARDID\":\""+card.getId()+"\",";
			
			//确定会员卡的类型和商家ID，未来扩充
			VipCardTypeData typeData = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(card.getVipCardTypeId());
			String type = "";
			if(typeData.getType() == 1) {
				type = "储值卡";
			}
			else {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			json += "\"TYPE\":\""+ type +"\",";
			//json += "\"STOREID\":\""+ typeData.getStoreUserId() +"\",";
			

			//调用ldk的接口查询店铺名字和店铺图片
			StoreInfoData stores = ServiceContext.get(StorePersistence.class).getFakeShopInfoData(typeData.getStoreUserId());
			if(stores == null) {
				json += "\"STORENAME\":\"NULL\",";
				json += "\"STOREIMAGE\":\"#\",";
			}
			else {
				json += "\"STORENAME\":\""+stores.getName()+"\",";
				json += "\"STOREIMAGE\":\""+stores.getImage()+"\",";
			}
			
			
			//确定会员卡的规格
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
			else {
				json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
				ActionUtil.sendJson(json);
				return;
			}
			json += "\"CONFIG\":\""+config+"\"}";
			if(i < list.size() - 1)
				json += ",";
		}
		json += "], \"MSG\": \"查找成功\"}";
		ActionUtil.sendJson(json);
		return;
	}
	
}

package com.siims.szb.vipcard.action.b2c;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.persistence.ConsumerVipCardPersistence;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * 给商品购买提供的会员卡支付接口
 * @author libo
 * 2015-10-10
 */
@Namespace("/vipcard/b2c")
public class VipCardQueryForShoppingAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 根据shopid和useropenid查询会员卡
	 */
	@Action(value = "querymycardoftheshop")
	public void queryMyCardOfTheShop() {
		String shopid = request.getParameter("shopid");
		String useropenid = request.getParameter("useropenid");
		
		String json;
		
		if(shopid == null || shopid.equals("") || useropenid == null || useropenid.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		List<ConsumerVipCardData> list = ServiceContext.get(ConsumerVipCardPersistence.class).queryConsumerVipCardDataByOpenIdAndShopId(useropenid, shopid);
		
		if(list == null || list.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"没有会员卡\"}";
			ActionUtil.sendJson(json);
			return;
		}
		int cnt = 0;
		json = "{\"SUCCESS\": true, \"DATA\":[";
		for(int i = 0; i < list.size(); i++) {
			ConsumerVipCardData data = list.get(i);
			VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(data.getVipCardConfigId());
			//计次卡不统计
			if(config.getType() == 3) continue;
			cnt++;
			if(cnt > 1)
				json += ",";
			json += "{\"CARDID\":\""+data.getId()+"\",";
			json += "\"TYPE\":" + config.getType() +",";
			switch(config.getType()) {
				case 0 : 
					json += "\"CONTEXT\":\"无优惠\",";
					break;
				case 1 :
					json += "\"CONTEXT\":\""+config.getBenefit()+"折\",";
					break;
				default :
					json += "\"CONTEXT\":\"充值返现卡\",";
					break;
			}
			json += "\"BENEFIT\":"+config.getBenefit()*1.0/10+",";
			json += "\"LASTMONEY\":"+data.getLastMoney()+"}";
		}
		if(cnt == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"没有可用于消费会员卡\"}";
			ActionUtil.sendJson(json);
			return;
		}
		json += "]}";
		
		ActionUtil.sendJson(json);
		return;
		
	}
	
	
}
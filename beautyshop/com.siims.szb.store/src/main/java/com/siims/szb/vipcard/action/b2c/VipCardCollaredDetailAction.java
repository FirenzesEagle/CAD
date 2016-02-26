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
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.szbldk.service.StoreService;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 已领取的会员卡详情
 * @author libo
 * 2015-09-20
 */
@Namespace("/vipcard/b2c")
public class VipCardCollaredDetailAction extends StrutsAction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跳转Action到B2C的已领取的会员卡详情
	 * 查询由另外一个Action来操作
	 * @author libo
	 * 2015-09-20
	 */
	@Action(value = "collareddetail", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/VIPCard-detail-get.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 供前端调用的method
	 * 返回已领取的会员卡详情的json
	 * @author libo
	 * 2015-09-20
	 */
	@Action(value = "collareddetailmethod")
	public void collaredDetail() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpServletRequest request = ServletActionContext.getRequest();
		String json;
		
		//从GET取领取的会员卡的ID
		String vipcardId = request.getParameter("vipcardId");
		if(vipcardId == null || vipcardId.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//根据id查询会员卡并检查
		ConsumerVipCardData card = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(vipcardId);
		if(card == null) {
			json = "{\"SUCCESS\":false, \"MSG\":\"无此会员卡\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		json = "{\"SUCCESS\":true,";
		//填充json
		json += "\"VIPCARDID\":\""+card.getId()+"\",";
		json += "\"NUMBER\":\""+String.format("%05d", card.getCardNumber())+"\",";
		
		
		VipCardConfigData cardConfig = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(card.getVipCardConfigId());
		if(cardConfig.getType() == 3)
			json += "\"LASTMONEY\":\""+(int)card.getLastMoney()+"次\",";
		else
			json += "\"LASTMONEY\":\""+card.getLastMoney()+"元\",";
		String tmp = null;
		switch (cardConfig.getType()) {
			case 0:
				tmp = "无优惠";
				break;
			case 1:
				tmp = cardConfig.getBenefit()+"折";
				break;
			case 2:
				tmp = "消费"+cardConfig.getBenefit()+"元";
				break;
			case 3:
				tmp = "消费"+(int)cardConfig.getBenefit().doubleValue()+"次";
				break;
			default:
				break;
		}
		json += "\"BENEFIT\":\""+tmp+"\",";
		
		//根据领取的会员卡查询出它所属的type和config
		VipCardTypeData typeData = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(card.getVipCardTypeId());
		
		if(typeData.getType() == 1) {
			json += "\"TYPE\":\"储值卡\",";
		}
		else {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		//json += "\"STOREID\":\""+ typeData.getStoreUserId() +"\",";
		
		//调用ldk的接口查询店铺名字和店铺图片
		StoreInfoData stores = ServiceContext.get(StorePersistence.class).getFakeShopInfoData(typeData.getStoreUserId());
		if(stores == null) {
			json += "\"STOREID\":\"NULL\",";
			json += "\"STORENAME\":\"NULL\",";
			json += "\"STOREIMAGE\":\"#\",";
			json += "\"CONFIGDATA\":[";
		}
		else {
			json += "\"STOREID\":\""+stores.getId()+"\",";
			json += "\"STORENAME\":\""+stores.getName()+"\",";
			json += "\"STOREIMAGE\":\""+stores.getImage()+"\",";
			json += "\"CONFIGDATA\":[";
		}
		
		List<VipCardConfigData> configDatas = ServiceContext.get(VipCardConfigService.class).getConfigDataByTypeId(typeData.getId());
		for(int i = 0; i < configDatas.size(); i++) {
			VipCardConfigData config = configDatas.get(i);
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
				context += "消费"+(int)config.getBenefit().doubleValue()+"次";
			}
			if(config.getIsdelete() == 1) {
				context += "(已停售)";
			}
			json += "{\"CONTEXT\":\""+context+"\"}";
			if(i < configDatas.size() - 1)
				json += ",";
		}
		json += "], \"MSG\": \"查找成功\"}";
		ActionUtil.sendJson(json);
		return;
	}
}

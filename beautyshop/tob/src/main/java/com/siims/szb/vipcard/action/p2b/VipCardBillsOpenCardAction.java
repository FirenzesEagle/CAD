package com.siims.szb.vipcard.action.p2b;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 给流水提供的查看会员卡充值记录的接口
 * @author libo
 * 2015-09-20
 */
@Namespace("/vipcard/p2b")
public class VipCardBillsOpenCardAction extends StrutsAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询所有的开卡的交易记录
	 * 返回json
	 * @author libo
	 * 2015-09-20
	 */
	@Action(value = "billsopencard")
	public void billsOpenCard() {
		String json;
		String shopId = request.getParameter("shopId");
		List<VipCardTypeData> typeList = ServiceContext.get(VipCardTypeService.class).queryAllValidVipcardTypeDataByStoreUserId(shopId);
		if(typeList == null || typeList.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"当前无交易记录\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		List<ConsumerVipCardData> vipcardList = new ArrayList<ConsumerVipCardData>();
		for(int i = 0; i < typeList.size(); i++) {
			String typeId = typeList.get(i).getId();
			List<ConsumerVipCardData> tmp = ServiceContext.get(ConsumerVipCardService.class).queryConsumerVipCardDataByTypeId(typeId);
			if(tmp != null && tmp.size() > 0) 
				vipcardList.addAll(tmp);
		}
		if(vipcardList == null || vipcardList.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"当前无交易记录\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		List<VipCardBillData> list = new ArrayList<VipCardBillData>();
		for(int i = 0; i < vipcardList.size(); i++) {
			String cardId = vipcardList.get(i).getId();
			List<VipCardBillData> tmp = ServiceContext.get(VipCardBillService.class).queryVipCardBillDataListByCardIdAndType(cardId, 0);
			if(tmp != null && tmp.size() > 0) 
				list.addAll(tmp);
		}
		
		if(list.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"当前无交易记录\"}";
			ActionUtil.sendJson(json);
			return;
		}
		json = "{\"SUCCESS\":true, \"DATA\":[";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i = 0; i < list.size(); i++) {
			VipCardBillData bill = list.get(i);
			json += "{\"TYPE\":\"会员卡充值\",";
			ConsumerVipCardData vipcard = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(bill.getVipCardId());
			VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(vipcard.getVipCardConfigId());
			json += "\"MONEY\":"+config.getPrice()+",";
			json += "\"TIME\":\""+format.format(bill.getTime())+"\",";
			ConsumerVipCardData card = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(bill.getVipCardId());
			json += "\"NAME\":\""+card.getConsumerName()+"\",";
			if(bill.getPayType() == 0) {
				json += "\"PAYTYPE\":\"微信支付\"}";
			}
			else
				json += "\"PAYTYPE\":\"支付宝支付\"}";
			if(i < list.size() - 1)
				json += ",";
		}
		json += "], \"MSG\": \"查找成功\"}";
		ActionUtil.sendJson(json);
		return;
	}
	
}

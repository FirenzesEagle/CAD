package com.siims.szb.vipcard.action.b2c;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * 查看我的会员卡账单
 * @author libo
 * 2015-09-20
 */
@Namespace("/vipcard/b2c")
public class VipCardQueryMyCardBillAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跳转Action到B2C的我的会员卡账单
	 * 查询由另外一个Action来操作
	 * @author libo
	 * 2015-09-20
	 */
	@Action(value = "mycardbill", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardb2cftl/my-bill.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String start() {
		return SUCCESS;
	}
	
	/**
	 * 查询出某个会员卡的所有流水记录
	 * @author libo
	 * 2015-09-20
	 */
	@Action(value = "mycardbillmethod")
	public void myCardBill() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpServletRequest request = ServletActionContext.getRequest();
		String json;
		
		//从GET里面去vipcardId
		String vipcardId = request.getParameter("vipcardId");
		if(vipcardId == null || vipcardId.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		List<VipCardBillData> list = ServiceContext.get(VipCardBillService.class).queryVipCardBillDataListByVipCardId(vipcardId);
		if(list.size() == 0) {
			json = "{\"SUCCESS\":false, \"MSG\":\"暂无交易记录\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		json = "{\"SUCCESS\":true, \"DATA\":[";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(int i = 0; i < list.size(); i++) {
			VipCardBillData bill = list.get(i);
			json += "{\"TIME\":\""+format.format(bill.getTime())+"\",";
			String tmp = "";
			switch(bill.getConsumeType()) {
				case 0:
					tmp = "开卡";
					break;
				case 1:
					tmp = "充值";
					break;
				case 2:
					tmp = "商品扣费";
					break;
				case 3:
					tmp = "现场收款";
					break;
				case 4:
					tmp = "现场扣次";
					break;
			}
			json += "\"ZHAIYAO\":\""+tmp+"\",";
			
			ConsumerVipCardData card = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(bill.getVipCardId());
			VipCardConfigData config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(card.getVipCardConfigId());
			
			if(bill.getConsumeType() <= 1) {
				if(config.getType() == 3) {
					json += "\"MONEY\":\"+"+(int)bill.getMoney().doubleValue()+"次\",";
				}
				else
					json += "\"MONEY\":\"+"+bill.getMoney()+"元\",";
			}
			else {
				if(config.getType() == 3) {
					json += "\"MONEY\":\"-"+(int)bill.getMoney().doubleValue()+"次\",";
				}
				else
					json += "\"MONEY\":\"-"+bill.getMoney()+"元\",";
			}
			if(config.getType() == 3) {
				json += "\"LASTMONEY\":\""+(int)bill.getLastMoney().doubleValue()+"次\"}";
			}
			else
				json += "\"LASTMONEY\":\""+bill.getLastMoney()+"元\"}";
			if(i < list.size()-1)
				json += ",";
		}
		json += "], \"MSG\": \"查找成功\"}";
		ActionUtil.sendJson(json);
		return;
	}
}

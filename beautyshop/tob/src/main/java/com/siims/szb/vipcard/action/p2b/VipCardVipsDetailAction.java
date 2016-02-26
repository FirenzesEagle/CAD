package com.siims.szb.vipcard.action.p2b;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * 储值卡会员详情
 * @author libo <br/>
 * 2015-09-17
 */
@Namespace("/vipcard/p2b")
public class VipCardVipsDetailAction extends StrutsAction {

	private static final long serialVersionUID = 1L;
	private ConsumerVipCardData consumerData;
	private List<VipCardBillData> bills;
	private VipCardConfigData config;
	
	/**
	 * 根据会员卡的ID返回会员卡详情以及账单
	 * @author libo <br/>
	 * 2015-09-17
	 */
	@Action(value = "vipcardvipsdetail", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/vipcardvipdetail.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String vipsDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		
		//权限检测
		String shopid = (String) session.getAttribute("shopid");
		if(shopid == null || shopid.equals(""))
			return ERROR;
		
		//从GET里面去ID
		String vipcardId = request.getParameter("vipcardId");
		if(vipcardId == null || vipcardId.equalsIgnoreCase(""))
			return ERROR;
		
		//查看会员卡详情
		consumerData = ServiceContext.get(ConsumerVipCardService.class).queryByConsumerVipCardId(vipcardId);
		if(consumerData == null)
			return ERROR;
		
		//查看规格
		config = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(consumerData.getVipCardConfigId());
		if(config == null)
			return ERROR;
		
		//查看流水详情
		bills = ServiceContext.get(VipCardBillService.class).queryVipCardBillDataListByVipCardId(vipcardId);
		
		return SUCCESS;
	}

	public ConsumerVipCardData getConsumerData() {
		return consumerData;
	}

	public void setConsumerData(ConsumerVipCardData consumerData) {
		this.consumerData = consumerData;
	}

	public List<VipCardBillData> getBills() {
		return bills;
	}

	public void setBills(List<VipCardBillData> bills) {
		this.bills = bills;
	}

	public VipCardConfigData getConfig() {
		return config;
	}

	public void setConfig(VipCardConfigData config) {
		this.config = config;
	}
	
	
	

}

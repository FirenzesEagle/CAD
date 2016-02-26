package com.siims.szb.bespeakorder.action;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.bespeak.data.BespeakData;
import com.siims.szb.bespeak.service.BespeakService;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.service.BespeakOrderService;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;

@Namespace("/siims/szb/bespeakorder")
public class GetBespeakOrderForOneAction extends StrutsAction{

	/**通过预约记录查找出预约订单
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	private BespeakData data = new BespeakData();
	
	@Action(value = "togetbespeakorderforone", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/XXX.ftl")})
	public String togetbespeakorder() {
		return SUCCESS;
	}
	
	@Action(value = "getbespeakorderforone")
	public void GetBespeakOrderForOne() throws IOException{
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		
		Gson gson = new Gson();
		
		String bespeakid = request.getParameter("speakid");
		
		if(bespeakid == null || bespeakid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No speak\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		data = ServiceContext.get(BespeakService.class).GetBespeakById(bespeakid);
		
		try{
			orderdata = ServiceContext.get(BespeakOrderService.class).GetBespeaorderByBespeak(bespeakid).get(0);
			
			String person_name = URLDecoder.decode(URLDecoder.decode(orderdata.getPersonname()));
			System.out.println(person_name);
			orderdata.setPersonname(person_name);
			
			String service_name = URLDecoder.decode(URLDecoder.decode(orderdata.getServicename()));
			System.out.println(service_name);
			orderdata.setServicename(service_name);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No speakorder\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		ServiceInfoData servicedata = ServiceContext.get(ServiceService.class).searchServiceInfo(data.getServiceid());
		
		if(servicedata == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"error\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		orderdata.setServiceimage(servicedata.getServiceShowImg());
		
		String timepart = data.getLine() + ":00 ~ " + (data.getLine()+1) + ":00";
		
		orderdata.setTimepart(timepart);
		if(servicedata.getServiceShowImg() != null)
		{
			orderdata.setServiceimage(servicedata.getServiceShowImg());
		}else{
			orderdata.setServiceimage("");
		}
		
		
		msg = gson.toJson(orderdata);
		
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + "}";
		ActionUtil.sendJson(responseResult);
	}

}

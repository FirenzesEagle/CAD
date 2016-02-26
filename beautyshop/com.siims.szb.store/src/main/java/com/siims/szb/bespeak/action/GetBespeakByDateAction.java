package com.siims.szb.bespeak.action;

import java.io.IOException;
import java.util.List;

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
@Namespace("/siims/szb/bespeak")
public class GetBespeakByDateAction extends StrutsAction{

	/**通过选择日期，给出相应日期的预约情况
	 * @author ling
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakData data = new BespeakData();
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	@Action(value = "togetbespeakbydatebyc", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/yuyue-form.ftl")})
	public String togetbespeakbydate() {
		return SUCCESS;
	}
	
	@Action(value = "getbespeakbydate")
	public void GetBespeakByDate() throws IOException {
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		
		System.out.println("here is bespeakbydate");
		
		String date = request.getParameter("date");
		String shopid = request.getParameter("shopid");
		
		Gson gson = new Gson();
		
		System.out.println("date is " + date);
		
		if(date == null || date.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No date\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		System.out.println("shopid is " + shopid);
		
		if(shopid == null || shopid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No shop\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		List<BespeakData> list = ServiceContext.get(BespeakService.class).GetBespeakByDate(date,shopid);
		
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				data = list.get(i);
				if(ServiceContext.get(BespeakOrderService.class).GetBespeaorderByBespeak(data.getId()) != null
						&& ServiceContext.get(BespeakOrderService.class).GetBespeaorderByBespeak(data.getId()).size() > 0)
				{
					orderdata = ServiceContext.get(BespeakOrderService.class).GetBespeaorderByBespeak(data.getId()).get(0);
					data.setOrderid(orderdata.getId());
					data.setState(orderdata.getState());
				}
				//data.setState(1);
				
				msg += gson.toJson(data) + ",";
			}
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "]}";
			ActionUtil.sendJson(responseResult);
		}else{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No record\"}";
			ActionUtil.sendJson(responseResult);
		}
	}

}

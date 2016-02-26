package com.siims.szb.bespeakorder.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
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

@Namespace("/siims/szb/bespeakorder")
public class SearchBespeakOrderAction extends StrutsAction{

	/**通过关键字搜索服务订单
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	private BespeakData data = new BespeakData();
	
	@Action(value = "tosearchbespeakorder", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/yuyueSearch.ftl")})
	public String tosearchbespeakorder() {
		return SUCCESS;
	}
	
	@Action(value = "searchbespeakorder")
	public void SearchBespeakOrder() throws IOException{
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
				
		String msg = "";
				
		Gson gson = new Gson();
		
		String s = "";
		
		int day = (new Date()).getDate();
		int month = (new Date()).getMonth()+1;
		int year = (new Date()).getYear()+1900;
		int hour = (new Date()).getHours();
		int minute = (new Date()).getMinutes();
		
		s = year + "" + month + "" + day + "" + hour + "" + minute;
		
		System.out.println(s);
		
		String key = request.getParameter("key");
		
		String shopid = request.getParameter("shopid");
		
		if(shopid == null || shopid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No shop\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		List<BespeakOrderData> list;
		
		if(key != null && key.length() != 0)
		{
			list = ServiceContext.get(BespeakOrderService.class).GetBespeakOrderByKey(key, shopid);
		}else{
			list = ServiceContext.get(BespeakOrderService.class).GetAllBespeakOrder(shopid);
		}
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				orderdata = list.get(i);
//				orderdata.setPersonname(URLDecoder.decode(URLDecoder.decode(orderdata.getPersonname())));
//				orderdata.setServicename(URLDecoder.decode(URLDecoder.decode(orderdata.getServicename())));
				data = ServiceContext.get(BespeakService.class).GetBespeakById(orderdata.getRecordid());
				if(data != null)
				{
					String timepart = data.getLine() + ":00~" + (data.getLine()+1) + ":00";
					orderdata.setTimepart(timepart);
					String serviceid = data.getServiceid();
					//通过服务id找出服务的图片
					String serviceimage = "";
					orderdata.setServiceimage(serviceimage);
				}else{
					orderdata.setTimepart("");
					orderdata.setServiceimage("");
				}
				msg += gson.toJson(orderdata) + ",";
			}
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "]}";
			ActionUtil.sendJson(responseResult);
		}else{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No record\"}";
			ActionUtil.sendJson(responseResult);
		}
		
	}
}

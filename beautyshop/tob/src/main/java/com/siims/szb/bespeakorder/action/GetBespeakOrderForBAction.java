package com.siims.szb.bespeakorder.action;

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
import com.siims.szb.service.service.service.ServiceService;

@Namespace("/siims/szb/bespeakorder")
public class GetBespeakOrderForBAction extends StrutsAction{

	/**通过订单的状态查找出顾客对应的订单
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	private BespeakData data = new BespeakData();
	
	@Action(value = "togetbespeakorderforb", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/XXX.ftl")})
	public String togetbespeakorderforb() {
		return SUCCESS;
	}
	
	@Action(value = "getbespeakorderforb")
	public void GetBespeakOrderForB() throws IOException {
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		
		Gson gson = new Gson();
		
		int type = -1;
		
		if(request.getParameter("type") != null && request.getParameter("type").length() != 0)
		{
			type = Integer.valueOf(request.getParameter("type"));
		}
		String shopid = request.getParameter("shopid");
		
		if(shopid == null || shopid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No shop\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		
		if(type != -1)
		{
			List<BespeakOrderData> list = ServiceContext.get(BespeakOrderService.class).GetBespeakOrderForShop(type, shopid);
			if(list != null && list.size() > 0)
			{
				for(int i = 0; i < list.size(); i++)
				{
					orderdata = list.get(i);
					data = ServiceContext.get(BespeakService.class).GetBespeakById(orderdata.getRecordid());
					if(data != null)
					{
						String timepart = data.getLine() + ":00 ~ " + (data.getLine()+1) + ":00";
						orderdata.setTimepart(timepart);
						String serviceid = data.getServiceid();
						String serviceimage = "";
						//通过服务id找出服务的图片
						if(ServiceContext.get(ServiceService.class).searchServiceInfo(serviceid) != null)
						{
							serviceimage = ServiceContext.get(ServiceService.class).searchServiceInfo(serviceid).getServiceShowImg();
						}
						
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
		}else{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"State Error\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
}

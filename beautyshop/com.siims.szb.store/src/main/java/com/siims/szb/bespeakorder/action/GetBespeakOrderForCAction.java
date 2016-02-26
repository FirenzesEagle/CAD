package com.siims.szb.bespeakorder.action;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;

@Namespace("/siims/szb/bespeakorder")
public class GetBespeakOrderForCAction extends StrutsAction{

	/**通过订单的状态查找出顾客对应的订单
	 * 面对c类用户的订单
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	private BespeakData data = new BespeakData();
	
	private ServiceInfoData servicedata = new ServiceInfoData();
	
	StoreInfoData storedata = new StoreInfoData();
	
	
	@Action(value = "togetbespeakorderforc", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/my-order.ftl")})
	public String togetbespeakorderforc() {
		return SUCCESS;
	}
	
	@Action(value = "getbespeakorderforc")
	public void GetBespeakOrderForC() throws IOException {
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		System.out.println("here is : getbespeakorderforc");
		
		String msg = "";
		
		Gson gson = new Gson();
		
		int type = -1;
		
		if(request.getParameter("type") != null && request.getParameter("type").length() != 0)
		{
			type = Integer.valueOf(request.getParameter("type"));
		}
		
		System.out.println(type);
		
		String personid = request.getParameter("personid");
		/*String shopid = request.getParameter("shopid");
		
		if(shopid == null || shopid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No shop\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}*/
		
		if(personid == null || personid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No customer\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		System.out.println("openid is :" + personid);
		
		if(type != -1)
		{
			List<BespeakOrderData> list = ServiceContext.get(BespeakOrderService.class).GetBespeakOrderForCustomer(type, personid);
			if(list != null && list.size() > 0)
			{
				
				for(int i = 0; i < list.size(); i++)
				{
					orderdata = list.get(i);
					orderdata.setServicename(URLDecoder.decode(URLDecoder.decode(orderdata.getServicename())));
					data = ServiceContext.get(BespeakService.class).GetBespeakById(orderdata.getRecordid());
					
					if(data != null)
					{
						String timepart = data.getLine() + ":00~" + (data.getLine()+1) + ":00";
						
						orderdata.setTimepart(timepart);
						String serviceid = data.getServiceid();
						//通过服务id找出服务的图片
						String serviceimage = "";
						String shopname = "";
						
						servicedata = ServiceContext.get(ServiceService.class).searchServiceInfo(orderdata.getServiceid());
						storedata = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(orderdata.getShopid());
						if(storedata != null)
							shopname = URLDecoder.decode(URLDecoder.decode(storedata.getName()));
						if(servicedata != null) {
							serviceimage = servicedata.getServiceShowImg();
						}
						orderdata.setServiceimage(serviceimage);
						orderdata.setShopname(shopname);
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

package com.siims.szb.service.service.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.service.ServiceConfigService;
import com.siims.szb.service.servicedetail.data.ServiceDetailData;
import com.siims.szb.service.servicedetail.service.ServiceDetailService;


@Namespace("/siims/szb/service")
public class ServiceListAction extends StrutsAction{

	/**
	 * @author liu
	 * @since 2015.9.7
	 */
	private static final long serialVersionUID = 1L;
	
	private String storeId;
	
	@Action(value = "servicelist", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/shop-goodlist.ftl")})
	public String servicelist() {
		return SUCCESS;
	}
	
	@Action(value = "serviceListByStoreId")
	public void serviceListByStoreId() throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//根据商铺id获取该商铺的所有的服务
		List<ServiceInfoData> listService;
		List<ServiceConfigData> listConfig;
		List<ServiceDetailData> listDetail;
		storeId = request.getParameter("storeId");
		String serviceId;
		listService = ServiceContext.get(ServiceService.class).searchAllServiceInfo(storeId);
		
		
		
		if(listService == null || listService.size() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Service\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			for(int i = 0; i < listService.size(); i++)
			{
				serviceId = listService.get(i).getId();
				listConfig = ServiceContext.get(ServiceConfigService.class).searchAllConfigInfo(serviceId);
				//展示的服务只有服务的isdelete字段为"0"
				if(listService.get(i).getIsDelete().equals("0"))
				{
					System.out.println(listService.size());
					
					System.out.println(listConfig.size());
					for(int k=0;k<listConfig.size();k++)
					{
						System.out.println("1");
						listDetail = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(listConfig.get(k).getId());
						System.out.println(listDetail.size());
						String temp =
								"{"+"\""+"serviceId"+"\""+":"+"\""+listConfig.get(k).getId()+"\""+","
								+"\""+"realServiceId"+"\""+":"+"\""+listService.get(i).getId()+"\""+","
								
								+"\""+"serviceShowImg"+"\""+":"+"\""+listService.get(i).getServiceShowImg()+"\""+","
								+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
								+"\""+"serviceName"+"\""+":"+"\""+listService.get(i).getServiceName()+"\""+","
								+"\""+"configName"+"\""+":"+"\""+listConfig.get(k).getConfigName()+"\""+","
								+"\""+"servicePrice"+"\""+":"+"\""+listConfig.get(k).getConfigPrice()+"\""+","
								+"\""+"serviceTime"+"\""+":"+"\""+listConfig.get(k).getServiceTime()+"\""+","
								+"\""+"serviceDes"+"\""+":"+"\""+listDetail.get(0).getServiceDes()+"\""+"}"
								;
						msg += temp+",";
					}
				}
					System.out.println("4");
			}
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
}


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
public class ServiceListToBAction extends StrutsAction{

	/**
	 * @author liu
	 * @since 2015.9.7
	 */
	private static final long serialVersionUID = 1L;
	
	private String storeId;
	
	
	
	@Action(value = "serviceShow")
	public void serviceShow() throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		String serviceConfigId = request.getParameter("serviceId");
		ServiceConfigData configData = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(serviceConfigId);
		String serviceId = configData.getServiceId();
		ServiceInfoData serviceData = ServiceContext.get(ServiceService.class).searchServiceInfo(serviceId);
		List<ServiceDetailData> listDetail = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(configData.getId());
		
		
		System.out.println(listDetail.get(0).getServiceDes());
		String tmp="" ;
		for(int i=0;i<listDetail.size();i++){
			tmp +="{"+"\""+"url"+"\""+":"+"\""+listDetail.get(i).getServiceDesImg()+"\""+","+"\""+"des"+"\""+":"+"\""+listDetail.get(i).getServiceDes()+"\""+"}"+",";
		}
		System.out.println(listDetail.size());
		if(tmp.length()>0)
			tmp = tmp.substring(0, tmp.length()-1);
		//System.out.println(tmp);
		String detailContent = "["+tmp+"]";
		System.out.println(detailContent);
		
		
		
		
		
		String temp =
				"{"+"\""+"serviceId"+"\""+":"+"\""+configData.getId()+"\""+","
				+"\""+"realServiceId"+"\""+":"+"\""+serviceData.getId()+"\""+","
				+"\""+"serviceDetailID"+"\""+":"+"\""+listDetail.get(0).getId()+"\""+","
				+"\""+"serviceShowImg"+"\""+":"+"\""+serviceData.getServiceShowImg()+"\""+","
				+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
				+"\""+"serviceName"+"\""+":"+"\""+serviceData.getServiceName()+"\""+","
				+"\""+"configName"+"\""+":"+"\""+configData.getConfigName()+"\""+","
				+"\""+"servicePrice"+"\""+":"+"\""+configData.getConfigPrice()+"\""+","
				+"\""+"detailInfoData"+"\""+":"+detailContent+","
				+"\""+"serviceTime"+"\""+":"+"\""+configData.getServiceTime()+"\""+","
				+"\""+"serviceDes"+"\""+":"+"\""+listDetail.get(0).getServiceDes()+"\""+"}"
				;
		msg += temp+",";
		if(msg.length()==0){
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"\"}";
		}
		else responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
		ActionUtil.sendJson(responseResult);
		
	}
	
	@Action(value = "servicelisttob", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/serviceManage.ftl")})
	public String servicelisttob() {
		return SUCCESS;
	}
	
	
	@Action(value = "serviceListToBByStoreId")
	public void serviceListToBByStoreId() throws IOException {
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
						
						if(listConfig.get(k).getIsDelete().equals("0")){
						String temp =
								"{"+"\""+"serviceId"+"\""+":"+"\""+listConfig.get(k).getId()+"\""+","
								+"\""+"realServiceId"+"\""+":"+"\""+listService.get(i).getId()+"\""+","
								+"\""+"serviceDetailID"+"\""+":"+"\""+listDetail.get(0).getId()+"\""+","
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
				}
					System.out.println("4");
			}
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
}


package com.siims.szb.service.service.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.service.ServiceConfigService;
import com.siims.szb.service.servicedetail.data.ServiceDetailData;
import com.siims.szb.service.servicedetail.service.ServiceDetailService;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;

/**
 * @author lmk
 *
 */
@Namespace("/siims/szb/service")
public class ServiceInfoAction extends StrutsAction {

	private static final long serialVersionUID = 1L;
	
	private String serviceId;
	private String serviceConfigId;
	
	@Action(value = "toserviceInfoByServiceIdAndServiceConfigId", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/services-detail.ftl")})
	public String serviceinfo() {
		return SUCCESS;
	}
	
	@Action(value = "serviceInfoByServiceIdAndServiceConfigId")
	public void getServiceFullInfoByServiceIdAndServiceConfigId() throws IOException{
		JsonObject jsonObject = new JsonObject();
		String serviceId = null;
		String serviceConfigId = request.getParameter("serviceConfigId");
		ServiceInfoData serviceInfoData = null;
		ServiceConfigData serviceConfigData = null;
		StoreInfoData storeInfoData = null;
		List<ServiceDetailData> serviceDetailDataList = null;
		if(serviceConfigId != null){
			serviceConfigData = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(serviceConfigId);
			serviceDetailDataList = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(serviceConfigId);
		}
		if(serviceConfigData != null){
			serviceId = serviceConfigData.getServiceId();
		}
		if(serviceId != null){
			serviceInfoData = ServiceContext.get(ServiceService.class).searchServiceInfo(serviceId);
		}
		if(serviceInfoData != null){
			storeInfoData = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(serviceInfoData.getStoreId());
		}
//		ServiceInfoData serviceInfoData = ServiceContext.get(ServiceService.class).searchServiceInfo(serviceId);
//		ServiceConfigData serviceConfigData = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(serviceConfigId);
//		StoreInfoData storeInfoData = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(serviceInfoData.getStoreId());
//		List<ServiceDetailData> serviceDetailDataList = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(serviceConfigId);
		
		if((serviceInfoData != null)&&(serviceConfigData != null)){
			jsonObject.addProperty("SUCCESS", true);
			jsonObject.addProperty("ERROMSG", "");
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray dataJsonArray = new JsonArray();
//			dataJsonArray.add(parser.parse(gson.toJson(serviceInfoData)));
//			dataJsonArray.add(parser.parse(gson.toJson(serviceConfigData)));
//			dataJsonArray.add(parser.parse(gson.toJson(storeInfoData)));
			JsonObject mainJson = new JsonObject();
			mainJson.addProperty("serviceConfigsId",serviceConfigData.getId() );
			mainJson.addProperty("serviceName",serviceInfoData.getServiceName());
			mainJson.addProperty("serviceShowImg",serviceInfoData.getServiceShowImg());
			mainJson.addProperty("configName", serviceConfigData.getConfigName());
			mainJson.addProperty("configPrice",serviceConfigData.getConfigPrice() );
			mainJson.addProperty("storeId", storeInfoData.getId());
			mainJson.addProperty("storeName",storeInfoData.getName() );
			mainJson.addProperty("storeImage", storeInfoData.getImage());
			mainJson.addProperty("phoneNumber", storeInfoData.getTel());
			dataJsonArray.add(mainJson);
			dataJsonArray.add(parser.parse(gson.toJson(serviceDetailDataList)));
			jsonObject.add("DATA", dataJsonArray);
		}else{
			jsonObject.addProperty("SUCCESS", false);
			jsonObject.addProperty("ERROMSG", "No service!");
		}
		ActionUtil.sendJson(jsonObject.toString());
	}
}

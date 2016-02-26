package com.siims.szb.service.service.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.service.service.data.ServiceInfoData;

public interface ServiceBusiness extends BaseBusiness{
	
	public String addServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public boolean delServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public boolean editServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public ServiceInfoData searchServiceInfo(String serviceId);
	
	
	public List<ServiceInfoData> searchAllServiceInfo(String pageID);
}

package com.siims.szb.service.serviceconfig.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;

import com.siims.szb.service.serviceconfig.data.ServiceConfigData;

public interface ServiceConfigBusiness extends BaseBusiness{
	
	public String addConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public boolean delConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public boolean editConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public ServiceConfigData searchConfigInfo(String serviceConfigID);
	
	
	public List<ServiceConfigData> searchAllConfigInfo(String serviceId);
}

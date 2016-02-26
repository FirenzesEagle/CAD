package com.siims.szb.service.serviceconfig.service;

import java.util.List;

import com.siims.framework.base.BaseService;

import com.siims.szb.service.serviceconfig.data.ServiceConfigData;


public interface ServiceConfigService extends BaseService{
	
	public String addConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public boolean delConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public boolean editConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public ServiceConfigData searchConfigInfo(String serviceConfigID);
	
	
	public List<ServiceConfigData> searchAllConfigInfo(String serviceId);

}

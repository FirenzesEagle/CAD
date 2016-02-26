package com.siims.szb.service.service.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.service.service.data.ServiceInfoData;


public interface ServiceService extends BaseService{
	
	public String addServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public boolean delServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public boolean editServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public ServiceInfoData searchServiceInfo(String serviceID);
	
	
	public List<ServiceInfoData> searchAllServiceInfo(String storeId);

}

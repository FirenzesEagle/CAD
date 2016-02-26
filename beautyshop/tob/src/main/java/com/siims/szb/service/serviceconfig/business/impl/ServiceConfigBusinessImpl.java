package com.siims.szb.service.serviceconfig.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;

import com.siims.szb.service.serviceconfig.business.ServiceConfigBusiness;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.persistence.ServiceConfigPersistence;


@Singleton
@AutoBind(bindClass = ServiceConfigBusiness.class)
public class ServiceConfigBusinessImpl implements ServiceConfigBusiness{

	public String addConfigInfo(ServiceConfigData configInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceConfigPersistence.class).addConfigInfo(configInfoData);
		return configInfoData.getId();
	}

	public boolean delConfigInfo(ServiceConfigData configInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceConfigPersistence.class).delConfigInfo(configInfoData);
		return true;
	}

	public boolean editConfigInfo(ServiceConfigData configInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceConfigPersistence.class).editConfigInfo(configInfoData);
		return true;
	}
	
	public ServiceConfigData searchConfigInfo(String serviceConfigID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceConfigPersistence.class).searchConfigInfo(serviceConfigID);
	}

	public List<ServiceConfigData> searchAllConfigInfo(String serviceId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceConfigPersistence.class).searchAllConfigInfo(serviceId);
	}

	
	

	
	

	

	

}

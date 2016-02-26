package com.siims.szb.service.serviceconfig.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;

import com.siims.szb.service.serviceconfig.business.ServiceConfigBusiness;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;

import com.siims.szb.service.serviceconfig.service.ServiceConfigService;


@Singleton
@AutoBind(bindClass = ServiceConfigService.class)
@TransactionalContext
public class ServiceConfigServiceImpl implements ServiceConfigService {

	public String addConfigInfo(ServiceConfigData serviceConfigData) {
		// TODO Auto-generated method stub

		ServiceContext.get(ServiceConfigBusiness.class).addConfigInfo(serviceConfigData);

		return serviceConfigData.getId();
	}

	public boolean delConfigInfo(ServiceConfigData serviceConfigData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceConfigBusiness.class).delConfigInfo(serviceConfigData);
		return true;
	}

	public boolean editConfigInfo(ServiceConfigData serviceInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceConfigBusiness.class).editConfigInfo(serviceInfoData);
		return true;
	}

	public ServiceConfigData searchConfigInfo(String serviceConfigID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceConfigBusiness.class).searchConfigInfo(serviceConfigID);
	}

	public List<ServiceConfigData> searchAllConfigInfo(String serviceId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceConfigBusiness.class).searchAllConfigInfo(serviceId);
	}

	

}

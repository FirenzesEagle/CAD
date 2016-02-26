package com.siims.szb.service.service.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.service.service.business.ServiceBusiness;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;


@Singleton
@AutoBind(bindClass = ServiceService.class)
@TransactionalContext
public class ServiceServiceImpl implements ServiceService {

	public String addServiceInfo(ServiceInfoData serviceInfoData) {
		// TODO Auto-generated method stub
//		demoData.setShop_id("1");
//		demoData.setFood_recommend(0);
//		demoData.setFood_type("FOOD");
		ServiceContext.get(ServiceBusiness.class).addServiceInfo(serviceInfoData);

		
		return serviceInfoData.getId();
	}

	public boolean delServiceInfo(ServiceInfoData serviceInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceBusiness.class).delServiceInfo(serviceInfoData);
		return true;
	}

	public boolean editServiceInfo(ServiceInfoData serviceInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceBusiness.class).editServiceInfo(serviceInfoData);
		return true;
	}

	public ServiceInfoData searchServiceInfo(String serviceId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceBusiness.class).searchServiceInfo(serviceId);
	}

	public List<ServiceInfoData> searchAllServiceInfo(String storeId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceBusiness.class).searchAllServiceInfo(storeId);
	}

	

}

package com.siims.szb.service.service.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.service.service.business.ServiceBusiness;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.persistence.ServicePersistence;


@Singleton
@AutoBind(bindClass = ServiceBusiness.class)
public class ServiceBusinessImpl implements ServiceBusiness{

	public String addServiceInfo(ServiceInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServicePersistence.class).addServiceInfo(goodsInfoData);
		return goodsInfoData.getId();
	}

	public boolean delServiceInfo(ServiceInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServicePersistence.class).delServiceInfo(goodsInfoData);
		return true;
	}

	public boolean editServiceInfo(ServiceInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServicePersistence.class).editServiceInfo(goodsInfoData);
		return true;
	}
	
	public ServiceInfoData searchServiceInfo(String goodsID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServicePersistence.class).searchServiceInfo(goodsID);
	}

	public List<ServiceInfoData> searchAllServiceInfo(String storeId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServicePersistence.class).searchAllServiceInfo(storeId);
	}

	
	

	
	

	

	

}

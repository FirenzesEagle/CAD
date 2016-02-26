package com.siims.szb.service.servicedetail.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;

import com.siims.szb.service.servicedetail.business.ServiceDetailBusiness;
import com.siims.szb.service.servicedetail.data.ServiceDetailData;
import com.siims.szb.service.servicedetail.persistence.ServiceDetailPersistence;


@Singleton
@AutoBind(bindClass = ServiceDetailBusiness.class)
public class ServiceDetailBusinessImpl implements ServiceDetailBusiness{

	public String addDetailInfo(ServiceDetailData configInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceDetailPersistence.class).addDetailInfo(configInfoData);
		return configInfoData.getId();
	}

	public boolean delDetailInfo(ServiceDetailData configInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceDetailPersistence.class).delDetailInfo(configInfoData);
		return true;
	}

	public boolean editDetailInfo(ServiceDetailData configInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceDetailPersistence.class).editDetailInfo(configInfoData);
		return true;
	}
	
	public ServiceDetailData searchDetailInfo(String goodsID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceDetailPersistence.class).searchDetailInfo(goodsID);
	}

	public List<ServiceDetailData> searchAllDetailInfo(String storeID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceDetailPersistence.class).searchAllDetailInfo(storeID);
	}

	
	

	
	

	

	

}

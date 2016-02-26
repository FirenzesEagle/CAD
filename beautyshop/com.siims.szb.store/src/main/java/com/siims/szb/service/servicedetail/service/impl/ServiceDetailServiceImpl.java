package com.siims.szb.service.servicedetail.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;

import com.siims.szb.service.servicedetail.business.ServiceDetailBusiness;
import com.siims.szb.service.servicedetail.data.ServiceDetailData;
import com.siims.szb.service.servicedetail.service.ServiceDetailService;


@Singleton
@AutoBind(bindClass = ServiceDetailService.class)
@TransactionalContext
public class ServiceDetailServiceImpl implements ServiceDetailService {

	public String addDetailInfo(ServiceDetailData serviceConfigData) {
		// TODO Auto-generated method stub
//		demoData.setShop_id("1");
//		demoData.setFood_recommend(0);
//		demoData.setFood_type("FOOD");
		ServiceContext.get(ServiceDetailBusiness.class).addDetailInfo(serviceConfigData);
		return serviceConfigData.getId();
		
	}

	public boolean delDetailInfo(ServiceDetailData serviceConfigData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceDetailBusiness.class).delDetailInfo(serviceConfigData);
		return true;
	}

	public boolean editDetailInfo(ServiceDetailData serviceInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ServiceDetailBusiness.class).editDetailInfo(serviceInfoData);
		return true;
	}

	public ServiceDetailData searchDetailInfo(String detailId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceDetailBusiness.class).searchDetailInfo(detailId);
	}

	public List<ServiceDetailData> searchAllDetailInfo(String serviceConfigId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ServiceDetailBusiness.class).searchAllDetailInfo(serviceConfigId);
	}

	

}

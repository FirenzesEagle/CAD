package com.siims.szb.service.servicedetail.service;

import java.util.List;

import com.siims.framework.base.BaseService;

import com.siims.szb.service.servicedetail.data.ServiceDetailData;


public interface ServiceDetailService extends BaseService{
	
	public String addDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public boolean delDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public boolean editDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public ServiceDetailData searchDetailInfo(String serviceDetailID);
	
	
	public List<ServiceDetailData> searchAllDetailInfo(String serviceDetailId);

}

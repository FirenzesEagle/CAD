package com.siims.szb.service.servicedetail.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;

import com.siims.szb.service.servicedetail.data.ServiceDetailData;

public interface ServiceDetailBusiness extends BaseBusiness{
	
	public String addDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public boolean delDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public boolean editDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public ServiceDetailData searchDetailInfo(String detailId);
	
	
	public List<ServiceDetailData> searchAllDetailInfo(String serviceConfigID);
}

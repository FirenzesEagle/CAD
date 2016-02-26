package com.siims.szb.service.servicedetail.persistence;

import java.util.List;






import com.siims.framework.base.BasePersistence;
import com.siims.szb.service.servicedetail.data.ServiceDetailData;

public interface ServiceDetailPersistence extends BasePersistence<ServiceDetailData> {
	
	
	public String addDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public boolean delDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public boolean editDetailInfo(ServiceDetailData serviceConfigData);
	
	
	public ServiceDetailData searchDetailInfo(String configId);
	
	
	public List<ServiceDetailData> searchAllDetailInfo(String serviceId);
}

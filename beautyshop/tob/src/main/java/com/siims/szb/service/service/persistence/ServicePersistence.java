package com.siims.szb.service.service.persistence;

import java.util.List;






import com.siims.framework.base.BasePersistence;
import com.siims.szb.service.service.data.ServiceInfoData;

public interface ServicePersistence extends BasePersistence<ServiceInfoData> {
	
	
	public String addServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public boolean delServiceInfo(ServiceInfoData serviceInfoData);
	
	
	public boolean editServiceInfo(ServiceInfoData serviceInfoData);
	
	
	
	public ServiceInfoData searchServiceInfo(String serviceId);
	
	
	public List<ServiceInfoData> searchAllServiceInfo(String storeId);
}

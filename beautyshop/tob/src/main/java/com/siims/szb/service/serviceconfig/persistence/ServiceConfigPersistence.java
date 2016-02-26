package com.siims.szb.service.serviceconfig.persistence;

import java.util.List;






import com.siims.framework.base.BasePersistence;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;

public interface ServiceConfigPersistence extends BasePersistence<ServiceConfigData> {
	
	
	public String addConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public boolean delConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public boolean editConfigInfo(ServiceConfigData serviceConfigData);
	
	
	public ServiceConfigData searchConfigInfo(String configId);
	
	
	public List<ServiceConfigData> searchAllConfigInfo(String serviceId);
}

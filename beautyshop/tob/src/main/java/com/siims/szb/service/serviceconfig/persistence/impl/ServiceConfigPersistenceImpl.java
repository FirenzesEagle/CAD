package com.siims.szb.service.serviceconfig.persistence.impl;

import java.util.List;




import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.persistence.ServiceConfigPersistence;


@Singleton
@AutoBind(bindClass = ServiceConfigPersistence.class)
public class ServiceConfigPersistenceImpl implements ServiceConfigPersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public ServiceConfigPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addConfigInfo(ServiceConfigData configInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, configInfoData);
		return configInfoData.getId();
	}

	public boolean delConfigInfo(ServiceConfigData configInfoData) {
		// TODO Auto-generated method stub
		configInfoData.setIsDelete("1");
		hibernateDao.update(null, configInfoData);
		return true;
	}

	public boolean editConfigInfo(ServiceConfigData configInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, configInfoData);
		return true;
	}

	public ServiceConfigData searchConfigInfo(String configId) {
		// TODO Auto-generated method stub
		ServiceConfigData goodInfoData = (ServiceConfigData) hibernateDao.queryData(configId, ServiceConfigData.class);
		return goodInfoData;
	}

	@SuppressWarnings("unchecked")
	public List<ServiceConfigData> searchAllConfigInfo(String serviceId) {
		String sql = "from ServiceConfigData s where s.serviceId = '" + serviceId +"' order by s.id desc";
		List<ServiceConfigData> list = hibernateDao.queryList(sql, null);
		return list;
	}

}

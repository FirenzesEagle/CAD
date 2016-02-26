package com.siims.szb.service.service.persistence.impl;

import java.util.List;




import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.persistence.ServicePersistence;


@Singleton
@AutoBind(bindClass = ServicePersistence.class)
public class ServicePersistenceImpl implements ServicePersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public ServicePersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addServiceInfo(ServiceInfoData serviceInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, serviceInfoData);
		return serviceInfoData.getId();
	}
	/**
	 * 删除服务只需令isDElete为1
	 */
	public boolean delServiceInfo(ServiceInfoData serviceInfoData) {
		// TODO Auto-generated method stub
		//hibernateDao.delete(null, serviceInfoData);
		serviceInfoData.setIsDelete("1");
		hibernateDao.update(null, serviceInfoData);
		return true;
	}

	public boolean editServiceInfo(ServiceInfoData serviceInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, serviceInfoData);
		return true;
	}

	public ServiceInfoData searchServiceInfo(String serviceId) {
		// TODO Auto-generated method stub
		ServiceInfoData goodInfoData = (ServiceInfoData) hibernateDao.queryData(serviceId, ServiceInfoData.class);
		return goodInfoData;
	}

	@SuppressWarnings("unchecked")
	public List<ServiceInfoData> searchAllServiceInfo(String storeId) {
		String sql = "from ServiceInfoData s where s.storeId = '" + storeId +"' order by s.createTime desc";
		List<ServiceInfoData> list = hibernateDao.queryList(sql, null);
		return list;
	}

}

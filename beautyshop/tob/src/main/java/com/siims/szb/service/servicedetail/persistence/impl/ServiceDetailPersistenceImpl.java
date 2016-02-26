package com.siims.szb.service.servicedetail.persistence.impl;

import java.util.List;





import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;

import com.siims.szb.service.servicedetail.data.ServiceDetailData;
import com.siims.szb.service.servicedetail.persistence.ServiceDetailPersistence;


@Singleton
@AutoBind(bindClass = ServiceDetailPersistence.class)
public class ServiceDetailPersistenceImpl implements ServiceDetailPersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public ServiceDetailPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addDetailInfo(ServiceDetailData detailInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, detailInfoData);
		return detailInfoData.getId();
	}

	public boolean delDetailInfo(ServiceDetailData detailInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.delete(null, detailInfoData);
		return true;
	}

	public boolean editDetailInfo(ServiceDetailData detailInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, detailInfoData);
		return true;
	}

	public ServiceDetailData searchDetailInfo(String detailId) {
		// TODO Auto-generated method stub
		ServiceDetailData goodInfoData = (ServiceDetailData) hibernateDao.queryData(detailId, ServiceDetailData.class);
		return goodInfoData;
	}

	@SuppressWarnings("unchecked")
	public List<ServiceDetailData> searchAllDetailInfo(String serviceConfigId) {
		String sql = "from ServiceDetailData s where s.serviceConfigId = '" + serviceConfigId +"' ";
		List<ServiceDetailData> list = hibernateDao.queryList(sql, null);
		return list;
	}

}

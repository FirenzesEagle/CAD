package com.siims.szb.vipcard.sbp.persistence.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.persistence.VipCardTypePersistence;

/**
 * VipCardType的持久化层实现类
 * @author libo
 * 2015-09-12
 */
@Singleton
@AutoBind(bindClass = VipCardTypePersistence.class)
@TransactionalContext
public class VipCardTypePersistenceImpl implements VipCardTypePersistence {
	
	//定义hibernate CRUD 操作对象
	private final BaseDao hibernateDao;

	//定义mybatis CRUD 操作对象
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	/**
	 * 构造函数初始化<br>
	 * 获得数据访问对象
	 * @param mybatis 数据访问对象
	 */
	@Inject
	public VipCardTypePersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	/**
	 * 新增一个VipCardData记录
	 * @author libo
	 * @param VipCardTypeData data
	 * 2015-09-12
	 */
	public void addVipCardTypeData(VipCardTypeData data) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, data);
		System.out.println("插入了一个VipCardType, ID为"+data.getId());
	}
	
	/**
	 * 根据商家的ID和TYPE查询一个TYPE的Data
	 * @author libo
	 * @param storeUserId 和 type
	 * 2015-09-13
	 */
	@SuppressWarnings("unchecked")
	public List<VipCardTypeData> queryByStoreUserIdAndType(String storeUserId,
			int type) {
		// TODO Auto-generated method stub
		String sql = "from VipCardTypeData vipcardtype where vipcardtype.storeUserId ='" + storeUserId + "' AND vipcardtype.type ="+type;
		List<VipCardTypeData> list = hibernateDao.queryList(sql, null);
		if(list.size() > 0)
			return list;
		else
			return null;
	}
	
	/**
	 * 根据ID返回信息
	 * @author libo
	 * 2015-09-18
	 */
	public VipCardTypeData queryVipCardTypeDataById(String typeId) {
		return (VipCardTypeData) hibernateDao.queryData(typeId, VipCardTypeData.class);
	}
	
	/**
	 * 查询出所有的未删除的会员卡类型
	 * @author libo
	 * 2015-09-19
	 */
	@SuppressWarnings("unchecked")
	public List<VipCardTypeData> queryAllValidVipCardTypeData() {
		String hql = "from VipCardTypeData vipcardtype where 1=1 And vipcardtype.isdelete=0";
		List<VipCardTypeData> list = hibernateDao.queryList(hql,null);
		return list;
	}
	
	/**
	 * 根据商家的userId查询出所有的typedata
	 */
	public List<VipCardTypeData> queryAllValidVipcardTypeDataByStoreUserId(String storeUserId) {
		String sql = "from VipCardTypeData vipcardtype where vipcardtype.storeUserId ='" + storeUserId + "'" ;
		List<VipCardTypeData> list = hibernateDao.queryList(sql, null);
		if(list.size() > 0)
			return list;
		else
			return null;
	}
}

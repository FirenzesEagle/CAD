package com.siims.szb.vipcard.sbp.persistence.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.persistence.VipCardConfigPersistence;
import com.siims.szb.vipcard.sbp.persistence.VipCardTypePersistence;

/**
 * VipCardConfigPersistence的实现类
 * @author libo
 * 2015-09-14
 */
@Singleton
@AutoBind(bindClass = VipCardConfigPersistence.class)
@TransactionalContext
public class VipCardConfigPersistenceImpl implements VipCardConfigPersistence {
	
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
	public VipCardConfigPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	
	/**
	 * 根据会员卡的类型ID查询出该类型ID的所有规格
	 * @author libo
	 * 2015-09-14
	 * @param String typeId
	 */
	public List<VipCardConfigData> getConfigDataByTypeId(String typeId) {
		// TODO Auto-generated method stub
		String hql = "from VipCardConfigData configdata where configdata.typeId ='" + typeId + "'";
		List<VipCardConfigData> list = hibernateDao.queryList(hql, null);
		return list;
	}
	
	/**
	 * 根据规格的价格，打折或者返现或者次数，以及Type，以及TypeID添加一行规则，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param VipCardConfigData data
	 */
	public String addVipCardConfigData(VipCardConfigData data) {
		data.setTime(new Date());
		hibernateDao.insert(null, data);
		return data.getId();
	}
	
	/**
	 * 根据会员卡规格ID查出规格详情
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public VipCardConfigData queryVipCardDataByConfigId(String configId) {
		return (VipCardConfigData) hibernateDao.queryData(configId, VipCardConfigData.class);
	}
	
	/**
	 * 更新会员卡规格的信息
	 * @author libo
	 * 2015-09-17
	 */
	public boolean updateVipCardConfigData(VipCardConfigData data) {
		hibernateDao.update(null, data);
		return true;
	}

}

package com.siims.szb.vipcard.sbp.persistence.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;
import com.siims.szb.vipcard.sbp.persistence.ConsumerVipCardPersistence;
import com.siims.szb.vipcard.sbp.persistence.VipCardBillPersistence;

/**
 * VipCardBillPersistence的实现类
 * @author libo <br/>
 * 2015-09-16
 */
@Singleton
@AutoBind(bindClass = VipCardBillPersistence.class)
@TransactionalContext
public class VipCardBillPersistenceImpl implements VipCardBillPersistence {
	
	//定义hibernate CRUD 操作对象
	private final BaseDao hibernateDao;

	//定义mybatis CRUD 操作对象
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	/**
	* 构造函数初始化<br>
	* 得数据访问对象
	* @param mybatis 数据访问对象
	*/
	@Inject
	public VipCardBillPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	
	/**
	 * 插入一条记录，返回插入的ID
	 * @author libo <br/>
	 * 2015-09-16
	 * @param VipCardBillData data
	 */
	public String addVipCardBillData(VipCardBillData data) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, data);
		return data.getId();
	}
	
	/**
	 * 根据会员卡的ID来查询所有的流水，并根据流水的日期由近及远的排序
	 * @author libo <br/>
	 * 2015-09-16
	 */
	public List<VipCardBillData> queryVipCardBillDataListByVipCardId(String vipCardId) {
		String hql = "from VipCardBillData bill where bill.vipCardId ='"+vipCardId+"'";
		hql += " Order by bill.time DESC";
		List<VipCardBillData> list = hibernateDao.queryList(hql, null);
		return list;
	}
	
	/**
	 * 根据交易的类型进行查询
	 * @author libo
	 * 2015-09-20
	 */
	public List<VipCardBillData> queryVipCardBillDataListByType(int type) {
		String hql = "from VipCardBillData bill where bill.consumeType ="+type+"";
		hql += " Order by bill.time DESC";
		List<VipCardBillData> list = hibernateDao.queryList(hql, null);
		return list;
	}
	/**
	 * 根据会员卡的ID和交易类型查询
	 */
	public List<VipCardBillData> queryVipCardBillDataListByCardIdAndType(String cardId, int type) {
		String hql = "from VipCardBillData bill where bill.consumeType ="+type+" ";
		hql += " AND bill.vipCardId='"+cardId+"' ";
		hql += " Order by bill.time DESC";
		List<VipCardBillData> list = hibernateDao.queryList(hql, null);
		return list;
	}

}

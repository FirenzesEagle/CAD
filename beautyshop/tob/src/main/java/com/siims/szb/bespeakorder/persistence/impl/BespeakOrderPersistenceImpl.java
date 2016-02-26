package com.siims.szb.bespeakorder.persistence.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.persistence.BespeakOrderPersistence;

@Singleton
@AutoBind(bindClass = BespeakOrderPersistence.class)
public class BespeakOrderPersistenceImpl implements BespeakOrderPersistence{
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
	public BespeakOrderPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}

	public String AddBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, data);
		return data.getId();
	}

	public void DeleteBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		data.setIsdelete(0);
		hibernateDao.update(null, data);
	}

	public void EditBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, data);
	}

	@SuppressWarnings("unchecked")
	public List<BespeakOrderData> GetBespeakOrderForCustomer(int state,
			String personid) {
		// TODO Auto-generated method stub
		String sql = "from BespeakOrderData bod where bod.isdelete = 1 and bod.state = '" + state + "' and bod.personid = '" + personid + "' order by bod.createtime desc";
		return hibernateDao.queryList(sql, null);
	}

	@SuppressWarnings("unchecked")
	public List<BespeakOrderData> GetBespeakOrderForShop(int state,
			String shopid) {
		// TODO Auto-generated method stub
		String sql = "from BespeakOrderData bod where bod.isdelete = 1 and bod.state = '" + state + "' and bod.shopid = '" + shopid + "' order by bod.createtime desc";
		return hibernateDao.queryList(sql, null);
	}

	@SuppressWarnings("unchecked")
	public List<BespeakOrderData> GetBespeakOrderByKey(String key, String shopid) {
		// TODO Auto-generated method stub
		String sql = "from BespeakOrderData bod where bod.isdelete = 1 and bod.shopid = '" + shopid + "' and (bod.id like '%" + key + "%' or bod.servicename like '%" + key + "%' or bod.personname like '%" + key + "%') order by bod.createtime desc";
		return hibernateDao.queryList(sql, null);
	}

	public BespeakOrderData GetBespeakOrderById(String id) {
		// TODO Auto-generated method stub
		return (BespeakOrderData) hibernateDao.queryData(id, BespeakOrderData.class);
	}

	@SuppressWarnings("unchecked")
	public List<BespeakOrderData> GetAllBespeakOrder(String shopid) {
		// TODO Auto-generated method stub
		String sql = "from BespeakOrderData bod where bod.isdelete = 1 and bod.shopid = '" + shopid + "'";
		return hibernateDao.queryList(sql, null);
	}

	@SuppressWarnings("unchecked")
	public List<BespeakOrderData> GetBespeaorderByBespeak(String bespeakid) {
		// TODO Auto-generated method stub
		String sql = "from BespeakOrderData bod where bod.isdelete = 1 and bod.recordid = '" + bespeakid + "'";
		return hibernateDao.queryList(sql, null);
	}
	
	
}

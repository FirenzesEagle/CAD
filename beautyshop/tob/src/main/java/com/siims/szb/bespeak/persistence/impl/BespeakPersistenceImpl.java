package com.siims.szb.bespeak.persistence.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.bespeak.data.BespeakData;
import com.siims.szb.bespeak.persistence.BespeakPersistence;

@Singleton
@AutoBind(bindClass = BespeakPersistence.class)
public class BespeakPersistenceImpl implements BespeakPersistence{

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
	public BespeakPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String AddBespeakRecord(BespeakData data) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, data);
		return data.getId();
	}

	@SuppressWarnings("unchecked")
	public List<BespeakData> GetBespeakByDate(String date,String shopid) {
		// TODO Auto-generated method stub
		String sql = "from BespeakData bd where bd.isdelete = 1 and bd.date = '" + date + "'and bd.shopid = '" + shopid + "' order by bd.line";
		return hibernateDao.queryList(sql, null);
	}

	@SuppressWarnings("unchecked")
	public List<BespeakData> GetBespeakByPoint(String date, int line, int row) {
		// TODO Auto-generated method stub
		String sql = "from BespeakData bd where bd.isdelete = 1 and bd.date = '" + date + "' and bd.line = '" + line + "' and bd.row = '" + row + "'";
		return hibernateDao.queryList(sql, null);
	}

	public BespeakData GetBespeakById(String id) {
		// TODO Auto-generated method stub
		return (BespeakData) hibernateDao.queryData(id, BespeakData.class);
	}
	
	public void ChangeBespeakState(BespeakData data) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, data);
	}

	public void DeleteBespeak(BespeakData data) {
		// TODO Auto-generated method stub
		data.setIsdelete(0);
		hibernateDao.update(null, data);
	}

}

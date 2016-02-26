package com.siims.szb.tixianManager.persistence.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.tixianManager.data.TiXianMoneyData;
import com.siims.szb.tixianManager.persistence.TiXianMoneyPersistence;


@Singleton
@AutoBind(bindClass = TiXianMoneyPersistence.class)
@TransactionalContext
public class TiXianMoneyPersistenceImpl implements TiXianMoneyPersistence {
	
private final BaseDao hibernateDao;
	
	@Inject
	public TiXianMoneyPersistenceImpl(@Named("hibernateDao") BaseDao hibernate) {
		this.hibernateDao = hibernate;
	}

	@SuppressWarnings("unchecked")
	public List<TiXianMoneyData> getTixianMoney(String shoperId){
		String hql = "from TiXianMoneyData f where f.shoperId = '" + shoperId +"'";		
		List<TiXianMoneyData> list  = hibernateDao.queryList(hql, null);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void updateTixianMoney(String shoperId,float money){
		//TiXianMoneyData result = (TiXianMoneyData)hibernateDao.queryData(shoperId, TiXianMoneyData.class);
		String hql = "from TiXianMoneyData f where f.shoperId = '" + shoperId +"'";		
		List<TiXianMoneyData> result  = hibernateDao.queryList(hql, null);
		int size = result.size();
		if(size == 0){
			TiXianMoneyData tixian = new TiXianMoneyData(shoperId);
			tixian.setMoney(money);
			hibernateDao.insert(null, tixian);
			
		}
		else if(size == 1){
			TiXianMoneyData res = result.get(0);
			res.setMoney(res.getMoney() + money);
			hibernateDao.update(null, res);
		}
	}
}

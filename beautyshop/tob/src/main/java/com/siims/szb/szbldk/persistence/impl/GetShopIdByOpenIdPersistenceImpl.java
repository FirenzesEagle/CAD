package com.siims.szb.szbldk.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
//import com.siims.szb.order.data.OrderGoods;
//import com.siims.szb.order.persistence.OrderPersistence;
import com.siims.szb.szbldk.data.ShoppingTrollyData;
import com.siims.szb.szbldk.persistence.GetShopIdByOpenIdPersistence;

@Singleton
@AutoBind(bindClass = GetShopIdByOpenIdPersistence.class)
@TransactionalContext
public class GetShopIdByOpenIdPersistenceImpl implements GetShopIdByOpenIdPersistence{
	
	private final BaseDao hibernateDao;
	
	@Inject
	public GetShopIdByOpenIdPersistenceImpl(@Named("hibernateDao") BaseDao hibernate) {
		this.hibernateDao = hibernate;
	}

	public List<Map<String, String>> getShopIdByOpenId(String data) {
		// TODO Auto-generated method stub
		String hql = "from ShoppingTrollyData where openid = '"+data+"'";
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> result = hibernateDao.queryList(hql, null);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for (int i = 0; i < result.size(); i++) {
			ShoppingTrollyData og = result.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("shopId", og.getShop_id());
			list.add(map);
		}
		return list;
	}

}

package com.siims.szb.tixianManager.persistence.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
//import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.tixianManager.data.TiXianData;
import com.siims.szb.tixianManager.persistence.TiXianPersistence;

@Singleton
@AutoBind(bindClass = TiXianPersistence.class)
@TransactionalContext
public class TiXianPersistenceImpl implements TiXianPersistence{
	
	private final BaseDao hibernateDao;
	
	@Inject
	public TiXianPersistenceImpl(@Named("hibernateDao") BaseDao hibernate) {
		this.hibernateDao = hibernate;
	}

	public String tixianRequest(String shoperId,String shoperName,String zhiFuBaoAccount,int type,String secret,float money,String cardType,String cardNumber) {
		// TODO Auto-generated method stub
		TiXianData tixian = new TiXianData(shoperId);
		tixian.setShoperName(shoperName);
		tixian.setZhiFuBaoAccount(zhiFuBaoAccount);
		tixian.setType(type);
		tixian.setSZBSecret(secret);
		tixian.setMoney(money);
		tixian.setCardType(cardType);
		tixian.setCardNumber(cardNumber);
		hibernateDao.insert(null, tixian);
		return tixian.getId();
	}

	public List<TiXianData> getTixianRequest(){
		@SuppressWarnings("unchecked")
		List<TiXianData> result = hibernateDao.queryList("from TiXianData", null);
		return result;
	}
	
	public List<Map<String, String>> getTixianRequestByShopId(String shoperId){
		@SuppressWarnings("unchecked")
		List<TiXianData> result = hibernateDao.queryList("from TiXianData t where t.shoperId = '" + shoperId +"'", null);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(int i = 0; i < result.size(); i++){
			Map<String, String> map = new HashMap<String, String>();
			TiXianData og = result.get(i);
			map.put("id", og.getId());
			map.put("type", Integer.toString(og.getType()));
			map.put("shoperId", og.getShoperId());
			map.put("cardType", og.getCardType());
			map.put("money", Float.toString(og.getMoney()));
			map.put("time", new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(result.get(i).getTime()));
			list.add(map);
		}
		return list;
	}
	
}

package com.siims.szb.bespeakorder.business.impl;

import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.bespeakorder.business.BespeakOrderBusiness;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.persistence.BespeakOrderPersistence;

@Singleton
@AutoBind(bindClass = BespeakOrderBusiness.class)
public class BespeakOrderBusinessImpl implements BespeakOrderBusiness{

	public String AddBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).AddBespeakOrder(data);
	}

	public void DeleteBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakOrderPersistence.class).DeleteBespeakOrder(data);
	}

	public void EditBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakOrderPersistence.class).EditBespeakOrder(data);
	}

	public List<BespeakOrderData> GetBespeakOrderForCustomer(int state,
			String personid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).GetBespeakOrderForCustomer(state, personid);
	}

	public List<BespeakOrderData> GetBespeakOrderForShop(int state,
			String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).GetBespeakOrderForShop(state, shopid);
	}

	public List<BespeakOrderData> GetBespeakOrderByKey(String key, String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).GetBespeakOrderByKey(key, shopid);
	}

	public BespeakOrderData GetBespeakOrderById(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).GetBespeakOrderById(id);
	}

	public List<BespeakOrderData> GetAllBespeakOrder(String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).GetAllBespeakOrder(shopid);
	}

	public List<BespeakOrderData> GetBespeaorderByBespeak(String bespeakid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderPersistence.class).GetBespeaorderByBespeak(bespeakid);
	}

}

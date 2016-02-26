package com.siims.szb.bespeakorder.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.bespeakorder.business.BespeakOrderBusiness;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.service.BespeakOrderService;

@Singleton
@AutoBind(bindClass = BespeakOrderService.class)
@TransactionalContext
public class BespeakOrderServiceImpl implements BespeakOrderService{

	public String AddBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		data.setCreatetime(new Date());
		data.setIsdelete(1);
		return ServiceContext.get(BespeakOrderBusiness.class).AddBespeakOrder(data);
	}

	public void DeleteBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakOrderBusiness.class).DeleteBespeakOrder(data);
	}

	public void EditBespeakOrder(BespeakOrderData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(BespeakOrderBusiness.class).EditBespeakOrder(data);
	}

	public List<BespeakOrderData> GetBespeakOrderForCustomer(int state,
			String personid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderBusiness.class).GetBespeakOrderForCustomer(state, personid);
	}

	public List<BespeakOrderData> GetBespeakOrderForShop(int state,
			String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderBusiness.class).GetBespeakOrderForShop(state, shopid);
	}

	public List<BespeakOrderData> GetBespeakOrderByKey(String key, String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderBusiness.class).GetBespeakOrderByKey(key, shopid);
	}

	public BespeakOrderData GetBespeakOrderById(String id) {
		// TODO Auto-generated method stub
		return  ServiceContext.get(BespeakOrderBusiness.class).GetBespeakOrderById(id);
	}

	public List<BespeakOrderData> GetAllBespeakOrder(String shopid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderBusiness.class).GetAllBespeakOrder(shopid);
	}

	public List<BespeakOrderData> GetBespeaorderByBespeak(String bespeakid) {
		// TODO Auto-generated method stub
		return ServiceContext.get(BespeakOrderBusiness.class).GetBespeaorderByBespeak(bespeakid);
	}

}

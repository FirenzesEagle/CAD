package com.siims.szb.tixianManager.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.tixianManager.business.TiXianMoneyBusiness;
import com.siims.szb.tixianManager.data.TiXianMoneyData;
import com.siims.szb.tixianManager.persistence.TiXianMoneyPersistence;


@Singleton
@AutoBind(bindClass = TiXianMoneyBusiness.class)
@TransactionalContext
public class TiXianMoneyBusinessImpl  implements TiXianMoneyBusiness{

	public List<TiXianMoneyData> getTixianMoney(String shoperId){
		return ServiceContext.get(TiXianMoneyPersistence.class).getTixianMoney(shoperId);
	}
	
	public void updateTixianMoney(String shoperId,float money){
		ServiceContext.get(TiXianMoneyPersistence.class).updateTixianMoney(shoperId,money);
	}
}

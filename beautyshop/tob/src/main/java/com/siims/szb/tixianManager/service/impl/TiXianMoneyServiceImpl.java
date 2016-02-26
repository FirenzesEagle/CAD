package com.siims.szb.tixianManager.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.tixianManager.business.TiXianMoneyBusiness;
import com.siims.szb.tixianManager.data.TiXianMoneyData;
import com.siims.szb.tixianManager.service.TiXianMoneyService;


@Singleton
@AutoBind(bindClass = TiXianMoneyService.class)
@TransactionalContext
public class TiXianMoneyServiceImpl implements TiXianMoneyService{
	
	
	public List<TiXianMoneyData> getTixianMoney(String shoperId){
		return ServiceContext.get(TiXianMoneyBusiness.class).getTixianMoney(shoperId);
	}
	
	public void updateTixianMoney(String shoperId,float money){
		ServiceContext.get(TiXianMoneyBusiness.class).updateTixianMoney(shoperId,money);
	}

}

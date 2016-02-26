package com.siims.szb.tixianManager.service.impl;

import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.tixianManager.business.TiXianBusiness;
import com.siims.szb.tixianManager.data.TiXianData;
import com.siims.szb.tixianManager.service.TiXianService;


@Singleton
@AutoBind(bindClass = TiXianService.class)
@TransactionalContext
public class TiXianServiceImpl implements TiXianService{
	
	public String tixianRequest(String shoperId,String shoperName,String zhiFuBaoAccount,int type,String secret,float money,String cardType,String cardNumber){
		
		return ServiceContext.get(TiXianBusiness.class).tixianRequest(shoperId,shoperName,zhiFuBaoAccount,type,secret,money,cardType,cardNumber);
		
	}
	
	public List<TiXianData> getTixianRequest(){
		
		return ServiceContext.get(TiXianBusiness.class).getTixianRequest();
		
	}
	
	public List<Map<String, String>> getTixianRequestByShopId(String shoperId){
		
		return ServiceContext.get(TiXianBusiness.class).getTixianRequestByShopId(shoperId);
		
	}
}

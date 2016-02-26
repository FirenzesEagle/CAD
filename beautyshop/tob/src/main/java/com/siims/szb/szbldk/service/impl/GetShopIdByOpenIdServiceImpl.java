package com.siims.szb.szbldk.service.impl;

import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.szbldk.business.GetShopIdByOpenIdBusiness;
import com.siims.szb.szbldk.service.GetShopIdByOpenIdService;

@Singleton
@AutoBind(bindClass = GetShopIdByOpenIdService.class)
@TransactionalContext
public class GetShopIdByOpenIdServiceImpl implements GetShopIdByOpenIdService  {

	public List<Map<String, String>> getShopIdByOpenId(String data) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GetShopIdByOpenIdBusiness.class).getShopIdByOpenId(data);
	}

}

package com.siims.szb.szbldk.business.impl;


import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.szbldk.business.ShoppingTrollyBusiness;
import com.siims.szb.szbldk.data.ShoppingTrollyData;
import com.siims.szb.szbldk.persistence.ShoppingTrollyPersistence;
import com.siims.szb.szbldk.service.ShoppingTrollyService;
@Singleton
@AutoBind(bindClass = ShoppingTrollyBusiness.class)
public class ShoppingTrollyBusinessImpl implements ShoppingTrollyBusiness{

	public ShoppingTrollyData addShoppingTrolly(ShoppingTrollyData data) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShoppingTrollyPersistence.class).addShoppingTrolly(data);
	}

	public List<List<ShoppingTrollyData>> getAllShoppingTrollyDataByUserid(
			String user_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).getAllShoppingTrollyDataByOpenid(user_id);
	}

	public int emptyShoppingTrolly(String user_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).emptyShoppingTrolly(user_id);
	}

	public List<ShoppingTrollyData> getShoppingTrollyByStore(String user_id,
			String shop_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).getShoppingTrollyByStore(user_id, shop_id);
	}

	public int deleteShoppingTrollyData(ShoppingTrollyData sd) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).deleteShoppingTrollyData(sd);
	}

	public float getShoppingTrollySum(String user_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).getShoppingTrollySum(user_id);
	}
}

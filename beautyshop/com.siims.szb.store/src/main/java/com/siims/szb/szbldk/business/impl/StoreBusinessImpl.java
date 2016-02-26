package com.siims.szb.szbldk.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.szbldk.business.StoreBusiness;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;

@Singleton
@AutoBind(bindClass = StoreBusiness.class)
public class StoreBusinessImpl implements StoreBusiness{

	public String addStoreInfo(StoreInfoData storeInfoData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(StorePersistence.class).addStoreInfo(storeInfoData);
	}

	public void delStore(StoreInfoData storeInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(StorePersistence.class).delStore(storeInfoData);
	}


	public List<StoreInfoData> getAllStoreByDistrictId(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(StorePersistence.class).getAllStoreByDistrictId(id);
	}

	public StoreInfoData getStoreInfoByStoreId(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(StorePersistence.class).getStoreInfoByStoreId(id);
	}

	public List<StoreInfoData> getStoreByUserId(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(StorePersistence.class).getStoreByUserId(id);
	}

	public StoreInfoData storeLogin(StoreInfoData data) {
		
		return ServiceContext.get(StorePersistence.class).storeLogin(data);
	}

	public StoreInfoData storeLoginPC(StoreInfoData data) {
		
		return  ServiceContext.get(StorePersistence.class).storeLoginPC(data);
	}

	public StoreInfoData editStoreInfo(String shop_id, int which, String info) {
		// TODO Auto-generated method stub
		return ServiceContext.get(StorePersistence.class).editStoreInfo(shop_id, which, info);
	}
	
	public StoreInfoData confirmAuth(StoreInfoData data) {
		
		return ServiceContext.get(StorePersistence.class).confirmAuth(data);
	}
}

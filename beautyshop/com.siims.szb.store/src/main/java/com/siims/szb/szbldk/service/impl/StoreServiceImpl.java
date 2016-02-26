package com.siims.szb.szbldk.service.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.szbldk.business.StoreBusiness;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.szbldk.service.StoreService;

@Singleton
@AutoBind(bindClass = StoreService.class)
@TransactionalContext
public class StoreServiceImpl implements StoreService{

	public String addStoreInfo(StoreInfoData storeInfoData) {
		// TODO Auto-generated method stub
		//设置系统默认的数据
		storeInfoData.setIsdelete(1);
		storeInfoData.setCreatetime(new Date());
		storeInfoData.setScore_average(0);
		storeInfoData.setScore_first(0);
		storeInfoData.setScore_second(0);
		storeInfoData.setScore_third(0);
		storeInfoData.setDistribution(0);
		storeInfoData.setPrice_least(0);
		return ServiceContext.get(StoreBusiness.class).addStoreInfo(storeInfoData);
	}

	public void delStore(StoreInfoData storeInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(StoreBusiness.class).delStore(storeInfoData);
	}

	public StoreInfoData editStoreInfo(String shop_id,int which,String info)  {
		// TODO Auto-generated method stub
		return ServiceContext.get(StoreBusiness.class).editStoreInfo(shop_id,which,info);
	}

	public List<StoreInfoData> getAllStoreByDistrictId(String id) {
		// TODO Auto-generated method stub
		if(id.contains("'") || id.contains("<") || id.contains(">"))
		{
			return null;
		}else{
			return ServiceContext.get(StoreBusiness.class).getAllStoreByDistrictId(id);
		}
	}

	public StoreInfoData getStoreInfoByStoreId(String id) {
		// TODO Auto-generated method stub
		if(id.contains("'") || id.contains("<") || id.contains(">"))
		{
			return null;
		}else{
			return ServiceContext.get(StoreBusiness.class).getStoreInfoByStoreId(id);
		}
	}

	public List<StoreInfoData> getStoreByUserId(String id) {
		// TODO Auto-generated method stub
		if(id.contains("'") || id.contains("<") || id.contains(">"))
		{
			return null;
		}else{
			return ServiceContext.get(StoreBusiness.class).getStoreByUserId(id);
		}
	}

	public StoreInfoData storeLogin(StoreInfoData data) {
		
		return ServiceContext.get(StoreBusiness.class).storeLogin(data);
	}

	public StoreInfoData storeLoginPC(StoreInfoData data) {
		
		return ServiceContext.get(StoreBusiness.class).storeLoginPC(data);
	}

	public List<StoreInfoData> getShopGroupByOpenid(String openid) {
		
		return ServiceContext.get(StorePersistence.class).getShopGroupByOpenid(openid);
	}
	
	public StoreInfoData confirmAuth(StoreInfoData data) {
		
		return ServiceContext.get(StoreBusiness.class).confirmAuth(data);
	}
}

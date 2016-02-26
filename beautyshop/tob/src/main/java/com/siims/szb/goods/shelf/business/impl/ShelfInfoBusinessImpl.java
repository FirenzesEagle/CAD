package com.siims.szb.goods.shelf.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.commodityconfig.business.CommodityConfigBusiness;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.persistence.CommodityConfigPersistence;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;
import com.siims.szb.goods.shelf.business.ShelfInfoBusiness;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.persistence.ShelfInfoPersistence;

@Singleton
@AutoBind(bindClass = ShelfInfoBusiness.class)
public class ShelfInfoBusinessImpl implements ShelfInfoBusiness{

	

	public String addShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ShelfInfoPersistence.class).addShelfInfo(shelfInfoData);
		return shelfInfoData.getId();
	}

	public boolean delShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ShelfInfoPersistence.class).delShelfInfo(shelfInfoData);
		return true;
	}

	public boolean editShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ShelfInfoPersistence.class).editShelfInfo(shelfInfoData);
		return true;
	}

	public ShelfInfoData searchShelfInfo(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoPersistence.class).searchShelfInfo(id);
	}

	public List<ShelfInfoData> searchAllShelfInfo(String storeId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoPersistence.class).searchAllShelfInfo(storeId);
	}

	public List<ShelfInfoData> searchShelfInfoByConfigId(String configId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoPersistence.class).searchShelfInfoByConfigId(configId);
	}

}

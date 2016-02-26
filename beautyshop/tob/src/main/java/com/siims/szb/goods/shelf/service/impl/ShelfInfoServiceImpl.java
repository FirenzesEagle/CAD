package com.siims.szb.goods.shelf.service.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.goods.commodityconfig.business.CommodityConfigBusiness;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;
import com.siims.szb.goods.shelf.business.ShelfInfoBusiness;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.service.ShelfInfoService;

@Singleton
@AutoBind(bindClass = ShelfInfoService.class)
@TransactionalContext
public class ShelfInfoServiceImpl implements ShelfInfoService{


	public String addShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoBusiness.class).addShelfInfo(shelfInfoData);
	}

	public boolean delShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoBusiness.class).delShelfInfo(shelfInfoData);
	}

	public boolean editShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoBusiness.class).editShelfInfo(shelfInfoData);
	}

	public ShelfInfoData searchShelfInfo(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoBusiness.class).searchShelfInfo(id);
	}

	public List<ShelfInfoData> searchAllShelfInfo(String storeId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoBusiness.class).searchAllShelfInfo(storeId);
	}

	public List<ShelfInfoData> searchShelfInfoByConfigId(String configId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ShelfInfoBusiness.class).searchShelfInfoByConfigId(configId);
	}

}

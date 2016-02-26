package com.siims.szb.goods.commodityconfig.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.commodityconfig.business.CommodityConfigBusiness;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.persistence.CommodityConfigPersistence;

@Singleton
@AutoBind(bindClass = CommodityConfigBusiness.class)
public class CommodityConfigBusinessImpl implements CommodityConfigBusiness{

	public String addCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigPersistence.class).addCommodityConfig(commodityConfigData);
	}

	public boolean delCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigPersistence.class).delCommodityConfig(commodityConfigData);
	}

	public boolean editCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigPersistence.class).editCommodityConfig(commodityConfigData);
	}

	public CommodityConfigData getCommodityConfig(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigPersistence.class).getCommodityConfig(id);
	}

	public List<CommodityConfigData> getAllCommodityConfig(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigPersistence.class).getAllCommodityConfig(id);
	}

	public List<CommodityConfigData> getCommodityType(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigPersistence.class).getCommodityType(id);
	}

}

package com.siims.szb.goods.commodityconfig.service.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.goods.commodityconfig.business.CommodityConfigBusiness;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;

@Singleton
@AutoBind(bindClass = CommodityConfigService.class)
@TransactionalContext
public class CommodityConfigServiceImpl implements CommodityConfigService{

	public String addCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		//commodityConfigData.setCreateTime(new Date());
		commodityConfigData.setIsDelete("0");
		return ServiceContext.get(CommodityConfigBusiness.class).addCommodityConfig(commodityConfigData);
	}

	public  boolean delCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigBusiness.class).delCommodityConfig(commodityConfigData);
	}

	public boolean editCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigBusiness.class).editCommodityConfig(commodityConfigData);
	}

	public CommodityConfigData getCommodityConfig(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigBusiness.class).getCommodityConfig(id);
	}

	public List<CommodityConfigData> getAllCommodityConfig(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigBusiness.class).getAllCommodityConfig(id);
	}

	public List<CommodityConfigData> getCommodityType(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(CommodityConfigBusiness.class).getCommodityType(id);
	}

}

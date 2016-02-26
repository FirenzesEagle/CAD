package com.siims.szb.goods.goods.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.goods.goods.business.GoodsBusiness;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;


@Singleton
@AutoBind(bindClass = GoodsService.class)
@TransactionalContext
public class GoodsServiceImpl implements GoodsService {

	public String addGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
//		demoData.setShop_id("1");
//		demoData.setFood_recommend(0);
//		demoData.setFood_type("FOOD");
		ServiceContext.get(GoodsBusiness.class).addGoodsInfo(goodsInfoData);
//		return demoData.getId();
		return goodsInfoData.getId();
	}

	public boolean delGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsBusiness.class).delGoodsInfo(goodsInfoData);
		return true;
	}

	public boolean editGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsBusiness.class).editGoodsInfo(goodsInfoData);
		return true;
	}

	public GoodsInfoData searchGoodsInfo(String goodsID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsBusiness.class).searchGoodsInfo(goodsID);
	}

	public List<GoodsInfoData> searchAllGoodsInfo(String pageID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsBusiness.class).searchAllGoodsInfo(pageID);
	}

	

}

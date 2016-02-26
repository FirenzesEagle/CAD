package com.siims.szb.goods.goods.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.goods.business.GoodsBusiness;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;


@Singleton
@AutoBind(bindClass = GoodsBusiness.class)
public class GoodsBusinessImpl implements GoodsBusiness{

	public String addGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsPersistence.class).addGoodsInfo(goodsInfoData);
		return goodsInfoData.getId();
	}

	public boolean delGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsPersistence.class).delGoodsInfo(goodsInfoData);
		return true;
	}

	public boolean editGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsPersistence.class).editGoodsInfo(goodsInfoData);
		return true;
	}
	
	public GoodsInfoData searchGoodsInfo(String goodsID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsPersistence.class).searchGoodsInfo(goodsID);
	}

	public List<GoodsInfoData> searchAllGoodsInfo(String pageID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsPersistence.class).searchAllGoodsInfo(pageID);
	}

	

}

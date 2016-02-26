package com.siims.szb.goods.goodsdetail.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.commodityconfig.business.CommodityConfigBusiness;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.persistence.CommodityConfigPersistence;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;
import com.siims.szb.goods.goodsdetail.business.GoodsDetailBusiness;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.goodsdetail.persistence.GoodsDetailPersistence;
import com.siims.szb.goods.shelf.business.ShelfInfoBusiness;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.persistence.ShelfInfoPersistence;

@Singleton
@AutoBind(bindClass = GoodsDetailBusiness.class)
public class GoodsDetailBusinessImpl implements GoodsDetailBusiness{


	public String addDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsDetailPersistence.class).addDetailInfo(detailInfoData);
		return detailInfoData.getId();
	}

	public boolean delDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsDetailPersistence.class).delDetailInfo(detailInfoData);
		return true;
	}

	public boolean editDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(GoodsDetailPersistence.class).editDetailInfo(detailInfoData);
		return true;
	}

	public DetailInfoData searchDetailInfo(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailPersistence.class).searchDetailInfo(id);
	}

	public List<DetailInfoData> searchAllDetailInfo(String goodsId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailPersistence.class).searchAllDetailInfo(goodsId);
	}

	public DetailInfoData searchDetailInfoByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailPersistence.class).searchDetailInfoByGoodsId(goodsId);
	}

	public List<DetailInfoData> searchDetailInfoByGoodsConfigId(String goodsConfigId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailPersistence.class).searchDetailInfoByGoodsConfigId(goodsConfigId);
	}

}

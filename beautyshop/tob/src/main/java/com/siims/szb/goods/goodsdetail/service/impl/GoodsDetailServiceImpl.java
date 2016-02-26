package com.siims.szb.goods.goodsdetail.service.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.goods.commodityconfig.business.CommodityConfigBusiness;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;
import com.siims.szb.goods.goodsdetail.business.GoodsDetailBusiness;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.goodsdetail.service.GoodsDetailService;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.service.ShelfInfoService;

@Singleton
@AutoBind(bindClass = GoodsDetailService.class)
@TransactionalContext
public class GoodsDetailServiceImpl implements GoodsDetailService{


	public String addDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		
		return ServiceContext.get(GoodsDetailBusiness.class).addDetailInfo(detailInfoData);
	}

	public boolean delDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailBusiness.class).delDetailInfo(detailInfoData);
	}

	public boolean editDetailInfo(DetailInfoData detailInfoData) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailBusiness.class).editDetailInfo(detailInfoData);
	}

	public DetailInfoData searchDetailInfo(String id) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailBusiness.class).searchDetailInfo(id);
	}

	public List<DetailInfoData> searchDetailInfoByGoodsConfigId(String goodsId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailBusiness.class).searchDetailInfoByGoodsConfigId(goodsId);
	}

	public DetailInfoData searchDetailInfoByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return ServiceContext.get(GoodsDetailBusiness.class).searchDetailInfoByGoodsId(goodsId);
	}

}

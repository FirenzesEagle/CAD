package com.siims.szb.goods.goods.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.goods.goods.data.GoodsInfoData;


public interface GoodsService extends BaseService{
	
	public String addGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public boolean delGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public boolean editGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public GoodsInfoData searchGoodsInfo(String goodsID);
	
	
	public List<GoodsInfoData> searchAllGoodsInfo(String pageID);

}

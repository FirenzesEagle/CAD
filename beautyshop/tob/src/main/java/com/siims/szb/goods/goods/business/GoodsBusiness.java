package com.siims.szb.goods.goods.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.goods.goods.data.GoodsInfoData;

public interface GoodsBusiness extends BaseBusiness{
	
	public String addGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public boolean delGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public boolean editGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public GoodsInfoData searchGoodsInfo(String goodsID);
	
	
	public List<GoodsInfoData> searchAllGoodsInfo(String pageID);
}

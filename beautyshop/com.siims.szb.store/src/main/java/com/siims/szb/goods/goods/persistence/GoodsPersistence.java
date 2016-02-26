package com.siims.szb.goods.goods.persistence;

import java.util.List;






import com.siims.framework.base.BasePersistence;
import com.siims.szb.goods.goods.data.GoodsInfoData;

public interface GoodsPersistence extends BasePersistence<GoodsInfoData> {
	
	
	public String addGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public boolean delGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public boolean editGoodsInfo(GoodsInfoData goodsInfoData);
	
	
	public GoodsInfoData searchGoodsInfo(String goodsID);
	
	//pageId
	public List<GoodsInfoData> searchAllGoodsInfo(String pageID);
}

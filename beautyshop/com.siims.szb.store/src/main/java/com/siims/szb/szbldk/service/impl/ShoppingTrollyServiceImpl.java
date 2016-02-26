package com.siims.szb.szbldk.service.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.persistence.CommodityConfigPersistence;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;
import com.siims.szb.szbldk.business.ShoppingTrollyBusiness;
import com.siims.szb.szbldk.data.ShoppingTrollyData;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.ShoppingTrollyPersistence;
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.szbldk.service.ShoppingTrollyService;
import com.siims.szb.szbldk.utils.SzbConstant;

@Singleton
@AutoBind(bindClass = ShoppingTrollyService.class)
@TransactionalContext
public class ShoppingTrollyServiceImpl implements ShoppingTrollyService{

	public ShoppingTrollyData addShoppingTrolly(ShoppingTrollyData data) {
		if(data == null)
			return null;
		
		data.setStatus(SzbConstant.SHOPPING_TROLLY_ORDINARY);
		CommodityConfigData commodityConfigData = ServiceContext.get(CommodityConfigPersistence.class).getCommodityConfig(data.getItem_id());
		
		GoodsInfoData goodsInfoData = ServiceContext.get(GoodsPersistence.class).searchGoodsInfo(commodityConfigData.getGoodsId());
		data.setItem_name(goodsInfoData.getGoodsName());
		data.setItem_img_url(goodsInfoData.getGoodsShowImg());
		StoreInfoData storeInfoData = ServiceContext.get(StorePersistence.class).getStoreInfoByStoreId(goodsInfoData.getStoreId());
		data.setShop_name(storeInfoData.getName());
		data.setItem_spec(commodityConfigData.getConfigName());
		System.out.println(goodsInfoData.getGoodsName()+" ********************** "+storeInfoData.getName());
		
//		跟踪时间
		System.out.println(data.getCreate_time().toString());
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).addShoppingTrolly(data);
	}

	public List<List<ShoppingTrollyData>> getAllShoppingTrollyDataByOpenid(String openid) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).getAllShoppingTrollyDataByOpenid(openid);
	}


	public int emptyShoppingTrolly(String openid) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).emptyShoppingTrolly(openid);
	}

	public float getShoppingTrollySum(String user_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).getShoppingTrollySum(user_id);
	}

	public List<ShoppingTrollyData> getShoppingTrollyByStore(String user_id,
			String shop_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).getShoppingTrollyByStore(user_id, shop_id);
	}

	public int deleteShoppingTrollyData(ShoppingTrollyData sd) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).deleteShoppingTrollyData(sd);
	}
	
	public int deleteShoppingTrollyItem(String item_id) {
		
		return ServiceContext.get(ShoppingTrollyPersistence.class).deleteShoppingTrollyItem(item_id);
	}

}

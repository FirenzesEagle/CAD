package com.siims.szb.order.business.impl;

import java.util.Set;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.order.business.OrderGoodsBusiness;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.persistence.OrderGoodsPersistence;

@Singleton
@AutoBind(bindClass = OrderGoodsBusiness.class)
public class OrderGoodsBusinessImpl implements OrderGoodsBusiness {

	public String create(String orderId, String goodsId, String goodsconfigId, String shopId, String price, String num, String paytype){
		return ServiceContext.get(OrderGoodsPersistence.class).create(orderId, goodsId, goodsconfigId, shopId, price, num, paytype);
	}

	public ListFacade queryOrderGoods(String id){
		return ServiceContext.get(OrderGoodsPersistence.class).queryOrderGoods(id);
	}
	
	public Set<String> queryByShopId(String shopId){
		return ServiceContext.get(OrderGoodsPersistence.class).queryByShopId(shopId);
	}
	
	public ListFacade queryOrderGoodsByOrderId(String orderId){
		return ServiceContext.get(OrderGoodsPersistence.class).queryOrderGoodsByOrderId(orderId);
	}
	
	public ListFacade queryAllInfoByShopId(String shopId){
		return ServiceContext.get(OrderGoodsPersistence.class).queryAllInfoByShopId(shopId);
	}
}

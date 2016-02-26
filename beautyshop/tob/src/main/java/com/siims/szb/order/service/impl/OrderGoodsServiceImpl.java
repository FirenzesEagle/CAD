package com.siims.szb.order.service.impl;

import java.util.Set;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.order.business.OrderGoodsBusiness;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.service.OrderGoodsService;

@Singleton
@AutoBind(bindClass = OrderGoodsService.class)
@TransactionalContext
public class OrderGoodsServiceImpl implements OrderGoodsService{

	public String create(String orderId, String goodsId, String goodsconfigId, String shopId, String price, String num, String paytype){
		return ServiceContext.get(OrderGoodsBusiness.class).create(orderId, goodsId, goodsconfigId, shopId, price, num, paytype);
	}
	
	public ListFacade queryOrderGoods(String id){
		return ServiceContext.get(OrderGoodsBusiness.class).queryOrderGoods(id);
	}

	public Set<String> queryByShopId(String shopId){
		return ServiceContext.get(OrderGoodsBusiness.class).queryByShopId(shopId);
	}

	public ListFacade queryOrderGoodsByOrderId(String orderId){
		return ServiceContext.get(OrderGoodsBusiness.class).queryOrderGoodsByOrderId(orderId);
	}
	
	public ListFacade queryAllInfoByShopId(String shopId){
		return ServiceContext.get(OrderGoodsBusiness.class).queryAllInfoByShopId(shopId);
	}
}

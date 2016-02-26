package com.siims.szb.order.persistence;

import java.util.Set;

import com.siims.framework.base.BasePersistence;
import com.siims.szb.order.data.OrderGoods;
import com.siims.szb.order.facade.ListFacade;

public interface OrderGoodsPersistence extends BasePersistence<OrderGoods> {

	public String create(String orderId, String goodsId, String goodsconfigId, String shopId, String price, String num, String paytype);
	
	public ListFacade queryOrderGoods(String id);

	public ListFacade queryWithKeywordAction(Integer customerId, String key);
	
	public Set<String> queryByShopId(String shopId);
	
	public ListFacade queryOrderGoodsByOrderId(String orderId);

	public ListFacade queryAllInfoByShopId(String shopId);

}

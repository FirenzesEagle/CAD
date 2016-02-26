package com.siims.szb.order.persistence;

import java.util.List;

import com.siims.framework.base.BasePersistence;
import com.siims.szb.order.data.OrderCard;
import com.siims.szb.order.wrapper.CardWrapper;


public interface OrderCardPersistence extends BasePersistence<OrderCard> {

	public void create(String orderid, String cardid, String password, String money, String type);
	
	public List<CardWrapper> queryOrderCardWithOrderId(String orderid);
	
}

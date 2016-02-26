package com.siims.szb.order.business.impl;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.order.business.OrderCardBusiness;
import com.siims.szb.order.persistence.OrderCardPersistence;


@Singleton
@AutoBind(bindClass = OrderCardBusiness.class)
public class OrderCardBusinessImpl implements OrderCardBusiness{

	public void create(String orderid, String cardid, String password, String money, String type) {
		ServiceContext.get(OrderCardPersistence.class).create(orderid, cardid, password, money, type);
	}

}

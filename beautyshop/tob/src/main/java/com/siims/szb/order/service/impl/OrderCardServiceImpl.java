package com.siims.szb.order.service.impl;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.order.business.OrderCardBusiness;
import com.siims.szb.order.service.OrderCardService;

@Singleton
@AutoBind(bindClass = OrderCardService.class)
@TransactionalContext
public class OrderCardServiceImpl implements OrderCardService {

	public void create(String orderid, String cardid, String password, String money, String type) {
		ServiceContext.get(OrderCardBusiness.class).create(orderid, cardid, password, money, type);
	}

}

package com.siims.szb.order.service.impl;

import java.util.Set;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.order.business.OrderBusiness;
import com.siims.szb.order.data.Order;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.facade.MapFacade;
import com.siims.szb.order.service.OrderService;

@Singleton
@AutoBind(bindClass = OrderService.class)
@TransactionalContext
public class OrderServiceImpl implements OrderService {

	public String save(Order order){
		return ServiceContext.get(OrderBusiness.class).save(order);
	}

	public void delete(String id){
		ServiceContext.get(OrderBusiness.class).delete(id);
	}
	
	public Boolean updateStatus(String id, String status){
		return ServiceContext.get(OrderBusiness.class).updateStatus(id, status);
	}
	
	public MapFacade querybasic(String id){
		return ServiceContext.get(OrderBusiness.class).querybasic(id);
	}
	
	public ListFacade querybasicByCustomerId(String id, String status){
		return ServiceContext.get(OrderBusiness.class).querybasicByCustomerId(id, status);
	}
	
	public ListFacade queryWithOrderids(Set<String> orderids, String status){
		return ServiceContext.get(OrderBusiness.class).queryWithOrderids(orderids, status);
	}

}

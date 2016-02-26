package com.siims.szb.order.business.impl;

import java.util.Set;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.order.business.OrderBusiness;
import com.siims.szb.order.data.Order;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.facade.MapFacade;
import com.siims.szb.order.persistence.OrderPersistence;


@Singleton
@AutoBind(bindClass = OrderBusiness.class)
public class OrderBusinessImpl implements OrderBusiness{

	public String save(Order order){
		return ServiceContext.get(OrderPersistence.class).save(order);
	}

	public void delete(String id){
		ServiceContext.get(OrderPersistence.class).delete(id);
	}
	
	public Boolean updateStatus(String id, String status){
		return ServiceContext.get(OrderPersistence.class).updateStatus(id, status);
	}
	
	public MapFacade querybasic(String id){
		return ServiceContext.get(OrderPersistence.class).querybasic(id);
	}
	
	public ListFacade querybasicByCustomerId(String id, String status){
		return ServiceContext.get(OrderPersistence.class).querybasicByCustomerId(id, status);
	}

	public ListFacade queryWithOrderids(Set<String> orderids, String status){
		return ServiceContext.get(OrderPersistence.class).queryWithOrderids(orderids, status);
	}
}

package com.siims.szb.order.service;

import java.util.Set;

import com.siims.framework.base.BaseService;
import com.siims.szb.order.data.Order;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.facade.MapFacade;

public interface OrderService extends BaseService {

	public String save(Order order);
	
	public void delete(String id);

	public Boolean updateStatus(String id, String status);

	public MapFacade querybasic(String id);

	public ListFacade querybasicByCustomerId(String id, String status);
	
	public ListFacade queryWithOrderids(Set<String> orderids, String status);


}

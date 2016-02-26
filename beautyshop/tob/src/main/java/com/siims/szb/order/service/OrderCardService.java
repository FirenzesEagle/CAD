package com.siims.szb.order.service;

import com.siims.framework.base.BaseService;

public interface OrderCardService extends BaseService{

	public void create(String orderid, String cardid, String password, String money, String type);
	
}

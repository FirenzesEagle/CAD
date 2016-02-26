package com.siims.szb.order.business;

import com.siims.framework.base.BaseBusiness;

public interface OrderCardBusiness extends BaseBusiness {

	public void create(String orderid, String cardid, String password, String money, String type);
	
}

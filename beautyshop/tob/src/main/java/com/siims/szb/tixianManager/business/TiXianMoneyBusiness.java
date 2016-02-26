package com.siims.szb.tixianManager.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.tixianManager.data.TiXianMoneyData;

public interface TiXianMoneyBusiness extends BaseBusiness{
	public List<TiXianMoneyData> getTixianMoney(String shoperId);
	public void updateTixianMoney(String shoperId,float money);
}

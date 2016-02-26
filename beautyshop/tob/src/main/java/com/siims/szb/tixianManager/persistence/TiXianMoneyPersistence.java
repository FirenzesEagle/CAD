package com.siims.szb.tixianManager.persistence;

import java.util.List;

import com.siims.framework.base.BasePersistence;
import com.siims.szb.tixianManager.data.TiXianMoneyData;

public interface TiXianMoneyPersistence extends BasePersistence<TiXianMoneyData>{
	public List<TiXianMoneyData> getTixianMoney(String shoperId);
	public void updateTixianMoney(String shoperId,float money);
}

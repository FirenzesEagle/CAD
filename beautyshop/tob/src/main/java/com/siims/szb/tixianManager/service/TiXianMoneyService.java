package com.siims.szb.tixianManager.service;

import java.util.List;

import com.siims.szb.tixianManager.data.TiXianMoneyData;


public interface TiXianMoneyService {
	
	public List<TiXianMoneyData> getTixianMoney(String shoperId);
	public void updateTixianMoney(String shoperId,float money);

}

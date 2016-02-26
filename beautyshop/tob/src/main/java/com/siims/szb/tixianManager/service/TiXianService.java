package com.siims.szb.tixianManager.service;

import java.util.List;
import java.util.Map;

import com.siims.szb.tixianManager.data.TiXianData;


public interface TiXianService {
	public String tixianRequest(String shoperId,String shoperName,String zhiFuBaoAccount,int type,String secret,float money,String cardType,String cardNumber);
	public List<TiXianData> getTixianRequest();
	public List<Map<String, String>> getTixianRequestByShopId(String shoperId);
}

package com.siims.szb.szbldk.persistence;

import java.util.List;
import java.util.Map;

public interface GetShopIdByOpenIdPersistence {
	public List<Map<String, String>> getShopIdByOpenId(String data);
}

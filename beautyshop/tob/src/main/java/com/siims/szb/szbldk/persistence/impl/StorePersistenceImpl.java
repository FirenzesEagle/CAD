package com.siims.szb.szbldk.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.persistence.StorePersistence;
import com.siims.szb.szbldk.utils.SzbConstant;

@Singleton
@AutoBind(bindClass = StorePersistence.class)
public class StorePersistenceImpl implements StorePersistence {

	// 定义hibernate CRUD 操作对象
	private final BaseDao hibernateDao;

	// 定义mybatis CRUD 操作对象
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;

	/**
	 * 构造函数初始化<br>
	 * 获得数据访问对象
	 * 
	 * @param mybatis
	 *            数据访问对象
	 */
	@Inject
	public StorePersistenceImpl(@Named("mybatisDao") BaseDao mybatis,
			@Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}

	public String addStoreInfo(StoreInfoData storeInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, storeInfoData);
		return storeInfoData.getId();
	}

	public void delStore(StoreInfoData storeInfoData) {
		// TODO Auto-generated method stub
		storeInfoData.setIsdelete(0);
		hibernateDao.update(null, storeInfoData);
	}

	public StoreInfoData editStoreInfo(String shop_id,int which,String info) {
		// TODO Auto-generated method stub
		StoreInfoData data = (StoreInfoData)hibernateDao.queryData(shop_id, StoreInfoData.class);
		switch (which) {
		case 1:
			
			break;
		case 2:
			data.setUsername(info);
			break;
		case 3:
			data.setName(info);
			break;
		case 4:
			data.setAddress(info);
			break;
		case 5:
			data.setTel(info);
			break;
		case 6:
			float price_least = Float.parseFloat(info);
			data.setPrice_least(price_least);
			break;
		case 7:
			float distribution = Float.parseFloat(info);
			data.setDistribution(distribution);
			break;
		case 8:
			data.setOpentime(info);
			break;
		default:
			break;
		}
		hibernateDao.update(null, data);
		return data;
	}

	@SuppressWarnings("unchecked")
	public List<StoreInfoData> getAllStoreByDistrictId(String id) {
		// TODO Auto-generated method stub
		String sql = "from StoreInfoData s where s.isdelete = 1 and s.districtID = '"
				+ id + "'";//order by s.createtime desc
		return hibernateDao.queryList(sql, null);
	}

	public StoreInfoData getStoreInfoByStoreId(String id) {
		// TODO Auto-generated method stub
		return (StoreInfoData) hibernateDao.queryData(id, StoreInfoData.class);
	}

	@SuppressWarnings("unchecked")
	public List<StoreInfoData> getStoreByUserId(String id) {
		// TODO Auto-generated method stub
		String sql = "from StoreInfoData s where s.isdelete = 1 and s.userID = '"
				+ id + "' order by s.createtime desc";
		return hibernateDao.queryList(sql, null);
	}

	public StoreInfoData storeLogin(StoreInfoData data) {
		String hql = "from StoreInfoData s where s.username = '" + data.getUsername()
				+ "' and s.password = '" + data.getPassword()+"'";
		System.out.println(hql);
		@SuppressWarnings("unchecked")
		List<StoreInfoData> list = hibernateDao.queryList(hql, null);
		
		if(list == null || list.size() <= 0){
			return null;
		}
		System.out.println("***storelogin openid:"+data.getOpenid()+"***");	
		list.get(0).setOpenid(data.getOpenid());
		
		hibernateDao.update(null, list.get(0));
		
		return list.get(0);
	}

	public StoreInfoData storeLoginPC(StoreInfoData data) {
		String hql = "from StoreInfoData s where s.username = '" + data.getUsername()
				+ "' and s.password = '" + data.getPassword()+"'";
		System.out.println(hql);
		@SuppressWarnings("unchecked")
		List<StoreInfoData> list = hibernateDao.queryList(hql, null);
		
		if(list == null || list.size() <= 0)
			return null;
		
		return list.get(0);
	}
	
	public List<StoreInfoData> getShopGroupByOpenid(String openid) {
		String hql = "from StoreInfoData s where s.openid = '" + openid+"'";
		@SuppressWarnings("unchecked")
		List<StoreInfoData> list = hibernateDao.queryList(hql, null);
		return list;
	}
	
	public StoreInfoData getFakeShopInfoData(String shopId){
		StoreInfoData s =  (StoreInfoData) hibernateDao.queryData(shopId, StoreInfoData.class);
		return s;
	}
	
	public Map<String,String> getFakeShopInfo(String shopId){
		StoreInfoData data = getFakeShopInfoData(shopId);
		if(data == null)
			return new HashMap<String,String>();
		Map<String,String> map = new HashMap<String, String>();
		map.put("shopName", data.getName());
		return map;
	}

	public StoreInfoData confirmAuth(StoreInfoData data) {
		String hql = "from StoreInfoData s where s.password = '" + data.getPassword()+"' and s.openid = '"+data.getOpenid()+"'";
		@SuppressWarnings("unchecked")
		List<StoreInfoData> list = hibernateDao.queryList(hql, null);
		
		if(list == null || list.size() <= 0)
			return null;
		if(list.get(0).getOpenid().equals(data.getOpenid())){
			list.get(0).setOpenid(data.getOpenid());
		}
		hibernateDao.update(null, list.get(0));
		
		return list.get(0);
	}

}

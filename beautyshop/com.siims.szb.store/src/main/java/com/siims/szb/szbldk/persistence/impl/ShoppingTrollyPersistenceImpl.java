package com.siims.szb.szbldk.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.szbldk.data.ShoppingTrollyData;
import com.siims.szb.szbldk.persistence.ShoppingTrollyPersistence;
import com.siims.szb.szbldk.utils.SzbConstant;





@Singleton
@AutoBind(bindClass = ShoppingTrollyPersistence.class)
public class ShoppingTrollyPersistenceImpl implements ShoppingTrollyPersistence{
	// *********hibernate CRUD*************
		private final BaseDao hibernateDao;

		@Inject
		public ShoppingTrollyPersistenceImpl(@Named("hibernateDao") BaseDao hibernate) {
			this.hibernateDao = hibernate;
		}
	public ShoppingTrollyData addShoppingTrolly(ShoppingTrollyData sd) {
		String shop_id = sd.getShop_id();
		String item_id = sd.getItem_id();
		String openid = sd.getOpenid();
		
		String hql = "from ShoppingTrollyData data where data.shop_id = '"+shop_id+"' AND data.item_id = '"+item_id+"' AND data.openid = '"+openid+"'";
		System.out.println("************"+hql+"***************");
		if(0 == sd.getItem_count()){
			
		}
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		if(list == null || list.size() <= 0){
			if(0 == sd.getItem_count()){
				return sd;
			}
			hibernateDao.insert(null, sd);
			return sd;
		}
		else{
			sd.setId(list.get(0).getId());
			if(0 == sd.getItem_count()){
				sd.setStatus(SzbConstant.SHOPPING_TROLLY_DELETED);
			}
			hibernateDao.update(null, sd);
			return  sd;
		}
	}
	public List<List<ShoppingTrollyData>> getAllShoppingTrollyDataByOpenid(
			String openid) {
		String hql = "from ShoppingTrollyData where openid LIKE '"+openid+"' AND status = "+SzbConstant.SHOPPING_TROLLY_ORDINARY + " order by shop_id desc";
		System.out.println("************"+hql+"***************");
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		List<List<ShoppingTrollyData>> listAssortment = new ArrayList<List<ShoppingTrollyData>>();
		
		if(list == null||list.size() <= 0){
			
			return listAssortment;
		}
		
		List<ShoppingTrollyData> tempList = null;
		
		System.out.println("******************listSize="+list.size()+"******************");
		for(int i = 0;i < list.size();i ++){
	
			
			if(i == 0||!list.get(i).getShop_id().equals(list.get(i - 1).getShop_id())){
			
				tempList = new ArrayList<ShoppingTrollyData>();
				listAssortment.add(tempList);
			}
		
				tempList.add(list.get(i));
			
		}
			
		
		
		return listAssortment;
	}
	
	public int deleteShoppingTrollyData(ShoppingTrollyData sd) {
		String hql = "from ShoppingTrollyData data where data.shop_id = '"+sd.getShop_id()+"' AND data.item_id = '"+sd.getItem_id()+"' AND data.openid = '"+sd.getOpenid()+"'";
		System.out.println("************"+hql+"***************");
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		if(list == null || list.size() <= 0){
		
			return SzbConstant.SZB_FAILED;
		}
		else{
			ShoppingTrollyData sdModify = list.get(0);
			sdModify.setStatus(SzbConstant.SHOPPING_TROLLY_DELETED);
			hibernateDao.update(null, sdModify);
			return  SzbConstant.SZB_SUCCESS;
		}
		
	}
	public int emptyShoppingTrolly(String openid) {
		String hql = "from ShoppingTrollyData where openid LIKE '"+openid+"'";
		System.out.println("************"+hql+"***************");
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		for (ShoppingTrollyData shoppingTrollyData : list) {
			shoppingTrollyData.setStatus(SzbConstant.SHOPPING_TROLLY_DELETED);
			hibernateDao.update(null, shoppingTrollyData);
		}
		return SzbConstant.SHOPPING_TROLLY_SUCCESS;
	}
	public List<ShoppingTrollyData> getShoppingTrollyByStore(String openid,
			String shop_id) {
		String hql = "from ShoppingTrollyData where openid LIKE '"+openid+"' AND shop_id LIKE '"+shop_id+"' AND status = " + SzbConstant.SHOPPING_TROLLY_ORDINARY;
		System.out.println("************"+hql+"***************");
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		if(list == null){
			list = new ArrayList<ShoppingTrollyData>();
			list.clear();
		}
		return list;
	}

	public float getShoppingTrollySum(String user_id) {
		String hql = "from ShoppingTrollyData where user_id LIKE '"+user_id+"'";
		System.out.println("************"+hql+"***************");
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		float sum = 0;
		for (ShoppingTrollyData shoppingTrollyData : list) {
			sum += shoppingTrollyData.getItem_count() * shoppingTrollyData.getItem_price();
		}
		return sum;
	}
	
	public int deleteShoppingTrollyItem(String item_id) {
		String hql = "from ShoppingTrollyData data where data.item_id = '"+item_id+"'";
		System.out.println("************"+hql+"***************");
		@SuppressWarnings("unchecked")
		List<ShoppingTrollyData> list = hibernateDao.queryList(hql, null);
		if(list == null || list.size() <= 0){
		
			return SzbConstant.SZB_SUCCESS;
		}
		for(ShoppingTrollyData d : list){
			d.setStatus(SzbConstant.SHOPPING_TROLLY_DELETED);
			hibernateDao.update(null, d);
		}
		
	return SzbConstant.SZB_SUCCESS;
}	
	
}

package com.siims.szb.szbldk.persistence;

import java.util.List;

import com.siims.framework.base.BasePersistence;
import com.siims.szb.szbldk.data.ShoppingTrollyData;;

public interface ShoppingTrollyPersistence extends BasePersistence<ShoppingTrollyData>{
//	deleteShoppingTrolly
//	emptyShoppingTrolly
//	getShoppingTrollyByStore
//	getShoppingTrollySum
	
	/**
	 * 添加或者修改购物车
	 * 添加之前没有添加过的新商品，或者修改已经添加商品的数量
	 * 返回添加或者修改商品的trolly_id，暨此条目的唯一标识
	 */
	public ShoppingTrollyData addShoppingTrolly(ShoppingTrollyData data);
	/**
	 * 获取某个用户的全部购物车条目
	 * @param user_id
	 * @return
	 */
	public List<List<ShoppingTrollyData>> getAllShoppingTrollyDataByOpenid(String openid);
	/**
	 * 清空用户的购物车
	 * @param userid
	 * @return 返回 SzbContant.SUCCESS成功，其他失败
	 */
	public int emptyShoppingTrolly(String userid);
	/**
	 * 
	 * @param shop_id
	 * @return  返回 SzbContant.SUCCESS成功，其他失败
	 */
	public List<ShoppingTrollyData> getShoppingTrollyByStore(String openid,String shop_id);
	/**
	 * 删除某个条目
	 * @param trolly_id
	 * @return
	 */
	public int deleteShoppingTrollyData(ShoppingTrollyData sd);
	/**
	 * 获得用户的购物车总金额
	 * @param user_id
	 * @return
	 */
	public float getShoppingTrollySum(String user_id);
	
	/**
	 * 通过与此商品ID关联的所有购物车条目
	 * @param item_id
	 * @return
	 */
	public int deleteShoppingTrollyItem(String item_id);
}

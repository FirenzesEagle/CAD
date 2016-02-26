package com.siims.szb.szbldk.business;

import java.util.List;

import com.siims.szb.szbldk.data.ShoppingTrollyData;

public interface ShoppingTrollyBusiness {
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
	public List<List<ShoppingTrollyData>> getAllShoppingTrollyDataByUserid(String user_id);
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
	public List<ShoppingTrollyData> getShoppingTrollyByStore(String user_id,String shop_id);
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
}

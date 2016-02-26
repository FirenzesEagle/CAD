package com.siims.szb.szbldk.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.szbldk.data.StoreInfoData;

public interface StoreService extends BaseService{
	
	/**添加网店信息
	 * @author ling
	 * @param storeInfoData
	 * @return 网店的ID
	 * @since 2015年9月6日15:16:47
	 */
	public String addStoreInfo(StoreInfoData storeInfoData);
	
	/**将网店软删除
	 * @author ling
	 * @param storeInfoData
	 * @since 2015年9月6日15:19:24
	 */
	public void delStore(StoreInfoData storeInfoData);
	
	/**修改网店信息
	 * @author ling
	 * @param storeInfoData
	 * @since 2015年9月6日15:20:14
	 */
	public StoreInfoData editStoreInfo(String shop_id,int which,String info) ;

	/**通过商圈ID找出所有商圈附属的网店信息
	 * @author ling
	 * @param id
	 * @return 同一商圈的所有网店
	 * @since 2015年9月6日15:26:51
	 */
	public List<StoreInfoData> getAllStoreByDistrictId(String id);
	
	/**通过网店ID找出网店的信息
	 * @author ling
	 * @param id
	 * @return 网店的实体
	 * @since 2015年9月6日15:32:04
	 */
	public StoreInfoData getStoreInfoByStoreId(String id);
	
	/**通过用户ID找出所有其名下的网店信息
	 * @author ling
	 * @param id
	 * @return 用户的所有网店
	 * @since 2015年9月6日15:46:11
	 */
	public List<StoreInfoData> getStoreByUserId(String id);
	
	/**
	 * 商户登陆，返回SzbConstant.SZB_SUCCESS成功，其他失败
	 * @param data 验证的信息，包括openid,usrname,password
	 * @return 成功或者失败
	 */
	public StoreInfoData storeLogin(StoreInfoData data);
	/**
	 * 商户pc端登陆，返回SzbConstant.SZB_SUCCESS成功，其他失败
	 * @param data 验证的信息，包括openid,usrname,password
	 * @return 成功或者失败
	 */
	public StoreInfoData storeLoginPC(StoreInfoData data);
	
	public List<StoreInfoData> getShopGroupByOpenid(String openid);
	
	public StoreInfoData confirmAuth(StoreInfoData data);

}

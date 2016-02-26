package com.siims.szb.vipcard.sbp.persistence;

import java.util.List;

import com.siims.framework.base.BasePersistence;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;

/**
 * VipCardType的持久化层
 * @author libo
 * 2015-09-12
 */

public interface VipCardTypePersistence extends BasePersistence<VipCardTypeData> {
	
	/**
	 * 新增一个VipCardData记录
	 * @author libo
	 * @param VipCardTypeData data
	 * 2015-09-12
	 */
	public void addVipCardTypeData(VipCardTypeData data);
	
	/**
	 * 根据商家的ID和TYPE查询一个TYPE的Data
	 * @author libo
	 * @param storeUserId 和 type
	 * 2015-09-13
	 */
	public List<VipCardTypeData> queryByStoreUserIdAndType(String storeUserId, int type);
	
	/**
	 * 根据ID返回信息
	 * @author libo
	 * 2015-09-18
	 */
	public VipCardTypeData queryVipCardTypeDataById(String typeId);
	
	/**
	 * 查询出所有的未删除的会员卡类型
	 * @author libo
	 * 2015-09-19
	 */
	public List<VipCardTypeData> queryAllValidVipCardTypeData();
	
	/**
	 * 根据商家的userId查询出所有的typedata
	 */
	public List<VipCardTypeData> queryAllValidVipcardTypeDataByStoreUserId(String storeUserId);
}

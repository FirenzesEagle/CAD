package com.siims.szb.vipcard.sbp.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;

/**
 * 服务于会员卡类型的Business
 * @author libo
 * 2015-09-12
 */
public interface VipCardTypeBusiness extends BaseBusiness {
	/**
	 * 新增一个VipCardData记录
	 * @author libo
	 * @param VipCardTypeData data
	 * 2015-09-12
	 */
	public void addVipCardTypeData(VipCardTypeData data);
	
	/**
	 * 返回商家的某个Type的VipCardTypeData, 如果不存在，则创建一个返回
	 * @author libo
	 * @param String storeUserId, int type
	 * 2015-09-13
	 */
	public VipCardTypeData getVipCardTypeDataByStoreUserIdAndType(String storeUserId, int type);
	
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

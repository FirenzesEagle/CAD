package com.siims.szb.vipcard.sbp.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.VipCardTypeBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * VipCardType Service的实现类
 * @author libo
 * 2015-09-12
 */
@Singleton
@AutoBind(bindClass = VipCardTypeService.class)
@TransactionalContext
public class VipCardTypeServiceImpl implements VipCardTypeService {
	
	/**
	 * 新增一个VipCardData记录
	 * @author libo
	 * @param 商家的ID，卡的类型
	 * 2015-09-12
	 */
	public void addVipCardTypeData(String storeUserId, int type) {
		// TODO Auto-generated method stub
		VipCardTypeData data = new VipCardTypeData();
		data.setIsdelete(0);
		data.setStoreUserId(storeUserId);
		data.setType(type);
		ServiceContext.get(VipCardTypeBusiness.class).addVipCardTypeData(data);
	}
	
	/**
	 * 返回商家的某个Type的VipCardTypeData, 如果不存在，则创建一个返回
	 * @author libo
	 * @param String storeUserId, int type
	 * 2015-09-13
	 */
	public VipCardTypeData getVipCardTypeDataByStoreUserIdAndType(
			String storeUserId, int type) {
		// TODO Auto-generated method stub
		return ServiceContext.get(VipCardTypeBusiness.class).getVipCardTypeDataByStoreUserIdAndType(storeUserId, type);
	}
	
	/**
	 * 根据ID返回信息
	 * @author libo
	 * 2015-09-18
	 */
	public VipCardTypeData queryVipCardTypeDataById(String typeId) {
		return ServiceContext.get(VipCardTypeBusiness.class).queryVipCardTypeDataById(typeId);
	}
	
	/**
	 * 查询出所有的未删除的会员卡类型
	 * @author libo
	 * 2015-09-19
	 */
	public List<VipCardTypeData> queryAllValidVipCardTypeData() {
		return ServiceContext.get(VipCardTypeBusiness.class).queryAllValidVipCardTypeData();
	}
	
	/**
	 * 根据商家的userId查询出所有的typedata
	 */
	public List<VipCardTypeData> queryAllValidVipcardTypeDataByStoreUserId(String storeUserId) {
		return ServiceContext.get(VipCardTypeBusiness.class).queryAllValidVipcardTypeDataByStoreUserId(storeUserId);
	}

}

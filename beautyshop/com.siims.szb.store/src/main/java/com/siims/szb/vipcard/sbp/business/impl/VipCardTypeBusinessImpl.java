package com.siims.szb.vipcard.sbp.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.VipCardTypeBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.persistence.VipCardTypePersistence;

/**
 * 服务于会员卡类型的Business 的实现类
 * @author libo
 * 2015-09-12
 */
@Singleton
@AutoBind(bindClass = VipCardTypeBusiness.class)
@TransactionalContext
public class VipCardTypeBusinessImpl implements VipCardTypeBusiness {
	
	/**
	 * 新增一个VipCardData记录
	 * @author libo
	 * @param VipCardTypeData data
	 * 2015-09-12
	 */
	public void addVipCardTypeData(VipCardTypeData data) {
		// TODO Auto-generated method stub
		ServiceContext.get(VipCardTypePersistence.class).addVipCardTypeData(data);
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
		
		//根据storeUserId和Type查询VipCardTypeData实体
		List<VipCardTypeData> list = ServiceContext.get(VipCardTypePersistence.class).queryByStoreUserIdAndType(storeUserId, type);
		
		//如果查询过后不存在的话，则创建一个记录后返回
		if(list == null) {
			VipCardTypeData data = new VipCardTypeData();
			data.setStoreUserId(storeUserId);
			data.setType(type);
			data.setIsdelete(0);
			addVipCardTypeData(data);
			return data;
		}
		//如果存在的话则直接返回
		else {
			return list.get(0);
		}
	}
	
	/**
	 * 根据ID返回信息
	 * @author libo
	 * 2015-09-18
	 */
	public VipCardTypeData queryVipCardTypeDataById(String typeId) {
		return ServiceContext.get(VipCardTypePersistence.class).queryVipCardTypeDataById(typeId);
	}
	
	/**
	 * 查询出所有的未删除的会员卡类型
	 * @author libo
	 * 2015-09-19
	 */
	public List<VipCardTypeData> queryAllValidVipCardTypeData() {
		return ServiceContext.get(VipCardTypePersistence.class).queryAllValidVipCardTypeData();
	}
	
	/**
	 * 根据商家的userId查询出所有的typedata
	 */
	public List<VipCardTypeData> queryAllValidVipcardTypeDataByStoreUserId(String storeUserId) {
		return ServiceContext.get(VipCardTypePersistence.class).queryAllValidVipcardTypeDataByStoreUserId(storeUserId);
	}
}

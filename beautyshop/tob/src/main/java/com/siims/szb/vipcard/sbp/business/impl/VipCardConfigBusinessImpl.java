package com.siims.szb.vipcard.sbp.business.impl;

import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.VipCardConfigBusiness;
import com.siims.szb.vipcard.sbp.business.VipCardTypeBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.persistence.VipCardConfigPersistence;

/**
 * VipCardBusiness的实现
 * @author libo
 * 2015-09-14
 */
@Singleton
@AutoBind(bindClass = VipCardConfigBusiness.class)
@TransactionalContext
public class VipCardConfigBusinessImpl implements VipCardConfigBusiness {

	/**
	 * 根据会员卡的类型ID查询出该类型ID的所有规格
	 * @author libo
	 * 2015-09-14
	 * @param String typeId
	 */
	public List<VipCardConfigData> getConfigDataByTypeId(String typeId){
		// TODO Auto-generated method stub
		return ServiceContext.get(VipCardConfigPersistence.class).getConfigDataByTypeId(typeId);
	}
	
	/**
	 * 根据规格的价格，打折或者返现或者次数，以及Type，以及TypeID添加一行规则，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param String typeId, int type, double price, double benifit
	 */
	public String addVipCardConfigData(VipCardConfigData data) {
		return ServiceContext.get(VipCardConfigPersistence.class).addVipCardConfigData(data);
	}
	
	/**
	 * 根据会员卡规格ID查出规格详情
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public VipCardConfigData queryVipCardDataByConfigId(String configId) {
		return ServiceContext.get(VipCardConfigPersistence.class).queryVipCardDataByConfigId(configId);
	}
	
	/**
	 * 更新会员卡规格的信息
	 * @author libo
	 * 2015-09-17
	 */
	public boolean updateVipCardConfigData(VipCardConfigData data) {
		return ServiceContext.get(VipCardConfigPersistence.class).updateVipCardConfigData(data);
	}
}

package com.siims.szb.vipcard.sbp.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.VipCardConfigBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * VipCardConfigService 的实现类
 * @author libo
 * 2015-09-14
 */
@Singleton
@AutoBind(bindClass = VipCardConfigService.class)
@TransactionalContext
public class VipCardConfigServiceImpl implements VipCardConfigService {
	
	/**
	 * 根据会员卡的类型ID查询出该类型ID的所有规格
	 * @author libo <br/>
	 * 2015-09-14
	 * @param String typeId
	 */
	public List<VipCardConfigData> getConfigDataByTypeId(String typeId){
		return ServiceContext.get(VipCardConfigBusiness.class).getConfigDataByTypeId(typeId);
	}
	
	/**
	 * 根据规格的价格，打折或者返现或者次数，以及Type，以及TypeID添加一行规则，返回插入的ID
	 * @author libo <br/>
	 * 2015-09-14
	 * @param String typeId, int type, double price, double benefit
	 */
	public String addVipCardConfigData(String typeId, int type, double price, double benefit) {
		VipCardConfigData data = new VipCardConfigData();
		data.setTypeId(typeId);
		data.setType(type);
		data.setPrice(price);
		data.setBenefit(benefit);
		return ServiceContext.get(VipCardConfigBusiness.class).addVipCardConfigData(data);
	}
	
	/**
	 * 根据会员卡规格ID查出规格详情
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public VipCardConfigData queryVipCardDataByConfigId(String configId) {
		return ServiceContext.get(VipCardConfigBusiness.class).queryVipCardDataByConfigId(configId);
	}
	
	/**
	 * 更新会员卡规格的信息
	 * @author libo
	 * 2015-09-17
	 */
	public boolean updateVipCardConfigData(VipCardConfigData data) {
		return ServiceContext.get(VipCardConfigBusiness.class).updateVipCardConfigData(data);
	}

}

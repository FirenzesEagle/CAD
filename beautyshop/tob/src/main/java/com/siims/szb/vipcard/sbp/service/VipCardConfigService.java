package com.siims.szb.vipcard.sbp.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;

/**
 * 服务于和会员卡规格相关的Service
 * @author libo
 * 2015-09-14
 */
public interface VipCardConfigService extends BaseService {
	
	/**
	 * 根据会员卡的类型ID查询出该类型ID的所有规格
	 * @author libo
	 * 2015-09-14
	 * @param String typeId
	 */
	public List<VipCardConfigData> getConfigDataByTypeId(String typeId);
	
	/**
	 * 根据规格的价格，打折或者返现或者次数，以及Type，以及TypeID添加一行规则，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param String typeId, int type, double price, double benefit
	 */
	public String addVipCardConfigData(String typeId, int type, double price, double benefit);
	
	/**
	 * 根据会员卡规格ID查出规格详情
	 * @author libo
	 * 2015-09-17
	 */
	public VipCardConfigData queryVipCardDataByConfigId(String configId);
	
	/**
	 * 更新会员卡规格的信息
	 * @author libo
	 * 2015-09-17
	 */
	public boolean updateVipCardConfigData(VipCardConfigData data);
	
}

package com.siims.szb.vipcard.sbp.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;

/**
 * 服务于VipCardConfig的Business层
 * @author libo
 * 2015-09-14
 */
public interface VipCardConfigBusiness extends BaseBusiness {
	
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
	 * @param VipCardConfigData data
	 */
	public String addVipCardConfigData(VipCardConfigData data);
	
	/**
	 * 根据会员卡规格ID查出规格详情
	 * @author libo <br/>
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

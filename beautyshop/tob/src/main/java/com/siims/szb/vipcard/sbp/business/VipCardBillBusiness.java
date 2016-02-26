package com.siims.szb.vipcard.sbp.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;

/**
 * 服务于会员卡流水的Business
 * @author libo <br/>
 * 2015-09-16
 */
public interface VipCardBillBusiness extends BaseBusiness {
	
	/**
	 * 插入一条记录，返回插入的ID
	 * @author libo <br/>
	 * 2015-09-16
	 * @param VipCardBillData data
	 */
	public String addVipCardBillData(VipCardBillData data);
	
	/**
	 * 根据会员卡的ID来查询所有的流水，并根据流水的日期由近及远的排序
	 * @author libo <br/>
	 * 2015-09-16
	 */
	public List<VipCardBillData> queryVipCardBillDataListByVipCardId(String vipCardId);
	
	/**
	 * 根据交易的类型进行查询
	 * @author libo
	 * 2015-09-20
	 */
	public List<VipCardBillData> queryVipCardBillDataListByType(int type);
	
	/**
	 * 根据会员卡的ID和交易类型查询
	 */
	public List<VipCardBillData> queryVipCardBillDataListByCardIdAndType(String cardId, int type);
}

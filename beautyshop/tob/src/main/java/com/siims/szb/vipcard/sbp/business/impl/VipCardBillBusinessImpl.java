package com.siims.szb.vipcard.sbp.business.impl;

import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.VipCardBillBusiness;
import com.siims.szb.vipcard.sbp.business.VipCardConfigBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;
import com.siims.szb.vipcard.sbp.persistence.VipCardBillPersistence;

/**
 * VipCardBillBusiness的实现类
 * @author libo <br/>
 * 2015-09-16
 */
@Singleton
@AutoBind(bindClass = VipCardBillBusiness.class)
@TransactionalContext
public class VipCardBillBusinessImpl implements VipCardBillBusiness {
	
	/**
	 * 插入一条记录，返回插入的ID
	 * @author libo <br/>
	 * 2015-09-16
	 * @param VipCardBillData data
	 */
	public String addVipCardBillData(VipCardBillData data) {
		// TODO Auto-generated method stub
		return ServiceContext.get(VipCardBillPersistence.class).addVipCardBillData(data);
	}
	
	/**
	 * 根据会员卡的ID来查询所有的流水，并根据流水的日期由近及远的排序
	 * @author libo <br/>
	 * 2015-09-16
	 */
	public List<VipCardBillData> queryVipCardBillDataListByVipCardId(String vipCardId) {
		return ServiceContext.get(VipCardBillPersistence.class).queryVipCardBillDataListByVipCardId(vipCardId);
	}
	
	/**
	 * 根据交易的类型进行查询
	 * @author libo
	 * 2015-09-20
	 */
	public List<VipCardBillData> queryVipCardBillDataListByType(int type) {
		return ServiceContext.get(VipCardBillPersistence.class).queryVipCardBillDataListByType(type);
	}
	
	/**
	 * 根据会员卡的ID和交易类型查询
	 */
	public List<VipCardBillData> queryVipCardBillDataListByCardIdAndType(String cardId, int type) {
		return ServiceContext.get(VipCardBillPersistence.class).queryVipCardBillDataListByCardIdAndType(cardId, type);
	}

}

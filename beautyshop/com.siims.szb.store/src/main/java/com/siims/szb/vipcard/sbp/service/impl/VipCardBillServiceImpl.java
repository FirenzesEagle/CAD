package com.siims.szb.vipcard.sbp.service.impl;


import java.util.Date;
import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.VipCardBillBusiness;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * VipCardBillService的实现类
 * @author libo <br/>
 * 2015-09-16
 */
@Singleton
@AutoBind(bindClass = VipCardBillService.class)
@TransactionalContext
public class VipCardBillServiceImpl implements VipCardBillService {
	
	/**
	 * 根据会员卡ID，这比交易的金额，余额，消费类型，摘要信息，支付类型，订单编号插入一条记录, 并返回插入的ID <br/>
	 * 其中摘要信息，支付类型，订单编号是可选字段
	 * @author libo <br/>
	 * 2015-09-16
	 * @param String cardId, double money,
			  double lastMoney, int consumeType, String comments, String orderId,
			  int paytype
	 */
	public String addVipCardBillData(String cardId, double money,
			double lastMoney, int consumeType, String comments, String orderId,
			Integer paytype) {
		// TODO Auto-generated method stub
		VipCardBillData data = new VipCardBillData();
		data.setVipCardId(cardId);
		data.setMoney(money);
		data.setLastMoney(lastMoney);
		data.setConsumeType(consumeType);
		data.setTime(new Date());
		if(comments != null)
			data.setComments(comments);
		if(orderId != null)
			data.setOrderId(orderId);
		if(paytype != null)
			data.setPayType(paytype);
		return ServiceContext.get(VipCardBillBusiness.class).addVipCardBillData(data);
	}
	
	/**
	 * 根据会员卡的ID来查询所有的流水，并根据流水的日期由近及远的排序
	 * @author libo <br/>
	 * 2015-09-16
	 */
	public List<VipCardBillData> queryVipCardBillDataListByVipCardId(String vipCardId) {
		return ServiceContext.get(VipCardBillBusiness.class).queryVipCardBillDataListByVipCardId(vipCardId);
	}
	
	/**
	 * 根据交易的类型进行查询
	 * @author libo
	 * 2015-09-20
	 */
	public List<VipCardBillData> queryVipCardBillDataListByType(int type) {
		return ServiceContext.get(VipCardBillBusiness.class).queryVipCardBillDataListByType(type);
	}
	/**
	 * 根据会员卡的ID和交易类型查询
	 */
	public List<VipCardBillData> queryVipCardBillDataListByCardIdAndType(String cardId, int type) {
		return ServiceContext.get(VipCardBillBusiness.class).queryVipCardBillDataListByCardIdAndType(cardId, type);
	}

}

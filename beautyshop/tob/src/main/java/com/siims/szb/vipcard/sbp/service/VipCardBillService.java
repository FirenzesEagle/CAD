package com.siims.szb.vipcard.sbp.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.vipcard.sbp.data.VipCardBillData;

/**
 * 服务于会员卡流水的Service
 * @author libo <br/>
 * 2015-09-16
 */
public interface VipCardBillService extends BaseService {
	
	/**
	 * 根据会员卡ID，这比交易的金额，余额，消费类型，摘要信息，支付类型，订单编号插入一条记录, 并返回插入的ID <br/>
	 * 其中摘要信息，支付类型，订单编号是可选字段
	 * @author libo <br/>
	 * 2015-09-16
	 * @param String cardId, double money,
			  double lastMoney, int consumeType, String comments, String orderId,
			  int paytype
	 */
	public String addVipCardBillData(String cardId, double money, double lastMoney, 
			int consumeType, String comments, String orderId, Integer paytype);
	
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

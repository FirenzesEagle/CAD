package com.siims.szb.vipcard.sbp.business.impl;

import java.util.List;

import javax.inject.Singleton;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.ConsumerVipCardBusiness;
import com.siims.szb.vipcard.sbp.business.VipCardConfigBusiness;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.persistence.ConsumerVipCardPersistence;

/**
 * ConsumerVipCardBusiness的实现类
 * @author libo
 * 2014-09-14
 */
@Singleton
@AutoBind(bindClass = ConsumerVipCardBusiness.class)
@TransactionalContext
public class ConsumerVipCardBusinessImpl implements ConsumerVipCardBusiness {

	/**
	 * 添加一条记录，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param ConsumerVipCardData
	 */
	public String addConsumerVipCardData(ConsumerVipCardData data) {
		return ServiceContext.get(ConsumerVipCardPersistence.class).addConsumerVipCardData(data);
	}
	
	/**
	 * 根据会员卡的TypeID来查询所有已经领取的会员卡
	 * @author libo
	 * 2015-09-15
	 * @param String typeId
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeId(String typeId) {
		return ServiceContext.get(ConsumerVipCardPersistence.class).queryConsumerVipCardDataByTypeId(typeId);
	}
	
	/**
	 * 根据会员卡的typeId以及搜索条件search字段来进行查询出所有的会员卡信息<br/>
	 * 精确匹配卡号、姓名、电话中的至少一个
	 * @author libo <br/>
	 * 2015-09-16
	 * @param String typeId, String search
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeIdAndSearch(String typeId, String search) {
		return ServiceContext.get(ConsumerVipCardPersistence.class).queryConsumerVipCardDataByTypeIdAndSearch(typeId, search);
	}
	
	/**
	 * 根据会员卡ID查询会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public ConsumerVipCardData queryByConsumerVipCardId(String vipCardId) {
		return ServiceContext.get(ConsumerVipCardPersistence.class).queryByConsumerVipCardId(vipCardId);
	}
	
	/**
	 * 更新会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public boolean updateConsumerVipCardData(ConsumerVipCardData data) {
		return ServiceContext.get(ConsumerVipCardPersistence.class).updateConsumerVipCardData(data);
	}
	
	/**
	 * 根据会员的WEIXINOPENID来查询会员卡
	 * @author libo <br/>
	 * 2015-09-18
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByOpenId(String openId) {
		return ServiceContext.get(ConsumerVipCardPersistence.class).queryConsumerVipCardDataByOpenId(openId);
	}

}

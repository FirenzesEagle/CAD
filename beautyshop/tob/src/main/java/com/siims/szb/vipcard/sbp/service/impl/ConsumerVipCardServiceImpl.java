package com.siims.szb.vipcard.sbp.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.business.ConsumerVipCardBusiness;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * ConsumerVipCardService的实现类
 * @author libo
 * 2015-09-14
 */
@Singleton
@AutoBind(bindClass = ConsumerVipCardService.class)
@TransactionalContext
public class ConsumerVipCardServiceImpl implements ConsumerVipCardService {
	
	/**
	 * 添加一条记录，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param String typeId, String configId, String openid, String name, String phone, String password
	 */
	public String addConsumerVipCardData(String typeId, String configId,
			String openid, String name, String phone, String password, double lastmoney) {
		// TODO Auto-generated method stub
		ConsumerVipCardData data = new ConsumerVipCardData();
		data.setVipCardTypeId(typeId);
		data.setVipCardConfigId(configId);
		data.setConsumerWeixinOpenId(openid);
		data.setConsumerName(name);
		data.setConsumerPhone(phone);
		data.setPassword(password);
		data.setLastMoney(lastmoney);
		data.setIspay(0);
		return ServiceContext.get(ConsumerVipCardBusiness.class).addConsumerVipCardData(data);
	}
	
	/**
	 * 根据会员卡的TypeID来查询所有已经领取的会员卡
	 * @author libo
	 * 2015-09-15
	 * @param String typeId
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeId(String typeId) {
		return ServiceContext.get(ConsumerVipCardBusiness.class).queryConsumerVipCardDataByTypeId(typeId);
	}
	
	/**
	 * 根据会员卡的typeId以及搜索条件search字段来进行查询出所有的会员卡信息<br/>
	 * 精确匹配卡号、姓名、电话中的至少一个
	 * @author libo <br/>
	 * 2015-09-16
	 * @param String typeId, String search
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeIdAndSearch(String typeId, String search) {
		return ServiceContext.get(ConsumerVipCardBusiness.class).queryConsumerVipCardDataByTypeIdAndSearch(typeId, search);
	}
	
	/**
	 * 根据会员卡ID查询会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public ConsumerVipCardData queryByConsumerVipCardId(String vipCardId) {
		return ServiceContext.get(ConsumerVipCardBusiness.class).queryByConsumerVipCardId(vipCardId);
	}
	
	/**
	 * 更新会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public boolean updateConsumerVipCardData(ConsumerVipCardData data) {
		return ServiceContext.get(ConsumerVipCardBusiness.class).updateConsumerVipCardData(data);
	}
	
	/**
	 * 根据会员的WEIXINOPENID来查询会员卡
	 * @author libo <br/>
	 * 2015-09-18
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByOpenId(String openId) {
		return ServiceContext.get(ConsumerVipCardBusiness.class).queryConsumerVipCardDataByOpenId(openId);
	}

}

package com.siims.szb.vipcard.sbp.persistence.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.persistence.ConsumerVipCardPersistence;
import com.siims.szb.vipcard.sbp.persistence.VipCardConfigPersistence;

/**
 * ConsumerVipCardPersistence的实现类
 * @author libo
 * 2014-09-14
 */
@Singleton
@AutoBind(bindClass = ConsumerVipCardPersistence.class)
@TransactionalContext
public class ConsumerVipCardPersistenceImpl implements
		ConsumerVipCardPersistence {
	
	//定义hibernate CRUD 操作对象
	private final BaseDao hibernateDao;

	//定义mybatis CRUD 操作对象
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	/**
	* 造函数初始化<br>
	* 获得数据访问对象
	* @param mybatis 数据访问对象
	*/
	@Inject
	public ConsumerVipCardPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	/**
	 * 根据会员卡的TypeID来查询所有已经领取的会员卡
	 * @author libo
	 * 2015-09-15
	 * @param String typeId
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeId(String typeId) {
		String hql = "from ConsumerVipCardData data where data.vipCardTypeId = '" + typeId + "'";
		hql += " AND data.ispay = 1 AND data.isDelete=0";
		List<ConsumerVipCardData> list = hibernateDao.queryList(hql, null);
		return list;
	}
	
	/**
	 * 添加一条ConsumerVipCardData记录，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param ConsumerVipCardData data
	 */
	public String addConsumerVipCardData(ConsumerVipCardData data) {
		data.setCreateTime(new Date());
		List<ConsumerVipCardData> list = queryConsumerVipCardDataByTypeId(data.getVipCardTypeId());		
		data.setCardNumber(list.size()+1);
		hibernateDao.insert(null, data);
		return data.getId();
	}
	
	/**
	 * 根据会员卡的typeId以及搜索条件search字段来进行查询出所有的会员卡信息<br/>
	 * 精确匹配卡号、姓名、电话中的至少一个
	 * @author libo <br/>
	 * 2015-09-16
	 * @param String typeId, String search
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeIdAndSearch(String typeId, String search) {
		
		int number = -1;
		try{
			number = Integer.valueOf(search);
		}
		catch(Exception e) {
			System.out.println("搜索条件不是数字");
			number = -1;
		}
		String hql = "from ConsumerVipCardData data where data.vipCardTypeId = '" + typeId + "'";
		//加上search的匹配
		if(number != -1)
			hql += "AND (data.cardNumber = " + number 
					+" OR data.consumerName ='" + search 
					+"' OR data.consumerPhone ='"+search +"')";
		else
			hql += "AND (data.consumerName ='" + search 
					+"' OR data.consumerPhone ='"+search +"')";
		
		hql += " AND data.ispay=1 AND data.isDelete=0";
		List<ConsumerVipCardData> list = hibernateDao.queryList(hql, null);
		return list;
	}
	
	/**
	 * 根据会员卡ID查询会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public ConsumerVipCardData queryByConsumerVipCardId(String vipCardId){
		return (ConsumerVipCardData) hibernateDao.queryData(vipCardId, ConsumerVipCardData.class);
	}
	
	/**
	 * 更新会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public boolean updateConsumerVipCardData(ConsumerVipCardData data) {
		hibernateDao.update(null, data);
		return true;
	}
	
	/**
	 * 根据会员的WEIXINOPENID来查询会员卡
	 * @author libo <br/>
	 * 2015-09-18
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByOpenId(String openId) {
		String hql = "from ConsumerVipCardData data where data.consumerWeixinOpenId='"+openId+"' AND data.isDelete=0 AND data.ispay=1";
		List<ConsumerVipCardData> list = hibernateDao.queryList(hql, null);
		return list;
	}

}

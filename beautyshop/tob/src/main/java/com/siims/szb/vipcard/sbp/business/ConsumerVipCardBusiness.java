package com.siims.szb.vipcard.sbp.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;

/**
 * 服务于ConsumerVipCardData的Business层
 * @author libo
 * 2015-09-14
 */
public interface ConsumerVipCardBusiness extends BaseBusiness {
	/**
	 * 添加一条记录，返回插入的ID
	 * @author libo
	 * 2015-09-14
	 * @param ConsumerVipCardData
	 */
	public String addConsumerVipCardData(ConsumerVipCardData data);
	
	/**
	 * 根据会员卡的TypeID来查询所有已经领取的会员卡
	 * @author libo
	 * 2015-09-15
	 * @param String typeId
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeId(String typeId);
	
	/**
	 * 根据会员卡的typeId以及搜索条件search字段来进行查询出所有的会员卡信息<br/>
	 * 精确匹配卡号、姓名、电话中的至少一个
	 * @author libo <br/>
	 * 2015-09-16
	 * @param String typeId, String search
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByTypeIdAndSearch(String typeId, String search);
	
	/**
	 * 根据会员卡ID查询会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public ConsumerVipCardData queryByConsumerVipCardId(String vipCardId);
	
	/**
	 * 更新会员卡信息
	 * @author libo <br/>
	 * 2015-09-17
	 */
	public boolean updateConsumerVipCardData(ConsumerVipCardData data);
	
	/**
	 * 根据会员的WEIXINOPENID来查询会员卡
	 * @author libo <br/>
	 * 2015-09-18
	 */
	public List<ConsumerVipCardData> queryConsumerVipCardDataByOpenId(String openId);
}

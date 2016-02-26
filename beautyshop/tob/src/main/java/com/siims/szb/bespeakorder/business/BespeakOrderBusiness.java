package com.siims.szb.bespeakorder.business;

import java.util.List;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.bespeakorder.data.BespeakOrderData;

public interface BespeakOrderBusiness extends BaseBusiness{
	/**添加服务订单项
	 * @author ling
	 * @param data
	 * @return 服务订单的id
	 */
	public String AddBespeakOrder(BespeakOrderData data);
	/**将订单记录进行软删除
	 * @author ling
	 * @param data
	 */
	public void DeleteBespeakOrder(BespeakOrderData data);
	/**编辑订单记录，如改变状态
	 * @author ling
	 * @param data
	 */
	public void EditBespeakOrder(BespeakOrderData data);
	/**通过状态，用户id，店铺id找出所有订单记录项
	 * @author ling
	 * @param state
	 * @param personid
	 * @param shopid
	 * @return list
	 */
	public List<BespeakOrderData> GetBespeakOrderForCustomer(int state,String personid);
	/**通过状态和店铺id找出所有订单记录
	 * @author ling
	 * @param state
	 * @param shopid
	 * @return list
	 */
	public List<BespeakOrderData> GetBespeakOrderForShop(int state,String shopid);
	/**通过关键字搜索
	 * @author ling
	 * @param key
	 * @param shopid
	 * @return list
	 */
	public List<BespeakOrderData> GetBespeakOrderByKey(String key,String shopid);
	/**获得店铺的所有订单信息
	 * @author ling
	 * @param shopid
	 * @return
	 */
	public List<BespeakOrderData> GetAllBespeakOrder(String shopid);
	/**根据订单id找出订单的实体
	 * @author ling
	 * @param id
	 * @return
	 */
	public BespeakOrderData GetBespeakOrderById(String id);
	/**通过预约的id找出服务订单
	 * @author ling
	 * @param bespeakid
	 * @return
	 */
	public List<BespeakOrderData> GetBespeaorderByBespeak(String bespeakid);
}

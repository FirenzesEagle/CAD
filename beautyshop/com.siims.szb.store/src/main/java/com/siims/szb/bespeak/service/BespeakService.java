package com.siims.szb.bespeak.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.bespeak.data.BespeakData;

public interface BespeakService extends BaseService{
	/**添加预约记录
	 * @author ling
	 * @param data
	 * @return 记录的id
	 */
	public String AddBespeakRecord(BespeakData data);
	/**通过日期查找出对应日期的所有预约记录
	 * @author ling
	 * @param date
	 * @return 预约记录的list
	 */
	public List<BespeakData> GetBespeakByDate(String date,String shopid);
	/**通过日期，预约行数，预约列数来定义一条记录
	 * @author ling
	 * @param date
	 * @param line
	 * @param row
	 * @return 预约的记录
	 */
	public List<BespeakData> GetBespeakByPoint(String date,int line,int row,String shopid);
	/**通过id查找出单条记录
	 * @author ling
	 * @param id
	 * @return 记录实体
	 */
	public BespeakData GetBespeakById(String id);
	/**改变预约的状态
	 * @author ling
	 * @param data
	 */
	public void ChangeBespeakState(BespeakData data);
	/**将预约删除
	 * @author ling
	 * @param data
	 */
	public void DeleteBespeak(BespeakData data);
}

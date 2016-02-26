
/**   
 * @Title: ReceivingInfoService.java 
 * @Package: com.siims.szb.demo.service 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午8:56:20 
 * @version 0.1
 */


package com.siims.szb.personal.service;

import java.util.List;

import com.siims.framework.base.BaseService;
import com.siims.szb.personal.data.ReceivingInfoData;

/** 
 * @Description 收货信息服务层接口
 * @author liumingbo
 * @date 2015年9月18日 下午8:56:20 
 * @version 0.1
 */

public interface ReceivingInfoService extends BaseService {
	/** 
	 * @Description 查询单条收货信息
	 * @author liumingbo
	 * @param receivingID
	 * @return ReceivingInfoData
	 */
	  	
	public ReceivingInfoData searchReceivingInfo(String receivingID);
	
	 
	/** 
	 * @Description 查询openID对应用户所有收货信息
	 * @author liumingbo
	 * @param openID
	 * @return List<ReceivingInfoData>
	 */
	  	
	public List<ReceivingInfoData> searchAllReceivingInfo(String openID);
	
	 
	/** 
	 * @Description 添加收货信息
	 * @author liumingbo
	 * @param receivingInfoData
	 * @return  RECEIVING_ID
	 */
	  	
	public String addReceivingInfo(ReceivingInfoData receivingInfoData);
	
	 
	/** 
	 * @Description 删除收货信息
	 * @author liumingbo
	 * @param receivingInfoData
	 * @return  是否删除成功	1-成功 0-失败
	 */
	  	
	public boolean delReceivingInfo(ReceivingInfoData receivingInfoData);
	
	 
	/** 
	 * @Description 修改收货信息
	 * @author liumingbo
	 * @param receivingInfoData
	 * @return  是否修改成功	1-成功 0-失败
	 */
	  	
	public boolean editReceivingInfo(ReceivingInfoData receivingInfoData);
}

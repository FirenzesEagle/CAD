
/**   
 * @Title: ReceivingInfoServiceImpl.java 
 * @Package: com.siims.szb.demo.service.impl 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午8:58:22 
 * @version 0.1
 */


package com.siims.szb.personal.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.personal.business.ReceivingInfoBusiness;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.service.ReceivingInfoService;

/** 
 * @Description 收货信息服务层实现类
 * @author liumingbo
 * @date 2015年9月18日 下午8:58:22 
 * @version 0.1
 */

@Singleton
@AutoBind(bindClass = ReceivingInfoService.class)
@TransactionalContext
public class ReceivingInfoServiceImpl implements ReceivingInfoService {

	/**
	 * Description 查询单条收货信息
	 * @param receivingID
	 * @return ReceivingInfoData
	 * @see com.siims.szb.demo.service.ReceivingInfoService#searchReceivingInfo(java.lang.String) 
	 */

	public ReceivingInfoData searchReceivingInfo(String receivingID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ReceivingInfoBusiness.class).searchReceivingInfo(receivingID);
	}

	/**
	 * Description 查询openID对应用户所有收货信息
	 * @param openID
	 * @return List<ReceivingInfoData>
	 * @see com.siims.szb.demo.service.ReceivingInfoService#searchAllReceivingInfo(java.lang.String) 
	 */

	public List<ReceivingInfoData> searchAllReceivingInfo(String openID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ReceivingInfoBusiness.class).searchAllReceivingInfo(openID);
	}

	/**
	 * Description 添加收货信息
	 * @param receivingInfoData
	 * @return RECEIVING_ID
	 * @see com.siims.szb.demo.service.ReceivingInfoService#addReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public String addReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ReceivingInfoBusiness.class).addReceivingInfo(receivingInfoData);
		return receivingInfoData.getReceiving_id();
	}

	/**
	 * Description 删除收货信息
	 * @param receivingInfoData
	 * @return 是否删除成功	1-成功 0-失败
	 * @see com.siims.szb.demo.service.ReceivingInfoService#delReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public boolean delReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ReceivingInfoBusiness.class).delReceivingInfo(receivingInfoData);
		return true;
	}

	/**
	 * Description 修改收货信息
	 * @param receivingInfoData
	 * @return 是否修改成功	1-成功 0-失败
	 * @see com.siims.szb.demo.service.ReceivingInfoService#editReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public boolean editReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ReceivingInfoBusiness.class).editReceivingInfo(receivingInfoData);
		return true;
	}

}

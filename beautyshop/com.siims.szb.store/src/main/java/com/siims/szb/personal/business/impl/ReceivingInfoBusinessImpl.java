
/**   
 * @Title: ReceivingInfoBusinessImpl.java 
 * @Package: com.siims.szb.demo.business.impl 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午7:36:10 
 * @version 0.1
 */


package com.siims.szb.personal.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.personal.business.ReceivingInfoBusiness;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.persistence.ReceivingInfoPersistence;

/** 
 * @Description 收货信息业务层实现类
 * @author liumingbo
 * @date 2015年9月18日 下午7:36:10 
 * @version 0.1
 */
@Singleton
@AutoBind(bindClass = ReceivingInfoBusiness.class)
public class ReceivingInfoBusinessImpl implements ReceivingInfoBusiness {

	/**
	 * Description 查询单条收货信息
	 * @param receivingID
	 * @return ReceivingInfoData
	 * @see com.siims.szb.demo.business.ReceivingInfoBusiness#searchReceivingInfo(java.lang.String) 
	 */

	public ReceivingInfoData searchReceivingInfo(String receivingID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ReceivingInfoPersistence.class).searchReceivingInfo(receivingID);
	}

	/**
	 * Description 查询openID对应用户所有收货信息
	 * @param openID
	 * @return List<ReceivingInfoData>
	 * @see com.siims.szb.demo.business.ReceivingInfoBusiness#searchAllReceivingInfo(java.lang.String) 
	 */

	public List<ReceivingInfoData> searchAllReceivingInfo(String openID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(ReceivingInfoPersistence.class).searchAllReceivingInfo(openID);
	}

	/**
	 * Description 添加收货信息
	 * @param receivingInfoData
	 * @return RECEIVING_ID
	 * @see com.siims.szb.demo.business.ReceivingInfoBusiness#addReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public String addReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ReceivingInfoPersistence.class).addReceivingInfo(receivingInfoData);
		return receivingInfoData.getReceiving_id();
	}

	/**
	 * Description 删除收货信息
	 * @param receivingInfoData
	 * @return 是否删除成功	1-成功 0-失败
	 * @see com.siims.szb.demo.business.ReceivingInfoBusiness#delReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public boolean delReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ReceivingInfoPersistence.class).delReceivingInfo(receivingInfoData);
		return true;
	}

	/**
	 * Description 修改收货信息
	 * @param receivingInfoData
	 * @return 是否修改成功	1-成功 0-失败
	 * @see com.siims.szb.demo.business.ReceivingInfoBusiness#editReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public boolean editReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		ServiceContext.get(ReceivingInfoPersistence.class).editReceivingInfo(receivingInfoData);
		return true;
	}

}

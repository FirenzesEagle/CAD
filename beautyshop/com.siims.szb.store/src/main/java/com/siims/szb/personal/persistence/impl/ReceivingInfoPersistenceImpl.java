
/**   
 * @Title: ReceivingInfoPersistenceImpl.java 
 * @Package: com.siims.szb.demo.persistence.impl 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月17日 下午9:58:52 
 * @version 0.1
 */


package com.siims.szb.personal.persistence.impl;

import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.persistence.ReceivingInfoPersistence;

/** 
 * @Description 收货信息持久化类
 * @author liumingbo
 * @date 2015年9月17日 下午9:58:52 
 * @version 0.1
 */
@Singleton
@AutoBind(bindClass = ReceivingInfoPersistence.class)
public class ReceivingInfoPersistenceImpl implements ReceivingInfoPersistence {

	// 定义hibernate CRUD 操作对象
	private final BaseDao hibernateDao;

	// 定义mybatis CRUD 操作对象
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;

	/**
	 * 构造函数初始化<br>
	 * 获得数据访问对象
	 * 
	 * @param mybatis	数据访问对象
	 */
	@Inject
	public ReceivingInfoPersistenceImpl(@Named("mybatisDao") BaseDao mybatis,
			@Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}

	/**
	 * Description 查询单条收货信息
	 * @param receivingID
	 * @return ReceivingInfoData
	 * @see com.siims.szb.demo.persistence.ReceivingInfoPersistence#searchReceivingInfo(java.lang.String) 
	 */

	public ReceivingInfoData searchReceivingInfo(String receivingID) {
		// TODO Auto-generated method stub
		ReceivingInfoData receivingInfoData = (ReceivingInfoData) hibernateDao.queryData(receivingID, ReceivingInfoData.class);
		return receivingInfoData;
	}

	/**
	 * Description 查询openID对应用户所有收货信息
	 * @param openID
	 * @return List<ReceivingInfoData>
	 * @see com.siims.szb.demo.persistence.ReceivingInfoPersistence#searchAllReceivingInfo(java.lang.String) 
	 */
	@SuppressWarnings("unchecked")
	public List<ReceivingInfoData> searchAllReceivingInfo(String openID) {
		// TODO Auto-generated method stub
		String sql = "from ReceivingInfoData r where r.openID = '" + openID + "'";
		List<ReceivingInfoData> list = hibernateDao.queryList(sql, null);
		return list;
	}

	/**
	 * Description 添加收货信息
	 * @param receivingInfoData
	 * @return RECEIVING_ID
	 * @see com.siims.szb.demo.persistence.ReceivingInfoPersistence#addReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		if(receivingInfoData.getDefault_receiving_info() == 0){
			hibernateDao.insert(null, receivingInfoData);
		}else{
			String sql = "from ReceivingInfoData r where r.openID = '" + receivingInfoData.getOpenID() + "'";
			List<ReceivingInfoData> list = hibernateDao.queryList(sql, null);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ReceivingInfoData receivingInfoData2 = (ReceivingInfoData) iterator.next();
				if(receivingInfoData2.getDefault_receiving_info() == 1){
					receivingInfoData2.setDefault_receiving_info(0);
					hibernateDao.update(null, receivingInfoData2);
				}
			}
			hibernateDao.insert(null, receivingInfoData);
		}
		return receivingInfoData.getId();
	}

	/**
	 * Description 删除收货信息
	 * @param receivingInfoData
	 * @return 是否删除成功	1-成功 0-失败
	 * @see com.siims.szb.demo.persistence.ReceivingInfoPersistence#delReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */

	public boolean delReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.delete(null, receivingInfoData);
		return true;
	}

	/**
	 * Description 修改收货信息
	 * @param receivingInfoData
	 * @return 是否修改成功	1-成功 0-失败
	 * @see com.siims.szb.demo.persistence.ReceivingInfoPersistence#editReceivingInfo(com.siims.szb.demo.data.ReceivingInfoData) 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean editReceivingInfo(ReceivingInfoData receivingInfoData) {
		// TODO Auto-generated method stub
		if(receivingInfoData.getDefault_receiving_info() == 0){
			hibernateDao.update(null, receivingInfoData);
		}else{
			String sql = "from ReceivingInfoData r where r.openID = '" + receivingInfoData.getOpenID() + "'";
			List<ReceivingInfoData> list = hibernateDao.queryList(sql, null);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ReceivingInfoData receivingInfoData2 = (ReceivingInfoData) iterator.next();
				if(receivingInfoData2.getDefault_receiving_info() == 1){
					receivingInfoData2.setDefault_receiving_info(0);
					hibernateDao.update(null, receivingInfoData2);
				}
			}
			hibernateDao.update(null, receivingInfoData);
		}
		return true;
	}

}

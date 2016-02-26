
/**   
 * @Title: PersonalInfoPersistenceImpl.java 
 * @Package: com.siims.szb.demo.persistence.impl 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午5:05:28 
 * @version 0.1
 */


package com.siims.szb.personal.persistence.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.personal.data.PersonalInfoData;
import com.siims.szb.personal.persistence.PersonalInfoPersistence;

/** 
 * @Description 个人中心持久化类
 * @author liumingbo
 * @date 2015年9月18日 下午5:05:28 
 * @version 0.1
 */
@Singleton
@AutoBind(bindClass = PersonalInfoPersistence.class)
public class PersonalInfoPersistenceImpl implements PersonalInfoPersistence {

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
	public PersonalInfoPersistenceImpl(@Named("mybatisDao") BaseDao mybatis,
			@Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	/**
	 * Description 查询用户信息
	 * @param openID
	 * @return PersonalInfoData
	 * @see com.siims.szb.demo.persistence.PersonalInfoPersistence#searchPersonalInfo(java.lang.String) 
	 */

	public PersonalInfoData searchPersonalInfo(String openID) {
		// TODO Auto-generated method stub
		PersonalInfoData personalInfoData = (PersonalInfoData) hibernateDao.queryData(openID, PersonalInfoData.class);
		return personalInfoData;
	}

}

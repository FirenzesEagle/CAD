
/**   
 * @Title: PersonalInfoServiceImpl.java 
 * @Package: com.siims.szb.demo.service.impl 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午8:36:40 
 * @version 0.1
 */


package com.siims.szb.personal.service.impl;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.personal.business.PersonalInfoBusiness;
import com.siims.szb.personal.data.PersonalInfoData;
import com.siims.szb.personal.service.PersonalInfoService;

/** 
 * @Description 个人信息服务层实体类
 * @author liumingbo
 * @date 2015年9月18日 下午8:36:40 
 * @version 0.1
 */

@Singleton
@AutoBind(bindClass = PersonalInfoService.class)
@TransactionalContext
public class PersonalInfoServiceImpl implements PersonalInfoService {

	/**
	 * Description 查询用户信息
	 * @param openID
	 * @return PersonalInfoData
	 * @see com.siims.szb.demo.service.PersonalInfoService#searchPersonalInfo(java.lang.String) 
	 */

	public PersonalInfoData searchPersonalInfo(String openID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(PersonalInfoBusiness.class).searchPersonalInfo(openID);
	}

}

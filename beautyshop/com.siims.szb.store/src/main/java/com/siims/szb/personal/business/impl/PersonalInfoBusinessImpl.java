
/**   
 * @Title: PersonalInfoBusinessImpl.java 
 * @Package: com.siims.szb.demo.business.impl 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午7:29:59 
 * @version 0.1
 */

package com.siims.szb.personal.business.impl;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.personal.business.PersonalInfoBusiness;
import com.siims.szb.personal.data.PersonalInfoData;
import com.siims.szb.personal.persistence.PersonalInfoPersistence;

/**
 * @Description 个人中心业务层实现类
 * @author liumingbo
 * @date 2015年9月18日 下午7:29:59
 * @version 0.1
 */

@Singleton
@AutoBind(bindClass = PersonalInfoBusiness.class)
public class PersonalInfoBusinessImpl implements PersonalInfoBusiness {

	/**
	 * Description 查询用户信息
	 * 
	 * @param openID
	 * @return PersonalInfoData
	 * @see com.siims.szb.demo.business.PersonalInfoBusiness#searchPersonalInfo(java.lang.String)
	 */

	public PersonalInfoData searchPersonalInfo(String openID) {
		// TODO Auto-generated method stub
		return ServiceContext.get(PersonalInfoPersistence.class).searchPersonalInfo(openID);
	}

}

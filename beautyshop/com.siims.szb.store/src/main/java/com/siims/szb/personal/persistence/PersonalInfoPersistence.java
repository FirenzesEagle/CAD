
/**   
 * @Title: PersonalInfoPersistence.java 
 * @Package: com.siims.szb.demo.persistence 
 * @Description: 个人信息持久化层接口
 * @author liumingbo 
 * @date 2015年9月13日 上午11:51:01 
 * @version 0.1
 */


package com.siims.szb.personal.persistence;


import com.siims.framework.base.BasePersistence;
import com.siims.szb.personal.data.PersonalInfoData;

/** 
 * @Description 个人信息持久化层接口
 * @author liumingbo
 * @date 2015年9月13日 上午11:51:01 
 * @version 0.1
 */

public interface PersonalInfoPersistence extends BasePersistence<PersonalInfoData> {
	
	/** 
	 * @Description 查询用户信息
	 * @author liumingbo
	 * @param openID
	 * @return PersonalInfoData
	 */
	public PersonalInfoData searchPersonalInfo(String openID);
	
	
}

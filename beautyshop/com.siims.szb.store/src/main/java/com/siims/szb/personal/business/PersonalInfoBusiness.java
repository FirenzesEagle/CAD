
/**   
 * @Title: PersonalBusiness.java 
 * @Package: com.siims.szb.demo.business 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午7:24:00 
 * @version 0.1
 */


package com.siims.szb.personal.business;

import com.siims.framework.base.BaseBusiness;
import com.siims.szb.personal.data.PersonalInfoData;

/** 
 * @Description 个人中心业务层接口
 * @author liumingbo
 * @date 2015年9月18日 下午7:24:00 
 * @version 0.1
 */

public interface PersonalInfoBusiness extends BaseBusiness {
	
	/** 
	 * @Description 查询用户信息
	 * @author liumingbo
	 * @param openID
	 * @return PersonalInfoData
	 */
	public PersonalInfoData searchPersonalInfo(String openID);
	
}

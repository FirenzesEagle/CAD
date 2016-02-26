
/**   
 * @Title: PersonalInfoService.java 
 * @Package: com.siims.szb.demo.service 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月18日 下午8:33:21 
 * @version 0.1
 */


package com.siims.szb.personal.service;

import com.siims.framework.base.BaseService;
import com.siims.szb.personal.data.PersonalInfoData;

/** 
 * @Description 个人中心服务层接口
 * @author liumingbo
 * @date 2015年9月18日 下午8:33:21 
 * @version 0.1
 */

public interface PersonalInfoService extends BaseService {
	/** 
	 * @Description 查询用户信息
	 * @author liumingbo
	 * @param openID
	 * @return PersonalInfoData
	 */
	public PersonalInfoData searchPersonalInfo(String openID);
}

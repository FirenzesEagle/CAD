
/**   
 * @Title: SearchPersonalInfoAction.java 
 * @Package: com.siims.szb.demo.action 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月20日 下午6:28:07 
 * @version 0.1
 */


package com.siims.szb.personal.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.personal.data.PersonalInfoData;
import com.siims.szb.personal.service.PersonalInfoService;

/** 
 * @Description 获取个人信息Action
 * @author liumingbo
 * @date 2015年9月20日 下午6:28:07 
 * @version 0.1
 */
@Namespace("/siims/szb/personal")
public class SearchPersonalInfoAction extends StrutsAction {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -2390453970104379180L;

	private PersonalInfoData personalInfoData = new PersonalInfoData();
	
	@Action(value = "tosearchpersonalinfo", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/personal-center.ftl")})
	public String toSearchPersonalInfo() {
		return SUCCESS;
	}
	
	@Action(value = "searchpersonalinfo")
	public void searchPersonalInfo() throws IOException{

		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String openID = request.getParameter("openID");

		if (openID == null || openID.equals("")) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"没有获取到个人信息\"}";
			ActionUtil.sendJson(responseResult);
		} else {
			// 根据openID查询数据库中对应的personalInfoData对象
			personalInfoData = ServiceContext.get(PersonalInfoService.class).searchPersonalInfo(openID);
			if (personalInfoData == null) {
				// response返回false
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"个人信息为空\"}";
				ActionUtil.sendJson(responseResult);
			} else {
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\": { \"openID\":\""
						+ personalInfoData.getOpenID()
						+ "\",\"user_wx_name\":\""
						+ personalInfoData.getUser_wx_name()
						+ "\",\"user_pic\":\""
						+ personalInfoData.getUser_pic()
						+ "\" } }";
				ActionUtil.sendJson(responseResult);
			}
		}

	}
	
}

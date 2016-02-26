
/**   
 * @Title: AddReceivingInfoAction.java 
 * @Package: com.siims.szb.demo.action 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月19日 上午9:07:13 
 * @version 0.1
 */


package com.siims.szb.personal.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.service.ReceivingInfoService;

/** 
 * @Description 添加收货信息Action
 * @author liumingbo
 * @date 2015年9月19日 上午9:07:13 
 * @version 0.1
 */
@Namespace("/siims/szb/personal")
public class AddReceivingInfoAction extends StrutsAction {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 3762918543684210026L;

	private ReceivingInfoData receivingInfoData = new ReceivingInfoData();
	
	@Action(value = "toaddreceivinginfo", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/address-add.ftl")})
	public String toAddReceivingInfo() {
		return SUCCESS;
	}
	
	@Action(value = "addreceivinginfo")
	public void addReceivingInfo() throws IOException {

		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		// 获取提交的信息
		String json = request.getParameter("DATA");
		if(json == null || json.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
		}
		
		Gson gson = new Gson();
		receivingInfoData = gson.fromJson(json, ReceivingInfoData.class);
		
		if (receivingInfoData == null) {
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"解析数据失败\"}";
			ActionUtil.sendJson(responseResult);
		} else {
			// 将信息提交至数据库进行存储
			String msg = ServiceContext.get(ReceivingInfoService.class).addReceivingInfo(receivingInfoData);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + msg + "\"}";
			ActionUtil.sendJson(responseResult);
		}
		
	}
	
}

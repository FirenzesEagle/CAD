
/**   
 * @Title: EditReceivingInfoAction.java 
 * @Package: com.siims.szb.demo.action 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月20日 下午5:13:21 
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
 * @Description 编辑收货信息Action
 * @author liumingbo
 * @date 2015年9月20日 下午5:13:21 
 * @version 0.1
 */
@Namespace("/siims/szb/personal")
public class EditReceivingInfoAction extends StrutsAction {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -7906155053365475351L;
	
	private ReceivingInfoData receivingInfoData = new ReceivingInfoData();
	
	@Action(value = "toeditreceivinginfo", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/address-add.ftl")})
	public String toEditReceivingInfo() {
		return SUCCESS;
	}
	
	@Action(value = "editreceivinginfo")
	@SuppressWarnings("unused")
	public void editReceivingInfo() throws IOException{
		
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
		
		String receiving_id = receivingInfoData.getReceiving_id();
		
		if (receiving_id == null || receiving_id.equals("")) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"没有获取到要修改的收货信息\"}";
			ActionUtil.sendJson(responseResult);
		} else {
			if (receivingInfoData == null) {
				// response返回false
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"收货信息为空\"}";
				ActionUtil.sendJson(responseResult);
			} else {
//				String openID = request.getParameter("openID");
//				String receiving_name = request.getParameter("receiving_name");
//				String receiving_tel = request.getParameter("receiving_tel");
//				String receiving_address_area = request.getParameter("receiving_address_area");
//				String receiving_address_detail = request.getParameter("receiving_address_detail");
//				Integer default_receiving_info = Integer.parseInt(request.getParameter("default_receiving_info"));
//				
//				//将数据已对象方式存储
//				receivingInfoData.setOpenID(openID);
//				receivingInfoData.setReceiving_name(receiving_name);
//				receivingInfoData.setReceiving_tel(receiving_tel);
//				receivingInfoData.setReceiving_address_area(receiving_address_area);
//				receivingInfoData.setReceiving_address_detail(receiving_address_detail);
//				receivingInfoData.setDefault_receiving_info(default_receiving_info);
				
				// 在数据库中编辑receivingInfoData对象
				boolean flag = ServiceContext.get(ReceivingInfoService.class).editReceivingInfo(receivingInfoData);
				if (flag = true) {
					responseResult = "{\"SUCCESS\":\"true\"}";
					ActionUtil.sendJson(responseResult);
				} else {
					responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"收货信息修改失败\"}";
					ActionUtil.sendJson(responseResult);
				}
				
			}
		}
		
	}

}

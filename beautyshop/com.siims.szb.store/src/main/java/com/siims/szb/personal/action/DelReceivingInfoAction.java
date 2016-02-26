
/**   
 * @Title: DelReceivingInfoAction.java 
 * @Package: com.siims.szb.demo.action 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月20日 下午4:41:34 
 * @version 0.1
 */


package com.siims.szb.personal.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.service.ReceivingInfoService;

/** 
 * @Description 删除收货信息Action
 * @author liumingbo
 * @date 2015年9月20日 下午4:41:34 
 * @version 0.1
 */
@Namespace("/siims/szb/personal")
public class DelReceivingInfoAction extends StrutsAction {

	/** @Fields serialVersionUID: */

	private static final long serialVersionUID = -5208601393718250033L;

	private ReceivingInfoData receivingInfoData = new ReceivingInfoData();

//	@Action(value = "todelreceivinginfo", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/demo.ftl")})
//	public String toDelReceivingInfo() {
//		return SUCCESS;
//	}
//	
	@Action(value = "delreceivinginfo")
	@SuppressWarnings("unused")
	public void delReceivingInfo() throws IOException {

		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String receiving_id = request.getParameter("receiving_id");

		if (receiving_id == null || receiving_id.equals("")) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"没有获取到要删除的收货信息\"}";
			ActionUtil.sendJson(responseResult);
		} else {
			// 根据receiving_id查询数据库中对应的receivingInfoData对象
			receivingInfoData = ServiceContext.get(ReceivingInfoService.class).searchReceivingInfo(receiving_id);
			if (receivingInfoData == null) {
				// response返回false
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"收货信息为空\"}";
				ActionUtil.sendJson(responseResult);
			} else {
				// 在数据库中删除receivingInfoData对象
				boolean flag = ServiceContext.get(ReceivingInfoService.class).delReceivingInfo(receivingInfoData);
				if (flag = true) {
					responseResult = "{\"SUCCESS\":\"true\"}";
					ActionUtil.sendJson(responseResult);
				} else {
					responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"收货信息删除失败\"}";
					ActionUtil.sendJson(responseResult);
				}
			}
		}
	}

}

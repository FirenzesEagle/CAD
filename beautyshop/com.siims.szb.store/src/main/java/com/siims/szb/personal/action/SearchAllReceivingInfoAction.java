
/**   
 * @Title: SearchAllReceivingInfoAction.java 
 * @Package: com.siims.szb.demo.action 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月20日 下午5:34:07 
 * @version 0.1
 */


package com.siims.szb.personal.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.service.ReceivingInfoService;

/** 
 * @Description 获取对应openID用户所有收货信息Action
 * @author liumingbo
 * @date 2015年9月20日 下午5:34:07 
 * @version 0.1
 */
@Namespace("/siims/szb/personal")
public class SearchAllReceivingInfoAction extends StrutsAction {

	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -4651433333942576534L;
	
	private List<ReceivingInfoData> receivingInfoDataList;
	
	@Action(value = "tosearchallreceivinginfo", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/address-list.ftl")})
	public String toSearchAllReceivingInfo() {
		return SUCCESS;
	}
	
	@Action(value = "searchallreceivinginfo")
	@SuppressWarnings("rawtypes")
	public void searchAllReceivingInfo() throws IOException{
		
		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String openID = request.getParameter("openID");
		
		if (openID == null || openID.equals("")) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"没有获取到用户的openID\"}";
			ActionUtil.sendJson(responseResult);
		} else {
			// 根据openID查询数据库中店铺中所有的ReceivingInfoData对象
			receivingInfoDataList = ServiceContext.get(ReceivingInfoService.class)
					.searchAllReceivingInfo(openID);
			if (receivingInfoDataList == null || receivingInfoDataList.size() == 0) {
				// response返回false
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"收货信息为空\"}";
				ActionUtil.sendJson(responseResult);
			} else {
				String tempResponseResult = "";
				for (Iterator iterator = receivingInfoDataList.iterator(); iterator
						.hasNext();) {
					ReceivingInfoData receivingInfoData = (ReceivingInfoData) iterator
							.next();
					tempResponseResult += "{ \"receiving_id\":\""
							+ receivingInfoData.getReceiving_id()
							+ "\",\"openID\":\""
							+ receivingInfoData.getOpenID()
							+ "\",\"receiving_name\":\""
							+ receivingInfoData.getReceiving_name()
							+ "\",\"receiving_tel\":\""
							+ receivingInfoData.getReceiving_tel()
							+ "\",\"receiving_address_area\":\""
							+ receivingInfoData.getReceiving_address_area()
							+ "\",\"receiving_address_detail\":\""
							+ receivingInfoData.getReceiving_address_detail()
							+ "\",\"default_receiving_info\":\""
							+ receivingInfoData.getDefault_receiving_info()
							+ "\" }" + ",";
				}
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":["
						+ tempResponseResult.substring(0,
								tempResponseResult.length() - 1) + "]}";
				ActionUtil.sendJson(responseResult);
			}

		}

	}
	
}

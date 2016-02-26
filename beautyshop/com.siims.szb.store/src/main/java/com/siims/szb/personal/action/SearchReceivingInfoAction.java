
/**   
 * @Title: SearchReceivingInfoAction.java 
 * @Package: com.siims.szb.demo.action 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月20日 下午5:56:31 
 * @version 0.1
 */


package com.siims.szb.personal.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.personal.data.ReceivingInfoData;
import com.siims.szb.personal.service.ReceivingInfoService;

/** 
 * @Description 获取单条收货信息Action
 * @author liumingbo
 * @date 2015年9月20日 下午5:56:31 
 * @version 0.1
 */
@Namespace("/siims/szb/personal")
public class SearchReceivingInfoAction extends StrutsAction {

	/** @Fields serialVersionUID: */

	private static final long serialVersionUID = 1781904549624413039L;

	@Action(value = "searchreceivinginfo")
	public void searchReceivingInfo() throws IOException {

		ReceivingInfoData receivingInfoData = new ReceivingInfoData();
		
		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String receiving_id = request.getParameter("receiving_id");

		if (receiving_id == null || receiving_id.equals("")) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"没有获取到收货信息\"}";
			ActionUtil.sendJson(responseResult);
		} else {
			// 根据receiving_id查询数据库中对应的receivingInfoData对象
			receivingInfoData = ServiceContext.get(ReceivingInfoService.class).searchReceivingInfo(receiving_id);
			if (receivingInfoData == null) {
				// response返回false
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"收货信息为空\"}";
				ActionUtil.sendJson(responseResult);
			} else {
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\": { \"receiving_id\":\""
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
						+ "\" } }";
				ActionUtil.sendJson(responseResult);
			}
		}

	}
	
	/** 
	 * @Description 根据openID获取默认收货信息
	 * @author liumingbo
	 * @param openID
	 * @return  defaultReceivingInfoData
	 */
	
	@Action(value = "searchdefaultreceivinginfo")
	@SuppressWarnings("rawtypes")
	public void searchDefaultReceivingInfo() throws IOException {

		List<ReceivingInfoData> receivingInfoDataList;
		ReceivingInfoData defaultReceivingInfoData = null;
		
		// 初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String openID = request.getParameter("openID");

		if (openID == null || openID.equals("")) {
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"没有获取到用户的openID\"}";
			ActionUtil.sendJson(responseResult);
		} else{
			receivingInfoDataList = ServiceContext.get(ReceivingInfoService.class)
					.searchAllReceivingInfo(openID);
			if (receivingInfoDataList.isEmpty()) {
				// response返回false
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"\"}";
				ActionUtil.sendJson(responseResult);
			} else {
				//是否存在默认收货地址标记
				boolean haveDefaultReceivingInfoData = false;
				for (Iterator iterator = receivingInfoDataList.iterator(); iterator.hasNext();) {
					ReceivingInfoData receivingInfoData = (ReceivingInfoData) iterator.next();
					if(receivingInfoData.getDefault_receiving_info() == 1){
						haveDefaultReceivingInfoData = true;
						defaultReceivingInfoData = receivingInfoData;
					}
				}
				//如果不存在默认收货地址，则返回List中的第一条数据
				if(haveDefaultReceivingInfoData == false){
					defaultReceivingInfoData = receivingInfoDataList.get(0);
				}
				
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\": { \"openID\":\""
						+ defaultReceivingInfoData.getOpenID()
						+ "\",\"receiving_id\":\""
						+ defaultReceivingInfoData.getReceiving_id()
						+ "\",\"receiving_name\":\""
						+ defaultReceivingInfoData.getReceiving_name()
						+ "\",\"receiving_tel\":\""
						+ defaultReceivingInfoData.getReceiving_tel()
						+ "\",\"receiving_address_area\":\""
						+ defaultReceivingInfoData.getReceiving_address_area()
						+ "\",\"receiving_address_detail\":\""
						+ defaultReceivingInfoData.getReceiving_address_detail()
						+ "\",\"default_receiving_info\":\""
						+ defaultReceivingInfoData.getDefault_receiving_info()
						+ "\" } }";
				ActionUtil.sendJson(responseResult);
			}

			
		}
		
	}

}

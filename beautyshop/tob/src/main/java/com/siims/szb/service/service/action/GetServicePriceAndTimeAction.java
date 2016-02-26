package com.siims.szb.service.service.action;

import java.io.IOException;




import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;




import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;

import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.service.ServiceConfigService;



/**
 * 根据服务规格id获取规格的价格和时长
 * @author liu
 *
 */

@Namespace("/siims/szb/service")
public class GetServicePriceAndTimeAction extends StrutsAction{

	/**
	 * @author liu
	 * @since 2015.9.7
	 */
	private static final long serialVersionUID = 1L;
	
	private String configId;
	
	
	
	@Action(value = "getServicePriceAndTimeById")
	public void getServicePriceAndTimeById() throws IOException {
		
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//根据服务规格id获取该规格实体
		configId = request.getParameter("configId");
		
		ServiceConfigData configData = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(configId);
		
		
		
		if(configData == null )
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Service\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			msg = gson.toJson(configData.getConfigPrice())+","+gson.toJson(configData.getServiceTime());
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()) + "],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
}

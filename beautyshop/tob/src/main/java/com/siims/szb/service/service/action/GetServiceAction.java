package com.siims.szb.service.service.action;

import java.io.IOException;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;


import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;

/**根据服务的id获取服务
 * @author liu
 * @since 2015.9.7
 */
@Namespace("/siims/szb/service")
public class GetServiceAction extends StrutsAction{

	
	private static final long serialVersionUID = 1L;
	
	private String serviceId;
	
	
	
	@Action(value = "getServiceById")
	public void getServiceById() throws IOException {
		
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//根据服务id获取该服务实体
		serviceId = request.getParameter("serviceId");
		ServiceInfoData serviceData = ServiceContext.get(ServiceService.class).searchServiceInfo(serviceId);
		
		
		
		if(serviceData == null )
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Service\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			msg = gson.toJson(serviceData);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
}

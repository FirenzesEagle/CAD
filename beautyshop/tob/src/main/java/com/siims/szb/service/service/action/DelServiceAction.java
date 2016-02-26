package com.siims.szb.service.service.action;

import java.io.IOException;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.service.ServiceConfigService;



@Namespace("/siims/szb/service")
public class DelServiceAction extends StrutsAction {

	/**
	 * @author liufeng
	 * @since 2015年8月20日16:24:49
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ServiceConfigData configData ;
	
	@Action(value = "deleteServiceById")
	/**
	 * 根据服务的id删除服务
	 * @throws IOException
	 */
	public void deleteServiceById() throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String configId = request.getParameter("serviceId");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		//根据configId获取服务对象
		configData = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(configId);
		
		//删除服务
		if(ServiceContext.get(ServiceConfigService.class).delConfigInfo(configData))
			responseResult = "{\"SUCCESS\":\"true\"}";
		
		ActionUtil.sendJson(responseResult);
	}
	
	
	
}

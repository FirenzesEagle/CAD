package com.siims.szb.szbldk.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.service.GetShopIdByOpenIdService;

@Namespace("/siims/szb/ShoppingTrollyAction")
public class GetShopIdByOpenIdAction extends StrutsAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 根据openid查询shopid
	 */
	@Action(value = "getShopIdByOpenId")
	public void getShopIdByOpenId() {
		Gson gson = new Gson();
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		String openid = request.getParameter("openid");
		
		if(openid == null || openid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		List<Map<String, String>> result = ServiceContext.get(GetShopIdByOpenIdService.class).getShopIdByOpenId(openid);
		
		String msg= gson.toJson(result);
		responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" + msg + "}";
		ActionUtil.sendJson(responseResult);
	}

}

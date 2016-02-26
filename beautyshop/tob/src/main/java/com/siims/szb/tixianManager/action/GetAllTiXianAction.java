package com.siims.szb.tixianManager.action;


import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.tixianManager.data.TiXianData;
import com.siims.szb.tixianManager.service.TiXianService;


@Namespace("/siims/szb/tixian/notOthers/but/wangyun/yes")
public class GetAllTiXianAction extends StrutsAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Action(value = "getTixianForWangyun", results = { @Result(name = "success", type = "freemarker", location = "/ui/ftl/tixianManager/queryTiXian.ftl") })
	public String getTixianForWangyun() {

		return SUCCESS;
	}

	@Action(value = "getTixianRequest")
	public void getTixianRequest() throws IOException{
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		List<TiXianData> result = ServiceContext.get(TiXianService.class).getTixianRequest();
		
		response.setContentType("application/json;charset=UTF-8"); 
		response.getWriter().write(gson.toJson(result));
	}

}

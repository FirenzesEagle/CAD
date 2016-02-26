package com.siims.szb.tixianManager.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.tixianManager.data.TiXianMoneyData;
import com.siims.szb.tixianManager.service.TiXianMoneyService;


@Namespace("/siims/szb/tixianMoney")
public class TiXianMoneyAction extends StrutsAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "tixianMoneyRequest")
	public void tixianMoneyRequest() throws IOException{
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String shoperId = request.getParameter("openId");
		
		if (shoperId.equals("") || shoperId == null){
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write("error");
		}
		else{
			List<TiXianMoneyData> res= ServiceContext.get(TiXianMoneyService.class).getTixianMoney(shoperId);
			
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write(gson.toJson(res));
		}
		
	}
	
	@Action(value = "updateTixianMoneyRequest")
	public void updateTixianMoneyRequest() throws IOException{
		
		//Gson gson = new GsonBuilder().serializeNulls().create();
		
		String shoperId = request.getParameter("openId");
		float money = Float.parseFloat(request.getParameter("money"));
		
		if (shoperId.equals("") || shoperId == null || money == 0){
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write("error");
		}
		else{
			ServiceContext.get(TiXianMoneyService.class).updateTixianMoney(shoperId,money);
			
			response.setContentType("application/json;charset=UTF-8"); 
			//response.getWriter().write("success");
		}
		
	}

}

package com.siims.szb.tixianManager.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.tixianManager.data.TiXianData;
import com.siims.szb.tixianManager.service.TiXianService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Namespace("/siims/szb/tixian")
public class TiXianAction extends StrutsAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Action(value = "toTiXianIndex", results = { @Result(name = "success", type = "freemarker", location = "/ui/ftl/tixianManager/withdrawIndex.ftl") })
	public String toTiXianIndex() {

		return SUCCESS;
	}
	
	@Action(value = "toTiXianPage", results = { @Result(name = "success", type = "freemarker", location = "/ui/ftl/tixianManager/withdrawPage.ftl") })
	public String toTiXianPage() {

		return SUCCESS;
	}
	
	@Action(value = "toTiXianSuc", results = { @Result(name = "success", type = "freemarker", location = "/ui/ftl/tixianManager/withdrawSuc.ftl") })
	public String toTiXianSuc() {

		return SUCCESS;
	}
	
	@Action(value = "toWithdrawRec", results = { @Result(name = "success", type = "freemarker", location = "/ui/ftl/tixianManager/withdrawRec.ftl") })
	public String toWithdrawRec() {

		return SUCCESS;
	}

	@Action(value = "tixianRequest")
	public void tixianRequest() throws IOException{
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String shoperId = request.getParameter("openId");
		String shoperName = request.getParameter("shoperName");
		String zhiFuBaoAccount = request.getParameter("zhiFuBaoAccount");
		int type = Integer.parseInt(request.getParameter("type"));
		String secret = request.getParameter("secret");
		float money = Float.parseFloat(request.getParameter("money"));
		String cardType = request.getParameter("cardType");
		String cardNumber = request.getParameter("cardNumber");
		
		if (shoperId.equals("") || shoperId == null){
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write("error");
		}
		else{
			String id = ServiceContext.get(TiXianService.class).tixianRequest(shoperId,shoperName,zhiFuBaoAccount,type,secret,money,cardType,cardNumber);
			
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write(id);
		}
		
	}
	
	@Action(value = "getTixianRequestByShopId")
	public void getTixianRequestByShopId() throws IOException{
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String shoperId = request.getParameter("openId");
		
		if (shoperId.equals("") || shoperId == null){
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write("error");
		}
		else{
			List<Map<String, String>> result = ServiceContext.get(TiXianService.class).getTixianRequestByShopId(shoperId);
			
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().write(gson.toJson(result));
		}
		
	}
	
	
}

package com.siims.szb.bespeakorder.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.service.BespeakOrderService;
@Namespace("/siims/szb/bespeakorder")
public class ChangeBespeakStateAction extends StrutsAction{

	/**改变服务订单状态
	 * @author ling
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakOrderData data = new BespeakOrderData();
	
	@Action(value = "tochangebespeakstate", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/XXX.ftl")})
	public String tochangebespeakstate() {
		return SUCCESS;
	}
	
	@Action(value = "changebespeakstate")
	public void ChanegeBespeakState() throws IOException{
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		String orderid = request.getParameter("orderid");
		
		int type = -1;
		
		if(orderid == null || orderid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No order\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		if(request.getParameter("type") == null)
		{
			type = -1;
		}else{
			type = Integer.valueOf(request.getParameter("type").toString());
		}
		
		if(type != -1)
		{
			data = ServiceContext.get(BespeakOrderService.class).GetBespeakOrderById(orderid);
			if(data != null)
			{
				//确认收款的部分
				if(type == 2 && data.getState() == 1)
				{
					data.setState(type);
					data.setFinishtime(new Date());
					ServiceContext.get(BespeakOrderService.class).EditBespeakOrder(data);
					responseResult = "{\"SUCCESS\":\"true\"}";
					ActionUtil.sendJson(responseResult);
				}else{
					responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"State Error\"}";
					ActionUtil.sendJson(responseResult);
				}
			}
		}else{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"State Error\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
}

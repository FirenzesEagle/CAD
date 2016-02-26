package com.siims.szb.bespeak.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.bespeak.data.BespeakData;
import com.siims.szb.bespeak.service.BespeakService;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.service.BespeakOrderService;

@Namespace("/siims/szb/bespeak")
public class CancelBespeakAction extends StrutsAction{

	/**取消预约，同时删除服务订单
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakData data = new BespeakData();
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	@Action(value = "tocancelbespeak", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/XXX.ftl")})
	public String tocancelbespeak() {
		return SUCCESS;
	}
	
	@Action(value = "cancelbespeak")
	public void CancelBespeak() throws IOException{
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
				
		String bespeakid = request.getParameter("bespeakid");
		
		System.out.println("1");
		
		if(bespeakid == null || bespeakid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No bespeak\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		System.out.println("2");
		
		String personid = request.getParameter("personid");
		
		if(personid == null || personid.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No person\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		
		data = ServiceContext.get(BespeakService.class).GetBespeakById(bespeakid);
		if(data == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No record\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}else{
			if(!data.getPersonid().equals(personid))
			{
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"Error\"}";
				ActionUtil.sendJson(responseResult);
				return;
			}else{
				ServiceContext.get(BespeakService.class).DeleteBespeak(data);
				List<BespeakOrderData> list = ServiceContext.get(BespeakOrderService.class).GetBespeaorderByBespeak(data.getId());
				if(list != null)
				{
					orderdata = list.get(0);
					orderdata.setState(0);
					ServiceContext.get(BespeakOrderService.class).EditBespeakOrder(orderdata);
					responseResult = "{\"SUCCESS\":\"true\"}";
					ActionUtil.sendJson(responseResult);
				}else{
					responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No record\"}";
					ActionUtil.sendJson(responseResult);
					return;
				}
			}
		}
	}

}

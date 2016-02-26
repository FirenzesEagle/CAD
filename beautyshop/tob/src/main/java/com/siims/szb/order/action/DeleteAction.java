package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.ResultFacade;
import com.siims.szb.order.service.OrderService;
import com.siims.szb.order.util.ReplyUtil;

@Namespace("/siims/szb/order")
public class DeleteAction extends StrutsAction {

	private String id;
	
	@Action(value="delete")
	public void delete() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		if (id == null || id.equals("")) {
			ResultFacade facade = new ResultFacade(ResultType.error, "传入的OrderId无效");
			ReplyUtil.reply(response, gson.toJson(facade));
		} else {
			ServiceContext.get(OrderService.class).delete(id);
			ResultFacade facade = new ResultFacade(ResultType.success, "订单删除成功");
			ReplyUtil.reply(response, gson.toJson(facade));
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

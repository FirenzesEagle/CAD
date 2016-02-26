package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/siims/szb/order")
public class Begin1Action extends StrutsAction {
	
	@Action(value = "toOrderManage", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/orderManage.ftl")})
	public String toOrderManage() {
		return SUCCESS;
	}
	
	@Action(value = "detail", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/orderDetail.ftl")})
	public String tochangebespeakstate() {
		return SUCCESS;
	}
}

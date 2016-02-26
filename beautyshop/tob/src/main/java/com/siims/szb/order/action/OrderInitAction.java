package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/siims/szb/order")
public class OrderInitAction extends StrutsAction {

	@Action(value = "init", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/orderInit.ftl")})
	public String tochangebespeakstate() {
		return SUCCESS;
	}
	
}

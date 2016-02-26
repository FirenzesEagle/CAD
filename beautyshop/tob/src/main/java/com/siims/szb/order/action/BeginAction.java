package com.siims.szb.order.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/siims/szb/order")
public class BeginAction extends StrutsAction {
	
	@Action(value = "begin", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/my-order.ftl")})
	public String tochangebespeakstate() {
		return SUCCESS;
	}
}

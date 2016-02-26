package com.siims.szb.vipcard.action.p2b;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * 会员卡规则设置入口的Action
 * @author libo
 * 2015-09-15
 */
@Namespace("/vipcard/p2b")
public class VipCardConfigManageAction extends StrutsAction{

	private static final long serialVersionUID = 1L;
	private List<VipCardConfigData> list;
	
	/**
	 * 会员卡规则设置的入口
	 * @author libo <br/>
	 * 2015-09-15
	 */
	@Action(value = "vipcardconfigmanage", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/membershipRuleManage.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String vipcardConfigManage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//从GET里面去typeId
		String typeId = request.getParameter("typeId");
		if(typeId == null || typeId.equals(""))
			return ERROR;
		
		//根据typeId获取所有的ConfigData
		list = ServiceContext.get(VipCardConfigService.class).getConfigDataByTypeId(typeId);
		
		return SUCCESS;
	}

	public List<VipCardConfigData> getList() {
		return list;
	}

	public void setList(List<VipCardConfigData> list) {
		this.list = list;
	}

}

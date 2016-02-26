package com.siims.szb.vipcard.action.p2b;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.vipcard.sbp.data.VipCardTypeData;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;
import com.siims.szb.vipcard.sbp.service.VipCardTypeService;

/**
 * 会员卡规则添加
 * @author libo
 * 2015-09-18
 */
@Namespace("/vipcard/p2b")
public class VipCardConfigAddAction extends StrutsAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 添加会员卡规则的跳转页面
	 * @author libo
	 * 2015-10-29
	 * @return
	 */
	@Action(value = "addconfigs", 
			results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/vipcardp2bftl/membershipRule.ftl"),
			@Result(name = "error", type = "freemarker", location = "/ui/ftl/error.ftl")})
	public String addConfigPage() {
		
		return SUCCESS;
	}
	
	
	/**
	 * 添加一个新的会员卡规则
	 * @author libo
	 * 2015-09-18
	 */
	@Action(value = "addconfig")
	public void addConfig() {
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setHeader("Access-Control-Allow-Origin", "*");
		//从GET里面去数据
		String json = "";
		try{
			String typeId = request.getParameter("typeId");
			int type = Integer.valueOf(request.getParameter("type"));
			double price = Double.valueOf(request.getParameter("price"));
			double benefit = Double.valueOf(request.getParameter("benefit"));
			
			//检查typeId是否存在
			VipCardTypeData typeData = ServiceContext.get(VipCardTypeService.class).queryVipCardTypeDataById(typeId);
			if(typeData == null) {
				json = "{\"SUCCESS\":false, \"MSG\":\"会员卡类型不存在\"}";
				ActionUtil.sendJson(json);
				return;
			}
			
			//插入数据
			ServiceContext.get(VipCardConfigService.class).addVipCardConfigData(typeId, type, price, benefit);
			json = "{\"SUCCESS\":true, \"MSG\":\"插入成功\"}";
			ActionUtil.sendJson(json);
			return;
		}
		catch(Exception e) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
	}
}
	
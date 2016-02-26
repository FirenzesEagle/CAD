package com.siims.szb.vipcard.action.p2b;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.vipcard.sbp.data.VipCardConfigData;
import com.siims.szb.vipcard.sbp.service.VipCardConfigService;

/**
 * 停止发售某个规格的会员卡
 * @author libo
 * 2015-09-17
 */
@Namespace("/vipcard/p2b")
public class VipCardConfigStopAction extends StrutsAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 停售某个规格的会员卡
	 * @author libo
	 * 2015-09-17
	 */
	@Action(value = "stopconfig")
	public void stopConfig() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpServletRequest request = ServletActionContext.getRequest();
		String configId = request.getParameter("configid");
		String json;
		
		//参数检查
		if(configId == null || configId.equals("")) {
			json = "{\"SUCCESS\":false, \"MSG\":\"参数错误\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//先查询是否存在，再更新
		VipCardConfigData data = ServiceContext.get(VipCardConfigService.class).queryVipCardDataByConfigId(configId);
		if(data == null) {
			json = "{\"SUCCESS\":false, \"MSG\":\"该规格不存在\"}";
			ActionUtil.sendJson(json);
			return;
		}
		
		//将isdelete置为1，表删除，即停售
		data.setIsdelete(1);
		data.setTime(new Date());
		ServiceContext.get(VipCardConfigService.class).updateVipCardConfigData(data);
		json = "{\"SUCCESS\":true, \"MSG\":\"更新成功\"}";
		ActionUtil.sendJson(json);
		return;
		
	}
	
}

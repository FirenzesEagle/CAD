package com.siims.szb.szbldk.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;


public class GetStoreAction extends StrutsAction{

	/**通过网店ID找出网店的详细信息
	 * @author ling
	 * @since 2015年9月7日10:42:45
	 */
	private static final long serialVersionUID = 1L;
	
	private StoreInfoData store = new StoreInfoData();
	
	@Action(value = "getstore")
	public void GetStore() throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//获得网店的ID
		String id = request.getParameter("shop_id");
		//判断ID是否为空
		if(id == null || id.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No StoreID\"}";
			ActionUtil.sendJson(responseResult);
		}
		//获得网店实体
		store = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(id);
		//对实体进行非空判断
		if(store == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No District\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			msg = gson.toJson(store);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + msg + "\",\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}

}

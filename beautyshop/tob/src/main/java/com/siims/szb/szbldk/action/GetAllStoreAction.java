package com.siims.szb.szbldk.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;


@Namespace("/siims/szb/store")
public class GetAllStoreAction extends StrutsAction{

	/**获取商圈下所有的网店的信息
	 * @author ling
	 * @since 2015年9月7日10:09:10
	 */
	private static final long serialVersionUID = 1L;
	
	@Action(value = "getallstore")
	public void GetAllStore() throws IOException{
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
				
		//获得商圈的ID
		String id = request.getParameter("district_id");
				
		//判断ID是否为空
		if(id == null || id.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No DistrictID\"}";
			ActionUtil.sendJson(responseResult);
		}
		
		//获取所有的网店实体
		List<StoreInfoData> list = ServiceContext.get(StoreService.class).getAllStoreByDistrictId(id);
		
		if(list == null || list.size() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Stores\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			
			String res = JSON.toJSONString(list);
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":" +res +",\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}

}

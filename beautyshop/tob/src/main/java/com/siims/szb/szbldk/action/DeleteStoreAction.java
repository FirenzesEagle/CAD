package com.siims.szb.szbldk.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;

import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;

public class DeleteStoreAction extends StrutsAction{

	/**将网店进行删除
	 * @author ling
	 * @since 2015年9月7日14:46:11
	 */
	private static final long serialVersionUID = 1L;
	
	@Action(value = "deletestore")
	public void DeleteStore() throws IOException{
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
				
		String id = request.getParameter("store_id");
		
		if(id == null || id.length() == 0)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No StoreID\"}";
			ActionUtil.sendJson(responseResult);
		}
		
		StoreInfoData storeInfoData = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(id);
		
		if(storeInfoData == null )
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Store\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			ServiceContext.get(StoreService.class).delStore(storeInfoData);
			responseResult = "{\"SUCCESS\":\"true\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
}

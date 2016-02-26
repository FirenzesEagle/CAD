package com.siims.szb.goods.goods.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;

@Namespace("/siims/szb/goods")
public class GetGoodsPriceByIdAction extends StrutsAction{

	/**
	 * @author liu
	 * @since 2015.9.7
	 */
	private static final long serialVersionUID = 1L;
	
	//规格id
	private String commodityId;
	
	
	
	@Action(value = "getGoodsPrice")
	public void getGoodsPrice() throws IOException {
		
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		commodityId = request.getParameter("commodityId");
		CommodityConfigData commodityConfigData =ServiceContext.get(CommodityConfigService.class).getCommodityConfig(commodityId);
		if(commodityConfigData == null || commodityId==null || commodityId.equals(""))
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"No Goods\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			msg = gson.toJson(commodityConfigData.getConfigPrice());
			
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()) + "],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
}

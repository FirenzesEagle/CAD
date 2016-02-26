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
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.service.ShelfInfoService;
import com.siims.szb.szbldk.service.ShoppingTrollyService;



@Namespace("/siims/szb/goods")
public class ChangeStatesAction extends StrutsAction{

	/**
	 * @author liufeng
	 * 
	 * @since 2015.9.7
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private String goodsId;
	private String type;
	private String isOnShelf;
	private String configId;
//	@Action(value = "goodslisttob", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/goodsManage.ftl")})
//	public String goodslisttob() {
//		return SUCCESS;
//	}
	
	
	@Action(value = "deleteGoods")
	public void deleteGoods() throws IOException{
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		/*****************************************/
		configId = request.getParameter("goodsId");
		/******************************************/
		CommodityConfigData commodityConfig = ServiceContext.get(CommodityConfigService.class).getCommodityConfig(configId);
		
		
		if(commodityConfig == null )
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"删除失败\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			commodityConfig.setIsDelete("1");
			ServiceContext.get(ShoppingTrollyService.class).deleteShoppingTrollyItem(commodityConfig.getId());
			
			ServiceContext.get(CommodityConfigService.class).editCommodityConfig(commodityConfig);
			
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"删除成功\"}";
			ActionUtil.sendJson(responseResult);
		}
	}
	
	
	@Action(value = "changeSaleOrNot")
	public void changeSaleOrNot() throws IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		
		
		configId = request.getParameter("goodsId");
		type =request.getParameter("type");
		
		if(type.equals("sale")) type="现货";
		if(type.equals("book")) type="预定";
		System.out.println(1);
		CommodityConfigData commodityConfig = ServiceContext.get(CommodityConfigService.class).getCommodityConfig(configId);
		System.out.println(2);
		String goodsId = commodityConfig.getGoodsId();
		System.out.println("lflflf"+goodsId);
		GoodsInfoData goodsInfo = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsId);
		
		
		if(goodsInfo == null )
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"修改失败\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			goodsInfo.setGoodsType(type);
			ServiceContext.get(GoodsService.class).editGoodsInfo(goodsInfo);
			System.out.println(goodsInfo.getGoodsType()+"  12345");
			
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"修改成功\"}";
			
			
			ActionUtil.sendJson(responseResult);
		}
	}
	
	
	@Action(value = "changeUpOrNot")
	public void changeUpOrNot() throws IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		
		
		configId = request.getParameter("goodsId");
		isOnShelf =request.getParameter("isOnShelf");
		
		
		
		//GoodsInfoData goodsInfo = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsId);
		ShelfInfoData shelfInfoData = ServiceContext.get(ShelfInfoService.class).searchShelfInfoByConfigId(configId).get(0);
		
		if(shelfInfoData == null )
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"修改失败\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			shelfInfoData.setOnShelf(isOnShelf);
			ServiceContext.get(ShelfInfoService.class).editShelfInfo(shelfInfoData);
			
			
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"修改成功\"}";
			
			
			ActionUtil.sendJson(responseResult);
		}
	}
}

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



@Namespace("/siims/szb/goods")
public class GoodsListAction extends StrutsAction{

	/**
	 * @author liufeng
	 * 
	 * @since 2015.9.7
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String storeId;
	
	@Action(value = "goodslist", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/shop-goodlist.ftl")})
	public String goodslist() {
		return SUCCESS;
	}
	
	@Action(value = "goodsListByStoreId")
	public void goodsListByStoreId() throws IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//根据商铺id获取该商铺的所有的商品
		List<GoodsInfoData> listGoods;
		List<CommodityConfigData> listConfig;
		List<ShelfInfoData> listShelf;
		ShelfInfoData shelfInfoData;
		storeId = request.getParameter("storeId");
		
		listGoods = ServiceContext.get(GoodsService.class).searchAllGoodsInfo(storeId);
		
		
		
		if(listGoods == null || listGoods.size() == 0)
		{
			responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"\"}";
			ActionUtil.sendJson(responseResult);
		}else{
			Gson gson = new Gson();
			for(int i = 0; i < listGoods.size(); i++)
			{
				//展示的服务只有商品的isdelete字段为"0"
				if(listGoods.get(i).getIsDelete().equals("0"))
				{
					System.out.println(listGoods.get(i).getIsDelete());
					String goodsId = listGoods.get(i).getId();
					System.out.println("1");
					listConfig = ServiceContext.get(CommodityConfigService.class).getAllCommodityConfig(goodsId);
					System.out.println(listGoods.get(i).getGoodsName());
					for(int j = 0; j<listConfig.size();j++)
					{
						//根据规格id获取该规格货架信息
						String configId = listConfig.get(j).getId();
						System.out.println("2");
						listShelf = ServiceContext.get(ShelfInfoService.class).searchShelfInfoByConfigId(configId);
						System.out.println("3");
						//if(listShelf.get(0)==null) System.out.println("lalala");
						System.out.println(listShelf.size()+"  hehe");
						if(listConfig.get(j).getIsDelete().equals("0")){
						if(listShelf.get(0).getOnShelf().equals("1"))
						{
							//这里的goodsId是指configId,realGoodsId才是goodsId
							String temp = "{"+"\""+"goodsId"+"\""+":"+"\""+configId+"\""+","
									+"\""+"realGoodsId"+"\""+":"+"\""+listGoods.get(i).getId()+"\""+","
									+"\""+"goodsShowImg"+"\""+":"+"\""+listGoods.get(i).getGoodsShowImg()+"\""+","
									+"\""+"sale"+"\""+":"+"\""+"0"+"\""+","
									+"\""+"goodsName"+"\""+":"+"\""+listGoods.get(i).getGoodsName()+"\""+","
									+"\""+"configName"+"\""+":"+"\""+listConfig.get(j).getConfigName()+"\""+","
									+"\""+"configPrice"+"\""+":"+"\""+listConfig.get(j).getConfigPrice()+"\""+","
									+"\""+"goodsDistribution"+"\""+":"+"\""+listGoods.get(i).getGoodsDistribution()+"\""+","
									+"\""+"goodsShowDes"+"\""+":"+"\""+listGoods.get(i).getGoodsShowDes()+"\""+","
									+"\""+"goodsType"+"\""+":"+"\""+listGoods.get(i).getGoodsType()+"\""+","
									+"\""+"onShelf"+"\""+":"+"\""+listShelf.get(0).getOnShelf()+"\""+"}";
							msg += temp+",";
							System.out.println("5");
						}
					  }
					}
					System.out.println(listGoods.get(i).getIsDelete());
				}
			}
			

			if(msg.length()==0){
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[],\"ERROMSG\":\"\"}";
			}
			else {
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":[" + msg.substring(0, msg.length()-1) + "],\"ERROMSG\":\"\"}";
			}
			ActionUtil.sendJson(responseResult);
			
			
		}
	}
	
}

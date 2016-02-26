package com.siims.szb.goods.goods.action;




import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.goodsdetail.service.GoodsDetailService;
import com.siims.szb.szbldk.data.StoreInfoData;
import com.siims.szb.szbldk.service.StoreService;

/**
 * @author lmk
 *
 */
@Namespace("/siims/szb/goods")
public class GoodsInfoAction extends StrutsAction {

private static final long serialVersionUID = 1L;
	
	private String goodsId;
	private String goodsConfigId;
	
	@Action(value = "togoodsInfoBygoodsIdAndgoodsConfigId", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/shop-detail.ftl")})
	public String goodslist() {
		return SUCCESS;
	}
	
	@Action(value = "goodsInfoBygoodsIdAndgoodsConfigId")
	public void getgoodsFullInfoBygoodsIdAndgoodsConfigId() throws IOException{
		JsonObject jsonObject = new JsonObject();
		String goodsId = null;
		String goodsConfigId = request.getParameter("goodsConfigId");
		GoodsInfoData goodsInfoData = null;
		CommodityConfigData goodsConfigData = null;
		StoreInfoData storeInfoData = null;
		List<DetailInfoData> goodsDetailDataList = null;

		if (goodsConfigId != null){
			goodsConfigData= ServiceContext.get(CommodityConfigService.class).getCommodityConfig(goodsConfigId);
		}
		if (goodsConfigData != null){
			goodsId = goodsConfigData.getGoodsId();
		}
		if (goodsId != null){
			goodsInfoData = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsId);
		}
		if (goodsInfoData != null){
			storeInfoData = ServiceContext.get(StoreService.class).getStoreInfoByStoreId(goodsInfoData.getStoreId());
		}
		if (goodsId != null){
			goodsDetailDataList = ServiceContext.get(GoodsDetailService.class).searchDetailInfoByGoodsConfigId(goodsConfigId);
		}
		
		if(goodsInfoData != null){
			jsonObject.addProperty("SUCCESS", true);
			jsonObject.addProperty("ERROMSG", "");
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray dataJsonArray = new JsonArray();
			JsonObject mainJson = new JsonObject();
			mainJson.addProperty("goodsConfigsId",goodsConfigData.getId() );
			mainJson.addProperty("goodsName",goodsInfoData.getGoodsName() );
			mainJson.addProperty("goodsShowImg",goodsInfoData.getGoodsShowImg() );
			mainJson.addProperty("configName", goodsConfigData.getConfigName());
			mainJson.addProperty("configPrice",goodsConfigData.getConfigPrice() );
			mainJson.addProperty("storeId", storeInfoData.getId());
			mainJson.addProperty("storeName",storeInfoData.getName() );
			mainJson.addProperty("storeImage", storeInfoData.getImage());
			mainJson.addProperty("phoneNumber", storeInfoData.getTel());
//			mainJson.addProperty("", );
//			dataJsonArray.add(parser.parse(gson.toJson(goodsInfoData)));
//			dataJsonArray.add(parser.parse(gson.toJson(goodsConfigData)));
//			dataJsonArray.add(parser.parse(gson.toJson(storeInfoData)));
			dataJsonArray.add(mainJson);
			dataJsonArray.add(parser.parse(gson.toJson(goodsDetailDataList)));
			
			jsonObject.add("DATA", dataJsonArray);
		}else{
			jsonObject.addProperty("SUCCESS", false);
			jsonObject.addProperty("ERROMSG", "No goods!");
		}
		ActionUtil.sendJson(jsonObject.toString());
	}
}

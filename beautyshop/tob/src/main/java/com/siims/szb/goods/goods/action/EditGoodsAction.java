package com.siims.szb.goods.goods.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;
import com.siims.szb.goods.goods.action.AddGoodsAction.B;
import com.siims.szb.goods.goods.action.AddGoodsAction.C;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.goodsdetail.data.DetailInfoData;
import com.siims.szb.goods.goodsdetail.service.GoodsDetailService;

import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.service.ShelfInfoService;


@Namespace("/siims/szb/goods")
public class EditGoodsAction extends StrutsAction{
    
    private static final long serialVersionUID = 1L;
    /**
     * @author liufeng
     * @since 2015年8月20日16:24:49
     */
    
    private GoodsInfoData goodsInfoData ;
    private CommodityConfigData commodityConfigData ;
    private ShelfInfoData shelfInfoData ;
   
    
    
    @Action(value = "editgoods", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/editGoods.ftl")})
	public String editgoods() {
		return SUCCESS;
	}
    
    /**
     * 根据传进来的商品规格id修改商品信息
     * @throws IOException
     */
    @Action(value = "editGoodsById")
    public void editGoodsById() throws IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	
        //根据传进来的商品规格id获取一个商品规格对象,从前端传进来的goodsId，实际上是从数据库中查询出的configId
        String configId = request.getParameter("goodsId");
        commodityConfigData = ServiceContext.get(CommodityConfigService.class).getCommodityConfig(configId);
        
        
        //根据获取的商品的id获取一个商品对象
        String goodsId = commodityConfigData.getGoodsId();
        goodsInfoData = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsId);
        
        //根据商品详细的Id获取一个商品详细信息对象，，
        
        
        //根据商品的Id创建一个货架对象
        shelfInfoData = ServiceContext.get(ShelfInfoService.class).searchShelfInfoByConfigId(configId).get(0);
        
        
        
        //初始化输出数据
        String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
        
        //获取提交的信息
        String goodsName = request.getParameter("goodsName");
        String configName = request.getParameter("configName");
        
        String configPrice = request.getParameter("configPrice");
        String goodsShowImg = request.getParameter("goodsShowImg");
        

        //商品分类：食品和饮料类
        String goodsDistribution =  request.getParameter("goodsDistribution");
        //商品类型：现货和预定
        String goodsType = request.getParameter("goodsType");
        
        String onShelf = request.getParameter("onShelf");
        
        if(goodsType.equals("sale")) goodsType="现货";
            if(goodsType.equals("book")) goodsType="预定";
                
                //将数据已对象方式存储
                goodsInfoData.setGoodsName(goodsName);
                commodityConfigData.setConfigName(configName);
                //将从前台获取的商品价格字符串转换成double类型
                commodityConfigData.setConfigPrice(Double.parseDouble(configPrice));
                
                
                goodsInfoData.setGoodsShowImg(goodsShowImg);
                //在数据库中showDes和goodsDesDes存储一样的数据
              //goodsInfoData.setGoodsShowDes(goodsDesDes);
                
                //设置商品分组：饮料和零食
                goodsInfoData.setGoodsDistribution(goodsDistribution);
                //设置商品类型：现货和预定
                goodsInfoData.setGoodsType(goodsType);
                
                
                System.out.println(goodsInfoData.getGoodsShowDes());
                Boolean msg3 = ServiceContext.get(GoodsService.class).editGoodsInfo(goodsInfoData);
                
                //将图片存入商品详细信息表
//                System.out.println(222);
//                detailInfoData.setGoodsDesDes(goodsDesDes);
//                System.out.println(111);
//                detailInfoData.setGoodsDesImg(goodsDesImg);
                
                System.out.println(333);
                //设置商品是否立刻上架
                shelfInfoData.setOnShelf(onShelf);
                
                
                //将信息提交至数据库进行存储
                //并且把3个对象的主键存储在msg
                Boolean msg4 = ServiceContext.get(ShelfInfoService.class).editShelfInfo(shelfInfoData);
                //Boolean msg3 = ServiceContext.get(GoodsService.class).editGoodsInfo(goodsInfoData);
                ServiceContext.get(CommodityConfigService.class).editCommodityConfig(commodityConfigData);
               
                
                
                
                
                
                //修改之前的detail表中的数据
                List<DetailInfoData> listDetail = ServiceContext.get(GoodsDetailService.class).searchDetailInfoByGoodsConfigId(commodityConfigData.getId());
  
                String json = request.getParameter("DATA");
        		System.out.println(json);
        		if(request.getParameter("DATA") == null)
        		{
        			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
        			ActionUtil.sendJson(responseResult);
        			return;
        		}
        		int i = 0;//有多少个Data
        		
        		
        		Type type = new TypeToken<C>() {
        		}.getType();
        		System.out.println(11);
        		Gson gson = new Gson();
        		System.out.println(22);
        		C c = gson.fromJson(json, type);
        		System.out.println(33);
        		System.out.println(c.Data.size());
        		//循环遍历从前端传过来的数组
        		while(i < c.Data.size())
        		{
        			
        			System.out.println(44);
        			//获得描述信息和
        			String goodsDesDes = c.Data.listIterator(i).next().getGoodDes();
        			String goodsDesImg = c.Data.listIterator(i).next().getGoodDesPicUrl();
        			/**************/
        			String detailId = c.Data.listIterator(i).next().getDetailId();
        			System.out.println(detailId);
        			System.out.println(goodsDesDes);
        			System.out.println(goodsDesImg);
        			int j;
        			System.out.println(listDetail.size());
        			for( j=0;j<listDetail.size();j++){
        				//修改已有的详情信息
        				System.out.println(listDetail.get(j).getId());
        				if(detailId.equals(listDetail.get(j).getId())){
        					System.out.println("只是做修改的详细");
        					DetailInfoData detailInfoData = ServiceContext.get(GoodsDetailService.class).searchDetailInfo(detailId);
        					detailInfoData.setGoodsDesImg(goodsDesImg);
                			detailInfoData.setGoodsDesDes(goodsDesDes);
                			ServiceContext.get(GoodsDetailService.class).editDetailInfo(detailInfoData);
                			break;
        				}        				           			
        			}
        			//add新的详情信息
        			if(j==listDetail.size()){
        				System.out.println("新添加的详细");
    					DetailInfoData detailInfoData = new DetailInfoData(commodityConfigData.getId());
    					System.out.println(66);
    					
    					detailInfoData.setGoodsDesImg(goodsDesImg);
    					detailInfoData.setGoodsDesDes(goodsDesDes);
    					System.out.println(77);
    					
    					System.out.println(ServiceContext.get(GoodsDetailService.class).addDetailInfo(detailInfoData));
    					//修改传过来的数据的id
    					c.Data.listIterator(i).next().setDetailId(detailInfoData.getId());
    					System.out.println(c.Data.listIterator(i).next().getDetailId());
    				
        			}
        			
        			i++;
        		}
        		//添加后的详细表数据
        		List<DetailInfoData> listDetail2 = ServiceContext.get(GoodsDetailService.class).searchDetailInfoByGoodsConfigId(commodityConfigData.getId());
        		System.out.println(listDetail2.size()+"........");
        		for(int m=0;m<c.Data.size();m++){
        			System.out.println("前端ID"+c.Data.listIterator(m).next().getDetailId());
        		}
        		
        		for(int m=0;m<listDetail2.size();m++){
        			System.out.println("数据库ID"+listDetail2.get(m).getId());
        		}
        		//删除详情信息
        			i=0;
        			for(int j=0;j<listDetail2.size();j++){
        				System.out.println("删除过程");
        				DetailInfoData detailInfoData = ServiceContext.get(GoodsDetailService.class).searchDetailInfo(listDetail2.get(j).getId());
        				while(i < c.Data.size()){
        					//遍历detail表
        					System.out.println("进入数组");
        					
        					String detailId = c.Data.listIterator(i).next().getDetailId();
        					System.out.println("*******************************");
        					System.out.println(detailId);
        					if(!detailId.equals(listDetail2.get(j).getId())){
        						i++;
        						
        					}
        					else
        						break;
        				}
        				//如果数据库中的一条数据不和所有的前段数据匹配则删除
        				if(i==c.Data.size()){
        					ServiceContext.get(GoodsDetailService.class).delDetailInfo(detailInfoData);
    						System.out.println("删除成功");
        				}
        				i=0;
        				
        			}
        	
        			System.out.println("删除结束");
        		 List<DetailInfoData> listDetail3 = ServiceContext.get(GoodsDetailService.class).searchDetailInfoByGoodsConfigId(commodityConfigData.getId());
                 System.out.println("修改后数据总数"+listDetail3.size());
              
              
                String msg = msg3+"  "+msg4;
                
                responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + msg + "\"}";
                ActionUtil.sendJson(responseResult);
                }
    
  
	/**
	 * 单个商品详细表
	 * @author liufeng
	 *
	 */
    class B {
		private String detailId;
		private String goodDes;
		private String goodDesPicUrl;
		//private String configId;
		public String getDetailId() {
			return detailId;
		}
		public void setDetailId(String detailId) {
			this.detailId = detailId;
		}
		public String getGoodDes() {
			return goodDes;
		}
		public void setGoodDes(String goodDes) {
			this.goodDes = goodDes;
		}
		public String getGoodDesPicUrl() {
			return goodDesPicUrl;
		}
		public void setGoodDesPicUrl(String goodDesPicUrl) {
			this.goodDesPicUrl = goodDesPicUrl;
		}
//		public String getConfigId() {
//			return configId;
//		}
//		public void setConfigId(String configId) {
//			this.configId = configId;
//		}
		
		
	}
	/**
	 * 商品详细表集合
	 * @author liufeng
	 *
	 */
	class C {
		private List<B> Data;

		public List<B> getData() {
			return Data;
		}
		public void setData(List<B> data) {
			Data = data;
		}
	}
    

    public GoodsInfoData getGoodsInfoData() {
        return goodsInfoData;
    }
    
    
    public void setGoodsInfoData(GoodsInfoData goodsInfoData) {
        this.goodsInfoData = goodsInfoData;
    }
    
    
    public CommodityConfigData getCommodityConfigData() {
        return commodityConfigData;
    }
    
    
    public void setCommodityConfigData(CommodityConfigData commodityConfigData) {
        this.commodityConfigData = commodityConfigData;
    }
    
    
    public ShelfInfoData getShelfInfoData() {
        return shelfInfoData;
    }
    
    
    public void setShelfInfoData(ShelfInfoData shelfInfoData) {
        this.shelfInfoData = shelfInfoData;
    }
}

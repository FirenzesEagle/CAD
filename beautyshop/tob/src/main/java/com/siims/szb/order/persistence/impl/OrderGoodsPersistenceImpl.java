package com.siims.szb.order.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.service.CommodityConfigService;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.order.data.Order;
import com.siims.szb.order.data.OrderGoods;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.persistence.OrderGoodsPersistence;
import com.siims.szb.order.persistence.OrderPersistence;
import com.siims.szb.szbldk.persistence.StorePersistence;


@Singleton
@AutoBind(bindClass = OrderGoodsPersistence.class)
public class OrderGoodsPersistenceImpl implements OrderGoodsPersistence {

	private final BaseDao hibernateDao;

	//定义mybatis CRUD 操作对象
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	/**
	 * 构造函数初始化<br>
	 * 获得数据访问对象
	 * @param mybatis 数据访问对象
	 */
	@Inject
	public OrderGoodsPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String create(String orderId, String goodsId, String goodsconfigId, String shopId, String price, String num, String paytype){
		OrderGoods orderGoods = new OrderGoods(orderId, goodsId, goodsconfigId, shopId, price, num, paytype);
		hibernateDao.insert(null, orderGoods);
		return orderGoods.getId();
	}	
	
	public ListFacade queryOrderGoods(String id){
		List<OrderGoods> orderGoods = hibernateDao.queryList("from OrderGoods o where o.orderId = '"+id+"'", null);
		if (orderGoods == null) {
			return new ListFacade(ResultType.error, "中间表Id不存在");
		} else {
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			for(OrderGoods o : orderGoods){
				Map<String, String> map = new HashMap<String, String>();
				map.put("goodsid", o.getGoodsId());
				map.put("goodsconfigid", o.getGoodsconfigId());
				map.put("id", o.getId());
				list.add(map);
			}
			return new ListFacade(ResultType.success, "信息查询成功", list);
		}
	}
	
	public ListFacade queryWithKeywordAction(Integer customerId, String key){
		List<OrderGoods> orderGoods = hibernateDao.queryList("from OrderGoods o where o.", null);
		return null;
	}
	
	public Set<String> queryByShopId(String shopId){
		Set<String> set = new HashSet<String>();
		List<OrderGoods> list = hibernateDao.queryList("from OrderGoods o where o.shopId = '"+shopId+"'", null);
		for (OrderGoods orderGoods : list) {
			if (!set.contains(orderGoods.getOrderId())) {
				set.add(orderGoods.getOrderId());
			}
		}
		return set;
	}
	
	public ListFacade queryOrderGoodsByOrderId(String orderId){
		String hql = "from OrderGoods og where og.orderId = '"+orderId+"'";
		List<OrderGoods> orderGoods = hibernateDao.queryList(hql, null);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for (int i = 0; i < orderGoods.size(); i++) {
			OrderGoods og = orderGoods.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", og.getNum());
			map.put("pay_type", og.getPaytype());
			map.put("price", og.getPrice());
			map.putAll(ServiceContext.get(OrderPersistence.class).querybasic(og.getOrderId()).getMap());
			System.out.println(map.get("1"));
			for(String key : map.keySet()){
				System.out.println(map.get(key));
			}
			System.out.println(map.get("2"));
			map.putAll(getFakeGoodsInfo(og.getGoodsId(), og.getGoodsconfigId()));
			
			for(String key : map.keySet()){
				System.out.println(map.get(key));
			}
			System.out.println(map.get("3"));
			map.putAll(ServiceContext.get(StorePersistence.class).getFakeShopInfo(og.getShopId()));
			
			for(String key : map.keySet()){
				System.out.println(map.get(key));
			}
			list.add(map);
		}
		return new ListFacade(ResultType.success, "查询成功", list);
	}
	
	public ListFacade queryAllInfoByShopId(String shopId){
		String hql = "from OrderGoods og where og.shopId = '"+shopId+"'";
		List<OrderGoods> orderGoods = hibernateDao.queryList(hql, null);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for (int i = 0; i < orderGoods.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			OrderGoods og = orderGoods.get(i);
			map.put("num", og.getNum());
			map.putAll(ServiceContext.get(OrderPersistence.class).querybasic(og.getOrderId()).getMap());
			map.putAll(getFakeGoodsInfo(og.getGoodsId(), og.getGoodsconfigId()));
			map.putAll(ServiceContext.get(StorePersistence.class).getFakeShopInfo(og.getShopId()));
			list.add(map);
		}
		return new ListFacade(ResultType.success, "查询成功", list);

	}
	
	
	public Map<String, String> getFakeGoodsInfo(String goodsId, String goodsConfigId) {
		Map<String, String> map = new HashMap<String, String>();
		
		CommodityConfigData commodityConfigData = ServiceContext.get(CommodityConfigService.class).getCommodityConfig(goodsConfigId);
		if(commodityConfigData==null){
			map.put("goodsName", "");
			map.put("goodPicUrl","");
			map.put("goodsConfig", "");
			map.put("goodsconfigPrice", "");
			return map;
		}
		else{
		 String goodsid = commodityConfigData.getGoodsId();
		 GoodsInfoData goodsData = ServiceContext.get(GoodsService.class).searchGoodsInfo(goodsid);
		 map.put("goodsName", goodsData.getGoodsName());
		 map.put("goodPicUrl",goodsData.getGoodsShowImg());
		 map.put("goodsConfig", commodityConfigData.getConfigName());
		 map.put("goodsconfigPrice", String.valueOf(commodityConfigData.getConfigPrice()));
		}
		
		
		return map;
		

	}
	

}


















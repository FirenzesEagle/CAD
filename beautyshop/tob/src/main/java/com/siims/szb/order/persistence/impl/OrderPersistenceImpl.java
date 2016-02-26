package com.siims.szb.order.persistence.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.order.data.Order;
import com.siims.szb.order.enums.ResultType;
import com.siims.szb.order.facade.ListFacade;
import com.siims.szb.order.facade.MapFacade;
import com.siims.szb.order.persistence.OrderCardPersistence;
import com.siims.szb.order.persistence.OrderPersistence;
import com.siims.szb.order.wrapper.CardWrapper;
import com.siims.szb.vipcard.sbp.data.ConsumerVipCardData;
import com.siims.szb.vipcard.sbp.service.ConsumerVipCardService;
import com.siims.szb.vipcard.sbp.service.VipCardBillService;

@Singleton
@AutoBind(bindClass = OrderPersistence.class)
public class OrderPersistenceImpl implements OrderPersistence {

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
	public OrderPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String save(Order order){
		hibernateDao.insert(null, order);
		return (String)order.getId();
	}
	
	public void delete(String id){
		Order order = (Order)hibernateDao.queryData(id, Order.class);
		order.setIsDeleted("1");
		hibernateDao.update(null, order);
	}
	
	public Boolean updateStatus(String id, String status){
		Order order = (Order)hibernateDao.queryData(id, Order.class);
		if (order == null) {
			return false;
		} else {
			if (status.equals("payed") && order.getStatus().equals("create") && getfake(ServiceContext.get(OrderCardPersistence.class).queryOrderCardWithOrderId(order.getId()))) {
				order.setStatus(status);
				hibernateDao.update(null, order);
				URL url = null;
				URLConnection connection = null;
				try {
					url = new URL("http://182.92.4.200/CxyhWeixinServer/orderpaymentsuccess?touser=TOUSER&orderMoneySum＝ORDEEMONEYSUM&orderProductName=ORDERPRODUCTNAME".replace("TOUSER", order.getCustomerId()).replace("ORDEEMONEYSUM", order.getPrice()).replace("ORDERPRODUCTNAME", order.getName()));
					connection = url.openConnection();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if (connection != null) {
						connection = null;
					}
				}
				return true;
			}else{
				order.setStatus(status);
				hibernateDao.update(null, order);
//				if (status.equals("receiveorder")) {
//					
//				} else if(status.equals("send")) {
//					URL url = null;
//					URLConnection connection = null;
//					try {
//						url = new URL("http://182.92.4.200/CxyhWeixinServer/goodsarrival?touser=TOUSER&keyword1=KEYWORD1&keyword2=KEYWORD2&keyword3=KEYWORD3".replace("TOUSER", order.getCustomerId()).replace("KEYWORD1", ).replace("ORDERPRODUCTNAME", order.getName()));
//						connection = url.openConnection();
//					} catch (MalformedURLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}finally{
//						if (connection != null) {
//							connection = null;
//						}
//					}
//				}
				return true;
			}
		}
	}

	public MapFacade querybasic(String id){
		Order order = (Order)hibernateDao.queryData(id, Order.class);
		if (order == null) {
			return new MapFacade(ResultType.error, "订单编号无效");
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("price", order.getPrice());
			map.put("code", order.getCode());
			map.put("status", order.getStatus());
			map.put("name", order.getName());
			map.put("phone", order.getPhone());
			map.put("orderId", order.getId());
			map.put("address", order.getAddress());
			map.put("createtime", new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(order.getCreatetime()));
			return new MapFacade(ResultType.success, "订单基本信息查询成功", map);
		}
	}
	
	public ListFacade querybasicByCustomerId(String id, String status){
		String hql  ="";
		if (status == null || status.equals("")) {
			hql =  "from Order o where o.customerId = '"+ id +"'";
		}else if(status.equals("create") || status.equals("takedelivery")){
			hql =  "from Order o where o.customerId = '"+ id +"' and status = '"+status+"'";
		}else if(status.equals("going")) {
			hql = "from Order o where o.customerId = '"+id+"' and (status = 'payed' or status = 'receiveorder' or status = 'send')";
		}else {
			return new ListFacade(ResultType.error, "传入的状态无效", new ArrayList<Map<String,String>>());
		}
		List<Order> list = hibernateDao.queryList(hql, null);
		List<Map<String, String>> l = new ArrayList<Map<String,String>>();
		if (list != null && list.size() != 0) {
			for (Order o : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("price", o.getPrice());
				map.put("status", o.getStatus());
				map.put("code", o.getCode());
				map.put("id", o.getId());
				l.add(map);
			}
		}
		return new ListFacade(ResultType.success, "信息查询成功", l);
	}
	
	
	public ListFacade queryWithOrderids(Set<String> orderids, String status){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(String orderId : orderids){
			Order order = (Order)hibernateDao.queryData(orderId, Order.class);
			if (status != null && !status.equals("")) {
				if (order.getStatus().equals(status)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", order.getId());
					map.put("code", order.getCode());
					map.put("createtime", order.getCreatetime().toString());
					map.put("status", order.getStatus());
					map.put("price", order.getPrice());
					list.add(map);
				}
			}
		}
		return new ListFacade(ResultType.success, "查询成功", list);
	}
	
	private boolean getfake(List<CardWrapper> wrappers) {
		
		System.out.println("FUCK ON CARD AND ORDER");
		
		if (wrappers == null) {
			System.out.println("the list is null");
		} else if(wrappers.size() == 0) {
			System.out.println("the size is 0");
		}else {
			for(CardWrapper cardWrapper : wrappers){
				System.out.println(cardWrapper.getCardid()+" "+cardWrapper.getMoney()+" "+cardWrapper.getType());
			}
		}
		//先对所有的会员卡做一次检查
		for(int i = 0; i < wrappers.size(); i++) {
			System.out.println("What is this? Man "+wrappers.get(i).getCardid());
			ConsumerVipCardData data = (ConsumerVipCardData) hibernateDao.queryData(wrappers.get(i).getCardid(), ConsumerVipCardData.class);
			System.out.println("会员卡卡号："+data.getId());
			if(data.getIsDelete() == 1 || data.getIspay() == 0 
					|| (data.getLastMoney() < Double.valueOf(wrappers.get(i).getMoney())) 
					|| !data.getPassword().equals(wrappers.get(i).getPassword())) {
				if(data.getIsDelete() == 1)
					System.out.println("会员卡已经删除");
				else if(data.getIspay() == 0)
					System.out.println("会员卡为支付");
				else if(data.getLastMoney() < Double.valueOf(wrappers.get(i).getMoney()))
					System.out.println("会员卡余额不足");
				else
					System.out.println("密码错误");
				System.out.println("There is no enough money");
				System.out.println("Fuck again!");
				return false;
			}
				
		}
		
		for(int i = 0; i < wrappers.size(); i++) {
			ConsumerVipCardData data = (ConsumerVipCardData) hibernateDao.queryData(wrappers.get(i).getCardid(), ConsumerVipCardData.class);
			data.setLastMoney(data.getLastMoney() - Double.valueOf(wrappers.get(i).getMoney()));
			ServiceContext.get(ConsumerVipCardService.class).updateConsumerVipCardData(data);
			ServiceContext.get(VipCardBillService.class).addVipCardBillData(wrappers.get(i).getCardid(), Double.valueOf(wrappers.get(i).getMoney()), data.getLastMoney(), 2, null, null, null);
		}
		
		return true;
	}
}




















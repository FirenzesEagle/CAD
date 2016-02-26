package com.siims.szb.order.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.LineNumberAttribute.Pc;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.order.data.OrderCard;
import com.siims.szb.order.persistence.OrderCardPersistence;
import com.siims.szb.order.wrapper.CardWrapper;


@Singleton
@AutoBind(bindClass = OrderCardPersistence.class)
public class OrderCardPersistenceImpl implements OrderCardPersistence{

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
	public OrderCardPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public void create(String orderid, String cardid, String password, String money, String type) {
		OrderCard orderCard = new OrderCard(orderid, cardid, password, money, type);
		hibernateDao.insert(null, orderCard);
	}

	public List<CardWrapper> queryOrderCardWithOrderId(String orderid) {
		String hql = "from OrderCard oc where oc.orderid = '"+orderid+"'";
		List<OrderCard> orderCards = hibernateDao.queryList(hql, null);
		List<CardWrapper> cardWrappers = new ArrayList<CardWrapper>();
		if (orderCards == null || orderCards.size() == 0) {
			return cardWrappers;
		} else {
			for(OrderCard oc : orderCards){
				CardWrapper wrapper = new CardWrapper(oc.getCardid(), oc.getPassword(), oc.getMoney(), oc.getType());
				cardWrappers.add(wrapper);
			}
			return cardWrappers;
		}
	}
	
}

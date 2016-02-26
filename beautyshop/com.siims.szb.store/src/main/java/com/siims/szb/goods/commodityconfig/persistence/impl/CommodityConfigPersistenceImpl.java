package com.siims.szb.goods.commodityconfig.persistence.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.commodityconfig.data.CommodityConfigData;
import com.siims.szb.goods.commodityconfig.persistence.CommodityConfigPersistence;
import com.siims.szb.goods.goods.data.GoodsInfoData;

@Singleton
@AutoBind(bindClass = CommodityConfigPersistence.class)
public class CommodityConfigPersistenceImpl implements CommodityConfigPersistence{
	//定义hibernate CRUD 操作对象
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
	public CommodityConfigPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, commodityConfigData);
		return commodityConfigData.getId();
	}

	public boolean delCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		commodityConfigData.setIsDelete("1");
		hibernateDao.update(null, commodityConfigData);
		return true;
	}

	public boolean editCommodityConfig(CommodityConfigData commodityConfigData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, commodityConfigData);
		return true;
	}

	public CommodityConfigData getCommodityConfig(String id) {
		// TODO Auto-generated method stub
		return (CommodityConfigData)hibernateDao.queryData(id, CommodityConfigData.class);
	}

	@SuppressWarnings("unchecked")
	public List<CommodityConfigData> getAllCommodityConfig(String goodsId) {
		// TODO Auto-generated method stub
		String sql = "from CommodityConfigData c where c.goodsId = '" + goodsId + "' order by c.createTime desc";
		//return (List<CommodityConfigData>) hibernateDao.queryData(sql, null);
		List<CommodityConfigData> list = hibernateDao.queryList(sql, null);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<CommodityConfigData> getCommodityType(String id) {
		// TODO Auto-generated method stub
		String sql = "from CommodityConfigData c where c.isDelete = 1 and c.type = 1 and c.parentId = '" + id + "'";
		return (List<CommodityConfigData>) hibernateDao.queryData(sql, null);
		
	}

}

package com.siims.szb.goods.shelf.persistence.impl;

import java.util.List;







import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;
import com.siims.szb.goods.shelf.data.ShelfInfoData;
import com.siims.szb.goods.shelf.persistence.ShelfInfoPersistence;


@Singleton
@AutoBind(bindClass = ShelfInfoPersistence.class)
public class ShelfInfoPersistenceImpl implements ShelfInfoPersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public ShelfInfoPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, shelfInfoData);
		return shelfInfoData.getId();
	}

	public boolean delShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.delete(null, shelfInfoData);
		return true;
	}

	public boolean editShelfInfo(ShelfInfoData shelfInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, shelfInfoData);
		return true;
	}

	public ShelfInfoData searchShelfInfo(String id) {
		// TODO Auto-generated method stub
		ShelfInfoData shelfInfoData = (ShelfInfoData) hibernateDao.queryData(id, ShelfInfoData.class);
		return shelfInfoData;
	}

	@SuppressWarnings("unchecked")
	public List<ShelfInfoData> searchAllShelfInfo(String storeId) {
		// TODO Auto-generated method stub
		//String sql = "from GoodsInfoData g,PageGoodsInfoData p where g.id=p.goodsinfoid";
		String sql = "from ShelfInfoData s where s.storeId = '" + storeId +"' order by s.id desc";
		List<ShelfInfoData> list = hibernateDao.queryList(sql, null);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ShelfInfoData> searchShelfInfoByConfigId(String configId) {
		// TODO Auto-generated method stub
		String sql = "from ShelfInfoData s where s.configId = '" + configId +"'  ";
		List<ShelfInfoData> list = hibernateDao.queryList(sql, null);
		return list;
	}

}

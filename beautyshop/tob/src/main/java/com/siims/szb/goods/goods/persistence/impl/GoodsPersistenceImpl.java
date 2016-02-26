package com.siims.szb.goods.goods.persistence.impl;

import java.util.List;




import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.siims.framework.base.BaseDao;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;


@Singleton
@AutoBind(bindClass = GoodsPersistence.class)
public class GoodsPersistenceImpl implements GoodsPersistence{

	
	private final BaseDao hibernateDao;

	
	@SuppressWarnings("unused")
	private final BaseDao mybatisDao;
	
	@Inject
	public GoodsPersistenceImpl(@Named("mybatisDao") BaseDao mybatis, @Named("hibernateDao") BaseDao hibernate) {
		this.mybatisDao = mybatis;
		this.hibernateDao = hibernate;
	}
	
	public String addGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.insert(null, goodsInfoData);
		return goodsInfoData.getId();
	}

	public boolean delGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub\
		//isDelete为1即删除成工
		goodsInfoData.setIsDelete("1");
		hibernateDao.update(null, goodsInfoData);
		return true;
	}

	public boolean editGoodsInfo(GoodsInfoData goodsInfoData) {
		// TODO Auto-generated method stub
		hibernateDao.update(null, goodsInfoData);
		return true;
	}

	public GoodsInfoData searchGoodsInfo(String goodId) {
		// TODO Auto-generated method stub
		GoodsInfoData goodInfoData = (GoodsInfoData) hibernateDao.queryData(goodId, GoodsInfoData.class);
		return goodInfoData;
	}

	@SuppressWarnings("unchecked")
	public List<GoodsInfoData> searchAllGoodsInfo(String storeId) {
		// TODO Auto-generated method stub
		//String sql = "from GoodsInfoData g,PageGoodsInfoData p where g.id=p.goodsinfoid";
		String sql = "from GoodsInfoData g where g.storeId = '" + storeId +"' order by g.goodsDistribution desc";
		List<GoodsInfoData> list = hibernateDao.queryList(sql, null);
		return list;
	}

}

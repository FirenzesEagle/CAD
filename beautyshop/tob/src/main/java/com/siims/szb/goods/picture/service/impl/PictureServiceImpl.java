package com.siims.szb.goods.picture.service.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.framework.transaction.TransactionalContext;
import com.siims.szb.goods.goods.business.GoodsBusiness;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.service.GoodsService;
import com.siims.szb.goods.picture.business.PictureBusiness;
import com.siims.szb.goods.picture.data.PictureData;
import com.siims.szb.goods.picture.service.PictureService;


@Singleton
@AutoBind(bindClass = PictureService.class)
@TransactionalContext
public class PictureServiceImpl implements PictureService {


	public String addPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		ServiceContext.get(PictureBusiness.class).addPicture(pictureData);
//		return demoData.getId();
		return pictureData.getId();
	}

	public boolean delPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean editPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		ServiceContext.get(PictureBusiness.class).editPicture(pictureData);
		return true;
	}

	public PictureData searchPicture(String pictureId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PictureData> searchAllPicture(String pageID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

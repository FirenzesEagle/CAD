package com.siims.szb.goods.picture.business.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.ioc.guice.annotation.AutoBind;
import com.siims.szb.goods.goods.business.GoodsBusiness;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.goods.persistence.GoodsPersistence;
import com.siims.szb.goods.picture.business.PictureBusiness;
import com.siims.szb.goods.picture.data.PictureData;
import com.siims.szb.goods.picture.persistence.PicturePersistence;


@Singleton
@AutoBind(bindClass = PictureBusiness.class)
public class PictureBusinessImpl implements PictureBusiness{

	
	public String addPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		ServiceContext.get(PicturePersistence.class).addPicture(pictureData);
		return pictureData.getId();
	}

	public boolean delPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean editPicture(PictureData pictureData) {
		// TODO Auto-generated method stub
		ServiceContext.get(PicturePersistence.class).editPicture(pictureData);
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

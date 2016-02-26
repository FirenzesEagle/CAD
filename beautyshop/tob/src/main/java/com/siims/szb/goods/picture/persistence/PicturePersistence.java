package com.siims.szb.goods.picture.persistence;

import java.util.List;






import com.siims.framework.base.BasePersistence;
import com.siims.szb.goods.goods.data.GoodsInfoData;
import com.siims.szb.goods.picture.data.PictureData;

public interface PicturePersistence extends BasePersistence<PictureData> {
	
	
	public String addPicture(PictureData pictureData);
	
	
	public boolean delPicture(PictureData pictureData);
	
	
	public boolean editPicture(PictureData pictureData);
	
	
	public PictureData searchPicture(String goodsId);
	
	//pageId
	public List<PictureData> searchAllPicture(String pageID);
}

package com.siims.szb.goods.picture.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_PICTURE")
public class PictureData implements BaseData{
	/**图片
	 * @author liufeng
	 * @since 9.16
	 */
	private static final long serialVersionUID = 1L;

	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
	/**
     * 图片存储的URL
     */
	@Column(name="URL")
    private String url;
	/**
     * 图片所属的商品id
     */
    @Column(name="GOODSID")
    private String goodsId;
    
    
    public PictureData(String url, String goodsId) {
		super();
		this.url = url;
		this.goodsId = goodsId;
	}
    
    
    public PictureData(String url) {
		super();
		this.url = url;
	}
    
    
    
 /*********************setter&&getter*********************/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
   
   
    
	
    
    
}

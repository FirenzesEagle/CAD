package com.siims.szb.goods.goodsdetail.data;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

/**商品详细信息实体
 * @author liufeng
 * @since 2015年9月6日10:37:50
 */

@Entity
@Table(name = "SZB_GOODSDETAIL")
public class DetailInfoData implements BaseData{

	
	private static final long serialVersionUID = 1L;

	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	/**
     * 商品id
     */
    @Column(name="GOODSID")
    private String goodsId;
   /**
    * 商品详细图片
    */
    @Column(name="GOODSDESIMG")
    private String goodsDesImg;
    /**
     * 商品描述
     */
    @Column(name="GOODSDESDES")
    private String goodsDesDes;
    
    /**
     * 商品创建时间
     */
    @Column(name = "CREATETIME")
    private Date createTime;
    public DetailInfoData(){}
    public DetailInfoData(String goodsId){
    	this.goodsDesDes="";
    	this.goodsId=goodsId;
    	this.goodsDesImg="";
    	this.createTime=new Date();
    }
    /*******************************setter和getter方法****************************/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsDesImg() {
		return goodsDesImg;
	}
	public void setGoodsDesImg(String goodsDesImg) {
		this.goodsDesImg = goodsDesImg;
	}
	public String getGoodsDesDes() {
		return goodsDesDes;
	}
	public void setGoodsDesDes(String goodsDesDes) {
		this.goodsDesDes = goodsDesDes;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
   
    
    
   
	
	
}

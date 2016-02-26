package com.siims.szb.goods.goods.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_GOODSINFO")
public class GoodsInfoData implements BaseData{
	/**商品信息实体
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
     * 商品版本
     */
	@Column(name="VERSION")
    private String version;
	/**
     * 商品名称
     */
    @Column(name="GOODSNAME")
    private String goodsName;
    /**
     * 商品描述
     */
    @Column(name="GOODSSHOWDES")
    private String goodsShowDes;
    /**
     * 商品展示图片
     */
    @Column(name="GOODSSHOWIMG")
    private String goodsShowImg;
    /**
     * 商品创建时间
     */
    @Column(name="CREATETIME")
    private Date createTime;
    /**
     * 0商品没删除 1商品删除
     */
    @Column(name="ISDELETE")
    private String isDelete;
    /**
     * 促销信息
     */
    @Column(name="PROMOTION")
    private String promotion;
    /**
     * 商品种类（如饮料、零食）
     */
    @Column(name="GOODSDISTRIBUTION")
    private String goodsDistribution;
    
    /**
     * 现货还是预定
     */
    @Column(name="GOODSTYPE")
    private String goodsType;
    
    /**
     * 商品所属店铺的id
     */
    @Column(name="STOREID")
    private String storeId;
    
    /**
     * 库存
     */
    @Column(name="STOCK")
    private String stock;
    
    
    /**
     * 商品信息二维码
     */
    @Column(name="GOODSEWCODE")
    private String goodsEWCode;

    public GoodsInfoData(){}
    
    public GoodsInfoData(String storeId) {
		super();
		this.version="";
		this.goodsName="";
		this.goodsShowDes="";
		this.goodsShowImg="";
		this.createTime = new Date();
		this.isDelete="0";
		this.promotion="";
		this.goodsDistribution="";
		this.goodsType="";
		this.storeId=storeId;
		this.stock="";
		this.goodsEWCode="";
	}

	/*-----------------------------getter&&setter-------------------------------*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	

	

	public String getGoodsEWCode() {
		return goodsEWCode;
	}

	public void setGoodsEWCode(String goodsEWCode) {
		this.goodsEWCode = goodsEWCode;
	}

	public String getGoodsShowDes() {
		return goodsShowDes;
	}

	public void setGoodsShowDes(String goodsShowDes) {
		this.goodsShowDes = goodsShowDes;
	}

	public String getGoodsShowImg() {
		return goodsShowImg;
	}

	public void setGoodsShowImg(String goodsShowImg) {
		this.goodsShowImg = goodsShowImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getGoodsDistribution() {
		return goodsDistribution;
	}

	public void setGoodsDistribution(String goodsDistribution) {
		this.goodsDistribution = goodsDistribution;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
    
   
    
	
    
    
}

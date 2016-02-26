package com.siims.szb.goods.commodityconfig.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

/**商品规格实体类
 * @author linufeng
 * @since 2015年9月6日10:37:50
 */

@Entity
@Table(name = "SZB_COMMODITYCONFIG")
public class CommodityConfigData implements BaseData{

	
	private static final long serialVersionUID = 1L;

	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	/**
     * 规格名称
     */
    @Column(name="CONFIGNAME")
    private String configName;
   /**
    * 规格价格
    */
    @Column(name="CONFIGPRICE")
    private double configPrice;
    /**
     * 市场价格
     */
    @Column(name="CONFIGMARKETPRICE")
    private double configMarketPrice;
    /**
     * 创建时间
     */
    @Column(name="CREATETIME")
    private Date   createTime;
    /**
     * 商品规格id
     */
    @Column(name="GOODSID")
    private String goodsId;
    /**
     * 删除 0已删除  1 未删除
     */
    @Column(name="ISDELETE")
    private String isDelete;
    
    /**
     * 版本
     */
    @Column(name="VERSION")
    private String version;
    
    
    

    public CommodityConfigData(){}
    public CommodityConfigData(String goodsId){
    	this.configName="";
    	this.configPrice=0;
    	this.configMarketPrice=0;
    	this.createTime = new Date();
    	this.goodsId=goodsId;
    	this.isDelete="";
    	this.version="";
    	
    }
    /*-----------------------------get和set方法-------------------------------*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public double getConfigPrice() {
		return configPrice;
	}

	public void setConfigPrice(double configPrice) {
		this.configPrice = configPrice;
	}

	public double getConfigMarketPrice() {
		return configMarketPrice;
	}

	public void setConfigMarketPrice(double configMarketPrice) {
		this.configMarketPrice = configMarketPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	

	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
    
 
	
}

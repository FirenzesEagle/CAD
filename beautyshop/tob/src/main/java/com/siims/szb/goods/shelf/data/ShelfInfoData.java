package com.siims.szb.goods.shelf.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

/**商品上架信息实体
 * @author liufeng
 * @since 2015年9月6日10:37:50
 */

@Entity
@Table(name = "SZB_SHELFINFO")
public class ShelfInfoData implements BaseData{

	
	private static final long serialVersionUID = 1L;

	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	/**
     * 商品信息id
     */
    @Column(name="CONFIGID")
    private String configId;
   /**
    * 商铺id
    */
    @Column(name="STOREID")
    private String storeId;
    /**
     * 是否上架 1:上架 0:下架
     */
    @Column(name="ONSHELF")
    private String onShelf;
    /**
     * 1:还有库存0：没有库存
     */
    @Column(name="ONSTOCK")
    private String   onStock;
    
  
    /**
     * 上架时间
     */
    @Column(name="SHELFTIME")
    private Date shelfTime;
    
    
    public ShelfInfoData(){}
    public ShelfInfoData(String configId){
    	this.configId=configId;
    	this.onShelf="";
    	this.onStock="";
    	this.storeId="";
    	this.shelfTime=new Date();
    }

    /*-----------------------------get和set方法-------------------------------*/
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	


	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getStoreId() {
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	public String getOnShelf() {
		return onShelf;
	}


	public void setOnShelf(String onShelf) {
		this.onShelf = onShelf;
	}


	public String getOnStock() {
		return onStock;
	}


	public void setOnStock(String onStock) {
		this.onStock = onStock;
	}


	public Date getShelfTime() {
		return shelfTime;
	}


	public void setShelfTime(Date shelfTime) {
		this.shelfTime = shelfTime;
	}

    
   
	
	
}

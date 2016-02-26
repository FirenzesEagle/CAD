package com.siims.szb.service.service.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_SERVICE")
public class ServiceInfoData implements BaseData{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
    @Column(name="SERVICENAME")
    private String serviceName;
  
    @Column(name="SERVICESHOWIMG")
    private String serviceShowImg;
  
    @Column(name="STOREID")
    private String storeId;
   
    @Column(name="CREATETIME")
    private Date createTime;
   
    @Column(name="ISDELETE")
    private String isDelete;
   
    
    @Column(name="SERVICETYPE")
    private String serviceType;
    
    @Column(name="SERVICEEWCODE")
	private String serviceEwcode;
    
    public ServiceInfoData(){
    	
    }
    /****************初始化***********************/
    public ServiceInfoData(String storeId){
    	super();
    	this.serviceName="";
    	this.serviceShowImg="";
    	this.storeId=storeId;
    	this.isDelete="0";
    	this.createTime = new Date();
    	this.serviceEwcode="";
    	this.serviceType="";
    }

   /*********************setter&&getter**************************/
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	


	public String getServiceShowImg() {
		return serviceShowImg;
	}
	public void setServiceShowImg(String serviceShowImg) {
		this.serviceShowImg = serviceShowImg;
	}
	public String getServiceEwcode() {
		return serviceEwcode;
	}
	public void setServiceEwcode(String serviceEwcode) {
		this.serviceEwcode = serviceEwcode;
	}
	public String getStoreId() {
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
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


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
    
    
    
   
}

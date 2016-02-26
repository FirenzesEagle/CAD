package com.siims.szb.service.serviceconfig.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_SERVICECONFIG")
public class ServiceConfigData implements BaseData{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
    @Column(name="CONFIGNAME")
    private String configName;
  
    @Column(name="CONFIGPRICE")
    private double configPrice;
  
    @Column(name="SERVICETIME")
    private String serviceTime;
   
    @Column(name="CREATETIME")
    private Date createTime;
   
    @Column(name="ISDELETE")
    private String isDelete;
   
    
    @Column(name="SERVICEID")
    private String serviceId;
    
    public ServiceConfigData(){
    	
    }
    public ServiceConfigData(String serviceId){
    	super();
    	this.configName="";
    	this.configPrice=0;
    	this.serviceTime="";
    	this.createTime = new Date();
    	this.isDelete="0";
    	this.serviceId=serviceId;
    }

/******************setter&&getter***********************/
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
	public String getServiceTime() {
		return serviceTime;
	}


	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
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


	public String getServiceId() {
		return serviceId;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	
    
    
   
}

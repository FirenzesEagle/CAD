package com.siims.szb.service.servicedetail.data;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_SERVICEDETAIL")
public class ServiceDetailData implements BaseData{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
    @Column(name="SERVICEDES")
    private String serviceDes;
  
    @Column(name="SERVICEDESIMG")
    private String serviceDesImg;
 
   
    
    @Column(name="SERVICECONFIGID")
    private String serviceConfigId;

    public ServiceDetailData(){
    	
    }
    
    public ServiceDetailData(String serviceConfigId){
    	this.serviceConfigId=serviceConfigId;
    	this.serviceDes="";
    	this.serviceDesImg="";
    }
    /******************setter&&getter***********************/
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getServiceDes() {
		return serviceDes;
	}



	public void setServiceDes(String serviceDes) {
		this.serviceDes = serviceDes;
	}



	



	

	public String getServiceDesImg() {
		return serviceDesImg;
	}

	public void setServiceDesImg(String serviceDesImg) {
		this.serviceDesImg = serviceDesImg;
	}

	public String getServiceConfigId() {
		return serviceConfigId;
	}



	public void setServiceConfigId(String serviceConfigId) {
		this.serviceConfigId = serviceConfigId;
	}


	
    
   
}

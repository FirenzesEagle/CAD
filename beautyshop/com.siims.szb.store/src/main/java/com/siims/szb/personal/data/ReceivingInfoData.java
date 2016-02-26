package com.siims.szb.personal.data;
/**   
 * @Title: ReceivingInfoData.java 
 * @Package: com.siims.szb.demo.data 
 * @Description: TODO
 * @author liumingbo 
 * @date 2015年9月12日 下午7:23:14 
 * @version 0.1
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

/** 
 * @Description 收货信息实体类
 * @author liumingbo
 * @date 2015年9月12日 下午7:23:14 
 * @version 0.1
 */

@Entity
@Table(name = "SZB_RECEIVING_INFO")
public class ReceivingInfoData implements BaseData {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1104540574390326063L;

	
	/** @Fields receiving_id: 收货人信息ID 主键*/
	  	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	
	@Column(name = "RECEIVING_ID", length = 32, nullable = false)
	private String receiving_id;
	
	
	/** @Fields openID: 用户openID*/
	  	
	@Column(name = "OPENID", length = 32)
	private String openID;
	
	/** @Fields receiving_name: 收货人姓名*/
	  	
	@Column(name = "RECEIVING_NAME", length = 32)
	private String receiving_name;
	
	
	/** @Fields receiving_tel: 联系电话*/
	  	
	@Column(name = "RECEIVING_TEL", length = 16)
	private String receiving_tel;
	
	
	/** @Fields receiving_address_area: 收货地区*/
	  	
	@Column(name = "RECEIVING_ADDRESS_AREA", length = 64)
	private String receiving_address_area;
	
	/** @Fields receiving_address_detail: 详细收货地址*/
	  	
	@Column(name = "RECEIVING_ADDRESS_DETAIL", length = 255)
	private String receiving_address_detail;
	
	
	/** @Fields default_receiving_info: 默认收货信息标识  0-非默认收货信息 1-默认收货信息*/
	  	
	@Column(name = "DEFAULT_RECEIVING_INFO")
	private Integer default_receiving_info;

	public String getId() {
		// TODO Auto-generated method stub
		return receiving_id;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		this.receiving_id = id;
	}

	public String getReceiving_id() {
		return receiving_id;
	}

	public void setReceiving_id(String receiving_id) {
		this.receiving_id = receiving_id;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getReceiving_name() {
		return receiving_name;
	}

	public void setReceiving_name(String receiving_name) {
		this.receiving_name = receiving_name;
	}

	public String getReceiving_tel() {
		return receiving_tel;
	}

	public void setReceiving_tel(String receiving_tel) {
		this.receiving_tel = receiving_tel;
	}

	public String getReceiving_address_area() {
		return receiving_address_area;
	}

	public void setReceiving_address_area(String receiving_address_area) {
		this.receiving_address_area = receiving_address_area;
	}

	public String getReceiving_address_detail() {
		return receiving_address_detail;
	}

	public void setReceiving_address_detail(String receiving_address_detail) {
		this.receiving_address_detail = receiving_address_detail;
	}

	public Integer getDefault_receiving_info() {
		return default_receiving_info;
	}

	public void setDefault_receiving_info(Integer default_receiving_info) {
		this.default_receiving_info = default_receiving_info;
	}

	@Override
	public String toString() {
		return "ReceivingInfoData [receiving_id=" + receiving_id + ", openID=" + openID + ", receiving_name="
				+ receiving_name + ", receiving_tel=" + receiving_tel + ", receiving_address_area="
				+ receiving_address_area + ", receiving_address_detail=" + receiving_address_detail
				+ ", default_receiving_info=" + default_receiving_info + "]";
	}
	
}

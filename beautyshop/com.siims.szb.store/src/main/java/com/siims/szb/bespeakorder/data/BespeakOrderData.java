package com.siims.szb.bespeakorder.data;

import java.util.Date;

import javax.inject.Singleton;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_BESPEAKORDER")
@Singleton
public class BespeakOrderData implements BaseData{

	/**预约服务订单实体类
	 * @author ling
	 */
	private static final long serialVersionUID = 1L;
	
	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	/**软删除标识
	 * 0为删除，1为未删除
	 */
	@Column(name = "ISDELETE")
	private int isdelete;
	/**服务的价格
	 * 
	 */
	@Column(name = "PRICE")
	private float price;
	/**订单的状态
	 * 
	 */
	@Column(name = "STATE")
	private int state;
	/**店铺的id
	 * 
	 */
	@Column(name = "SHOPID")
	private String shopid;
	/**预约记录的id
	 * 
	 */
	@Column(name = "RECORDID")
	private String recordid;
	/**预约人的id
	 * 
	 */
	@Column(name = "PERSONID")
	private String personid;
	/**预约人名称
	 * 
	 */
	@Column(name = "PERSONNAME")
	private String personname;
	/**联系人电话
	 * 
	 */
	@Column(name = "PERSONTEL")
	private String persontel;
	/**服务名称
	 * 
	 */
	@Column(name = "SERVICENAME")
	private String servicename;
	/**服务图片
	 * 
	 */
	@Column(name = "SERVICEIMAGE")
	private String serviceimage;
	/**服务名称
	 * 
	 */
	@Column(name = "SERVICEID")
	private String serviceid;
	/**预约的时间段
	 * 
	 */
	@Transient
	private String timepart;
	/**店铺的名称
	 * 
	 */
	@Transient
	private String shopname;
	/**订单创建的时间
	 * 
	 */
	@Column(name = "CREATETIME")
	private Date createtime;
	/**订单完成的时间
	 * 
	 */
	@Column(name = "FINISHTIME")
	private Date finishtime;
	
	
	/*---------------------------华丽的分割线---------------------------------------*/
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getPersontel() {
		return persontel;
	}

	public void setPersontel(String persontel) {
		this.persontel = persontel;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}
	
	public String getServiceimage() {
		return serviceimage;
	}

	public void setServiceimage(String serviceimage) {
		this.serviceimage = serviceimage;
	}
	
	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	/*------------------------------非数据库子段------------------------------------*/
	public String getTimepart() {
		return timepart;
	}

	public void setTimepart(String timepart) {
		this.timepart = timepart;
	}
	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
}

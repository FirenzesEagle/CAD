package com.siims.szb.bespeak.data;


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
@Table(name = "SZB_BESPEAK")
@Singleton
public class BespeakData implements BaseData{
	/**预约记录的实体类
	 * @author ling
	 * @since 2015年9月13日09:31:09
	 */
	private static final long serialVersionUID = 1L;
	
	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	/**预约的日期
	 * 
	 */
	@Column(name = "BESPEAKDATE")
	private String date;
	/**预约的行数，代表了时间段
	 * 
	 */
	@Column(name = "BESPEAKLINE")
	private int line;
	/**预约的列数
	 * 
	 */
	@Column(name = "BESPEAKROW")
	private int row;
	/**预约人id
	 * 
	 */
	@Column(name = "PERSONID")
	private String personid;
	/**预约人名称
	 * 
	 */
	@Column(name = "PERSONNAME")
	private String personname;
	/**预约人电话
	 * 
	 */
	@Column(name = "PERSONTEL")
	private String persontel;
	/**服务的id
	 * 
	 */
	@Column(name = "SERVICEID")
	private String serviceid;
	/**服务规格的id
	 * 
	 */
	@Column(name = "SPECID")
	private String specid;
	
	@Column(name = "ISDELETE")
	private int isdelete;
	
	@Column(name = "SHOPID")
	private String shopid;
	
	@Column(name = "CREATETIME")
	private Date createtime;
	/**预约备注，保留字段
	 * 
	 */
	@Column(name = "REMARKS")
	private String remarks;
	/**预约技师名称，保留字段
	 * 
	 */
	@Column(name = "MECHANIC")
	private String mechanic;
	
	@Transient
	private int state;
	
	@Transient
	private String orderid;
	/*-------------------------------华丽的分割线------------------------------------------*/
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public String getSpecid() {
		return specid;
	}

	public void setSpecid(String specid) {
		this.specid = specid;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMechanic() {
		return mechanic;
	}

	public void setMechanic(String mechanic) {
		this.mechanic = mechanic;
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
/*---------------------------非数据库字段----------------------------------*/
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	
	
}

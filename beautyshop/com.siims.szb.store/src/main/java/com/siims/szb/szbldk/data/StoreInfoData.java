package com.siims.szb.szbldk.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.siims.framework.base.BaseData;

@Entity
@Table(name = "SZB_STORE_INFO")
public class StoreInfoData implements BaseData{

	/**网店的实体类
	 * @author ling
	 * @since 2015年9月6日10:55:56
	 */
	private static final long serialVersionUID = 1L;

	//主键
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
	/**
	 * 微信的openid
	 */
	@Column(name = "OPENID")
	private String openid;
	
	/**
	 * 用户名
	 */
	@Column(name = "USERNAME")
	private String username;
	
	/**
	 * 密码
	 */
	@Column(name = "PASSWORD")
	private String password;
	
	/**
	 * 注册状态，已注册，未完全注册
	 */
	@Column(name = "REGSTATUS")
	private int regstatus;
	//网店的名称
	@Column(name = "NAME")
	private String name;
	
	//网店的描述
	@Column(name = "DESCRIPTION")
	private String desc;
	
	//网店的所有人的ID
	@Column(name = "USERID")
	private String userID;
	
	//网店的地址
	@Column(name = "ADDRESS")
	private String address;
	
	//网店的图片
	@Column(name = "STOREIMAGE")
	private String image;
	
	//隶属的商圈ID
	@Column(name = "DISTRICTID")
	private String districtID;
	
	//是否删除;0-删除、1-未删除
	@Column(name = "ISDELETE")
	private int isdelete;
	
	//网店的营业时间
	@Column(name = "OPENTIME")
	private String opentime;
	
	//网店的营业范围
	@Column(name = "AREA")
	private String area;
	
	//网点联系人名称
	@Column(name = "PERSON_CHARGE")
	private String person;
	
	//网点联系人的电话
	@Column(name = "TEL")
	private String tel;
	
	//网店联系人的电子邮箱
	@Column(name = "EMAIL")
	private String email;
	
	//网店创建的时间
	@Column(name = "CREATETIME")
	private Date createtime;
	
	//网店状态 0:暂存、1:已发布、2 :禁用、3:编辑中、4:被禁用后，启用成功的状态
	@Column(name = "STATUS")
	private int status;
	
	//禁用的时间
	@Column(name = "DISABLEDTIME")
	private Date disabledtime;
	
	//禁用的类型
	@Column(name = "DISABLEDTYPE")
	private int disabledtype;
	
	//禁用的备注
	@Column(name = "DISABLEDREASON")
	private String disabledreason;
	
	//二维码
	@Column(name = "ZING")
	private String zing;
	
	//平均评分
	@Column(name = "SCORE_AVERAGE")
	private float score_average;
	
	//第一项评分
	@Column(name = "SCORE_FIRST")
	private float score_first;
	
	//第二项评分
	@Column(name = "SCORE_SECOND")
	private float score_second;
	
	//第三项评分
	@Column(name = "SCORE_THIRD")
	private float score_third;
	
	//店铺的配送费
	@Column(name = "DISTRIBUTION_CHARGE")
	private float distribution;
	
	//店铺的起送价
	@Column(name = "PRICE_LEAST")
	private float price_least;
	

	
	/*--------------------------get和set方法---------------------------------------*/
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDisabledtime() {
		return disabledtime;
	}

	public void setDisabledtime(Date disabledtime) {
		this.disabledtime = disabledtime;
	}

	public int getDisabledtype() {
		return disabledtype;
	}

	public void setDisabledtype(int disabledtype) {
		this.disabledtype = disabledtype;
	}

	public String getDisabledreason() {
		return disabledreason;
	}

	public void setDisabledreason(String disabledreason) {
		this.disabledreason = disabledreason;
	}

	public String getZing() {
		return zing;
	}

	public void setZing(String zing) {
		this.zing = zing;
	}

	public float getScore_average() {
		return score_average;
	}

	public void setScore_average(float score_average) {
		this.score_average = score_average;
	}

	public float getScore_first() {
		return score_first;
	}

	public void setScore_first(float score_first) {
		this.score_first = score_first;
	}

	public float getScore_second() {
		return score_second;
	}

	public void setScore_second(float score_second) {
		this.score_second = score_second;
	}

	public float getScore_third() {
		return score_third;
	}

	public void setScore_third(float score_third) {
		this.score_third = score_third;
	}

	public float getDistribution() {
		return distribution;
	}

	public void setDistribution(float distribution) {
		this.distribution = distribution;
	}

	public float getPrice_least() {
		return price_least;
	}

	public void setPrice_least(float price_least) {
		this.price_least = price_least;
	}
}

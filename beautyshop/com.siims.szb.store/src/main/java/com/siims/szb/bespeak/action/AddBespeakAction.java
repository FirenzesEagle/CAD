package com.siims.szb.bespeak.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
import java.net.URLDecoder;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.bespeak.data.BespeakData;
import com.siims.szb.bespeak.service.BespeakService;
import com.siims.szb.bespeakorder.data.BespeakOrderData;
import com.siims.szb.bespeakorder.service.BespeakOrderService;
import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.service.ServiceConfigService;

@Namespace("/siims/szb/bespeak")
public class AddBespeakAction extends StrutsAction{

	/**添加预约记录
	 * @author ling
	 */
	private static final long serialVersionUID = 1L;
	
	private BespeakData data = new BespeakData();
	
	private ServiceInfoData servicedata;
	
	private ServiceConfigData configdata;
	
	private BespeakOrderData orderdata = new BespeakOrderData();
	
	private Map<String,Object> application;
	
	@Action(value = "toaddbespeak", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/yuyue-confirm.ftl")})
	public String toaddbespeak() {
		return SUCCESS;
	}
	
	@Action(value = "addbespeak")
	public void AddBespeak() throws IOException{
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		String msg = "";
		//获取预约集合
		String json = request.getParameter("DATA");
		//String json = "{\"record\":[{\"shopid\":\"1\",\"serviceid\":\"1\",\"specid\":\"123456789\",\"personname\":\"1\",\"persontel\":\"123\",\"year\":\"2015\",\"month\":\"11\",\"day\":\"8\",\"line\":\"17\",\"row\":\"1\"}]}";
		boolean real = false;
		//判空
		if(json == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		//i记录有多少个预约记录
		int i = 0;
		
		Type type = new TypeToken<Bespeaks>(){}.getType();
		
		Gson gson = new Gson();
		try{
			Bespeaks bespeaks = gson.fromJson(json, type);
		
			while(i < bespeaks.record.size())
			{
				bespeak d = bespeaks.record.listIterator(i).next();
				data.setServiceid(d.getServiceid());
				data.setShopid(d.getShopid());
				data.setSpecid(d.getSpecid());
				data.setPersonid(d.getPersonid());
				
				String person_name = URLDecoder.decode(d.getPersonname());
				
				data.setPersonname(person_name);
				System.out.println("here is addbespeak : the personname is " + person_name);
				data.setPersontel(d.getPersontel());
				String Date = "";
				if(d.getMonth() < 10 && d.getDay() < 10)
				{
					Date = d.getYear() + "0" + d.getMonth() + "0" + d.getDay() + "";
				}else if(d.getMonth() < 10)
				{
					Date = d.getYear() + "0" + d.getMonth() + "" + d.getDay() + "";
				}else if(d.getDay() < 10)
				{
					Date = d.getYear() + "" + d.getMonth() + "0" + d.getDay() + "";
				}else{
					Date = d.getYear() + "" + d.getMonth() + "" + d.getDay() + "";
				}
				data.setDate(Date);
				data.setLine(d.getLine());
				data.setRow(d.getRow());
				//判断是否是在可预约的时间段
				String s = "";
				int day = (new Date()).getDate();
				int month = (new Date()).getMonth()+1;
				int year = (new Date()).getYear()+1900;
				if(month < 10 && day < 10)
				{
					s = year + "0" + month + "0" + day + "";
				}else if(day < 10){
					s = year + "" + month + "0" + day + "";
				}else if(month < 10){
					s = year + "0" + month + "" + day + "";
				}else{
					s = year + "" + month + "" + day + "";
				}
				int hour = (new Date()).getHours();
				int minute = (new Date()).getMinutes();
			
				//获取application对象
				application = ActionContext.getContext().getApplication();
				if(application.get("bespeakkey") == null)
				{
					application.put("bespeakkey", false);
				}
			
				boolean key = (Boolean) application.get("bespeakkey");
			
				if(key == false)
				{
					key = true;
					application.put("bespeakkey", key);
				}
			
				if(key == true)
				{
					if(ServiceContext.get(BespeakService.class).GetBespeakByPoint(Date, d.getLine(), d.getRow(), d.getShopid()).size() == 0)
					{
						if(s.equals(Date) && hour == d.getLine() && minute >= 40)
						{
							msg += Date+"的"+d.getLine()+":00~"+(d.getLine()+1)+":00时间段的服务预约失败,剩余时间小于20分钟则停止预约,";
						}else{
							//此处需要通过id调用接口以获得数据
							servicedata = ServiceContext.get(ServiceService.class).searchServiceInfo(d.getServiceid());
							
							configdata = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(d.getSpecid());
							//获得数据后，将越狱转化为预约订单
							if(servicedata != null && configdata != null)
							{
								String bespeakid = ServiceContext.get(BespeakService.class).AddBespeakRecord(data);
								msg += Date+"的"+d.getLine()+":00~"+(d.getLine()+1)+":00时间段的服务预约成功,";
								
								orderdata.setPersonid(d.getPersonid());
								orderdata.setPersonname(person_name);
								orderdata.setPersontel(d.getPersontel());
								orderdata.setRecordid(bespeakid);
								orderdata.setPrice((float)configdata.getConfigPrice());
								orderdata.setServiceid(d.getServiceid());
								orderdata.setServiceimage(servicedata.getServiceShowImg());
								orderdata.setServicename(servicedata.getServiceName());
								orderdata.setShopid(d.getShopid());
								orderdata.setState(1);
								
								ServiceContext.get(BespeakOrderService.class).AddBespeakOrder(orderdata);
							}else{
								msg += Date+"的"+d.getLine()+":00~"+(d.getLine()+1)+":00时间段的服务预约失败2,";
							}
						}
					}else{
						msg += Date+"的"+d.getLine()+":00~"+(d.getLine()+1)+":00时间段的服务预约失败3,";
					}
					i++;
					key = false;
					application.put("bespeakkey", key);
				}
			}
			if(msg.contains("成功"))
			{
				responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\" " + msg.substring(0, msg.length()-1) +"\"}";
			}else{
				responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\" " + msg.substring(0, msg.length()-1) +"\"}";
			}
			ActionUtil.sendJson(responseResult);
			}catch(Exception e){
				System.out.println(e.toString());
				responseResult = "{\"SUCCESS\":\"false\",\"ERROMSG\":\"数据错误\"}";
				ActionUtil.sendJson(responseResult);
				return;
			}
	}

	class bespeak {
		private String shopid;
		private String serviceid;
		private String specid;
		private String personid;
		private String personname;
		private String persontel;
		private int year;
		private int month;
		private int day;
		private int line;
		private int row;
		
		public String getShopid() {
			return shopid;
		}
		public void setShopid(String shopid) {
			this.shopid = shopid;
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
		public String getPersonid() {
			return personid;
		}
		public void setPersonid(String personid) {
			this.personid = personid;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public int getMonth() {
			return month;
		}
		public void setMonth(int month) {
			this.month = month;
		}
		public int getDay() {
			return day;
		}
		public void setDay(int day) {
			this.day = day;
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
	}
	
	class Bespeaks {
		private List<bespeak> record;

		public List<bespeak> getRecord() {
			return record;
		}

		public void setRecord(List<bespeak> record) {
			this.record = record;
		}
	}
}

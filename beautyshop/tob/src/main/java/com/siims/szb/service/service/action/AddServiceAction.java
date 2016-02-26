package com.siims.szb.service.service.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siims.framework.ioc.ServiceContext;
import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;


import com.siims.szb.service.service.data.ServiceInfoData;
import com.siims.szb.service.service.service.ServiceService;
import com.siims.szb.service.serviceconfig.data.ServiceConfigData;
import com.siims.szb.service.serviceconfig.service.ServiceConfigService;
import com.siims.szb.service.servicedetail.data.ServiceDetailData;
import com.siims.szb.service.servicedetail.service.ServiceDetailService;


@Namespace("/siims/szb/service")
public class AddServiceAction extends StrutsAction {

	/**
	 * @author liufeng
	 * @since 2015年8月20日16:24:49
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	private ServiceInfoData serviceInfoData ;
	private ServiceConfigData serviceConfigData ;
	//private ServiceDetailData serviceDetailData ;
	
	@Action(value = "addservice", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/addService.ftl")})
	public String addservice() {
		return SUCCESS;
	}
	
	
	@Action(value = "addService")
	public void addService() throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		
		//根据传进来的商铺id创建一个服务对象
		String storeId = request.getParameter("storeId");
		serviceInfoData = new ServiceInfoData(storeId);
		
		//获取提交的信息
		//String serviceName = URLDecoder.decode( request.getParameter("serviceName"));
		String serviceName = request.getParameter("serviceName");
		//String serviceTime = URLDecoder.decode( request.getParameter("serviceTime"));
		String serviceTime = request.getParameter("serviceTime");
				
		String servicePrice = request.getParameter("servicePrice");
		String serviceShowImg = request.getParameter("serviceShowImg");
				
		//String serviceDesImg =  request.getParameter("serviceDescImg");
		//String serviceDes = request.getParameter("serviceDes");
		
		//将获取的服务数据已对象方式存储
		serviceInfoData.setServiceName(serviceName);
		serviceInfoData.setServiceShowImg(serviceShowImg);
		String msg3 = ServiceContext.get(ServiceService.class).addServiceInfo(serviceInfoData);
		
		//根据服务的id创建一个规格对象
		String serviceId = serviceInfoData.getId();
		
		serviceConfigData =new ServiceConfigData(serviceId);
		
		//将获取的服务规格数据已对象方式存储
		serviceConfigData.setServiceTime(serviceTime);
		serviceConfigData.setConfigPrice(Double.valueOf(servicePrice));
		String msg2 = ServiceContext.get(ServiceConfigService.class).addConfigInfo(serviceConfigData);
		
		//根据规格的Id创建一个服务详细信息对象
		//String detailId = serviceConfigData.getId();
//		serviceDetailData = new ServiceDetailData(detailId);
//		
//		serviceDetailData.setServiceDesImg(serviceDesImg);
//		serviceDetailData.setServiceDes(serviceDes);
		
		 
		
		
		
		
		
		
		
		
		
		String json = request.getParameter("DATA");
		System.out.println(json);
		if(request.getParameter("DATA") == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		int i = 0;//有多少个Data
		int j = 0;//每个评论有多少个pic
		
		Type type = new TypeToken<C>() {
		}.getType();
		System.out.println(11);
		Gson gson = new Gson();
		System.out.println(22);
		C c = gson.fromJson(json, type);
		System.out.println(33);
		System.out.println(c.Data.size());
		while(i < c.Data.size())
		{
			
			System.out.println(44);
			//获得描述信息和
			//String serviceDes = URLDecoder.decode(c.Data.listIterator(i).next().getServiceDes());
			String serviceDes = c.Data.listIterator(i).next().getServiceDes();
			String serviceDesPicUrl = c.Data.listIterator(i).next().getServiceDesPicUrl();
			System.out.println(serviceDes);
			System.out.println(serviceDesPicUrl);
			System.out.println(55);
			System.out.println(serviceConfigData.getId());
			ServiceDetailData serviceDetailData = new ServiceDetailData(serviceConfigData.getId());
			System.out.println(66);
			serviceDetailData.setServiceDesImg(serviceDesPicUrl);
			serviceDetailData.setServiceDes(serviceDes);
			System.out.println(77);
			ServiceContext.get(ServiceDetailService.class).addDetailInfo(serviceDetailData);
			System.out.println(88);
			i++;
		}
		
		
		
		
		
		
		
		
		
		
		String msg ="  "+msg2+"  "+msg3;
		
		//responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + msg + "\"}";
		responseResult = "{\"SUCCESS\":\"true\"}";
		ActionUtil.sendJson(responseResult);
	}
	
	/**
	 * 单个服务详细表
	 * @author liufeng
	 *
	 */
	class B {
		
		private String serviceDes;
		private String serviceDesPicUrl;
		public String getServiceDes() {
			return serviceDes;
		}
		public void setServiceDes(String serviceDes) {
			this.serviceDes = serviceDes;
		}
		public String getServiceDesPicUrl() {
			return serviceDesPicUrl;
		}
		public void setServiceDesPicUrl(String serviceDesPicUrl) {
			this.serviceDesPicUrl = serviceDesPicUrl;
		}
		

		
		
		
	}
	/**
	 * 服务详细表集合
	 * @author liufeng
	 *
	 */
	class C {
		private List<B> Data;

		public List<B> getData() {
			return Data;
		}
		public void setData(List<B> data) {
			Data = data;
		}
	}
	
	
}

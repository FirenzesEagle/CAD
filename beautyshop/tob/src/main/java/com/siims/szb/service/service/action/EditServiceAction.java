package com.siims.szb.service.service.action;

import java.io.IOException;
import java.lang.reflect.Type;
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

/**
 * 
 * @author liufeng
 *
 */
@Namespace("/siims/szb/service")
public class EditServiceAction extends StrutsAction {

	
	private static final long serialVersionUID = 1L;
	
	
	private ServiceInfoData serviceInfoData ;
	private ServiceConfigData serviceConfigData;
	private ServiceDetailData serviceDetailData ;
	
		
	
	 @Action(value = "editservice", results = {@Result(name = "success", type = "freemarker", location = "/ui/ftl/editService.ftl")})
		public String editservice() {
			return SUCCESS;
		}
	
	
	@Action(value = "editServiceByDetailId")
	/**
	 * 根据服务详细信息的id修改服务、服务规格和服务详细信息
	 * 
	 */
	public void editServiceByDetailId() throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String configId = request.getParameter("serviceId");
		System.out.println(configId+"hahahahahahaha");
		//List<ServiceDetailData> listDetail = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(configId);
		
		
		
		//根据detail表的id获取detail对象
		//String serviceDetailId = listDetail.get(0).getId();
		
		//serviceDetailData = ServiceContext.get(ServiceDetailService.class).searchDetailInfo(serviceDetailId);
		
		
		
		serviceConfigData = ServiceContext.get(ServiceConfigService.class).searchConfigInfo(configId);
		
		String serviceID = serviceConfigData.getServiceId();
		serviceInfoData = ServiceContext.get(ServiceService.class).searchServiceInfo(serviceID);
		
		
		//初始化输出数据
		String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
		
		//获取提交的信息
		String serviceName = request.getParameter("serviceName");
		String serviceTime = request.getParameter("serviceTime");
		
		String servicePrice = request.getParameter("servicePrice");
		String serviceShowImg = request.getParameter("serviceShowImg");
		
//		
		
		//将数据已对象方式存储
		serviceInfoData.setServiceName(serviceName);
		serviceInfoData.setServiceShowImg(serviceShowImg);
		
		serviceConfigData.setServiceTime(serviceTime);
		serviceConfigData.setConfigPrice(Double.valueOf(servicePrice));
		
//		serviceDetailData.setServiceDesImg(serviceDesImg);
//		serviceDetailData.setServiceDes(serviceDes);
		
		//将信息提交至数据库进行存储,并获得自动生成的主键id
		//并且把detail的主键存储在msg，因为根据detail的主键可以查找service和config的主键
		ServiceContext.get(ServiceService.class).editServiceInfo(serviceInfoData);
		ServiceContext.get(ServiceConfigService.class).editConfigInfo(serviceConfigData);
		//ServiceContext.get(ServiceDetailService.class).editDetailInfo(serviceDetailData);
		
		
		
		
		System.out.println("****+++++****");
		
//		 List<ServiceDetailData> listDetail = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(serviceConfigData.getId());
//         for(int i=0;i<listDetail.size();i++){
//        	 ServiceContext.get(ServiceDetailService.class).delDetailInfo(listDetail.get(i));
//         }
		
		
	    //修改之前的detail表中的数据
        List<ServiceDetailData> listDetail = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(serviceConfigData.getId());

        String json = request.getParameter("DATA");
		System.out.println(json);
		if(request.getParameter("DATA") == null)
		{
			responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"无数据\"}";
			ActionUtil.sendJson(responseResult);
			return;
		}
		int i = 0;//有多少个Data
		
		
		Type type = new TypeToken<C>() {
		}.getType();
		System.out.println(11);
		Gson gson = new Gson();
		System.out.println(22);
		C c = gson.fromJson(json, type);
		System.out.println(33);
		System.out.println(c.Data.size());
		//循环遍历从前端传过来的数组
		while(i < c.Data.size())
		{
			
			System.out.println(44);
			//获得描述信息和
			String serviceDesDes = c.Data.listIterator(i).next().getServiceDes();
			String serviceDesImg = c.Data.listIterator(i).next().getServiceDesPicUrl();
			/**************/
			String detailId = c.Data.listIterator(i).next().getServiceId();
			System.out.println(detailId);
			System.out.println(serviceDesDes);
			System.out.println(serviceDesImg);
			int j;
			System.out.println(listDetail.size());
			for( j=0;j<listDetail.size();j++){
				//修改已有的详情信息
				System.out.println(listDetail.get(j).getId());
				if(detailId.equals(listDetail.get(j).getId())){
					System.out.println("只是做修改的详细");
					ServiceDetailData serviceDetail = ServiceContext.get(ServiceDetailService.class).searchDetailInfo(detailId);
					serviceDetail.setServiceDesImg(serviceDesImg);
					serviceDetail.setServiceDes(serviceDesDes);
        			ServiceContext.get(ServiceDetailService.class).editDetailInfo(serviceDetail);
        			break;
				}        				           			
			}
			//add新的详情信息
			if(j==listDetail.size()){
				System.out.println("新添加的详细");
				ServiceDetailData serviceDetail = new ServiceDetailData(serviceConfigData.getId());
				System.out.println(66);
				
				serviceDetail.setServiceDesImg(serviceDesImg);
				serviceDetail.setServiceDes(serviceDesDes);
				System.out.println(77);
				
				System.out.println(ServiceContext.get(ServiceDetailService.class).addDetailInfo(serviceDetail));
				//修改传过来的数据的id
				c.Data.listIterator(i).next().setServiceId(serviceDetail.getId());
				System.out.println(c.Data.listIterator(i).next().getServiceId());
			
			}
			
			i++;
		}
		//添加后的详细表数据
		 List<ServiceDetailData> listDetail2 = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(serviceConfigData.getId());
		System.out.println(listDetail2.size()+"........");
		for(int m=0;m<c.Data.size();m++){
			System.out.println("前端ID"+c.Data.listIterator(m).next().getServiceId());
		}
		
		for(int m=0;m<listDetail2.size();m++){
			System.out.println("数据库ID"+listDetail2.get(m).getId());
		}
		//删除详情信息
			i=0;
			for(int j=0;j<listDetail2.size();j++){
				System.out.println("删除过程");
				ServiceDetailData serviceDetail = ServiceContext.get(ServiceDetailService.class).searchDetailInfo(listDetail2.get(j).getId());
				while(i < c.Data.size()){
					//遍历detail表
					System.out.println("进入数组");
					
					String detailId = c.Data.listIterator(i).next().getServiceId();
					System.out.println("*******************************");
					System.out.println(detailId);
					if(!detailId.equals(listDetail2.get(j).getId())){
						i++;
						
					}
					else
						break;
				}
				//如果数据库中的一条数据不和所有的前段数据匹配则删除
				if(i==c.Data.size()){
					ServiceContext.get(ServiceDetailService.class).delDetailInfo(serviceDetail);
					System.out.println("删除成功");
				}
				i=0;
				
			}
	
			System.out.println("删除结束");
			 List<ServiceDetailData> listDetail3 = ServiceContext.get(ServiceDetailService.class).searchAllDetailInfo(serviceConfigData.getId());
         System.out.println("修改后数据总数"+listDetail3.size());
		
		
		responseResult = "{\"SUCCESS\":\"true\"}";
		ActionUtil.sendJson(responseResult);
	}
	

	/**
	 * 单个服务详细表
	 * @author liufeng
	 *
	 */
	class B {
		private String serviceId;
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
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
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

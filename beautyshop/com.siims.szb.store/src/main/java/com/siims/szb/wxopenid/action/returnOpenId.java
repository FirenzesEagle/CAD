package com.siims.szb.wxopenid.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import net.sf.json.JSONObject;

import com.siims.framework.mvc.struts.StrutsAction;
import com.siims.framework.utils.ActionUtil;
import com.siims.szb.utils.CommonUtil;

//@Namespace("/siims/szb/openid")
public class returnOpenId extends StrutsAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//微信公众平台id
	private String appid = "wxbf649da2ec1bd6e9";
	//微信公众平台密钥
	private String appsecret = "ac55516b3a4b31912d437c63584aafd5";
	
	//@Action(value = "returnopenid")
	public void returnopenid() throws IOException{
		
		System.out.println("returnopenid");
		
		//初始化输出数据
		 String responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"\",\"ERROMSG\":\"\"}";
				
		 String code = request.getParameter("code");
	        
	     String openId ="";
	     String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
	     JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
	     if (null != jsonObject) {
	    	 openId = jsonObject.getString("openid");
	    	 responseResult = "{\"SUCCESS\":\"true\",\"DATA\":\"" + openId + "\"}";
	     }else{
	    	 openId = null;
	    	 responseResult = "{\"SUCCESS\":\"false\",\"DATA\":\"" + openId + "\"}";
	     }
	     ActionUtil.sendJson(responseResult);
	}

}

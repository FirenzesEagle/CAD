package com.siims.szb.order.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.siims.framework.mvc.struts.StrutsAction;

@Namespace("/siims/szb/order")
public class GoodsOrderWXNotifyAction extends StrutsAction{
	
	/**商品订单的微信支付成功后的操作
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String DOMIN = "localhost:8838";

	@Action(value = "GoodsOrderWXNotify")
	public void GoodsOrderWXNotify() throws IOException,DocumentException{
		String type = request.getParameter("type");
		System.out.println(type);
		
		System.out.println(type);
		
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        if(sb.toString()!="" && !"".equals(sb.toString())){
            Document dom=DocumentHelper.parseText(sb.toString());
            Element root=dom.getRootElement();
            //微信返回成功值
            String return_code=root.element("return_code").getText();
            if(return_code.equals("SUCCESS")){
                //订单编号 
                String orderCode=root.element("out_trade_no").getText();
                if(orderCode!=null && !"".equals(orderCode)){
                	String url = DOMIN + "/siims/szb/order/updateStatus.jspx?status="+type+"&id="+orderCode;
                	response.sendRedirect(url);
                    //response.sendRedirect(DOMAIN+"/pagehtml/"+conSumerOrderData.getBaseReserveId()+".html");
                	return;
                }
             }
         }
        
	}
	
}

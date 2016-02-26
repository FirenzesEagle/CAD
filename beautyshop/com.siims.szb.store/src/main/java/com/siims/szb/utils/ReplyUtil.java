package com.siims.szb.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/*
 * author:罗贵木
 * time:2015-08-28
 * descption:Base中的用户回复URL访问消息的工具类
 * version:1.0.0
 */
public class ReplyUtil {

	public static void reply(HttpServletResponse response, String content) {
		try {
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setContentType("application/json;charset=UTF-8");
			response.setContentType("text/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

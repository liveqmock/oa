package com.icss.oa.hr.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.resourceone.sdk.framework.Context;

public class HRInfoServlet extends ServletBase {

	protected void performTask(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {

		GetMethod authGet = null;
		try {
			String host = HRGroupWebservice.readValue("HRPAGE");
			Context ctx = Context.getInstance();
			String userid = ctx.getCurrentUserid();
			
			String URL = host+"?user="+userid;

			// 构造HttpClient的实例
			HttpClient httpClient = new HttpClient();
			// 创建GET方法的实例

			authGet = new GetMethod(URL);
			// postMethod = new PostMethod(URL);
			//			
			// NameValuePair[] data = { new NameValuePair("id", userid),
			// new NameValuePair("passwd", "yourPwd") };
			// 将表单的值放入postMethod中
			// postMethod.setRequestBody(data);
			// System.out.println(postMethod.getResponseCharSet());
			// 执行postMethod

			// httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GBK");
			
			httpClient.getParams().setContentCharset("gb2312");
			int statusCode = httpClient.executeMethod(authGet);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + authGet.getStatusLine());
			}
			// 读取内容
			// byte[] responseBody = postMethod.getResponseBody();

			String responseBody = authGet.getResponseBodyAsString();
			// String(responseBody1.getBytes("ISO-8859-1"),"gb2312");
			// 处理内容

			PrintWriter out = rep.getWriter();

			out.write(responseBody);
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			authGet.releaseConnection();
		}
	}

}
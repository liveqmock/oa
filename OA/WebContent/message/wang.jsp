<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.icss.oa.message.api.MSGSender"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.io.BufferedReader"%>
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
</HEAD>
<body>
<%
	System.out.println("msg1:begin");
	String num_msg=request.getParameter("num_msg");
	System.out.println("msg2:"+num_msg);
	StringBuffer resStr=new StringBuffer(); 
	InputStream in = null;
	try{
		URL url=new URL("http://192.9.100.48:9080/oabase/msg/GetShortMsgServlet?num_msg="+num_msg);
		in=url.openConnection().getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String str;
		while((str = br.readLine())!=null){
			resStr.append(str);  
		}
		//System.out.println("msg3:receive"+resStr.toString());
	}
	catch(Exception e){
		e.printStackTrace();
		System.out.println("msg3:error"+e);
		System.out.println("msg4:无法发送短信："+num_msg);
	}
	finally{
	    try {
			if(in!=null){
			 	in.close();
			}
		} catch (Exception e) {
		   	e.printStackTrace();
		}
	}
	response.setContentType("text/xml");
	response.getWriter().write(resStr.toString());
	System.out.println("msg5:end");
	
%>
</body>
</html>
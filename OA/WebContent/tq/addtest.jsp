<%@ page contentType="text/html; charset=GBK" language="java"
	errorPage=""%>
<%@ page import="com.icss.oa.tq.Webservice.TQUser"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@page import="java.sql.ResultSet"%>
<html>
<head>
<title>����»�ͨ��Ա_FOR_XUMEI</title>
</head>
<body>
	
	
	
	
	
<%
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String uin = "";

	String userid = request.getParameter("userid");
	
	String username="addByXuMei";
	
	String password="ROTDqyBNVFSxE";
	String sex="sex";
	
	try {
		if (userid != null && !"".equals(userid.trim())) {
			out.println("�û���="+userid);
				TQUser tquser = new TQUser();
			uin = tquser.oneUserRegister(userid,username,password,
						sex, "", "", "2", "");
			if (uin.length() < 7) {
				out.println("�������,����û���="+uin);
			} 
			
		
			
		} else {
			out.println("�������Ӻ������userid<br/>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		if (rs != null)
			rs.close();
		if (pst != null)
			pst.close();
		if (conn != null)
			conn.close();
	}
%>

<table><tr><td>�û���:
	<input type=text id="uin" value="yxpt"></input>
<input type=button onclick="_submit();" value="�ύ"></input>
		
		
		</td></tr></table>



<script language="javascript">
//alert("uin="+uin);

function _submit(){
	var uid;
	uid=document.getElementById("uin").value;
  //alert("�����û���="+uid);
	window.location.href="http://10.102.1.37/oabase/tq/addtest.jsp?result=login&LTPAToken=eHVtZWkqNDM2MGM1MWYtMTAwYjViYTgxNzQtOTg0OWRkZDljZGE1NjdjNzIyYjUxZTQzNjljMzU3N2EqMjMqU3RyaW5n&userid="+uid;
	if(uid=="")
	{
		alert("�����û���");
    return;
  }
  
	
	
	}






</script>
</body>
</html>

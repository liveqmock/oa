<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="com.icss.oa.user.vo.UserInfoSearchVO"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>

<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>

<%
UserInfoSearchVO vo = (UserInfoSearchVO) request.getAttribute("userInfoSearchVO");
String orgcode = vo.getHrorgcode()==null?"":vo.getHrorgcode();
String searchpriv = (String)request.getAttribute("searchpriv");
String specialpriv = (String)request.getAttribute("specialpriv");
String jobcode=vo.getHrjobcode();
String mobilephone = vo.getHrmobilephone();
String homephone = vo.getHrhomephone();
//System.out.println(username + "orgcode is "+orgcode);
if("0".equals(specialpriv)){
 //可以查看所有人电话
 //System.out.println("specialpriv is 0");
}else{
	if(!"".equals(orgcode) && specialpriv.indexOf(orgcode)>=0){
	//拥有该查看该部门所有人员信息的权限
	//System.out.println("specialpriv is not 0");
	}else{
	//System.out.println("not in specialpriv");
   		if("".equals(searchpriv) && jobcode!=null){
			homephone = "";
			mobilephone = "";
		}else{
								   
		   if(searchpriv.indexOf(","+jobcode)>=0 || jobcode==null){
			//有权查看
			}else{
				homephone = "";
				mobilephone = "";
			}
		}
	}
}

if(vo!=null){
%>
<html>
	<head>
		<title>查看个人信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2132">
		<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/Style/css_grey.css" rel="stylesheet" type="text/css" />

	</head>
	<body bgcolor="#ffffff">
    	<table width="600" cellpadding="0" cellspacing="1" border="0" class="table_bgcolor" align="center">
        	<tr>
           	  <td class="block_title" colspan="3">&nbsp;&nbsp;<%=vo.getUsername()%> <span class="message_title">的信息</span></td>
            </tr>
            <tr>
            	<td height="160" width="110" align="center" rowspan="5" bgcolor="#FFFFFF">
                <IMG height="145" width="100" src="<%=request.getContextPath()%>/servlet/UserInfoServlet?image=1&personuuid=<%=vo.getUseruuid()%>" alt='暂无头像'>
                </td>
                <td width="440" colspan=2 height="30" bgcolor="#FFFFFF" class="message_title">&nbsp;所在部门：<span class="message_title_bold"><%=handler.getPersonJUPositionInJsp(vo.getUseruuid())%></span></td>
                <!--<td width="220" bgcolor="#FFFFFF" class="message_title">&nbsp;职务：<span class="message_title_bold"><%=StringUtil.escapeNull(vo.getHeadship())%></span></td> -->
            </tr>
            <tr>
                <td width="220" height="30" bgcolor="#FFFFFF" class="message_title">&nbsp;办公电话：<span class="message_title_bold"><%="社领导".equals(vo.getHrdeptname())?"":StringUtil.escapeNull(vo.getHrofficephone())%></span></td>
                <td width="220" bgcolor="#FFFFFF" class="message_title">&nbsp;传真：<span class="message_title_bold"><%=StringUtil.escapeNull(vo.getHrfaxphone())%></span></td>
			</tr>
            <tr>
                <td width="220" height="30" bgcolor="#FFFFFF" class="message_title">&nbsp;宽带电话：<span class="message_title_bold"><%=StringUtil.escapeNull(vo.getHrnetphone())%></span></td>
                <td width="220" bgcolor="#FFFFFF" class="message_title">&nbsp;VPN电话：<span class="message_title_bold"><%=StringUtil.escapeNull(vo.getVpn())%></span></td>
			</tr>
            <tr>
                <td width="220" height="30" bgcolor="#FFFFFF" class="message_title">&nbsp;手机：<span class="message_title_bold"><%//=StringUtil.escapeNull(mobilephone)%></span></td>
                <td width="220" bgcolor="#FFFFFF" class="message_title">&nbsp;家庭电话：<span class="message_title_bold"><%//=StringUtil.escapeNull(homephone)%></span></td>
			</tr>
            <!--
            <tr>
                <td width="220" height="30" bgcolor="#FFFFFF" class="message_title">&nbsp;办公地址：</td>
                <td width="220" bgcolor="#FFFFFF" class="message_title">&nbsp;家庭住址：<span class="message_title_bold"><%=StringUtil.escapeNull(vo.getHrhomeaddress())%></span></td>
			</tr>
            -->
            
        </table>
        
</body>
</html>
<% 
}else{
out.println("没有此人信息!");
}
%>

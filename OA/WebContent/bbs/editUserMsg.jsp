<%@ page contentType="text/html; charset=GBK" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.oa.filetransfer.handler.personInfoHandler" %>
<%@ page import="com.icss.oa.bbs.handler.BBSAreaHandler" %>
<%
//System.err.println("ok1!");
Collection collection = (Collection)request.getAttribute("userList");
//System.err.println("ok2!");
Iterator Iterator = collection.iterator();
//System.err.println("ok3!");
BbsUserinfoVO usermsgVO = null;

//System.err.println("ok4!");
if(Iterator.hasNext())
{
	usermsgVO = (BbsUserinfoVO)Iterator.next();
}
String uuid = usermsgVO.getUserid();
String  currUserId = (String)request.getAttribute("currUserId");
System.err.println("ok5!");
//out.print(currUserId);
Connection conn = null;
try {
	System.err.println("ok3!");
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("editUserMsg.jsp");
	personInfoHandler _personInfoHandler = new personInfoHandler(conn);		
	BBSAreaHandler ahandler = new BBSAreaHandler(conn);	
	String orguuid = _personInfoHandler.getOrgUUID(uuid);
	System.err.println("orguuid="+orguuid);
	String orgname = _personInfoHandler.getOrgName(orguuid);
	System.err.println("orgname="+orgname);	
	String _orguuid = _personInfoHandler.getParentUUID(orguuid);
	System.err.println("_orguuid="+_orguuid);
	String _orgname = _personInfoHandler.getOrgName(_orguuid);
	System.err.println("_orgname="+_orgname);
	
	String tel = ahandler.getPersonTel(uuid);
	
	if(tel==null||tel==""){
		tel = "暂无电话号码";
	}
	String job = ahandler.getPersonJob(uuid);
	if(job==null||job==""){
		job = "";
	}
	System.err.println("tel="+tel);
%>

<html>
<head>
<title>查看个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">
<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
</head>
<body bgcolor="#ffffff">
<FORM name=form1  method=post>
<table width="100%" height="523"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><div align="center"><br>
      </div>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          	<td width="100%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" colspan=3 height="23">
				<div align="center" class="white2-12-b">用户 <%=usermsgVO.getTruename()%>
				的资料</div>
				</td>
          </tr>
      </table>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">

        <tr>
          <td width="1"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td ><table width="100%"  border="0" cellspacing="2" cellpadding="1" bgcolor="#B9DAF9">
                    <tr> 
                      <td height="22" colspan="2" bgcolor="FBFBEE">
								<div align="center" class="blue2-12">个人资料</div>
								</td>
                    </tr>
                    <tr> 
                      <td height="22" bgcolor="FFFFFF"><span class="blue2-12">&nbsp;<B>用户名称：</B><%=usermsgVO.getTruename()%></span></td>
        			  <td class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>总共发贴：</B>&nbsp;<%=usermsgVO.getPubnum()%></td></tr>
                    <tr> 
<td height="22" bgcolor="FFFFFF" class="blue2-12">&nbsp;<B>用户电话：</B><%=tel%></td>
								
								<td class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>最后登陆：</B> <%if (usermsgVO.getLasttime() != null) {%>
								<%=CommUtil.getTime(usermsgVO.getLasttime().longValue())%> <%}%>
								</td>
                    </tr>
					<tr>
						<td height="22" class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>用户所在：</B><%=_orgname%>-<%=orgname%>&nbsp;
						
						<td width="50%" class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>登录ＩＰ：</B> <%if (usermsgVO.getLastip() != null) {%>
						<%=usermsgVO.getLastip()%> <%}%></td>
					</tr>
					<tr>
						<td height="22" colspan="2" bgcolor="FBFBEE">
						<div align="center"><span class="blue2-12">联系资料</span></div>
						</td>
					</tr>
                    <tr>
                      <td bgcolor="FFFFFF" class="blue2-12"><B>用户头像</B><BR>
                     <INPUT 
		    type=radio CHECKED  value=001.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/001.gif" width="40" height="40"><INPUT 
			type=radio value=002.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/002.gif" width="40" height="40"><INPUT 
            type=radio value=003.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/003.gif" width="40" height="40"><INPUT 
            type=radio value=004.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/004.gif" width="40" height="40"><INPUT 
            type=radio value=005.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/005.gif" width="40" height="40"><INPUT 
            type=radio value=006.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/006.gif" width="40" height="40"><INPUT 
            type=radio value=007.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/007.gif"width="40"height="40"><br><INPUT 
			type=radio value=008.gif name=face><IMG 
			src="<%=request.getContextPath()%>/images/bbs/face/008.gif" width="40" height="40"><INPUT 
            type=radio value=009.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/009.gif" width="40" height="40"><INPUT 
            type=radio value=010.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/010.gif" width="40" height="40"><INPUT 
            type=radio value=011.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/011.gif" width="40" height="40"><INPUT 
            type=radio value=012.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/012.gif" width="40" height="40"><INPUT 
            type=radio value=013.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/013.gif" width="40" height="40"><INPUT 
            type=radio value=014.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/014.gif" width="40" height="40"><br><INPUT 
            type=radio value=015.gif name=face><IMG 
            src="<%=request.getContextPath()%>/images/bbs/face/015.gif" width="40" height="40"></td>
            <td class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>用户签名: <br>&nbsp;&nbsp;
                        <textarea name="underWrite" rows=5 cols=50 class="blue2-12"><%
					 if(usermsgVO.getUnderwrite()!= null){
					 out.print(usermsgVO.getUnderwrite());}
					  %></textarea>
                        </B></td>
                    </tr>
                    
                    <tr>
								<td bgcolor="#FFFFFF" colspan=3 align="left" class="blue2-12">
								<div align="center"><input type="button" name="Submit" value="提　交　修  改" onclick="javascript:_editUser('<%=request.getContextPath()%>','<%=usermsgVO.getUserid()%>','<%=currUserId%>')">
								</td>
					</tr>
                  </table></td>
              </tr>
          </table></td>
          
        </tr>
      </table>  
    </td>
  </tr>
   
</table>
</FORM>
</body>
</html>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("editUserMsg.jsp");
		}
	} catch (Exception e) {
	}
}
%>
<SCRIPT LANGUAGE="JavaScript">

function _editUser(url,userId,currUserId){
	document.form1.action=url+"/servlet/EditUserInfoServlet?userId="+userId+"&currUserId="+currUserId;
	document.form1.submit();

}

</SCRIPT>
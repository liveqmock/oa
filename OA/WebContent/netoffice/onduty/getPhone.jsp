<%@ page contentType="text/html; charset=gb2312" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler" %>
<%@ page import="com.icss.oa.phonebook.vo.PhoneJobnameVO" %>
<%@ page import="com.icss.oa.phonebook.vo.PhoneInfoVO" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<html>
<head>
<title>查看值班人电话信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">
<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
<%
     //只有一条记录
     List phonelist = (List)request.getAttribute("phoneList");
     if(phonelist!=null&&phonelist.size()>0){
         Connection conn = null;
         try {
	         conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
		    ConnLog.open("getPhone.jsp");
		     PhoneHandler pHandler = new PhoneHandler(conn);
		     PhoneInfoVO pVO = (PhoneInfoVO)phonelist.get(0);
%>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">&nbsp;&nbsp;<%=pVO.getUsername()%>的电话簿信息</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg">
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="30%" height="22" class="text-01">&nbsp;<B>所属名称：</B><%=pVO.getUsername()%></td>
                      <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                      <td width="70%" class="text-01">&nbsp;<B>电话职能：</B>&nbsp;<font color="red"><%if(pVO.getFunction()!=null){ out.print("值班电话");}else{ out.print("非值班电话");}%></font></td>         
                    </tr>
                    <tr> 
                      <td height="2" colspan="3" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    </tr>
                    <tr>
                      <td height="22" bgcolor="F2F9FF" ><span class="text-01">&nbsp;<B>所属组织：</B><%=pHandler.GetOrgName(pVO.getOrguuid())%></span></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td bgcolor="F2F9FF" class="text-01">&nbsp;<B>职&nbsp;&nbsp;&nbsp;&nbsp;务：</B>&nbsp;
                          <%
                             String nameids = pVO.getNameids();
                             StringBuffer sbf = new StringBuffer();
                             if(nameids!=null){
                                 StringTokenizer namelist = new StringTokenizer(nameids,",");
                                 int t = 0;
                                 while (namelist.hasMoreTokens()) {
                                     Integer nameID = new Integer(namelist.nextToken());
                                     PhoneJobnameVO pjVO = pHandler.getOrgjobVO(nameID);
                                 	 String jobName = pjVO.getName();
                                     if(t>0){
                                         sbf.append(",");
                                     }
                                     sbf.append(jobName);
                                     t++;
                                 }
                             }else{
                                 sbf.append("");
                             }
                             out.print(sbf.toString());
                          %>
                       </td>
                    </tr>
                    <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    </tr>
                    <tr> 
                      <td height="22" class="text-01">&nbsp;<B>办公电话：</B><%if(pVO.getOfficephone()!=null) out.print(pVO.getOfficephone());%></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td height="22"><span class="text-01">&nbsp;<B>办公室地址：</B><%if(pVO.getOfficeaddress()!=null) out.print(pVO.getOfficeaddress());%></span></td>
                    </tr>
                    <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    </tr>
                    <tr> 
                      <td height="22" bgcolor="F2F9FF" class="text-01">&nbsp;<B>移动电话：</B>&nbsp;<%if(pVO.getMobilephone()!=null) out.print(pVO.getMobilephone());%></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td bgcolor="F2F9FF" class="text-01">&nbsp;<B>家庭电话：</B>&nbsp;<%if(pVO.getHomephone()!=null) out.print(pVO.getHomephone());%></td>
                    </tr>
                    <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    </tr>
                    <tr> 
                      <td height="22" class="text-01"><b>&nbsp;宽带电话：</b><%if(pVO.getNetphone()!=null) out.print(pVO.getNetphone());%></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td class="text-01">&nbsp;<b>文传电话：</b><%if(pVO.getFaxphone()!=null) out.print(pVO.getFaxphone());%></td>
                    </tr>
                    <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr>
                    <tr> 
                      <td height="22" bgcolor="F2F9FF" class="text-01">&nbsp;<B>最后维护人：</B><%if(pVO.getMaintanperson()!=null) out.print(pVO.getMaintanperson());%></td>
                      <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td height="22" bgcolor="F2F9FF" class="text-01">&nbsp;<B>最后维护时间：</B>&nbsp;<%if(pVO.getLastmaintantime()!=null) out.print(com.icss.oa.util.CommUtil.getTime(pVO.getLastmaintantime().longValue()));%></td>
                    </tr>
                    <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    </tr>
                  </table>
                  </td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"> 
            
            </div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
      </table>
	<%
	    } catch (DBConnectionLocatorException e) {
		    e.printStackTrace();
	    } finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("getPhone.jsp");
					}
			} catch (Exception e) {
		    }
	    }
	  }else{  //if
    %>
      <br><br><br><div align="center"><font color=red>没有找到<%=request.getParameter("personName")%>的电话簿信息</font></div>
    <%}%>
    </td>
  </tr>
</table>
</body>
</html>

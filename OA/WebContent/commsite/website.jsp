<!-- /commonsite/website.jsp -->
<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.commsite.vo.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>

<HTML><HEAD><TITLE>常用站点</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>

<BODY text=#000000 leftMargin=0 background="<%=request.getContextPath()%>/images/bg-08.gif" topMargin=10>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
    <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">
    新华社内部网常用站点</td>
    <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<%
	List list = (List)request.getAttribute("list");
	Iterator it = list.iterator();
%>
  <tr>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
    <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr valign="top"  bgcolor="#D8EAF8">
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">				  
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="2" rowspan="7" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                <td width="34%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">						  
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">							  
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
              </tr>
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
              </tr>
              <tr valign="top" bgcolor="#F2F9FF";>               
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                    </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">					  
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
				</td>
                <td width="34%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
              </tr>
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
              </tr>
              <tr valign="top" bgcolor="#D8EAF8">
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="34%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
              </tr>
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
              </tr>
              <tr bgcolor="#F2F9FF"; valign="top">
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="34%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="58" hspace="10" vspace="10" border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
                <td width="33%" height="100" valign="top" align="center">
<% 
	if(it.hasNext()){
		CommsiteListVO vo = (CommsiteListVO)it.next();
%>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr align="center" bgcolor="FBFBEE">
                      <td height="22" colspan="2"><div align="left" class="title-04"><img src="<%=request.getContextPath()%>/images/green-arrow.gif" width="8" height="8" hspace="3"><a href="<%=vo.getOcsLink()%>"><%=vo.getOcsName()%></a></div></td>
                      </tr>
                    <tr align="center">
                      <td width="47%"><a href="<%=vo.getOcsLink()%>">	
					 <img src="DownLoadWebPageServlet?ocsId=<%=vo.getOcsId()%>" alt="<%=vo.getOcsLink()%>" width="126" height="56"  border="0">
					  </a></td>
					  
                      <td width="53%" align="left" valign="top" class="unnamed1"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td>&nbsp;&nbsp;<%=vo.getOcsDes()%></td>
                        </tr>
                      </table>
					  </td>
                    </tr>
                  </table>
<%
	}
%>
                </td>
              </tr>
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
  </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
    <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
    <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
  </tr>
</table>
</body>
</html>

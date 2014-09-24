<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%
Collection intendworkCollection = (Collection)request.getAttribute("intendwork");
Iterator intendworkIterator = intendworkCollection.iterator();
%>
<html>
<body  background="<%=request.getContextPath()%>/images/top_images/bg.gif">
<BODY>
<table width="200" height="41"  border="0" align="right" cellpadding="0" cellspacing="0">
       <tr>
         <td valign="top"><marquee direction= "up" width="220" height="41" scrollamount="1" scrolldelay="0" class="marquee" id=xiaoqing  onmouseout=this.start() 
            onmouseover=this.stop()>
<%
while(intendworkIterator.hasNext()){
	OfficePendingVO officePendingVO = (OfficePendingVO)intendworkIterator.next();
%>		
	   <A HREF="/bbs/servlet/ShowTopicServlet?boardId=3&primeFlag=0" target=mainFrame class=marquee>·网站建设动态最新消息</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame class=marquee>·外汇局公布2003年末我国外债数据</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·群众参与的广播电视直播节目须延</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·非公有制企业参加三险比例低</A><br>
	  <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·八成行政事业性收费纳入预算管理</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·外汇局公布2003年末我国外债数据</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·群众参与的广播电视直播节目须延</A><br>
	  <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·八成行政事业性收费纳入预算管理</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·外汇局公布2003年末我国外债数据</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>·群众参与的广播电视直播节目须延</A>
<%
}//end while
%>
           </marquee>
         </td>
       </tr>
     </table>
</BODY>

</body>
</html>
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
	   <A HREF="/bbs/servlet/ShowTopicServlet?boardId=3&primeFlag=0" target=mainFrame class=marquee>����վ���趯̬������Ϣ</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame class=marquee>�����ֹ���2003��ĩ�ҹ���ծ����</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>��Ⱥ�ڲ���Ĺ㲥����ֱ����Ŀ����</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>���ǹ�������ҵ�μ����ձ�����</A><br>
	  <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>���˳�������ҵ���շ�����Ԥ�����</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>�����ֹ���2003��ĩ�ҹ���ծ����</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>��Ⱥ�ڲ���Ĺ㲥����ֱ����Ŀ����</A><br>
	  <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>���˳�������ҵ���շ�����Ԥ�����</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>�����ֹ���2003��ĩ�ҹ���ծ����</A><br>
      <A HREF="/oa/html/userweb/web_daiban.html" target=mainFrame  class=marquee>��Ⱥ�ڲ���Ĺ㲥����ֱ����Ŀ����</A>
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
<%@ page contentType="text/html; charset=GBK" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%

Collection scollection = (Collection)request.getAttribute("userArticleList");
String currUserId = (String )request.getAttribute("currUserId");
BbsBoardVO boardVO = (BbsBoardVO)request.getAttribute("boardVO");
//out.print(boardVO);
%>
<HTML><HEAD><TITLE>新华社论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="/Style/css_grey.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>

</head>

<body background="">
<form name="form1" method="post" action="">
<%@ include file= "/include/top.jsp" %>

<DIV align=center>
  <div align="center">
  <TABLE width="95%" border=0 cellPadding=0 cellSpacing=0>
  <tr><td height="5"></td></tr>
  </TABLE>
    <TABLE width="95%" border=0 cellPadding=1 cellSpacing=1 bgcolor="#62a1c2" class="text-01">
      <TBODY>
        <TR> 
          <TD align=left bgColor=#f7f7f7 class="blue3-12-b" height=23>
           <img src="<%=request.getContextPath()%>/images/bbs/bbshome.gif" style="cursor:hand" onClick="javascript:_gohome('<%=request.getContextPath()%>');" title="到论坛首页">
           <a href="<%=request.getContextPath()%>/servlet/ShowIndexServlet" title="到论坛首页">论坛首页</A> ==&gt; <A  class="text-01" 
      href="#"onClick="javascript:_topic('<%=request.getContextPath()%>','<%=boardVO.getBoardid()%>')"><%=boardVO.getBoardname()%></A> ==&gt; 
      <a>帖子搜索</a></TD>
        </TR>
      </TBODY>
    </TABLE>
	<TABLE width="95%" border=0 cellPadding=0 cellSpacing=0>
  <tr><td height="5"></td></tr>
  </TABLE>
    
  </div>
  
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
    <tr>
      
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr  bgcolor="#FFFBEF"> 
                  <td width="18%" height="22" class="block_title" align="center">作者</td>
                  
                  <td width="50%" class="block_title" align="center">主题</td>
                  <td width="14%" class="block_title" align="center">回复数</td>
                  <td width="18%" class="block_title" align="center">最新回复</td>
                </tr>
                <%
if(scollection != null){
int showFlag = 1;
Iterator Iterator = scollection.iterator();
while(Iterator.hasNext()){
	ArticleUserinfoVO vo = (ArticleUserinfoVO)Iterator.next();
	  if(showFlag == 1){
%>
                
                <%} showFlag ++;%>
                <%if(showFlag%2==1){%>
                <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
                  <%}else{%>
                <tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" > 
                  <%}%>
                  <td height="22" class="message_title"><div align="center"><A href="#"onClick="javascript:_open('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=vo.getUserid()%>&currUserId=<%=currUserId%>')"  class="text-01"><%=vo.getTurename()%></A></div></td>
                  <td  class="message_title"><div align="center"><A		 href="#"onClick="javascript:_open('<%=request.getContextPath()%>/servlet/ShowSearchArticleServlet?topicId=<%=vo.getArticleid().toString()%>&boardId=<%=vo.getBoardid().toString()%>&hitFlag=<%=vo.getTopic()%>&userId=<%=currUserId%>')"  class="text-01"><%=vo.getArticlename()%> 
                      </A></div></td>
                  <td  class="message_title"><div align="center"><%=vo.getRenum()%> </div></td>
                  <td  class="message_title"><div align="center">
                      <%
			if(vo.getReuserid() != null){	  
			out.print(vo.getReusername()); }
		%>
                      　</div></td>
               
                </tr>
                
                <%
}//while		
  }//if
%>
              </table></td>
          </tr>
      </table></td>
      
    </tr>
  </table>
  
</form>
<center></center>

</body>

</html>
<script language="JavaScript">
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}

function _open(url){
window.open(url,"","");
}

 function _article(url,topicId,boardId,hitFlag){
	document.form1.action=url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag="+hitFlag;
    document.form1.submit();
 }


 function _topic(url,boardId){
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }
</script>		
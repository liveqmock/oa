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
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.log.handler.LogHandler" %>
<%@ page import="com.icss.oa.log.vo.*" %>
<%
//System.out.println("++++++++++logmsglist++++++");
List list = (List)request.getAttribute("logList");

Map attachmap = (Map)request.getAttribute("attachmap");

String parentId = request.getParameter("parentId")==null?"-1":request.getParameter("parentId").toString();
System.out.println("++++++++++logmsglist++++++"+parentId);
CurrentUser user = new CurrentUser();

%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>
<SCRIPT src="index_files/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>
</HEAD>

<body background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
<form name="form1" method="post" action="">
  
<DIV align=center>
      <!-- <div align="center">
          <TABLE width="95%" border=0 cellPadding=1 cellSpacing=1 bgcolor="#62a1c2" class="text-01">
            <TBODY>
              <TR> 
                <TD width="70%" height=23 align=left bgColor=#f7f7f7 class="text-01" > 欢迎
                  
                </TD>
                <TD width="30%" align=left bgColor=#f7f7f7 class="text-01"></TD>
              </TR>
            </TBODY>
          </TABLE>
        </div>
		<br> -->
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"  class="title-05">资源列表</td>
      <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr  bgcolor="#FFFBEF">
                <td width="5%" height="22">
                  <div align="center"> 序号 </div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <!-- <td width="5%" ><div align="center" class="title-04">分类</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                 -->
                <td width="30%" ><div align="center" class="title-04">主题</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="15%" ><div align="center" class="title-04">说明</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td  ><div align="center" class="title-04">内容</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="10%" ><div align="center" class="title-04">上载时间</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="25%" height="22"><div align="center" class="title-04">附件</div></td>
              </tr>
			 <%
if(list != null&&list.size()>0){
int showFlag = 0;
Iterator Iterator = list.iterator();
while(Iterator.hasNext()){
	LogMsgVO vo = (LogMsgVO)Iterator.next();
%>
              <tr>
                <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	          <!--  <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td> -->
                <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
           	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	       <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	        <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	         <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
             </tr>
	<% showFlag ++;%>
             <%if(showFlag%2==1){%>
                <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';"  onMouseOver="this.bgColor='#8CC0E8';"  > 
<%}else{%>
<tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" >
	<%}%>
                <td height="22" class="text-01"><%=showFlag %><div align="center">
				<!-- <td  class="text-01"><div align="center"><%=vo.getSysId() %></div></td> -->
                <td  class="text-01"><div align="left">
                <a href="<%=request.getContextPath()%>/servlet/LogDetailServlet?sysid=<%=vo.getSysId() %>&logid=<%=vo.getLogId() %>"> <%=vo.getLogPheno() %> </a> 
                <%if(vo.getLogPerson().toString().equals(user.getId().toString())){ %>
                <img src="<%=request.getContextPath()%>/images/del.gif" onclick="javascript:_deletemsg('<%=parentId %>','<%=vo.getLogId() %>')">
                <%} %> </div></td>
                <td  class="text-01"><div align="center"><%=vo.getLogReason() %>
               
                </div></td>
                <td  class="text-01"><div align="center"><%=vo.getLogAnalyse() %></div></td>
                <td  class="text-01"><div align="center"><%=CommUtil.getTime(vo.getLogTime().longValue())%></div></td>
                <td height="22" ><div name="divtest"   align="center" class="text-01" >
                <%if(attachmap.containsKey(vo.getLogId())){
                	List attachlist=(List) attachmap.get(vo.getLogId());
                	Iterator attachitr=attachlist.iterator();
                	%>
                <%while(attachitr.hasNext()){
                	LogAccessoryVO attachvo=(LogAccessoryVO) attachitr.next();
                	
                	%>
                	<a href="<%=request.getContextPath()%>/servlet/DownloadLogAttachServlet?attachid=<%=attachvo.getAccessoryId() %>"><%=attachvo.getAccessoryName() %></a><br>
                <%}} %>
                </div></td>
              </tr>
<%
		}//while		
  	}     //if
%>
              <tr>		
                 <!-- <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td> -->
	             <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
           	 	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	       		 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	        	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	         	 <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
             </tr>            
		</table></td>  
          </tr>
      </table></td>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
    </tr>
  </table>
  <!-- 翻页 begin -->
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="1%"><img src="<%=request.getContextPath()%>/images/folder/bg-21.jpg" width="10" height="21"></td>
              <td width="70%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"> 
                <%@ include file= "/include/defaultPageScrollBar.jsp" %>
              </td>
              <td width="27%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          <!-- 翻页 end -->
</form>

<p>
<%if(!"-1".equals(parentId)){ %>
	 <img src="<%=request.getContextPath()%>/images/botton-add.gif" onclick="javascript:_addlogmsg('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;
     <!-- <img src="<%=request.getContextPath()%>/images/botton-delete.gif"   onclick="javascript:_dellogmsg('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> -->
<%} %>                 
</p>

</body>

</html>


<script language="JavaScript">
 function _addlogmsg(url,parentId){

	
	form1.action=url+"/log/addlogmsg.jsp?sysid="+parentId;
	
    form1.submit();
	
 }
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}
function _deletemsg(sysid,logid){
  document.location.href = "<%=request.getContextPath()%>/servlet/DeleteLogMsgServlet?sysid="+sysid+"&logid="+logid;
}
function showattach(logid){
	var xmlHttp = XmlHttp.create();
	
	xmlHttp.open("GET", "<%=request.getContextPath()%>/ShowLogAttachServlet?logid="+logid, false);	
	
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState == 4) {
			var result = xmlHttp.responseText;
			if (result==""||result==null){
			//alert("test1");
				divtest.innerHTML = xmlHttp.responseText;
			}else{
			//alert("test2"+result);
			
				divtest.innerHTML = xmlHttp.responseText;
				//alert(xmlHttp.responseText);
			}
		}
		
	};
	xmlHttp.send(null);
	//alert("test3");
}
function _openUserMsg(url){
window.open(url,"","");
}

function _showTopic(url){

	boardId = boardTopic.value;
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }

function _article(url,topicId,boardId){
	document.form1.action=url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1";
    document.form1.submit();
 }
 
 function _applyBoard(url){
 	//加一个参数表示为申请板块
 	window.open(url+"/bbs/newBoard.jsp?isApply=1","applyboard","width=550,height=350,left=170,top=110,scrollbars=yes");
 
 }
</script>
<%@ page contentType="text/html; charset=gb2312"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.log.handler.LogHandler" %>
<%@ page import="com.icss.oa.log.handler.CommonHandler" %>
<%@ page import="com.icss.oa.log.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>

<%
String sysid = request.getParameter("sysid") == null ? "-1"
					: request.getParameter("sysid");
String logid = request.getParameter("logid") == null ? "-1"
		: request.getParameter("logid");
List loglist = (List)request.getAttribute("loglist");	
List replylist = (List)request.getAttribute("replylist");	
List attachlist = (List)request.getAttribute("attachlist");
Iterator logit=loglist.iterator();
Iterator replyit=replylist.iterator();
Iterator attachitr=attachlist.iterator();
CommonHandler commonhandler=new CommonHandler();
CurrentUser user = new CurrentUser();



%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/include/style.css">
<SCRIPT language=JavaScript	src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
 <script language="JavaScript">
function _reply(sysid,logid){
	form.action="<%=request.getContextPath()%>/servlet/AddLogReplyServlet?sysid="+sysid+"&logid="+logid;
	form.submit();
}
function _return(){
	form.action="<%=request.getContextPath()%>/servlet/ShowLogListServlet?parentId="+<%=sysid%>;
	form.submit();
}
function _deletereply(sysid,logid,replyid){
  document.location.href = "<%=request.getContextPath()%>/servlet/DeleteReplyServlet?sysid="+sysid+"&logid="+logid+"&replyid="+replyid;
}
 </script>

</HEAD>
<body background="<%=request.getContextPath()%>/images/grid.gif">
<FORM name=form action=""  method=post>
	 
	
<%if(logit.hasNext()){
		LogMsgVO vo=(LogMsgVO)logit.next();
%>
<input name="logid" type="hidden" value="<%=logid%>"> 
<input name="sysid" type="hidden" value="<%=sysid%>"> 							
<TABLE  cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TR>
    <TD  background=<%=request.getContextPath()%>/images/bg-08.gif>
	      	<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
	        
	        <TR>
	          <TD width="1%" background=<%=request.getContextPath()%>/images/bg-12.gif><IMG height=22             src="../images/bg-10.gif" width=14></TD>
	          <TD class=title-05 width="97%"             background=<%=request.getContextPath()%>/images/bg-12.gif>主题:<%=vo.getLogPheno() %></TD>
	          <TD width="1%"> <IMG height=22 src="../images/bg-11.gif"             width=20></TD>
			</TR>
		 	
		 	</TABLE>
	  
	      	<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
	        <TR>
	          <TD width=1 background=<%=request.getContextPath()%>/images/bg-21.gif><IMG height=4    src="<%=request.getContextPath()%>/images/bg-21.gif" width=1></TD>
	          <TD width="100%">
		          	  <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center   border=0>
		              <TR>
		              <TD background=<%=request.getContextPath()%>/images/bg-09.jpg>
								<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
			                    <TR bgColor=#FFFBEF>
			                      <TD class=text-01 vAlign=top width="100" height=22>
			                        <DIV align=center>发表的主题:</DIV></TD>
			                      <TD width=1 background=<%=request.getContextPath()%>/images/bg-21.gif rowspan=100><IMG height=4    src="<%=request.getContextPath()%>/images/bg-21.gif" width=1></TD>
			                      <TD class=text-01 vAlign=top  ><%=vo.getLogPheno() %>				  </TD>
			                    </TR>
			                    <TR>
			                      <TD class=text-01 background=<%=request.getContextPath()%>/images/bg-13.gif 
			                      height=2></TD>
			                      <TD background=<%=request.getContextPath()%>/images/bg-13.gif></TD>
			                    </TR>
			                    <TR>
			                      <TD class=text-01 height=50 >
			                      <Table>
			                      <TR  >
			                      	<TD><DIV class=text-01 align=center>内容:</DIV></TD>
			                      	<TD rolspan=2></TD>
			                      </TR>
			                      <TR><TD  style="FILTER: glow(color=none,strength=2)">
			                      签名：<%=commonhandler.getPerson_name(vo.getLogPerson()) %></TD></TR>
			                      </Table>
			                      </TD>
			                      <TD><DIV align=left><%=vo.getLogAnalyse() %></DIV>
			                      </TD>
			                    </TR>
			                    <TR>
			                      <TD background=<%=request.getContextPath()%>/images/bg-13.gif height=2></TD>
			                      <TD class=text-01 background=<%=request.getContextPath()%>/images/bg-13.gif 
			                      bgColor=#f7f7f7 height=2></TD>
			                    </TR>
			                    <%if(attachitr.hasNext()){ %>
			                    <TR bgColor=#f2f9ff>
			                      <TD height=22>
			                        <DIV class=text-01 align=center>附件:</DIV></TD>
			                      <TD class=text-01 height=22>
			                        <DIV align=left>
			                        <%while(attachitr.hasNext()){
			                        	LogAccessoryVO attachvo=(LogAccessoryVO) attachitr.next();
			                        	
			                        	%>
			                        	<a href="<%=request.getContextPath()%>/servlet/DownloadLogAttachServlet?attachid=<%=attachvo.getAccessoryId() %>"><%=attachvo.getAccessoryName() %></a>&nbsp;&nbsp;
			                        	
			                        <%} %>
			                        </DIV></TD></TR>
			                    <TR>
			                      <TD background=<%=request.getContextPath()%>/images/bg-13.gif height=2></TD>
			                      <TD class=text-01 background=<%=request.getContextPath()%>/images/bg-13.gif 
			                      bgColor=#f7f7f7 height=2></TD>
			                    </TR>
			                    <%} %>
			                  	</TABLE>
						<%
						
						while(replyit.hasNext()){
									LogReplyVO replyvo=(LogReplyVO)replyit.next();
									
									
									
			%>
							<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		                   	
		                    <TR bgColor=#FFFBEF>
			                      <TD class=text-01 vAlign=top width="100" height=22>
			                        <DIV align=center>回复:</DIV></TD>
			                      <TD width=1 background=<%=request.getContextPath()%>/images/bg-21.gif rowspan=100 >
			                      <IMG height=4    src="<%=request.getContextPath()%>/images/bg-21.gif" width=1></TD>
			                      <TD class=text-01 vAlign=top  ><%=replyvo.getReplyTitle() %>				  </TD>
			               </TR>
			                <TR>
			                      <TD class=text-01 background=<%=request.getContextPath()%>/images/bg-13.gif 
			                      height=2></TD>
			                      <TD background=<%=request.getContextPath()%>/images/bg-13.gif></TD>
			                    </TR>
			               <TR>
			                      <TD class=text-01 height=50 >
			                      <Table>
			                      <TR  >
			                      	<TD><DIV class=text-01 align=center>回复内容:</DIV></TD>
			                      	<TD rolspan=2></TD>
			                      </TR>
			                      <TR><TD  style="FILTER: glow(color=none,strength=2)">
			                      签名：<%=commonhandler.getPerson_name(replyvo.getReplyPerson()) %>
			                      <%if(replyvo.getReplyPerson().toString().equals(user.getId().toString())){ %>
                <img src="<%=request.getContextPath()%>/images/del.gif" onclick="javascript:_deletereply('<%=sysid %>','<%=vo.getLogId() %>','<%=replyvo.getReplyId() %>')">
                <%} %>
			                      
			                      </TD></TR>
			                      </Table>
			                      </TD>
			                      <TD class=text-01 height=50>
			                        <DIV align=left><%=replyvo.getReplyContent() %></DIV></TD></TR>
			                <TR>
		                    <TR>
		                      <TD background=<%=request.getContextPath()%>/images/bg-13.gif height=2></TD>
		                      <TD class=text-01 background=<%=request.getContextPath()%>/images/bg-13.gif                       bgColor=#f7f7f7 height=2></TD>
		                    </TR>
		                   
		                   	</TABLE>
		 <%
							  }%>
		                  
		            	</TD>
		            	</TR>
		            	</TABLE>
				</TD>
	          	<TD width=1 background=<%=request.getContextPath()%>/images/bg-22.gif><IMG height=4    src="<%=request.getContextPath()%>/images/bg-21.gif" width=1></TD>
	        </TR>
			</TABLE>
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
	</TD>
	</TR>
	</TABLE>
<br>
<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
			
		<tr>
		<td>
		  <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
			  <TD width="2%" background='../images/bg-12.gif'><IMG height=22 src="../images/bg-10.gif" width=14></TD>
			  <TD class=title-05 background='../images/bg-12.gif'>[类型信息] </TD>
			  <TD width="1%" align="right"><IMG height=22 src="../images/bg-11.gif" width=20></TD>
			</TR>
		</TABLE>
		</td>
  		</tr>
		<tr>
    	<td class="dot-border">
     	 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
			<tr bgcolor="#f2f9ff">
          		<td width="17%" height="35" align="center"  class="dot">回复主题</td>
          		<td width="65%" height="35" align="left"  class="dot">&nbsp;
            	<input name="replytitle" type="text" class = "txt3"  size="50"  formTitle="回复主题">            </td>
          		<td width="18%" height="35" align="left"  class="dot-right">&nbsp;<img src="../images/more.gif" width="10" height="12">&nbsp;</td>
        	</tr>
        	<TR bgColor=#f2f9ff ; >
           		<TD width="17%" height="22" align=center noWrap class=dot>回复内容</TD>
           		
           		<TD width="65%" align=left noWrap class=dot>
           		<span class="text-01">   &nbsp;
				<textarea name="replycontent" cols="43" rows="6"></textarea>
             	</span></TD>
           		<TD width="18%" align=left valign="top" noWrap class=dot-right>&nbsp;<img src="../images/more.gif" width="10" height="12">&nbsp;</TD>
         	</TR>		

    	</table>
		</td>
		</tr>
		<tr>
    		<td>
      		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
       		 <TR>
          		<TD width="1%" background="../images/bg-23.jpg"><IMG height=21 src="../images/bg-21.jpg" width=10></TD>
          		<TD class=text-01 width="97%" background="../images/bg-23.jpg">
          		</TD>
          		<TD width="2%" background="../images/bg-23.jpg" align="right"><IMG height=21 src="../images/bg-22.jpg" width=11></TD>
        	</TR>
    		</TABLE></td>
 	    </tr>
  
		</table>
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
	    <td height="50" align="center">
	      <table width="500" height="100%"  border="0" cellpadding="0" cellspacing="0">
	        <tr align="center" valign="middle">
	          
	          <td width="49%"><a href="javascript:_reply('<%=sysid %>','<%=logid %>')"><img src="../images/botton-reply.gif" width="70" height="22" border="0"></a></td>
	          <td width="1%">&nbsp;</td>
	          
	          <td width="50%"><a href="javascript:_return()"><img src="../images/botton-return.gif" width="70" height="22" border="0"></a></td>
	                 
	        </tr>
	    </table>
	    </td>
  	</tr>
</table>

</FORM>
 <%}%> 
</body>

</html>

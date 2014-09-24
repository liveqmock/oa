<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.meeting.vo.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</style>

<script language="JavaScript">

function _addtoShedule(){

			document.form_update.action="<%=request.getContextPath()%>/servlet/ShowScheduleServlet";
			document.form_update.submit();
}

function _Baoming(){

			document.form_update.action="<%=request.getContextPath()%>/servlet/BaomingServlet";
			document.form_update.submit();
}
</script>
</head>
<% 
   MeetingPersonmeetVO vo = (MeetingPersonmeetVO)request.getAttribute("vo");
   %>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
 <form name="form_update" action="" method="post">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 
        [<%=vo.getMeetingName()%>]的信息</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
  </table>
	  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                   <tr>
                      <td width="100" height="22" class="text-01" align="right">会议名称：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <%= vo.getMeetingName()%></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议类型：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <%= vo.getMeetingType()%>
                       </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                   <tr>
                      <td width="100" height="22" class="text-01" align="right">会议开始时间：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                      <%=new SimpleDateFormat("yyyy-MM-dd").format(new Date(vo.getStarttime().longValue()))%>
                	  <%= vo.getStart1()%>
                      </td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议结束时间：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <%=new SimpleDateFormat("yyyy-MM-dd").format(new Date(vo.getEndtime().longValue()))%>
					  <%= vo.getEnd1()%>
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td width="110" height="22" class="text-01" align="left">会议申请(联系人)：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                      <%
                         EntityManager entityManager = EntityManager.getInstance();
	                     Person person = entityManager.findPersonByUuid(vo.getMeetingPutperon()); %>
                      <%= person.getFullName()%>&nbsp;&nbsp;&nbsp;联系电话：<%= person.getOfficetel()%>
                	  
                      </td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">会议申请部门：</div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <%= vo.getMeetingPutdep()%>
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td width="100" height="22" class="text-01" align="right">申请的会议室：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                	  <%= vo.getMeetingRoom()%></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td width="100" class="text-01"><div align="right"></div></td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">	         
                      </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td width="100" height="22" class="text-01" align="right">服务要求：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01" valign="top"><textarea cols="40" rows="5" readonly><%= vo.getMeetingUtil()!=null?vo.getMeetingUtil():""%></textarea>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td width="100" height="22" class="text-01" align="right">会议概要：</td>
                      <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td  bgcolor="F2F9FF" class="text-01" valign="top"><textarea cols="40" rows="5" readonly><%= vo.getMeetingConcept()!=null?vo.getMeetingConcept():""%></textarea></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
              </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
  </table>
	   <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>  
  </table>
	  <div align="center"><br>
	  <img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="window.close()" style="cursor:hand">
  </div>

</form>
</body>
</html>

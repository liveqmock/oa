<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<%@ page import="com.icss.oa.message.vo.MsgBackSearchVO" %>
<%@ page import="com.icss.oa.message.handler.MsgHandler" %>
<HTML><HEAD><TITLE>短信搜索结果列表</TITLE>  
<META http-equiv=Content-Type content="text/html; charset=GBK"><LINK 
href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>
<script language="javascript">
function _showContent(content){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	window.open('<%=request.getContextPath()%>/message/msgContent.jsp?msgContent='+content,'','width=190,height=150,toolbar=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,copyhistory=no,left='+showx+',top='+showy);
}
</script>
<BODY background="<%=request.getContextPath()%>/images/bg-08.gif" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>
    <br>
    	<table align="center" width="95%"><tr><td>
        <table align="center" width="100%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">短信回复列表</td>
            <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="22" nowrap align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;回复人&nbsp;</td>
                        <td width="2" rowspan="100" align="center" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;电话&nbsp;</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;内容概要&nbsp;</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;回复时间&nbsp;</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;接收者&nbsp;</td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td nowrap align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;状态&nbsp;</td>
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
	List list = (List)request.getAttribute("list");
	if(list!=null){
	Iterator it = list.iterator();
	int i=0;
	while(it.hasNext()){
		++i;
		MsgBackSearchVO vo = (MsgBackSearchVO)it.next();
		if(i%2==1){
%>
                    <tr bgColor=#D6E7F7 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D6E7F7';">
<%
		}
		else{
%>
					<tr bgcolor="#F2F9FF"; onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';">
<%
		}
%>
						<td height="22" nowrap align="center" class="text-01">&nbsp;<%=vo.getCnname()%>&nbsp;</td>
						<td nowrap align="center" class="text-01">&nbsp;<%=vo.getMsPhone()%>&nbsp;</td>
                        <td nowrap height="22" align="left" class="text-01">&nbsp;<a href="#" title="<%=MsgHandler.replaceContent(MsgHandler.getContent(vo.getMsContent()),true)%>" onclick="javascript:_showContent('<%=MsgHandler.replaceContent(vo.getMsContent(),false)%>')"><%=MsgHandler.cutContent(MsgHandler.replaceContent(MsgHandler.getContent(vo.getMsContent()),true))%></a>&nbsp;</td>
                        <td nowrap align="center" class="text-01">&nbsp;<%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(vo.getMsDate().longValue())).toString()%>&nbsp;</td>
                        <td nowrap align="center" class="text-01">&nbsp;<%="1".equals(vo.getMsPower())?"是":"否"%>&nbsp;</td>
                        <td nowrap align="center" class="text-01">&nbsp;<%=MsgHandler.getBackMsgModeString(vo.getMsMode())%>&nbsp;</td>
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
	}
	}
%>
                  </table></td>
                </tr>
            </table></td>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
        </table>
        </td></tr></table>
        </center>
    </div>
</body>
</html>

<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ taglib uri="/WEB-INF/cms-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/cms-info.tld" prefix="cms"%>

<%
String poscode = request.getParameter("poscode")==null?"0":request.getParameter("poscode");
String titlename = request.getParameter("titlename")==null?"":request.getParameter("titlename");
String titlenumber = request.getParameter("titlenumber")==null?"0":request.getParameter("titlenumber");
String perpagecount = request.getParameter("perpagecount")==null?"5":request.getParameter("perpagecount");
int intperpagecount = Integer.valueOf(perpagecount).intValue();
String showstyle = request.getParameter("showstyle")==null?"none":request.getParameter("showstyle");
int messagecount=0;
%>


<table width="100%" border="0" cellpadding="0" cellspacing="0" id="<%=titlename%>_block_<%=titlenumber%>" style="display:<%=showstyle%>">
	<cms:position code="<%=poscode%>" id="pos<%=poscode%>" type="none" target="_blank"/>
	<%
	messagecount=0;
	%>
    <cms:infolist name="pos<%=poscode%>" id="info<%=poscode%>" type="sameSite" perPageSize="<%=intperpagecount%>" titleLength="30">
	<%
	  messagecount++;
	%>
    <tr>
		<td width="79%"  align="left" class="message_title" height="24">¡¤<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
        <td width="21%"  align="left" class="message_title" height="24"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info<%=poscode%>" property="date" /></a></span></td>
    </tr>
    </cms:infolist>     
	<%
	if(messagecount<intperpagecount){
		for(int m=messagecount;m<intperpagecount;m++){
	%>
      	<tr><td colspan="3" height="24"></td></tr>
    <%
		}
	}
	%>
    <tr>
		<td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
    </tr>
</table>

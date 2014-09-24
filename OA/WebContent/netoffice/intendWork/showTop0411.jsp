<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%@ page import="com.icss.oa.config.IntendWorkConfig"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
setTimeout("self.location.reload();",120000);
//-->
</SCRIPT>
<html>
<head>

</head>
<body marginwidth="0" marginheight="0" onunload="_unload()" >
<SCRIPT LANGUAGE="JavaScript">
<!--
str = "";
var count = 0;
str+= "<marquee hspace=0 vspace=0 height=\"150\" direction=\"up\" scrollamount=\"2\" scrolldelay=\"0\" onmouseout=\"this.start()\" onmouseover=\"this.stop()\">";
<%
List totalList = (List)request.getAttribute("totalList");
%>
count = <%= totalList.size()%> 
<%
Iterator topIterator = totalList.iterator();
while(topIterator.hasNext()){
    OfficePendingVO officePendingVO = (OfficePendingVO)topIterator.next();
    String showStr = officePendingVO.getOpTopic();
    if(officePendingVO.getOpType().equals(IntendWorkConfig.IMPORT_LOCAL) || officePendingVO.getOpType().equals(IntendWorkConfig.IMPORT_NEW))
    {
%>
    str += "<a href=\"#\" title=\"<%=officePendingVO.getOpTopic()%>\" onclick=\"_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(officePendingVO.getOpUrl())%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>');nframe.location.reload();\" > + <font color=\"red\">[重要]</font><%=showStr%></a><br><br>";
<%
	}else{
%>
    str += "<a href=\"#\" title=\"<%=officePendingVO.getOpTopic()%>\" onclick=\"_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(officePendingVO.getOpUrl())%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>');nframe.location.reload();\" > + <%=showStr%></a><br><br>"
<%
	}
}
%>

if(count==0){str +="您没有任何的待办事宜！";}
str+= "</marquee>";
<%
List Ringlist = (List)request.getAttribute("ringlist");
if(Ringlist !=null && !Ringlist.isEmpty()){
%>
//str+= "<BGSOUND SRC=\"../include/ring.wav\" LOOP=INFINITE>";
str += "<object name=\"wmaplayer\" classid=CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95 codebase=/plugin/nsmp2inf.cab#Version=5,1,52,701 width=0 height=0 id=MIDIPlayer type=application/x-oleobject VIEWASTEXT standby=\"正在加载 Microsoft Windows Media Player 请稍候...\">";
//可以在这里用程序产生随机的音乐文件(处理一下/oabase)
str += "<param name=\"FileName\" value=\"/oabase/include/music/msg.wav\">";
str += "<param name=\"AutoStart\" value=\"true\">";
str += "<param name=\"AutoRewind\" value=\"-1\">";
str += "<param name=\"AnimationAtStart\" value=\"false\">";
str += "<param name=\"ShowControls\" value=\"false\">";
str += "<param name=\"ClickToPlay\" value=\"false\">";
str += "<param name=\"EnableContextMenu\" value=\"true\">";
str += "<param name=\"EnablePositionControls\" value=\"false\">";
str += "<param name=\"Balance\" value=\"0\">";
str += "<param name=\"ShowStatusBar\" value=\"false\">";
str += "<param name=\"AutoSize\" value=\"0\">";
str += "<param name=\"Loop\" value=\"0\">";

str += "<embed type=\"application/x-mplayer2\" filename src autostart=\"false\" enablecontextmenu=\"false\" clicktoplay=\"false\" enablepositioncontrols=\"false\" showcontrols=\"0\" showstatusbar=\"1\" showdisplay=\"0\">";
str += "</object>";
<%
  }
  else{
%>
str+= "&nbsp;";
<%
  }
%>
var localtype = "'1','11'";

parent.intendwork.innerHTML = str;
//-->
</SCRIPT>

</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _unload(){
    parent.intendwork.innerHTML = "<table align=\"center\" width=\"125\" height=\"150\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
                                    +"<tr><td width=\"110\" height=\"150\" align=\"center\" valign=\"top\">";
                                           +"<font color=\"red\">正在装载数据...</font>";
                                        +"</td>";
                                    +"</tr>";
                                 +"</table>";
}
//-->
</SCRIPT>
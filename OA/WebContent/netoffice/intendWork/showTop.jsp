<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%@ page import="com.icss.oa.config.IntendWorkConfig"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
setTimeout("self.location.reload();",3000000);
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
var v =2;

<%
List totalList = (List)request.getAttribute("totalList");

//2005-09-02 lizb �滻�ַ���
org.apache.regexp.RE re = new org.apache.regexp.RE("\"");
org.apache.regexp.RE re1 = new org.apache.regexp.RE("<");
org.apache.regexp.RE re2 = new org.apache.regexp.RE(">");
%>
count = <%= totalList.size()%> 

if(count==0) v =1;

str+= "<marquee hspace=0 vspace=0 height=\"150\" direction=\"up\" scrollamount=\""+v+"\" scrolldelay=\"0\" onmouseout=\"this.start()\" onmouseover=\"this.stop()\">";

<%
Iterator topIterator = totalList.iterator();
while(topIterator.hasNext()){
    OfficePendingVO officePendingVO = (OfficePendingVO)topIterator.next();
    String showStr = officePendingVO.getOpTopic();
	showStr = re.match(showStr)?re.subst(showStr,"\\\""):showStr;
	showStr = re1.match(showStr)?re1.subst(showStr,"&lt;"):showStr;
	showStr = re2.match(showStr)?re2.subst(showStr,"&gt;"):showStr;
	String showStr1 = officePendingVO.getOpTopic();
	showStr1 = re.match(showStr1)?re.subst(showStr1,""):showStr1;
	showStr1 = re1.match(showStr1)?re1.subst(showStr1,"&lt;"):showStr1;
	showStr1 = re2.match(showStr1)?re2.subst(showStr1,"&gt;"):showStr1;
    if(officePendingVO.getOpType().equals(IntendWorkConfig.IMPORT_LOCAL) || officePendingVO.getOpType().equals(IntendWorkConfig.IMPORT_NEW))
    {
%>
    str += "<a href=\"#\" title=\"<%=showStr1%>\" onclick=\"_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(officePendingVO.getOpUrl())%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>');nframe.location.reload();\" > + <font color=\"red\">[��Ҫ]</font><%=showStr%></a><br><br>";
<%
	}else{
%>
    str += "<a href=\"#\" title=\"<%=showStr1%>\" onclick=\"_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(officePendingVO.getOpUrl())%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>');nframe.location.reload();\" > + <%=showStr%></a><br><br>"
<%
	}
}
%>

if(count==0){str +="��û���κεĴ������ˣ�";}
str+= "</marquee>";
<%
List Ringlist = (List)request.getAttribute("ringlist");
if(Ringlist !=null && !Ringlist.isEmpty()){
%>
//str+= "<BGSOUND SRC=\"../include/ring.wav\" LOOP=INFINITE>";
str += "<object name=\"wmaplayer\" classid=CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95 codebase=/plugin/nsmp2inf.cab#Version=5,1,52,701 width=0 height=0 id=MIDIPlayer type=application/x-oleobject VIEWASTEXT standby=\"���ڼ��� Microsoft Windows Media Player ���Ժ�...\">";
//�����������ó����������������ļ�(����һ��/oabase)
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
                                           +"<font color=\"red\">����װ������...</font>";
                                        +"</td>";
                                    +"</tr>";
                                 +"</table>";
}
//-->
</SCRIPT>
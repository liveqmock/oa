<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.MessageHandler" %>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>
<%@page import="com.icss.oa.config.FileTransferConfig"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
	String type = request.getParameter("type");
	String folderSort = request.getAttribute("folderSort")==null?"":(String)request.getAttribute("folderSort");
	List reList = (List)request.getAttribute("listOfMessages");
	Iterator messageIter = null;
	 if(reList!=null)
      {messageIter = reList.iterator();}

	FiletransferSetHandler handler = new FiletransferSetHandler();
	//用户自定义文件夹名
	List userFolderName = (List)request.getAttribute("userFolderName");
	Iterator userFolderNameItr = userFolderName.iterator();

	String typename = "文件夹";    
	String folderName = "";
	if("Inbox".equals(folderSort))          //收件箱
		folderName = "收件箱";
	else if("Sent".equals(folderSort))       //发件箱
		folderName = "发件箱";
	else if("Draft".equals(folderSort))    //草稿箱
		folderName = "草稿箱";
	else if("Junk".equals(folderSort))      //垃圾箱
		folderName = "垃圾箱";
	else                                   //为用户自定义文件夹
		folderName = folderSort;

int _curPageNum = ((Integer)request.getAttribute("curPageNum")).intValue();
//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
String sortname = request.getParameter("sortname");
//排序方式 默认为倒序
String sorttype = request.getParameter("sorttype");

String img_date = "<a border=\"0\" href=\"javascript:_sort('"+_curPageNum+"','0','DSC')\"><img alt=\"点击按日期倒序排序\" border=\"0\" src=\""+request.getContextPath()+"/images/Sort_none.gif\" width=\"9\" height=\"13\" align=\"absmiddle\"></a>";//按时间排序时的图片
String img_size = "<a border=\"0\" href=\"javascript:_sort('"+_curPageNum+"','1','DSC')\"><img alt=\"点击按大小倒序排序\" border=\"0\" src=\""+request.getContextPath()+"/images/Sort_none.gif\" width=\"9\" height=\"13\" align=\"absmiddle\"></a>";//按大小排序时的图片

if("0".equals(sortname)){
	if("DSC".equals(sorttype)){
		img_date = "<a border=\"0\" href=\"javascript:_sort('"+_curPageNum+"','0','ASC')\"><img alt=\"点击按日期升序排序\" border=\"0\" src=\""+request.getContextPath()+"/images/Sort_desc2.gif\" width=\"9\" height=\"13\" align=\"absmiddle\"></a>";//按时间排序时的图片
		
	}else if ("ASC".equals(sorttype)){
		img_date = "<a border=\"0\" href=\"javascript:_sort('"+_curPageNum+"','0','DSC')\"><img alt=\"点击按日期倒序排序\" border=\"0\" src=\""+request.getContextPath()+"/images/Sort_ascend2.gif\" width=\"9\" height=\"13\" align=\"absmiddle\"></a>";//按时间排序时的图片
		
	} 
}else if("1".equals(sortname)){
	if("DSC".equals(sorttype)){
		img_size = "<a border=\"0\" href=\"javascript:_sort('"+_curPageNum+"','1','ASC')\"><img alt=\"点击按大小升序排序\" border=\"0\" src=\""+request.getContextPath()+"/images/Sort_desc2.gif\" width=\"9\" height=\"13\" align=\"absmiddle\"></a>";//按邮件大小排序时的图片		
	}else if ("ASC".equals(sorttype)){
		img_size = "<a href=\"javascript:_sort('"+_curPageNum+"','1','DSC')\"><img alt=\"点击按大小倒序排序\" border=\"0\" src=\""+request.getContextPath()+"/images/Sort_ascend2.gif\" width=\"9\" height=\"13\" align=\"absmiddle\"></a>";//按邮件大小排序时的图片
	}
}




%>

<html>
<head>

<title><%=folderName%></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<script language="JavaScript" src="<%=path%>/include/common.js"></script>
<script language="javascript">
function changeStyle(id){//切换样式
	//alert("dddd");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	document.cookie=name+"="+value+",";
}
function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(",")); 
		var value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(","));
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
}

function CheckAll()
  {
    for (var i=0;i<document.listmail.elements.length;i++)
    {
      var e = document.listmail.elements[i];
      if (e.name != 'allbox')
        e.checked = document.listmail.allbox.checked;
    }
  }

function _toPage(pageno){
    
    if(pageno!='0'){
		window.location.href = "<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=" + pageno + "&type=<%=type%>&folder=<%=folderSort%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
	}
}
   function _openSystemfile(type,folder,mailName){
 
        //window.showAlert.innerHTML = "正在打开该文件，请稍等......";  
	    //弹出正在发送的提示框
		//_showAlertBox('alertbox1','','show');  
	
        document.listmail.action="<%=path%>/servlet/ShowFile1Servlet?type="+type+"&folder="+folder+"&mailName="+mailName+"&curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
        document.listmail.submit();
        //window.top.leftFrame.location.reload();
	
 }

 function _Delete()
{
     if(!listmail.messageid){
		alert("没有邮件！");
        return false;
    }
    if(!IsChecked(listmail.messageid,"请选中邮件！")){
      return false;
    }
    ok=confirm("您要选择永久删除这些文件吗？");
    if(ok){
     document.listmail.action="<%=request.getContextPath()%>/servlet/DelMailServlet?curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
     document.listmail.submit();
     //window.top.leftFrame.location.reload();
     return true;
    }else{
      return false;
    }
}

function _Move()
{
     if(!listmail.messageid){
		alert("没有邮件！");
        return false;
    }
    if(!IsChecked(listmail.messageid,"请选中邮件！")){
        return false;
    }else{
        document.listmail.action="<%=request.getContextPath()%>/servlet/MoveMailServlet?curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
        document.listmail.submit();
       // window.top.leftFrame.location.reload();
        return true;
    }
}

function _ForeverDelete()
{
    ok=confirm("确定要清空垃圾箱吗？");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder=<%=folderSort%>";
        return true;
    }else{
        return false;
    }
}
initstyle();
</script>
</head>

<body>
<form name="listmail" method="POST">
			<input type="hidden" name="mov_action" value="">
			<input type="hidden" name="unread" value="">
			<input type="hidden" name="folder" value="<%=folderSort%>">
			<input type="hidden" name="type" value="<%=type%>">
<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="537" class="top_logo" height="85"></td>
    <td class="top_back" height="85" width="121">&nbsp;</td>
    <td width="325" class="top_back">
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
        	  <td class="top_function" width="390" height="31"></td>
        	</tr>
            <tr>
              <td class="top_left" width="390" height="54"></td>
            </tr>
        </table>    
    </td>
    </tr>
    </table>
    </td>
    <td width="10" >&nbsp;</td>
  </tr>
</table>

<table width="1003" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr>
    	<td width="10"></td>
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="top_back">
            	<tr>
                	<td width="134" class="top_left_buttom" height="29"></td>
                  <td width="60%" class="top_back_buttom">
               	  <table width="98%">
                        <tr>
                            <td width="34%" height="20" class="message_zhuanti"><%=time%></td>
                          <td width="42%" align="right" class="content"><input name="searchfield2" type="text" class="biankuang" size="26" value="请输入检索关键字"/></td>
                          <td width="9%" class="message_zhuanti" style="cursor:hand">&gt;&gt;检索</td>
                          <td width="15%" class="content"><a href="searchList.html" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; 高级检索</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="点击选择页面色彩！" bgcolor="#FFFFFF">
                      <tr>
                        <td width="30"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="red" style="cursor:hand;" onClick="changeStyle('red')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#d0e9cb" style="cursor:hand;" onClick="changeStyle('lake')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ffd4ad" onClick="changeStyle('orange')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ddc99d" onClick="changeStyle('brown')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#afd5ae" onClick="changeStyle('green')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#CCCCCC" onClick="changeStyle('grey')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
          </table>
        </td>
        <td width="10"></td>
    </tr>
</table>
<!--主要内容去区-->


<table width="1003" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="3" rowspan="2" valign="top">
        <TABLE width="180" height="57" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
            <TR>
              <TD width="90" align="right" bgcolor="#FFFFFF">
              <table width="100%" cellspacing="5">
                <tr><td width="36" align="right" style="cursor:hand;">
              <img src="<%=path%>/images/mail_receive.JPG" width="26" height="26" /></TD>
              <TD width="45" align="left" class="message_title_bold" style="cursor:hand;"><a href="<%=path%>/servlet/ShowNoReadServlet" class="message_title_bold" style="text-decoration:none">未读<br />
                邮件</a></TD>
              </tr>
              </table>
              <TD width="90" align="right" bgcolor="#FFFFFF"><table width="100%" cellspacing="5">
                <tr>
                  <td width="36" align="right" style="cursor:hand"><a href="SendMail1.html"><img src="<%=path%>/images/mail_send.JPG" width="26" height="26" border="0" /></a></td>
                  <td width="45" align="left" class="blue-12" style="cursor:hand;"><a href="<%=path%>/servlet/SendFileFileTransferServlet" style="text-decoration:none">写信</a></td>
                </tr>
              </table></TD>
            </TR>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        <TABLE width="180" height="30" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
       	  <tr>
            <td bgcolor="#FFFFFF">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title">搜索文件夹</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="table_title" style="cursor:hand;">&nbsp;<select name="select" style="width:90px;">
                    <option>全部</option>
                    <option>收件箱</option>
                    <option>发件箱</option>
                    <option>草稿箱</option>
                    <option>垃圾箱</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title">是否已读</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="table_title" style="cursor:hand;">&nbsp;<select name="select" style="width:90px;">
                    <option>全部</option>
                    <option>未读邮件</option>
                    <option>已读邮件</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">接收时间从</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="table_title" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="table_title" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">至</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="table_title" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="table_title" style="cursor:hand;"><img src="<%=path%>/images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
            </table>            </td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"  height="30">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="131" align="right" bgcolor="#FFFFFF"><input name="textfield2222" type="text" class="biankuang-blue" size="15" value="输入关键字"/></td>
                  <td width="47" align="center" nowrap="nowrap" bgcolor="#E0EDF8" class="message_title_bold" style="cursor:hand;">搜索</td>
                </tr>
            </table>            </td>
          </tr>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        <table width="180" border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
          <tr>
            <td width="196"><table width="100%" height="170" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="20" align="left"  bgcolor="#E0EDF8" class="block_title" colspan="3"> 文件夹</td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td width="55" align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_inbox.JPG" width="15" height="16" />&nbsp;</td>
                  <td width="134" bgcolor="#FFFFFF" ><a href="MessageListServlet?type=system&folder=Inbox&search=no" class="message_title" style="cursor:hand">收件箱</a><span class="message_title"></span></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_caogao.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Draft&search=no" style="cursor:hand">草稿箱</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_outbox.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no" style="cursor:hand">发件箱</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Junk&search=no" class="text">垃圾箱</a>&nbsp;&nbsp;<span class="message_title"><a href="javascript:_ForeverDelete()">[清空]</a></span></td>
                </tr>
                <tr>
                  <td height="25" colspan="3" bgcolor="#E0EDF8" class="block_title">自定义文件夹&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="BoxListServlet" style="text-decoration:none" class="message_title_bold">[管理]</a></td>
                </tr>
				<%
				  while(userFolderNameItr.hasNext()){
                     String folder = userFolderNameItr.next().toString();
				%>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
	
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=user&folder=<%=handler.decodeBase64(folder)%>&search=no" class="text"><%=handler.decodeBase64(folder)%></a></td>
                </tr>
				<%}userFolderNameItr = userFolderName.iterator();%>
            </table></td>
          </tr>
      </table></td>
      <td headers="11">&nbsp;</td>
      <td width="100" height="30" valign="middle" class="message_title"><select name="targetfolder" style="font-size:18;" onchange="javascript:_Move()">
        <option value="0" selected="selected">转移邮件到</option>
			<% if(!("Inbox".equals(folderSort)))%>          
                <option value="Inbox">收件箱</option>
            <% if(!("Sent".equals(folderSort)))%>       
                <option value="Sent">发件箱</option>
            <% if(!("Draft".equals(folderSort)))%>   
                <option value="Draft">草稿箱</option>
            <% if(!("Junk".equals(folderSort))){%>
                <option value="Junk" >垃圾箱</option>
            <% } %>
			<% while(userFolderNameItr.hasNext()){
                     String folder = userFolderNameItr.next().toString();
                     if(!(folderSort.equals(handler.decodeBase64(folder)))){
              %>
                 <option value="<%=handler.decodeBase64(folder)%>"><%=handler.decodeBase64(folder)%></option>
              <%
                     }
                }
              %>
      </select></td>
      <td width="29" height="30" align="right" valign="middle"><img src="<%=path%>/images/mail_delete.JPG" width="17" height="17" /></td>
      <td width="34" valign="middle" class="foot_message"><a href="javascript:_Delete()">删除</a></td>
      <td width="36" height="30" align="right" valign="middle"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" /></td>
      <td width="144" height="30" valign="middle" class="foot_message"><a href="javascript:_ForeverDelete()" >清空垃圾箱</a></td>
      <td width="438" height="30" align="right" valign="top">
        
        <table>
    	<tr>
        <td valign="top" class="foot_message">总容量100M：已用91M（91%），剩余9M&nbsp;</td>
        <td valign="middle">
        <div style="border-color:#B9DAF9;border-style:solid;border-width:1px;width:150px;height:10px" align="left">
        	<div style="width:91%;background-color:#ee0000;height:10px"></div>
        </div>        </td>
        </tr>
   	  </table>      </td>
    </tr>
    <tr>
    	<td width="5">&nbsp;</td>
        <td width="11" headers="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
      <td colspan="6" valign="top">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
        <tr>
          <td nowrap="nowrap" width="5%" height="26" class="block_title" align="center">
              <input type="checkbox" name="allbox" value="checkbox" onclick="javascript:CheckAll()"/>
            全选</td>
          <td width="5%" class="block_title" align="center">&nbsp;</td>
          <td width="45%" colspan="4" class="block_title" align="center">主题</td>
          <td width="20%" class="block_title" align="center">日期<%out.print(img_date);%></td>
          <td width="15%" class="block_title" align="center">发件人</td>
          <td width="10%" class="block_title" align="center">大小<%out.print(img_size);%></td>
        </tr>
	<%
	int count =0;
	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
		ConnLog.open("messageList.jsp");
		MessageHandler mHandler = new MessageHandler();
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
		if(messageIter!=null){
		if(!messageIter.hasNext()){
	%>
     <tr bgColor='#D8EAF8'>
         <td height="52" class="text-01" colspan=11>
             <div align="center"><br><br>没有邮件！<br><br></div>
         </td>             
     </tr>            
     <tr>
         <td colspan=11 height="2" background="<%=path%>/images/bg-13.gif" class="text-01"></td>
     </tr>
	<%
	}else{
		int i=0;
		while(messageIter.hasNext()){
		List listOfMessages = (List)messageIter.next();
		String[] mailArray = new String[3];
		for(int j=0;j<listOfMessages.size();j++){
			mailArray[j] = listOfMessages.get(j).toString();
		}
		long result = Long.parseLong(mailArray[0]);    //得到邮件大小
		double mailnum = 0;
		String mailMemory = "";  //用于页面显示
		if(result>=1024*1024){
			mailnum = CommUtil.getDivision(result, 1024*1024, 2);
			mailMemory = Double.toString(mailnum)+"MB";
		}else{
			mailnum = CommUtil.getDivision(result, 1024, 2);
			mailMemory = Double.toString(mailnum)+"KB";
		}
		String mailHead = mailArray[1];   //得到邮件头
		String mailName = mailArray[2];   //得到邮件名
		try{
			String mailFrom = ftsHandler.getCName(mHandler.getFrom(mailHead).substring(0,mHandler.getFrom(mailHead).indexOf("@")));
			Date reDate = mHandler.getReceiveDate(mailHead);
			String  mailSubject = mHandler.getSubject(mailHead);
		    String contentType = mHandler.getContentType(mailHead);
			boolean noreadflag=false;
			String secondword = mailName.substring(1,2);
			if(FileTransferConfig.NEW_FLAG.equals(secondword)){   //为未读邮件
				noreadflag=true;
			}
	%>

        <tr>
          <td height="26" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="messageid" value="<%=mailName%>" />
          </div></td>
          <td bgcolor="#FFFFFF"><div align="center"><img src="<%=path%><%out.print(noreadflag==true?"/images/email.gif":"/images/email_open.gif");%>" width="16" height="16" hspace="3" vspace="3" /></div></td>
          <td colspan="4" bgcolor="#FFFFFF" class="message_title_bold" >
		  <%if("system".equals(type)){%>
			<a href="javaScript:_openSystemfile('system','<%=folderSort%>','<%=mailName%>')"  class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>" style="text-decoration:none"><%=mailSubject%></a>
		  <%}else{%>
			<a href="javaScript:_openSystemfile('user','<%=folderSort%>','<%=mailName%>')"  class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>" style="text-decoration:none"><%=mailSubject%></a>
		  <%}%>
		  </td>
          <td bgcolor="#FFFFFF" class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><div align="center"><% if(reDate != null) out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));%></div></td>
          <td bgcolor="#FFFFFF" class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><div align="center"><%=mailFrom!=null?mailFrom:""%></div></td>
          <td bgcolor="#FFFFFF" class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><table border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="blue3-12-b"><%=mailMemory%></td>
                <%if(!(contentType.trim().equals("text/plain"))) {%>
				<td><img src="<%=path%>/images/icon_attachment.gif" width="16" height="16" hspace="5" /></td>
				<%}%>
              </tr>
          </table></td>
        </tr>
  <%
	}catch(Exception ex){
		System.out.println("there is a mail error（一个非法的邮件）....");
	}
		i++;
	}}//end while
	}//else
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	}catch(Exception ex){
		System.out.println(ex.getMessage());
	}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("messageList.jsp");
					}
			} catch (Exception e) {
		}
	}
   %>

        
        <tr>
          <td height="26" colspan="9" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>

				 <%@ include file = "./PageScrollBar0.jsp" %>
              </tr>
          </table></td>
        </tr>
      </table>      </td>
    </tr>
    
    
    <tr>
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
	  
	  
<table width="1013" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="1013" height="54"><div align="center" class="foot_message">多媒体数据库系统│多媒体待编稿库系统│eNews系统│总社新闻编辑系统│国内分社多媒体编辑系统│报刊编辑系统│音频供稿系统│稿件采用查询系统│信息部多媒体采编系统<br />
    全球卫星供稿系统│图书/数字图书系统│通信供稿系统│因特网供稿系统│图片/图表编辑系统│营销管理系统│防病毒管理系统</div></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 版权所有　　<br />
      制作单位：新华社技术局　（浏览本网主页，建议将电脑显示屏的分辨率调为1024*768）<br />
    </div></td>
  </tr>
</table>
</form>
</body>
</html>

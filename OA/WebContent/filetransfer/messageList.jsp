<%@ page contentType="text/html; charset=GBK" %>
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
String type = request.getParameter("type");
//String folderSort = request.getParameter("folder");
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
<HTML><HEAD><TITLE>读邮件</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript" src="../include/common.js"></script>
</head>

<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<div align="left"> 
<form name="listmail" method="POST"  action="/cgi-bin/movemail">

<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
			<tr bgcolor="#EEFFF7">
				<td height="25" align="center" id="showAlert"></td>  
			</tr>
		</table>
</div>
 
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"><a href="<%=request.getContextPath()%>/servlet/BoxListServlet" title="点击回文件夹列表"><font color="white"><%=typename%></font></a>-&gt;<%=folderName%></td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg">
                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="5%" bgcolor="#FFFBEF">&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="5%" align="center" bgcolor="#FFFBEF">&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="51%" bgcolor="#FFFBEF"><div align="center" class="title-04">主题</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="17%" bgcolor="#FFFBEF"><div align="center" class="title-04">日期<%out.print(img_date);%></div></td>
                      <td width="0%" rowspan="100" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="12%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">发件人</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="10%" bgcolor="#FFFBEF"><div align="center" class="title-04">大小<%out.print(img_size);%></div></td>
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
         <td colspan=11 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
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
	
	String color = "#F2F9FF";
	if(i % 2 == 0){
		color = "#D8EAF8";
	}
%>
    <tr bgColor=<%=color%> onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='<%=color%>';">
       <td nowrap class="text-01" align="center"><input type="checkbox" name="messageid" value="<%=mailName%>"></td>
       <td nowrap class="text-01" align="center">
<%
    String secondword = mailName.substring(1,2);
    if(FileTransferConfig.NEW_FLAG.equals(secondword)){   //为未读邮件
%>
  	       <img alt="未读邮件标记" src="<%=request.getContextPath()%>/images/noread.gif" width="15" height="10">
<%  }%>
	   </td>
       <td nowrap>
	       <div align="center" class="text-01">
	       <%if("system".equals(type)){%>
	           <div align="left"><a href="#" onClick="javaScript:_openSystemfile('system','<%=folderSort%>','<%=mailName%>')"> ·<%if(FileTransferConfig.NEW_FLAG.equals(secondword)){out.print("<b>");}%><%=mailSubject%></a></div>
	       <%}else{%>
	           <div align="left"><a href="#" onClick="javaScript:_openSystemfile('user','<%=folderSort%>','<%=mailName%>')"> ·<%if(FileTransferConfig.NEW_FLAG.equals(secondword)){out.print("<b>");}%><%=mailSubject%></a></div>
	       <%}%>
	       </div>
       </td> 
       <td nowrap class="text-01"><div align="center"><% if(reDate != null) out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));%></div></td>
       <td nowrap height="22" class="text-01" align="center"><%=mailFrom!=null?mailFrom:""%></td>

       <td nowrap height="22"><div align="center"><%=mailMemory%> <%if(!(contentType.trim().equals("text/plain"))) {%><img src="<%=request.getContextPath()%>/images/attachsign.gif" alt="本邮件带附件" width="8" height="15" align="absmiddle"><%}%></div></td>
                          
    </tr>
    <tr>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
                </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
	           <%@ include file = "./PageScrollBar0.jsp" %>
	      </td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg">
              <div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div>
          </td>
        </tr>
</table> 
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EEEEEE">
  <tr>
    <td nowrap height="30" width="52%">
      <input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();">
      <font class="tblitemfont"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">选中所有的文件</a></font> 
      <font class="tblcautionfont">　<a href="#" onclick="javascript:_ForeverDelete()">[清空垃圾箱]</a></font>
      <img align="middle" src="<%=request.getContextPath()%>/images/botton-delete.gif" width="70" height="22" style="cursor:hand" onClick="javascript:_Delete()" >
    </td>
    <td nowrap width="48%" align="right">
			<input type="hidden" name="mov_action" value="">
			<input type="hidden" name="unread" value="">
			<input type="hidden" name="folder" value="<%=folderSort%>">
			<input type="hidden" name="type" value="<%=type%>">
      <img align="middle" src="<%=request.getContextPath()%>/images/botton-move.gif" width="70" height="22" style="cursor:hand" onClick="javascript:_Move()"> 
      <select name="targetfolder" size=1>
	          <% if(!("Inbox".equals(folderSort)))%>          
                 <option value="Inbox">收件箱</option>
              <% if(!("Sent".equals(folderSort)))%>       
                 <option value="Sent">发件箱</option>
              <% if(!("Draft".equals(folderSort)))%>   
                 <option value="Draft">草稿箱</option>
              <% if(!("Junk".equals(folderSort))){%>
                 <option value="Junk" selected>垃圾箱</option>
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
      </select>                    
    </td>
  </tr>
</table>
</td></tr>
</table>
</form>
</div>
</body>
</html>
<script language="JavaScript">

<!--

function CheckAll()
  {
    for (var i=0;i<document.listmail.elements.length;i++)
    {
      var e = document.listmail.elements[i];
      if (e.name != 'allbox')
        e.checked = document.listmail.allbox.checked;
    }
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
function MM_findObj(n, d) { //v4.01
	var p,i,x;  
	if(!d) d=document; 
	if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if(!(x=d[n])&&d.all) x=d.all[n]; 
	for (i=0;!x&&i<d.forms.length;i++) 
		x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
		x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); 
	return x;
}

function _showAlertBox() { //v6.0
	var i,p,v,obj,
	args=_showAlertBox.arguments;
	for (i=0; i<(args.length-2); i+=3) {
		if ((obj=MM_findObj(args[i]))!=null) { 
			v=args[i+2];
			if (obj.style) { 
				obj=obj.style; 
				v=(v=='show')?'visible':(v=='hide')?'hidden':v; 
			}
			obj.visibility=v; 
		}
	}
}

 function _openSystemfile(type,folder,mailName){
 
        window.showAlert.innerHTML = "正在打开该文件，请稍等......";  
	    //弹出正在发送的提示框
		_showAlertBox('alertbox1','','show');  
		
        document.listmail.action="<%=request.getContextPath()%>/servlet/ShowMailServlet?type="+type+"&folder="+folder+"&mailName="+mailName+"&curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
        document.listmail.action="<%=request.getContextPath()%>/servlet/ShowFile1Servlet?type="+type+"&folder="+folder+"&mailName="+mailName+"&curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
        document.listmail.submit();
        //window.top.leftFrame.location.reload();
	
 }
 
 function _pop(){  
   var kk = <%= request.getAttribute("percent") %>;
   if(parseInt(kk)>95){
   window.open('<%=request.getContextPath()%>/filetransfer/delmail.jsp','','width=600,height=100,toolbar=0,directories=0,status=0,menu=0,scrollbars=no,resizable=no,copyhistory=no,left=170,top=110');
   }
 }

function _toPage(pageno){
    
    if(pageno!='0'){
		window.top.mainFrame.location.href = "<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=" + pageno + "&type=<%=type%>&folder=<%=folderSort%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
	}
}

function _sort(pageno,sortname,sorttype){
    if(pageno!='0'){
		window.top.mainFrame.location.href = "<%=request.getContextPath()%>/servlet/MessageListServlet?curPageNum=" + pageno + "&type=<%=type%>&folder=<%=folderSort%>&sortname="+sortname+"&sorttype="+sorttype;
	}
}
//-->

</script>
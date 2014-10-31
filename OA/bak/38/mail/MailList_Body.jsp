 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>
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
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.MimeUtility" %>
<%@ page import="javax.mail.internet.MimeMessage" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
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
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<script>
function _sort(pageno,sortname,sorttype){
    if(pageno!='0'){
		window.location.href = "<%=path%>/servlet/MessageListServlet?curPageNum=" + pageno + "&type=<%=type%>&folder=<%=folderSort%>&sortname="+sortname+"&sorttype="+sorttype;
	}
}

function changeStyle(id){//切换样式
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//切换样式时设置COOKIE
	var cookies = document.cookie;
	var setcookies = "";
	var lastcookies = "";
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	
	var deltime = new Date();
	daltime = exp.setTime (exp.getTime() - 1);    

	while(cookies.indexOf(";")>=0){
		var tempcookie = cookies.substring(0,cookies.indexOf(";"));
		cookies = cookies.substring(cookies.indexOf(";")+1);
		
		if(tempcookie.indexOf(name)>=0){
			//删除原来的COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//设置新的样式
	document.cookie = name+"="+value+";expires="+exp.toGMTString()+";path=/;domain=10.102.1.40";
}

function getCookie(name){
	var cook =document.cookie;
	//alert(cook);
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(";"));
		var value = "grey";
		if(cook1.indexOf(";")>0){ 
			value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";"));
		}else{
			value = cook1.substring(cook1.indexOf("=")+1);
		}
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+style+".css";
}

initstyle();
</script>
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


function _checkone(){
   flag = true;
   for(var i=0;i<document.listmail.elements.length;i++){
      var e = document.listmail.elements[i];
	  if (e.name != 'allbox' && e.checked == false){
         flag = false;
		 break;
	  }
   }
   if(flag == true){
      document.listmail.allbox.checked = true;
   }else{
      document.listmail.allbox.checked = false;
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

function _ForeverDelete(folder)
{
	var foldername = "";
	if(folder == "Rec"){
		foldername = "收件箱";
	}else if(folder == "Sent"){
		foldername = "发件箱";
	}else if(folder == "Draft"){
		foldername = "草稿箱";
	}else if(folder == "Junk"){
		foldername = "垃圾箱";
	}else{
		foldername = folder;
	}
    ok=confirm("确定要清空"+foldername+"吗？");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder="+folder;
        return true;
    }else{
        return false;
    }
}
//initstyle();
</script>
</head>

<body onload="javascript:frameautoheight()">
<form name="listmail" method="POST">
			<input type="hidden" name="mov_action" value="">
			<input type="hidden" name="unread" value="">
			<input type="hidden" name="folder" value="<%=folderSort%>">
			<input type="hidden" name="type" value="<%=type%>">



<table width="750" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
    <td>
    <table>
    <tr>
      <td width="99" height="30" valign="middle"><select name="targetfolder" style="font-size:14;" onChange="javascript:_Move()">
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
      <td width="28" height="30" align="right" valign="middle"><img src="<%=path%>/images/mail_delete.JPG" width="17" height="17" /></td>
      <td width="32" valign="middle" class="foot_message"><a href="#" onClick="javascript:_Delete()" style="text-decoration:none">删除</a></td>
      <td width="34" height="30" align="right" valign="middle"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" /></td>
      <td width="114" height="30" valign="middle" class="foot_message"><a href="#" onClick="javascript:_ForeverDelete('Junk')" style="text-decoration:none">清空垃圾箱</a></td>
     <td width="448" height="30" align="center" valign="top">
        
      <iframe name="foldersizefrm" src="<%=path%>/servlet/FolderSizeServlet" width="100%" height="100%" scrolling="no" frameborder="0" framespacing="0"></iframe>	   </td>

    </tr>
	</table>
	</td>
	</tr>

    <tr>
      <td>
      	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" class="table_bgcolor">
        <tr>
          <td nowrap="nowrap" width="5%" height="26" class="block_title" align="center">
              <input type="checkbox" name="allbox" value="checkbox" onClick="javascript:CheckAll()"/>全选</td>
          <td width="5%" class="block_title" align="center">&nbsp;</td>
          <td width="45%" class="block_title" align="center">主题</td>
          <td width="20%" class="block_title" align="center">日期<%out.print(img_date);%></td>
          <td width="15%" class="block_title" align="center"> <%=("Sent".equals(folderSort))?"收件人":"发件人"%></td>
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
         <td height="52" class="text-01" colspan=6>
             <div align="center"><br><br>没有邮件！<br><br></div>         </td>             
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

  String receiver ="";
            if("Sent".equals(folderSort)){
            MimeMessage mm = mHandler.getHeadMessage(mailHead);
	        Address[] toArray = mm.getRecipients(Message.RecipientType.TO);
            for (int k = 0; k < toArray.length; k++) {
                if(k>1){
                    receiver+="...";
                    break;
                    }else if(k>0){
                         receiver+=",";
                    }
				String Address = MimeUtility.decodeText(toArray[k].toString());
				receiver += ftsHandler.getCName(Address.substring(0, Address.indexOf("@")));
                }

                mailFrom = receiver;
            }

			//mailSubject = mailSubject.substring(0,mailSubject.length()-1);
			if(",".equals(mailSubject.substring(mailSubject.length()-1)))
			mailSubject = mailSubject.substring(0,mailSubject.length()-1);
			mailSubject = mailSubject.replaceAll("　"," ");
			if("".equals(mailSubject.trim())) mailSubject="无主题";    
			String contentType = mHandler.getContentType(mailHead);
			boolean noreadflag=false;
			String secondword = mailName.substring(1,2);
			if(FileTransferConfig.NEW_FLAG.equals(secondword)){   //为未读邮件
				noreadflag=true;
			}
	%>

        <tr>
          <td height="26" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="messageid" value="<%=mailName%>" onclick="_checkone()"/>
          </div></td>
          <td bgcolor="#FFFFFF"><div align="center"><img src="<%=path%><%out.print(noreadflag==true?"/images/email.gif":"/images/email_open.gif");%>" width="16" height="16" hspace="3" vspace="3" /></div></td>
          <td bgcolor="#FFFFFF" class="message_title_bold" >
		  <%if("system".equals(type)){%>
			<a href="javaScript:_openSystemfile('system','<%=folderSort%>','<%=mailName%>')"  class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>" style="text-decoration:none"><%=mailSubject%></a>
		  <%}else{%>
			<a href="javaScript:_openSystemfile('user','<%=folderSort%>','<%=mailName%>')"  class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>" ><%=mailSubject%></a>
		  <%}%>		  </td>
          <td bgcolor="#FFFFFF" class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><div align="center"><% if(reDate != null) out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));%></div></td>
          <td bgcolor="#FFFFFF" class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><div align="center"><%=mailFrom!=null?mailFrom:""%></div></td>
          <td bgcolor="#FFFFFF" class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><table border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="<%out.print(noreadflag==true?"message_title_bold":"message_title");%>"><%=mailMemory%></td>
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
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
</form>
<script language="javascript">
function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
}
	window.top.leftfrm.location.reload();
</script>	
</body>
</html>

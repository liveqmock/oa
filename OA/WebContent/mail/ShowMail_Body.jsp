 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.*"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachHelper"%>
<%@page import="javax.mail.internet.MimeUtility"%>
<%@page import="com.icss.oa.util.StringUtility"%>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@ page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>
<%
String path = request.getContextPath();
String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
MimeMessage fileMessage = (MimeMessage)request.getAttribute("fileMsg");
String folder = (String)request.getAttribute("folder");
String content = (String)request.getAttribute("content");
String type = (String)request.getAttribute("type");
String mailName = (String)request.getAttribute("mailName");
String username = (String)request.getAttribute("username");
String senduuid = (String)request.getAttribute("senduuid");

List tolist = (List)request.getAttribute("tolist");
List cclist = (List)request.getAttribute("cclist");
//阅读记录的人中文名
List readPersonlist = (List)request.getAttribute("readPersonlist");
//保存的邮件的接收人
String recePerson = (String)request.getAttribute("recePerson");

String _curPageNum = request.getParameter("curPageNum");

if(_curPageNum == null){
	_curPageNum = "1";
}
//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
String sortname = request.getParameter("sortname");
//排序方式 默认为倒序
String sorttype = request.getParameter("sorttype");

%>

<html>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<head>
<title>新华社办公信息化系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#movediv{
    width:600px;height:190px;position:absolute;border:1px solid #000;background:#EAEAEA;
    cursor:pointer;
    text-align:center;
    line-height:100px;
    left:100px;
    top:10px;
}

A:LINK{TEXT-DECORATION:NONE}
A:VISITED{TEXT-DECORATION:NONE}
A:HOVER {COLOR:#FF0000; TEXT-DECORATION:UNDERLINE}
A:ACTIVE{COLOR:#FF0000; TEXT-DECORATION:UNDERLINE}
-->
</style>
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/include/xmlextras.js"></SCRIPT>
<script language="JavaScript" src="<%=path%>/include/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
var _IsMousedown = 0;
   var _ClickLeft = 0;
   var _ClickTop = 0;
   function moveInit(divID,evt)
   {
    _IsMousedown = 1;
    if(getBrowserType() == "NSupport")
    {
     return;
    }
    var obj = getObjById(divID);
    if(getBrowserType() == "fox")
    {
     _ClickLeft = evt.pageX - parseInt(obj.style.left);
     _ClickTop = evt.pageY - parseInt(obj.style.top);
    }else{
     _ClickLeft = evt.x - parseInt(obj.style.left);
     _ClickTop = evt.y - parseInt(obj.style.top);
    }
   }
    function changeDiv(obj,url)
   {
		document.getElementById(obj).style.display="";
		document.getElementById(obj).innerHTML = "<iframe src='"+url+"'   width=100%   height=100% marginheight='0px' marginwidth='0px' frameborder='0'></iframe><input type=\"button\" onclick=\"this.parentElement.style.display='none'\" value=\"关闭\" />";
   }	
   function Move(divID,evt)
   {
    if(_IsMousedown == 0)
    {
     return;
    }
    var objDiv = getObjById(divID);
    if(getBrowserType() == "fox")
    {
     objDiv.style.left = evt.pageX - _ClickLeft;
     objDiv.style.top = evt.pageY - _ClickTop;
    }
    else{
     objDiv.style.left = evt.x - _ClickLeft;
     objDiv.style.top = evt.y - _ClickTop;
    }
    
   }
   function stopMove()
   {
    _IsMousedown = 0;
   }
   function getObjById(id)
   {
    return document.getElementById(id);
   }
   function getBrowserType()
   {
    var browser=navigator.appName
    var b_version=navigator.appVersion
    var version=parseFloat(b_version)
    //alert(browser);
    if ((browser=="Netscape"))
    {
     return "fox";
    }
    else if(browser=="Microsoft Internet Explorer")
    {
     if(version>=4)
     {
      return "ie4+";
     }
     else
     {
      return "ie4-";
     }
    }
    else
    {
     return "NSupport";
    }
   }


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

function _showperson(){
	window.open('<%=request.getContextPath()%>/servlet/GetPersonInfoServlet?from=<%=fileMessage.getFrom()[0].toString()%>')
}

function _showrec(){
	var rec=document.getElementById('rectr');
	var link=document.getElementById('reclink');
	rec.style.display = "block";
	link.style.display = "none";
	frameautoheight();
}

function _hiderec(){
	var rec=document.getElementById('rectr');
	var link=document.getElementById('reclink');
	rec.style.display = "none";
	link.style.display = "block";
}

</script>
</head>

<body>
<form name="printheader" method="post">
<input  type="hidden"name="subject"  value="<%=StringUtility.replace(fileMessage.getSubject(),"\"","\'")%>">
<input type="hidden" name="folder" value="<%=folder%>">
<input type="hidden" name="mailName" value="<%=mailName%>">
<input type="hidden" name="messageid" value="<%=mailName%>">
<input type="hidden" name="content" value="<%=content%>">
<!--<textarea name="content" style="display:none"><%=content%></textarea>-->
<input type="hidden" name="from" value="<%=MimeUtility.decodeText(fileMessage.getFrom()[0].toString())%>">
<input type="hidden" name="type" value="<%=type%>">
 <div id="movediv"  style="left:150px;top:20px;display:none" onmousedown="moveInit('movediv',event);" onmousemove="Move('movediv',event)" onmouseup="stopMove()" onmouseout="stopMove()"><ifream></div>

<table width="788" border="0" cellspacing="0" cellpadding="0">

    <tr>
      <td height="30" colspan="3"width="1" valign="top"></td>
      <td valign="top">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" class="table_bgcolor">
          <tr>
            <td height="44" colspan="2" bgcolor="#FFFFFF">
            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                   	  <td><img src="<%=path%>/images/mail_replybutton.JPG" width="59" height="25" onClick="javascript:reply()" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_resendbutton.JPG" width="59" height="25" onClick="javascript:transmit()"  style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_deletebutton.JPG" width="59" height="25" onClick="javascript:_Delete()" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" width="59" height="25" onClick="javascript:_goback('<%=type%>')" style="cursor:hand"/></td>
                      <td width="5%">&nbsp;</td>
                        <td width="11%">&nbsp;</td>
                	</tr>
                </table>                </td>
          </tr>
          <tr>
          	<td colspan="2" bgcolor="#FFFFFF">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
   	      <tr>
                  <td width="12%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">发件人：</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="107" class="message_title"><%=(String)request.getAttribute("sendMan")%></td>
                          <td width="37" align="right" class="blue-12">&nbsp;<img src="<%=path%>/images/mail_private.JPG" width="17" height="17" />&nbsp;</td>
                          <td width="89" class="message_title"><a href="###" onclick="javascript:_openPage('<%=request.getContextPath()%>/servlet/GetIndiGroupServlet?from=<%=fileMessage.getFrom()[0].toString()%>')" alt="加入个人分组" >加入个人分组</a></td>
                          <td width="34" align="right" class="blue-12"><img src="<%=path%>/images/mail_personinfo.JPG" width="17" height="17" />&nbsp;</td>
                          <td width="250" class="message_title"><a href="###" onclick="javascript:changeDiv('movediv','<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=senduuid%>')"  alt="查看发信人详细信息">发信人详细信息</a></td>
                          <td width="35" class="blue-12">&nbsp;</td>
                          <td width="138" class="blue-12">&nbsp;</td>
                      </tr>
                  </table></td>      </tr>

				 <tr>
					<td height="26" style="display:none" id="rectr" colspan="4">
				        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
				            <tr>
				                <td height="26" width="12%" align="right" bgcolor="#FFFFFF" class="message_title"><a href="#" onClick="javascript:_hiderec()" alt="隐藏收件人" >收件人</a>：</td>
				                <td bgcolor="#FFFFFF" class="message_title">
				                 <%
				                        if(tolist!=null||tolist.size()!=0){
				                            for(int i = 0; i < tolist.size(); i ++){
				                                if(i!=0) out.print(",");
				                                out.print(tolist.get(i));
				                            }
				                        }
				                 %>	
				                 </td>
				             </tr>
				         </table>			
				    </td>
				 </tr>

          
                  <tr> 
					    <td height="26" id="reclink" colspan="4">
					        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					            <tr>
					                <td height="26" width="12%" align="right" bgcolor="#FFFFFF" class="message_title"><img src="<%=path%>/images/mail_show.jpg" /></td>
					                <td bgcolor="#FFFFFF" class="message_title"><span><a href="#" onClick="javascript:_showrec()" alt="显示收件人" >显示收件人</a></span>
					                 </td>
					             </tr>
					         </table>			
					    </td> 
					</tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">文件标题：</td>
                    <%String mailSubject = fileMessage.getSubject().substring(0,fileMessage.getSubject().length()-13);
                    if(",".equals(mailSubject.substring(mailSubject.length()-1)))
											mailSubject = mailSubject.substring(0,mailSubject.length()-1);
                    
                    %>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title_bold"><%=mailSubject%></td>
                  </tr>
                  
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">发件日期：</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title"><%=com.icss.oa.util.CommUtil.getTime(fileMessage.getSentDate().getTime())%></td>
                  </tr>
                
                  <%if(!"Inbox".equals(folder)){%>
				
				<tr>
				    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">已阅人员：</td>
                    <td colspan="3"  bgcolor="#FFFFFF" class="message_title" >
					<%
					if(readPersonlist!=null && readPersonlist.size()>0){                 		
						Iterator personItr = readPersonlist.iterator();
						int num = 0;
						while(personItr.hasNext()){
							if(num!=0)  out.print(",");
							out.print(personItr.next());
							num++;
						}
					}else{
							out.print("&nbsp;无");
						}
					%>					</td>
                </tr>

                <%}%>

				 <tr>
				 <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">正文内容：</td>
                    <td colspan="3"  bgcolor="#FFFFFF">
					 <div  style=" width:600px;height:190px;border:1px solid #000;overflow:auto" class="message_title" align="left">
					<%=request.getAttribute("content")%>
					</div>
					</td>
                  </tr>


              </table>

            
                 <br>
              <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="1" bgcolor="#B9DAF9" width="100%"></td></tr>
              </table>
                 <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">

                    <tr>
                      <td></td>
                    <td height="15"></td></tr>
<%
	ArrayList fileList = (ArrayList)request.getAttribute("attachList");
	if(fileList != null && fileList.size() > 0){ //附件存在
%>
					<tr>
                      <td>&nbsp;</td>
                    	<td class="message_title">附件:</td>
                    </tr>
<%
		for(int i = 0; i < fileList.size(); i ++){	
		AttachFileBean attachFileBean = (AttachFileBean)fileList.get(i);
		String filename = attachFileBean.getFileOriginName();
		String extend = filename.substring(filename.lastIndexOf(".")+1,filename.length());
		String onclickaction = "";
		String downloadaction = "";
		if("doc".equals(extend) || "xls".equals(extend)){
			onclickaction = "javascript:window.open('"+ path  +"/servlet/DownloadAttachServlet?url="+java.net.URLEncoder.encode(attachFileBean.getDownloadUrl())+"&filename="+java.net.URLEncoder.encode(attachFileBean.getFileOriginName())+"')";
		}else{
			onclickaction = path + "/servlet/DownloadAttachServlet?url="+java.net.URLEncoder.encode(attachFileBean.getDownloadUrl())+"&filename="+java.net.URLEncoder.encode(attachFileBean.getFileOriginName());
			//onclickaction = "javascript:window.location.href='"+onclickaction+"'";
			onclickaction = "javascript:_download('"+onclickaction+"')";
		}
		downloadaction = path + "/servlet/DownloadAttachServlet?url="+java.net.URLEncoder.encode(attachFileBean.getDownloadUrl())+"&filename="+java.net.URLEncoder.encode(attachFileBean.getFileOriginName())+"&down=1";
		//downloadaction = "javascript:window.location.href='"+downloadaction+"'";
%>

      <tr>
         <td>&nbsp;</td>
            <td><span class="table_title"><strong>   <a href="#" onclick="<%=onclickaction%>" style="TEXT-DECORATION:UNDERLINE;"><font color="red"><b><%=attachFileBean.getFileOriginName()%>(<%=AttachHelper.getFileSize(attachFileBean.getFilesize())%>)</font></a></strong></span> <span class="message_title_bold">&nbsp;&nbsp;<a href="#" onClick="window.open('<%=request.getContextPath()%>/servlet/SaveAccessServlet?url=<%=java.net.URLEncoder.encode(attachFileBean.getDownloadUrl())%>&filename=<%=java.net.URLEncoder.encode(attachFileBean.getFileOriginName())%>','','width=500,height=300,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=252,top=110')" title="[点击后选择要保存到网络文件夹中的位置。确定则保存,取消则放弃。]"><b>添加到网络文件夹</a></span><span class="message_title_bold">&nbsp;&nbsp;
            <a href="#" onclick="_download('<%=downloadaction%>');" title="[点击后选择下载路径]"><b>点击下载</a><br></span></td>
     </tr>
                   
<%
	}//end for
}//end if
%>   
                 </table>            </td>
          </tr>
          
          <tr>
            <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="message_title"><img src="<%=path%>/images/mail_replybutton.JPG" onClick="javascript:reply()" width="59" height="25" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_resendbutton.JPG" onClick="javascript:transmit()" width="59" height="25" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_deletebutton.JPG" onClick="javascript:_Delete()" width="59" height="25" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" onClick="javascript:_goback('<%=type%>')" width="59" height="25" style="cursor:hand" /></td>
          </tr>
      </table></td>
    </tr>
    
    
    
    <tr>
    	<td colspan="4" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
	  
	  
</form>
</body>
<script>
var x; 
function _download(url){
	x = window.open(url);
	setTimeout(_closeW,5000);
} 

function _closeW(){
	x.close();
}

//回复
function reply(){
	//alert('1');
	//window.parent.location.reload();
	//alert(parent.bodyfrm.src);
	//alert(document.readyState);
	document.printheader.action="<%=path%>/servlet/BuildReplyServlet?curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
	document.printheader.submit();
}

//转发
function transmit(){
	document.printheader.action="<%=path%>/servlet/SendFileBuildTransmitServlet";
	document.printheader.submit();
}

//返回
function _goback(type){
    if(type=="searchmail"){
	    document.location.href="<%=path%>/servlet/SearchMailServlet";
	}else if(type=="noread"){
	    document.location.href="<%=path%>/servlet/ShowNoReadServlet";
	}else{
	    document.location.href="<%=path%>/servlet/MessageListServlet?type="+type+"&folder=<%=folder%>&curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
        //alert(history.back());
        //document.location.href= "javascript: history.back(-2)";
	    //alert(history.back());
	}
}

//下载附件
function _downloadattach(uploadurl,filename){
    document.location.href = "<%=request.getContextPath()%>/servlet/DownloadAttachServlet?url="+uploadurl+"&filename="+filename;
    //window.location.reload();
}

//删除
function _Delete()
{
     if(confirm("您要选择永久删除这些文件吗？")){
     document.printheader.action="<%=path%>/servlet/DelMailServlet?curPageNum=<%=_curPageNum%>&sortname=<%=sortname%>&sorttype=<%=sorttype%>";
     document.printheader.submit();
     return true;
    }else{
     // return false;
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

	function rep(text,src,des){
		var num = text.indexOf(src);
		alert(num);
		while(num>=0){
			text=text.substring(0,num)+des+text.substring(num+1,text.length);
			num = text.indexOf(src);
		}
		return text;
	}

function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
}
frameautoheight();


 document.printheader.content.value = '<%=content%>';

</script>
</html>

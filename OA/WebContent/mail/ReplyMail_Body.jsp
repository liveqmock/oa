<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.util.CommUtil"%>
<%
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日")+TimeUtil.getCurrentWeek();
	
	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
	String sun_flag = (String)request.getSession().getAttribute("sun_flag");
	if (sun_flag == null){
		sun_flag = "";
	}
%>

<html xmlns="http://www.w3.org/1999/xhtml">
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
-->
</style>
<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
<script language="javascript">
function _updown(id){
	 var obj=document.getElementById(id);
   if(searchStr(obj.src,"mail_up.jpg")){
			 obj.src='<%=path%>/images/mail_down.jpg';
	 }else{
		  obj.src='<%=path%>/images/mail_up.jpg';
	 }
}

function searchStr(str1,str2){   
   if(str1.search(str2)>=0)   
        return   true;      
        else   
        return   false;    
}   

function changeStyle(id){
	//alert("dddd");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
//20110420 dyt
function setCookie(name,value){
		
 var expire = ""; 
var hours = 100;
  if(hours != null) { 
    expire = new Date((new Date()).getTime() + hours * 3600000); 
    expire = "; expires=" + expire.toGMTString(); 
  }

if("mail_title"==name||"mail_context"==name){
		document.cookie=name+"="+escape(value)+"quqs"+expire ;
		}else{
                 document.cookie=name+"="+value+expire;
                }


}

function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf(name)>=0){
			var cook1 = unescape(cook.substring(cook.indexOf(name)));
			var value;
			if("mail_title"==name||"mail_context"==name){
			value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf("quqs;")));
			}else{value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";")));}
			alert("get:"+name+"="+value);
			return value;
		}else if("xhsstyle"==name){
			return "grey";
			}else
			{return "";}
		
}

function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
}


function check(){
	
	if(document.sendForm.topic.value == ""){
		if(confirm("你没有添加标题！请确认以‘无标题’作为您的标题吗？"))
		{ 
			document.sendForm.topic.value = "无主题";
		  	return true;
		};
		return false;
	}
	return true;
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

function _return(){
	location.href = "<%=path%>/servlet/BoxListServlet";
}

function mailsend(){
	if(check()){
		window.showAlert.innerHTML = "正在发送文件，请稍等......";
	    //弹出正在发送的提示框
		_showAlertBox('alertbox1','','show');
		//20110420 dyt
		var mail_title;
		var mail_context;
		mail_context=document.sendForm.content.value;		
		mail_title=document.sendForm.topic.value;
		setCookie("mail_title",mail_title);
		setCookie("mail_context",mail_context);
		
		
		document.sendForm.action = "<%=path%>/servlet/ReplyFileServlet";
		document.sendForm.submit();
	}
}

function savetodraft(){
	if(check()){
		window.showAlert.innerHTML = "正在保存文件，请稍等......";
		_showAlertBox('alertbox1','','show');
		document.sendForm.action = "<%=path%>/servlet/SaveToDraft1Servlet";
		document.sendForm.submit();
	}
}

function _attachfile(){
	document.sendForm.action="<%=path%>/servlet/SendFileAttachFileServlet?"
	document.sendForm.donext.value="2";
	document.sendForm.submit();
}
</script>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body >
<form method="post" name="sendForm" ENCTYPE="multipart/form-data">

<input type="hidden" name="AttFiles" value ="">
<!-- 选择收件人、抄送、密送为0，1，2 -->
<input type="hidden" name="sendType" value="">
<!-- 发邮件的方式 -->
<input type="hidden" name="sendMail" value="1">
<!--以下为了添加附件的时候传递收件人信息用-->
<input type="hidden" name="donext" value=""/>
 
	<div id="alertbox1"
				style="position: absolute; width: 250px; height: 24px; z-index: 1; left: 320px; top: 100px; visibility: hidden;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					bgcolor="#000000">
					<tr bgcolor="#EEFFF7">
						<td height="25" align="center" id="showAlert"
							class="message_title"></td>
					</tr>
				</table>
			</div>
<table width="770" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="4" height="5"><img src="<%=path%>/images/kongbai.jpg" width="11" height="5" /></td>
    </tr>
    <tr>
      <td height="30" colspan="3" valign="top"></td>
      <td valign="top">
      <table width="770" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
            <td height="36" colspan="2" bgcolor="#FFFFFF"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" onClick="javascript:mailsend()" style="cursor:hand"/> <img src="<%=path%>/images/mail_returnbutton.JPG" onClick="javascript:_return()" width="59" height="25" style="cursor:hand" /></td>
            <td width="240" rowspan="3" valign="top" bgcolor="#FFFFFF" class="blue3-12">
            
                	  </td>
          </tr>
          

          <tr>
          	<td colspan="2" bgcolor="#FFFFFF" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <input type="hidden" name="sendType" />
      		  <tr>
                    <td width="12%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">收件人：</td>
                  <td width="88%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490" style="width:500px"><input type="text" value="<%=sendFileBean.getSendto()%>" readonly size="64"></td>
                        </tr>
                    </table></td>
                </tr>
                    		 
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">主题(<span class="STYLE1">*</span>)：</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><input name="topic" type="text" class="biankuang-blue" size="64" value="<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-14)%>"/></td>
                        </tr>


                    </table></td>
                  </tr>
                  
					<tr>
                        <td height="26" align="right" bgcolor="#FFFFFF" class="message_title" nowrap> 
						<INPUT TYPE="hidden" NAME="filenum">
						<INPUT TYPE="hidden" NAME="realnum">
						<div>
							<a id="container1" class="addfile" href="#" onclick="document.getElementById('file_0').click();"><input id="file_0" 
							name="file_0" type="file" class="addfile" onchange="createnew();"  />
							</a>
							</div>
						</td>
                        <td colspan="3" bgcolor="#FFFFFF">
                        <div id="container2" style=" width:460px;height:21px;background:#CCFFFF"></div>
						</td>
                      </tr>
                  
                  <tr style="display:none">
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title"></td>
                    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr style="display:none">
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title"></td>
                    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
              </table>
                 
          <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="1" class="table_bgcolor" width="100%"></td></tr>
                 </table>
                 <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="10" colspan="2" bgcolor="#FFFFFF"></td></tr>
                    <tr>
                      <td colspan="2" align="left" valign="top" class="message_title">&nbsp;内容概要(<span class="STYLE1">500字以内</span>)：</td>
                    </tr>
                    <tr>
                   	  <td colspan="2" align="center" valign="top" class="message_title">                  	    
               	      <textarea name="content" cols="68" rows="13" class="biankuang-blue"><%=CommUtil.unformathtm(sendFileBean.getContent())%></textarea></td>
                    </tr>
                    <tr>
                      <td width="78" height="33"></td>
                      <td width="503" class="message_title"><input name="isSent" type="checkbox" value="checked2" checked/>
                        保留一份在发件箱&nbsp;&nbsp;
                        <input name="isRe" type="checkbox" value="checked1" />
                      收到邮件时给我回复 </td>
                   </tr>
             <!--       <tr>
                    	<td colspan="5">
                        	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                                <tr><td height="1" class="table_bgcolor" width="100%"></td></tr>
                             </table>                        </td>
                    </tr>
                    <tr>
                    	<td height="44" align="right" class="message_title">添加附件</td>
                   	  <td class="message_title">
                      
                          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                            	<tr>
                                	<td width="22%">
                          <img src="<%=path%>/images/icon_attachment.gif" width="24" height="16"/><a href="javascript:_attachfile()" class="message_title"  style="cursor:hand">点击添加附件</a>                          			</td>
                          <td width="82%" align="center">
							<table>
						<%
							for(int index = 0; index < sendFileBean.filenumber();index ++ ){
								AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
						%>
						 <tr>
							 <td  align="left" class="message_title"><IMG SRC="<%=path%>/images/attachfile.gif" BORDER="0" ALT="添加附件">&nbsp;<%=attachFileBean.getFileOriginName()%></td>
						</tr>
						<%
												}//end for
						%>
							</table>
						  </td></tr>
								  
								
                              </table>                                    
                                
                       </tr>-->
                     </table>               </td>
        </tr>
          <tr>
            <td height="40" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="blue3-12"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="25" style="cursor:hand" onClick="javascript:mailsend()" style="cursor:hand"/> 
			<img src="<%=path%>/images/mail_returnbutton.JPG" onClick="javascript:_return()" width="59" height="25" style="cursor:hand" /></td>
          </tr>
      
      </table></td>
    </tr>
    
    <tr><td>&nbsp;</td></tr>
    
    <tr>
    	<td colspan="4" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 

</form>
<script>

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

function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
}
frameautoheight();



</script>
</body>
</html>

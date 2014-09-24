<%@ page contentType="text/html; charset=GB2312" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachHelper"%>
<%@page import="javax.mail.internet.*"%>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>

<%
HttpSession receSession = request.getSession();
List recePersonList = (List)receSession.getAttribute("recePersonList");
String path = request.getContextPath();
String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
List tolist = (List)recePersonList.get(0);
List cclist = (List)recePersonList.get(1);
List bcclist = (List)recePersonList.get(2);
MimeMessage fileMessage = (MimeMessage)recePersonList.get(3);
SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
String folder = sendFileBean.getfolder();
String type = sendFileBean.getType();
String mailName = sendFileBean.getMailName();

String sendHtml = (String)request.getAttribute("sendHtml");
String ccHtml = (String)request.getAttribute("ccHtml");
String bccHtml = (String)request.getAttribute("bccHtml");


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
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
<SCRIPT language=JavaScript src="<%=path%>/include/address.js"></SCRIPT>
<script>

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

function _sendFile(){
	   window.showAlert.innerHTML = "正在发送文件，请稍等......";
		_showAlertBox('alertbox1','','show');
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFromDraftServlet";
		document.sendForm.submit();
}

function _goback(type){
	document.location.href="<%=request.getContextPath()%>/servlet/MessageListServlet?type="+type+"&folder=<%=folder%>";
}

function _modify(){
	document.sendForm.action = "<%=request.getContextPath()%>/mail/FromDraftSendModify_Body.jsp";
	document.sendForm.submit();
}

function _return(){
	location.href = "<%=path%>/servlet/BoxListServlet";
}

function attachfile(){
	document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFileAttachFileServlet?donext=5";
	document.sendForm.submit();
}

function updateInfo(){
	
   var foldername = "<%=sendFileBean.getfolder()%>";
   <%
		StringBuffer tostr = new StringBuffer();
		StringBuffer ccstr = new StringBuffer();
		StringBuffer bccstr = new StringBuffer();
        if(tolist!=null||tolist.size()!=0){
            for(int i = 0; i < tolist.size(); i ++){
                if(i!=0) tostr.append(",");
	            tostr.append(tolist.get(i));
            }
   %>

			
			to.innerHTML = "<%=tostr.toString()%>";
   <%
       }
       if(cclist!=null||cclist.size()!=0){
            for(int i = 0; i < cclist.size(); i ++){
                if(i!=0) ccstr.append(",");
	            ccstr.append(cclist.get(i));
            }
   %>
			cc.innerHTML = "<%=ccstr.toString()%>";
			if(cc.innerHTML != ""){
				document.getElementById("cctr").style.display = "block";
			}	
   <%            
       }
		
       if(bcclist!=null||bcclist.size()!=0){
            for(int i = 0; i < bcclist.size(); i ++){
                if(i!=0) bccstr.append(",");
	            bccstr.append(bcclist.get(i));
            }
   %>
			bcc.innerHTML = "<%=bccstr.toString()%>";
			if(bcc.innerHTML != ""){
				document.getElementById("bcctr").style.display = "block";
			}
			
   <%            
       }           		
	%>
	topic = "";
	<%if(!("".equals(sendFileBean.getTopic()))&&sendFileBean.getTopic()!=null){%>
	   	topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-14)%>";
	<%}%>

	document.sendForm.topic.value = topic;
}


</SCRIPT>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body>
<form method="post" name="sendForm" >
 <!--以下为发送邮件的时候为传人或分组 -->
	<input type="hidden" name="addPerson_person">
	<input type="hidden" name="addPerson_group">
	<input type="hidden" name="addPerson_org">
	<input type="hidden" name="addcc_person">
	<input type="hidden" name="addcc_group">
	<input type="hidden" name="addcc_org">
	<input type="hidden" name="addbcc_person">
	<input type="hidden" name="addbcc_group">
	<input type="hidden" name="addbcc_org">



<!--
	<input type="text" name="addPerson_person" style="width:100%"><br>
	<input type="text" name="addPerson_group" style="width:100%"><br>
	<input type="text" name="addPerson_org" style="width:100%"><br>
	<input type="text" name="addcc_person" style="width:100%"><br>
	<input type="text" name="addcc_group" style="width:100%"><br>
	<input type="text" name="addcc_org" style="width:100%"><br>
	<input type="text" name="addbcc_person" style="width:100%"><br>
	<input type="text" name="addbcc_group" style="width:100%"><br>
	<input type="text" name="addbcc_org" style="width:100%"><br>-->
<input type="hidden" name="AttFiles" value ="">
<!-- 选择收件人、抄送、密送为0，1，2 -->
<input type="hidden" name="sendType" value="">
<!-- 发邮件的方式 -->
<input type="hidden" name="sendMail" value="1">
<!--以下为了添加附件的时候传递收件人信息用-->
<input type="hidden" name="donext" value=""/>
<textarea name="sendHtml" style="display:none"><%=sendHtml%></textarea><!-- 收件人 -->
<textarea name="ccHtml" style="display:none"><%=ccHtml%></textarea><!-- 抄送 -->
<textarea name="bccHtml" style="display:none"><%=bccHtml%></textarea><!-- 密送 --> 
<textarea name="SendFileSendHtml" style="display:none"><%=session.getAttribute("SendFileSendHtml")%></textarea><!-- 收件人 -->
<textarea name="SendFileCcHtml" style="display:none"><%=session.getAttribute("SendFileCcHtml")%></textarea><!-- 抄送 -->
<textarea name="SendFileBccHtml" style="display:none"><%=session.getAttribute("SendFileBccHtml")%></textarea><!-- 密送 --> 
<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align="center" id="showAlert" class="message_title"></td>
		</tr>
	</table>
</div>  


<table width="750" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="6" height="5"><img src="<%=path%>/images/kongbai.jpg" width="11" height="5" /></td>
    </tr>
    <tr>
      <td height="30">&nbsp;</td>
      <td colspan="3" rowspan="2" valign="top">
      </td>
      <td headers="11">&nbsp;</td>
      <td rowspan="2" valign="top"><table width="789" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
           <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="blue3-12"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="23" onClick="javascript:_sendFile()" style="cursor:hand"/> <img src="<%=path%>/images/mail_updatesend.JPG" width="96" height="25" onClick="javascript:_modify()" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" width="65" height="23" onClick="javascript:_return()" style="cursor:hand"/></td>
            <td width="197" rowspan="3" valign="top" bgcolor="#FFFFFF" class="blue3-12">
          
            </td>
          </tr>
          

          <tr>
          	<td colspan="2" bgcolor="#FFFFFF">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <input type="hidden" name="sendType" />
      		  <tr>
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">收件人</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490" style="width:500px"><span id="to" class="message_title"></span></td>
                        </tr>
                    </table></td>
                </tr>
                    		  <tr id="cctr" style="display:none">
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">抄送人</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr >
                          <td width="490"><span id="cc" class="message_title"></span></td>
                        </tr>
                    </table></td>
                </tr>
                      		  <tr  id="bcctr" style="display:none">
                    <td width="14%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">密抄人</td>
                  <td width="86%" colspan="3" bgcolor="#FFFFFF" class="blue3-12-b">
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="490"><span id="bcc" class="message_title"></span></td>
                        </tr>
                    </table></td>
                </tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">主题(<span class="STYLE1">*</span>)</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td><input name="topic" type="text" class="biankuang-blue" size="68" value=""/></td>
                        </tr>
                    </table></td>
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
                   	  <td width="78" align="right" valign="top" class="message_title">内容概要<br />
               	      (<span class="STYLE1">500字以内</span>)</td>
                      <td width="503"><textarea name="content" cols="68" rows="10" class="biankuang-blue"><%=sendFileBean.getContent()%></textarea></td>
                    </tr>
                    <tr>
                      <td height="33"></td>
                      <td class="message_title"><input name="isSent" type="checkbox" value="checked2" checked/>
                        保留一份在发件箱&nbsp;&nbsp;
                        <input name="isRe" type="checkbox" value="checked1" />
                      收到邮件时给我回复 </td>
                   </tr>
                    <tr>
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
                                	<td width="19%">
                          <img src="<%=path%>/images/icon_attachment.gif" width="16" height="16"/><a href="javascript:attachfile()" class="message_title"  style="cursor:hand">点击添加附件</a>                          			</td>
                          <td width="79%" align="center">
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
						  <td width="2%">                                </tr>
								  
								
                              </table>
   </tr>
                     </table>               </td>
        </tr>
  
          <tr>
            <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="blue3-12"><img src="<%=path%>/images/mail_sendbutton.JPG" width="59" height="23" onClick="javascript:_sendFile()" style="cursor:hand"/> <img src="<%=path%>/images/mail_updatesend.JPG" width="96" height="25" onClick="javascript:_modify()" style="cursor:hand"/>&nbsp;<img src="<%=path%>/images/mail_returnbutton.JPG" width="65" height="23" onClick="javascript:_return()" style="cursor:hand"/></td>
          </tr>
   </form>         
      </table></td>
            
    </tr>
    <tr>
    	<td width="11">&nbsp;</td>
        <td width="14" headers="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
        
    </tr>
    
    
    <tr>
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 

<script>
updateInfo();
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
</form>
</body>
</html>

<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@page import="com.icss.oa.filetransfer.handler.AttachHelper"%>

<%

SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);

//为了机关党委的缺省发送而设置

String sun_flag = (String)request.getParameter("sun_flag");
if (sun_flag == null){
	sun_flag = "";
}

%>
<HTML><HEAD><TITLE>文件传递</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/address.js"></SCRIPT>

<script>

function _selectOpen(){
	var swidth = window.screen.width;
	var sheight = window.screen.height;
	var width = 800;
	var height = 600;
	var top = (sheight-height)/2 - 30;
	if (top <0){
		top = 0;
	}
	var left = (swidth-width)/2;
	window.open('<%=request.getContextPath()%>/address/sendfile/selectPersonFrame.jsp','addressbook','width='+width+',height='+height+',left='+left+',top='+top+',scrollbars=yes,resizable=yes');
}
function _selectPerson(){
	document.sendForm.sendType.value = "0";
	_selectOpen();
}
function _selectcc(){
	document.sendForm.sendType.value = "1";
	_selectOpen();
}

function _selectbcc(){
	document.sendForm.sendType.value = "2";
	_selectOpen();
}

function _addPerson_hidden(usernamestring){
	document.sendForm.sendto.value = usernamestring;
	window.bottomto.innerHTML = usernamestring;
	return true;	
}

function _addcc(usernamestring){
	document.sendForm.sendcc.value = usernamestring;
	window.bottomcc.innerHTML = usernamestring;
	return true;	
}

function _addbcc(usernamestring){
	document.sendForm.sendbcc.value = usernamestring;
	window.bottombcc.innerHTML = usernamestring;
	return true;
}

function _addPerson_hidden1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addPerson.value = user_uuid;
	//alert("document.sendForm.addPerson.value "+document.sendForm.addPerson.value);  
}
function _addcc1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addcc.value = user_uuid;
	//alert("document.sendForm.addcc.value "+document.sendForm.addcc.value);
}

function _addbcc1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addbcc.value = user_uuid;
	//alert("document.sendForm.addbcc.value "+document.sendForm.addbcc.value);
}

function check(){
	if(!setPersonToSend()){
		alert("请填写收件人");
		return false;
	}
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

function _sendFile(){
	if(check()){
		window.showAlert.innerHTML = "正在发送文件，请稍等......";
	    //弹出正在发送的提示框
		_showAlertBox('alertbox1','','show');
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFile1Servlet?sun_flag=<%= sun_flag%>";
		document.sendForm.submit();
	}
}

function _saveToDraft(){
	if(check()){
		window.showAlert.innerHTML = "正在保存文件，请稍等......";
	    //弹出正在发送的提示框
		_showAlertBox('alertbox1','','show');
		
		document.sendForm.action = "<%=request.getContextPath()%>/servlet/SaveToDraft1Servlet";
		document.sendForm.submit();
	}
}

function attachfile(){
	getSendHtml();
	document.sendForm.action = "<%=request.getContextPath()%>/servlet/SendFileAttachFileServlet?donext=1";
	document.sendForm.submit();
}

function changeRadioSelected(radioName,radioValue){
	for (var i=0;i<document.sendForm.elements.length;i++) {
		var e=document.sendForm.elements[i];
		if (e.name==radioName){
			if(radioValue == e.value){
				e.click();
			}
		}
	}
}



//修改以后的程序
//发送人span的ID
var urlPath = "<%=request.getContextPath()%>";


function _load(){
<% if("party".equals(sun_flag)) {%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"机关党委\" personName=\"机关党委\" uuid=\"\" person=\"jiguandangwei\" onclick="selectName(this,0);" title=\"所属组织：&#13;&#10;机关党委\"><font face=\"Webdings\" color=\"#009900\">m</font>机关党委,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"机关党委\" personName=\"机关党委\" uuid=\"\"  person=\"jiguandangwei\" onclick="selectName(this,0);" title=\"所属组织：&#13;&#10;机关党委\"><img src=\"/oabase/images/person.gif\">机关党委,</span>";
	}
<%}else if("hr".equals(sun_flag)){%>
	var sendHtml;
	if (showChar){
		sendHtml = "<span class=\"send\" personType=\"0\" department=\"人事局->人才开发服务处\" personName=\"苏成芳\" uuid=\"\" person=\"suchengfang\" onclick=\"selectName(this,0);\" title=\"所属组织：&#13;&#10;人事局->人才开发服务处\"><font face=\"Webdings\" color=\"#009900\">m</font>苏成芳,</span>";
	}else{
    	sendHtml = "<span class=\"send\" personType=\"0\" department=\"人事局->人才开发服务处\" personName=\"苏成芳\" uuid=\"\" person=\"suchengfang\" onclick=\"selectName(this,0);\" title=\"所属组织：&#13;&#10;人事局->人才开发服务处\"><img src=\"/oabase/images/person.gif\">苏成芳,</span>";
	}
<%}else{
	if ("1".equals(request.getParameter("atta"))){
%>
     document.getElementById(sendToId).innerHTML = '<%=session.getAttribute("SendFileSendHtml")%>';
<%	}
}
%>
 
	topic = "";
	<%if(sendFileBean.getTopic()!=null && !("".equals(sendFileBean.getTopic()))){%>
	   topic = "<%=(sendFileBean.getTopic()).substring(0,sendFileBean.getTopic().length()-13)%>";
	<%}
		if ("1".equals(request.getParameter("atta"))){
	%>
	document.getElementById(sendccId).innerHTML = '<%=session.getAttribute("SendFileCcHtml")%>';
	document.getElementById(sendbccId).innerHTML = '<%=session.getAttribute("SendFileBccHtml")%>';
	<%}%>
	document.sendForm.topic.value = topic;
	<%
	 if (sendFileBean.getisRe() == null){
	%>
		document.all("isRe").checked = false;
	<%
	}else{
	%>
		document.all("isRe").checked = true;
	<%}%>
	<%
	 if (sendFileBean.getisSent() == null && request.getAttribute("newSendFile") == null){
	%>
		document.all("isSent").checked = false;
	<%
	}else{
	%>
		document.all("isSent").checked = true;
	<%}%>
	//<%=request.getAttribute("newSendFile")%>
	getAllPerson(0);
	getAllPerson(1);
	getAllPerson(2);
}

</script>


</head>
<body leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif" onload="_load()">
<form name="sendForm" method="post" action="">
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
<textarea name="sendHtml" style="display:none"></textarea><!-- 收件人 -->
<textarea name="ccHtml" style="display:none"></textarea><!-- 抄送 -->
<textarea name="bccHtml" style="display:none"></textarea><!-- 密送 --> 

<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align="center" id="showAlert"></td>
		</tr>
	</table>
</div>      
      
<div align="center" >
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">发送文件</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td nowrap width="15%" height="22" class="text-01"><div align="right">收件人(<font color='red'>*</font>)：</div></td>
                          <td width="2" rowspan="14" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../../filetransfer/userweb/images/bg-18.gif" width="2" height="2"></td>
                          <td width="85%" bgcolor="F2F9FF" class="text-01">
                              <table border="0" cellspacing="0" cellpadding="0"><tr><td style="width:500px"><span id="sendto" class="sendToText" onselectstart="return(false)" state="0">
							  </span></td><td valign="bottom" nowrap style="width:100%"><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(0)" alt="删除" title="删除收件人中选择的人或组织" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,0,'')" alt="展开" title="展开收件人列表" style="cursor: hand">&nbsp;
                              <img src="<%=request.getContextPath()%>/images/filetransfer/button-rece.gif" style="cursor:hand" align="absmiddle"  onClick="javascript:_selectPerson()">
                          	</td></tr></table>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01"><a name="ccBook">主题</a>(<font color='red'>*</font>)：</div></td>
                          <td nowrap bgcolor="F2F9FF" >
                              <input type="text" name="topic"  size="55" class=txt2 maxlength="50">&nbsp;（<font color='red'>少于25个汉字</font>）
                              <input type="checkbox" name="isRe" value="checked1">是否需要回执&nbsp;
                              <input type="checkbox" name="isSent" value="checked2" checked>保存到发件箱
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22" class="text-01"><div align="right">抄送：</div></td>
                          <td bgcolor="F2F9FF" class="text-01">
                              <table border="0" cellspacing="0" cellpadding="0" ><tr><td><span id="sendcc" class="sendToText" onselectstart="return(false)" state="0">
							  </span></td><td valign="bottom" nowrap><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(1)" alt="删除" title="删除抄送中选择的人或组织" style="cursor: hand"><a name="bccBook">&nbsp;</a><img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,1,'ccBook')" alt="展开" title="展开抄送列表" style="cursor: hand">&nbsp;
                              <img src="<%=request.getContextPath()%>/images/filetransfer/button-cc.gif" style="cursor:hand" align="middle"  onClick="javascript:_selectcc()">
                          </td></tr></table>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22" class="text-01"><div align="right">密送：</div></td>
                          <td bgcolor="F2F9FF" class="text-01">
                              <table border="0" cellspacing="0" cellpadding="0" ><tr><td><span id="sendbcc" class="sendToText" onselectstart="return(false)" state="0">
							  </span></td><td valign="bottom" nowrap><img src="<%=request.getContextPath()%>/images/delPerson.gif" onclick="javascript:delSel(2)" alt="删除" title="删除密送中选择的人或组织" style="cursor: hand">&nbsp;<img src="<%=request.getContextPath()%>/images/outspread.gif" onclick="javascript:listState(this,2,'bccBook')" alt="展开" title="展开密送列表" style="cursor: hand">&nbsp;
                              <img src="<%=request.getContextPath()%>/images/filetransfer/button-bcc.gif" style="cursor:hand" align="middle"  onClick="javascript:_selectbcc()">
                          </td></tr></table>
                          </td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">内容概要&#65306;<br>( <font color="red">500字以内</font> )</div></td>
                          <td nowrap bgcolor="F2F9FF" class="text-01"><textarea name="content" cols="70" rows="14" class=txt2><%=sendFileBean.getContent()%></textarea></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>

                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">附加文件：</div></td>
                          <td nowrap height="22" bgcolor="F2F9FF" class="text-01">
                            <table width="90%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="100%" class="lineheigh">
<%
for(int index = 0; index < sendFileBean.filenumber(); index ++){
	AttachFileBean atachFileBean = sendFileBean.getAttachFile(index);
%>
	<nobr><%=atachFileBean.getFileOriginName()%>(<%=AttachHelper.getFileSize(atachFileBean.getFilesize())%>)&nbsp;</nobr>
<%
}
%>
                                </td>
                              </tr>
                              
                          </table></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        </tr>
                        <tr>
                          <td nowrap height="22"><div align="right" class="text-01">文件大小：</div></td>
                          <td nowrap height="22" bgcolor="F2F9FF" class="text-01">
                            <table width="90%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="3%"></td>
                                <td width="17%" align="left"><%=AttachHelper.getFileSize(sendFileBean.getTotleFilesize())%></td>
                                <td width="45%"><img src="<%=request.getContextPath()%>/images/filetransfer/button-addattach.gif" onclick="javascript:attachfile()" style="cursor:hand"></td>
                                <td width="35%"></td>
                              </tr>
                              </table></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
						<tr>
							<td colspan="3" height="35" align="center">
								<img src="<%=request.getContextPath()%>/images/botton-doc_send.gif" style="cursor:hand"   onclick="javascript:_sendFile();">&nbsp;&nbsp;&nbsp;&nbsp;
								<img src="<%=request.getContextPath()%>/images/filetransfer/button-savetoDraft.gif" style="cursor:hand"   onclick="javascript:_saveToDraft();">
							</td>
						</tr>
                        <tr>
                          <td colspan="3" background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="center"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
            </table>
            </td></tr>
          </table><br>

          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">所有接收人</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="15%" height="22" align="right">接收人：</td>
							<td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../../filetransfer/userweb/images/bg-18.gif" width="2" height="2"></td>
							<td width="85%" class="text-01" id="bottomto"></td>
						</tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
						<tr>
							<td align="right" height="22">抄送人：</td>
							<td class="text-01" id="bottomcc"></td>
						</tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
						<tr>
							<td align="right" height="22">密送人：</td>
							<td class="text-01" id="bottombcc"></td>
						</tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" ></td>
                        </tr>
					</table>
					</td></tr>
				</table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="center"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
            </table>
            </td></tr>
          </table><br><br>
</td></tr>
</table>                

</div>
</form>
</body>

</html>
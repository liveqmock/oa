<%@ page contentType="text/html; charset=GBK" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachHelper"%>
<%@	page import="java.text.DecimalFormat"%>  

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
	String topic = sendFileBean.getTopic();
	if((!("".equals(topic)))&&topic!=null){
	    topic = topic.substring(0,topic.length()-13);
}

	//上载后判断是否大于10M
	String error = request.getParameter("error");
%>
<HTML><HEAD><TITLE>文件上载</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<LINK href="<%=request.getContextPath()%>/Style/css_grey.css" rel=stylesheet>
<script>
function _addNext(){
	document.doattach.donext.value="next";
	_submit();
}
function _confirm(){

		document.doattach.donext.value = document.doattach.returnback.value;
		_submit();

}
function _submit(){
	//弹出正在发送的提示框
	_showAlertBox('alertbox4','','show');
	
	document.doattach.action="<%=request.getContextPath()%>/servlet/SendFileUploadAttachServlet";
	document.doattach.submit();
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

function _delAttach(name){
	document.attachForm.action="<%=request.getContextPath()%>/servlet/SendFileDelAttachServlet?attachname="+name;
	document.attachForm.submit();
}
</SCRIPT>

</head>
<BODY leftmargin="10">

<div id="alertbox4" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align=center>正在上传文件，请稍等......</td>
		</tr>
	</table>
</div> 

<div align="center" >
  <table width="500" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
    	<tr>
        <td>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" class="block_title">&nbsp;</td>
              <td class="block_title">附加文件</td>
              <td width="1%" class="block_title"></td>
            </tr>
          </table>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" ></td>
              <td width="100%"  bgcolor="#ffffff">

<table border="0" cellpadding="0" cellspacing="0" width="386" align=center>
<tr>
<td width="386" height="180" valign="top" bgcolor="#FFFFFF">
<br><br>
<!--单击“<FONT  COLOR="#000000">浏览</FONT>”选择文件（<font color='red'>如果你的文件名长于20个中文字符，请改名，否则会被程序截断后再发送！</font>）<P>-->
单击“<FONT  COLOR="#000000">浏览</FONT>”选择文件<P>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td nowrap><label for="attFbutton">查找文件</label>：&nbsp;</td>
<td width="100%" style="padding-right:20px">
<form name="doattach" ENCTYPE="multipart/form-data" method="POST" action="">
<input type="hidden" name="returnback" value="<%=request.getParameter("donext")%>">
<input type="hidden" name="donext">


<input type="file" name="attfile" size="30" tabindex="1" style="width:100%">
</form>
</td>
</tr>
<tr>
<td colspan=2 align=right>&nbsp;<br><img src="<%=request.getContextPath()%>/images/filetransfer/button-confirm.gif" onClick="javascript:_confirm()" style="cursor:hand">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/filetransfer/button-addanother.gif" onClick="javascript:_addNext()" style="cursor:hand"></td>
</tr>

<tr>
<td colspan=2>&nbsp;<br>邮件“<FONT  COLOR="#000000"><%if("".equals(topic)||topic==null){out.println("无标题");}else{out.println(topic);}%></FONT>”当前的附件
</td>
</tr><tr>
<td colspan=2 style="padding-right:20px" align="center">

&nbsp;<br>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#FFFFFF" style="border: 1px solid #A0C6E5;" align="center">
<tr bgcolor="#A0C6E5" >
<td nowrap colspan=2 class="block_title"><font color=black>附件</font></td>
<td align=center nowrap class="block_title"><font color=black>大小</font></td>
<td nowrap class="block_title"><font color=black>删除</font></td>
</tr>
<form name="attachForm"  method="POST" action="">
<input type="hidden" name="delreturn" value="<%=request.getParameter("donext")%>">
<%
for(int index = 0; index < sendFileBean.filenumber();index ++ ){
	AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
%>
<tr>
<td  width="20" valign="top"><img src="<%=request.getContextPath()%>/images/attachfile.gif" width="15" height="12"></td>
<td  width="80%"><%=attachFileBean.getFileOriginName()%></td>
<td  align=right nowrap><%=AttachHelper.getFileSize(attachFileBean.getFilesize())%></td>
<td  align="center"><image name="att0" src="<%=request.getContextPath()%>/images/delFile.gif" onclick="javascript:_delAttach('<%=index%>')"></td>
</tr>
<tr>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
<%
}//end for
%>
</form>
<tr>
<td  colspan="3" align="right">总计&nbsp;<%=AttachHelper.getFileSize(sendFileBean.getTotleFilesize())%></td>
<td >&nbsp;</td>
</tr>
</table>

<table border=0 cellspacing="0" cellpadding="2">
<tr>
<td colspan=3>附件大小限制10&nbsp;MB</td>
</tr>
<tr>
<td>附件使用</td>
<td width="104">
<%
long totalsize = sendFileBean.getTotleFilesize();
DecimalFormat decimalFormat = new DecimalFormat("0.00");
String percentStr = decimalFormat.format((new Long(totalsize).doubleValue()/(10*1024*1024)*100));
%>
<table cellpadding="0" border="0" cellspacing="0" style="background-color:white; border: #104a7b 1px solid; padding-right: 0px; padding-left: 0px; padding-bottom: 1px; padding-top: 1px;" width="100">
<tr>
<td>
<div style="height:5px; width:<%=new Long(totalsize).doubleValue()/(10*1024*1024)*100%>; font-size:2px; background-color:#339933"></div>
</td>
</tr>
</table>
</td>
<td><%=percentStr%>%</td>
</tr>
<tr>
<td colspan="3"></td>
</tr>
</table>

</td>
</tr></table></td>
</tr>
  
</table>
             </td>
              <td width="1" ></td>
            </tr>
          
          </table>
          
        </td>
      </tr>
  </table>
          
</div>
<script language="javascript">
<%
if(error!=null&&"1".equals(error)){
%>
alert("发送的附件总和不能超过10M!");
<% }else if(error!=null&&"2".equals(error)){  %>
alert("发送的附件过大，或是文件路径无效！");
<% } %>
</script>
</body>

</html>
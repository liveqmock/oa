<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Iterator"%>
<%@page import="com.icss.resourceone.message.vo.RoMessageVO"%>
<%@page import="com.icss.resourceone.message.handler.MessageHandler"%>
<%
	//���õ���Ϣ����,
	List list = request.getAttribute("list")==null?new ArrayList():(List) request.getAttribute("list");
	//������Ϣ
	String errorMsg = (String)request.getAttribute("errorMsg") == null?"":(String)request.getAttribute("errorMsg");;
%>
<html>
<script language="javascript">
//��֤����
function _CheckForm(){
	if(document.msgForm.serialindex.value==""){
    	alert("����д��ţ�");
    	document.msgForm.serialindex.focus;
    	return false;
    }
    if(document.msgForm.content.value==""){
    	alert("����д��Ϣ���ݣ�");
    	document.msgForm.content.focus;
    	return false;
    }
    if(isNaN(document.msgForm.serialindex.value)){
    	alert("��ű��������֣�");
    	document.msgForm.serialindex.focus;
    	return false;
    }
	if(document.msgForm.serialindex.value.indexOf(".")!=-1){
    	alert("����д������");
    	document.msgForm.serialindex.focus;
    	return false;
    }
    if(document.msgForm.serialindex.value<0){
    	alert("����д��������");
    	document.msgForm.serialindex.focus;
    	return false;
    }
   	return true;
}

//������Ϣ
function _Add(){
  	if(!_CheckForm()){
    	return;
  	}else{
		document.msgForm.action="<%=request.getContextPath()%>/servlet/ROMessageAddServlet";
		document.msgForm.submit();
	}
}

function _AddSelected() {
	//alert("document.msgForm.content.value = "+document.msgForm.content.value);
	//alert("document.msgForm.selectedcontent.selected = "+document.msgForm.selectedcontent.selected);
	
	var obj1 = document.getElementById("selectedcontent");
	var obj2 = document.getElementById("content");
	//var obj2 = document.getElementById("content");
	//alert("obj1="+obj1.value);
	//alert("obj2="+obj2.value);
	obj2.value=obj2.value + obj1.value;
	//alert(obj2.value);
}

function UpdateApp(messageid,serialindex,ispublish) {
 	
	//��Ϣ����
	document.msgForm.messageid.value = messageid;
	//���
	document.msgForm.serialindex.value = serialindex;
	//�Ƿ���ʾ
	if(ispublish=="0"){
		//����ʾ
		document.msgForm.ispublishChk.checked = false;
		document.msgForm.ispublish.value = ispublish;
	}else{
		//��ʾ
		document.msgForm.ispublishChk.checked = true;
		document.msgForm.ispublish.value = ispublish;
	}
	var content = document.getElementsByName("msg_" + messageid)[0].value;
	//��Ϣ����
	document.msgForm.content.value   = content;	 
}

//�ı䷢��ѡ��
function _changePublish(){
	if(document.msgForm.ispublishChk.checked == true){
		document.msgForm.ispublish.value = "1";
	}else{
		document.msgForm.ispublish.value = "0";
	}
}

function _Update(){
	//if(!document.msgForm.messageid){
		//alert("��ѡ����Ϣ��¼��");
		//return ;
	//}
	//if(!IsRadioChecked(document.msgForm.messageids,"��ѡ����Ϣ��¼��")){
		//return;
	//}
	if(document.msgForm.messageid.value=="-1"){
		alert("��ѡ����Ϣ��¼��");
		return;
	}
	if(!_CheckForm()){
		return ;
	}else{
		document.msgForm.action="<%=request.getContextPath()%>/servlet/ROMessageUpdateServlet";
		document.msgForm.submit();
	}
}

function _Delete(){
	if(document.msgForm.messageid.value=="-1"){
		alert("��ѡ����Ϣ��¼��");
		return;
	}
    if(confirm("ȷ��Ҫɾ��ѡ�����Ϣ��¼��?")){
     	document.msgForm.action="<%=request.getContextPath()%>/servlet/ROMessageDeleteServlet";
     	document.msgForm.submit();
    }
	
}
function _Preview() {
	hide.style.display = "block";
	
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��Ϣ�б�</title>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../include/judge.js"></script>
</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<%	
	out.print("<font color=red>"+errorMsg+"</font>");
%>
<form name="msgForm" method="post" action="">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<input type="hidden" name="messageid" value="-1" >
<input type="hidden" name="ispublish" value="0" >
	<tr>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="2%" background='../images/bg-12.gif'><IMG height=22
					src="../images/bg-10.gif" width=14></TD>
				<TD class=title-05 background='../images/bg-12.gif'>[��Ϣ�б�]</TD>
				<TD width="1%" align="right"><IMG height=22
					src="../images/bg-11.gif" width=20></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
	<tr>
		<td class="dot-border">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr bgcolor="#fffbef" class="title-04">
				<td width="10%" height="22" align="center" class="dot">ѡ��</td>
				<td width="10%" align="center" class="dot">���</td>
				<td width="10%" align="center" class="dot">�Ƿ���ʾ</td>
				<td width="70%" align="center" class="dot-right">��Ϣ����</td>
			</tr>
<%
if (list.size() == 0) {
%>
			<tr bgcolor="#f2f9ff" class="title-04">
				<td height="44" align="center" class="dot-right"
					colspan=6>û����Ϣ��¼��</td>
			</tr>
<%
}

	//����ֵ
	int index = 0;
	//�������е���Ϣ��¼
	for (int i = 0; i < list.size(); i++) {
		//����ֵ��1
		index++;
		RoMessageVO vo = (RoMessageVO) list.get(i);
		//��������ֵ�����б���2�ײ�ͬ�ļ������ɫ
		String color0;
		String color1;
		if (index % 2 == 0) {
			//��1�ֱ���ɫ
			color0 = "#d6e7f7";
			color1 = "#8CC0E8";
		} else {
			//��2�ֱ���ɫ
			color0 = "#f2f9ff";
			color1 = "#8CC0E8";
		}
%>
			<tr bgcolor="<%=color0%>" onMouseOver="this.bgColor='<%=color1%>';" onMouseOut="this.bgColor='<%=color0%>';">
			  <td width="10%" height="22" align="center" class="dot"><input type="radio" name="messageids" value="<%=vo.getMessageid()%>" 					
			  onclick="javascript:UpdateApp('<%=vo.getMessageid()%>','<%=vo.getSerialindex()%>','<%=vo.getIspublish()%>')">	
				  <textarea name="msg_<%=vo.getMessageid()%>" style="display:none"><%=vo.getMessagecontent()%></textarea>
			  </td>
				<td width="10%" align="center" class="dot"><%=vo.getSerialindex()%></td>
				<td width="10%" align="center" class="dot">
<%
	String _ispublish = "δ֪";
	if("0".equals(vo.getIspublish())){
		//������
		_ispublish = "��";
	}else if("1".equals(vo.getIspublish())){
		//����
		_ispublish = "��";		
	}
%>				
				<%=_ispublish%>			  
				</td>
			  <td width="70%" align="center" class="dot-right"><%=vo.getMessagecontent()%></td>
			</tr>
<%
	}
%>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="1%" background="../images/bg-23.jpg"><IMG height=21 src="../images/bg-21.jpg" width=10></TD>
				<TD class=text-01 width="97%" background="../images/bg-23.jpg">&nbsp;</TD>
				<TD width="2%" background="../images/bg-23.jpg" align="right"><IMG height=21 src="../images/bg-22.jpg" width=11></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
</table>
<br>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="2%" background='../images/bg-12.gif'><IMG height=22 src="../images/bg-10.gif" width=14></TD>
				<TD class=title-05 background='../images/bg-12.gif'>[��Ϣ��Ϣ]</TD>
				<TD width="1%" align="right"><IMG height=22 src="../images/bg-11.gif" width=20></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
	<tr>
		<td class="dot-border">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr bgcolor="#fffbef" class="title-04">
				<td height="22" colspan="2" align="center" class="dot-right">&nbsp;&nbsp;</td>
			</tr>
			<tr bgcolor="#f2f9ff">
				<td width="15%" height="22" align="center" class="dot">��� * </td>
				<td width="85%" align="left" class="dot-right">&nbsp;
			  		<input name="serialindex" type="text" class="txt3" size="10" maxlength="100">
			  	</td>
			</tr>
			<tr bgcolor="#f2f9ff">
				<td width="15%" height="22" align="center" class="dot">�Ƿ���ʾ&nbsp;&nbsp;</td>
				<td width="85%" align="left" class="dot-right">&nbsp;
			  		<input type="checkbox" name="ispublishChk" value="checkbox" onClick="_changePublish()">
			  	</td>
			</tr>
			<tr bgcolor="#f2f9ff">
				<td width="15%" height="22" align="center" class="dot">��Ϣ���� * </td>
				<td width="85%" align="center" class="dot-right">&nbsp;
			  	<TABLE cellSpacing=0 cellPadding=0 width="100%" height="22" align=center border=0>
					<TR>
						<TD width="70%" align="center">&nbsp;&nbsp;<input name="content" id="content" type="text" class="txt3" size="100"></TD>
						<TD width="15%" align="center">
						<select name="selectedcontent" id="selectedcontent" style="width:80px">
             				<option value="@CHANNEL@" selected>Ƶ��</option>
             				<option value="@APP@">Ӧ��</option>
             				<option value="@IPADDRESS@">IP��ַ</option>
             				<option value="@USERNAME@">�û���</option>
             				<option value="@LASTTIME@">�ϴε�¼ʱ��</option>
             			</select>  </TD>
						<TD width="15%" align="center"><a href="javascript:_AddSelected()"><img src="../images/botton-add.gif" width="70"  border="0"></a>&nbsp;</TD>
					</TR>
				</TABLE>
             	</td>
             	
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="1%" background="../images/bg-23.jpg"><IMG height=21
					src="../images/bg-21.jpg" width=10></TD>
				<TD class=text-01 width="97%" background="../images/bg-23.jpg"><!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
				</TD>
				<TD width="2%" background="../images/bg-23.jpg" align="right"><IMG height=21 src="../images/bg-22.jpg" width=11></TD>
			</TR>
		</TABLE>
		</td>
	</tr>

</table>

</form>

<%
	MessageHandler handler = new MessageHandler();
	String _curappgrpname = "Ƶ��";
	String _curappname = "Ӧ��";
	String ipAddress = "IP��ַ";
	String username = "�û���";
	String lasttime = "�ϴε�¼ʱ��";
	List msgList = handler.getMessages(_curappgrpname,_curappname,ipAddress,username,lasttime);
	String line = "";
	for(int i = 0; i< msgList.size(); i++){
		RoMessageVO vo = (RoMessageVO)msgList.get(i); 
		String content = vo.getMessagecontent();  
	 	line += content + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	 }
	 //String lines = "";
	 //if(line.length()>6){
 		//lines = line.substring(6);
 	//}else{
 		//lines = line;
 	//}
%>
<table id="hide" style="display:none;" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="2%" background='../images/bg-12.gif'><IMG height=22 src="../images/bg-10.gif" width=14></TD>
				<TD class=title-05 background='../images/bg-12.gif'>[Ԥ��Ч��]</TD>
				<TD width="1%" align="right"><IMG height=22 src="../images/bg-11.gif" width=20></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
	<tr>
		<td class="dot-border">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr bgcolor="#fffbef" class="title-04">
				<td height="22" colspan="2" align="center" class="dot-right">&nbsp;&nbsp;</td>
			</tr>
			<tr bgcolor="#f2f9ff">
				<td width="100%" height="22" align="left" class="dot-right">&nbsp;
			  		<marquee direction="left" scrollamount=1><%=line%></marquee>
			  	</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD width="1%" background="../images/bg-23.jpg"><IMG height=21
					src="../images/bg-21.jpg" width=10></TD>
				<TD class=text-01 width="97%" background="../images/bg-23.jpg"><!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
				</TD>
				<TD width="2%" background="../images/bg-23.jpg" align="right"><IMG height=21 src="../images/bg-22.jpg" width=11></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="50" align="center">
		<table width="400" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr align="center" valign="middle">
				<td width="25%"><a href="javascript:_Add()"><img src="../images/botton-add.gif" width="70" height="22" border="0"></a></td>
				<td width="25%"><a href="javascript:_Update()"><img src="../images/botton-update.gif" width="70" height="22" border="0"></a></td>
				<td width="25%"><a href="javascript:_Delete()"><img src="../images/botton-delete.gif" width="70" height="22" border="0"></a></td>
				<td width="25%"><a href="javascript:_Preview()"><img src="../images/botton-preview.gif" width="70" height="22" border="0"></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
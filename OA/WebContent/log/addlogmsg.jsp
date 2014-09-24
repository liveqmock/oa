<%@ page contentType="text/html; charset=gb2312"%>
<%response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			%>
<%@	page import="com.icss.oa.log.handler.SendFileBean"%>
<%@	page import="com.icss.oa.log.handler.AttachFileBean"%>

<%String sysid = request.getParameter("sysid") == null ? "-1"
					: request.getParameter("sysid");
			//out.print(parentId);
SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);

			%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/include/style.css">
<SCRIPT language=JavaScript
	src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>
<SCRIPT src="index_files/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript
	src="<%=request.getContextPath()%>/include/treeview.js"
	type="text/JavaScript"></SCRIPT>
</HEAD>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>
	/images/bg-08.gif topMargin=5>
<div id="alertbox1"
	style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	bgcolor="#000000">
	<tr bgcolor="#EEFFF7">
		<td height="25" align="center" id="showAlert"></td>
	</tr>
</table>
</div>
<FORM name=form1 action="" method=post>
<input type="hidden" name=sysid value="<%=sysid %>">
<table width="100%" border="0" cellpadding="0" cellspacing="0">

	<tr>
		<td valign="top"
			background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="1%"
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img
					src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif"
					width="14" height="22"></td>
				<td width="97%"
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"
					class="title-05">系统日志</td>
				<td width="2%">
				<div align="right"><img
					src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif"
					width="20" height="22"></div>
				</td>
			</tr>
		</table>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="1"
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img
					src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"
					width="1" height="4"></td>
				<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td
							background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">

							<tr>

								<td width="20%" height="22" class="text-01">
								<div align="right">主题:</div>
								</td>
								<td width="2" rowspan="9" valign="top"
									background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img
									src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"
									width="2" height="2"></td>
								<td width="80%" bgcolor="F2F9FF" class="text-01">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>

										<td>
										<input name="logpheno" type="text"	value="<%=sendFileBean.getLogpheno() %>" size="30"	maxlength=30></td>
										<td class="text-01">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>

							<tr>
								<td height="2"	background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"	class="text-01"></td>
								<td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
							</tr>
							<tr>
								<td width="20%" height="22" class="text-01">
								<div align="right">说明:</div>
								</td>
								<td width="80%" bgcolor="F2F9FF" class="text-01">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>

										<td><input name="logreason" type="text"		value="<%=sendFileBean.getLogreason() %>" size="30"	maxlength=30></td>

										<td class="text-01">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td height="2"	background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"	class="text-01"></td>
								<td	background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
							</tr>
							<tr>
								<td width="20%" height="22" class="text-01">	<div align="right">内容:</div></td>
								<td width="80%" bgcolor="F2F9FF" class="text-01">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>

										<td><input name="loganalyse" type="text"
											value="<%=sendFileBean.getLoganalyse() %>" size="30"
											maxlength=30></td>
										<td class="text-01">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td height="2"	background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"	class="text-01"></td>
								<td	background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
							</tr>
							<tr>
								<td height="22">
								<div align="right" class="text-01">描述:</div>
								</td>

								<td width="30%" bgcolor="F2F9FF">
								<textarea name="logdesc"	cols="50" rows="6">&nbsp;<%=sendFileBean.getLogdesc() == null ? "": sendFileBean.getLogdesc()%></textarea></td>
							</tr>

							<tr>
								<td height="2"
									background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
								<td
									background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"
									bgcolor="F2F9FF" class="text-01"></td>
							</tr>
							<tr>
								<td width="20%" height="22" class="text-01">
								<div align="right">附件:</div>
								</td>
								<td width="80%" bgcolor="F2F9FF" class="text-01">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3%"></td>
										<td width="40%">
										<%for (int index = 0; index < sendFileBean.filenumber(); index++) {
											AttachFileBean atachFileBean = sendFileBean.getAttachFile(index);

										%> <nobr>
										<%=atachFileBean.getFileOriginName()%>&nbsp;</nobr> 
										<%}
										%></td>
										<td width="">
										<img	src="<%=request.getContextPath()%>/images/filetransfer/button-addattach.gif"	onclick="javascript:attachfile()" style="cursor:hand"></td>
										<td width="35%"></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td height="2"
									background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"
									class="text-01"></td>
								<td
									background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
				<td width="1"
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img
					src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"
					width="1" height="4"></td>
			</tr>
		</table>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="1%"
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img
					src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg"
					width="10" height="21"></td>
				<td
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"
					class="text-01">
				<div align="right"></div>
				</td>
				<td width="2%"
					background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg">
				<div align="right"><img
					src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg"
					width="11" height="21"></div>
				</td>
			</tr>
		</table>

		<br>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
				<div align="center"><img
					src="<%=request.getContextPath()%>/images/botton-add.gif"
					onclick="javascript:_add('<%=request.getContextPath()%>','<%=sysid%>')"
					style="cursor:hand"> &nbsp;&nbsp; <img
					src="<%=request.getContextPath()%>/images/botton-return.gif"
					onClick="javascript:_goback('<%=sysid%>')" style="cursor:hand"></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

</table>
</form>
<script language="JavaScript">
 function _add(url,sysid){

	//if(check()){
		//window.showAlert.innerHTML = "正在上载文件，请稍等......";
	    //弹出正在发送的提示框
		//_showAlertBox('alertbox1','','show');
		
		document.form1.action = "<%=request.getContextPath()%>/servlet/AddLogMsgServlet?sysid="+sysid;
		document.form1.submit();
	//}
	
 }
 function check(){
	if(document.form1.logpheno.value == ""){
		alert("请填写故障现象！");
		return false;
	}
	return true;
}
 function _goback(sysid){
     if (parentId == "1")
		 window.location.href = "<%=request.getContextPath()%>/servlet/ShowAddrbookRootFolderServlet";
	 else
		 window.location.href = "<%=request.getContextPath()%>/servlet/ShowAddressbookListServlet?sysid=" + sysid;
 }
 function attachfile(){
	document.form1.action = "<%=request.getContextPath()%>/servlet/AddLogMsgAttachFileServlet?donext=1";
	document.form1.submit();
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
 
 </script>
</body>
</html>










<%@ page contentType="text/html; charset=gb2312"%>
<%

%>
<HTML>
	<HEAD>
		<TITLE>群发短信</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<LINK href="<%=request.getContextPath()%>/include/style.css"
			rel=stylesheet>
		<LINK href="<%=request.getContextPath()%>/include/sms.css"
			rel=stylesheet>
		<link href="../Style/css_grey.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript
			src="<%=request.getContextPath()%>/include/sms.js"></SCRIPT>

		<SCRIPT language=JavaScript
			src="<%=request.getContextPath()%>/include/counter.js"></SCRIPT>

		<script>

		var urlPath = "<%=request.getContextPath()%>";
		
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
function _send(){
	if(check()){
	document.sendForm.action = "<%=request.getContextPath()%>/sms/publicsend.jsp";
	document.sendForm.submit();
	document.sendForm.send.disabled=true; 
	}
}
function check(){

			if(""==document.sendForm.sendtoperson.value)
			{
				alert('请输入手机号');
				document.sendForm.sendtoperson.focus();
			    return false;
			}
			
			if(""==document.sendForm.content.value)
			{
				alert('请输入短信内容');
				document.sendForm.content.focus();
			    return false;
			}
		return true;
}



function max(message)
	{
	var max=200;
	document.getElementById('zishu').innerHTML='您已经输入了<font color=red>'+message.value.length+'</font>字'
	if (message.value.length > max) {
	message.value = message.value.substring(0,max);
	alert("内容不能超过 200 个字!");
	 }
	}
</script>


		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
	</HEAD>
	<body leftmargin="10">
		<FORM name=sendForm action="" method=post>
			<div align="center">

				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td height="10"></td>
					</tr>
				</table>
				<table width="721" cellpadding="0" cellspacing="1"
					class="table_bgcolor">
					<tr>
						<td height="30" class="block_title" colspan="10">
							<div align="center">
								发送手机短信
							</div>
						</td>
					</tr>
					
					<tr>
						<td height="22" nowrap bgcolor="#FFFFFF" class="table_title">
							<div align="right" class="blue3-12-b">
								手机号:
							</div>
						</td>
						<td nowrap bgcolor="#FFFFFF" class="text-01">
							<textarea name="sendtoperson" cols="60" rows="4" class=txt2 >SELECT T.MOBILEPHONE FROM HRPERSON T WHERE T.SEX='女' AND T.MOBILEPHONE IS NOT NULL AND T.PERSONSORT='在职人员'</textarea>
						</td>
						<td bgcolor="#FFFFFF" class="black-12">
							
						</td>
					</tr>
					
					<tr>
						<td height="22" nowrap bgcolor="#FFFFFF" class="table_title">
							<div align="right" class="blue3-12-b">
								输入短信内容:
							</div>
						</td>
						<td nowrap bgcolor="#FFFFFF" class="text-01">
							<textarea name="content" cols="60" rows="8" class=txt2
								onKeyDown='max(this)' onKeyUP='max(this)'></textarea>
						</td>
						<td bgcolor="#FFFFFF" class="black-12">
							<div id='zishu'></div>
							<br>
							如果输入的短信内容超过60个字符，系统将自动分割成多条短信发送。
						</td>
					</tr>
				</table>
				<br>
				<input name="send" type="button" class="word5" value="发送短信"
					onClick="_send()">
			</div>
			<div id=test>
			</div>
		</FORM>
		<SCRIPT LANGUAGE="JavaScript">
			_counter('群发短信');
		</SCRIPT>
	</body>

</HTML>
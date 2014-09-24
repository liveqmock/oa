<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<% 
	String msg =request.getParameter("msg");
%>
<html>
	<head>
		<title>发送系统消息</title>
		<LINK href="<%=request.getContextPath()%>/include/style.css"
			rel=stylesheet />
		<link href="<%=request.getContextPath()%>/Style/css.css"
			rel="stylesheet" type="text/css" />
			<SCRIPT LANGUAGE="JavaScript"
			src="<%=request.getContextPath()%>/tq/js/calendar.js">
			</SCRIPT>
		<script type="text/javascript">
			  function _send()
					{
						  if(document.sysForm.title.value==""){
							    alert("请填写标题！");
							    document.sysForm.title.focus;
							    return false;
							    }
						  if(document.sysForm.endday.value==""){
						  		alert("请选择有效日期!");
						  		return false;
						  		}
						 ok=confirm("是否要发送此系统消息");
						    if(ok){
						     document.sysForm.action="../servlet/SendSysMsgServlet";
						     document.sysForm.submit()
							}
						    else{
						      return false
						    }
					}
					
					function max(message)
							{
								var max=500;
								if (message.value.length > max) {
								message.value = message.value.substring(0,max);
								alert("内容不能超过 500 个字!");
								}
								
							}
					
						  
		window.onload = function(){
			var s='<%=msg%>';
		 	if(s=='error'){
		 	alert("无法发送，请和管理员联系！");
			}
			if(s=='ok'){
			alert('发送成功!');
			}
			}
			</script>
			
			
	</head>
	<body>
		<form name="sysForm" method="post" action="">
			<div align="center">

				<table width="500" cellpadding="0" cellspacing="1"
					class="table_bgcolor" bgcolor="#B9DAF9">
					<tr>

						<td height="30" colspan="5"
							background="<%=request.getContextPath()%>/images/2-title-05.gif"
							bgcolor="#E0EDF8">
							<div align="center">
								<div align="center" class="block_title">
									发送系统消息
								</div>
							</div>
						</td>

					</tr>
					<tr>

						<td height="22" width="26%" align="center" bgcolor="#FFFFFF">
							标题
						</td>
						<td nowrap align="center" bgcolor="#FFFFFF" class="blue3-12">

							<input style="width:100%"
								type="text" name="title" id="title" maxlength=50>
						</td>
					</tr>
					<tr>

						<td align="center" bgcolor="#FFFFFF" class="blue3-12">
							摘要
						</td>
						
						<td align="center" bgcolor="#FFFFFF" class="blue3-12">

							<input style="width:100%"
								type="text" name="digest" id="digest" maxlength=50>
						</td>
					</tr>
					<tr>
					<td height="24" width="26%" align="center" bgcolor="#FFFFFF">
							有效日期
					</td>
					<td align="left" bgcolor="#FFFFFF" class="blue3-12">
					到<input name="endday" type="text" id="endday" title="点击选择" onClick="javascript:ShowCalendar(this)" size="10">
 					<input type="button" name="Submit" value="选 择" onClick="javascript:ShowCalendar(sysForm.endday)">(此日期为系统消息的最后有效日期)
 					</td>
					
					</tr>
					<tr>

						<td align="center" bgcolor="#FFFFFF" class="blue3-12">
							内容
						</td>
						<td align="right" bgcolor="#FFFFFF" class="blue3-12">
							<textarea name="body" cols="50" rows="8" class=txt2  onKeyDown='max(this)' onKeyUP='max(this)'></textarea>
						</td>
					</tr>
					<tr>
						<td height="26" colspan="2" bgcolor="#E0EDF8">
							<br>
						</td>
					</tr>
				</table>

				<img src="<%=request.getContextPath()%>/images/send.gif" border=0
					style="cursor: hand" onclick="javascript:_send()" />
			</div>
		</form>
	</body>

</html>


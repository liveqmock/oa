<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<% 
	String msg =request.getParameter("msg");
%>
<html>
	<head>
		<title>����ϵͳ��Ϣ</title>
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
							    alert("����д���⣡");
							    document.sysForm.title.focus;
							    return false;
							    }
						  if(document.sysForm.endday.value==""){
						  		alert("��ѡ����Ч����!");
						  		return false;
						  		}
						 ok=confirm("�Ƿ�Ҫ���ʹ�ϵͳ��Ϣ");
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
								alert("���ݲ��ܳ��� 500 ����!");
								}
								
							}
					
						  
		window.onload = function(){
			var s='<%=msg%>';
		 	if(s=='error'){
		 	alert("�޷����ͣ���͹���Ա��ϵ��");
			}
			if(s=='ok'){
			alert('���ͳɹ�!');
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
									����ϵͳ��Ϣ
								</div>
							</div>
						</td>

					</tr>
					<tr>

						<td height="22" width="26%" align="center" bgcolor="#FFFFFF">
							����
						</td>
						<td nowrap align="center" bgcolor="#FFFFFF" class="blue3-12">

							<input style="width:100%"
								type="text" name="title" id="title" maxlength=50>
						</td>
					</tr>
					<tr>

						<td align="center" bgcolor="#FFFFFF" class="blue3-12">
							ժҪ
						</td>
						
						<td align="center" bgcolor="#FFFFFF" class="blue3-12">

							<input style="width:100%"
								type="text" name="digest" id="digest" maxlength=50>
						</td>
					</tr>
					<tr>
					<td height="24" width="26%" align="center" bgcolor="#FFFFFF">
							��Ч����
					</td>
					<td align="left" bgcolor="#FFFFFF" class="blue3-12">
					��<input name="endday" type="text" id="endday" title="���ѡ��" onClick="javascript:ShowCalendar(this)" size="10">
 					<input type="button" name="Submit" value="ѡ ��" onClick="javascript:ShowCalendar(sysForm.endday)">(������Ϊϵͳ��Ϣ�������Ч����)
 					</td>
					
					</tr>
					<tr>

						<td align="center" bgcolor="#FFFFFF" class="blue3-12">
							����
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


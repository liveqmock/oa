<%@ page contentType="text/html; charset=gb2312"%>
<%
	Integer isout = new Integer(request.getAttribute("isout")
			.toString());
	Integer sendnumber = new Integer(request.getAttribute("sendnumber")
			.toString());
	Integer selectedNum = new Integer(0);
	if (request.getAttribute("selectedNum") != null) {
		selectedNum = new Integer(request.getAttribute("selectedNum")
				.toString());
	}
	String userlist = (String) request.getAttribute("userlist");
%>
<HTML>
	<HEAD>
		<TITLE>���Ͷ���</TITLE>
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
		
		function _phone(uuid){
		   var xmlhttp;
		   if (window.ActiveXObject) {
		      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		   }else if(window.XMLHttpRequest)  {
		      xmlhttp=new XMLHttpRequest();
		   }
		   if (xmlhttp) {
		     	xmlhttp.open("GET","<%=request.getContextPath()%>/sms/userphone.jsp?uuid="+uuid,true);
		        xmlhttp.send(null);
		   		
		        xmlhttp.onreadystatechange=function () {
		             if(xmlhttp.readyState==4)  {
		             if(xmlhttp.status==200)  {
		                var t=unescape(xmlhttp.responseText);
		                document.getElementById(uuid).title=trim(t);
		                }
		          	}
		       } 
	    	}   
	         
		}
		
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
function _send(){
	if(check()){
	var ele = document.getElementById("sendto");
	tempStr=ele.innerHTML;
	document.all("sendtoperson").value = tempStr;
	document.sendForm.action = "<%=request.getContextPath()%>/servlet/SMSTransformServlet";
	document.sendForm.submit();
	document.sendForm.send.disabled=true; 

	}
}
function check(){
	if(!setPersonToSend()&&""==document.sendForm.phonenums.value){
		alert("����д�ռ���");
		return false;
	}
	if(""!=document.sendForm.phonenums.value){
		var phonenums =document.sendForm.phonenums.value;
		var phonenum=phonenums.split(",");
		var ph="";
		for(i=0;i<phonenum.length;i++){
			j=phonenum[i];
			var reg0 = /^1\d{10}$/;
	        var reg1 = /^0\d{10,11}$/;
	        var my = false;
	        if (reg0.test(j))my=true;
	        if (reg1.test(j))my=true;
	        if (!my){
	        	ph+=","+j;
	            
	        }
       
    }
     	if(""!=ph){
     		alert('�Բ�����������ֻ������У�'+ph.substring(1)+' ����');
     		document.sendForm.phonenums.focus();
	       	return false;
     	}
}
			if(""==document.sendForm.content.value)
			{
				alert('�������������');
				document.sendForm.content.focus();
			    return false;
			}
			
		
	return true;
}

function _selectPerson(){
	document.sendForm.sendType.value = "0";
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

 window.onload= function(){
	 var s=<%=selectedNum%>
	 var n=<%=sendnumber%>
		 if(s>0){
          alert("��ѡ�� "+s+" �˳����涨�� "+n+" �ˣ�������ѡ��");
	 }
  }   


function max(message)
	{
	var max=200;
	document.getElementById('zishu').innerHTML='���Ѿ�������<font color=red>'+message.value.length+'</font>��'
	if (message.value.length > max) {
	message.value = message.value.substring(0,max);
	alert("���ݲ��ܳ��� 200 ����!");
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
			<input type="hidden" name="addPerson_person">
			<input type="hidden" name="addPerson_group">
			<input type="hidden" name="addPerson_oagroup">
			<input type="hidden" name="addPerson_org">
			<input type="hidden" name="sendtoperson">
			<input type="hidden" name="sendMail" value="1">
			<input type="hidden" name="sendType" value="">
			<%
				if (isout.intValue() == 0) {
			%>
			<input type="hidden" name="phonenums" value="">
			<%
				}
			%>

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
								�����ֻ�����
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%" height="22" nowrap bgcolor="#FFFFFF"
							class="table_title">
							<div align="right" class="blue3-12-b">
								<img src="<%=request.getContextPath()%>/images/smssel.gif"
									style="cursor: hand" onClick="javascript:_selectPerson()"
									alt="ѡ��������">

							</div>
						</td>


						<td width="50%" bgcolor="#FFFFFF">
							<div>
								<span id="sendto" class="sendToText"
									onselectstart="return(false)" state="0"> <%
 	if (userlist != null) {
 %>
									<%=userlist%> <%
 	}
 %> </span>
							</div>
						</td>

						<td width="35%" nowrap bgcolor="#FFFFFF">
							<div align="left">
								<img src="<%=request.getContextPath()%>/images/delPerson.gif"
									onclick="javascript:delSel(0)" alt="ɾ��" title="ɾ���ռ�����ѡ����˻���֯"
									style="cursor: hand">
								&nbsp;
								<img src="<%=request.getContextPath()%>/images/outspread.gif"
									onclick="javascript:listState(this,0,'')" alt="չ��"
									title="չ���ռ����б�" style="cursor: hand">
								&nbsp;
								<img src="<%=request.getContextPath()%>/images/smssel.gif"
									style="cursor: hand" onClick="javascript:_selectPerson()"
									alt="ѡ��������">
								&nbsp;
							</div>
						</td>


					</tr>
					<%
						if (isout.intValue() != 0) {
					%>
					<tr>
						<td width="15%" height=22 align=right nowrap bgcolor="#FFFFFF"
							class=table_title>
							�������ֻ���:
						</td>
						<td bgcolor="#FFFFFF">
							<input name="phonenums" size="60">
						</td>
						<td bgcolor="#FFFFFF">
							<span class="STYLE1">�����������","�ֿ�,����13800138000,13800000000</span>��
						</td>
					</tr>
					<%
						}
					%>
					<tr>
						<td height="22" nowrap bgcolor="#FFFFFF" class="table_title">
							<div align="right" class="blue3-12-b">
								�����������:
							</div>
						</td>
						<td nowrap bgcolor="#FFFFFF" class="text-01">
							<textarea name="content" cols="60" rows="8" class=txt2
								onKeyDown='max(this)' onKeyUP='max(this)'></textarea>
						</td>
						<td bgcolor="#FFFFFF" class="black-12">
							<div id='zishu'></div>
							<br>
							�������Ķ������ݳ���60���ַ���ϵͳ���Զ��ָ�ɶ������ŷ��͡�
							<font color=red>(ϵͳ���Զ�Ϊ��ǩ��)</font>
						</td>
					</tr>
				</table>
				<br>
				<input name="send" type="button" class="word5" value="���Ͷ���"
					onClick="_send()">
			</div>
			<div id=test>
			</div>
		</FORM>
		<SCRIPT LANGUAGE="JavaScript">
			_counter('���Ͷ���');
		</SCRIPT>
	</body>

</HTML>
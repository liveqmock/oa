<%@ page contentType="text/html; charset=GBK" %>


<html>
<head>
<%
String isCms = "0";
if(request.getParameter("isCms")!=null)
{
	isCms = request.getParameter("isCms");
}
%>
<title>�ӵ�ַ��ѡ��</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript">
	//-----------------�õ�����Ҫ�Ĳ���---------------------//
	var selectPersonsArray;
	var sendType = window.opener.document.all("sendType").value;
	var sendMail = window.opener.document.all("sendMail").value;
	if (sendType == ""){
		sendType = "0";
	}
	selectPersonsArray = window.opener.getOurPerson(sendType);
	var type = 0;
	window.focus();
	function setLeft(){
		//if (sendMail == "1"){
		//	document.frames.leftFrame.selectOften.style.display = "";
		//}
	}
	//ɾ��ָ�����û� persons��ʽ ��aa��ss��
	function delPersons(persons){
		window.frames['listFrame'].getNoSelectPerson(persons);
	}
	//�����û�
	function setPersons(persons){
		window.frames['listFrame'].setPersons(persons);
	}
	//�����������ʼ�ҳ��
	function setSelect(personList){
		if (sendType == "1"){
			window.opener.selccValue = personList;
			type = 1;
		}else if (sendType == "2"){
			window.opener.selbccValue = personList;
			type = 2;
		}else{
		    
			window.opener.selSendValue = personList;
			type = 0;
		}
		window.opener.loadPerson(type);
	}
	//�ƶ����ڵ���
	function moveFrame(hidden){
		if (hidden != null && hidden){
			document.getElementById('allRight').rows = "*,0";
		}else{
			document.getElementById('allRight').rows = "400,*";
		}
	}
	//�õ�����ѡ��ҳ�����
	function getSelectPerson(loadShow){
		if (loadShow == "1"){
			return selectPersonsArray;
		}else{
			try{
				return window.frames['listFrame'].getAllPerson();
			}catch(e){
				if (loadShow != "1"){
					alert("�Բ���,����ҳ����ص�����,ϵͳ����Ա��ʼ��������״̬.");
				}
				return selectPersonsArray;
			}
		}
		
	}
</script>
</head>
<frameset cols="194,*" frameborder=1 border="0" framespacing="3"  bordercolor="#AED4EE" onload="setLeft()">
  <frame src="<%=request.getContextPath()%>/servlet/SendFileOrgTree2Servlet?isCms=<%=isCms%>" name="leftFrame" scrolling="auto">
	<frameset  id="allRight" rows="*,0" cols="*" frameborder=1 border="0" framespacing="3" bordercolor="#AED4EE">
	 <frame src="<%=request.getContextPath()%>/servlet/SendFileOrgServlet?loadShow=1" name="mainFrame">
	 <frame src="<%=request.getContextPath()%>/address/sendfile/selected.jsp" name="listFrame" id="listFrame">
	</frameset>
	<noframes>
	<body>
	<p>��ҳ��ʹ���˿�ܣ��������������֧�ֿ�ܡ�<br>��ʹ��IE6.0���ϰ汾�����</p>
	</body>
	</noframes>
</frameset>

</html>

<%@ page contentType="text/html; charset=GBK" %>
<%
String sessionname = request.getParameter("sessionname");
System.out.println("selectPersonFramejsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");
String orguuid = request.getParameter("orguuid");
HttpSession tempsession = request.getSession();
tempsession.removeAttribute(sessionname);
%>
<script language="JavaScript">
self.moveTo(100,50)
self.resizeTo(800,650)
</script>
<html>
<head>
<title>�ӵ�ַ��ѡ��2</title>
<script language="JavaScript">
	//-----------------�õ�����Ҫ�Ĳ���---------------------//
	var selectPersonsArray;
	var sendType = window.opener.sendForm.sendType.value;
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
		document.frames.listFrame.getNoSelectPerson(persons);
	}
	//�����û�
	function setPersons(persons){
		document.frames.listFrame.setPersons(persons);
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
			document.frames.allRight.rows = "*,0";
		}else{
			document.frames.allRight.rows = "400,*";
		}
	}
	//�õ�����ѡ��ҳ�����
	function getSelectPerson(loadShow){
		if (loadShow == "1"){
			return selectPersonsArray;
		}else{
			try{
				return document.frames.listFrame.getAllPerson();
			}catch(e){
				if (loadShow != "1"){
					alert("�Բ���,����ҳ����ص�����,ϵͳ����Ա��ʼ��������״̬.");
				}
				return selectPersonsArray;
			}
		}
		
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<frameset cols="194,*" frameborder=1 border="0" framespacing="3"  bordercolor="#AED4EE">
  <frame src="<%=request.getContextPath()%>/servlet/OrgTree4Servlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>&orguuid=<%=orguuid%>&block=ftp" name="leftFrame" scrolling="auto">
	<frameset rows="400,*" cols="*" frameborder=1 border="0" framespacing="3" bordercolor="#AED4EE">
	 <frame src="<%=request.getContextPath()%>/servlet/SelectPersonServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>&block=ftp" name="mainFrame">
	 <frame src="<%=request.getContextPath()%>/servlet/SelectOrgpersonServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>&block=ftp" name="listFrame">
	</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
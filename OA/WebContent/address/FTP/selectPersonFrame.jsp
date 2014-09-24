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
<title>从地址簿选择2</title>
<script language="JavaScript">
	//-----------------得到所需要的参数---------------------//
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
	//删除指定的用户 persons格式 шaaшssш
	function delPersons(persons){
		document.frames.listFrame.getNoSelectPerson(persons);
	}
	//设置用户
	function setPersons(persons){
		document.frames.listFrame.setPersons(persons);
	}
	//返回至发送邮件页面
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
	//移动窗口的桢
	function moveFrame(hidden){
		if (hidden != null && hidden){
			document.frames.allRight.rows = "*,0";
		}else{
			document.frames.allRight.rows = "400,*";
		}
	}
	//得到所有选择页面的人
	function getSelectPerson(loadShow){
		if (loadShow == "1"){
			return selectPersonsArray;
		}else{
			try{
				return document.frames.listFrame.getAllPerson();
			}catch(e){
				if (loadShow != "1"){
					alert("对不起,由于页面加载的问题,系统将人员初始化至加载状态.");
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

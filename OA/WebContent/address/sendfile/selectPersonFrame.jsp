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
<title>从地址簿选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript">
	//-----------------得到所需要的参数---------------------//
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
	//删除指定的用户 persons格式 шaaшssш
	function delPersons(persons){
		window.frames['listFrame'].getNoSelectPerson(persons);
	}
	//设置用户
	function setPersons(persons){
		window.frames['listFrame'].setPersons(persons);
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
			document.getElementById('allRight').rows = "*,0";
		}else{
			document.getElementById('allRight').rows = "400,*";
		}
	}
	//得到所有选择页面的人
	function getSelectPerson(loadShow){
		if (loadShow == "1"){
			return selectPersonsArray;
		}else{
			try{
				return window.frames['listFrame'].getAllPerson();
			}catch(e){
				if (loadShow != "1"){
					alert("对不起,由于页面加载的问题,系统将人员初始化至加载状态.");
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
	<p>此页面使用了框架，但您的浏览器不支持框架。<br>请使用IE6.0以上版本浏览。</p>
	</body>
	</noframes>
</frameset>

</html>

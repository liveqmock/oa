<%@ page contentType="text/html; charset=gb2312" %>

<html>
<head>
<title>选择组织</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT language=JavaScript>
  function _commitOrg(){
    var orgid=document.a.orgId.value;
    var orgname=document.a.orgName.value;
    //调用父页面的javascript
    // var doUrl = "window.top.opener.ss()";
   	//eval(doUrl);
   //  var a=orgid +","+orgname;
   
   
  
   //对父页面操作
	window.top.opener.document.form2.zuzhi2.value=orgname;
	window.top.opener.document.form2.zuzhiId.value=orgid;
    window.top.close();
  }
  
  function _commitPerson(){

	var checkedstr="";
	for (var i=0;i<form1.elements.length;i++){
		var e = form1.elements[i];
		if (e.name == "personname")
			checkedstr = checkedstr + "," + e.value;
	}
	checkedstr = checkedstr.substring(1,checkedstr.length);	
	//alert(checkedstr);
	var doUrl = "window.top.opener._addPerson(checkedstr)";
	eval(doUrl);
	window.top.close();
}
</SCRIPT>
</head>

<%
 //out.print("orgid = "+request.getParameter("orgid"));
//out.print("<br>orgname = "+request.getParameter("orgName"));
%>
<body>
<form name="a">
<input type="hidden" name="orgId" value="<%=request.getParameter("orgid")%>">
<input type="hidden" name="orgName" value="<%=request.getParameter("orgName")%>">
</form>
<SCRIPT language=JavaScript>
  _commitOrg();
</SCRIPT>

</body>
</html>

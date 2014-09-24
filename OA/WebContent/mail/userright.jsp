<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String org=(String)request.getAttribute("org");
	String cur=(String)request.getAttribute("cur");
	String right=(String)request.getAttribute("right");
	String writestr=(String)request.getAttribute("writestr");
	String writeuuid=(String)request.getAttribute("writeuuid");
	String readstr=(String)request.getAttribute("readstr");
	String readuuid=(String)request.getAttribute("readuuid");
%>
<script>
	var flag = "";
	function cancel(){
		window.close();
	}

	function _del(){
		var uuid = _check();
		if(uuid != false){
			document.frm.action = "<%=path%>/servlet/DelRightServlet?org=<%=org%>"
			document.frm.submit();
		}

	}

	function _modify(){
		var uuid = _check();
		if(uuid != false){
			_setdata();
			document.frm.action = "<%=path%>/servlet/MidifyRightServlet?org=<%=org%>"
			document.frm.submit();
		}

	}

	function _openright(th,obj,def){
		if(th.checked == true){
			obj.disabled = false;
		}else{
			obj.disabled = true;
			for(var i=0;i<obj.options.length;i++){
				if(obj.options[i].value == def){
					obj.options[i].selected = true;
				}
			}
		}
	}


	function _setdata(){
		var objs = document.getElementsByName("cheperson");
		var rightval = "";
		for(var i=0;i<objs.length;i++){
			if(objs[i].checked == true){	
				rightval = rightval + "," + eval("document.frm.right"+i).value;
			}
		}
		document.frm.rightval.value = rightval;
	}

	function _check(){
		var n = 0;
		var namestr = "";
		var objs = document.getElementsByName("cheperson");
		for(var i=0;i<objs.length;i++){
			if(objs[i].checked == true){				
				namestr = namestr + "," + objs[i].value;
				n = n + 1;
			}
		}
		if(n == 0){
			alert("请选择要删除的人员");
			return false;
		}
		
		document.frm.choose.value = namestr;
		return namestr;
	}
</script>

<html>
<head>
<title>权限分配</title>
</head>
<body>
<form name="frm" method="post">
<input type="hidden" name="choose" />
<input type="hidden" name="readuuid" value="<%=readuuid%>"/>
<input type="hidden" name="writeuuid" value="<%=writeuuid%>"/>
<input type="hidden" name="cur" value="<%=cur%>">
<input type="hidden" name="org"  value="<%=org%>">
<input type="hidden" name="rightval">
<table border="0" cellpadding="0" cellspacing="0" width="1003">
	<tr><th colspan="6">已分配人员</th></tr>
	<tr><td></td><td>人员</td><td>权限</td><td></td><td>人员</td><td>权限</td></tr>
	<%
		int i=0;
		StringTokenizer readstk = new StringTokenizer(readstr,",");
		StringTokenizer readuuidstk = new StringTokenizer(readuuid,",");
		while(readstk.hasMoreElements() && readuuidstk.hasMoreElements()){
			String name = (String)readstk.nextElement();
			String uuid = (String)readuuidstk.nextElement();
			if(i % 2 == 0){
	%>
	<tr><%}%>
	
	<td><INPUT TYPE="checkbox" NAME="cheperson" value="<%=uuid%>" onclick="javascript:_openright(this,right<%=i%>,2)"></td><td><%=name%></td>
	<td><SELECT NAME="right<%=i%>" disabled>
		<OPTION VALUE="2" SELECTED>只读</OPTION>
		<OPTION VALUE="1" >可写</OPTION>
	</SELECT></td>
	
	<%if(i % 2 != 0){%>
	</tr>
	<%}
		i=i+1;
		}
	%>

	<%

		StringTokenizer writestk = new StringTokenizer(writestr,",");
		StringTokenizer writeuuidstk = new StringTokenizer(writeuuid,",");
		
	
		while(writestk.hasMoreElements() && writeuuidstk.hasMoreElements()){
			String name = (String)writestk.nextElement();
			String uuid = (String)writeuuidstk.nextElement();
	
	%>
	<%if(i % 2 == 0){%><tr><%}%>
	
	<td><INPUT TYPE="checkbox" NAME="cheperson" value="<%=uuid%>" onclick="javascript:_openright(this,right<%=i%>,1)"></td><td><%=name%></td>
	<td><SELECT NAME="right<%=i%>" disabled>
		<OPTION VALUE="2">只读</OPTION>
		<OPTION VALUE="1" SELECTED>可写</OPTION>
	</SELECT></td>
	
	<%if(i % 2 != 0){%></tr><%}%>

	<%
		i=i+1;
		}
	%>

	<tr><td colspan="6" align="center"><INPUT TYPE="button" VALUE="删除人员" ONCLICK="javascript:_del()"><INPUT TYPE="button" VALUE="修改权限" ONCLICK="javascript:_modify()"></td></tr>
</table>

<hr>


<table border="0" cellpadding="0" cellspacing="0" width="1003" align="left">
  <tr>
  	<th colspan="3">分配新权限</th>
  </tr>
  <tr>
  	<td width="18" height="10"></td>
    <td height="10" colspan="2">




	<table>
		<tr><td><input type="checkbox" name="allread" value="1" onclick="javascript:_clear('read')"/>全读</td></td><td><input type="checkbox" name="allwrite" value="1" onclick="javascript:_clear('write')"/>全写</td></td></td>
    	<tr><td>可读人员</td><td><textarea name="read" cols="50" rows="1" readonly></textarea></td></td><td><input type="button" value="选择人员" onclick="javascript:_selectpeople('read')"/></td></tr>
		<tr><td>可写人员</td><td><textarea name="write" cols="50" rows="1" readonly></textarea></td></td><td><input type="button" value="选择人员" onclick="javascript:_selectpeople('write')"/></td></tr>
    </table>

	</td>
    <td width="116" valign="middle">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr><td align="center" width="100%">&nbsp;</td></tr>
            <tr><td height="10"></td></tr>
            <tr><td align="center" width="100%">&nbsp;</td></tr>
      </table>    </td>
    <td width="21" height="10"></td>
  </tr>
  <tr><td colspan="2" align="center"><input type="button" value="提交" onclick="javascript:setright()"/><input type="button" value="关闭" onclick="javascript:cancel()"/></td></tr>
</table>
</form>
<script language="javascript">

	if('<%=right%>' == "1"){
		frm.allread.checked = true;
		frm.allwrite.checked = true;
	}else if('<%=right%>' == "2"){
		frm.allread.checked = true;
	}

function _selectpeople(arg){
	flag = arg;
	if(flag == "read" && frm.allread.checked){
		alert("当前选择全读！");
		return;
	}
	if(flag == "write" && frm.allwrite.checked){
		alert("当前选择全写！");
		return;
	}
	var org = '<%=org%>';
	var URL = '<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame2.jsp?doFunction=_addUser&sessionname=orgAccess&orguuid='+org;
	 window.open(URL,'','width=700,height=550,scrollbars=yes,resizable=yes');
}


function _clear(obj){
	if(obj == "read"){
		frm.read.value="";
	}else{
		frm.write.value="";
	}
}

function setright(){
	frm.action = "<%=path%>/servlet/UserRightServlet";
	frm.submit();
}

function _addUser(msgAccess){
	if(flag == "read"){
		frm.read.value=msgAccess;
	}else if(flag == "write"){
		frm.write.value=msgAccess;
	}
}


function _addUser1(uuid){
	if(flag == "read"){
		frm.readuuid.value=uuid;
	}else if(flag == "write"){
		frm.writeuuid.value=uuid;
	}
}
</script>



</body>
</html>


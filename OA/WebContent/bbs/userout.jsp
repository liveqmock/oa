<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.bbs.vo.BbsOutVO" %>
<%
	String path = request.getContextPath();
	List userlist =request.getAttribute("userlist")!=null ?(List)request.getAttribute("userlist") : new ArrayList();
%>
<html>
<head>
<script>
	var flag = "";
	function cancel(){
		window.close();
	}

	function _del(){
		if(_check()){
			document.sendForm.action = "<%=path%>/servlet/DelUOutServlet"
			document.sendForm.submit();
		}

	}

	function _modify(){
		if(_check()){
			document.sendForm.action = "<%=path%>/servlet/ModifyUOutServlet"
			document.sendForm.submit();
		}

	}

	function _openright(th,a){
		//alert(a);
		var obj = document.getElementById(a);
		//alert(th.checked);
		if(th.checked){
			obj.disabled = false;
		}else{
			obj.disabled = true;
			obj.options[0].selected = true;
			}
		}

	function _check(){
		var objs = document.getElementsByName("cheperson");
		for(var i=0;i<objs.length;i++){
			if(objs[i].checked == true){				
				return true;
			}
		}
		alert("��ѡ����Ա!");
		return false;
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
-->
</style>
<link href="<%=path%>/Style/css_ftp.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<LINK href="/oabase/include/style.css" rel=stylesheet>
<LINK href="/oabase/include/sms.css" rel=stylesheet>
<SCRIPT language=JavaScript src="/oabase/include/sms.js"></SCRIPT>
<title>��̳������Ա</title>
</head>
<body>
<form name="sendForm" method="post">
<input type="hidden" name="addPerson_person">
<input type="hidden" name="addPerson_group">
<input type="hidden" name="addPerson_oagroup">
<input type="hidden" name="addPerson_org">
<input type="hidden" name="sendtoperson">
<input type="hidden" name="sendType" value="0">
<input type="hidden" name="sendMail" value="1">
<%@ include file= "/include/top.jsp" %>

<table border="0" cellpadding="0" cellspacing="0" width="1003">
	<tr><td height="5"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="1" width="983" class="table_bgcolor" align="center">
	<tr><th colspan="6" bgcolor="#FFFFFF">�ѽ�����Ա</th></tr>
	<tr><td class="block_title"></td><td class="block_title">��Ա</td><td class="block_title">ʱ��</td>
	<td class="block_title"></td><td class="block_title">��Ա</td><td class="block_title">ʱ��</td></tr>
	<%
		int i=0;
		for(Iterator it = userlist.iterator(); it.hasNext();){
			BbsOutVO vo = (BbsOutVO)it.next();
			String name = vo.getCnname();
			String uuid = vo.getPersonuuid();
			int a  =(int)( (vo.getTime().getTime()-(new Date()).getTime())/(1000*60*60*24)) +1;
			if(i % 2 == 0){
	%>
	<tr><%}%>
	
	<td align="right" bgcolor="#FFFFFF">
	<INPUT TYPE="checkbox" NAME="cheperson" value="<%=uuid%>" onClick="javascript:_openright(this,'<%=uuid%>')">
	</td>
	<td bgcolor="#FFFFFF" class="message_title"><%=name%></td>
	<td bgcolor="#FFFFFF">
	<SELECT name="<%=uuid%>" id='<%=uuid%>' disabled>
		<OPTION VALUE="<%=a%>" SELECTED><%=a%>��</OPTION>
		<OPTION VALUE="60" >60��</OPTION>
		<OPTION VALUE="30" >30��</OPTION>
		<OPTION VALUE="10" >10��</OPTION>
		<OPTION VALUE="3" >3��</OPTION>
		<OPTION VALUE="1" >1��</OPTION>
	</SELECT></td>
	
	<%if(i % 2 != 0){%>
	</tr>
	<%}
		i++;
	}
	%>

	<tr>
	<td colspan="6" align="center" bgcolor="#FFFFFF">
	<INPUT TYPE="checkbox" id="checkall">ȫѡ	&nbsp;&nbsp;
	<INPUT TYPE="button" VALUE="ɾ����Ա" ONCLICK="javascript:_del()">&nbsp;
	<INPUT TYPE="button" VALUE="�޸�Ȩ��" ONCLICK="javascript:_modify()"></td></tr>
</table>

<hr width="983">

<table border="0" cellpadding="0" cellspacing="0" width="983" align="center">
  <tr>
  	<th colspan="3">ѡ�������Ա</th>
  </tr>
  <tr>
  	<td width="18" height="10"></td>
    <td height="10" colspan="2">
	<table>
	   	<tr>
    	<td class="message_title" align='right'>������Ա:</td>
    	<td width="60%" bgcolor="#FFFFFF">
			<div>
			<span id="sendto" class="sendToText"
									onselectstart="return(false)" state="0">  </span>
			</div>
		</td>
			<td width="25%" nowrap bgcolor="#FFFFFF">
				<div align="left">
					<img src="/oabase/images/delPerson.gif"
						onclick="javascript:delSel(0)" alt="ɾ��" title="ɾ��ѡ����˻���֯"
						style="cursor: hand">
								&nbsp;
					<img src="/oabase/images/outspread.gif"
									onclick="javascript:listState(this,0,'')" alt="չ��"
									title="չ����Ա�б�" style="cursor: hand">
								&nbsp;
					<input type="button" value="ѡ����Ա" onClick="javascript:_selectOpen()"/>
				</div>
		</td>
	</tr>
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
  <tr>
  <td colspan="2" align="center"><input type="button" value="�ύ" onClick="javascript:setright()"/>
  <input type="button" value="�ر�" onClick="javascript:cancel()"/></td></tr>
</table>
</form>
<script language="javascript">

function _selectOpen(){
	var swidth = window.screen.width;
	var sheight = window.screen.height;
	var width = 800;
	var height = 600;
	var top = (sheight-height)/2 - 30;
	if (top <0){
		top = 0;
	}
	var left = (swidth-width)/2;
	window.open('/oabase/address/sendfile/selectPersonFrame.jsp','ѡ����Ա','width='+width+',height='+height+',left='+left+',top='+top+',scrollbars=yes,resizable=yes');
}


function setright(){
    setPersonToSend();
	sendForm.action = "<%=path%>/servlet/AddUOutServlet";
	sendForm.submit();
}

var urlPath = "/oabase"
function _phone(){
return "";
}

document.getElementById("checkall").onclick = function(){
	var objs = document.getElementsByName("cheperson");
		for(var i=0;i<objs.length;i++){
			objs[i].checked = this.checked;	
		}
}
</script>
</body>
</html>


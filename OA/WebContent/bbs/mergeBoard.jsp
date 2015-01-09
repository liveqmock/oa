<%@ page contentType="text/html; charset=GBK" %>


<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<html>
<head>
<title>�»�����̳</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
</head>
<BODY>
<FORM name=form1 action="" method=post>
<table width="90%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#B9DAF9">
			<tr>
				<td width="100%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" colspan=4 height="23">
				<div align="center" class="white2-12-b">�ϲ����</div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=4 height="20"></td>
			</tr>
			<%Connection conn = null;
try {
	conn =
		DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("mergeBoard.jsp");
	BBSHandler handler = new BBSHandler(conn);
	List arealist = handler.getSubareaList();
	List boardlist = new ArrayList();
	if (arealist != null && arealist.size() > 0) {
		Iterator AItr = arealist.iterator();
		Iterator BItr = arealist.iterator();
%>
			<tr>
				<td bgcolor="#FFFFFF" class="blue2-12" width="20%" align="right"><font
					color='red'><b>��</b>&nbsp;</font></td>
				<td width="80%" colspan=3 bgcolor="#FFFFFF" class="blue2-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select name="areaA" class="blue2-12" onchange="_getBoard('1')">
					<%while (AItr.hasNext()) {
	BbsAreaVO aVO1 = (BbsAreaVO) AItr.next();%>
					<option value="<%=aVO1.getAreaid()%>" class="text-01"><%=aVO1.getAreaname()%></option>
					<%}%>
				</select>&nbsp;ר���е�&nbsp; <select name="boardA" class="blue2-12">
				</select>&nbsp;���</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" class="blue2-12" align="right"><font
					color='red'><b>�ϲ���</b>&nbsp;</font></td>
				<td colspan=3 bgcolor="#FFFFFF" class="blue2-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select name="areaB" class="blue2-12" onchange="_getBoard('2')">
					<%while (BItr.hasNext()) {
	BbsAreaVO aVO2 = (BbsAreaVO) BItr.next();%>
					<option value="<%=aVO2.getAreaid()%>" class="text-01"><%=aVO2.getAreaname()%></option>
					<%}%>
				</select>&nbsp;ר���е�&nbsp; <select name="boardB" class="blue2-12">
				</select>&nbsp;���
				<td>
			</tr>
			<%} else {%>
			<tr>
				<td bgcolor="#FFFFFF" colspan="4" width="100%" heigth="30"
					align="center" class="blue2-12">û��ר��</td>
			</tr>

			<%}%>
			<tr>
				<td bgcolor="#FFFFFF" colspan=4 align="center" class="blue2-12"><img
					src="<%=request.getContextPath()%>/images/bbs/button-confirm.gif"
					onclick="javascript:_mergeBoard('<%=request.getContextPath()%>')"
					style="cursor: hand"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">
var xmlhttp;
function loadXMLDoc(url)
{
	xmlhttp = null;
	if (window.XMLHttpRequest) {// code for all new browsers
		xmlhttp = new XMLHttpRequest();
	} 

	if (xmlhttp != null) {
		xmlhttp.onreadystatechange = state_Change;
		xmlhttp.open("GET", url, false); //falseΪͬ��������ȡ����ר����ģ��������⡣
		xmlhttp.send();
	}
}

function state_Change() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		var xmlDoc = xmlhttp.responseXML;
		//if(xmlDoc.childNodes[0] != null){
			var elements = xmlDoc.getElementsByTagName("Oneboard");
			for(i=0;i<elements.length;i++){
				var oOption = document.createElement('OPTION');
				oOption.text = elements[i].getElementsByTagName("boardname")[0].firstChild.nodeValue;
				oOption.value = elements[i].getElementsByTagName("boardid")[0].firstChild.nodeValue;
				listboard.options.add(oOption);
			}
		//}
	}
}

function _getBoard(num) {
	var list;
	if(num=='1'){
		listarea = document.form1.areaA;
		listboard = document.form1.boardA;
	}else if(num=='2'){
		listarea = document.form1.areaB;
		listboard = document.form1.boardB;	
	}
	
	var i;
	//ɾ���û��б����������������ȫѡ�Ϳ��
	for(i=listboard.length-1;i>=0;--i)
	{
		listboard.options.remove(i);
	}

	//���ѡ����Ϊ�գ������κα仯
	//if(form1.org.options(form1.org.selectedIndex).text=="")
	//	return true;

	
	//�õ�XML�ĵ�
	var sURL ="./boardxml.jsp?areaid=" + listarea.options[listarea.selectedIndex].value;
	if (window.ActiveXObject){
		var XMLDoc = new ActiveXObject("MSXML");
		//����XML�ĵ�
		XMLDoc.url = sURL;
		var boardRoot = XMLDoc.root;
		//����ڵ㲻Ϊ�գ����û��б��в����û�
		if(boardRoot.children != null)
		{
			for(i=0;i<boardRoot.children.length;i++)
			{
				bOption = document.createElement('OPTION');
				bOption.value = boardRoot.children.item(i).children.item("BoardId").text;
				bOption.text = boardRoot.children.item(i).children.item("BoardName").text;
				listboard.options.add(bOption);
			}
		}
	}
	else{
			loadXMLDoc(sURL);
	}
}

function _mergeBoard(url){
	if(document.form1.boardA.value == ""||document.form1.boardB.value == ""){
		alert("ר����û�а�飬���ܺϲ���");
		return false;
	}
	if(document.form1.boardA.value == document.form1.boardB.value){
		alert("��ѡ����������ͬ�İ�飡������ѡ��");
		return false;
	}
    if(confirm(document.form1.boardA.options(form1.boardA.selectedIndex).text
		+"��齫��ɾ���������������ӽ���ת�Ƶ�"+document.form1.boardB.options(form1.boardB.selectedIndex).text+"����£���ȷ��Ҫ��������")){
		document.form1.action=url+"/servlet/MergeBoardServlet";
		document.form1.submit();
		return true;
	}

}

_getBoard('1');
_getBoard('2');

</SCRIPT>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("mergeBoard.jsp");
		}
	} catch (Exception e) {
	}
}
%>
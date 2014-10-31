<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.address.handler.*" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.common.log.ConnLog" %>

<%
String orguuid = request.getAttribute("_orguuid")==null?"":(String)request.getAttribute("_orguuid");
String loadShow = (String)request.getAttribute("loadShow");
%>
<HTML>
<HEAD>
<TITLE>�Ӹ�����ѡ��</TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>
	
</HEAD>
<SCRIPT language=JavaScript>
<!--

function changeOffice2() {
	var i;
	//ɾ���û��б����������������ȫѡ�Ϳ��
	for(i=form1.orgperson.options.length-1;i>=0;--i){
		form1.orgperson.options.remove(i);
	}
	//���ѡ����Ϊ�գ������κα仯
	if(form1.org.options(form1.org.selectedIndex).text==""){
		return true;
	}
	//�õ�XML�ĵ�
	var oXMLDoc = new ActiveXObject("MSXML");
	var sURL = "../address/pubaddress/newxml.jsp?orguuid=" + form1.org.options(form1.org.selectedIndex).value;
	//����XML�ĵ�
	oXMLDoc.url = sURL;
	var oRoot=oXMLDoc.root;
	//����ڵ㲻Ϊ�գ����û��б��в����û�
	if(oRoot.children != null){
		for(i=0;i<oRoot.children.length;i++){
			oOption = document.createElement('OPTION');
			oOption.text = oRoot.children.item(i).children.item("Truename").text;
			oOption.value = oRoot.children.item(i).children.item("Username").text;
			//�����ź�UUID����
			oOption.setAttribute("department",form1.org.options(form1.org.selectedIndex).text);
			oOption.setAttribute("uuid",oRoot.children.item(i).children.item("PersonUuid").text);
			oOption.setAttribute("personType","0");
			form1.orgperson.options.add(oOption);
		}
	}
}
function changeOffice() {
		document.form1.action="<%=request.getContextPath()%>/servlet/OrgServlet?orguuid="+form1.org.options(form1.org.selectedIndex).value;
		document.form1.submit();
}
function B_user_addall_onclick() {
	var i;
	var flag;			
	var selected_option;	//OPTION����
	var selected_option1;	//OPTION����
	var persons = new Array();
	for (i=0;i<form1.orgperson.length;i++) {
	
		//�ж��Ƿ�������
		flag=true;		//���ñ�־
		for (var j=0; j<form1.selectedperson.options.length; j++) {
			if (form1.selectedperson.options(j).value == form1.orgperson.options(i).value) 
				flag=false
		}	
		
		if (flag){
				selected_option = document.createElement("OPTION");
				selected_option.value = form1.orgperson.options(i).value;
				selected_option.text = form1.orgperson.options(i).text;
				//�����ż���
				selected_option.setAttribute("department",form1.orgperson.options(i).getAttribute("department"));
				selected_option.setAttribute("uuid",form1.orgperson.options(i).getAttribute("uuid"));
				selected_option.setAttribute("personType",form1.orgperson.options(i).getAttribute("personType"));
				form1.selectedperson.add(selected_option);
				var onePerson = new Array();
				onePerson["name"] = form1.orgperson.options(i).text;
				onePerson["value"] = form1.orgperson.options(i).value;
				onePerson["department"] = form1.orgperson.options(i).getAttribute("department");
				onePerson["uuid"] = form1.orgperson.options(i).getAttribute("uuid");
				onePerson["type"] = form1.orgperson.options(i).getAttribute("personType");
				persons[persons.length] = onePerson;
			}
	}
	window.top.setPersons(persons);
	for (i=form1.orgperson.length;i>=0;i--) {
		form1.orgperson.remove(i);
	}
	return true;
}
/**
 * ��������
 */
function _loadData(){
	var selectPersonArray = window.top.getSelectPerson("<%=loadShow%>");
	for (var i=0;i<selectPersonArray.length;i++){
		var selected_option = document.createElement("OPTION");
		selected_option.text = selectPersonArray[i]["name"];
		selected_option.value = selectPersonArray[i]["value"];
		selected_option.setAttribute("department",selectPersonArray[i]["department"]);
		selected_option.setAttribute("uuid",selectPersonArray[i]["uuid"]);
		selected_option.setAttribute("personType",selectPersonArray[i]["type"]);
		form1.selectedperson.add(selected_option);
	}
}

function B_user_add_onclick() {
	var index;				//��ѡ�б��ѡ�������
	var selected_option;	//OPTION����
	var selected_option1;	//OPTION����
	index = form1.orgperson.selectedIndex;
	if (index == -1) {
		//alert("û��ѡ��Ҫ��ӵ��û���");
		return false;
	}
	for (var i=0; i<form1.selectedperson.options.length; i++) {
		if (form1.selectedperson.options(i).value == form1.orgperson.options(index).value) {
			alert("��ӵ���Ա�Ѿ����ڣ������ظ����");
			return false;
		}
	}
	selected_option = document.createElement("OPTION");
	selected_option.value = form1.orgperson.options(index).value;
	selected_option.text = form1.orgperson.options(index).text;
	//�����ż���
	selected_option.setAttribute("department",form1.orgperson.options(index).getAttribute("department"));
	selected_option.setAttribute("uuid",form1.orgperson.options(index).getAttribute("uuid"));
	selected_option.setAttribute("personType",form1.orgperson.options(index).getAttribute("personType"));
	
	form1.selectedperson.add(selected_option);		
	
	//�����ݷŵ�select.jsp����
	var persons = new Array();
	var onePerson = new Array();
	onePerson["name"] = form1.orgperson.options(index).text;
	onePerson["value"] = form1.orgperson.options(index).value;
	onePerson["department"] = form1.orgperson.options(index).getAttribute("department");
	onePerson["uuid"] = form1.orgperson.options(index).getAttribute("uuid");
	onePerson["type"] = form1.orgperson.options(index).getAttribute("personType");
	persons[0] = onePerson;
	window.top.setPersons(persons);
	form1.orgperson.remove(index);
	
	return true;			
}
function B_user_del_onclick() {
	var index;				//��ѡ�б��ѡ�������
	var selected_option;	//OPTION����
	index = form1.selectedperson.selectedIndex;
	if (index == -1) {
		alert("û��ѡ��Ҫɾ�����û���");
		return false;
	}
	selected_option = document.createElement("OPTION");
	selected_option.value = form1.selectedperson.options(index).value;
	selected_option.text = form1.selectedperson.options(index).text;
	selected_option.setAttribute("department",form1.selectedperson.options(index).getAttribute("department"));
	selected_option.setAttribute("uuid",form1.selectedperson.options(index).getAttribute("uuid"));
	selected_option.setAttribute("personType",form1.selectedperson.options(index).getAttribute("personType"));
	
	
	//��select.jspͬ��ɾ����ֵ
	var delStr = "��" + form1.selectedperson.options(index).value + "��";
	window.top.delPersons(delStr);
	form1.orgperson.add(selected_option);
	form1.selectedperson.remove(index);
	return true;
}
function B_user_delall_onclick() {
	var i;
	var selected_option;	//OPTION����
	var delStr = "��";
	for (i=0;i<form1.selectedperson.length;i++) {
		selected_option = document.createElement("OPTION");
		selected_option.value = form1.selectedperson.options(i).value;
		selected_option.text = form1.selectedperson.options(i).text;
		selected_option.setAttribute("department",form1.selectedperson.options(i).getAttribute("department"));
		selected_option.setAttribute("uuid",form1.selectedperson.options(i).getAttribute("uuid"));
		selected_option.setAttribute("personType",form1.selectedperson.options(i).getAttribute("personType"));
		delStr += form1.selectedperson.options(i).value + "��";
		form1.orgperson.add(selected_option);
	}
	//��select.jspͬ��ɾ����ֵ
	window.top.delPersons(delStr);
	for (i=form1.orgperson.length;i>=0;i--) {
		form1.selectedperson.remove(i);
	}
	return true;
}


function adduser_ondblclick() {
	B_user_add_onclick();
}

function deluser_ondblclick() {
	B_user_del_onclick();
}


//��������
function IndexEntryOnKeyUp(indexcontainer,IndexEntry) { //text typed -> select closest listbox entry 
 try{
    var oList = document.getElementById(indexcontainer); 
    var ListLen = oList.length; 
    var iCurSel = oList.selectedIndex; 
    var text = document.getElementById(IndexEntry).value.toUpperCase(); 
    var TextLen = text.length; 
    for (var i = 0; i < ListLen; i++) { 
      var listitem = oList.item(i).text.substr(0, TextLen).toUpperCase(); 
      if (listitem == text) { 
        if (i != iCurSel) oList.selectedIndex = i; 
        break; 
      } 
    } 
    return(true); //do not gobble keypress 
    }catch(e){
    return(false);
    }
  } 
    function KeyDownEvent0(event,indexcontainer,IndexEntry) {
    if ((event.which && event.which==13) || (event.keyCode && event.keyCode==13)) {
      IndexListOnChange(indexcontainer,IndexEntry);  //update entry field to list box selected text
      changeOffice2();
      document.getElementById(indexcontainer).focus();
      return(false);        //gobble text
      } else return(true);
  }
    function KeyDownEvent(event,indexcontainer,IndexEntry) {
    if ((event.which && event.which==13) || (event.keyCode && event.keyCode==13)) {
      IndexListOnChange(indexcontainer,IndexEntry);  //update entry field to list box selected text
      var iSelect = document.getElementById(indexcontainer).selectedIndex;
      if (iSelect != -1){
      adduser_ondblclick();
      }
      return(false);        //gobble text
      } else return(true);
  }
  function IndexListOnChange(indexcontainer,IndexEntry) { //Listbox select -> Put selected text into Entry field
    var iSelect = document.getElementById(indexcontainer).selectedIndex;
    var text = ""
    if (iSelect != -1){
	    text = document.getElementById(indexcontainer).item(iSelect).text;
    }
    text = escape(text);    text = text.replace(/^(%A0)+/g, "");
    text = unescape(text);  text = text.replace(/^\s+/g, "");
    document.getElementById(IndexEntry).value = text;
  }

function _OnUpdateParameter(_orguuid){
	var indexObj = document.getElementById("indexcontainer");
	for (var i=0;i<indexObj.length;i++){
		if (indexObj.item(i).value == _orguuid){
			indexObj.selectedIndex = i;
			IndexListOnChange('indexcontainer','IndexEntry');
			changeOffice2();
			break;
		}
	}
}

//�޸���Ϣ,����javascript



//-->
</SCRIPT>

<BODY bgcolor="#eff7ff" onload="window.top.moveFrame(true);_loadData();_OnUpdateParameter('<%=orguuid%>');">
<form name="form1">
<table width="100%">
	<tr>
		<td class="title-04">&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>
		<TABLE width="540" align=center border=0 cellspacing="0"
			cellpadding="0">
			
			<tr>
				<td width="25%" align="left" ><FONT color="red">[��ģ����ѯ]</FONT></td>
				<td width="35%" align="left"><FONT color="red">[��ģ����ѯ]</FONT></td>
				<td align="left"></td>
			</tr>
			
			<tr>
				<td width="25%" id="innersort" align="left">�ּ���λ�б�:</td>
				<td width="35%" id="innersort" align="left">�ּ���λ��Ա�б�:</td>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ѡ����Ա�б�:</td>
			</tr>
			
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<TABLE width="540" align=center border=0 cellspacing="0"
			cellpadding="0">
			<tr>
				<td width="25%" align="left"><input type="text" tabindex="1" id="IndexEntry"
					class="docstyle" style="width: 125px"
					onkeydown="javascript:return(KeyDownEvent0(event,'indexcontainer','IndexEntry'));"
					onkeyup="javascript:return(IndexEntryOnKeyUp('indexcontainer','IndexEntry'));"></td>
				<td width="35%" align="left"><input type="text" id="IndexEntry1" tabindex="3"
					class="docstyle" style="width: 130px"
					onkeydown="javascript:return(KeyDownEvent(event,'indexcontainer1','IndexEntry1'));"
					onkeyup="javascript:return(IndexEntryOnKeyUp('indexcontainer1','IndexEntry1'));"></td>
				<td align="left" width="40%"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>

		<td width="50%" align="center" height="410">
		<TABLE width=470 align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top width="100%" height="399">
					<DIV align=right><%Connection conn = null;
List orglist = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection("jdbc/OABASE");
	ConnLog.open("selectbyorg.jsp");
	SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
	orglist = handler.getOrgByLevel1(3);

} catch (Exception ex) {
	ex.printStackTrace();
} finally {
	if (conn != null) {
		conn.close();
		ConnLog.close("selectbyorg.jsp");
	}

}
%> <SELECT style="FONT-SIZE: 9pt; WIDTH: 130px" name=org size="30"
						id="indexcontainer" tabindex="2"
						ondblclick="javascript:return(true);"
						onclick="javascript:changeOffice2();"
						onkeydown="javascript:return(KeyDownEvent0(event,'indexcontainer','IndexEntry'));"
						onChange="IndexListOnChange('indexcontainer','IndexEntry');changeOffice2();">
						<%if (orglist != null) {
	Iterator iter = orglist.iterator();
	int i = 0;

	while (iter.hasNext()) {
		
		++i;
		SysOrgVO vo = (SysOrgVO) iter.next();
%>
						<OPTION value="<%=vo.getOrguuid()%>"><%=vo.getCnname()%></OPTION>
						<%}
//0408sunchuantingΪ�˽��������ȥ��������%>
						<option
							value="4161cca6-fc3557c6aa-97fe05e58eef24f3370b74f3cc7c23fc">�������</option>
						<%}
%>
					</SELECT></DIV>
					</TD>
					<td valign=center height="399">
					<p></p>
					</td>
					<TD vAlign=top width="100%" height="399">

					<DIV align=right>
						<SELECT style="FONT-SIZE: 9pt; WIDTH: 130px"
							tabindex="4" name=orgperson size="30" language=javascript
							id="indexcontainer1"
							ondblclick="javascript:return adduser_ondblclick();"
							onkeydown="javascript:return(KeyDownEvent(event,'indexcontainer1','IndexEntry1'));"
							onChange="IndexListOnChange('indexcontainer1','IndexEntry1')">
						</SELECT>
					</DIV>

					</TD>
					<td valign=center height="399">
					<p><img
						src="<%=request.getContextPath()%>/images/address/addall.gif"
						onClick="return B_user_addall_onclick()" hspace="10"
						style="cursor: hand" border=0 align="absmiddle"
						alt="��������û�&gt;&gt;"></p>
					<p><img src="<%=request.getContextPath()%>/images/address/add.gif"
						onClick="return B_user_add_onclick()" hspace="10"
						style="cursor: hand" border=0 align="absmiddle" alt="���ѡ���û�&gt;"></p>
					<p><img src="<%=request.getContextPath()%>/images/address/del.gif"
						onClick="return B_user_del_onclick()" hspace="10"
						style="cursor: hand" border=0 align="absmiddle" alt="&lt;ɾ��ѡ���û�"></p>
					<p><img
						src="<%=request.getContextPath()%>/images/address/delall.gif"
						onClick="return B_user_delall_onclick()" hspace="10"
						style="cursor: hand" border=0 align="absmiddle"
						alt="&lt;&lt;ɾ�������û�"></p>
					<p></p>
					</td>
					<TD vAlign=top width="100%" height="399">
					<DIV align=right><SELECT style="FONT-SIZE: 9pt; WIDTH: 130px"
						name=selectedperson size="30" LANGUAGE=javascript
						ondblclick="return deluser_ondblclick()">

					</SELECT></DIV>
					</TD>
					<TD vAlign=center height="399">
					<P></P>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
		</tr>
		</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			id="AutoNumber2" height="65" align="center" valign="bottom">
			<tr>
				<td align="center" class="text-01"><img
					src="<%=request.getContextPath()%>/images/botton-ok.gif"
					onClick="javascript:setSelectUserByOrg()" hspace="10"
					style="cursor: hand" border=0 align="middle" alt="">
				</td>
			</tr>
		</table>
		</form>
</BODY>
</HTML>

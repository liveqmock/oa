<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfomainVO"%>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfoVO"%>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfotypeVO"%>

<%System.out.println("����dutyEditFCK.jsp11111"); %>
<%List typeList = new ArrayList();
//System.err.println("typeList1="+typeList.size());
typeList = (List) request.getAttribute("typeList") == null ? new ArrayList() : (List) request.getAttribute("typeList");
//System.err.println("typeList=" + typeList.size());
List list = new ArrayList();
Map map1 = new HashMap();
for (int k = 0; k < typeList.size(); k++) {
	TbRhWorkinfotypeVO vo2 = (TbRhWorkinfotypeVO) typeList.get(k);
	list.add(vo2.getWitId().toString());
	map1.put(vo2.getWitId(),vo2.getWitName());
}
List dutyInfoList = (List) request.getAttribute("dutyInfoList") == null ? new ArrayList() : (List) request.getAttribute("dutyInfoList");
Map map = (Map) request.getAttribute("map") == null ? new HashMap() : (Map) request.getAttribute("map");
TbRhWorkinfomainVO mainvo =
	(TbRhWorkinfomainVO) request.getAttribute("vo") == null ? new TbRhWorkinfomainVO() : (TbRhWorkinfomainVO) request.getAttribute("vo");

		String 	lastEditer=	request.getAttribute("lastEditer")==null?"":(String)request.getAttribute("lastEditer");
		String 	lastIP = 	request.getAttribute("lastIP")==null?"":(String)request.getAttribute("lastIP");
		String 	noediter  =	request.getAttribute("noediter")==null?"":(String)request.getAttribute("noediter");

%>
<html>
<head>
<title>������Ϣ�Ǽ�</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT language=JavaScript
	src="/oabase/zbs/include/formverify/extendString.js"></SCRIPT>
<SCRIPT language=JavaScript
	src="/oabase/zbs/include/formverify/formVerify.js"></SCRIPT>
<SCRIPT language=JavaScript
	src="<%=request.getContextPath()%>/zbs/include/formverify/runFormVerify.js"></SCRIPT>
<SCRIPT language=JavaScript src="/oabase/zbs/include/js/common.js"></SCRIPT>



<script type="text/javascript" src="<%=request.getContextPath()%>/fckeditor/fckeditor.js"></script> 

<!--<link rel="stylesheet" href="/oabase/zbs/include/style.css">-->
<link href="/oabase/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	color: #FFFFFF;
	font-weight: bold;
}

body {
	background-image: url();
}

.style2 {
	color: #FFFFFF
}
-->
</style>
<style type="text/css">
.testDiv {
	color: black;
	border-style: solid;
	border-width: 0px;
	border-color: red;
	background: white;
	position: relative; /* λ�� ���Ի�������������һ��Ԫ�� relative absolute */
	top: 0px;
	left: 0px;
	height: 370px; /* ��������ĸ�*/
	width: 100%;
	/*   ������ */
	/*   overFlow: auto ; */ /* visible ��ȴʡ�� hidden auto scroll */
	overFlow-x: auto;
	overFlow-y: auto;
	/*   ��������ص���ɫ���� */
	scrollBar-face-color: #fffbef; /* ���� */
	scrollBar-hightLight-color: red; /* ���� */
	scrollBar-3dLight-color: orange; /* 3ά���� */
	scrollBar-darkshadow-color: white; /* ��Ӱ    */
	scrollBar-shadow-color: yellow; /* ��Ӱ   */
	scrollBar-arrow-color: blue; /* ��ͷ */
	scrollBar-track-color: white; /* ������ɫ */
	scrollBar-base-color: #fffbef; /* ��Ҫ��ɫ */
	font-size: 12;
}

.STYLE3 {
	color: #006699
}

.STYLE5 {
	color: #006699;
	font-size: 12px;
	line-height:24px;
}
</style>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script language="javascript">

var num=0;
var temp = <%=list.size()%>

function getEditorHTMLContents(EditorName) { 
    var oEditor = FCKeditorAPI.GetInstance(EditorName); 
    return(oEditor.GetXHTML(true)); 
}

function getEditorTextContents(EditorName){
	 var oEditor = FCKeditorAPI.GetInstance(EditorName); 
	 return(oEditor.EditorDocument.body.innerText);
}
function FCKeditor_OnComplete(editorInstance) {

	num++;
	if(num==temp){
		 sel('0');
	}
 }

function _return(){
	document.form1.action="/oabase/servlet/RhEditFCKReturnServlet";
	document.form1.submit();
}

function sel(actindex){
	<%for (int k = 0; k < typeList.size(); k++) {
	TbRhWorkinfotypeVO vo = (TbRhWorkinfotypeVO) typeList.get(k);
	Integer id = vo.getWitId();
	String ids = id.toString();%>
	if(actindex==<%=ids%>){				
		<%for (int n = 0; n < list.size(); n++) {
	if (!ids.equals(list.get(n))) {%>
		changeBlackColor(document.getElementById("font_"+<%=list.get(n)%>));
		document.getElementById('type_div_<%=list.get(n)%>').style.display="none";
		<%}
	}%>
		document.getElementById('quanbu').style.display="none";
		changeBlackColor(font_0);
		document.getElementById('type_div_<%=ids%>').style.display="block";
		changeRedColor(document.getElementById("font_"+<%=ids%>));
		//alert('type_div_<%=ids%>');
	}
	<%}%>
	if(actindex==0){
		//_HightLight("ȫ��");	
		var content = "";	
		changeRedColor(font_0);		
		<%for (int n = 0; n < list.size(); n++) {%>
		<% 
		String dsa = "<a style=text-decoration:none href=javascript:sel("+list.get(n)+")><p style='font-size:16pt;color:red'><b> ��"+(String)map1.get(new Integer((String)list.get(n)))+"</b></p></a><br>";
		%>
		changeBlackColor(document.getElementById("font_"+<%=list.get(n)%>));
		content += ""+"<%=dsa%>";
		var contents = getEditorHTMLContents("type_fck_"+<%=list.get(n)%>);
		if(contents!=null){
			content =content+contents+"<BR>";
		}else{
			//content += getNotNullContent("type_fck_"+<%=list.get(n)%>);
		}
		document.getElementById('type_div_<%=list.get(n)%>').style.display="none";		
		<%}%>
		document.getElementById('quanbu').style.display="block";
		document.getElementById('quanbu').innerHTML = content;
	}
}
function getNotNullContent(myElement){
	var content = getEditorHTMLContents(myElement);
	if(content==null){
		content = getNotNullContent(myElement);
	}
	return content;
}
function _HightLight(nWord)
{
var oRange = document.body.createTextRange();
while(oRange.findText(nWord))
{
oRange.pasteHTML("<span style='color:red;background-color:yellow;font-size:13;font-weight: bold;'>" + oRange.text + "</span>");
oRange.moveStart('character',1);
}
}

function _save(addnot){
	
	var leader = document.form1.leader.value;
	var secret = document.form1.secret.value;
	if(secret==null|secret==""){
		document.form1.secret.value=" ";
		}
	if(leader==null|leader==""){
		alert("ֵ���쵼������д");
	}else{
		if(num==temp){
		
			document.form1.action="<%=request.getContextPath()%>/servlet/RhDutyWholeAddServlet";
			document.form1.submit();
		}else{
		
			setTimeout("_save('1')",3000);
		}
	}
}
function changeRedColor(myElement) {
	myElement.color = "#ff0000";	
}
function changeBlackColor(myElement) {
	myElement.color = "#0080FF";	
}

function printdiv(printpage)

{

var newstr = document.all.item(printpage).innerHTML;
document.getElementById("daying").innerHTML=newstr;
allhtml.style.display="none";
daying.style.display="block";
//var oldstr = document.body.innerHTML;
//document.body.innerHTML = newstr;
window.print(); 
daying.style.display="none";

allhtml.style.display="block";
//window.location.replace(location);
return false;
} 

function OpenWord(obj){   
ExcelSheet = new ActiveXObject('Word.Application');   
ExcelSheet.Application.Visible = true;   
var mydoc=ExcelSheet.Documents.Add('',0,1);   
myRange =mydoc.Range(0,1)   
var sel=document.body.createTextRange() 
sel.moveToElementText(obj);
sel.select()   
document.execCommand('Copy')   
sel.moveEnd('character')   
myRange.Paste();   
location.reload()   
ExcelSheet.ActiveWindow.ActivePane.View.Type=9  
}   




function _warn(name,ip){
form1.sbutton.disabled=true;
alert(name +" ���� "+ip+" �ϱ༭����־��Ϊ��֤��־���ݵ�һ���ԣ������˳���");
//ȡ���Զ�����
clearInterval(saveInteval);
}
window.onload=function(){

	<%if("yes".equals(noediter)){%>
		_warn('<%=lastEditer%>','<%=lastIP%>');
	<%}%>
}




			
function checkLeave(){
   
   document.form1.action="/oabase/servlet/RhEditFCKReturnServlet";
	 document.form1.submit();
}


</script>
</head>

<BODY text="#000000" leftMargin="0" topMargin="10" onunload="checkLeave()">
<div id ="daying" style ="display:none" ></div>
<div id ="allhtml">
<form action="" name="form1" method="post">
<jsp:include page= "/include/top.jsp" />
<input type="hidden" name="mainId"
	value="<%=mainvo.getWimId()%>">
<input type="hidden" name="addnot"
	value="1">
<table width="983" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
  		<td height="5" colspan="2"></td>
  	</tr>
	<tr>
		
		<td valign="top">
		<TABLE cellpadding="0" cellspacing="1" width="100%" align=center border=0 class="table_bgcolor">
			<TR>
				<td height="30" colspan="2" class="block_title">
				<div align="center">������־��Ϣ�Ǽ�</div>
				</td>
			</TR>
			<TR>
				<td width="8%" height="30" bgcolor="#FFFFFF" class="table_title">
				<div align="right">����</div>
				</td>
				<td width="92%" bgcolor="#FFFFFF" valign="top">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="message_title" nowrap="nowrap" valign="bottom">
						<div align="right"><%Timestamp time = (Timestamp) mainvo.getWitDate();
//System.err.println("Timestamp=" + time);
String timestr = "";
if (time != null) {
	timestr = time.toString().substring(0, 10);
}%> <%=timestr%> ������־ <select name="dutytype" style="display:none">
							<option value="1" <%if ("1".equals(mainvo.getWitClass())) {%>
								selected <%}%>>�װ�</option>
							<option value="2" <%if ("2".equals(mainvo.getWitClass())) {%>
								selected <%}%>>ҹ��</option>
						</select></div>
						</td>
						<td width=5%></td>
						<td class="message_title" >
						<div align="center" nowrap>&nbsp;�װ��쵼:<input name="leader" type="text" size="10" notnull="������д" title="ֵ���쵼"
							value="<%=mainvo.getWitLeader()%>" > ҹ���쵼:<input name="secret" type="text" size="10"
							value="<%=mainvo.getWitSecret()%>" ></div>
						</td>
						<td width=35%>
							<div align="center">
			                <input name='sbutton' type="button" value=" ���� " onClick="javascript:_save('1')">&nbsp;
			                <input type="button" value=" ��ӡ " onclick="javascript:printdiv('quanbu')">&nbsp;
			                <input type="button" value=" ���� " onClick="javascript:_return()">
			                </div>
						</td>
						<td>
						<div id="msg" class="message_title_bold" nowrap></div>
						</td>
							
					</tr>
				</table>
				</td>
				<!--
				<td nowrap align="right" bgcolor="#FFFFFF"><img src="/oabase/images/button_baocun.jpg" width="90" height="23" hspace="0" onclick=""/>
				<img src="/oabase/images/button_saveword.jpg" width="90" height="23" hspace="0" onclick=""/>
				<img src="/oabase/images/button_fanhui.jpg" width="90" height="23" hspace="0" onclick=""/></td>-->
			</TR>
			<TR>
				<td bgcolor="#FFFFFF" class="table_title">
				<div align="right">��¼����</div>
				</td>
				<td bgcolor="#FFFFFF" colspan="1" width="100%">
				<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td colspan="2" width="100%"><div id="chaolianjie"><span class="STYLE5"><a
							href="javascript:sel('0')" style="text-decoration: none"><font id="font_0">ȫ��</font></a> <%for (int i = 0; i < typeList.size(); i++) {
							TbRhWorkinfotypeVO vo3 = (TbRhWorkinfotypeVO) typeList.get(i);
							Integer id1 = vo3.getWitId();%> | <a href="#" style="text-decoration: none"
							onClick="sel('<%=id1.toString()%>')"><font id="font_<%=id1.toString()%>"><%=vo3.getWitName()%></font></a> <%}%>
						
						</span></div>
                        <div id="xToolbar"></div>
                        </td>
					</tr>
                    
					<tr>
						<td colspan="2" width="100%">
						<div id="quanbu" style="display: block" class="testDiv">
						<%String ids = "";String contents111="";
							for (int a = 0; a < dutyInfoList.size(); a++) {
								TbRhWorkinfoVO infovo1 = (TbRhWorkinfoVO) dutyInfoList.get(a);
								ids += infovo1.getWitId() + "||";
								contents111 = infovo1.getWitContent();
								if(contents111==null)contents111="";
								%><br><br><%=contents111%><br>
													<%}%>
						</div>
						<%
						for (int j = 0; j < typeList.size(); j++) {
							//System.err.println("���2");
							TbRhWorkinfotypeVO vo4 = (TbRhWorkinfotypeVO) typeList.get(j);
							Integer id = vo4.getWitId();
							//String info = "<b>"+vo4.getWitName()+"</b><br>";
							String info = "  ";
							String wiid = "";
							if (ids.indexOf(id.toString()) >= 0) {
								TbRhWorkinfoVO infovo = (TbRhWorkinfoVO) map.get(id);
								
								if (infovo.getWitContent() != null) {
									info = infovo.getWitContent();
								}
								if(infovo.getWiId()!=null){
									wiid = infovo.getWiId().toString();
								}
							}%>
						<div id="type_div_<%=id%>" style="display: none">
						<input type="hidden" name="infoid_<%=id%>"	value="<%=wiid%>" >
						
						<textarea name="type_fck_<%=id%>" cols="80" rows="4">
							<%= info%>
						</textarea>
						
							<script type="text/javascript">
							var oFCKeditor = new FCKeditor('type_fck_<%=id%>') ;
							oFCKeditor.BasePath	= "<%=request.getContextPath()%>/fckeditor/";
							oFCKeditor.Height	= 400 ;
							oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:parent(xToolbar)'
							oFCKeditor.ToolbarSet = "Default"; 
							oFCKeditor.ReplaceTextarea();
							</script>

						</div>
						<%}
						%></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="msg" ></div>
<script type="text/javascript">
	function saveInfo()
		{
		    //��ȡ���ܷ�����Ϣ��
		    var msg = document.getElementById("msg"); 
		    var postStr = "mainId="+<%=mainvo.getWimId()%>;
		    
		    //��ȡֵ������
		    
		    <%for (int n = 0; n < list.size(); n++) {%>
		
				//var type_fck_<%=list.get(n)%> = getEditorTextContents("type_fck_<%=list.get(n)%>");
				//alert(postStr);
				
				//��ҪPOST��ֵ����ÿ��������ͨ��&������ 
			    postStr += "&type_fck_"+<%=list.get(n)%>+"="+getEditorHTMLContents("type_fck_<%=list.get(n)%>").replace(/&/g,"�Ҧ�");
			    			    
			<%}%>
		    
		    postStr=encodeURI(postStr);
		    postStr=encodeURI(postStr);
		    
		 
		    //���ձ�����URL��ַ
		    var url = "<%=request.getContextPath()%>/servlet/RhDutyEditAjaxServlet";     
		    
		    //ʵ����Ajax
		    var ajax = null;
		    if(window.XMLHttpRequest){
		        ajax = new XMLHttpRequest(); 
		       }
		    else if(window.ActiveXObject){
		        ajax = new ActiveXObject("Microsoft.XMLHTTP");
		       }
		    else{
		        return;
		       }
		    //ͨ��Post��ʽ������
		    ajax.open("POST", url, true);  
		    //���崫����ļ�HTTPͷ��Ϣ 
		    ajax.setRequestHeader("Content-length", postStr.length);
		    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
		    //����POST����
		    ajax.send(postStr);
		    //�������ݵĴ�������
		    ajax.onreadystatechange = function(){
		        if (ajax.readyState == 4 && ajax.status == 200){
		        	var rtext = ajax.responseText;
		        	if(rtext.indexOf("��ͻ")!=-1){
		        		var t = rtext.split("$");
		        		_warn(t[1],t[2]);
		        	}else{
			        	msg.innerHTML = ajax.responseText; 
		        	}
	            }
		      }
		}

	//setInterval("saveInfo()",300000);
	var saveInteval = setInterval("saveInfo()",300000);

</script>
</form>
</div>
</BODY>
</HTML>
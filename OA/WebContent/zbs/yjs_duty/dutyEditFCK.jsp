<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO"%>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfoVO"%>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfotypeVO"%>

<%System.out.println("进入dutyEditFCK.jsp11111"); %>
<%List typeList = new ArrayList();
//System.err.println("typeList1="+typeList.size());
typeList = (List) request.getAttribute("typeList") == null ? new ArrayList() : (List) request.getAttribute("typeList");
//System.err.println("typeList=" + typeList.size());
List list = new ArrayList();
Map map1 = new HashMap();
for (int k = 0; k < typeList.size(); k++) {
	TbYjsWorkinfotypeVO vo2 = (TbYjsWorkinfotypeVO) typeList.get(k);
	list.add(vo2.getWitId().toString());
	map1.put(vo2.getWitId(),vo2.getWitName());
}
List dutyInfoList = (List) request.getAttribute("dutyInfoList") == null ? new ArrayList() : (List) request.getAttribute("dutyInfoList");
Map map = (Map) request.getAttribute("map") == null ? new HashMap() : (Map) request.getAttribute("map");
TbYjsWorkinfomainVO mainvo =
	(TbYjsWorkinfomainVO) request.getAttribute("vo") == null ? new TbYjsWorkinfomainVO() : (TbYjsWorkinfomainVO) request.getAttribute("vo");

		String 	lastEditer=	request.getAttribute("lastEditer")==null?"":(String)request.getAttribute("lastEditer");
		String 	lastIP = 	request.getAttribute("lastIP")==null?"":(String)request.getAttribute("lastIP");
		String 	noediter  =	request.getAttribute("noediter")==null?"":(String)request.getAttribute("noediter");

%>
<html>
<head>
<title>工作信息登记</title>
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
	position: relative; /* 位置 绝对或者相对于最近的一个元素 relative absolute */
	top: 0px;
	left: 0px;
	height: 370px; /* 定义区域的高*/
	width: 100%;
	/*   滚动条 */
	/*   overFlow: auto ; */ /* visible ：却省的 hidden auto scroll */
	overFlow-x: auto;
	overFlow-y: auto;
	/*   滚动条相关的颜色属性 */
	scrollBar-face-color: #fffbef; /* 滑块 */
	scrollBar-hightLight-color: red; /* 高亮 */
	scrollBar-3dLight-color: orange; /* 3维光线 */
	scrollBar-darkshadow-color: white; /* 暗影    */
	scrollBar-shadow-color: yellow; /* 阴影   */
	scrollBar-arrow-color: blue; /* 箭头 */
	scrollBar-track-color: white; /* 滑道颜色 */
	scrollBar-base-color: #fffbef; /* 主要颜色 */
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
	document.form1.action="/oabase/servlet/YjsEditFCKReturnServlet";
	document.form1.submit();
}

function sel(actindex){
	<%for (int k = 0; k < typeList.size(); k++) {
	TbYjsWorkinfotypeVO vo = (TbYjsWorkinfotypeVO) typeList.get(k);
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
		//_HightLight("全部");	
		var content = "";	
		changeRedColor(font_0);		
		<%for (int n = 0; n < list.size(); n++) {%>
		<% 
		String dsa = "<a style=text-decoration:none href=javascript:sel("+list.get(n)+")><p style='font-size:16pt;color:red'><b> ■"+(String)map1.get(new Integer((String)list.get(n)))+"</b></p></a><br>";
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
		alert("值班领导必须填写");
	}else{
		if(num==temp){
		
			document.form1.action="<%=request.getContextPath()%>/servlet/YjsDutyWholeAddServlet";
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
alert(name +" 正在 "+ip+" 上编辑此日志，为保证日志内容的一致性，请您退出！");
//取消自动保存
clearInterval(saveInteval);
}
window.onload=function(){

	<%if("yes".equals(noediter)){%>
		_warn('<%=lastEditer%>','<%=lastIP%>');
	<%}%>
}




			
function checkLeave(){
   
   document.form1.action="/oabase/servlet/YjsEditFCKReturnServlet";
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
<table width="983" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
  		<td height="5" colspan="2"></td>
  	</tr>
	<tr>
		
		<td valign="top">
		<TABLE cellpadding="0" cellspacing="1" width="100%" align=center border=0 class="table_bgcolor">
			<TR>
				<td height="30" colspan="2" class="block_title">
				<div align="center">工作日志信息登记</div>
				</td>
			</TR>
			<TR>
				<td width="8%" height="30" bgcolor="#FFFFFF" class="table_title">
				<div align="right">标题</div>
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
}%> <%=timestr%> 工作日志 <select name="dutytype" style="display:none">
							<option value="1" <%if ("1".equals(mainvo.getWitClass())) {%>
								selected <%}%>>白班</option>
							<option value="2" <%if ("2".equals(mainvo.getWitClass())) {%>
								selected <%}%>>夜班</option>
						</select></div>
						</td>
						<td width=5%></td>
						<td class="message_title" >
						<div align="center" nowrap>&nbsp;白班领导:<input name="leader" type="text" size="10" notnull="必须填写" title="值班领导"
							value="<%=mainvo.getWitLeader()%>" > 夜班领导:<input name="secret" type="text" size="10"
							value="<%=mainvo.getWitSecret()%>" ></div>
						</td>
						<td width=35%>
							<div align="center">
			                <input name='sbutton' type="button" value=" 保存 " onClick="javascript:_save('1')">&nbsp;
			                <input type="button" value=" 打印 " onclick="javascript:printdiv('quanbu')">&nbsp;
			                <input type="button" value=" 返回 " onClick="javascript:_return()">
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
				<div align="right">记录内容</div>
				</td>
				<td bgcolor="#FFFFFF" colspan="1" width="100%">
				<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td colspan="2" width="100%"><div id="chaolianjie"><span class="STYLE5"><a
							href="javascript:sel('0')" style="text-decoration: none"><font id="font_0">全部</font></a> <%for (int i = 0; i < typeList.size(); i++) {
							TbYjsWorkinfotypeVO vo3 = (TbYjsWorkinfotypeVO) typeList.get(i);
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
								TbYjsWorkinfoVO infovo1 = (TbYjsWorkinfoVO) dutyInfoList.get(a);
								ids += infovo1.getWitId() + "||";
								contents111 = infovo1.getWitContent();
								if(contents111==null)contents111="";
								%><br><br><%=contents111%><br>
													<%}%>
						</div>
						<%
						for (int j = 0; j < typeList.size(); j++) {
							//System.err.println("你好2");
							TbYjsWorkinfotypeVO vo4 = (TbYjsWorkinfotypeVO) typeList.get(j);
							Integer id = vo4.getWitId();
							//String info = "<b>"+vo4.getWitName()+"</b><br>";
							String info = "  ";
							String wiid = "";
							if (ids.indexOf(id.toString()) >= 0) {
								TbYjsWorkinfoVO infovo = (TbYjsWorkinfoVO) map.get(id);
								
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
		    //获取接受返回信息层
		    var msg = document.getElementById("msg"); 
		    var postStr = "mainId="+<%=mainvo.getWimId()%>;
		    
		    //获取值班内容
		    
		    <%for (int n = 0; n < list.size(); n++) {%>
		
				//var type_fck_<%=list.get(n)%> = getEditorTextContents("type_fck_<%=list.get(n)%>");
				//alert(postStr);
				
				//需要POST的值，把每个变量都通过&来联接 
			    postStr += "&type_fck_"+<%=list.get(n)%>+"="+getEditorHTMLContents("type_fck_<%=list.get(n)%>").replace(/&/g,"σρ");
			    			    
			<%}%>
		    
		    postStr=encodeURI(postStr);
		    postStr=encodeURI(postStr);
		    
		 
		    //接收表单的URL地址
		    var url = "<%=request.getContextPath()%>/servlet/YjsDutyEditAjaxServlet";     
		    
		    //实例化Ajax
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
		    //通过Post方式打开连接
		    ajax.open("POST", url, true);  
		    //定义传输的文件HTTP头信息 
		    ajax.setRequestHeader("Content-length", postStr.length);
		    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
		    //发送POST数据
		    ajax.send(postStr);
		    //返回数据的处理函数
		    ajax.onreadystatechange = function(){
		        if (ajax.readyState == 4 && ajax.status == 200){
		        	var rtext = ajax.responseText;
		        	if(rtext.indexOf("冲突")!=-1){
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

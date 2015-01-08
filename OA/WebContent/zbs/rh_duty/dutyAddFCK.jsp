<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfotypeVO"%>
<%
List typeList = new ArrayList();
//System.err.println("typeList1="+typeList.size());
typeList = (List) request.getAttribute("typeList")==null?new ArrayList():(List) request.getAttribute("typeList");
System.err.println("typeList="+typeList.size());
List list = new ArrayList();
Map map = new HashMap();
for(int k=0;k<typeList.size();k++){
	TbRhWorkinfotypeVO vo = (TbRhWorkinfotypeVO)typeList.get(k);
	list.add(vo.getWitId().toString());
	map.put(vo.getWitId(),vo.getWitName());
}
%>
<html>
<head>
<title>工作信息登记</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT language=JavaScript src="/oabase/zbs/include/formverify/extendString.js"></SCRIPT>
<SCRIPT language=JavaScript src="/oabase/zbs/include/formverify/formVerify.js"></SCRIPT>
<SCRIPT language=JavaScript src="/oabase/zbs/include/formverify/runFormVerify.js"></SCRIPT>
<SCRIPT language=JavaScript src="/oabase/zbs/include/js/common.js"></SCRIPT>

<script type="text/javascript" src="<%=request.getContextPath()%>/fckeditor/fckeditor.js"></script> 

<!--<link rel="stylesheet" href="/oabase/zbs/include/style.css">-->
<link href="/oabase/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {color: #FFFFFF;
	font-weight: bold;
}
body {
	background-image: url();
}
.style2 {color: #FFFFFF}

-->
</style>
<style type="text/css">
.testDiv{
   color:black;
   border-style: solid ;     
   border-width: 0px ;    
   border-color: red ;
   background: white;

   position: relative ; /* 位置 绝对或者相对于最近的一个元素 relative absolute */
   top:0px ;
   left:0px ;
   height:370px ; /* 定义区域的高*/
   width:765px ;
  
   /*   滚动条 */
    /*   overFlow: auto ; */ /* visible ：却省的 hidden auto scroll */
     overFlow-x: auto ;
     overFlow-y: auto ;     
  
    /*   滚动条相关的颜色属性 */
     scrollBar-face-color:#fffbef;        /* 滑块 */
     scrollBar-hightLight-color: red;    /* 高亮 */
     scrollBar-3dLight-color: orange;    /* 3维光线 */
     scrollBar-darkshadow-color:white;    /* 暗影    */
     scrollBar-shadow-color:yellow;      /* 阴影   */
     scrollBar-arrow-color:blue;       /* 箭头 */
     scrollBar-track-color:white;         /* 滑道颜色 */
     scrollBar-base-color:#fffbef;          /* 主要颜色 */
  
     font-size:12;
}

.STYLE3 {color: #006699}
.STYLE5 {color: #006699; font-size: 12px; line-height:24px; }
</style>

<script language="javascript">
function getEditorHTMLContents(EditorName) { 
    var oEditor = FCKeditorAPI.GetInstance(EditorName); 
    return(oEditor.GetXHTML(true)); 
}
function _return(){
	//alert(getEditorHTMLContents("type_fck_1"));
	document.form1.action="/oabase/servlet/RhMainDutyListServlet";
	document.form1.submit();
}
function changeRedColor(myElement) {
	myElement.color = "#ff0000";	
}
function changeBlackColor(myElement) {
	myElement.color = "#0080FF";	
}
function _go(){
	var a = "";
	a =	window.frames["type_fck_1___Frame"].xEditingArea.frames["type_fck_1"].innerHTML;
	alert(a);
}
function sel(actindex){
	<%	for(int k=0;k<typeList.size();k++){
		TbRhWorkinfotypeVO vo = (TbRhWorkinfotypeVO)typeList.get(k);
		Integer id = vo.getWitId();
		String ids = id.toString();									
		%>
	if(actindex==<%=ids%>){				
		<%for(int n=0;n<list.size();n++){
			if(!ids.equals(list.get(n))){
		%>
		changeBlackColor(document.getElementById("font_"+<%=list.get(n)%>));
		type_div_<%=list.get(n)%>.style.display="none";
		<%}}%>
		quanbu.style.display="none";
		changeBlackColor(font_0);
		type_div_<%=ids%>.style.display="block";
		changeRedColor(document.getElementById("font_"+<%=ids%>));
		//alert('type_div_<%=ids%>');
	}
	<%}%>
	if(actindex==0){
		var content = "";
		changeRedColor(font_0);
		//font_1.style.background-color = yellow;
		//document.write();	
		<%for(int n=0;n<list.size();n++){%>		
		<% 
		String dsa = "<a style=text-decoration:none href=javascript:sel("+list.get(n)+")><p style='font-size:16pt;color:red'><b> ■"+(String)map.get(new Integer((String)list.get(n)))+"</b></p></a><br>";
		%>
		
		changeBlackColor(document.getElementById("font_"+<%=list.get(n)%>));
		content += ""+"<%=dsa%>";
		var contents = getEditorHTMLContents("type_fck_"+<%=list.get(n)%>);
		if(contents!=null){
			content = content+contents+"<BR>";
		}else{
			//content += getNotNullContent("type_fck_"+<%=list.get(n)%>);
		}
		type_div_<%=list.get(n)%>.style.display="none";
		//alert(getEditorHTMLContents("type_fck_"+<%=list.get(n)%>));
		<%}%>
		quanbu.style.display="block";
		quanbu.innerHTML = content;
		
	}
}
function _save(){
	var leader = document.form1.leader.value;
	var secret = document.form1.secret.value;
	if(secret==null|secret==""){
		document.form1.secret.value=" ";
		}
	if(leader==null|leader==""){
		alert("值班领导必须填写");
	}else{
	document.form1.action="<%=request.getContextPath()%>/servlet/RhDutyWholeAddServlet";
	document.form1.submit();
	}
}

function _dutychange(){
	var leader = document.form1.leader.value;
	var secret = document.form1.secret.value;
	if(secret==null|secret==""){
		document.form1.secret.value=" ";
		}
	if(leader==null|leader==""){
		alert("值班领导必须填写");
	}else{
	document.form1.action="<%=request.getContextPath()%>/servlet/RhDutyWholeAddServlet?isChange=y";
	document.form1.submit();
	}
}
</script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
</head>

<BODY text="#000000" leftMargin="0" topMargin="10">
<form action="" name="form1" method="post">
<jsp:include page= "/include/top.jsp" />
<table width="983" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td height="5" colspan="2"></td>
  </tr>
  <tr>
  	
    <td valign="top">
      	<TABLE  cellpadding="2" cellspacing="1" width="100%" align=center border=0 class="table_bgcolor">
          	<TR>
            	<td height="30" colspan="2" class="block_title">
				<div align="center">
            		工作日志信息登记
            	</div>
				<div align="center"></div>
				</td>          
          	</TR>
			<TR>
				<td width="8%" height="30" class="table_title" bgcolor="#FFFFFF" align="right">
					标题
				</td>
				<td width="92%" bgcolor="#FFFFFF" valign="top">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="message_title" valign="bottom" >
									<div align="right" >
									<% 
										  Date now = new Date(); 
										  DateFormat d = DateFormat.getDateInstance(); 
										  String str = d.format(now); 							
									%>
											<%=str%> 工作日志
										
										
									<select  name="dutytype" style="display:none">
										<option value="1" selected>白班</option>
										<option value="2">夜班</option>
									</select>
									<script type="text/javascript">
										today = new Date();
										var time = today.getHours();
										var objSel = document.form1.elements["dutytype"];
										if(time >15)
										objSel.options[1].selected=true; 
									</script>				
											
									</div>
							</td>
							<td width="5%"></td>
							<td class="message_title">
									<div align="right">
										白班领导:<input name="leader" type="text" size="10" notnull="必须填写" title="白班领导">
										夜班领导:<input name="secret" type="text" size="10" notnull="必须填写" title="夜班领导">
									</div>
							</td>	
						<td  width="10%" align=center> 
		                    <input type="button" value=" 交班 " onClick="javascript:_dutychange()">
		                </td>	
						<td width="30%">
		                    <div align="center">
		                    <input type="button" value=" 保存 " onClick="javascript:_save()">&nbsp;
		                    <input type="button" value=" 返回 " onClick="javascript:_return()">
		                    </div>
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
				<td class="table_title" bgcolor="#FFFFFF">
					<div align="right">
							记录内容
        			</div>
				</td>			
				<td bgcolor="#FFFFFF" colspan="1" width="100%">
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
          						<tr>
									<td colspan="2" width="100%"><span class="STYLE5"><a href="javascript:sel('0')" style="text-decoration: none"><font id="font_0">全部</font></a>
									<%for(int i=0;i<typeList.size();i++){
										//System.err.println("你好");
										TbRhWorkinfotypeVO vo = (TbRhWorkinfotypeVO)typeList.get(i);
										Integer id = vo.getWitId();									
										%>
										|<a href="#" style="text-decoration: none" onClick="sel('<%=id.toString()%>')"><font id="font_<%=id.toString()%>"><%=vo.getWitName()%></font></a>
									<%}%>									
									<!--|<a href="javascript:sel('1')">待办事件</a>|<a href="#" onClick="sel('2')">已办事件</a>|<a href="#" onClick="sel('3')">总编室文件传阅夹</a>|<a href="#" onClick="sel('4')">待上编务会文件</a>|<a href="#" onClick="sel('5')">需提醒的事项专用文件夹</a>|<a href="#" onClick="sel('6')">近期需要反馈的文件专用夹</a>|<a href="#" onClick="sel('7')">本周需要反馈的文件专用夹</a>-->
									</span>
									 <div id="xToolbar"></div>
									</td>
								</tr>
								<tr>
									<td colspan="2" width="100%">
										<div id="quanbu" style="display:block" class="testDiv"><br><br>请进入具体分类填写内容</div>
				<%for(int j=0;j<typeList.size();j++){
					TbRhWorkinfotypeVO vo = (TbRhWorkinfotypeVO)typeList.get(j);
					Integer id = vo.getWitId();									
					%>					
				<div id="type_div_<%=id%>" style="display:none">
				
						<textarea name="type_fck_<%=id%>" cols="80" rows="4">
						
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
				<%}%>
									</td>
								</tr>
							
				  </table>
      					</td>
					</tr>
			</table>
		</td>
	</tr>
</table>

</form>
</BODY></HTML>
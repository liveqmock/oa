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
<title>������Ϣ�Ǽ�</title>
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

   position: relative ; /* λ�� ���Ի�������������һ��Ԫ�� relative absolute */
   top:0px ;
   left:0px ;
   height:370px ; /* ��������ĸ�*/
   width:765px ;
  
   /*   ������ */
    /*   overFlow: auto ; */ /* visible ��ȴʡ�� hidden auto scroll */
     overFlow-x: auto ;
     overFlow-y: auto ;     
  
    /*   ��������ص���ɫ���� */
     scrollBar-face-color:#fffbef;        /* ���� */
     scrollBar-hightLight-color: red;    /* ���� */
     scrollBar-3dLight-color: orange;    /* 3ά���� */
     scrollBar-darkshadow-color:white;    /* ��Ӱ    */
     scrollBar-shadow-color:yellow;      /* ��Ӱ   */
     scrollBar-arrow-color:blue;       /* ��ͷ */
     scrollBar-track-color:white;         /* ������ɫ */
     scrollBar-base-color:#fffbef;          /* ��Ҫ��ɫ */
  
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
		String dsa = "<a style=text-decoration:none href=javascript:sel("+list.get(n)+")><p style='font-size:16pt;color:red'><b> ��"+(String)map.get(new Integer((String)list.get(n)))+"</b></p></a><br>";
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
		alert("ֵ���쵼������д");
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
		alert("ֵ���쵼������д");
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
            		������־��Ϣ�Ǽ�
            	</div>
				<div align="center"></div>
				</td>          
          	</TR>
			<TR>
				<td width="8%" height="30" class="table_title" bgcolor="#FFFFFF" align="right">
					����
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
											<%=str%> ������־
										
										
									<select  name="dutytype" style="display:none">
										<option value="1" selected>�װ�</option>
										<option value="2">ҹ��</option>
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
										�װ��쵼:<input name="leader" type="text" size="10" notnull="������д" title="�װ��쵼">
										ҹ���쵼:<input name="secret" type="text" size="10" notnull="������д" title="ҹ���쵼">
									</div>
							</td>	
						<td  width="10%" align=center> 
		                    <input type="button" value=" ���� " onClick="javascript:_dutychange()">
		                </td>	
						<td width="30%">
		                    <div align="center">
		                    <input type="button" value=" ���� " onClick="javascript:_save()">&nbsp;
		                    <input type="button" value=" ���� " onClick="javascript:_return()">
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
							��¼����
        			</div>
				</td>			
				<td bgcolor="#FFFFFF" colspan="1" width="100%">
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
          						<tr>
									<td colspan="2" width="100%"><span class="STYLE5"><a href="javascript:sel('0')" style="text-decoration: none"><font id="font_0">ȫ��</font></a>
									<%for(int i=0;i<typeList.size();i++){
										//System.err.println("���");
										TbRhWorkinfotypeVO vo = (TbRhWorkinfotypeVO)typeList.get(i);
										Integer id = vo.getWitId();									
										%>
										|<a href="#" style="text-decoration: none" onClick="sel('<%=id.toString()%>')"><font id="font_<%=id.toString()%>"><%=vo.getWitName()%></font></a>
									<%}%>									
									<!--|<a href="javascript:sel('1')">�����¼�</a>|<a href="#" onClick="sel('2')">�Ѱ��¼�</a>|<a href="#" onClick="sel('3')">�ܱ����ļ����ļ�</a>|<a href="#" onClick="sel('4')">���ϱ�����ļ�</a>|<a href="#" onClick="sel('5')">�����ѵ�����ר���ļ���</a>|<a href="#" onClick="sel('6')">������Ҫ�������ļ�ר�ü�</a>|<a href="#" onClick="sel('7')">������Ҫ�������ļ�ר�ü�</a>-->
									</span>
									 <div id="xToolbar"></div>
									</td>
								</tr>
								<tr>
									<td colspan="2" width="100%">
										<div id="quanbu" style="display:block" class="testDiv"><br><br>�������������д����</div>
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
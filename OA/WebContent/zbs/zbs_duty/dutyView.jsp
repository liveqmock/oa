<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.icss.oa.zbs.duty.vo.TbZbsWorkinfomainVO"%>
<%@ page import="com.icss.oa.zbs.duty.vo.TbZbsWorkinfoVO"%>
<%@ page import="com.icss.oa.zbs.duty.vo.TbZbsWorkinfotypeVO"%>
<%
	List typeList = new ArrayList();
	//System.err.println("typeList1="+typeList.size());
	typeList = (List) request.getAttribute("typeList") == null ? new ArrayList()
			: (List) request.getAttribute("typeList");
	//System.err.println("typeList=" + typeList.size());
	List list = new ArrayList();
	Map map1 = new HashMap();
	for (int k = 0; k < typeList.size(); k++) {
		TbZbsWorkinfotypeVO vo2 = (TbZbsWorkinfotypeVO) typeList.get(k);
		list.add(vo2.getWitId().toString());
		map1.put(vo2.getWitId(), vo2.getWitName());
	}
	List dutyInfoList = (List) request.getAttribute("dutyInfoList") == null ? new ArrayList()
			: (List) request.getAttribute("dutyInfoList");
	Map map = (Map) request.getAttribute("map") == null ? new HashMap()
			: (Map) request.getAttribute("map");
	TbZbsWorkinfomainVO mainvo = (TbZbsWorkinfomainVO) request
			.getAttribute("vo") == null ? new TbZbsWorkinfomainVO()
			: (TbZbsWorkinfomainVO) request.getAttribute("vo");

	
%>
<html>
	<head>
		<title>ֵ����Ϣ�鿴</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<SCRIPT language=JavaScript
			src="/oabase/zbs/include/formverify/extendString.js"></SCRIPT>
		<SCRIPT language=JavaScript
			src="/oabase/zbs/include/formverify/formVerify.js"></SCRIPT>
		<SCRIPT language=JavaScript
			src="<%=request.getContextPath()%>/zbs/include/formverify/runFormVerify.js"></SCRIPT>
		<SCRIPT language=JavaScript src="/oabase/zbs/include/js/common.js"></SCRIPT>
		<link href="/oabase/Style/css.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			id=homepagestyle rel="stylesheet" type="text/css" />

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
	/*   ������ */ /*   overFlow: auto ; */
	/* visible ��ȴʡ�� hidden auto scroll */
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

function _return(){
	document.form1.action="/oabase/servlet/MainDutyListServlet";
	document.form1.submit();
}

function sel(actindex){
	<%for (int k = 0; k < typeList.size(); k++) {
	TbZbsWorkinfotypeVO vo = (TbZbsWorkinfotypeVO) typeList.get(k);
	Integer id = vo.getWitId();
	String ids = id.toString();%>
	if(actindex==<%=ids%>){				
		<%for (int n = 0; n < list.size(); n++) {
	if (!ids.equals(list.get(n))) {%>
		changeBlackColor(document.getElementById("font_"+<%=list.get(n)%>));
		type_div_<%=list.get(n)%>.style.display="none";
		<%}
}%>
		quanbu.style.display="none";
		changeBlackColor(font_0);
		type_div_<%=ids%>.style.display="block";
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
		String dsa = "<a style='text-decoration:none'  href=javascript:sel("+list.get(n)+")><p style='font-size:16pt;color:red'><b> ��"+(String)map1.get(new Integer((String)list.get(n)))+"</b></p></a><br>";
		%>
		changeBlackColor(document.getElementById("font_"+<%=list.get(n)%>));
		content += ""+"<%=dsa%>";

		content += document.getElementById("type_div_"+<%=list.get(n)%>).innerHTML;
		content += "<BR><BR>";
		type_div_<%=list.get(n)%>.style.display="none";		
		<%}%>
		quanbu.style.display="block";
		quanbu.innerHTML = content;
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

function changeRedColor(myElement) {
	myElement.color = "#ff0000";	
}
function changeBlackColor(myElement) {
	myElement.color = "#0080FF";	
}

function printdiv(printpage)

{
var headstr = "<html><head><title></title></head><body>";

var footstr = "</body>";
var newstr = document.all.item(printpage).innerHTML;
var oldstr = document.body.innerHTML;
document.body.innerHTML = headstr+newstr+footstr;
window.print(); 
document.body.innerHTML = oldstr;
return false;
} 

function _opentb(){
	window.open("/cms/cms/website/XHSZBS/znbm/layout2/layout2_1.jsp?channelId=499&siteId=3");
}

function _edittb(){
	window.open("/cms/servlet/cms/manage/info/collect/infonew?SITE_ID=3&new_COLLECT_TYPE_CODE=1&CHANNEL_ID=499");
}

</script>
	</head>

<BODY text="#000000" leftMargin="0" topMargin="10" onLoad="sel('0')">

		<form action="" name="form1">
			<jsp:include page="/include/top.jsp" />
			<input type="hidden" name="mainId" value="<%=mainvo.getWimId()%>">
			<input type="hidden" name="addnot" value="1">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
                    <td height="5" colspan="2"></td>
                </tr>
				<tr>
					<td bgcolor="#FFFFFF">&nbsp;
						
					</td>
					<td valign="top">
						<TABLE cellpadding="0" cellspacing="1" width="100%" align=center
							border=0 class="table_bgcolor">
							<TR>
								<td height="30" colspan="2" class="block_title">
									<div align="center">
											ֵ����Ϣ�Ǽ�
									</div>
								</td>
							</TR>
							<TR>
								<td width="8%" height="30" bgcolor="#FFFFFF" class="table_title">
									<div align="right">
										����
									</div>
								</td>
								<td width="92%" bgcolor="#FFFFFF" class="message_title" valign="top">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<div align="right" class="message_title">
													<div align="right">
														<%
															Timestamp time = (Timestamp) mainvo.getWitDate();
															//System.err.println("Timestamp=" + time);
															String timestr = "";
															if (time != null) {
																timestr = time.toString().substring(0, 10);
															}
														%>
														<%=timestr%>
														ֵ����־
														<select name="dutytype" disabled>
															<option value="1"
																<%if ("1".equals(mainvo.getWitClass())) {%> selected
																<%}%>>
																�װ�
															</option>
															<option value="2"
																<%if ("2".equals(mainvo.getWitClass())) {%> selected
																<%}%>>
																ҹ��
															</option>
														</select>
													</div>
												</div>
											</td>
											<td>
												<div align="right" class="message_title">
													<div align="right">
														ֵ���쵼:
														<input name="leader" type="text" size="10" notnull="������д"
															title="ֵ���쵼" value="<%=mainvo.getWitLeader()%>" readonly>
														ֵ������:
														<input name="secret" type="text" size="10"
															value="<%=mainvo.getWitSecret()%>" readonly>
													</div>
												</div>
											</td>
											
											<td width="30%">
											<div align="center">
												<input type=button onClick="OpenWord(quanbu)" value="����ΪWORD">&nbsp;
												<input type=button onClick="printdiv('quanbu')" value=" ��ӡ ">&nbsp;
												<input type="button" value=" ���� " onClick="javascript:_return()" >
											</div>
											</td>
										</tr>
									</table>
								</td>
								
							</TR>
							<TR>
								<td bgcolor="#FFFFFF">
									<div align="right" class="table_title">
										<div align="right">
											��¼����
										</div>
									</div>
								</td>
								<td bgcolor="#FFFFFF" colspan="1" width="100%">
									<table border="0" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<td colspan="2" width="100%">
												<div id="chaolianjie">
													<span class="STYLE5"><a href="javascript:sel('0')"
														style="text-decoration: none"><font id="font_0">ȫ��</font>
													</a> <%
														 	for (int i = 0; i < typeList.size(); i++) {
														 		TbZbsWorkinfotypeVO vo3 = (TbZbsWorkinfotypeVO) typeList.get(i);
														 		Integer id1 = vo3.getWitId();
														 %> |<a href="#"
														style="text-decoration: none"
														onClick="sel('<%=id1.toString()%>')"><font
															id="font_<%=id1.toString()%>"><%=vo3.getWitName()%></font>
													</a> <%
														 	}
														 %> |<a href="#"
														style="text-decoration: none"
														onClick="_opentb()">�������ͨ�� </a>
														|<a href="#" style="text-decoration:none"	onClick="_edittb()">�������ͨ������</a></span>
														 <HR>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" width="100%">
												<div id="quanbu" style="display: block" class="testDiv" >
													<%
														String ids = "";
														String contents111 = "";
														for (int a = 0; a < dutyInfoList.size(); a++) {
															TbZbsWorkinfoVO infovo1 = (TbZbsWorkinfoVO) dutyInfoList.get(a);
															ids += infovo1.getWitId() + "||";
															contents111 = infovo1.getWitContent();
															if (contents111 == null)
																contents111 = "";
													%><br>
													<br><%=contents111%><br>
													<%
														}
													%>
												</div>
												<%
													for (int j = 0; j < typeList.size(); j++) {
														//System.err.println("���2");
														TbZbsWorkinfotypeVO vo4 = (TbZbsWorkinfotypeVO) typeList.get(j);
														Integer id = vo4.getWitId();
													
														//String info = "<b>"+vo4.getWitName()+"</b><br>";
														String info = " ";
														if (ids.indexOf(id.toString()) >= 0) {
															TbZbsWorkinfoVO infovo = (TbZbsWorkinfoVO) map.get(id);
															
															if (infovo.getWitContent() != null) {
																info = infovo.getWitContent();
															}
													}
												%>
											
												<div id="type_div_<%=id%>" style="display: none" class="testDiv">
													<br>
													<%=info%>
												</div>
												<%
													}
												%>
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
	</BODY>
	<script type="text/javascript">
			function OpenWord(){   
			ExcelSheet = new ActiveXObject('Word.Application');   
			ExcelSheet.Application.Visible = true;   
			var mydoc=ExcelSheet.Documents.Add('',0,1);   
			myRange =mydoc.Range(0,1)   
			var sel=document.body.createTextRange() 
			sel.moveToElementText(quanbu);
			sel.select()   
			document.execCommand('Copy')   
			sel.moveEnd('character')   
			myRange.Paste();   
			location.reload()   
			ExcelSheet.ActiveWindow.ActivePane.View.Type=9  
			}   
</script>
</HTML>

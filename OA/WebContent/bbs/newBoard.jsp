<%@ page contentType="text/html; charset=GBK" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSAreaHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.oa.bbs.handler.UserMsgHandler" %>

<%
Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("newBoard.jsp");
	UserMsgHandler userMsghandler = new UserMsgHandler(conn);
	String uuid = userMsghandler.getUserId();
	BBSAreaHandler handler = new BBSAreaHandler(conn);
	List rightAreaList = handler.getRightAreaList(uuid);
	String adminStr = handler.getAdminString();
	if(adminStr.indexOf(uuid)>=0){
		rightAreaList = handler.getAreaccList();
	}
	Iterator Itr = rightAreaList.iterator();
%>
<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />

</head>
<BODY>
   <FORM name=form1 action=""  method=post>
   <table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
		<tr>
		<td colspan="5" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#B9DAF9">
			<tr>
				<td width="100%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" colspan=4 height="23">
				<div align="center" class="white2-12-b">新建板块</div>
				</td>
			</tr>
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">板块名称：</div>
				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=3><input
					name="boardName" maxlength=60 class="blue2-12" size=30 value=""></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">板块描述：</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3><textarea
					name="boardDes" cols="50" rows="6" class="blue2-12">	
					</textarea><!--<input
					type="checkbox" name="checkbox3" value="checked">是否加入待办事宜--></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF" width="15%">
				<div align="right" class="blue2-12">所属分区：</div>
				</td>
				<TD bgColor="#FFFFFF" class="blue2-12" colspan=1 width="25%"><select name="areaId">
                            <%
		while(Itr.hasNext()){
			BbsAreaccVO vo = (BbsAreaccVO)Itr.next();
	  %>
                            <option value="<%=vo.getAreaid()%>" class="text-01"><%=handler.getAreaNameById(vo.getAreaid())%></option>
                            <% } %>
                          </select></TD>
                <td height="22" bgcolor="#FFFFFF" width="10%">
				<div align="right" class="blue2-12">是否审批：</div>
				</td>
                <TD bgColor="#FFFFFF" class="blue2-12" colspan=1 width="50%">
                <input type="radio" name="audit" value="yes">
                          是 
                          <input type="radio" name="audit" value="no" checked>
                          否
                </td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">版主选择：</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3>
	                      <textarea name="selectedManager" cols="30" rows="2" class="text-01" readOnly></textarea>
                          
                          <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" onClick="javascript:_selectManager('<%=request.getContextPath()%>')">
                          &nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">选择可否进入的人员:</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3>
							<input type="radio" name="permit" value="yes">
                          可进 <input name="permit" type="radio" value="no" checked>
                          不可进 
				<textarea name="selectedUser" cols="30" rows="1" class="text-01" readOnly></textarea> 
                          <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" onclick="javascript:_selectUser('<%=request.getContextPath()%>')"> </td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" colspan=4 align="left" class="blue2-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class=bdtj type="button" name="Submit" value="增加" onclick="javascript:_newBoard()">
                        	<input class=bdtj type="button" name="cancel" value="取消" onclick="javascript:window.close()"> </td>
			</tr>
			
			
			
		</table>
		</td>
</table>
	</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">

function _newBoard(){
	if(document.form1.boardName.value==""){
		alert("请填写板块名称!");
		return false;
	}
	if(document.form1.areaId.value==""||document.form1.areaId.value==null){
		alert("请选择具有权限的版块!");
		return false;
	}
	var isApply = "<%=request.getParameter("isApply")%>";
	document.form1.action="<%=request.getContextPath()%>/servlet/NewBoardServlet?isApply="+isApply;
	document.form1.submit();

}

function _selectManager(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addManager&sessionname=manager','','width=700,height=550,scrollbars=yes,resizable=yes');
}
function _selectUser(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addBUser&sessionname=user','','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addManager(manager){
document.form1.selectedManager.value=manager;
}
function _addBUser(user){
document.form1.selectedUser.value=user;
}

</SCRIPT>
	<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("newBoard.jsp");
					}
			} catch (Exception e) {
		}
	}
%>

<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>


<%
Collection bcollection = (Collection)request.getAttribute("boardList");
Iterator BoardIterator = bcollection.iterator();
BbsBoardVO boardVO = null;
if(BoardIterator.hasNext())
{
	//得到板块 只取得一条记录
	boardVO = (BbsBoardVO)BoardIterator.next();
}

Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("editBoard.jsp");

	BBSHandler handler = new BBSHandler(conn);
	//得到专区列表，用于后面的比较
	List areaList = handler.getSubareaList();
//	List userMsgList = handler.getUserMsgList();
	Iterator Itr = areaList.iterator();
//	Iterator Itu = userMsgList.iterator();
	//out.print(areaList);
	
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
				<div align="center" class="white2-12-b">编辑板块</div>
				</td>
			</tr>
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">板块名称：</div>
				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=3><input
					name="boardName" maxlength=60 class="blue2-12" size=30 value="<%=boardVO.getBoardname()%>"></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">板块描述：</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3><textarea
					name="boardDes" cols="50" rows="6" class="blue2-12">
					<%if(boardVO.getBoarddes()!= null){	
						%><%=boardVO.getBoarddes()%>
						<%}%>	
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
			BbsAreaVO vo = (BbsAreaVO)Itr.next();
	  %>
                            <option value="<%=vo.getAreaid()%>" <%if(vo.getAreaid().toString().equals(boardVO.getAreaid().toString())){%>selected<%}%> class="text-01"><%=vo.getAreaname()%></option>
                            <% } %>
                          </select></TD>
                <td height="22" bgcolor="#FFFFFF" width="10%">
				<div align="right" class="blue2-12">是否审批：</div>
				</td>
                <TD bgColor="#FFFFFF" class="blue2-12" colspan=1 width="50%">
                <input type="radio" name="audit" value="yes" <%if(boardVO.getIsaudit().equals("1")){%>checked<%}%> class="text-01">
                          是 
                          <input type="radio" name="audit" value="no" <%if(boardVO.getIsaudit().equals("0")){%>checked<%}%>>
                          否
                </td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">版主选择：</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3><%
			                    List managerList = handler.getManagerList();
			                    StringBuffer smanager = new StringBuffer();
			                    String mnames = "";
			                    Iterator managerIterator = managerList.iterator();
		                        ManagerUserinfoVO managerVO = null;
                                while(managerIterator.hasNext()){ 
	                                managerVO = (ManagerUserinfoVO)managerIterator.next();
	                                if(managerVO.getBoardid().equals(boardVO.getBoardid())){
	                                     smanager.append(managerVO.getTruename());
	                                     smanager.append(",");
	                                }
	                            }
			                    if(smanager.length()>0){
			                       smanager.deleteCharAt((smanager.length()-1));
			                       mnames = smanager.toString();
			                    }
	                      %>
	                      <textarea name="selectedManager" cols="30" rows="2" class="text-01" readOnly><%=mnames%></textarea>
                          <!--input name="selectedManager" type="text" value="<%=smanager%>" size="30" class="text-01"-->
                          <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" onClick="javascript:_selectManager('<%=request.getContextPath()%>')">
                          &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/bbs/managerOrUserlist.jsp?boardid=<%=boardVO.getBoardid()%>&type=1" class="text-01">已有版主维护</a></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">选择人员：<br>(<font color=red><%if(boardVO.getPermit().equals("1")){%>可以进入<%}else{%>不可以进入<%}%></font>)</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3><%
			                List userList = handler.getBoardAccList(boardVO.getBoardid());
			                StringBuffer usernames = new StringBuffer();
			                String unames = "";
			                Iterator aIt = userList.iterator();
			                while(aIt.hasNext()){ 
				                BbsBoardaccVO aVO = (BbsBoardaccVO)aIt.next();
				                usernames.append(handler.getUserName(aVO.getUserid()));
				                usernames.append(",");
			                }
			                if(usernames.length()>0){
			                   usernames.deleteCharAt((usernames.length()-1));
			                   unames = usernames.toString();
			                }
                        %>
                        
                          <textarea name="selectedUser" cols="30" rows="2" class="text-01" readOnly><%=unames%></textarea>
                          <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" onClick="javascript:_selectUser('<%=request.getContextPath()%>')">
                          &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/bbs/managerOrUserlist.jsp?boardid=<%=boardVO.getBoardid()%>&type=2" class="text-01">已有人员维护</a></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" colspan=4 align="left" class="blue2-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="Submit" value="修  改" onClick="javascript:_editBoard('<%=request.getContextPath()%>','<%=boardVO.getBoardid().toString()%>')"></td>
			</tr>
			
			
			
		</table>
		</td>
</table>
</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">

function _editBoard(url,boardId){
	document.form1.action=url+"/servlet/EditBoardServlet?boardId="+boardId;
	document.form1.submit();

}
function _selectManager(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addManager&sessionname=manager','','width=700,height=550,scrollbars=yes,resizable=yes');
}
function _selectUser(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addBUser&sessionname=user','','width=700,height=550,scrollbars=yes,resizable=yes');
}


function _addManager(manager){
  if(document.form1.selectedManager.value=="")
      document.form1.selectedManager.value = manager;
  else{
      var smstr=document.form1.selectedManager.value.split(",");
      var mstr=manager.split(",");
      var tempstr = "";
      for(var i=0;i<mstr.length;i++){
          var pd=0;
          for(var j=0;j<smstr.length;j++){
              if(mstr[i]==smstr[j]){
                  pd=1;
                  break;
              }
          }
          if(pd==0)  tempstr = tempstr+mstr[i]+",";
      }
      if(tempstr!=""){
          tempstr = tempstr.substring(0,tempstr.length-1);
          var managerStr = tempstr;
          document.form1.selectedManager.value+=","+managerStr;
      }
  }
}

function _addBUser(user){
  if(document.form1.selectedUser.value=="")
      document.form1.selectedUser.value+=user;
  else
      var sustr=document.form1.selectedUser.value.split(",");
      var ustr=user.split(",");
      var tempstr = "";
      for(var i=0;i<ustr.length;i++){
          var pd=0;
          for(var j=0;j<sustr.length;j++){
              if(ustr[i]==sustr[j]){
                  pd=1;
                  break;
              }
          }
          if(pd==0)  tempstr = tempstr+ustr[i]+",";
      }
      if(tempstr!=""){
          tempstr = tempstr.substring(0,tempstr.length-1);
          var userStr = tempstr;
          document.form1.selectedUser.value+=","+userStr;
      }    

}

</SCRIPT>
	<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("editBoard.jsp");
					}
			} catch (Exception e) {
		}
	}
%>

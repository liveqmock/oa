<%@ page contentType="text/html; charset=gb2312" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>

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
String areaName = (String)request.getAttribute("areaName");

Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("showBoardMsg.jsp");

	BBSHandler handler = new BBSHandler(conn);
	//得到专区列表，用于后面的比较
	List areaList = handler.getSubareaList();
	Iterator Itr = areaList.iterator();
	//out.print(areaList);
	
%>
<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>
<div align="center">
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
   <FORM name=form1 action=""  method=post>
    <tr> 
      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif"> 
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">申请板块信息</td>
            <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
          </tr>
        </table>
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td width="20%" height="22" class="text-01"><div align="right"><b>板块名称：</b></div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="2"></td>
                        <td width="80%" colspan="7" bgcolor="F2F9FF" class="text-01">
                            <table  border="0" cellspacing="0" cellpadding="0">
                            	<tr> 
                              		<td>&nbsp;<%=boardVO.getBoardname()%></td>
                              		<td class="text-01">&nbsp;</td>
                            	</tr>
                          	</table>
                        </td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td colspan="7" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr> 
                        <td height="22"><div align="right" class="text-01"><b>板块描述：</b></div></td>
                        <td width="80%" colspan="7" bgcolor="F2F9FF" class="text-01"> 
                        	<textarea name="boardDes" cols="50" rows="6" class="text-01" style="text-align:left" readonly>
							<% if(boardVO.getBoarddes()!= null){ %>
									<%=boardVO.getBoarddes()%>
							<% }%>	
							</textarea>
						</td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td colspan="7" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr> 
                        <td height="22"> <div align="right" class="text-01"><b>所属专区：</b></div></td>
                        <td width="20%" bgcolor="F2F9FF">&nbsp;&nbsp;<%=areaName%></td>
                        <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="8"></td>
                        <td width="13%" bgcolor="F2F9FF"> <div align="right" class="text-01"><b>是否审批：</b></div></td>
                        <td width="2" rowspan="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="8"></td>
                        <td width="57%" bgcolor="F2F9FF">&nbsp;
                          <%
                          	if(boardVO.getIsaudit().equals("1")){ 
                          		out.print("板块内主题需要审批"); 
                          	}else if(boardVO.getIsaudit().equals("0")){ 
                          		out.print("板块内主题不需要审批"); 
                          	}
                          %>
						</td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr> 
                        <td height="22"> <div align="right" class="text-01"><b>版主：</b></div></td>
                        <td colspan="5" bgcolor="F2F9FF" class="text-01">&nbsp;
                          <%
			                    List managerList = handler.getManagerList();
			                    StringBuffer smanager = new StringBuffer();
			                    String mnames = "";
			                    Iterator managerIterator = managerList.iterator();
		                        ManagerUserinfoVO managerVO = null;
                                while(managerIterator.hasNext()){ 
	                                managerVO = (ManagerUserinfoVO)managerIterator.next();
	                                if(managerVO.getBoardid().equals(boardVO.getBoardid())){
	                                     smanager.append(managerVO.getUsername());
	                                     smanager.append(",");
	                                }
	                            }
			                    if(smanager.length()>0){
			                       smanager.deleteCharAt((smanager.length()-1));
			                       mnames = smanager.toString();
			                       out.print(mnames);
			                    }else{
			                       out.print("没有选择版主");
			                    }
	                      %>
                          </td>
                       </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
                      <tr> 
                        <td height="22"><div align="right"><b>选择人员：</b><br>(<font color=red><%if(boardVO.getPermit().equals("1")){%>可以进入<%}else{%>不可以进入<%}%></font>)</div></td>
                        <td height="22" colspan="5" bgcolor="F2F9FF">&nbsp;
                        <%
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
			                   out.print(unames);
			                }else{
			                   out.print("没有选择人员");
			                }
                        %>
                        </td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td colspan="7" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
          </tr>
        </table>
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
            <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"></div></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
        </table>
       </td>
    </tr>		   
	</form>
  </table>
  <p>
	<img src="<%=request.getContextPath()%>/images/botton-cancel.gif" onclick="javascript:window.close()" width="70" height="22" hspace="10"  border=0>
  </p>
</div>
</body>
</html>
	<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("showBoardMsg.jsp");
					}
			} catch (Exception e) {
		}
	}
%>

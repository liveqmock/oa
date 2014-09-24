<%@ page contentType="text/html; charset=GBK" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.icss.oa.address.vo.*"%>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.bbs.handler.BBSAreaHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%

Collection scollection = (Collection)request.getAttribute("areaList");
System.out.print("---------------------- editarea "+scollection);
Iterator AreaIterator = scollection.iterator();
BbsAreaVO areaVO = null;
if(AreaIterator.hasNext())
{
	areaVO = (BbsAreaVO)AreaIterator.next();
}
Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("editArea.jsp");

	BBSAreaHandler Areahandler = new BBSAreaHandler(conn);
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
				<div align="center" class="white2-12-b">修改分区</div>
				</td>
			</tr>
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">分区名称：</div>
				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=3><input
					name="areaName" maxlength=60 class="blue2-12" size=30 value="<%=areaVO.getAreaname()%>"></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">分区描述：</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3><textarea
					name="areaDes" cols="50" rows="6" class="blue2-12">
					<%=areaVO.getAreades()%>
					</textarea><!--<input
					type="checkbox" name="checkbox3" value="checked">是否加入待办事宜--></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">分区管理员选择：</div>
			  </td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=3><%
			                    List managerList = Areahandler.findManagerListByAreaId(areaVO.getAreaid());
			                    StringBuffer smanager = new StringBuffer();
			                    String mnames = "";
			                    Iterator managerIterator = managerList.iterator();
		                        BbsAreaccVO accVO = null;
                                while(managerIterator.hasNext()){ 
	                                accVO = (BbsAreaccVO)managerIterator.next();
	                                if(accVO.getAreaid().equals(areaVO.getAreaid())){
	                                     smanager.append(Areahandler.getUserName(accVO.getUserid()));
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
                          &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/bbs/areaManagerlist.jsp?areaid=<%=areaVO.getAreaid()%>" class="text-01">维护版主</a></td>
			</tr>			
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">分区所属组织：</div>
				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=3>
				
                <%
				String arearight = areaVO.getArearight();
				List orglist = (List)request.getAttribute("orglist");
				%>
				<select name="areaRight">
                	<option value="0" <%if("0".equals(arearight)||arearight==null){%>selected<%}%>>全社</option>
                    <%
					if(orglist!=null){
					for(int i=0;i<orglist.size();i++){
						SysOrgVO vo = (SysOrgVO)orglist.get(i);
						String orgid = vo.getOrguuid();
					%>
                    	<option value="<%=orgid%>" <%if(orgid.equals(arearight)){%>selected<%}%>><%=vo.getCnname()%></option>
                    <%
					}
					}
					%>
			    </select>
									</td>
			</tr>
					
			<tr>
				<td bgcolor="#FFFFFF" colspan=4 align="left" class="blue2-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="Submit" value="修  改" onClick="javascript:_editArea('<%=request.getContextPath()%>','<%=areaVO.getAreaid().toString()%>')"></td>
			</tr>
		</table>
		</td>
		</tr>
</table>
   
	</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">



function _selectUser(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addUser&sessionname=user','','width=700,height=550,scrollbars=yes,resizable=yes');
}
function _selectManager(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addManager&sessionname=manager','','width=700,height=550,scrollbars=yes,resizable=yes');
}
function _editArea(url,areaId){
	
	document.form1.action=url+"/servlet/EditAreaServlet?areaId="+areaId;
	document.form1.submit();

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
function _addUser(user){
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
					ConnLog.close("editArea.jsp");
					}
			} catch (Exception e) {
		}
	}
%>
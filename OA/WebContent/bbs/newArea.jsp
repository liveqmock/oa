<%@ page contentType="text/html; charset=GBK" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.address.handler.*" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.common.log.ConnLog" %>
<%
Connection conn = null;
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
%>
<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
</head>
<BODY text=#000000 leftMargin=0 topMargin=5>
   <FORM name=form1 action=""  method=post>
   <table width="90%" border="0" cellspacing="0" cellpadding="0"
	align="center">
   <tr><td colspan="5" valign="top">&nbsp;<br></td></tr>
   <tr>
		<td colspan="5" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#B9DAF9">
			<tr>
				<td width="100%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" colspan=3 height="23">
				<div align="center" class="white2-12-b">增加分区</div>				</td>
			</tr>
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">分区名称：</div>				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=2><input
					name="areaName" maxlength=30 class="blue2-12" size="30"></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">分区管理员选择：</div>				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=2>
	                      <textarea name="selectedManager" cols="30" rows="2" class="text-01" readOnly></textarea>                          
                          <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" onClick="javascript:_selectManager('<%=request.getContextPath()%>')">                          </td>
			</tr>
			<tr>
			  <td height="22" align="right" bgcolor="#FFFFFF" class="blue2-12">分区所属组织：</td>
			  <td bgcolor="#FFFFFF" class="blue2-12" colspan=2>
              	<select name="areaRight">
                	<option value="0">全社</option>
                    <%
					if(orglist!=null){
						for(int i=0;i<orglist.size();i++){
							SysOrgVO vo = (SysOrgVO)orglist.get(i);
					%>
                    		<option value="<%=vo.getOrguuid()%>"><%=vo.getCnname()%></option>
                    <%
						}
					}
					%>
			    </select>
			  </td>
		  </tr>
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">分区描述：</div>				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=2><textarea name="areaDes" cols="50" rows="6"></textarea></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" colspan=5 align="left" class="blue2-12">
				<div align="center" class="blue2-12">
				<input  type="button" name="Submit" value="增加" onClick="javascript:_newArea()">
                <input  type="button" name="cancel" value="取消" onClick="javascript:window.close()"> 
				</div>				</td>
			</tr>
		</table>
	  </td>
	</tr>
	</table>
	</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">

function _newArea(){
	if(document.form1.areaName.value==""){
		alert("请填写专区名称!");
		return false;
	}
	if(document.form1.selectedManager.value==""){
		alert("请选择管理员!");
		return false;
	}
	document.form1.action="<%=request.getContextPath()%>/servlet/NewAreaServlet";
	document.form1.submit();

}
function _selectManager(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addManager&sessionname=manager','','width=700,height=550,scrollbars=yes,resizable=yes');
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
</SCRIPT>
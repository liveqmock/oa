<%@ page contentType="text/html; charset=GBK" %>
<%@ page pageEncoding="GBK" %>

<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.config.AddressConfig"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.address.handler.Group" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="java.sql.Connection" %>

<%
//Collection commomCollection = (Collection)request.getAttribute("commonGroup");
Collection indiCollection = (Collection)request.getAttribute("IndiGroup");
//Iterator commonGroupIterator = commomCollection.iterator();
Iterator indiGroupIterator = indiCollection.iterator();
String exist = (String) request.getAttribute("groupNameExist");
Connection con = null;
%>

<%try{
 con = com.icss.j2ee.services.DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("group.jsp");
 Group handler1=new Group(con);%>

<html>
<head>
<title>分组列表</title>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<script language="JavaScript">
function UpdateApp(groupid,groupName,groupDes)
{
	 document.groupForm.groupid.value    = groupid;
	 document.groupForm.groupName.value   = groupName;
	 document.groupForm.groupDes.value   = groupDes;
	 
}

function _CheckForm(){
  if(document.groupForm.groupName.value==""){
    alert("请填写分组名称！");
    document.groupForm.groupName.focus;
    return false
    }
  if(document.groupForm.groupDes.value==""){
    alert("请填写分组描述！");
    document.groupForm.groupDes.focus;
    return false
    }
   return true
 }

function _Add(){

  if(!_CheckForm()){
    return false
  }
  document.groupForm.action="<%=request.getContextPath()%>/servlet/IndeGroupAdd2Servlet";
  document.groupForm.submit();
  window.top.leftFrame.location.reload();
 }

 function _Update()
 {
     if(!groupForm.groupid){
		alert("没有选择分组！");
      return false;
    }
  if(!IsChecked(groupForm.groupid,"请选中一个分分组!")){
      return false
   }
  if(!_CheckForm()){
    return false
   }
   document.groupForm.action="IndiGroupAlterServlet";
   document.groupForm.submit();
	window.top.leftFrame.location.reload();
  }
  function _Delete()
  {
     if(!groupForm.groupid){
		alert("没有选择分组！");
      return false
    }
    if(!IsChecked(groupForm.groupid,"请选中一个分组！")){
      return false
    }
    ok=confirm("此操作将删除该应用下的所有关联信息,您确定要进行吗?");
    if(ok){
     document.groupForm.action="IndiGroupDelServlet";
     document.groupForm.submit()
	window.top.leftFrame.location.reload();
    }
    else{
      return false
    }
  }
</script>
</head>


<body>
<form name="groupForm" method="post" >
<input type="hidden" name="type" value="<%=com.icss.oa.config.AddressConfig.GROUPTYPE_PRIVATE%>">
<%
if(exist != null && exist.equals("true")){
	
	out.print("<font color=red>分组："+(String)request.getAttribute("groupName")+" 已存在，请重新添加！</font><br>");
}
%>

<!--主要内容去区-->
<table width="750" border="0" cellspacing="0" cellpadding="0">


    <tr>
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
	<tr>
    	<td colspan="6" height="11">个人分组列表</td>
    </tr>
    
  
    <tr>
    	<td width="8">&nbsp;</td>
        
      <td valign="top">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
        <tr>
          <td nowrap="nowrap" width="8%" height="26" class="block_title">
          <input type="checkbox" name="all" value="checkbox" onclick="javascript:allcheck()"/></td>
          <td colspan="4" class="block_title"><div align="center">编号</div></td>
          <td width="12%" class="block_title"><div align="center">名称</div></td>
          <td width="16%" class="block_title"><div align="center">描述</div></td>
          <td width="17%" class="block_title"><div align="center">分组人员</div></td>
        </tr>
<%
if(!indiGroupIterator.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="message_title_bold" colspan=9><div align="center">
                <br><br>还没有分组信息！
		</div></td>
 </tr>
  <%
	}
int index = 0;		
while(indiGroupIterator.hasNext()){
	AddressGroupVO addressGroupVO = (AddressGroupVO)indiGroupIterator.next();
	index ++;
%>
        <tr>
			<td height="22" bgcolor="#FFFFFF"><div align="center">
            <input type="radio" name="groupid" onclick="javascript:UpdateApp('<%=addressGroupVO.getId()%>','<%=addressGroupVO.getGroupname()%>','<%=addressGroupVO.getGroupdes()%>')" value="<%=addressGroupVO.getId()%>"> </div></td>


          <td colspan="4" bgcolor="#FFFFFF" class="message_title_bold" ><%=index%></td>

          <td bgcolor="#FFFFFF" class="message_title_bold">
		 <div align="center">
 				<a href="ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_PRIVATE%>"><%=addressGroupVO.getGroupname()%></a></div>
		  </td>
          <td bgcolor="#FFFFFF" class="message_title_bold">
		 <%=addressGroupVO.getGroupdes()%>
		 </td>
          <td bgcolor="#FFFFFF" class="message_title_bold">
		  <a href="ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_PRIVATE%>">查看</a>
							<%if(!handler1.personInGroupbyNameIsNull(addressGroupVO.getId(),AddressConfig.GROUPTYPE_PRIVATE)){%>
                            <font color="red">[此分组下无人]</font></div>
                            <%}%></td>
		  </td>
        </tr>

<%}%>


        
                     
      </table></td>
    </tr>
      </table>      
</td>
    </tr>
    
    
    <tr>
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
	  
	  

</form>
</body>
</html>

<%
} catch (Exception ex) {
	ex.printStackTrace();
} finally {
	try {
		if (con != null){
			con.close();
			ConnLog.close("group.jsp");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	//
}%>
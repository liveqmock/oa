<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.oa.config.AddressConfig"%>  
<%@ page import="com.icss.oa.util.RoleControl"%> 
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.address.handler.Group" %>  
<%@ page import="com.icss.common.log.ConnLog" %>


<%  
Collection commomCollection = (Collection)request.getAttribute("commonGroup");   
//Collection indiCollection = (Collection)request.getAttribute("IndiGroup");
Iterator commonGroupIterator = commomCollection.iterator();
//Iterator indiGroupIterator = indiCollection.iterator();
String exist = (String) request.getAttribute("groupNameExist");
String kkkd;
Integer flag = (Integer)request.getAttribute("flag");

Context ctx = Context.getInstance();
UserInfo user = ctx.getCurrentLoginInfo();
%>

<%  Connection con = null;
try{
	con = com.icss.j2ee.services.DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("commonGroup.jsp");
    Group handler1=new Group(con);%>
    
<html>
<head>
<title>分组列表</title>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript">
function UpdateApp(groupid,groupName,groupDes,needaccredit,OldAccredit)
{
	 document.groupForm.groupid.value    = groupid;
	 document.groupForm.groupName.value   = groupName;
	 document.groupForm.groupDes.value   = groupDes;
	 document.groupForm.OldAccredit.value   = OldAccredit;
	 if (needaccredit == "1"){
	 	document.groupForm.needAccredit[0].checked = true;
	 }else{
	 	document.groupForm.needAccredit[1].checked = true;
	 }
	 
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
  document.groupForm.action="CommonGroupAddServlet";
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
   document.groupForm.action="CommonGroupAlterServlet";
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
     document.groupForm.action="CommonGroupDelServlet";
     document.groupForm.submit()
	window.top.leftFrame.location.reload();
    }
    else{
      return false
    }
  }
function checkuserid(){

	if(document.searchForm.cnname.value == ""){
		alert("请填写要查询的用户中文名!");
		return false;
	}
	if(getLength(document.searchForm.cnname.value)<=2){
    	alert("考虑到查询的效率请您输入两个以上的汉字进行查询！");
    	return false;
    }
	return true;
}
function search(){
	if(checkuserid()){
		document.searchForm.action = "SearchPersonServlet";
		document.searchForm.submit();
	}
}
</script>    
</head>

<body text=#eeeeee bgColor=#ffffff leftMargin=0 background="<%=request.getContextPath()%>/images/bg-08.gif" topMargin=0 marginheight="0" 
marginwidth="0">


<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module8");
		String ip=request.getRemoteAddr();		
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

<div align="center">
<br>
<form name="searchForm" method="post">
<div align="left" class="title-04">
&nbsp;&nbsp;&nbsp;请输入用户中文名查询此人是否被授权:
<input name="cnname" type="text"  size="30" maxlength="128" > 
<input name="QueryType" type="hidden" value="RootGroup"> 
<img src="<%=request.getContextPath()%>/images/botton-search_in.gif" align="absmiddle" style="cursor:hand" onclick="javascript:search()">
</div>
</form>

<form name="groupForm" method="post" action=""> 
<input type="hidden" name="type" value="<%=com.icss.oa.config.AddressConfig.GROUPTYPE_COMMOM%>">
<html:synchToken />
<%
if(exist != null && exist.equals("true")){
	
	out.print("<font color=red>分组："+(String)request.getAttribute("groupName")+" 已存在，请重新添加！</font><br>");
}
%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">公共分组列表
     </td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
		  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">					
                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr  bgcolor="#FFFBEF">
                        <td width="5%" height="22">
                          <div align="center"> </div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" ><div align="center" class="title-04">编号</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" ><div align="center" class="title-04">名称</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">描述</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" height="22"><div align="center" class="title-04">使用者</div></td>
                        
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
if(!commonGroupIterator.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="text-01" colspan=9><div align="center">
                <br><br>还没有分组信息！
                        </div></td>
                       
                      </tr>
                      
                      <tr>
                        <td colspan=9 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        
                      </tr>
<%
}
int index = 0;
while(commonGroupIterator.hasNext()){
	AddressCommongroupVO addressGroupVO = (AddressCommongroupVO)commonGroupIterator.next();
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
%>
                      <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
                        <td height="22" class="text-01"><div align="center">
                            <input type="radio" name="groupid" onclick="javascript:UpdateApp('<%=addressGroupVO.getId()%>','<%=addressGroupVO.getGroupname()%>','<%=addressGroupVO.getGroupdes()%>','<%=addressGroupVO.getNeedaccredit()%>','<%=addressGroupVO.getNeedaccredit()%>')" value="<%=addressGroupVO.getId()%>">
                        </div></td>
                        <td  class="text-01"><div align="center"><%=index%></div></td>
                        <td  class="text-01"><div align="left">
                        <%if(flag.intValue()==1||"0".equals(addressGroupVO.getNeedaccredit())){%>
                        <a href="<%=request.getContextPath()%>/servlet/ListCommonTwoGroupServlet?ParentID=<%=addressGroupVO.getId()%>&shouquan=<%=addressGroupVO.getNeedaccredit()%>"><%=addressGroupVO.getGroupname()%></a></div>
                        <%}else if(handler1.isAccreded(addressGroupVO.getId(),user.getPersonUuid())){%>
                        <a href="<%=request.getContextPath()%>/servlet/ListCommonTwoGroupServlet?ParentID=<%=addressGroupVO.getId()%>&shouquan=<%=addressGroupVO.getNeedaccredit()%>"><%=addressGroupVO.getGroupname()%></a></div>
						<%}else{%>
                        <a href="<%=request.getContextPath()%>/servlet/ListCommonTwoGroupServlet?ParentID=<%=addressGroupVO.getId()%>&shouquan=<%=addressGroupVO.getNeedaccredit()%>"><%=addressGroupVO.getGroupname()%><% out.print("&nbsp;<font color='red'>[未被授权]</font>");%></a></div>
                        <%}%> 
                        </td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupdes()%></div></td>
                        <td class="text-01">
                        <%
                        if((flag.intValue()==1)){
                        if (addressGroupVO.getNeedaccredit().equals("1")){
	                    %>  
	                        <div align="center">
								<a href="ListGrouprightServlet?groupid=<%=addressGroupVO.getId()%>">授权</a><%if(handler1.getAccreditList(addressGroupVO.getId()).size()==0) out.print("&nbsp;<font color='red'>[没有进行授权]</font>"); %></div>
						<%
						}else{
						%>		
	                        <div align="center">
								此分组不需要授权</div>
						<%
						}}
						%>
                        <%
                        if(!(flag.intValue()==1)){
                        if (addressGroupVO.getNeedaccredit().equals("1")){
	                    %>  
	                        <div align="center">此分组需要授权</div>
						<%
						}else{
						%>		
	                        <div align="center">
								此分组不需要授权</div>
						<%
						}}
						%>		
						</td>
                      </tr>
                      
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
}//end while
%>
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              <%@ include file="../include/defaultPageScrollBar.jsp"%>
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
<br>
<br>

  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">
          分组信息</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
  </table>
	  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr  bgcolor="#FFFBEF">
                      <td width="20%" height="22" bgcolor="D8EAF8">
                        <div align="right">分组名称： </div></td>
                      <td width="2" rowspan="10" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="80%" bgcolor="#F2F9FF" >
                        <input name="groupName" type="text"  size="40" maxlength="128" class=txt2>
*</div></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr >
                      <td height="22" class="text-01"><div align="right">分组描述：
                      </div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
                       <input name="groupDes" type="text"   size="64" maxlength="128" class=txt2>
*                      </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr >
                      <td height="22" class="text-01"><div align="right">是否需要授权：</div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
	                      <input type=radio name=needAccredit value="1" checked> 是
						  <input type=radio name=needAccredit value="0" > 否
						  <input name="OldAccredit" type="hidden">
                      </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                      </table></td>
                    </tr>
                </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
  </table>
	   <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
  </table>

<br>  

<%if(flag.intValue()==1){%>
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="javascript:_Add()" >&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="javascript:_Update()">&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand" onClick="javascript:_Delete()" ><br>
<%}%>
</form>

</div>

</body>

</html>
<%
} catch (Exception ex) {
	ex.printStackTrace();
} finally {
	try {
		if (con != null){
			con.close();
			ConnLog.close("commonGroup.jsp");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	//
}%>
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
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.address.handler.Group" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>


<%
	Connection con = null;
	
	Collection commomCollection = (Collection)request.getAttribute("commonTwoGroup");  
	Iterator commonGroupIterator = commomCollection.iterator();
	String exist = (String) request.getAttribute("groupNameExist");
	
	Collection commomCollection1 = (Collection)request.getAttribute("commonTwoGroup1");  
	Iterator commonGroupIterator1 = commomCollection1.iterator();
%>
   
   
<%
try{ 
    con = com.icss.j2ee.services.DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("commonTwoGroup.jsp");
    Group handler1=new Group(con);%>
    
<html>
<head>
<title>分组列表</title>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript">
function UpdateApp(groupid,groupName,groupDes,needaccredit)
{
	 document.groupForm.groupid.value    = groupid;
	 document.groupForm.groupName.value   = groupName;
	 document.groupForm.groupDes.value   = groupDes;
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
  document.groupForm.action="CommonTwoGroupAddServlet";
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
   document.groupForm.action="CommonTwoGroupAlterServlet";
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
     document.groupForm.action="CommonTwoGroupDelServlet";
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

function _back1(){
     document.groupForm.action="<%=request.getContextPath()%>/servlet/ListCommonGroupServlet";
	 document.groupForm.submit();
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
&nbsp;&nbsp;&nbsp;请输入用户中文名查询此人所在的分组:
<input name="cnname" type="text"  size="30" maxlength="128" > 
<input name="QueryType" type="hidden" value="TwoGroup"> 
<img src="<%=request.getContextPath()%>/images/botton-search_in.gif" align="absmiddle" style="cursor:hand" onclick="javascript:search()">
</div>
</form>

<form name="groupForm" method="post" action=""> 
<input name="ParentID" type="hidden" value="<%=request.getAttribute("ParentID")%>">
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
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">可管理的二级公共分组列表
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
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr  bgcolor="#FFFBEF">
                        <td width="5%" height="22">
                          <div align="center"> </div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="5%" ><div align="center" class="title-04">编号</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" ><div align="center" class="title-04">名称</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">描述</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" height="22"><div align="center" class="title-04">分组人员</div></td>
                        
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
	AddressCommongroupAddressTransferVO addressGroupVO = (AddressCommongroupAddressTransferVO)commonGroupIterator.next();
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
%>
                      <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
                        <td height="22" class="text-01">
                        <%
                            Context ctx = Context.getInstance();
			              	UserInfo user = ctx.getCurrentLoginInfo();
			              	if(user.getPersonUuid().equals(addressGroupVO.getPersonuuid())){%>
                            <div align="center">
                            <input type="radio" name="groupid" onclick="javascript:UpdateApp('<%=addressGroupVO.getId()%>','<%=addressGroupVO.getGroupname()%>','<%=addressGroupVO.getGroupdes()%>','<%=addressGroupVO.getNeedaccredit()%>')" value="<%=addressGroupVO.getId()%>">
                            </div>
							<%}%> 
                        </td>
                        <td  class="text-01"><div align="center"><%=index%></div></td>
                        <td  class="text-01"><div align="center">
                         <a href="<%=request.getContextPath()%>/servlet/ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_COMMOM%>&shouquan=<%=addressGroupVO.getNeedaccredit()%>&ParentID=<%=addressGroupVO.getId()%>"><%=addressGroupVO.getGroupname()%></a></div></td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupdes()%></div></td>
                        <td class="text-01"><div align="center">
							<a href="ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_COMMOM%>&flag=two&pass=ok">查看</a>
							<%if(!handler1.personInGroupbyNameIsNull(addressGroupVO.getId(),AddressConfig.GROUPTYPE_COMMOM)){%>
                            <font color="red">[此分组下无人]</font></div><%}%></td>
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
          二级分组信息</td>
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
                        <div align="right">二级分组名称： </div></td>
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
                      <td height="22" class="text-01"><div align="right">二级分组描述：
                      </div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
                       <input name="groupDes" type="text"   size="64" maxlength="128" class=txt2>
*                      </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
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
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="javascript:_Add()" >&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="javascript:_Update()">&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand" onClick="javascript:_Delete()" >
    <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="_back1()" style="cursor:hand"> 
</form>
</div>

<%
if(exist != null && exist.equals("true")){
	
	out.print("<font color=red>分组："+(String)request.getAttribute("groupName")+" 已存在，请重新添加！</font><br>");
}
%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">其他的二级公共分组列表
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
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr  bgcolor="#FFFBEF">
                        <td width="5%" height="22">
                          <div align="center"> </div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="5%" ><div align="center" class="title-04">编号</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" ><div align="center" class="title-04">名称</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">描述</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" height="22"><div align="center" class="title-04">分组人员</div></td>
                        
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
if(!commonGroupIterator1.hasNext()){
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
int index1 = 0;
while(commonGroupIterator1.hasNext()){
	AddressCommongroupVO addressGroupVO = (AddressCommongroupVO)commonGroupIterator1.next();
	index1 ++;
	String color = "#F2F9FF";
	if(index1 % 2 == 1)
		color = "#D8EAF8";
%>
                      <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
                        <td height="22" class="text-01">
                        <!--<%
                            Context ctx = Context.getInstance();
			              	UserInfo user = ctx.getCurrentLoginInfo();
			              	if(user.getPersonUuid().equals(addressGroupVO.getOwner())){%>
                            <div align="center">
                            <input type="radio" name="groupid" onclick="javascript:UpdateApp('<%=addressGroupVO.getId()%>','<%=addressGroupVO.getGroupname()%>','<%=addressGroupVO.getGroupdes()%>','<%=addressGroupVO.getNeedaccredit()%>')" value="<%=addressGroupVO.getId()%>">
                            </div>
							<%}%> -->
                        </td>
                        <td  class="text-01"><div align="center"><%=index1%></div></td>
                        <td  class="text-01"><div align="center">
                         <a href="<%=request.getContextPath()%>/servlet/ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_COMMOM%>&shouquan=<%=addressGroupVO.getNeedaccredit()%>&ParentID=<%=addressGroupVO.getId()%>"><%=addressGroupVO.getGroupname()%></a></div></td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupdes()%></div></td>
                        <td class="text-01"><div align="center">
							<a href="ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_COMMOM%>">查看</a></div></td>
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
              <!--%@ include file="../include/defaultPageScrollBar.jsp"%-->
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>


</body>
</html>

<%
} catch (Exception ex) {
	ex.printStackTrace();
} finally {
	try {
		if (con != null){
			con.close();
			ConnLog.close("commonTwoGroup.jsp");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	//
}%>
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
<title>�����б�</title>
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
    alert("����д�������ƣ�");
    document.groupForm.groupName.focus;
    return false
    }
  if(document.groupForm.groupDes.value==""){
    alert("����д����������");
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
		alert("û��ѡ����飡");
      return false;
    }
  if(!IsChecked(groupForm.groupid,"��ѡ��һ���ַ���!")){
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
		alert("û��ѡ����飡");
      return false
    }
    if(!IsChecked(groupForm.groupid,"��ѡ��һ�����飡")){
      return false
    }
    ok=confirm("�˲�����ɾ����Ӧ���µ����й�����Ϣ,��ȷ��Ҫ������?");
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
		alert("����дҪ��ѯ���û�������!");
		return false;
	}
	if(getLength(document.searchForm.cnname.value)<=2){
    	alert("���ǵ���ѯ��Ч�����������������ϵĺ��ֽ��в�ѯ��");
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


<%--*******************���´�����վ��ͳ�Ƶ���չ���/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module8");
		String ip=request.getRemoteAddr();		
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************վ��ͳ�Ƶ���չ��ǵ��˽���/End****************************--%>

<div align="center">
<br>
<form name="searchForm" method="post">
<div align="left" class="title-04">
&nbsp;&nbsp;&nbsp;�������û���������ѯ�����Ƿ���Ȩ:
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
	
	out.print("<font color=red>���飺"+(String)request.getAttribute("groupName")+" �Ѵ��ڣ���������ӣ�</font><br>");
}
%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">���������б�
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
                        <td width="15%" ><div align="center" class="title-04">���</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" ><div align="center" class="title-04">����</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">����</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" height="22"><div align="center" class="title-04">ʹ����</div></td>
                        
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
                <br><br>��û�з�����Ϣ��
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
                        <a href="<%=request.getContextPath()%>/servlet/ListCommonTwoGroupServlet?ParentID=<%=addressGroupVO.getId()%>&shouquan=<%=addressGroupVO.getNeedaccredit()%>"><%=addressGroupVO.getGroupname()%><% out.print("&nbsp;<font color='red'>[δ����Ȩ]</font>");%></a></div>
                        <%}%> 
                        </td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupdes()%></div></td>
                        <td class="text-01">
                        <%
                        if((flag.intValue()==1)){
                        if (addressGroupVO.getNeedaccredit().equals("1")){
	                    %>  
	                        <div align="center">
								<a href="ListGrouprightServlet?groupid=<%=addressGroupVO.getId()%>">��Ȩ</a><%if(handler1.getAccreditList(addressGroupVO.getId()).size()==0) out.print("&nbsp;<font color='red'>[û�н�����Ȩ]</font>"); %></div>
						<%
						}else{
						%>		
	                        <div align="center">
								�˷��鲻��Ҫ��Ȩ</div>
						<%
						}}
						%>
                        <%
                        if(!(flag.intValue()==1)){
                        if (addressGroupVO.getNeedaccredit().equals("1")){
	                    %>  
	                        <div align="center">�˷�����Ҫ��Ȩ</div>
						<%
						}else{
						%>		
	                        <div align="center">
								�˷��鲻��Ҫ��Ȩ</div>
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
          ������Ϣ</td>
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
                        <div align="right">�������ƣ� </div></td>
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
                      <td height="22" class="text-01"><div align="right">����������
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
                      <td height="22" class="text-01"><div align="right">�Ƿ���Ҫ��Ȩ��</div></td>
                      <td bgcolor="#F2F9FF"  class="text-01">
	                      <input type=radio name=needAccredit value="1" checked> ��
						  <input type=radio name=needAccredit value="0" > ��
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
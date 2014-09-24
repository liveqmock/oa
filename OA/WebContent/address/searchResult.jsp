<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.AddressCommongroupVO"%>
<%@ page import="com.icss.oa.config.AddressConfig"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>

<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>

<%
Collection groupCollection = (Collection)request.getAttribute("personGroupList");
Collection accredCollection = (Collection)request.getAttribute("accredPersonList");
Iterator groupGroupIterator = groupCollection.iterator();
Iterator accredIterator = accredCollection.iterator();
String userID =  request.getParameter("cnname");
String QueryType = request.getParameter("QueryType");
String uuid1 = (String)request.getAttribute("uuid1");
%>
<html>
<head>
<title>搜索结果</title>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script language="JavaScript">


function checkgroup(){
	for (var i=0;i<document.groupForm.elements.length;i++)
   {
     var e = document.groupForm.elements[i];
	  if (e.name == 'groupid' && e.checked){
		 return true;
		}
   }
   return false;
}

function _deletePeople(){
	if(checkgroup()){
		document.groupForm.action="<%=request.getContextPath()%>/servlet/DeletePersonInfoServlet";
		document.groupForm.submit();
	}
	else{
		alert("请选择要删除的人员！ ");
	}
}

function checkaccred(){
	for (var i=0;i<document.accredForm.elements.length;i++)
   {
     var e = document.accredForm.elements[i];
	  if (e.name == 'groupid' && e.checked){
		 return true;
		}
   }
   return false;
}

function _deleteaccred(){
	if(checkaccred()){
		document.accredForm.action="<%=request.getContextPath()%>/servlet/DeletePersonInfoServlet";
		document.accredForm.submit();
	}
	else{
		alert("请选择要删除的人员！");
	}
}
  
function CheckAll()
 {
   for (var i=0;i<document.groupForm.elements.length;i++)
   {
     var e = document.groupForm.elements[i];
	  if (e.name == 'groupid')
		 e.checked = document.groupForm.allbox.checked;
   }
 }
 
function CheckAllAccred()
 {
   for (var i=0;i<document.accredForm.elements.length;i++)
   {
     var e = document.accredForm.elements[i];
	  if (e.name == 'groupid')
		 e.checked = document.accredForm.allbox.checked;
   }
 }
 
function _back1(){
     document.accredForm.action="<%=request.getContextPath()%>/servlet/ListCommonGroupServlet";
	 document.accredForm.submit();
}

function _back2(){
     document.groupForm.action="<%=request.getContextPath()%>/servlet/ListCommonGroupServlet";
	 document.groupForm.submit();
}
</script>
</head>

<body text=#eeeeee bgColor=#ffffff leftMargin=0 background="<%=request.getContextPath()%>/images/bg-08.gif" topMargin=0 marginheight="0" 
marginwidth="0">

<div align="center">
<br>
<%if("TwoGroup".equals(QueryType)){%>
<form name="groupForm" method="post" action=""> 
<input type="hidden" name="userID" value="<%=uuid1%>">
<input type="hidden" name="type" value="groupinfo">
<input type="hidden" name="cnname" value="<%=request.getAttribute("cnnname1")%>">
<input type="hidden" name="QueryType" value="<%= QueryType %>">

          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">你所输入的帐号[<%=userID%>] 所在二级公共分组列表
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
                        <td width="10%" height="22"><div align="center" class="title-04">编号</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">名称</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">描述</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">分组人员</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">部门</div></td>

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
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
if(!groupGroupIterator.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="text-01" colspan=13><div align="center">
                <br><br>该人员还没有被分配到二级公共分组中！
                        </div></td>
                       
                      </tr>
                      
                      <tr>
                        <td colspan=13 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        
                      </tr>
<%
}
int index = 0;
EntityManager entityManager = EntityManager.getInstance();
while(groupGroupIterator.hasNext()){
	AddressCommongroupVO addressGroupVO = (AddressCommongroupVO)groupGroupIterator.next();
	Person person = entityManager.findPersonByUuid(uuid1);
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
%>
                      <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">

                        <td  class="text-01" height="22"><div align="center"><%=index%></div></td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupname()%></div></td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupdes()%></div></td>
                        <td  class="text-01"><div align="center"><%=person.getFullName()%></div></td>
                        <td  class="text-01"><div align="center"><%=person.getPrimaryOrg().getName()%></div></td>
                        <td  class="text-01"><div align="center"><%=(person.getPosition().getPname()) == null?"":person.getPosition().getPname()%></div></td>
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
          </table><br>
              <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="_back2()" style="cursor:hand"> </p>
</form><%}%>
<br>
<br>
<%if("RootGroup".equals(QueryType)){%>
<form name="accredForm" method="post" action=""> 
<input type="hidden" name="type" value="accredinfo">
<input type="hidden" name="userID" value="<%=uuid1%>">
<input type="hidden" name="cnname" value="<%=request.getAttribute("cnnname1")%>">
<input type="hidden" name="QueryType" value="<%= QueryType %>">
         <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">你所输入的帐号[<%=userID%>]已授权使用的一级公共分组列表
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

                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="10%" height="22"><div align="center" class="title-04">编号</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">名称</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">描述</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">授权人员</div></td>
                        <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="" ><div align="center" class="title-04">部门</div></td>
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
if(!accredIterator.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="text-01" colspan=13><div align="center">
                <br><br>该人员还没有授权可以使用的一级公共分组！
                        </div></td>
                        
                       
                      </tr>
                      
                      <tr>
                        <td colspan13 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        
                      </tr>
<%
}
int index1 = 0;
EntityManager entityManager = EntityManager.getInstance();
while(accredIterator.hasNext()){
	AddressCommongroupVO addressGroupVO = (AddressCommongroupVO)accredIterator.next();
	index1 ++;
	Person person = entityManager.findPersonByUuid(uuid1);
	String color = "#F2F9FF";
	if(index1 % 2 == 1)
		color = "#D8EAF8";
%>
                      <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">                       
                        <td  class="text-01" height="22"><div align="center"><%=index1%></div></td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupname()%></div></td>
                        <td  class="text-01"><div align="center"><%=addressGroupVO.getGroupdes()%></div></td>
                        <td  class="text-01"><div align="center"><%=person.getFullName()%></div></td>
                        <td  class="text-01"><div align="center"><%=person.getPrimaryOrg().getName()%></div></td>

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
          </table><br>
           <!--img src="<%=request.getContextPath()%>/images/botton-delete.gif" style="cursor:hand" width="70" height="22" hspace="10" onclick="_deleteaccred()"-->
           <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="_back1()" style="cursor:hand"> </p>
</form><%}%>
<br>
    

</div>

</body>

</html>
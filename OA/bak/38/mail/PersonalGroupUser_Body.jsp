<%@ page contentType="text/html; charset=GBK" %>
<%@ page pageEncoding="GBK" %>

<%
String path = request.getContextPath();
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@ page import="java.util.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%

Collection groupinfoCollection = (Collection)request.getAttribute("groupinfo");

Iterator groupinfoIterator = groupinfoCollection.iterator();

String groupname = (String)request.getAttribute("groupname");
String owner = (String) request.getAttribute("owner");
try{
%>
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>

<html>

<head>
<title>人员列表</title>
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<SCRIPT LANGUAGE="JavaScript">
function _selectPerson(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=selectorgperson','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
	//alert(usernamestring);
	window.top.bodyfrm.location.href = "<%=request.getContextPath()%>/servlet/AddGroupInfoServlet?groupid=<%=request.getParameter("groupid")%>&type=<%=request.getParameter("type")%>";
	return true;
}

function check(){
	for (var i=0;i<document.groupForm.elements.length;i++)
   {
     var e = document.groupForm.elements[i];
	  if (e.name == 'personid' && e.checked){
		 return true;
		}
   }
   
   return false;
}

function _deletePeople(){
	if(check()){
		document.groupForm.action="<%=request.getContextPath()%>/servlet/DeletePersonServlet";
		document.groupForm.submit();
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
	  if (e.name == 'personid')
		 e.checked = document.groupForm.allcheck.checked;
   }
 }
 
function _back1(){

     document.groupForm.action="<%=request.getContextPath()%>/servlet/ListGroupServlet";
	 document.groupForm.submit();
}
</script>
</head>


<body onload="javascript:frameautoheight()">
<form name="groupForm" method="post" >
<input type="hidden" name="groupid" value="<%=(String)request.getAttribute("groupid")%>">
<input type="hidden" name="type" value="<%=request.getParameter("type")%>">


<!--主要内容去区-->
<table width="750" border="0" cellspacing="0" cellpadding="0">


    <tr>
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
	<tr>
    	<td colspan="6" height="11" class="message_title_bold" align="center"><%=groupname%>分组人员列表</td>
    </tr>
    
  
    <tr>
    	<td width="8">&nbsp;</td>
        
      <td valign="top">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
        <tr>
          <td nowrap="nowrap" width="5%" height="26" class="block_title"><div align="center">
		  <INPUT TYPE="checkbox" NAME="allcheck" onclick="javascript:CheckAll()"></div>
		  </td>
          <td width="10%" class="block_title"><div align="center">编号</div></td>
          <td width="15%" class="block_title"><div align="center">姓名</div></td>
		   <td width="10%" class="block_title"><div align="center">性别</div></td>
		   <td width="15%" class="block_title"><div align="center">职务</div></td>
          <td width="15%" class="block_title"><div align="center">电话</div></td>
          <td width="30%" class="block_title"><div align="center">部门</div></td>
        </tr>


<%
if(!groupinfoIterator.hasNext()){
%>

<tr bgColor='#D8EAF8'>
   <td height="52" class="message_title_bold" colspan=13><div align="center">
                <br><br>还没有添加分组人员！<br>
              </div></td>
</tr>
<%
}

int index = 0;
EntityManager entityManager = EntityManager.getInstance();
while(groupinfoIterator.hasNext()){
	AddressGroupinfoSysPersonSearch1VO groupInfoVO = (AddressGroupinfoSysPersonSearch1VO)groupinfoIterator.next();
	
	Person person = null;	
	
	Integer k11 = null;
	
    try{
		person = entityManager.findPersonByUuid(groupInfoVO.getUserid());
		k11 = person.getSex();
	}
	catch(EntityException e){
		e.printStackTrace() ;
	}
	if(k11!=null){
	
	index ++;
%>


        <tr>
			<td height="22" bgcolor="#FFFFFF" width="5%">
           <div align="center">
               <%Context ctx = Context.getInstance();
			    UserInfo user = ctx.getCurrentLoginInfo();
			    if(user.getPersonUuid().equals(owner)){%>
           <input type="checkbox" name="personid" value="<%=groupInfoVO.getUserid()%>"><%}%> 
		   </div>
		   </td>

<%
            String sex ="未知";
            if (person.getSex().equals(new Integer(0)))
				sex = "保密";
			if (person.getSex().equals(new Integer(1)))
				sex = "男";
			if (person.getSex().equals(new Integer(2)))
				sex = "女";
			if (person.getSex().equals(new Integer(3)))
				sex = "未知";

%>
          <td bgcolor="#FFFFFF" class="message_title_bold" width="10%"><div align="center"><%=index%></div></td>
          <td bgcolor="#FFFFFF" class="message_title_bold" width="15%"><div align="center"><%=person.getFullName()%></div></td>
          <td bgcolor="#FFFFFF" class="message_title_bold" width="10%"><div align="center"><%= sex %></div></td>
          <td bgcolor="#FFFFFF" class="message_title_bold" width="15%"><div align="center"><%=""%></div>
		<td bgcolor="#FFFFFF" class="message_title_bold" width="15%"><div align="center"><%= person.getOfficetel()%></div></td>
		<td bgcolor="#FFFFFF" class="message_title_bold" width="30%"><div align="center"><%= handler.getPersonJUPositionInJsp(groupInfoVO.getUserid())%></div></td>
		  </td>
        </tr>

<%
}}
%> 

		<tr><td colspan="7"><%@ include file="../include/FolderPageScrollBar.jsp"%></td></tr>
        
                     
      </table></td>
    </tr>
      </table>      
</td>
    </tr>
    
    
    <tr>
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
          <%
                Context ctx = Context.getInstance();
			    UserInfo user = ctx.getCurrentLoginInfo();
			    if(user.getPersonUuid().equals(owner)){%>	  
<table width="750" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan=2 height="11" class="message_title_bold" align="center">
			<a href="#" onclick="javascript:_selectPerson('<%=request.getContextPath()%>')">添加本组人员</a>&nbsp;
			<a href="#" onclick="javascript:_deletePeople()">删除</a>&nbsp;
			<a href="#" onclick="javascript:_back1()">返回</a>
		</td>
	</tr>
</table>
 <%}%>
</form>
</body>
</html>

<%
}catch(Exception e){
		e.printStackTrace();
}
%>
<script>
function frameautoheight(){
	parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
	parent.document.all("bodyfrm").style.width=document.body.scrollWidth;
}
//frameautoheight();
</script>
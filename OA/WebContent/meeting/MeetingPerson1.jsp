<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupinfoVO"%>
<%@ page import="com.icss.oa.meeting.vo.*"%>
<%@ page import="com.icss.oa.address.helper.AddressViewHelper"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>

<%
int num =((Integer)request.getAttribute("num")).intValue();
List list = (List)request.getAttribute("list");
Iterator it = list.iterator();
try{
%>
<html>

<head>
<title>人员列表</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT LANGUAGE="JavaScript">

function _selectPerson(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=selectorgperson','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
	window.top.mainFrame.location = "<%=request.getContextPath()%>/servlet/AddPersonServlet?putId=<%=request.getParameter("putId")%>";
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
		document.groupForm.action="<%=request.getContextPath()%>/servlet/DelPersonServlet?putId=<%=request.getParameter("putId")%>";
		document.groupForm.submit();
	}
	else{
		alert("请选择要删除的人员！");
	}
}

function _attendPeople(){
	if(check()){
		document.groupForm.action="<%=request.getContextPath()%>/servlet/AttendPersonServlet?putId=<%=request.getParameter("putId")%>&type=canjia";
		document.groupForm.submit();
	}
	else{
		alert("请选择实际与会的人员！");
	}
}

function _UnattendPeople(){
	if(check()){
		document.groupForm.action="<%=request.getContextPath()%>/servlet/AttendPersonServlet?putId=<%=request.getParameter("putId")%>&type=Notcanjia";
		document.groupForm.submit();
	}
	else{
		alert("请选择实际与会的人员！");
	}
}



function CheckAll()
 {
   for (var i=0;i<document.groupForm.elements.length;i++)
   {
     var e = document.groupForm.elements[i];
	  if (e.name == 'personid')
		 e.checked = document.groupForm.allbox.checked;
   }
 }
</script>
</head>
<body  background="<%=request.getContextPath()%>/images/bg-08.gif">

<form name="groupForm" method="post" >
<div align="center">
  <table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td valign="top"><div align="center"><br>
        </div>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">与会人员列表  共<%= num %>人</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr  bgcolor="#FFFBEF">
                          <td width="10%" ><div align="center" class="title-04">序号</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="16%" height="22" ><div align="center" class="title-04">姓名</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="8%" ><div align="center" class="title-04">性别</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="20%" ><div align="center" class="title-04">职务</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td><div align="center" class="title-04">部门</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>

                         

                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
<%
if(!it.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="40" class="text-01" colspan=15><div align="center">
                <br><br>还没有添加与会人员人员！<br>
                        </div></td>
                       
                      </tr>
                      
                      <tr>
                        <td colspan=13 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td colspan=13 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      </tr>
<%
}

int index = 0;
EntityManager entityManager = EntityManager.getInstance();
while(it.hasNext()){
	MeetingPersonVO vo= (MeetingPersonVO)it.next();
	
	Person person = null;
	Integer k11 = null;
	
	try{
		person = entityManager.findPersonByUuid(vo.getPerson());
		k11 = person.getSex();
	}
	catch(EntityException e){
		e.printStackTrace() ;
	}

if(k11!=null){
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
%>
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
                       <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
                          <td  class="text-01"><div align="center"><%= (vo.getOrder1().intValue()==10000)?"未进行排序":vo.getOrder1().toString()%></div></td>
                          <td  class="text-01" height="22"><div align="center"><%=person.getFullName()%></div></td>
                          <td  class="text-01"><div align="center"><%= sex%></div></td>
                          <td  class="text-01"><div align="center"><%=person.getJob()== null?"":person.getJob()%></div></td>
                          <td  class="text-01"><div align="center"><%=person.getPrimaryOrg().getName()%></div></td>
                    
                          
                          			
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
<%
}}
//end while
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
          <p align="center">
          <img  src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand">
          </p>
          </td>
    </tr>
  </table>

</div>
</form>
</body>

</html>
<%
}catch(Exception e){
		e.printStackTrace();
}
%>
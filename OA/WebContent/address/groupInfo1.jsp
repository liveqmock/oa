<%@ page contentType="text/html; charset=GBK" %>

<%
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
String pass =(String)request.getParameter("pass");
Integer _2flag = (Integer)request.getAttribute("_2flag")!=null?(Integer)request.getAttribute("_2flag"):new Integer(0);
Integer OneManager_flag = (Integer)request.getAttribute("OneManager_flag")==null?new Integer(0):(Integer)request.getAttribute("OneManager_flag");

try{
%>
<html>
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>
<head>
<title>��Ա�б�</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT LANGUAGE="JavaScript">
function _selectPerson(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=selectorgperson','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
	window.top.mainFrame.location = "<%=request.getContextPath()%>/servlet/AddGroupInfoServlet?groupid=<%=request.getParameter("groupid")%>&type=<%=request.getParameter("type")%>";
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
		alert("��ѡ��Ҫɾ������Ա��");
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
 
function _back1(){


     document.groupForm.action="<%=request.getContextPath()%>/servlet/ListCommonGroupServlet";
	 document.groupForm.submit();
}
</script>
</head>
<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
<form name="groupForm" method="post" >
<input type="hidden" name="groupid" value="<%=(String)request.getAttribute("groupid")%>">
<input type="hidden" name="type" value="<%=request.getParameter("type")%>">
<div align="center">
  <table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td valign="top"><div align="center"><br>
        </div>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05"><%=groupname%>  ������Ա�б�</td>
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
                          <td width="5%" height="22"> <div align="center">
                          </div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="7%" ><div align="center" class="title-04">���</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="16%" ><div align="center" class="title-04">����</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="8%" ><div align="center" class="title-04">�Ա�</div></td>
                           <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="15%" ><div align="center" class="title-04">ְ��</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="14%" ><div align="center" class="title-04">�绰</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="35%" ><div align="center" class="title-04">����</div></td>
                          
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
                          
                        </tr>
<%
if(!groupinfoIterator.hasNext()){
%>
<tr bgColor='#D8EAF8'>
   <td height="52" class="text-01" colspan=13><div align="center">
                <br><br>��û����ӷ�����Ա��<br>
                        </div></td>
                       
                      </tr>
                      
                      <tr>
                        <td colspan=13 height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        
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
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
%>
                       <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
                          <td height="22" class="text-01"><div align="center">
                            <%
               // Context ctx = Context.getInstance();
			   // UserInfo user = ctx.getCurrentLoginInfo();
			    if(_2flag.intValue()==1||OneManager_flag.intValue()==1){%>
                            <input type="checkbox" name="personid" value="<%=groupInfoVO.getUserid()%>"><%}%>
<%
            String sex ="δ֪";
            if (person.getSex().equals(new Integer(0)))
				sex = "����";
			if (person.getSex().equals(new Integer(1)))
				sex = "��";
			if (person.getSex().equals(new Integer(2)))
				sex = "Ů";
			if (person.getSex().equals(new Integer(3)))
				sex = "δ֪";

%>
                          </div></td>
                          <td  class="text-01"><div align="center"><%=index%></div></td>
                          <td  class="text-01"><div align="center"><%=person.getFullName()%></div></td>
                          <td  class="text-01"><div align="center"><%= sex %></div></td>
                          <td  class="text-01"><div align="center"><%=""%></div></td>
                          <td  class="text-01"><div align="center"><%= person.getOfficetel() %></div></td>
                          <td  class="text-01"><div align="center"><!--%=person.getPrimaryOrg().getName()==null?"":person.getPrimaryOrg().getName()%--><%= handler.getPersonJUPositionInJsp(groupInfoVO.getUserid())%></div></td>

                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
<%
}

}
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
          <%
              //  Context ctx = Context.getInstance();
			   // UserInfo user = ctx.getCurrentLoginInfo();
			    if(_2flag.intValue()==1||OneManager_flag.intValue()==1){%>
          
          <input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();">
          <label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">ȫѡ</a></label>  
          <img src="<%=request.getContextPath()%>/images/button_addperson.gif" style="cursor:hand"  height="22" hspace="10"  onclick="_selectPerson('<%=request.getContextPath()%>')">
          <img src="<%=request.getContextPath()%>/images/botton-delete.gif" style="cursor:hand" width="70" height="22" hspace="10" onclick="_deletePeople()">
          <%}%>

          <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="_back1()" style="cursor:hand"> </p> 
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
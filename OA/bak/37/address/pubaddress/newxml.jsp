<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.oa.address.handler.PersonOrderList" %>
<%
String orguuid = request.getParameter("orguuid");
List personlist = null;
try {
	EntityManager entitymanger = EntityManager.getInstance();
	personlist = PersonOrderList.personList(entitymanger.findPersonsRecursivelyByOrgUuid(orguuid));
}catch (Exception ex) {
	ex.printStackTrace();
}
%>
<?xml version="1.0" encoding="gb2312" ?>
<Dept_UserList>
<%
	if(personlist!=null){
	Iterator iter = personlist.iterator();
	int i=0;
	while(iter.hasNext()){
		++i;
		Person person = (Person)iter.next();
%>
	<UserList>
		<UserName><%=person.getLoginName()%></UserName>
		<truename><%=person.getFullName()%></truename>
		<PersonUuid><%=person.getUuid()%></PersonUuid>
	</UserList>
<%
	}
	}
%>
</Dept_UserList>
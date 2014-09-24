<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="java.util.List"  %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.icss.oa.sync.vo.PersonVO" %>
<%@ page import="com.icss.oa.hr.vo.HRPersonTempVO" %>

<%
List list = (List)request.getAttribute("faultlist"); 
List list1 = (List)request.getAttribute("faultlist1"); 

if(list!=null && list.size()>0){

Iterator it = list.iterator();

while(it.hasNext()){
PersonVO vo = (PersonVO)it.next();
%>
<div>
<%=vo.getCnname() %>
</div>

<% }

}
if(list1!=null && list1.size()>0){

Iterator it1 = list.iterator();

while(it1.hasNext()){
HRPersonTempVO vo = (HRPersonTempVO)it1.next();
%>
<div>
<%=vo.getUsername() %>
</div>

<% 
}
}

%>
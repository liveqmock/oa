<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>

<%
    List list1 = (List)request.getAttribute("list1");
    List list2 = (List)request.getAttribute("list2");
%>

<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">  
<style type="text/css">
<!--
.style1 {color: #CC0000}
-->
</style>

<SCRIPT LANGUAGE="JavaScript">
<!--
var personuuid;

function _commit(){

	if(check()){
	    document.form1.action="<%=request.getContextPath()%>/servlet/AddTransfServlet?groupid=<%= request.getParameter("groupid")%>";  
		document.form1.submit();
		//setTimeout('',2000);
		var doUrl = "window.opener._addPerson('<%= request.getParameter("groupid")%>')";
		eval(doUrl);
		window.close();
		}
	else{
		alert("请选择要添加的人员！");
		}
		
}

function _update(personuuid1){
   document.form1.h1.value = personuuid1;
}

function check(){
	for (var i=0;i<document.form1.elements.length;i++)
   {
     var e = document.form1.elements[i];
	  if (e.name == 'paraid' && e.checked){
		 return true;
		}
   }
   return false;
}
//-->
</SCRIPT>

<TITLE><%=request.getParameter("Groupname")%></TITLE>
</head>
<script language="javascript">
function _openInfoId(InfoId){
	window.location="<%=request.getContextPath()%>/servlet/ListAllMessageServlet?dateType=all&infoId="+InfoId;
}
function _openSortId(SortId){
	window.location="<%=request.getContextPath()%>/servlet/ListMsgServlet?dateType=all&sortId="+SortId;
}
</script>
<body bgcolor="#eff7ff">
<form name="form1" method="post" action="">
<input type=hidden name=h1 value="">
<table id="wang" width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><div align="center">
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td bgcolor="#eff7ff" height="23">
             <font color=red>一级信息管理员</font>
          </td>
        </tr>
        <tr>
          <td height="81" bgcolor="#D8EAF8"><div align="center">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
              
<%
            Iterator it1 = null;
            if(list1!=null) {it1 = list1.iterator();}
            int pp1 = 0;
            
			while(it1.hasNext()){
				Person vo1 = (Person)it1.next();
				pp1++;%>
				
              <tr bgcolor="#D8EAF8">
                     <td  width="40%" height="23" class="text-01">
                	<input type=radio name=paraid  value="<%= vo1.getUuid()%>" onClick="h1.value=<%= vo1.getUuid()%>">
                    <%= vo1.getFullName()%> 
			     	</td>
						  
					<td width="60%">
						<%= handler.getPersonJUPositionInJsp(vo1.getUuid()) 
	                    %> 
					</td>
              </tr>
<%
			}
%>	
            </table>
            </div></td>
        </tr>
      </table>
    
    
    
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td bgcolor="#eff7ff" height="23">
             <font color=red>二级信息管理员</font>
          </td>
        </tr>
        <tr>
          <td height="81" bgcolor="#D8EAF8"><div align="center">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
              
<%
            Iterator it2 = null;
            if(list2!=null) {it2 = list2.iterator();}
            int pp = 0;
            
			while(it2.hasNext()){
				Person vo2 = (Person)it2.next();
				pp++;%>
				
              <tr bgcolor="#D8EAF8">
              
                     <td  width="40%" height="23" class="text-01">
                	<input type=radio name=paraid  value="<%= vo2.getUuid()%>" onClick="javaSript:_update(<%= vo2.getUuid()%>);">
                    <%= vo2.getFullName()%>
				     </td>
				     
                     <td width="60%">
                     <%= handler.getPersonJUPositionInJsp(vo2.getUuid())  
						//Organization org = vo2.getPrimaryOrg();
						//out.print(org.getName());
                    	%> 
                     </td>
<%
			}
%>
              </tr>

            </table>
            </div></td>
        </tr>
      </table>
      
      
      </div>    </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" height="45" align="center">
     <tr>
     	<td>
    		 <div align="center"><span class="text-01"><img onClick="javascript:_commit()" src="<%=request.getContextPath()%>/images/botton-ok.gif"style="cursor:hand"></span></div>
    	 </td>
	 </tr>
</table>

</form>
</body>

</html>
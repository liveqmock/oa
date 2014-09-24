


<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.shortmessage.maintenance.vo.*"%>


<%

//Collection commomCollection = (Collection)request.getAttribute("commonGroup");
//Collection indiCollection = (Collection)request.getAttribute("indiGroup");
//Iterator commonGroupIterator = commomCollection.iterator();
//Iterator indiGroupIterator = indiCollection.iterator();

List list=(List)request.getAttribute("list");
Iterator iter=list.iterator();
%>
<html>
<head>
<title>短信组织</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview_sm.js" type="text/JavaScript"></script>
<SCRIPT language="JavaScript" >
<%
int commRoot = -1;
%>
var tree=new TreeView('tree','_parent')
tree.add(<%=commRoot%>,0,'短信部门','','','mainFrame')					  
<%
int index = -1;

while(iter.hasNext()){
	DuanxinShortmappingSysOrgVO vo = (DuanxinShortmappingSysOrgVO)iter.next();
	index --;
%>
tree.add(<%=index%>,<%=commRoot%>,' <%=vo.getCnname()%>','<%=request.getContextPath()%>/servlet/ListOrgSMpersonServlet?orgid=<%=vo.getDepid()%>','','mainFrame')		
<%
}

%>


</script>

</head>
<body text=#eeeeee bgColor=#d8eaf8 leftMargin=5 topMargin=0 marginheight="0" 
marginwidth="0">

 <table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-03.gif" width="178" height="28"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath()%>/images/left_img/07.gif">
    <table width="156"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6" colspan="2"></td>
        </tr>
      <tr>
        <td width=15></td><td colspan="2" class="left" >

	
		  <script>
			  document.write(tree);
			   tree.keepOpen('0');
		 </script>
         

         </td>
        </tr>
		
    </table>
	</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/08.gif" width="178" height="7"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  <%@ include file= "../../include/searchLeft.jsp" %>
  
 
</table>

</body>
</html>

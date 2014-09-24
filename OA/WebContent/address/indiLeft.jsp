<!-- /address/indiLeft.jsp -->
<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.oa.config.AddressConfig"%>
<%
Collection commomCollection = (Collection)request.getAttribute("commonGroup");
Collection indiCollection = (Collection)request.getAttribute("indiGroup");
Collection commomTwoCollection = (Collection)request.getAttribute("commonTwoGroup");
Iterator commonGroupIterator = commomCollection.iterator();
Iterator indiGroupIterator = indiCollection.iterator();
Iterator commonTwoGroupIterator = commomTwoCollection.iterator();
%>
<html>
<head>
<title>个人地址簿</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview_pro.js" type="text/JavaScript"></script>
<SCRIPT language="JavaScript" >
<%
int IndiRoot = 1;
%>

var inditree=new TreeView('inditree','_parent')
inditree.add(<%=IndiRoot%>,0,' 个人分组','<%=request.getContextPath()%>/servlet/ListGroupServlet','','mainFrame')					  
<%
int indiIndex = 1;

while(indiGroupIterator.hasNext()){
	AddressGroupVO addressGroupVO = (AddressGroupVO)indiGroupIterator.next();
	indiIndex ++;
%>
inditree.add(<%=indiIndex%>,<%=IndiRoot%>,' <%=addressGroupVO.getGroupname()%>','<%=request.getContextPath()%>/servlet/ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_PRIVATE%>','','mainFrame')		
<%
}
%>
</script>

</head>
<body text=#eeeeee bgColor=#d8eaf8 leftMargin=5 topMargin=0 marginheight="0" 
marginwidth="0">
<br>
<%@ include file= "/include/intendWork.jsp" %>    
<br>
 <table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-05.gif" width="178" height="28"></td>
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
			  document.write(inditree);
			   //inditree.keepOpen('1');
		 </script>
<br>
	
		  <script>
			  //document.write(tree);
			   //tree.keepOpen('-1');
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
</table>

</body>
</html>
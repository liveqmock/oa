<!-- /address/indiLeft.jsp -->
<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.oa.config.AddressConfig"%>
<%
Collection indiCollection = (Collection)request.getAttribute("indiGroupList");
Iterator indiGroupIterator = indiCollection.iterator();

String personUUid = (String)request.getAttribute("personUUid");
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

var inditree=new TreeView('inditree','_self')
<%
if(indiCollection.size()>0){
%>
inditree.add(<%=IndiRoot%>,0,' 个人分组','','','mainFrame')					  
<%}else{%>
inditree.add(<%=IndiRoot%>,0,' 个人分组（无个人分组）','<%=request.getContextPath()%>/filetransfer/closeGroup.jsp','','')
<%}
int indiIndex = 1;

while(indiGroupIterator.hasNext()){
	AddressGroupVO addressGroupVO = (AddressGroupVO)indiGroupIterator.next();
	indiIndex ++;
%>
inditree.add(<%=indiIndex%>,<%=IndiRoot%>,' <%=addressGroupVO.getGroupname()%>','<%=request.getContextPath()%>/servlet/AddToGroupServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_PRIVATE%>&personUUid=<%=personUUid%>','','')		
<%
}
%>
</script>

</head>
<body text=#eeeeee bgColor=#d8eaf8 leftMargin=5 topMargin=5 marginheight="0" marginwidth="0">
 <table width="180" border="0" align="center" cellpadding="0" cellspacing="0">
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
    <table width="160"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6" colspan="2"></td>
        </tr>
      <tr>
        <td width=15></td>
        <td colspan="2" class="left" >
			<script>
			  document.write(inditree);
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
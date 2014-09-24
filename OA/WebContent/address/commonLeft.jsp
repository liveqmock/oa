<!-- /bbs/commonLeft.jsp -->
<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.AddressCommongroupVO"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.oa.config.AddressConfig"%>
<%
Collection commomCollection = (Collection)request.getAttribute("commonGroup");
Collection commonTwoCollection = (Collection)request.getAttribute("commonTwoGroup");
Iterator commonGroupIterator = commomCollection.iterator();
Iterator commonTwoGroupIterator = commonTwoCollection.iterator();
%>
<html>
<head>
<title>公共分组</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview_pro.js" type="text/JavaScript"></script>
<SCRIPT language="JavaScript" >
<%
int commRoot = -1;
%>
var tree=new TreeView('tree','_parent')
tree.add(<%=commRoot%>,0,' 公共分组','<%=request.getContextPath()%>/servlet/ListCommonGroupServlet','','mainFrame')					  
<%
int index = -1;
int indexTwo = 1;

while(commonGroupIterator.hasNext()){
	AddressCommongroupVO addressCommongroupVO = (AddressCommongroupVO)commonGroupIterator.next();
	index --;
%>
tree.add(<%=index%>,<%=commRoot%>,' <%=addressCommongroupVO.getGroupname()%>','<%=request.getContextPath()%>/servlet/ListCommonTwoGroupServlet?ParentID=<%=addressCommongroupVO.getId()%>&shouquan=<%=addressCommongroupVO.getNeedaccredit()%>','','mainFrame')		
<%    
      commonTwoCollection = (Collection)request.getAttribute("commonTwoGroup");
      commonTwoGroupIterator = commonTwoCollection.iterator();
      while(commonTwoGroupIterator.hasNext()){
          AddressCommongroupVO addressCommonTwogroupVO = (AddressCommongroupVO)commonTwoGroupIterator.next();
            if(addressCommonTwogroupVO.getParentid().intValue()==addressCommongroupVO.getId().intValue())
              { indexTwo ++;%>
tree.add(<%=indexTwo%>,<%=index%>,' <%=addressCommonTwogroupVO.getGroupname()%>','<%=request.getContextPath()%>/servlet/ListGroupInfoServlet?groupid=<%=addressCommonTwogroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_COMMOM%>&shouquan=<%=addressCommongroupVO.getNeedaccredit()%>&ParentID=<%=addressCommongroupVO.getId()%>','','mainFrame')		     
              <%} 

}

}
%>


</script>

</head>
<body text=#eeeeee bgColor=#d8eaf8 leftMargin=5 topMargin=0 marginheight="0" 
marginwidth="0">
<br>
<br>
 <table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-06.gif" width="178" height="28"></td>
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

</table>

</body>
</html>
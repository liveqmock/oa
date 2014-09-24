<%@ page contentType="text/html; charset=GBK" %>
<%@ page pageEncoding="GBK" %>
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
<title>个人分组列表</title>   
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview_pro.js" type="text/JavaScript"></script>
<style type="text/css">    
body {
	margin-left: 0px;
	margin-top: 0px;  
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>

</head>
<body  text=#eeeeee bgColor=#d8eaf8 align=center  width=100% leftMargin=0 topMargin=0 marginheight="0" 
marginwidth="0" >
 <br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/images/left-01.gif" width="13" height="36"></td>
    <td width="153" background="<%=request.getContextPath()%>/images/left-02.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/images/left-03.gif" width="12" height="36"></td>
  </tr>
  <tr>
    <td width="13" background="<%=request.getContextPath()%>/images/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
<script type="text/javascript">

<%
int IndiRoot = 1;
%>

var inditree=new TreeView('inditree','_parent')
inditree.add(<%=IndiRoot%>,0,' 个人分组','<%=request.getContextPath()%>/servlet/ListGroupServlet','','bodyfrm')					  
<%
int indiIndex = 1;

while(indiGroupIterator.hasNext()){
	AddressGroupVO addressGroupVO = (AddressGroupVO)indiGroupIterator.next();
	indiIndex ++;
%>
inditree.add(<%=indiIndex%>,<%=IndiRoot%>,' <%=addressGroupVO.getGroupname()%>','<%=request.getContextPath()%>/servlet/ListGroupInfoServlet?groupid=<%=addressGroupVO.getId()%>&type=<%=AddressConfig.GROUPTYPE_PRIVATE%>','','bodyfrm')		
<%
}
%>

document.write(inditree);
</script>
</td>
    <td width="12" background="<%=request.getContextPath()%>/images/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left-07.gif" width="13" height="16"></td>
    <td background="<%=request.getContextPath()%>/images/left-08.gif"><img src="<%=request.getContextPath()%>/images/left-08.gif" width="3" height="16"></td>
    <td><img src="<%=request.getContextPath()%>/images/left-09.gif" width="12" height="16"></td>
  </tr>
 <br>
</body>
</html>

<script>
function frameautoheight(){
	parent.document.all("leftfrm").style.height=document.body.scrollHeight+20;
}

</script>
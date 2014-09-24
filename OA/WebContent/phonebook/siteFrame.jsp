<%@ page pageEncoding="GBK" %>
<%@ page contentType="text/html; charset=GBK"%>
<% String basePath = request.getContextPath(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CMS2.0信息统计框架</title>
<script>
N=(document.all)? 0:1;
function _expend(){
	if(N){
	    sub_frmObj = document.getElementById("sub_frm");
		sub_frmObj.cols="170,9,*";
	}else{
		sub_frm.cols="170,9,*";
	}
}
function _close(){
	if(N){
	    sub_frmObj = document.getElementById("sub_frm");
		sub_frmObj.cols="0,9,*";
	}else{
		sub_frm.cols="0,9,*";
	}
}  

nNav=1;
function _funNav(){
		if(nNav==0){
			_expend();
			window.parent.navFrame.imgNav.src="<%=basePath%>/cms/images/lefta.gif";
			window.parent.navFrame.divNav.style.display="block";
			nNav=1;
		}else{
			_close();
			window.parent.navFrame.imgNav.src="<%=basePath%>/cms/images/righta.gif";
			window.parent.navFrame.divNav.style.display="block";
			nNav=0;
		}
}

function _showFrame(){
        right_frmObj = document.getElementById("sub_frm");
        right_frmObj.rows="80,*";       
}

function _closeFrame(){
        right_frmObj = document.getElementById("sub_frm");
        right_frmObj.rows="0,*";       
}


</script>


<%
   String judge = request.getParameter("judge"); 

%>
 <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <%
    if (request.getParameter("siteId")!=null){ %>
    var redirectUrl = document.all.siteFrame.srcSiteListURL;
		redirectUrl += '?';
		redirectUrl += 'siteId=<%=request.getParameter("siteId")%>';
	var forwardUrl = '<%=request.getContextPath()%>/cms/jsp/manage/wait.jsp?nextPage=';
		forwardUrl += redirectUrl;
	window.self.siteFrame.location.href = forwardUrl;	  
    <%}%> 
    <% if(request.getParameter("key")!=null&& request.getParameter("key").equals("1")){%>
     	 var redirectUrl = document.all.siteFrame.srcSite;			
		window.self.siteFrame.location.href = redirectUrl;	 	
     <%}%>
     <%if(request.getParameter("key")!=null && request.getParameter("key").equals("2")){%>
    	 var redirectUrl = document.all.siteFrame.scrArch;		
		window.self.siteFrame.location.href = redirectUrl;	 
	<%}%>
    
</SCRIPT>


</head>
<frameset id="sub_frm" name="sub_frm" rows="0,*" frameborder="NO" border="0" framespacing="0">
     <frame name="selectFrame"    srcURL="<%=basePath%>/cms/jsp/Frames/emptyPage.jsp" src="<%=basePath%>/cms/include/jsp/CMS_Right_Statistics_Site.jsp" srcTime="<%=basePath%>/cms/include/jsp/CMS_Time_Statistics_Site1.jsp" >
      <frame name="siteFrame"  noresize  src="<%=basePath%>/cms/jsp/Frames/emptyPage.jsp"  srcMapURL="<%=basePath%>/cms/jsp/manage/statistcs/DiagramShow.jsp"  srcSiteListURL="<%=basePath%>/cms/statistics/sitestatistics"  srcSite="<%=basePath%>/cms/manage/pagestatistics/CountListServlet?countType=0&isArch=0"  scrArch="<%=basePath%>/cms/manage/pagestatistics/CountListServlet?countType=0&isArch=1">
</frameset>
<noframes>
<body>
您的浏览器不支持框架
</body>
</noframes>
</html>
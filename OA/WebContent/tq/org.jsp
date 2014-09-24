<%@ page contentType="text/html; charset=GBK" %>
<script> 
document.onreadystatechange=function(){ 
if(document.readyState=="complete"){ 
        document.getElementById('loading').style.display='none'; 
    } 
} 
</script> 
<body style="margin:0;padding:0;overflow:hidden" > 
<div id="loading" style="position:absolute;width:100%;height:100%; 
left:0px;top:0px;background-color:#ffffff;filter:alpha 
(opacity=100)">  
<div style="text-align:left;padding-top:20px;font-size:12px;font-weight:bold;color:#606060;"> 
正在加载中....
<br/> 
<image src="js/loading.gif" />
</div> 
</div> 
<iframe src="<%=request.getContextPath()%>/servlet/TqOrgServlet?hwnd=<%=request.getParameter("hwnd")%>" style="height:100%;width:100%;margin:0; 
padding:0;border=0;" frameBorder=0></iframe> 
</body> 
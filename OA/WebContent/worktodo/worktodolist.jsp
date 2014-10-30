<%@ page contentType="text/html; charset=GBK"%>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%
Collection workListCollection = (Collection)request.getAttribute("workList");

Iterator workListGroupIterator = workListCollection.iterator();

%>
<HTML>
	<HEAD>
		<TITLE>待办工作</TITLE>
        <style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background:#ffffff;
}
-->
</style>
<!--<meta http-equiv="Refresh" content="1800;url=">-->
		<LINK href="<%=request.getContextPath()%>/Style/css_grey.css" rel=stylesheet>
	</head>
	<script language="javascript">
	function _dowork(workid,url,navigate,type){
		
	//		//弹出显示
			//window.open("<%=request.getContextPath()%>/servlet/DoWorkServlet?workid="+workid+"&url=" + url,"","width=660,height=240,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=170,top=110");
			window.open("<%=request.getContextPath()%>/servlet/DoWorkServlet?workid="+workid+"&url=" + url,"");
			parent.location.reload();
			window.location.reload();
			
			}
			var n=5;
			var flag1 = false;
			var flag2 = false;
			var flag3 = false;
			var flag4 = false;

				
			var aParams = document.location.search.substr(1).split('&');
			for(var i=0;i<aParams.length;i++){
				var aParam = aParams[i].split('=');
				var sParamName= decodeURIComponent(aParam[0]);
				var sParamValue= decodeURIComponent(aParam[1]);
				if('re_nu1'==sParamName) {flag1 =true; }
				if('re_nu2'==sParamName) {flag2 =true; }
				if('re_nu3'==sParamName) {flag3 =true; }
				if('re_nu4'==sParamName) {flag4 =true; }
				}

			function _refresh(){
				if(!flag1) 	window.location.href = window.location.href+'&re_nu1=1';
				if(!flag2) 	window.location.href = window.location.href+'&re_nu2=2';
				if(!flag3) 	window.location.href = window.location.href+'&re_nu3=3';
				if(!flag4) 	window.location.href = window.location.href+'&re_nu4=4';
			  }

			 setTimeout("_refresh();",1800000);
		
	</script>
	<body>
		<form name="workForm" method="post" action="">
			
			<table width="216" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					
					
					<td valign="top" align="center">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							bgcolor="#B9DAF9">
                            <%
							if(!workListGroupIterator.hasNext()){
							%>
                            <tr><td width="100%" class="message_title" bgcolor="#FFFFFF" height="160">您暂时没有待办信息！</td></tr>	
                            <%
							}else{
							%>
							<tr bgcolor="#FFFFFF">     
                  				<td width="100%"  class="text-01" align="left">
                                <marquee direction="up" height="160" width="100%" scrollamount="1" scrolldelay="1" onMouseOver=stop() onMouseOut=start() style="cursor:hand" align="left">
							<%	
							int index = 0;
							while(workListGroupIterator.hasNext()){
								OfficePendingVO officePendingVO = (OfficePendingVO)workListGroupIterator.next();
								
								CommUtil.getTime(officePendingVO.getOpModify().longValue(),com.icss.oa.config.Config.ALL_TYPE);
								index ++;
								String color = "#F2F9FF";
								if(index % 2 == 1)
									color = "#D8EAF8";
							%>
							<a href="#" onClick="javascript:_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(officePendingVO.getOpUrl())%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>')" style="text-decoration:none" > 
								<font class="message_title"><strong>+[<%=officePendingVO.getOpSource()%>]</strong> 
								<%=officePendingVO.getOpTopic()%></font></a><br>
                                
                            <%
							}
							%>
                            	</marquee>
                                </td>
                  			</tr>
                            <%
							}
							%>
						</table>
				  </td>
									</tr>
				
			</table>
			  </form>

			
</body>

</html>
<script language="javascript">
function frameautoheight(){
	parent.document.all("worktodoframe").style.height=document.body.scrollHeight;
}
frameautoheight();

</script>

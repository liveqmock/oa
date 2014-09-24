<%@ page contentType="text/html; charset=GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>在线交流</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/Style/css_grey.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/include/tq_talk_new.js"></script>
		

	<script type="text/javascript">
		 function changepage(cp){
		 if(!cp){
		  cp=1;
		 }
		  
		 var key=document.all.key.value;
		 
		 var xmlhttp;
		   if (window.ActiveXObject) {
		      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		   }else if(window.XMLHttpRequest)  {
		      xmlhttp=new XMLHttpRequest();
		   }
		   if (xmlhttp) {
		        xmlhttp.onreadystatechange=function () {
		                if(xmlhttp.readyState==4)  {
		             if(xmlhttp.status==200)  {
		                   var yy=unescape(xmlhttp.responseText);
		                
		                   document.getElementById("list").innerHTML=yy;
		                }else {
		                     alert("error");
		                }
		          }
		              } 
		           xmlhttp.open("post","../servlet/OnlineUserListServlet?cp="+cp+"&key="+key);
		           xmlhttp.send(null);
		    }             
		 }
		 
		 
		 function showall(){
		 	document.all.key.value="";
		 	changepage(1);
		 }
		 
 </script>
	</head>
	<body onLoad="changepage(1)">
		<div align=center>
			<div class=message_title>
			<div align="right">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            	<tr>
                	<td width="40%"></td>
                	<td valign="middle" align="center" width="10%">
                    	<a href="/cms/cms/manage/info/attachfile?infoId=115579" target="_blank" class="message_title"><img src="<%=request.getContextPath()%>/images/download.gif" width="23" height="23" border="0"></a>
                    </td>
                	<td nowrap width="20%">
            			<a href="/cms/cms/manage/info/attachfile?infoId=115579" target="_blank" class="message_title">下载新华通</a>&nbsp;&nbsp;
                    </td>
                    <td valign="middle" align="center" width="10%">
                    	<a href="/oabase/help/index.html" target="_blank" class="message_title"><img src="<%=request.getContextPath()%>/images/help.jpg" width="19" height="19" border="0"></a>
                    </td>
                    <td nowrap width="20%">
            			<a href="/oabase/help/index.html" target="_blank" class="message_title">帮助</a>&nbsp;&nbsp;
            		</td>
                </tr>
            </table>
            </div>
			<div id=num > </div>
			<input size="16" type="text" name="key" class="biankuang-blue">
			<input type="submit" value="查询" onClick="changepage(1)">
            <input type="button" value="显示全部" onClick="showall()">
			</div>
            <table height="3" width="100%" border="0" cellpadding="0" cellspacing="0">
            	<tr><td height="3"></td></tr>
            </table>
			<div id=list></div>
		</div>
	
	</body>
</html>
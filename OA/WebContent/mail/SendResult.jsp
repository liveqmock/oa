<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
//response.setDateHeader("Expires", 0);

String failResult = (String)request.getSession().getAttribute("failResult");


if("该邮件可能已经被删除！".equals(failResult))response.sendRedirect("/oabase/servlet/MessageListServletX?search=no&type=system&folder=Inbox");

String sendResult = (String)request.getSession().getAttribute("sendResult");
sendResult = ("".equals(sendResult)||sendResult==null)?"":sendResult.substring(0,sendResult.length()-1);
String overFlowResult = (String)request.getSession().getAttribute("overFlowResult");
overFlowResult = ("".equals(overFlowResult)||overFlowResult==null)?"":overFlowResult.substring(0,overFlowResult.length()-1);
String noPersonResult = (String)request.getSession().getAttribute("noPersonResult");
noPersonResult = ("".equals(noPersonResult)||noPersonResult==null)?"":noPersonResult.substring(0,noPersonResult.length()-1);
String title1 = (String)request.getSession().getAttribute("title1");
String title2 = (String)request.getSession().getAttribute("title2");
String coreStr = (String)request.getSession().getAttribute("coreStr");
if(coreStr == null){
	coreStr = "成功发送到：";
}
%>
<SCRIPT>
 function _onload(){
  <% if(failResult!=null){   %>
       var mail_context = getCookie("mail_context");
       var mail_title = getCookie("mail_title"); 
       //document.getElementById("content").innerHTML=String(document.cookie);
        document.getElementById("content").innerHTML=  String(mail_context);
        document.getElementById("topic").innerHTML=  String(mail_title);
        <%}%>
 }  	 
function setCookie(name,value){
	var cook =document.cookie;		
if("mail_title"==name||"mail_context"==name){
		document.cookie=name+"="+escape(value)+"quqs";}
		else{document.cookie=name+"="+value;}
		
}

function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf(name)>=0){
			var cook1 = unescape(cook.substring(cook.indexOf(name)));
			var value;
			if("mail_title"==name||"mail_context"==name){
			value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf("quqs;")));
			}else{value = unescape(cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";")));}
			return value;
		}else if("xhsstyle"==name){
			return "grey";
			}else
			{return "";}
		
}
</SCRIPT>
<HTML><HEAD><TITLE><%=title1%></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<LINK href="<%=request.getContextPath()%>/Style/css_grey.css" rel=stylesheet>
<script>
function _back(){
   <% if(failResult==null){   %>
    window.location.href = "<%=request.getContextPath()%>/servlet/BoxListServlet";
	<%}
	else {
	request.getSession().setAttribute("failResult",failResult);%>
	window.location.href = "<%=request.getContextPath()%>/servlet/SendFileFileTransferServlet";
	<%}%>
}
</SCRIPT>

</head>
<BODY leftmargin="10" onload="_onload()" >
<div align="center" >
          
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
          	<tr><td colspan="3" class="block_title"><%=title2%></td></tr>
            <tr>

              <td width="100%" bgcolor="#FFFFFF">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                  <tr>
                    <td bgcolor="#FFFFFF">
                    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    <% if(failResult!=null){   %>
                        <tr>
                          <td height="20" class="text-01" bgcolor="#FFFFFF"><div align="center"><%=failResult%>,请返回重发</div></td>
                        </tr>

                        <tr>
                          <td height="80" class="text-01" bgcolor="#FFFFFF">邮件标题:<br><div id="topic" style="word-break:break-all;" align="left"></div></td>
                        </tr>
                        <tr>
                          <td height="2"  bgcolor="#FFFFFF" class="text-01" align="left">-------------------------------</td>
                        </tr>
                         <tr>
                          <td height="80" class="text-01" bgcolor="#FFFFFF" >邮件内容:<br><div id="content" style="word-break:break-all;" align="left"></div></td>
                        </tr>
                        <tr>
                          <td height="2"  bgcolor="#FFFFFF" class="text-01" align="left"></td>
                        </tr>
                   <% }else{       
                   %>
                        <tr>
                            <td height="20" colspan="3"  bgcolor="#FFFFFF"></td>
                        </tr>                   
                        <tr>
                          <td colspan="3" height="2"  bgcolor="#FFFFFF" class="text-01"></td>                          
                        </tr>  
                   <% if(!("".equals(sendResult))&&sendResult!=null){ %>                                 
                        <tr height="30">
                          <td width="20%" class="text-01"  bgcolor="#FFFFFF"><div align="right" valign="top"><b><%=coreStr%></b></div></td>
                          <td width="2" valign="top"  bgcolor="#FFFFFF"></td>
                          <td width="80%" class="text-01"  bgcolor="#FFFFFF"><div valign="top" align="left"><%=sendResult%></div></td>
                        </tr>
                        
                  <%  } 
                      
                      if(!"".equals(overFlowResult)&&overFlowResult!=null){
                  %>
                        <tr height="30">
                          <td width="20%" class="text-01"  bgcolor="#FFFFFF"><div align="right" valign="top"><b>下列用户邮箱已满，只发送提示信息：</b></div></td>
                          <td width="2" valign="top"  bgcolor="#FFFFFF"></td>
                          <td width="80%" class="text-01"  bgcolor="#FFFFFF"><div valign="top" align="left"><%=overFlowResult%></div></td>
                        </tr>
                        
                  <%  } 
                      
                      if(!"".equals(noPersonResult)&&noPersonResult!=null){
                  %>                        
                        <tr height="30">
                          <td width="20%" class="text-01" bgcolor="#FFFFFF"><div align="right" valign="top"><b>下列用户邮箱尚未开通：</b></div></td>
                          <td width="2" valign="top"  bgcolor="#FFFFFF"></td>
                          <td width="80%" class="text-01" bgcolor="#FFFFFF"><div valign="top" align="left"><%=noPersonResult%></div></td>
                        </tr>
                        
                   <% }  
                   } //else
                   %>
                    </table></td>
                  </tr>
              </table></td>

            </tr>
          </table>
          
          <p>
			<img src="<%=request.getContextPath()%>/images/botton-return.gif" style="cursor:hand" onClick="javascript:_back();">
          </p>

</div>
</body>

</html>
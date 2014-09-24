<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

String failResult = (String)request.getAttribute("failResult");
String sendResult = (String)request.getAttribute("sendResult");
String noPersonResult = (String)request.getAttribute("noPersonResult");
noPersonResult = ("".equals(noPersonResult)||noPersonResult==null)?"":noPersonResult.substring(0,noPersonResult.length()-1);
String title1 = (String)request.getAttribute("title1");
String title2 = (String)request.getAttribute("title2");
%>

<HTML><HEAD><TITLE><%=title1%></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script>
function _back(){
    window.location.href = "<%=request.getContextPath()%>/servlet/BoxListServlet";
}
</SCRIPT>

</head>
<BODY leftmargin="10" background="<%=request.getContextPath()%>/images/bg-08.gif">
<div align="center" >
          <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"><%=title2%></td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    <% if(failResult!=null){   %>
                        <tr>
                          <td height="80" class="text-01"><div align="center"><%=failResult%></div></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        </tr>
                   <% }else{       
                   %>
                        <tr>
                            <td height="20" colspan="3"></td>
                        </tr>                   
                        <tr>
                          <td colspan="3" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>                          
                        </tr>  
                   <% if(!("".equals(sendResult))&&sendResult!=null){ %>                                 
                        <tr height="30">
                          <td colspan="3" class="text-01"><div align="center"><b>成功保存到草稿箱</b></div></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        </tr>
                  <%  }else{	%>
                        <tr height="30">
                          <td colspan="3" width="20%" class="text-01"><div align="center"><b>没有可发送的人</b></div></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        </tr>
                  <%
                      } 
                      
                      if(!"".equals(noPersonResult)&&noPersonResult!=null){
                  %>                        
                        <tr height="30">
                          <td class="text-01" width="20%"><div align="right" valign="top"><b>下列用户邮箱尚未开通：</b></div></td>
                          <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="../userweb/images/bg-18.gif" width="2" height="2"></td>
                          <td class="text-01" width="80%"><div valign="top" align="left"><%=noPersonResult%></div></td>
                        </tr>
                        <tr>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                          <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        </tr>
                   <% }  
                   } //else
                   %>
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          <p>
			<img src="<%=request.getContextPath()%>/images/botton-return.gif" style="cursor:hand" onclick="javascript:_back();">
          </p>

</div>
</body>

</html>
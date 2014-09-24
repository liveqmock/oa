<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupinfoVO"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.oa.address.helper.AddressViewHelper"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

String sendresult = (String)request.getAttribute("sendresult");
String title1 = (String)request.getAttribute("title1");
String title2 = (String)request.getAttribute("title2");
Person  person = (Person)request.getAttribute("person");
String org =(String)request.getAttribute("org");
%>

<script>
function _OK(){
if(document.sendForm.kkk.value==""){alert("请确认输入序号!");}
else{
	if(isDigital(document.sendForm.kkk.value)){
		sendForm.action="<%=request.getContextPath()%>/servlet/UpdatePersonIdServlet";
		sendForm.submit();
		window.opener.location.reload();
		window.top.close();}
		else{alert("请确认输入的准确性!");
		}}
}

//IsDigital函数判断一个字符串是否由数字(int or long)组成
function isDigital(str)
{
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
       return false;
    }
  }
  return true;
}
</SCRIPT>

<HTML><HEAD><TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script>

</SCRIPT>

</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/grid.gif topMargin=10>
<form name="sendForm" method="post" action="">
<% System.out.println("putId in personId.jsp "+request.getAttribute("putId"));%>
<input type="hidden" name="putid" value="<%= request.getAttribute("putId")%>" >
<input type="hidden" name="kkk2" value="<%= request.getAttribute("personID")%>" >
<div align="center" >
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">与会人员排序</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>

          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="100%" class="text-01"><div align="center"></div></td>
                          
                        </tr>
                        <tr>
                          <td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="kkk" value="<%= request.getAttribute("id")%>" size="2">&nbsp;&nbsp;&nbsp;<img onClick="javascript:_OK()" src="<%=request.getContextPath()%>/images/botton-ok.gif"style="cursor:hand"></td>
           
                        </tr>
                        
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          

</div>
</form>
</body>

</html>
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo" %>
<%@ page import="com.icss.resourceone.sdk.framework.Context" %>
<%@ page import="com.icss.resourceone.sdk.framework.EntityException" %>
<script language="javascript">
function showDate()
{
	var week; 
	var date = new Date();
	if(date.getDay()==0)           week="星期日"
	if(date.getDay()==1)           week="星期一"
	if(date.getDay()==2)           week="星期二" 
	if(date.getDay()==3)           week="星期三"
	if(date.getDay()==4)           week="星期四"
	if(date.getDay()==5)           week="星期五"
	if(date.getDay()==6)           week="星期六"
	var m=date.getMonth()+1;//获得月份 
	var d=date.getDate();//获取日 
	var y=date.getFullYear(); //获取年(四位) 
	var fullDate = y + "年" +　m + "月" + d + "日 " + week;
	//alert(fullDate);
	//alert(document.showdate.innerHTML);
	//document.showdate.innerHTML = fullDate;
	document.getElementById("showdate").innerHTML = fullDate;
}
</script>
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/include/js/jquery.js"></SCRIPT>
<%
	com.icss.resourceone.common.logininfo.UserInfo user = (com.icss.resourceone.common.logininfo.UserInfo)session.getAttribute(com.icss.j2ee.util.Globals.J2EE_USER_NAME);
	String username = "";
    if(user==null){
      response.sendRedirect("/resourceone/common/timeout.jsp");
    }else{
		username = user.getCnName();
	}

	Context ctx = Context.getInstance();
			UserInfo users = ctx.getCurrentLoginInfo();


		
	String cnname = new String(users.getCnName().getBytes("GBK"),"ISO-8859-1");
%>
<style type="text/css">
<!--
.TOPSTYLE {
	color: #FFFFFF;
	font-size: 12px;
}
-->
</style>
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/include/cookie.js"></SCRIPT>

<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="536" class="top_logo" height="84"></td>
    <td class="top_back" height="84" width="137">&nbsp;</td>
    <td width="310" class="top_back" height="84">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
              <td width="40" height="30">&nbsp;</td>
        	  <td width="32" align="right" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><img src="/cms/images/top/top_home.png" border="0"/></td>
        	  <td width="34" align="center" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><span class="TOPSTYLE">首页</span></a></td>
        	  <td width="32" align="right" valign="middle"><a href="/oabase/help/index.html" target="_blank" style="text-decoration:none" class="TOPSTYLE"><img src="/cms/images/top/top_help.png" border="0" /></a></td>
        	  <td width="34" align="center" valign="middle" class="TOPSTYLE"><a href="/oabase/help/index.html" target="_blank" style="text-decoration:none" class="TOPSTYLE"><span class="TOPSTYLE">帮助</span></a></td>
        	  
              <td width="32" align="right" valign="middle"><a href="/oabase/user/ModifyPassword.jsp" style="text-decoration:none" target="_blank"><img src="/cms/images/top/top_alter.png" border="0"/></td>
        	  <td width="60" align="center" valign="middle"><a href="/oabase/user/ModifyPassword.jsp" style="text-decoration:none" target="_blank"><span class="TOPSTYLE">修改密码</span></a></td>
              <td width="32" align="right" valign="middle"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none"><img src="/cms/images/top/top_out.png" border="0" /></a></td>
        	  <td width="36" align="center" valign="middle" class="TOPSTYLE"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none" class="TOPSTYLE"><span class="TOPSTYLE">退出</span></a></td>
        	  </tr>
            <tr><td colspan="9">&nbsp;</td></tr>
            <tr><td colspan="9" height="30">&nbsp;</td></tr>
        </table>
    </td>
    </tr>
    </table>
    </td>
    <td width="10" >&nbsp;</td>
  </tr>
</table>

<table width="1003" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr>
    	<td width="10"></td>
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="top_back">
            	<tr>
                	<td width="134" class="top_left_buttom" height="29"><span class="message_zhuanti" id="showdate"></span></td>
                  <td width="60%" class="top_back_buttom">
                  
                  <script language="javascript">
					function _search(){
						document.forms[0].action="/cms/cms/SearchServlet"
						document.forms[0].submit();
					}
					</script>
               	  <table width="98%">
                        <tr>
                            <td width="34%" height="20" align="left" class="message_zhuanti">&nbsp;欢迎<span style="font-size:12;color:#000000" id="personInfo"></span></td>
                          <td width="42%" align="right" class="content"><input name="keyword" type="text" class="biankuang" size="26" value="请输入检索关键字" onmousedown="_clearinfo();"/>
                          <input type="hidden" name="forwardUrl" value="/search.jsp" /></td>
                          <script language="javascript">
						  	function _clearinfo(){
								if(document.forms[0].keyword.value=="请输入检索关键字"){
									document.forms[0].keyword.value="";
								}
							}
						  </script>
                          <td width="9%" class="message_zhuanti" style="cursor:hand"><span onclick="_search();" style="cursor:hand">&gt;&gt;检索</span></td>
                          <td width="15%" class="content"><a href="/cms/advanceSearch.jsp" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; 高级检索</a></td>
                      </tr>
                    </table>
                    
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="点击选择页面色彩！" bgcolor="#FFFFFF">
                      <tr>
                        <td width="30"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="red" style="cursor:hand;" onClick="changeStyle('red')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#d0e9cb" style="cursor:hand;" onClick="changeStyle('lake')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ffd4ad" onClick="changeStyle('orange')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ddc99d" onClick="changeStyle('brown')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#afd5ae" onClick="changeStyle('green')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#CCCCCC" onClick="changeStyle('grey')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
          </table>
        </td>
        <td width="10"></td>
    </tr>
</table>


<script language="javascript">
//showDate();
$(function(){
	  $("#personInfo").load("/cms/cms/org/getpersoninfo?uuid=<%=users.getPersonUuid()%>&type=1"); 
});

</script>
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%
	String btime = com.icss.oa.util.TimeUtil.getCurrentDate("yyyyÄêMMÔÂddÈÕ")+" "+cms.base.TimeUtil.getCurrentWeek();
	com.icss.resourceone.common.logininfo.UserInfo user = (com.icss.resourceone.common.logininfo.UserInfo)session.getAttribute(com.icss.j2ee.util.Globals.J2EE_USER_NAME);
	String username = "";
    if(user==null){
      response.sendRedirect("/resourceone/common/timeout.jsp");
    }else{
		username = user.getCnName();
	}

%>
<style type="text/css">
<!--
.TOPSTYLE {
	color: #FFFFFF;
	font-size: 12px;
}
-->
</style>
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/include/cookiemail.js"></SCRIPT>

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
        	  <td width="34" align="center" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><span class="TOPSTYLE">Ê×Ò³</span></a></td>
        	  <td width="32" align="right" valign="middle"><img src="/cms/images/top/top_help.png" /></td>
        	  <td width="34" align="center" valign="middle" class="TOPSTYLE">°ïÖú</td>
        	  
              <td width="32" align="right" valign="middle"><a href="/oabase/user/ModifyPassword.jsp" style="text-decoration:none" target="_blank"><img src="/cms/images/top/top_alter.png" border="0"/></td>
        	  <td width="60" align="center" valign="middle"><a href="/oabase/user/ModifyPassword.jsp" style="text-decoration:none" target="_blank"><span class="TOPSTYLE">ÐÞ¸ÄÃÜÂë</span></a></td>
              <td width="32" align="right" valign="middle"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none"><img src="/cms/images/top/top_out.png" border="0" /></a></td>
        	  <td width="36" align="center" valign="middle" class="TOPSTYLE"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none" class="TOPSTYLE">ÍË³ö</a></td>
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
                	<td width="120" class="top_left_buttom" height="29"><span class="message_zhuanti"><%=btime%></span></td>
                  <td width="60%" class="top_back_buttom">
                  
                  <script language="javascript">
					function _search(){
						document.forms[0].action="/cms/cms/SearchServlet"
						document.forms[0].submit();
					}
					</script>
               	  <table width="98%">
                        <tr>
                            <td width="36%" height="20" align="left" class="message_zhuanti">»¶Ó­<span style="font-size:12;color:#000000"> <%=username%> </span></td>
                          <td width="42%" align="right" class="content"><input name="keyword" type="text" class="biankuang" size="26" value="ÇëÊäÈë¼ìË÷¹Ø¼ü×Ö" onmousedown="_clearinfo();"/><input type="hidden" name="forwardUrl" value="/search.jsp" /></td>
                          <script language="javascript">
						  	function _clearinfo(){
								if(document.forms[0].keyword.value=="ÇëÊäÈë¼ìË÷¹Ø¼ü×Ö"){
									document.forms[0].keyword.value="";
								}
							}
						  </script>
                          <td width="9%" class="message_zhuanti" style="cursor:hand"><span onclick="_search();" style="cursor:hand">&gt;&gt;¼ìË÷</span></td>
                          <td width="15%" class="content"><a href="/cms/advanceSearch.jsp" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; ¸ß¼¶¼ìË÷</a></td>
                      </tr>
                    </table>
                    
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="µã»÷Ñ¡ÔñÒ³ÃæÉ«²Ê£¡" bgcolor="#FFFFFF">
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


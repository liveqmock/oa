<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo" %>
<%@ page import="com.icss.resourceone.sdk.framework.Context" %>
<%@ page import="com.icss.resourceone.sdk.framework.EntityException" %>
<script language="javascript">
function showDate()
{
	var week; 
	var date = new Date();
	if(date.getDay()==0)           week="������"
	if(date.getDay()==1)           week="����һ"
	if(date.getDay()==2)           week="���ڶ�" 
	if(date.getDay()==3)           week="������"
	if(date.getDay()==4)           week="������"
	if(date.getDay()==5)           week="������"
	if(date.getDay()==6)           week="������"
	var m=date.getMonth()+1;//����·� 
	var d=date.getDate();//��ȡ�� 
	var y=date.getFullYear(); //��ȡ��(��λ) 
	var fullDate = y + "��" +��m + "��" + d + "�� " + week;
	//alert(fullDate);
	//alert(document.showdate.innerHTML);
	//document.showdate.innerHTML = fullDate;
	document.getElementById("showdate").innerHTML = fullDate;
}
function _logout(){
	window.opener=null;
	window.open("","_self");
	window.close();
	//if(confirm('���Ƿ�Ҫ�˳�ϵͳ?')){
	//	window.location.href="/resourceone/common/Logout.jsp";
	//	if(opener.parent) opener.parent.window.close();
	//	opener.window.close();
	//}
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
.SYSSTYLE {
	color: #09065E;
	font-weight: normal;
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
    	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="84">
        	<tr>
        		<!--
        	  <td width="32" height="30">&nbsp;</td>
        	  <td width="60" height="30">&nbsp;</td>
        	  -->
              <td width="40" height="30">&nbsp;</td>
        	  <td width="32" align="right" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><img src="/cms/images/top/top_home.png" border="0"/></td>
        	  <td width="34" align="center" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><span class="TOPSTYLE">��ҳ</span></a></td>
        	  <td width="32" align="right" valign="middle"><a href="/oabase/help/index.html" target="_blank" style="text-decoration:none" class="TOPSTYLE"><img src="/cms/images/top/top_help.png" border="0" /></a></td>
        	  <td width="34" align="center" valign="middle" class="TOPSTYLE"><a href="/oabase/help/index.html" target="_blank" style="text-decoration:none" class="TOPSTYLE"><span class="TOPSTYLE">����</span></a></td>
              
              
              <td width="32" align="right" valign="middle">
              	<a href="/oabase/user/ModifyPassword.jsp" style="text-decoration:none" target="_blank"><img src="/cms/images/top/top_alter.png" border="0"/>
              </td>
        	  <td width="60" align="center" valign="middle">
        	  	<a href="/oabase/user/ModifyPassword.jsp" style="text-decoration:none" target="_blank"><span class="TOPSTYLE">�޸�����</span></a>
        	  </td>
        	   
              <td width="32" align="right" valign="middle">  <span onclick="_logout();" style="cursor:hand" class="TOPSTYLE"><img src="/cms/images/top/top_out.png" border="0" /></span></td>
	      <td width="60" align="center" valign="middle" class="TOPSTYLE"><span onclick="_logout();" style="cursor:hand" class="TOPSTYLE">�ر�ҳ��</span></td>
        	  </tr>
            <tr><td colspan="9">&nbsp;</td></tr>
            <tr>
            	<td colspan="9" height="25">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                    	<td>&nbsp;</td>
                        <td width="110" height="25" align="right" background="/images/system_bg.gif" style="background-repeat:no-repeat"><a href="/cms/cms/website/RSJ/znbm/index.jsp?siteId=13" style="text-decoration:none"><span class="SYSSTYLE">���¹���ϵͳ&nbsp;</span></a></td>
                        <td width="5"></td>
                    </tr>
                </table>
                </td>
            </tr>
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
               	  <table width="100%" border="0" cellpadding="0" cellspacing="0" height="29">
                        <tr>
                           <td width="35%" height="29" align="left" valign="middle" class="message_zhuanti">��ӭ<span style="font-size:12;color:#000000" id="personInfo"></span></td>
                          <td width="35%" align="center" class="content" valign="middle"><span class="message_zhuanti">��Ϣ����:</span>
													<input name="keyword" type="text" id="keyword" class="biankuang" size="16" value="����������ؼ���" style="color:#888;" title="������ж���ؼ��֣������ո�ָ" onfocus="if(this.value!='����������ؼ���'){this.style.color='#000'}else{this.value='';this.style.color='#888'}" 
													onblur="if(this.value==''){this.value='����������ؼ���';this.style.color='#888'}" 
														onkeydown="this.style.color='#000'" onkeypress="_tosearch();"/>
                          <input type="hidden" name="forwardUrl" value="/search.jsp" />                          </td>
                          <td width="25%" class="message_zhuanti" style="cursor:hand" align="left">
						  						<span onclick="_search();" style="cursor:hand">&nbsp;&gt;&gt;���� </span>
						  						<a href="/cms/advanceSearch.jsp" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt;�߼�����</a></td>
						  						<td width="5%" class='message_zhuanti' align='left'></td>
                      </tr>
                    </table>
                    
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="���ѡ��ҳ��ɫ�ʣ�" bgcolor="#FFFFFF">
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


					function _search(){
						if(document.forms[0].keyword.value==""){
							alert('������ؼ���');
							return false;
						}
						document.forms[0].action="/cms/cms/SearchServlet"
						document.forms[0].submit();
					}
					function _tosearch(){
						
						if(event.keyCode==13){
							if(document.forms[0].keyword.value==""){
							alert('������ؼ���');
							return false;
							}
							document.forms[0].action="/cms/cms/SearchServlet"
							document.forms[0].submit();
						}
					}
</script>
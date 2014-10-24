<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
 String channelId = request.getParameter("channelId");
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
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/cms/include/js/jquery.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function changeStyle(id){//切换样式
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//切换样式时设置COOKIE
	var cookies = document.cookie;
	var setcookies = "";
	var lastcookies = "";
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	
	var deltime = new Date();
	daltime = exp.setTime (exp.getTime() - 1);    

	while(cookies.indexOf(";")>=0){
		var tempcookie = cookies.substring(0,cookies.indexOf(";"));
		cookies = cookies.substring(cookies.indexOf(";")+1);
		
		if(tempcookie.indexOf(name)>=0){
			//删除原来的COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//设置新的样式
	document.cookie = name+"="+value+";expires="+exp.toGMTString()+";path=/;domain=10.102.1.61";
}
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

function getCookie(name){
	var cook =document.cookie;
	//alert(cook);
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(";"));
		var value = "grey";
		if(cook1.indexOf(";")>0){ 
			value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";"));
		}else{
			value = cook1.substring(cook1.indexOf("=")+1);
		}
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+style+".css";
}
function _logout(){
	if(confirm('您是否要退出系统?')){
	window.location.href="/resourceone/common/Logout.jsp";
	//	if(opener.parent) opener.parent.window.close();
	//	opener.window.close();
	}

}
initstyle();
</SCRIPT>

<table width="988" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	
    <td>
    <table width="983" border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="536" class="top_logo" height="84"></td>
    <td class="top_back" height="84" width="115">&nbsp;</td>
    <td width="310" class="top_back" height="84">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="84">
        	<tr>
              <td width="40" height="30">&nbsp;</td>
        	  <td width="32" align="right" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><img src="/cms/images/top/top_home.png" border="0"/></td>
        	  <td width="34" align="center" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><span class="TOPSTYLE">首页</span></a></td>
        	  <td width="32" align="right" valign="middle"><a href="/oabase/help/index.html" style="text-decoration:none" target="_blank"><img src="/cms/images/top/top_help.png" border="0" /></a></td>
        	  <td width="34" align="center" valign="middle" class="TOPSTYLE"><a href="/oabase/help/index.html" style="text-decoration:none" class="TOPSTYLE" target="_blank"><span class="TOPSTYLE">帮助</span></a></td>
        	  
              <td width="32" align="right" valign="middle"><span onclick="window.open('/oabase/user/ModifyPassword.jsp','修改密码','width=640,height=200,toolbar=no,menubar=no,resizable=no,resizable=no,location=no,status=no')" style="text-decoration:none;cursor:hand;" target="_blank"><img src="/cms/images/top/top_alter.png" border="0"/></span></td>
        	  <td width="60" align="center" valign="middle"><span onclick="window.open('/oabase/user/ModifyPassword.jsp','修改密码','width=640,height=200,toolbar=no,menubar=no,resizable=no,resizable=no,location=no,status=no')" style="text-decoration:none;cursor:hand" target="_blank"><span class="TOPSTYLE">修改密码</span></span></td>
		  <td width="32" align="right" valign="middle">
			  <span onclick="_logout();" style="cursor:hand" class="TOPSTYLE"><img src="/cms/images/top/top_out.png" border="0" /></span></td>
	      <td width="60" align="center" valign="middle" class="TOPSTYLE"><span onclick="_logout();" style="cursor:hand" class="TOPSTYLE">退出系统</span></td>
        	  </tr>
            <tr><td colspan="9">&nbsp;</td></tr>
            <tr>
            	<td colspan="9" height="25">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                    	<td>&nbsp;</td>
                        <td width="110" height="25" align="right" background="/images/system_bg.gif" style="background-repeat:no-repeat"><a href="/cms/cms/website/RSJ/znbm/index.jsp?siteId=13" style="text-decoration:none"><span class="SYSSTYLE">人事管理系统&nbsp;</span></a></td>
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
  
  </tr>
</table>

<table width="988" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr>
    	
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="top_back">
            	<tr>
                	<td width="134" height="29" valign="middle" class="top_left_buttom"><span class="message_zhuanti" id="showdate"></span></td>
                  <td width="60%" class="top_back_buttom">
                  
                  <script language="javascript">

					function _search(){
						if(document.forms[0].keyword.value==""){
							alert('请输入关键字');
							return false;
						}
						document.forms[0].action="<%=request.getContextPath()%>/cms/SearchServlet"
						document.forms[0].submit();
					}
					function _tosearch(){
						
						if(event.keyCode==13){
							if(document.forms[0].keyword.value==""){
							alert('请输入关键字');
							return false;
							}
							document.forms[0].action="<%=request.getContextPath()%>/cms/SearchServlet"
							document.forms[0].submit();
						}
					}
					</script>
               	  <table width="100%" border="0" cellpadding="0" cellspacing="0" height="29">
                        <tr>
                            <td width="35%" height="29" align="left" valign="middle" class="message_zhuanti">欢迎<span style="font-size:12;color:#000000" id="personInfo"></span></td>
                          <td width="33%" align="center" class="content" valign="middle"><span class="message_zhuanti">信息检索:</span>
							<input name="keyword" type="text" id="keyword" class="biankuang" size="16" value="请输入检索关键字" style="color:#888;" title="如果您有多个关键字，请键入空格分割。" onfocus="if(this.value!='请输入检索关键字'){this.style.color='#000'}else{this.value='';this.style.color='#888'}" 
							onblur="if(this.value==''){this.value='请输入检索关键字';this.style.color='#888'}" 
							onkeydown="this.style.color='#000'" onkeypress="_tosearch();"/>
                          <input type="hidden" name="forwardUrl" value="/search.jsp" />                          </td>
                          <td width="22%" class="message_zhuanti" style="cursor:hand" align="left">
						  <span onclick="_search();" style="cursor:hand">&nbsp;&gt;&gt;检索 </span>
						  	<a href="/cms/advanceSearch.jsp" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt;高级检索</a></td>
						  <td width="11%" class='message_zhuanti' align='left'><%if(channelId!=null){ %><input type="checkbox" name="channelId" value="<%=channelId%>" checked/>本栏目<%}%></td>
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
        
    </tr>
</table>


<script language="javascript">
//showDate();
$(function(){
	  $("#personInfo").load("<%=request.getContextPath()%>/cms/org/getpersoninfo"); 
});

</script>

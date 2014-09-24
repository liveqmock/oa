<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新华社办公信息化系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="../../Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<script language="javascript">
function changeStyle(id){//切换样式
	//alert("dddd");
	document.getElementById("homepagestyle").href = "../../Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	document.cookie=name+"="+value+",";
}
function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(",")); 
		var value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(","));
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "../../Style/css_"+style+".css";
}
initstyle();
</script>
</head>

<body>
<form name="frm">
<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="537" class="top_logo" height="85"></td>
    <td class="top_back" height="85" width="121">&nbsp;</td>
    <td width="325" class="top_back">
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
        	  <td class="top_function" width="390" height="31"></td>
        	</tr>
            <tr>
              <td class="top_left" width="390" height="54"></td>
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
                	<td width="134" class="top_left_buttom" height="29"></td>
                  <td width="60%" class="top_back_buttom">
               	  <table width="98%">
                        <tr>
                            <td width="34%" height="20" class="message_zhuanti">2008年3月3日 星期一</td>
                          <td width="42%" align="right" class="content"><input name="searchfield2" type="text" class="biankuang" size="26" value="请输入检索关键字"/></td>
                          <td width="9%" class="message_zhuanti" style="cursor:hand">&gt;&gt;检索</td>
                          <td width="15%" class="content"><a href="searchList.html" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; 高级检索</a></td>
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
<!--主要内容去区-->
<table width="1003" border="0" cellspacing="0" cellpadding="0">

    <tr>
    	<td colspan="6" height="11"><img src="../../images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    <tr>
      <td height="30">&nbsp;</td>
      <td colspan="3" rowspan="2" valign="top">
        <TABLE width="180" height="57" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
            <TR>
              <TD width="90" align="right" bgcolor="#FFFFFF">
              <table width="100%" cellspacing="5">
                <tr><td width="36" align="right" style="cursor:hand;">
              <img src="../../images/mail_receive.JPG" width="26" height="26" /></TD>
              <TD width="45" align="left" class="message_title_bold" style="cursor:hand;"><a href="index.html" class="message_title_bold" style="text-decoration:none">未读<br />
                邮件</a></TD>
              </tr>
              </table>
              <TD width="90" align="right" bgcolor="#FFFFFF"><table width="100%" cellspacing="5">
                <tr>
                  <td width="36" align="right" style="cursor:hand"><a href="SendMail1.html"><img src="../../images/mail_send.JPG" width="26" height="26" border="0" /></a></td>
                  <td width="45" align="left" class="blue-12" style="cursor:hand;"><a href="SendMail1.html" class="message_title_bold" style="text-decoration:none">写信</a></td>
                </tr>
              </table></TD>
            </TR>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="../../images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        <TABLE width="180" height="30" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
       	  <tr>
            <td bgcolor="#FFFFFF">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title">搜索文件夹</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<select name="select" style="width:90px;">
                    <option>全部</option>
                    <option>收件箱</option>
                    <option>发件箱</option>
                    <option>草稿箱</option>
                    <option>垃圾箱</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title">是否已读</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<select name="select" style="width:90px;">
                    <option>全部</option>
                    <option>未读邮件</option>
                    <option>已读邮件</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">接收时间从</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="../../images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">至</td>
                  <td width="90" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;">&nbsp;<input name="textfield222" type="text" class="biankuang-blue" value="2008-12-01" size="10" /></td>
                  <td width="16" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><img src="../../images/calendar_view_day.gif" width="16" height="16" /></td>
                </tr>
            </table>            </td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"  height="30">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="131" align="right" bgcolor="#FFFFFF"><input name="textfield2222" type="text" class="biankuang-blue" size="15" value="输入关键字"/></td>
                  <td width="47" align="center" nowrap="nowrap" bgcolor="#E0EDF8" class="message_title_boldold" style="cursor:hand;">搜索</td>
                </tr>
            </table>            </td>
          </tr>
        </TABLE>
        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="../../images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        <table width="180" border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
          <tr>
            <td width="196"><table width="100%" height="170" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="20" align="left"  bgcolor="#E0EDF8" class="block_title" colspan="3"> 文件夹</td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td width="55" align="right" bgcolor="#F8FCFF"><img src="../../images/mail_inbox.JPG" width="15" height="16" />&nbsp;</td>
                  <td width="134" bgcolor="#FFFFFF" class="message_title"><a href="index.html" style="text-decoration:none" class="message_title">收件箱</a>&nbsp;&nbsp;<span class="red2-12-b">(29)</span></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="../../images/mail_caogao.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="index.html" class="message_title" style="text-decoration:none">草稿箱</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="../../images/mail_outbox.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="index.html" style="text-decoration:none" class="message_title">发件箱</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="../../images/mail_laji.JPG" width="16" height="19" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="index.html" style="text-decoration:none" class="message_title">垃圾箱</a>&nbsp;&nbsp;<span class="grap2-12">[清空]</span></td>
                </tr>
                <tr>
                  <td height="25" colspan="3" bgcolor="#E0EDF8" class="block_title">自定义文件夹&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="FloderManage.html" style="text-decoration:none" class="message_title_boldold">[管理]</a></td>
                </tr>
                <tr>
                  <td bgcolor="#F8FCFF" width="8" height="25">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="../../images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="index.html" style="text-decoration:none" class="message_title">自定义</a></td>
                </tr>
            </table>
            
            </td>
          </tr>
      </table></td>
      <td headers="11">&nbsp;</td>
      <td rowspan="2" valign="top"><table width="789" border="0" align="center" cellpadding="2" cellspacing="1" class="table_bgcolor">
          <tr>
            <td height="44" colspan="2" bgcolor="#FFFFFF">
            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                   	  <td><img src="../../images/mail_replybutton.JPG" width="59" height="25" />&nbsp;<img src="../../images/mail_resendbutton.JPG" width="59" height="25" />&nbsp;<img src="../../images/mail_deletebutton.JPG" width="59" height="25" />&nbsp;<img src="../../images/mail_returnbutton.JPG" width="59" height="25" /></td>
                      <td width="5%">&nbsp;</td>
                        <td width="11%">&nbsp;</td>
                	</tr>
                </table>
                </td>
          </tr>
          <tr>
          	<td colspan="2" bgcolor="#FFFFFF">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
   	      <tr>
                  <td width="12%" height="26" align="right" bgcolor="#FFFFFF" class="message_title">发件人：</td>
                    <td colspan="3" bgcolor="#FFFFFF">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                          <td width="107" class="message_title">总编室考核协调组</td>
                          <td width="37" align="right" class="blue-12">&nbsp;<img src="../../images/mail_private.JPG" width="17" height="17" />&nbsp;</td>
                          <td width="89" class="message_title">加入个人分组</td>
                          <td width="34" align="right" class="blue-12"><img src="../../images/mail_personinfo.JPG" width="17" height="17" />&nbsp;</td>
                          <td width="250" class="message_title">发信人详细信息</td>
                          <td width="35" class="blue-12">&nbsp;</td>
                          <td width="138" class="blue-12">&nbsp;</td>
                      </tr>
                  </table></td>
                </tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                    <td width="3%" bgcolor="#FFFFFF" class="message_title"><img src="../../images/mail_show.jpg" /></td>
                    <td width="77%" bgcolor="#FFFFFF" class="message_title">显示收件人</td>
                    <td width="8%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">文件标题：</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title_bold">驻外考核</td>
                  </tr>
                  
                  <tr>
                    <td height="26" align="right" bgcolor="#FFFFFF" class="message_title">发件日期：</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title">2008-01-21 16:49</td>
                  </tr>
                 </table>
                 
              <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr><td height="1" bgcolor="#B9DAF9" width="100%"></td></tr>
              </table>
                 <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
                 	<tr>
                 	  <td>&nbsp;</td>
                 	  <td class="blue-12">&nbsp;</td>
               	  </tr>
                 	<tr>
                 	  <td width="5%">&nbsp;</td>
               	    <td width="95%" class="blue-12">附件为驻外分社2007年度考核数据。</td>
                 	</tr>
                    <tr>
                      <td></td>
                    <td height="15"></td></tr>
                    <tr>
                      <td>&nbsp;</td>
                    	<td class="message_title">附件:</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    	<td><span class="table_title"><strong>点击下载 2007年度考核.rar(0.11M)</strong></span> <span class="message_title_bold">&nbsp;&nbsp;添加到网络文件夹</span></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    	<td><span class="table_title"><strong>点击下载 非洲.rar(0.08M)</strong></span> <span class="message_title_bold">&nbsp;&nbsp;添加到网络文件夹</span></td>
                    </tr>
                 </table>
            </td>
          </tr>
          
          <tr>
            <td height="48" colspan="2" align="left" valign="middle" bgcolor="#FFFFFF" class="message_title"><img src="../../images/mail_replybutton.JPG" width="59" height="25" />&nbsp;<img src="../../images/mail_resendbutton.JPG" width="59" height="25" />&nbsp;<img src="../../images/mail_deletebutton.JPG" width="59" height="25" />&nbsp;<img src="../../images/mail_returnbutton.JPG" width="59" height="25" /></td>
          </tr>
          
      </table></td>
    </tr>
    <tr>
    	<td width="11">&nbsp;</td>
        <td width="14" headers="11"><img src="../../images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    
    
    <tr>
    	<td colspan="6" height="11"><img src="../../images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
	  
	  
<table width="1013" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="1013" height="54"><div align="center" class="foot_message">多媒体数据库系统│多媒体待编稿库系统│eNews系统│总社新闻编辑系统│国内分社多媒体编辑系统│报刊编辑系统│音频供稿系统│稿件采用查询系统│信息部多媒体采编系统<br />
    全球卫星供稿系统│图书/数字图书系统│通信供稿系统│因特网供稿系统│图片/图表编辑系统│营销管理系统│防病毒管理系统</div></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 版权所有　　<br />
      制作单位：新华社技术局　（浏览本网主页，建议将电脑显示屏的分辨率调为1024*768）<br />
    </div></td>
  </tr>
</table>
</form>
</body>
</html>

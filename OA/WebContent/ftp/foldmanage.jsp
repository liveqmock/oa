<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>

<title>�ļ����Ϲ���</title>
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
<link href="<%=path%>/Style/css_ftp.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE2 {
	font-family: "����";
	font-size: 36px;
	color: #FFFFFF;
	font-weight: bold;
	font-style: italic;
}
.STYLE6 {font-size: 12px}
.STYLE7 {
	color: #FFFFFF
}
.STYLE9 {font-size: 12px; color: #999999; }
.STYLE12 {
	font-size: 12px;
	color: #FF9900;
}
.STYLE13 {font-size: 14px}
-->
</style>
</head>

<script  language="javascript">
function changeStyle(id){//�л���ʽ
	//alert("dddd");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
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
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
}

//initstyle();
</script>

<body>
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
              <td width="390" height="54" align="right" class="top_left STYLE2">&nbsp;&nbsp;</td>
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
                            <td width="34%" height="20" class="message_zhuanti">2008��3��3�� ����һ</td>
                          <td width="42%" align="right" class="content"><input name="searchfield2" type="text" class="biankuang" size="26" value="����������ؼ���"/></td>
                          <td width="9%" class="message_zhuanti" style="cursor:hand">&gt;&gt;����</td>
                          <td width="15%" class="content"><a href="searchList.html" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; �߼�����</a></td>
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

<table border="0" cellpadding="0" cellspacing="0" width="1003">
	<tr><td height="5"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="1003" align="center">
  <tr>
  	<td width="10" height="10"></td>
    <td width="86" height="30" align="center" valign="bottom" background="<%=path%>/images/ftp_title.jpg" class="table_title" style="background-repeat:no-repeat">
    ����Ŀ¼    </td>
    <td width="374" >
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td width="10">&nbsp;</td>
            	<td align="center" valign="middle"><img src="<%=path%>/images/ftp_back01.jpg" width="23" height="23" id="local_last" onClick="javascript:laststep()" style="cursor:hand"></td>
           	  <td align="center" valign="middle" nowrap class="foot_message">&nbsp;����&nbsp;&nbsp;</td>
              <td align="center" valign="middle"><img src="<%=path%>/images/ftp_next01.jpg" width="23" height="23" id="local_next" onClick="javascript:nextstep()" style="cursor:hand"></td>
           	  <td align="center" valign="middle" nowrap class="foot_message" >&nbsp;ǰ��&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_up.jpg" width="23" height="23" id="local_up" onClick="javascript:upfolder()" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;����&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_home.jpg" width="23" height="23" onClick="javascript:rootpath()" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;��Ŀ¼&nbsp;&nbsp;</td>
        	</tr>
        </table>    </td>
    
    
    <td width="10" height="10"></td>
  </tr>
  <tr>
  	<td width="10" height="10"></td>
    <td width="460" height="10" colspan="2">


    <div style="width:460px; height:400px; scroll:auto; border: groove; border-color: #9999FF; border-width: 1;" valign="top" > 
    <table border="0" cellpadding="0" cellspacing="0" width="100%" valign="top" >
    	<tr>
        	<td height="20"><img src="<%=path%>/images/ftp_addr.jpg" width="47" height="20"></td>
            <td height="20"><input type="text" size="56" id="cur_local_dir"></td>
        </tr>
    </table>

	<table id="showlist">
    	<tr><td colspan="2" height="3"></td></tr>
    </table>
	</div>    </td>
    
    
    <td width="10" height="10"></td>
  </tr>
</table>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td height="5"></td></tr>
</table>


<table width="1003" border="0" cellspacing="0" cellpadding="0" align="center">
  
  <tr>
  	<td width="10" bgcolor="#FFFFFF"></td>
    <td height="48" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 ��Ȩ���С���<br />
������λ���»��缼���֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  	<td width="10" bgcolor="#FFFFFF"></td>
  </tr>
</table>

</body>
</html>

<link href="<%=path%>/include/FTP/Span.css"  rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=path%>/include/FTP/LocalDir.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTP/ShowFolders.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTP/StringUtil.js"></script>
<script language="javascript">
drawelement("root");
function changemessage(id){
	//document.getElementById("message").className="title_mainmessage"+id;
	for(i=1;i<3;i++){
		document.getElementById("message"+i).style.display="none";
	}
	document.getElementById("message"+id).style.display="block";
}

</script>
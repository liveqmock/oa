<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
      <a href="/infopub/servlet/FrontPageServlet" target="mainFrame"><img src="/oabase/include/images/left_home.gif" border="0" align="absmiddle">��Ϣ��ҳ</a>
    </td>
    <td align="center">
      <a href="#" onclick="loadsetup();"><img src="/oabase/include/images/left_setting.gif" border="0" align="absmiddle">��ҳ����</a>
    </td>  
  </tr>
</table>
<br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="/oabase/include/images/pics-01.gif" width="9" height="32"></td>
    <td width="1%" background="/oabase/include/images/pics-02.gif"><img src="/oabase/include/images/pics-09.gif" width="35" height="32"></td>
    <td width="96%" background="/oabase/include/images/pics-02.gif" class="unnamed1" align="left">��ʾ��&nbsp</font></td>
    <td width="2%" align="right"><img src="/oabase/include/images/pics-03.gif" width="10" height="32"></td>
  </tr>
  <tr>
    <td background="/oabase/include/images/pics-04.gif"><img src="/oabase/include/images/pics-04.gif" width="9" height="3"></td>
	<td colspan="2" width="98%" bgcolor="#FFFFFF" id="intendwork"></td>
    <td background="/oabase/include/images/pics-05.gif"><img src="/oabase/include/images/pics-05.gif" width="10" height="3"></td> 
  </tr>
  <tr>
    <td><img src="/oabase/include/images/pics-06.gif" width="9" height="21"></td>
    <td colspan="2" background="/oabase/include/images/pics-07.gif" valign="bottom" align="right">
    <a href="/oabase/servlet/IntendWorkListServlet?intype='1','11','2','12'" alt="������칤��" target="mainFrame">����������ʾ��</a></td>
    <td align="right"><img src="/oabase/include/images/pics-08.gif" width="10" height="21"></td>
  </tr>
</table>
<p>
<iframe id=nframe src="/oabase/servlet/GetTopServlet" scrolling="no" width="0" height="0"  marginwidth="0" marginheight="0" frameborder='0'></iframe>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _dowork(workid,url,navigate,type){
//	alert(type);
	if ((type == "11")||(type == "12")){	//������ʾ
	    //alert("/oabase/servlet/DoWorkServlet?workid="+workid+"&url=" + url);
		//window.open("/oabase/servlet/DoWorkServlet?workid="+workid+"&url=" + url,"","width=660,height=240,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=170,top=110");
		window.top.mainFrame.location = "/oabase/servlet/DoWorkServlet?workid="+workid+"&url=" + url;
	}else{									//��ҳ��ʾ
		var funnav;
		if ((navigate == "null")||(navigate == "#")){
			window.top._funNav(4);
		}else{
			window.top._funNav(2);
		}
		window.top.leftFrame.location = navigate;  
		//alert("/oabase/servlet/DoWorkServlet?workid="+workid+"&url=" + url);
		window.top.mainFrame.location = "/oabase/servlet/DoWorkServlet?workid="+workid+"&url=" + url;
	}
}

function loadsetup(){
  window.top.mainFrame.location="/infopub/info/maintance/userSet.jsp";
  window.top.leftFrame.leftiframe.location.replace("/infopub/servlet/UserSetTreeServlet");
  window.top.mainFrame.reload();
  }
//-->
</SCRIPT>
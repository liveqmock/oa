<%@ page contentType="text/html; charset=gbk" %>


<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>

<%
  String currentDay = request.getParameter("currentDay");
  String selectDay = request.getParameter("selectDay");
  System.out.println("the selectday is:"+selectDay);
  Date now=new Date();
  //SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
  //String formatTime = formatter.format(now);
  SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
  String formatTime2 = "";
  if(selectDay!=null&&!("null".equals(selectDay))&&!("".equals(selectDay))){
  	formatTime2 = selectDay;  	
  	System.out.println("the selectday is:..........");
  }else{
  	formatTime2 = formatter2.format(now);
  	System.out.println("the selectday is:111111111");
  }
  String page_num=request.getParameter("page_num");
  System.out.println("the result is:"+formatTime2+" and "+page_num);
%>
<html>
<head>
<title>编辑备忘录 </title>
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/include/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript>
<!--
writeDIV();//启动日历javascript
function checkform(url)
{
	if (document.formmemo.subject.value == "")
	{			
		alert('请填写主题以便以后阅读!');
		document.formmemo.subject.focus();
		return false;
	}
	if (document.formmemo.content.value == "")
	{			
		alert('请填写内容以便以后阅读!');
		document.formmemo.content.focus();
		return false;
	}
	
	document.formmemo.action=url+"/servlet/AddMemoServlet";
	document.formmemo.submit();
	//location.href='javascript:history.go(-1)';
}

function rewrite(){
	
	document.formmemo.subject.value="";
	document.formmemo.content.value="";
	return false;
}

function fPopUpCalendarDlg(ctrlobj){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
}

function update(){
	if(<%=currentDay%>==true){
		curday.style.display = "block";
		oneday.style.display = "none";
		document.formmemo.startTime1.value = "<%=formatTime2%>";
	}else{
		curday.style.display = "none";
		oneday.style.display = "block";
		document.formmemo.startTime1.value = "";
	}
}

//-->
</SCRIPT>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" marginwidth="0" 
marginheight="0">
<br>
<form name="formmemo"  action='' method='POST'>
  <input name=_page_num type=hidden value="<%=page_num%>">
  <input name=selectDay type=hidden value="<%=selectDay%>">
  <table width="75%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>

      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="/infopub/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">备忘录</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
	  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="2" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="0">
              <tr>
                <td id="curday" style="display:none" background="<%=request.getContextPath()%>/images_new/bg-09.jpg" bgcolor="#D8EAF8" colspan="2">时间:
                     <input type="text" name="startTime1" size="16" class="txt2" readonly style="cursor:hand" onclick="fPopUpCalendarDlg(startTime)">
                     <img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 align="absmiddle" alt="点击 弹出日历" onclick="fPopUpCalendarDlg(startTime1)">
                     <select name="hour_m" >
              			<option value="0" selected>0</option>
              			<option value="1" >1</option>
              			<option value="2" >2</option>
              			<option value="3" >3</option>
              			<option value="4" >4</option>
              			<option value="5" >5</option>
              			<option value="6" >6</option>
              			<option value="7" >7</option>
              			<option value="8" >8</option>
              			<option value="9" >9</option>
              			<option value="10" >10</option>
              			<option value="11" >11</option>
              			<option value="12" >12</option>
              			<option value="13" >13</option>
              			<option value="14" >14</option>
              			<option value="15" >15</option>
              			<option value="16" >16</option>
              			<option value="17" >17</option>
              			<option value="18" >18</option>
              			<option value="19" >19</option>
              			<option value="20" >20</option>
              			<option value="21" >21</option>
              			<option value="22" >22</option>
              			<option value="23" >23</option>
            		</select>&nbsp;时&nbsp;
            		<select name="minu_m" >
              			<option value="0" selected >:00</option>
              			<option value="5" >:05</option>
              			<option value="10" >:10</option>
              			<option value="15" >:15</option>
              			<option value="20" >:20</option>
              			<option value="25" >:25</option>
              			<option value="30" >:30</option>
              			<option value="35" >:35</option>
              			<option value="40" >:40</option>
              			<option value="45" >:45</option>
              			<option value="50" >:50</option>
              			<option value="55" >:55</option>
            		</select>分                     
                </td>
                <td id="oneday" style="display:none" background="<%=request.getContextPath()%>/images_new/bg-09.jpg" bgcolor="#D8EAF8" colspan="2">时间:
                     <input type="text" name="startTime2" value="<%=formatTime2%>" size="16" class="txt2" readonly style="cursor:hand">
                     <select name="hour_m" >
              			<option value="0" selected>0</option>
              			<option value="1" >1</option>
              			<option value="2" >2</option>
              			<option value="3" >3</option>
              			<option value="4" >4</option>
              			<option value="5" >5</option>
              			<option value="6" >6</option>
              			<option value="7" >7</option>
              			<option value="8" >8</option>
              			<option value="9" >9</option>
              			<option value="10" >10</option>
              			<option value="11" >11</option>
              			<option value="12" >12</option>
              			<option value="13" >13</option>
              			<option value="14" >14</option>
              			<option value="15" >15</option>
              			<option value="16" >16</option>
              			<option value="17" >17</option>
              			<option value="18" >18</option>
              			<option value="19" >19</option>
              			<option value="20" >20</option>
              			<option value="21" >21</option>
              			<option value="22" >22</option>
              			<option value="23" >23</option>
            		</select>&nbsp;时&nbsp;
            		<select name="minu_m" >
              			<option value="0" selected >:00</option>
              			<option value="5" >:05</option>
              			<option value="10" >:10</option>
              			<option value="15" >:15</option>
              			<option value="20" >:20</option>
              			<option value="25" >:25</option>
              			<option value="30" >:30</option>
              			<option value="35" >:35</option>
              			<option value="40" >:40</option>
              			<option value="45" >:45</option>
              			<option value="50" >:50</option>
              			<option value="55" >:55</option>
            		</select>分                     
                </td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg" bgcolor="#D8EAF8">主题：<input name="subject" type="text" class="txt2" size="50" /></td>
				<td width="35%" bgcolor="#D8EAF8">&nbsp;</td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
			  <tr>
                <td bgcolor="#D8EAF8" colspan="2" align="left" >
	              备忘录内容：<br>
				  <textarea class="txt2" name="content" cols=74 rows=8></textarea>
                        <br>
				  说明：备忘录内容不能超过200字
				</td>
              </tr>
			  <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1" colspan="2"></td>
              </tr>
			  
            </table>
		  </td>
          <td width="2" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="2" height="4"></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
						  <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
						  <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">
   						     　　
 　　                          </td>
						  
						  <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
						</tr>
			    </table>
	  
	  </td>
    </tr>
	<tr>
    <td height="18" align="center">
    </td>
  	</tr>    
	<tr>
    <td align="center">	  
      <img src="<%=request.getContextPath()%>/images/baocun.gif" onClick="checkform('<%=request.getContextPath()%>');" style="cursor:hand">&nbsp;&nbsp;
	  <img src="<%=request.getContextPath()%>/images/clear.gif" onclick="rewrite();" style="cursor:hand"> &nbsp;&nbsp;
	  <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand"> 
	</td>
    
  </tr>
    
  
</table>
</form>
<script language="javascript">
	update();
</script>
</body>
</html>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>OA数据导入</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>
<body background="<%=request.getContextPath()%>/webapp/images/grid.gif">


 <FORM enctype="multipart/form-data" name=form action=""  method=post>
 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
 <tr>
      <td width="" valign="top">
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            		<td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">添加excel</td>
            		<td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            		<td width="100%">
              				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                			<tr>
                  					<td background="<%=request.getContextPath()%>/images/bg-09.jpg">       						  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="200" height="22" class="text-01" align="right">添加excel&nbsp;&nbsp; </td>
                                        <td width="2" rowspan="13" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" colspan="2" bgcolor="F2F9FF" class="txt3">
                                          <input name="dataimp" type="file" value="" size="100"></td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                       
                                      </tr>
                                      
                                    </table></td>
                			</tr>
            				</table>
					</td>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
            		<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
          	</tr>
        	</table>
    </tr>
  	</table>
  	
  			<br>
		 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-add.gif"  onclick="javascript:_addexcel()" style="cursor:hand"> &nbsp;&nbsp;
               
				<img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()"  style="cursor:hand"> 
              </div></td>  
          </tr>
        </table>
  	
  	
</form>


</body>
</html>

<script language="JavaScript">
 function _addexcel(){
	if(document.form.dataimp.value=="")
	{
		alert("请选择excel作为文章导入内容!");
		return;
	}else{
		document.form.action="<%=request.getContextPath()%>/useraddress/fileupload.jsp";
    	document.form.submit();
	}
	//window.top.leftFrame.location.reload();
	
 }
 </script>
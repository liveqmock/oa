<%@ page contentType="text/html; charset=gb2312" language="java" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);  
%>

<%
String parentId = request.getParameter("parentId")==null?"-1":request.getParameter("parentId");
//out.print(parentId);
%>


<html>
<head>
<title>Ìí¼ÓÈÕÖ¾</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>

  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
   <FORM name=form1 action=""  method=post>
    <tr> 
      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif"> 
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">ÏµÍ³°æ¿éÌí¼Ó</td>
            <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
          </tr>
        </table>
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td width="20%" height="22" class="text-01"><div align="right">°æ¿éÃû³Æ: 
                          </div></td>
                        <td width="2" rowspan="9" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="2"></td>
                        <td width="80%" bgcolor="F2F9FF" class="text-01">
						<table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
	      
 							  <td><input name="sysname" type="text" value="" size="30" maxlength=30></td>
                              <td class="text-01">&nbsp;</td>
							  </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
					   <tr> 
                        <td width="20%" height="22" class="text-01"><div align="right">°æ¿é´úÂë: 
                          </div></td>
                        <td width="80%" bgcolor="F2F9FF" class="text-01">
						<table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
	      
 							  <td><input name="syscode" type="text" value="" size="30" maxlength=30></td>
                              <td class="text-01">&nbsp;</td>
							  </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
					   <tr> 
                        <td width="20%" height="22" class="text-01"><div align="right">°æ¿éÅÅÐò: 
                          </div></td>
                        <td width="80%" bgcolor="F2F9FF" class="text-01">
						<table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
	      
 							  <td><input name="sysorder" type="text" value="" size="30" maxlength=30></td>
                              <td class="text-01">&nbsp;</td>
							  </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr> 
                        <td height="22"><div align="right" class="text-01">°æ¿éÃèÊö£º</div></td>
                           
                        <td width="30%" bgcolor="F2F9FF">
						<textarea name="sysmemo" cols="50" rows="6"></textarea>
                        </td>
                      </tr>
						
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
          </tr>
        </table>
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
            <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"></div></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
        </table>
		
		<br>
		 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-add.gif"  onclick="javascript:_add('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="javascript:_goback('<%=parentId%>')"  style="cursor:hand"> 
              </div></td>  
          </tr>
        </table>
      </td>
    </tr>
	</form>
  </table>
<script language="JavaScript">
 function _add(url,parentId){

	document.form1.action=url+"/servlet/AddLogSysServlet?parentId="+parentId;
    document.form1.submit();
	window.top.leftFrame.location.reload();
	
 }
 
 function _goback(parentId){
     if (parentId == "1")
		 window.location.href = "<%=request.getContextPath()%>/servlet/ShowAddrbookRootFolderServlet";
	 else
		 window.location.href = "<%=request.getContextPath()%>/servlet/ShowAddressbookListServlet?parentId=" + parentId;
 }
 
 
 </script>
</body>
</html>










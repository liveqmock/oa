<%@ page contentType="text/html; charset=gb2312" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%
String parentId = request.getParameter("parentId");
//out.print(parentId);
%>

<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <form name=form1   enctype="multipart/form-data" method=post>
    <tr> 
      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif"> 
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">上传文件</td>
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
                        <td width="20%" height="22" class="text-01"><div align="right">文件浏览：</div></td>
                        <td width="2" rowspan="7" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="2"></td>
                        <td width="80%" bgcolor="F2F9FF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td><input name="file" type="file" class="bt1"  style="width:300px" ></td>
                              <td class="text-01">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr> 
                        <td height="22"><div align="right" class="text-01">文件描述：</div></td>
                        <td width="30%" bgcolor="F2F9FF"> <textarea name="fileDes" cols="50" rows="6"></textarea> 
                        </td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      <input type="hidden" name="folderId" value="<%=parentId%>"></tr>
                      
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
				<img src="<%=request.getContextPath()%>/images/botton-upload.gif"  onclick="javascript:_upload('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()" style="cursor:hand"> 
              </div></td>  
          </tr>
        </table></td>
    </tr>
	
  </form>
</table>
</body>
</html>
<script language="JavaScript">
 function _upload(url,parentId){

	
	document.form1.action=url+"/servlet/UploadFileServlet?parentId="+parentId;
    document.form1.submit();
	window.top.leftFrame.location.reload();
	
 }
 </script>


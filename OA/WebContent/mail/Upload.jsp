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
<link href="<%=request.getContextPath()%>/Style/css_grey.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>
  <form name=form1   enctype="multipart/form-data" method=post>
<table width="750"  border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" align="center">

    <tr> 
      <td valign="top" bgcolor="#FFFFFF"> 
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" class="block_title">&nbsp;</td>
            <td width="97%" class="block_title">上传文件</td>
            <td width="2%" class="block_title"><div align="right"></div></td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1" ></td>
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td ><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td width="20%" height="22" bgcolor="#FFFFFF" class="text-01"><div align="right">文件浏览：</div></td>
                        <td width="2" rowspan="7" valign="top" bgcolor="#FFFFFF" ></td>
                        <td width="80%" bgcolor="#FFFFFF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td><input name="file" type="file" class="bt1"  style="width:300px" ></td>
                              <td class="text-01">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" bgcolor="#FFFFFF" class="text-01"></td>
                        <td bgcolor="#FFFFFF" ></td>
                      </tr>
                      <tr> 
                        <td height="22" bgcolor="#FFFFFF"><div align="right" class="text-01">文件描述：</div></td>
                        <td width="30%" bgcolor="#FFFFFF"> <textarea name="fileDes" cols="50" rows="6"></textarea> 
                        </td>
                      </tr>
                      <tr> 
                        <td height="2" ></td>
                        <td  bgcolor="FFFFFF"></td>
                      <input type="hidden" name="folderId" value="<%=parentId%>"></tr>
                      
                    </table></td>
                </tr>
              </table></td>
            <td width="1" ></td>
          </tr>
        </table>
        <br>
		 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td bgcolor="#FFFFFF"> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-upload.gif"  onclick="javascript:_upload('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;&nbsp;
            <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()" style="cursor:hand">            </div></td>  
          </tr>
      </table></td>
    </tr>
	
 
</table>
 </form>
</body>
</html>
<script language="JavaScript">
 function _upload(url,parentId){

	
	document.form1.action=url+"/servlet/UploadFileServlet?parentId="+parentId;
    document.form1.submit();
 }
 </script>


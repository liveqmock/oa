<%@ page contentType="text/html; charset=GBK" %>

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
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/Style/css_grey.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>

  <table width="750"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" class="table_bgcolor" align="center">
   <FORM name=form1 action=""  method=post>
    <tr> 
      <td valign="top" bgcolor="#FFFFFF"> 
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" class="block_title">&nbsp;</td>
            <td width="97%" class="block_title">文件夹添加</td>
            <td width="2%" class="block_title"><div align="right"></div></td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td ><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td width="20%" height="22" bgcolor="#FFFFFF" class="message_title_bold"><div align="right">文件夹名称: 
                          </div></td>
                        <td width="2" rowspan="7" valign="top" bgcolor="#FFFFFF"></td>
                        <td width="80%" bgcolor="#FFFFFF" class="text-01">
                        <table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
	      
 <td><input name="folderName" type="text" value="<%=(request.getParameter("folderName")!=null)?(request.getParameter("folderName")):""%>" size="30" maxlength=30></td>
                              <td class="text-01">&nbsp;</td>
							  <% if(request.getParameter("folderName")!=null&&!(request.getParameter("folderName").equals(""))){ %>
							  <td><font color="#003300">**您输入的文件或文件夹重名**</font></td>
							  <% } %>
							   <% if(new String("") .equals((request.getParameter("folderName")))){ %>
							  <td><font color="#003300">**请您输入文件或文件夹的名称**</font></td>
							  <% } %>
							  
							  
							
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" bgcolor="#FFFFFF" class="text-01"></td>
                        <td bgcolor="#FFFFFF"></td>
                      </tr>
                      <tr> 
                        <td height="22" bgcolor="#FFFFFF" class="message_title_bold"><div align="right" class="text-01">文件夹描述：</div></td>
                           
                        <td width="30%" bgcolor="#FFFFFF">
<textarea name="folderDes" cols="50" rows="6"><%=(request.getParameter("folderDes")!=null)?(request.getParameter("folderDes")):""%></textarea>
                        </td>
                      </tr>
						
                      <tr> 
                        <td height="2"></td>
                        <td class="text-01"></td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
            
          </tr>
        </table>
        <br>
		 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-add.gif"  onclick="javascript:_addFolder('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()"  style="cursor:hand"> 
              </div></td>  
          </tr>
        </table>
      </td>
    </tr>
	</form>
  </table>

</body>
</html>

<script language="JavaScript">
 function _addFolder(url,parentId){

	document.form1.action=url+"/servlet/AddFolderServlet?parentId="+parentId;
    document.form1.submit();
	window.top.leftFrame.location.reload();
	
 }
 </script>
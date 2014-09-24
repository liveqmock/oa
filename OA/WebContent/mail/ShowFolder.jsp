<%@ page contentType="text/html; charset=gb2312" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%

FolderPackageVO vo = (FolderPackageVO)request.getAttribute("folderVO");
String folderId = (String)request.getAttribute("folderId");
String parentId = (String)request.getAttribute("parentId");
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

  <table width="750"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" class="table_bgcolor">
   <FORM name=form1 action=""  method=post>
    <tr> 
      <td valign="top" bgcolor="#FFFFFF"> 
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" class="block_title">&nbsp;</td>
            <td width="97%" class="block_title">文件夹修改</td>
            <td width="2%" class="block_title">&nbsp;</td>
          </tr>
        </table>
        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td ><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td width="20%" height="22" bgcolor="#FFFFFF" class="text-01"><div align="right">文件夹名称：</div></td>
                        <td width="2" rowspan="7" valign="top" bgcolor="#FFFFFF" ></td>
                        <td width="80%" bgcolor="#FFFFFF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td><input name="folderName" type="text" size="30" maxlength=30 value="<%=vo.getFpName()%>"></td>
                              <td class="text-01">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" bgcolor="#FFFFFF" ></td>
                        <td bgcolor="#FFFFFF" ></td>
                      </tr>
                      <tr> 
                        <td height="22" bgcolor="#FFFFFF"><div align="right" class="text-01">文件夹描述：</div></td>
                           
                        <td width="30%" bgcolor="#FFFFFF">
<textarea name="folderDes" cols="50" rows="6"><%=(vo.getFpDesc()==null)?"":vo.getFpDesc()%></textarea>
                        </td>
                      </tr>
						 <tr> 
                        <td height="2" ></td>
                        <td  bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
						 
                    </table></td>
                </tr>
              </table></td>
           
          </tr>
        </table>
        
		<br>
		 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td bgcolor="#FFFFFF"> 
		      <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-update.gif"  onclick="javascript:_addFolder('<%=request.getContextPath()%>','<%=folderId%>')" style="cursor:hand"> &nbsp;&nbsp;
            <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()" style="cursor:hand" >              </div></td>  
          </tr>
        </table>
       </td>
    </tr>
	<INPUT TYPE="hidden" name="parentId" value="<%=parentId%>">
	</form>
  </table>

</body>
</html>

<script language="JavaScript">
 function _addFolder(url,folderId){

	
	document.form1.action=url+"/servlet/ReNameServlet?folderId="+folderId;
    document.form1.submit();
	window.top.leftFrame.location.reload();
	
 }
 </script>
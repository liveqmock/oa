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
            <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">文件夹修改</td>
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
                        <td width="20%" height="22" class="text-01"><div align="right">文件夹名称：</div></td>
                        <td width="2" rowspan="7" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="2"></td>
                        <td width="80%" bgcolor="F2F9FF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td><input name="folderName" type="text" size="30" maxlength=30 value="<%=vo.getFpName()%>"></td>
                              <td class="text-01">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr> 
                        <td height="22"><div align="right" class="text-01">文件夹描述：</div></td>
                           
                        <td width="30%" bgcolor="F2F9FF">
<textarea name="folderDes" cols="50" rows="6"><%=vo.getFpDesc()%></textarea>
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
				<img src="<%=request.getContextPath()%>/images/botton-update.gif"  onclick="javascript:_addFolder('<%=request.getContextPath()%>','<%=folderId%>')" style="cursor:hand"> &nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()" style="cursor:hand" > 
              </div></td>  
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
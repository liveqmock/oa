<%@ page contentType="text/html; charset=gb2312" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%

String boardId = request.getParameter("boardId");
String boardName = request.getParameter("boardName");
String auditFlag = request.getParameter("auditFlag"); 
//out.print(boardId);
//out.print(auditFlag);
%>
<html>
<head>
<title>发贴</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">

<TABLE class=tb1 cellSpacing=1 cellPadding=0 width="98%" border=0>
  <TBODY>
  <TR>
  <TD class=tdc align=left bgColor=#f7f7f7 height=23 class="text-01"><IMG 
      src="<%=request.getContextPath()%>/images/bbs/todaytopic.gif"> <A class="text-01"
      href="<%=request.getContextPath()%>/servlet/ShowAreaServlet">论坛首页</A> ==&gt; <A class="text-01"
      href="<%=request.getContextPath()%>/servlet/ShowTopicServlet?boardId=<%=boardId%>&primeFlag=0"><%=boardName%></A> ==&gt;<a class="text-01">发表新主题 </a></TD>
      <TD class=tdc align=right bgColor=#f7f7f7></TD></TR></TBODY></TABLE><FONT 
size=1>&nbsp;</FONT><BR>
 <FORM name=form1 action="" enctype="multipart/form-data" method=post>
<table width="100%" height="523"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif"><div align="center"><br>
      </div>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">发新帖</td>
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
                    <td width="20%" height="22" class="text-01"><div align="right">主题：</div></td>
                    <td width="2" rowspan="10" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="2"></td>
                    <td width="80%" bgcolor="F2F9FF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><input name="articleName" type="text" size="30" maxlength=30 class="text-01"></td>
                        <td class="text-01">（不得超过30个汉字）</td>
                      </tr>
                    </table></td>
                    </tr>
                  <tr>
                    <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" class="text-01"></td>
                    <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    </tr>
                  <tr>
                    <td height="44" class="text-01"><div align="right">当前心情&#65306;</div></td>
                    <td bgcolor="F2F9FF" class="text-01"><table height="44"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                      <tr>
                        <td><div align="center">
                            <input type="radio" name="face" value="0.gif" CHECKED>
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/0.gif" width="21" height="21"></div></td>
                        <td><div align="center">
                            <input type="radio" name="face" value="1.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/1.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="10.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/10.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="12.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/12.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="13.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/13.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="15.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/15.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="16.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/16.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="17.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/17.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="5.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/5.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="8.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/8.gif" width="21" height="21"></div></td>
                        <td class="text-01">
                          <div align="center">
                            <input type="radio" name="face" value="18.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/18.gif" width="21" height="21"> </div></td>
                      </tr>
                      <tr>
                        <td><div align="center">
                            <input type="radio" name="face" value="20.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/20.gif" width="21" height="21"> </div>
                            <div align="center"></div></td>
                        <td><div align="center">
                            <input type="radio" name="face" value="21.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/21.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="23.gif">
                        </div></td>
                        <td class="text-01"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/23.gif" width="21" height="21">
                            <div align="center"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="24.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/24.gif" width="21" height="21"> </div>
                            <div align="center"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="26.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/26.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="27.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/27.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="29.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/29.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="3.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/3.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="6.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/6.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="9.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/9.gif" width="21" height="21"></div></td>
                        <td class="text-01"><div align="center">
                            <input type="radio" name="face" value="4.gif">
                        </div></td>
                        <td class="text-01"><div align="center"><img src="<%=request.getContextPath()%>/images/bbs/reArticle/4.gif" width="21" height="21"></div></td>
                      </tr>
                    </table></td>
                    </tr>
                  <tr>
                    <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr>
                  <tr>
                    <td height="22"><div align="right" class="text-01">内容&#65306;</div></td>
                    <td bgcolor="F2F9FF" class="text-01"><table width="98%"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30%"><textarea name="content" cols="60" rows="8" class="text-01"></textarea></td>
                        <td width="3%" valign="bottom"><input type="checkbox" name="checkbox4" value="checkbox"></td>
                        <td width="67%" valign="bottom" class="text-01">显示个性签名</td>
                      </tr>
                    </table></td>
                    </tr>
                  <tr>
                    <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr>
                  <tr>
                    <td height="22"><div align="right" class="text-01">上传文件&#65306;</div></td>
                    <TD class=tdc bgColor=#eff1f3 class="text-01"><input class=bdtj type="file" name="acc" class="text-01"></TD>
                      </tr>
                   <tr>
                    <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr>
                  <tr>
                    <td height="22"><div align="right" class="text-01"></div></td>
                    <td height="22" bgcolor="F2F9FF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td><img src="<%=request.getContextPath()%>/images/bbs/newbbs/button_ok.gif" width="59" height="19" hspace="5" onclick="javascript:_newArticle('<%=request.getContextPath()%>')"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/button_yulan.gif" width="59" height="19" hspace="5"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/button_qingkong.gif" width="59" height="19" hspace="5"></td>
                          <td class="text-01">[按CTRL+ENTER直接发表] 帖子长度：</td>
                          <td class="text-01">&nbsp;最大为：3000字节，最小为0字节&nbsp;</td>
                          </tr>
                      </table>
					  <INPUT TYPE="hidden" name="boardId" value="<%=boardId%>">
						<INPUT TYPE="hidden" name="auditFlag" value="<%=auditFlag%>">
                        <div align="right"></div></td>
                  </tr>
                  <tr>
                    <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                    <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
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
      <p>&nbsp;</p></td>
  </tr>
</table>
</form>
</body>
</html>


<script language="JavaScript">

function _newArticle(url){

	document.form1.action=url+"/servlet/NewArticleServlet";
    document.form1.submit();
	
	
 }


</script>
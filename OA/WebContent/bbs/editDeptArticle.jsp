<%@ page contentType="text/html; charset=GBK" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%

Collection scollection = (Collection)request.getAttribute("articleList");

String topicId = request.getParameter("topicId");
BbsBoardVO boardVO = (BbsBoardVO)request.getAttribute("boardVO");
String boardId = boardVO.getBoardid().toString();
String boardName = boardVO.getBoardname();
//out.print(boardId+topicId);
Iterator Iterator = scollection.iterator();
BbsArticleVO vo = null;
if(Iterator.hasNext())
{
	vo = (BbsArticleVO)Iterator.next();
}
%>
<html>
<head>
<title>编辑帖子</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="/Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
</head>
<body>
<FORM name=form1 action="" enctype="multipart/form-data" method=post>

<%@ include file= "/include/top.jsp" %>

<table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="5" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td colspan="5" valign="top"></td>
			</tr>
			<tr>
				<td colspan="5" valign="top">
				<table width="100%" border="0" cellpadding="2" cellspacing="1"
					bgcolor="#FFFFFF">
					<tr>			
						<td colspan="5" valign="top">
						<div align="left">
						<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#f7f7f7
							border=0>
							<tr>
								<td width="100%" bgColor=#f7f7f7 class="blue3-12-b"><IMG
									title=到论坛首页 style="CURSOR: hand"
									onClick="javascript:_gohome('<%=request.getContextPath()%>');"
									src="../images/bbshome.gif"><a style="text-decoration: none"
									href="<%=request.getContextPath()%>/servlet/ShowDeptBBSServlet"
									title="到部门交流园地首页">部门交流园地</a> ==&gt; <A class="STYLE4"
									style="text-decoration: none"
									href="<%=request.getContextPath()%>/servlet/ShowDeptTopicServlet?boardId=<%=boardId%>&primeFlag=0"><%=boardName%></a> ==&gt;
								<a class="STYLE4">编辑帖子</a></td>
								<td width="25%" align="right" nowrap bgColor=#f7f7f7><!--<a href="#"
							class="blue3-12" style="cursor: hand; text-decoration: none">全部</a>
						<a href="#" class="blue3-12"
							style="cursor: hand; text-decoration: none">精华</a>-->&nbsp;&nbsp;</td>
							</tr>
						</TABLE>
						</div>
						</td>
						<!--<td width="11"><img src="../images/kongbai.jpg" width="11"
					height="11" /></td>-->
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
		<tr>
		<td colspan="5" valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
			<tr>
				<td width="100%" class="block_title" colspan=3 height="23">"编辑<%=vo.getArticlename()%>"
				</td>
			</tr>
			<tr>
				<td width="15%" height="22" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">主题：</div>
				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=2><input
					name="articleName" maxlength=60 class="blue2-12" size=61 value="<%=vo.getArticlename()%>">（不得超过30个汉字）</td>
			</tr>
			<tr>
				<td height="44" class="blue2-12" bgcolor="#FFFFFF">
				<div align="right">当前心情&#65306;</div>
				</td>
				<td bgcolor="#FFFFFF" class="blue2-12" colspan=2>
				<table height="44" border="0" cellpadding="0" cellspacing="0"
					bgcolor="#FFFFFF">
					<tr>
						<td>
						<div align="center"><input type="radio" name="face" value="0.gif"
							CHECKED></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/0.gif" width="21"
							height="21"></div>
						</td>
						<td>
						<div align="center"><input type="radio" name="face" value="1.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/1.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="10.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/10.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="12.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/12.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="13.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/13.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="15.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/15.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="16.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/16.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="17.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/17.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="5.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/5.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="8.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/8.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="18.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/18.gif" width="21"
							height="21"></div>
						</td>
					</tr>
					<tr>
						<td>
						<div align="center"><input type="radio" name="face" value="20.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/20.gif" width="21"
							height="21"></div>
						<div align="center"></div>
						</td>
						<td>
						<div align="center"><input type="radio" name="face" value="21.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/21.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="23.gif"></div>
						</td>
						<td class="blue2-12"><img src="../images/23.gif" width="21"
							height="21">
						<div align="center"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="24.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/24.gif" width="21"
							height="21"></div>
						<div align="center"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="26.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/26.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="27.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/27.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="29.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/29.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="3.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/3.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="6.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/6.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="9.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/9.gif" width="21"
							height="21"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="4.gif"></div>
						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/4.gif" width="21"
							height="21"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">内容&#65306;</div>
				</td>
				<td class="blue2-12" bgcolor="#FFFFFF" colspan=2><textarea
					name="content" cols="60" rows="8" class="blue2-12"><%
                        String content = vo.getArticlecontent();
                        if(content != null){
                        
			try {
				content = CommUtil.unformathtm(content);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		                %><%=content%><%}%></textarea><!--<input
					type="checkbox" name="checkbox3" value="checked">是否加入待办事宜--></td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="blue2-12">上传文件&#65306;</div>
				</td>
				<TD bgColor="#FFFFFF" class="blue2-12" colspan=2><input type="file"
					name="acc" size=50></TD>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" colspan=3 align="left" class="blue2-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
					src="../images/button_ok.gif" width="59" height="19" hspace="5"
					onclick="javascript:_editArticle('<%=request.getContextPath()%>')">帖子长度：&nbsp;最大为：3000字节，最小为0字节&nbsp;</td>
			</tr>
	  	<INPUT TYPE="hidden" name="topicId" value="<%=topicId%>">
			<INPUT TYPE="hidden" name="boardId" value="<%=boardId%>">
			<INPUT TYPE="hidden" name="articleId" value="<%=vo.getArticleid()%>">
			<INPUT TYPE="hidden" name="accType" value="<%=vo.getAcctype()%>">
		</table>
		</td>
</table>

</form>
</body>
</html>


<script language="JavaScript">

function _editArticle(url){
	if(document.form1.articleName.value==""){
		alert("主题不能为空！");
		return false;
	}else{
		document.form1.action=url+"/servlet/EditDeptArticleServlet";
    	document.form1.submit();
    	return true;
    }		
 }

function _gohome(url){
  document.location.href = url+"/servlet/ShowDeptBBSServlet";
}
</script>
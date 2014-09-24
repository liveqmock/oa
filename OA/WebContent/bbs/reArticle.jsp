<%@ page contentType="text/html; charset=GBK" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%
String articleName = request.getParameter("articleName");
String articleId = request.getParameter("articleId");
String topicId = request.getParameter("topicId");
String boardId = request.getParameter("boardId");
String boardName = request.getParameter("boardName");
String auditFlag = request.getParameter("auditFlag"); 
String quto = request.getParameter("quto")==null ? "0" :request.getParameter("quto"); 
String reContent = request.getParameter("reContent")==null ?" ":request.getParameter("reContent");

//String conc = request.getParameter("conc"); 

//out.print(auditFlag);
%>
<html>
<head>
<title>回贴</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="/Style/css_grey.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.reAc{
border: 1px solid rgb(153, 153, 153); padding: 3px; background: rgb(255, 255, 238) none repeat scroll 0% 0%; overflow: hidden; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; margin-bottom: 12px;
}
.reSp{
padding: 5px 3px 0pt 0pt; color: rgb(30, 80, 162);
}
.reP{
margin: 9px 3px; line-height: 130%; color: rgb(0, 0, 0); clear: both;
}
</style>
<script>

</script>
</head>
<body background="" >
<FORM name=form1 method=post action="" enctype="multipart/form-data">
<%@ include file= "/include/top.jsp" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
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
								<td width="100%" bgColor=#f7f7f7 class="message_title"><IMG
									title=到论坛首页 style="CURSOR: hand"
									onClick="javascript:_gohome('<%=request.getContextPath()%>');"
									src="../images/bbshome.gif"><a style="text-decoration: none"
									href="<%=request.getContextPath()%>/servlet/ShowIndexServlet"
									title="到论坛首页">论坛首页</a> ==&gt; <A class="STYLE4"
									style="text-decoration: none"
									href="<%=request.getContextPath()%>/servlet/ShowTopicServlet?boardId=<%=boardId%>&primeFlag=0"><%=boardName%></a> ==&gt;
								<a class="STYLE4">回复"<%=articleName%>"</a></td>
								<td width="25%" align="right" nowrap bgColor=#f7f7f7>
									<!--<a href="#"
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
		<table width="980" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#B9DAF9" align="center">
			<tr>
				<td width="100%" class="block_title" colspan=3 height="23">
				回复"<%=articleName%>"
				</td>
			</tr>
			<tr>
				<td width="15%" height="22" class="message_title_bold" bgcolor="#FFFFFF">
				<div align="right">主题：</div>
				</td>
				<td bgcolor="#FFFFFF" class="message_title_bold" colspan=2><input
					name="articleName" maxlength=60 class="blue2-12" size=61 value="Re:<%=articleName%>">（不得超过30个汉字）</td>
			</tr>
			<tr>
				<td height="44" class="message_title_bold" bgcolor="#FFFFFF">
				<div align="right">当前心情&#65306;</div>
				</td>
				<td bgcolor="#FFFFFF" class="message_title_bold" colspan=2>
				<table height="44" border="0" cellpadding="0" cellspacing="0"
					bgcolor="#FFFFFF">
					<tr>
						<td>
						<div align="center"><input type="radio" name="face" value="0.gif"
							CHECKED></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/0.gif" width="21"
							height="21"></div>
						</td>
						<td>
						<div align="center"><input type="radio" name="face" value="1.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/1.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="10.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/10.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="12.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/12.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="13.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/13.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="15.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/15.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="16.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/16.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="17.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/17.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="5.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/5.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="8.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/8.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="18.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/18.gif" width="21"
							height="21"></div>
						</td>
					</tr>
					<tr>
						<td>
						<div align="center"><input type="radio" name="face" value="20.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/20.gif" width="21"
							height="21"></div>
						<div align="center"></div>
						</td>
						<td>
						<div align="center"><input type="radio" name="face" value="21.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/21.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="23.gif"></div>
						</td>
						<td class="message_title_bold"><img src="../images/23.gif" width="21"
							height="21">
						<div align="center"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="24.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/24.gif" width="21"
							height="21"></div>
						<div align="center"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="26.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/26.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="27.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/27.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="29.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/29.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="3.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/3.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="6.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/6.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="9.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/9.gif" width="21"
							height="21"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><input type="radio" name="face" value="4.gif"></div>
						</td>
						<td class="message_title_bold">
						<div align="center"><img src="../images/4.gif" width="21"
							height="21"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="message_title_bold">内容&#65306;</div>
				</td>
				<td class="message_title_bold" bgcolor="#FFFFFF" colspan=2>
				<textarea name="content" cols="80" rows="12" class="blue2-12" onKeyDown='max(this,<%=1500 - reContent.length()%>)' onKeyUP='max(this,<%=1500 - reContent.length()%>)'></textarea>
				<div class="message_title" id='zishu' ></div>
					<!--<input
					type="checkbox" name="checkbox3" value="checked">是否加入待办事宜-->
				</td>
			</tr>
			<tr>
				<td height="22" bgcolor="#FFFFFF">
				<div align="right" class="message_title_bold">上传文件&#65306;</div>
				</td>
				<TD bgColor="#FFFFFF" class="message_title_bold" colspan=2><input type="file"
					name="acc" size=50></TD>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" colspan=3 align="left" class="message_title_bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="../images/button_ok.gif" width="59" height="19" hspace="5"
					onclick="javascript:_reArticle('<%=request.getContextPath()%>')">帖子长度：&nbsp;最大为：<%=1500 - reContent.length()%>字，最小为0字 (受引用帖子字数的影响)&nbsp;</td>
			</tr>
	  <INPUT TYPE="hidden" name="topicId" value="<%=topicId%>">
	  <INPUT TYPE="hidden" name="rearticleId" value="<%=articleId%>">
	  <INPUT TYPE="hidden" name="boardId" value="<%=boardId%>">
	  <INPUT TYPE="hidden" name="auditFlag" value="<%=auditFlag%>">
	  <INPUT TYPE="hidden" name="quto" value="<%=quto%>">
	  
	  
		</table>
		</td>
</table>
</form>
</body>
</html>


<script language="JavaScript">

	function max(message,max)
	{
	//var max=1500;
	document.getElementById('zishu').innerHTML='您已经输入了<font color=red>'+message.value.length+'</font>字'
	if (message.value.length > max) {
	message.value = message.value.substring(0,max);
	alert("内容不能超过 "+max+" 个字!");
	 }
	}
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}

function _reArticle(url){

	document.form1.action=url+"/servlet/ReArticleServlet";
    document.form1.submit();
	
	
 }

</script>
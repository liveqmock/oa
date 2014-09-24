<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler"%>
<%@ page import="com.icss.oa.bbs.vo.*"%>
<%@ page import="com.icss.oa.util.*"%>
<%@ page import="com.icss.common.log.ConnLog"%>
<%
int pageNum = com.icss.j2ee.util.PageScrollUtil.getPageNum();
if(pageNum==0){
	pageNum=1;
}
int ReNum = com.icss.j2ee.util.PageScrollUtil.getRowCount() - 1;
Boolean ulock  = (Boolean)request.getAttribute("isUnlock");
Collection scollection = (Collection) request.getAttribute("articleList");
BbsBoardVO boardVO = (BbsBoardVO) request.getAttribute("boardVO");
String boardId = boardVO.getBoardid().toString();
Iterator TIterator = scollection.iterator();
BbsUserinfoVO userVO = (BbsUserinfoVO) request.getAttribute("userVO");
List rightList = (List) request.getAttribute("rightList");
String maintopic = (String) request.getAttribute("maintopic");
int numperpage = ((Integer) request.getAttribute("numperpage")).intValue();
ArticleUserinfoVO tvo = null;
String topicId = "";
if (TIterator.hasNext()) {
	tvo = (ArticleUserinfoVO) TIterator.next();
	topicId = tvo.getArticleid().toString();
}
String stopicId = (String) request.getAttribute("stopicId");
if(stopicId!=null){
	topicId =  stopicId;
}

List wholemanagerList = (List) request.getAttribute("managerList");

ManagerUserinfoVO managerVO = null;
String ManagerIds = "";
Iterator managerIterator = wholemanagerList.iterator();
while (managerIterator.hasNext()) {
	managerVO = (ManagerUserinfoVO) managerIterator.next();
	if (managerVO.getBoardid().equals(new Integer(boardId))) {
		ManagerIds += managerVO.getUserid()+"||";
	}
}
%>

<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="/Style/css_red.css" id="homepagestyle" rel="stylesheet"
	type="text/css" />
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
<script language="javascript"> 
function _changeImg(mypic){ 
    var xw=768; 
    var xl=512; 
         
    var width = mypic.width; 
    var height = mypic.height; 
    var bili = width/height;         
         
    var A=xw/width; 
    var B=xl/height; 
         
    if(!(A>1&&B>1)) 
    { 
        if(A<B) 
        { 
            mypic.width=xw; 
            mypic.height=xw/bili; 
        } 
        if(A>B) 
        { 
            mypic.width=xl*bili; 
            mypic.height=xl; 
        } 
    } 
} 

function doZoom(size){ 
var content = document.getElementsByName('copyid');
content[0].style.fontSize=size+'px';
for(i=0;i<content.length;i++)
{
	content[i].style.fontSize=size+'px';
}
} 
</script>

</head>
<body bgcolor="#ffffff">
<FORM name=form1 method=post>
<%
 String _page_num=request.getParameter("_page_num");
  if(_page_num==null||_page_num.equals(""))
  _page_num="1";
%> 
<input type=hidden name="_page_num" value="<%=_page_num%>"> 
<%@ include file="/include/top.jsp"%>

<TABLE cellSpacing=0 cellPadding=0 width="983" bgColor=#FFFFFF border=0
	align="center">
	<tr>
		<td width="100%" align="right" valign="bottom" nowrap="nowrap">
		<table cellspacing=0 cellpadding=0 width="983" bgcolor=#FFFFFF
			border=0 align="center">
			<tr>
				<td colspan="6" height="10"></td>
			</tr>
			<tr>
				<td bgcolor=#ffffff class="message_title_bold" colspan=6 width='70%'><img
					src="<%=request.getContextPath()%>/images/bbs/bbshome.gif"
					style="cursor: hand"
					onClick="javascript:_gohome('<%=request.getContextPath()%>');"
					title="到论坛首页"> <a style="text-decoration: none"
					href="<%=request.getContextPath()%>/servlet/ShowIndexServlet"
					title="到论坛首页">论坛首页</a> ==&gt; <a style="text-decoration: none"
					href="javascript:_topic('<%=request.getContextPath()%>','<%=boardId%>')"
					class="blue3-12-b"><%=boardVO.getBoardname()%></a> ==&gt; <a
					style="text-decoration: none" class="blue3-12-b"> <%=maintopic%>
				</a></td>

				<td class="message_title_bold" align='left'>【字体：<A
					href="javascript:doZoom(20)">大</A> <A href="javascript:doZoom(16)">中</A>
				<A href="javascript:doZoom(12)">小</A>】</td>
			</tr>
			<tr>
				<td colspan="10">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<%@ include file="/include/defaultPageScrollBar2.jsp"%>
					<td width="175"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td background="<%=request.getContextPath()%>/images/2-title-05.jpg"
					bgcolor="#E0EDF8" colspan=10 height="23">
				<div align="left" class="block_title">&nbsp;&nbsp;主题:&nbsp;&nbsp;<%=tvo.getArticlename()%>
				<%if (tvo.getArticlelock().equals("1")) {%> (该主题已关闭) <%}%>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="7" valign="top">
				<%int n = 0;
Iterator iterator = scollection.iterator();
System.err.println("scollection size="+scollection.size());


while (iterator.hasNext()) {
	ArticleUserinfoVO vo = (ArticleUserinfoVO) iterator.next();
%>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="table_bgcolor">
					<tr>
						<td valign=top width="20%" bgcolor=#ffffff>
						<div align=center>
						<table width="96%" align=center border=0 bgcolor=#ffffff
							cellspacing="1">
							<tbody>
								<tr>
									<td valign="top" width="80%">&nbsp;<span
										onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUserid()%>','个人信息','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')"
										style="cursor: hand;" class="foot_message"><b><%=vo.getTurename()%></b></span></td>
									<td valign="top" align=right width="20%">&nbsp;</td>
								</tr>
								<tr>
									<td valign=top colspan=2 bgcolor=#ffffff align="center">
									&nbsp;<!--<IMG height="72" width="50" src="<%=request.getContextPath()%>/servlet/UserInfoServlet?image=1&personuuid=<%=vo.getUserid()%>">-->
									&nbsp;</td>
								</tr>
							</tbody>
						</table>
						</div>
						</td>
						<td valign=top width="100%" bgcolor=#ffffff>
						<table width="96%" align=center border=0>
							<tbody>
								<tr>
									<td class=text-01 valign="top" width="93%" bgcolor=#ffffff
										height=23><a href="#"
										style="cursor: hand; text-decoration: none"
										onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUserid()%>')"><img
										alt=按此观看发贴人的个人资料
										src="<%=request.getContextPath()%>/images/bbs/chakan.gif"
										border=0></a> <a style="cursor: hand; text-decoration: none"
										href="<%=request.getContextPath()%>/servlet/SearchArticleServlet?userId=<%=vo.getUserid()%>&boardId=<%=boardId%>"><img
										alt=按此搜索发贴人在该版块发表的主题
										src="<%=request.getContextPath()%>/images/bbs/find.gif"
										border=0> </a> <%if (vo.getMail() != null) {%> <a
										style="cursor: hand; text-decoration: none"
										href="mailto:<%=vo.getMail()%>"><img alt=按此发邮件给
										<%=vo.getUsername()%>
										src="<%=request.getContextPath()%>/images/bbs/email.gif"
										border=0> </a> <%}
if (vo.getHomepage() != null) {%> <a href="http://<%=vo.getHomepage()%>"
										style="cursor: hand; text-decoration: none" target=_blank><img
										alt=访问 <%=vo.getUsername()%> 的主页
										src="<%=request.getContextPath()%>/images/bbs/homepage.gif"
										border=0> </a> <%}
if (vo.getOicq() != null) {%> <a
										style="cursor: hand; text-decoration: none"
										href="http://search.tencent.com/cgi-bin/friend/user_show_info?ln=<%=vo.getOicq()%>"
										target=_blank><img
										alt="<%=vo.getUsername()%> 的 oicq 是<%=vo.getOicq()%>，查看<%=vo.getUsername()%>的资料"
										src="<%=request.getContextPath()%>/images/bbs/oicq.gif"
										border=0> </a> <%}%> <%
						if (ulock.booleanValue()) {%> <a
										style="cursor: hand; text-decoration: none"
										href=#a>
										<img alt='直接回复此帖'
										src="<%=request.getContextPath()%>/images/bbs/re.gif" border=0 />
									</a>
									
									<a
										style="cursor: hand; text-decoration: none"
										onclick="javascript:_reply(this,'<%=vo.getArticleid()%>','<%=request.getContextPath()%>/bbs/reArticle.jsp?articleName=<%=tvo.getArticlename()%>&topicId=<%=stopicId%>&articleId=<%=vo.getArticleid()%>&boardId=<%=boardId%>&boardName=<%=boardVO.getBoardname()%>&auditFlag=<%=boardVO.getIsaudit()%>&quto=1')">
										<img alt='直接引用此帖'
										src="<%=request.getContextPath()%>/images/bbs/quotereply.gif" border=0 />
									</a>
									
									 <%}%> </td>
									<td class=message_title_bold valign="top" align=right
										bgcolor=#ffffff height=23><b> <%if (n == 0 && pageNum == 1) {%>
									楼 主 <%} else {%> 第<%=((pageNum - 1) * numperpage) + n%>楼 <%}%> </b></td>
								</tr>
								<tr>
									<td bgcolor=#62a1c2 colspan=2 height=1></td>
								</tr>
								<tr>
									<td class=text-01 bgcolor=#ffffff colspan=2 height=*>
									<table width="100%" border=0>
										<tbody>
											<tr>
												<td class=message_title_bold valign=top bgcolor=#ffffff
													height=*><img
													src="<%=request.getContextPath()%>/images/<%=vo.getFace()%>"
													align="top" border=0>标题: <%=vo.getArticlename()%></td>
												<td class=text-01 align=right bgcolor=#ffffff height=*>&nbsp;</td>
											</tr>
											<tr>
												<td class=tdc id=thetd style="FONT-SIZE: 9pt" valign=top
													bgcolor=#ffffff colspan=2 height=*><span id=copyid
													name='copyid' style="FONT-SIZE: 9pt"> <span id ='<%=vo.getArticleid()%>' >
														<%=vo.getArticlecontent()==null?"":vo.getArticlecontent()%>
													</span>
												 <%if (vo.getAcctype().equals("1")) {%> //图片 <img
													src="<%=request.getContextPath()%>/servlet/DownloadAccServlet?articleId=<%=vo.getArticleid()%>"
													onload="_changeImg(this)"> <%} else if (vo.getAcctype().equals("2")) {%>
												//其他 <img
													src="<%=request.getContextPath()%>/images/bbs/download.gif"><a
													class=tdc href="#"
													onClick="javascript:_downLoad('<%=request.getContextPath()%>','<%=vo.getArticleid()%>')"><b>点击下载</b></a>
												<%}%> <%if (vo.getTopic().equals("0") && vo.getEdittime() != null) {%>
												<br>
												<br>
												<i>本回复由作者编辑于 <%=CommUtil.getTime(vo.getEdittime().longValue())%></i><br>
												<br>
												<%} else if (vo.getTopic().equals("1") && vo.getEdittime() != null) {%>
												<br>
												<br>
												<i>本主题由作者编辑于 <%=CommUtil.getTime(vo.getEdittime().longValue())%></i><br>
												<br>
												<%}
if (vo.getUnderwrite() != null) {%> <img
													src="<%=request.getContextPath()%>/images/bbs/sign.gif"><br>
												<i>签名：</i><font size=2pt color=red><%=vo.getUnderwrite()%></font><br>
												<%}%> </span></td>
											</tr>
										</tbody>
									</table>
									</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td height=22 bgcolor=#ffffff>
						<div class=message_title align=center><img alt=发表时间
							src="<%=request.getContextPath()%>/images/posttime.gif"><%=CommUtil.getTime(vo.getEmittime().longValue())%></div>
						</td>
						<td bgcolor=#ffffff height=22>
						<div align=right>
						<%
				//System.err.println("UserId1="+userVO.getUserid());
				//System.err.println("UserId2="+vo.getUserid());
				System.err.println("!!pageNum="+pageNum);
				if(userVO.getUserid().equals(vo.getUserid()) || ManagerIds.indexOf(userVO.getUserid())>=0){
				//System.err.println("匹配");
				
				%> <a href="#"
							onClick="javascript:_editArticle('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>','<%=stopicId!=null?stopicId:tvo.getArticleid().toString()%>')"><img
							alt=编辑该贴 src="<%=request.getContextPath()%>/images/bbs/edit.gif"
							border=0></a> <%
				if(n != 0 ||(n==0&&pageNum>1)){%> <a href="#"
							onClick="javascript:_delArticle('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>','<%=stopicId!=null?stopicId:tvo.getArticleid().toString()%>')"><img
							alt=删除该贴 src="<%=request.getContextPath()%>/images/bbs/del.gif"
							border=0></a> <%}else if(n==0){%> <a
							href="#"
							onClick="javascript:_delArticle2('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>','<%=stopicId!=null?stopicId:tvo.getArticleid().toString()%>')"><img
							alt=删除该贴 src="<%=request.getContextPath()%>/images/bbs/del.gif"
							border=0></a> <%}} %>
						</div>
						</td>
					</tr>
				</table>
				<%n++;
}%>
				</td>
			</tr>
			<%@ include file="/include/defaultPageScrollBar2.jsp"%>
			<td width="56%" align="right" valign="bottom" nowrap="nowrap">
			<%if (ulock.booleanValue()) {%> <a
				href="<%=request.getContextPath()%>/bbs/newArticle.jsp?boardId=<%=boardVO.getBoardid().toString()%>&boardName=<%=boardVO.getBoardname()%>&auditFlag=0"><img
				src="<%=request.getContextPath()%>/images/newtopic.gif" border="0"></a><img
				src="<%=request.getContextPath()%>/images/newspecial.gif"> <!--<A style="cursor: hand; text-decoration: none" href="<%=request.getContextPath()%>/bbs/reArticle.jsp?articleName=<%=tvo.getArticlename()%>&articleId=<%=stopicId%>&boardId=<%=boardId%>&boardName=<%=boardVO.getBoardname()%>&auditFlag=<%=boardVO.getIsaudit()%>">-->
			<!--<a href=#a> <img
				src="<%=request.getContextPath()%>/images/reply.gif" border="0"></a>-->
			 <%}%>
			</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<INPUT TYPE="hidden" name="reContent" value="">
</form>
<%
if (ulock.booleanValue()) {%>
<FORM name=form2 method=post action="" enctype="multipart/form-data">
<table cellSpacing=0 cellPadding=0 width="983" bgColor=#FFFFFF border=0
	align="center">
	<tr>
		<td width="100%" colspan="7">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td colspan="7" valign="top"></td>
			</tr>
			<tr>
				<td colspan="7" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="table_bgcolor">
					<tr>
						<td
							background="<%=request.getContextPath()%>/images/2-title-05.jpg"
							bgcolor="#E0EDF8" height="23" colspan=7>
						<div align="center" class="block_title"><a name=a>快速回复主题</a></div>
						</td>
					</tr>
					<tr>
						<td width="15%" height="22" bgcolor="#FFFFFF"
							class="message_title_bold">
						<div align="right">标题：</div>
						</td>
						<td width="642" colspan=6 bgcolor="#FFFFFF" class="message_title"><input
							name="articleName" maxlength=60 class="blue2-12" size=61
							bgcolor="#FFFFFF" value="Re:<%=tvo.getArticlename()%>">（标题请不要超过30个汉字）</td>
					</tr>
					<tr>
						<td height="44" class="message_title_bold" bgcolor="#FFFFFF">
						<div align="right">当前心情&#65306;</div>
						</td>
						<td bgcolor="#FFFFFF" class="blue2-12" colspan=6>
						<table height="44" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td>
								<div align="center"><input type="radio" name="face"
									value="0.gif" CHECKED></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/0.gif" width="21"
									height="21"></div>
								</td>
								<td>
								<div align="center"><input type="radio" name="face"
									value="1.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/1.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="10.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/10.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="12.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/12.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="13.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/13.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="15.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/15.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="16.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/16.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="17.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/17.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="5.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/5.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="8.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/8.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="18.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/18.gif" width="21"
									height="21"></div>
								</td>
							</tr>
							<tr>
								<td>
								<div align="center"><input type="radio" name="face"
									value="20.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/20.gif" width="21"
									height="21"></div>
								<div align="center"></div>
								</td>
								<td>
								<div align="center"><input type="radio" name="face"
									value="21.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/21.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="23.gif"></div>
								</td>
								<td class="blue2-12"><img
									src="<%=request.getContextPath()%>/images/23.gif" width="21"
									height="21">
								<div align="center"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="24.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/24.gif" width="21"
									height="21"></div>
								<div align="center"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="26.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/26.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="27.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/27.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="29.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/29.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="3.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/3.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="6.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/6.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="9.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/9.gif" width="21"
									height="21"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><input type="radio" name="face"
									value="4.gif"></div>
								</td>
								<td class="blue2-12">
								<div align="center"><img
									src="<%=request.getContextPath()%>/images/4.gif" width="21"
									height="21"></div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td height="22" bgcolor="#FFFFFF" class="message_title_bold">
						<div align="right" class="blue2-12">内容&#65306;</div>
						</td>
						<td class="blue2-12" bgcolor="#FFFFFF" colspan=6><textarea
							name="content" cols="80" rows="8" class="blue2-12"
							onKeyDown='max(this,1500)' onKeyUP='max(this,1500)'></textarea>
						<div class="message_title" id='zishu'></div>
						<!--<input
							type="checkbox" name="checkbox3" value="checked">是否加入待办事宜--></td>
					</tr>
					<tr>
						<td height="22" bgcolor="#FFFFFF">
						<div align="right" class="message_title_bold">上传文件&#65306;</div>
						</td>
						<TD bgColor="#FFFFFF" class="blue2-12" colspan=6><input
							type="file" name="acc" size=60 class="blue2-12"></TD>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" colspan=7 align="left" class="message_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img
							src="<%=request.getContextPath()%>/images/reply.gif" border="0" style="cursor:hand"
							onClick="javascript:_reArticle('<%=request.getContextPath()%>')" />帖子长度：&nbsp;最大为：1500字，最小为0字&nbsp;</td>
					</tr>
				</table>
				<INPUT TYPE="hidden" name="topicId" value="<%=topicId%>"> <INPUT
					TYPE="hidden" name="topicId" value="<%=stopicId%>"> <INPUT
					TYPE="hidden" name="boardId" value="<%=boardId%>"> <INPUT
					TYPE="hidden" name="auditFlag" value="0"></td>
		</table>
		</td>
	</tr>
</table>
<FONT size=1>&nbsp;</FONT> <BR>
</form>
<%}%>


<SCRIPT src="<%=request.getContextPath()%>/images/bbs/addubb.js"></SCRIPT>

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
/*
var currentpos,timer;
function initialize() {
	timer = setInterval("scrollwindow()", 10);
}
function sc() {
	clearInterval(timer);
}
function scrollwindow() {
	currentpos=document.body.scrollTop;
	window.scroll(0,++currentpos);
	if (currentpos != document.body.scrollTop) sc();
}
document.onmousedown = sc;
document.ondblclick  = initialize;
function judge_size(){
    var height=document.all.avatar.height;
    var width=document.all.avatar.width;
    var b=height/width;
    if (height>width & height>100){
        document.all.avatar.height=100;
        document.all.avatar.width=100/b;
    }
    if (width>=height & width>100){
        document.all.avatar.width=100;
        document.all.avatar.height=100*b;
    }
}
*/

function copyText(obj) {var rng = document.body.createTextRange();rng.moveToElementText(obj);rng.select();rng.execCommand('Copy');}

  function openwindow(url1){
  var focusok;
  var newwin=window.open(url1,"newWin","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=0,left=0,width=500,height=400");
  
  	if(focusok){
  		newwin.focus();
  	}
  return false;
  }
  

//-->
</script>
</form>
</body>
</html>


<script language="JavaScript">
function _reArticle(url){

	document.form2.action=url+"/servlet/ReArticleServlet";
    document.form2.submit();
	
	
 }


function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}

function _openUserMsg(url){
window.open(url,"","height=240,width=700,scrollbar=no,status=no,location=no,toolbar=no,menubar=no");
}

function _searchArticle(url){
window.open(url,"","");
}

  function _editArticle(url,articleId,boardId,topicId){

	document.form1.action=url+"/servlet/ShowSingleArticleServlet?boardId="+boardId+"&articleId="+articleId+"&topicId="+topicId;
    document.form1.submit();
	
	
 }

 

    function _downLoad(url,articleId){

	
	form1.action=url+"/servlet/DownloadAccServlet?articleId="+articleId;
    form1.submit();
	
	
 }

 function _delArticle(url,articleId,boardId,topicId){

	
	document.form1.action=url+"/servlet/DelArticleServlet?boardId="+boardId+"&articleId="+articleId+"&topicId="+topicId+"&topicFlag=0";
    document.form1.submit();
	
	
 }
 function _delArticle2(url,articleId,boardId,topicId){

	
	document.form1.action=url+"/servlet/DelArticleServlet?boardId="+boardId+"&articleId="+articleId+"&topicId="+topicId+"&topicFlag=1&articleDelete=1";
    document.form1.submit();
	
	
 }
 function _topic(url,boardId){

	
	document.form1.action=url+"/servlet/ShowTopicServlet?_page_num=1&boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
	
	
 }

 function _showTopic1(url){
	boardId = document.form1.boardTopic1.value;
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }
 
  function _showTopic2(url){
	boardId = document.form1.boardTopic2.value;
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }
 
  function _reply(obj,articleId,url){
	document.form1.reContent.value = document.getElementById(articleId).innerHTML;
	document.form1.action = url;
	document.form1.submit();
 }
 
   function trimn(src){
      var obj = src.toString();
      var a = obj.substring(obj.length-1,obj.length);
      while(a=='\n'){
      	obj = obj.substring(0,obj.length-1);
      	a = obj.substring(obj.length-1,obj.length);
      }
      return obj;
   }
   
</script>

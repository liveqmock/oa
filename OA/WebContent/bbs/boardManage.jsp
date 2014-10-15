<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%

//System.out.println("comehere");
Collection scollection = (Collection)request.getAttribute("topicList");
//System.out.println("comehere11111");

//List noticeList = (List)request.getAttribute("noticeList");
//Iterator noticeItr = noticeList.iterator();
//System.out.println("comehere22222");

//BbsNoticeVO noticeVO = null;
//if(noticeItr.hasNext()){
//	noticeVO = (BbsNoticeVO)noticeItr.next();
//}
//System.out.println("comehere....");
List managerList = (List)request.getAttribute("managerList");
BbsBoardVO boardVO = (BbsBoardVO)request.getAttribute("boardVO");
String boardId = boardVO.getBoardid().toString();
//System.out.println("comehere!!!!!");

BbsUserinfoVO userVO = (BbsUserinfoVO)request.getAttribute("userVO");
List rightList =(List)request.getAttribute("rightList");
//System.out.println(boardVO);
List boardList = null;
Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("boardManage.jsp");
	

	BBSHandler handler = new BBSHandler(conn);
	boardList = handler.getBoardList();
	//System.out.println(boardList);
	Iterator Itr = boardList.iterator();

%>
<HTML><HEAD><TITLE>�»�����̳������Աҳ��</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.STYLE3 {
	color: #006699;
	font-size: 12px;
}

.STYLE4 {
	color: #006699;
	font-weight: bold;
	font-size: 12px;
}

.STYLE5 {
	color: #000066;
	font-weight: bold;
}

.STYLE6 {
	color: #000066;
	font-weight: bold;
	font-size: 12px;
}
</style>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id="homepagestyle"  rel="stylesheet" type="text/css" />
</head>

<body>
<form name="form1" method="post" action="">
<jsp:include page= "/include/top.jsp" />
<table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr><td height="5"></td></tr>
</table>
<table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td colspan="4" valign="top"></td>
	</tr>
    
    <tr>
		<td colspan="5">
			<table>
				<tr> 
					
				<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
				
				<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
				</tr>
			</table>
		</td>
	</tr>

	<tr>
		
		<td colspan="5" valign="top">
		<div align="left">
		<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#FFFFFF border=0>
			<tr>
				<td width="75%" bgColor=#FFFFFF class="blue3-12-b"><IMG title=����̳��ҳ
					style="CURSOR: hand"
					onClick="javascript:_gohome('<%=request.getContextPath()%>');"
					src="../images/bbshome.gif"><a style="text-decoration: none"
					href="<%=request.getContextPath()%>/servlet/ShowIndexServlet"
					title="����̳��ҳ">��̳��ҳ</a> ==&gt; <a href="#"
					class="STYLE4" style="text-decoration: none"><%=boardVO.getBoardname()%></a>
					  ==&gt; ����ҳ��
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!--<A  class="blue2-12" href="<%=request.getContextPath()%>/bbs/batchDelete.jsp?boardId=<%=boardId%>&boardName=<%=boardVO.getBoardname()%>">����ɾ��</a> &nbsp; &nbsp;</font></a>
			      -->
			      <A class="blue2-12"
		           href="#"onClick="javascript:_topic('<%=request.getContextPath()%>','<%=boardId%>')">�˳�����</A>
		           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           <A class="blue3-12-b"
		           href="<%=request.getContextPath()%>/servlet/ShowUnauditServlet">�������</A>
				</td>
				<td width="25%" align="right" nowrap bgColor=#FFFFFF class="blue2-12">
				�������:
            <%
			int boardManageFlag = 0 ;		 
			 Iterator managerIt = managerList.iterator();
		  ManagerUserinfoVO managerVO = null;
		while(managerIt.hasNext()){ 
	 managerVO = (ManagerUserinfoVO)managerIt.next();
	if(managerVO.getBoardid().equals(boardVO.getBoardid()))
	{
		boardManageFlag = 1;
		%>	  
					  <A title=�鿴����"<%=managerVO.getTruename()%>"������ 
      href="#"onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=managerVO.getUserid().toString()%>&currUserId=<%=userVO.getUserid()%>')"  class="text-01"><%=managerVO.getTruename()%></a>
							<%}
							}%>
		<%if(boardManageFlag == 0){%>
							����..
								<%}%>
				<!--<a href="#"
					class="blue3-12" style="cursor: hand; text-decoration: none">ȫ��</a>
				<a href="#" class="blue3-12"
					style="cursor: hand; text-decoration: none">����</a>&nbsp;&nbsp;--></td>
			</tr>
		</TABLE>
		</div>
		</td>
		
	</tr>
	<tr>
		
		<td colspan="5" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#B9DAF9">
			<tr>
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="white2-12-b" nowrap>״̬</td>
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="white2-12-b" nowrap>ͼ��</td>
				<td width="50%" height="24" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8">
				<div align="center" class="white2-12-b">����</div>
				</td>
				<!--<td width="5%" background="/OA/images/2-title-05.jpg" bgcolor="#E0EDF8">&nbsp;</td>-->
				<td width="12%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8">
				<div align="center">
				<div align="center" class="white2-12-b">���ⷢ����</div>
				</div>
				</td>
				<td width="10%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="white2-12-b">
				<div align="center">����״̬</div>
				</td>
				<td width="21%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="white2-12-b">
				<div align="center">��󷢱�</div>
				</td>
			</tr>
			<%if (scollection != null) {
	int showFlag = 1;
	Iterator Iterator = scollection.iterator();
	while (Iterator.hasNext()) {
		ArticleUserinfoVO vo = (ArticleUserinfoVO) Iterator.next();

		if (showFlag % 2 == 1) {%>
			<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
				<%} else {%>
			<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
				<%}%>
				<td height="22" class="blue3-12">
				<div align="center"><%if (vo.getTop().equals("1")) {%><IMG alt=�̶���
					src="<%=request.getContextPath()%>/images/bbs/locktop.gif"> <%} else if (vo.getArticlelock().equals("1")) {%><IMG
					alt=�����ѹر� src="<%=request.getContextPath()%>/images/bbs/lock.gif">
				<%} else if (vo.getHitnum().intValue() <= 30) {%> <IMG alt=��ͨ��
					src="<%=request.getContextPath()%>/images/bbs/closed.gif"> <%} else if (vo.getHitnum().intValue() > 30) {%><IMG
					alt=���� src="<%=request.getContextPath()%>/images/bbs/hotclosed.gif"><%}%></div>
				</td>
				<td class="blue3-12">
				<div align="center"><A href=# style="text-decoration: none"><IMG
					src="<%=request.getContextPath()%>/images/bbs/reArticle/<%=vo.getFace()%>"
					border=0></A></div>
				</td>
				<td class="blue3-12">
				<div align="left"><%if (vo.getPrime().equals("1")) {%> <img alt=����
					src="<%=request.getContextPath()%>/images/bbs/jh.gif"> <%}%> <A
					class="blue3-12" style="text-decoration: none"
					title="���⣺<%=vo.getArticlename()%>��&#13;&#10;�ۿ���<%=vo.getHitnum()%>��&#13;&#10;�ظ���<%=vo.getRenum()%>ƪ&#13;&#10;�����ˣ�<%=vo.getTurename()%>&#13;&#10;����ʱ�䣺<%=CommUtil.getTime(vo.getEmittime().longValue())%>&#13;&#10;���ظ���<%if (vo.getReuserid() != null) {%><%=vo.getReusername()%><%}%>	&#13;&#10;���ظ�ʱ�䣺<%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%> &#13;&#10;�������ݴ�С��122�ֽ�"
					href="#"
					onClick="javascript:_article('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>')"><%=vo.getArticlename()%>
				</A>				
				</div>
				<div align=right >| <a class="blue3-12" href="#"onclick="javascript:_setPrime('<%=request.getContextPath()%>','<%=boardId%>','<%=vo.getArticleid().toString()%>','<%=vo.getPrime()%>')" title="��������뾫������ȡ������">��</a>| 
                      <a class="blue3-12" href="#"onclick="javascript:_setTop('<%=request.getContextPath()%>','<%=boardId%>','<%=vo.getArticleid().toString()%>','<%=vo.getTop()%>')" title="�������ö�����ȡ���ö�">��</a>| 
                      <a class="blue3-12" href="#"onclick="javascript:_setLock('<%=request.getContextPath()%>','<%=boardId%>','<%=vo.getArticleid().toString()%>','<%=vo.getArticlelock()%>')" title="�������������ȡ������">��</a>| 
                      <a class="blue3-12" href="#"onclick="javascript:_delArticle('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>','<%=vo.getArticleid().toString()%>')" title="ɾ��������">ɾ</a>| 
					   <!--a href="#"onclick="javascript:_setView('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>','<%=vo.getIsview()%>')" title="�Ѹ�������Ϊ���ɼ���Ϊδ����״̬">��</a>|-->
                      <!--<a class="blue3-12" href="#"  onclick="javascript:_newNotice('<%=request.getContextPath()%>/bbs/notice.jsp?boardId=<%=boardId%>')" title="��������">��</a>|-->
                </div>				
				</td>
				<td class="blue3-12">
				<div align="center"><A class="blue3-12" href="#"
					style="text-decoration: none"
					onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=vo.getUserid()%>&currUserId=<%=userVO.getUserid()%>')"><%=vo.getTurename()%></A>
				</div>
				</td>
				<td class="blue3-12">
				<div align="center">ͨ������</div>
				</td>
				<td height="22">
				<div align="center" class="blue3-12"><%=CommUtil.getTime(vo.getEmittime().longValue())%></div>
				</td>
			</tr>

			<%}
//while		
} //if
%>
		</table>
		</td>
	</tr>
	
	<tr>
	<td colspan="5">
        <table>
            <tr> 
                
            <%@ include file= "/include/defaultPageScrollBar2.jsp" %>
            
            <td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
            </tr>
        </table>
	</td>
	</tr>
	
	
	
	<!--�޸�<//%@ include file= "/include/defaultPageScrollBar2.jsp" %>
	<td width="175" align="right" valign="bottom"-->
	<!--<a
		href="<//%=request.getContextPath()%>/bbs/newArticle.jsp?boardId=<//%=boardVO.getBoardid().toString()%>&boardName=<//%=boardVO.getBoardname()%>&auditFlag=0"><img
		src="../images/newtopic.gif" border="0"></a><img
		src="../images/newspecial.gif">-->
	<!--/td>
	<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
	-->



  </table>
  </form>
</body>

</html>
		<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("boardManage.jsp");
					}
			} catch (Exception e) {
		}
	}
%>

<script language="JavaScript">
function _goto(url){
	document.form1.action=url+"<%=request.getContextPath()%>/servlet/SearchServlet?boardId=<%=boardId%>&primeFlag=0&manageFlag=1";
	document.form1.submit();
}
function _search(url){
	document.form1.action=url+"<%=request.getContextPath()%>/servlet/SearchArticleServlet?boardId=<%=boardVO.getBoardid()%>&quickSearchFlag=1";
	document.form1.submit();
}

function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}

function _openUserMsg(url){
window.open(url,"","");
}

function _newNotice(url){
window.open(url,"","width=550,height=210,scrollbars=yes");
}

function _showTopic(url){

	boardId = boardTopic.value;
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
	
	
 }

function _showNotice(url){
window.open(url,"","");
}

function _article(url,topicId,boardId){

	
	document.form1.action=url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1";
    document.form1.submit();
	
	
 }


 function _setPrime(url,boardId,topicId,prime){

	
	document.form1.action=url+"/servlet/SetPrimeServlet?boardId="+boardId+"&topicId="+topicId+"&prime="+prime;
    document.form1.submit();
	
	
 }

   function _setTop(url,boardId,topicId,top){

	
	document.form1.action=url+"/servlet/SetTopServlet?boardId="+boardId+"&topicId="+topicId+"&top="+top;
    document.form1.submit();
	
	
 }

   function _setLock(url,boardId,topicId,lock){

	
	document.form1.action=url+"/servlet/SetLockServlet?boardId="+boardId+"&topicId="+topicId+"&lock="+lock;
    document.form1.submit();
	
	
 }

  function _setView(url,topicId,boardId,view){

	
	document.form1.action=url+"/servlet/SetViewServlet?boardId="+boardId+"&topicId="+topicId+"&view="+view;
    document.form1.submit();
	
	
 }
  function _delArticle(url,articleId,boardId,topicId){

	
	document.form1.action=url+"/servlet/DelArticleServlet?boardId="+boardId+"&articleId="+articleId+"&topicId="+topicId+"&topicFlag=1";
    document.form1.submit();
	
	
 }

 function _primeTopic(url,boardId){

	
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=1";
    document.form1.submit();
	
	
 }

function _topic(url,boardId){

	
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
	
	
 }

function _audit(url,boardId){

	
	document.form1.action=url+"/servlet/ShowUnauditServlet?boardId="+boardId;
    document.form1.submit();
	
	
 }

</script>
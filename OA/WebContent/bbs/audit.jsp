<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSBoardHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%
Collection scollection = (Collection)request.getAttribute("topicList");
BbsUserinfoVO userVO = (BbsUserinfoVO)request.getAttribute("userVO");
Connection conn = null;
	try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("audit.jsp");
	BBSBoardHandler handler = new BBSBoardHandler(conn);
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
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr><td height="5"></td></tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td colspan="5" valign="top"></td>
	<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
	<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
				<td td colspan="5" bgColor=#FFFFFF class="blue3-12-b"><IMG title=����̳��ҳ
					style="CURSOR: hand"
					onClick="javascript:_gohome('/oabase');"
					src="../images/bbshome.gif"><a style="text-decoration: none"
					href="/oabase/servlet/ShowIndexServlet"
					title="����̳��ҳ">��̳��ҳ</a>
					  ==&gt; ���ҳ��
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a style="text-decoration: none"
					href="/oabase/servlet/ViewDelArticleServlet" class="message_title"
					title="ɾ�������б�"><b>ɾ�������б�</b></a>

					</td>
			<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
			</tr>
	<tr>
		<td width="11" bgcolor="#FFFFFF"><img src="../images/kongbai.jpg"
			width="11" height="11" /></td>
		<td colspan="5" valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#B9DAF9">
			<tr bgcolor="#E0EDF8">
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					 class="white2-12-b" nowrap >ȫѡ<input type="checkbox" name="checkall"
										onclick="checkAll(this,'topicId')" </td>
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					 class="white2-12-b" nowrap>ͼ��</td>
				<td width="40%" height="24" background="../images/2-title-05.jpg"
					>
				<div align="center" class="white2-12-b">����</div>
				</td>
				<td width="15%" height="24" background="../images/2-title-05.jpg"
					 align="center" class="white2-12-b">���ڰ��
				</td>
				<td width="10%" background="../images/2-title-05.jpg"
					>
				<div align="center" class="white2-12-b">���ⷢ����</div>
				</td>
				<td width="10%" background="../images/2-title-05.jpg"
					 class="white2-12-b">
				<div align="center">����״̬</div>
				</td>
				<td width="15%" background="../images/2-title-05.jpg"
					 class="white2-12-b">
				<div align="center">����ʱ��</div>
				</td>
			</tr>
			<%if (scollection != null) {
				int showFlag = 1;
				Iterator Iterator = scollection.iterator();
				while (Iterator.hasNext()) {
				ArticleUserinfoVO vo = (ArticleUserinfoVO) Iterator.next();
				Integer boardId = vo.getBoardid();
				if (showFlag % 2 == 1) {%>
					<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
					onMouseOver="this.bgColor='#E0EDF8';">
					<%} else {%>
				<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
				<%}%>
				<td height="22" class="blue3-12">
				<div align="center">
				<input type="checkbox" name="topicId" value="<%=vo.getArticleid()%>">
				</td>
				<td class="blue3-12">
				<div align="center"><A href=# style="text-decoration: none"><IMG
					src="<%=request.getContextPath()%>/images/bbs/reArticle/<%=vo.getFace()%>"
					border=0></A></div>
				</td>
				<td class="blue3-12">
				<div align="left">
				<%if (vo.getPrime().equals("1")) {%> <img alt=����
					src="<%=request.getContextPath()%>/images/bbs/jh.gif"> <%}%> 
					<A	class="blue3-12" style="text-decoration: none"
					title="���⣺<%=vo.getArticlename()%>��&#13;&#10;�ۿ���<%=vo.getHitnum()%>��&#13;&#10;�ظ���<%=vo.getRenum()%>ƪ&#13;&#10;�����ˣ�<%=vo.getTurename()%>&#13;&#10;����ʱ�䣺<%=CommUtil.getTime(vo.getEmittime().longValue())%>&#13;&#10;���ظ���<%if (vo.getReuserid() != null) {%><%=vo.getReusername()%><%}%>	&#13;&#10;���ظ�ʱ�䣺<%if (vo.getRetime() != null) {
					out.print(CommUtil.getTime(vo.getRetime().longValue()));
					}%>"
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
				<td class="blue3-12" align='center'>
				<%=handler.getBoardName(boardId)%>
				</td>
				<td class="blue3-12">
				<div align="center" onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUserid()%>','������Ϣ','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')" style="cursor:hand;">
					<%=vo.getTurename()%>
				</div>
				</td>
				<td class="blue3-12">
				<div align="center">δ���</div>
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
		<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
		</tr>
		<tr>
		<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
		<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
		</tr>
		<tr>
		<td height='20'>
		</td>
		<td >
			<div align='center'>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="�� �� ͨ ��"
										onclick="javacript:_audit()" />
										</div>	</td>
		</tr>
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
					ConnLog.close("audit.jsp");
					}
			} catch (Exception e) {
		}
	}
%>

<script language="JavaScript">

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

	
	document.form1.action=url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&isview=0";
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

	
	document.form1.action=url+"/servlet/DelArticleServlet?boardId="+boardId+"&articleId="+articleId+"&topicId="+topicId+"&topicFlag=1&auditFlag=1";
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

 function _audit(){
 	
 		if(submitcheck()){
		document.form1.action="<%=request.getContextPath()%>/servlet/SetAuditServlet";
    document.form1.submit();
    }else{
          alert('��ѡ������������');
     }
	
 }
 
 function submitcheck()
{
		  var aa = document.getElementsByName("topicId");
		  var result=false;
			for (var i=0; i<aa.length; i++)
			{
                 if(aa[i].checked){
                 result=true;
				 }
			} 
			return result;

	}
	
	function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}

</script>
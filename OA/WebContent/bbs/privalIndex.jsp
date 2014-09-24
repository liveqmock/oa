<%@ page contentType="text/html; charset=GBK" %>
<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
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

List list = (List) request.getAttribute("topicList");
int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
int tiao1  = com.icss.j2ee.util.PageScrollUtil.getRowCount();
String dpt = (String) request.getAttribute("dpt");
String orgname = (String) request.getAttribute("orgname");


String firstLogin = (String) request.getAttribute("firstLogin");
BbsUserinfoVO userVO = (BbsUserinfoVO) request.getAttribute("userVO");

Collection bcollection = (Collection) request.getAttribute("boardList");
Collection sbcollection = (Collection) request.getAttribute("subareaList");
//System.out.println("----------area size "+sbcollection.size());
List wholerightList = (List) request.getAttribute("rightList");
List wholemanagerList = (List) request.getAttribute("managerList");

List areaRightList = (List) request.getAttribute("areaRightList");
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("privalIndex.jsp");

	BBSHandler handler = new BBSHandler(conn);
%>

<HTML>
<HEAD>
<TITLE>�»�����̳</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>

<link href="/Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />
	<SCRIPT language=JavaScript
			src="<%=request.getContextPath()%>/include/counter.js">
	</SCRIPT>

</head>

<body>
<form name="form1" method="post" action="">
<%@ include file= "/include/top.jsp" %>

<table width="1003" border="0" cellspacing="0" cellpadding="0"
	align="center">

	<tr>
		<td bgcolor="#FFFFFF" height="5"></td>
		<td colspan="5" valign="top"></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td colspan="5" valign="top">
		<table width="983" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
	  <tr>
				<td width="4%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">&nbsp;</td>
				<td width="38%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">
				<div align="center" class="white2-12-b">������</div>
				</div>
				</td>
			  <td width="6%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">����</td>
			  <td width="7%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">������</td>
			  <td width="13%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">��󷢱�</td>
			  <td width="32%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">����</div>
				</td>
		  </tr>
			<%
            
            boolean areaf=true;
            String area_name = "";
Iterator AreaIterator = sbcollection.iterator();
while (AreaIterator.hasNext()) {
    
    areaf=true;
	BbsAreaVO areaVO = (BbsAreaVO) AreaIterator.next();
	String areaRightString ="";
	if("1".equals(areaVO.getArearight())){
		for(int n=0;n<areaRightList.size();n++){
			BbsAreaccVO areaccvo = (BbsAreaccVO)areaRightList.get(n);
			areaRightString += areaccvo.getUserid()+"||";
		}		
        }

        Iterator BoardIterator = bcollection.iterator();
	//System.out.println("areaRightString=:"+areaRightString);
	//System.out.println("userVO.getUserid().toString()=:"+userVO.getUserid().toString());
	if((!"1".equals(areaVO.getArearight())||areaRightString.indexOf(userVO.getUserid().toString())>=0)&&BoardIterator.hasNext()){
	%>


			<%
while (BoardIterator.hasNext()) {
	BbsBoardVO vo = (BbsBoardVO) BoardIterator.next();
    //System.out.println("###########"+vo.getBoardname());
    String rightflag = "";
	if (vo.getAreaid().toString().equals(areaVO.getAreaid().toString()))
		//��ר����������
		{
		if (wholerightList.size() == 0) { //��ǰ��¼���˻����δ������
			if (vo.getPermit().equals("1")) {
				rightflag = "0"; //��ǰ��¼���˻���鲻�ɽ�
			} //if
			else {
				rightflag = "1"; //��ǰ��¼���˻����ɽ�
			} //else
		} else {
			if (vo.getIslimited().equals("0")) { //��Ȩ�����ƣ�δѡ���˻���飩
				if (vo.getPermit().equals("1")) {
					rightflag = "0"; //��ǰ��¼���˻���鲻�ɽ�
				} //if
				else {
					rightflag = "1"; //��ǰ��¼���˻����ɽ�
				} //else
			} else { //ѡ�����˻����&&��ǰ��¼���˻����������
				for (int i = 0;
					i < wholerightList.size();
					i++) { //rightList ��ŵ�ǰ��¼�û���BoardAcc����صļ�¼
					BbsBoardaccVO bvo = (BbsBoardaccVO) wholerightList.get(i);
					if (vo.getBoardid().equals(bvo.getBoardid())) {
						//��ǰ�û������ް��ID:bvo.getBoardid()
						if (vo.getPermit().equals("1")) {
							rightflag = "1"; //��ǰ��¼�߿��Խ���
						} //if
						else {
							rightflag = "0";
						} //else
						break; //һ������һ���û�ֻ����һ�����ƣ������ҵ���ȵĺ��break
					} //if
					else {
						if (vo.getPermit().equals("1")) {
							rightflag = "0"; //��ǰ��¼�߿��Խ���
						} //if
						else {
							rightflag = "1";
						} //else
					}
				} //for
			} //else
		} //else
        %>
        <%if (rightflag.equals("1")) {%>
        <%if(areaf){ areaf=false;%>
        	<tr>
				<td colspan="6" class="message_title_bold" height="20">&nbsp;&nbsp;&nbsp;<%=areaVO.getAreaname()%></td>
            </tr>
            <%}%>   

			<tr>
				<td width="40" height="20" align="center" nowrap bgcolor="#FFFFFF">
				<img src="<%=request.getContextPath()%>/images/bbs/forumnew.gif"
					border=0>  </td>
				<td bgcolor="#FFFFFF" align="center" height="20">
				<table width="98%">
					<tr>
						<td bgcolor="#FFFFFF" class="message_title_bold"><%if (rightflag.equals("1")) {%>
						<A class="message_title_bold" style="text-decoration: none"
							title="����<%=vo.getBoardname()%>���" href="#"
							onClick="javascript:_topic('<%=request.getContextPath()%>','<%=vo.getBoardid().toString()%>')"><%=vo.getBoardname()%></A>
						<%} %> </td>
						<%if (rightflag.equals("1")) {%>
						<td align="right" bgcolor="#FFFFFF" class="message_title"><A
							title="�ڡ�<%=vo.getBoardname()%>����̳�ڷ������� "
							href="<%=request.getContextPath()%>/bbs/newArticle.jsp?boardId=<%=vo.getBoardid().toString()%>&boardName=<%=vo.getBoardname()%>&auditFlag=0"><IMG
							src="<%=request.getContextPath()%>/images/bbs/quickpost.gif"
							border=0></A><A href="#" style="text-decoration: none"
							onClick="javascript:_primeTopic('<%=request.getContextPath()%>','<%=vo.getBoardid().toString()%>')">
						<IMG alt="�鿴��<%=vo.getBoardname()%>����̳������"
							src="<%=request.getContextPath()%>/images/bbs/quickjh.gif"
							border=0></A></td>
						<%} %>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" class="message_title"> <%if (vo.getBoarddes() != null) {%>
						<%=vo.getBoarddes()%> <%}%></td>
					</tr>
				</table>
				</td>
				<td bgcolor="#FFFFFF" class="message_title" align="center"><span
					class="STYLE3"><%=vo.getTopicnum()%></span></td>
				<td bgcolor="#FFFFFF" class="message_title" align="center"><span
					class="STYLE3"><%=vo.getArticlenum()%></span></td>
				<%
					String lastname = vo.getLastusername();					
					//String lasttime = CommUtil.getTime(vo.getLasttime().longValue());
					if(lastname==null){
					%>
						<td align="center" bgcolor="#FFFFFF" class="message_title">��<br></td>
					<%
					}
					else{%>
				<td align="center" bgcolor="#FFFFFF" class="message_title"><span
					class="STYLE3"><%=CommUtil.getTime(vo.getLasttime().longValue())%><BR>
				by 
					<!-- <span onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getLastid()%>','������Ϣ','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')" title="�鿴�û���<%=vo.getLastusername()%>��������" style="cursor:hand;" class="message_title">
					 -->
					 <span>
					<%=vo.getLastusername()%>
				</span></span></td><%}%>
				<td align="center" bgcolor="#FFFFFF" class="message_title">
					<span>OAϵͳ����Ա</span>
					
					<%Iterator managerIterator = wholemanagerList.iterator();
ManagerUserinfoVO managerVO = null;
String ManagerIds = "";
while (managerIterator.hasNext()) {
	managerVO = (ManagerUserinfoVO) managerIterator.next();
	if (managerVO.getBoardid().equals(vo.getBoardid())) {
		ManagerIds += managerVO.getUserid()+"||";
}
}%>
<%if(ManagerIds.indexOf(userVO.getUserid())>=0){%>

	<div align="center" >
	<span >
		<a style="cursor:hand" title=���ӹ��� 
					class="message_title_bold" 
					onClick="javascript:window.location.href('<%=request.getContextPath()%>/servlet/ShowTopicServlet?boardId=<%=vo.getBoardid().toString()%>&primeFlag=0&manageFlag=1')">
				���ӹ���
		</a>
	</span>
	&nbsp;
		<span ><a style="text-decoration: none" title=�鿴�˰������ href="#"
					class="message_title_bold"
					onClick="javascript:_openBoard('<%=request.getContextPath()%>/servlet/ShowSingleBoardServlet?boardId=<%=vo.getBoardid().toString()%>')">
				�������
		</a>
		</span>
		
	</div>
				
				
				<%}%>
</td>
            </tr>
            
            <%}}
}}
}


%>


	<%if ("yes".equals(dpt)) {%>
	<tr>
		<td  colspan="10" height="20" align="left" class="message_title">
		&nbsp;&nbsp;<img src="../images/users.png" border=0/>
		<a href='<%=request.getContextPath()%>/servlet/ShowDeptBBSServlet' ><B>���롺<%=orgname%>��������̳</B></a>
	</td>
	</tr>
	<%}%>
		</table>
	  </td>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td width="11" bgcolor="#FFFFFF"><img src="../images/kongbai.jpg"
			width="11" height="11" /></td>
		<td colspan="5" valign="top">
		<div align="left"></div>
		</td>
		<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
		<td width="175" align="right" valign="bottom"></td>
		<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
	<tr>
		<td width="11" bgcolor="#FFFFFF"><img src="../images/kongbai.jpg"
			width="11" height="11" /></td>
		<td colspan="5" valign="top">
		<div align="left">
		<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#ffffff
			border=0>
			<tr>
				<td width="100%" bgColor=#ffffff class="message_title-b">
				<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#fffff border=0>
				   <tr>
             <td width="50%" align="left">
							<IMG title=����̳��ҳ
							style="CURSOR: hand" src="../images/bbshome.gif"> <a
							href="<%=request.getContextPath()%>/servlet/ShowIndexServlet"
							title="����̳��ҳ" class="message_title_bold"
							style="text-decoration: none">��̳��ҳ</a>
					   </td>
					   <td width="50%" align="right">
							<input name="namesearch" type="text" style="width:100px"/>&nbsp;&nbsp;<a onclick="javascript:_search('<%=request.getContextPath()%>')" style="CURSOR: hand;text-decoration: none;font-size:10pt">��������</a>&nbsp;&nbsp;&nbsp;&nbsp;
					   </td>
				   </tr>
				<TABLE>
			    </td>
		  </tr>
		</TABLE>
		</div>
		</td>
		<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>

	<tr>
		<td colspan="5" valign="top">
		<table width="983" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
		</td>
	  <tr>
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title" nowrap>״̬</td>
			  <td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title" nowrap>ͼ��</td>
			  <td width="39%" height="24" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center" class="white2-12-b">����</div>
				</td>
		    <!--<td width="5%" background="/OA/images/2-title-05.jpg" bgcolor="#E0EDF8">&nbsp;</td>-->
				<td width="12%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">
				<div align="center" class="white2-12-b">���ⷢ����</div>
				</div>
				</td>
			  <td width="9%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">���</div>
				</td>
			  <td width="9%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">�ظ�</div>
				</td>
			  <td width="21%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">��󷢱�</div>
				</td>
		  </tr>
			<%
			
			
			if(list != null&&list.size()>0){
int showFlag = 1;
Iterator Iterator = list.iterator();
while(Iterator.hasNext()){
	BbsArticleBbsBoardaccVO vo = (BbsArticleBbsBoardaccVO)Iterator.next();

		if (showFlag % 2 == 1) {%>
			<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
				<%} else {%>
			<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
				<%}%>
				<td height="22" class="message_title">
				<div align="center"><%if (vo.getTop().equals("1")) {%><IMG alt=�̶���
					src="<%=request.getContextPath()%>/images/bbs/locktop.gif"> <%} else if (vo.getArticlelock().equals("1")) {%><IMG
					alt=�����ѹر� src="<%=request.getContextPath()%>/images/bbs/lock.gif">
				<%} else if (vo.getHitnum().intValue() <= 30) {%> <IMG alt=��ͨ��
					src="<%=request.getContextPath()%>/images/bbs/closed.gif"> <%} else if (vo.getHitnum().intValue() > 30) {%><IMG
					alt=���� src="<%=request.getContextPath()%>/images/bbs/hotclosed.gif"><%}%></div>
				</td>
				<td class="message_title">
				<div align="center"><A href=# style="text-decoration: none"><IMG
					src="<%=request.getContextPath()%>/images/bbs/reArticle/<%=vo.getFace()%>"
					border=0></A></div>
				</td>
				<td class="message_title">
				<div align="left"><%if (vo.getPrime().equals("1")) {%> <img alt=����
					src="<%=request.getContextPath()%>/images/bbs/jh.gif"> <%}%> <A
					class="message_title" style="text-decoration: none"
					title="���⣺<%=vo.getArticlename()%>��&#13;&#10;�ۿ���<%=vo.getHitnum()%>��&#13;&#10;�ظ���<%=vo.getRenum()%>ƪ&#13;&#10;�����ˣ�&#13;&#10;����ʱ�䣺<%=CommUtil.getTime(vo.getEmittime().longValue())%>&#13;&#10;���ظ���<%if (vo.getReuserid() != null) {%><%=vo.getReusername()%><%}%>	&#13;&#10;���ظ�ʱ�䣺<%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%> &#13;&#10;�������ݴ�С��122�ֽ�"
					href="#"
					onClick="javascript:_article('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=vo.getBoardid().toString()%>')"><%=vo.getArticlename()%>
				</A></div>
				</td>
				<td class="message_title">
				<div align="center">
					<!-- <span onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getPersonuuid()%>','������Ϣ','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')" style="cursor:hand;" class="message_title">
					 -->
					 <span>
					<%=handler.getUserName(vo.getPersonuuid())%></span>
				</div>
				</td>
				<td class="message_title">
				<div align="center"><%=vo.getHitnum()%></div>
				</td>
				<td class="message_title">
				<div align="center"><%=vo.getRenum()%></div>
				</td>
				<td height="22">
				<div align="center" class="message_title"><%if (vo.getReuserid() != null) {%>
				<!-- 
				<span onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getReuserid()%>','������Ϣ','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')" style="cursor:hand;" class="message_title">
				 -->
				 <span>
					<%=vo.getReusername()%></span>|<%}%>
				<%if (vo.getRetime() != null) {
	%><%=CommUtil.getTime(vo.getRetime().longValue())%><%
}%></div>
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
</table>

		<SCRIPT LANGUAGE="JavaScript">
			_counter('��̳');
		</SCRIPT>
</body>

</html>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("privalIndex.jsp");
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
 function _openBoard(url){
    window.open(url,"","width=550,height=350,left=170,top=110,scrollbars=yes");
 }
function _showTopic(url){

	boardId = boardTopic.value;
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }
function _topic(url,boardId){
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();	
}

function _article(url,topicId,boardId){
	document.form1.action=url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1";
    document.form1.submit();
 }
 
 function _applyBoard(url){
 	//��һ��������ʾΪ������
 	window.open(url+"/bbs/newBoard.jsp?isApply=1","applyboard","width=550,height=350,left=170,top=110,scrollbars=yes");
 
 }
  function _primeTopic(url,boardId){
    window.location.href = url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=1";
 }
  function _search(url){
	var name = document.getElementsByName("namesearch")[0].value;
	window.location.href = url+"/servlet/ShowIndexServlet?name="+name;
  }
</script>

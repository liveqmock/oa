<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.oa.bbs.vo.BbsArticleDelVO" %>
<%@ page import="com.icss.oa.bbs.vo.BbsBoardVO" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			List alist = request.getAttribute("alist")!=null ?(List) request.getAttribute("alist"):new ArrayList();
			Connection conn = null;
			try {
				conn = DBConnectionLocator.getInstance().getConnection(
						"jdbc/OABASE");
				BBSHandler handler = new BBSHandler(conn);
		%>

		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>删除帖子列表</title>
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			id=homepagestyle rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.anylinkcss {
	position: absolute;
	visibility: hidden;
	z-index: 100;
}
//
-->
</style>
		<script language="javascript">

function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}

function checkItem(e, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
	{
     if(!aa[i].checked) 
	  {
		all.checked = false;
		return;
	  }
  }
    all.checked = true;
  }
}
function submitcheck()
{
		  var aa = document.getElementsByName("syncorg");
		  var result=false;
			for (var i=0; i<aa.length; i++)
			{
                 if(aa[i].checked){
                 result=true;
				 }
			} 
			return result;

	}
	
function  doAction(url)   
{  
          frm.action=url;  
          frm.submit();   
 }
 
 function  doAction1(url)   
{  
	if(submitcheck()){
		 frm.action=url;  
          frm.submit();   
         }else{
          alert('请选择审批的内容');
          }
 }

 function viewContent(id){
	 window.open("/oabase/bbs/delArticleContent.jsp?id="+id,"","toolbar=no,width=400,height=300,left="+(screen.width-500)/2+",top="+(screen.height-400)/2);
	 //window.open("/oabase/bbs/delArticleContent.jsp?id="+id);
 }


</script>

	</head>

	<body>
		<jsp:include page="/include/top.jsp" />

		<form name="frm" method="post" action="" target="_blank">
		<input type="hidden" id="content" name="content" value=""/>
			<table width="100%">
				<tr>
					<td width="5%"></td>
					<td width="90%">
					<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="5"></td>
							</tr>
						</table>

						<table border="0" cellpadding="0" cellspacing="1"
							class="table_bgcolor" width="100%">
							<tr>
								<td width="10%" align="center" class="block_title">
									删除时间
								</td>
								<td width="10%" align="center" class="block_title">
									删除人员
								</td>
								<td width="10%" align="center" class="block_title">
									发帖人
								</td>

								<td width="15%" align="center" class="block_title">
									所属论坛分区
								</td>

								<td width="10%" align="center" class="block_title">
									发帖时间
								</td>
								<td width="45%" align="center" class="block_title">
									帖子题目
								</td>
							</tr>
							<%
									int i = 0;
									for (Iterator it = alist.iterator();it.hasNext();) {
										BbsArticleDelVO vo = (BbsArticleDelVO) it.next();
										i++;
										String color = "#eff5e7";
										if (i % 2 == 1)
											color = "#FFFFFF";
									BbsBoardVO bvo = handler.getBoardVO(vo.getBoardid());
									
							%>
							<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
								onMouseOver="this.bgColor='#e2f2cd';">
								<td align="center" class="message_title">
									<b><%=vo.getDeltime()%></b>
								</td>
								<td align="center" class="message_title"><%=handler.getUserName(vo.getDelpersonuuid())%>
								</td>
								<td align="center" class="message_title"><%=handler.getUserName(vo.getUserid())%>
								</td>

								<td align="center" class="message_title"><%=bvo.getBoardname()%>
								</td>

								<td align="center" class="message_title"><%=CommUtil.getTime(vo.getEmittime().longValue())%></td>
								<td align="center" class="message_title"><span style="cursor:hand" onclick="viewContent('<%=vo.getArticleid()%>');"><%=vo.getArticlename()%> </span></td>
							</tr>


							<%
								}//end
							%>

							<tr>
								<td colspan="12" height="20"><%@ include
										file="/include/defaultPageScrollBar.jsp"%></td>
							</tr>

						</table>
					</td>
					<td width="5%"></td>
				</tr>
			</table>
			<br />
			<br />

		</form>

	</body>

</html>

<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();

	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}
%>

<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%@ page import="com.icss.oa.sync.vo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.oa.sync.handler.OrgSyncHandler"%>
<%@ page import="com.icss.common.log.ConnLog"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			List orglist = (List) request.getAttribute("orglist");

			Connection conn = null;
			try {
				conn = DBConnectionLocator.getInstance().getConnection(
						"jdbc/ROEEE");

				OrgSyncHandler handler = new OrgSyncHandler(conn);
		%>

		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>����ͬ����֯</title>

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
          alert('��ѡ������������');
          }
 }



</script>

	</head>

	<body>
		<jsp:include page="/include/top.jsp" />




		<form name="frm" method="post" action="">

			<table width="100%">
				<tr>
					<td width="5%"></td>
					<td width="90%">

						<!--����������-->
						<table border="0" cellpadding="0" cellspacing="1" width="100%"
							class="table_bgcolor">
							<tr class="block_title">
								<td colspan="10" align=center>
									��������
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" class="table_title" align=right>
									��������
								</td>
								<td bgcolor="#FFFFFF">
									<select name="type" id="type">
										<option value="all">
											����
										</option>
										<option value="new">
											��������
										</option>
										<option value="upd">
											��Ϣ���
										</option>
										<option value="del">
											����ɾ��
										</option>
										<option value="unite">
											�����ϲ�
										</option>
										<option value="transit">
											������ת
										</option>
									</select>
								</td>

								<td bgcolor="#FFFFFF" class="table_title" align=right>
									�������
								</td>
								<td bgcolor="#FFFFFF" class="message_title_bold">
									<select name="pass" id="pass">
										<option value="2">
											����
										</option>
										<option value="1">
											ͨ��
										</option>
										<option value="-1">
											δͨ��
										</option>
										<option value="0" selected>
											δ����
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" class="table_title"></td>
								<td bgcolor="#FFFFFF" class="table_title" colspan="4">
									<input type="button" name="search" value="��ѯ"
										onclick="doAction('/oabase/servlet/GetSyncOrgServlet')" />
								</td>
							</tr>
						</table>
						<!--����������-->

						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="5"></td>
							</tr>
						</table>

						<!--�����û��б�-->
						<table border="0" cellpadding="0" cellspacing="1"
							class="table_bgcolor" width="100%">

							<tr>
								<td width="8%" align="left" class="block_title">
									ȫѡ
									<input type="checkbox" name="syncorgall"
										onclick="checkAll(this,'syncorg')" />
								</td>
								<td width="10%" align="center" class="block_title">
									��֯����
								</td>
								<td width="10%" align="center" class="block_title">
									��֯CODE
								</td>
								<td width="15%" align="center" class="block_title">
									�ϲ���֯
								</td>

								<td width="10%" align="center" class="block_title">
									ʱ��
								</td>

								<td width="10%" align="center" class="block_title">
									��������
								</td>
								<td width="10%" align="center" class="block_title">
									�����ֶ�
								</td>
								<td width="10%" align="center" class="block_title">
									����ǰ
								</td>
								<td width="10%" align="center" class="block_title">
									���ĺ�
								</td>
								<td width="10%" align="center" class="block_title">
									״̬
								</td>
							</tr>
							<%
								Iterator it = orglist.iterator();
									int i = 0;
									while (it.hasNext()) {
										OrgTempVO vo = (OrgTempVO) it.next();
										i++;
										String color = "#eff5e7";
										if (i % 2 == 1)
											color = "#FFFFFF";
										String parentorgname = handler.getOrgName(vo
												.getParentorgid());
							%>
							<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
								onMouseOver="this.bgColor='#e2f2cd';">
								<td align="center" class="message_title">
									<%
										if (vo.getPass().intValue() == 0) {
									%>
									<input type="checkbox" name="syncorg" value=<%=vo.getId()%>
										onclick="checkItem(this,'syncorgall')" />
									<%
										}
									%>
								</td>
								<td align="center" class="message_title">
									<b><%=vo.getOrgname()%></b>
								</td>
								<td align="center" class="message_title"><%=StringUtil.escapeNull(vo.getOrgcode())%>
								</td>
								<td align="center" class="message_title"><%=parentorgname%>
								</td>

								<td align="center" class="message_title"><%=StringUtil.escapeNull(vo.getTime())%>
								</td>

								<td align="center" class="message_title"><%=vo.getBusiness()%></td>
								<td align="center" class="message_title"><%=StringUtil.escapeNull(vo.getUpdcontent())%></td>
								<td align="center" class="message_title"><%=StringUtil.escapeNull(vo.getOldmsg())%></td>
								<td align="center" class="message_title"><%=StringUtil.escapeNull(vo.getNewmsg())%></td>
								<%int p = vo.getPass().intValue();
								String pass="";
								if(p==0) pass="δ����";
								if(p==1) pass="ͨ��";
								if(p==-1) pass="δͨ��";%>
								<td align="center" class="message_title"><%=pass%></td>

							</tr>


							<%
								}//end
							%>

							<tr>
								<td colspan="12" height="20"><%@ include
										file="/include/defaultPageScrollBar.jsp"%></td>
							</tr>

							<tr>
								<td colspan='12' align='center'>
									<input type="button" value="����ͨ��"
										onclick="doAction1('/oabase/servlet/OrgPassServlet')" />
									&nbsp; &nbsp; &nbsp; &nbsp;

									<input type="button" value="������ͨ��"
										onclick="doAction1('/oabase/servlet/OrgPassServlet?type=no')" />
								</td>
							</tr>

						</table>
						<!--�����û��б�-->

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

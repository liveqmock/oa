<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.sync.vo.SynUserErrVO"%>
<%@ page import="com.icss.oa.sync.handler.UserHandler"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>

<%
	List userList = request.getAttribute("userlist") == null ? new ArrayList()
			: (List) request.getAttribute("userlist");
%>

<html>
	<head>
		<title>��Աͬ�������¼</title>
		<link href="<%=request.getContextPath()%>/Style/css.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			id="homepagestyle" rel="stylesheet" type="text/css" />
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>


	</head>
	<BODY text="#000000" leftMargin="0" topMargin="10">
		<jsp:include page="/include/top.jsp" />
		<br/>
		<table width="980" border="0" cellpadding="0" cellspacing="1"
			class="table_bgcolor" align="center">
			<tr align="center">
				<td width="10%" height="24" class="block_title">
					����
				</td>
				<td width="20%" class="block_title">
					����
				</td>
				<td width="20%" class="block_title">
					����
				</td>
				<td width="10%" class="block_title">
					��������
				</td>
				<td width="10%" class="block_title">
					����ֶ�
				</td>
				<td width="15%" class="block_title">
					�������
				</td>
				<td width="15%" class="block_title">
					���
				</td>
			</tr>
			<%
				Iterator it = userList.iterator();
				int i = 0;
				while (it.hasNext()) {
					SynUserErrVO vo = (SynUserErrVO) it.next();
					i++;
					String color = "#eff5e7";
					if (i % 2 == 1)
						color = "#FFFFFF";
					String rs = vo.getResult();

					String result = "";

					if ("101".equalsIgnoreCase(rs)) {
						result = "����ʧ�ܣ������Ѵ���";
					} else if ("102".equalsIgnoreCase(rs)) {
						result = "����ʧ�ܣ��޸���֯";
					} else if ("109".equalsIgnoreCase(rs)) {
						result = "����ʧ�ܣ�����ԭ��";
					} else if ("201".equalsIgnoreCase(rs)) {
						result = "����ʧ�ܣ�����������";
					} else if ("202".equalsIgnoreCase(rs)) {
						result = "����ʧ�ܣ��޸���֯";
					} else if ("209".equalsIgnoreCase(rs)) {
						result = "����ʧ�ܣ�����ԭ��";
					} else if ("301".equalsIgnoreCase(rs)) {
						result = "ɾ��ʧ�ܣ�����������";
					} else if ("309".equalsIgnoreCase(rs)) {
						result = "ɾ��ʧ�ܣ�����ԭ��";
					}
			%>
			<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
				onMouseOver="this.bgColor='#e2f2cd';">
				<td class="message_title" height="22">
					<%=vo.getPname()%>
				</td>
				<td class="message_title">
					<%=UserHandler.getOrgName(vo.getDeptid())%>
				</td>
				<td class="message_title">
					<%=vo.getTime()%>
				</td>
				<td class="message_title">
					<%=vo.getOperatetype()%>
				</td>
				<td class="message_title">
					<%=StringUtil.escapeNull(vo.getUpdcontent())%>
				</td>
				<td class="message_title">
					<%=StringUtil.escapeNull(vo.getNewmsg())%>
				</td>
				<td class="message_title">
					<%=result%>
				</td>
			</tr>
			<%
				}
			%>
			
			 <tr>
                	<td colspan="12" height="20"><%@ include file= "/include/defaultPageScrollBar.jsp" %></td>
              </tr>
		</table>
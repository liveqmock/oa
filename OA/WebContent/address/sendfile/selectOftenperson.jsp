<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<!--0413�ӵ�ַ����ѡ����Ա���͡��͵绰����-->
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>
<jsp:useBean id="handler1" class="com.icss.oa.address.handler.SysOrgpersonHandler"/>
<!--0413�ӵ�ַ����ѡ����Ա���͡��͵绰����-->
<HTML>
<HEAD>
<TITLE>������ϵ���б�</TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>
</HEAD>

<BODY  bgcolor="#eff7ff" onload="window.top.moveFrame()">
<%
String isperson = "";

List personlist = request.getAttribute("personlist")==null?new ArrayList():(List)request.getAttribute("personlist");
//���û��ȡ����֯�б��е���Ա��Ϣ����ȥȡ����Ա�ķ�����Ϣ
if (!personlist.isEmpty()){
	isperson = "1";
%>
<form name="personform" method="post" action="" target="listFrame">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">������ϵ��</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>

          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			<table width=100%  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
                        <td width="10%" ><div align="center" class="title-04">ѡ��</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">����</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">�û�����</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" height="22"><div align="center" class="title-04">������֯</div></td>                        
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">�绰</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                   <!-- <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td> -->
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%

        Iterator result=personlist.iterator();
		int i = 0;
        while(result.hasNext()){
            Person person = (Person)result.next();
            String personuuid = person.getUuid();
			i++;
			String color = "#F2F9FF";
			if(i % 2 == 1)
				color = "#D8EAF8";
				
				
		String personJUPosition = handler.getPersonJUPositionInJsp(personuuid);
%>
			<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
				<td  align="center"  class="text-01"><input type=checkbox name="personid" value="<%=person.getLoginName()%>��<%=StringUtil.escapeNull(person.getFullName())%>��<%=personJUPosition%>��<%=person.getUuid()%>"></td>
				<td height="22"   class="text-01"><div align="center"><%=StringUtil.escapeNull(person.getFullName())%></div></td>
				<td  class="text-01" align="center"><%=handler1.getJobFromPhonebyPeron(personuuid)%></td>
				<td class="text-01" align="center"><%=personJUPosition%></td>
				<td  class="text-01" align="center"><%=StringUtil.escapeNull(person.getOfficetel())%></td>
			</tr>
			  <tr>
			  
				<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				 <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
			  </tr>
<%
			}//end while
%>
				</table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
<input type=hidden name="isperson" value="<%=isperson%>">

<br>
<center>
<img src="<%=request.getContextPath()%>/images/button-select.gif" border=0 style="cursor:hand" onClick="javascript:getSearchPerson()" >
</center>
</form>
<%
}else{
%>
<br>
<p align="center" class="text-01">û���ҵ�������������Ա</p>
<%
}//else

%>
<%
if(request.getAttribute("personlist")==null){
%>
<br>
<p align="center" class="text-01">�����ߵĵ�����ѡ��</p>
<%
}
%>
</BODY>
</HTML>

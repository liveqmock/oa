<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.hr.vo.HRSysPersonVO"%>


<!--0413�ӵ�ַ����ѡ����Ա���͡��͵绰����-->
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>
<jsp:useBean id="handler1" class="com.icss.oa.address.handler.SysOrgpersonHandler"/>
<!--0413�ӵ�ַ����ѡ����Ա���͡��͵绰����-->

<HTML>
<HEAD>
<TITLE>���·�����Ա�б�</TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>
</HEAD>
<SCRIPT language=JavaScript>
function _back(){
   window.location.href = "<%=request.getContextPath()%>/servlet/SendFileSelectCommonTwoLevelGroupServlet?groupid=<%= request.getAttribute("groupid")%>";
}

function CheckAll()
	 {
	   for (var i=0;i<document.personform.elements.length;i++)
	   {
	     var e = document.personform.elements[i];
		  if (e.name == 'personid')
			 e.checked = document.personform.allbox.checked;
	   }
	 }
 
</SCRIPT>
<BODY  bgcolor="#eff7ff" onload="window.top.moveFrame()">
<%

String isperson = "";
List personlist = new ArrayList();

if(request.getAttribute("personlist")!=null){
	personlist = (List)request.getAttribute("personlist");
}
if (!personlist.isEmpty()){
	isperson = "1";
%>
<form name="personform" method="post" action="" target="listFrame">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">���·�����Ա�б�</td>
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
                        <td width="0" rowspan="1000"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">����</div></td>
                        <td width="0"  rowspan="1000"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">�û�����</div></td>
                        <td width="0"  rowspan="1000"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" height="22"><div align="center" class="title-04">������֯</div></td>
                        <td width="0"  rowspan="1000"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="30%" ><div align="center" class="title-04">�绰</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%

        Iterator result=personlist.iterator();

		int i = 0;//ѭ������
        while(result.hasNext()){
            HRSysPersonVO vo =(HRSysPersonVO)result.next();
            System.out.println("%%%%%%%%%%%%%%%%%"+vo.getPersonuuid());
            i++;
			String color = "#F2F9FF";
			if(i % 2 == 1)
				color = "#D8EAF8";
			
			
			
			

		String personJUPosition = handler.getPersonJUPositionInJsp(vo.getPersonuuid());
%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td  align="center"  class="text-01"><input type=checkbox name="personid" value="<%=vo.getUserid()%>��<%=StringUtil.escapeNull(vo.getCnname())%>��<%=personJUPosition%>��<%=vo.getPersonuuid()%>"></td>
                        <td height="22"   class="text-01"><div align="center"><%=StringUtil.escapeNull(vo.getCnname())%></div></td>
                        <td  class="text-01"><div align="center"><%= handler1.getJobFromPhonebyPeron(vo.getPersonuuid())%> </div></td>
                        <td class="text-01"><div align="center"><%= personJUPosition%></div></td>
                        <td  class="text-01"><div align="center"><% String officecode = handler1.GetOnePhone(vo.getPersonuuid());%><%= officecode!=null?officecode:(StringUtil.escapeNull(vo.getOfficetel()))%></div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
			
			}//while
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
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              	              	
								<table width="100%"><tr>
									<td width="50%" align="left"> 
								�� ҳ&nbsp;&#9474;
								��һҳ&nbsp;&#9474;
								��һҳ&nbsp;&#9474;
								β ҳ
								</td>
								<td align="right" width="50%">
								<select name ='ee' onChange="javaScript:_toPage(this.value)" style="width:60px;font-size:12px">
								    <option value='0'>ҳ ��</option>
								</select>
								��&nbsp;1&nbsp;ҳ&nbsp;��&nbsp;1&nbsp;ҳ&nbsp;&nbsp;����<%=i%>����¼
								
								</td></tr></table>

								<SCRIPT language=JavaScript>
								function _toPage(pageno){
								    
								    if(pageno!='0'){
									    var str = '/oabase/servlet/SendFileSelectCommonPersonServlet?result=login&LTPAToken=ZGV2KjdkZWVmNzU4LWZkYjc1ZjQ2OTMtODU4MWJkMmFmMTllNjYwMDM3ZDE0NGY1NWNhNGZiMWUqMjMqU3RyaW5n&groupid=GA0002&_page_num=-1';
									    var a1=str.lastIndexOf("=");
									    var a3=str.substr(0,a1);
									    var a4=a3+'='+pageno;
										window.location.href = a4;}
								}
								
								
								</SCRIPT>
              	
             <!-- <%@ include file="../../include/defaultPageScrollBar.jsp"%>-->
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
<input type=hidden name="isperson" value="<%=isperson%>">
<br>
<center>
<input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">��ҳȫѡ</a></label>
<img src="<%=request.getContextPath()%>/images/button-select.gif" border=0 style="cursor:hand" onClick="javascript:getSearchPerson()" >
<!--  <img src="<%=request.getContextPath()%>/images/botton-return.gif" border=0 style="cursor:hand" onClick="javascript:_back()" >
-->
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

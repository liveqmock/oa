<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.oa.address.vo.SelectOrgpersonVO"%>


<!--0413从地址簿中选择人员类型。和电话号码-->
<jsp:useBean id="handler" class="com.icss.oa.filetransfer.handler.personInfoHandler"/>
<jsp:useBean id="handler1" class="com.icss.oa.address.handler.SysOrgpersonHandler"/>

<!--0413从地址簿中选择人员类型。和电话号码-->

<script type="text/javascript">
<!--

	function CheckAll()
	 {
	   for (var i=0;i<document.personform.elements.length;i++)
	   {
	     var e = document.personform.elements[i];
		  if (e.name == 'personid')
			 e.checked = document.personform.allbox.checked;
	   }
	 }
 

//-->
</script>

<HTML>
<HEAD>
<TITLE>人员列表</TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/sendfile.js"></SCRIPT>

<SCRIPT language=JavaScript>
function _loadSelectPerson(){
<%
//读取选择的数据进行处理
List selectorgpersonlist = (List)request.getAttribute("selectorgpersonlist");
	String orgname = request.getParameter("orgname");
if (selectorgpersonlist!=null){
%>
	window.waitShow.style.display = "";
	var persons = new Array();
<%
	for(int i=0;i<selectorgpersonlist.size();i++){
		SelectOrgpersonVO selectorgpersonvo = (SelectOrgpersonVO)selectorgpersonlist.get(i);
%>
	var onePerson<%=i%> = new Array();
	onePerson<%=i%>["name"] = "<%=selectorgpersonvo.getName()%>";
	onePerson<%=i%>["value"] = "<%=selectorgpersonvo.getUserid()%>";
	onePerson<%=i%>["department"] = "<%=orgname%>";
	onePerson<%=i%>["type"] = "0";
	onePerson<%=i%>["uuid"] = "<%=selectorgpersonvo.getUseruuid()%>";
	persons[persons.length] = onePerson<%=i%>;
<%
	}
%>
	window.top.setPersons(persons);
	window.waitShow.style.display = "none";
<%
}
%>
}
function _childOrgsPeople(){
	window.waitShow.style.display = "";
	document.personform.action="<%=request.getContextPath()%>/servlet/SendFileChooseChildOrgsPersonServlet";
	document.personform.submit();
}
function _allPeople(){
	window.waitShow.style.display = "";
	document.personform.action="<%=request.getContextPath()%>/servlet/SendFileChooseAllPersonServlet";
	document.personform.submit();
}
</SCRIPT>
</HEAD>
<BODY  bgcolor="#eff7ff" onload="_loadSelectPerson();window.top.moveFrame();">
<div style="display:none;position: absolute; width: 122px; height: 17px; z-index: 1; left: 337px; top: 79px; border-style: solid; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px; background-color: #FFFFE6" id="waitShow">
正在添加请稍候...</div>
<%
//读取选择的数据进行处理

//以下是列表
String isperson = "";
List personlist = new ArrayList();
if(request.getAttribute("personlist")!=null){
	personlist = (List)request.getAttribute("personlist");
}
String orguuid = request.getParameter("orguuid");


%>

<form name="personform" method="post" action="" target="_self">
<input type=hidden name="isperson" value="<%=isperson%>">
<input type=hidden name="orguuid" value="<%=orguuid%>">
<input type=hidden name="orgname" value="<%=orgname%>">
<%
if (!personlist.isEmpty()){
	isperson = "1";
%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05"><%=orgname%>人员列表</td>
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
                        <td width="10%" ><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan=<%=3*personlist.size()%>   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" ><div align="center" class="title-04">姓名</div></td>
                        <td width="0"  rowspan=<%=3*personlist.size()%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" ><div align="center" class="title-04">职务</div></td>
                        <td width="0"  rowspan=<%=3*personlist.size()%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="40%" height="22"><div align="center" class="title-04">所属组织</div></td>
                        <td width="0"  rowspan=<%=3*personlist.size()%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">电话</div></td>

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

		int i = 0;//循环变量
        while(result.hasNext()){
            SysOrgpersonVO sysorgpersonvo=(SysOrgpersonVO)result.next();
			i++;
			String color = "#F2F9FF";
			if(i % 2 == 1)
				color = "#D8EAF8";
			String sex = "";
			if (sysorgpersonvo.getSex().equals(new Integer(0)))
				sex = "保密";
			if (sysorgpersonvo.getSex().equals(new Integer(1)))
				sex = "男";
			if (sysorgpersonvo.getSex().equals(new Integer(2)))
				sex = "女";
				
				
			String personJUPosition = handler.getPersonJUPositionInJsp(sysorgpersonvo.getPersonuuid());
%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td  align="center"  class="text-01"><input type=checkbox name="personid" value="<%=sysorgpersonvo.getUserid()%>ш<%=StringUtil.escapeNull(sysorgpersonvo.getCnname())%>ш<%=personJUPosition%>ш<%=sysorgpersonvo.getPersonuuid()%>"></td>
                        <td height="22"   class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgpersonvo.getCnname())%></div></td>
                        <td  class="text-01"><div align="center"><%= handler1.getJobFromPhonebyPeron(sysorgpersonvo.getPersonuuid())%> </div></td>
                        <td class="text-01"><div align="center"><%= personJUPosition%></div></td>                        
                        <td  class="text-01"><div align="center"><%String officecode = handler1.GetOnePhone(sysorgpersonvo.getPersonuuid());%>
																 <%= officecode!=null?officecode:(StringUtil.escapeNull(sysorgpersonvo.getOfficetel()))%></div></td>
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
              <%@ include file="../../include/defaultPageScrollBar.jsp"%>
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
<br>
<center>
<input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">全选</a></label>
<img src="<%=request.getContextPath()%>/images/button-selectall.gif" border=0 style="cursor:hand" onClick="javascript:_childOrgsPeople()" >
<img src="<%=request.getContextPath()%>/images/button-childorgsperson.gif" border=0 style="cursor:hand" onClick="javascript:_allPeople()" >
<img src="<%=request.getContextPath()%>/images/button-select2.gif" border=0 style="cursor:hand" onClick="javascript:getSearchPerson()" >
</center>
<%
}else{
 if(!orgname.equals("null")){
%>
<br>
<p align="center" class="text-01"><%=orgname%>下没有人员</p>
<%
}//if
}//else

%>
<%
	if(request.getAttribute("personlist")==null){
%>
<br>
<p align="center" class="text-01">请从左边的导航栏选择</p>
<%
}
if (personlist.isEmpty()&&!orgname.equals("null")){
%>
<br>
<center>

<img src="<%=request.getContextPath()%>/images/button-selectall.gif" border=0 style="cursor:hand" onClick="javascript:_childOrgsPeople()" >
</center>  
<%
}
%>
</form>
</BODY>
</HTML>




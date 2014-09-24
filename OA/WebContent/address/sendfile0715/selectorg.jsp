<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>




<script type="text/javascript">
<!--

	function CheckAll()
	 {
	   for (var i=0;i<document.orgform.elements.length;i++)
	   {
	     var e = document.orgform.elements[i];
		  if (e.name == 'orgid')
			 e.checked = document.orgform.allbox.checked;
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
String orgname = (String)request.getAttribute("orgname");
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
String isorg = "";
List orglist = new ArrayList();
if(request.getAttribute("orglist")!=null){
	orglist = (List)request.getAttribute("orglist");
}
String orguuid = request.getParameter("orguuid");


%>

<form name="orgform" method="get" action="" target="_self">
<input type=hidden name="isorg" value="<%=isorg%>">
<input type=hidden name="orguuid" value="<%=orguuid%>">
<input type=hidden name="orgname" value="<%=orgname%>">
<%
if (!orglist.isEmpty()){
	isorg = "1";
%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05"><%=orgname%>单位列表</td>
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

                        <td width="15%" ><div align="center" class="title-04">部门名称</div></td>
                    
             
					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                   
                      </tr>
<%

        Iterator result=orglist.iterator();

		int i = 0;//循环变量
        while(result.hasNext()){
				 SysOrgVO sysorgvo=(SysOrgVO)result.next();
				 String color = "#F2F9FF";
				 i++;
			if(i % 2 == 1)
			{color = "#D8EAF8";}
%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td  align="center"  class="text-01"><input type=checkbox name="orgid" value="<%=StringUtil.escapeNull(sysorgvo.getCnname())%>ш<%=StringUtil.escapeNull(sysorgvo.getCnname())%>ш<%=StringUtil.escapeNull(sysorgvo.getCnname())%>ш<%=sysorgvo.getOrguuid()%>"></td>
                        <td height="22"   class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgvo.getCnname())%></div></td>
             
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
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
<input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">本页全选</a></label>
<img src="<%=request.getContextPath()%>/images/button-select2.gif" border=0 style="cursor:hand" onClick="javascript:getSelectOrg()" >
</center>
<%
}else{
 if(!orgname.equals("null")){
%>
<br>
<p align="center" class="text-01"><%=orgname%>下没有部门</p>
<%
}//if
}//else

%>
<%
	if(request.getAttribute("orglist")==null){
%>
<br>
<p align="center" class="text-01">请从左边的导航栏选择</p>
<%
}
if (orglist.isEmpty()&&!orgname.equals("null")){
%>
<br> 
<%
}
%>
</form>
</BODY>
</HTML>




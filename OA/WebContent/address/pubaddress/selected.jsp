<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<%@ page import="com.icss.oa.config.*"%>
<%@ page import="com.icss.oa.address.handler.AddressHelp"%>

<%
String sessionname = request.getParameter("sessionname");

String doFunction = request.getParameter("doFunction");
AddressHelp handler = new AddressHelp();
%>


<html>
<head>
<title>Untitled Document</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT LANGUAGE="JavaScript">
function _commitPerson(){
	var checkedstr="";
	for (var i=0;i<form1.elements.length;i++){
		var e = form1.elements[i];
		if (e.name == "personname")
			checkedstr = checkedstr + "," + e.value;
	}
	
	var checkedstr_uuid="";
	for (var i=0;i<form1.elements.length;i++){
		var e = form1.elements[i];
		if (e.name == "personuuid")
			checkedstr_uuid = checkedstr_uuid + "," + e.value;
	}
	
	checkedstr = checkedstr.substring(1,checkedstr.length);	
	checkedstr_uuid = checkedstr_uuid.substring(1,checkedstr_uuid.length);	
  
    //alert(checkedstr);
    //alert(checkedstr_uuid);
	
	if( "<%=doFunction%>" == "_addPerson_hidden" || "<%=doFunction%>" == "_addcc" || "<%=doFunction%>" == "_addbcc"|| "<%=doFunction%>" == "_addUserftp" || "<%=doFunction%>" == "_addUserCms" || "<%=doFunction%>" == "_addUser"){
		var doUrl1 = "window.top.opener.<%=doFunction%>1(checkedstr_uuid)"; 
		//alert(checkedstr_uuid);
		//alert(doUrl1);
		eval(doUrl1);   
		//alert("eval1_successful!"); 
	}
	
	var doUrl = "window.top.opener.<%=doFunction%>(checkedstr)";
	//alert(doUrl);
	eval(doUrl);
	
	
	window.top.close();
}
function _Delete()
{
  	document.form1.submit();
}
function _clearsession()
{
	document.form1.action="<%=request.getContextPath()%>/servlet/ClearAddressSessionServlet"
  	document.form1.submit();
}
function _cancel()
{
	document.form1.action="<%=request.getContextPath()%>/address/pubaddress/clearsession.jsp?sessionname=<%=sessionname%>";
  	document.form1.submit();
}
</SCRIPT>
</head>
<body>
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/DelOrgpersonServlet">
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">已选中人员或组</td>
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
<%
String imageurl = null;
List selectorgpersonlist = new ArrayList();

if (session.getAttribute(sessionname) != null)
	selectorgpersonlist = (List)session.getAttribute(sessionname);
	//System.err.println("selectorgpersonlist:"+selectorgpersonlist.size());

String sessioninfo = "";
if (!selectorgpersonlist.isEmpty()){
%>
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF"> 
                        <td width="13%"  height="22"><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan=<%=selectorgpersonlist.size()*4%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="12%" ><div align="center" class="title-04">类型</div></td>
                        <td width="0" rowspan=<%=selectorgpersonlist.size()*4%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" ><div align="center" class="title-04">组名</div></td>
                        <td width="0"  rowspan=<%=selectorgpersonlist.size()*4%> valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="13%" ><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan=<%=selectorgpersonlist.size()*4%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="12%" ><div align="center" class="title-04">类型</div></td>
                        <td width="0" rowspan=<%=selectorgpersonlist.size()*4%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="25%" ><div align="center" class="title-04">组名</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>

<%
Iterator result=selectorgpersonlist.iterator();
	int i = 0;//循环变量
    while(result.hasNext()){

        SelectOrgpersonVO selectorgpersonvo=(SelectOrgpersonVO)result.next();
		i++;
		String color = "#F2F9FF";
		if(i % 2 == 1)
			color = "#D8EAF8";
			
        if (selectorgpersonvo.getIsperson().equals("1")){
        	imageurl = "person.gif";
        }else{
        	if(selectorgpersonvo.getIsperson().equals("00")){
        		imageurl = "commongroup.gif";
        	}else{
        		imageurl = "indigroup.gif";
        	}
        }
%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						<td align="center" ><input type=checkbox name="personid" value="<%=selectorgpersonvo.getUserid()%>"></td>
						<td align="center" ><img src="<%=request.getContextPath()%>/images/<%=imageurl%>" border=0 ></td>
                        <td height="22"   class="text-01"><div align="center"><%=selectorgpersonvo.getName()%>
                        <input type=hidden name="personname" value="<%=selectorgpersonvo.getName()%>">
						<% if(selectorgpersonvo.getIsperson().equals("1")){%>
                        		<input type=hidden name="personuuid" value="<%=selectorgpersonvo.getUseruuid()%>">
                        <%}else if(selectorgpersonvo.getIsperson().equals("00")&&("_addPerson".equals(doFunction)||"_addcc".equals(doFunction)||"_addbcc".equals(doFunction))){
 
                        %>
								<input type=hidden name="personuuid" value="<%=handler.getPersonUUID_StringByGruopId(Integer.valueOf(selectorgpersonvo.getUserid()),AddressConfig.GROUPTYPE_COMMOM)%>">
						<%}else if(selectorgpersonvo.getIsperson().equals("01")&&("_addPerson".equals(doFunction)||"_addcc".equals(doFunction)||"_addbcc".equals(doFunction))){

						%>
								<input type=hidden name="personuuid" value="<%=handler.getPersonUUID_StringByGruopId(Integer.valueOf(selectorgpersonvo.getUserid()),AddressConfig.GROUPTYPE_PRIVATE)%>">
						<%}%>
                        </div></td>
                    
<%
	if (result.hasNext()){
			selectorgpersonvo=(SelectOrgpersonVO)result.next();  
	        if (selectorgpersonvo.getIsperson().equals("1")){
	        	imageurl = "person.gif";
	        }else{
	        	if(selectorgpersonvo.getIsperson().equals("00")){
	        		imageurl = "commongroup.gif";
	        	}else{
	        		imageurl = "indigroup.gif";
	        	}
	        }
			%>
					
						<td align="center" ><input type=checkbox name="personid" value="<%=selectorgpersonvo.getUserid()%>"></td>
						<td align="center" ><img src="<%=request.getContextPath()%>/images/<%=imageurl%>" border=0 ></td>
                        <td height="22"   class="text-01"><div align="center"><%=selectorgpersonvo.getName()%>
                        <input type=hidden name="personname" value="<%=selectorgpersonvo.getName()%>">
                        <% if(selectorgpersonvo.getIsperson().equals("1")){%>
                        		<input type=hidden name="personuuid" value="<%=selectorgpersonvo.getUseruuid()%>">
                        <%}else if(selectorgpersonvo.getIsperson().equals("00")&&("_addPerson".equals(doFunction)||"_addcc".equals(doFunction)||"_addbcc".equals(doFunction))){%>
								<input type=hidden name="personuuid" value="<%=handler.getPersonUUID_StringByGruopId(Integer.valueOf(selectorgpersonvo.getUserid()),AddressConfig.GROUPTYPE_COMMOM)%>">
						<%}else if(selectorgpersonvo.getIsperson().equals("01")&&("_addPerson".equals(doFunction)||"_addcc".equals(doFunction)||"_addbcc".equals(doFunction))){%>
								<input type=hidden name="personuuid" value="<%=handler.getPersonUUID_StringByGruopId(Integer.valueOf(selectorgpersonvo.getUserid()),AddressConfig.GROUPTYPE_PRIVATE)%>">
						<%}%>
                        </div></td>
                    </tr>  
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
			
			<%
	}else{
	%>
						<td align="center" ></td>
						<td align="center" ></td>
                        <td height="22"   class="text-01"></td>
                    </tr>  
                      <tr>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
	
	<%
	}//else
	}//while
}else{
%>
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
                      <tr>
	                      <td align="center" height="22" colspan=13><p  class="title-04">没有选中人员或组</p></td>
                      </tr>
                      <tr>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>

<%
}
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
              <!--%@ include file="../../include/defaultPageScrollBar.jsp"%-->
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
          <br>
<center>
<img src="<%=request.getContextPath()%>/images/botton-delete2.gif" border=0 style="cursor:hand" onClick="javascript:_clearsession()" >
<img src="<%=request.getContextPath()%>/images/button-delallperson2.gif" border=0 style="cursor:hand" onClick="javascript:_Delete()" >
<img src="<%=request.getContextPath()%>/images/botton-ok.gif" border=0 style="cursor:hand" onClick="javascript:_commitPerson()" >
<img src="<%=request.getContextPath()%>/images/button-closeaddress.gif" border=0 style="cursor:hand" onClick="javascript:_cancel()" >
</center>
<input type=hidden name="doFunction" value="<%=doFunction%>">
<input type=hidden name="sessionname" value="<%=sessionname%>">
<a name="anchor"></a>
</form>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function _auto(){
	     if(<%= request.getParameter("auto")%>){
	     		setTimeout("_commitPerson()",1);
	     }
	} 
	_auto();  
//-->
</SCRIPT>


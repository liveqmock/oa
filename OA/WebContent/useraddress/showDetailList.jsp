<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.useraddress.vo.*" %> 
<%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<%@ page import="com.icss.oa.useraddress.handler.*" %>

<%
Collection ucollection = (Collection)request.getAttribute("userinfoList");
List regionlist=(List)request.getAttribute("regionlist");
Iterator regionitr=regionlist.iterator();
List deptlist=(List)request.getAttribute("deptlist");
System.out.println("++++++++++showdetaillist.jsp"+deptlist.size());
Iterator deptitr = deptlist.iterator();


%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>OA�û���ַ�б�</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</head>
<script language="JavaScript">
function _userQuery(url){
		document.QueryForm.action=url+"/servlet/userAddressQueryServlet";
	    document.QueryForm.submit();	
	}
	
function UpdateUserInfo(odid,odusrname,oddept,odroomnum,odtel,odword,OdBuss,OdSys,OdIp,OdOpesys,OdMacnum,OdRoomnod,OdMachid,OdMachcircs,OdOnflag,OdRegion,OdVirus,OdOffice,OdOpentime,OdMachname,OdMemo,OdNetcircs,OdNettel)
{
    document.form_update.odid.value   = odid;	
	document.form_update.odusrname.value   = odusrname;
    document.form_update.oddept.value   = oddept;
    document.form_update.odroomnum.value   = odroomnum;
	document.form_update.odtel.value   = odtel;
	document.form_update.odword.value=odword;
	document.form_update.OdBuss.value=OdBuss;
	document.form_update.OdSys.value=OdSys;
	document.form_update.OdIp.value=OdIp;
	document.form_update.OdOpesys.value=OdOpesys;
	document.form_update.OdMacnum.value=OdMacnum;
	document.form_update.OdRoomnod.value=OdRoomnod;
	document.form_update.OdMachid.value=OdMachid;
	document.form_update.OdMachcircs.value=OdMachcircs;
	document.form_update.OdOnflag.value=OdOnflag;
	document.form_update.OdRegion.value=OdRegion;
	document.form_update.OdVirus.value=OdVirus;
	document.form_update.OdOffice.value=OdOffice;
	document.form_update.OdOpentime.value=OdOpentime;
	
	document.form_update.OdMachname.value=OdMachname;
	document.form_update.OdMemo.value=OdMemo;
	document.form_update.OdNetcircs.value=OdNetcircs;
	document.form_update.OdNettel.value=OdNettel;
}
function _Add(){
	if(!IsFormItemEmpty()){
    	document.form_update.action="<%=request.getContextPath()%>/servlet/addUseraddrServlet";
  		document.form_update.submit();
 	}
}
function _Update(){
	 if(IsCheck()){
     	if(!IsFormItemEmpty()){
			document.form_update.action="<%=request.getContextPath()%>/servlet/updateUseraddrServlet";
			document.form_update.submit();
		}
    }
}
function _Delete(){
    if(IsCheck()){
		document.form_update.action="<%=request.getContextPath()%>/servlet/delUseraddrServlet";
    	document.form_update.submit();
    }
}
function _print(){
   		document.form_update.action="<%=request.getContextPath()%>/servlet/UseraddressPrintServlet";
    	document.form_update.submit();
}
function _import(){
   		document.form_update.action="<%=request.getContextPath()%>/useraddress/dataimp.jsp";
    	document.form_update.submit();
}
function IsFormItemEmpty(){
  	if(document.form_update.odusrname.value==""){
    	alert("����д�û����ƣ�");
    	return true;
    }
    
	return false;
} 
function IsCheck(){
	if(document.form_update.odid.value  == "null"){
    	alert("��ѡ��һ����¼��");
		return false;
	}
	return true;
}
</script	>
<body  background="<%=request.getContextPath()%>/images/bg-08.gif">  

<FORM name=QueryForm method=post>
 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
 <tr>
      <td width="" valign="top">
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            		<td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">�»���OA�û����ϲ�ѯ</td>
            		<td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            		<td width="100%">
              				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                			<tr>
                  					<td background="<%=request.getContextPath()%>/images/bg-09.jpg">       						  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">�û�������</td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="username" type="text" value="" size="29"></td>
                                        <td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" height="22" class="text-01" align="right">���ڲ��ţ�</td>
                                        <td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif">
                                        </td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                        <select name="dept">
                                        <option value="">ȫ��</option>
                                        	<%while(deptitr.hasNext()){ 
                                        		String	deptname=deptitr.next().toString();
                                        		%>
                                        		
                                        		<option value="<%=deptname %>" ><%=deptname %></option>
                                        	<%} %>
                                        </select>	
                                         </td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">���ڵ�����</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                         <select name ="region">
                                         <option value="">ȫ��</option>
                                         <%while(regionitr.hasNext()){ 
                                        		String	regionname=regionitr.next().toString();
                                        		%>
                                        		
                                        		<option value="<%=regionname %>" ><%=regionname %></option>
                                        	<%} %>
                                         </select></td>
                                        <td width="100" height="22" class="text-01" align="right">�����ʶ��</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="machname" type="text" value="" size="29"></td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                                                            <tr>
                                        <td width="100" height="22" class="text-01" align="right">����ϵͳ��</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="opesys" type="text" value="" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">���ڰ칫�ң�</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="roomnum" type="text" value="" size="29"></td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">�û�IP��</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="ip" type="text" value="" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">��������</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="word" type=text value="" size="29"></td></tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
          								<tr>
                                        <td width="100" height="22"  class="text-01" align="right">���������</td>
                                        <td bgcolor="F2F9FF" colspan="5" class="text-01"><input name="machcircs" type="text" value="" size="29">
                                            <img onClick="javascript:_userQuery('<%=request.getContextPath()%>')" src="<%=request.getContextPath()%>/images/botton-search_in.gif" align="absmiddle" style="cursor:hand"></td>
                                      </tr>
                                      <tr>
                                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                                      </tr>
                                    </table></td>
                			</tr>
            				</table>
					</td>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
            		<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
          	</tr>
        	</table>
    </tr>
  	</table>
</form>
<FORM name=oauserForm method=post>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
		 <tr>
     	 <td height="95%" valign="top">
          		<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            		<tr>
              			<td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            			<td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">OA�û���ַ�б�</td>
              			<td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
            		</tr>
          		</table>
          		<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            		<tr>
              			<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              			<td width="100%">
						  	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  			<tr>
                    			<td background="<%=request.getContextPath()%>/images/bg-09.jpg">
									<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      				<tr  bgcolor="#FFFBEF">
										<td width="3%" bgcolor="#FFFBEF"></td>
										<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
										<td width="4%" align="center" class="title-04">���</td>
										<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>                      				  	
										<td width="7%" align="center" class="title-04">�û���</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        				<td width="9%" align="center" class="title-04">����</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        				<td width="10%" align="center" class="title-04">λ��</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                       					<td width="10%" align="center" class="title-04">��ϵ�绰</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        				<td width="8%" align="center" class="title-04">�豸���</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        				<td width="8%" height="22" align="center" class="title-04">����ڵ�</td>
                         				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        				<td width="12%" align="center" class="title-04">�������</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>         
                        				<td width="12%" height="22" align="center" class="title-04">IP</td>
                         				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                       					 <td width="7%" height="22" align="center" class="title-04">����ϵͳ</td>
                        				<td width="2" rowspan="800" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                         				<td width="10%" height="22" align="center" class="title-04">��ʶ</td>
                      				</tr>
                      				
                      				
                      				<tr>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
										<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
										<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
										<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        				<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      				</tr>
									
<%		
		int i=0;
		Iterator UserAddIterator = ucollection.iterator();
		while(UserAddIterator.hasNext()){
				OaaddListVO  vo = (OaaddListVO)UserAddIterator.next();
				i++; 
       			 if(i%2!=0){
 %>
				 <tr onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#D8EAF8';" bgcolor="#D8EAF8">
<%
					}
				else{
%>

									<tr class="text-01" onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';" bgcolor="#F2F9FF">
<%	}
%>    									
										 <td height="22" class="text-01" align="center"><input name="ipid" type="radio" value="radiobutton"
							 				onClick='javascript:UpdateUserInfo("<%=vo.getOdId()%>","<%= vo.getOdUsrname() %>","<%= vo.getOdDept()%>","<%= vo.getOdRoomnum() %>",
							 				"<%= vo.getOdTel()%>","<%= vo.getOdWord()%>","<%= vo.getOdBuss()%>","<%= vo.getOdSys()%>","<%= vo.getOdIp()%>","<%= vo.getOdOpesys()%>","<%= vo.getOdMacnum()%>","<%= vo.getOdRoomnod()%>","<%= vo.getOdMachid()%>","<%=vo.getOdMachcircs() %>","<%= vo.getOdOnflag()%>","<%= vo.getOdRegion()%>","<%= vo.getOdVirus()%>","<%= vo.getOdOffice()%>","<%= vo.getOdOpentime()%>","<%= vo.getOdMachname()%>","<%= vo.getOdMemo()%>","<%= vo.getOdNetcircs()%>","<%=vo.getOdNettel()%>")'></td>
        								<td height="22" class="text-01" align="center"><%=i%></td>
										<td height="22" class="text-01" align="center"><%=(vo.getOdUsrname()==null?"":vo.getOdUsrname())%></td>
                						<td class="text-01" align="center"><%=(vo.getOdDept()==null?"":vo.getOdDept())%></td>
       									<td class="text-01" align="center"><%=(vo.getOdRegion()==null?"":vo.getOdRegion())%></td>
                						<td class="text-01" align="center" ><%=(vo.getOdTel()==null?"":vo.getOdTel())%></td>
                						<td class="text-01" align="center"><%=(vo.getOdMachid()==null?"":vo.getOdMachid())%></td>
                						<td class="text-01" align="center"><%=(vo.getOdRoomnod()==null?"":vo.getOdRoomnod())%></td>
										<td  class="text-01" align="center"><%=(vo.getOdMachcircs()==null?"":vo.getOdMachcircs())%></td>
                						<td  class="text-01" align="center"><%=(vo.getOdIp()==null?"":vo.getOdIp())%></td>
										<td  class="text-01" align="center"><%=(vo.getOdOpesys()==null?"":vo.getOdOpesys())%></td>
										<td  class="text-01" align="center"><%=(vo.getOdMachname()==null?"":vo.getOdMachname())%></td>
             						</tr> 

             						<tr>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
										<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                						<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
										<td colspan="1" height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
             						</tr>
 <%  } %>
		                            </table>
								</td>
                  			</tr>
              				</table>
						</td>
              			<td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            		</tr>
          		</table>
   		        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
        			<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
        			<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
                  </tr>
                </table>
   		   </td>
		 </tr>
  	</table>
</form>



<form name="form_update" action="" method="post" >
  				<input name="odid" type="hidden" value="null">
				<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    			<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
             	<tr>
               		<td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
               		<td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> ���/�޸�/ɾ�� OA�û�����</td>
               		<td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
             	</tr>
           		</table>
           		<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
             	<tr>
               		<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
               		<td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                   		<tr>
                     		<td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                         		<tr>
                           			<td width="16%" align="right">�û�����</td>
                           			<td width="2" rowspan="22" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                           			<td width="25%" bgcolor="#F2F9FF"><input name="odusrname" type="text" class="txt3" size="26" maxlength="64">&nbsp;*</td>
									<td width="2" rowspan="22" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                           			<td width="16%" align="right">���ڲ��ţ�</td>
                           			<td width="2" rowspan="22" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="oddept" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
                         		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
                         		<tr>
                           			<td width="16%" align="right">���ڰ칫�ң�</td>
                           			<td width="25%" bgcolor="#F2F9FF"><input name="odroomnum" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">��ϵ�绰��</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="odtel" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
                        		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
                         		<tr>
                           			<td width="16%" align="right">��������</td>
                           			<td width="25%" bgcolor="#F2F9FF"><input name="odword" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">ְ��</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdBuss" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
                        		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
								<tr>
                           			<td width="16%" align="right">����ϵͳ��</td>
                       			  <td width="25%" bgcolor="#F2F9FF"><input name="OdSys" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">IP��</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdIp" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
                        		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
								<tr>
                           			<td width="16%" align="right">����ϵͳ��</td>
                       			  <td width="25%" bgcolor="#F2F9FF"><input name="OdOpesys" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">MAC��ַ��</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdMacnum" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
								<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
								<tr>
                           			<td width="16%" align="right">����ڵ㣺</td>
                       			    <td width="25%" bgcolor="#F2F9FF"><input name="OdRoomnod" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">�豸��ţ�</td>
								    <td width="43%" bgcolor="#F2F9FF"><input name="OdMachid" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
								<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
								<tr>
                           			<td width="16%" align="right">���������</td>
                       			    <td width="25%" bgcolor="#F2F9FF"><input name="OdMachcircs" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">�Ƿ���ͨ��</td>
                           			 <td width="43%" bgcolor="#F2F9FF">
							            <select name="OdOnflag">
                             					<option value="�ѽ�ͨ">�ѽ�ͨ</option>
                             					<option value="����ͨ">����ͨ</option>
                             			</select>	</td>
                             					
                         		</tr>
                        		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
                         		<tr>
                           			<td width="16%" align="right">���ڵ�����</td>
                       			  <td width="25%" bgcolor="#F2F9FF"><input name="OdRegion" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">��������������</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdVirus" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
								<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr><tr>
                           			<td width="16%" align="right">���ң�</td>
                       			  <td width="25%" bgcolor="#F2F9FF"><input name="OdOffice" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">��ͨʱ�䣺</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdOpentime" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
								<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
								<tr>
                           			<td width="16%" align="right">�û���ʶ��</td>
                       			  <td width="25%" bgcolor="#F2F9FF"><input name="OdMachname" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">��ע��</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdMemo" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
                        		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
                         		<tr>
                           			<td width="16%" align="right">�ۺϲ��ߣ�</td>
                       			  <td width="25%" bgcolor="#F2F9FF"><input name="OdNetcircs" type="text" class="txt3" size="26" maxlength="64">&nbsp;</td>
                           			<td width="16%" align="right">����绰��</td>
								  <td width="43%" bgcolor="#F2F9FF"><input name="OdNettel" type="text" class="txt3" size="26" maxlength="64">                       			    &nbsp;</td>
                         		</tr>
                        		<tr>
                           			<td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                           			<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                         		</tr>
                     			</table>
							</td>
                   		</tr>
               			</table>
					</td>
               		<td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
             	</tr>
           		</table>
           		<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
             	<tr>
               		<td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
               		<td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
               		<td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
               		<td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
             	</tr>
           		</table>
           		<div align="center"><br>
               		<img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="_Add()" >&nbsp; 
               		<img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="_Update()">&nbsp; 
               		<img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand"  onClick="_Delete()">&nbsp;
               		<input type="button" value="��ӡexcel��"  onClick="_print()">&nbsp;
               		<input type="button" value="���ص���ģ��"  onClick="document.location.href='<%=request.getContextPath()%>/useraddress/demo.xls'">&nbsp;
               		<input type="button" value="����excel��"  onClick="_import()"><br>
           		</div>
			</form>
</body>

</html>
<%@ page contentType="text/html; charset=GBK" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.addressbook.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.addressbook.admin.*" %>   



<%

List addrbList = (List)request.getAttribute("fileList");
String parentId = (String)request.getAttribute("parentId");
String path = (String)request.getAttribute("path");
//out.print("shareFlag="+shareFlag+" accessFlag="+accessFlag);
%>

<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module14");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312"><LINK 
href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>

<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>

<SCRIPT src="index_files/calendar.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>

</HEAD>
<BODY topMargin=0 background="<%=request.getContextPath()%>/images/folder/bg-08.gif">



<br>

<FORM name="QueryForm" method="post">
 <input name="queryparentId" type="hidden" value="<%=parentId%>"> 

 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
 <tr>
      <td width="" valign="top">
        	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            		<td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">通讯录查询</td>
            		<td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
          	</tr>
        	</table>
        	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            		<td width="100%">
              				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                			<tr>
                  					<td background="<%=request.getContextPath()%>/images/bg-09.jpg">       						  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">姓名：</td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="queryname" type="text" value="" size="29"></td>
                                        <td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" height="22" class="text-01" align="right">所在单位：</td>
                                        <td width="2" rowspan="8" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="querycompany" type="text" value="" size="29"></td>
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
                                        <td width="100" height="22" class="text-01" align="right">单位地址：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="querycompanyaddress" type="text" value="" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">家庭住址：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="queryfamilyaddress" type="text" value="" size="29"></td>
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
                                        <td width="100" height="22" class="text-01" align="right">单位电话：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="querycompanytel" type="text" value="" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">住宅电话：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="queryfamilytel" type="text" value="" size="29"></td>
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
                                        <td width="100" height="22" class="text-01" align="right">手机：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="querycellphone" type="text" value="" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">电子邮件：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="queryemail" type="text" value="" size="29"></td>
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
                                        <td width="100" height="22"  class="text-01" align="right">备注：</td>
                                        <td bgcolor="F2F9FF" colspan="5" class="text-01"><input name="querymemo" type="text" value="" size="29">
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
        	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
            		<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
          	</tr>
        	</table>
    </tr>
  	</table>
</form>


    <form name="form" method="post" action="">     
   		 <input name="abfid" type="hidden" value="null"> 
   		 <input name="abfname" type="hidden" value="null"> 
   		 <input name="abfdesc" type="hidden" value="null"> 
   		  <input name="abcid" type="hidden" value="null"> 
   		 <input name="abcname" type="hidden" value="null"> 
   		 <input name="abccompany" type="hidden" value="null"> 
   		 <input name="abccompanyaddress" type="hidden" value="null"> 
   		 <input name="abcfamilyaddress" type="hidden" value="null"> 
   		 <input name="abccompanytel" type="hidden" value="null"> 
   		 <input name="abcfamilytel" type="hidden" value="null"> 
   		 <input name="abccellphone" type="hidden" value="null"> 
   		 <input name="abcemail" type="hidden" value="null"> 
   		 <input name="abcemailsec" type="hidden" value="null"> 
   		 <input name="abcmemo" type="hidden" value="null"> 
   		  <input name="abclevel" type="hidden" value="null"> 
   		  <input name="abcfid" type="hidden" value="null"> 
   		   
  <table width="100%"  cellSpacing=0 cellPadding=0 align=center border=0>
    <tr> 
      <td vAlign=top> <div align="center"> 
          <TABLE  width="90%"  cellSpacing=0 cellPadding=0 align=center border=0>
            <TR> 
              <TD align=left > <table width=100% border=0>
                  <tr valign=bottom height=21> 
                    <td ><div align="center"><font color=#fb4303>当前所在位置：<%=path%></font> 
                      </div></td>
                  </tr>
                  <tr> 
                    <td height=10  width=100% > <div align="center" ></div></td>
                  <tr> </table>
              <td> </tr>
            <tr> 
              <td height=8></td>
            </tr>
          </table>
          <!----------文件和目录列表显示start------------------------>
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-12.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/folder/bg-12.gif" class="title-05">分组/通讯录</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <table width="100%" border="0" align=center cellpadding="0" cellspacing="0">
            <tbody>
              <tr bgColor=#FFFBEF class="title-04"> 
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2 ></td>
                <td width="4%" height=25><div align="center">选择</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="4%" height=25><div align="center">序号</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="14%"><div align="center">姓名</div></td>
                 <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="12%"><div align="center">单位电话</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td width="13%"><div align="center">住宅电话</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
               <td width="15%"><div align="center">手机</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
               <td width="18%"><div align="center">电子邮件</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
              <td width=""><div align="center">备注</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
              
              </tr>
              <tr> 
                <td height="0" background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
               <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
              </tr>
			    <%
						  int i=0;

 Iterator Itr = addrbList.iterator();
 AddressbookAllSearchVO vo = null;
 while(Itr.hasNext()){
	  vo = (AddressbookAllSearchVO)Itr.next();
	  i++;
	  if(vo.getAbfFlag().equals("1")){

%>
          
              <%if(i%2==0){%>
              <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}else{%>
              <tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}%>
               		 <td height="22" class="text-01" align="center"><input name="selectabfid" type="checkbox" value="<%=vo.getAbfId()%>"
						onClick='javascript:UpdateAddressbookFolder("<%=vo.getAbfId()%>","<%=vo.getAbfName()%>","<%=vo.getAbfDescript()%>","")'></td>
                 	<td width=""><div align="center"><%=i%></div></td>
				 	<td width="" colspan="9"><div align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A  href="<%=request.getContextPath()%>/servlet/ShowAddressbookListServlet?parentId=<%=vo.getAbfId()%>" title="查看分组内容"><img src="<%=request.getContextPath()%>/images/folder/close.gif" width="15" height="15" align="middle"   style="border=0"> <%=vo.getAbfName()%></a></div></td>
  					<td width=""><a href="#" onClick="javascript:UpdateAddressbookFolder('<%=vo.getAbfId()%>','<%=vo.getAbfName()%>','<%=vo.getAbfDescript()%>','<%=parentId%>')" alt="查看或修改此分组信息"><div align="center">分组</div></a></td>
               </tr>
               <tr> 
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td colspan="15" background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                
              </tr>
         <%}else{%>   
              <%if(i%2==0){%>
              <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}else{%>
              <tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}%>
                <td height="22" class="text-01" align="center"><input name="selectabpid" type="checkbox" value="<%=vo.getAddAbfcId()%>" onClick='javascript:UpdateAddressbook("<%=vo.getAbcId()%>","<%=vo.getAbcName()%>","<%=vo.getAbcCompany()%>","<%=vo.getAbcCompanyaddress()%>","<%=vo.getAbcFamilyaddress()%>","<%=vo.getAbcCompanytel()%>","<%=vo.getAbcFamilytel()%>","<%=vo.getAbcCellphone()%>","<%=vo.getAbcEmail()%>","<%=vo.getAbcEmailsec()%>","<%=vo.getAbcMemo()%>","<%=vo.getAbcLever()%>","<%=vo.getAddAbfcId()%>")'></td>                    
				<td width=""><div align="center"><%=i%></div></td>
                <td width="">
                    <div align="center">
                    <a href="<%=request.getContextPath()%>/servlet/GotoUpdateServlet?parentId=<%=parentId%>&abcid=<%=vo.getAbcId()%>" title="修改这条记录"><%=vo.getAbcName()%></a>
                    <a href="<%=request.getContextPath()%>/servlet/GotoUpdateServlet?parentId=<%=parentId%>&abcid=<%=vo.getAbcId()%>" title="修改这条记录"><img src="<%=request.getContextPath()%>/images/compose.gif" border="0"></a>
                    </div>
                </td>
                <td width=""><div align="center"><%=(vo.getAbcCompanytel()==null?"":vo.getAbcCompanytel())%></div></td>
               
                <td width=""><div align="center"><%=(vo.getAbcFamilytel()==null?"":vo.getAbcFamilytel())%></div></td>

               <td width=""><div align="center"><%=(vo.getAbcCellphone()==null?"":vo.getAbcCellphone())%></div></td>
               
               <td width=""><div align="center"><%=(vo.getAbcEmail()==null?"":vo.getAbcEmail())%></div></td>
              
               <td width=""><div align="center"><%=(vo.getAbcMemo()==null?"":vo.getAbcMemo())%></div></td>
              </tr>
           
              <tr> 
              <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
               <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
               
              </tr>
          <%}}%>
            </tbody>
          </table>
           <!-- 翻页 begin -->
          <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="1%"><img src="<%=request.getContextPath()%>/images/folder/bg-21.jpg" width="10" height="21"></td>
              <td width="55%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"> 
                <%@ include file= "/include/defaultPageScrollBar.jsp" %>
              </td>
              <td width="30%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          <!-- 翻页 end -->
         <br>
          <table width=800 border=0>
            <tbody>
              <tr> 
                <td> <div align=center> 
                    
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-addgroup.gif" onClick="javascript:_addFolder('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-renamegroup.gif"  onclick="javascript:_updateAddrbFolder('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-addaddrb.gif"  onclick="javascript:_addAddressbook('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-delete.gif" onClick="javascript:_delAddrbFolder('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    
                    <img src="<%=request.getContextPath()%>/images/addressbook/button-printaddrb.gif" onClick="javascript:_printAddressbook('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                     </div></td>
                    
			  </tr>
            </tbody>
          </table>
          <!-- Tail begin -->
        </div></TD>
    </TR>
  </TABLE>	  
		</td>
	</tr>
</table>
</form>
</BODY></HTML>

<script language="JavaScript">
function UpdateAddressbookFolder(inabfid,inabfname,inabfdesc,parentId)
{
    document.form.abfid.value   = inabfid;	
    document.form.abfname.value   = inabfname;	
    document.form.abfdesc.value   = inabfdesc;
    if(parentId!=""){
        document.form.action = "<%=request.getContextPath()%>/addressbook/updateAddrbFolder.jsp?parentId="+parentId;
   		document.form.submit();
    }
    
}
function UpdateAddressbook(inabcid,inabcname,inabccompany,inabccompanyaddress,inabcfamilyaddress,inabccompanytel,inabcfamilytel,inabccellphone,inabcemail,inabcemailsec,inabcmemo,inabclevel,inabcfid)
{
    document.form.abcid.value   = inabcid;	
    document.form.abcname.value   = inabcname;	
    document.form.abccompany.value   = inabccompany;	
    document.form.abccompanyaddress.value   = inabccompanyaddress;	
    document.form.abcfamilyaddress.value   = inabcfamilyaddress;	
    document.form.abccompanytel.value   = inabccompanytel;	
    document.form.abcfamilytel.value   = inabcfamilytel;	
    document.form.abccellphone.value   = inabccellphone;	
    document.form.abcemail.value   = inabcemail;	
    document.form.abcemailsec.value   = inabcemailsec;	
    document.form.abcmemo.value   = inabcmemo;	
    document.form.abclevel.value   = inabclevel;
    document.form.abcfid.value   = inabcfid;
    
    
}


function _checkOnly(outString,outString2){
	var i;
	var n;
	if(form.folderId == undefined){
		alert(outString);
		return false;
	}
	if(form.folderId.length == undefined){
		if(form.folderId.checked){
			return true;
		}
	}
	else{
		n=0;
		for(i=0;i<form.folderId.length&&n<2;i++){
			if(form.folderId[i].checked){
				++n;
			}
		}
		if(n>1){
			alert(outString2);
			return false;
		}
		else if(n==0){
			alert(outString);
			return false;
		}
		else if(n==1){
			return true;
		}
	}
	alert(outString);
	return false;
}

function _checkfolder(outString){
	if(document.form.selectabfid == undefined){
	    alert("没有可重命名的分组！");
	    return false;
	}else if(document.form.selectabfid.length == undefined){
		if(document.form.selectabfid.checked){	
			return true;
		}else{
		    alert("请选择一个分组！");
		    return false;
		}
	}else{
	    var checknum = 0;
	    for(var i=0;i<document.form.selectabfid.length;i++){
	        if(document.form.selectabfid[i].checked){
	            checknum++;
	        }
	    }
	    if(checknum==0){
	       alert("请您选择一个分组！");
	       return false;
	    }
	    if(checknum>1){
	    	alert("您只能选择一个分组进行重命名！");
	    	return false;
	    }
	}
	return true;
}

function _checkdeletebook(){
    if(document.form.selectabfid == undefined&&document.form.selectabpid == undefined){
        alert("没有可删除的联系人或分组！");
        return false;
    }else if(document.form.selectabfid == undefined){
        if(document.form.selectabpid.length == undefined){
        	if(document.form.selectabpid.checked){
            	return true;
        	}
            alert("请选择联系人！");
            return false;
        }else{
		    for(var j=0;j<document.form.selectabpid.length;j++){
                if(document.form.selectabpid[j].checked){
                    return true;
                }
            }
            alert("请选择一个联系人！");
            return false;      
        }
    }else if(document.form.selectabpid == undefined){
        if(document.form.selectabfid.length == undefined){
        	if(document.form.selectabfid.checked){
            	return true;
        	}else{
            	alert("请选择分组！");
            	return false;
        	}
        }else{
		    for(var j=0;j<document.form.selectabfid.length;j++){
                if(document.form.selectabfid[j].checked){
                    return true;
                }
            }
            alert("请选择一个分组！");
            return false;                    
        }
    }else if(document.form.selectabfid.length == undefined&&document.form.selectabpid.length == undefined){
        if(document.form.selectabfid.checked||document.form.selectabpid.checked){
            return true;
        }else{
            alert("请选择一个删除项！");
            return false;
        }
    }else if(document.form.selectabfid.length == undefined&&document.form.selectabpid.length > 0){
	    var pd = false;
		if(document.form.selectabfid.checked){
		    pd = true;
		}else{
		    for(var j=0;j<document.form.selectabpid.length;j++){
                if(document.form.selectabpid[j].checked){
                    pd = true;
                }
            }
		}
		if(pd==false) alert("请选择一个删除项！");
		return pd;
	}else if(document.form.selectabfid.length > 0&&document.form.selectabpid.length == undefined){
	    var pd2 = false;
		if(document.form.selectabpid.checked){
		    pd2 = true;
		}else{
		    for(var j=0;j<document.form.selectabfid.length;j++){
                if(document.form.selectabfid[j].checked){
                    pd2 = true;
                }
            }
		}
		if(pd2==false) alert("请选择一个删除项！");
		return pd2;
	}else{
            for(var i=0;i<document.form.selectabfid.length;i++){
                if(document.form.selectabfid[i].checked){
                    return true;
                }
            }
            for(var j=0;j<document.form.selectabpid.length;j++){
                if(document.form.selectabpid[j].checked){
                    return true;
                }
            }
            alert("请选择一个删除项！");
            return false;
    }
}

function _checkaddressbook(outString){
	if(form.abcid == undefined||form.abcid.value=="null"){
		alert(outString);
		return false;
	}
	return true;
}

function _addFolder(url,parentId){

	
	form.action=url+"/addressbook/addFolder.jsp?parentId="+parentId;
	
    form.submit();
	
 }
 function _addAddressbook(url,parentId){

	
	form.action=url+"/addressbook/addAddressbook.jsp?parentId="+parentId;
	
    form.submit();
	
 }
function _updateAddrbFolder(url,parentId){

	if(_checkfolder("请选择要重命名的分组")){	
		form.action=url+"/addressbook/updateAddrbFolder.jsp?parentId="+parentId;
   		form.submit();
   	}
	
 }
 
 function _updateAddrb(url,parentId){

	if(_checkaddressbook("请选择要修改的联系人")){	
	form.action=url+"/addressbook/updateAddrb.jsp?parentId="+parentId;
	
    form.submit();
	}
 }
 
function _delAddrbFolder(url,parentId){

	if(_checkdeletebook()){	
		
		form.action=url+"/servlet/DelAddressbookServlet?parentId="+parentId;
    	form.submit();
		window.top.leftFrame.location.reload();
	}
 }

function _userQuery(url){
		document.QueryForm.action=url+"/servlet/AddressbookQueryServlet";
	    document.QueryForm.submit();	
	}

function _printAddressbook(url,parentId){
		document.QueryForm.action=url+"/servlet/AddressbookPrintServlet";
	    document.QueryForm.submit();	
	}
	
	
function CheckAll()
 {
   for (var i=0;i<document.form.elements.length;i++)
   {
     var e = document.form.elements[i];
	  if (e.name == 'workid')
		 e.checked = document.workForm.allbox.checked;
   }
 }
 
</script>
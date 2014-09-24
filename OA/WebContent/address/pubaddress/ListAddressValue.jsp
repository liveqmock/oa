<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>
<HTML>
<HEAD>
<TITLE>list</TITLE>
<script language="JavaScript" src="../js/common.js"></script>
</HEAD>

<%
String _page_num=request.getParameter("_page_num");
if(_page_num==null)
    _page_num="1";
List tempfieldlist = new ArrayList();
%>

<BODY>
<form name="form1" method="post" enctype="multipart/form-data" action="">
	<TABLE  border="4" bordercolor="#000000">
		<TR>
			<TABLE  align="center">
				<TR>
				<%
			try{
				tempfieldlist= (List)request.getAttribute("fieldlist");
				Iterator fieldlistiterator = tempfieldlist.iterator();
				while(fieldlistiterator.hasNext()){
					AddressFieldVO addressfieldvo=(AddressFieldVO)fieldlistiterator.next();

				%>
					<TD width="20%"><%=addressfieldvo.getName()%></TD>
		<%
				}	
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		%>
				</TR>
				<%
			try{
				List templist = (List)request.getAttribute("valuelist");
				Iterator list = templist.iterator();
				int i = 0;//循环变量
				while(list.hasNext()){
					AddressValueVO addressvaluevo=(AddressValueVO)list.next();
					i++;
					if (i==1){
				%>
				<TR>
				<%	
					}//if
					if (i<tempfieldlist.size()){
					
				%>
						<TD><%=addressvaluevo.getValueval()%></TD>
				<%
					}else{
					i = 0;
				%>
						<TD><%=addressvaluevo.getValueval()%></TD>
				</TR>
				<%
					}//else
				%>
				<%
				}//while
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		%>
				<TR>
				  <TD colspan="8"  align="center">
					<%@ include file="defaultPageScrollBar.jsp" %>
				  </TD>
				</TR>
			</TABLE>
		</TR>
		<TR>
			<TABLE>
				<TR>
				  <TD colspan="2" align="center" >增加/删除/修改 应用</TD>
				</TR>
		<%
				Iterator fieldlistiterator2 = tempfieldlist.iterator();
				while(fieldlistiterator2.hasNext()){
					AddressFieldVO addressfieldvo2=(AddressFieldVO)fieldlistiterator2.next();

				%>
				<TR>
				    <TD width="33%" align="right"><%=addressfieldvo2.getName()%>&nbsp;</TD>
					<TD width="33%"><input name="para" type="text" size="36" > </TD>
				</TR>
		<%
				}	
		%>
				  <input name="userid" type="hidden" size="36" class="txt1" >
				  <input type="hidden" name="_page_num" value="<%=_page_num%>">
			</TABLE>
		</TR>
		<TR>
			<TD>
			  <div align="center">
				<input name="add" type="button" value="增加" size="36" class="txt1" onclick="javascript:_Add()"> 
				<input name="add" type="button" value="修改" size="36" class="txt1" onclick="javascript:_Update()"> 
				<input name="add" type="button" value="删除" size="36" class="txt1" onclick="javascript:_Delete()">
			  </div>
			</TD>
	    </TR>
	</TABLE>
</form>
</BODY>
</HTML>
<script language="JavaScript">

 function _Add(){

  document.form1.action="/test/servlet/AddAddressSortServlet";
  document.form1.submit();
//  window.top.leftFrame.location.reload();
 }

 function _Update()
 {
   document.form1.action="/test/servlet/AlterAddressSortServlet";
   document.form1.submit();
	//window.top.leftFrame.location.reload();
  }
  function _Delete()
  {
    ok=confirm("此操作将删除该应用下的所有关联信息,您确定要进行吗?");
    if(ok){
     document.form1.action="/test/servlet/DelAddressSortServlet";
     document.form1.submit();
	//window.top.leftFrame.location.reload();
    }
    else{
      return false
    }
  }
function UpdateApp(userid)
{
 document.form1.userid.value   = userid;
}

</script>
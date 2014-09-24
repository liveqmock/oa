<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.filetransfer.vo.*"%>
<%
	String orgid = (String)request.getAttribute("orgid");
%>
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</HEAD>

<BODY text=#eeeeee bgColor=#ffffff leftMargin=0 background="<%=request.getContextPath()%>/images/bg-08.gif" topMargin=0 marginheight="0" marginwidth="0">

<form name="form1" method="post" action="">
<div align="center">
<br>
<%
String _page_num=request.getParameter("curPageNum");
if(_page_num==null)
    _page_num="1";

%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">
<%
       if(request.getParameter("orgname")!=null){
//     System.out.println("orgname = "+request.getParameter("orgname"));
%>
              <%=request.getParameter("orgname")%>
<%
}
%>
              人员邮箱大小列表</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>

		  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">	
<%
List list = (List)request.getAttribute("list");
if (!list.isEmpty()){
%>				
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
						<td height="22" align="center">选择</td>
                        <td width="2"  rowspan=<%=list.size()*3%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="0" height="2"></td>
                        <td width="40%" height="22"><div align="center" class="title-04">姓名</div></td>
                        <td width="2"  rowspan=<%=list.size()*3%>  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="0" height="2"></td>
                        <td width="50%" height="22"><div align="center" class="title-04">邮箱大小</div></td>
                        

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%

        Iterator result=list.iterator();

int index = 0;
        while(result.hasNext()){
            FiletransferSetVO filetransetvo=(FiletransferSetVO)result.next();
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
		String name = "";
		EntityManager entitymanger = EntityManager.getInstance();
		name = entitymanger.findPersonByUid(filetransetvo.getFsUid()).getFullName();

%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						
						<TD align="center"><input name="app" type="radio" value="<%=filetransetvo.getId()%>"  onClick='javascript:UpdateApp("<%=filetransetvo.getId()%>","<%=name%>","<%=filetransetvo.getFsSize()%>")'/></TD>
                        <td height="22"   class="text-01"><div align="center"><%=name%></div></td>
                        <td  class="text-01"><div align="center"><%=filetransetvo.getFsSize()%> </div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
        }//while
}else{
	if(("null").equals(request.getParameter("orgname"))||(request.getParameter("orgname")==null)){
%>
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="EEF4FF">
				<td height="22"  align="center" class="title-04">请从左边的导航栏选择</td>
			</tr>


<%		
	}else{
%>			
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="EEF4FF">
				<td height="22"  align="center" class="title-04"><%=request.getParameter("orgname")%>下没有人员</td>
			</tr>
<%
	}
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
				<%@ include file = "setSizePageScrollBar.jsp" %>
				</td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
		
          </table>
	
<BR>
<BR>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">修改邮箱大小</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          
           <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">	
	
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
                        <td width="40%" height="22"><div align="center" class="title-04">姓名</div></td>
                        <td width="0"  rowspan="10"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="0" height="2"></td>
                        <td width="61.8%" height="22"><div align="left" class="title-04"><input name="name" type="text" size="36" readonly></div></td>
					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
					  <tr  bgcolor="#FFFBEF">
                        <td height="22"><div align="center" class="title-04"> 邮箱大小</div></td>
                        <td height="22"><div align="left" class="title-04"><input name="size" type="text" size="36" ></div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>

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
                
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
    <br>
    <br>
				  <input name="id" type="hidden">
				   <input name="orgid" type="hidden" value="<%=orgid%>">
				  <input type="hidden" name="_page_num" value="<%=_page_num%>">
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onclick="javascript:_Update()">&nbsp;
          
          
<BR>
<BR>
<BR>
</div>
</form>
</BODY>
</HTML>

<script language="JavaScript">
 function _CheckForm(){
  if(!isDigital(document.form1.size.value)){
	alert("邮箱大小必须为整数！");
	document.form1.size.focus;
	return false
  }
   return true
 }

 function _Update()
 {
     if(!form1.app){
		alert("没有选择人员！");
      return false
    }
  if(!IsChecked(form1.app,"请选中一个人员！")){
      return false
   }
  if(!_CheckForm()){
    return false
   }
   document.form1.action="<%=request.getContextPath()%>/servlet/SetSizeServlet";
   document.form1.submit();
	//window.top.leftFrame.location.reload();
  }
function UpdateApp(id,name,size)
{
 document.form1.id.value   = id;
 document.form1.name.value   = name;
 document.form1.size.value   = size;
}

</script>
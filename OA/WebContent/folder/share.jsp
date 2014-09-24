<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.folder.control.*" %> 

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%
String folderId = request.getParameter("folderId");
String parentId = request.getParameter("parentId");
//out.print(parentId);
%>

<%
List List5= null; 
FolderShareVO vo5=new FolderShareVO();    
List5 = (List)request.getAttribute("list2");  
Iterator  List1Iterator5= null;   
if(List5!=null){     
 List1Iterator5 = List5.iterator();    
}

while(List1Iterator5.hasNext()){
  vo5= (FolderShareVO)List1Iterator5.next();
	}
String accessright = (String )request.getAttribute("accessright");	
%>

<%
List List1 = null; 
FolderPackageVO vo=new FolderPackageVO();    
List1 = (List)request.getAttribute("list1");  
Iterator  List1Iterator= null;   
if(List1!=null){     
 List1Iterator = List1.iterator();    
}
while(List1Iterator.hasNext()){
vo= (FolderPackageVO)List1Iterator.next();
	}
%>


<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>

<FORM name="Form_DeleteShare" action=""  method=post>
<input type="hidden" name="folderId" value="<%=request.getParameter("folderId")%>">
<input type="hidden" name="parentId" value="<%=request.getParameter("parentId")%>">	
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">已共享人员列表</td>
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
                        <td width="10%"  height="22"><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">姓名</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">共享类别</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="10%" ><div align="center" class="title-04">选择</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">姓名</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">共享类别</div></td>

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
List list=(List)request.getAttribute("list");
if(list!=null){
Iterator it = list.iterator();
SharePersonVO vo1=null;
SharePersonVO vo2=null;
int i=0;
while(it.hasNext()){
	++i;
	vo1=(SharePersonVO)it.next();
	if(it.hasNext()){
		vo2=(SharePersonVO)it.next();
	}
	if(i%2==1){
%>
                    <tr bgColor=#D6E7F7 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D6E7F7';">
<%
	}
	else{
%>
					<tr bgcolor="#F2F9FF"; onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';">
<%
		}
%>
						<td align="center" ><input type=checkbox name="personid" value="<%=vo1.getPersonuuid()%>"></td>
						<td height="22"  align="center"  class="text-01"><%=vo1.getCnname()%></td>
                        <td height="22"  align="center"  class="text-01"><%=("1".equals(vo1.getFscAccessright()))?"读写":"只读"%></td>				
						<td align="center" ><%=vo2!=null?("<input type=checkbox name=\"personid\" value=\""+vo2.getPersonuuid()+"\">"):""%></td>
						<td height="22"  align="center"  class="text-01"><%=vo2!=null?vo2.getCnname():""%></td>
                        <td height="22"  align="center"  class="text-01"><%=vo2!=null?(("1".equals(vo1.getFscAccessright()))?"读写":"只读"):""%></td>
                    </tr>  
                     <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<% vo2 =null;
}  vo1 =null;
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
</form>        
<table align="center" height="50">
          <tr>
            <td>
			<img src="<%=request.getContextPath()%>/images/botton-delete.gif" hspace="10"  onClick="javascript:_deleteShare()"  style="cursor:hand">
			</td>
          </tr>
        </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
   <FORM name=form1 action=""  method=post>
   
    <tr> 
      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif"> 
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05">文件共享</td>
            <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
          </tr>
        </table>
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
            <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
          
                      <tr> 
                        <td height="22"> <div align="right" class="text-01">选择共享人员：</div></td>
						 <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-18.gif" width="2" height="2"></td>
                        <td align="left" bgcolor="F2F9FF" class="text-01"> <textarea name="user" cols="30" rows="1"></textarea>
                           <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" align="absmiddle" style="cursor:hand" onclick="javascript:_selectUser('<%=request.getContextPath()%>')"></td>
                      </tr>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
					   <tr> 
                        <td height="22"> <div align="right" class="text-01">共享名：</div></td>
                        <td bgcolor="F2F9FF" class="text-01">
                          <div align="left" class="text-01">
                            <%   isSHARE dd=new isSHARE(new Integer(folderId)); %>
							<% if(!dd.jj){ %>
                            <input name="shareName" type="text"  value="<%= vo.getFpName() %>" size="32" class="text-01">
							<% }else{ 
								int nPosition=0;
								String shareName=vo5.getFsName();
			                	nPosition=shareName.lastIndexOf(":");
								shareName=(vo5.getFsName() ).substring(nPosition+1);
								
								%>
							 <input name="shareName" type="text"  value="<%= shareName  %>" size="32" class="text-01">
							 <% } %>
                          </div></td>
                      </tr>
					  <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
					 <!--  
					  <tr> 
                        <td height="22"> <div align="right" class="text-01">读写权限：</div></td>
                        <td bgcolor="F2F9FF" class="text-01">
                          <%  int www = -1;
                              if("1".equals(accessright)){ www=1;}
                              if("0".equals(accessright)){ www=0;}
                          %>
                          <div align="left" class="text-01"><input type="radio" name="write" value="no"<%if(www==0||www==-1){%>checked<%}%>>
                           只读 
                          <input name="write" type="radio" value="yes" <%if(www==1){%>checked<%}%>>
                          读写</div></td>
                      </tr>
                     
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
                       -->
                       <input name="write" type="hidden" value="no">
                    </table></td>
                </tr>
              </table></td>
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
          </tr>
        </table>
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
            <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"></div></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
        </table>
		<br>
		 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-ok.gif"  onclick="javascript:_shareFolder()" style="cursor:hand"> 
				<img src="<%=request.getContextPath()%>/images/botton-cancel.gif"  onClick="history.back()"  style="cursor:hand"> 
                <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand"> 
              </div></td>  
          </tr>
        </table>
       </td>       
    </tr>
		<INPUT TYPE="hidden" name="folderId" value="<%=folderId%>">
		<INPUT TYPE="hidden" name="parentId" value="<%=parentId%>">   
		<!--INPUT TYPE="hidden" name="userAddFlag" value="0"-->
	</form>
  </table>

</body>
</html>

<SCRIPT LANGUAGE="JavaScript">

function _shareFolder(){
    if(document.form1.shareName.value==""||document.form1.shareName.value==null){alert("请输入共享名");}
	else{
	  if( <%= (list!=null)&&list.size()>0%>||(document.form1.user.value!=null&&document.form1.user.value!="")){
	document.form1.action="<%=request.getContextPath()%>/servlet/ShareServlet";
	document.form1.submit();
	window.top.leftFrame.location.reload();
	}
	else{alert("请选择共享的人员");}}
}

function _selectUser(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addUser&sessionname=user','','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _deleteShare(){
	if(_check("请选择要删除共享的人员！")){
		Form_DeleteShare.action="<%=request.getContextPath()%>/servlet/DeleteShareServlet";
		Form_DeleteShare.submit();
	}
}

function _check(outString){
	var i;
	if(Form_DeleteShare.personid == undefined){
		alert(outString);
		return false;
	}
	if(Form_DeleteShare.personid.length == undefined){
		if(Form_DeleteShare.personid.checked){
			return true;
		}
	}
	else{
		for(i=0;i<Form_DeleteShare.personid.length;i++){
			if(Form_DeleteShare.personid[i].checked){
				return true;
			}
		}
	}
	alert(outString);
	return false;
}
/*function _resetUser(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_resetUserFlag&sessionname=user','','width=700,height=550,scrollbars=yes,resizable=yes');
}*/


function _addUser(user){
document.form1.user.value=user;
//document.form1.userAddFlag.value="1";
}

/*function _resetUserFlag(user){
document.form1.user.value=user;
document.form1.userAddFlag.value="2";
}*/
</SCRIPT>

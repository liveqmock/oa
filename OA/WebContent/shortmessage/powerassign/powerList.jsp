


<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.shortmessage.powerassign.vo.DXShortaccessOrgPersonVO" %>

<html>
<head>
<title>权限分配列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/common.js"></script>
<SCRIPT LANGUAGE="JavaScript">

function _Add(orgCnName,OrgCode)
{
	if(frmOrgList.CunrentCnName.value==""){
		alert("组织中文名称不能为空");
		frmOrgList.CunrentCnName.focus();
		return false;
	}
	if(frmOrgList.CunrentEnName.value==""){
		alert("组织英文名称不能为空");
		frmOrgList.CunrentEnName.focus();
		return false;
	}
	if(!(frmOrgList.SequenceNO.value=="")){

	  if(!isDigital(Trim(frmOrgList.SequenceNO.value))){
	     alert("显示序号要填写数字")
	     frmOrgList.SequenceNO.focus();
	     return false;
	  }
	}
	if(!(frmOrgList.SerialIndex.value=="")){
	   if(!isDigital(Trim(frmOrgList.SerialIndex.value))){
	     alert("序列序号要填写数字")
	     frmOrgList.SerialIndex.focus();
	     return false;
	   }
	}
	orgcn=orgCnName.split("&");
	for(i=0;i<orgcn.length;i++){
	    if(frmOrgList.CunrentCnName.value==orgcn[i]){
	       alert("组织中文名不能重复");
	       frmOrgList.CunrentCnName.focus();
	       return false;
	    }
	}
	orgen=OrgCode.split("&");
	for(i=0;i<orgen.length;i++){
	  if(frmOrgList.OrgCode.value==orgen[i]){
	     alert("组织代码不能重复");
	     frmOrgList.OrgCode.focus();
	     return false;
	  }
	}
	frmOrgList.action='/servlet/OrgAddServlet';
	//frmOrgList.submit();
	//window.setTimeout('', 1000);
	//window.top.leftFrame.location.replace("/servlet/OrgXmlTreeServlet?nodeUrl=/servlet/OrgListServlet");
}



function _Delete(url)
{
	
	var j;
	if(document.smForm.smChe== undefined){
		alert("没有可删除对象");		
	}
	else if( document.smForm.smChe.length == undefined){
        if(document.smForm.smChe.checked){
			document.smForm.action=url+"/servlet/DelSMPowerServlet";
			document.smForm.submit();	
			return;
		}
	}
	else{
	    for(j=0;j<document.smForm.smChe.length;j++){
	        if(document.smForm.smChe[j].checked){
			document.smForm.action=url+"/servlet/DelSMPowerServlet";
			document.smForm.submit();	
			return;
			}
		}

	}

	  alert("未选中删除项！");
	
}

function selectPerson(){

    window.open('/oabase/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=sendtoperson','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
    var depid=document.smForm.depid.value;
	document.smForm.action ="<%=request.getContextPath()%>"+"/servlet/AddSMPowerServlet?depid="+depid+"&sessionname=sendtoperson";
	document.smForm.submit();
	
}

</script>
</head>
<%
    
   try{
      String depid=(String)request.getAttribute("orgid");
      List list=(List)request.getAttribute("list");
      Iterator iter=list.iterator();
%>

 <body background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" leftmargin="0" topmargin="10">


<form name="smForm" method="post" action="" >


<input name="depid" type="hidden" value="<%=depid%>">

<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
    <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">短信权限分配_人员列表</td>
    <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
  </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
    <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%" align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;</td>
                <td width="2" rowspan="29" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                <td width="10%" height="22" align="center" bgcolor="#FFFBEF" class="title-04">序号</td>
                <td width="2" rowspan="29" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                <td width="40%" align="center" bgcolor="#FFFBEF" class="title-04">姓名</td>
                <td width="2" rowspan="29" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                <td width="45%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">所属组织</div></td>
        </tr>
        <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        </tr>
        <% int i=0;
           while(iter.hasNext()){
           DXShortaccessOrgPersonVO vo=(DXShortaccessOrgPersonVO)iter.next();
           i++;
           
           String color="#F2F9FF";
		   if(i%2!=0)
             color="#D8EAF8";
            
                     
        %>
        <tr bgColor=<%=color%> onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='<%=color%>';">
                <td height="22" align="center"  class="text-01"><input type="checkbox" name="smChe" value="<%=vo.getSaId()%>"></td>
                <td height="22" align="center"  class="text-01"><%=i%></td>
                <td   class="text-01"><div align="center"><%=vo.getCnnameper()%></div></td>
                <td  class="text-01"><div align="center"> <%=vo.getCnname()%></div></td>
        </tr>
        <tr>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
        </tr>
        
        <%
         }
     }
     catch(Exception e){
       out.print(e.getMessage());
   
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
      <%@ include file = "../../include/defaultPageScrollBar.jsp" %>
    </td>
    <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
  </tr>
</table>
<p align="center">	
     <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries_bak.gif" width="99" height="22" border="0" style="cursor:hand;" onClick="javascript:selectPerson()"> &nbsp;&nbsp;
     <img src="<%=request.getContextPath()%>/images/botton-delete.gif" width="70" height="22" border="0" style="cursor:hand;" onclick="javascript:_Delete('<%=request.getContextPath()%>')"> 
</p>
</form>
</body>
</html>

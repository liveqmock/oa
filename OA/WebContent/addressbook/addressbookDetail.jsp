<%@ page contentType="text/html; charset=gb2312" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);  
%>

<%@page import="com.icss.oa.addressbook.vo.AddressbookContentVO" %>

<%
String parentId = request.getParameter("parentId");
AddressbookContentVO avo = (AddressbookContentVO)request.getAttribute("abcvo");

%>


<html>
<head>
<title>联系人详细信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>


 <FORM name=form1 action=""  method=post>
 <input name="abcid" type="hidden" value="<%=avo.getAbcId()%>"> 
 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
 <tr>
      <td width="" valign="top">
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            		<td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">&#26032;&#22686;&#36890;&#35759;&#24405;</td>
            		<td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
          	</tr>
        	</table>
        	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            		<td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            		<td width="100%">
              				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                			<tr>
                  				<td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                  					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="100" height="22" class="text-01" align="right">姓名：</td>
                                        <td width="2" rowspan="13" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcname" type="text" value="<%=avo.getAbcName()%>" size="29">
                                        </td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" height="22" class="text-01" align="right">单位：</td>
                                        <td width="2" rowspan="11" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abccompany" type="text" value="<%if(avo.getAbcCompany()==null) out.print(""); else out.print(avo.getAbcCompany());%>" size="29">
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
                                        <td width="100" height="22" class="text-01" align="right">单位地址：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abccompanyaddress" type="text" value="<%if(avo.getAbcCompanyaddress()==null) out.print(""); else out.print(avo.getAbcCompanyaddress());%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">家庭住址：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcfamilyaddress" type="text" value="<%if(avo.getAbcFamilyaddress()==null) out.print(""); else out.print(avo.getAbcFamilyaddress());%>" size="29"></td>
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
                                          <input name="abccompanytel" type="text" value="<%if(avo.getAbcCompanytel()==null) out.print("");else out.print(avo.getAbcCompanytel());%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">住宅电话：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcfamilytel" type="text" value="<%if(avo.getAbcFamilytel()==null) out.print(""); else out.print(avo.getAbcFamilytel());%>" size="29"></td>
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
                                          <input name="abccellphone" type="text" value="<%if(avo.getAbcCellphone()==null) out.print(""); else out.print(avo.getAbcCellphone());%>" size="29"></td>
                                        <td width="100" height="22" class="text-01" align="right">电子邮件：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcemail" type="text" value="<%if(avo.getAbcEmail()==null) out.print(""); else out.print(avo.getAbcEmail());%>" size="29"></td>
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
                                        <td width="100" height="22" class="text-01" align="right">职务级别</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abclevel" type="text" value="<%if(avo.getAbcLever()==null) out.print(""); else out.print(avo.getAbcLever());%>" size="29">
                                        </td>
                                        <td width="100" height="22" class="text-01" align="right">备用电子邮件：</td>
                                        <td width="100" bgcolor="F2F9FF" class="text-01">
                                          <input name="abcemailsec" type="text" value="<%if(avo.getAbcEmailsec()==null) out.print(""); else out.print(avo.getAbcEmailsec());%>" size="29">
                                        </td>
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
                                        <td width="100" height="22"  class="text-01" align="right">备注</td>
                                        <td bgcolor="F2F9FF" colspan="5" class="text-01"><textarea name="abcmemo"   cols="50" rows="6"><%if(avo.getAbcMemo()==null) out.print(""); else out.print(avo.getAbcMemo());%></textarea>
                                        </td>
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
            		<td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
            		<td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
          	</tr>
        	</table>
    </tr>
  	</table>
  	
  			<br>
		 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
		    <td> 
			    <div align="center">
				<img src="<%=request.getContextPath()%>/images/botton-update.gif"  onclick="javascript:_updateAddressbook('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> &nbsp;&nbsp;
                <img src="<%=request.getContextPath()%>/images/botton-return.gif"  onClick="history.back()"  style="cursor:hand"> 
              </div></td>  
          </tr>
        </table>
  	
  	
</form>


</body>
</html>

<script language="JavaScript">
 <!--
 //IsDigital函数判断一个字符串是否由数字(int or long)组成
 function isDigital(str)
 {
    for(ilen=0;ilen<str.length;ilen++)
    {
        if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    	{
       		return false;
    	}
  	}
  	return true;
 }
 
 function _updateAddressbook(url,parentId){
    if(document.form1.abcname.value == ""){
        alert("姓名不能为空！");
        return false;
    }
    
    if(document.form1.abccompanytel.value!=""){
        if(!isDigital(document.form1.abccompanytel.value)){
           alert("电话号码只能由数字组成！");
           return false;
        }
    }
    
    if(document.form1.abcfamilytel.value!=""){
        if(!isDigital(document.form1.abcfamilytel.value)){
            alert("电话号码只能由数字组成！");
            return false;
        }
    }
    
    if(document.form1.abccellphone.value!=""){
        if(!isDigital(document.form1.abccellphone.value)){
            alert("电话号码只能由数字组成！");
            return false;
        }
    }
    
	document.form1.action=url+"/servlet/UpdateAddressbookServlet?parentId="+parentId;
    document.form1.submit();
	window.top.leftFrame.location.reload();
	
 }
 //-->
 </script>
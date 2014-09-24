<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.shortmessage.partrecord.vo.DuanxinShortaccessSysOrgVO" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>写短信</title>
<SCRIPT language=JavaScript>
<!--


function checkForm() { 
  if(document.duanxinForm.content.value==""||document.duanxinForm.haoma.value==""){
     alert("短信内容为空或未添写手机号码!");
	 return false;
  }
  window.open('success.htm','','height=250 width=400');
}

//-->
</SCRIPT>
</style>
<script language="JavaScript">


    function calclength(s)
    {
        var count = 0;
        for (var i=0;i<s.length;i++)
        {
            if (s.charAt(i) >=' ' && s.charAt(i)< '~')
            {
             count ++;
            } else
            {
             count = count +2;
            }
        }
        return count;
   
    }
   
  

    function calcword()
    {
        len = calclength(document.all.message.value);
		document.all.wordlabel.innerHTML = len;
    }

  
    function checkword()
    {
        calcword();
		if (calclength(document.all.message.value) > 200)
        {
            alert("正文超长!");
            //document.all.message.value = subs(document.all.message.value,40);
            document.all.wordlabel.innerHTML = "200";
            return false;
        }
        return true;

    }
    
    function selectMobile(){
   		window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addMobile&sessionname=sendtoperson','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
	}

    function _addMobile(usernamestring){
      //var depid=document.smForm.depid.value;
	  //document.smForm.action ="<%=request.getContextPath()%>"+"/servlet/SendSMServlet?sessionname=sendtoperson";
	  //document.smForm.submit();
	  document.smForm.mobile.value=usernamestring;
	  return true;
	
    }
    
    function _sendSM(){
      //TODO 发送接口
      document.smForm.action ="<%=request.getContextPath()%>"+"/servlet/SendSMServlet?sessionname=sendtoperson";
      document.smForm.submit();
    
    }
</script> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<%
   try{
      List list=(List)request.getAttribute("list");
     
      Iterator iter=list.iterator();
      
      
 
%>
<form name="smForm"  action='' method='POST'>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22" /></td>
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 短信编辑</td>
      <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22" /></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="images/bg-21.gif" width="1" height="4" /></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr  bgcolor="#FFFBEF">
                  <td width="50%" height="22" > <font class="title-04">&nbsp;发送内容:</font>已写入字符数<font color="red"><span id="wordlabel">0</span></font></td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr >
                  <td height="22" bgcolor="#F2F9FF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="unnamed1">&nbsp;选择以哪个部门身份发送&nbsp;</td>
                        <td> 
             <select name="dep">
                        
          <%  
               while(iter.hasNext()){
                  
                   DuanxinShortaccessSysOrgVO vo = (DuanxinShortaccessSysOrgVO)iter.next();
                   //out.print("ssssss"+vo.getCnname());
                %>
                  
                <option value="<%=vo.getAccessdepid()%>"><%=vo.getCnname()%></option>   
                <%   
              
              }
              %>
              </select>
              <%
           }
           catch(Exception e){
               out.print(e.getMessage());
   
           }   
          %>            
                       
                            
                        
                        </td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                </tr>
                <tr  bgcolor="#F2F9FF"; >
                  <td height="22" bgcolor="#F2F9FF" class="text-01"><table width="100%"  border="0" cellspacing="0" cellpadding="5">
                      <tr>
                        <td width="41%">
						<textarea name="message" cols="50"  rows="6"  onkeydown="checkword();"  >
                        </textarea>
						</td>
                        <td width="59%" valign="top" class="unnamed1">&nbsp;&nbsp;&nbsp;&nbsp;在此输入您的短信内容，短信内容和署名的内容相加用户最多可输入140个英文字符或69个中文字符（包括数字、英文、标点符号)</td>
                      </tr>
                  </table></td>
                </tr>
                <tr  bgcolor="#F2F9FF"; >
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="#F2F9FF" class="text-01"></td>
                </tr>
                <tr  bgcolor="#F2F9FF"; >
                  <td height="22" bgcolor="#F2F9FF" class="text-01"><table  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="unnamed1">&nbsp;接收方手机号码:&nbsp;</td>
                        <td><input type="text" name="mobile" size="20"></td>
                        <td>&nbsp;
                            <IMG  name="select_per"  onClick="javascript:selectMobile()" style="cursor:hand" src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" width="93" height="19" /></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr>
                  <td height="22" align="center">&nbsp;</td>
                </tr>
                <tr>
                  <td height="22" align="center"><input  name="ss"  type="image" onClick="javascript:_sendSM()"  src="<%=request.getContextPath()%>/images/send.gif" width="70" height="22" /></td>
                </tr>
            </table></td>
          </tr>
      </table></td>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="images/bg-22.gif" width="1" height="4" /></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21" /></td>
      <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
      <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
      <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21" /></div></td>
    </tr>
  </table>
  <span class="text-01">&nbsp;</span>
</form>
</body>
</html>

<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.shortmessage.maintenance.vo.*" %>


<%

  List orgcodeList = null;
  DuanxinShortmappingSysOrgVO vo=new DuanxinShortmappingSysOrgVO();
  orgcodeList = (List)request.getAttribute("list");
  Iterator  orgcodeIterator=null;
  if(orgcodeList!=null){
    orgcodeIterator = orgcodeList.iterator();
  }
  
%>


<HTML><HEAD><TITLE>�ļ�����</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312"><LINK 
href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js">
</SCRIPT>
<SCRIPT language=JavaScript>
 
   function _addCode(url){

	if (document.form2.zuzhi2.value == "")
	{			
		alert('��ѡ����֯!');
		document.form2.zuzhi2.focus();
		return false;
	}
	if (document.form2.shortMessCode.value == "")
	{			
		alert('����д���ź�!');
		document.form2.shortMessCode.focus();
		return false;
	}
	document.form2.action=url+"/servlet/AddOrgSMCodeServlet";
	document.form2.submit();
	//location.href='javascript:history.go(-1)';
}

function _selectOrg(){
	window.open('<%=request.getContextPath()%>/servlet/PopupOrgTreeServlet?nodeFunc=_addOrgForCode','','width=400px,height=500px,scrollbars=yes,left=200,top=100,resizable=yes');
}


function _delete1(url){
  
    if(!form2.radiobutton){
		alert("û��ѡ����飡");
      return false
    }
    if(!IsChecked(form2.radiobutton,"��ѡ��һ�����飡")){
      return false
    }
    ok=confirm("�˲�����ɾ����Ӧ���µ����й�����Ϣ,��ȷ��Ҫ������?");
    if(ok){
     document.form2.action=url+"/servlet/DelOrgSMCodeServlet";
     document.form2.submit();
	
    }
    else{
      return false
    }
   
}


function UpdateApp(orgid,orgname,smcode)
{
	 document.form2.zuzhiId.value    = orgid;
	 document.form2.zuzhi2.value   = orgname;
	 document.form2.shortMessCode.value   = smcode;
}

function _update(url){
    if(!form2.radiobutton){
		alert("û��ѡ����飡");
      return false
    }
    if(!IsChecked(form2.radiobutton,"��ѡ��һ�����飡")){
      return false
    }
    ok=confirm("�˲������޸ĸ�Ӧ���µ����й�����Ϣ,��ȷ��Ҫ������?");
    if(ok){
     document.form2.action=url+"/servlet/UpdateOrgSMCodeServlet";
     document.form2.submit();
	
    }
    else{
      return false
    }
  }
  
  function _addOrgForCode(orgid,orgname){
     document.form2.zuzhiId.value    = orgid;
	 document.form2.zuzhi2.value   = orgname;
  
  }
   
   
</SCRIPT>

</HEAD>

<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif topMargin=10>

<div align="center">
  <form name="form2" method="post" action="">
    <div align="center">
    <center>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">��֯���ź��б�</td>
            <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
          </tr>
      </table>
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            <td><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="5%" align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;</td>
                        <td width="2" rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                        <td width="50%" height="22" align="center" bgcolor="#FFFBEF" class="title-04">��֯����</td>
                        <td width="2" rowspan="50" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                        <td width="45%" align="center" bgcolor="#FFFBEF" class="title-04">���ű��</td>
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
                      <% 
	         int i=0;
             while(orgcodeIterator.hasNext()){
	           vo = (DuanxinShortmappingSysOrgVO)orgcodeIterator.next();
	           i++;
			  
		     String color="#F2F9FF";
			

            %>
            
            <% 
                if(i%2!=0)
                color="#D8EAF8";

            %>
                      <tr bgColor=<%=color%> onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='<%=color%>';">
                        <td height="22" align="center"  class="text-01"><input name="radiobutton" type="radio" value="<%=vo.getSmId()%>" onclick="javascript:UpdateApp('<%=vo.getDepid()%>','<%=vo.getCnname()%>','<%=vo.getSmCode()%>')"></td>
                        <td height="" align="center"  class="text-01"><a href="#"><%=vo.getCnname()%></a></td>
                        <td   class="text-01"><div align="center"><%=vo.getSmCode()%></div></td>
                      </tr>
                      
            
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
                      
               <%
                 } //while  
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
        <p>&nbsp;</p>
     
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">��֯���ź����/�޸�/ɾ��</td>
            <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
          </tr>
      </table>
      
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
            <td><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="40%" align="center" bgcolor="#FFFBEF" class="title-04">&nbsp;</td>
                        <td width="2" rowspan="19" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                        <td width="60%" height="22" align="center" bgcolor="#FFFBEF" class="title-04">��֯����</td>
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
                      <tr bgColor=#D8EAF8 >
                        <td height="22" align="center"  class="text-01">��֯����</td>
                        <td height="" align="center"  class="text-01">
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="hidden" name="zuzhiId" value="">
						<input type="text" name="zuzhi2"  size="20" readonly value="">
						 <img src="<%=request.getContextPath()%>/images/choice_org.gif" border=0 style="cursor:hand" onclick="javascript:_selectOrg()" >
						</td>
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      </tr>
                      <tr bgcolor="F2F9FF"  >
                        <td height="22" align="center" class="text-01" >���ź�</td>
                        <td height="" align="center" class="text-01" >&nbsp;
                        <input name="shortMessCode" type="text" size="20" ></td>
                      </tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
            <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp; </td>
            <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"> </div></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
      </table>
        <div align="center"><br>
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onclick="_addCode('<%=request.getContextPath()%>');" >&nbsp; 
    <img onClick="javascript:_update('<%=request.getContextPath()%>')" src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="">&nbsp;
    <img onClick="javascript:_delete1('<%=request.getContextPath()%>')"  src="<%=request.getContextPath()%>/images/botton-delete.gif" style="cursor:hand"  border=0><br>
  </div>
    </center>
  </form>
  
  
</div>
<%   
     
       String flag=(String)request.getAttribute("flag");
       //out.println("ffffffffffffff"+flag);
       if(flag.equals("1")){
       
  %>
  <SCRIPT language=JavaScript>
  alert("����֯�ѷ�����źŻ�˶��ź��Ѿ�����")
  </SCRIPT>
 <% }
  %>
</body>
</html>

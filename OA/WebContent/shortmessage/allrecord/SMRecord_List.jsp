<%@ page contentType="text/html; charset=gb2312" %>


<%@ page import="java.util.*"%>
<%@ page import="java.text.*" %>
<%@ page import="com.icss.oa.shortmessage.allrecord.vo.DXHistoryOrgPersonVO"%>
<HTML><HEAD><TITLE>已发送短信列表</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<script language="JavaScript">

<!--
function EY_LISTMAIL_ONCLICK(m) 
{
 document.smForm.mov_action.value = m.name;
}
function EY_smForm_BAK(folder)
{
 //  var folder = folder;
   document.smForm.action = "/cgi-bin/backup?folder=" + folder ;
  // document.smForm.submit();
}
function CheckAll()
  {
    for (var i=0;i<document.smForm.elements.length;i++)
    {
      var e = document.smForm.elements[i];
      if (e.name != 'allbox')
        e.checked = document.smForm.allbox.checked;
    }
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
			document.smForm.action=url+"/servlet/DelSMRecordServlet";
			document.smForm.submit();	
			return;
			}
		}

	}

	  alert("未选中删除项！");
	
}
//-->

</script>

<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>
<%
    List list=(List)request.getAttribute("list");
    Iterator iter=list.iterator();
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
%>
<body  background="<%=request.getContextPath()%>/images/bg-08.gif"leftmargin="20" >
<div align="left"> 
<form name="smForm" method="POST"  action="">
    <input type="hidden" name="orgid" value="<%=request.getAttribute("orgid")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
        <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">已发送短信列表</td>
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
                    <td width="5%" align="center" bgcolor="#FFFBEF"  class="title-04">&nbsp;</td>
                    <td width="2" rowspan="30" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                    <td width="10%" height="22" align="center" bgcolor="#FFFBEF"  class="title-04">序号</td>
                    <td width="2" rowspan="30" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                    <td width="15%" align="center" bgcolor="#FFFBEF"  class="title-04">发送至</td>
                    <td width="2" rowspan="30" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                    <td width="10%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">日期</div></td>
                    <td width="0%" rowspan="30" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                    <td width="35%" align="center" bgcolor="#FFFBEF" class="title-04">内容</td>
                    <td width="0%" rowspan="30" align="center" background="<%=request.getContextPath()%>/images/bg-24.gif" class="title-04"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                    <td width="10%" align="center" bgcolor="#FFFBEF" class="title-04">发送人</td>
                    <td width="0%" rowspan="30" align="center" background="<%=request.getContextPath()%>/images/bg-24.gif" class="title-04"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                    <td width="15%" align="center" bgcolor="#FFFBEF" class="title-04">发送部门</td>
                  </tr>
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  </tr>
                  
                  <%
                    int i=0;
                   while(iter.hasNext()){
                     DXHistoryOrgPersonVO  vo=(DXHistoryOrgPersonVO)iter.next();
                     i++;
                     Long date=vo.getShDate();
                     String formatTime = formatter.format(new Date(date.longValue()));
                     //content 应该处理,取出一定的长度TODO
                  %>
                  <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                    <td height="22" align="center"  class="text-01"><input type="checkbox" name="smChe" value="<%=vo.getShId()%>"></td>
                    <td height="22" align="center"  class="text-01"><%=i%></td>
                    <td   class="text-01"><div align="center"><%=vo.getMobile()%></div></td>
                    <td  class="text-01"><div align="center"> <%=formatTime%> </div></td>
                    <td align="center" ><div align="left"><a href="#" onclick="javascript:window.open('content_duanxin.htm','','height=250, width=350');" title="请在下午3:00前大家把上个月的总结发到局的">·<%=vo.getShContent()%>..</a></div></td>
                    <td align="center" ><%=vo.getSenderCnname()%></td>
                    <td align="center" ><%=vo.getCnname()%></td>
                  </tr>
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
        <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"> 首 页│上一页│下一页│尾 页 </td>
        <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"> 第1页/共1页 共2条 </div></td>
        <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
                     <td width="28%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">选中所有的短信</a>
                     </td>
                     <td width="72%">
                        <!--<p align="center"> //-->
                        
              <p align="left"> 
                <img src="<%=request.getContextPath()%>/images/botton-delete.gif" height="22" border=0 style="cursor:hand"  onclick="javascript:_Delete('<%=request.getContextPath()%>')">
                
			
        </td>
                                   
      </tr>
    </table>
            


</form>
</div>
<br><br><br>
</body>
</html>
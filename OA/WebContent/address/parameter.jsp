<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</style>
<title>范围参数列表</title>
</head>
<script language="JavaScript">
function _checkPage(){
<%
		int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
		int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
		if(curPageNum1>pageCount1){
			curPageNum1=pageCount1;
			String url=request.getContextPath()+"/servlet/BoundListServlet?_page_num="+curPageNum1;
%>
		this.location="<%=url%>";
<%
		}
%>
}
function IsCheck(){
	if(document.form_update.ParaId.value  == "null"){
    	alert("请选择一条记录！");
		return false;
	}
	return true;
}
function IsFormItemEmpty(){
  	if(document.form_update.ParaCnName.value==""){
    	alert("请填写范围参数中文名称！");
    	return true;
    }
    if(document.form_update.ParaSQLDescrptor.value==""){
    	alert("请填写范围参数的SQL描述！");
    	return true;
    }
     if(document.form_update.ParaName.value==""){
    	alert("请填写范围参数的英文名称！");
    	return true;
    }
	return false;
} 
function _Add(){
	if(!IsFormItemEmpty()){
    	document.form_update.action="<%=request.getContextPath()%>/servlet/AddParaServlet";
  		document.form_update.submit();
 	}
}
function _Update(){
	 if(IsCheck()){
     	if(!IsFormItemEmpty()){
			document.form_update.action="<%=request.getContextPath()%>/servlet/UpdateParaServlet";
			document.form_update.submit();
		}
    }
}
function _Delete(){
    if(IsCheck()){
		document.form_update.action="<%=request.getContextPath()%>/servlet/DelParaServlet";
    	document.form_update.submit();
    }
}
function UpdateSubSystem(ParaID,ParaCnName,ParaName,ParaSQL)
{	
	document.form_update.ParaId.value   = ParaID;
    document.form_update.ParaCnName.value   = ParaCnName;
    document.form_update.ParaName.value   = ParaName;
    document.form_update.ParaSQLDescrptor.value   = ParaSQL;
}
</script>

<%  
    List list1 = (List)request.getAttribute("list1");
    if(list1==null){ list1 = (List)request.getAttribute("list"); }
	Iterator it1 = list1.iterator();
	%>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
<script language="javascript">
_checkPage()
</script>
<form name="form_list" action="" method="post">
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">范围参数列表</td>
      <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
   	<tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
		  	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
            	<tr>
                      <td width="5%" bgcolor="#FFFBEF"></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="5%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">序号</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="15%" bgcolor="#FFFBEF"><div align="center" class="title-04">范围参数中文名称</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="15%" bgcolor="#FFFBEF"><div align="center" class="title-04">范围参数英文名称</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="60%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">范围SQL</div></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
	List list = (List)request.getAttribute("list");
	Iterator it = list.iterator();
	int i=0;
	while(it.hasNext()){
		++i;
		ParameterVO vo = (ParameterVO)it.next();
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
			
						<td height="22" class="text-01" align="center"><input name="ParaID" type="radio" value="radiobutton"
							 onClick='javascript:UpdateSubSystem("<%= vo.getId() %>","<%= vo.getParaCnname()%>","<%= vo.getParaName() %>","<%= vo.getParaSql() %>")'></td>
                      	<td class="text-01" align="center"><%= vo.getId()%></td>
                      	<td class="text-01" align="center"><%= vo.getParaCnname()%></td>
                      	<td class="text-01" align="center"><%= vo.getParaName()%></td>
                      	<td class="text-01" align="center"><%= vo.getParaSql()%></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
		}
%>
              </table>
			</td>
           </tr>
        </table></td>
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
  </form>
  <form name="form_update" method="post">
  	<input name="ParaId" type="hidden" value="null">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 
        添加/修改/删除 范围参数</td>
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
                  <td width="20%" align="right">范围参数中文名称：</td>
                  <td width="2" rowspan="7" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="30%" bgcolor="#F2F9FF"><input name="ParaCnName" type="text" class="txt3" size="26" maxlength="64"> 
                    *    范围参数英文名称：<input name="ParaName" type="text" class="txt3" size="26" maxlength="64"> 
                    *</td>
                  
                </tr>
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr > 
                  <td align="right">参数SQl描述：</td>
                  <td bgcolor="#F2F9FF"  class="text-01"><textarea name="ParaSQLDescrptor" cols="80" rows="3" wrap="VIRTUAL" class="txt2" id="textarea"></textarea></td>
                  
                </tr>
                
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td colspan="6" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                </tr>
              </table></td>
              </tr>
          </table></td>
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
    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand"  onClick="_Delete()"><br>
  </div>

</form>
</body>
</html>

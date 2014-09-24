<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.commsite.vo.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" /><LINK 
href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<title>常用站点</title>
</head>
<script language="JavaScript">
function _checkPage(){
<%
		int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
		int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
		if(curPageNum1>pageCount1){
			curPageNum1=pageCount1;
			String url=request.getContextPath()+"/servlet/ListWebPageServlet?_page_num="+curPageNum1;
%>
		this.location="<%=url%>";
<%
		}
%>
}
function IsCheck(){
	if(document.form_update.ocsId.value  == "null"){
    	alert("请选择一条记录！");
		return false;
	}
	return true;
}
function IsFormItemEmpty(){
  	if(document.form_update.ocsName.value==""){
    	alert("请填写站点名称！");
    	return true;
    }
    if(document.form_update.ocsIndex.value==""){
    	alert("请填写序号！");
    	return true;
    }
 	if(document.form_update.ocsLink.value==""){
    	alert("请填写站点链接！");
    	return true;
    }
	return false;
} 
function _Add(){
	if(!IsFormItemEmpty()){
    	document.form_update.action="<%=request.getContextPath()%>/servlet/AddWebPageServlet";
  		document.form_update.submit();
 	}
}
function _Update(){
	 if(IsCheck()){
     	if(!IsFormItemEmpty()){
			document.form_update.action="<%=request.getContextPath()%>/servlet/UpdateWebPageServlet";
			document.form_update.submit();
		}
    }
}
function _Delete(){
    if(IsCheck()){
		document.form_update.action="<%=request.getContextPath()%>/servlet/DeleteWebPageServlet";
    	document.form_update.submit();
    }
}
function UpdateSubSystem(ocsId,ocsName,ocsIndex,ocsLink,ocsDes,havelogo)
{
 	document.form_update.ocsId.value   = ocsId;
 	document.form_update.ocsName.value   = ocsName;
 	document.form_update.ocsIndex.value   = ocsIndex;
	document.form_update.ocsLink.value = ocsLink;
    document.form_update.ocsDes.value = ocsDes;
    document.form_update.logourl.src = "DownLoadWebPageServlet?ocsId="+ocsId;
    if(!havelogo){
    	document.form_update.check.checked=true;
    }
    else{
    	document.form_update.check.checked=false;
    }
}
function _changeImage(){
	//alert(document.form_update.ocsLogo.value);
	document.form_update.logourl.src = "file:\\"+document.form_update.ocsLogo.value;
    document.form_update.check.checked=false;
}
function _changecheck(){
	if(document.form_update.check.checked==true){
		document.form_update.logourl.src = "DownLoadWebPageServlet";
	}
}
</script>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">

<script language="javascript">
_checkPage()
</script>

<form name="form_list" action="" method="post">
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">新华社内部网常用站点</td>
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
                      <td width="30%" bgcolor="#FFFBEF"><div align="center" class="title-04">站点名称</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="30%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">站点链接</div></td>
					  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="30%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">站点描述</div></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
		CommsiteVO vo = (CommsiteVO)it.next();
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
						<td height="22" class="text-01" align="center"><input name="websiteid" type="radio" value="radiobutton"
							 onClick='javascript:UpdateSubSystem("<%= vo.getOcsId()%>","<%= vo.getOcsName() %>","<%= vo.getOcsIndex()%>",
							 "<%= vo.getOcsLink() %>","<%= vo.getOcsDes() %>",<%=(vo.getOcsLogo()==null)?"false":"true"%>)'></td>
                      	<td class="text-01" align="center"><%= vo.getOcsIndex()%></td>
                      	<td class="text-01" align="center"><%= vo.getOcsName() %></td>
                      	<td class="text-01" align="center"><%= vo.getOcsLink() %></td>
                      	<td class="text-01" align="center"><%= vo.getOcsDes() %></td>
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
  <form name="form_update" action="" method="post" enctype="multipart/form-data">
  	<input name="ocsId" type="hidden" value="null">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">
          添加/修改/删除 常用站点</td>
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
                  <td width="16%" align="right">站点名称：</td>
                  <td width="2" rowspan="7" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="57%" bgcolor="#F2F9FF"><input name="ocsName" type="text" class="txt3" size="26" maxlength="64"> 
                    &nbsp;*</td>
                  <td width="2" rowspan="5" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="12%" class="text-01" align="right">序号：</td>
                  <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="15%" bgcolor="#F2F9FF"><input name="ocsIndex" type="text" class="txt3" value="" size="3" maxlength="2" /> 
                    &nbsp;* </td>
                </tr>
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr  > 
                  <td align="right" class="text-01">站点链接：</td>
                  <td bgcolor="#F2F9FF"  class="text-01"><input name="ocsLink" type="text" class="txt3" id="link2" value="" size="55" > 
                    &nbsp;*</td>
                  <td align="center" colspan="3" class="text-01">Logo预览</td>
                </tr>
                <tr> 
                  <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr > 
                  <td align="right">站点描述：</td>
                  <td bgcolor="#F2F9FF"  class="text-01"><textarea name="ocsDes" cols="50" rows="4" wrap="VIRTUAL" class="txt2" id="textarea"></textarea></td>
                  <td colspan="3" align="center">
				    <TABLE style="TABLE-LAYOUT: fixed; WORD-BREAK: break-all" width="96%" align=center border=0>
					  <tr>
					    <td align="center">
		  				  <img id="logourl" src="DownLoadWebPageServlet" height="60">
						</td>
					  </tr>
					</table>
				  </td>
                </tr>
                <tr> 
                  <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr> 
                  <td align="right">站点图标：</td>
                  <td colspan="5" bgcolor="#F2F9FF"  class="text-01"><input name="ocsLogo" size="45" type="file" class="txt2" onchange="_changeImage()">
                    (建议图片最佳效果126×60)&nbsp; <input type="checkbox" name="check" value="check" checked onclick="javascript:_changecheck()"/>
                    使用默认站点图标</td>
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

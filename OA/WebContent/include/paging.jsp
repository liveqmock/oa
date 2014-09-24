<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.core.web.BackURLDecoder"%>
<%@ page import="com.icss.core.web.HTMLGenerator"%>
<%@ page import="com.icss.core.db.PagingInfo" %>
<%
PagingInfo pagingInfo = (PagingInfo) request.getAttribute("pagingInfo");
String encodingUrl = pagingInfo.getEncodingURL();
String decodeUrl = BackURLDecoder.decode(encodingUrl);
/**
 * ����backurl����һ�����ص�form
 * @param fromName Ҫ���ɵ�form��name
 * @param formId   Ҫ���ɵ�form��id
 * @param url  ����
 * @return ���ɵ�form��html����
 */
%>
<%=HTMLGenerator.getHiddenFormHtml("paging_form","paging_form",decodeUrl)%>
<script language="javascript">
function _goPage(page)
{ 
	var re = /^[0-9]*[1-9][0-9]*$/;
	//var re = /^-?[1-9]*(\.\d*)?$|^-?0(\.\d*)?$/;
   
	if (page=="" || !re.test(page))
	{
		alert("������ҳ������ҳ������Ϊ��������");
		return false;
	}
	else if(page<=0)
	{
		alert("ҳ������Ϊ������������������");
		return false;
	}
	else if(page><%=pagingInfo.getTotalPageCount()%>)
	{
		alert("ҳ�����ܴ������ҳ��������������");
		return false;
	}
	
	if(document.paging_form.pageno == undefined)
	{
		document.paging_form.action = document.paging_form.action + '?pageno=' + page;
	}
	else
	{
		document.paging_form.pageno.value = page;
	}
	
	document.paging_form.submit();
}


function changepageSize(pageSize)
{ 
	var re = /^[0-9]*[1-9][0-9]*$/;
	//var re = /^-?[1-9]*(\.\d*)?$|^-?0(\.\d*)?$/;
	if (pageSize=="" || !re.test(pageSize))
	{
		alert("������ÿҳ��¼��������ҳ������Ϊ��������");
		return false;
	}
	else if(pageSize<=0)
	{
		alert("ÿҳ��¼��������Ϊ������������������");
		return false;
	}
	
	
	if(document.paging_form.pageSize == undefined)
	{
		document.paging_form.action = document.paging_form.action + '?pageSize=' + pageSize;
	}
	else
	{
		document.paging_form.pageSize.value = pageSize;
	}
	
	document.paging_form.submit();
}


</script>
<DIV>
    <%
	if (pagingInfo.isFirstPage()) {
	%> 
		<span>��ҳ</span>&nbsp;<span>��һҳ</span>&nbsp;
	<%}else{ %>
		<a href="javascript:_goPage(1)"><span>��ҳ</span></a>&nbsp;<a href="javascript:_goPage(<%=pagingInfo.getCurrentPageNo()-1%>)"><span>��һҳ</span></a>&nbsp;
	<%}
	if(pagingInfo.isLastPage()){
	 %>
	 <span>��һҳ</span>&nbsp;<span>ĩҳ</span>&nbsp;	 
	<%}else{ %>
	<a href="javascript:_goPage(<%=pagingInfo.getCurrentPageNo()+1%>)"><span>��һҳ</span></a>&nbsp;<a href="javascript:_goPage(<%=pagingInfo.getTotalPageCount()%>)"><span>ĩҳ</span></a>	     
	<%}%>
	ÿҳ��ʾ<input name="pageSize" id="pageSize" value="<%=pagingInfo.getPageSize()%>" type="text" class="inputtxt" size="4" style="width:30px;height:14px;" >�� <input type="button" value="ȷ��" style="font-size:12px;" onclick="changepageSize(document.getElementById('pageSize').value)"> &nbsp;&nbsp;����<input name="__gopage" id="__gopage" type="text" class="inputtxt" size="4" style="width:30px;height:14px;" >ҳ&nbsp;<input type="button" value="ȷ��" style="font-size:12px;" onclick="_goPage(document.getElementById('__gopage').value)">
	&nbsp;&nbsp;��<%=pagingInfo.getCurrentPageNo()%>ҳ&nbsp;��<%=pagingInfo.getTotalPageCount()==0?"1":new Integer(pagingInfo.getTotalPageCount()).toString()%>ҳ&nbsp;��<%=pagingInfo.getTotalRecordCount()%>��&nbsp;
</DIV>


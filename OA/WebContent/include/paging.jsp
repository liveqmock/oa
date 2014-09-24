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
 * 根据backurl生成一个隐藏的form
 * @param fromName 要生成的form的name
 * @param formId   要生成的form的id
 * @param url  请求串
 * @return 生成的form的html代码
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
		alert("请输入页数，且页数必须为正整数！");
		return false;
	}
	else if(page<=0)
	{
		alert("页数必须为正整数，请重新输入");
		return false;
	}
	else if(page><%=pagingInfo.getTotalPageCount()%>)
	{
		alert("页数不能大于最大页数，请重新输入");
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
		alert("请输入每页记录条数，且页数必须为正整数！");
		return false;
	}
	else if(pageSize<=0)
	{
		alert("每页记录条数必须为正整数，请重新输入");
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
		<span>首页</span>&nbsp;<span>上一页</span>&nbsp;
	<%}else{ %>
		<a href="javascript:_goPage(1)"><span>首页</span></a>&nbsp;<a href="javascript:_goPage(<%=pagingInfo.getCurrentPageNo()-1%>)"><span>上一页</span></a>&nbsp;
	<%}
	if(pagingInfo.isLastPage()){
	 %>
	 <span>下一页</span>&nbsp;<span>末页</span>&nbsp;	 
	<%}else{ %>
	<a href="javascript:_goPage(<%=pagingInfo.getCurrentPageNo()+1%>)"><span>下一页</span></a>&nbsp;<a href="javascript:_goPage(<%=pagingInfo.getTotalPageCount()%>)"><span>末页</span></a>	     
	<%}%>
	每页显示<input name="pageSize" id="pageSize" value="<%=pagingInfo.getPageSize()%>" type="text" class="inputtxt" size="4" style="width:30px;height:14px;" >条 <input type="button" value="确定" style="font-size:12px;" onclick="changepageSize(document.getElementById('pageSize').value)"> &nbsp;&nbsp;到第<input name="__gopage" id="__gopage" type="text" class="inputtxt" size="4" style="width:30px;height:14px;" >页&nbsp;<input type="button" value="确定" style="font-size:12px;" onclick="_goPage(document.getElementById('__gopage').value)">
	&nbsp;&nbsp;第<%=pagingInfo.getCurrentPageNo()%>页&nbsp;共<%=pagingInfo.getTotalPageCount()==0?"1":new Integer(pagingInfo.getTotalPageCount()).toString()%>页&nbsp;共<%=pagingInfo.getTotalRecordCount()%>条&nbsp;
</DIV>


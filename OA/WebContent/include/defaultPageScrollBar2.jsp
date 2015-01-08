<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
try{
	int curPageNum = com.icss.j2ee.util.PageScrollUtil.getPageNum();
	//System.out.println("++++++ current page number is "+curPageNum);
	int pageCount = com.icss.j2ee.util.PageScrollUtil.getPageCount();
	//System.out.println("++++++ page count is "+pageCount);
	int tiao  = com.icss.j2ee.util.PageScrollUtil.getRowCount();
	//System.out.println("++++++ tiao is "+tiao);
%>
	<tr>
		<td valign="middle" width="117">
            <table width="100%" cellspacing="1" class="table_bgcolor">
                <tr>
                    <td width="50" style="width:50px;" align="center" bgcolor="#FFFFFF"><span class="message_title">22<%=tiao%></span></td>
                    <td width="58" style="width:58px;" align="center" bgcolor="#FFFFFF" class="message_title">33<%=curPageNum%>/<%=pageCount%></td>
                </tr>
            </table>
		</td>
		<td width="5" valign="middle" >&nbsp;</td>
		<%
            int i = curPageNum;
            int j = pageCount;
            int k = pageCount/7;
            int l = pageCount%7;
		%>
		<td  valign="middle">
		<table cellspacing="1" class="table_bgcolor" >
			<tr align='left'>
				<td width="20" align="center" valign="bottom" bgcolor="#FFFFFF"
					class="message_title"> <a style="text-decoration: none" href="javascript:_toPage(1)" title='首页'>|&lt;</a> </td>
				<td width="20" align="center" bgcolor="#FFFFFF" class="message_title"> <a style="text-decoration: none" title='上一页' href="javascript:_toPage(<%if(curPageNum!=1){%><%=curPageNum-1%><%}else{%><%=curPageNum%><%}%>)">&lt;&lt;</a></td>
				<%
				//页数小于8
					if(curPageNum<8 ){
					for(int a=1;a<=(pageCount>8?8:pageCount);a++){
				%>
				<td width="20" align="center" valign="bottom" <%if(curPageNum==a){%>bgcolor="#9999FF"<%}else{%>bgcolor="#FFFFFF"<%}%>
					class="message_title">
					<a style="text-decoration: none" href="javascript:_toPage(<%=a%>)"><%=a%></a></td>	
				<%}}else 
				//最后三页
				if(i>j-3){
				for(int a=i-3;a<=j;a++){%>
				<td width="20" align="center" valign="bottom" <%if(curPageNum==a){%>bgcolor="#9999FF"<%}else{%>bgcolor="#FFFFFF"<%}%>
					class="message_title"><a style="text-decoration: none" href="javascript:_toPage(<%=a%>)"><%=a%></a></td>
				<%}}else
				//其他
				{
				for(int a=i-3;a<=i+3;a++){%>
				<td width="20" align="center" valign="bottom" <%if(curPageNum==a){%>bgcolor="#9999FF"<%}else{%>bgcolor="#FFFFFF"<%}%>
					class="message_title"><a style="text-decoration: none" href="javascript:_toPage(<%=a%>)"><%=a%></a></td>
				<%}}%>
				<td width="20" align="center" valign="bottom" bgcolor="#FFFFFF"
					class="message_title"><a style="text-decoration: none" title='下一页' href="javascript:_toPage(<%if(curPageNum!=pageCount){%><%=curPageNum+1%><%}else{%><%=curPageNum%><%}%>)">&gt;&gt;</a></td>
				<td width="20" align="center" bgcolor="#FFFFFF" class="message_title"><a style="text-decoration: none" title='末页' href="javascript:_toPage(<%=pageCount%>)">&gt;|</a></td>
			</tr>			
		</table>
		</td>
		<td width="110" valign="middle" align='left'><input type="text" size="5" name="page" id="page"
			style="height: 18"><a href="javascript:_toPage2()" class="message_title"
			style="text-decoration: none">&nbsp;GO</a></td>
		

<SCRIPT language=JavaScript>
function _toPage(pageno){
    
    if(pageno!='0'){
	    var str = '<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>';
	    var a1=str.lastIndexOf("=");
	    var a3=str.substr(0,a1);
	    var a4=a3+'='+pageno;
		window.location.href = a4;}
}
function _toPage2(){
    pageno = document.getElementById("page").value;
    if(pageno>=<%=pageCount%>){
    	pageno = <%=pageCount%>;
    }else if(pageno<1){
    	pageno = 1;
    }
    //alert(pageno);
    
    if(pageno!='0'){
	    var str = '<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>';
	    var a1=str.lastIndexOf("=");
	    var a3=str.substr(0,a1);
	    var a4=a3+'='+pageno;
		window.location.href = a4;}
}

</SCRIPT>
<%


}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>

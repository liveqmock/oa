<!%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<%
try{
	int curPageNum2 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
	int pageCount2 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
	int tiao2  = com.icss.j2ee.util.PageScrollUtil.getRowCount();
%>
	<tr>
		<td width="11" height="34" bgcolor="#FFFFFF" ><img
			src="../images/kongbai.jpg" width="11" height="11" /></td>
		<td valign="middle" width="117">
		<table width="100%" cellspacing="1" bgcolor="#9999FF">
			<tr>
				<td width="50" align="center" bgcolor="#FFFFFF"><span class="STYLE6"><%=tiao2%></span></td>
				<td width="58" align="center" bgcolor="#FFFFFF" class="STYLE6"><%=curPageNum2%>/<%=pageCount2%></td>
			</tr>
		</table>
		</td>
		<td width="5" valign="middle" >&nbsp;</td>
		<%
				int j2=1;
				int k2=9;
				if(pageCount2-curPageNum2<9){
					k2=pageCount2-1;
				}else{
					k2=9;
				}
							
				if(curPageNum2>5){
					j2 = curPageNum2-5;
					if(pageCount2-curPageNum2<5){
						j2 = pageCount2 -5;
					}
				}
				if(j2+k2>=pageCount2){
					k2 = pageCount2-j2;
				}
				if(k2>9){
					k2=9;
				}
		%>
		<td <%if(k2>=9){%>width="350"<%}%> valign="middle" width='220'>
		<table cellspacing="1" bgcolor="#9999FF" width=100%>
			<tr align='left'>
				<td width="20" align="center" valign="bottom" bgcolor="#FFFFFF"
					class="STYLE6"> <a style="text-decoration: none" href="javascript:_toPage(1)">|&lt;</a> </td>
				<td width="20" align="center" bgcolor="#FFFFFF" class="STYLE6"> <a style="text-decoration: none" href="javascript:_toPage(<%if(curPageNum2!=1){%><%=curPageNum2-1%><%}else{%><%=curPageNum2%><%}%>)">&lt;&lt;</a></td>
								
				<%			
				for(int i2=j2;i2<=j2+k2;i2++){%>

				<%
					if(i2==curPageNum2){
				%>
				<td width="18" align="center" valign="bottom" bgcolor="#9999FF"
					class="white2-12-b"><%=i2%></td>	
				<%}else{%>
				<td width="20" align="center" valign="bottom" bgcolor="#FFFFFF"
					class="STYLE6"><a style="text-decoration: none" href="javascript:_toPage(<%=i2%>)"><%=i2%></a></td>
				<%}}%>
				<td width="20" align="center" valign="bottom" bgcolor="#FFFFFF"
					class="STYLE6"><a style="text-decoration: none" href="javascript:_toPage(<%if(curPageNum2!=pageCount2){%><%=curPageNum2+1%><%}else{%><%=curPageNum2%><%}%>)">&gt;&gt;</a></td>
				<td width="20" align="center" bgcolor="#FFFFFF" class="STYLE6"><a style="text-decoration: none" href="javascript:_toPage(<%=pageCount2%>)">&gt;|</a></td>
				
			</tr>			
		</table>
		</td>
		<td width="80" valign="middle"><input type="text" size="5" name="page" id="page"
			style="height: 18"><a href="javascript:_toPage2()" class="STYLE6"
			style="text-decoration: none">&nbsp;GO</a></td>
			<td width='700'>&nbsp;</td>
<SCRIPT language=JavaScript>
function _toPage(pageno){
    
    if(pageno!='0'&&pageno!='-1'){
	    var str = '<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>';
	    var a1=str.lastIndexOf("=");
	    var a3=str.substr(0,a1);
	    var a4=a3+'='+pageno;
		window.top.mainFrame.location.href = a4;}
}
function _toPage2(){
    pageno = document.getElementById("page").value;
    if(pageno>=<%=pageCount2%>){
    	pageno = <%=pageCount2%>;
    }else if(pageno<1){
    	pageno = 1;
    }
    //alert(pageno);
    
    if(pageno!='0'){
	    var str = '<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>';
	    var a1=str.lastIndexOf("=");
	    var a3=str.substr(0,a1);
	    var a4=a3+'='+pageno;
		window.top.mainFrame.location.href = a4;}
}

</SCRIPT>
<%


}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>

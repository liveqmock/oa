<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.oa.popup.personPwdHandler"%>
<%
	Context ctx = Context.getInstance();;
	String uuid = ctx.getCurrentPersonUuid();
	String userid = ctx.getCurrentUserid();
	String pass = personPwdHandler.getPwdByUUID(uuid);;
	Collection workListCollection = (Collection) request
			.getAttribute("workList");
	Iterator workListGroupIterator = workListCollection.iterator();

%>
<html>
	<head>
		<title>提示信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<style type="text/css">
td img {
	display: block;
}
</style>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			rel="stylesheet" type="text/css" />
		<SCRIPT LANGUAGE="JavaScript">
			function _dowork(workid,url,navigate,type){
			window.open("../../SignOnServlet?userid=<%=userid%>&password=<%=pass%>&url=/oabase/servlet/DoWorkServlet?workid="+workid+"βurl=" + url);
			window.setTimeout(_reload,2000);
			}

			function _home(){
					window.open("../../SignOnServlet?userid=<%=userid%>&password=<%=pass%>&url=/cms/cms/website/index.html");
			}
			function _reload(){
				//alert('1111');
				window.location.reload(true);
			}
			
			function _moreInfo(){
			window.open("../../SignOnServlet?userid=<%=userid%>&password=<%=pass%>&url=/oabase/servlet/AllIntendWorkServlet");
			}
			
			function   getdate()   
				  {   
				  var   now=new   Date();   
				  y=now.getFullYear();
				  m=now.getMonth()+1; 
				  d=now.getDate();
				  m=m<10?"0"+m:m;  
				  d=d<10?"0"+d:d;  
				  document.write(y+"-"+m+"-"+d); 
				  }
			</SCRIPT>
	</head>
	<body bgcolor="#ffffff">
	<form name="frm" method="post" target="_blank">
		<table width="520" height="300"  border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="19">
					<img name="window_r1_c1"
						src="<%=request.getContextPath()%>/images/window_r1_c1.jpg"
						width="19" height="53" border="0" id="window_r1_c1" alt="" />
				</td>
				<td
					background="<%=request.getContextPath()%>/images/window_r1_c2.jpg">
					<div align="left">
						<table width="100%" height="53" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											 <td width="50%" align="right" >
									 			</td>
											 	<td width="5%" align="right" style="cursor:hand;">
											 		<img src="<%=request.getContextPath()%>/images/tophome.gif" alt="OA首页" title="OA首页" width="14" height="14" border="0"  onClick="_home()" >
											 		</td >
											 		<td width="5%" align="right" style="cursor:hand;">
											 			<img src="<%=request.getContextPath()%>/images/reflash.gif" onClick="window.location.reload();" width="18" height="18" border="0" alt="刷新" title="刷新本页面"> 
											 			</td>
            					 <td width="40%" valign="bottom">
            					 	<div align="center" class="foot_message">
            					 		信息更新于：<% java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
            					 			java.util.Date cur = new java.util.Date();
            					 			out.print(formatter.format(cur));
            					 		 %>
            					 </div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td valign="bottom">
									<div align="right">
										<table width="400" height="29" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td width="110"
													background="<%=request.getContextPath()%>/images/window-tag.jpg">
													<div align="center" class="message_title_bold" style="text-decoration:none">
														提示信息
													</div>
												</td>
												<td>
													<div align="center" >
														<a href="<%=request.getContextPath()%>/servlet/MailPopupServlet" class="message_title_bold" style="text-decoration:none">最新邮件
													</div>
												</td>
												<td>
													<div align="center" >
														<a href="/cms/filter/popup/grgz.jsp" class="message_title_bold" style="text-decoration:none">个人关注</a>
													</div>
												</td>
												<td>
													<div align="center" >
														<a href="/cms/filter/popup/grxx.jsp" class="message_title_bold" style="text-decoration:none">个人信息</a>
													</div>
												</td>
												<td>
													<div align="center" >
														<a href="/cms/filter/popup/snzx.jsp" class="message_title_bold" style="text-decoration:none">社内最新</a>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td width="27">
					<img name="window_r1_c5"
						src="<%=request.getContextPath()%>/images/window_r1_c5.jpg"
						width="27" height="53" border="0" id="window_r1_c5" alt="" />
				</td>
			</tr>
			<tr>
				<td>
					<img name="window_r2_c1"
						src="<%=request.getContextPath()%>/images/window_r2_c1.jpg"
						width="19" height="20" border="0" id="window_r2_c1" alt="" />
				</td>
				<td>
					&nbsp;
				</td>
				<td height="20" class="message_title">
					<img name="window_r2_c5" src="<%=request.getContextPath()%>/images/window_r2_c5.jpg"	
					width="27" height="20" border="0" id="window_r2_c5" alt="" /></td>
			</tr>
			<tr>
				<td valign="top"
					background="<%=request.getContextPath()%>/images/window_r4_c1.jpg" class="foot_message">
					<img name="window_r3_c1"
						src="<%=request.getContextPath()%>/images/window_r3_c1.jpg"
						width="19" height="115" border="0" id="window_r3_c1" alt="" />
				</td>
				<td rowspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<%
							if (!workListGroupIterator.hasNext()) {
						%>
						<tr bgColor='#D8EAF8'>
							<td height="260" class="text-01" colspan=15>
								<div align="center">
									<br>
									<br>
									没有提示信息！
									<br>
									<br>
								</div>
							</td>

						</tr>
						<%
							}else{
							int index = 0;
							while (workListGroupIterator.hasNext()) {
								OfficePendingVO officePendingVO = (OfficePendingVO) workListGroupIterator
										.next();
								
								String topic =officePendingVO.getOpTopic();
								
								String url = officePendingVO.getOpUrl();
								String newurl=url.replace('&','*');
								System.out.println(newurl);
								index++;
								String color = "#F2F9FF";
								if (index % 2 == 1)
									color = "#D8EAF8";
						%>
						<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
							onMouseOver="this.bgColor='#8CC0E8';">
							<td width="3%" height="26" class="dot">
								<img src="<%=request.getContextPath()%>/images/arrow.gif" width="18" height="26"/>
							</td>
							<td class="dot">
								
								<div align="left">
								
									<a href="#"
										onclick="javascript:_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(newurl)%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>')" class="message_title"  style="text-decoration:none">
										<%=(topic.length()>35?topic.substring(0,32)+"......":topic)%></a>
								</div>
							</td>
							<td class="message_date">
								<div align="right">(<%=CommUtil.getTime(officePendingVO.getOpDate()
								.longValue(),
								com.icss.oa.config.Config.YYMMDD)%>)</div>
							</td>
						
						</tr>
						<%
							}
						if(index<10){
							for(int j=index;j<10;j++){
						%>
							<tr><td colspan="3" height="26"></td></tr>
						<%
							}
						}
							}
						%>
					</table>
				</td>
				<td valign="top"
					background="<%=request.getContextPath()%>/images/window_r4_c5.jpg">
					<img name="<%=request.getContextPath()%>/window_r3_c5"
						src="<%=request.getContextPath()%>/images/window_r3_c5.jpg"
						width="27" height="115" border="0" id="window_r3_c5" alt="" />
				</td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/images/window_r4_c1.jpg">
					&nbsp;
				</td>
				<td
					background="<%=request.getContextPath()%>/images/window_r4_c5.jpg">
					&nbsp;
				</td>
			</tr>
		</table>
		<table width="520" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="19">
					<img name="window_r5_c1"
						src="<%=request.getContextPath()%>/images/window_r5_c1.jpg"
						width="19" height="49" border="0" id="window_r5_c1" alt="" />
				</td>
				<td colspan="2" valign="bottom"
					background="<%=request.getContextPath()%>/images/window_r5_c3.jpg">
					
				</td>
				<td width="137">
					<img src="<%=request.getContextPath()%>/images/window_r5_c4.jpg"
						alt="" name="window_r5_c4" width="137" height="49" border="0" style="cursor:hand"
						usemap="#window_r5_c4Map" id="window_r5_c4" onClick="javascript:_moreInfo()"/>
				</td>
				<td width="27">
					<img name="window_r5_c5"
						src="<%=request.getContextPath()%>/images/window_r5_c5.jpg"
						width="27" height="49" border="0" id="window_r5_c5" alt="" />
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>
<script language="javascript">
function _search(){
	frm.action="/cms/cms/SearchServlet?forwardUrl=/search.jsp"
	frm.submit();
}
</script>
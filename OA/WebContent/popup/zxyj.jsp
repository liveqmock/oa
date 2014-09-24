<%@page contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.MessageHandler"%>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%>
<%@page import="com.icss.oa.config.FileTransferConfig"%>
<%@ page import="java.sql.Connection"%>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.common.log.ConnLog"%>

<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="com.icss.oa.popup.personPwdHandler"%>

<%
	Context ctx = Context.getInstance();;
	String uuid = ctx.getCurrentPersonUuid();
	String userid = ctx.getCurrentUserid();
	String pass = personPwdHandler.getPwdByUUID(uuid);;
	//System.out.println(uuid);
	//System.out.println(pass);

	String folderSort = request.getAttribute("folderSort")==null?"":(String)request.getAttribute("folderSort");
	List reList = (List) request.getAttribute("listOfMessages");
	Iterator messageIter = null;

	if (reList != null) {
		messageIter = reList.iterator();
	}

%>
<html>
	<head>
		<title>最新邮件</title>
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
			function _openSystemfile(type,folder,mailName){
			window.open("../../SignOnServlet?userid=<%=userid%>&password=<%=pass%>&url=/oabase/mail/Mail_Main_Frame.jsp?next=readβtype="+type+"βfolder=" + folder+"βmailName="+mailName);
			}
			
			function _moreInfo(){
			window.open("../../SignOnServlet?userid=<%=userid%>&password=<%=pass%>&url=/oabase/mail/Mail_Main_Frame.jsp?next=receive|folder=<%=folderSort%>");
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
				function _home(){
					window.open("../../SignOnServlet?userid=<%=userid%>&password=<%=pass%>&url=/cms/cms/website/index.html");
			}
			</SCRIPT>
	</head>
	<body bgcolor="#ffffff">
	<form name="frm" method="post" target="_blank">
		<table width="520" height="300" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; word-wrap:break-word;">
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
												<td>
													<div align="center">
														<a
															href="<%=request.getContextPath()%>/servlet/IntendWorkPopupServlet"
															class="message_title_bold" style="text-decoration:none">提示信息</a>
													</div>
												</td>
												<td width="110"
													background="<%=request.getContextPath()%>/images/window-tag.jpg">
													<div align="center" class="message_title_bold" style="text-decoration:none">
														最新邮件</div>
												</td>
												<td>
													<div align="center" >
														<a href="/cms/filter/popup/grgz.jsp" class="message_title_bold" style="text-decoration:none">个人关注</a>
													</div>
												</td>
												<td>
													<div align="center">
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
				<td>
					<img name="window_r2_c5"
						src="<%=request.getContextPath()%>/images/window_r2_c5.jpg"
						width="27" height="20" border="0" id="window_r2_c5" alt="" />
				</td>
			</tr>
			<tr>
				<td valign="top"
					background="<%=request.getContextPath()%>/images/window_r4_c1.jpg"  class="foot_message">
					<img name="window_r3_c1"
						src="<%=request.getContextPath()%>/images/window_r3_c1.jpg"
						width="19" height="115" border="0" id="window_r3_c1" alt="" />
				</td>
				<td rowspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">

						<%
							int count = 0;

							Connection conn = null;
							try {
								conn = DBConnectionLocator.getInstance().getConnection(
										Globals.DATASOURCEJNDI);
								ConnLog.open("zxyj.jsp");
								MessageHandler mHandler = new MessageHandler();
								FiletransferSetHandler ftsHandler = new FiletransferSetHandler(
										conn);
								if (messageIter != null) {
									if (!messageIter.hasNext()) {
						%>
						<tr bgColor='#D8EAF8'>
							<td height="260" class="text-01" colspan=15>
								<div align="center">
									<br>
									<br>
									没有邮件！			<br>
									<br>
								</div>
							</td>

						</tr>
						<%
							} else {
										int i = 0;
										while (messageIter.hasNext()) {
											List listOfMessages = (List) messageIter.next();
											String[] mailArray = new String[3];
											for (int j = 0; j < listOfMessages.size(); j++) {
												mailArray[j] = listOfMessages.get(j).toString();
											}
											String mailHead = mailArray[1]; 
											String mailName = mailArray[2]; 
												try {
												String mailFrom = ftsHandler.getCName(mHandler
														.getFrom(mailHead).substring(
																0,
																mHandler.getFrom(mailHead)
																		.indexOf("@")));
												Date reDate = mHandler.getReceiveDate(mailHead);
												String mailSubject = mHandler
														.getSubject(mailHead);

												String color = "#F2F9FF";
												if (i % 2 == 0) {
													color = "#D8EAF8";
												}
						%>
						<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
							onMouseOver="this.bgColor='#8CC0E8';">

							<td width="15" height="26">
								<%
									String secondword = mailName.substring(1, 2);
														if (FileTransferConfig.NEW_FLAG
																.equals(secondword)) {	%>
								<img alt=""
									src="<%=request.getContextPath()%>/images/email.gif"
									>
								<%
									}else{
								%>
								
									<img alt=""
									src="<%=request.getContextPath()%>/images/email_open.gif"
									>
								<%
									}
									%>
							</td>
							<td nowrap height="26" width="200">
								<div align="left">
									<a href="#" class="message_title"  style="text-decoration:none"
										onClick="javaScript:_openSystemfile('system','<%=folderSort%>','<%=mailName%>')">
										<%
										if (FileTransferConfig.NEW_FLAG
																	.equals(secondword)) {
																out.print("<b>");
															}
									%><%=mailSubject.length()>10?mailSubject.substring(0,10)+"...":mailSubject%></a>
								</div>
							</td>
							<td  nowrap class="message_title">
								<div align="center">
									<%
										if (FileTransferConfig.NEW_FLAG.equals(secondword)) {
																out.print("<b>");
															}%>
															
															<%
									if(reDate!=null)
									out.print(com.icss.oa.util.CommUtil.getTime(reDate.getTime()));
									 %>
									 
								</div>
							</td>
							<td class="message_date" align="center" nowrap>
							<%
								if (FileTransferConfig.NEW_FLAG.equals(secondword)) {
																out.print("<b>");
															}
															%>
							<%=mailFrom!=null?mailFrom:""%>
							</td>
						</tr>
						<%
							} catch (Exception ex) {
												System.out
														.println("there is a mail error");
											}
											i++;
										}//while

										if(i<10){
												for(int m=i;m<10;m++){
										%>
												<tr><td colspan="10" height="26"></td></tr>
										<%
												}
											}
									}
								}
							} catch (Exception ex) {

								ex.printStackTrace();

							} finally {

								try {
									if (conn != null) {
										conn.close();
										ConnLog.close("zxyj.jsp");
									}
								} catch (Exception e) {
									e.printStackTrace();
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
						alt="" name="window_r5_c4" width="137" height="49" border="0"
						usemap="#window_r5_c4Map" id="window_r5_c4" style="cursor:hand"
						onClick="javascript:_moreInfo()" />
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
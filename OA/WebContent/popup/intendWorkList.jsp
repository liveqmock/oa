<%@ page contentType="text/html; charset=GBK"%>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.intendwork.vo.OfficePendingVO"%>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%
Collection workListCollection = (Collection)request.getAttribute("workList");

Iterator workListGroupIterator = workListCollection.iterator();

%>
<HTML>
	<HEAD>
		<TITLE>���칤��</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<LINK href="<%=request.getContextPath()%>/include/style.css"
			rel=stylesheet>
            <link href="<%=request.getContextPath()%>/Style/css_grey.css" id="homepagestyle" rel="stylesheet" type="text/css" />
            <style type="text/css">
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
			</style>
			<LINK href="<%=request.getContextPath()%>/include/ext/resources/css/ext-all.css"
			rel=stylesheet>
		<SCRIPT LANGUAGE="JavaScript"
			src="<%=request.getContextPath()%>/include/calendar.js"></SCRIPT>
			<SCRIPT LANGUAGE="JavaScript"
			src="<%=request.getContextPath()%>/include/ext/adapter/ext/ext-base.js"></SCRIPT>
			<SCRIPT LANGUAGE="JavaScript"
			src="<%=request.getContextPath()%>/include/ext/ext-all.js"></SCRIPT>
		<SCRIPT language=JavaScript>
writeDIV();//��������javascript
</SCRIPT>
		<SCRIPT LANGUAGE="JavaScript">

function CheckAll()
 {
   for (var i=0;i<document.workForm.elements.length;i++)
   {
     var e = document.workForm.elements[i];
	  if (e.name == 'workid')
		 e.checked = document.workForm.allbox.checked;
   }
 }
 

function search(path){
	document.workForm.action = path + "/servlet/AllIntendWorkServlet";
	document.workForm.submit();
}

function _delete(){
	if(check()){
		document.workForm.action = "<%=request.getContextPath()%>/servlet/DeleteWorkServlet";
		document.workForm.submit();
	}
	else{
		alert("��ѡ��Ҫɾ���Ĺ�����");
	}
}

function _deleteall(){
		confirm('���Ƿ�Ҫɾ�����еĴ�������');
		document.workForm.action = "<%=request.getContextPath()%>/servlet/DeleteWorkServletbyUserid";
		document.workForm.submit();
	
}

function check(){
	for (var i=0;i<document.workForm.elements.length;i++)
   {
     var e = document.workForm.elements[i];
	  if (e.name == 'workid' && e.checked){
		 return true;
		}
   }
   return false;
}

function _dowork(workid,url,navigate,type){
	window.open("<%=request.getContextPath()%>/servlet/DoWorkServlet?workid="+workid+"&url=" + url);
	
}

function fPopUpCalendarDlg(ctrlobj){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
}

function _restorePending()
{
			 var sForm = document.workForm;
			sForm.flag.value = "list";
       		sForm.action = "/cms/cms/xhs/pendingblacklistservlet";
       		sForm.submit();
}

function _cancelPending(channelId,disPlayName)
{

if(Ext.MessageBox){
   Ext.MessageBox.buttonText = {
      cancel : "ȡ��",
      yes    : "ȡ������Ŀ������������Ŀ������",
      no     : "��ȡ������Ŀ������"
   };
}
	 Ext.MessageBox.show({
           title:'��ѡ��ȡ�����ѵķ�ʽ?',
           msg: '��ѡ������Ŀ����ȡ�����ѵĲ�����������ѡ�����²���?',
           buttons: Ext.MessageBox.YESNOCANCEL,
           fn:  function showResult(btn){
           var sForm = document.workForm;
           sForm.channelid.value = channelId;
           sForm.displayname.value = disPlayName;
       if(btn=='yes')
       {
       		sForm.include.value = "1";
       		sForm.flag.value = "add";
       		sForm.action = "/cms/cms/xhs/pendingblacklistservlet";
       		sForm.submit();
       }
       if(btn=='no')
       {
       		sForm.include.value = "0";
       		sForm.flag.value = "add";
       		sForm.action = "/cms/cms/xhs/pendingblacklistservlet";
       		sForm.submit();
       }
    },
           icon: Ext.MessageBox.QUESTION
       });
}

</SCRIPT>
	</head>
	<body>
		<form name="workForm" method="post" action="">
			 <input type="hidden" name="flag">
			 <input type="hidden" name="channelid">
			 <input type="hidden" name="include">
			 <input type="hidden" name="displayname">
            <jsp:include page="/include/top.jsp"></jsp:include>
             <table  width="983" border="0" cellspacing="0" cellpadding="0">
             	<tr><td height="5"></td></tr>
             </table>
			<table  width="983" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td>&nbsp;
						
					</td>
					<td>
						<table width="983" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
							<tr>
								<td height="24" align="center" colspan=6 class="block_title">
									���칤������
								</td>
							</tr>
							 <tr bgcolor="FFFFFF" >
                  				<td width="26%" height="22" class="message_title_bold" align="right">���⣺</td>
                  				<td class="message_title"> <input name="topic" type="text" size="47" class="txt2"></td>
                			</tr>
                			<tr bgcolor="FFFFFF" >
                  				<td height="22" class="message_title_bold" align="right">����ʱ�䣺</td>
                  				<td class="message_title">�� 
                      			<input type="text" name="startTime" size="16" class="txt2" readonly style="cursor:hand;" onClick="fPopUpCalendarDlg(startTime)"> 
                    				<img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 alt="��� ��������" onClick="fPopUpCalendarDlg(startTime)">
                  				�� 
                      			<input type="text" name="endTime" size="16" class="txt2" readonly style="cursor:hand;" onClick="fPopUpCalendarDlg(endTime)"> 
                    			<img src="<%=request.getContextPath()%>/images/calendar.gif" style="cursor:hand;" border=0 alt="��� ��������" onClick="fPopUpCalendarDlg(endTime)">
                 				</td>
                			</tr>
                			<tr height="21" bgcolor="FFFFFF">
                				<td colspan=6 align="center">
                				<img src="<%=request.getContextPath()%>/images/botton-search_in.gif" style="cursor:hand" onClick="javascript:search('<%=request.getContextPath()%>')" >
                				</td>
                			</tr>

						</table>
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
			</table>
			<br>
			<br>

			<table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td >&nbsp;
						
					</td>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
							<tr>
								<td height="24" align="center" colspan=7 bgcolor="#E0EDF8" class="block_title">
								���칤���б�
							</td>
							<tr  bgcolor="#FFFBEF">
	                  			<td width="5%" height="22">
	                    			<div align="center"> </div></td>
	                  			<td width="5%" ><div align="center" class="title-04">���</div></td>
	                  			<td width="30%" ><div align="center" class="title-04">����</div></td>
	                  			<td width="15%" ><div align="center" class="title-04">����ʱ��</div></td>
	                  			<td width="10%" ><div align="center" class="title-04">��Դ</div></td>
	                  			<td width="20%" ><div align="center" class="title-04">������Ŀ</div></td>
	                  			<td width="15%" ><div align="center" class="title-04">��������</div></td>
	                  		</tr>
							
							
							<%
								if(!workListGroupIterator.hasNext()){
							%>
							<tr bgColor='#FFFFFF'>
								<td height="52" class="message_title" colspan=15>
									<div align="center">
										<br>
										<br>
										û����Ϣ��
										<br>
										<br>
									</div>
								</td>

							</tr>
							<%	
							}
							int index = 0;
							boolean isCmsInfo = false;
							String [] codeArr = null;
							String code = null;
							while(workListGroupIterator.hasNext()){
								OfficePendingVO officePendingVO = (OfficePendingVO)workListGroupIterator.next();
					            code = officePendingVO.getOpCode();
								CommUtil.getTime(officePendingVO.getOpModify().longValue(),com.icss.oa.config.Config.ALL_TYPE);
								index ++;
								String color = "#F2F9FF";
								if(index % 2 == 1)
									color = "#D8EAF8";
							%>
							 <tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">     
              					<td height="22" class="message_title"><div align="center">
                     				 <input type="checkbox" name="workid" value="<%=officePendingVO.getOpId()%>">
                  				</div></td>
                  				<td  class="message_title"><div align="center"><%=index%></div></td>
                  				<td  class="message_title"><div align="left"><a href="#" onClick="javascript:_dowork('<%=officePendingVO.getOpId()%>','<%=java.net.URLEncoder.encode(officePendingVO.getOpUrl())%>','<%=officePendingVO.getOpNavigate()%>','<%=officePendingVO.getOpType()%>')" > <%=officePendingVO.getOpTopic()%></a></div></td>
                  				<td  class="message_title"><div align="center"><%=CommUtil.getTime(officePendingVO.getOpDate().longValue(),com.icss.oa.config.Config.ALL_TYPE)%></div></td>
                  				<td  class="message_title"><div align="left"><%=officePendingVO.getOpSource()%></div></td>
                  				<%if(code!=null&&code.startsWith("cms")){
                  				   codeArr = code.split("��");
                  				%>
                  				<td  class="message_title"><div align="center"><%=codeArr[4]%></div></td>
                  				<td  class="message_title"><div align="left"><input type="button" class="inputbutton" value="ȡ������"	onclick="_cancelPending('<%=codeArr[2]%>','<%=codeArr[4]%>');"></div></td>
                  				<%}else{ %>
                  				<td  class="message_title"><div align="center"></div></td>
                  				<td  class="message_title"><div align="left"></div></td>
                  				<%} %>
                  			</tr>
							<%
							}
							%>
							<tr>
							<td height="22" colspan="8" bgcolor="#FFFFFF">
        						<%@ include file="../include/defaultPageScrollBar.jsp"%>
        					</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
				<tr>
					<td width="11" bgcolor="#FFFFFF">
						<img src="<%=request.getContextPath()%>/images/kongbai.gif"
							width="11" height="11" />
					</td>
					<td valign="top">
						<div align="left"></div>
					</td>
					<td width="11">
						<img src="<%=request.getContextPath()%>/images/kongbai.gif"
							width="11" height="11" />
					</td>
				</tr>
			</table>
			  <table border="0" cellpadding="0" cellspacing="0" width="69%" id="AutoNumber2" height="65" align="center">
			    <tr>
			      <td width="25%" align="center" class="message_title"><input type="checkbox" name="allbox" value="Check All" onClick="CheckAll();">
			          <label for="allbox"><a href="javascript:CheckAll();" onClick="allbox.checked=!allbox.checked;">ȫѡ</a></label>
			      </td>
			      <td width="" align="center"></td>
			      <td align="center">
			      <img src="<%=request.getContextPath()%>/images/bt_hf.gif" style="cursor:hand" onClick="javascript:_restorePending()">&nbsp;
			      <img src="<%=request.getContextPath()%>/images/bt_del.gif" style="cursor:hand" onClick="javascript:_delete()">
			      &nbsp;
			      <img src="<%=request.getContextPath()%>/images/bt_delall.gif"  border=0 onClick="javascript:_deleteall()" style="cursor:hand" alt="ɾ��ȫ������">&nbsp;
			      
			      <img src="<%=request.getContextPath()%>/images/bt_back.gif"  border=0 onClick="javascript:flesh()" style="cursor:hand" alt="������Ϣ��ҳ">
			      
			      </td>
			    </tr>
			  </table>
			  </form>

			<SCRIPT language="JavaScript">

function flesh(){
		if(window.opener)
		window.opener.location.reload();
   window.close();
   
}

</SCRIPT>
</body>

</html>
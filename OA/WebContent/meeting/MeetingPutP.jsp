<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.meeting.vo.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.EntityManager"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</style>
</head>
<%--*******************���´�����վ��ͳ�Ƶ���չ���/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module11");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************վ��ͳ�Ƶ���չ��ǵ��˽���/End****************************--%>
<script language="JavaScript">
function _checkPage(){
<%
		int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
		int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
		if(curPageNum1>pageCount1){
			curPageNum1=pageCount1;
			String url=request.getContextPath()+"/servlet/MeetingPutServlet?_page_num="+curPageNum1;
%>
		this.location="<%=url%>";
<%
		}
%>
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

function IsCheck(){
	if(document.form_update.MeetingId.value  == "null"){
    	alert("��ѡ��һ����¼��");
		return false;
	}
	return true;
}
function IsFormItemEmpty(){
  	if(document.form_update.MeetingName.value==""){
    	alert("����д�������������ƣ�");
    	return true;
    }
 	if(document.form_update.MeetingStartTime.value==""){
    	alert("����д���鿪ʼ��ʱ��");
    	return true;
    }
    if(document.form_update.MeetingEndTime.value==""){
    	alert("����д���������ʱ��");
    	return true;
    }
    if(document.form_update.MeetingStartTimeHour.value==""){
    	alert("����д���������ʱ��");
    	return true;
    }
    if(document.form_update.MeetingStartTimeMinit.value==""){
    	alert("����д���������ʱ��");
    	return true;
    }
    if(document.form_update.MeetingEndTimeHour.value==""){
    	alert("����д���������ʱ��");
    	return true;
    }
    if(document.form_update.MeetingEndTimeMinit.value==""){
    	alert("����д���������ʱ��");
    	return true;
    }
	return false;
} 
function _Add(){

    	document.form_list.action="<%=request.getContextPath()%>/meeting/MeetingPutNew.jsp";
  		document.form_list.submit();
}

function _DelPut(){
	    var j;
	    if(document.form_list.PutId == undefined){
			alert("û�п�ɾ������");		
	    }
	    else if( document.form_list.PutId.length == undefined){
        	if(document.form_list.PutId.checked){
				document.form_list.action="<%=request.getContextPath()%>/servlet/DelMeetingPutServlet";
				document.form_list.submit();	
				return;
			}
		}
		else{
	    	for(j=0;j<document.form_list.PutId.length;j++){
	        	if(document.form_list.PutId[j].checked){
					document.form_list.action="<%=request.getContextPath()%>/servlet/DelMeetingPutServlet";
					document.form_list.submit();	
					return;
				}
			}
		}
		alert("δѡ��ɾ���");
	}
function _updateSchedule(){

    	document.journalForm.action="<%=request.getContextPath()%>/servlet/QueryMeetingServlet";
  		document.journalForm.submit();
}

function _DelPut(){
	    var j;
	    if(document.form_list.PutId == undefined){
			alert("û�п�ɾ������");		
	    }
	    else if( document.form_list.PutId.length == undefined){
        	if(document.form_list.PutId.checked){
				document.form_list.action="<%=request.getContextPath()%>/servlet/DelMeetingPutServlet";
				document.form_list.submit();	
				return;
			}
		}
		else{
	    	for(j=0;j<document.form_list.PutId.length;j++){
	        	if(document.form_list.PutId[j].checked){
					document.form_list.action="<%=request.getContextPath()%>/servlet/DelMeetingPutServlet";
					document.form_list.submit();	
					return;
				}
			}
		}
		alert("δѡ��ɾ���");
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
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">�����������б�</td>
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
            	      <td width="30%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">��������</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="7%" bgcolor="#FFFBEF"><div align="center" class="title-04">��������</div></td>
					  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="8%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">����ʹ�õĻ�����</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="8%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">��������״̬</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="9%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">����(��ϵ)��</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="8%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">���벿��</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="8%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">����ʱ��</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="6%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">�����Ա</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="14%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">����</div></td>

                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
     EntityManager entityManager = EntityManager.getInstance();
	List list = (List)request.getAttribute("list");
	Iterator it = list.iterator();
	int i=0;
	while(it.hasNext()){
		++i;
		MeetingPutVO vo = (MeetingPutVO)it.next();
		Person person = entityManager.findPersonByUuid(vo.getMeetingPutperson());
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
						<td height="22" class="text-01" align="center"><input type="checkbox" name="PutId" value="<%= vo.getPutId()%>"></td>
                      	<td class="text-01" align="left" height="22">
                      	
						  <% if(("������").equals(vo.getMeetingSatus())){ %>
		                  <img src="<%=request.getContextPath()%>/images/meeting/kkk.gif" width="15" height="15" align="middle" alt="�����л���" style="border=0"> 
		                  <%}%>
                          
                          <% if(("��ȷ��").equals(vo.getMeetingSatus())){ %>
		                  <img src="<%=request.getContextPath()%>/images/meeting/ok1.gif" width="15" height="15" align="middle"  alt="��ȷ�ϻ���"  style="border=0"> 
		                  <%}%>
                           
                          <% if(("δȷ��").equals(vo.getMeetingSatus())){ %>
		                  <img src="<%=request.getContextPath()%>/images/meeting/not.gif" width="15" height="15" align="middle" alt="δȷ�ϻ���" style="border=0"> 
		                  <%}%>
 						  
                          <% if(("������").equals(vo.getMeetingSatus())){ %>
		                  <img src="<%=request.getContextPath()%>/images/meeting/pics-09.gif" width="15" height="15" align="middle"  alt="�ѷ�������" style="border=0"> 
		                  <%}%>
                          
                           <% if(("���鿪ʼ").equals(vo.getMeetingSatus())){ %>
		                  <img src="<%=request.getContextPath()%>/images/meeting/start.gif" width="15" height="15" align="middle"  alt="�ѿ�ʼ����" style="border=0"> 
		                  <%}%>
			
			               <% if(("�������").equals(vo.getMeetingSatus())){ %>
		                  <img src="<%=request.getContextPath()%>/images/meeting/over.gif" align="middle"  alt="�ѽ�������" style="border=0"> 
		                  <%}%>

                      	<a href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/servlet/ShowMeetingServlet?putId=<%= vo.getPutId()%>&type=kk','','width=800,height=400,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=50,top=20');"><%= vo.getMeetingName()%></a></td>
                      	<td class="text-01" align="center"><%= vo.getMeetingType()%></td>
                      	<td class="text-01" align="center"><%= vo.getMeetingRoom()%></td>
                      	<td class="text-01" align="center"><%= vo.getMeetingSatus()%></td>
                      	<td class="text-01" align="center"><%= person.getFullName()%></td>
                      	<td class="text-01" align="center"><%= vo.getMeetingPutdep()%></td>
                      	<td class="text-01" align="center"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date(vo.getPutTime().longValue()))%></td>
                      	<td class="text-01" align="center"><a href = "<%=request.getContextPath()%>/servlet/ListMeetingPersonServlet?putId=<%= vo.getPutId()%>&type=kk">�鿴</a></td>
                      	<td class="text-01" align="center">
                        <% if(("������").equals(vo.getMeetingSatus())||("δȷ��").equals(vo.getMeetingSatus())||("��ȷ��").equals(vo.getMeetingSatus()))
                      	{%>  <a href = "<%=request.getContextPath()%>/servlet/MeetingPutServletP?putId=<%= vo.getPutId()%>">
                      	<%}%>��׼����</a></td>

                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
  </div>
    <div align="center"><br>
    <img src="<%=request.getContextPath()%>/images/meeting/button-deletmeeting.gif" border=0 style="cursor:hand"  onClick="_DelPut()">

  </div>
  </form>
  
  <FORM name=journalForm method=post>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
  <tr>
  
    <td width="" valign="top">
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">������������ѯ</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="171" height="22" class="text-01" align="right">�������⣺</td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                      <td width="319" bgcolor="F2F9FF" class="text-01">
                		<input name="content" type="text" value="<%=(request.getParameter("content")!=null)?(request.getParameter("content")):""%>" size="29"></td>
                   
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="100" class="text-01"><div align="right">�������ͣ�</div></td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
					  <td  bgcolor="F2F9FF"  class="text-01">
					  <select name="MeetingType">
  					  <option value="��ͨ����">��ͨ����</option>
  					  <option value="���ܻ���">���ܻ���</option>
  					  <option value="�绰����">�绰����</option>
  					  <option value="ȫ������">ȫ������</option>
					  </select>
					         
                       </td>  
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"> </td>   
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td height="22" class="text-01"><div align="right">��������״̬��</div></td>
                      <td bgcolor="F2F9FF" class="text-01">
                      <select name="MeetingStatus">
  					  <option value="������">������</option>
  					  <option value="������">������</option>
  					  <option value="δȷ��">δȷ��</option>
  					  <option value="��ȷ��">��ȷ��</option>
  					  <option value="���鿪ʼ">���鿪ʼ</option>
  					  <option value="�������">�������</option>
  					  <option value="ȫ��">ȫ��</option>
					  </select>
                      <td class="text-01"><div align="right">��������ң�</td>
                      <td bgcolor ="F2F9FF" class="text-01">
                        <input name="meetingroom" type="text" value="<%=(request.getParameter("meetingroom")!=null)?(request.getParameter("meetingroom")):""%>" size="29"></td>
                      	
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                  </table>
				</td>
              </tr>
            </table>
		  </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
        </tr>
      </table>
    </tr>
  </table> 
  <table border="0" cellpadding="0" cellspacing="0" height="45" align="center">
     <tr><td><div align="center"><span class="text-01"><img onClick="javascript:_updateSchedule()" src="<%=request.getContextPath()%>/images/botton-search.gif"style="cursor:hand"></span></div></td>
	 </tr></table>
  </table>
  </form>
  
</body>
</html>

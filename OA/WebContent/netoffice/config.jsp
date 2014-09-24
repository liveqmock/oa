<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.setNetoffice.vo.*" %>

<%
 
 
 List setNetofficeList = null;
 OfficeSetnetofficeVO  vo=new OfficeSetnetofficeVO();
 setNetofficeList = (List)request.getAttribute("list");
 Iterator  setNetofficeIterator=null;
 if(setNetofficeList!=null){
   setNetofficeIterator = setNetofficeList.iterator();
 }
   
   Integer[]reserveMonths =new Integer[3];
   Integer[]remindHours =new Integer[3];
   Integer[]id= new Integer[3];
   
   int i=0;
      while(setNetofficeIterator.hasNext()){
	    vo = (OfficeSetnetofficeVO)setNetofficeIterator.next();
	     
	    reserveMonths[i]=(Integer)vo.getMonthsReserve();
	   // out.println("111111111"+reserveMonths[i]);
	    remindHours[i]=vo.getHoursRemind();
	    id[i]=vo.getSetId();
	  i++;
       ////////
      }
%>

<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module5");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

<html>
<head>
<LINK href="<%=request.getContextPath()%>/include/style.css" type=text/css rel=stylesheet>
<title>个人办公室设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT language=JavaScript>
	function _updateSchedule(url){
		document.scheduleForm.action=url+"/servlet/SetScheduleServlet";
	    document.scheduleForm.submit();
	
	}
	
	function _updateJournal(url){
		document.journalForm.action=url+"/servlet/SetJournalServlet";
	    document.journalForm.submit();
	
	}
	
	function _updateMemo(url){
		document.memoForm.action=url+"/servlet/SetMemoServlet";
	    document.memoForm.submit();
	
	}

		</SCRIPT>

</head>

<body>
<div align="center">
<table width="60%"  border="0">
  <tr>
    <td>
	  <div align="center">
                <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
                    <td  width="97%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05" align="left">个人办公室设置</td>
                    <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
                  </tr>
                </table>
                <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="2" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="1"></td>
                    <td width="100%">
					  
                       <table width="100%" height="33%"  border="0" cellpadding="0" cellspacing="0" valign=middle>
                         <form action="" method="post" name="scheduleForm">
                         <input type="hidden" name="sche_serMonth" value="<%=reserveMonths[0]%>">
                         <input type="hidden" name="sche_id" value="<%=id[0]%>">
						 <tr>
                          <td bgcolor="#FFFBEF" align="left" height="22">&nbsp;&nbsp;日程安排</td>
                         </tr>
						 <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height=""></td>
                        </tr>
                          <tr>
                           <td bgcolor="#F2F9FF">
						     <table width="100%" border="0" height="70%" align=center cellpadding="0" cellspacing="0">
                        <tr bgColor=#F2F9FF  class="title-04">
                          <td height="22" bgcolor="#F2F9FF"><table  width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="38%" align="right"> 保&nbsp;留</td>
                                <td width="15%" align="center">
                                 <select id="selSchedule" name="selSchedule" size="1" class="txt2">
                                    <option value="1" selected>一个月</option>
                                    <option value="2">两个月</option>
                                    <option value="3">三个月</option>
                                    <option value="4">四个月</option>
                                    <option value="5">五个月</option>
									<option value="6">六个月</option>
                                </select></td>
                                <td width="47%" align="left"> 的日程安排 </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                        <tr bgColor=#D8EAF8  class="title-04">
                          <td height="22" bgcolor="#F2F9FF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td  width="38%" align="right"> 提&nbsp;前</td>
                                <td width="15%" align="center"><input name="remind_hours" value="<%=remindHours[0]%>" type="text" size=7 ></td>
                                <td width="47%" align="left">小时提示日程安排</td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height=""></td>
                        </tr>
                    </table>
					
					
						   </td>
                         </tr>
					<tr  bgcolor="#F2F9FF">
					  <td height="30" align="right" valign="bottom">
					  <img onClick="javascript:_updateSchedule('<%=request.getContextPath()%>');"  src="<%=request.getContextPath()%>/images/botton-ok.gif" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
					</tr>
					<tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
						</form>
                </table>
				
				
				<table width="100%" height="33%"  border="0" cellpadding="0" cellspacing="0">
                        <form name="journalForm" action="" method="post">
                       <input type="hidden"  name="journal_serMonth"  value="<%=reserveMonths[1]%>">
                       <input type="hidden"  name="journal_id"  value="<%=id[1]%>">
						 <tr>
                          <td bgcolor="#FFFBEF" align="left" height="22">&nbsp;&nbsp;日记</td>
                         </tr>
						 <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                          <tr>
                           <td bgcolor="#F2F9FF">
						     <table width="100%" border="0" height="70%" align=center cellpadding="0" cellspacing="0">
                        <tr bgColor=#F2F9FF  class="title-04">
                          <td height="42" bgcolor="#F2F9FF"><table  border="0" cellspacing="0" cellpadding="0" width="100%">
                              <tr>
                                <td  width="38%" align="right"> 保&nbsp;留</td>
                                <td width="15%" align="center"><select name="select_journal" size="1" class="txt2">
                                    <option value="1" selected>一个月</option>
                                    <option value="2">两个月</option>
                                    <option value="3">三个月</option>
                                    <option value="4">四个月</option>
                                    <option value="5">五个月</option>
									<option value="6">六个月</option>
                                </select></td>
                                <td width="47%" align="left"> 的日记 </td>
                              </tr>
                          </table></td>
                        </tr>
                        
                        
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                    </table>
					
					
						   </td>
                         </tr>
						 <tr bgcolor="#F2F9FF">
					  <td height="30" align="right"  valign="bottom">
					  <img  onClick="javascript:_updateJournal('<%=request.getContextPath()%>');" src="<%=request.getContextPath()%>/images/botton-ok.gif" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
					</tr>
					<tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height=""></td>
                  </tr>
				  </form>
                </table>
				
				
				<table width="100%" height="34%"  border="0" cellpadding="0" cellspacing="0">
				<form action="" method="post" name="memoForm">
				<input type="hidden" name="memo_serMonth" value="<%=reserveMonths[2]%>">
				<input type="hidden" name="memo_id" value="<%=id[2]%>">
                         <tr>
                          <td height="22" align="left" bgcolor="#FFFBEF">&nbsp;&nbsp;备忘录</td>
                         </tr>
						 <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                          <tr>
                           <td bgcolor="#F2F9FF">
						     <table width="100%" border="0" height="70%" align=center cellpadding="0" cellspacing="0">
                        <tr bgColor=#F2F9FF  class="title-04">
                          <td height="32" bgcolor="#F2F9FF"><table  border="0" cellspacing="0" cellpadding="0" width="100%">
                              <tr>
                                <td width="38%" align="right"> 保&nbsp;留</td>
                                <td width="15%" align="center"><select name="select_memo" size="1" class="txt2">
                                    <option value="1" selected>一个月</option>
                                    <option value="2">两个月</option>
                                    <option value="3">三个月</option>
                                    <option value="4">四个月</option>
                                    <option value="5">五个月</option>
									<option value="6">六个月</option>
                                </select></td>
                                <td width="47%" align="left"> 的备忘录</td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                        </tr>
                    </table>
					
					       </td>
                          </tr>
						 <tr bgcolor="#F2F9FF">
					  <td height="30" align="right" valign="bottom">
					  <img  onClick="javascript:_updateMemo('<%=request.getContextPath()%>');" src="<%=request.getContextPath()%>/images/botton-ok.gif" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
					</tr>
					<tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01" height="1"></td>
                  </tr>
				  </form>
                </table>
				
    
					</td>
                    <td width="2" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="1"></td>
                  </tr>
                </table>
                <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
                    <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center"> </td>
                    <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
                  </tr>
                </table>
                
              </div>
	 
	
	</td>
  </tr>
</table>
  <SCRIPT language=JavaScript>
		<!--
		
		//var k=0;
		for(k=0;k<document.scheduleForm.selSchedule.length;k++){
		   var month_option=document.scheduleForm.selSchedule.options[k].value;
		  var month_datebase=document.scheduleForm.sche_serMonth.value;
		   if(month_option==month_datebase){
		      document.scheduleForm.selSchedule.options[k].selected=true;
		      break;
		   }
		      
		}
		
		
		/////////////////////////////////////////
		for(m=0;m<document.journalForm.select_journal.length;m++){
		   var month_option=document.journalForm.select_journal.options[m].value;
		  var month_datebase=document.journalForm.journal_serMonth.value;
		   if(month_option==month_datebase){
		      document.journalForm.select_journal.options[m].selected=true;
		      break;
		   }
		      
		}
		///////////////////////////////////////////////
		for(n=0;n<document.memoForm.select_memo.length;n++){
		   var month_option=document.memoForm.select_memo.options[n].value;
		  var month_datebase=document.memoForm.memo_serMonth.value;
		   if(month_option==month_datebase){
		      document.memoForm.select_memo.options[n].selected=true;
		      break;
		   }
		      
		}
//-->
 </SCRIPT>  
</div>
  
</body>
</html>

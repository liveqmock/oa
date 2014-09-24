<%@ page contentType="text/html; charset=gb2312" %>



<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.config.TypeConfig" %>
<%@ page import="com.icss.oa.netoffice.schedule.handler.*"%>
<%@ page import="com.icss.oa.netoffice.schedule.vo.*"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.oa.util.CommUtil" %>
<%@ page import="com.icss.oa.util.StringUtility" %>
<%@ page import="com.icss.common.log.ConnLog" %>


<html>
<head>
<title>日程安排</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<%
              String year_select=request.getParameter("year_select"); 
              Integer yearIn=new Integer(year_select);
              int year=yearIn.intValue();   
              //out.print("aaaaaaaaa"+year_select);
              String month_select=request.getParameter("month_select");
              int month=new Integer(month_select).intValue();
        %>

<SCRIPT language=JavaScript>
		<!--
		function setCalendarBegin(){
		  var year=document.CalendarForm.year.value;
		 // alert(year);
		  var month=document.CalendarForm.month.value;
		  //alert(month);
		  document.CalendarForm.year_select.selectedIndex=year-2001;
		  document.CalendarForm.month_select.selectedIndex=month-1;
		}
		
		function _toSchedule(url,date){
		   
		    //alert("aaa"+date);
		    document.CalendarForm.date.value=date;
		   document.CalendarForm.action=url+"/servlet/CalendarSelectScheduleServlet";
		   document.CalendarForm.submit();
		}
		
		function _del(url,direct,sid){
		  if(confirm("是否确定要删除？")){
		     document.CalendarForm.direct.value=direct;
		     document.CalendarForm.jc.value=sid;
		     document.CalendarForm.action=url+"/servlet/DelScheduleServlet";
		     document.CalendarForm.submit();
		  }
		  else{
		     
		    return;
		  }
	
		}
		
		function _contentShow(url,sid){
		     document.CalendarForm.sid.value=sid;
		     document.CalendarForm.action=url+"/servlet/ContentShowScheduleServlet";
		     document.CalendarForm.submit();
		
		}
		
		function _toAdd1(url){
		  document.CalendarForm.direct.value="calDest";
		  document.CalendarForm.action=url+"/netoffice/schedule/add1.jsp";
		  document.CalendarForm.submit();
		}
		
//-->
</SCRIPT>
<!--Fireworks MX 2004 Dreamweaver MX 2004 target.  Created Wed Mar 03 13:51:10 GMT+0800 2004-->
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">

</head>
<body background="<%=request.getContextPath()%>/images/bg-08.gif">
<table width="100%" height="523"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" ><div align="center"><br>
    </div>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td width="96%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">&#26085;&#31243;&#23433;&#25490;</td>
          <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>
       
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		  
		     <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			   <tr width="100%" >
			     <td width="33%" height="100%" valign="bottom">
				    <table width="100%" height="100%" cellpadding="0" cellspacing="0">
					  <tr>
					    <td  height="22" bgcolor="FBFBEE"><div align="center" class="title-04"></div></td>
					  </tr>
					  <tr>
                     <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				      </tr>
					  <tr>
					    <td  valign="top" bgcolor="F2F9FF" class="text-01"><div align="center">
                        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td align=center><br>
                              <%=year%>&#24180;-<%=month%>&#26376;</p>
                              <p>&nbsp;</p></td>
                          </tr>
                          <tr>
                            <td>
                            <Form name="CalendarForm" action="<%=request.getContextPath()%>/netoffice/schedule/schedule_calendarDest.jsp">
                            
                           
                            <input type=hidden name=year value="<%=year%>">
                            <input type=hidden name=month value="<%=month%>">
                            <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td width="4%"><img src="<%=request.getContextPath()%>/images/bg-01.gif" width="10" height="10"></td>
                                  <td width="92%" background="<%=request.getContextPath()%>/images/bg-02.gif"></td>
                                  <td width="4%"><img src="<%=request.getContextPath()%>/images/bg-03.gif" width="9" height="10"></td>
                                </tr>
                                <tr>
                                  <td background="<%=request.getContextPath()%>/images/bg-04.gif">&nbsp;</td>
                                  <td bgcolor="#FFFFFF"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td colspan="3" class="title-02">&nbsp;</td>
                                    </tr>
                                    <tr bgcolor="#D8EAF8">
                                      <td height="22" colspan="3" class="title-02">&nbsp;&#35831;&#36873;&#25321;&#24180;&#26376;</td>
                                      </tr>
                                    <tr>
                                      <td>&nbsp;</td>
                                      <td>&nbsp;</td>
                                      <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td width="27%">&nbsp;</td>
                                      <td width="31%"><select name="year_select">
                                        <option>2001</option>
                                        <option>2002</option>
                                        <option>2003</option>
                                        <option>2004</option>
                                        <option>2005</option>
                                        <option>2006</option>
                                        <option>2007</option>
                                        <option>2008</option>
                                        <option>2009</option>
                                        <option>2010</option>
                                        <option>2011</option>
                                        <option>2012</option>
                                                                            </select>年</td>
                                      <td width="42%">&nbsp;
                                        <select name="month_select">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>
                                            </select>月</td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp;</td>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp;</td>
                                      <td colspan="2"><p>
                                      <A href="#" onclick="submit();">
                                      <img src="<%=request.getContextPath()%>/images/botton-01.gif" width="59" height="19" border="0">
                                      </A>
                                      </p>
                                        <p>&nbsp;</p>
                                        <p>&nbsp;</p></td>
                                    </tr>
                                  </table>                                    </td>
                                  <td bgcolor="#FFFFFF"></td>
                                </tr>
                                <tr>
                                  <td><img src="<%=request.getContextPath()%>/images/bg-05.gif" width="10" height="9"></td>
                                  <td bgcolor="#FFFFFF"></td>
                                  <td><img src="<%=request.getContextPath()%>/images/bg-09.gif" width="9" height="9"></td>
                                </tr>
                              </table>           
                                               
                              
                              
                                </td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td align="center"><A   
                                href="<%=request.getContextPath()%>/servlet/ShowScheduleOnedayServlet"><IMG src="<%=request.getContextPath()%>/images/botton-02.gif" width="59" height="19" border="0" ></a>&nbsp;&nbsp;
								               
                             <a href="#" onclick="_toAdd1('<%=request.getContextPath()%>')">
							  <img src="<%=request.getContextPath()%>/images/botton-03.gif" width="62" height="19" hspace="10" border="0"></a></td>
                          </tr>
                        </table>
                      </div></td>
					  </tr>
					   <tr>
                     <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				      </tr>
					</table>
		         </td>
				 <td width="2"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
				 <td width="67%" height="100%" valign=top>
				 <table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                     <td width="14%" bgcolor="FBFBEE"><div align="center" class="title-04">&#26143;&#26399;&#22825;</div></td>
                     <td width="%0" rowspan="20" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                     <td width="14%" bgcolor="FBFBEE"><div align="center" class="title-04">&#26143;&#26399;&#19968;</div></td>
                     <td width="%0" rowspan="20" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="FBFBEE"><div align="center"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></div></td>
                     <td width="14%" bgcolor="FBFBEE"><div align="center" class="title-04">&#26143;&#26399;&#20108;</div></td>
                     <td width="%0" rowspan="20" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="FBFBEE"><div align="center"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2" vspace="2"></div></td>
                     <td width="14%" bgcolor="FBFBEE"><div align="center" class="title-04">&#26143;&#26399;&#19977;</div></td>
                     <td width="%0" rowspan="20" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="FBFBEE"><div align="center"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></div></td>
                     <td width="14%" bgcolor="FBFBEE"><div align="center" class="title-04">&#26143;&#26399;&#22235;</div></td>
                     <td width="%0" rowspan="20" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="FBFBEE"><div align="center"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></div></td>
                     <td width="15%" bgcolor="FBFBEE"><div align="center" class="title-04">&#26143;&#26399;&#20116;</div></td>
                     <td width="%0" rowspan="20" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="FBFBEE"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                     <td width="15%" height="22" bgcolor="FBFBEE"><div align="center" class="title-04">星期六</div></td>
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
                   </tr>
                   <% 
                   
				     Connection conn =null;
					  try{
			           //????
			
			           int yearnum = year;//指定年
                       int monthnum = month;//指定月
			          //使用Calendar类
			           Calendar calen = Calendar.getInstance();
			     TypeConfig calendConfig= new TypeConfig();
				 calen.set(yearnum,monthnum - 1,1);// 设置每月的开始日期
				 //int days =calen.get(DAY_OF_YEAR);
	 			 int days = calendConfig.daysOfMon(monthnum,yearnum);//得到每月的天数***********
				 int weeknum = 0;//每周的第几天
				 int linenum= 1;
				 int gridnum= 0;
				
				 conn =DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("schedule_calendarDest.jsp");
				 for(int j = 1; j <= days; j ++){//for days
				    
					 if(weeknum == 1 || weeknum == 0){
						 out.println("<tr bgcolor=\"F2F9FF\">");
					 }//end if(weeknum == 1);
					 if(j == 1){
						 //取得每月的第一天是一个星期的第几天
						 weeknum = calen.get(Calendar.DAY_OF_WEEK);
						 gridnum= weeknum + days -1;
						 for(int k = 1; k < weeknum; k ++){
				
						     out.println("<td height=\"70\" valign=\"top\" class=\"text-01\"> ");
							 out.println("<table width=\"100%\" height=\"70\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
							 out.println("<tr><td width=\"20\" bgcolor=\"#D8EAF8\" class=\"style1\" height=10>&nbsp;</td></tr>");
							 out.println("<tr><td height=\"50\">&nbsp;</td></tr>");
                             out.println("</table></td>"); 
						
						 } //打印空格
		
                            
					 }//end if j ==1
					
					 weeknum ++;
					     //   out.println("<td height=\"70\" valign=\"top\" class=\"text-01\"> ");
						//	out.println("<table width=\"100%\" height=\"70\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
						//	out.println("<tr><td width=\"20\" bgcolor=\"#D8EAF8\" class=\"style1\">&nbsp;</td></tr>");
						//	out.println("<tr><td height=\"50\">"+j+"</td></tr>");
                        //    out.println("</table></td>"); //打印每一天
                        
                        
                         //拚个字符串
                         StringBuffer datex=new StringBuffer();
                         datex.append(year);
                         if(month<10){
                           datex.append("-0"+month);
                         }
                         else{
                           datex.append("-"+month);
                         }
                        
                         if(j<10){
                            datex.append("-0"+j);
                         }
                         else{
                            datex.append("-"+j);
                         }
                         
                         String date2=datex.toString();
                        //out.println(date2);
                       
                        List list=null;
                        Iterator  scheduleIterator=null;
                        conn =DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
                        ScheduleHandler shandler=new ScheduleHandler(conn);
                        long date_sche=CommUtil.getLongByTime(date2);
                        String personid=shandler.getUserId();
                        list=shandler.getScheduleListBydate(new Long(date_sche),personid);
					    OfficeScheduleVO vo=null;
					    
						//edit by yangyang 20050707
						//在for循环体中取得的数据库连接没有关闭
					    if (conn != null){
							conn.close();
						}
					 %>
					
					  <td height="70" valign="top" class="text-01"> 
					     <table width="100%" height="70"  border="0" cellpadding="0" cellspacing="0">  
					     <tr><td width="20" bgcolor="#D8EAF8" class="style1" colspan=2 height=10>&nbsp;</td></tr> 
					     <tr>
					     <td  align=center valign=center height="50" width=20%><a href="#" onclick="_toSchedule('<%=request.getContextPath()%>','<%=date2%>');"><%=j%></a></td>
					     <td height="50" width=80%>
					        <% String topicShort=new String();
					           if(list!=null){
					              scheduleIterator = list.iterator();
					           }
					            int k=0;
					            while (scheduleIterator.hasNext()){
					              vo=(OfficeScheduleVO)scheduleIterator.next();
					              String topic=vo.getOsTopic();
					              int sid=vo.getOsId().intValue();
					              k++;
					              if(k>=4){
					                 String moreheadline="更多..";
					         %>
					           
					         <a href="#"  onclick="_contentShow('<%=request.getContextPath()%>','<%=sid%>')"><%=moreheadline%></a>
					         <%    
					           break;   
					              }
					        
					        topicShort=StringUtility.getMoreString(topic, 6, "..",StringUtility.HTML_FILL);
					             
					           
					            
					           
					        %>
					        <a href="#"  onclick="_contentShow('<%=request.getContextPath()%>','<%=sid%>')"><%=topicShort%></a>
					        <a href="#" onclick="_del('<%=request.getContextPath()%>','calDest','<%=sid%>')"><img src="<%=request.getContextPath()%>/images/del.gif" border=0></a><br>
					        <%}%>
					     </td>
					     </tr>
					     </table>
					   </td>
				<% 
					 if(weeknum == 8){
					     linenum++;
						 out.println("</tr>");//????
						 weeknum = 1;
						 //打印一行虚线
					 //	out.println("<tr bgcolor=\"F2F9FF\">");
					 //	out.println("<td height=\"2\" background=\"images/bg-13.gif\" class=\"text-01\"></td>");
					 //	 for(int a= 1; a<= 6;a++)
					 //	   out.println("<td background=\"images/bg-13.gif\"></td>");
					 //	 out.println("</tr>");
					 %>
						 
					<tr>
                     <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                     <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                   </tr>
						 <% 
					 }// end if(weeknum == 8)
				 }//end for days
				
                 if(weeknum>1&&weeknum <= 7){
					 while(weeknum <= 7){
						
							 out.println("<td height=\"70\" valign=\"top\" class=\"text-01\"> ");
							 out.println("<table width=\"100%\" height=\"70\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
							 out.println("<tr><td width=\"20\" bgcolor=\"#D8EAF8\" class=\"style1\" height=10>&nbsp;</td></tr>");
							 out.println("<tr><td height=\"50\">&nbsp;</td></tr>");
							 out.println("</table></td>");
							 weeknum ++;
				}
					out.println("</tr>");
				
			  %>
					 <tr>
                     <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
					
		  }catch(Exception e){
			 out.println(e.getMessage());
		 }
		
		finally { 
			try {
				 if (conn != null){
				 conn.close();
				 ConnLog.close("schedule_calendarDest.jsp");
				 }
			   } catch (SQLException e1) {
					
					e1.printStackTrace();
				}
        }
		%>
                 </table></td>
			   </tr>
              
             </table>
		  </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
      </table>      
    </td>
  </tr>
</table>
 <input type=hidden name="date" value="">
 <input type=hidden name="direct" value="">
 <input type=hidden name="jc" value="">
 <input type=hidden name="doChoice" value="content">
 <input type=hidden name="sid" value="">
</Form>
<SCRIPT language=JavaScript> 
		<!-- 
		 setCalendarBegin();
		
 //-->
</SCRIPT>  
</body>
</html>

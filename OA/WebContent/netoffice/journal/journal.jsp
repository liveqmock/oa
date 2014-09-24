<%@ page contentType="text/html; charset=gbk" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.netoffice.journal.vo.*" %>
<%@ page import="com.icss.oa.util.StringUtility" %>

<%
List journalList = null;
OfficeJournalVO vo=new OfficeJournalVO();
journalList = (List)request.getAttribute("list");
Iterator  journalIterator=null;
if(journalList!=null){
 journalIterator = journalList.iterator();
}
%>

<HTML><HEAD><TITLE>网上日记</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">


<SCRIPT language=JavaScript>

<!--
var OutPlace;

function ClearCalender()
{
	for(i=0;i<=5;i++)
	{
	CmdStr = "window.Sun"+i+".innerText = '-'";
	eval(CmdStr);

	CmdStr = "window.Mon"+i+".innerText = '-'";
	eval(CmdStr);

	CmdStr = "window.Tue"+i+".innerText = '-'";
	eval(CmdStr);

	CmdStr = "window.Wed"+i+".innerText = '-'";
	eval(CmdStr);

	CmdStr = "window.Thu"+i+".innerText = '-'";
	eval(CmdStr);

	CmdStr = "window.Fri"+i+".innerText = '-'";
	eval(CmdStr);

	CmdStr = "window.Sat"+i+".innerText = '-'";
	eval(CmdStr);
	}
}

function ShowPrevNext(year,month)
{
	if (month == 1)
	{
	prevyear = year -1;
	prevmonth = 12;
	}
	else
	{
	prevyear = year;
	prevmonth = parseInt(month) - 1;	
	}
	
	if (month == 12)
	{
	nextyear = year + 1;
	nextmonth = 1;
	}
	else
	{
	nextyear = year;
	nextmonth = parseInt(month) + 1;
	}

	window.PreMon .innerHTML = "<INPUT type=image src='<%=request.getContextPath()%>/images/dateback.gif' id=prev name=prev onclick=SetCalender("+prevyear+","+prevmonth+")>";
	window.ThisMon .innerHTML = "<font color=#FFFFFF>"+year +", "+month+"</font>";
	window.NextMon .innerHTML = "<INPUT type=image src='<%=request.getContextPath()%>/images/datefwd.gif' id=next name=next onclick=SetCalender("+nextyear+","+nextmonth+")>";

}

function SetCalender(year,month)
{
ClearCalender();
ShowPrevNext(year,month);
window.jForm.inyear.value = year;
//alert(year);
window.jForm.inmonth .value = month;

var Mydate = new Date(year,month-1,1);
FirstDayInWeek = Mydate.getDay();

var Mydate1 = new Date(year,month,0)
DaysNumber = Mydate1.getDate();

AddValue = FirstDayInWeek;
	for(i=1;i<=DaysNumber;i++)
	{
		Temp = i - 1 + AddValue;
		WeekRow = Math.round (Temp/7-0.5);
		WeekColumn = Temp
		while (WeekColumn >= 7)
		{
		WeekColumn = WeekColumn - 7;
		}

		switch (WeekColumn)
		{
		case 0:
			RowName = "Sun";
			break;
		case 1:
			RowName = "Mon";
			break;
		case 2:
			RowName = "Tue";
			break;
		case 3:
			RowName = "Wed";
			break;
		case 4:
			RowName = "Thu";
			break;
		case 5:
			RowName = "Fri";
			break;
		case 6:
			RowName = "Sat";
			break;
		}

		var datex=year+"-"+month+"-"+i;
		
	//CmdStr = "window.jForm."+RowName+WeekRow+".innerHTML = '<a href=javascript:GoToDate("+year+","+month+","+i+")>"+i+"</a>'";
	
		CmdStr = "window."+RowName+WeekRow+".innerHTML = '<a href=\"CalendarChoiceServlet2?date="+datex+"\" >"+i+"</a>'";
		eval(CmdStr);

		var d;
		d=new Date();
		var today=d.getDate();
		
		if(today == i){
			CmdStr = "window."+RowName+WeekRow+".innerHTML = '<a href=\"CalendarChoiceServlet2?date="+datex+"\" ><font color=\\\'#FF0000\\\'>"+today+"</font></a>'";
			//CmdStr = "window.jForm."+RowName+WeekRow+".color=\"#FF0000\"";
			//alert(CmdStr);
			eval(CmdStr);
			
		}
       	
		
	}

	
}


function JumpToDate(){ //v3.0
	year = window.jForm.inyear.options[window.jForm.inyear.selectedIndex].value;
	month = window.jForm.inmonth .options[window.jForm.inmonth.selectedIndex].value;
	SetCalender(year,month);

}

function GoToDate(year,month,day)
{
dateValue = year*10000+month*100+day;
OutStr = "window.parent."+OutPlace+".value = '"+dateValue+"'";
eval(OutStr);
window.parent .hideLayer();
}

function ShowCalendar(OutPlaceStr)
{
	var datenow = new Date();
	year = datenow.getYear();
	month = datenow.getMonth() + 1;
	SetCalender(year,month);
	OutPlace = OutPlaceStr;
	
}

function delete1(url)
{
	  if(_check("没有可删除的对象","请至少选择一个要删除的日记"	)){
  	      document.jForm.action=url+"/servlet/DelJournalServlet";
	      document.jForm.submit();
	      return true;	
      }
      return false;	
}

function _update(url)
{	
	var j;
	    for(j=0;j<document.jForm.jc.length;j++){
        if(document.jForm.jc[j].checked){
		document.jForm.action=url+"/servlet/UpdateJournalServlet";
		document.jForm.submit();	
		return;
		}
      }
	  alert("未选中修改项！");
}

function  showContent(rj_id,url){
  document.jForm.action=url+"/servlet/ContentShowServlet?=1000";
  alert(document.jForm.action);
  document.jForm.submit();
  
}

function _export(url){
    if(_check("没有可导出的日记","请选择至少一个要导出的日记！")){
        document.jForm.action=url+"/servlet/exportJourServlet";
        document.jForm.submit();
        return true;
    }
    return false;
}

function _check(msg1,msg2){
	if(document.jForm.jc == undefined){
		alert(msg1);
		return false;
	}else if( document.jForm.jc.length == undefined){
        if(document.jForm.jc.checked){	
			return true;
		}else{
		    alert(msg2);
		    return false;
		}
	}else{
	    for(var j=0;j<document.jForm.jc.length;j++){
	        if(document.jForm.jc[j].checked){	
		        return true;
			}
		}
	    alert(msg2);
	    return false;  
	}
}
		//-->
</SCRIPT>
<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module2");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

<META content="MSHTML 6.00.2600.0" name=GENERATOR>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</HEAD>

<BODY bgColor=#66CCFF background="<%=request.getContextPath()%>/images/bg-08.gif" leftMargin=0 topMargin=0  
marginheight="0" marginwidth="0">
<br>

<Form name="jForm" action="">
<%
  String _page_num=request.getParameter("_page_num");
  if(_page_num==null||_page_num.equals(""))
  _page_num="1";
  
  //得到设置的月份数
    String months=request.getParameter("months");
  
%>
<input type=hidden name="_page_num" value="<%=_page_num%>">
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0" bgColor='#D6E7F7'>
  <tr>
    <td width="2%" background="images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif"></td>
    <td width="97%" background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">日记</td>
    <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif"></div></td>
  </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0" bgColor='#D6E7F7'>
  <tr>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
    <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="100%" background="<%=request.getContextPath()%>/images_new/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="27%" height="205" align="center" valign="" bgcolor="#D6E7F7"><div align="left"><table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
                    
                    <tr align="center">
                      <td  bgcolor="#D6E7F7" height="0"></td>
                      </tr>
                    <tr  height="100%" bgcolor="#D6E7F7">
                      <td height="100%" align="center" valign="middle"><table cellspacing="2" cellpadding="0" border="0" id="Table1">
                        <tr>
                          <td>
                            <table width="180" border="0" cellspacing="0" cellpadding="0" id="Table2">
                              <tr bgcolor="#FFAE00">
                                <td id="PreMon" width="20" align="left">
                                  <input type="image" src="<%=request.getContextPath()%>/images/dateback.gif" id="prev222" name="prev22" onClick="PrevMonth(2001,1)" width="20" height="18"></td>
                                <td id="ThisMon" width="142" align="middle" bgcolor="#FFAE00">2001,7</td>
                                <td id="NextMon" width="18" align="right"><input type="image" src="<%=request.getContextPath()%>/images/datefwd.gif" id="next222" name="next22" onClick="NextMonth(2001,1)" width="18" height="18"></td>
                              </tr>
                              <tr>
                                <td colspan="3" valign="middle">
                                  <table id="CalenderTable" width="100%" border="1" cellspacing="0" cellpadding="0" bgcolor="#FFEBC0" bordercolorlight="#000000" bordercolordark="#ffffff">
                                    <tr>
                                      <td align="middle">日</td>
                                      <td align="middle">一</td>
                                      <td align="middle">二</td>
                                      <td align="middle">三</td>
                                      <td align="middle">四</td>
                                      <td align="middle">五</td>
                                      <td align="middle">六</td>
                                    </tr>
                                    <tr>
                                      <td align="middle" id="Sun0">-</td>
                                      <td align="middle" id="Mon0">-</td>
                                      <td align="middle" id="Tue0">-</td>
                                      <td align="middle" id="Wed0">-</td>
                                      <td align="middle" id="Thu0">-</td>
                                      <td align="middle" id="Fri0">-</td>
                                      <td align="middle" id="Sat0">-</td>
                                    </tr>
                                    <tr>
                                      <td align="middle" id="Sun1">-</td>
                                      <td align="middle" id="Mon1">-</td>
                                      <td align="middle" id="Tue1">-</td>
                                      <td align="middle" id="Wed1">-</td>
                                      <td align="middle" id="Thu1">-</td>
                                      <td align="middle" id="Fri1">-</td>
                                      <td align="middle" id="Sat1">-</td>
                                    </tr>
                                    <tr>
                                      <td align="middle" id="Sun2">-</td>
                                      <td align="middle" id="Mon2">-</td>
                                      <td align="middle" id="Tue2">-</td>
                                      <td align="middle" id="Wed2">-</td>
                                      <td align="middle" id="Thu2">-</td>
                                      <td align="middle" id="Fri2">-</td>
                                      <td align="middle" id="Sat2">-</td>
                                    </tr>
                                    <tr>
                                      <td align="middle" id="Sun3">-</td>
                                      <td align="middle" id="Mon3">-</td>
                                      <td align="middle" id="Tue3">-</td>
                                      <td align="middle" id="Wed3">-</td>
                                      <td align="middle" id="Thu3">-</td>
                                      <td align="middle" id="Fri3">-</td>
                                      <td align="middle" id="Sat3">-</td>
                                    </tr>
                                    <tr>
                                      <td align="middle" id="Sun4">-</td>
                                      <td align="middle" id="Mon4">-</td>
                                      <td align="middle" id="Tue4">-</td>
                                      <td align="middle" id="Wed4">-</td>
                                      <td align="middle" id="Thu4">-</td>
                                      <td align="middle" id="Fri4">-</td>
                                      <td align="middle" id="Sat4">-</td>
                                    </tr>
                                    <tr>
                                      <td align="middle" id="Sun5">-</td>
                                      <td align="middle" id="Mon5">-</td>
                                      <td align="middle" id="Tue5">-</td>
                                      <td align="middle" id="Wed5">-</td>
                                      <td align="middle" id="Thu5">-</td>
                                      <td align="middle" id="Fri5">-</td>
                                      <td align="middle" id="Sat5">-</td>
                                    </tr>
                                </table></td>
                              </tr>
                              <tr bgcolor="#E19831">
                                <td colspan="3" align="middle">
                                  <select id="inyear" name="inyear" onChange="JumpToDate(this,0)" style="FONT-SIZE: 9pt; HEIGHT: 18px">
                                    <option value="1993" >1993</option>
                                    <option value="1994">1994</option>
                                    <option value="1995">1995</option>
                                    <option value="1996">1996</option>
                                    <option value="1997">1997</option>
                                    <option value="1998">1998</option>
                                    <option value="1999">1999</option>
                                    <option value="2000">2000</option>
                                    <option value="2001">2001</option>
                                    <option value="2002">2002</option>
                                    <option value="2003">2003</option>
                                    <option value="2004" selected>2004</option>
                                    <option value="2005">2005</option>
                                    <option value="2006">2006</option>
                                    <option value="2007">2007</option>
                                    <option value="2008">2008</option>
                                    <option value="2009">2009</option>
                                  </select>
            年
            <select id="inmonth" name="inmonth" onChange="JumpToDate(this,0)" style="FONT-SIZE: 9pt; HEIGHT: 18px">
              <option value="1" selected >1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>
            月 </td>
                              </tr>
                          </table></td>
                        </tr>
                      </table>
                        <br>
                        <div align="left">&nbsp;&nbsp; 说明:此系统已设置为保留<%=months%>月内的日记</div></td>
                      </tr>
                  </table>
              </div></td>
			  <td width="2" rowspan="" valign="bottom" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
              <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0" bgColor='#D6E7F7'>
                <tr>
                  
                  <td width="6%" bgcolor="#FFFBEF">&nbsp;</td>
                  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="6%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">序号</div></td>
                  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="38%" bgcolor="#FFFBEF"><div align="center" class="title-04">标题</div></td>
                  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="28%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">日期</div></td>
                  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="12%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">天气</div></td>
				  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="10%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">操作</div></td>
                </tr>
                <tr>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
				  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                
			 <%
	       int i=0;
           while(journalIterator.hasNext()){
	          vo = (OfficeJournalVO)journalIterator.next();
	          i++;
			  
			  //alert(d);
			  Long time=vo.getRjDate();
			  long time1=time.longValue();
			  

			  Date date=new Date(time1);

			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			  String formatTime = formatter.format(date);
			  //out.print(date);
			  //int year1=date.getYear();
			 // int year=year1+1900;
			  //int month1=date.getMonth();
			  //int month=month1+1;
			  //int day=date.getDate();
			  //out.print(year);	
			  String headline=vo.getRjHeadline();  
			  String subHead=null;
			//  if(headline.length()<15){
			//    subHead=headline;
			//  }
			//  else{
			//    subHead=headline.substring(0,20);//这种方法无法区分中文和英语的长度。
			//  }
			  subHead=StringUtility.getMoreString(headline,30,"..",0);
			  
           %>

		       <%
                 if(i%2!=0){

               %>
                 <tr onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#D8EAF8';" bgcolor="#D8EAF8">
                    <td class="text-01" align="center"><input type="checkbox" name="jc" value="<%=vo.getRjId()%>"></td>
                    <td height="22" class="text-01" align="center"><%=i%></td>
                    <td  class="text-01"><div align="left"> <a 
                        
                        href="<%=request.getContextPath()%>/servlet/ContentShowServlet?jid=<%=vo.getRjId()%>&&doChoice=content">&nbsp;<%=subHead%></a> </div></td>
                    <td height="22"><div align="center" class="text-01"><%=formatTime%></div></td>
                    <td height="22"><div align="center" class="text-01"><%=vo.getRjWeather()%> </div></td>
					<td height="22"><div align="center" class="text-01"><a href="<%=request.getContextPath()%>/servlet/ContentShowServlet?jid=<%=vo.getRjId()%>&&doChoice=update1&&_page_num=<%=_page_num%>"">·修改</a> </div></td>
                  </tr>
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
					<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  </tr>
				 <%
                 }//if end

			     else{
              %>
                 <tr onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';" bgcolor="#F2F9FF">
                    <td class="text-01" align="center"><input type="checkbox" name="jc" value="<%=vo.getRjId()%>"></td>
                    <td height="22" class="text-01" align="center"><%=i%></td>
                    <td  class="text-01"><div align="left"> 
                    <a  href="<%=request.getContextPath()%>/servlet/ContentShowServlet?jid=<%=vo.getRjId()%>&&doChoice=content"" >&nbsp;<%=subHead%></a> </div></td>
                    <td height="22"><div align="center" class="text-01"><%=formatTime%></div></td>
                    <td height="22"><div align="center" class="text-01"><%=vo.getRjWeather()%> </div></td>
					<td height="22"><div align="center" class="text-01"><a href="<%=request.getContextPath()%>/servlet/ContentShowServlet?jid=<%=vo.getRjId()%>&&doChoice=update1&&_page_num=<%=_page_num%>"">·修改</a> </div></td>
                  </tr>
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
					<td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  </tr>
				<%
               }//else end
            %>
			<%
               }//while
            %>
                
               
              </table></td>
            </tr>
            <tr>
                      <td height="2"  background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2"  background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <%
                      if(i<=7){
                      %>
                      <td height="2"  background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <%}%>
            </tr>
                    
          </table>          </td>
        </tr>
    </table></td>
    <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
  </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
    <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
	   <%@ include file = "../../include/netofficePageScrollBar.jsp" %>
	</td>
    
    <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
  </tr>
</table>
<table align="center" width="98%">
  <tr>
    <TD width="30%" align=right vAlign=center>&nbsp;</TD>
    <TD width="20%" height=54 align=left vAlign=center><A href="<%=request.getContextPath()%>/netoffice/journal/write1.jsp?_page_num=<%=_page_num%>" ><img src="<%=request.getContextPath()%>/images/write_journal.gif" border="0" ></A>  </TD>
    
    <td width="20%" align="">
					   
					   <img  onClick="javascript:delete1('<%=request.getContextPath()%>')"    src="<%=request.getContextPath()%>/images/botton-delete.gif" style="cursor:hand">
	</td>
	 <td width="30%" align="">
					   
					<!--a href = "javascript:_export('<%=request.getContextPath()%>')" >
					<img src="<%=request.getContextPath()%>/images/exportjournal.gif" border="0" style="cursor:hand;">
					</a-->
					<img  onClick="javascript:_export('<%=request.getContextPath()%>')"    src="<%=request.getContextPath()%>/images/exportjournal.gif"  style="cursor:hand">
	</td>
  </tr>
</table>
<TBODY><TR vAlign=top align=middle><TD>&nbsp;</TD>
    <TD width=11>&nbsp;</TD></TR>
  <TR>
    <TD align=middle colSpan=2 height=60>
      
<TABLE cellSpacing=0 cellPadding=0 width=760 align=center bgColor=#c0c0c0 
border=0>
  <TBODY>
  <TR>
          
        </TR></TBODY></TABLE>
 
  <SCRIPT language=JavaScript>
		<!--
		var d;
		d=new Date();
		var iyear=d.getYear();
		var imonth=d.getMonth();
		
       SetCalender(iyear,imonth+1);
		
		

		//alert(d.getDate());
		//alert(d.getYear());
//-->
</SCRIPT>  
</Form>
  </BODY></HTML>

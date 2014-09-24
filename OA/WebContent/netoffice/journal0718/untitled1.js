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
	//alert(document.jForm.jc.length);
	var j;
	if(document.jForm.jc == undefined){
		alert("没有可删除对象");		
	}
	else if( document.jForm.jc.length == undefined){
        if(document.jForm.jc.checked){
			document.jForm.action=url+"/servlet/DelJournalServlet";
			document.jForm.submit();	
			return;
		}
	}
	else{
	    for(j=0;j<document.jForm.jc.length;j++){
	        if(document.jForm.jc[j].checked){
			document.jForm.action=url+"/servlet/DelJournalServlet";
			//document.jForm.action1.value = 'del';
			document.jForm.submit();	
			return;
			}
		}

	}

	  alert("未选中删除项！");
	
}

function _update(url)
{	
	
	var j;
	    for(j=0;j<document.jForm.jc.length;j++){
        if(document.jForm.jc[j].checked){
		document.jForm.action=url+"/servlet/UpdateJournalServlet";
		//document.jForm.action1.value = 'del';
		document.jForm.submit();	
		return;
		
		}

		
      }

	  alert("未选中修改项！");
	
}

function  showContent(rj_id,url){
//document.jForm..value="sdfgsd";
  document.jForm.action=url+"/servlet/ContentShowServlet?=1000";
  alert(document.jForm.action);
  //document.jForm.action1.value = 'del';
  document.jForm.submit();
  
}

function _export(url){
  //alert(url);    
  var j;
	if(document.jForm.jc == undefined){
		alert("没有选择导出对象");		
	}
	else if( document.jForm.jc.length == undefined){
        if(document.jForm.jc.checked){
			document.jForm.action=url+"/servlet/exportJourServlet";
			document.jForm.submit();  	
			return;
		}
	}
	else{
	    for(j=0;j<document.jForm.jc.length;j++){
	        if(document.jForm.jc[j].checked){
			document.jForm.action=url+"/servlet/exportJourServlet";
			//document.jForm.action1.value = 'del';
			document.jForm.submit();	
			return;
			}
		}

	}

	  alert("没有选择导出对象！");
  //document.jForm.action=url+"/servlet/exportJourServlet";
  //document.jForm.submit();     
}
		//-->
</SCRIPT>
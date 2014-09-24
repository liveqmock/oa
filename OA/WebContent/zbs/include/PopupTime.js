var gcToggle = "#ffff00";
var gcBG = "#CCCCFF";

var ctlObj = new Object();
var winPopupWindow = new Object(); 
N = (document.all) ? 0 : 1;
 
function IgnoreEvents(e)
{
  return false
}
function HandleFocus()
{
  if (winPopupWindow)
  {
    if (!winPopupWindow.win.closed)
    {
      winPopupWindow.win.focus()
    }
    else
    {
      window.top.releaseEvents (Event.CLICK|Event.FOCUS)
    }
  }
  return false
}

function fPopUpDlgTime(dialogType,ctl,WINname,WINwidth,WINheight){
N = (document.all) ? 0 : 1;

	var endtarget;
	
		endtarget = "/popup_posincharge.htm";

	if(N){
	    	showx = window.screen.width /2;
	    	showy = window.screen.height /2;
	}else{
	    showx = event.screenX - event.offsetX - 4 - WINwidth ; // + deltaX;
	    showy = event.screenY - event.offsetY + 18; // + deltaY;
	}

	if (dialogType == "POPUPLIST_CONTRACTSTOP" ){
	    if(N){
	    	showx = window.screen.width /2;
	    	showy = window.screen.height /2;
	    }else{
	        showx = event.screenX - event.offsetX - WINwidth + 150; 
  	        showy = event.screenY - event.offsetY + 20; 
	    }
	} 

	newWINwidth = WINwidth + 4 + 18;
	var retval;
	if(N){
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
            winPopupWindow.returnedValue = new Array(); 
   	    if (dialogType == "POPUPLIST_CONTRACTSTOP" ){
	        if(N){
            	    winPopupWindow.returnFunc = HRMContractN6SubmitDelete;
	        }
	    }
            winPopupWindow.args = ctl;
            winPopupWindow.win = window.open(endtarget,"PopupDialog","dependent=yes,left="+showx + ",top=" + showy + ",width="+newWINwidth + ",height=" + WINheight )
            winPopupWindow.win.focus()
            winPopupWindow.win.screen.top = showy;
            winPopupWindow.win.screen.left = showx;
            return winPopupWindow;
	}else{
		var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:no;Resizeable=no';
	    retval = window.showModalDialog(endtarget, " ", features );
        }

	if( retval != null ){
		retval = trim(retval);
		ctl.value = retval;
	}else{
		//alert("canceled");
	}
}

function fPopUpCalendarTimeDlg(ctrlobj)
{
	if(N){
	    showx = 220 ; // + deltaX;
	    showy = 220; // + deltaY;
	}else{
	    showx = event.screenX - event.offsetX - 4 - 110 ; // + deltaX;
	    showy = event.screenY - event.offsetY + 18; // + deltaY;
        }
	newWINwidth = 210 + 4 + 18;
	if(N){
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
            winPopupWindow.args = ctrlobj;
            winPopupWindow.returnedValue = new Array(); 
            // winPopupWindow.returnFunc = PopupRetFunc;
            winPopupWindow.args = ctrlobj;
	    newWINwidth = 202;
            winPopupWindow.win = window.open("/CalendarDlg.htm","CalendarDialog","dependent=yes,left="+showx + ",top=" + showy + ",width="+newWINwidth + ",height=182px" )
            winPopupWindow.win.focus()
            return winPopupWindow;
	}else{
	    retval = window.showModalDialog("/CalendarDlg.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
        }
        
	if( retval != null ){
		ctrlobj.value = retval;
	}else{
		//alert("canceled");
	}
}

function fPopUpCalendarTimeDlg_New(ctrlobj)
{
	if(N){
	    showx = 220 ; // + deltaX;
	    showy = 220; // + deltaY;
	}else{
	    showx = event.screenX - event.offsetX - 4 - 110 ; // + deltaX;
	    showy = event.screenY - event.offsetY + 18; // + deltaY;
        }
	newWINwidth = 210 + 4 + 18;
	if(N){
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
            winPopupWindow.args = ctrlobj;
            winPopupWindow.returnedValue = new Array(); 
            // winPopupWindow.returnFunc = PopupRetFunc;
            winPopupWindow.args = ctrlobj;
	    newWINwidth = 202;
            winPopupWindow.win = window.open("/CalendarDlgTime.htm","CalendarDialog","dependent=yes,left="+showx + ",top=" + showy + ",width="+newWINwidth + ",height=182px" )
            winPopupWindow.win.focus()
            return winPopupWindow;
	}else{
	    retval = window.showModalDialog("/CalendarDlgTime.htm", "", "dialogWidth:197px; dialogHeight:250px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
        }
        
	if( retval != null ){
		if (retval == ""){
			document.all(ctrlobj).value=retval;
			return retval;
		}else{
			document.all(ctrlobj).value=retval + ":00";
			return retval + ":00";
		}
		
	}else{
		return;
	}
}
function fPopUpCalendarTimeDlg_NewReturn(ctrlobj)
{
	if(N){
	    showx = 220 ; // + deltaX;
	    showy = 220; // + deltaY;
	}else{
	    showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
	    showy = event.screenY - event.offsetY + 18; // + deltaY;
        }
	newWINwidth = 210 + 4 + 18;
	if(N){
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
            winPopupWindow.args = ctrlobj;
            winPopupWindow.returnedValue = new Array(); 
            // winPopupWindow.returnFunc = PopupRetFunc;
            winPopupWindow.args = ctrlobj;
	    newWINwidth = 202;
            winPopupWindow.win = window.open("/CalendarDlgTime.htm","CalendarDialog","dependent=yes,left="+showx + ",top=" + showy + ",width="+newWINwidth + ",height=182px" )
            winPopupWindow.win.focus()
            return winPopupWindow;
	}else{
	    retval = window.showModalDialog("/CalendarDlgTime.htm", "", "dialogWidth:197px; dialogHeight:230px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
        }
        
	if( retval != null ){
		if (retval == ""){
			return retval;
		}else{
			return retval + ":00.000";
		}
	}else{
		//alert("canceled");
	}
}
function fPopUpColorDlg(ctrlobj)
{
    if(N){
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
            winPopupWindow.args = ctrlobj;
            winPopupWindow.returnedValue = new Array(); 
            winPopupWindow.win = window.open("/source/style/js/calendar/vote/palette.htm","CalendarDialog","dependent=yes,width=242px,height=333px" )
            winPopupWindow.win.focus()
            return winPopupWindow;
    }else{	 
	showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 380 + 4 + 18;

	retval = window.showModalDialog("/source/style/js/calendar/vote/palette.htm", "", "dialogWidth:242px; dialogHeight:333px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}else{
	}
    }
}

function fPopUpChart(dialogType,WINwidth,WINheight, para1,para2)
{
    if(N){
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
	    if(dialogType == "POPUP_VOTECHART"){
		endtarget = "/source/style/js/calendar/vote/vote-chart.do?action=Init&serialId=" + para1;
	    }
    	    showx = 30;
	    showy = 30;
            winPopupWindow.win = window.open(endtarget,"CalendarDialog","dependent=yes,width="+WINwidth + ",height="+WINheight + ",left=" + showx + ",top=" + showy  )
            winPopupWindow.win.focus()
            return winPopupWindow;
    }else{
	showx = event.screenX / 3 ; // + deltaX;
	showy = event.screenY / 3 ; // + deltaY;

	var features =
		'dialogWidth:'  + WINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:no;Resizeable=no';

	if(dialogType == "POPUP_VOTECHART"){
		endtarget = "/source/style/js/calendar/vote/vote-chart.do?action=Init&serialId=" + para1;
	}
	var retval = window.showModalDialog(endtarget, " ", features );
    }
}

function Gwp_GetSelected(aCell)
{
	if(document.all){
  		window.returnValue = aCell.innerText;
		window.close();
	}else{
		// alert(opener.winPopupWindow.returnFunc);
		// alert(aCell.innerHTML);
		// alert(aCell.childNodes[0].nodeValue);
		// alert(opener.winPopupWindow.args);
		// alert(opener.winPopupWindow.args.value);
	    opener.winPopupWindow.returnedValue = aCell.childNodes[0].nodeValue;
		opener.winPopupWindow.args.value = trim(aCell.childNodes[0].nodeValue);
		if(opener.winPopupWindow.returnFunc) opener.winPopupWindow.returnFunc();
 		window.close();
	}
  
}

//用于信息检索的函数
function selectType(type){
	document.forms[0].SearchType.value = type.value;
	//alert(document.forms[0].SearchType.value);
	if (type.value == "date"){
		//selectDate(document.getElementsByName("SelDate")[0]);
		document.getElementById("date").style.removeAttribute("display");
		document.getElementById("people").style.display = "none";
	}
	else if (type.value == "people"){
		document.getElementById("date").style.display = "none";
		document.getElementById("people").style.removeAttribute("display");
	}
	else document.getElementById("date").style.display = "none";
}

function selectDate(date){
	var da = new Date();
	if (date.value != "other"){
		document.forms[0].Info_Search_EndDate.value = da.getFullYear() + "/" + (new Number((da.getMonth() + 1)).toString().length == 1 ? ("0" + (da.getMonth() + 1)) : (da.getMonth() + 1)) + "/" + (new Number(da.getDate()).toString().length == 1 ? ("0" + da.getDate()) : da.getDate());
		da.setDate(da.getDate() - parseInt(date.value));
		document.forms[0].Info_Search_StartDate.value = da.getFullYear() + "/" + (new Number((da.getMonth() + 1)).toString().length == 1 ? ("0" + (da.getMonth() + 1)) : (da.getMonth() + 1)) + "/" + (new Number(da.getDate()).toString().length == 1 ? ("0" + da.getDate()) : da.getDate());
	}
}

function getDateTime(elName){
	//fPopUpCalendarDlg(document.getElementsByName(elName)[0]);
	return fPopUpCalendarTimeDlg_New(elName);
}
function getSubDateTime(elName,i){
	//fPopUpCalendarDlg(document.getElementsByName(elName)[0]);
	var index = new Number(i);
	var reDate = fPopUpCalendarTimeDlg_NewReturn(elName);
	if (reDate!=null){
		document.getElementsByName(elName)[index+1].value = reDate;
	}
}
//选择考勤人员查询
function selPeople(f1,f2){
  dept=window.showModalDialog("/GWPWEB/Attendance/PeopleList.jsp","","dialogWidth:380px;dialogHeight:360px;center:1;status:0");
  if(dept!=null){
    if(dept[0]!=null&&dept[0]!="")f2.value=dept[0];
    if(dept[1]!=null&&dept[1]!="")f1.value=dept[1];
  }
}

function fPopUpCalendarTimeDlg_App()
{
	if(N){
	try{
	    window.top.captureEvents (Event.CLICK|Event.FOCUS);
    	    window.top.onclick=IgnoreEvents;
            window.top.onfocus=HandleFocus;
             winPopupWindow.returnedValue = new Array(); 
            // winPopupWindow.returnFunc = PopupRetFunc;
	    newWINwidth = 202;
	    }catch(e){
	
	}
            winPopupWindow.win = window.open("/CalendarDlgTime.htm","CalendarDialog","dependent=yes,width="+newWINwidth + ",height=210px" )
            winPopupWindow.win.focus()
            return winPopupWindow;
	}else{
	    retval = window.showModalDialog("/CalendarDlgTime.htm", "", "dialogWidth:197px; dialogHeight:210px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
        }
        
	if( retval != null ){
		//alert(ctrlobj.name);
		if (retval == ""){
			return retval;
		}else{
			return retval + ":00.000";
		}
		//ctrlobj.value = retval;
	}else{
		return "";
	}
}
function getAppDateTime(){
	//fPopUpCalendarDlg(document.getElementsByName(elName)[0]);
	return fPopUpCalendarTimeDlg_App();
}
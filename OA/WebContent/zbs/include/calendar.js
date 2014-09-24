//需要修改的目标日期对象
var mm_obj;
var mm_curday;
var mm_today;
var mm_months = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月");  
var mm_daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);  
/*var mm_days = new Array("Sunday", "Monday", "Tuesday",  "Wednesday", "Thursday", "Friday", "Saturday");*/  
var mm_days = new Array("日","一", "二", "三",  "四", "五", "六");  

//得到某月的天数
function getDays(month, year) {  
if (1 == month)  
return ((0 == year % 4) && (0 != (year % 100))) ||  
(0 == year % 400) ? 29 : 28;  
else  
return mm_daysInMonth[month];  
}  

//得到今天的年,月,日 
function getToday() {  
this.now = new Date();
this.year = this.now.getFullYear();  
this.month = this.now.getMonth();  
this.day = this.now.getDate();  
}  

mm_today = new getToday(); 
mm_curday = new Date();

function newCalendar() {  
mm_today = new getToday();  
var parseYear = parseInt(document.all.mm_year[document.all.mm_year.selectedIndex].text);  
var newCal = new Date(parseYear, document.all.mm_month.selectedIndex, 1); 
var day = -1;  
var startDay = newCal.getDay();  
var daily = 0;  
if ((mm_today.year == newCal.getFullYear()) &&(mm_today.month == newCal.getMonth()))  
day = mm_today.day;  
var tableCal = document.all.calendar.tBodies.dayList;  
var intDaysInMonth =getDays(newCal.getMonth(), newCal.getFullYear());  
for (var intWeek = 0; intWeek < tableCal.rows.length;intWeek++) 
for (var intDay = 0;intDay < tableCal.rows[intWeek].cells.length;intDay++) 
{ 
var cell = tableCal.rows[intWeek].cells[intDay];
if ((intDay == startDay) && (0 == daily)) 
daily = 1; 

if(day==daily) 
//今天，调用今天的Class 
cell.className = "today"; 
else if(intDay==6) 
//周六 
cell.className = "sunday"; 
else if (intDay==0) 
//周日 
cell.className ="satday"; 
else 
//平常 
cell.className="normal";

if ((daily==mm_curday.getDate())&&(parseYear==mm_curday.getYear())&&(mm_curday.getMonth()==document.all.mm_month.selectedIndex)){
cell.className="curday";}
if ((daily > 0) && (daily <= intDaysInMonth)) 
{ 
cell.innerText = daily; 
daily++; 
} 
else 
cell.innerText = ""; 
} 
} 

//这段代码处理鼠标点击的情况 
function getDate() { 
var sDate; 
if ("TD" == event.srcElement.tagName)
	{
	if ("" != event.srcElement.innerText) 
	{ 
	sDate = document.all.mm_year.value + "-" + document.all.mm_month.value + "-" + event.srcElement.innerText; 
	mm_curday = new Date(document.all.mm_year.value,document.all.mm_month.selectedIndex,event.srcElement.innerText);
	m_obj.value=sDate ;
	showhidecalendar('hide', '', 0);
	} 
	}
}


//显示和隐藏日历
function showhidecalendar(v,f,_frm){
    _sForm=document.forms[_frm];
	m_obj=_sForm.elements(f);
    newCalendar();
    var obj=document.all["Lcal"];
     if (obj.style) { 
			obj=obj.style; 
			v=(v=='show')?'visible':(v='hide')?'hidden':v; 
	}
	//确定日历显示位置
    if((document.body.clientWidth - event.x) < 130)
		obj.left=document.body.clientWidth - 140 + ((document.body.scrollLeft=="undefined")?0:document.body.scrollLeft);
	else
		obj.left=event.x+ ((document.body.scrollLeft=="undefined")?0:document.body.scrollLeft);
    
	if((document.body.clientHeight - event.y) < 130)
		obj.top=document.body.clientHeight - 140 + ((document.body.scrollTop=="undefined")?0:document.body.scrollTop);
	else
		obj.top=event.y + ((document.body.scrollTop=="undefined")?0:document.body.scrollTop);
	obj.visibility=v;
}

//显示和隐藏日历
function showhidecalendar_obj(v,inputobj){
	m_obj = inputobj;
    newCalendar();
    var obj=document.all["Lcal"];
     if (obj.style) { 
			obj=obj.style; 
			v=(v=='show')?'visible':(v='hide')?'hidden':v; 
	}
	//确定日历显示位置
    if((document.body.clientWidth - event.x) < 130)
		obj.left=document.body.clientWidth - 140 + ((document.body.scrollLeft=="undefined")?0:document.body.scrollLeft);
	else
		obj.left=event.x+ ((document.body.scrollLeft=="undefined")?0:document.body.scrollLeft);
    
	if((document.body.clientHeight - event.y) < 130)
		obj.top=document.body.clientHeight - 140 + ((document.body.scrollTop=="undefined")?0:document.body.scrollTop);
	else
		obj.top=event.y + ((document.body.scrollTop=="undefined")?0:document.body.scrollTop);
	obj.visibility=v;
}


//<!---- start of calendar ------>
function writeDIV(){
document.write("<DIV id=Lcal style='height:130 width:130;POSITION: absolute ; TOP: 0px; left:150px ;VISIBILITY: hidden; Z-INDEX: 2' onMouseDown=MM_dragLayer('Lcal','',0,0,0,0,true,false,-1,-1,-1,-1,false,false,0,'',false,'');>");
document.write("<TABLE border=0 bgcolor='#0f249b' cellspacing='0' cellpadding='1'><tr><td>");
document.write("<TABLE border=0 bgcolor='#FFFFFF' cellspacing='0' cellpadding='2' width=100% height=100%>");
document.write("<tr align=left bgcolor='#660099'><td width=120 height=10 class='n9'><font color='#ffffff'><b>日历</b></font></td><td bgcolor='#cccccc' width=10 height=10 style='BACKGROUND-COLOR: lightsteelblue; BORDER-BOTTOM: #000000 1px solid; BORDER-LEFT: #FFFFFF 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #ffffff 1px solid; COLOR: #666699;' align='center'><a onclick=\"showhidecalendar('hide', '', 0)\" style='cursor:hand;font-size:7px'><font style='font-family:Webdings'>r</font></a></td></tr>");
document.write("<TR><TD colspan=2>");
document.write("<TABLE ID='calendar' cellspacing='0' cellpadding='0' border=0 width=128 style='color:#999900;font-size:12px;font-family:Arial;background:#FFFFFF'> ");
document.write("<THEAD><TR><TD COLSPAN=7 ALIGN=CENTER>"); 
document.write("<SELECT ID='mm_month' ONCHANGE='newCalendar()'>");
document.write("<SCRIPT LANGUAGE='JavaScript'> for (var intLoop = 0; intLoop < mm_months.length; intLoop++) ");
document.write("document.write(\"<OPTION VALUE= \" + (intLoop + 1) + \" \" + (mm_today.month == intLoop ? \"Selected\" : \"\") + \">\" +  mm_months[intLoop]);  ");
document.write("</SCRIPT>");
document.write("</SELECT>");

document.write("<SELECT ID='mm_year' ONCHANGE='newCalendar()'>  ");
document.write("<SCRIPT LANGUAGE='JavaScript'> for (var intLoop = mm_today.year-100; intLoop < (mm_today.year + 64); intLoop++) ");
document.write("document.write(\"<OPTION VALUE= \" + intLoop + \" \" + (mm_today.year == intLoop ? \"Selected\" : \"\") + \">\" +  intLoop);  ");
document.write("</SCRIPT>");
document.write("</SELECT>");
document.write("</TD></TR>");
document.write("<TR height=1><TD colspan=7 bgcolor='#AABBCC'></TD></TR>");
document.write("<TR CLASS='days'>");
document.write("<SCRIPT LANGUAGE='JavaScript'>  ");
document.write("document.write(\"<TD class=satday>\" + mm_days[0] + \"</TD>\");  ");
document.write("for (var intLoop = 1; intLoop < mm_days.length-1; intLoop++) ");
document.write("document.write(\"<TD>\" + mm_days[intLoop] + \"</TD>\");  ");
document.write("document.write(\"<TD class=sunday>\" + mm_days[intLoop] + \"</TD>\");  ");
document.write("</SCRIPT> </TR>");

document.write("<TR height=1><TD colspan=7 bgcolor='#AABBCC'></TD></TR>");
document.write("</THEAD>");
document.write("<TBODY border=1 cellspacing=0 cellpadding=0 ID='dayList' ALIGN=CENTER ONCLICK='getDate()'>  ");
document.write("<SCRIPT LANGUAGE='JavaScript'>  ")
document.write("for (var intWeeks = 0; intWeeks < 6; intWeeks++) { ");
document.write("document.write(\"<TR style='cursor:hand'>\");  ");
document.write("for (var intDays = 0; intDays < mm_days.length; intDays++) ");
document.write("document.write(\"<TD>&nbsp;</TD>\");  ");
document.write("document.write(\"</TR>\");  ");
document.write("} ");
document.write("</SCRIPT></TBODY>  ");
document.write("</TABLE></TD></TR>");
document.write("</TABLE></TD></TR></table>");

document.write("</div>");
//document.write("<SCRIPT LANGUAGE=\"JavaScript\">");
//document.write("MM_dragLayer('Lcal','',0,0,0,0,true,false,-1,-1,-1,-1,false,false,0,'',false,'');");
//document.write("</SCRIPT>");
}
<!--
function MM_findObj(n, d) { //v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_dragLayer(objName,x,hL,hT,hW,hH,toFront,dropBack,cU,cD,cL,cR,targL,targT,tol,dropJS,et,dragJS) { //v3.0
  //Copyright 1998 Macromedia, Inc. All rights reserved.
  var i,j,aLayer,retVal,curDrag=null,NS=(navigator.appName=='Netscape'), curLeft, curTop;
  if (!document.all && !document.layers) return false;
  retVal = true; if(!NS && event) event.returnValue = true;
  if (MM_dragLayer.arguments.length > 1) {
    curDrag = MM_findObj(objName); if (!curDrag) return false;
    if (!document.allLayers) { document.allLayers = new Array();
      with (document) if (NS) { for (i=0; i<layers.length; i++) allLayers[i]=layers[i];
        for (i=0; i<allLayers.length; i++) if (allLayers[i].document && allLayers[i].document.layers)
          with (allLayers[i].document) for (j=0; j<layers.length; j++) allLayers[allLayers.length]=layers[j];
      } else for (i=0;i<all.length;i++) if (all[i].style&&all[i].style.position) allLayers[allLayers.length]=all[i];}
    curDrag.MM_dragOk=true; curDrag.MM_targL=targL; curDrag.MM_targT=targT;
    curDrag.MM_tol=Math.pow(tol,2); curDrag.MM_hLeft=hL; curDrag.MM_hTop=hT;
    curDrag.MM_hWidth=hW; curDrag.MM_hHeight=hH; curDrag.MM_toFront=toFront;
    curDrag.MM_dropBack=dropBack; curDrag.MM_dropJS=dropJS;
    curDrag.MM_everyTime=et; curDrag.MM_dragJS=dragJS;
    curDrag.MM_oldZ = (NS)?curDrag.zIndex:curDrag.style.zIndex;
    curLeft= (NS)?curDrag.left:curDrag.style.pixelLeft; curDrag.MM_startL = curLeft;
    curTop = (NS)?curDrag.top:curDrag.style.pixelTop; curDrag.MM_startT = curTop;
    curDrag.MM_bL=(cL<0)?null:curLeft-cL; curDrag.MM_bT=(cU<0)?null:curTop -cU;
    curDrag.MM_bR=(cR<0)?null:curLeft+cR; curDrag.MM_bB=(cD<0)?null:curTop +cD;
    curDrag.MM_LEFTRIGHT=0; curDrag.MM_UPDOWN=0; curDrag.MM_SNAPPED=false; //use in your JS!
    document.onmousedown = MM_dragLayer; document.onmouseup = MM_dragLayer;
    if (NS) document.captureEvents(Event.MOUSEDOWN|Event.MOUSEUP);
  } else {
    var theEvent = ((NS)?objName.type:event.type);
    if (theEvent == 'mousedown') {
      var mouseX = (NS)?objName.pageX : event.clientX + document.body.scrollLeft;
      var mouseY = (NS)?objName.pageY : event.clientY + document.body.scrollTop;
      var maxDragZ=null; document.MM_maxZ = 0;
      for (i=0; i<document.allLayers.length; i++) { aLayer = document.allLayers[i];
        var aLayerZ = (NS)?aLayer.zIndex:aLayer.style.zIndex;
        if (aLayerZ > document.MM_maxZ) document.MM_maxZ = aLayerZ;
        var isVisible = (((NS)?aLayer.visibility:aLayer.style.visibility).indexOf('hid') == -1);
        if (aLayer.MM_dragOk != null && isVisible) with (aLayer) {
          var parentL=0; var parentT=0;
          if (!NS) { parentLayer = aLayer.parentElement;
            while (parentLayer != null && parentLayer.style.position) {
              parentL += parentLayer.offsetLeft; parentT += parentLayer.offsetTop;
              parentLayer = parentLayer.parentElement; } }
          var tmpX=mouseX-(((NS)?pageX:style.pixelLeft+parentL)+MM_hLeft);
          var tmpY=mouseY-(((NS)?pageY:style.pixelTop +parentT)+MM_hTop);
          var tmpW = MM_hWidth;  if (tmpW <= 0) tmpW += ((NS)?clip.width :offsetWidth);
          var tmpH = MM_hHeight; if (tmpH <= 0) tmpH += ((NS)?clip.height:offsetHeight);
          if ((0 <= tmpX && tmpX < tmpW && 0 <= tmpY && tmpY < tmpH) && (maxDragZ == null
              || maxDragZ <= aLayerZ)) { curDrag = aLayer; maxDragZ = aLayerZ; } } }
      if (curDrag) {
        document.onmousemove = MM_dragLayer; if (NS) document.captureEvents(Event.MOUSEMOVE);
        curLeft = (NS)?curDrag.left:curDrag.style.pixelLeft;
        curTop = (NS)?curDrag.top:curDrag.style.pixelTop;
        MM_oldX = mouseX - curLeft; MM_oldY = mouseY - curTop;
        document.MM_curDrag = curDrag;  curDrag.MM_SNAPPED=false;
        if(curDrag.MM_toFront) {
          eval('curDrag.'+((NS)?'':'style.')+'zIndex=document.MM_maxZ+1');
          if (!curDrag.MM_dropBack) document.MM_maxZ++; }
        retVal = false; if(!NS) event.returnValue = false;
    } } else if (theEvent == 'mousemove') {
      if (document.MM_curDrag) with (document.MM_curDrag) {
        var mouseX = (NS)?objName.pageX : event.clientX + document.body.scrollLeft;
        var mouseY = (NS)?objName.pageY : event.clientY + document.body.scrollTop;
        newLeft = mouseX-MM_oldX; newTop  = mouseY-MM_oldY;
        if (MM_bL!=null) newLeft = Math.max(newLeft,MM_bL);
        if (MM_bR!=null) newLeft = Math.min(newLeft,MM_bR);
        if (MM_bT!=null) newTop  = Math.max(newTop ,MM_bT);
        if (MM_bB!=null) newTop  = Math.min(newTop ,MM_bB);
        MM_LEFTRIGHT = newLeft-MM_startL; MM_UPDOWN = newTop-MM_startT;
        if (NS) {left = newLeft; top = newTop;}
        else {style.pixelLeft = newLeft; style.pixelTop = newTop;}
        if (MM_dragJS) eval(MM_dragJS);
        retVal = false; if(!NS) event.returnValue = false;
    } } else if (theEvent == 'mouseup') {
      document.onmousemove = null;
      if (NS) document.releaseEvents(Event.MOUSEMOVE);
      if (NS) document.captureEvents(Event.MOUSEDOWN); //for mac NS
      if (document.MM_curDrag) with (document.MM_curDrag) {
        if (typeof MM_targL =='number' && typeof MM_targT == 'number' &&
            (Math.pow(MM_targL-((NS)?left:style.pixelLeft),2)+
             Math.pow(MM_targT-((NS)?top:style.pixelTop),2))<=MM_tol) {
          if (NS) {left = MM_targL; top = MM_targT;}
          else {style.pixelLeft = MM_targL; style.pixelTop = MM_targT;}
          MM_SNAPPED = true; MM_LEFTRIGHT = MM_startL-MM_targL; MM_UPDOWN = MM_startT-MM_targT; }
        if (MM_everyTime || MM_SNAPPED) eval(MM_dropJS);
        if(MM_dropBack) {if (NS) zIndex = MM_oldZ; else style.zIndex = MM_oldZ;}
        retVal = false; if(!NS) event.returnValue = false; }
      document.MM_curDrag = null;
    }
    if (NS) document.routeEvent(objName);
  } return retVal;
}
//-->

//<!-- end of calendar --->
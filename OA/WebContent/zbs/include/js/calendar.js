
/////////////////////////////////
// ICSS选择日期（时间）对象 1.0 //
// Author: lizb               //
// E-mail: lizb@icss.com.cn   //
//  2006-04-27                //
////////////////////////////////
/**
 * 选择日期对象，包括起始日期和结束日期
 * @param sName 第一个日期域的名称
 * @param sDate 第一个日期域的默认日期，“today”为当天日期
 * @param buttonWords 选择区域的HTML代码
 * @param inputExtend 第一个日期域的扩展属性
 * @param splitChar 两个日期域间隔的文字或者字符
 * @param sName1 第二个日期域的名称
 * @param sDate1 第二个日期域的默认日期，“today”为当天日期
 * @param inputExtend1 第二个日期域的扩展属性
 */ 
function IcssDates(sName,sDate,buttonWords,inputExtend,splitChar,sName1,sDate1,inputExtend1,isReadOnly){
  /////////////////////////////////////////////////////////////////////////
  //inputValue属性的值为"today"时表示（客户机）当前日期。
  //直接在这里把默认值修改成你想要的，使用时你就什么也不用设置了。
  this.inputName = sName ;
  this.inputName1 = sName1 ;
  this.inputValue = sDate || "";
  this.inputValue1 = sDate1 || "";
  this.inputSize = 10;
  this.inputClass = "";
  this.color = "#000080";
  this.bgColor = "#EEEEFF";
  this.buttonWidth = 20;
  this.buttonWords = buttonWords;
  this.canEdits = true;
  this.canEdits1 = true;
  this.hidesSelects = true;
  this.inputExtend = inputExtend;
  this.inputExtend1 = inputExtend1;
  this.splitChar = splitChar;
  this.hidden = false;
  /////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////
  //定义display方法。
  this.display = function ()
  {
    var reDate = /^(19[7-9]\d|20[0-5]\d)\-(0?\d|1[0-2])\-([0-2]?\d|3[01])$/;
    if (reDate.test(this.inputValue))
    {
      var dates = this.inputValue.split("-");
      var year = parseInt(dates[0], 10);
      var month = parseInt(dates[1], 10);
      var mday = parseInt(dates[2], 10);
    }
    else
    {
      var today = new Date();
      var year = today.getFullYear();
      var month = today.getMonth()+1;
      var mday = today.getDate();
    }
    var nowDate = year + "-" + month + "-" + mday;
    if (this.inputValue == "today")
      inputValue = nowDate;
    else
      inputValue = this.inputValue;
    if (this.inputValue1 == "today")
      inputValue1 = nowDate;
    else
      inputValue1 = this.inputValue1;
    var lastDay = new Date(year, month, 0);
    lastDay = lastDay.getDate();
    var firstDay = new Date(year, month-1, 1);
    firstDay = firstDay.getDay();
    
    var btnBorder =
      "border-left:1px solid " + this.color + ";" +
      "border-right:1px solid " + this.color + ";" +
      "border-top:1px solid " + this.color + ";" +
      "border-bottom:1px solid " + this.color + ";";
    var btnStyle =
      "padding-top:3px;cursor:hand;width:" + this.buttonWidth + "px;text-align:center;height:20px;top:-10px;" +
      "font:normal 12px 宋体;position:absolute;z-index:999;" ;
    var boardStyle = 
      "position:absolute;width:1px;height:1px;background:" + this.bgColor + ";top:10px;border:1px solid "+
      this.color + ";display:none;padding:3px;";
    var buttonEvent =
      " onmouseover=\"this.childNodes[0].style.borderLeft='1px solid " + this.color + "';" +
      		"this.childNodes[0].style.borderRight='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderTop='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderBottom='1px solid " + this.bgColor + "';" + 
      		"this.childNodes[0].style.backgroundColor='" + this.bgColor + "';" + 
          "this.childNodes[1].style.display='';this.style.zIndex=100;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='hidden';"
          : "") + "\"" +
      " onmouseout=\"this.childNodes[0].style.borderLeft='0px';" +
      "this.childNodes[0].style.borderRight='0px';" + 
      		"this.childNodes[0].style.borderTop='0px';" + 
      		"this.childNodes[0].style.borderBottom='0px';" + 
      		"this.childNodes[0].style.backgroundColor='';" + 
          "this.childNodes[1].style.display='none';this.style.zIndex=99;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "\"" +
      " onselectstart=\"return false;\"";
    var mdayStyle = "font:normal 9px Verdana,Arial,宋体;line-height:12px;cursor:default;color:" + this.color;
    var weekStyle = "font:normal 12px 宋体;line-height:15px;cursor:default;color:" + this.color;
    var arrowStyle = "font:bold 7px Verdana,宋体;cursor:hand;line-height:16px;color:" + this.color;
    var ymStyle = "font:bold 12px 宋体;line-height:16px;cursor:default;color:" + this.color;
    var changeMdays = 
      "var year=parseInt(this.parentNode.cells[2].childNodes[0].innerText);" +
      "var month=parseInt(this.parentNode.cells[2].childNodes[2].innerText);" +
      "var firstDay=new Date(year,month-1,1);firstDay=firstDay.getDay();" +
      "var lastDay=new Date(year,month,0);lastDay=lastDay.getDate();" +
      "var tab=this.parentNode.parentNode, day=1;" +
      "for(var row=2;row<8;row++)" +
      "  for(var col=0;col<7;col++){" +
      "    if(row==2&&col<firstDay){" +
      "      tab.rows[row].cells[col].innerHTML='&nbsp;';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "    else if(day<=lastDay){" +
      "      tab.rows[row].cells[col].innerHTML=day;" +
      "      tab.rows[row].cells[col].isDay=1;day++;}" +
      "    else{" +
      "      tab.rows[row].cells[col].innerHTML='';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "  }";
    var pyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)-1;" +
                  changeMdays + "\"";
    var pmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)-1;if(m.innerText=='0'){m.innerText=12;y.innerText=" +
                 "parseInt(y.innerText)-1;}" + changeMdays + "\"";
    var nmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)+1;if(m.innerText=='13'){m.innerText=1;y.innerText=" +
                 "parseInt(y.innerText)+1;}" + changeMdays + "\"";
    var nyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)+1;" +
                  changeMdays + "\"";
    var mdayEvent =
      " onmouseover=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.color + "';" +
          "event.srcElement.style.color='" + this.bgColor + "';" +
          "event.srcElement.style.cursor='hand';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onmouseout=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.bgColor + "';" +
          "event.srcElement.style.color='" + this.color + "';" +
          "event.srcElement.style.cursor='default';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onclick=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "var inp=this.parentNode.parentNode.parentNode.previousSibling.childNodes[0];" +
          "inp.value=this.rows[0].cells[2].childNodes[0].innerText+'-'+this.rows[0].cells[2].childNodes[2]." +
          "innerText+'-'+event.srcElement.innerText;" +
          "this.parentNode.style.display='none';this.parentNode.parentNode.style.zIndex=999;" +
          "this.parentNode.previousSibling.style.borderBottom='1px solid " + this.color + "';" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "}\"";
    if (this.inputExtend == null){
    	this.inputExtend = "";
    }
    if (this.buttonWords == null){
    	this.buttonWords = "选择";
    }
    
    var output = "";
    if(this.inputName != null && this.inputName1 != null){
	    output += "<table cellpadding=0 cellspacing=0 style='display:inline;'><tr>";
	    output += "  <td><input size=" + this.inputSize + " maxlength=10 value=\"" + inputValue + "\"";
	    output +=    (this.canEdits ? "" : " readonly") + (this.inputClass==null ?"":" class=\"" + this.inputClass + "\"") + " name=\"" + this.inputName + "\"";
	    output += " " + this.inputExtend;
	    output += "></td>";
	    output += "  <td id=\"" + sName + "_select\" width=" + this.buttonWidth + " " + (this.hidden?"style='display:none'":"") + ">";
	    output += "    <div style=\"position:absolute;overflow:visible;width:0px;height:0px;\"" + buttonEvent + ">";
	    output += "      <div style=\"" + btnStyle + "\"><nobr>" + this.buttonWords + "</nobr></div>";
	    output += "      <div style=\"" + boardStyle + "\">";
	    output += "        <table cellspacing=1 cellpadding=1 width=175" + mdayEvent + ">";
	    output += "          <tr height=20 align=center>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"上一年\"" + pyEvent + ">&lt;&lt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=left title=\"上个月\"" + pmEvent + ">&lt;</td>";
	    output += "            <td colspan=3 style=\"" + ymStyle + "\" valign=bottom>";
	    output += "              <span>" + year + "</span><span>年</span><span>" + month + "</span><span>月</span>";
	    output += "            </td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=right title=\"下个月\"" + nmEvent + ">&gt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"下一年\"" + nyEvent + ">&gt;&gt;</td>";
	    output += "          </tr>";
	    output += "          <tr height=20 align=center bgcolor=" + this.bgColor + ">";
	    output += "            <td width=14% style=\"" + weekStyle + "\">日</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">一</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">二</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">三</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">四</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">五</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">六</td>";
	    output += "          </tr>";
	    var day = 1;
	    for (var row=0; row<6; row++)
	    {
	      output += "<tr align=center>";
	      for (var col=0; col<7; col++)
	      {
	        if (row == 0 && col < firstDay)
	          output += "<td style=\"" + mdayStyle + "\">&nbsp;</td>";
	        else if (day <= lastDay)
	        {
	          output += "<td style=\"" + mdayStyle + "\" isDay=1>" + day + "</td>";
	          day++;
	        }
	        else
	          output += "<td style=\"" + mdayStyle + "\"></td>";
	      }
	      output += "</tr>";
	    }
	    
	    //增加清除和选择今天
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"2\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName;
	    output += "')[0].value='';\" style='cursor: hand'>清 除</span>";
	    output += "</td>";
	    output += "<td align=\"center\" colspan=\"5\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName + "')[0].value = '" + nowDate + "'";
	    output += "\" style='cursor: hand'>今 天 ";
	    output += nowDate;
	    output += "</span>";
	    output += "</td></tr>";
	    //
	    output += "        </table>";
	    output += "      </div>";
	    output += "    </div>";
	    output += "  </td>";
	    output += "<td valign=\"middle\">";
	    output += this.splitChar;
	    output += "</td>";
	    //第二个日历
	    if (this.inputExtend1 == null){
	    	this.inputExtend1 = "";
	    }
	    output += "  <td><input size=" + this.inputSize + " maxlength=10 value=\"" + inputValue1 + "\"";
	    output +=    (this.canEdits ? "" : " readonly") + (this.inputClass==null ?"":" class=\"" + this.inputClass + "\"") + " name=\"" + this.inputName1 + "\"";
	    output += " " + this.inputExtend1;
	    output += "></td>";
	    output += "  <td id=\"" + sName1 + "_select\" width=" + this.buttonWidth + " "+ (this.hidden?"style='display:none'":"") + ">";
	    output += "    <div style=\"position:absolute;overflow:visible;width:0px;height:0px;\"" + buttonEvent + ">";
	    output += "      <div style=\"" + btnStyle + "\"><nobr>" + this.buttonWords + "</nobr></div>";
	    output += "      <div style=\"" + boardStyle + "\">";
	    output += "        <table cellspacing=1 cellpadding=1 width=175" + mdayEvent + ">";
	    output += "          <tr height=20 align=center>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"上一年\"" + pyEvent + ">&lt;&lt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=left title=\"上个月\"" + pmEvent + ">&lt;</td>";
	    output += "            <td colspan=3 style=\"" + ymStyle + "\" valign=bottom>";
	    output += "              <span>" + year + "</span><span>年</span><span>" + month + "</span><span>月</span>";
	    output += "            </td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=right title=\"下个月\"" + nmEvent + ">&gt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"下一年\"" + nyEvent + ">&gt;&gt;</td>";
	    output += "          </tr>";
	    output += "          <tr height=20 align=center bgcolor=" + this.bgColor + ">";
	    output += "            <td width=14% style=\"" + weekStyle + "\">日</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">一</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">二</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">三</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">四</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">五</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">六</td>";
	    output += "          </tr>";
	    var day = 1;
	    for (var row=0; row<6; row++)
	    {
	      output += "<tr align=center>";
	      for (var col=0; col<7; col++)
	      {
	        if (row == 0 && col < firstDay)
	          output += "<td style=\"" + mdayStyle + "\">&nbsp;</td>";
	        else if (day <= lastDay)
	        {
	          output += "<td style=\"" + mdayStyle + "\" isDay=1>" + day + "</td>";
	          day++;
	        }
	        else
	          output += "<td style=\"" + mdayStyle + "\"></td>";
	      }
	      output += "</tr>";
	    }
	        //增加清除和选择今天
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"2\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName1;
	    output += "')[0].value='';\" style='cursor: hand'>清 除</span>";
	    output += "</td>";
	    output += "<td align=\"center\" colspan=\"5\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName1 + "')[0].value = '" + nowDate + "'";
	    output += "\" style='cursor: hand'>今 天 ";
	    output += nowDate;
	    output += "</span>";
	    output += "</td></tr>";
	    output += "        </table>";
	    output += "      </div>";
	    output += "    </div>";
	    output += "  </td>";
	    output += "</tr></table>";
	}
    document.write(output);
  }
}
function IcssDate (sName, sDate,buttonWords,inputExtend)
{
  /////////////////////////////////////////////////////////////////////////
  //inputValue属性的值为"today"时表示（客户机）当前日期。
  //直接在这里把默认值修改成你想要的，使用时你就什么也不用设置了。
  this.inputName = sName ;
  this.inputValue = sDate || "";
  this.inputSize = 10;
  this.inputClass = "";
  this.color = "#000080";
  this.bgColor = "#EEEEFF";
  this.buttonWidth = 20;
  this.buttonWords = buttonWords;
  this.canEdits = true;
  this.hidesSelects = true;
  this.inputExtend = inputExtend;
  /////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////
  //定义display方法。
  this.display = function ()
  {
    var reDate = /^(19[7-9]\d|20[0-5]\d)\-(0?\d|1[0-2])\-([0-2]?\d|3[01])$/;
    if (reDate.test(this.inputValue))
    {
      var dates = this.inputValue.split("-");
      var year = parseInt(dates[0], 10);
      var month = parseInt(dates[1], 10);
      var mday = parseInt(dates[2], 10);
    }
    else
    {
      var today = new Date();
      var year = today.getFullYear();
      var month = today.getMonth()+1;
      var mday = today.getDate();
    }
    var nowDate = year + "-" + month + "-" + mday;
    if (this.inputValue == "today")
      inputValue = nowDate;
    else
      inputValue = this.inputValue;
    var lastDay = new Date(year, month, 0);
    lastDay = lastDay.getDate();
    var firstDay = new Date(year, month-1, 1);
    firstDay = firstDay.getDay();
    
    var btnBorder =
      "border-left:1px solid " + this.color + ";" +
      "border-right:1px solid " + this.color + ";" +
      "border-top:1px solid " + this.color + ";" +
      "border-bottom:1px solid " + this.color + ";";
    var btnStyle =
      "padding-top:3px;cursor:hand;width:" + this.buttonWidth + "px;text-align:center;height:20px;top:-10px;" +
      "font:normal 12px 宋体;position:absolute;z-index:999;" ;
    var boardStyle = 
      "position:absolute;width:1px;height:1px;background:" + this.bgColor + ";top:10px;border:1px solid "+
      this.color + ";display:none;padding:3px;";
    var buttonEvent =
      " onmouseover=\"this.childNodes[0].style.borderLeft='1px solid " + this.color + "';" +
      		"this.childNodes[0].style.borderRight='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderTop='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderBottom='1px solid " + this.bgColor + "';" + 
      		"this.childNodes[0].style.backgroundColor='" + this.bgColor + "';" + 
          "this.childNodes[1].style.display='';this.style.zIndex=100;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='hidden';"
          : "") + "\"" +
      " onmouseout=\"this.childNodes[0].style.borderLeft='0px';" +
      "this.childNodes[0].style.borderRight='0px';" + 
      		"this.childNodes[0].style.borderTop='0px';" + 
      		"this.childNodes[0].style.borderBottom='0px';" + 
      		"this.childNodes[0].style.backgroundColor='';" + 
          "this.childNodes[1].style.display='none';this.style.zIndex=99;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "\"" +
      " onselectstart=\"return false;\"";
    var mdayStyle = "font:normal 9px Verdana,Arial,宋体;line-height:12px;cursor:default;color:" + this.color;
    var weekStyle = "font:normal 12px 宋体;line-height:15px;cursor:default;color:" + this.color;
    var arrowStyle = "font:bold 7px Verdana,宋体;cursor:hand;line-height:16px;color:" + this.color;
    var ymStyle = "font:bold 12px 宋体;line-height:16px;cursor:default;color:" + this.color;
    var changeMdays = 
      "var year=parseInt(this.parentNode.cells[2].childNodes[0].innerText);" +
      "var month=parseInt(this.parentNode.cells[2].childNodes[2].innerText);" +
      "var firstDay=new Date(year,month-1,1);firstDay=firstDay.getDay();" +
      "var lastDay=new Date(year,month,0);lastDay=lastDay.getDate();" +
      "var tab=this.parentNode.parentNode, day=1;" +
      "for(var row=2;row<8;row++)" +
      "  for(var col=0;col<7;col++){" +
      "    if(row==2&&col<firstDay){" +
      "      tab.rows[row].cells[col].innerHTML='&nbsp;';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "    else if(day<=lastDay){" +
      "      tab.rows[row].cells[col].innerHTML=day;" +
      "      tab.rows[row].cells[col].isDay=1;day++;}" +
      "    else{" +
      "      tab.rows[row].cells[col].innerHTML='';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "  }";
    var pyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)-1;" +
                  changeMdays + "\"";
    var pmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)-1;if(m.innerText=='0'){m.innerText=12;y.innerText=" +
                 "parseInt(y.innerText)-1;}" + changeMdays + "\"";
    var nmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)+1;if(m.innerText=='13'){m.innerText=1;y.innerText=" +
                 "parseInt(y.innerText)+1;}" + changeMdays + "\"";
    var nyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)+1;" +
                  changeMdays + "\"";
    var mdayEvent =
      " onmouseover=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.color + "';" +
          "event.srcElement.style.color='" + this.bgColor + "';" +
          "event.srcElement.style.cursor='hand';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onmouseout=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.bgColor + "';" +
          "event.srcElement.style.color='" + this.color + "';" +
          "event.srcElement.style.cursor='default';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onclick=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "var inp=this.parentNode.parentNode.parentNode.previousSibling.childNodes[0];" +
          "inp.value=this.rows[0].cells[2].childNodes[0].innerText+'-'+this.rows[0].cells[2].childNodes[2]." +
          "innerText+'-'+event.srcElement.innerText;" +
          "this.parentNode.style.display='none';this.parentNode.parentNode.style.zIndex=99;" +
          "this.parentNode.previousSibling.style.borderBottom='1px solid " + this.color + "';" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "}\"";
    if (this.inputExtend == null){
    	this.inputExtend = "";
    }
    if (this.buttonWords == null){
    	this.buttonWords = "选择";
    }
    var output = "";
    if(this.inputName != null){
	    output += "<table cellpadding=0 cellspacing=0 style='display:inline;'><tr>";
	    output += "  <td><input size=" + this.inputSize + " maxlength=10 value=\"" + inputValue + "\"";
	    output +=    (this.canEdits ? "" : " readonly") + (this.inputClass==null ?"":" class=\"" + this.inputClass + "\"") + " name=\"" + this.inputName + "\"";
	    output += " " + this.inputExtend;
	    output += "></td>";
	    output += "  <td id=\"" + sName + "_select\" width=" + this.buttonWidth + ">";
	    output += "    <div style=\"position:absolute;overflow:visible;width:0px;height:0px;\"" + buttonEvent + ">";
	    output += "      <div style=\"" + btnStyle + "\"><nobr>" + this.buttonWords + "</nobr></div>";
	    output += "      <div style=\"" + boardStyle + "\">";
	    output += "        <table cellspacing=1 cellpadding=1 width=175" + mdayEvent + ">";
	    output += "          <tr height=20 align=center>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"上一年\"" + pyEvent + ">&lt;&lt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=left title=\"上个月\"" + pmEvent + ">&lt;</td>";
	    output += "            <td colspan=3 style=\"" + ymStyle + "\" valign=bottom>";
	    output += "              <span>" + year + "</span><span>年</span><span>" + month + "</span><span>月</span>";
	    output += "            </td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=right title=\"下个月\"" + nmEvent + ">&gt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"下一年\"" + nyEvent + ">&gt;&gt;</td>";
	    output += "          </tr>";
	    output += "          <tr height=20 align=center bgcolor=" + this.bgColor + ">";
	    output += "            <td width=14% style=\"" + weekStyle + "\">日</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">一</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">二</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">三</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">四</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">五</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">六</td>";
	    output += "          </tr>";
	    var day = 1;
	    for (var row=0; row<6; row++)
	    {
	      output += "<tr align=center>";
	      for (var col=0; col<7; col++)
	      {
	        if (row == 0 && col < firstDay)
	          output += "<td style=\"" + mdayStyle + "\">&nbsp;</td>";
	        else if (day <= lastDay)
	        {
	          output += "<td style=\"" + mdayStyle + "\" isDay=1>" + day + "</td>";
	          day++;
	        }
	        else
	          output += "<td style=\"" + mdayStyle + "\"></td>";
	      }
	      output += "</tr>";
	    }
	        //增加清除和选择今天
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"2\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName;
	    output += "')[0].value='';\" style='cursor: hand'>清 除</span>";
	    output += "</td>";
	    output += "<td align=\"center\" colspan=\"5\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName + "')[0].value = '" + nowDate + "'";
	    output += "\" style='cursor: hand'>今 天 ";
	    output += nowDate;
	    output += "</span>";
	    output += "</td></tr>";
	    output += "        </table>";
	    output += "      </div>";
	    output += "    </div>";
	    output += "  </td>";
	    output += "</tr></table>";
	}
    document.write(output);
  }
  /////////////////////////////////////////////////////////////////////////
}
function IcssDateTimes(sName,sDate,buttonWords,inputExtend,splitChar,sName1,sDate1,inputExtend1){
  /////////////////////////////////////////////////////////////////////////
  //inputValue属性的值为"today"时表示（客户机）当前日期。
  //直接在这里把默认值修改成你想要的，使用时你就什么也不用设置了。
  this.inputName = sName ;
  this.inputName1 = sName1 ;
  this.inputValue = sDate || "";
  this.inputValue1 = sDate1 || "";
  this.inputSize = 17;
  this.inputClass = "";
  this.color = "#000080";
  this.bgColor = "#EEEEFF";
  this.buttonWidth = 20;
  this.buttonWords = buttonWords;
  this.canEdits = true;
  this.canEdits1 = true;
  this.hidesSelects = true;
  this.inputExtend = inputExtend;
  this.inputExtend1 = inputExtend1;
  this.splitChar = splitChar;
  this.inputSelectName = sName + "_select_";
  this.inputSelectName1 = sName1 + "_select_";
  /////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////
  //定义display方法。
  this.display = function ()
  {
    var reDate = /^(19[7-9]\d|20[0-5]\d)\-(0?\d|1[0-2])\-([0-2]?\d|3[01])$/;
    if (reDate.test(this.inputValue))
    {
      var dates = this.inputValue.split("-");
      var year = parseInt(dates[0], 10);
      var month = parseInt(dates[1], 10);
      var mday = parseInt(dates[2], 10);
      var mThisDateHour = "00";
      var mThisDateMinute = "00";
    }
    else
    {
      var today = new Date();
      var year = today.getFullYear();
      var month = today.getMonth()+1;
      var mday = today.getDate();
      var mThisDateHour = today.getHours();
      if (mThisDateHour<10)
      	mThisDateHour = "0" + mThisDateHour;
  	  var mThisDateMinute = today.getMinutes();
  	  if (mThisDateMinute<10)
      	mThisDateMinute = "0" + mThisDateMinute;
    }
    var nowDate = year + "-" + month + "-" + mday ;
    if (this.inputValue == "today")
      inputValue = nowDate + " " + mThisDateHour + ":" + mThisDateMinute + ":00";
    else
      inputValue = this.inputValue;
    if (this.inputValue1 == "today")
      inputValue1 = nowDate + " " + mThisDateHour + ":" + mThisDateMinute + ":00";
    else
      inputValue1 = this.inputValue1;
    var lastDay = new Date(year, month, 0);
    lastDay = lastDay.getDate();
    var firstDay = new Date(year, month-1, 1);
    firstDay = firstDay.getDay();
    
    var btnBorder =
      "border-left:1px solid " + this.color + ";" +
      "border-right:1px solid " + this.color + ";" +
      "border-top:1px solid " + this.color + ";" +
      "border-bottom:1px solid " + this.color + ";";
    var btnStyle =
      "padding-top:3px;cursor:hand;width:" + this.buttonWidth + "px;text-align:center;height:20px;top:-10px;" +
      "font:normal 12px 宋体;position:absolute;z-index:999;" ;
    var boardStyle = 
      "position:absolute;width:1px;height:1px;background:" + this.bgColor + ";top:10px;border:1px solid "+
      this.color + ";display:none;padding:3px;";
    var buttonEvent =
      " onmouseover=\"this.childNodes[0].style.borderLeft='1px solid " + this.color + "';" +
      		"this.childNodes[0].style.borderRight='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderTop='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderBottom='1px solid " + this.bgColor + "';" + 
      		"this.childNodes[0].style.backgroundColor='" + this.bgColor + "';" + 
          "this.childNodes[1].style.display='';this.style.zIndex=100;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++){if(slts[i].name != '" + this.inputSelectName + "hour' && slts[i].name != '" + this.inputSelectName1 + "hour' && slts[i].name != '" + this.inputSelectName + "minute' && slts[i].name != '" + this.inputSelectName1 + "minute')slts[i].style.visibility='hidden';}"
          : "") + "\"" +
      " onmouseout=\"this.childNodes[0].style.borderLeft='0px';" +
      "this.childNodes[0].style.borderRight='0px';" + 
      		"this.childNodes[0].style.borderTop='0px';" + 
      		"this.childNodes[0].style.borderBottom='0px';" + 
      		"this.childNodes[0].style.backgroundColor='';" + 
          "this.childNodes[1].style.display='none';this.style.zIndex=99;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "\"" +
      " onselectstart=\"return false;\"";
    var mdayStyle = "font:normal 9px Verdana,Arial,宋体;line-height:12px;cursor:default;color:" + this.color;
    var weekStyle = "font:normal 12px 宋体;line-height:15px;cursor:default;color:" + this.color;
    var arrowStyle = "font:bold 7px Verdana,宋体;cursor:hand;line-height:16px;color:" + this.color;
    var ymStyle = "font:bold 12px 宋体;line-height:16px;cursor:default;color:" + this.color;
    var changeMdays = 
      "var year=parseInt(this.parentNode.cells[2].childNodes[0].innerText);" +
      "var month=parseInt(this.parentNode.cells[2].childNodes[2].innerText);" +
      "var firstDay=new Date(year,month-1,1);firstDay=firstDay.getDay();" +
      "var lastDay=new Date(year,month,0);lastDay=lastDay.getDate();" +
      "var tab=this.parentNode.parentNode, day=1;" +
      "for(var row=2;row<8;row++)" +
      "  for(var col=0;col<7;col++){" +
      "    if(row==2&&col<firstDay){" +
      "      tab.rows[row].cells[col].innerHTML='&nbsp;';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "    else if(day<=lastDay){" +
      "      tab.rows[row].cells[col].innerHTML=day;" +
      "      tab.rows[row].cells[col].isDay=1;day++;}" +
      "    else{" +
      "      tab.rows[row].cells[col].innerHTML='';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "  }";
    var pyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)-1;" +
                  changeMdays + "\"";
    var pmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)-1;if(m.innerText=='0'){m.innerText=12;y.innerText=" +
                 "parseInt(y.innerText)-1;}" + changeMdays + "\"";
    var nmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)+1;if(m.innerText=='13'){m.innerText=1;y.innerText=" +
                 "parseInt(y.innerText)+1;}" + changeMdays + "\"";
    var nyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)+1;" +
                  changeMdays + "\"";
    var mdayEvent =
      " onmouseover=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.color + "';" +
          "event.srcElement.style.color='" + this.bgColor + "';" +
          "event.srcElement.style.cursor='hand';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onmouseout=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.bgColor + "';" +
          "event.srcElement.style.color='" + this.color + "';" +
          "event.srcElement.style.cursor='default';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onclick=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "var inp=this.parentNode.parentNode.parentNode.previousSibling.childNodes[0];" +
          "inp.value=this.rows[0].cells[2].childNodes[0].innerText+'-'+this.rows[0].cells[2].childNodes[2]." +
          "innerText+'-'+event.srcElement.innerText + ' ' + " +
          "document.getElementsByName(inp.name + '_select_hour')[0].value + ':' +" + 
          "document.getElementsByName(inp.name + '_select_minute')[0].value + ':00';" + 
          "this.parentNode.style.display='none';this.parentNode.parentNode.style.zIndex=999;" +
          "this.parentNode.previousSibling.style.borderBottom='1px solid " + this.color + "';" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "}\"";
    if (this.inputExtend == null){
    	this.inputExtend = "";
    }
    if (this.buttonWords == null){
    	this.buttonWords = "选择";
    }
    var output = "";
    if(this.inputName != null && this.inputName1 != null){
	    output += "<table cellpadding=0 cellspacing=0 style='display:inline;'><tr>";
	    output += "  <td><input size=" + this.inputSize + " maxlength=10 value=\"" + inputValue + "\"";
	    output +=    (this.canEdits ? "" : " readonly") + (this.inputClass==null ?"":" class=\"" + this.inputClass + "\"") + " name=\"" + this.inputName + "\"";
	    output += " " + this.inputExtend;
	    output += "></td>";
	    output += "  <td width=" + this.buttonWidth + ">";
	    output += "    <div style=\"position:absolute;overflow:visible;width:0px;height:0px;\"" + buttonEvent + ">";
	    output += "      <div style=\"" + btnStyle + "\"><nobr>" + this.buttonWords + "</nobr></div>";
	    output += "      <div style=\"" + boardStyle + "\">";
	    output += "        <table cellspacing=1 cellpadding=1 width=175" + mdayEvent + ">";
	    output += "          <tr height=20 align=center>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"上一年\"" + pyEvent + ">&lt;&lt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=left title=\"上个月\"" + pmEvent + ">&lt;</td>";
	    output += "            <td colspan=3 style=\"" + ymStyle + "\" valign=bottom>";
	    output += "              <span>" + year + "</span><span>年</span><span>" + month + "</span><span>月</span>";
	    output += "            </td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=right title=\"下个月\"" + nmEvent + ">&gt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"下一年\"" + nyEvent + ">&gt;&gt;</td>";
	    output += "          </tr>";
	    output += "          <tr height=20 align=center bgcolor=" + this.bgColor + ">";
	    output += "            <td width=14% style=\"" + weekStyle + "\">日</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">一</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">二</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">三</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">四</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">五</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">六</td>";
	    output += "          </tr>";
	    var day = 1;
	    for (var row=0; row<6; row++)
	    {
	      output += "<tr align=center>";
	      for (var col=0; col<7; col++)
	      {
	        if (row == 0 && col < firstDay)
	          output += "<td style=\"" + mdayStyle + "\">&nbsp;</td>";
	        else if (day <= lastDay)
	        {
	          output += "<td style=\"" + mdayStyle + "\" isDay=1>" + day + "</td>";
	          day++;
	        }
	        else
	          output += "<td style=\"" + mdayStyle + "\"></td>";
	      }
	      output += "</tr>";
	    }
	    
	    //增加清除和选择今天
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"2\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName;
	    output += "')[0].value='';\" style='cursor: hand'>清 除</span>";
	    output += "</td>";
	    output += "<td align=\"center\" colspan=\"5\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName + "')[0].value = '" + nowDate + " ' + ";
	    output += "document.getElementsByName('" + this.inputSelectName + "hour')[0].value + ':' +";
        output += "document.getElementsByName('" + this.inputSelectName + "minute')[0].value + ':00';";
	    output += "\" style='cursor: hand'>今 天 ";
	    output += nowDate;
	    output += "</span>";
	    output += "</td></tr>";
	    //时间
	    var nowThisDate = new Date();
	    var nowThisDateHour = nowThisDate.getHours();
      	var nowThisDateMinute = nowThisDate.getMinutes();
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"7\">";
		output += "<SELECT name=\"" + this.inputSelectName + "hour\" size=\"1\">";
		var iChar = "";
		for(var i=0;i<24;i++){
			if (i<10){
				iChar = "0" + i;
			}else{
				iChar = "" + i;
			}
			if (nowThisDateHour == i){
				output += "<option value='" + iChar + "' selected>" + iChar + "</option>";
			}else{
				output += "<option value='" + iChar + "'>" + iChar + "</option>";
			}
		}
		output += "</SELECT>";
		output += "点";
		output += "<SELECT name=\"" + this.inputSelectName + "minute\" size=\"1\">";
		for(var i=0;i<60;i++){
			if (i<10){
				iChar = "0" + i;
			}else{
				iChar = "" + i;
			}
			if (nowThisDateMinute == i){
				output += "<option value='" + iChar + "' selected>" + iChar + "</option>";
			}else{
				output += "<option value='" + iChar + "'>" + iChar + "</option>";
			}
		}
		output += "</SELECT>";
		output += "分";
	    output += "</td>";
	    output += "</td></tr>";
	    //
	    output += "        </table>";
	    output += "      </div>";
	    output += "    </div>";
	    output += "  </td>";
	    output += "<td valign=\"middle\">";
	    output += this.splitChar;
	    output += "</td>";
	    //第二个日历
	    if (this.inputExtend1 == null){
	    	this.inputExtend1 = "";
	    }
	    output += "  <td><input size=" + this.inputSize + " maxlength=10 value=\"" + inputValue1 + "\"";
	    output +=    (this.canEdits1 ? "" : " readonly") + (this.inputClass==null ?"":" class=\"" + this.inputClass + "\"") + " name=\"" + this.inputName1 + "\"";
	    output += " " + this.inputExtend1;
	    output += "></td>";
	    output += "  <td width=" + this.buttonWidth + ">";
	    output += "    <div style=\"position:absolute;overflow:visible;width:0px;height:0px;\"" + buttonEvent + ">";
	    output += "      <div style=\"" + btnStyle + "\"><nobr>" + this.buttonWords + "</nobr></div>";
	    output += "      <div style=\"" + boardStyle + "\">";
	    output += "        <table cellspacing=1 cellpadding=1 width=175" + mdayEvent + ">";
	    output += "          <tr height=20 align=center>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"上一年\"" + pyEvent + ">&lt;&lt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=left title=\"上个月\"" + pmEvent + ">&lt;</td>";
	    output += "            <td colspan=3 style=\"" + ymStyle + "\" valign=bottom>";
	    output += "              <span>" + year + "</span><span>年</span><span>" + month + "</span><span>月</span>";
	    output += "            </td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=right title=\"下个月\"" + nmEvent + ">&gt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"下一年\"" + nyEvent + ">&gt;&gt;</td>";
	    output += "          </tr>";
	    output += "          <tr height=20 align=center bgcolor=" + this.bgColor + ">";
	    output += "            <td width=14% style=\"" + weekStyle + "\">日</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">一</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">二</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">三</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">四</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">五</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">六</td>";
	    output += "          </tr>";
	    var day = 1;
	    for (var row=0; row<6; row++)
	    {
	      output += "<tr align=center>";
	      for (var col=0; col<7; col++)
	      {
	        if (row == 0 && col < firstDay)
	          output += "<td style=\"" + mdayStyle + "\">&nbsp;</td>";
	        else if (day <= lastDay)
	        {
	          output += "<td style=\"" + mdayStyle + "\" isDay=1>" + day + "</td>";
	          day++;
	        }
	        else
	          output += "<td style=\"" + mdayStyle + "\"></td>";
	      }
	      output += "</tr>";
	    }
	        //增加清除和选择今天
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"2\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName1;
	    output += "')[0].value='';\" style='cursor: hand'>清 除</span>";
	    output += "</td>";
	    output += "<td align=\"center\" colspan=\"5\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName1 + "')[0].value = '" + nowDate + " ' + ";
	    output += "document.getElementsByName('" + this.inputSelectName1 + "hour')[0].value + ':' +";
        output += "document.getElementsByName('" + this.inputSelectName1 + "minute')[0].value + ':00';";
	    output += "\" style='cursor: hand'>今 天 ";
	    output += nowDate;
	    output += "</span>";
	    output += "</td></tr>";
	    //时间
	    var nowThisDate = new Date();
	    var nowThisDateHour = nowThisDate.getHours();
      	var nowThisDateMinute = nowThisDate.getMinutes();
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"7\">";
		output += "<SELECT name=\"" + this.inputSelectName1 + "hour\" size=\"1\">";
		var iChar = "";
		for(var i=0;i<24;i++){
			if (i<10){
				iChar = "0" + i;
			}else{
				iChar = "" + i;
			}
			if (nowThisDateHour == i){
				output += "<option value='" + iChar + "' selected>" + iChar + "</option>";
			}else{
				output += "<option value='" + iChar + "'>" + iChar + "</option>";
			}
		}
		output += "</SELECT>";
		output += "点";
		output += "<SELECT name=\"" + this.inputSelectName1 + "minute\" size=\"1\">";
		for(var i=0;i<60;i++){
			if (i<10){
				iChar = "0" + i;
			}else{
				iChar = "" + i;
			}
			if (nowThisDateMinute == i){
				output += "<option value='" + iChar + "' selected>" + iChar + "</option>";
			}else{
				output += "<option value='" + iChar + "'>" + iChar + "</option>";
			}
		}
		output += "</SELECT>";
		output += "分";
	    output += "</td>";
	    output += "</td></tr>";
	    output += "        </table>";
	    output += "      </div>";
	    output += "    </div>";
	    output += "  </td>";
	    output += "</tr></table>";
	}
    document.write(output);
  }
}

function IcssDateTime (sName, sDate,buttonWords,inputExtend)
{
  /////////////////////////////////////////////////////////////////////////
  //inputValue属性的值为"today"时表示（客户机）当前日期。
  //直接在这里把默认值修改成你想要的，使用时你就什么也不用设置了。
  this.inputName = sName;
  this.inputValue = sDate || "";
  this.inputSize = 17;
  this.inputClass = "";
  this.color = "#000080";
  this.bgColor = "#EEEEFF";
  this.buttonWidth = 20;
  this.buttonWords = buttonWords;
  this.canEdits = true;
  this.hidesSelects = true;
  this.inputExtend = inputExtend;
  this.inputSelectName = sName + "_select_";
  /////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////
  //定义display方法。
  this.display = function ()
  {
    var reDate = /^(19[7-9]\d|20[0-5]\d)\-(0?\d|1[0-2])\-([0-2]?\d|3[01])$/;
    if (reDate.test(this.inputValue))
    {
      var dates = this.inputValue.split("-");
      var year = parseInt(dates[0], 10);
      var month = parseInt(dates[1], 10);
      var mday = parseInt(dates[2], 10);
      var mThisDateHour = "00";
      var mThisDateMinute = "00";
    }
    else
    {
      var today = new Date();
      var year = today.getFullYear();
      var month = today.getMonth()+1;
      var mday = today.getDate();
      var mThisDateHour = today.getHours();
      if (mThisDateHour<10)
      	mThisDateHour = "0" + mThisDateHour;
  	  var mThisDateMinute = today.getMinutes();
  	  if (mThisDateMinute<10)
      	mThisDateMinute = "0" + mThisDateMinute;
    }
    var nowDate = year + "-" + month + "-" + mday;
    if (this.inputValue == "today")
      inputValue = nowDate + " " + mThisDateHour + ":" + mThisDateMinute + ":00";
    else
      inputValue = this.inputValue;
    var lastDay = new Date(year, month, 0);
    lastDay = lastDay.getDate();
    var firstDay = new Date(year, month-1, 1);
    firstDay = firstDay.getDay();
    
    var btnBorder =
      "border-left:1px solid " + this.color + ";" +
      "border-right:1px solid " + this.color + ";" +
      "border-top:1px solid " + this.color + ";" +
      "border-bottom:1px solid " + this.color + ";";
    var btnStyle =
      "padding-top:3px;cursor:hand;width:" + this.buttonWidth + "px;text-align:center;height:20px;top:-10px;" +
      "font:normal 12px 宋体;position:absolute;z-index:999;" ;
    var boardStyle = 
      "position:absolute;width:1px;height:1px;background:" + this.bgColor + ";top:10px;border:1px solid "+
      this.color + ";display:none;padding:3px;";
    var buttonEvent =
      " onmouseover=\"this.childNodes[0].style.borderLeft='1px solid " + this.color + "';" +
      		"this.childNodes[0].style.borderRight='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderTop='1px solid " + this.color + "';" + 
      		"this.childNodes[0].style.borderBottom='1px solid " + this.bgColor + "';" + 
      		"this.childNodes[0].style.backgroundColor='" + this.bgColor + "';" + 
          "this.childNodes[1].style.display='';this.style.zIndex=100;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)if(slts[i].name != '" + this.inputSelectName + "hour' && slts[i].name != '" + this.inputSelectName + "minute')slts[i].style.visibility='hidden';"
          : "") + "\"" +
      " onmouseout=\"this.childNodes[0].style.borderLeft='0px';" +
      "this.childNodes[0].style.borderRight='0px';" + 
      		"this.childNodes[0].style.borderTop='0px';" + 
      		"this.childNodes[0].style.borderBottom='0px';" + 
      		"this.childNodes[0].style.backgroundColor='';" + 
          "this.childNodes[1].style.display='none';this.style.zIndex=99;" +
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "\"" +
      " onselectstart=\"return false;\"";
    var mdayStyle = "font:normal 9px Verdana,Arial,宋体;line-height:12px;cursor:default;color:" + this.color;
    var weekStyle = "font:normal 12px 宋体;line-height:15px;cursor:default;color:" + this.color;
    var arrowStyle = "font:bold 7px Verdana,宋体;cursor:hand;line-height:16px;color:" + this.color;
    var ymStyle = "font:bold 12px 宋体;line-height:16px;cursor:default;color:" + this.color;
    var changeMdays = 
      "var year=parseInt(this.parentNode.cells[2].childNodes[0].innerText);" +
      "var month=parseInt(this.parentNode.cells[2].childNodes[2].innerText);" +
      "var firstDay=new Date(year,month-1,1);firstDay=firstDay.getDay();" +
      "var lastDay=new Date(year,month,0);lastDay=lastDay.getDate();" +
      "var tab=this.parentNode.parentNode, day=1;" +
      "for(var row=2;row<8;row++)" +
      "  for(var col=0;col<7;col++){" +
      "    if(row==2&&col<firstDay){" +
      "      tab.rows[row].cells[col].innerHTML='&nbsp;';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "    else if(day<=lastDay){" +
      "      tab.rows[row].cells[col].innerHTML=day;" +
      "      tab.rows[row].cells[col].isDay=1;day++;}" +
      "    else{" +
      "      tab.rows[row].cells[col].innerHTML='';" +
      "      tab.rows[row].cells[col].isDay=0;}" +
      "  }";
    var pyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)-1;" +
                  changeMdays + "\"";
    var pmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)-1;if(m.innerText=='0'){m.innerText=12;y.innerText=" +
                 "parseInt(y.innerText)-1;}" + changeMdays + "\"";
    var nmEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];m=this.parentNode.cells[2].childNodes[2];" +
                 "m.innerText=parseInt(m.innerText)+1;if(m.innerText=='13'){m.innerText=1;y.innerText=" +
                 "parseInt(y.innerText)+1;}" + changeMdays + "\"";
    var nyEvent =
      " onclick=\"var y=this.parentNode.cells[2].childNodes[0];y.innerText=parseInt(y.innerText)+1;" +
                  changeMdays + "\"";
    var mdayEvent =
      " onmouseover=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.color + "';" +
          "event.srcElement.style.color='" + this.bgColor + "';" +
          "event.srcElement.style.cursor='hand';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onmouseout=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "event.srcElement.style.backgroundColor='" + this.bgColor + "';" +
          "event.srcElement.style.color='" + this.color + "';" +
          "event.srcElement.style.cursor='default';" +
          "var ym=event.srcElement.parentNode.parentNode.rows[0].cells[2].childNodes;" +
          "event.srcElement.title=ym[0].innerText+'-'+ym[2].innerText+'-'+event.srcElement.innerText;}\"" +
      " onclick=\"if(event.srcElement.tagName=='TD'&&event.srcElement.isDay){" +
          "var inp=this.parentNode.parentNode.parentNode.previousSibling.childNodes[0];" +
          "inp.value=this.rows[0].cells[2].childNodes[0].innerText+'-'+this.rows[0].cells[2].childNodes[2]." +
          "innerText+'-'+event.srcElement.innerText + ' ' +" +
          "document.getElementsByName(inp.name + '_select_hour')[0].value + ':' +" + 
          "document.getElementsByName(inp.name + '_select_minute')[0].value + ':00';" + 
          "this.parentNode.style.display='none';this.parentNode.parentNode.style.zIndex=99;" +
          "this.parentNode.previousSibling.style.borderBottom='1px solid " + this.color + "';" +
          
          (this.hidesSelects ?
          "var slts=document.getElementsByTagName('SELECT');" +
          "for(var i=0;i<slts.length;i++)slts[i].style.visibility='';"
          : "") + "}\"";
    if (this.inputExtend == null){
    	this.inputExtend = "";
    }
    if (this.buttonWords == null){
    	this.buttonWords = "选择";
    }
    var output = "";
    if(this.inputName != null){
	    output += "<table cellpadding=0 cellspacing=0 style='display:inline;'><tr>";
	    output += "  <td><input size=" + this.inputSize + " maxlength=10 value=\"" + inputValue + "\"";
	    output +=    (this.canEdits ? "" : " readonly") + (this.inputClass==null ?"":" class=\"" + this.inputClass + "\"") + " name=\"" + this.inputName + "\"";
	    output += " " + this.inputExtend;
	    output += "></td>";
	    output += "  <td width=" + this.buttonWidth + ">";
	    output += "    <div style=\"position:absolute;overflow:visible;width:0px;height:0px;\"" + buttonEvent + ">";
	    output += "      <div style=\"" + btnStyle + "\"><nobr>" + this.buttonWords + "</nobr></div>";
	    output += "      <div style=\"" + boardStyle + "\">";
	    output += "        <table cellspacing=1 cellpadding=1 width=175" + mdayEvent + ">";
	    output += "          <tr height=20 align=center>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"上一年\"" + pyEvent + ">&lt;&lt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=left title=\"上个月\"" + pmEvent + ">&lt;</td>";
	    output += "            <td colspan=3 style=\"" + ymStyle + "\" valign=bottom>";
	    output += "              <span>" + year + "</span><span>年</span><span>" + month + "</span><span>月</span>";
	    output += "            </td>";
	    output += "            <td style=\"" + arrowStyle + "\" align=right title=\"下个月\"" + nmEvent + ">&gt;</td>";
	    output += "            <td style=\"" + arrowStyle + "\" title=\"下一年\"" + nyEvent + ">&gt;&gt;</td>";
	    output += "          </tr>";
	    output += "          <tr height=20 align=center bgcolor=" + this.bgColor + ">";
	    output += "            <td width=14% style=\"" + weekStyle + "\">日</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">一</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">二</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">三</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">四</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">五</td>";
	    output += "            <td width=14% style=\"" + weekStyle + "\">六</td>";
	    output += "          </tr>";
	    var day = 1;
	    for (var row=0; row<6; row++)
	    {
	      output += "<tr align=center>";
	      for (var col=0; col<7; col++)
	      {
	        if (row == 0 && col < firstDay)
	          output += "<td style=\"" + mdayStyle + "\">&nbsp;</td>";
	        else if (day <= lastDay)
	        {
	          output += "<td style=\"" + mdayStyle + "\" isDay=1>" + day + "</td>";
	          day++;
	        }
	        else
	          output += "<td style=\"" + mdayStyle + "\"></td>";
	      }
	      output += "</tr>";
	    }
	        //增加清除和选择今天
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"2\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName;
	    output += "')[0].value='';\" style='cursor: hand'>清 除</span>";
	    output += "</td>";
	    output += "<td align=\"center\" colspan=\"5\">";
	    output += "<span onclick=\"document.getElementsByName('";
	    output += this.inputName + "')[0].value = '" + nowDate + " ' + ";
	    output += "document.getElementsByName('" + this.inputSelectName + "hour')[0].value + ':' +";
        output += "document.getElementsByName('" + this.inputSelectName + "minute')[0].value + ':00';";
	    output += "\" style='cursor: hand'>今 天 ";
	    output += nowDate;
	    output += "</span>";
	    output += "</td></tr>";
	    //时间
	    var nowThisDate = new Date();
	    var nowThisDateHour = nowThisDate.getHours();
      	var nowThisDateMinute = nowThisDate.getMinutes();
	    output += "<tr>";
	    output += "<td align=\"center\" colspan=\"7\">";
		output += "<SELECT name=\"" + this.inputSelectName + "hour\" size=\"1\">";
		var iChar = "";
		for(var i=0;i<24;i++){
			if (i<10){
				iChar = "0" + i;
			}else{
				iChar = "" + i;
			}
			if (nowThisDateHour == i){
				output += "<option value='" + iChar + "' selected>" + iChar + "</option>";
			}else{
				output += "<option value='" + iChar + "'>" + iChar + "</option>";
			}
		}
		output += "</SELECT>";
		output += "点";
		output += "<SELECT name=\"" + this.inputSelectName + "minute\" size=\"1\">";
		for(var i=0;i<60;i++){
			if (i<10){
				iChar = "0" + i;
			}else{
				iChar = "" + i;
			}
			if (nowThisDateMinute == i){
				output += "<option value='" + iChar + "' selected>" + iChar + "</option>";
			}else{
				output += "<option value='" + iChar + "'>" + iChar + "</option>";
			}
		}
		output += "</SELECT>";
		output += "分";
	    output += "</td>";
	    output += "</td></tr>";
	    //--------------------
	    output += "        </table>";
	    output += "      </div>";
	    output += "    </div>";
	    output += "  </td>";
	    output += "</tr></table>";
	}
    document.write(output);
  }
  /////////////////////////////////////////////////////////////////////////
}
/**
 * 得到IMG节点的HTML编码
 * @param url 图像地址
 * @param border border的属性
 * @param alt 提示信息
 * @param style 样式
 */
function getImg(url,border,alt,style){
	if (url == null){
		return "";
	}
	var output = "<img src=\"" + url + "\"";
	if (border != null){
		output +=" border=\"" + border + "\"";
	}
	if (alt != null){
		output +=" alt=\"" + alt + "\"";
	}
	if (style != null){
		output +=" style=\"" + style + "\"";
	}
	output +=">";
	return output; 
}
var tmpColor = "";
var tempFileColor = "";
function _mouseOver(obj,newColor){
	tmpColor = obj.bgColor;
	if (newColor == null){
		obj.bgColor = "#DFE7DF";
	}else{
		obj.bgColor = newColor;
	}
}
function _mouseOut(obj,newColor){
	if (newColor == null){
		obj.bgColor = tmpColor;
	}else{
		obj.bgColor = newColor;
	}
}

function _mouseOverFile(obj,newColor){
	tempFileColor = obj.style.backgroundColor;
	if (newColor == null){
		obj.style.backgroundColor = "#DFE7DF";
	}else{
		obj.style.backgroundColor = newColor;
	}
}
function _mouseOutFile(obj,newColor){
	if (newColor == null){
		obj.style.backgroundColor = tempFileColor;
	}else{
		obj.style.backgroundColor = newColor;
	}
}

function openWin(url){
	window.showModalDialog(url,"","dialogWidth:800px; dialogHeight:600px; help:no; status:0");
}
/*
alwaysLowered | yes/no | 指定窗口隐藏在所有窗口之后 
alwaysRaised | yes/no | 指定窗口悬浮在所有窗口之上 
depended | yes/no | 是否和父窗口同时关闭 
directories | yes/no | Nav2和3的目录栏是否可见 
height | pixel value | 窗口高度 
hotkeys | yes/no | 在没菜单栏的窗口中设安全退出热键 
innerHeight | pixel value | 窗口中文档的像素高度 
innerWidth | pixel value | 窗口中文档的像素宽度 
location | yes/no | 位置栏是否可见 
menubar | yes/no | 菜单栏是否可见 
outerHeight | pixel value | 设定窗口(包括装饰边框)的像素高度 
outerWidth | pixel value | 设定窗口(包括装饰边框)的像素宽度 
resizable | yes/no | 窗口大小是否可调整 
screenX | pixel value | 窗口距屏幕左边界的像素长度 
screenY | pixel value | 窗口距屏幕上边界的像素长度 
scrollbars | yes/no | 窗口是否可有滚动栏 
titlebar | yes/no | 窗口题目栏是否可见 
toolbar | yes/no | 窗口工具栏是否可见 
Width | pixel value | 窗口的像素宽度 
z-look | yes/no | 窗口被激活后是否浮在其它窗口之上
*/
function openwindow(url,name,iWidth,iHeight,iLeft,iTop){
  var url;                                 //转向网页的地址;
  var name;                           //网页名称，可为空;
  var iWidth;                          //弹出窗口的宽度;
  var iHeight;                     //弹出窗口的高度;
  if (iTop==null)
  iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
  if (iLeft==null)
  iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
  window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
 }
/**
 * 给select增加年的option
 * @param select select对象
 * @param i 增加之前几年
 * @param o 增加之后几年
 * @param selYear 选择的年，selYear为null时，为当前年
 * @param isDefault 是否有默认数据，例如：请选择
 * @param defaultValue 默认的数据值 例如：""
 * @param defaultText 默认的显示数据 例如：请选择
 */
function getYearOption(select,i,o,selYear,isDefault,defaultValue,defaultText){
	if (isDefault==null){
		isDefault=true;
	}
	if (defaultValue==null){
		defaultValue = "0";
	}
	if (defaultText == null){
		defaultText = "全部";
	}
	i = (i==null)?0:i;
	o = (o==null)?0:o;
	var oDate = new Date();
	var oYear = oDate.getFullYear();
	if (selYear == null){
		selYear = oYear;
	}
	var isSelect = "";
	if (isDefault){
		var obj = document.createElement("OPTION");
		obj.text= defaultText;
		obj.value=defaultValue;
		if (selYear==new Number(defaultValue)){
			isSelect = selYear;
		}
		select.add(obj);
	}
	
	for(var m=oYear-i;m<=(oYear+o);m++){
		var obj1 = document.createElement("OPTION");
		obj1.text= m;
		obj1.value=m;
		if (m==selYear){
			isSelect = selYear;
		}
		select.add(obj1);
	}

	if (isSelect!="")
	select.value = isSelect;
}
function getYearOptionNoAll(select,i,o,selYear){
	i = (i==null)?0:i;
	o = (o==null)?0:o;
	var oDate = new Date();
	var oYear = oDate.getFullYear();
	if (selYear == null){
		selYear = oYear;
	}
	var isSelect = "";
	for(var m=oYear-i;m<=(oYear+o);m++){
		var obj1 = document.createElement("OPTION");
		obj1.text= m;
		obj1.value=m;
		if (m==selYear){
			isSelect = selYear;
		}
		select.add(obj1);
	}
	if (isSelect!="")
	select.value = isSelect;
}
/**
 * 给select增加季度的option
 * @param select select对象
 * @param selQuarter 选择的季度
 */
function getQuarterOption(select,selQuarter){
	var oDate = new Date();
	var quarter = ((oDate.getMonth() + 1)/4) + 1;
	if (selQuarter == null){
		selQuarter = quarter;
	}
	var isSelect = "";
	var obj = document.createElement("OPTION");
	obj.text= "全部";
	obj.value="0";
	if (selQuarter==0){
		isSelect = selQuarter;
	}
	select.add(obj);
	for(var m=1;m<=4;m++){
		var obj1 = document.createElement("OPTION");
		obj1.text= "第" + m + "季度";
		obj1.value=m;
		if (m==selQuarter){
			isSelect = selQuarter;
		}
		select.add(obj1);
	}
	if (isSelect!="")
	select.value = isSelect;
}
function getQuarterOptionNoAll(select,selQuarter){
	var oDate = new Date();
	var quarter = ((oDate.getMonth() + 1)/4) + 1;
	if (selQuarter == null){
		selQuarter = quarter;
	}
	var isSelect = "";
	var obj = document.createElement("OPTION");
	for(var m=1;m<=4;m++){
		var obj1 = document.createElement("OPTION");
		obj1.text= "第" + m + "季度";
		obj1.value=m;
		if (m==selQuarter){
			isSelect = selQuarter;
		}
		select.add(obj1);
	}
	if (isSelect!="")
	select.value = isSelect;
}
/**
 * 全选/不选
 * @param obj 选择的checkbox框
 * @param name 单选和复选框的名称（name）
 */
function changeSelect(obj,name) {
	var checkValue = false;
	if (obj.checked){
		checkValue = true;
	}
	var selElements = document.getElementsByName(name);
	for (var i = 0; i < selElements.length; i++) {
		if (selElements[i].tagName.toLowerCase() == "input") {
			if (selElements[i].type.toLowerCase() == "checkbox" || selElements[i].type.toLowerCase() == "radio") {
				if (selElements[i].disabled || selElements[i].readOnly){
					selElements[i].checked =false;
				}else{
					selElements[i].checked = checkValue;
				}
			}
		}
	}
	return false;
}
/**
 * 得到IE的版本
 */
 function getIEVersonNumber(){
    var ua = navigator.userAgent;
    var msieOffset = ua.indexOf("MSIE ");
    if(msieOffset < 0)
    {
        return 0;
    }
    return parseFloat(ua.substring(msieOffset + 5, ua.indexOf(";", msieOffset)));
}
var ieVer = getIEVersonNumber();
/**
 * 处理弹出窗口的宽度
 * @param n 原IE6宽度
 * @return 返回当前浏览器版本所需要的宽度
 */
function ieX(n){
	if (ieVer>=7){
		n = n-10
	}
	if (n<0){
		n=0;
	}
	return n;
}
/**
 * 处理弹出窗口的高度
 * @param n 原IE6高度
 * @return 返回当前浏览器版本所需要的高度
 */
function ieY(n){
	if (ieVer>=7){
		n = n-10
	}
	if (n<0){
		n=0;
	}
	return n;
}

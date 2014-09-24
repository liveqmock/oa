
/*
List页面的JS对象集
*/
var context = new Array();

//以下是排序的方法
//var orderImg = "<img src=\'/gddemo/resource/images/order.gif\'>";
var orderImg = "";
var orderupImg = "";
var orderdownImg = "";
var datas = new Array();
var orderData = new Array();
var o;
var titleStr = "";

window.onload = function() {
	document.body.scroll = "no";
	try{
		if (path == null || path.length<1){
			alert("图片路径没有设置，可能出现的原因：\n1、没有在页面上增加path变量(var path = \"<%=path%>\";)；\n2、没有在Servlet中使用setStyle(HttpServletRequest request)方法设置ptah。");
		}
		orderImg = "<img src=\'" + path + "/images/order.gif\'>";
		orderupImg = "<img src=\'" + path + "/images/orderup.gif\'>";
		orderdownImg = "<img src=\'" + path + "/images/orderdown.gif\'>";
		var buttons = document.getElementsByTagName("button");
		var buts = new Array();
		var shows = new Array();
		for(var i=0;i<buttons.length;i++){
		
		//得到以f开头的所有的id，并验证是否有对应显示的内容
			var b_id = buttons[i].id;
			if (b_id != null && b_id.indexOf("f_")==0){
				var showId = b_id.substring(2,b_id.length);
				var show = document.getElementById("s_" + showId);
				if (show != null){
					buts[buts.length] = buttons[i];
					shows[shows.length] = show;
				}
			}
		}
		context["fuzhu"]=buts;
		context["fuzhuValue"]=shows;
	}catch(e){
	
	}
	var msg = document.getElementById("msg");
	if (msg != null)
		context["msg"] = msg;
	var dataTable = document.getElementById("dataTable");
	if (dataTable != null)
		context["data"]=dataTable;
	var pageState = document.getElementById("pageState");
	if (pageState != null)
		context["pageState"]=pageState;
	var state = document.getElementById("state");
	if (state != null)
		context["state"]=state;
	toOrder();
	
	try{
		_load();
	}catch(e){
		outputMsgs("[开发]请在页面添加_load()方法或者_load()中有错误：\n function _load(){\n	//消息加载区\n    setState(\"这是要加载的消息\");\n//onload区\n }\n错误信息：" + e);
	}
};


/**
 * 设置消息
 * @param message 消息
 */
function setState(message){
	var showMsg = "<marquee scrollamount=\"20\" scrolldelay=\"500\" behavior=\"alternate\">" + message + "</marquee>";
	//context["state"].className = "";
	if (message != null && message != ""){
		context["state"].style.display = "";
		context["state"].innerHTML = showMsg;
	}
}

//将数据整理为排序数据
function setOrder(){
	//把得到排序组合数据
	if (datas.length>0){
		for (var k=0;k<datas[0].length;k++){
			var fStr = "var data_" + k + "=new Array();";
			eval(fStr);
			for(var i=0;i<datas.length;i++){
				var data = datas[i];
				for (var j=0;j<data.length;j++){
					var fStrj = "data_" + k + "[" + i + "]=\"" + data[k].replaceByString("\"","“") + "\";";
					eval(fStrj);
				}
			}
			var fStrk = "orderData["+ k + "] = data_" + k + ";"
			eval(fStrk);
		}
	}
}

/**
 * 对指定的对象进行排序
 * @param obj 要排序的标题对象
 * @param ii 要排序的数据列
 */
function orderMe(objName,ii){
	showOrderMsg(true);
	var obj = document.getElementById(objName);
	var t = obj.getAttribute("order");
	loadOrder(obj);
	if (t=="1"){
		initOrder(obj,"升序",orderdownImg,"0",ii);
		//降序的计算
		toOrderData(ii,1);
	}else if (t=="0"){
		initOrder(obj,"降序",orderupImg,"1",ii);
		//升序的计算
		toOrderData(ii,0);
	}else{
		initOrder(obj,"升序",orderupImg,"0",ii);
		//升序的计算
		toOrderData(ii,1);
	}
	setHtml();
}
	//进行数据整理
function toOrderData(ii,tt){
	o=new Array();
	if (orderData[ii]==null || orderData[ii].length==0){
		return;
	}
	//亚洲语言排序
	orderData[ii].sort(function(a,b){return a.localeCompare(b)});
	if (tt==0){
		//升序
		for (var i=0;i<orderData[ii].length;i++){
			var value = orderData[ii][i];
			if (i==0 || value != orderData[ii][i-1]){
				for (var j=0;j<datas.length;j++){
					if (datas[j][ii]==value){
						o[o.length] = datas[j];
					}
				}
			}
		}
	}else{
		//降序
		for (var i=orderData[ii].length-1;i>=0;i--){
			var value = orderData[ii][i];
			if (i==0 || value != orderData[ii][i-1]){
				for (var j=0;j<datas.length;j++){
					if (datas[j][ii]==value){
						o[o.length] = datas[j];
					}
				}
			}
		}
	}
}
//页面加载排序标题
function loadOrder(obj){
	for(var i=0;true;i++){
		var orderText = document.getElementById("order"+i);
		if (orderText==null){
			break;
		}else{
			if (obj == null){
				if (orderText.getAttribute("order")==null){
					initOrder(orderText,"升序",orderImg,"1",i);
				}
			}else if (obj != orderText ){
				initOrder(orderText,"升序",orderImg,"1",i);
			}
		}
	}
}
//初始化排序标题信息
function initOrder(orderText,paixu,_orderImg,t,ii){
	var txt = orderText.outerText;
	orderText.innerHTML = txt + _orderImg;
	orderText.style.cursor="hand";
	orderText.onclick=function(){
		orderMe(this.id,ii);
	}
	orderText.title="点击按照[" + txt + "]进行" + paixu + "排序";
	orderText.setAttribute("order",t);
}

//将数据放到页面展现
function setHtml(){
	var rowLength=tableTitle.cells.length-1;
	var oTable = document.getElementById("dataTable");
	var rows = oTable.rows.length;
	if (rows>1){
		for (var i=1;i<rows;i++){
			oTable.deleteRow(1);
		}
	}
	for (var i=0;i<o.length;i++){
		//var bgColor = "#FDFDF0";
		var bgColor = "#FFFFFF";
		if (i%2==1){
			//bgColor = "#ECECFF";
			bgColor = "#FFFFFF";
		}
		var oRow=oTable.insertRow(oTable.rows.length);
		oRow.bgColor=bgColor;
		oRow.onmouseover=function(){
			this.bgColor = "#EDF5F8";
		};
		oRow.onmouseout=function(){
			this.bgColor = "#FFFFFF";
		};
		var aRows=oTable.rows;
		var aCells=oRow.cells;
		try{
			setRows(aRows,oRow,aCells,i);
		}catch(e){
			outputMsgs("[开发]请在页面添加setRows(aRows,oRow,aCells,i)方法, 设置页面的数据，否则页面将没有数据！<br>错误信息：" + e);
			return;
		}
	}
	showOrderMsg(false);
/*	
		if (o.length<15){
			for(var i=0;i<15-o.length;i++){
				var bgColor = "#FDFDF0";
				if ((o.length+i)%2==1){
					bgColor = "#ECECFF";
				}
				var oRow=oTable.insertRow(oTable.rows.length);
				oRow.bgColor=bgColor;
				var aRows=oTable.rows;
				var aCells=oRow.cells;
				addCell(aRows,oRow,aCells,"celltxt","center",o.length+i+1);
				for(var j=0;j<rowLength;j++){
					addCell(aRows,oRow,aCells,"celltxt","center","&nbsp;");
				}
			}
		}
		*/
}
/**
 * 增加页面显示数据
 * @param aRows
 * @param oRow
 * @param aCells 
 * @param _className 样式的名称
 * @param _align 对齐方式 "left"、"center"、"right"
 * @param nowarp true表示不换行，false或者没有该条件 表示可以换行
 */
function addCell(aRows,oRow,aCells,_className,_align,value,nowarp){
	var oCell=aRows(oRow.rowIndex).insertCell(aCells.length);
	oCell.className=_className;
	oCell.align=_align;
	if (nowarp!=null && nowarp){
		oCell.noWrap=true;
	}
	oCell.innerHTML = value;
}
function toOrder(){
	showOrderMsg(true);
	loadOrder();
	loadData();
	setOrder();
	defOrder();
	try{
	
	}catch(e){
		outputMsgs("[开发]请在页面添加defOrder()方法：<br> function defOrder(){<br>	//设置默认排序<br>    orderMe(\"order0\",0);<br> }");
	}
	showOrderMsg(false);
}

function loadData(){

	for(var i=0;true;i++){
		var d = new Array();
		if (document.all("icss[" + i + "][0]") == null){
			break;
		}
		for(var j=0;true;j++){
			var data = document.all("icss[" + i + "][" + j + "]");
			if (data!=null){
				d[j]=data.innerText;
			}else{
				break;
			}
		}
		datas[i] = d;
	}
}
function showOrderMsg(open){
	var mm = document.getElementById("orderMsg");
	if(open){
		if (mm == null){
			mm = createOrderMsg();
		}
		mm.style.display = "block";
	}else{
		mm.style.display = "none";
	}
}	
function createOrderMsg(){
	var str="";
	str +="<table border=\"1\" width=\"100%\" bordercolorlight=\"#3089CA\" bordercolordark=\"#3089CA\" style=\"border-collapse: collapse\" bgcolor=\"#E4EDF4\" height=\"100%\">";
	str +="<tr><td align=\"center\"><font size=\"2\" color=\"#000066\">正在排序，请稍候...</font></td></tr></table>";
	var div = document.createElement("div");
	div.setAttribute("id","orderMsg");
	div.className = "divwait";
	div.innerHTML = str;
	div.style.display="none";
	document.body.appendChild(div);
	return div;
}
	
function checkSearch(obj,foc){
	var imgPlus = document.getElementById("imgPlus");
	var isopen = false;
	for(var i=0;i<context["fuzhu"].length;i++){
		if (context["fuzhu"][i] == obj){
			if (context["fuzhuValue"][i].style.display == "none"){
				isopen = false;
			}else{
				isopen = true;
			}
		}
	}
	hiddenAll();
	if (!isopen){
		hiddenAllbut();
		obj.className="butsel";
		for(var i=0;i<context["fuzhu"].length;i++){
			if (obj == context["fuzhu"][i]){
				context["fuzhuValue"][i].style.display="";
			}
		}
		var f = document.getElementsByName(foc);
		if (f != null && f[0] != null)
			f[0].focus();
		imgPlus.src = path + "/images/whiteminus.gif";
	}else{
		hiddenAllbut();
		imgPlus.src = path + "/images/whiteplus.gif";
	}
}
	
	
function showSearch(obj){
	
	var isShow = false;
	for(var i=0;i<context["fuzhuValue"].length;i++){
		if (context["fuzhuValue"][i].style.display != "none"){
			isShow = true;
		}
	}
	if (isShow){
		hiddenAll();
		obj.src=path + "/images/whiteplus.gif";
	}else{
		var isClick = false;
		for(var i=0;i<context["fuzhu"].length;i++){
		
			if (context["fuzhu"][i].className =="butsel"){
				context["fuzhu"][i].click();
				isClick = true;
				break;
			}
		}
		if (!isClick){
			context["fuzhu"][0].click();
		}
		obj.src=path + "/images/whiteminus.gif";
	}
}
function hiddenAll(){
	for(var i=0;i<context["fuzhuValue"].length;i++){
		if (context["fuzhuValue"][i].style.display != "none"){
			context["fuzhuValue"][i].style.display = "none";
		}
	}
}
function hiddenAllbut(){
	for(var i=0;i<context["fuzhu"].length;i++){
		if (context["fuzhu"][i].className != "but"){
			context["fuzhu"][i].className = "but";
		}
	}
}
function _goto(page,pageNum){
	if (page>pageNum){
		alert("页码超出最大页数！");
		return;
	}
	if (page<1){
		alert("页面不能小于1！");
	}
	document.all("page").value = page;
	document.forms[0].submit();
}

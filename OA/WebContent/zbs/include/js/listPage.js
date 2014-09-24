
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
var _number = -1;

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
		var search_Object = null;
		for(var i=0;i<buttons.length;i++){
			//检索
			if (buttons[i].className=="butt" && buttons[i].innerText.trim()=="检索"){
				search_Object = buttons[i];
				break;
			}
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
		//所有的input增加回车搜索
		var inputs = document.getElementsByTagName("INPUT");
		for(var i=0;i<inputs.length;i++){
			if (inputs[i].type=="text")
				inputs[i].onkeydown = function(){
					if(event.keyCode==13){
						if (search_Object!=null){
							search_Object.onclick();
						}
					}
				};
		}
		context["fuzhu"]=buts;
		context["fuzhuValue"]=shows;
	}catch(e){
	//	_alert("页面初始化附加功能出错，但不影响基本功能使用，请联系系统维护人员！\n错误信息：" + e);
	}
	loadOrder();
	_load();
};


/**
 * 设置消息
 * @param message 消息
 */
function setState(message){
	var showMsg = "<marquee scrollamount=\"20\" scrolldelay=\"500\" behavior=\"alternate\">" + message + "</marquee>";
	var state = document.getElementById("state");
	//context["state"].className = "";
	if (message != null && message != ""){
		state.style.display = "";
		state.innerHTML = showMsg;
	}
}


/**
 * 对指定的对象进行排序
 * @param obj 要排序的标题对象
 */
function orderMe(obj){
	var t = obj.getAttribute("order");
	if (t=="1"){
		//降序的计算
		orderDataShow(obj,1);
	}else if (t=="0"){
		//升序的计算
		orderDataShow(obj,0);
	}else{
		//升序的计算
		orderDataShow(obj,1);
	}
}
/**
 * 对指定的对象进行排序
 * @param obj 要排序的标题对象
 * @param t 1升序 0 降序
 */
function orderDataShow(obj,t){
	var orderTxt = "ASC";
	if (t==1){
		orderTxt = "DESC";
	}
	var order_top = obj.getAttribute("orderby");
	orderTxt = order_top + " " + orderTxt;
	document.getElementsByName("orderby")[0].value = orderTxt;
	var nowP = document.all("page");
	if (nowP != null){
		nowP.value="1";
	}
	document.forms[0].submit();
}
//页面加载排序标题
function loadOrder(jsValue){
	try{
		var orderTxt = document.getElementsByName("orderby")[0].value.toUpperCase().trim();
	}catch(e){
		return;
	}
	for(var i=0;true;i++){
		var orderText = document.getElementById("order"+i);
		if (orderText==null){
			break;
		}else{
			var oby = orderText.getAttribute("orderby");
			if (oby==null){
				var ooo = orderText.getAttribute("order");
				if (jsValue ==("order"+i)){
					if ("1"==ooo){
						initOrderJs(orderText,"降序",orderupImg,"1")
					}else{
						initOrderJs(orderText,"升序",orderdownImg,"0");
					}
				}else{
					if ("1"==ooo){
						initOrderJs(orderText,"降序",orderImg,"1")
					}else{
						initOrderJs(orderText,"升序",orderImg,"0");
					}
					//initOrderJs(orderText,"升序",orderImg,"0");
				}
				continue;
			}
			oby = oby.toUpperCase();
			if (orderTxt.indexOf(oby)==0 && orderTxt.length<=oby.length+5 && jsValue == null){
				if(orderTxt.lastIndexOf(" ASC") + 4 == orderTxt.length){
					initOrder(orderText,"降序",orderupImg,"1");
				}else if (orderTxt.lastIndexOf(" DESC")+ 5 == orderTxt.length){
					initOrder(orderText,"升序",orderdownImg,"0");
				}else{
					initOrder(orderText,"降序",orderupImg,"1");
				}
			}else{
				initOrder(orderText,"升序",orderImg,"0");
			}
		}
	}
}
//初始化排序标题信息
function initOrder(orderText,paixu,_orderImg,t){
	var txt = orderText.outerText;
	orderText.innerHTML = txt + _orderImg;
	orderText.style.cursor="hand";
	orderText.onclick=function(){
		orderMe(this);
	}
	orderText.title="点击按照[" + txt + "]进行" + paixu + "排序";
	orderText.setAttribute("order",t);
}
function initOrderJs(orderText,paixu,_orderImg,t){
	var txt = orderText.outerText;
	orderText.innerHTML = txt + _orderImg;
	orderText.style.cursor="hand";
	orderText.onclick=function(){
		orderMeJs(this);
	}
	orderText.title="点击按照[" + txt + "]进行" + paixu + "排序";
	orderText.setAttribute("order",t);
}
function orderMeJs(o){
	var starDate = new Date();
	var ty = o.getAttribute("type");
	if (ty==null){
		ty = "2";
	}
	//ty=5 百分比
	var tb = document.getElementById("dataTable");
	var nn = 0;
	var isPaixu = false;
	var n = o.cellIndex;
	var r = tb.rows.length;
	if (r<2){
		return;
	}
	var p = new Array();
	var d = new Array();
	for(var i=0;i<r;i++){
		var tr = tb.rows[i];
		var td = tr.cells[n];

		if (td ==null){
			continue;
		}
		if (td == o){
			isPaixu = true;
			nn = i+1;
			continue;
		}
		
		if(!isPaixu){
			continue;
		}
		//忽略不排序行
		var isOrder = tr.getAttribute("isOrder");
		if (isOrder != null && isOrder =="no"){
			continue;
		}
		var v = td.innerText;
		if (ty=="5"){
			var vl = v.indexOf("%");
			if (vl>0){
				v = v.substring(0,vl); 
			}
		}
		if (d[v]==null){
			p[p.length] =v;
			var d1 = new Array();
			d1[d1.length] = tr;
			d[v]=d1;
		}else{
			//p[p.length] =td.innerText;
			d[v][d[v].length] = tr;
		}
	}
	
	p.SortBy(ty);
	var orderby = o.getAttribute("order");
	var _count = 0;
	var sNumber = 1;
	if("1" != orderby){
		o.setAttribute("order","1");
		for (var i=0;i<p.length;i++){
			var dd = d[p[i]];
			for(var j=0;j<dd.length;j++){
				try{
					if (_number>=0){
						dd[j].cells[_number].innerHTML = sNumber;
					}
				}catch(e){
					alert("请检查序号列设置是否正确,当前序号列为：" + _number);
				}
				while(true){
					if (dd[j].rowIndex==(nn)){
						nn++;
						sNumber++;
						break;
					}
					tb.moveRow(dd[j].rowIndex,nn);
					_count++;
				}
			}
		}
	}else{
		o.setAttribute("order","0");
		for (var i=(p.length-1);i>=0;i--){
			var dd = d[p[i]];
			for(var j=0;j<dd.length;j++){
				try{
					if (_number>=0){
						dd[j].cells[_number].innerHTML = sNumber;
					}
				}catch(e){
					alert("请检查序号列设置是否正确,当前序号列为：" + _number);
				}
				
				while(true){
					if (dd[j].rowIndex==(nn)){
						nn++;
						sNumber++;
						break;
					}
					tb.moveRow(dd[j].rowIndex,nn);
					_count++;
				}
			}
		}
	}
	//alert("排序执行表格移动次数：" + _count);
	loadOrder(o.id);
	var endDate = new Date();
	//outputOtherMsg("页面排序所用时间：" + ((endDate.valueOf() - starDate.valueOf())/1000.0) + "秒(该信息为测试信息)");
}
//一维数组的排序
// type 参数 
// 0 字母顺序（默认） 
// 1 大小 比较适合数字数组排序
// 2 拼音 适合中文数组
// 3 乱序 有些时候要故意打乱顺序，呵呵
// 4 带搜索 str 为要搜索的字符串 匹配的元素排在前面

function Array.prototype.SortBy(type,str){ 
	switch (type)
	{ 
		case "0":this.sort(); break;
		case "1":this.sort(function(a,b){ return a-b; }); break;
		case "2":this.sort(function(a,b){ return a.localeCompare(b) }); break;
		case "3":this.sort(function(){ return Math.random()>0.5?-1:1; }); break;
		case "4":this.sort(function(a,b){ return a.indexOf(str)==-1?1:-1; }); break;
		case "5":this.sort(function(a,b){ return a-b; }); break;
		default:this.sort();
	}
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


/*
Listҳ���JS����
*/
var context = new Array();

//����������ķ���
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
			alert("ͼƬ·��û�����ã����ܳ��ֵ�ԭ��\n1��û����ҳ��������path����(var path = \"<%=path%>\";)��\n2��û����Servlet��ʹ��setStyle(HttpServletRequest request)��������ptah��");
		}
		orderImg = "<img src=\'" + path + "/images/order.gif\'>";
		orderupImg = "<img src=\'" + path + "/images/orderup.gif\'>";
		orderdownImg = "<img src=\'" + path + "/images/orderdown.gif\'>";
		var buttons = document.getElementsByTagName("button");
		var buts = new Array();
		var shows = new Array();
		var search_Object = null;
		for(var i=0;i<buttons.length;i++){
			//����
			if (buttons[i].className=="butt" && buttons[i].innerText.trim()=="����"){
				search_Object = buttons[i];
				break;
			}
		//�õ���f��ͷ�����е�id������֤�Ƿ��ж�Ӧ��ʾ������
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
		//���е�input���ӻس�����
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
	
	}
	loadOrder();
	_load();
};


/**
 * ������Ϣ
 * @param message ��Ϣ
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
 * ��ָ���Ķ����������
 * @param obj Ҫ����ı������
 */
function orderMe(obj){
	var t = obj.getAttribute("order");
	if (t=="1"){
		//����ļ���
		orderDataShow(obj,1);
	}else if (t=="0"){
		//����ļ���
		orderDataShow(obj,0);
	}else{
		//����ļ���
		orderDataShow(obj,1);
	}
}
/**
 * ��ָ���Ķ����������
 * @param obj Ҫ����ı������
 * @param t 1���� 0 ����
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
	document.myForm.submit();
}
//ҳ������������
function loadOrder(jsValue){
	var orderTxt = document.getElementsByName("orderby")[0].value.toUpperCase().trim();
	for(var i=0;true;i++){
		var orderText = document.getElementById("order"+i);
		if (orderText==null){
			break;
		}else{
			var oby = orderText.getAttribute("orderby");
			if (oby==null){
				if (jsValue ==("order"+i)){
					var ooo = orderText.getAttribute("order");
					if ("0"!=ooo){
						initOrderJs(orderText,"����",orderupImg,"1")
					}else{
						initOrderJs(orderText,"����",orderdownImg,"0");
					}
				}else{
					initOrderJs(orderText,"����",orderImg,"0");
				}
				continue;
			}
			oby = oby.toUpperCase();
			if (orderTxt.indexOf(oby)==0 && orderTxt.length<=oby.length+5 && jsValue == null){
				if(orderTxt.lastIndexOf(" ASC") + 4 == orderTxt.length){
					initOrder(orderText,"����",orderupImg,"1");
				}else if (orderTxt.lastIndexOf(" DESC")+ 5 == orderTxt.length){
					initOrder(orderText,"����",orderdownImg,"0");
				}else{
					initOrder(orderText,"����",orderupImg,"1");
				}
			}else{
				initOrder(orderText,"����",orderImg,"0");
			}
		}
	}
}
//��ʼ�����������Ϣ
function initOrder(orderText,paixu,_orderImg,t){
	var txt = orderText.outerText;
	orderText.innerHTML = txt + _orderImg;
	orderText.style.cursor="hand";
	orderText.onclick=function(){
		orderMe(this);
	}
	orderText.title="�������[" + txt + "]����" + paixu + "����";
	orderText.setAttribute("order",t);
}
function initOrderJs(orderText,paixu,_orderImg,t){
	var txt = orderText.outerText;
	orderText.innerHTML = txt + _orderImg;
	orderText.style.cursor="hand";
	orderText.onclick=function(){
		orderMeJs(this);
	}
	orderText.title="�������[" + txt + "]����" + paixu + "����";
	orderText.setAttribute("order",t);
}
function orderMeJs(o){
	var ty = o.getAttribute("type");
	if (ty==null){
		ty = "2";
	}
	//ty=5 �ٷֱ�
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
	
	if("1" != orderby){
		o.setAttribute("order","1");
		for (var i=0;i<p.length;i++){
			var dd = d[p[i]];
			for(var j=0;j<dd.length;j++){
				while(true){
					if (dd[j].rowIndex==(nn)){
						nn++;
						break;
					}
					tb.moveRow(dd[j].rowIndex,nn);
				}
			}
		}
	}else{
		o.setAttribute("order","0");
		for (var i=(p.length-1);i>=0;i--){
			var dd = d[p[i]];
			for(var j=0;j<dd.length;j++){
				while(true){
					if (dd[j].rowIndex==(nn)){
						nn++;
						break;
					}
					tb.moveRow(dd[j].rowIndex,nn);
				}
			}
		}
	}
	loadOrder(o.id);
}
//һά���������
// type ���� 
// 0 ��ĸ˳��Ĭ�ϣ� 
// 1 ��С �Ƚ��ʺ�������������
// 2 ƴ�� �ʺ���������
// 3 ���� ��Щʱ��Ҫ�������˳�򣬺Ǻ�
// 4 ������ str ΪҪ�������ַ��� ƥ���Ԫ������ǰ��

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
		alert("ҳ�볬�����ҳ����");
		return;
	}
	if (page<1){
		alert("ҳ�治��С��1��");
	}
	document.all("page").value = page;
	document.myForm.submit();
}

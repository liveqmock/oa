var area_provinceSelectName;
var area_citySelectName;
var area_countrySelectName;
var area_controllSelectName;
var area_provinceSelectedValue;
var area_citySelectedValue;
var area_countrySelectedValue;
var area_servletPath="/basecode";

/**
 * 加载Select的名字
 * @param provinceSelectName 省选择框的名称
 * @param provinceSelectName 市选择框的名字
 * @param provinceSelectName 县选择框的名字
 * @param provinceSelectName 省选择框的默认值
 * @param provinceSelectName 市选择框的默认值
 * @param provinceSelectName 县选择框的默认值
 */
function area_load(_provinceSelectName,_citySelectName,_countrySelectName,_provinceSelectedValue,_citySelectedValue,_countrySelectedValue){
	area_provinceSelectName=_provinceSelectName;
	area_citySelectName=_citySelectName;
	area_countrySelectName=_countrySelectName;
	area_provinceSelectedValue=_provinceSelectedValue;
	area_citySelectedValue=_citySelectedValue;
	area_countrySelectedValue=_countrySelectedValue;
	area_controllSelectName=area_provinceSelectName;
	area_getAreaInfo("156");
}

//获取当前控制Select的默认值
function area_getCurrentDefValue(){
	var _temp;
	if(area_controllSelectName==area_provinceSelectName){
		_temp=area_provinceSelectedValue;
		area_provinceSelectedValue="";
		return _temp;
		}
	if(area_controllSelectName==area_citySelectName){
		_temp=area_citySelectedValue;
		area_citySelectedValue="";
		return _temp;
		}
	if(area_controllSelectName==area_countrySelectName){
		_temp=area_countrySelectedValue;
		area_countrySelectedValue="";
		return _temp;
		}
	return "";
}

//去掉Select的所有选项
function area_removeSelect(_selectName){
	var _select=document.getElementById(_selectName);
	if (_select!=null)
	for(var i=_select.length-1;i>-1;i--){
		_select.options.remove(i);
	}
}

//============以下是封装的 AJAX
function createXMLHttpRequest() {
	var xmlHttp = null;
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    if (xmlHttp == null){
    		alertInfo("您的浏览器不支持此系统!");	
    		return;
    }
    return xmlHttp;
}
function startRequest(xmlHttp,reqUrl,handleStateChange,reqMethod,isSynchronization ) {
    createXMLHttpRequest();
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.open(reqMethod, reqUrl, isSynchronization);
    xmlHttp.send();
}
 /** 解析XML字符串
 * @param str 要解析的XML字符串
 * @return document对象
 */
function loadXml(str) {
		if (str == null)
			 return null;
		var doc = new ActiveXObject("MSXML.DOMDocument");
		//var doc = new ActiveXObject("Msxml2.DOMDocument.4.0");
		doc.async = false;
		doc.loadXML(str);
		return doc;
}

/** 
* 交互状态改变触发
*/
function area_stateChange() {
       if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {  
            var str = xmlHttp.responseText;
            sjldDoc = loadXml(str);
            codesDoc=sjldDoc.getElementsByTagName("codes")(0);
    	    var nodeSize =codesDoc.childNodes.length; 			
			var areaSelect = document.getElementById(area_controllSelectName);	//获取要刷新的Select			
			var defValue=area_getCurrentDefValue();
			if(areaSelect!=null){
				area_removeSelect(area_controllSelectName);
				areaSelect.options.add(new Option("未选择",""));				
				for(var i=0;i<nodeSize;i++){
					var codeDoc = codesDoc.getElementsByTagName("code")(i);
					var DM=codeDoc.getElementsByTagName("DM")(0).text;
					var MC=codeDoc.getElementsByTagName("MC")(0).text;
					//添加选项
					var oOption = document.createElement("OPTION");					
					areaSelect.options.add(oOption);//产品类别select增加option					
					if (defValue == DM){
						oOption.selected = true;
					}
					oOption.innerText = MC;
					oOption.value = DM;	
				}
			}
			if(defValue!=""&&areaSelect.value!=""&&area_controllSelectName!=area_countrySelectName)
				SelectOnChange(areaSelect.name,areaSelect.value);
		}
    }		
}

//获取区域信息
var xmlHttp;
function area_getAreaInfo(_fjdm){		
    xmlHttp = createXMLHttpRequest();
	var area_url='/basecode/BaseCodeSearchChildrenServlet?DMJ=101001&FJDM='+_fjdm;
    startRequest(xmlHttp,area_url,area_stateChange,"post",false);
}

//选择时间
function SelectOnChange(_name,_fjdm) {
	if(_fjdm!=""){
		if (_name == area_provinceSelectName){
			area_controllSelectName=area_citySelectName;
			area_removeSelect(area_countrySelectName);
			if(document.getElementById(area_countrySelectName)!=null)
				document.getElementById(area_countrySelectName).options.add(new Option("未选择",""));
		}
		else
			if (_name == area_citySelectName){
				area_controllSelectName=area_countrySelectName;
			}
		area_getAreaInfo(_fjdm);
	}
	else{
		if (_name == area_provinceSelectName){
				area_removeSelect(area_citySelectName);
				if(document.getElementById(area_citySelectName)!=null)
					document.getElementById(area_citySelectName).options.add(new Option("未选择",""));				
				area_removeSelect(area_countrySelectName);
				if(document.getElementById(area_countrySelectName)!=null)
					document.getElementById(area_countrySelectName).options.add(new Option("未选择",""));
		}else
			if (_name == area_citySelectName){
					area_removeSelect(area_countrySelectName);
					if(document.getElementById(area_countrySelectName)!=null)
						document.getElementById(area_countrySelectName).options.add(new Option("未选择",""));
			}
	}
}

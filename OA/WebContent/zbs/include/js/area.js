var area_provinceSelectName;
var area_citySelectName;
var area_countrySelectName;
var area_controllSelectName;
var area_provinceSelectedValue;
var area_citySelectedValue;
var area_countrySelectedValue;
var area_servletPath="/basecode";

/**
 * ����Select������
 * @param provinceSelectName ʡѡ��������
 * @param provinceSelectName ��ѡ��������
 * @param provinceSelectName ��ѡ��������
 * @param provinceSelectName ʡѡ����Ĭ��ֵ
 * @param provinceSelectName ��ѡ����Ĭ��ֵ
 * @param provinceSelectName ��ѡ����Ĭ��ֵ
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

//��ȡ��ǰ����Select��Ĭ��ֵ
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

//ȥ��Select������ѡ��
function area_removeSelect(_selectName){
	var _select=document.getElementById(_selectName);
	if (_select!=null)
	for(var i=_select.length-1;i>-1;i--){
		_select.options.remove(i);
	}
}

//============�����Ƿ�װ�� AJAX
function createXMLHttpRequest() {
	var xmlHttp = null;
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    if (xmlHttp == null){
    		alertInfo("�����������֧�ִ�ϵͳ!");	
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
 /** ����XML�ַ���
 * @param str Ҫ������XML�ַ���
 * @return document����
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
* ����״̬�ı䴥��
*/
function area_stateChange() {
       if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {  
            var str = xmlHttp.responseText;
            sjldDoc = loadXml(str);
            codesDoc=sjldDoc.getElementsByTagName("codes")(0);
    	    var nodeSize =codesDoc.childNodes.length; 			
			var areaSelect = document.getElementById(area_controllSelectName);	//��ȡҪˢ�µ�Select			
			var defValue=area_getCurrentDefValue();
			if(areaSelect!=null){
				area_removeSelect(area_controllSelectName);
				areaSelect.options.add(new Option("δѡ��",""));				
				for(var i=0;i<nodeSize;i++){
					var codeDoc = codesDoc.getElementsByTagName("code")(i);
					var DM=codeDoc.getElementsByTagName("DM")(0).text;
					var MC=codeDoc.getElementsByTagName("MC")(0).text;
					//���ѡ��
					var oOption = document.createElement("OPTION");					
					areaSelect.options.add(oOption);//��Ʒ���select����option					
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

//��ȡ������Ϣ
var xmlHttp;
function area_getAreaInfo(_fjdm){		
    xmlHttp = createXMLHttpRequest();
	var area_url='/basecode/BaseCodeSearchChildrenServlet?DMJ=101001&FJDM='+_fjdm;
    startRequest(xmlHttp,area_url,area_stateChange,"post",false);
}

//ѡ��ʱ��
function SelectOnChange(_name,_fjdm) {
	if(_fjdm!=""){
		if (_name == area_provinceSelectName){
			area_controllSelectName=area_citySelectName;
			area_removeSelect(area_countrySelectName);
			if(document.getElementById(area_countrySelectName)!=null)
				document.getElementById(area_countrySelectName).options.add(new Option("δѡ��",""));
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
					document.getElementById(area_citySelectName).options.add(new Option("δѡ��",""));				
				area_removeSelect(area_countrySelectName);
				if(document.getElementById(area_countrySelectName)!=null)
					document.getElementById(area_countrySelectName).options.add(new Option("δѡ��",""));
		}else
			if (_name == area_citySelectName){
					area_removeSelect(area_countrySelectName);
					if(document.getElementById(area_countrySelectName)!=null)
						document.getElementById(area_countrySelectName).options.add(new Option("δѡ��",""));
			}
	}
}

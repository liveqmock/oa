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
alwaysLowered | yes/no | ָ���������������д���֮�� 
alwaysRaised | yes/no | ָ���������������д���֮�� 
depended | yes/no | �Ƿ�͸�����ͬʱ�ر� 
directories | yes/no | Nav2��3��Ŀ¼���Ƿ�ɼ� 
height | pixel value | ���ڸ߶� 
hotkeys | yes/no | ��û�˵����Ĵ������谲ȫ�˳��ȼ� 
innerHeight | pixel value | �������ĵ������ظ߶� 
innerWidth | pixel value | �������ĵ������ؿ�� 
location | yes/no | λ�����Ƿ�ɼ� 
menubar | yes/no | �˵����Ƿ�ɼ� 
outerHeight | pixel value | �趨����(����װ�α߿�)�����ظ߶� 
outerWidth | pixel value | �趨����(����װ�α߿�)�����ؿ�� 
resizable | yes/no | ���ڴ�С�Ƿ�ɵ��� 
screenX | pixel value | ���ھ���Ļ��߽�����س��� 
screenY | pixel value | ���ھ���Ļ�ϱ߽�����س��� 
scrollbars | yes/no | �����Ƿ���й����� 
titlebar | yes/no | ������Ŀ���Ƿ�ɼ� 
toolbar | yes/no | ���ڹ������Ƿ�ɼ� 
Width | pixel value | ���ڵ����ؿ�� 
z-look | yes/no | ���ڱ�������Ƿ�����������֮��
*/
function openwindow(url,name,iWidth,iHeight,iLeft,iTop){
  var url;                                 //ת����ҳ�ĵ�ַ;
  var name;                           //��ҳ���ƣ���Ϊ��;
  var iWidth;                          //�������ڵĿ��;
  var iHeight;                     //�������ڵĸ߶�;
  if (iTop==null)
  iTop = (window.screen.availHeight-30-iHeight)/2;       //��ô��ڵĴ�ֱλ��;
  if (iLeft==null)
  iLeft = (window.screen.availWidth-10-iWidth)/2;           //��ô��ڵ�ˮƽλ��;
  window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
 }
/**
 * ��select�������option
 * @param select select����
 * @param i ����֮ǰ����
 * @param o ����֮����
 * @param selYear ѡ����꣬selYearΪnullʱ��Ϊ��ǰ��
 * @param isDefault �Ƿ���Ĭ�����ݣ����磺��ѡ��
 * @param defaultValue Ĭ�ϵ�����ֵ ���磺""
 * @param defaultText Ĭ�ϵ���ʾ���� ���磺��ѡ��
 */
function getYearOption(select,i,o,selYear,isDefault,defaultValue,defaultText){
	if (isDefault==null){
		isDefault=true;
	}
	if (defaultValue==null){
		defaultValue = "0";
	}
	if (defaultText == null){
		defaultText = "ȫ��";
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
 * ��select���Ӽ��ȵ�option
 * @param select select����
 * @param selQuarter ѡ��ļ���
 */
function getQuarterOption(select,selQuarter){
	var oDate = new Date();
	var quarter = ((oDate.getMonth() + 1)/4) + 1;
	if (selQuarter == null){
		selQuarter = quarter;
	}
	var isSelect = "";
	var obj = document.createElement("OPTION");
	obj.text= "ȫ��";
	obj.value="0";
	if (selQuarter==0){
		isSelect = selQuarter;
	}
	select.add(obj);
	for(var m=1;m<=4;m++){
		var obj1 = document.createElement("OPTION");
		obj1.text= "��" + m + "����";
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
		obj1.text= "��" + m + "����";
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
 * ȫѡ/��ѡ
 * @param obj ѡ���checkbox��
 * @param name ��ѡ�͸�ѡ������ƣ�name��
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
 * �õ�IE�İ汾
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
 * ���������ڵĿ��
 * @param n ԭIE6���
 * @return ���ص�ǰ������汾����Ҫ�Ŀ��
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
 * ���������ڵĸ߶�
 * @param n ԭIE6�߶�
 * @return ���ص�ǰ������汾����Ҫ�ĸ߶�
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

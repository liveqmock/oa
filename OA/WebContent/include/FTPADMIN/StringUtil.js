/**
 * �����ַ���
 * @author lxy
 * @version 1.0
 */



//��Array������ӷ������
Array.prototype.removeall = kill;   

function kill(){     
	this.splice(0,this.length);
} 

/**
 * ���ַ���"\",ת����"\\"
 * @param {Object} str
 */
function linkedstr(str){
	var string = str+"";	
	var sub = new Array();
	var i = string.indexOf("\\");
	var j = 0;
	var result = "";
	while(i>=0){
		sub[j] = string.substring(0,i);
		string = string.substring(i+1,string.length);
		i = string.indexOf("\\");
		j=j+1;
	}
	for(var n=0;n<sub.length;n++){
		result = result + sub[n]+"\\\\";
	}
	result = result + string;
	return result;
}

/**
 * ȡ������·�������ļ�/�ļ�����
 * @param {Object} str
 */
function lastnamestr(str){
	var string = str + "";
	var index = string.lastIndexOf("\\");
	var result = string.substring(index+1,string.length); 
	return result;
}

/**
 * �õ���ǰĿ¼��һ��Ŀ¼
 * @param {Object} str
 */
function upperpathstr(str){
	var string = str + "";
	var index = string.lastIndexOf("\\");
 	var result = string.substring(0,index);
	return result;
}

/**
 * ����һ���ַ��������ַ���ת��Ϊ���飬��","�ָ�, type: 0,Ŀ¼ 1,�ļ��� 2,��Ŀ¼
 * @param {Object} str
 */
function strtoarr(str,type){
	var arr = new Array();
	if(type=="0"){
		var ele = new felement();
		ele.type = "0" ;
		ele.icon = config.foldericon;
		ele.text = ".(ˢ�±�ҳ)";
		ele.path = ".";
		arr[arr.length]=ele;
		var ele = new felement();
		ele.type = "0" ;
		ele.icon = config.foldericon;
		ele.text = "..(�����ϲ�Ŀ¼)";
		ele.path = "..";
		arr[arr.length]=ele;
	    str = str.substring(6,str.length);	
	}else if(type == "1" || type == "2"){
		str = str.substring(1,str.length);	
	}
	var index = str.indexOf(",");
	while(index>=0){
		var ele = new felement();
		if(type=="0" || type=="2"){
			ele.type = "0" ;
			ele.icon = config.foldericon;
		}else if(type=="1"){
			ele.type = "1" ;
			ele.icon = config.fileicon;
		}
		ele.text = str.substring(0,index);
		ele.path = curremotecurdir+ele.text+"/";
		arr[arr.length]=ele;
		str = str.substring(index+1,str.length);
		index = str.indexOf(",");
	}
	if(str!=""){
		var ele = new felement();
		if(type=="0" || type=="2"){
			ele.type = "0" ;
			ele.icon = config.foldericon;
		}else if(type=="1"){
			ele.type = "1" ;
			ele.icon = config.fileicon;
		}
		ele.text = str;
		ele.path = curremotecurdir+ele.text+"/";
		arr[arr.length]=ele;	
	}
	//alert(arr.length);
	return arr;
}

/**
 * ���ַ��������ڶ���"/"���������ȥ��
 * @param {Object} str
 */
function retstr(str){
	str = str.substring(0,str.lastIndexOf("/"));
	str = str.substring(0,str.lastIndexOf("/")+1);
	return str;
}

/**
 * ȡ���ַ����������"/"֮����ַ�
 * @param {Object} str
 */
function rfoldernamestr(str){
	str = str.substring(0,str.lastIndexOf("/"));
	str = str.substring(str.lastIndexOf("/")+1);
	return str;
}

/**
 * �ж��ַ������Ƿ�ֻ�е�Ͷ���
 * @param {Object} str
 */
function dottrim(str){
	for(var i=0;i<str.length;i++){
		if(str.substring(i,i+1)!="." && str.substring(i,i+1)!=","){
			return false;
		}
	}
	return true;
}

/**
 * ���һ���ļ����Ƿ�Ϊ�����ļ���
 * @param {Object} str
 */
function checkbackup(str){
	if(str.substring(str.length-4,str.length) == ".bak"){
		return true;
	}else{
		return false;
	}
}




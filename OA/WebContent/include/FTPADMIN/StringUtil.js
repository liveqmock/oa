/**
 * 处理字符串
 * @author lxy
 * @version 1.0
 */



//给Array数组添加方法清空
Array.prototype.removeall = kill;   

function kill(){     
	this.splice(0,this.length);
} 

/**
 * 将字符串"\",转换成"\\"
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
 * 取得完整路径最后的文件/文件夹名
 * @param {Object} str
 */
function lastnamestr(str){
	var string = str + "";
	var index = string.lastIndexOf("\\");
	var result = string.substring(index+1,string.length); 
	return result;
}

/**
 * 得到当前目录上一层目录
 * @param {Object} str
 */
function upperpathstr(str){
	var string = str + "";
	var index = string.lastIndexOf("\\");
 	var result = string.substring(0,index);
	return result;
}

/**
 * 输入一个字符串，将字符串转化为数组，以","分割, type: 0,目录 1,文件夹 2,根目录
 * @param {Object} str
 */
function strtoarr(str,type){
	var arr = new Array();
	if(type=="0"){
		var ele = new felement();
		ele.type = "0" ;
		ele.icon = config.foldericon;
		ele.text = ".(刷新本页)";
		ele.path = ".";
		arr[arr.length]=ele;
		var ele = new felement();
		ele.type = "0" ;
		ele.icon = config.foldericon;
		ele.text = "..(返回上层目录)";
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
 * 将字符串倒数第二个"/"后面的内容去掉
 * @param {Object} str
 */
function retstr(str){
	str = str.substring(0,str.lastIndexOf("/"));
	str = str.substring(0,str.lastIndexOf("/")+1);
	return str;
}

/**
 * 取出字符串最后两个"/"之间的字符
 * @param {Object} str
 */
function rfoldernamestr(str){
	str = str.substring(0,str.lastIndexOf("/"));
	str = str.substring(str.lastIndexOf("/")+1);
	return str;
}

/**
 * 判断字符串中是否只有点和逗号
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
 * 检查一个文件夹是否为备份文件夹
 * @param {Object} str
 */
function checkbackup(str){
	if(str.substring(str.length-4,str.length) == ".bak"){
		return true;
	}else{
		return false;
	}
}




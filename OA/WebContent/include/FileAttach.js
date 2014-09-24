/**
 * ·¢ËÍÓÊ¼þÕ³Ìù¸½¼þ
 * @author lxy
 * version 1.0
 */

var uploadc = "upfile";
//var frame = "uploadfrm";
//var subform = "frm";
var showc = "showfile";
var textclass = "message_title";
var filetable = "filetable";
var filenum = 1;

function upload(){
	alert("m");
	var uploadobj = document.getElementById(uploadc);
	var showobj = document.getElementById(showc); 
	var addfile = document.getElementById(filetable); 
	uploadobj.click();
	showobj.innerHTML = showobj.innerHTML + parsehtml(filename(uploadobj.value),uploadobj.value);
	var filehtml = "<input type=\"file\" id=\"upfile"+filenum+"\" >";
	alert(filehtml);
	alert(addfile);
	//addfilespan.innerHTML = addfilespan.innerHTML + filehtml;
	//alert(eval("document.getElementById(upfile"+filenum+")"))
	//filenum = filenum + 1;
}

function filename(path){
	var num = path.lastIndexOf("\\")+1;
	var name = path.substring(num,path.length);
	return name;
}
 
 function parsehtml(name,path){
	var htmlstr = "<span path=\""+path+"\" class=\""+textclass+"\">"+name+";"+"</span>";
	return htmlstr;
 }

function createrequest(){
	var request = false;
	try {
		request = new XMLHttpRequest();
		return request;
	} catch (failed) {
		request = false;
	}
	if (!request)
		alert("Error initializing XMLHttpRequest!");
}
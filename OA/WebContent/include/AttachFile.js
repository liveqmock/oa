
var filename = "filebox";
var filetabname = "files";
var filespan = "showfile";
var nametype = "";
var filenum = 0;

function addfile(){
	var filetableobj = document.getElementById(filetabname);
	var filespanobj = document.getElementById(filespan);
	var trobj = filetableobj.insertRow(filetableobj.rows.length);
	var tdobj = trobj.insertCell(0);
	tdobj.innerHTML = "<input type=\"file\" id=\"filebox"+ filenum +"\" name=\"filebox"+ filenum +"\"/>";
	var fileobj = eval("document.getElementById('"+filename+filenum+"')");
	fileobj.click();
	filespanobj.innerHTML = filespanobj.innerHTML + parsehtml(getfilename(fileobj.value));
	filenum = filenum + 1;
}

function getfilename(name){
	var filename = name.substring(name.lastIndexOf("\\")+1,name.length);
	return filename;
}

function parsehtml(str){
	var strhtml = "<span class=\""+nametype+"\" id=\"show"+filenum+"\">"+str+"<a href=\"javascript:delfile('"+filenum+"')\">É¾³ý</a>;"+"</span>";
	return strhtml;
}

function delfile(objnum){
	var delshow = eval("document.getElementById('show"+objnum+"')");
	var delfile = filename + objnum;
	var filetableobj = document.getElementById(filetabname);
	var rownum = filetableobj.rows.length;
	delshow.outerHTML = "";
	for(var i=0;i<rownum;i++){
		var tent = filetableobj.rows[i];
		if(tent.cells[0].firstChild.id == delfile){
			filetableobj.deleteRow(i);
		}
	}

	
}

var fileNumber = 0;
var realnum = 0;
var existnum = 0;
var fso = getfilesystem();
var totalsize = 0;
var fileArr = new Array();

function reduceSize(fullName){
	for (var i = 0; i < fileArr.length; i++){
		if (fileArr[i].name === fullName){
			totalsize -= fileArr[i].size/(1024*1024);
			break;
		}
	}
}

function createnew()
{
	checkname();
    var c_a = document.getElementById('container1');// 找到上传控件的a容器
    var c_div = document.getElementById('container2');// 放置文件的容器
    var fileCtr = c_a.firstChild;// 上传控件
    var subDiv = document.createElement("span");// 将放置到c_div中的容器
    var span1 = document.createElement("span");// 上传的文件
    var size = 0;
	if(fso != null){
		var size = filesize(fileCtr.value);
	}else if (fileCtr.files && fileCtr.files.length >= 1){
		var size = fileCtr.files[0].size;
	}

	if(totalsize + size/(1024*1024) > 20){
		alert("上传附件不能超过20MB！");
		return;
	}
	var showsize = parsesize(size);
	var fullName = getfilename(fileCtr.value)+showsize+";";
	fileArr.push({"name":fullName,"size":size});
	
    span1.innerText = fullName;
	span1.className = "class_1";
	if(checkname(span1.innerText) == false){
		return;
	}
    var img2 = document.createElement("img");// 删除图片按钮
	img2.src = "/oabase/mail/css/del.gif";  
	img2.width="11";
	img2.height="15";
	img2.title = "删除";
	img2.style.cursor = 'pointer';
    img2.onclick = function(){
    	reduceSize(this.parentNode.getElementsByTagName('span')[0].innerHTML);
		this.parentNode.parentNode.removeChild(this.parentNode);
		realnum = realnum - 1;
		if (realnum == 0){
			document.getElementById('container2').style.height = '21px';
		} else{
			document.getElementById('container2').style.height = '100%';
		}
		document.sendForm.realnum.value = realnum + existnum;
	}
    subDiv.appendChild(span1);
    subDiv.appendChild(img2); 
    subDiv.appendChild(fileCtr);    
    c_div.appendChild(subDiv);
    c_div.style.height = c_div.scrollHeight;
    fileNumber++;
	realnum++;
	
	document.sendForm.filenum.value = fileNumber;
	document.sendForm.realnum.value = realnum + existnum;
    var newFileCtr = document.createElement("input");
    newFileCtr.type = "file";
    newFileCtr.className = "addfile";
    newFileCtr.name = "file_"+fileNumber;
    newFileCtr.id = newFileCtr.name;
    newFileCtr.onchange = createnew;
    c_a.onclick = clickFileInput;
    while(c_a.firstChild)
    {
        c_a.removeChild(c_a.firstChild);
    }
    c_a.appendChild(newFileCtr);
}  

function clickFileInput()
{
	document.getElementById("file_"+fileNumber).click();
}

function existfilenode(name,path,size){ 
	var c_div = document.getElementById('container2');
	var subDiv = document.createElement("span");
	var span1 = document.createElement("span");
	if(totalsize + size/(1024*1024) > 20){
		alert("上传附件不能超过20MB！");
		return;
	}
	var showsize = parsesize(size);

    span1.innerText = name+showsize+";";
	span1.className = "class_1";
	var img2 = document.createElement("img");// 删除图片按钮
  	img2.src = "/oabase/mail/css/del.gif";  
	img2.width="11";
	img2.height="15";
	img2.title = "删除";
    img2.onclick = function(){
		this.parentNode.parentNode.removeChild(this.parentNode);
		document.sendForm.realnum.value = realnum;
		existnum = existnum - 1;
		document.sendForm.realnum.value = realnum + existnum;
	}
	var pathh = document.createElement("input");
	pathh.type = "hidden";
	pathh.name = "exist_"+existnum;
	pathh.value = path;
    subDiv.appendChild(span1);
    subDiv.appendChild(img2);    
    subDiv.appendChild(pathh);    
    c_div.appendChild(subDiv); 
    existnum = existnum + 1;
	document.sendForm.realnum.value = realnum + existnum;
}

function getfilename(name){
	var filename = name.substring(name.lastIndexOf("\\")+1,name.length);
	return filename;
}

function checkname(name){
	var obj = document.getElementById("container2");
	var names = obj.getElementsByTagName("SPAN");
	for(var i=0;i<names.length;i++){
		if(names[i].innerHTML == name){
			alert("有同名文件存在!");
			return false;
		}
	}
}

function getfilesystem(){
	try{
		var fso = new ActiveXObject("Scripting.FileSystemObject"); 
		return fso;
	}catch(e){
		return null;
	}
}

function filesize(path){
	var f=fso.GetFile(path);
	return f.size;
}

function parsesize(size){
	if (size == 0) return '';
	var showsize = "";
	totalsize = totalsize + size/(1024*1024);
	var sizestr;
	if(size/(1024*1024)>=1){
		sizestr = new String(size/(1024*1024));
		if (sizestr.indexOf(".") == -1){
			sizestr += 'MB';
		}else{
			sizestr = sizestr.substring(0, sizestr.indexOf(".") + 2) + "MB";
		}
	}else if(size/1024>=1){
		sizestr = new String(size/1024);
		if (sizestr.indexOf(".") == -1){
			sizestr += 'KB';
		}else{
			sizestr = sizestr.substring(0, sizestr.indexOf(".") + 2) + "KB";
		}
	}else{
		sizestr = new String(size);
		sizestr = sizestr + "Byte";
	}
	showsize = "("+sizestr+")";
	return showsize;
}
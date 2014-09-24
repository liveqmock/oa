window.$ = document.getElementById;
var fileNumber = 0;
function createnew()
{
    var c_a = $('container1');// 找到上传控件的a容器
    var c_div = $('container2');// 放置文件的容器
    var fileCtr = c_a.firstChild;// 上传控件
    var subDiv = document.createElement("span");// 将放置到c_div中的容器
    var span1 = document.createElement("span");// 上传的文件
    span1.innerText = getfilename(fileCtr.value)+";";
	span1.className = "message_title";
    var img2 = document.createElement("img");// 删除图片按钮
    img2.className = "addfile";
    img2.onclick = function(){this.parentNode.parentNode.removeChild(this.parentNode)}
    subDiv.appendChild(span1);
    subDiv.appendChild(img2);    
    subDiv.appendChild(fileCtr);    
    c_div.appendChild(subDiv); 
    fileNumber++;
	document.sendForm.filenum.value = fileNumber;
	alert(c_div.innerHTML)
    var newFileCtr = document.createElement("input");
    newFileCtr.type = "file";
    newFileCtr.className = "addfile";
    newFileCtr.name = "file_"+fileNumber;
    newFileCtr.onchange = createnew;
    while(c_a.firstChild)
    {
        c_a.removeChild(c_a.firstChild);
    }
    c_a.appendChild(newFileCtr);
}  

function getfilename(name){
	var filename = name.substring(name.lastIndexOf("\\")+1,name.length);
	return filename;
}
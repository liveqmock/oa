
/**
 * ������ҳ�������ʾ��Ϣ
 * @author lxy
 * @version 1.0
 */

/*************************��ȡҳ����ʾԪ��****************************/

var localarea = {
	list : document.getElementById("showlist"),
	dir : document.getElementById("cur_local_dir"),
	up : document.getElementById("local_up"),
	last : document.getElementById("local_last"),
	next : document.getElementById("local_next"),
	bar : document.getElementById("sendbar")
};


var remotearea = {
	list : document.getElementById("remote_list"),
	dir : document.getElementById("cur_remote_dir"),
	up : document.getElementById("remote_up"),
	last : document.getElementById("remote_last"),
	next : document.getElementById("remote_next"),
	bar : document.getElementById("receivebar")
};

var bupload = document.getElementById("upload");

var bdownload = document.getElementById("download");
/*******************************************************************/
/**
 * ��ȡ��ĻҪ��ʾ��Ԫ��,sta=1������ʾ��ʷ��¼�����洢����
 * @param {Object} path
 * @param {Object} sta
 */
function drawelement(path,area,sta){
	if(area == "localarea"){
		clear(area);
		drawdiskproxy(area);
		var folderelement = new Array();
		var fileelement = new Array();
		curcurfolder = path;
		curcurfoldername = lastnamestr(path);
		if(queue.length <= config.queuesize && sta != 1){
			queue[curqueuelength] = path ;
			curqueuelength = curqueuelength + 1;
			curcursor = curqueuelength;
		}
		if(path == "root"){
			folderelement = getdrives();
			draw(area,folderelement);
		}else{
			folderelement = getfolderbydir(path);
			fileelement = getfilesbydir(path);
			if(folderelement.length == 0){
				draw(area,fileelement);
				return;
			}else{
				draw(area,folderelement,fileelement);
				return;
			}
		}
	}else if(area == "remotearea"){
		clear(area);
		drawdiskproxy(area,"���ڶ�ȡ������Ϣ�����Ժ�...");
		var folderelement = new Array();
		var fileelement = new Array();
		if(path!="." && path!=".."){
			curremotecurdir = path;
		}
		if(path == ".."){
			curremotecurdir = retstr(curremotecurdir);
			folderelement = remotefolders(curremotecurdir);
			fileelement = remotefiles(curremotecurdir);	
		}
		if(rqueue.length <= config.queuesize && sta != 1){
			rqueue[currqueuelength] = path ;
			currqueuelength = currqueuelength + 1;
			currcursor = currqueuelength;
		}
		if(path != ".."){
			folderelement = remotefolders(path);
			fileelement = remotefiles(path);
		}
		draw(area,folderelement,fileelement);

	}
}

/**
 * ������Ĳ������Ƶ�ҳ����
 */
function draw(){
	var area = arguments[0];
	var dirobj = eval(area+".dir");
	var upobj = eval(area+".up");
	var lastobj = eval(area+".last");
	var nextobj = eval(area+".next");
	clear(area);
	//��ʾ���̡��ļ����ļ�����Ϣ
	for(var i=1;i<arguments.length;i++){
		for(var j=0;j<arguments[i].length;j++){
			drawcell(area,arguments[i][j]);
		}
	}
	//��ʾϵͳ��ǰλ��
	if(area == "localarea"){
		dirobj.value = curcurfolder;
	}else{
		dirobj.value = curremotecurdir;
	}
	
	//��������ϲ�Ŀ¼���������ϲ�Ŀ¼
	if(area == "localarea"){
		if(curcurfolder == "root"){
			upobj.src = config.upicon;
			upobj.onclick = "false";
		}else{
			upobj.src = config.upicon;
			upobj.onclick = function(){upfolder(area);};
		}
	}else{
		if(curremotecurdir == "/"){
			upobj.src = config.upicon;
			upobj.onclick = "false";
		}else{
			upobj.src = config.upicon;
			upobj.onclick = function(){upfolder(area);};
		}
	}
	
	//����ڶ���ͷ�����ܷ�����һ��������ٶ���β���ܷ�����һ��
	if(area == "localarea"){
		if(curcursor == 1){
			lastobj.src = config.backdisableicon;
			lastobj.onclick = "false";
		}else if(curcursor != 1){
			lastobj.src = config.backicon;
			lastobj.onclick = function(){laststep(area);};
		}
		if(curcursor == curqueuelength){
			nextobj.src = config.nextdisableicon;
			nextobj.onclick = "false";
		}else if(curcursor != curqueuelength){
			nextobj.src = config.nexticon;
			nextobj.onclick = function(){nextstep(area);};
		}
	}else if(area == "remotearea"){
		if(currcursor == 1){
			lastobj.src = config.backdisableicon;
			lastobj.onclick = "false";
		}else if(currcursor != 1){
			lastobj.src = config.backicon;
			lastobj.onclick = function(){laststep(area);};
		}
		if(currcursor == currqueuelength){
			nextobj.src = config.nextdisableicon;
			nextobj.onclick = "false";
		}else if(currcursor != currqueuelength){
			nextobj.src = config.nexticon;
			nextobj.onclick = function(){nextstep(area);};
		}
	}
}

/**
 * �����������һ��Ԫ��
 * @param {Object} text
 */
function drawcell(area,ele){
	var listobj = eval(area+".list");
	var link = linkedstr(ele.path);
	var rflag = "";
	if(area == "localarea"){
		rflag = "local";
	}else if(area == "remotearea"){
		rflag = "remote";
	}
	if((ele.type == 0 || ele.type == 2)){
		var newrow = listobj.insertRow(listobj.rows.length);

		var c1 = newrow.insertCell(0);
		c1.innerHTML = "<img src=\"" + ele.icon + "\">";
	 	var c2 = newrow.insertCell(1);
		c2.vAlign = "bottom";
		c2.innerHTML = "<span style=\"cursor:hand\" class=\"unselect\" rflag=\"" + rflag 
 		 + "\" onclick=\"javascript:select(this,1)\" path=\"" + link + "\" type=\"" + ele.type + "\">" + ele.text + "</span>";
		if(ele.avaliable == true){
			c2.ondblclick = function(){
				if(ele.path == ".." && curremotecurdir == ftpbase){
					drawelement(".",area);
				}else{
					drawelement(link,area);
				}
			}
		}
		 if(checkbackup(ele.text) == false && ele.path!="." && ele.path!=".."){
		 	var c3 = newrow.insertCell(2);
			c3.innerHTML = "<span class=\"unselect\">���û�Ŀ¼��</span>";
		 }else if(checkbackup(ele.text) == true){
		 	var c3 = newrow.insertCell(2);
			c3.innerHTML = "<span class=\"unselect\">������Ŀ¼��</span>";
		 }
		
		 if(ele.path!="." && ele.path!=".." && checkbackup(ele.text) == false){
		 	var c4 = newrow.insertCell(3);
			var fid = document.getElementById("fid");
			var ser = basepath + "/servlet/ShowUserRightServlet?cur="+curremotecurdir+ele.text+"&fid="+fid.value;
			var jscode = "javascript:window.open('"+ser+"')";
			c4.innerHTML = "<a href=\"#\" class=\"unselect\" onclick=\"" + jscode + "\">����Ȩ��</a>"
		 }
	}else{ 
		var newrow = listobj.insertRow(listobj.rows.length);
		var c1 = newrow.insertCell(0);
		c1.innerHTML = "<img src=\"" + ele.icon + "\">";
	 	var c2 = newrow.insertCell(1);
		c2.vAlign = "bottom";
		c2.innerHTML = "<span id=\""+ ele.text +"\" style=\"cursor:hand\" class=\"unselect\" rflag=\"" + rflag 
		 + "\" onclick=\"javascript:select(this)\" path=\"" + link + "\" type=\"" + ele.type + "\">" + ele.text + "</span>";
		var c3 = newrow.insertCell(2);
		c3.innerHTML = "<span class=\"unselect\">���û��ļ���</span>";
	}
}

/**
 * �޼��ַ���
 * @param {Object} text
 */
function clear(area){
	var obj = eval(area+".list");
	var x = obj.rows.length-1;
	for(var i=0;i<x;i++){
		var index = obj.rows[1].rowIndex;
		obj.deleteRow(index);
	}
	
}

/**
 * ��ӡ���̶�ȡ������Ϣ
 */
function drawdiskproxy(area,text){
	var obj = eval(area+".list");
	var newrow = obj.insertRow(obj.rows.length);
	var c1 = newrow.insertCell(0);
	c1.className = "unselect";
	c1.innerHTML = text;
}

/**
 * ���ص���һ��Ŀ¼
 */
function upfolder(area){
	if(area == "localarea"){
		var curpath = curcurfolder ;
			if(curpath.length <= 3){
				drawelement("root",area);
			}else{
			drawelement(upperpathstr(curpath),area);
		}
	}else if(area == "remotearea"){
		if(curremotecurdir == ftpbase){
			drawelement(".",area);
		}else{
			drawelement("..",area);
		}
		
	}
}


/**
 * ������һ�������ļ���
 */
function laststep(area){
	if(area == "localarea"){
		if (curcursor > 0) {
			curcursor = curcursor - 1;
		}
		var path = queue[curcursor - 1];
		drawelement(path,area,1);
	}else if(area == "remotearea"){
		if (currcursor > 0) {
			currcursor = currcursor - 1;
		}
		var path = rqueue[currcursor - 1];
		drawelement(path,area,1);
	}

}

/**
 * ������һ�������ļ���
 */
function nextstep(area){
	if (area == "localarea") {
		if (curcursor < curqueuelength) {
			curcursor = curcursor + 1;
		}
		var path = queue[curcursor - 1];
		drawelement(path, area, 1);
	}else if(area == "remotearea"){
		if (currcursor < currqueuelength) {
			currcursor = currcursor + 1;
		}
		var path = rqueue[currcursor - 1];
		drawelement(path, area, 1);
	}
}

/**
 * ���ظ�Ŀ¼
 */
function rootpath(area){
	if(area == "localarea"){
		drawelement("root",area);
	}else if(area == "remotearea"){
		drawelement(ftpbase,area);
	}
}

/**
 * �½��ļ���
 */
function newfolder(){
	var name = window.prompt("���������ļ��е����֣�","");
	if(name != null){
		if(name == ""){
			alert("�������ļ�����");
			return;
		}
		if(rfolderexist(name)){
			alert("��ͬ���ļ��д��ڣ������´�����");
			return;
		}
		var fid = document.getElementById("fid");
		window.location.href = basepath + "/servlet/NewUserFolderServlet?name="+name+"&pid="+fid.value+"&cur="+curremotecurdir;
	}
}

/**
 * ɾ��ѡ�е��ļ��ļ���
 */
function deletesel(){
	clearsel("remotearea");
	var num = getallselect("remotearea");
	var folderstr = "";
	var filestr = "";
	var is,ok;
	var i, j;
	for (var i = 0; i < currselfolder.length; i++) {
		if(currselfolder[i].path != "." && currselfolder[i].path != ".."){
			folderstr = folderstr + "," + rfoldernamestr(currselfolder[i].path);
		}
	}
	for (var j = 0; j < currselfile.length; j++) {
		filestr = filestr + "," + currselfile[j].innerHTML;
	}
	if ((i==0 || dottrim(folderstr)) && j==0) {
		alert("����ѡ��Ҫɾ���Ķ���!");
		return false;
	}
	ok = confirm("�Ƿ�ɾɾ��\n�ļ���:" + folderstr + "\n" + "�ļ�:" + filestr);
	if (ok) {
//		var sta = document.FtpApplet.deleteBatch(folderstr,filestr);
//		if(sta != "-1"){
//			alert("�ļ���:\""+sta+"\"�������ļ���,����ɾ��!");
//		}
//		drawelement(curremotecurdir, "remotearea");
		var fid = document.getElementById("fid");
		window.location.href = basepath + "/servlet/DelUserFolderServlet?folderstr="+folderstr+"&filestr="+filestr+"&cur="+curremotecurdir+"&fid="+fid.value;
	}
}

/**
 * �����ƶ��ļ��� act:save ����  move �ƶ�
 * @param {Object} act
 */
function filetransfer(act){
	clearsel("remotearea");
	var num = getallselect("remotearea");
	var folderstr = "";
	var filestr = "";
	var is,ok;
	var i, j;
	for (var j = 0; j < currselfile.length; j++) {
		filestr = filestr + "," + currselfile[j].innerHTML;
	}
	if (j==0) {
		alert("����ѡ��Ҫ�ƶ����ļ�!");
		return false;
	}
	var fid = document.getElementById("fid");
	var path = basepath + "/servlet/FolderTreeServlet?fid="+fid.value+"&files="+filestr+"&act="+ act +"&cur="+curremotecurdir;
	window.open(path);
}


/**
 * ѡ��Ҫѡ�еĶ���,flag=1Ϊ��ѡ��2Ϊ��ѡ
 * @param {Object} obj
 * 
 */
function select(obj,flag){
	if(flag == 1){
		if(obj.className == "unselect"){
			obj.className = config.select;
		}else if(obj.className == config.select){
			obj.className = "unselect";
		}	
	}else{
		if(obj.className == "unselect"){
			obj.className = config.select;
		}else if(obj.className == config.select){
			obj.className = "unselect";
		}
	}
}

/**
 * ��ȡ����ѡ�е��ļ��к��ļ�
 */
function getallselect(area){
	var tags = document.getElementsByTagName("span");
	var m = 0;
	var n = 0;
	if (area == "localarea") {
		curselfolder.removeall();
		curselfile.removeall();
		for (var i = 0; i < tags.length; i++) {
			if (tags[i].type == 0 && tags[i].className == config.select && tags[i].rflag == "local") {
				curselfolder[m] = tags[i];
				m = m + 1;
			}
			else 
				if (tags[i].type == 1 && tags[i].className == config.select && tags[i].rflag == "local") {
					curselfile[n] = tags[i];
					n = n + 1;
				}
		}
	}else if(area =="remotearea"){
		currselfolder.removeall();
		currselfile.removeall();
		for (var i = 0; i < tags.length; i++) {
			if (tags[i].type == 0 && tags[i].className == config.select && tags[i].rflag == "remote") {
				currselfolder[m] = tags[i];
				m = m + 1;
			}
			else if (tags[i].type == 1 && tags[i].className == config.select && tags[i].rflag == "remote") {
				currselfile[n] = tags[i];
				n = n + 1;
			}
		}
	}
	return m+n;
}

/**
 * ���ѡ�е�Ԫ��
 */
function clearsel(area){
	if(area =="localarea"){
		curselfolder.removeall();
		curselfile.removeall();
	}else if(area =="remotearea"){
		currselfolder.removeall();
		currselfile.removeall();
	}

}

/**
 * ͨѶ�����������������ݣ�����ftp������
 */
function connecttoftp(area){
	clearsel(area);
	var num = getallselect(area);
	var folderstr = "";
	var filestr = "";
	var is,ok;
	var barobj = eval(area+".bar");
	if (area == "localarea") {
		var i,j;
		for (i = 0; i < curselfolder.length; i++) {
			folderstr = folderstr + "[" + curselfolder[i].path + "] ";
		}
		for (j = 0; j < curselfile.length; j++) {
			filestr = filestr + "," + curselfile[j].path;
		}
		if(i+j == 0){
			alert("����ѡ��Ҫ�ϴ��Ķ���!");
			return false;
		}
		if(curremotecurdir == ftpbase){
			alert("��������������Ŀ¼�ϴ��ļ�!");
			return;
		}
		if(compareFile(area,curselfile) != false){
			is = confirm("�ļ�"+compareFile(area,curselfile)+"�Ѿ����ڣ��Ƿ�Ҫ���ǣ�");
			if(is){
				ok = confirm("�ļ�: " + filestr + "\n�ϴ���FTP������Ŀ¼\"" + curremotecurdir + "\"");
			}	
			if(ok){
				barobj.style.display = "block";
				document.FtpApplet.uploadBatch(filestr);
				barobj.style.display = "none";
				drawelement(curremotecurdir,"remotearea");
			}
		}else{
			ok = confirm("�ļ�: " + filestr + "\n�ϴ���FTP������Ŀ¼\"" + curremotecurdir + "\"");
			if(ok){
				barobj.style.display = "block";
				document.FtpApplet.uploadBatch(filestr);
				barobj.style.display = "none";
				drawelement(curremotecurdir,"remotearea");
			}
		}

	}else if(area =="remotearea"){
		var i,j;
		for (var i = 0; i < currselfolder.length; i++) {
			if(currselfolder[i].path != "." && currselfolder[i].path != ".."){
				folderstr = folderstr + "[" + currselfolder[i].path + "] ";
			}
		}
		for (var j = 0; j < currselfile.length; j++) {
			filestr = filestr + "," + currselfile[j].innerHTML;
		}
		if(i+j == 0 || dottrim(folderstr)){
			alert("����ѡ��Ҫ���صĶ���!");
			return false;
		}
		if(curcurfolder == "root"){
			alert("��������Ŀ¼�����ļ�!");
			return;
		}
		if(compareFile(area,currselfile) != false){
			is = confirm("�ļ�"+compareFile(area,currselfile)+"�Ѿ����ڣ��Ƿ�Ҫ���ǣ�");
			if(is){
				ok = confirm("�ļ�: " + filestr + "\n���ص�����Ŀ¼\"" + curcurfolder + "\"");
			}
			if(ok){
				barobj.style.display = "block";
				document.FtpApplet.downloadBatch(filestr,curcurfolder);
				barobj.style.display = "none";
				drawelement(curcurfolder,"localarea");
			}
		}else{
			ok = confirm("�ļ�: " + filestr + "\n���ص�����Ŀ¼\"" + curcurfolder + "\"");
			if(ok){
				barobj.style.display = "block";
				document.FtpApplet.downloadBatch(filestr,curcurfolder);
				barobj.style.display = "none";
				drawelement(curcurfolder,"localarea");
			}
		}
	}
}

/**
 * ���͵����ļ�
 * @param {Object} obj
 * @param {Object} area compareCurFile(area, file)
 */
function singletransfer(obj,area){
	var obj = document.getElementById(obj);
	var barobj = eval(area+".bar");
	var is,ok;
	if (area == "localarea") {
		if(curremotecurdir == ftpbase){
			alert("��������������Ŀ¼�ϴ��ļ�!");
			return;
		}
		if (compareCurFile(area,obj.innerHTML) == true) {
			is = confirm("�ļ�"+obj.innerHTML +"�Ѿ����ڣ��Ƿ�Ҫ���ǣ�");
			if (is) {
				ok = confirm("�ļ�: " + obj.innerHTML + "\n�ϴ���FTP������Ŀ¼\"" + curremotecurdir + "\"");
			}
			if (ok) {
				barobj.style.display = "block";
				document.FtpApplet.uploadFile(obj.path);
				barobj.style.display = "none";
				drawelement(curremotecurdir, "remotearea");
			}
		}else{
			ok = confirm("�ļ�: " + obj.innerHTML + "\n�ϴ���FTP������Ŀ¼\"" + curremotecurdir + "\"");
			if (ok) {
				barobj.style.display = "block";
				document.FtpApplet.uploadFile(obj.path);
				barobj.style.display = "none";
				drawelement(curremotecurdir, "remotearea");
			}
		}
		
	}else if(area =="remotearea"){
		if(curcurfolder == "root"){
			alert("��������Ŀ¼�����ļ�!");
			return;
		}
		if (compareCurFile(area, obj.innerHTML) == true) {
			is = confirm("�ļ�"+obj.innerHTML +"�Ѿ����ڣ��Ƿ�Ҫ���ǣ�");
			if(is){
				ok = confirm("�ļ�: " + obj.innerHTML  + "\n���ص�����Ŀ¼\"" + curcurfolder + "\"");	
			}
			if(ok){
				barobj.style.display = "block";
				document.FtpApplet.downloadBatch(obj.innerHTML,curcurfolder);
				barobj.style.display = "none";
				drawelement(curcurfolder,"localarea");
			}
		}else{
			ok = confirm("�ļ�: " + obj.innerHTML  + "\n���ص�����Ŀ¼\"" + curcurfolder + "\"");
			if(ok){
				barobj.style.display = "block";
				document.FtpApplet.downloadBatch(obj.innerHTML,curcurfolder);
				barobj.style.display = "none";
				drawelement(curcurfolder,"localarea");
			}
		}
	}
}



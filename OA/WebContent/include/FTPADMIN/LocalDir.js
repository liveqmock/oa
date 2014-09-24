/**
 * 用于本地文件资源访问
 * @author liuxy
 * @version 1.0
 */

/**************************系统变量********************************/

//从服务器读取路径
var basepath = "/oabase";

//ftp服务器根路径
var ftpbase = "";

//储存当前状态相关变量
//curfolder:当前在哪个文件夹下
//cursor:当前在指向任务队列的那项
//queuelength:当前任务队列的任务数
//curfoldername:当前文件夹名称
//var curset = {
//	curfolder : "root",
//	cursor : 0,
//	remotecurdir : "",
//	queuelength : 0,
//	rcursor :0,
//	rqueuelength:0,
//	curfoldername : "根目录",
//	selfolder : new Array(),
//	selfile : new Array(),
//	rselfolder : new Array(),
//	rselfile : new Array()
//};

	var curcurfolder = "root";
	var curcursor = 0;
	var curremotecurdir = "";
	var curqueuelength = 0;
	var currcursor = 0;
	var currqueuelength = 0;
	var curcurfoldername = "根目录";
	var curselfolder = new Array();
	var curselfile = new Array();
	var currselfolder = new Array();
	var currselfile = new Array();

//系统配置信息
//queuesize:任务队列大小
var config = {
	queuesize : 20,
	backicon : basepath + "/images/ftp_back.jpg",
	backdisableicon : basepath + "/images/ftp_back01.jpg",
	upicon : basepath + "/images/ftp_up.jpg",
	nexticon : basepath + "/images/ftp_next.jpg",
	nextdisableicon : basepath + "/images/ftp_next01.jpg",
	homeicon : basepath + "/images/ftp_home.jpg",
	diskicon : basepath + "/images/ftp_disk.jpg",
	foldericon : basepath + "/images/ftp_folder.jpg",
	fileicon : basepath + "/images/ftp_file.jpg",
	unselect : "unselect",
	select : "select"
}

//任务队列，储存操作步骤
var queue = new Array();
//远程任务队列
var rqueue = new Array();


/*****************************************************************/



/**
 * 文件夹对象类,type: 0,文件夹  1,文件  2,驱动器
 */
function felement(){
	this.type = "";
	this.icon = "";
	this.text = "";
	this.path = "";
	this.avaliable = true ;
}

/**
 * 创建文件系统
 */
function getfilesystem(){
	var fso = new ActiveXObject("Scripting.FileSystemObject"); 
	if(fso == null){
		alert("文件系统创建出错！");
	}else{
		return fso;
	}
}

/**
 * 返回驱动器列表
 */
function getdrives(){
	var n, drives; 
	var result = new Array(); 
	var i=0; 
	drives = new Enumerator(fso.Drives); 
	for (;!drives.atEnd();drives.moveNext()){ 
		var e = new felement();
		var x = drives.item();
		var s = ""
		s = s + x.DriveLetter;
		s = s + "_";
		if(x.DriveType == 3){
			n = x.ShareName;
		}else if(x.IsReady){
			n = x.VolumeName;
		}else{
			n = "[驱动器未就绪]";
			e.avaliable = false ;
		}
		e.icon = config.diskicon;
		e.text = s + n;
		e.path = x.DriveLetter+":\\";
		e.type = "2";
		result[i] = e;
		i = i + 1;
   }
	return result; 
} 



/**
 * 通过一个路径返回该路径下所有文件
 * @param {Object} path
 */
function getfilesbydir(path){
	var files, curfolder; 
	var result = new Array(); 
	var i=0; 
	curfolder = getfolderinfo(path);
	files = new Enumerator(curfolder.Files); 
	for (;!files.atEnd();files.moveNext()){ 
		var e = new felement();
		e.icon = config.fileicon;
		e.text = lastnamestr(files.item());
		e.path = files.item();
		e.type = "1";
		result[i] = e ;
		i = i + 1;
   }
	return result; 
}

/**
 * 通过一个路径返回该路径下所有文件夹
 * @param {Object} path
 */
function getfolderbydir(path){
	var folders, curfolder; 
	var result = new Array(); 
	var i=0; 
	curfolder = getfolderinfo(path);
	folders = new Enumerator(curfolder.SubFolders); 
	for (;!folders.atEnd();folders.moveNext()){ 
		var e = new felement();
		e.icon = config.foldericon;
		e.text = lastnamestr(folders.item());
		e.path = folders.item();
		e.type = "0";
		result[i] = e;
		i = i + 1;
   }
	return result; 
}

/**
 * 输入一段文件字符串，判断当前目录下有没有同名文件
 * @param {Object} filsstr
 */
function compareFile(area,files){
	var samefiles = "";
	if(area == "localarea"){
		var fileelement = remotefiles(curremotecurdir);
		for(var i=0;i<fileelement.length;i++){
			for(var j=0;j<files.length;j++){
				if(fileelement[i].text == files[j].innerHTML){
					samefiles = samefiles + "[" + fileelement[i].text + "] ";
				}
			}
		}
		if(samefiles != null){
			return samefiles;
		}else{
			return false;
		}
	}else if(area == "remotearea"){
		var fileelement = getfilesbydir(curcurfolder);
		for(var i=0;i<fileelement.length;i++){
			for(var j=0;j<files.length;j++){
				if(fileelement[i].text == files[j].innerHTML){
					samefiles = samefiles + "[" + fileelement[i].text + "] ";
				}
			}
		}
		if(samefiles != null){
			return samefiles;
		}else{
			return false;
		}
	}
}

/**
 * 判断当前选中的文件是否存在
 * @param {Object} area
 * @param {Object} files
 */
function compareCurFile(area, file){
	if(area == "localarea"){
		var fileelement = remotefiles(curremotecurdir);
		for(var i=0;i<fileelement.length;i++){
			if(fileelement[i].text == file){
				return true;
			}
		}
		return false;
	}else if(area == "remotearea"){
		var fileelement = getfilesbydir(curcurfolder);
		for(var i=0;i<fileelement.length;i++){
			if(fileelement[i].text == file){
				return true;
			}
		}
		return false;
	}
}
	

/**
 * 通过一个文件名，返回该文件的所有信息
 * @param {Object} filename
 */
function getfileinfo(filename){
	var fileinfo ="";
	try{
	 	fileinfo = fso.GetFile(filename);
		return fileinfo;
	}catch(e){
		return e+"#file not found!";
	}
}

/**
 * 通过一个文件夹名，返回该文件夹所有信息
 * @param {Object} foldername
 */
function getfolderinfo(foldername){
	var folderinfo = fso.GetFolder(foldername);
	if(folderinfo != null){
		return folderinfo;
	}else{
		return false; 
	}
}

/**
 * 通过一个驱动器名，返回该驱动器的信息
 * @param {Object} drivename
 */
function getdriveinfo(drivename){
	var driveinfo = fso.GetDrive(drivename);
	if(driveinfo != null){
		return driveinfo;
	}else{
		return false; 
	}
}

/**
 * 通过一个文件名创建文件
 * @param {Object} filename
 * @param {Object} code
 * code: 0,通过CreateTextFile创建文件; 1,通过OpenTextFile创建文件； 2,通过OpenAsTextStream创建文件。
 */
function createfile(filename,code){
	var newfile ="";
	var ForWriting = 2; 
	if(code == undefined){
		code = 0;
	}
	switch(code){
		case 0: newfile = fso.createtextfile(filename,true); break;
		case 1: newfile = fso.OpenTextFile(filename, ForWriting, true);break;
		case 2: fso.CreateTextFile (filename); 
				var temp = fso.GetFile(filename); 
				newfile = temp.OpenAsTextStream(ForWriting, true); 
				break;
		default: newfile = fso.createtextfile(filename,true); break;
	}
	if(newfile != ""){
		return newfile;
	}else{
		return "#"+filename+" Create fail!";
	}
}

/**
 * 通过一个文件夹的名字创建文件夹
 * @param {Object} foldername
 */
function createfolder(foldername){
	var newfolder = fso.CreateFolder (foldername); 
	if(newfolder !=null){
		return newfolder;
	}else{
		return false;
	}
}

/**
 * 输入源文件,目标文件,将源文件拷贝到目标文件
 * @param {Object} srcname
 * @param {Object} desname
 */
function filecopy(srcname,desname){
	if(srcname == undefined || desname == undefined){
		return "#请输入文件名！";
	}
	var srcfile = fso.GetFile(srcname);
	srcfile.Copy(desname);
	var desfile = fso.GetFile(desname);
	if(desfile != undefined){
		return desfile;
	}else{
		return "#文件拷贝失败！";
	}
}







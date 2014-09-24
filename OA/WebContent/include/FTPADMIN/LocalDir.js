/**
 * ���ڱ����ļ���Դ����
 * @author liuxy
 * @version 1.0
 */

/**************************ϵͳ����********************************/

//�ӷ�������ȡ·��
var basepath = "/oabase";

//ftp��������·��
var ftpbase = "";

//���浱ǰ״̬��ر���
//curfolder:��ǰ���ĸ��ļ�����
//cursor:��ǰ��ָ��������е�����
//queuelength:��ǰ������е�������
//curfoldername:��ǰ�ļ�������
//var curset = {
//	curfolder : "root",
//	cursor : 0,
//	remotecurdir : "",
//	queuelength : 0,
//	rcursor :0,
//	rqueuelength:0,
//	curfoldername : "��Ŀ¼",
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
	var curcurfoldername = "��Ŀ¼";
	var curselfolder = new Array();
	var curselfile = new Array();
	var currselfolder = new Array();
	var currselfile = new Array();

//ϵͳ������Ϣ
//queuesize:������д�С
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

//������У������������
var queue = new Array();
//Զ���������
var rqueue = new Array();


/*****************************************************************/



/**
 * �ļ��ж�����,type: 0,�ļ���  1,�ļ�  2,������
 */
function felement(){
	this.type = "";
	this.icon = "";
	this.text = "";
	this.path = "";
	this.avaliable = true ;
}

/**
 * �����ļ�ϵͳ
 */
function getfilesystem(){
	var fso = new ActiveXObject("Scripting.FileSystemObject"); 
	if(fso == null){
		alert("�ļ�ϵͳ��������");
	}else{
		return fso;
	}
}

/**
 * �����������б�
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
			n = "[������δ����]";
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
 * ͨ��һ��·�����ظ�·���������ļ�
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
 * ͨ��һ��·�����ظ�·���������ļ���
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
 * ����һ���ļ��ַ������жϵ�ǰĿ¼����û��ͬ���ļ�
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
 * �жϵ�ǰѡ�е��ļ��Ƿ����
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
 * ͨ��һ���ļ��������ظ��ļ���������Ϣ
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
 * ͨ��һ���ļ����������ظ��ļ���������Ϣ
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
 * ͨ��һ���������������ظ�����������Ϣ
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
 * ͨ��һ���ļ��������ļ�
 * @param {Object} filename
 * @param {Object} code
 * code: 0,ͨ��CreateTextFile�����ļ�; 1,ͨ��OpenTextFile�����ļ��� 2,ͨ��OpenAsTextStream�����ļ���
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
 * ͨ��һ���ļ��е����ִ����ļ���
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
 * ����Դ�ļ�,Ŀ���ļ�,��Դ�ļ�������Ŀ���ļ�
 * @param {Object} srcname
 * @param {Object} desname
 */
function filecopy(srcname,desname){
	if(srcname == undefined || desname == undefined){
		return "#�������ļ�����";
	}
	var srcfile = fso.GetFile(srcname);
	srcfile.Copy(desname);
	var desfile = fso.GetFile(desname);
	if(desfile != undefined){
		return desfile;
	}else{
		return "#�ļ�����ʧ�ܣ�";
	}
}







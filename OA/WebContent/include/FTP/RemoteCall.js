/**
 * ����Applet����������Զ������
 * @author lxy
 * @version 1.0
 */


/**
 * �Ͽ�FTP����
 */
function unloadftp(){
	document.FtpApplet.disconnectServer();
}

/**
 * ȡ��FTP������Ŀ¼�µ������ļ���
 * @param {Object} dir
 */
function remotefolders(dir){
	var folders = document.FtpApplet.getFolders(dir);
	if(dir == "/"){
		var f = strtoarr(folders,2);
	}else{
		var f = strtoarr(folders,0);
	}
	return f;
}

/**
 * ȡ��FTP������Ŀ¼�µ������ļ�
 * @param {Object} dir
 */
function remotefiles(dir){
	var files = document.FtpApplet.getFiles(dir);
	var f = strtoarr(files,1);
	return f;
}

/**
 * �жϵ�ǰ�������µĸ��ļ����Ƿ����
 * @param {Object} name
 */
function rfolderexist(name){
	var folders = remotefolders(curremotecurdir);
	for(var i=0;i<folders.length;i++){
		if(folders[i].text == name){
			return true;
		}
	}
	return false;
}


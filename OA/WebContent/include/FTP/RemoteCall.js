/**
 * 调用Applet方法，访问远程数据
 * @author lxy
 * @version 1.0
 */


/**
 * 断开FTP连接
 */
function unloadftp(){
	document.FtpApplet.disconnectServer();
}

/**
 * 取得FTP服务器目录下的所有文件夹
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
 * 取得FTP服务器目录下的所有文件
 * @param {Object} dir
 */
function remotefiles(dir){
	var files = document.FtpApplet.getFiles(dir);
	var f = strtoarr(files,1);
	return f;
}

/**
 * 判断当前工作区下的该文件夹是否存在
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


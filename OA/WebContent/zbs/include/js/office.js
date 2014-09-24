	var webWord;
/**********************以下是word对象************************************/

/**
 * 对word进行操作的对象,EBWebOffice 2003 控件
 * @param office EBWebOffice的对象
 * @param encode 编码/解码/处理文件的对象（控件）
 * @param documentCode 存放编码的域对象
 * @param fileName 文件名称
 * @param userName 用户名称
 */
function EBWebOffice(office,encode,documentCode,fileName,userName){
	this.base = Word;
	this.base(encode,documentCode,fileName,userName);
	this.office = office;
	this.office.Titlebar = false;
	/**
	 * 打开文档，如果没有文档则新建
	 * @param isNew 如果没有文档是否创建新文档
	 */
	this.openDoc = function(){
		//显示控件
		//得到文档的名字
		this.office.Open(this.nowFileName);
		//初始化word对象
		this.initObject(this.office.ActiveDocument);
		//设置用户信息
		this.setUser();
	}
	/**
	 * 打开本地文档
	 * @param localFileName 本地文件的完整路径
	 */
	this.openLocalDoc = function(localFileName){
		if (localFileName == null || localFileName == ""){
			alert("没有指定本地文件名!");
			return;
		}
		this.nowFileName = localFileName;
		//显示控件
		//得到文档的名字
		this.office.Open(localFileName);
		//初始化word对象
		this.initObject(this.office.ActiveDocument);
		//设置用户信息
		this.setUser();
	}
	/**
	 * 关闭文档
	 * 
	 */
	this.closeDoc = function(){
		//还原word用户信息
		this.resumeUser();
		//关闭控件
		this.office.Close();
		//隐藏控件
	}
	/**
	 * 新建文档
	 * 
	 */
	this.createDoc = function(){
		//打开控件，且打开新文件
		this.office.Open(this.fileName);
		//初始化word对象
		this.initObject(this.office.ActiveDocument);
		//设置用户信息
		this.setUser();
	}
}
/*****************************************************************/
/**
 * 对word进行操作的对象
 * @param encode 编码/解码/处理文件的对象（控件）
 * @param documentCode 存放编码的域对象
 * @param fileName 新建文件使用的基础文件
 * @param userName 用户名称
 */

function Word(encode,documentCode,fileName,userName){
	//ActiveDocument对象
	this.activeDocument ;
	//Application对象
	this.application ;
	//Documents对象
	this.documents ;
	//文件处理对象
	this.data = encode;
	//新建文件名字
	this.fileName = fileName;
	//现在正在使用的文件名字
	this.nowFileName = fileName;
	//用户名称
	this.userName = userName;
	//存放编码域的对象
	this.docCode = documentCode;
	//扩展名
	this.EXPAND = "doc";
	//word中原始用户简称
	this.userInitials;
	//word中原始用户全称
	this.wordUserName;

	/**
	 * 打开文档，如果没有文档则新建
	 * (未实现的方法,继承重写)
	 */
	this.openDoc = function(){
		//设置用户信息
		this.setUser();
	}
	/**
	 * 关闭文档
	 * (未实现的方法,继承重写)
	 */
	this.closeDoc = function(){
		//还原word用户信息
		this.resumeUser();
	}
	/**
	 * 新建文档
	 * (未实现的方法,继承重写)
	 */
	this.createDoc = function(){
		//设置用户信息
		this.setUser();
	}
	/**
	 * 重新初始word对象
	 * @param activeDocument word的ActiveDocument对象
	 */
	this.initObject = function(activeDocument){
		this.activeDocument = activeDocument;
		this.application = this.activeDocument.Application;
		this.documents = this.application.Documents;
	}
	/**
	 * 得到要打开的文档名字
	 */
	this.getDocName = function(){
		if (this.docCode.value ==""){
			this.nowFileName = "";
			return "";
		}
		//解码
		this.data.decodeValue(this.docCode.value);
		//改名
		this.data.changeFileName(this.EXPAND);
		//取得文件名
		this.nowFileName = this.data.decodefile;
		//返回文件名字
		return this.nowFileName;
	}
	/**
	 * 保存文档
	 */
	this.saveDoc = function(){
		this.unProtect();
		//找到一个新的文件名
		
		this.nowFileName = this.data.tempFileName;
		this.nowFileName = this.nowFileName.substring(0,this.nowFileName.length-3) + this.EXPAND;
		
		//另存为文件
		this.activeDocument.SaveAs(this.nowFileName,0);
		//关闭页面
		this.closeDoc();
		//指定文件名字
		this.data.filename = this.nowFileName;
		//编码
		this.data.encode();
		//放回编码的文件
		this.docCode.value = this.data.encodeString;
	}
	/**
	 * 设置用户信息
	 */
	this.setUser = function(){
		//保存word原来用户信息
		this.wordUserName = this.application.UserName;
		this.userInitials = this.application.UserInitials;
		//设置当前用户完整名称
		this.application.UserName = this.userName;
		//设置当前用户简称
		this.application.UserInitials = this.userName;
	}
	/**
	 * 还原用户信息
	 */
	this.resumeUser = function(){
		//判断为空的情况
		if (this.wordUserName == null){
			this.wordUserName = this.userInitials;
		}
		if (this.wordUserName != null){
			//设置用户完整名称
			this.application.UserName = this.wordUserName;
			if (this.userInitials == null){
				//设置当前用户简称
				this.application.UserInitials = this.wordUserName;
			}else{
				//设置当前用户简称
				this.application.UserInitials = this.userInitials;
			}
		}
	}
	/**
	 * 设置为浏览状态
	 */
	this.setBrowse= function(){
		this.unProtect();
		this.activeDocument.Protect(3,false,this.password);
	}
	/**
	 * 设置为编辑状态
	 */
	this.setEdit= function(){
		this.unProtect();
		this.displayRevie(true);
	}
	/**
	 * 设置为修订状态
	 * @param isRev 是否可修订
	 */
	this.setRevision = function(isRev){
		this.unProtect();
		if (isRev){
			//可以修订，可以接受
			this.showRevision(true);
		}else{
			//必须修订，不能接受
			this.activeDocument.Protect(0,false,this.password);
		}
	}
	/**
	 * 设置是否显示修订
	 * @param rev 是否显示修订
	 */
	this.showRevision = function(rev){
		this.activeDocument.TrackRevisions = rev;  //在屏幕上不显示修订
		this.activeDocument.PrintRevisions = rev;  //在打印中的文档中不显示修订
		this.activeDocument.ShowRevisions = rev;   //编辑时不标记修订 	
		this.displayRevie(rev);
		if (rev){
			this.activeDocument.ActiveWindow.View.ShowRevisionsAndComments = true;
			this.activeDocument.ActiveWindow.View.RevisionsView = 0;
		}
	}
	/**
	 * 解锁
	 */
	this.unProtect = function(){
		if (this.activeDocument.ProtectionType!=-1){
 				this.activeDocument.Unprotect(this.password);
		}
	}
	/**
	 * 显示/隐藏修订工具栏
	 */
	this.displayRevie = function(isDis){
		this.activeDocument.CommandBars("Reviewing").Enabled = isDis;
		this.activeDocument.CommandBars("Reviewing").Visible = isDis;
	}
	/**
	 * 接受所有修订
	 */
	this.allChangeInDoc = function(){
		this.application.WordBasic.AcceptAllChangesInDoc();
	}
	/**
	 * 是否已最终状态显示修订
	 * @param isHidd true 隐藏修订 false 显示修订
	 */
	this.hiddenRevie = function(isHidd){
		if (isHidd){
			this.activeDocument.ActiveWindow.View.ShowRevisionsAndComments = false;
			this.activeDocument.ActiveWindow.View.RevisionsView =0;
		}else{
			this.activeDocument.ActiveWindow.View.ShowRevisionsAndComments = true;
			this.activeDocument.ActiveWindow.View.RevisionsView =0;
		}
	}
	/**
	 *保护窗体，包括不能复制
	 */
	this.protectForm = function(){
		this.unProtect();
		this.activeDocument.Protect(2,false,this.password);
	}
	/**
	 * 产生随机密码
	 * @param len 密码的长度
	 */
	this.getPassWord = function(len){
		var passStr = "";
		for (var i=0;i<len;i++){
			var pass = Math.round(Math.random()*10);
			passStr +=pass;
		}
		return passStr;
	}
	this.password = this.getPassWord(10);
}
	function getWord() {
		try{
			return new ActiveXObject("Word.Application");    //启动word
		}
		catch (e) {
			alert("对不起，初始化Word对象失败！\n请确认您是否安装了Microsoft Word 97或更高版本；\n并且将本站点添加到可信站点列表。");
			return null;
		}
	}
	function getNowDate(){
		var d = new Date();
		var s = "";
		s += d.getFullYear() + "-";
		s += (d.getMonth() + 1) + "-";
		s += d.getDate() + "-";
		/*s += " ";
		s += d.getHours() + "点";
		s += d.getMinutes() + "分";
		s += d.getSeconds() + "秒";
		//s += d.getMilliseconds() + "毫秒";
		*/
		return s;
	}
	function findExecute(templetTitle,textValue,offH){
		if (offH.Selection.Find.Execute(getTempletTitle(templetTitle))){
			offH.Selection.Delete(1,1);
			inEditValue(offH,textValue);
			goBack(offH);
		}
	}
	function quitWord(offH){
		offH.Quit(0);
	}
	function goBack(offH){
		offH.Selection.GoTo (1,2,1,"1");
    	offH.Selection.GoTo (3,1,1,"");
	}
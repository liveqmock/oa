	var webWord;
/**********************������word����************************************/

/**
 * ��word���в����Ķ���,EBWebOffice 2003 �ؼ�
 * @param office EBWebOffice�Ķ���
 * @param encode ����/����/�����ļ��Ķ��󣨿ؼ���
 * @param documentCode ��ű���������
 * @param fileName �ļ�����
 * @param userName �û�����
 */
function EBWebOffice(office,encode,documentCode,fileName,userName){
	this.base = Word;
	this.base(encode,documentCode,fileName,userName);
	this.office = office;
	this.office.Titlebar = false;
	/**
	 * ���ĵ������û���ĵ����½�
	 * @param isNew ���û���ĵ��Ƿ񴴽����ĵ�
	 */
	this.openDoc = function(){
		//��ʾ�ؼ�
		//�õ��ĵ�������
		this.office.Open(this.nowFileName);
		//��ʼ��word����
		this.initObject(this.office.ActiveDocument);
		//�����û���Ϣ
		this.setUser();
	}
	/**
	 * �򿪱����ĵ�
	 * @param localFileName �����ļ�������·��
	 */
	this.openLocalDoc = function(localFileName){
		if (localFileName == null || localFileName == ""){
			alert("û��ָ�������ļ���!");
			return;
		}
		this.nowFileName = localFileName;
		//��ʾ�ؼ�
		//�õ��ĵ�������
		this.office.Open(localFileName);
		//��ʼ��word����
		this.initObject(this.office.ActiveDocument);
		//�����û���Ϣ
		this.setUser();
	}
	/**
	 * �ر��ĵ�
	 * 
	 */
	this.closeDoc = function(){
		//��ԭword�û���Ϣ
		this.resumeUser();
		//�رտؼ�
		this.office.Close();
		//���ؿؼ�
	}
	/**
	 * �½��ĵ�
	 * 
	 */
	this.createDoc = function(){
		//�򿪿ؼ����Ҵ����ļ�
		this.office.Open(this.fileName);
		//��ʼ��word����
		this.initObject(this.office.ActiveDocument);
		//�����û���Ϣ
		this.setUser();
	}
}
/*****************************************************************/
/**
 * ��word���в����Ķ���
 * @param encode ����/����/�����ļ��Ķ��󣨿ؼ���
 * @param documentCode ��ű���������
 * @param fileName �½��ļ�ʹ�õĻ����ļ�
 * @param userName �û�����
 */

function Word(encode,documentCode,fileName,userName){
	//ActiveDocument����
	this.activeDocument ;
	//Application����
	this.application ;
	//Documents����
	this.documents ;
	//�ļ��������
	this.data = encode;
	//�½��ļ�����
	this.fileName = fileName;
	//��������ʹ�õ��ļ�����
	this.nowFileName = fileName;
	//�û�����
	this.userName = userName;
	//��ű�����Ķ���
	this.docCode = documentCode;
	//��չ��
	this.EXPAND = "doc";
	//word��ԭʼ�û����
	this.userInitials;
	//word��ԭʼ�û�ȫ��
	this.wordUserName;

	/**
	 * ���ĵ������û���ĵ����½�
	 * (δʵ�ֵķ���,�̳���д)
	 */
	this.openDoc = function(){
		//�����û���Ϣ
		this.setUser();
	}
	/**
	 * �ر��ĵ�
	 * (δʵ�ֵķ���,�̳���д)
	 */
	this.closeDoc = function(){
		//��ԭword�û���Ϣ
		this.resumeUser();
	}
	/**
	 * �½��ĵ�
	 * (δʵ�ֵķ���,�̳���д)
	 */
	this.createDoc = function(){
		//�����û���Ϣ
		this.setUser();
	}
	/**
	 * ���³�ʼword����
	 * @param activeDocument word��ActiveDocument����
	 */
	this.initObject = function(activeDocument){
		this.activeDocument = activeDocument;
		this.application = this.activeDocument.Application;
		this.documents = this.application.Documents;
	}
	/**
	 * �õ�Ҫ�򿪵��ĵ�����
	 */
	this.getDocName = function(){
		if (this.docCode.value ==""){
			this.nowFileName = "";
			return "";
		}
		//����
		this.data.decodeValue(this.docCode.value);
		//����
		this.data.changeFileName(this.EXPAND);
		//ȡ���ļ���
		this.nowFileName = this.data.decodefile;
		//�����ļ�����
		return this.nowFileName;
	}
	/**
	 * �����ĵ�
	 */
	this.saveDoc = function(){
		this.unProtect();
		//�ҵ�һ���µ��ļ���
		
		this.nowFileName = this.data.tempFileName;
		this.nowFileName = this.nowFileName.substring(0,this.nowFileName.length-3) + this.EXPAND;
		
		//���Ϊ�ļ�
		this.activeDocument.SaveAs(this.nowFileName,0);
		//�ر�ҳ��
		this.closeDoc();
		//ָ���ļ�����
		this.data.filename = this.nowFileName;
		//����
		this.data.encode();
		//�Żر�����ļ�
		this.docCode.value = this.data.encodeString;
	}
	/**
	 * �����û���Ϣ
	 */
	this.setUser = function(){
		//����wordԭ���û���Ϣ
		this.wordUserName = this.application.UserName;
		this.userInitials = this.application.UserInitials;
		//���õ�ǰ�û���������
		this.application.UserName = this.userName;
		//���õ�ǰ�û����
		this.application.UserInitials = this.userName;
	}
	/**
	 * ��ԭ�û���Ϣ
	 */
	this.resumeUser = function(){
		//�ж�Ϊ�յ����
		if (this.wordUserName == null){
			this.wordUserName = this.userInitials;
		}
		if (this.wordUserName != null){
			//�����û���������
			this.application.UserName = this.wordUserName;
			if (this.userInitials == null){
				//���õ�ǰ�û����
				this.application.UserInitials = this.wordUserName;
			}else{
				//���õ�ǰ�û����
				this.application.UserInitials = this.userInitials;
			}
		}
	}
	/**
	 * ����Ϊ���״̬
	 */
	this.setBrowse= function(){
		this.unProtect();
		this.activeDocument.Protect(3,false,this.password);
	}
	/**
	 * ����Ϊ�༭״̬
	 */
	this.setEdit= function(){
		this.unProtect();
		this.displayRevie(true);
	}
	/**
	 * ����Ϊ�޶�״̬
	 * @param isRev �Ƿ���޶�
	 */
	this.setRevision = function(isRev){
		this.unProtect();
		if (isRev){
			//�����޶������Խ���
			this.showRevision(true);
		}else{
			//�����޶������ܽ���
			this.activeDocument.Protect(0,false,this.password);
		}
	}
	/**
	 * �����Ƿ���ʾ�޶�
	 * @param rev �Ƿ���ʾ�޶�
	 */
	this.showRevision = function(rev){
		this.activeDocument.TrackRevisions = rev;  //����Ļ�ϲ���ʾ�޶�
		this.activeDocument.PrintRevisions = rev;  //�ڴ�ӡ�е��ĵ��в���ʾ�޶�
		this.activeDocument.ShowRevisions = rev;   //�༭ʱ������޶� 	
		this.displayRevie(rev);
		if (rev){
			this.activeDocument.ActiveWindow.View.ShowRevisionsAndComments = true;
			this.activeDocument.ActiveWindow.View.RevisionsView = 0;
		}
	}
	/**
	 * ����
	 */
	this.unProtect = function(){
		if (this.activeDocument.ProtectionType!=-1){
 				this.activeDocument.Unprotect(this.password);
		}
	}
	/**
	 * ��ʾ/�����޶�������
	 */
	this.displayRevie = function(isDis){
		this.activeDocument.CommandBars("Reviewing").Enabled = isDis;
		this.activeDocument.CommandBars("Reviewing").Visible = isDis;
	}
	/**
	 * ���������޶�
	 */
	this.allChangeInDoc = function(){
		this.application.WordBasic.AcceptAllChangesInDoc();
	}
	/**
	 * �Ƿ�������״̬��ʾ�޶�
	 * @param isHidd true �����޶� false ��ʾ�޶�
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
	 *�������壬�������ܸ���
	 */
	this.protectForm = function(){
		this.unProtect();
		this.activeDocument.Protect(2,false,this.password);
	}
	/**
	 * �����������
	 * @param len ����ĳ���
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
			return new ActiveXObject("Word.Application");    //����word
		}
		catch (e) {
			alert("�Բ��𣬳�ʼ��Word����ʧ�ܣ�\n��ȷ�����Ƿ�װ��Microsoft Word 97����߰汾��\n���ҽ���վ����ӵ�����վ���б�");
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
		s += d.getHours() + "��";
		s += d.getMinutes() + "��";
		s += d.getSeconds() + "��";
		//s += d.getMilliseconds() + "����";
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
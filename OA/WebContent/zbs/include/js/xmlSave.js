//var folderURL = "D:/zjdemo/zjdemo/WebRoot/industrial/file/files";
<<<<<<< xmlSave.js
var baseURL = "http://172.16.20.60:8000/";
//var folderURL = "D:/workspace/gdworkspace/demo/WebContent/temp/files";
var folderURL = "F:/workspace/share_web/WebContent/attached/files";
=======
var baseURL = "http://127.0.0.1/";
//var folderURL = "D:/gddemo/temp/files";
var folderURL = "C:/Inetpub/wwwroot/gddemo/WebContent/temp/files_hugg";
>>>>>>> 1.4
//var folderURL = "\\\\192.9.101.67\\d$\\zjdemo\\zjdemo\\WebRoot\\industrial\\file\\files";
<<<<<<< xmlSave.js
//var folderHTTP ="http://127.0.0.1/gddemo/industrial/file/files";//0015E942AD63
//var folderHTTP ="ytlzb/gddemo/temp/files";
var folderHTTP ="172.16.20.60:8000/share/attached/files";
=======
//var folderHTTP ="http://127.0.0.1/industrial/file/files";//0015E942AD63
var folderHTTP ="127.0.0.1/gddemo/temp/files";
>>>>>>> 1.4
var fso = new ActiveXObject("Scripting.FileSystemObject");
var fileObject = null;
//�õ������ļ��ж���
function getFolders(){
	var f;
	f = fso.GetFolder(folderURL);
    return new Enumerator(f.SubFolders);
}
//��������
function loadMe(){
	var data = getOneData(getData());
	if (data == null){
		return;
	}
	var eles = document.getElementsByTagName("input");
	document.all("spid").value = getID();
	for (var i=0;i<eles.length;i++){
		if (eles[i].type != "button" && eles[i].name !=""){
			var v = data[eles[i].name];
			if (v != null && v != "")
				eles[i].value = v;
		}
	}
	eles = document.getElementsByTagName("textarea");
	for (var i=0;i<eles.length;i++){
		if (eles[i].name !=""){
			var v = data[eles[i].name];
			if (v != null && v != "")
				eles[i].value = v;
		}
	}
}
//���ز鿴������
function loadViewMe(){
	var data = getOneData(getData());
	if (data == null){
		return;
	}
	document.all("spid").value = getID();
	var eles = document.getElementsByTagName("span");
	for (var i=0;i<eles.length;i++){
		if (eles[i].id != ""){
			var v = data[eles[i].id];
			if (v != null && v != "")
				eles[i].innerText = v;
			else
				eles[i].innerHTML = "&nbsp;";
		}
	}
}
function getOneData(docs){
	var spid = getID();
	//�����ݴ����б�
	for(var i=0;i<docs.length;i++){
		if (docs[i]["name"] == spid){
			return docs[i]["value"];
		}
	}
	return null;
}
//�õ����м�¼
function getData(){
	try{
		var fc = getFolders();
		var docs = new Array();
		for (var k=0;!fc.atEnd(); fc.moveNext()){
			var fcc = fc.item();
			//�ļ���·��
			var fcPath = fcc.Path;
			//�ļ�������
			var fcName = fcc.Name;
			//alert("�ļ������ƣ�" + fcName + "\n�ļ���·����" + fcPath);
			var fs = new Enumerator(fso.GetFolder(fcPath).files);
			var doc = new Array();
			doc["name"] = fcName;
			var data = new Array();
			var tacheold = "";
			var Ins_Mingcheng = "";
			var Ins_XKZBH = "";
			for (; !fs.atEnd(); fs.moveNext()){
				
				//�õ��ļ�
				var fl = fs.item();
				var flPath = fl.Path;
				var flName = fl.Name;
				if(flName.substring(flName.length-3,flName.length)== "xml"){
					//��ʼ����
					var xmlDoc=new ActiveXObject("Microsoft.XMLDOM"); //����Document����
		    		xmlDoc.async="false"; //�����ĵ��첽����Ϊ��
		    		//xmlDoc.loadXML(str);
		    		 xmlDoc.load(flPath);
		    		 var root = xmlDoc.documentElement; //����rootΪ�ĵ��ĸ��ڵ�
		    		if (root != null){
			    		 var attus = root.childNodes.item(0).attributes;
			    		 for(var i=0;i<attus.length;i++){
			    		 	if (data[attus.item(i).name] == null || data[attus.item(i).name] == ""){
			    		 		data[attus.item(i).name] = attus.item(i).value;
			    		 	 }
			    		 	if (attus.item(i).name == "tache" && flName == "base.xml"){
			    		 		
			    		 		tacheold = attus.item(i).value;
			    		 	}
			    		 	if (attus.item(i).name == "Ins_Mingcheng" && flName == "base.xml"){
			    		 		Ins_Mingcheng = attus.item(i).value;
			    		 	}
			    		 	if (attus.item(i).name == "Ins_XKZBH" && flName == "base.xml"){
			    		 		Ins_XKZBH = attus.item(i).value;
			    		 	}
			    		 }
		    		 }
	    		 }
			}
	    	if (tacheold != ""){
	    	data["tache"] = tacheold;
	    	}
	    	if (Ins_Mingcheng != ""){
	    	data["Ins_Mingcheng"] = Ins_Mingcheng;
	    	}
	    	if (Ins_XKZBH != ""){
	    	data["Ins_XKZBH"] = Ins_XKZBH;
	    	}
			doc["value"] = data;
			docs[k] = doc;
			k++;		
		}
		return docs;
	}catch(e){
		return new Array();
	}
}
//�õ�ID
function getID(){
	var str = document.location.href;
	var n = str.indexOf("?id=");
	if (n>0){
		var reValue = str.substring(n+4,str.length);
		return reValue;
	}
	return "";
}
//�õ�Ҫ���������
function getValue(){
	var eles = document.getElementsByTagName("input");
	var xml = "<?xml version=\"1.0\" encoding=\"Unicode\"?>";
	xml += "<root><base";
	for (var i=0;i<eles.length;i++){
		if (eles[i].type != "button" && eles[i].name !=""){
		if (xml.indexOf(eles[i].name + "=")>0){
			alert("�����ظ��ֶΣ�" + eles[i].name);
		}
			xml += " " + eles[i].name + "=\"" + eles[i].value + "\"";
		}
	}
	eles = document.getElementsByTagName("textarea");
	for (var i=0;i<eles.length;i++){
		if (eles[i].type != "button" && eles[i].name !=""){
		if (xml.indexOf(eles[i].name + "=")>0){
			alert("�����ظ��ֶΣ�" + eles[i].name);
		}
			xml += " " + eles[i].name + "=\"" + eles[i].value + "\"";
		}
	}
	xml+="></base></root>";
	//alert(xml);
	return xml;
}
//�����ļ�
function saveFile(fileName,nextPage){
	var spid = getFolderName();
	//�����ļ���
	var fName = folderURL + "/" + spid;
	document.myEditForm.spid.value = spid;
	if (!fso.FolderExists(folderURL))
		fso.CreateFolder(folderURL);
	if (!fso.FolderExists(fName))
		fso.CreateFolder(fName);
	//���������Ϣ�ļ�
	var f1 = fso.CreateTextFile(fName + "/" + fileName, true,true);
	f1.Write(getValue());
	f1.Close();
	if (nextPage != null && nextPage!=""){
		document.location = nextPage;
	}
}
function updataFile(){
	folderURL = folderURL.substring(0,folderURL.lastIndexOf("/"));
	var fs = new Enumerator(fso.GetFolder(folderURL).files);
	var xml = "<?xml version=\"1.0\" encoding=\"Unicode\"?>";
	xml += "<root><base";
	for (; !fs.atEnd(); fs.moveNext()){
		
		//�õ��ļ�
		var fl = fs.item();
		var flPath = fl.Path;
		var flName = fl.Name;
		if(flName == "base.xml"){
			//��ʼ����
			var xmlDoc=new ActiveXObject("Microsoft.XMLDOM"); //����Document����
    		xmlDoc.async="false"; //�����ĵ��첽����Ϊ��
    		xmlDoc.load(flPath);
    		var root = xmlDoc.documentElement; //����rootΪ�ĵ��ĸ��ڵ�
    		if (root != null){
	    		 var attus = root.childNodes.item(0).attributes;
	    		 for(var i=0;i<attus.length;i++){
	    		 	if (attus.item(i).name == "Ins_XXDZ"){
	    		 		attus.item(i).value = document.all("Ins_XXDZ").value;
	    		 	}
	    		 	if (attus.item(i).name == "Ins_Mingcheng"){
	    		 		attus.item(i).value = document.all("Ins_Mingcheng").value;
	    		 	}
	    		 	xml += " " + attus.item(i).name + "=\"" + attus.item(i).value + "\"";
	    		 }
    		 }
		 }
	}
	xml+="></base></root>";
	if (!fso.FolderExists(folderURL))
		fso.CreateFolder(folderURL);
	//���������Ϣ�ļ�
	var f1 = fso.CreateTextFile(folderURL + "/" + "base.xml", true,true);
	f1.Write(xml);
	f1.Close();
}
function upTache(tacheValue,spid){
	var myFolderURL = folderURL + "/" + spid
	var fs = new Enumerator(fso.GetFolder(myFolderURL).files);
	var xml = "<?xml version=\"1.0\" encoding=\"Unicode\"?>";
	xml += "<root><base";
	for (; !fs.atEnd(); fs.moveNext()){
		
		//�õ��ļ�
		var fl = fs.item();
		var flPath = fl.Path;
		var flName = fl.Name;
		if(flName == "base.xml"){
			//��ʼ����
			var xmlDoc=new ActiveXObject("Microsoft.XMLDOM"); //����Document����
    		xmlDoc.async="false"; //�����ĵ��첽����Ϊ��
    		xmlDoc.load(flPath);
    		var root = xmlDoc.documentElement; //����rootΪ�ĵ��ĸ��ڵ�
    		if (root != null){
	    		 var attus = root.childNodes.item(0).attributes;
	    		 for(var i=0;i<attus.length;i++){
	    		 	if (attus.item(i).name == "tache"){
	    		 		attus.item(i).value = tacheValue;
	    		 	}
	    		 	xml += " " + attus.item(i).name + "=\"" + attus.item(i).value + "\"";
	    		 }
    		 }
		 }
	}
	xml+="></base></root>";
	if (!fso.FolderExists(myFolderURL))
		fso.CreateFolder(myFolderURL);
	//���������Ϣ�ļ�
	var f1 = fso.CreateTextFile(myFolderURL + "/" + "base.xml", true,true);
	f1.Write(xml);
	f1.Close();
}
function updataFileBl(){
	folderURL = folderURL.substring(0,folderURL.lastIndexOf("/"));
	var fs = new Enumerator(fso.GetFolder(folderURL).files);
	var xml = "<?xml version=\"1.0\" encoding=\"Unicode\"?>";
	xml += "<root><base";
	for (; !fs.atEnd(); fs.moveNext()){
		
		//�õ��ļ�
		var fl = fs.item();
		var flPath = fl.Path;
		var flName = fl.Name;
		var bb = true;
		if(flName == "base.xml"){
			//��ʼ����
			var xmlDoc=new ActiveXObject("Microsoft.XMLDOM"); //����Document����
    		xmlDoc.async="false"; //�����ĵ��첽����Ϊ��
    		xmlDoc.load(flPath);
    		var root = xmlDoc.documentElement; //����rootΪ�ĵ��ĸ��ڵ�
    		if (root != null){
	    		 var attus = root.childNodes.item(0).attributes;
	    		 for(var i=0;i<attus.length;i++){
	    		 	if (attus.item(i).name == "Ins_XKZBH"){
	    		 		bb = false;
	    		 		attus.item(i).value = document.all("Ins_BL_NXHZBH").value;
	    		 	}
	    		 	xml += " " + attus.item(i).name + "=\"" + attus.item(i).value + "\"";
	    		 }
    		 }
		 }
	}
	if (bb)
		xml += " Ins_XKZBH=\"" + document.all("Ins_BL_NXHZBH").value + "\"";
	xml+="></base></root>";
	if (!fso.FolderExists(folderURL))
		fso.CreateFolder(folderURL);
	//���������Ϣ�ļ�
	var f1 = fso.CreateTextFile(folderURL + "/" + "base.xml", true,true);
	f1.Write(xml);
	f1.Close();
}
//�õ��ϴ�������Ϣ
function loadUpData(){
	var datas = getUpData();
	if (datas != null){
		var eles = document.getElementsByTagName("div");
		for(var i=0;i<datas.length;i++){
			for(var j=0;j<eles.length;j++){
				if(datas[i]["spanid"] == eles[j].id){
					var iData = datas[i]["spanValue"];
					var strs = "";
					for(var k=0;k<iData.length;k++){
						if (k>0){
							strs += "<br>";
							strs +=createFileSpan(iData[k].Path,iData[k].Name);
						}else{
							strs =createFileSpan(iData[k].Path,iData[k].Name);
						}
					}
					eles[j].innerHTML = strs;
				}
			}
		}
	}
}
//�õ������ϴ��ļ�
function getUpData(){
	var spid = document.all("spid").value;
	if (spid == ""){
		return null;
	}
	try{
		var fc = new Enumerator(fso.GetFolder(folderURL + "/" + spid + "/upload").SubFolders);
		var fa = new Array();
		for (var i=0; !fc.atEnd(); fc.moveNext(),i++){

			var fcc = fc.item();

			//�ļ���·��
			var fcPath = fcc.Path;

			//�ļ�������(�������)
			var fcName = fcc.Name;

			var fs = new Enumerator(fso.GetFolder(fcPath).files);

			var fccup = new Array();
			fccup["spanid"] = fcName;
			var faas = new Array();
			for (var mm = 0 ; !fs.atEnd(); fs.moveNext(),mm++){
				var fl = fs.item();
				faas[mm] = fl;
		    }
		    fccup["spanValue"] = faas;
		    fa[i] =fccup;
		}
	}catch(e){}
	return fa;
}
//?????????
var req;

function saveAttachedFile(filePath){
  
  if (window.XMLHttpRequest) {

      req = new XMLHttpRequest();

  }else if (window.ActiveXObject) {

      req = new ActiveXObject("Microsoft.XMLHTTP");

  }

  if(req){
      
      
      
      if (!fso.FolderExists(folderURL)){
		fso.CreateFolder(folderURL);
	}
	var fPathName = folderURL + "/upload/" + fileObject.id;     	  
	 
	 var fileName = "";
	 var upPath = "";
	 if (filePath != ""){
	  
		var sf = fso.GetFile(filePath);
		fileName = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length);
		
		upPath = fPathName + "/" + fileName;
		
	 	sf.Copy(upPath);
	  }
	 
      var url="/share/attached/AttatchedServlet";
      req.open("POST", url, true);
	  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");   	  
	  req.send("name=" + fileName+"&url=" + upPath);
	  

  }	
}
//�ϴ��ļ�
function saveUpFile(filePath){
	var spid = document.all("spid").value ;
	if (!fso.FolderExists(folderURL)){
		fso.CreateFolder(folderURL);
	}
	var fPathName = folderURL + "/" + spid + "/upload/" + fileObject.id;
	var upPath = "";
	var fileName = "";
	if (!fso.FolderExists(folderURL + "/" + spid + "/upload")){
		fso.CreateFolder(folderURL + "/" + spid + "/upload");
	}
	if (!fso.FolderExists(fPathName)){
		fso.CreateFolder(fPathName);
	}
	if (filePath != ""){
		var sf = fso.GetFile(filePath);
		fileName = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length);
		upPath = fPathName + "/" + fileName;
		sf.Copy(upPath);
	}
	if (upPath != ""){
		if (fileObject.innerHTML != ""){
			fileObject.innerHTML += "<br>";
			fileObject.innerHTML += createFileSpan(upPath,fileName);
		}else{
			fileObject.innerHTML = createFileSpan(upPath,fileName);
		}
	}
}
//����һ��������ʾ��span
function createFileSpan(filePath,fileName){
	//style="text-align: right"
	return "<span sel='false' fileValue='" + filePath + "' title='˫���鿴�ļ�����' style='cursor:hand;width:100%;background-color: #FFFFFF' onclick='selectMe(this)' ondblclick='openMe(this)' onMouseOver='_mouseOverFile(this)' onMouseOut='_mouseOutFile(this)'>" + fileName + "</span>";
}

//ѡ���ļ�
function selectMe(obj){
	if (obj.getAttribute("sel")=="false"){
		obj.setAttribute("sel","true");
		tempFileColor="#FFCCCC";
	}else{
		obj.setAttribute("sel","false");
		tempFileColor="#FFFFFF";
	}
}
//���Ӹ���
function addFile(obj){   
    alert(obj); 
	fileObject = obj;
	alert(fileObject);
	document.myEditForm.filePath.click();
}

//ɾ������
function removeFile(obj){
	var nodes = obj.childNodes;
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].nodeName.toLowerCase()=="span"){
			if(nodes[i].getAttribute("sel")=="true"){
				//ɾ���ļ�
				fso.DeleteFile(nodes[i].getAttribute("fileValue"));
				try{
					if (i==0 && nodes.length>1){
						nodes[i+1].style.display="none";
					}else{
						nodes[i-1].style.display="none";
					}
				}catch(e){}
				nodes[i].setAttribute("sel","false");
				nodes[i].style.display="none";
			}
		}
	}
}

//���Լ�
function openMe(obj){
	var fullFile = obj.getAttribute("fileValue");
	var sizedian = fullFile.lastIndexOf(".");
	var fileType = "unknown";
	if (sizedian>=0 && fullFile.length>sizedian){
		fileType = fullFile.substring(sizedian + 1,fullFile.length);
	}
	fullFile = "http://" + folderHTTP + fullFile.substring(folderURL.length,fullFile.length);
	if (fileType.toLowerCase() == "doc" || fileType.toLowerCase() == "dot") {
		openWord(fullFile);
	}
	else if(fileType.toLowerCase() == "xls") {
		openExcel(fullFile);
	}
	else {
		window.open(fullFile,"newWindow", "height=" + (window.screen.height-10) + ", width=" + (window.screen.width-10) + ", toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no,left=0,top=0");
	}
	obj.setAttribute("sel","false");
	tempFileColor="#FFFFFF";
}
function openWord(fullFile){
	var app = null;
	try{
		app = new ActiveXObject("Word.Application");    //����word
	}catch(e){
		window.open(fullFile,"newWindow", "height=" + (window.screen.height-10) + ", width=" + (window.screen.width-10) + ", toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no,left=0,top=0");
	}
	app.Documents.Open(fullFile);
	app.visible = true;
}
function openExcel(fullFile){
	var app = null;
	try{
		app = new ActiveXObject("Excel.Application");    //����Excel
	}catch(e){
		window.open(fullFile,"newWindow", "height=" + (window.screen.height-10) + ", width=" + (window.screen.width-10) + ", toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no,left=0,top=0");
	}
	var book = app.Workbooks.Open(fullFile);
	book.Application.Visible = true;
}
//�õ����ݵ�ID
function getFolderName(){
	if (document.all("spid").value != ""){
		return document.all("spid").value;
	} 
	  var today = new Date();
      var year = today.getFullYear();
      var month = today.getMonth()+1;
      if (month<10)
      	month = "0" + month;
      var mday = today.getDate();
      if (mday<10)
      	mday = "0" + mday;
      var mThisDateHour = today.getHours();
      if (mThisDateHour<10)
      	mThisDateHour = "0" + mThisDateHour;
  	  var mThisDateMinute = today.getMinutes();
  	  if (mThisDateMinute<10)
      	mThisDateMinute = "0" + mThisDateMinute;
      var mThisDateSeconds = today.getSeconds();
  	  if (mThisDateSeconds<10)
      	mThisDateSeconds = "0" + mThisDateSeconds;
      try{
		document.all("createDate").value = year + "-" + month + "-" + mday + " " + mThisDateHour + ":" + mThisDateMinute + ":" + mThisDateSeconds;
      }catch(e){
      }
      try{
		document.all("dxDate").value = year + "-" + month + "-" + mday + " " + mThisDateHour + ":" + mThisDateMinute + ":" + mThisDateSeconds;
      }catch(e){
      }
      return "" + year + month + mday + mThisDateHour + mThisDateMinute + mThisDateSeconds + "A" + today.getMilliseconds();
}

function getDemoData(){
	var demoStr = "<?xml version=\"1.0\" encoding=\"Unicode\"?><root><base spid=\"\" Ins_Daima=\"282285988\" Ins_Mingcheng=\"��ɽ�����մ����޹�˾\" Ins_Leibie=\"�����մ���\" Ins_LXR=\"�ս���\" Ins_YB=\"528031\" Ins_DH=\"075782282282\" Ins_XXDZ=\"�㶫ʡ��ɽ�к�ƽ·230��\" Ins_SQRQ=\"2006-6-8\" Ins_FRDB=\"�ս���\" Ins_ZGBM=\"��ɽ�����������ල��\" Ins_YYZZ=\"51010110215\" Ins_JJXZ=\"˽��\" Ins_QYZRS=\"300\" Ins_JSRYS=\"52\" Ins_JCSJ=\"1998-12-01\" Ins_ZDMJ=\"70000\" Ins_JZMJ=\"35000\" Ins_GDZC=\"500\" Ins_LDZJ=\"300\" Ins_NZCZ=\"12000\" Ins_NXSE=\"5000\" Ins_NJSJE=\"1000\" Ins_NLR=\"2800\" Ins_ZDCPMC=\"����װ�δ�ש����ԡ\" Ins_CPMC=\"����������Ͱ\" Ins_GGXH=\"JR20062201\" Ins_CPJDHXS=\"\" Ins_JYDW=\"��ɽ�����������ල�������\" Ins_XMZTZ=\"1200��Ԫ\" Ins_ZCSB=\"����\" Ins_PLTCSJ=\"2006-3-15\" Ins_SBZCH=\"55846621\" Ins_NSJNL=\"\" Ins_NCL=\"800\" Ins_NCZ=\"100\" Ins_NXSE1=\"100\" Ins_NJSE=\"20\" Ins_NLR1=\"60\" createDate=\"\" tache=\"\" uses=\"|admin\" filePath=\"\"></base></root>";
	var xmlDoc = new ActiveXObject("Microsoft.XMLDOM"); //����Document����
	xmlDoc.async="false"; //�����ĵ��첽����Ϊ��
	xmlDoc.loadXML(demoStr);
	//xmlDoc.load(flPath);
	var root = xmlDoc.documentElement; //����rootΪ�ĵ��ĸ��ڵ�
	if (root != null){
		var attus = root.childNodes.item(0).attributes;
		for(var i=0;i<attus.length;i++){
			try{
				document.all(attus.item(i).name).value = attus.item(i).value;
			}catch(e){}
		}
	}
}
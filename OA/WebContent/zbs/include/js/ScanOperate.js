var url = "/Scan/servlet/CcQyInfoServlet";
var pdfurl = "/Scan/servlet/PDFOprationServlet";
var ymsz = new Array();
var ymzdsz = new Array();

var ccidName,qyidName,phName,sidName,cpmcName,qyMC;

//�õ�ҳ�洫�������ֶ�����
function getArray(){

	ccidName = ymzdsz["CCID"];//�����ҵID
	qyidName = ymzdsz["QYID"];//��ҵID
	phName = ymzdsz["PH"];//����
	cpmcName = ymzdsz["CPMC"];//��Ʒ����
	qyMC=ymzdsz["MC"];//��ҵ����
	
}
//�ж���ɨ���¼

function judge(smid,czfs){
	var ccid = document.getElementById('CCID').value;
	var pdfNewurl = pdfurl+"?smid=" + escape(smid)+'&czfs='+escape(czfs);
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req){
		req.open("GET", pdfNewurl, true);
		req.onreadystatechange = callback;
		req.send(null);
	}
	
}

//�ж��ϴ�PDF��ť����
function judgePDF(smid,czfs){
	var ccid = document.getElementById('CCID').value;
	var pdfNewurl = pdfurl+"?smid=" + escape(smid)+'&czfs='+escape(czfs);
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req){
		req.open("GET", pdfNewurl, true);
		req.onreadystatechange = callbackPdf;
		req.send(null);
	}
	
}
function callbackPdf(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    document.getElementById("msg").style.visibility="hidden";
			parseMessagePdf();
		}else{
			
		}
	}else{
	    document.getElementById("msg").style.visibility="visible";
		
	}
}

function parseMessagePdf(){
	var xmlDoc = req.responseXML.documentElement;
	var node = xmlDoc.getElementsByTagName('info');
	var info =node[0].firstChild.nodeValue;
	if (ty=="1"){
		info = "0";
	}
	if(info=="1"){
		//window.
		if(confirm('�ñ����Ѿ�����,ȷ������(ɨ��)�ϴ���?')){
			var ccid = document.getElementById('CCID').value;
			judgePDF(ccid,"1");
			document.getElementById("smck").style.display="none";
	        document.getElementById("scfj").style.display = "";
	        
		}else{
			document.getElementById("scan").style.display="none";
	        document.getElementById("uploadpdf").style.display = "none";
	        document.getElementById("scfj").style.display = "none";
		}
	}else if(info =="0"){
		document.getElementById("smck").style.display="none";
	    document.getElementById("scfj").style.display = "";
	}
}

function callback(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    document.getElementById("msg").style.visibility="hidden";
			parseMessage();
		}else{
			
		}
	}else{
	    document.getElementById("msg").style.visibility="visible";
		
	}
}

function parseMessage(){
	var xmlDoc = req.responseXML.documentElement;
	var node = xmlDoc.getElementsByTagName('info');
	var info =node[0].firstChild.nodeValue;
	if (ty=="1"){
		info = "0";
	}
	if(info=="1"){
		//window.
		if(confirm('�ü�¼�Ѿ�ɨ���,ȷ������ɨ����?')){
			var ccid = document.getElementById('CCID').value;
			judge(ccid,"1");
			document.getElementById("smck").style.display="";
	        document.getElementById("scfj").style.display = "none";
			
	        
		}else{
			document.getElementById("scan").style.display="none";
	        document.getElementById("uploadpdf").style.display = "none";
		}
	}else if(info =="0"){
		document.getElementById("smck").style.display="";
	    document.getElementById("scfj").style.display = "none";
		document.getElementById("summsg").style.display = "";
	}
	
}

//���س����ҵ��Ϣ
function loadCcQy(qyzd,qybz){
	if(qybz=="0"){
		if(qyzd.length<6 ){
			alert("�������������ŵ�ǰ��λ��Ȼ��س�");
		}else{
			getArray();
			var qUrl = url+'?qyzd='+qyzd+'&bz='+qybz;
			loadEntInfo(qUrl,GetAllCcQyCallBack);
		}
		
	}else{
		getArray();
		var qUrl = url+'?qyzd='+qyzd+'&bz='+qybz;
		loadEntInfo(qUrl,GetAllCcQyCallBack);
	}
    
}

function loadEntInfo(sUrl,callBackFunction){

    //AttXml ������ajax����Servlet��������xml�ļ�
	var oXML = new AttXml() ;
	//alert("loadEntInfo");
	if ( callBackFunction )
		oXML.LoadUrl( sUrl, callBackFunction ) ;	// Asynchronous load.
	else
		return oXML.LoadUrl( sUrl ) ;

}

//���������ļ���Ϣ��xml
function GetAllCcQyCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("û���ҵ�������������ҵ������Ϣ");
	  if(ccidName!="") {
		 document.getElementsByName(ccidName)[0].value='';
	  }
	  if(qyidName!="") {
	  	document.getElementsByName(qyidName)[0].value='';
	  }
	   if(phName!="") {
	  	document.getElementsByName(phName)[0].value='';
	  }
	   if(cpmcName!="") {
	  	document.getElementsByName(cpmcName)[0].value='';
	  }
	
	}else if(size == 1){
	   //alert("�ҵ�һ������������ҵ������Ϣ��ֱ�Ӵ�ҳ����");
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	   for(var i=0; i<oNodes.length; i++){
	     
			var oNode = oNodes[i] ;
			var CCID = oNode.attributes.getNamedItem('CCID').value ; 
			var QYID = oNode.attributes.getNamedItem('QYID').value ; 
			var PH = oNode.attributes.getNamedItem('PH').value ; 
			var SID = oNode.attributes.getNamedItem('SID').value ; 
			var CPMC = oNode.attributes.getNamedItem('CPMC').value ; 
			var QYMC = oNode.attributes.getNamedItem('MC').value ; 	
			
			if(ccidName!="") {
		 		document.getElementsByName(ccidName)[0].value=CCID;
	  		}
	  		if(qyidName!="") {
	  			document.getElementsByName(qyidName)[0].value=QYID;
	  		}
	   		if(phName!="") {
	  			document.getElementsByName(phName)[0].value=PH;
	  		}
	   		if(cpmcName!="") {
	  			document.getElementsByName(cpmcName)[0].value=CPMC;
	  		}
	  		if(qyMC!="") {
	  			document.getElementsByName(qyMC)[0].value=QYMC;
	  		}
	   
	   }
	   
	   document.getElementById("scan").style.display = "";	
	   document.getElementById("uploadpdf").style.display = "";
	
	}else{
	
	  // alert("�ҵ�����������һ����ҳ������ʾ");
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	  
	  //alert(qydmname);
	   openwin(oNodes);
	   document.getElementById("scan").style.display = "";	
	   document.getElementById("uploadpdf").style.display = "";
	   
	}
}

function openwin(oNodes) 
{
	var times = new Date().getTime();
	var reValue = window.showModalDialog("select.html?d=" + times,oNodes,"dialogWidth=400px;dialogHeight=400px");
	if (reValue != null){
	    //alert(document.getElementsByName.value);
	    //document.getElementsByName.value='';
		//alert(reValue["QYMC"]);
		    //alert(reValue("CCID"));
			if(ccidName!="") {
		 		document.getElementsByName(ccidName)[0].value=reValue["CCID"];
	  		}
	  		if(qyidName!="") {
	  			document.getElementsByName(qyidName)[0].value=reValue["QYID"];
	  		}
	   		if(phName!="") {
	  			document.getElementsByName(phName)[0].value=reValue["PH"];
	  		}
	   		if(cpmcName!="") {
	  			document.getElementsByName(cpmcName)[0].value=reValue["CPMC"];
	  		}
	  		if(qyMC!="") {
	  			document.getElementsByName(qyMC)[0].value=reValue["MC"];;
	  		}
		
	}

} 
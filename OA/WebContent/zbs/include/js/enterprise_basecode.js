var url = "/share/enterprise/EntBaseInfoServlet";
var qurl = "/share/enterprise/EntBaseQyLxServlet";
var producturl = "/share/enterprise/EntBaseProInfoServlet";
var dataValues = new Array();
var qydmname=new Array();
var ymsz = new Array();
var ymzdsz = new Array();
var oNode,qymcName,qydmName,jjlxName,sshyName,txdzName,yzbmName,fddbrName,qylxdhName;
var zdcpmcName,yyzzbhName,qycyrsName,qyflName,tzjgName,xzqhName,jdName,wdName,ztName,jccplbName,shengName,shiName,xianName;


//�õ�ҳ�洫�������ֶ�����
function getArray(){
	
	qyidName = ymzdsz["QYID"];//��ҵID����
	qymcName = ymzdsz["QYMC"];//��ҵ����
	qydmName = ymzdsz["QYDM"];//��ҵ����
	jjlxName = ymzdsz["JJLX"];//��������
	sshyName = ymzdsz["SSHY"] ; //������ҵ
	txdzName = ymzdsz["TXDZ"]; //ͨ�ŵ�ַ
	yzbmName = ymzdsz["YZBM"];//��������
	fddbrName = ymzdsz["FDDBR"];//����������
	qylxdhName = ymzdsz["QYLXDH"];//��ҵ��ϵ�绰
	zdcpmcName = ymzdsz["ZDCPMC"];//������Ʒ����
	yyzzbhName = ymzdsz["YYZZBH"];//Ӫҵִ�ձ��
	qycyrsName = ymzdsz["QYCYRS"];//��λ��ҵ����
	qyflName = ymzdsz["QYFL"];//��ҵ����
	tzjgName = ymzdsz["TZJG"];//�ⷽͶ�ʻ�����������
	xzqhName = ymzdsz["XZQH"];//��������
	jdName = ymzdsz["JD"];//����
	wdName = ymzdsz["WD"];//γ��
	ztName = ymzdsz["ZT"];//״̬
	shengName = ymzdsz["SHENG"];//ʡ
	shiName = ymzdsz["SHI"];//��
	xianName = ymzdsz["XIAN"];//��
	jccplbName = ymzdsz["JCCPLB"];//��Ʒ���ID
	jccplbNameValue = ymzdsz["JCCPLBVALUE"];//��Ʒ���ID
	cpmcName = ymzdsz["CPMC"];//��Ʒ����
	cpidName = ymzdsz["CPID"];//��Ʒ���ID

	
}
//�ж��Ƿ�Ϊ��
function ifNull(_ymValue){
	if(_ymValue==null){
		return true;
	}
	if(_ymValue==""){
		return true;
	}
	
	return false;
}
function loadInfo(value,bz,iftb){
  // alert(url);
  if(iftb==null){
  	iftb=true;
  }
   getArray();
   var qydm = value;
   
   var sUrl = url+'?qydm='+qydm+'&bz='+bz;
  // alert(sUrl);
   SetValue();
   loadEntInfo(sUrl,GetAllFieldsCallBack,iftb);
}

function loadCplb(qyid){

	var qUrl = qurl+'?qyid='+qyid;
	loadEntInfo(qUrl,GetAllQyLbCallBack,true);
}


function loadEntInfo(sUrl,callBackFunction,iftb){

    //AttXml ������ajax����Servlet��������xml�ļ�
	var oXML = new AttXml() ;
	//alert("loadEntInfo");
	if ( callBackFunction )
		oXML.LoadUrl( sUrl, callBackFunction,iftb ) ;	// Asynchronous load.
	else
		return oXML.LoadUrl( sUrl ) ;

}

function SetValue(){
  var tagNames = document.getElementsByTagName("input");
  var tmp = '';
  for(var i = 0; i<tagNames.length; i++){
  
  		if(tagNames.type="text"){
  		  
  		  tmp=tagNames[i].name;
  		  if(tmp.substring(tmp.lastIndexOf(".")+1)=="QYDM"){
  		  	qydmname["QYDM"]=tmp;
  		  }else if(tmp.substring(tmp.lastIndexOf(".")+1)=="QYID"){
  		    qydmname["QYID"]=tmp;
  		  }
  		  
  		}
  		
  }
}
	
//���������ļ���Ϣ��xml
function GetAllFieldsCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("û���ҵ�������������ҵ������Ϣ");
	
	}else if(size == 1){
	   //alert("�ҵ�һ������������ҵ������Ϣ��ֱ�Ӵ�ҳ����");
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	   for(var i=0; i<oNodes.length; i++){
	     
			var oNode = oNodes[i] ;
			var QYID = oNode.attributes.getNamedItem('QYID').value ; 
			var QYMC = oNode.attributes.getNamedItem('QYMC').value ; 
			var QYDM = oNode.attributes.getNamedItem('QYDM').value ; 
			var JJLX = oNode.attributes.getNamedItem('JJLX').value ; 
			var SSHY = oNode.attributes.getNamedItem('SSHY').value ; 
			var TXDZ = oNode.attributes.getNamedItem('TXDZ').value ; 
			var YZBM = oNode.attributes.getNamedItem('YZBM').value ; 
			var FDDBR = oNode.attributes.getNamedItem('FDDBR').value ; 
			var QYLXDH = oNode.attributes.getNamedItem('QYLXDH').value ; 
			var ZDCPMC = oNode.attributes.getNamedItem('ZDCPMC').value ; 
			var YYZZBH = oNode.attributes.getNamedItem('YYZZBH').value ; 
			var QYCYRS = oNode.attributes.getNamedItem('QYCYRS').value ;
			var QYFL = oNode.attributes.getNamedItem('QYFL').value ;
			var TZJG = oNode.attributes.getNamedItem('TZJG').value ;
			var XZQH = oNode.attributes.getNamedItem('XZQH').value ;
			var JD = oNode.attributes.getNamedItem('JD').value ;
			var WD = oNode.attributes.getNamedItem('WD').value ;
			var ZT = oNode.attributes.getNamedItem('ZT').value ;
			var SHENG = oNode.attributes.getNamedItem('SHENG').value ;
			var SHI = oNode.attributes.getNamedItem('SHI').value ;
			var XIAN = oNode.attributes.getNamedItem('XIAN').value ;
			//alert("QYID="+QYID+",QYMC="+QYMC+",QYDM="+QYDM+",JJLX="+JJLX+",SSHY="+SSHY);
			clearAll();
			if(!ifNull(jccplbName)){
				loadCplb(QYID);
			}
				
			if(!ifNull(qyidName)){			
				document.getElementsByName(qyidName)[0].value = QYID;

			}
			
			//alert("qyid");
			if(!ifNull(qymcName)){
				document.getElementsByName(qymcName)[0].value = QYMC;
			}
			
			//alert(document.getElementsByName("B_QYJBXXB.QYDM").value);
			if(!ifNull(qydmName)){
				document.getElementsByName(qydmName)[0].value = QYDM;
			}
			
			if(!ifNull(jjlxName)){
				document.getElementsByName(jjlxName)[0].value = JJLX;			
			}
			
			if(!ifNull(sshyName)){			
				document.getElementsByName(sshyName)[0].value = SSHY;
			}
			
			if(!ifNull(txdzName)){			
				document.getElementsByName(txdzName)[0].value = TXDZ;
			}
			
			if(!ifNull(yzbmName)){			
				document.getElementsByName(yzbmName)[0].value = YZBM;
			}
			
			if(!ifNull(fddbrName)){
				document.getElementsByName(fddbrName)[0].value = FDDBR;
			}
			
			if(!ifNull(qylxdhName)){
				document.getElementsByName(qylxdhName)[0].value = QYLXDH;
			}
			
			if(!ifNull(zdcpmcName)){
				document.getElementsByName(zdcpmcName)[0].value = ZDCPMC;
			}
			
			if(!ifNull(yyzzbhName)){
				document.getElementsByName(yyzzbhName)[0].value = YYZZBH;
			}
			
			if(!ifNull(qycyrsName)){
				document.getElementsByName(qycyrsName)[0].value = QYCYRS;
			}
			
			if(!ifNull(qyflName)){
				document.getElementsByName(qyflName)[0].value = QYFL;
			}
			
			if(!ifNull(tzjgName)){
				document.getElementsByName(tzjgName)[0].value = TZJG;
			}
			
			if(!ifNull(xzqhName)){
				document.getElementsByName(xzqhName)[0].value = XZQH;
			}
			
			if(!ifNull(jdName)){
				document.getElementsByName(jdName)[0].value = JD;
			}
			
			if(!ifNull(wdName)){
				document.getElementsByName(wdName)[0].value = WD;
			}
			
			if(!ifNull(ztName)){
				document.getElementsByName(ztName)[0].value = ZT;
			}
			if(!ifNull(shengName)){
				document.getElementsByName(shengName)[0].value = SHENG;
			}
			if(!ifNull(shiName)){
				document.getElementsByName(shiName)[0].value = SHI;
			}
			if(!ifNull(xianName)){
				document.getElementsByName(xianName)[0].value = XIAN;
			}
			
				   
			
	   
	   
	   }
	   
	   
	
	}else{
	
	  // alert("�ҵ�����������һ����ҳ������ʾ");
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	  
	  //alert(qydmname);
	   openwin(oNodes);
	   
	}




}

//���������ļ���Ϣ��xml
function GetAllProFieldsCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("û���ҵ�������������ҵ������Ϣ");
	  if(cpmcName!="") {
		 document.getElementsByName(cpmcName)[0].value='';
	  }
	  if(cpidName!="") {
	  	document.getElementsByName(cpidName)[0].value='';
	 }
	
	}else if(size == 1){
	   //alert("�ҵ�һ������������ҵ������Ϣ��ֱ�Ӵ�ҳ����");
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	   for(var i=0; i<oNodes.length; i++){
	     
			var oNode = oNodes[i] ;
			var CPID = oNode.attributes.getNamedItem('CPID').value ; 
			var QYCPLBID = oNode.attributes.getNamedItem('QYCPLBID').value ; 
			var CPMC = oNode.attributes.getNamedItem('CPMC').value ; 
			var ZCSBH = oNode.attributes.getNamedItem('ZCSBH').value ; 
			var ZCSB = oNode.attributes.getNamedItem('ZCSB').value ; 
			var BSDJBAH = oNode.attributes.getNamedItem('BSDJBAH').value ; 
			var CPMS = oNode.attributes.getNamedItem('CPMS').value ; 
			var GG = oNode.attributes.getNamedItem('GG').value ; 
			var XH = oNode.attributes.getNamedItem('XH').value ; 	
			
			if(!ifNull(cpmcName)) {
				document.getElementsByName(cpmcName)[0].value=CPMC;
			}
			if(!ifNull(cpidName)) {
				document.getElementsByName(cpidName)[0].value=CPID;
			}
	   
	   }
	   
	   
	
	}else{
	
	  // alert("�ҵ�����������һ����ҳ������ʾ");
	  // var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	  
	  //alert(qydmname);
	   //openwin(oNodes);
	   
	}




}
function getProductInfos(select){
    //valueid = select.options[select.selectedIndex].getAttribute("paramValue");
    
    //alert(valueid);
    var qUrl = producturl+'?QYCPLBID='+select.value;
	loadEntInfo(qUrl,GetAllProFieldsCallBack,true);
}

//���������ļ���Ϣ��xml
function GetAllQyLbCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;
	
	//var jccplbName = ymsz["JCCPLB"];//��Ʒ���ID
	if(jccplbName!=""){
		
		var jccplbSelect = document.getElementsByName(jccplbName)[0];
		jccplbSelect.innerHTML="";
		if (jccplbSelect != null){
			jccplbSelect.onchange = function(){
				getProductInfos(this);
			
			}
		}
		if(size==0){
  			var jccplbSelect = document.getElementsByName(jccplbName)[0];
  			jccplbSelect.length = 1;

		}else{
   			//alert("�ҵ�һ������������ҵ������Ϣ��ֱ�Ӵ�ҳ����");
   			var oNodes = AttXml.SelectNodes('TableName/Cplbids/Cplbid');
   			
   			for(var i=0; i<oNodes.length; i++){
     
				var oNode = oNodes[i] ;
				var JCCPLB = oNode.attributes.getNamedItem('JCCPLB').value ;
				var QYCPLBID = oNode.attributes.getNamedItem('QYCPLBID').value ;
				//alert("QYCPLBID="+QYCPLBID);
		
	    		var oOption = document.createElement("OPTION");
        		jccplbSelect.add(oOption);//��Ʒ���select����option
        		
        		oOption.text = JCCPLB;
				oOption.value = QYCPLBID;
				if (jccplbNameValue != ""){
					if (jccplbNameValue == QYCPLBID){
						oOption.selected = true;
					}
				}
				//oOption.setAttribute("paramValue",QYCPLBID);  	
				
				
	   }   
	
	}
	getProductInfos(jccplbSelect);
	}

}

function openwin(oNodes) 
{
	var times = new Date().getTime();
	var reValue = window.showModalDialog("/share/enterprise/select.html?d=" + times,oNodes,"dialogWidth=400px;dialogHeight=400px");
	if (reValue != null){
	    //alert(document.all.value);
	    //document.all.value='';
		//alert(reValue["QYMC"]);
		clearAll();
		if(!ifNull(jccplbName)){
			loadCplb(reValue["QYID"]);
		}
		if(!ifNull(qyidName)){			
				document.getElementsByName(qyidName)[0].value = reValue["QYID"];
				
			}
			
			//alert("qyid");
			if(!ifNull(qymcName)){
				document.getElementsByName(qymcName)[0].value = reValue["QYMC"];
				
			}
			
			//alert(document.all("B_QYJBXXB.QYDM").value);
			if(!ifNull(qydmName)){
				document.getElementsByName(qydmName)[0].value = reValue["QYDM"];
			}
			
			if(!ifNull(jjlxName)){
				document.getElementsByName(jjlxName)[0].value = reValue["JJLX"];			
			}
			
			if(!ifNull(sshyName)){			
				document.getElementsByName(sshyName)[0].value = reValue["SSHY"];
			}
			
			if(!ifNull(txdzName)){			
				document.getElementsByName(txdzName)[0].value = reValue["TXDZ"];
			}
			
			if(!ifNull(yzbmName)){			
				document.getElementsByName(yzbmName)[0].value = reValue["YZBM"];
			}
			
			if(!ifNull(fddbrName)){
				document.getElementsByName(fddbrName)[0].value = reValue["FDDBR"];
			}
			
			if(!ifNull(qylxdhName)){
				document.getElementsByName(qylxdhName)[0].value = reValue["QYLXDH"];
			}
			
			if(!ifNull(zdcpmcName)){
				document.getElementsByName(zdcpmcName)[0].value = reValue["ZDCPMC"];
			}
			
			if(!ifNull(yyzzbhName)){
				document.getElementsByName(yyzzbhName)[0].value = reValue["YYZZBH"];
			}
			
			if(!ifNull(qycyrsName)){
				document.getElementsByName(qycyrsName)[0].value = reValue["QYCYRS"];
			}
			
			if(!ifNull(qyflName)){
				document.getElementsByName(qyflName)[0].value = reValue["QYFL"];
			}
			
			if(!ifNull(tzjgName)){
				document.getElementsByName(tzjgName)[0].value = reValue["TZJG"];
			}
			
			if(!ifNull(xzqhName)){
				document.getElementsByName(xzqhName)[0].value = reValue["XZQH"];
			}
			
			if(!ifNull(jdName)){
				document.getElementsByName(jdName)[0].value = reValue["JD"];
			}
			
			if(!ifNull(wdName)){
				document.getElementsByName(wdName)[0].value = reValue["WD"];
			}
			
			if(!ifNull(ztName)){
				document.getElementsByName(ztName)[0].value = reValue["ZT"];
			}
			if(!ifNull(shengName)){
				document.getElementsByName(shengName)[0].value = reValue["SHENG"];
			}
			if(!ifNull(shiName)){
				document.getElementsByName(shiName)[0].value = reValue["SHI"];
			}
			if(!ifNull(xianName)){
				document.getElementsByName(xianName)[0].value = reValue["XIAN"];
			}
	}

} 

function clearAll(){

          if(!ifNull(qyidName)){			
				document.getElementsByName(qyidName)[0].value = '';
				
			}
			
			//alert("qyid");
			if(!ifNull(qymcName)){
				document.getElementsByName(qymcName)[0].value =  '';
			}
			
			//alert(document.getElementsByName("B_QYJBXXB.QYDM")[0].value);
			if(!ifNull(qydmName)){
				document.getElementsByName(qydmName)[0].value =  '';
			}
			
			if(!ifNull(jjlxName)){
				document.getElementsByName(jjlxName)[0].value =  '';			
			}
			
			if(!ifNull(sshyName)){			
				document.getElementsByName(sshyName)[0].value =  '';
			}
			
			if(!ifNull(txdzName)){			
				document.getElementsByName(txdzName)[0].value =  '';
			}
			
			if(!ifNull(yzbmName)){			
				document.getElementsByName(yzbmName)[0].value =  '';
			}
			
			if(!ifNull(fddbrName)){
				document.getElementsByName(fddbrName)[0].value =  '';
			}
			
			if(!ifNull(qylxdhName)){
				document.getElementsByName(qylxdhName)[0].value =  '';
			}
			
			if(!ifNull(zdcpmcName)){
				document.getElementsByName(zdcpmcName)[0].value =  '';
			}
			
			if(!ifNull(yyzzbhName)){
				document.getElementsByName(yyzzbhName)[0].value =  '';
			}
			
			if(!ifNull(qycyrsName)){
				document.getElementsByName(qycyrsName)[0].value =  '';
			}
			
			if(!ifNull(qyflName)){
				document.getElementsByName(qyflName)[0].value =  '';
			}
			
			if(!ifNull(tzjgName)){
				document.getElementsByName(tzjgName)[0].value =  '';
			}
			
			if(!ifNull(xzqhName)){
				document.getElementsByName(xzqhName)[0].value =  '';
			}
			
			if(!ifNull(jdName)){
				document.getElementsByName(jdName)[0].value =  '';
			}
			
			if(!ifNull(wdName)){
				document.getElementsByName(wdName)[0].value =  '';
			}
			
			if(!ifNull(ztName)){
				document.getElementsByName(ztName)[0].value =  '';
			}
			if(!ifNull(shengName)){
				document.getElementsByName(shengName)[0].value =  '';
			}
			if(!ifNull(shiName)){
				document.getElementsByName(shiName)[0].value =  '';
			}
			if(!ifNull(xianName)){
				document.getElementsByName(xianName)[0].value =  '';
			}
			if(!ifNull(cpmcName)) {
				document.getElementsByName(cpmcName)[0].value='';
			}
			if(!ifNull(cpidName)) {
				document.getElementsByName(cpidName)[0].value='';
			}
	  
}



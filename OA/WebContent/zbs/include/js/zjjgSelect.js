var zjjgUrl = "/inspection/ZjjgSelectServlet";
var zjjgsz = new Array();
var zjjgid,zjjgmc;

//�����ʼ������Ϣ
function loadZjjg(value,bz,iftb){

  if(iftb==null){
  	iftb=true;
  }
   getArray();
   var zjjgValue = value;
   
   var sUrl = zjjgUrl+'?zjjgmc='+zjjgValue+'&bz='+bz;
   
   loadZjjgInfo(sUrl,GetAllFieldsCallBack,iftb);

}

function loadZjjgInfo(sUrl,callBackFunction,iftb){

    //AttXml ������ajax����Servlet��������xml�ļ�
	var oXML = new AttXml() ;
	//alert("loadEntInfo");
	if ( callBackFunction )
		oXML.LoadUrl( sUrl, callBackFunction,iftb ) ;	// Asynchronous load.
	else
		return oXML.LoadUrl( sUrl ) ;

}

//�õ�ҳ���ֶ�����
function getArray(){
	zjjgid = zjjgsz["ZJJGID"];//�ʼ����ID
	zjjgmc = zjjgsz["JGMC"];//�ʼ��������
	
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

//���������ļ���Ϣ��xml
function GetAllFieldsCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("û���ҵ�������������ҵ������Ϣ");
	
	}else if(size == 1){
	   //alert("�ҵ�һ������������ҵ������Ϣ��ֱ�Ӵ�ҳ����");
	   var oNodes = AttXml.SelectNodes('TableName/Zjjgs/Zjjg');
	   for(var i=0; i<oNodes.length; i++){
	     
			var oNode = oNodes[i] ;
			var ZZJGID = oNode.attributes.getNamedItem('ZZJGID').value ; 
			var JGMC = oNode.attributes.getNamedItem('JGMC').value ; 
			var JGLX = oNode.attributes.getNamedItem('JGLX').value ; 
			var JGDZ = oNode.attributes.getNamedItem('JGDZ').value ; 
			var YZBM = oNode.attributes.getNamedItem('YZBM').value ; 
			var CZ = oNode.attributes.getNamedItem('CZ').value ; 
			var DZXX = oNode.attributes.getNamedItem('DZXX').value ; 
			var FZRXM = oNode.attributes.getNamedItem('FZRXM').value ; 
			
			var FZRZW = oNode.attributes.getNamedItem('FZRZW').value ; 
			var FZRDH = oNode.attributes.getNamedItem('FZRDH').value ; 
			var LXRXM = oNode.attributes.getNamedItem('LXRXM').value ; 
			var LXRZW = oNode.attributes.getNamedItem('LXRZW').value ;
			var LXRDH = oNode.attributes.getNamedItem('LXRDH').value ;
			var SSFDDWMC = oNode.attributes.getNamedItem('SSFDDWMC').value ;
			var FDDWDZ = oNode.attributes.getNamedItem('FDDWDZ').value ;
			var FDDWYB = oNode.attributes.getNamedItem('FDDWYB').value ;
			var FDDWCZ = oNode.attributes.getNamedItem('FDDWCZ').value ;
			var FDDWDZXX = oNode.attributes.getNamedItem('FDDWDZXX').value ;
			var FDDWFZRXM = oNode.attributes.getNamedItem('FDDWFZRXM').value ;
			var FDDWFZRDH = oNode.attributes.getNamedItem('FDDWFZRDH').value ;
			var FDDWFZRZW = oNode.attributes.getNamedItem('FDDWFZRZW').value ;
			var ZGBMMC = oNode.attributes.getNamedItem('ZGBMMC').value ;
			var ZGBMDZ = oNode.attributes.getNamedItem('ZGBMDZ').value ;
			var ZGBMYB = oNode.attributes.getNamedItem('ZGBMYB').value ;
			var ZGBMCZ = oNode.attributes.getNamedItem('ZGBMCZ').value ;
			var ZGBMDZXX = oNode.attributes.getNamedItem('ZGBMDZXX').value ;
			var ZGBMFZRXM = oNode.attributes.getNamedItem('ZGBMFZRXM').value ;
			var ZGBMFZRDH = oNode.attributes.getNamedItem('ZGBMFZRDH').value ;
			var ZGBMFZRZW = oNode.attributes.getNamedItem('ZGBMFZRZW').value ;
			var JGSSTD = oNode.attributes.getNamedItem('JGSSTD').value ;
			var ZYLB = oNode.attributes.getNamedItem('ZYLB').value ;
			var SCQZRS = oNode.attributes.getNamedItem('SCQZRS').value ;
			var XTZZID = oNode.attributes.getNamedItem('XTZZID').value ;
			clearAll();
				
			if(!ifNull(zjjgid)){			
				document.getElementsByName(zjjgid)[0].value = ZZJGID;

			}
			
			//alert("qyid");
			if(!ifNull(zjjgmc)){
				document.getElementsByName(zjjgmc)[0].value = JGMC;
			}
			
			
	   }
	   
	   
	
	}else{
	
	  // alert("�ҵ�����������һ����ҳ������ʾ");
	   var oNodes = AttXml.SelectNodes('TableName/Zjjgs/Zjjg');
	  
	  //alert(qydmname);
	   openwin(oNodes);
	   
	}

}

function openwin(oNodes) 
{
	var times = new Date().getTime();
	var reValue = window.showModalDialog("/inspection/common/select.html?d=" + times,oNodes,"dialogWidth=400px;dialogHeight=400px");
	if (reValue != null){
		clearAll();
		if(!ifNull(zjjgid)){			
				document.getElementsByName(zjjgid)[0].value = reValue["ZZJGID"];
				
			}
			
			//alert("qyid");
			if(!ifNull(zjjgmc)){
				document.getElementsByName(zjjgmc)[0].value = reValue["JGMC"];
				
			}
			
	}

} 

function clearAll(){

          if(!ifNull(zjjgid)){			
				document.getElementsByName(zjjgid)[0].value = '';
				
			}
			
			//alert("qyid");
			if(!ifNull(zjjgmc)){
				document.getElementsByName(zjjgmc)[0].value =  '';
			}
			
			 
}
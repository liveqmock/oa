var zjjgUrl = "/inspection/CommonZjjgSelectServlet";
var zjjgsz = new Array();
var zjjgid,zjjgmc;

var selectAllUrl="/inspection/CommonZjjgSelectServlet";	
var reValue;
//�������е��ʼ������Ϣ	
function loadAllZjjg(){
  // var zjjgValue = '';
   
  // var sUrl = selectAllUrl+"?all='1'";
   
  // loadAllCommonZjjgInfo(sUrl,GetALLZjjgCallBack,true);
   reValue= window.showModalDialog("/inspection/CommonZjjgSelectServlet?all=1&page=1&pageSize=15",'',"dialogWidth=600px;dialogHeight=500px");
   if (reValue != null){
		setValuesToPage(reValue);
	}

}

function loadAllCommonZjjgInfo(sUrl,callBackFunction,iftb){

    //AttXml ������ajax����Servlet��������xml�ļ�
	var oXML = new AttXml() ;
	//alert("loadEntInfo");
	if ( callBackFunction )
		oXML.LoadUrl( sUrl, callBackFunction,iftb ) ;	// Asynchronous load.
	else
		return oXML.LoadUrl( sUrl ) ;

}
//���������ļ���Ϣ��xml
function GetALLZjjgCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("û���ҵ�������������ҵ������Ϣ");
	
	}else{
	   var oNodes = AttXml.SelectNodes('TableName/Zjjgs/Zjjg');
	   setValueToArray(oNodes);
	}
	
}	
//�����ʼ������Ϣ
function loadZjjg(lbdm,cplbid,ssdq,sfqpp,iftb,sffx){
	
	if(sffx==null){
		sffx=0;
	}

 // if(iftb==null){
  //	iftb=true;
 // }
   //getArray();
  // var zjjgValue = '';
   
  // var sUrl = zjjgUrl+'?lbdm='+lbdm+'&cplbid='+cplbid+'&sfqpp='+sfqpp+'&ssdq='+ssdq;
   
  // loadCommonZjjgInfo(sUrl,GetZjjgCallBack,iftb);
  reValue= window.showModalDialog("/inspection/CommonZjjgSelectServlet?all=1&page=1&pageSize=15&sffx="+sffx,'',"dialogWidth=600px;dialogHeight=500px");
   if (reValue != null){
		setValuesToPage(reValue);
	}

}

function loadCommonZjjgInfo(sUrl,callBackFunction,iftb){

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
function GetZjjgCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;	
	if(size==0){
	  //alert("û���ҵ�������������ҵ������Ϣ");	
	}else{
	   var oNodes = AttXml.SelectNodes('TableName/Zjjgs/Zjjg');
	   openWin(oNodes);
	   
	}
	
}

function openWin(oNodes) 
{
	var times = new Date().getTime();
	var reValue = window.showModalDialog("/inspection/common/commonSelect.html?d=" + times,oNodes,"dialogWidth=600px;dialogHeight=500px");
	if (reValue != null){
		setValuesToPage(reValue);
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
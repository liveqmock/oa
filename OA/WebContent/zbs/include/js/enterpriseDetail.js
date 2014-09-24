var url = "/share/enterprise/EntBaseDetailInfoServlet";
var qurl = "/share/enterprise/EntBaseProInfoListServlet";
var producturl = "/share/enterprise/EntBaseProInfoServlet";
var xkzurl="/share/enterprise/EntBaseXkzInfoListServlet";
var dataValues = new Array();
var qydmname=new Array();
var ymsz = new Array();
var ymzdsz = new Array();
var en_proList=new Array();
var en_licList=new Array();
var oNode,qymcName,qydmName,jjxzName,sshyName,xxdzName,yzbmName,frdbName,lxdmName;
var zdcpmcName,yyzzbhName,qyzrsName,qyflName,wftzjggbhdqName,xzqhName,jdName,wdName,ztName,jccplbName;

ymzdsz["XXID"]="";
ymzdsz["QYID"]="";
ymzdsz["QYDM"]="";
ymzdsz["QYMC"]="";
ymzdsz["SSHY"]="";
ymzdsz["QYFL"]="";
ymzdsz["WFTZJGGBHDQ"]="";
ymzdsz["JD"]="";
ymzdsz["WD"]="";
ymzdsz["ZT"]="";
ymzdsz["SHENG"]="";
ymzdsz["SHI"]="";
ymzdsz["XIAN"]="";
ymzdsz["LXR"]="";
ymzdsz["YZBM"]="";
ymzdsz["CZ"]="";
ymzdsz["DZYX"]="";
ymzdsz["LXDH"]="";
ymzdsz["XXDZ"]="";
ymzdsz["FRDB"]="";
ymzdsz["QYFZR"]="";
ymzdsz["ZLBZFZR"]="";
ymzdsz["ZGBMMC"]="";
ymzdsz["ZGBMID"]="";
ymzdsz["YYZZBH"]="";
ymzdsz["JJXZ"]="";
ymzdsz["QYZRS"]="";
ymzdsz["GCJSRS"]="";
ymzdsz["JCSJ"]="";
ymzdsz["ZDMJ"]="";
ymzdsz["JZMJ"]="";
ymzdsz["GDZC"]="";
ymzdsz["ZCZJ"]="";
ymzdsz["LDZJ"]="";
ymzdsz["NZCZ"]="";
ymzdsz["NXSE"]="";
ymzdsz["NJSE"]="";
ymzdsz["NLR"]="";
ymzdsz["ZDCPMC"]="";
ymzdsz["ZDCPID"]="";
ymzdsz["PCSQBS"]="";
ymzdsz["UUID"]="";
ymzdsz["ORGID"]="";
ymzdsz["CJSJ"]="";
ymzdsz["UPDATE3"]="";
ymzdsz["GXSJ"]="";
//普查历史纪录
//ymzdsz["PCJLID"]="";
//ymzdsz["PCRQ"]="";
//ymzdsz["PCR"]="";
//ymzdsz["CJBMID"]="";
//ymzdsz["CJRQ"]="";
//ymzdsz["FJBID"]="";
//ymzdsz["BZ"]="";
//是否来自三级监管
ymzdsz["FROM3"]="";

ymzdsz["ifUsedLicense"]="";//是否需要许可证信息
ymzdsz["XKZValue"]="";//工业许可证的默认ID值
ymzdsz["XKZSelect"]="";//工业许可证的选择
ymzdsz["SETXKZLB"]="";//设置要显示的许可证类别，留空为显示所有类别，1为工业许可证,2为食品许可证,3特种设备,4计量器具

ymzdsz["ID"]="";//工业许可证ID
ymzdsz["XKCPLBID"]="";//许可证产品类别
ymzdsz["ZSBH"]="";//证书编号
ymzdsz["XKZLB"]="";//许可证类别
ymzdsz["FZDW"]="";//发证单位
ymzdsz["HZRQ"]="";//获证日期
ymzdsz["YXRQ"]="";//有效日期
ymzdsz["SCDD"]="";//生产地点
ymzdsz["ZT"]="";//状态(有效，无效)




function loadInfo(value,bz,iftb){
  // alert(url);
  if(iftb==null){
  	iftb=true;
  }
   //getArray();
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

function loadXkz(qyid){
	var xkzUrl = xkzurl+'?qyid='+qyid;
	if(ymzdsz["SETXKZLB"]!="")
		xkzUrl = xkzUrl+'&XKZLB='+ymzdsz["SETXKZLB"];
	loadEntInfo(xkzUrl,GetAllXkzCallBack,true);
}


function loadEntInfo(sUrl,callBackFunction,iftb){

    //AttXml 利用了ajax解析Servlet传过来的xml文件
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
	
//解析包含文件信息的xml
function GetAllFieldsCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("没有找到符合条件的企业基本信息");
	
	}else if(size == 1){
	  
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');
	   
	   for(var i=0; i<oNodes.length; i++){	     
			var oNode = oNodes[i] ;
			//alert(oNode);				
			var XXID = oNode.attributes.getNamedItem('XXID').value ; 
			var LXR = oNode.attributes.getNamedItem('LXR').value ; 
			var CZ = oNode.attributes.getNamedItem('CZ').value ; 
			var DZYX = oNode.attributes.getNamedItem('DZYX').value ; 
			var QYFZR = oNode.attributes.getNamedItem('QYFZR').value ; 
;			var ZLBZFZR = oNode.attributes.getNamedItem('ZLBZFZR').value ; 		
			var ZGBMMC = oNode.attributes.getNamedItem('ZGBMMC').value ; 
			var ZGBMID = oNode.attributes.getNamedItem('ZGBMID').value ; 
			var GCJSRS = oNode.attributes.getNamedItem('GCJSRS').value ; 
			var JCSJ = oNode.attributes.getNamedItem('JCSJ').value ; 
			var ZDMJ = oNode.attributes.getNamedItem('ZDMJ').value ; 
			var JZMJ = oNode.attributes.getNamedItem('JZMJ').value ; 
			var GDZC = oNode.attributes.getNamedItem('GDZC').value ; 
			var ZCZJ = oNode.attributes.getNamedItem('ZCZJ').value ; 
			var LDZJ = oNode.attributes.getNamedItem('LDZJ').value ; 
			var NZCZ = oNode.attributes.getNamedItem('NZCZ').value ; 
			var NXSE = oNode.attributes.getNamedItem('NXSE').value ; 
			var NJSE = oNode.attributes.getNamedItem('NJSE').value ;
			var NLR = oNode.attributes.getNamedItem('NLR').value ; 		
			var ZDCPMC = oNode.attributes.getNamedItem('ZDCPMC').value ; 
			var ZDCPID = oNode.attributes.getNamedItem('ZDCPID').value ; 
			var PCSQBS = oNode.attributes.getNamedItem('PCSQBS').value ; 
			var UUID = oNode.attributes.getNamedItem('UUID').value ; 
			var ORGID = oNode.attributes.getNamedItem('ORGID').value ; 
			var CJSJ = oNode.attributes.getNamedItem('CJSJ').value ; 
			var UPDATE3 = oNode.attributes.getNamedItem('UPDATE3').value ; 
			var GXSJ = oNode.attributes.getNamedItem('GXSJ').value ; 			
			
			var QYID = oNode.attributes.getNamedItem('QYID').value ; 
			var QYMC = oNode.attributes.getNamedItem('QYMC').value ; 
			var QYDM = oNode.attributes.getNamedItem('QYDM').value ; 
			var JJXZ = oNode.attributes.getNamedItem('JJXZ').value ; 
			var SSHY = oNode.attributes.getNamedItem('SSHY').value ; 
			var XXDZ = oNode.attributes.getNamedItem('XXDZ').value ; 
			var YZBM = oNode.attributes.getNamedItem('YZBM').value ; 
			var FRDB = oNode.attributes.getNamedItem('FRDB').value ; 
			var LXDH = oNode.attributes.getNamedItem('LXDH').value ; 
			var ZDCPMC = oNode.attributes.getNamedItem('ZDCPMC').value ; 
			var YYZZBH = oNode.attributes.getNamedItem('YYZZBH').value ; 
			var QYZRS = oNode.attributes.getNamedItem('QYZRS').value ;
			var QYFL = oNode.attributes.getNamedItem('QYFL').value ;
			var WFTZJGGBHDQ = oNode.attributes.getNamedItem('WFTZJGGBHDQ').value ;
			var XZQH = oNode.attributes.getNamedItem('XZQH').value ;
			var JD = oNode.attributes.getNamedItem('JD').value ;
			var WD = oNode.attributes.getNamedItem('WD').value ;
			var ZT = oNode.attributes.getNamedItem('ZT').value ;
			var SHENG = oNode.attributes.getNamedItem('SHENG').value ;
			var SHI = oNode.attributes.getNamedItem('SHI').value ;
			var XIAN = oNode.attributes.getNamedItem('XIAN').value ;
			//alert("QYID="+QYID+",QYMC="+QYMC+",QYDM="+QYDM+",JJXZ="+JJXZ+",SSHY="+SSHY);
			//普查历史纪录
			/*
			var PCJLID = oNode.attributes.getNamedItem('PCJLID').value ;
			var PCRQ = oNode.attributes.getNamedItem('PCRQ').value ;
			var PCR = oNode.attributes.getNamedItem('PCR').value ;
			var CJBMID = oNode.attributes.getNamedItem('CJBMID').value ;
			var CJRQ = oNode.attributes.getNamedItem('CJRQ').value ;
			var FJBID = oNode.attributes.getNamedItem('FJBID').value ;
			var BZ = oNode.atributes.getNamedItem('BZ').value ;
			*/
			var FROM3= oNode.attributes.getNamedItem('FROM3').value ;			

			
			
			clearAll();
			loadCplb(QYID);
			
			if(ymzdsz["ifUsedLicense"]=="Y")
				loadXkz(QYID);			
				
			if(document.getElementById(ymzdsz["XXID"])!= null){			
				document.getElementById(ymzdsz["XXID"]).value = XXID;
			}
			if(document.getElementById(ymzdsz["LXR"])!= null){			
				document.getElementById(ymzdsz["LXR"]).value = LXR;
			}
			if(document.getElementById(ymzdsz["CZ"])!= null){			
				document.getElementById(ymzdsz["CZ"]).value = CZ;
			}
			
			if(document.getElementById(ymzdsz["DZYX"])!= null){			
				document.getElementById(ymzdsz["DZYX"]).value = DZYX;
			}
			if(document.getElementById(ymzdsz["QYFZR"])!= null){			
				document.getElementById(ymzdsz["QYFZR"]).value = QYFZR;
			}
			if(document.getElementById(ymzdsz["ZLBZFZR"])!= null){			
				document.getElementById(ymzdsz["ZLBZFZR"]).value = ZLBZFZR;
			}
			if(document.getElementById(ymzdsz["ZGBMMC"])!= null){			
				document.getElementById(ymzdsz["ZGBMMC"]).value = ZGBMMC;
			}
			if(document.getElementById(ymzdsz["ZGBMID"])!= null){			
				document.getElementById(ymzdsz["ZGBMID"]).value = ZGBMID;
			}
			if(document.getElementById(ymzdsz["GCJSRS"])!= null){			
				document.getElementById(ymzdsz["GCJSRS"]).value = GCJSRS;
			}
			if(document.getElementById(ymzdsz["JCSJ"])!= null){			
				document.getElementById(ymzdsz["JCSJ"]).value = JCSJ;
			}
			if(document.getElementById(ymzdsz["ZDMJ"])!= null){			
				document.getElementById(ymzdsz["ZDMJ"]).value = ZDMJ;
			}
			if(document.getElementById(ymzdsz["JZMJ"])!= null){			
				document.getElementById(ymzdsz["JZMJ"]).value = JZMJ;
			}
			if(document.getElementById(ymzdsz["GDZC"])!= null){			
				document.getElementById(ymzdsz["GDZC"]).value = GDZC;
			}
			if(document.getElementById(ymzdsz["ZCZJ"])!= null){			
				document.getElementById(ymzdsz["ZCZJ"]).value = ZCZJ;
			}
			if(document.getElementById(ymzdsz["LDZJ"])!= null){			
				document.getElementById(ymzdsz["LDZJ"]).value = LDZJ;
			}
			if(document.getElementById(ymzdsz["NZCZ"])!= null){			
				document.getElementById(ymzdsz["NZCZ"]).value = NZCZ;
			}
			if(document.getElementById(ymzdsz["NXSE"])!= null){			
				document.getElementById(ymzdsz["NXSE"]).value = NXSE;
			}
			if(document.getElementById(ymzdsz["NJSE"])!= null){			
				document.getElementById(ymzdsz["NJSE"]).value = NJSE;
			}
			if(document.getElementById(ymzdsz["NLR"])!= null){			
				document.getElementById(ymzdsz["NLR"]).value = NLR;
			}
			if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
				document.getElementById(ymzdsz["ZDCPMC"]).value = ZDCPMC;
			}
			if(document.getElementById(ymzdsz["ZDCPID"])!= null){			
				document.getElementById(ymzdsz["ZDCPID"]).value = ZDCPID;
			}
			if(document.getElementById(ymzdsz["PCSQBS"])!= null){			
				document.getElementById(ymzdsz["PCSQBS"]).value = PCSQBS;
			}
			if(document.getElementById(ymzdsz["UUID"])!= null){			
				document.getElementById(ymzdsz["UUID"]).value = UUID;
			}
			if(document.getElementById(ymzdsz["ORGID"])!= null){			
				document.getElementById(ymzdsz["ORGID"]).value = ORGID;
			}
			if(document.getElementById(ymzdsz["CJSJ"])!= null){			
				document.getElementById(ymzdsz["CJSJ"]).value = CJSJ;
			}
			if(document.getElementById(ymzdsz["UPDATE3"])!= null){			
				document.getElementById(ymzdsz["UPDATE3"]).value = UPDATE3;
			}
			if(document.getElementById(ymzdsz["GXSJ"])!= null){			
				document.getElementById(ymzdsz["GXSJ"]).value = GXSJ;
			}
			if(document.getElementById(ymzdsz["QYID"])!= null){			
				document.getElementById(ymzdsz["QYID"]).value = QYID;
			}
			if(document.getElementById(ymzdsz["QYMC"])!= null){			
				document.getElementById(ymzdsz["QYMC"]).value = QYMC;
			}
			if(document.getElementById(ymzdsz["QYDM"])!= null){			
				document.getElementById(ymzdsz["QYDM"]).value = QYDM;
			}
			if(document.getElementById(ymzdsz["JJXZ"])!= null){			
				document.getElementById(ymzdsz["JJXZ"]).value = JJXZ;
			}
			if(document.getElementById(ymzdsz["SSHY"])!= null){			
				document.getElementById(ymzdsz["SSHY"]).value = SSHY;
			}
			if(document.getElementById(ymzdsz["XXDZ"])!= null){			
				document.getElementById(ymzdsz["XXDZ"]).value = XXDZ;
			}
			if(document.getElementById(ymzdsz["YZBM"])!= null){			
				document.getElementById(ymzdsz["YZBM"]).value = YZBM;
			}
			if(document.getElementById(ymzdsz["FRDB"])!= null){			
				document.getElementById(ymzdsz["FRDB"]).value = FRDB;
			}
			if(document.getElementById(ymzdsz["LXDH"])!= null){			
				document.getElementById(ymzdsz["LXDH"]).value = LXDH;
			}
			if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
				document.getElementById(ymzdsz["ZDCPMC"]).value = ZDCPMC;
			}
			if(document.getElementById(ymzdsz["FRDB"])!= null){			
				document.getElementById(ymzdsz["FRDB"]).value = FRDB;
			}
			if(document.getElementById(ymzdsz["LXDH"])!= null){			
				document.getElementById(ymzdsz["LXDH"]).value = LXDH;
			}
			if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
				document.getElementById(ymzdsz["ZDCPMC"]).value = ZDCPMC;
			}
			if(document.getElementById(ymzdsz["YYZZBH"])!= null){			
				document.getElementById(ymzdsz["YYZZBH"]).value = YYZZBH;
			}
			if(document.getElementById(ymzdsz["QYZRS"])!= null){			
				document.getElementById(ymzdsz["QYZRS"]).value = QYZRS;
			}
			if(document.getElementById(ymzdsz["QYFL"])!= null){			
				document.getElementById(ymzdsz["QYFL"]).value = QYFL;
			}
			if(document.getElementById(ymzdsz["WFTZJGGBHDQ"])!= null){			
				document.getElementById(ymzdsz["WFTZJGGBHDQ"]).value = WFTZJGGBHDQ;
			}
			if(document.getElementById(ymzdsz["XZQH"])!= null){			
				document.getElementById(ymzdsz["XZQH"]).value = XZQH;
			}
			if(document.getElementById(ymzdsz["JD"])!= null){			
				document.getElementById(ymzdsz["JD"]).value = JD;
			}
			
			if(document.getElementById(ymzdsz["WD"])!= null){			
				document.getElementById(ymzdsz["WD"]).value = WD;
			}
			
			if(document.getElementById(ymzdsz["ZT"])!= null){			
				document.getElementById(ymzdsz["ZT"]).value = ZT;
			}
			
			if(document.getElementById(ymzdsz["SHENG"])!= null){			
				document.getElementById(ymzdsz["SHENG"]).value = SHENG;
			}
			if(document.getElementById(ymzdsz["SHI"])!= null){			
				document.getElementById(ymzdsz["SHI"]).value = SHI;
			}	
			if(document.getElementById(ymzdsz["XIAN"])!= null){			
				document.getElementById(ymzdsz["XIAN"]).value = XIAN;
				if(area_countrySelectName!=null)
					area_load(area_provinceSelectName,area_citySelectName,area_countrySelectName,SHENG,SHI,XIAN);
				
			}
			//普查历史纪录
			/*
			if(document.getElementById(ymzdsz["PCJLID"])!= null){			
				document.getElementById(ymzdsz["PCJLID"]).value = PCJLID;
			}
			if(document.getElementById(ymzdsz["PCRQ"])!= null){			
				document.getElementById(ymzdsz["PCRQ"]).value = PCRQ;
			}
			if(document.getElementById(ymzdsz["PCR"])!= null){			
				document.getElementById(ymzdsz["PCR"]).value = PCR;
			}
			if(document.getElementById(ymzdsz["CJBMID"])!= null){			
				document.getElementById(ymzdsz["CJBMID"]).value = CJBMID;
			}
			if(document.getElementById(ymzdsz["CJRQ"])!= null){			
				document.getElementById(ymzdsz["CJRQ"]).value = CJRQ;
			}
			if(document.getElementById(ymzdsz["FJBID"])!= null){			
				document.getElementById(ymzdsz["FJBID"]).value = FJBID;
			}
			if(document.getElementById(ymzdsz["BZ"])!= null){			
				document.getElementById(ymzdsz["BZ"]).value = BZ;
			}
			*/
			if(document.getElementById(ymzdsz["FROM3"])!= null){			
				document.getElementById(ymzdsz["FROM3"]).value = FROM3;
			}
					

	   }	
	}else{	
	  // alert("找到多条，弹出一个新页面来显示");
	   var oNodes = AttXml.SelectNodes('TableName/Fields/Field');	  
	  //alert(qydmname);
	   openwin(oNodes);	   
	}
}

//解析包含文件信息的xml
function GetAllProFieldsCallBack( AttXml )
{

	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;

	
	if(size==0){
	  //alert("没有找到符合条件的企业基本信息");
	  if(cpmcName!="") {
		 document.getElementsByName(cpmcName)[0].value='';
	  }
	  if(cpidName!="") {
	  	document.getElementsByName(cpidName)[0].value='';
	 }
	
	}else if(size == 1){
	   //alert("找到一条符合条件企业基本信息，直接打到页面上");
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
			
			if(cpmcName!="") {
				document.getElementsByName(cpmcName)[0].value=CPMC;
			}
			if(cpidName!="") {
				document.getElementsByName(cpidName)[0].value=CPID;
			}
	   
	   }
	   
	   
	
	}else{
	
	  // alert("找到多条，弹出一个新页面来显示");
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

//解析包含文件信息的xml
function GetAllQyLbCallBack( AttXml )
{
	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;
	//var jccplbName = ymsz["JCCPLB"];//产品类别ID
		if(size>0){
   			var oNodes = AttXml.SelectNodes('TableName/Cplbids/Cplbid');
			//var rowLength=tableTitle.cells.length-1;				
			var oTable = document.getElementById("productList");			
   			for(var i=0; i<oNodes.length; i++){
   				en_proList[i]=new Array();
				var oNode = oNodes[i] ;
				var CPLBID = oNode.attributes.getNamedItem('CPLBID').value;
				en_proList[i]['CPLBID']= CPLBID;
				var QYCPLBID = oNode.attributes.getNamedItem('QYCPLBID').value ;
				en_proList[i]['QYCPLBID']=QYCPLBID;
				var CPID = oNode.attributes.getNamedItem('CPID').value ;
				en_proList[i]['CPID']=CPID; 

				var CPMC = oNode.attributes.getNamedItem('CPMC').value ;
				en_proList[i]['CPMC']=CPMC;
				var ZCSBH = oNode.attributes.getNamedItem('ZCSBH').value ;
				en_proList[i]['ZCSBH']=ZCSBH;
				var ZCSB = oNode.attributes.getNamedItem('ZCSB').value ;
				en_proList[i]['ZCSB']=ZCSB;
				var BSDJBAH = oNode.attributes.getNamedItem('BSDJBAH').value ;
				en_proList[i]['BSDJBAH']=BSDJBAH;
				var CPMS = oNode.attributes.getNamedItem('CPMS').value ;
				en_proList[i]['CPMS']=CPMS;
				var GG = oNode.attributes.getNamedItem('GG').value ;
				en_proList[i]['GG']=GG;
				var XH = oNode.attributes.getNamedItem('XH').value ;
				en_proList[i]['XH']=XH;	
				//展示到页面list上;
				if(oTable!=null){				
					var rowLength=tableTitle.cells.length-1;
					var rows = oTable.rows.length;				
					//var rowLength=0;	    		
					var bgColor = "#FFFFFF";				
					var oRow=oTable.insertRow(oTable.rows.length);
					oRow.bgColor=bgColor;
					oRow.onmouseover="this.bgColor = '#EDF5F8';" 
					oRow.onmouseout="this.bgColor = '#FFFFFF';"
					var aRows=oTable.rows;
					var aCells=oRow.cells;
					addCell(aRows,oRow,aCells,"celltxt","center",(i+1));
					addCell(aRows,oRow,aCells,"celltxt","left",CPMC);
					addCell(aRows,oRow,aCells,"celltxt","left",GG);
					addCell(aRows,oRow,aCells,"celltxt","left",XH);
					addCell(aRows,oRow,aCells,"celltxt","left",CPLBID);
					addCell(aRows,oRow,aCells,"celltxt","left",ZCSB);
				}
	   }	  
	}
}

//解析包含文件信息的xml
function GetAllXkzCallBack( AttXml )
{
	var oNode = AttXml.SelectSingleNode( 'TableName/Size' ) ;
	var size	= oNode.attributes.getNamedItem('size').value ;
	if(ymzdsz["XKZSelect"]!=""){
		var jccplbSelect = document.getElementsByName(ymzdsz["XKZSelect"])[0];
		jccplbSelect.innerHTML="";
		if (jccplbSelect != null){
			jccplbSelect.onchange = function(){
				getXkzInfos(this);
			}
		}
		if(size>0){
			var oOption = document.createElement("OPTION");
        	jccplbSelect.add(oOption);
        	oOption.text = "请选择";
			oOption.value = "-1";
   			var oNodes = AttXml.SelectNodes('TableName/Xkzs/Xkz');			
   			for(var i=0; i<oNodes.length; i++){
   				en_licList[i]=new Array();
				var oNode = oNodes[i] ;
				var ID = oNode.attributes.getNamedItem('ID').value;
				en_licList[i]['ID']= ID;
				var XKCPLBID = oNode.attributes.getNamedItem('XKCPLBID').value ;
				en_licList[i]['XKCPLBID']=XKCPLBID;
				var ZSBH = oNode.attributes.getNamedItem('ZSBH').value ;
				en_licList[i]['ZSBH']=ZSBH; 
				var XKZLB= oNode.attributes.getNamedItem('XKZLB').value ;
				en_licList[i]['XKZLB']=XKZLB;
				var FZDW = oNode.attributes.getNamedItem('FZDW').value ;
				en_licList[i]['FZDW']=FZDW;
				var HZRQ = oNode.attributes.getNamedItem('HZRQ').value ;
				en_licList[i]['HZRQ']=HZRQ;
				var YXRQ = oNode.attributes.getNamedItem('YXRQ').value ;
				en_licList[i]['YXRQ']=YXRQ;
				var SCDD = oNode.attributes.getNamedItem('SCDD').value ;
				en_licList[i]['SCDD']=SCDD;
				var ZT = oNode.attributes.getNamedItem('ZT').value ;
				en_licList[i]['ZT']=ZT;
				//展示到页面list上;
				var oOption = document.createElement("OPTION");
        		jccplbSelect.add(oOption);
        		oOption.text = ZSBH;
				oOption.value = ID;
				if (ymzdsz["XKZSelect"] != ""){
					if (ymzdsz["XKZSelect"] == ID){
						oOption.selected = true;
						getXkzInfos(ID);
					}
				}					
			}
   		}else{
   			var oOption = document.createElement("OPTION");
        	jccplbSelect.add(oOption);
        	oOption.text = "无许可证";
			oOption.value = "-1";
   		}
   		
	 }
}

function getXkzInfos(obj){	
	var selectedValue=obj.value;
	var len=en_licList.length;
	clearXkz();
	for(var i=0;i<len;i++){
		if(selectedValue==en_licList[i]["ID"]){
			if(document.getElementById(ymzdsz["ID"])!= null){			
				document.getElementById(ymzdsz["ID"]).value = en_licList[i]["ID"];
			}
			if(document.getElementById(ymzdsz["XKCPLBID"])!= null){			
				document.getElementById(ymzdsz["XKCPLBID"]).value = en_licList[i]["XKCPLBID"];
			}
			if(document.getElementById(ymzdsz["ZSBH"])!= null){			
				document.getElementById(ymzdsz["ZSBH"]).value = en_licList[i]["ZSBH"];
			}
			if(document.getElementById(ymzdsz["XKZLB"])!= null){			
				document.getElementById(ymzdsz["XKZLB"]).value = en_licList[i]["XKZLB"];
			}
			if(document.getElementById(ymzdsz["FZDW"])!= null){			
				document.getElementById(ymzdsz["FZDW"]).value = en_licList[i]["FZDW"];
			}
			if(document.getElementById(ymzdsz["HZRQ"])!= null){			
				document.getElementById(ymzdsz["HZRQ"]).value = en_licList[i]["HZRQ"];
			}
			if(document.getElementById(ymzdsz["YXRQ"])!= null){
				document.getElementById(ymzdsz["YXRQ"]).value = en_licList[i]["YXRQ"];
			}
			if(document.getElementById(ymzdsz["SCDD"])!= null){
				document.getElementById(ymzdsz["SCDD"]).value = en_licList[i]["SCDD"];
			}
			if(document.getElementById(ymzdsz["ZT"])!= null){
				document.getElementById(ymzdsz["ZT"]).value = en_licList[i]["ZT"];
			}			
		}
	}
}

function clearXkz(){
	if(document.getElementById(ymzdsz["ID"])!= null){			
		document.getElementById(ymzdsz["ID"]).value = "";
	}
	if(document.getElementById(ymzdsz["XKCPLBID"])!= null){			
		document.getElementById(ymzdsz["XKCPLBID"]).value = "";
	}
	if(document.getElementById(ymzdsz["ZSBH"])!= null){			
		document.getElementById(ymzdsz["ZSBH"]).value = "";
	}
	if(document.getElementById(ymzdsz["XKZLB"])!= null){			
		document.getElementById(ymzdsz["XKZLB"]).value = "";
	}
	if(document.getElementById(ymzdsz["FZDW"])!= null){			
		document.getElementById(ymzdsz["FZDW"]).value = "";
	}
	if(document.getElementById(ymzdsz["HZRQ"])!= null){			
		document.getElementById(ymzdsz["HZRQ"]).value = "";
	}
	if(document.getElementById(ymzdsz["YXRQ"])!= null){
		document.getElementById(ymzdsz["YXRQ"]).value = "";
	}
	if(document.getElementById(ymzdsz["SCDD"])!= null){
		document.getElementById(ymzdsz["SCDD"]).value = "";
	}
	if(document.getElementById(ymzdsz["ZT"])!= null){
		document.getElementById(ymzdsz["ZT"]).value = "";
	}
}


function addCell(aRows,oRow,aCells,_className,_align,value,nowarp){
	var oCell=aRows(oRow.rowIndex).insertCell(aCells.length);
	oCell.className=_className;
	oCell.align=_align;
	if (nowarp!=null && nowarp){
		oCell.noWrap=true;
	}
	oCell.innerHTML = value;
}

function openwin(oNodes) 
{
	var times = new Date().getTime();
	var reValue = window.showModalDialog("/share/enterprise/selectDetail.html?d=" + times,oNodes,"dialogWidth=400px;dialogHeight=400px");
	if (reValue != null){
	    //alert(document.all.value);
	    //document.all.value='';
		//alert(reValue["QYMC"]);
		clearAll();
		loadCplb(reValue["QYID"]);
		if(ymzdsz["ifUsedLicense"]=="Y")
			loadXkz(reValue["QYID"]);		
					
		 if(document.getElementById(ymzdsz["XXID"])!= null){			
			document.getElementById(ymzdsz["XXID"]).value = reValue["XXID"];
		}
		if(document.getElementById(ymzdsz["LXR"])!= null){			
			document.getElementById(ymzdsz["LXR"]).value = reValue["LXR"];
		}
		
		if(document.getElementById(ymzdsz["CZ"])!= null){			
			document.getElementById(ymzdsz["CZ"]).value = reValue["CZ"];
		}
		
		if(document.getElementById(ymzdsz["DZYX"])!= null){			
			document.getElementById(ymzdsz["DZYX"]).value = reValue["DZYX"];
		}
		if(document.getElementById(ymzdsz["QYFZR"])!= null){			
			document.getElementById(ymzdsz["QYFZR"]).value = reValue["QYFZR"];
		}
		if(document.getElementById(ymzdsz["ZLBZFZR"])!= null){			
			document.getElementById(ymzdsz["ZLBZFZR"]).value = reValue["ZLBZFZR"];
		}
		if(document.getElementById(ymzdsz["ZGBMMC"])!= null){			
			document.getElementById(ymzdsz["ZGBMMC"]).value = reValue["ZGBMMC"];
		}
		if(document.getElementById(ymzdsz["ZGBMID"])!= null){			
			document.getElementById(ymzdsz["ZGBMID"]).value = reValue["ZGBMID"];
		}
		if(document.getElementById(ymzdsz["GCJSRS"])!= null){			
			document.getElementById(ymzdsz["GCJSRS"]).value = reValue["GCJSRS"];
		}
		if(document.getElementById(ymzdsz["JCSJ"])!= null){			
			document.getElementById(ymzdsz["JCSJ"]).value = reValue["JCSJ"];
		}
		if(document.getElementById(ymzdsz["ZDMJ"])!= null){			
			document.getElementById(ymzdsz["ZDMJ"]).value = reValue["ZDMJ"];
		}
		if(document.getElementById(ymzdsz["JZMJ"])!= null){			
			document.getElementById(ymzdsz["JZMJ"]).value = reValue["JZMJ"];
		}
		if(document.getElementById(ymzdsz["GDZC"])!= null){			
			document.getElementById(ymzdsz["GDZC"]).value = reValue["GDZC"];
		}
		if(document.getElementById(ymzdsz["ZCZJ"])!= null){			
			document.getElementById(ymzdsz["ZCZJ"]).value = reValue["ZCZJ"];;
		}
		if(document.getElementById(ymzdsz["LDZJ"])!= null){			
			document.getElementById(ymzdsz["LDZJ"]).value = reValue["LDZJ"];;
		}
		if(document.getElementById(ymzdsz["NZCZ"])!= null){			
			document.getElementById(ymzdsz["NZCZ"]).value = reValue["NZCZ"];;
		}
		if(document.getElementById(ymzdsz["NXSE"])!= null){			
			document.getElementById(ymzdsz["NXSE"]).value = reValue["NXSE"];;
		}
		if(document.getElementById(ymzdsz["NJSE"])!= null){			
			document.getElementById(ymzdsz["NJSE"]).value = reValue["NJSE"];;
		}
		if(document.getElementById(ymzdsz["NLR"])!= null){			
			document.getElementById(ymzdsz["NLR"]).value = reValue["NLR"];;
		}
		if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
			document.getElementById(ymzdsz["ZDCPMC"]).value = reValue["ZDCPMC"];
		}
		if(document.getElementById(ymzdsz["ZDCPID"])!= null){			
			document.getElementById(ymzdsz["ZDCPID"]).value = reValue["ZDCPID"];
		}
		if(document.getElementById(ymzdsz["PCSQBS"])!= null){			
			document.getElementById(ymzdsz["PCSQBS"]).value = reValue["PCSQBS"];
		}
		if(document.getElementById(ymzdsz["UUID"])!= null){			
			document.getElementById(ymzdsz["UUID"]).value = reValue["UUID"];
		}
		if(document.getElementById(ymzdsz["ORGID"])!= null){			
			document.getElementById(ymzdsz["ORGID"]).value = reValue["ORGID"];
		}
		if(document.getElementById(ymzdsz["CJSJ"])!= null){			
			document.getElementById(ymzdsz["CJSJ"]).value = reValue["CJSJ"];
		}
		if(document.getElementById(ymzdsz["UPDATE3"])!= null){			
			document.getElementById(ymzdsz["UPDATE3"]).value = reValue["UPDATE3"];
		}
		if(document.getElementById(ymzdsz["GXSJ"])!= null){			
			document.getElementById(ymzdsz["GXSJ"]).value = reValue["GXSJ"];
		}
		if(document.getElementById(ymzdsz["QYID"])!= null){			
			document.getElementById(ymzdsz["QYID"]).value = reValue["QYID"];
		}
		if(document.getElementById(ymzdsz["QYMC"])!= null){			
			document.getElementById(ymzdsz["QYMC"]).value = reValue["QYMC"];
		}
		if(document.getElementById(ymzdsz["QYDM"])!= null){			
			document.getElementById(ymzdsz["QYDM"]).value = reValue["QYDM"];
		}
		if(document.getElementById(ymzdsz["JJXZ"])!= null){			
			document.getElementById(ymzdsz["JJXZ"]).value = reValue["JJXZ"];
		}
		if(document.getElementById(ymzdsz["SSHY"])!= null){			
			document.getElementById(ymzdsz["SSHY"]).value = reValue["SSHY"];
		}
		if(document.getElementById(ymzdsz["XXDZ"])!= null){			
			document.getElementById(ymzdsz["XXDZ"]).value = reValue["XXDZ"];
		}
		if(document.getElementById(ymzdsz["YZBM"])!= null){			
			document.getElementById(ymzdsz["YZBM"]).value = reValue["YZBM"];
		}
		if(document.getElementById(ymzdsz["FRDB"])!= null){			
			document.getElementById(ymzdsz["FRDB"]).value = reValue["FRDB"];
		}
		if(document.getElementById(ymzdsz["LXDH"])!= null){			
			document.getElementById(ymzdsz["LXDH"]).value = reValue["LXDH"];
		}
		if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
			document.getElementById(ymzdsz["ZDCPMC"]).value = reValue["ZDCPMC"];
		}
		if(document.getElementById(ymzdsz["FRDB"])!= null){			
			document.getElementById(ymzdsz["FRDB"]).value = reValue["FRDB"];
		}
		if(document.getElementById(ymzdsz["LXDH"])!= null){			
			document.getElementById(ymzdsz["LXDH"]).value = reValue["LXDH"];
		}
		if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
			document.getElementById(ymzdsz["ZDCPMC"]).value = reValue["ZDCPMC"];
		}
		if(document.getElementById(ymzdsz["YYZZBH"])!= null){			
			document.getElementById(ymzdsz["YYZZBH"]).value = reValue["YYZZBH"];
		}
		if(document.getElementById(ymzdsz["QYZRS"])!= null){			
			document.getElementById(ymzdsz["QYZRS"]).value = reValue["QYZRS"];
		}
		if(document.getElementById(ymzdsz["QYFL"])!= null){			
			document.getElementById(ymzdsz["QYFL"]).value = reValue["QYFL"];
		}
		if(document.getElementById(ymzdsz["WFTZJGGBHDQ"])!= null){			
			document.getElementById(ymzdsz["WFTZJGGBHDQ"]).value = reValue["WFTZJGGBHDQ"];
		}
		if(document.getElementById(ymzdsz["XZQH"])!= null){			
			document.getElementById(ymzdsz["XZQH"]).value = reValue["XZQH"];
		}
		if(document.getElementById(ymzdsz["JD"])!= null){			
			document.getElementById(ymzdsz["JD"]).value = reValue["JD"];
		}
		
		if(document.getElementById(ymzdsz["WD"])!= null){			
			document.getElementById(ymzdsz["WD"]).value = reValue["WD"];
		}
		
		if(document.getElementById(ymzdsz["ZT"])!= null){			
			document.getElementById(ymzdsz["ZT"]).value = reValue["ZT"];
		}
		
		if(document.getElementById(ymzdsz["SHENG"])!= null){			
			document.getElementById(ymzdsz["SHENG"]).value = reValue["SHENG"];
		}
		if(document.getElementById(ymzdsz["SHI"])!= null){			
			document.getElementById(ymzdsz["SHI"]).value = reValue["SHI"];
		}	
		if(document.getElementById(ymzdsz["XIAN"])!= null){			
			document.getElementById(ymzdsz["XIAN"]).value = reValue["XIAN"];
			if(area_countrySelectName!=null)
				area_load(area_provinceSelectName,area_citySelectName,area_countrySelectName,reValue["SHENG"],reValue["SHI"],reValue["XIAN"]);
		}
		
		//普查历史纪录
		/*
		if(document.getElementById(ymzdsz["PCJLID"])!= null){			
			document.getElementById(ymzdsz["PCJLID"]).value = reValue["PCJLID"];
		}
		if(document.getElementById(ymzdsz["PCRQ"])!= null){			
			document.getElementById(ymzdsz["PCRQ"]).value = reValue["PCRQ"];
		}
		if(document.getElementById(ymzdsz["PCR"])!= null){			
			document.getElementById(ymzdsz["PCR"]).value = reValue["PCR"];
		}
		if(document.getElementById(ymzdsz["CJBMID"])!= null){			
			document.getElementById(ymzdsz["CJBMID"]).value = reValue["CJBMID"];
		}
		if(document.getElementById(ymzdsz["CJRQ"])!= null){			
			document.getElementById(ymzdsz["CJRQ"]).value = reValue["CJRQ"];
		}
		if(document.getElementById(ymzdsz["FJBID"])!= null){			
			document.getElementById(ymzdsz["FJBID"]).value = reValue["FJBID"];
		}
		if(document.getElementById(ymzdsz["BZ"])!= null){			
			document.getElementById(ymzdsz["BZ"]).value = reValue["BZ"];
		}
		*/
		if(document.getElementById(ymzdsz["FROM3"])!= null){			
			document.getElementById(ymzdsz["FROM3"]).value = reValue["FROM3"];
		}
		
	}

} 

function clearAll(){

          if(document.getElementById(ymzdsz["XXID"])!= null){			
			document.getElementById(ymzdsz["XXID"]).value = "";
		}
		if(document.getElementById(ymzdsz["LXR"])!= null){			
			document.getElementById(ymzdsz["LXR"]).value = "";
		}
		if(document.getElementById(ymzdsz["CZ"])!= null){			
			document.getElementById(ymzdsz["CZ"]).value = "";
		}
		
		if(document.getElementById(ymzdsz["DZYX"])!= null){			
			document.getElementById(ymzdsz["DZYX"]).value = "";
		}
		if(document.getElementById(ymzdsz["QYFZR"])!= null){			
			document.getElementById(ymzdsz["QYFZR"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZLBZFZR"])!= null){			
			document.getElementById(ymzdsz["ZLBZFZR"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZGBMMC"])!= null){			
			document.getElementById(ymzdsz["ZGBMMC"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZGBMID"])!= null){			
			document.getElementById(ymzdsz["ZGBMID"]).value = "";
		}
		if(document.getElementById(ymzdsz["GCJSRS"])!= null){			
			document.getElementById(ymzdsz["GCJSRS"]).value = "";
		}
		if(document.getElementById(ymzdsz["JCSJ"])!= null){			
			document.getElementById(ymzdsz["JCSJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZDMJ"])!= null){			
			document.getElementById(ymzdsz["ZDMJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["JZMJ"])!= null){			
			document.getElementById(ymzdsz["JZMJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["GDZC"])!= null){			
			document.getElementById(ymzdsz["GDZC"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZCZJ"])!= null){			
			document.getElementById(ymzdsz["ZCZJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["LDZJ"])!= null){			
			document.getElementById(ymzdsz["LDZJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["NZCZ"])!= null){			
			document.getElementById(ymzdsz["NZCZ"]).value = "";
		}
		if(document.getElementById(ymzdsz["NXSE"])!= null){			
			document.getElementById(ymzdsz["NXSE"]).value = "";
		}
		if(document.getElementById(ymzdsz["NJSE"])!= null){			
			document.getElementById(ymzdsz["NJSE"]).value = "";
		}
		if(document.getElementById(ymzdsz["NLR"])!= null){			
			document.getElementById(ymzdsz["NLR"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
			document.getElementById(ymzdsz["ZDCPMC"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZDCPID"])!= null){			
			document.getElementById(ymzdsz["ZDCPID"]).value = "";
		}
		if(document.getElementById(ymzdsz["PCSQBS"])!= null){			
			document.getElementById(ymzdsz["PCSQBS"]).value = "";
		}
		if(document.getElementById(ymzdsz["UUID"])!= null){			
			document.getElementById(ymzdsz["UUID"]).value = "";
		}
		if(document.getElementById(ymzdsz["ORGID"])!= null){			
			document.getElementById(ymzdsz["ORGID"]).value = "";
		}
		if(document.getElementById(ymzdsz["CJSJ"])!= null){			
			document.getElementById(ymzdsz["CJSJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["UPDATE3"])!= null){			
			document.getElementById(ymzdsz["UPDATE3"]).value = "";
		}
		if(document.getElementById(ymzdsz["GXSJ"])!= null){			
			document.getElementById(ymzdsz["GXSJ"]).value = "";
		}
		if(document.getElementById(ymzdsz["QYID"])!= null){			
			document.getElementById(ymzdsz["QYID"]).value = "";
		}
		if(document.getElementById(ymzdsz["QYMC"])!= null){			
			document.getElementById(ymzdsz["QYMC"]).value = "";
		}
		if(document.getElementById(ymzdsz["QYDM"])!= null){			
			document.getElementById(ymzdsz["QYDM"]).value = "";
		}
		if(document.getElementById(ymzdsz["JJXZ"])!= null){			
			document.getElementById(ymzdsz["JJXZ"]).value = "";
		}
		if(document.getElementById(ymzdsz["SSHY"])!= null){			
			document.getElementById(ymzdsz["SSHY"]).value = "";
		}
		if(document.getElementById(ymzdsz["XXDZ"])!= null){			
			document.getElementById(ymzdsz["XXDZ"]).value = "";
		}
		if(document.getElementById(ymzdsz["YZBM"])!= null){			
			document.getElementById(ymzdsz["YZBM"]).value = "";
		}
		if(document.getElementById(ymzdsz["FRDB"])!= null){			
			document.getElementById(ymzdsz["FRDB"]).value = "";
		}
		if(document.getElementById(ymzdsz["LXDH"])!= null){			
			document.getElementById(ymzdsz["LXDH"]).value = "";
		}
		if(document.getElementById(ymzdsz["ZDCPMC"])!= null){			
			document.getElementById(ymzdsz["ZDCPMC"]).value = "";
		}
		
		if(document.getElementById(ymzdsz["YYZZBH"])!= null){			
			document.getElementById(ymzdsz["YYZZBH"]).value = "";
		}
		if(document.getElementById(ymzdsz["QYZRS"])!= null){			
			document.getElementById(ymzdsz["QYZRS"]).value = "";
		}
		if(document.getElementById(ymzdsz["QYFL"])!= null){			
			document.getElementById(ymzdsz["QYFL"]).value = "";
		}
		if(document.getElementById(ymzdsz["WFTZJGGBHDQ"])!= null){			
			document.getElementById(ymzdsz["WFTZJGGBHDQ"]).value = "";
		}
		if(document.getElementById(ymzdsz["XZQH"])!= null){			
			document.getElementById(ymzdsz["XZQH"]).value = "";
		}
		if(document.getElementById(ymzdsz["JD"])!= null){			
			document.getElementById(ymzdsz["JD"]).value = "";
		}
		
		if(document.getElementById(ymzdsz["WD"])!= null){			
			document.getElementById(ymzdsz["WD"]).value = "";
		}
		
		if(document.getElementById(ymzdsz["ZT"])!= null){			
			document.getElementById(ymzdsz["ZT"]).value = "";
		}
		
		if(document.getElementById(ymzdsz["SHENG"])!= null){			
			document.getElementById(ymzdsz["SHENG"]).value ="";
		}
		if(document.getElementById(ymzdsz["SHI"])!= null){			
			document.getElementById(ymzdsz["SHI"]).value = "";
		}	
		if(document.getElementById(ymzdsz["XIAN"])!= null){			
			document.getElementById(ymzdsz["XIAN"]).value = "";
		}
		//普查历史纪录
		/*
		if(document.getElementById(ymzdsz["PCJLID"])!= null){			
			document.getElementById(ymzdsz["PCJLID"]).value = "";
		}
		if(document.getElementById(ymzdsz["PCRQ"])!= null){			
			document.getElementById(ymzdsz["PCRQ"]).value = "";
		}
		if(document.getElementById(ymzdsz["PCR"])!= null){			
			document.getElementById(ymzdsz["PCR"]).value = "";
		}
		if(document.getElementById(ymzdsz["CJBMID"])!= null){			
			document.getElementById(ymzdsz["CJBMID"]).value = "";
		}
		if(document.getElementById(ymzdsz["CJRQ"])!= null){			
			document.getElementById(ymzdsz["CJRQ"]).value = "";
		}
		if(document.getElementById(ymzdsz["FJBID"])!= null){			
			document.getElementById(ymzdsz["FJBID"]).value = "";
		}
		if(document.getElementById(ymzdsz["BZ"])!= null){			
			document.getElementById(ymzdsz["BZ"]).value = "";
		}
		*/
		if(document.getElementById(ymzdsz["FROM3"])!= null){			
			document.getElementById(ymzdsz["FROM3"]).value = "";
		}
		clearXkz();
		
		var oTable = document.getElementById("productList");
		if(oTable!=null){
			var rows = oTable.rows.length;
			if (rows>2){
				for (var i=2;i<rows;i++){
					oTable.deleteRow(2);
				}
			}
		}	
	  
}





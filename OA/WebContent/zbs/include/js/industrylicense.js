var xkzurl="/share/enterprise/QueryGYXKZListServlet";
var dataValues = new Array();
var qydmname=new Array();
var ymsz = new Array();
var xkzinfo = new Array();
var oNode,qymcName,qydmName,jjlxName,sshyName,txdzName,yzbmName,fddbrName,qylxdhName;
var xkzlb;

var zdcpmcName,yyzzbhName,qycyrsName,qyflName,tzjgName,xzqhName,jdName,wdName,ztName,jccplbName,shengName,shiName,xianName;
var zsbhName,zsztName,hzrqName,yxrqName;

//得到页面传过来的字段数组
function getxkzArray(){
	zsbhName = xkzinfo["ZSBH"];//证书编号
	zsztName = xkzinfo["ZT"];//证书状态
	hzrqName = xkzinfo["HZRQ"]//发证日期
	yxrqName = xkzinfo["YXRQ"]//证书有效期
	xkzlb    = xkzinfo["SETXKZLB"];//许可证类别
}
//判断是否为空
function ifNull(_ymValue){
	if(_ymValue==null){
		return true;
	}
	if(_ymValue==""){
		return true;
	}
	
	return false;
}
//
function loadXKZInfo(value){
   getxkzArray();
   openEnterpriseGYXKZListInfo(value);
   	
}
 
 

function openEnterpriseGYXKZListInfo(qyid){

   var reValue = window.showModalDialog("/share/QueryGYXKZListServlet?page=1&pageSize=15&qyid="+qyid+"&xkzlb="+xkzlb,'',"dialogWidth=650px;dialogHeight=550px");
   if (reValue != null){   		
   		for(var i = 0;i<reValue.length;i++){
	  		clearAll();
			/*if(!ifNull(qyidName)){			
					document.getElementsByName(qyidName)[0].value = reValue[i]["QYID"];
					
			}
			if(!ifNull(qymcName)){
				document.getElementsByName(qymcName)[0].value = reValue[i]["QYMC"];
				
			}
			
			if(!ifNull(qydmName)){
				document.getElementsByName(qydmName)[0].value = reValue[i]["QYDM"];
			}
			
			if(!ifNull(qydmName)){
				document.getElementsByName(qydmName)[0].value = reValue[i]["QYDM"];
			}*/
			
			if(!ifNull(zsbhName)){
				document.getElementsByName(zsbhName)[0].value = reValue[i]["ZSBH"];
			}
			
			/*if(!ifNull(zsbhName)){
				document.getElementsByName("zsbh")[0].value = reValue[i]["ZSBH"];
			}*/
			
			if(!ifNull(hzrqName)){
				document.getElementsByName(hzrqName)[0].value = reValue[i]["HZRQ"];
			}
			
			if(!ifNull(yxrqName)){
				document.getElementsByName(yxrqName)[0].value = reValue[i]["YXRQ"];
			}
			
			/*if(!ifNull(zsztName)){
				document.getElementsByName(zsztName)[0].value = reValue[i]["ZT"];
			}*/
			_zt_=reValue[i]["ZT"];
			if(_zt_!=null)
			{
				var _zszt_="0";
				if(_zt_=="Y")
					_zszt_="1";
				else
					_zszt_="0";
				for(var i=0;i<document.form1.Ins_zszt.length;i++)
				{
					if(_zszt_==document.form1.Ins_zszt[i].value)
						document.form1.Ins_zszt[i].checked=true;
					//document.form1.zszt[i].disabled=true;
				}
				//document.form1.Ins_zszt.value=_zszt_;
		  	}
		 }
   }
   
}

function clearAll(){

	  if(!ifNull(zsbhName)){			
	  		document.getElementsByName(zsbhName)[0].value = '';
		
	  }
	  
	  /*if(!ifNull(zsbhName)){			
	  		document.getElementsByName("zsbh")[0].value = '';
		
	  }*/
	  
	  if(!ifNull(hzrqName)){
		 	document.getElementsByName(hzrqName)[0].value =  '';
	  }
	
	  if(!ifNull(yxrqName)){
		 	document.getElementsByName(yxrqName)[0].value =  '';
	  }
			
	  
}



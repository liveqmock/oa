var filesName = "bmp,chm,css,csv,dll,doc,dochtml,docxml,dot,dothtml,dtd,folder,gif,html,htm,java,jpeg,js,jsp,mda,mdb,mdl,media,mht,mhtml,mpp,page,pdf,pps,ppt,rar,rm,txt,vsd,xls,xml,xsd,xsl,zip,jpg";
var ffArray = new Array();
var tmp = new Array();
//ҳ���������
var ymcs = new Array();
var fjbidqz="Att_Fjbid0"; ;//������idǰ׺
function getArray(){
	fjbidqz = ymcs["FJBIDQZ"];//������IDǰ׺
}
tmp=filesName.split(",");
for(var i=0;i<tmp.length;i++){
	ffArray[tmp[i].toLowerCase()] = tmp[i]+".gif";
}
function AddSelectOption( selectElement, optionText, optionValue )
{
	var oOption = document.createElement("OPTION") ;

	oOption.text	= optionText ;
	oOption.value	= optionValue ;	

	selectElement.options.add(oOption) ;

	return oOption ;
}

function alert_jg(msg){
	alert(msg);
}

function StringBuilder( value )
{
    this._Strings = new Array( value || '' ) ;
}

StringBuilder.prototype.Append = function( value )
{
    if ( value )
        this._Strings.push( value ) ;
}

StringBuilder.prototype.ToString = function()
{
    return this._Strings.join( '' ) ;
}

//����һ������
var oListManager = new Object() ;

//�����ö������շ���
oListManager.Clear = function()
{
	document.getElementById('msg').innerHTML='';
}
oListManager.GetFileRowHtml = function( fileName,id, fileUrl, fileSize,ifFile,sFileFId,sLxid,sFjybs,sReadonly )
{
	
	// Build the link to view the folder.
	//alert(fjbidqz);
	if(fjbidqz!=null){
		var fjbidname='Att_Fjbid0';
		//document.getElementsByName(fjbidname)[0].value=sFileFId;
		document.getElementById(fjbidname).value=sFileFId;
		//alert(document.getElementsByName(fjbidname)[0].value);
	}
	var fileGif = "page.gif";
	var iim = fileName.lastIndexOf(".");
	if (iim>0 && iim<fileName.length){
		var fileTypeStr = fileName.substring((iim+1),fileName.length);
		if (ffArray[fileTypeStr.toLowerCase()]!=null){
			fileGif =ffArray[fileTypeStr.toLowerCase()];
		}
	}
	if (fileGif==null){
		fileGif = "page.gif";
	}
    if(ifFile==0){
		 var atturl = fileUrl;
		 var delurl ='/share/attached/AttDeleteFileServlet?fjfid='+sFileFId+'&fjzid='+id+'&LXID='+sLxid+'&FJYBS='+sFjybs+"&readonly="+sReadonly;
	     //alert(atturl);
		 var delcontent;
		 if(sReadonly=="false"){
		 	delcontent= '<td class=atta align="center"nowrap><a href="#" onclick="DelFile(\'' + delurl.replace( /'/g, '\\\'') + '\');return false;">ɾ��</a></td>';
		 }else{
		 	delcontent='';
		 }
		 
	     var sLink = '<input type=hidden name=fjfid value='+sFileFId+'><input type=hidden name=attid'+ sFjybs+' value='+id+'><a href="#" onclick="OpenFile(\'' + atturl.replace( /'/g, '\\\'') + '\');return false;">' ;
	
		 return '<tr>' +
					'<td class=atta nowrap width="10px" align="right"><img border="0" src="/source/style/images/att/' + fileGif + '"></td><td class=atta rowspan >' +
						sLink + fileName + 
						'</a>' +
					'</td>'+delcontent+'<td class=atta align="right" nowrap>&nbsp;' +
						fileSize + 
						' KB' +
				'</td></tr>' ;
  }else{
  
     var atturl = '/share/attached/AttReadFileServlet?fjfid='+sFileFId+'&fjzid='+id+'&LXID='+sLxid+'&FJYBS='+sFjybs;
	 var delurl ='/share/attached/AttDeleteFileServlet?fjfid='+sFileFId+'&fjzid='+id+'&LXID='+sLxid+'&FJYBS='+sFjybs+"&readonly="+sReadonly;
     //alert(atturl);
	 var delcontent;
		 if(sReadonly=="false"){
		 	delcontent= '<td class=atta align="center"nowrap><a href="#" onclick="DelFile(\'' + delurl.replace( /'/g, '\\\'') + '\');return false;">ɾ��</a></td>';
		 }else{
		 	delcontent='';
		 }
     var sLink = '<input type=hidden name=fjfid value='+sFileFId+'><input type=hidden name=attid'+ sFjybs+' value='+id+'><a href="#" onclick="OpenFile(\'' + atturl.replace( /'/g, '\\\'') + '\');return false;">' ;

	 return '<tr>' +
				'<td class=atta nowrap width="10px" align="right"><img border="0" src="/source/style/images/att/' + fileGif + '"></td><td class=atta rowspan >' +
					sLink + fileName + 
					'</a>' +
				'</td>'+delcontent+'<td class=atta align="right" nowrap>&nbsp;' +
					fileSize + 
					' KB' +
			'</td></tr>' ;
     
  }
}
//���ļ��ķ���
function OpenFile( fileUrl )
{
	//window.open(fileUrl);
	window.document.location=fileUrl;
}

function DelFile(fileUrl){
	
  if(confirm("ȷ��ɾ��������Ϣ")){
	  	if (window.XMLHttpRequest) {
	
	      req = new XMLHttpRequest();
	
	  }else if (window.ActiveXObject) {
	
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	
	  }
	
	  if(req){   
	      req.open("GET", fileUrl, true);		  
	      req.onreadystatechange = callback;
		  req.send(null); 
		  
	
	  }	
  }
  
  
}

function callback() {
	
    if (req.readyState == 4) {

        if (req.status == 200) {

                 parseMessage();
        }else{

            alert ("ɾ��������");

        }       

    }
    
   }
   
   
function parseMessage() {
	  var xmlDoc = req.responseXML.documentElement;
    
     var fjzid = xmlDoc.getElementsByTagName('fjzid');
	 if(fjzid[0].firstChild==null){
	 	fjzidvalue='';
	 }else{
		 fjzidvalue=fjzid[0].firstChild.nodeValue;
	 }	 
	 var fjfid = xmlDoc.getElementsByTagName('fjfid');
	 var lxid = xmlDoc.getElementsByTagName('lxid');
	 var fjybs = xmlDoc.getElementsByTagName('fjybs');
	 var readonly = xmlDoc.getElementsByTagName('readonly');
	 //alert("akakaka "+node[0].firstChild.nodeValue);
      ReDelfresh(fjzidvalue,fjfid[0].firstChild.nodeValue,lxid[0].firstChild.nodeValue,fjybs[0].firstChild.nodeValue,readonly[0].firstChild.nodeValue);
    
    
	}

//����һ������
var oAttach = new Object() ;
//��ǰĿ¼
oAttach.CurrentFolder	= '/' ;
//����URL
oAttach.AttachUrl='/share/attached/AttUploadServlet?';

//���������Servlet

oAttach.SendCommand = function( command, params, callBackFunction )
{

	var sUrl = this.AttachUrl + 'Command=' + command ;
	sUrl += '&Type=' + this.ResourceType ;
	sUrl += '&CurrentFolder=' + escape( this.CurrentFolder ) ;
	
	if ( params ) sUrl += '&' + params ;
	
//AttXml ������ajax����Servlet��������xml�ļ�
	var oXML = new AttXml() ;
	
	if ( callBackFunction )
		oXML.LoadUrl( sUrl, callBackFunction,false ) ;	// Asynchronous load.
	else
		return oXML.LoadUrl( sUrl ) ;
}

//����xml�ļ����ҳ����еĴ�����Ϣ
oAttach.CheckError = function( responseXml )
{
	var iErrorNumber = 0
	//ѡ�������Ϣ�ڵ�
	var oErrorNode = responseXml.SelectSingleNode( 'Attach/Error' ) ;
	
	if ( oErrorNode )
	{
		iErrorNumber = parseInt( oErrorNode.attributes.getNamedItem('number').value ) ;
		
		switch ( iErrorNumber )
		{
			case 0 :
				break ;
			case 1 :	// Custom error. Message placed in the "text" attribute.
				alert( oErrorNode.attributes.getNamedItem('text').value ) ;
				break ;
			case 101 :
				alert( 'Folder already exists' ) ;
				break ;
			case 102 :
				alert( 'Invalid folder name' ) ;
				break ;
			case 103 :
				alert( '�������س���������ٽ��в���!' ) ;
				break ;
			case 110 :
				alert( 'Unknown error creating folder' ) ;
				break ;
			default :
				alert( '�������س���������ٽ��в���!' ) ;
				break ;
		}
	}
	return iErrorNumber ;
}

//������Դ
function LoadResources( resourceType, folderPath,attids,fjfid,lxid,fjybs,readonly )
{    
	
   // alert("aaaa");
	//alert();
	//alert(document.getElementsByName("attid").value);
	var msg = 'att_msg'+lxid+fjybs;
	if(document.getElementById(msg)!=null){
		document.getElementById(msg).innerHTML='';
	}
	
	oAttach.ResourceType = resourceType ;
	oAttach.CurrentFolder = folderPath
	oAttach.SendCommand( 'GetFoldersAndFiles','id='+attids+'&fjbid='+fjfid+"&LXID="+lxid+'&FJYBS='+fjybs+"&readonly="+readonly, GetFoldersAndFilesCallBack ) ;
}



//ˢ�µ�ǰҳ��
function Refresh(fjfid,fjzid,lxid,fjybs,readonly)
{
	//alert(document.getElementById('msg').outerHTML);
	document.getElementsByName("fjbid")[0].value=fjfid;
	//alert("attid"+fjybs);
	var oaa = document.getElementsByName("attid"+fjybs);
	if(oaa.length==0){
       var attids =fjzid;
	}else{
	  var attids =fjzid+ ",";
	}
	for(var i=0;i<oaa.length;i++){
		if (i>0)
		{
			attids +=",";
		}
		attids +=oaa[i].value;
	}
	//alert(attids);
	LoadResources( 'Attach', '/',attids,fjfid,lxid,fjybs,readonly) ;
}

//ˢ�µ�ǰҳ��
function ReDelfresh(attid,fjfid,lxid,fjybs,readonly)
{

	if(attid==''){
		document.getElementsByName("fjbid")[0].value="";
	}
	var oaa = document.getElementsByName("attid"+fjybs);
	var attids="";
	for(var i=0;i<oaa.length;i++){
		if (i>0)
		{
			if(attid==oaa[i].value){
				
			}else{
				attids +=",";
			}
			
		}
		if(attid==oaa[i].value){
			
		}else{
			attids +=oaa[i].value;
		}
		
	}
	//alert(attids);
	LoadResources( 'Attach', '/',attids ,fjfid,lxid,fjybs,readonly) ;
}

//���������ļ���Ϣ��xml
function GetFoldersAndFilesCallBack( AttXml )
{
	if ( oAttach.CheckError( AttXml ) != 0 )
		return ;

	// Get the current folder path.
	var oNode = AttXml.SelectSingleNode( 'Attach/CurrentFolder' ) ;
	var sCurrentFolderPath	= oNode.attributes.getNamedItem('path').value ;
	var sCurrentFolderUrl	= oNode.attributes.getNamedItem('url').value ;
	var lxid	= oNode.attributes.getNamedItem('lxid').value ;
	var fjybs	= oNode.attributes.getNamedItem('fjybs').value ;

//	var dTimer = new Date() ;

	
    var msg;
	var sFileName,sFileTrueName,sFileSize,sFileId,sFileFId,sIfFile,sLxid,sFjybs;
	var fjybsz = new Array();
	var fjnrz = new Array();
	var s = ",";
	// Add the Files.	
	var oNodes = AttXml.SelectNodes( 'Attach/Files/File' ) ;
	for ( var i = 0 ; i < oNodes.length ; i++ )
	{
		 oNode = oNodes[i] ;
		 sFileName = oNode.attributes.getNamedItem('FJMC').value ;
		 sFileTrueName = oNode.attributes.getNamedItem('truename').value ;
		 sFileSize = oNode.attributes.getNamedItem('size').value ;
		 sFileId = oNode.attributes.getNamedItem('FJZID').value ;
		 sFileFId = oNode.attributes.getNamedItem('FJFID').value ;
		 sIfFile = oNode.attributes.getNamedItem('ifFile').value ;
		 sLxid = oNode.attributes.getNamedItem('lxid').value ;
		 sReadonly = oNode.attributes.getNamedItem('readonly').value ;
		 sFjybs = oNode.attributes.getNamedItem('fjybs').value ;
		 var t = new Array();
		 t["sFileName"]=sFileName;
		 t["sFileTrueName"]=sFileTrueName;
		 t["sFileSize"]=sFileSize;
		 t["sFileId"]=sFileId;
		 t["sFileFId"]=sFileFId;
		 t["sIfFile"]=sIfFile;
		 t["sLxid"]=sLxid;
		 t["sReadonly"]=sReadonly;
		 t["sFjybs"]=sFjybs;
		 var v="att_msg"+sLxid+sFjybs;
		 if (fjnrz[v]==null){
			 fjnrz[v] = new Array();
			
		 }
		 
		 fjnrz[v][fjnrz[v].length]=t;
		
		//oHtml.Append( oListManager.GetFileRowHtml( sFileName,sFileId, sCurrentFolderUrl + sFileTrueName, sFileSize,sIfFile,sFileFId ,sLxid,sFjybs) ) ;
	}
	var msgnum = document.getElementsByTagName("div");
	//alert(msgnum.length);
	var msgname;
	
	for(var a=0;a<msgnum.length;a++){
		//alert(msgnum[a].id)
		if(msgnum[a].id.indexOf("att_msg")!=-1){
			//alert("ok");
			msgname= msgnum[a].id;
			var oHtml = new StringBuilder( '<table id="tableFiles" cellspacing="1" cellpadding="0" width="100%" border="0">' ) ;
			if(fjnrz[msgname]!=null){
				for(var b=0;b<fjnrz[msgname].length;b++){
				 sFileName = fjnrz[msgname][b]["sFileName"] ;
				 sFileTrueName = fjnrz[msgname][b]["sFileTrueName"] ;
				 sFileSize = fjnrz[msgname][b]["sFileSize"] ;
				 sFileId = fjnrz[msgname][b]["sFileId"] ;
				 sFileFId = fjnrz[msgname][b]["sFileFId"] ;
				 sIfFile = fjnrz[msgname][b]["sIfFile"] ;
				 sLxid = fjnrz[msgname][b]["sLxid"] ;
				 sReadonly = fjnrz[msgname][b]["sReadonly"] ;
				 sFjybs = fjnrz[msgname][b]["sFjybs"] ;
				 oHtml.Append( oListManager.GetFileRowHtml( sFileName,sFileId, sCurrentFolderUrl + sFileTrueName, sFileSize,sIfFile,sFileFId ,sLxid,sFjybs,sReadonly) ) ;
			}//for end 
			oHtml.Append( '</table>' ) ;
			//alert(oHtml.ToString());
			if(document.getElementById(msgname)!=null){
	           document.getElementById(msgname).innerHTML = oHtml.ToString() ;
	        }
		}
			
		}//if end 
	}
	//if(sLxid==null||sFjybs==null){
	//	msg = 'att_msg'+lxid+fjybs;
	//}else{
	//	msg = 'att_msg'+sLxid+sFjybs;
	//}

}
function attsubmit(bs,lxid,readonly){
    var fjbid;
    if(document.getElementsByName("fjbid")!=null){
        fjbid= document.getElementsByName("fjbid")[0].value;
		
		if(fjbidqz!=null){
			var fjbidname=fjbidqz;
			document.getElementById(fjbidname).value=fjbid;
		}			
    }
    
    document.AttForm.action="/share/attached/AttUploadServlet?LXID="+lxid+"&fjbid="+fjbid+"&FJYBS="+bs+"&readonly="+readonly;
	
	document.AttForm.submit();	
}
//ҳ��onloadʱ����ã�fjbidΪҳ�渽����ID��lxid�ĺ�������ڸ�������ά��ҳ�����������ID
var attcomp_name;
function onload_att(fjbid,lxid){
    var attas = document.getElementsByTagName("atta");
	var isIframe = true;
	var _ii = attas.length;
	for(var i=0;i<_ii;i++){
	
		var _a = attas[0];
		var _number = _a.getAttribute("number");
		var _type = _a.getAttribute("type");
		//alert("here");
		var _readonly = _a.getAttribute("readonly");
		//�Ƿ����ɨ�谴ť trueΪ���֣�����Ϊ������
		var _ifCxSmAnu=_a.getAttribute("scan");
		//alert(_ifCxSmAnu);
        var _tyy;
		var name = "NewFile" + _number;
		var dName = "att_msg" + _type + _number;
		if(_readonly!=null){
			_tyy = _number + "," + _type+","+"'"+_readonly+"'";
			
		}else{
			_readonly='false';
			_tyy = _number + "," + _type+","+"'"+_readonly+"'";
			
		}
		
		if ( _number != null && _type != null){
			var _s ="";
			if (isIframe){
				_s+="<iframe name=\"hidden_frame\" id=\"hidden_frame\" style=\"display:none\"></iframe>";
				_s+="<input type=hidden name=fjbid>";
				isIframe = false;
			}
				_s+= "<table class=\"uptable\">";
					_s+="<tr>";
						_s+="<td height=\"100%\" valign=\"bottom\">";
						_s+="<div id=\"" + dName + "\"></div>";
						_s+="</td>";
					_s+="</tr>";
					if(_readonly=='false'){
					_s+="<tr>";
						_s+="<td height=\"20px\">";
				        _s+="<span id=filelx><input name=\"" + name + "\" type=\"file\" value=\"\" class=\"upload\" id=\"" + name + "\"></span>";
				        _s+="&nbsp;<input type=\"button\"  onclick=\"att_submit(" + _tyy + ")\" class=\"upload\" value=\"�ϴ�\"/>";
				        if(_ifCxSmAnu=='true'){
				        	_s+=" <input type=\"button\" onclick=\"begin_scan('"+fjbid+"','"+_type+"',"+_number+")\" class=\"upload\" value=\"ɨ��\"/>";
				        }
						_s+="</td>";
					_s+="</tr>";
					}
				_s+="</table>";
			_a.outerHTML = _s;
			
			//if(LoadResources( 'Attach', '/','',fjbid,lxid,_number,_readonly)){
				//continue;
			//}
			LoadResources( 'Attach', '/','',fjbid,lxid,_number,_readonly);
			
		}
	}
	//alert(ymcs["FJBIDQZ"]);
	if(_ii==0){
	}else{
		document.getElementsByName("fjbid")[0].value=fjbid;
	}
	
	
}

//��ʼɨ��
function begin_scan(fjbid,fjlx,fjybs){
	window.open("/share/scan/scan.jsp?fjbid="+fjbid+"&fjlx="+fjlx+"&fjybs="+fjybs,"����ɨ��ģ��","resizable=yes,menubar=no,directories=no,status=no,location=no,scrollbars=no,left=200,top=120,width=800,height=600");
}
//�ύ���������У�bsΪ������ʶ������0��lxidΪ�������ҵ������ID
function att_submit(bs,lxid,readonly){
    var fjvalue=document.getElementsByName("NewFile"+bs)[0].value;
	if(fjvalue==null||fjvalue==""){
		alert("����ѡ���ϴ��ĸ���");
	}else{
		attsubmit(bs,lxid,readonly);
	}
	
}

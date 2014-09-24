function AddSelectOption( selectElement, optionText, optionValue )
{
	var oOption = document.createElement("OPTION") ;

	oOption.text	= optionText ;
	oOption.value	= optionValue ;	

	selectElement.options.add(oOption) ;

	return oOption ;
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

//创建一个对象
var oListManager = new Object() ;

//创建该对象的清空方法
oListManager.Clear = function()
{
	document.getElementById('msg').innerHTML='';
}
//显示文件列表的方法
oListManager.GetFileRowHtml = function( fileName,id, fileUrl, fileSize,ifFile )
{
	
	// Build the link to view the folder.
	
    if(ifFile==0){
		var sLink = '<a href="#" onclick="OpenFile(\'' + fileUrl.replace( /'/g, '\\\'') + '\');return false;">' ;
	
		// Get the file icon.
		
	    
		return '<tr>' +
				'<td>' +
					sLink + 
					'<input type=hidden name=id value='+id+'>'+fileName + 
					'</a>' +
				'</td><td align="center"nowrap><a href=#>删除</a></td><td align="right" nowrap>&nbsp;' +
					fileSize + 
					' KB' +
			'</td></tr>' ;
  }else{
     var atturl = '/share/attached/AttReadFileServlet?id='+id;
	 var delurl ='/share/attached/AttDeleteFileServlet?id='+id;
     //alert(atturl);
     var sLink = '<input type=hidden name=attid value='+id+'><a href="#" onclick="OpenFile(\'' + atturl.replace( /'/g, '\\\'') + '\');return false;">' ;
	
		// Get the file icon.
		
	    
		return '<tr>' +
				'<td>' +
					sLink + fileName + 
					'</a>' +
				'</td><td align="center"nowrap><a href="#" onclick="DelFile(\'' + delurl.replace( /'/g, '\\\'') + '\');return false;">删除</a></td><td align="right" nowrap>&nbsp;' +
					fileSize + 
					' KB' +
			'</td></tr>' ;
     
  }
}

//打开文件的方法
function OpenFile( fileUrl )
{
	window.open(fileUrl);
}

function DelFile(fileUrl){
  
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

function callback() {
	
    if (req.readyState == 4) {

        if (req.status == 200) {

                 parseMessage();

                 // update the HTML DOM based on whether or not message is valid

        }else{

            alert ("删除有问题");

        }       

    }
    
   }
   
   
   function parseMessage() {
	  var xmlDoc = req.responseXML.documentElement;
    
     var node = xmlDoc.getElementsByTagName('info');
	 //alert("akakaka "+node[0].firstChild.nodeValue);
      ReDelfresh(node[0].firstChild.nodeValue);
    
    
	}

//创建一个对象
var oAttach = new Object() ;
//当前目录
oAttach.CurrentFolder	= '/' ;
//请求URL
oAttach.AttachUrl='/share/attached/AttOptServlet?';

//发送请求给Servlet

oAttach.SendCommand = function( command, params, callBackFunction )
{

	var sUrl = this.AttachUrl + 'Command=' + command ;
	sUrl += '&Type=' + this.ResourceType ;
	sUrl += '&CurrentFolder=' + escape( this.CurrentFolder ) ;
	
	if ( params ) sUrl += '&' + params ;
	
//AttXml 利用了ajax解析Servlet传过来的xml文件
	var oXML = new AttXml() ;
	
	if ( callBackFunction )
		oXML.LoadUrl( sUrl, callBackFunction ) ;	// Asynchronous load.
	else
		return oXML.LoadUrl( sUrl ) ;
}

//解析xml文件，找出其中的错误信息
oAttach.CheckError = function( responseXml )
{
	var iErrorNumber = 0
	//选择错误信息节点
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
				alert( 'You have no permissions to create the folder' ) ;
				break ;
			case 110 :
				alert( 'Unknown error creating folder' ) ;
				break ;
			default :
				alert( 'Error on your request. Error number: ' + iErrorNumber ) ;
				break ;
		}
	}
	return iErrorNumber ;
}

//加载资源
function LoadResources( resourceType, folderPath,attids )
{    
	
   // alert("aaaa");
	//alert();
	//alert(document.getElementsByName("attid").value);
	document.getElementById('msg').innerHTML='';
	oAttach.ResourceType = resourceType ;
	oAttach.CurrentFolder = folderPath
	oAttach.SendCommand( 'GetFoldersAndFiles','id='+attids, GetFoldersAndFilesCallBack ) ;
}



//刷新当前页面
function Refresh(id)
{
	//alert(document.getElementById('msg').outerHTML);
	document.getElementById("filelx").innerHTML = "<input type=\"file\" name=\"NewFile\" id=\"NewFile\"/>";
	var oaa = document.getElementsByName("attid");
	if(oaa.length==0){
       var attids =id;
	}else{
	  var attids =id+ ",";
	}
	for(var i=0;i<oaa.length;i++){
		if (i>0)
		{
			attids +=",";
		}
		attids +=oaa[i].value;
	}
	//alert(attids);
	LoadResources( 'Attach', '/',attids ) ;
}

//刷新当前页面
function ReDelfresh(attid)
{
	//alert("ddddddddddddddddd");
	//alert(attid);
	var oaa = document.getElementsByName("attid");
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
	LoadResources( 'Attach', '/',attids ) ;
}

//解析包含文件信息的xml
function GetFoldersAndFilesCallBack( AttXml )
{
	if ( oAttach.CheckError( AttXml ) != 0 )
		return ;

	// Get the current folder path.
	var oNode = AttXml.SelectSingleNode( 'Attach/CurrentFolder' ) ;
	var sCurrentFolderPath	= oNode.attributes.getNamedItem('path').value ;
	var sCurrentFolderUrl	= oNode.attributes.getNamedItem('url').value ;

//	var dTimer = new Date() ;

	var oHtml = new StringBuilder( '<table id="tableFiles" cellspacing="1" cellpadding="0" width="100%" border="0">' ) ;

	// Add the Files.	
	var oNodes = AttXml.SelectNodes( 'Attach/Files/File' ) ;
	for ( var i = 0 ; i < oNodes.length ; i++ )
	{
		var oNode = oNodes[i] ;
		var sFileName = oNode.attributes.getNamedItem('name').value ;
		var sFileTrueName = oNode.attributes.getNamedItem('truename').value ;
		var sFileSize = oNode.attributes.getNamedItem('size').value ;
		var sFileId = oNode.attributes.getNamedItem('id').value ;
		var sIfFile = oNode.attributes.getNamedItem('ifFile').value ;
		oHtml.Append( oListManager.GetFileRowHtml( sFileName,sFileId, sCurrentFolderUrl + sFileTrueName, sFileSize,sIfFile ) ) ;
	}

	oHtml.Append( '</table>' ) ;
	
	document.getElementById('msg').innerHTML = oHtml.ToString() ;
//alert(document.getElementById('msg').outerHTML);
//alert(document.getElementById("attid").value);
//alert(document.getElementById("attid"));
//	window.top.document.title = 'Finished processing in ' + ( ( ( new Date() ) - dTimer ) / 1000 ) + ' seconds' ;

}
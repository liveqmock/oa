var AttXml = function()
{}

AttXml.prototype.GetHttpRequest = function()
{
	if ( window.XMLHttpRequest )		// Gecko
		return new XMLHttpRequest() ;
	else if ( window.ActiveXObject )	// IE
		return new ActiveXObject("MsXml2.XmlHttp") ;
}

AttXml.prototype.LoadUrl = function( urlToCall, asyncFunctionPointer,iftb )
{
	var oAttXml = this ;

	var bAsync = ( typeof(asyncFunctionPointer) == 'function' ) ;

	var oXmlHttp = this.GetHttpRequest() ;
		
	oXmlHttp.open( "GET", urlToCall,iftb ) ;
	
	if ( bAsync )
	{	
		oXmlHttp.onreadystatechange = function() 
		{
			if ( oXmlHttp.readyState == 4 )
			{
				oAttXml.DOMDocument = oXmlHttp.responseXML ;
				if ( oXmlHttp.status == 200 || oXmlHttp.status == 304 )
					asyncFunctionPointer( oAttXml ) ;
				else
					alert( '文件加载错误: ' + oXmlHttp.statusText + ' (' + oXmlHttp.status + ')' ) ;
			}
		}
	}
	
	oXmlHttp.send( null ) ;
	
	if ( ! bAsync )
	{
		if ( oXmlHttp.status == 200 || oXmlHttp.status == 304 )
			this.DOMDocument = oXmlHttp.responseXML ;
		else
		{
			alert( '文件加载错误: ' + oXmlHttp.statusText + ' (' + oXmlHttp.status + ')' ) ;
		}
	}
}

AttXml.prototype.SelectNodes = function( xpath )
{
	if ( document.all )		// IE
		return this.DOMDocument.selectNodes( xpath ) ;
	else					// Gecko
	{
		var aNodeArray = new Array();

		var xPathResult = this.DOMDocument.evaluate( xpath, this.DOMDocument, 
				this.DOMDocument.createNSResolver(this.DOMDocument.documentElement), XPathResult.ORDERED_NODE_ITERATOR_TYPE, null) ;
		if ( xPathResult ) 
		{
			var oNode = xPathResult.iterateNext() ;
 			while( oNode )
 			{
 				aNodeArray[aNodeArray.length] = oNode ;
 				oNode = xPathResult.iterateNext();
 			}
		} 
		return aNodeArray ;
	}
}

AttXml.prototype.SelectSingleNode = function( xpath ) 
{
	if ( document.all )		// IE
		return this.DOMDocument.selectSingleNode( xpath ) ;
	else					// Gecko
	{
		var xPathResult = this.DOMDocument.evaluate( xpath, this.DOMDocument,
				this.DOMDocument.createNSResolver(this.DOMDocument.documentElement), 9, null);

		if ( xPathResult && xPathResult.singleNodeValue )
			return xPathResult.singleNodeValue ;
		else	
			return null ;
	}
}

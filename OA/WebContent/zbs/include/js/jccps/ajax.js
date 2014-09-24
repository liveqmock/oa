/**
 * @author lancelot 2007-01-22
 * ״̬��Ϣ��ʾ�࣬��var Status=new function()���壬���Ծ�̬�������еķ���
 * һ�������Ϊfunction Status()���������ܾ�̬�������еķ�������Ҫͨ������������
 */
var Status=new function()
{
    this.statusDiv=null;
    
    /**
     * ��ʼ��״̬��ʾ��
     */
	this.init=function()
	{
	    if (this.statusDiv!=null)
	    {
	        return;
	    }
		var body = document.getElementsByTagName("body")[0];
		var div = document.createElement("div");
		div.style.position = "absolute";
		div.style.top = "10%";
		div.style.left = "50%";
		div.style.width = "280px";
		div.style.margin = "-50px 0 0 -100px";		
		div.style.padding = "14px";
		//div.style.backgroundColor = "#353555";
		//div.style.border = "1px solid #CFCFFF";
		//div.style.color = "#CFCFFF";
		div.style.fontSize = "12px";
		div.style.textAlign = "center";
		div.id = "status";
		body.appendChild(div);
		div.style.display="none";
		this.statusDiv=document.getElementById("status");
	}
	
	/**
	 * ����״̬��Ϣ
	 * @param _message:Ҫ��ʾ����Ϣ
	 */	
	this.showInfo=function(_message)
	{	  
	    if (this.statusDiv==null)
	    {
	        this.init();
	    }  
	    this.setStatusShow(true);
	    this.statusDiv.innerHTML = _message;	    
	}
	 
	/**
	 * ����״̬���Ƿ���ʾ
	 * @param _show:booleanֵ��trueΪ��ʾ��falseΪ����ʾ
	 */ 
	this.setStatusShow=function(_show)
	{	  
	    if (this.statusDiv==null)
	    {
	        this.init();
	    } 
	    if (_show)
	    {
	        this.statusDiv.style.display="";
	    }
	    else
	    {
	        this.statusDiv.innerHTML="";
	        this.statusDiv.style.display="none";
	    }
	}
}

/**
 * @author lancelot
 * ���ڴ��ͨ�����Ƽ�ͨ�Ŷ�����࣬��������ͨ����ͬͨ�����������ֲ�ͬ��ͨ�Ŷ���
 */
function HttpRequestObject()
{
    this.chunnel=null;
    this.instance=null;
}

/**
 * @author lancelot
 * ͨ�Ŵ����࣬���Ծ�̬�������еķ���
 */
var Request=new function()
{
    this.showStatus=true;
    
    //ͨ����Ļ���
    this.httpRequestCache=new Array();
    
    /**
     * �����µ�ͨ�Ŷ���
     * @return һ���µ�ͨ�Ŷ���
     */
    this.createInstance=function()
    {
        var instance=null;
        if (window.XMLHttpRequest)
        {
            //mozilla
            instance=new XMLHttpRequest();
            //��Щ�汾��Mozilla�����������������ص�δ����XML mime-typeͷ����Ϣ������ʱ�������ˣ�Ҫȷ�����ص����ݰ���text/xml��Ϣ
            if (instance.overrideMimeType)
            {
                instance.overrideMimeType="text/xml";
            }
        }
        else if (window.ActiveXObject)
        {
            //IE
            var MSXML = ['MSXML2.XMLHTTP.5.0', 'Microsoft.XMLHTTP', 'MSXML2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP'];
            for(var i = 0; i < MSXML.length; i++)
            {
                try
                {
                    instance = new ActiveXObject(MSXML[i]);
                    break;
                }
                catch(e)
                {                    
                }
            }
        }
        return instance;
    }
    
    /**
     * ��ȡһ��ͨ�Ŷ���
     * ��ûָ��ͨ�����ƣ���Ĭ��ͨ����Ϊ"default"
     * �������в�������Ҫ��ͨ���࣬�򴴽�һ����ͬʱ����ͨ���໺����
     * @param _chunnel��ͨ�����ƣ��������ڴ˲�������Ĭ��Ϊ"default"
     * @return һ��ͨ�Ŷ���������ͨ���໺����
     */
    this.getInstance=function(_chunnel)
    {
        var instance=null;
        var object=null;
        if (_chunnel==undefined)//ûָ��ͨ������
        {
            _chunnel="default";
        }
        var getOne=false;
        for(var i=0; i<this.httpRequestCache; i++)
        {
            object=HttpRequestObject(this.httpRequestCache[i]);
            if (object.chunnel==_chunnel)
            {
                if (object.instance.readyState==0 || object.instance.readyState==4)
                {
                    instance=object.instance;
                }
                getOne=true;
                break;                    
            }
        }
        if (!getOne) //�����ڻ����У��򴴽�
        {
            object=new HttpRequestObject();
            object.chunnel=_chunnel;
            object.instance=this.createInstance();
            this.httpRequestCache.push(object);
            instance=object.instance;
        }         
        return instance;
    }
    
    /**
     * �ͻ��������˷�������
     * @param _url:����Ŀ��
     * @param _data:Ҫ���͵�����
     * @param _processRequest:���ڴ����ؽ���ĺ������䶨������ڱ�ĵط�����Ҫ��һ����������Ҫ�����ͨ�Ŷ���
     * @param _chunnel:ͨ�����ƣ�Ĭ��Ϊ"default"
     * @param _asynchronous:�Ƿ��첽����Ĭ��Ϊtrue,���첽����
     */
    this.send=function(_url,_data,_processRequest,_chunnel,_asynchronous)
    {
        if (_url.length==0 || _url.indexOf("?")==0)
        {
            Status.showInfo("����Ŀ��Ϊ�գ�����ʧ�ܣ����飡");
            window.setTimeout("Status.setStatusShow(false)",3000);
            return;
        }
        if (this.showStatus)
        {
            Status.showInfo("�������У����Ժ�......");  
        }  
        if (_chunnel==undefined || _chunnel=="")
        {
            _chunnel="default";
        }
        if (_asynchronous==undefined)
        {
            _asynchronous=true;
        }
        try{
        var instance=this.getInstance(_chunnel);}catch(e){alert(e.message);}
        if (instance==null)
        {
            Status.showInfo("�������֧��ajax�����飡")
            window.setTimeout("Status.setStatusShow(false)",3000);
            return;
        }        
        if (typeof(_processRequest)=="function")
        {
            instance.onreadystatechange=function()
            {
                if (instance.readyState == 4) // �ж϶���״̬
                {
                    if (instance.status == 200) // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
                    {                        
                        _processRequest(instance);
                        Status.setStatusShow(false);
                        Request.showStatus=true;                                    
                    }
                    else
                    {
                        Status.showInfo("���������ҳ�����쳣�����飡");
                        window.setTimeout("Status.setStatusShow(false)",3000);
                    }                    
                }                                
            }            
        }
        //_url��һ��ʱ�̸ı�Ĳ�������ֹ���ڱ�����������ͬ�����������������������
        if (_url.indexOf("?")!=-1)
        {
            _url+="&requestTime="+(new Date()).getTime();
        }
        else
        {
            _url+="?requestTime="+(new Date()).getTime();
        }
        if (_data.length==0)
        {
            instance.open("GET",_url,_asynchronous);
            instance.send(null);
        }
        else
        {
            instance.open("POST",_url,_asynchronous);
            instance.setRequestHeader("Content-Length",_data.length);
            instance.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            instance.send(_data);
        }
    }
    
    /**
     * ���һ��ʱ�������������ֻ�����첽����ֻ����GET��ʽ
     * @param _interval:���������Ժ����
     * @param _url:�����ַ
     * @param _processRequest:���ڴ����ؽ���ĺ������䶨������ڱ�ĵط�����Ҫ��һ����������Ҫ�����ͨ�Ŷ���
     * @param _chunnel:ͨ�����ƣ�Ĭ��Ϊ"defaultInterval"���Ǳ���
     */
    this.intervalSend=function(_interval,_url,_processRequest,_chunnel)
    {
        var action=function()
        {
            if (_chunnel==undefined)
            {
                _chunnel="defaultInterval";
            }
            var instance=Request.getInstance(_chunnel);
            if (instance==null)
            {
                Status.showInfo("�������֧��ajax�����飡")
                window.setTimeout("Status.setStatusShow(false)",3000);
                return;
            }
            if (typeof(_processRequest)=="function")
            {
                instance.onreadystatechange=function()
                {
                    if (instance.readyState == 4) // �ж϶���״̬
                    {
                        if (instance.status == 200) // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
                        {
                            _processRequest(instance);
                        }
                        else
                        {
                            Status.showInfo("���������ҳ�����쳣�����飡");
                            window.setTimeout("Status.setStatusShow(false)",3000);
                        }
                    }
                }
            }
            //_url��һ��ʱ�̸ı�Ĳ�������ֹ���ڱ�����������ͬ�����������������������
            if (_url.indexOf("?")!=-1)
            {
                _url+="&requestTime="+(new Date()).getTime();
            }
            else
            {
                _url+="?requestTime="+(new Date()).getTime();
            }
            instance.open("GET",_url,true);
            instance.send(null);
        }
        window.setInterval(action,_interval);        
    }
}

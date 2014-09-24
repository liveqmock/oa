
function tq_talk(talk_type,frienduin,myuin)
{
	var wsh=false;	
	try{
		wsh=new ActiveXObject("tqtalk.QuanZi");
		if(wsh) 
		{
	
			location.href="tqtalk:<TQ><UIN>"+frienduin+"</UIN><TYPE>"+talk_type+"</TYPE><MYUIN>"+myuin+"</MYUIN></TQ>";	
		
		}
	
	}catch(e)
	{
		if (confirm("您尚未安装新华通客户端,或当前客户端版本太低。\n\r您是否下载并安装新版本客户端？"))
    		{ 
	       document.location.href="/cms/cms/manage/info/attachfile?infoId=115579"; 
	       wsh=false;
     		} 
	  
	} 

	

}



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
		if (confirm("����δ��װ�»�ͨ�ͻ���,��ǰ�ͻ��˰汾̫�͡�\n\r���Ƿ����ز���װ�°汾�ͻ��ˣ�"))
    		{ 
	       document.location.href="/cms/cms/manage/info/attachfile?infoId=115579"; 
	       wsh=false;
     		} 
	  
	} 

	

}


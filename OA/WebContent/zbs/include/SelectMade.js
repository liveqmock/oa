function do_select2()//���"ɾ��"��ť������ɾ������������ѡ��Ķ��󣨿ɶ�ѡ��
{
	var A = new Array(select2.options.length);
	var l=0;
	for(var i=0;i<select2.options.length;i++)
	{
		if (select2.options[i].selected)
		{
			A[l]=i;
			l++;
		}		
	}	
	for (var w=A.length-1;w>0;w--)
	{
		if (A[w]==null){
			A.pop();
		}
	}
	var len= A.length;
	var q;
	for (q=0;q<len;q++)
	{
        select2.options[A[q]]=null;    
		for (var k=q+1;k<len;k++){
			A[k]--;
		}
	}
	if (select2.length>0){
		select2.options[0].selected=true;
	}
}

function db_select()//˫�����˶���,������ӵ������˶�����
{
  if (select1.options.length > 0)
  	{
	    for(var i=0;i<select1.options.length;i++) 
		{
		  if(select1.options(i).selected)
		  {
    		  var oOption = document.createElement("OPTION");
			  if (select2.options.length > 0)//"�����˶���"��Ϊ��
			  {
			    var k=0;
			    for(var j=0;j<select2.options.length;j++)//ѭ�����������˶�����ʾ�ļ�¼
		        {
                  if(select1.options[select1.selectedIndex].value==select2.options(j).value)//���û���ѡ��"���˶���"��¼��"�����˶���"��ļ�¼��ͬ��k��1��,����"�����˶���"�����ѡ��¼
				  {
				  k=k+1;
				  break;
				  }
				}
				if (k<1)//���û���ѡ��"���˶���"��¼��"�����˶���"��ļ�¼����ͬ��k��ֵΪ0��,Ȼ����"�����˶���"�����ѡ��¼
				{
    		       select2.options.add(oOption);
    		       oOption.innerText =select1.options[select1.selectedIndex].innerText;
                   oOption.value =select1.options[select1.selectedIndex].value;
				}
			  }
			  else//"�����˶���"Ϊ��
			  {
			  select2.options.add(oOption);
    		  oOption.innerText =select1.options[select1.selectedIndex].innerText;
              oOption.value =select1.options[select1.selectedIndex].value;
			  }
		  }
		  
		}
    }
}

function db_select2()//˫�������˶���,���Խ���ɾ��
{
  if (select2.options.length > 0) 
  	{
	    for(var i=0;i<select2.options.length;i++) 
		{
		  if(select2.options(i).selected) 
		  {
    		  var oOption = document.createElement("OPTION");
			  select2.options[select2.selectedIndex]=null;
		  }
		  
		}
    }
}

function all_select()//���ȫ����Ӱ�ť�����б��еĶ���ȫ����ӵ������˶�����
{
	  if (select1.options.length > 0)
  	{
	    for(var i=0;i<select1.options.length;i++) 
		{
    		  var oOption = document.createElement("OPTION");
			  if (select2.options.length > 0)//"�����˶���"��Ϊ��
			  {
			    var k=0;
			    for(var j=0;j<select2.options.length;j++)//ѭ�����������˶�����ʾ�ļ�¼
		        {
                  if(select1.options[i].value==select2.options(j).value)//���û���ѡ��"���˶���"��¼��"�����˶���"��ļ�¼��ͬ��k��1��,����"�����˶���"�����ѡ��¼
				  {
				  k=k+1;
				  break;
				  }
				}
				if (k<1)//���û���ѡ��"���˶���"��¼��"�����˶���"��ļ�¼����ͬ��k��ֵΪ0��,Ȼ����"�����˶���"�����ѡ��¼
				{
    		       select2.options.add(oOption);
    		       oOption.innerText =select1.options[i].innerText;
                   oOption.value =select1.options[i].value;
				}
			  }
			  else//"�����˶���"Ϊ��
			  {
			  select2.options.add(oOption);
    		  oOption.innerText =select1.options[i].innerText;
              oOption.value =select1.options[i].value;
			  }
		  }
		  
		}
}

function do_select()//���"���"��ť�����԰���ѡ������ӵ�����������ɶ�ѡ��
{
	var A = new Array(select1.options.length);
	var l=0;
	for(var i=0;i<select1.options.length;i++)
	{
		if (select1.options[i].selected)
		{
			A[l]=i;
			l++;
		}
		
	}	
	for (var w=A.length-1;w>0;w--)
	{
		if (A[w]==null){
			A.pop();
		}
	}
	for (var q=0;q<A.length;q++)
	{
    	  var oOption = document.createElement("OPTION");
		  if (select2.options.length > 0)//"�����˶���"��Ϊ��
		  {
			    var k=0;
			    for(var j=0;j<select2.options.length;j++)//ѭ�����������˶�����ʾ�ļ�¼
		        {
                  if(select1.options[A[q]].value==select2.options(j).value)//���û���ѡ��"���˶���"��¼��"�����˶���"��ļ�¼��ͬ��k��1��,����"�����˶���"�����ѡ��¼
				  {
				  k=k+1;
				  break;
				  }
				}
				if (k<1)//���û���ѡ��"���˶���"��¼��"�����˶���"��ļ�¼����ͬ��k��ֵΪ0��,Ȼ����"�����˶���"�����ѡ��¼
				{
    		       select2.options.add(oOption);
    		       oOption.innerText =select1.options[A[q]].innerText;
                   oOption.value =select1.options[A[q]].value;
				}
			  }
			  else//"�����˶���"Ϊ��
			  {
			  select2.options.add(oOption);
    		  oOption.innerText =select1.options[A[q]].innerText;
              oOption.value =select1.options[A[q]].value;
			  }
		  }
}

function all_del()//����ѡ�еı����˶���ȫ��ɾ��
{
	if (select2.options.length > 0){ 
    	for (var i=select2.options.length;i>=0;i--){
			select2.options[i]=null;
		}
	}
}
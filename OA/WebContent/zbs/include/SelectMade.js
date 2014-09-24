function do_select2()//点击"删除"按钮，可以删除被考核组已选择的对象（可多选）
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

function db_select()//双击考核对象,可以添加到被考核对象里
{
  if (select1.options.length > 0)
  	{
	    for(var i=0;i<select1.options.length;i++) 
		{
		  if(select1.options(i).selected)
		  {
    		  var oOption = document.createElement("OPTION");
			  if (select2.options.length > 0)//"被考核对象"不为空
			  {
			    var k=0;
			    for(var j=0;j<select2.options.length;j++)//循环读出被考核对象显示的记录
		        {
                  if(select1.options[select1.selectedIndex].value==select2.options(j).value)//如用户所选的"考核对象"记录与"被考核对象"里的记录相同（k＋1）,不向"被考核对象"添加所选记录
				  {
				  k=k+1;
				  break;
				  }
				}
				if (k<1)//如用户所选的"考核对象"记录与"被考核对象"里的记录不相同（k的值为0）,然后向"被考核对象"添加所选记录
				{
    		       select2.options.add(oOption);
    		       oOption.innerText =select1.options[select1.selectedIndex].innerText;
                   oOption.value =select1.options[select1.selectedIndex].value;
				}
			  }
			  else//"被考核对象"为空
			  {
			  select2.options.add(oOption);
    		  oOption.innerText =select1.options[select1.selectedIndex].innerText;
              oOption.value =select1.options[select1.selectedIndex].value;
			  }
		  }
		  
		}
    }
}

function db_select2()//双击被考核对象,可以进行删除
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

function all_select()//点击全部添加按钮，将列表中的对象全部添加到被考核对象里
{
	  if (select1.options.length > 0)
  	{
	    for(var i=0;i<select1.options.length;i++) 
		{
    		  var oOption = document.createElement("OPTION");
			  if (select2.options.length > 0)//"被考核对象"不为空
			  {
			    var k=0;
			    for(var j=0;j<select2.options.length;j++)//循环读出被考核对象显示的记录
		        {
                  if(select1.options[i].value==select2.options(j).value)//如用户所选的"考核对象"记录与"被考核对象"里的记录相同（k＋1）,不向"被考核对象"添加所选记录
				  {
				  k=k+1;
				  break;
				  }
				}
				if (k<1)//如用户所选的"考核对象"记录与"被考核对象"里的记录不相同（k的值为0）,然后向"被考核对象"添加所选记录
				{
    		       select2.options.add(oOption);
    		       oOption.innerText =select1.options[i].innerText;
                   oOption.value =select1.options[i].value;
				}
			  }
			  else//"被考核对象"为空
			  {
			  select2.options.add(oOption);
    		  oOption.innerText =select1.options[i].innerText;
              oOption.value =select1.options[i].value;
			  }
		  }
		  
		}
}

function do_select()//点击"添加"按钮，可以把所选对象添加到被考核组里（可多选）
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
		  if (select2.options.length > 0)//"被考核对象"不为空
		  {
			    var k=0;
			    for(var j=0;j<select2.options.length;j++)//循环读出被考核对象显示的记录
		        {
                  if(select1.options[A[q]].value==select2.options(j).value)//如用户所选的"考核对象"记录与"被考核对象"里的记录相同（k＋1）,不向"被考核对象"添加所选记录
				  {
				  k=k+1;
				  break;
				  }
				}
				if (k<1)//如用户所选的"考核对象"记录与"被考核对象"里的记录不相同（k的值为0）,然后向"被考核对象"添加所选记录
				{
    		       select2.options.add(oOption);
    		       oOption.innerText =select1.options[A[q]].innerText;
                   oOption.value =select1.options[A[q]].value;
				}
			  }
			  else//"被考核对象"为空
			  {
			  select2.options.add(oOption);
    		  oOption.innerText =select1.options[A[q]].innerText;
              oOption.value =select1.options[A[q]].value;
			  }
		  }
}

function all_del()//将已选中的被考核对象全部删除
{
	if (select2.options.length > 0){ 
    	for (var i=select2.options.length;i>=0;i--){
			select2.options[i]=null;
		}
	}
}
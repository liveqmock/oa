function issue()//询问是否发布
{
	if (confirm("你确认要发布该通知吗?")){
		alert("发布成功!");
	}
}

function openAttachment(url)//打开附件窗体
{
	var name = "attachment";
	var propertylist = "width=800,height=600,location=no,resizable=yes,menubar=no,toolbar=yes,scrollbars=yes";
	window.open(url,name,propertylist);	
}

function isdel()//询问是否删除
{
	if (confirm("你确认要删除吗?")){
		alert("已删除!");
	}
}

function iscancel()//询问是否取消
{
	if (confirm("你确认要取消吗?")){
		alert("取消成功!");
	}
}

function callback(){//发询问是否回收
	var confirmCallback = window.confirm("确定要回收提交的工作吗？");
	if (confirmCallback) {
		alert("回收提交的工作！");
   	} 
}

function doPass(){//审批通过
		var confirmCallback = window.confirm("确定要审核通过吗？");
		if (confirmCallback) {
			alert("审核已通过！");
    	} 
}

function doNotPass(){
		var confirmCallback = window.confirm("确定要不通过审核吗？");
		if (confirmCallback) {
			alert("审核不通过！");
    	} 		
}
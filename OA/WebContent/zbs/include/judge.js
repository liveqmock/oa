function issue()//ѯ���Ƿ񷢲�
{
	if (confirm("��ȷ��Ҫ������֪ͨ��?")){
		alert("�����ɹ�!");
	}
}

function openAttachment(url)//�򿪸�������
{
	var name = "attachment";
	var propertylist = "width=800,height=600,location=no,resizable=yes,menubar=no,toolbar=yes,scrollbars=yes";
	window.open(url,name,propertylist);	
}

function isdel()//ѯ���Ƿ�ɾ��
{
	if (confirm("��ȷ��Ҫɾ����?")){
		alert("��ɾ��!");
	}
}

function iscancel()//ѯ���Ƿ�ȡ��
{
	if (confirm("��ȷ��Ҫȡ����?")){
		alert("ȡ���ɹ�!");
	}
}

function callback(){//��ѯ���Ƿ����
	var confirmCallback = window.confirm("ȷ��Ҫ�����ύ�Ĺ�����");
	if (confirmCallback) {
		alert("�����ύ�Ĺ�����");
   	} 
}

function doPass(){//����ͨ��
		var confirmCallback = window.confirm("ȷ��Ҫ���ͨ����");
		if (confirmCallback) {
			alert("�����ͨ����");
    	} 
}

function doNotPass(){
		var confirmCallback = window.confirm("ȷ��Ҫ��ͨ�������");
		if (confirmCallback) {
			alert("��˲�ͨ����");
    	} 		
}
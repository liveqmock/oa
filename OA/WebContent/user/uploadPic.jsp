<%@ page contentType="text/html; charset=GBK"%>
<%
String msg =(String)request.getAttribute("msg");
 %>
<html>
<head>
<script>
/**---------�ж��Ƿ���ͼƬ�����ϸ�ʽ��jpg gif bmp,png,jpeg*/
function isPicture(fileName){
       if(fileName!=null && fileName !=""){
      //lastIndexOf���û���������򷵻�Ϊ-1
      if (fileName.lastIndexOf(".")!=-1) {
   	  var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
      var suppotFile = new Array();
   						suppotFile[0] = "jpg";
                        suppotFile[1] = "gif";
                        suppotFile[2] = "bmp";
                        suppotFile[3] = "png";
                        suppotFile[4] = "jpeg";
   for (var i =0;i<suppotFile.length;i++) {
    if (suppotFile[i]==fileType) {
     return true;
    } else{
     continue;
    }
   }
   //alert("�ļ����Ͳ��Ϸ�,ֻ����jpg��gif��bmp��png��jpeg���ͣ�");
   return false;
  } else{
   //alert("�ļ����Ͳ��Ϸ�,ֻ���� jpg��gif��bmp��png��jpeg ���ͣ�");
   return false;
  }
      }
}

function _upload(){
	if(document.uploadform.file1.value!=""){
         if(!isPicture(document.uploadform.file1.value)){
           alert("�ļ����Ͳ��Ϸ�,ֻ����jpg��gif��bmp��png��jpeg���ͣ�");
           return false;
         }else{
         	document.uploadform.submit();
         }
       }


}

</script> 
<title>ͷ���ϴ�</title>
</head>
<body bgcolor=#FFFFFF text=#000000  leftmargin=0 topmargin=40 marginwidth=0  marginheight=0 >
<center>
<h1>ͷ���ϴ�</h1>
<form name="uploadform" method="POST" action="<%=request.getContextPath()%>/servlet/FileUploadServlet"  ENCTYPE=multipart/form-data >
 <table border=1 width=450 cellpadding=4  cellspacing=2 bordercolor=#9BD7FF >
 <tr><td width=100% colspan=2 >
 ͷ��<input name=file1 size=40 type=file unselectable=on >
 </td></tr>
 </table>
 <br/><br/>
 <table>
 <tr><td align=center ><input name=upload  type=button value=��ʼ�ϴ� onclick='_upload()'/></td></tr>
 <tr><td align=center > <font color=red> <% if(msg!=null) out.print(msg); %> </font> </td> </tr>
</table>
<br>
��ע���ϴ���ͼƬ�ļ���С���ܳ���200K����
</form>
</center>
</body>
</html>

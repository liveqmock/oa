<%@ page contentType="text/html; charset=GBK" %>

<html>
<head>
<title>city</title>
<script language="Javascript">
var selprov = new Array();
var selarea = new Array();
</script>
<%
 out.print("<script>");
 //��ÿ��ʡ��������ӵ�������
 out.print("selprov[330000] = new Array(new Option('',''), new Option('..','goUp'), new Option('���ݵ���','330100'), new Option('��������','330200'), new Option('���ݵ���','330300'));");
 out.print("selprov[350000] = new Array(new Option('',''), new Option('..','goUp'), new Option('���ݵ���','350100'), new Option('���ŵ���','350200'), new Option('Ȫ�ݵ���','350300'));");
 out.print("</script>");

 out.print("<script>");
 //��ÿ��ʡÿ�����������������������
 out.print("selarea[330100] = new Array(new Option('',''), new Option('..','goUp'), new Option('������','330101'), new Option('��ɽ��','330102'), new Option('�ຼ��','330102'));");
 out.print("selarea[330200] = new Array(new Option('',''), new Option('..','goUp'), new Option('������','330201'), new Option('��Ҧ��','330202'), new Option('���','330203'));");
 out.print("selarea[350100] = new Array(new Option('',''), new Option('..','goUp'), new Option('������','350101'), new Option('������','350102'), new Option('��xx��','350102'));");
 out.print("</script>");
%>
<script>

</script>
<script>
function prov_chg()
{
 with (document.all){
  if(hprov.value) {
   var ln = harea.options.length;
   while(ln--) {
    harea.options[ln] = null;
   }
   ln = hcity.options.length;
   while(ln--) {
    hcity.options[ln] = null;
   }
   for(var i=0; i<selprov[hprov.value].length; i++){
    harea.add(selprov[hprov.value][i]);
   }
  }
 }
 if(document.form1.hprov.value != "")
 {
  d2.style.display="";
  d1.style.display="";
  d3.style.display="";
 }
 else {
 }
}

function area_chg()
{
 if(document.form1.harea.value == "goUp")
 {
  document.form1.hprov.value = "";
  d1.style.display="";
  d2.style.display="";
  d3.style.display="";
 } else if (document.form1.harea.value != "")
 {
  d1.style.display="";
  d2.style.display="";
  d3.style.display="";
 }
 with (document.all){
  if(harea.value) {
   var ln = hcity.options.length;
   while(ln--) {
    hcity.options[ln] = null;
   }
   for(var i=0; i<selarea[harea.value].length; i++){
    hcity.add(selarea[harea.value][i]);
   }
  }
 }
}

function city_chg() {
 if (document.form1.hcity.value == "goUp")
 {
  d1.style.display="";
  d2.style.display="";
  d3.style.display="";
 }
}

</script>
</head>
<body bgcolor="#ffffff"> 
<form name="form1" method="post" action="./city.jsp"> 
  <table > 
    <tr> </tr> 
    <tr> 
      <td style="display:inline " id="d1"> ʡ��
        <select  id="hprov" onChange="javascipt:prov_chg();" > 
          <option value=""></option> 
<%
 out.print("<option value='330000'>�㽭ʡ</option>");
 out.print("<option value='350000'>����ʡ</option>");
%> 
        </select> </td> 
      <td style="display:inline " id="d2"> ����
        <select  id="harea" onChange="javascipt:area_chg();" > 
          <option value=""></option> 
        </select> </td> 
      <td style="display:inline " id="d3"> ����
        <select  id="hcity" onChange="javascipt:city_chg();"> 
          <option value=""></option> 
        </select> </td> 
    </tr> 

  </table> 
</form> 
</body>
</html>

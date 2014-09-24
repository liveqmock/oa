<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@page import="com.icss.oa.address.vo.SysOrgVO"%>
<%@page import="com.icss.oa.phonebook.vo.PhonePrivPersonSearchVO"%>

<%
	//无页面请求参数
	//获得输出数据
	String path = request.getContextPath();
	String sun_flag = (String)request.getParameter("sun_flag");
	if (sun_flag == null){
		sun_flag = "";
	}
%>


<html>
<head>

<title>新华社办公信息化系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<LINK href="<%=request.getContextPath()%>/include/address.css" rel=stylesheet>
<link href="<%=request.getContextPath()%>/Style/css.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			id="homepagestyle" rel="stylesheet" type="text/css" />
<script language="javascript"> var urlPath = "<%=request.getContextPath()%>"; </SCRIPT>
	
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/address.js"></SCRIPT>






<script language="javascript">

function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}
function checkItem(e, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
     if(!aa[i].checked) return;
    all.checked = true;
  }
}


function _updown(id){
	 var obj=document.getElementById(id);
   if(searchStr(obj.src,"mail_up.jpg")){
			 obj.src='<%=path%>/images/mail_down.jpg';
	 }else{
		  obj.src='<%=path%>/images/mail_up.jpg';
	 }
}

function searchStr(str1,str2){   
   if(str1.search(str2)>=0)   
        return   true;      
        else   
        return   false;    
}   



function _selectOpen(){
	var swidth = window.screen.width;
	var sheight = window.screen.height;
	var width = 800;
	var height = 600;
	var top = (sheight-height)/2 - 30;
	if (top <0){
		top = 0;
	}
	var left = (swidth-width)/2;
	window.open('<%=request.getContextPath()%>/address/sendfile/selectPersonFrame.jsp','addressbook','width='+width+',height='+height+',left='+left+',top='+top+',scrollbars=yes,resizable=yes');
}
function _selectPerson(){
	document.sendForm.sendType.value = "0";
	_selectOpen();
}
function _selectcc(){
	document.sendForm.sendType.value = "1";
	_selectOpen();
}

function _selectbcc(){
	document.sendForm.sendType.value = "2";
	_selectOpen();
}

function _addPerson_hidden(usernamestring){
	document.sendForm.sendto.value = usernamestring;
	alert('fsdfasfasdfasdfasfasfd');
	//window.bottomto.innerHTML = usernamestring;
	return true;	
}

function _addcc(usernamestring){
	document.sendForm.sendcc.value = usernamestring;
	//window.bottomcc.innerHTML = usernamestring;
	return true;	
}

function _addbcc(usernamestring){
	document.sendForm.sendbcc.value = usernamestring;
	//window.bottombcc.innerHTML = usernamestring;
	return true;
}

function _addPerson_hidden1(user_uuid){
	alert(user_uuid);
	document.sendForm.addPerson.value = user_uuid;
	//alert("document.sendForm.addPerson.value "+document.sendForm.addPerson.value);  
}
function _addcc1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addcc.value = user_uuid;
	//alert("document.sendForm.addcc.value "+document.sendForm.addcc.value);
}

function _addbcc1(user_uuid){
	//alert(user_uuid);
	document.sendForm.addbcc.value = user_uuid;
	//alert("document.sendForm.addbcc.value "+document.sendForm.addbcc.value);
}




function changeStyle(id){
	//alert("dddd");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}


function setCookie(name,value){
	document.cookie=name+"="+value+",";
}


function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(",")); 
		var value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(","));
		return value;
	}else{
		return "grey";
	}
}



function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
}
initstyle();




function MM_findObj(n, d) { //v4.01
	var p,i,x;  
	if(!d) d=document; 
	if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if(!(x=d[n])&&d.all) x=d.all[n]; 
	for (i=0;!x&&i<d.forms.length;i++) 
		x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
		x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); 
	return x;
}




function _showAlertBox() { //v6.0
	var i,p,v,obj,
	args=_showAlertBox.arguments;
	for (i=0; i<(args.length-2); i+=3) {
		if ((obj=MM_findObj(args[i]))!=null) { 
			v=args[i+2];
			if (obj.style) { 
				obj=obj.style; 
				v=(v=='show')?'visible':(v=='hide')?'hidden':v; 
			}
			obj.visibility=v; 
		}
	}
}


function showprivtr(){
	document.getElementById("privtr1").style.display="block";
	document.getElementById("privtr2").style.display="block";
	document.getElementById("privtr3").style.display="none";
	
}
function hideprivtr(){
	document.getElementById("privtr1").style.display="none";
	document.getElementById("privtr2").style.display="none";
	document.getElementById("privtr3").style.display="block";
}
</script>


<script language="javascript">
//部门频道浮动窗口
var disappeardelay=150 //弹出菜单延迟时间 
var enableanchorlink=1 //Enable or disable the anchor link when clicked on? (1=e, 0=d) 
var hidemenu_onclick=1 //hide menu when user clicks within menu? (1=yes, 0=no) 

var ie5=document.all 
var ns6=document.getElementById&&!document.all 

function getposOffset(what, offsettype){ 
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop; 
var parentEl=what.offsetParent; 
while (parentEl!=null){ 
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop; 
parentEl=parentEl.offsetParent; 
} 
return totaloffset; 
} 

function showhide(obj, e, visible, hidden){ 
if (ie5||ns6) 
dropmenuobj.style.left=dropmenuobj.style.top=-500 
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover") 
obj.visibility=visible 
else if (e.type=="click") 
obj.visibility=hidden 
} 

function iecompattest(){ 
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body 
} 

function clearbrowseredge(obj, whichedge){ 
var edgeoffset=0 
if (whichedge=="rightedge"){ 
var windowedge=ie5 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15 
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth 
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure) 
edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth 
} 
else{ 
var windowedge=ie5 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18 
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight 
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure) 
edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight 
} 
return edgeoffset 
} 

function dropdownmenu(obj, e, dropmenuID){

if (window.event) event.cancelBubble=true 
else if (e.stopPropagation) e.stopPropagation()

if (typeof dropmenuobj!="undefined") //hide previous menu 
{
//是否已经有展开的菜单
	dropmenuobj.style.visibility="hidden"
	clearhidemenu() 
}

if (ie5||ns6){ 
	obj.onmouseout=delayhidemenu
	//将传入的对象设置为活动对象
	dropmenuobj=document.getElementById(dropmenuID)
	
	if (hidemenu_onclick) {
		dropmenuobj.onclick=function(){dropmenuobj.style.visibility='hidden'} 
	}
	
	dropmenuobj.onmouseover=clearhidemenu 
	dropmenuobj.onmouseout=ie5? function(){ dynamichide(event)} : function(event){ dynamichide(event)} 
	showhide(dropmenuobj.style, e, "visible", "hidden") 
	
	dropmenuobj.x=getposOffset(obj, "left")
	dropmenuobj.y=getposOffset(obj, "top") 
	dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px" 
	dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px" 
} 
//return clickreturnvalue() 
} 



function clickreturnvalue(){ 
if ((ie5||ns6) && !enableanchorlink) return false 
else return true 
} 

function contains_ns6(a, b) { 
while (b.parentNode) 
if ((b = b.parentNode) == a) 
return true; 
return false; 
} 

function dynamichide(e){ 
if (ie5&&!dropmenuobj.contains(e.toElement)) 
delayhidemenu() 
else if (ns6&&e.currentTarget!= e.relatedTarget&& !contains_ns6(e.currentTarget, e.relatedTarget)) 
delayhidemenu() 
} 

function delayhidemenu(){ 
delayhide=setTimeout("dropmenuobj.style.visibility='hidden'",disappeardelay) 
} 

function clearhidemenu(){ 
if (typeof delayhide!="undefined") 
clearTimeout(delayhide) 
} 
</script>


<script>

function edit(privname,pptype,ppscope,pplevel,ppsealevel,ppid,personuuid,ppname)
{
  var app='';
  personuuidarr=new Array();
  ppnamearr=new Array();
  if(personuuid!=null){
    personuuidarr=personuuid.split(",");
    ppnamearr=ppname.split(",");
  }
  
  if(personuuidarr.length>0&&ppnamearr.length>0&&personuuidarr.length==ppnamearr.length)
  {
     for(var i=0;i<ppnamearr.length;i++)
       app=app+"<span class=\"send\"  person=\" \" department=\" \" personType=\"" +"0"+ "\" personName=\"" + ppnamearr[i] + "\" uuid=\"" +personuuidarr[i]+ "\" onclick=\"selectName(this," + "0" + ");\" > <img src=\"" + urlPath + "/images/person.gif"+ "\">" +ppnamearr[i]+ ",</span>";
  }
  document.all.sendto.innerHTML="";
  document.all.sendto.innerHTML=document.all.sendto.innerHTML+app;

  document.all.phoneprivname.value=privname;
  document.all.privtype.value=pptype;
  if(pptype==0)
    showprivtr();
  else
    hideprivtr();
  document.all.privtype[pptype].checked=true;
  document.all.pp_id.value=ppid;
  document.all.scope.value=ppscope;
  
  pplevelarr = new Array();
  pplevelarr=pplevel.split(",");
  var check1= document.getElementsByName("joblevel");
  
  ppsealevelarr = new Array();
  ppsealevelarr=ppsealevel.split(",");
  var check2= document.getElementsByName("showjoblevel");

  for(var i=0;i<check1.length;i++)
    check1[i].checked=false;
  for(var i=0;i<check2.length;i++)
    check2[i].checked=false;
    
  for(var i=0;i<check1.length;i++)
  {
     for(var j=0;j<pplevelarr.length;j++)
     {
        
        if((check1[i].value).substring(0,(check1[i].value).length-1)==pplevelarr[j])
        {
           check1[i].checked=true;
        }
     }
  }
  
  for(var i=0;i<check2.length;i++)
  {
     for(var j=0;j<ppsealevelarr.length;j++)
     {
        
        if((check2[i].value).substring(0,(check2[i].value).length-1)==ppsealevelarr[j])
        {
           check2[i].checked=true;
        }
     }
  }
}


function searchpriv()
{
    test();
    sendForm.action="/oabase/servlet/PhonebookprivServlet?from=search"
    sendForm.submit()
}

function addpriv()
{
    test();
    if(document.all.phoneprivname.value==""){
      alert('请输入权限名称');}
    else{
        sendForm.action="/oabase/servlet/AddPhonePrivServlet"
        sendForm.submit()
    }
}

function updatepriv()
{
  test();
  if(document.all.pp_id.value=="")
    alert('请单击您所要修改的电话记录！');
  else
  { 
     if(document.all.phoneprivname.value==""){
      alert('请输入权限名称');}
     else{
          sendForm.action="/oabase/servlet/UpdatePhonePrivServlet"
          sendForm.submit()
     }
  }
}


function delpriv()
{
      test();
      if(document.all.mm.value>0)
      { 
         var appid=document.all.mm.value
         sendForm.action="/oabase/servlet/DelPhonePrivServlet?appids="+appid
         if(confirm("您确定要删除所选择的电话记录吗?"))
            sendForm.submit()
      }
      else
      {
         var aa = document.all.mm;
         var pstr="";
         var flag=false;
         for (var i=0;i<aa.length;i++)
         {
             if(aa[i].checked==true)
             {flag=true;
              if(pstr!="")
                pstr=pstr+",";
              pstr=pstr+aa[i].value;
             }
         }
         if(flag==true)
         {
           if(confirm("您确定要删除所选择的记录吗?")){
             sendForm.action="/oabase/servlet/DelPhonePrivServlet?appids="+pstr;
             sendForm.submit()
           }
         }
         else
            alert("请单击左侧复选框选择您要删除的电话记录！");
      }
}

function test()
{
  var str=document.all.sendto.innerHTML;
  var useruuid="",personname="";
  var p=0,q=0,i=0;
  while((p=str.indexOf("uuid",p))!=-1)
  {   
      if(i++>0)
        useruuid+=",";
      q=str.indexOf('"',p+6);
      useruuid=useruuid+str.substring(p+6,q);
      p=q+1;
  }
  p=0,q=0,i=0;
  while((p=str.indexOf("personName",p))!=-1)
  {
      if(i++>0)
        personname+=",";
      q=str.indexOf('"',p+12);
      personname=personname+str.substring(p+12,q);
      p=q+1;
  }
  document.all.username.value=personname;
  document.all.useruuid.value=useruuid;
}

function delperson(ppid,personuuid)
{
   sendForm.action="/oabase/servlet/DelPhonePrivPersonServlet?ppid="+ppid+"&personuuid="+personuuid;
   sendForm.submit()
}


</script>

<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>

<body >

<form method="post" name="sendForm" >
 <!--以下为发送邮件的时候为传人或分组 -->
	<input type="hidden" name="addPerson_person">
	<input type="hidden" name="addPerson_group">
	<input type="hidden" name="addPerson_org">
	<input type="hidden" name="addcc_person">
	<input type="hidden" name="addcc_group">
	<input type="hidden" name="addcc_org">
	<input type="hidden" name="addbcc_person">
	<input type="hidden" name="addbcc_group">
	<input type="hidden" name="addbcc_org">
	<input type="hidden" name="sendType" value="">
	<input type="hidden" name="sendMail" value="1">
	
	
	<input type="hidden" name="donext">
    <input type="hidden" name="username">
    <input type="hidden" name="useruuid">

<div id="alertbox1" style="position:absolute; width:196px; height:24px; z-index:1; left: 320px; top: 100px;visibility: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
		<tr bgcolor="#EEFFF7">
			<td height="25" align="center" id="showAlert"></td>
		</tr>
	</table>
</div>  



<!--头-->
<jsp:include page="/include/top.jsp" />
<!--头-->
<table height="5" width="100%">
	<tr><td height="5"><img src="../images/kongbai.jpg" height="5"/></td></tr>
</table>


<%
  List alljob=(ArrayList)request.getAttribute("joblist");
  List allchuorg =(ArrayList)request.getAttribute("allchuorg");
  List phoneprivList =(ArrayList)request.getAttribute("phoneprivList");
  Map privmap = (Map)request.getAttribute("privmap");
%>

<!--检索条件区-->
<table width="100%">
	<tr>
    	<td width="10"></td>
  		<td width="90%">
        	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor">
            	<tr class="block_title">
                	<td colspan="10">按职级进行权限设置</td>
                </tr>
                <tr>
                	<td width="12%" bgcolor="#FFFFFF" class="table_title">权限名称</td>
                  <td width="33%" bgcolor="#FFFFFF" class="table_title"><span class="content">
                    <input name="phoneprivname" type="text" class="biankuang" size="20"/>
                  </span></td>
                  <td width="13%" bgcolor="#FFFFFF" class="table_title">类别</td>
                  <td width="42%" bgcolor="#FFFFFF" class="message_title"><table width="200">
                    <tr>
                      <td width="63" bgcolor="#FFFFFF" class="message_title"><label>
                        <input type="radio" selected name="privtype" value="0" onClick="showprivtr()" checked="checked" />
                      按职级</label></td>
                      <td width="125" class="message_title"><label>
                        <input type="radio" name="privtype" value="1" onClick="hideprivtr()"/>
                      特殊</label></td>
                    </tr>
                  </table>
                  </td>
				  
				  </tr>
                <tr>
                	<td bgcolor="#FFFFFF" class="table_title">范围</td>
                  <td bgcolor="#FFFFFF" class="message_title_bold">
                  <select name="scope" id="scope">
                      <option value="0">全社</option>
                      <option value="-1">本部门</option>
                     <% 
                       Iterator it=allchuorg.iterator();
				        while(it.hasNext())
				        {
				          SysOrgVO vo = (SysOrgVO)it.next();
				          %>
				            <option value=<%=vo.getOrguuid()%>><%=vo.getCnname()%></option>
			          <%}%>
                  </select>                  </td>
                    <td bgcolor="#FFFFFF" class="table_title">&nbsp;
                    <input type="hidden" name=pp_id  id="pp_id"/>
                    </td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
              </tr>
              <tr id=privtr1>
                	<td bgcolor="#FFFFFF" class="table_title">职级</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title">
                        <%it=alljob.iterator();
				         while(it.hasNext())
				         {%>
				             
				            <%String temstr = (String)it.next();
				             String jobcode="",job="";
				             jobcode=temstr.substring(0,temstr.indexOf(","));
				             job=temstr.substring(temstr.indexOf(",")+1);%>
				             <input type="checkbox" name="joblevel" id="joblevel" value=<%=jobcode%>/>
				             <%=job%>
			             <%}%>
                    </td>
              </tr>
              
              
               <tr id=privtr2>
                		<td bgcolor="#FFFFFF" class="table_title">查看职级</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title">
                        <%it=alljob.iterator();
				         while(it.hasNext())
				         {%>
				            <%String temstr = (String)it.next();
				             String jobcode="",job="";
				             jobcode=temstr.substring(0,temstr.indexOf(","));
				             job=temstr.substring(temstr.indexOf(",")+1);%>
				             <input type="checkbox" name="showjoblevel" id="showjoblevel" value=<%=jobcode%>/>
				             <%=job%>
			             <%}%>
                    </td>
              </tr>
              
              <tr id=privtr3 style="display:none">
               	 <td bgcolor="#FFFFFF" class="table_title">权限人员</td>
               	
                 <td colspan="3" bgcolor="#FFFFFF" class="message_title">
                     <a style="cursor:hand" onClick="javascript:_selectPerson()"></a>  

                     <table width="750" border="0" cellspacing="0" cellpadding="0">
                     			<tr>
          	                     <td colspan="2" bgcolor="#FFFFFF">
                                 <table width="100%" border="0" cellpadding="0" cellspacing="0">
      		                       <tr>
                                         <td colspan="4" align="left" bgcolor="#FFFFFF" class="message_title"> 
                                           <table border="0" cellspacing="0" cellpadding="0" width="53%">
                                             <tr>
                                               <td width="490" height="40" style="width:500px"><span id="sendto" class="sendToText" onselectstart="return(false)" state="0"></span><img src="<%=request.getContextPath()%>/images/delPerson.gif" onClick="javascript:delSel(0)" alt="删除" title="删除" style="cursor: hand">
                                               <input type="button" style="cursor:hand" class="message_title" value="选择权限人员" onClick="javascript:_selectPerson()"></td>
                                             </tr>
                                           </table></td>
                                   </tr>
                                  </table>
                                 </td>
                               </tr>
                     </table>
 
                 </td>
              </tr>
              
              
            </table>
			
            <table border="0" cellpadding="0" cellspacing="0">
            	<tr><td height="5"></td></tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            	<tr><td height="30">
            	  <input type="button" name="Submit2" onclick="addpriv()" value="添加">
            	  <input type="button" name="Submit3" onclick="delpriv()" value="删除">
            	  <input type="button" name="Submit" onclick="updatepriv()" value="修改">
          	      <input type="button" name="Submit" onclick="searchpriv()" value="查询">
            	</td>
            	</tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="0">
            	<tr><td height="5"></td></tr>
            </table>
            
            <!--电话簿展示区-->
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
               <tr>
			   
			        <td width="5%" height="25" align="center" class="block_title">
                    全选
                    <input type=checkbox name=mmAll id=mmAll onclick="checkAll(this,'mm')">
                </td>
                	<td width="17%" align="center"  class="block_title">权限名称</td>
                    <td width="12%" align="center" class="block_title">类别</td>
                    <td width="14%" align="center" class="block_title">范围</td>
                    <td width="13%" align="center" class="block_title">职级</td>
                    <td width="15%" align="center" class="block_title">查看职级</td>
                    <td width="29%" align="center" class="block_title">权限人员</td>
              </tr>
          
              <% 
                    Iterator it2=phoneprivList.iterator();
                    String[] pp_levelarr;
                    String prepp_id="",prepp_levelname="",prepp_searchlevelname="",prepp_name="",preperson_name="",prepp_scope="",prepp_scopeid="",prepp_type="";
                    String prepp_level="",prepp_searchlevel="",prepersonuuid="";
                    
                    if(it2.hasNext()){
                    PhonePrivPersonSearchVO vo=(PhonePrivPersonSearchVO)it2.next();
                    if(vo.getPp_name()!=null)
                      prepp_name=vo.getPp_name();
                   
                    if(vo.getPersonuuid()!=null) 
                      prepersonuuid=vo.getPersonuuid();
                    
                    if(vo.getPersonname()!=null)
                      preperson_name=vo.getPersonname();
                    
                    if(vo.getPp_id()!=null)
                      prepp_id=vo.getPp_id().toString();
                    
                    if(vo.getPp_type()!=null)
                      prepp_type=vo.getPp_type();
                      
                    if(vo.getPp_scope()!=null){
                      prepp_scope=vo.getPp_scope();
                      String[] scopearr=prepp_scope.split(",");
                       if(scopearr.length==2){
                         prepp_scope=scopearr[1];
                         prepp_scopeid=scopearr[0];
                       }
                    }
                      
                    if(vo.getPp_level()!=null)
                      prepp_level=vo.getPp_level();
                      
                    if(vo.getPp_searchlevel()!=null)
                      prepp_searchlevel=vo.getPp_searchlevel();
                    
                    if(vo.getPp_level()!=null&&!"".equals(vo.getPp_level()))
                    {
                         pp_levelarr=(vo.getPp_level()).split(",");
                         for(int i=0;i<pp_levelarr.length;i++){
                               if(i>0)
                                 prepp_levelname+=",";
                               prepp_levelname+=privmap.get(pp_levelarr[i]);
                         }
                    }
                    
                    if(vo.getPp_searchlevel()!=null&&!"".equals(vo.getPp_searchlevel()))
                    {
                         pp_levelarr=(vo.getPp_searchlevel()).split(",");
                         for(int i=0;i<pp_levelarr.length;i++){
                               if(i>0)
                                 prepp_searchlevelname+=",";
                               prepp_searchlevelname+=privmap.get(pp_levelarr[i]);
                         }
                    }
                
                    
                 while(it2.hasNext())
                 {
                     vo=(PhonePrivPersonSearchVO)it2.next();
                    String pp_levelname="",pp_searchlevelname="",pp_name="",person_name="",pp_scope="",pp_scopeid="",pp_type="",pp_id="";
                    String pp_level="",pp_searchlevel="",personuuid="";
                    if(vo.getPp_name()!=null)
                      pp_name=vo.getPp_name();
                      
                    if(vo.getPersonuuid()!=null)
                      personuuid=vo.getPersonuuid();
                      
                    if(vo.getPersonname()!=null)
                      person_name=vo.getPersonname();
                    
                    if(vo.getPp_id()!=null)
                      pp_id=vo.getPp_id().toString();
                      
                    if(vo.getPp_type()!=null)
                      pp_type=vo.getPp_type();
                      
                    if(vo.getPp_scope()!=null)
                    {
                       pp_scope=vo.getPp_scope();
                       String[] scopearr=pp_scope.split(",");
                       if(scopearr.length==2){
                         pp_scope=scopearr[1];
                         pp_scopeid=scopearr[0];
                         }
                    }
                      
                    if(vo.getPp_level()!=null)
                      pp_level=vo.getPp_level();
                      
                    if(vo.getPp_searchlevel()!=null)
                      pp_searchlevel=vo.getPp_searchlevel();
                    
                    if(vo.getPp_level()!=null&&!"".equals(vo.getPp_level()))
                    {
                         pp_levelarr=(vo.getPp_level()).split(",");
                         for(int i=0;i<pp_levelarr.length;i++){
                               if(i>0)
                                 pp_levelname+=",";
                               pp_levelname+=privmap.get(pp_levelarr[i]);
                         }
                    }
                    
                    if(vo.getPp_searchlevel()!=null&&!"".equals(vo.getPp_searchlevel()))
                    {
                         pp_levelarr=(vo.getPp_searchlevel()).split(",");
                         for(int i=0;i<pp_levelarr.length;i++){
                               if(i>0)
                                 pp_searchlevelname+=",";
                               pp_searchlevelname+=privmap.get(pp_levelarr[i]);
                         }
                    }
                    
                    if(prepp_id.equals(pp_id))
                    {
                       preperson_name=preperson_name+","+person_name;
                       prepersonuuid=prepersonuuid+","+personuuid;
                    }
                    else
                    {             
              %>
               <tr onClick=edit('<%=prepp_name%>','<%=prepp_type%>','<%=prepp_scopeid%>','<%=prepp_level%>','<%=prepp_searchlevel%>','<%=prepp_id%>','<%=prepersonuuid%>','<%=preperson_name%>')>
			        <td width="5%" height="25" align="center" bgcolor="#FFFFFF" class="message_title" )>
                         <input type=checkbox name="mm" value=<%=prepp_id%> onclick="checkItem(this,'mmAll')" />       
                    </td>
                	<td style="cursor:hand" width="17%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_name%>
                    </td>
                    <td style="cursor:hand" width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%
                       if(prepp_type.equals("0"))
                         out.println("按职级");
                       else
                         out.println("特殊");
                     %>
                    </td>
                    <td style="cursor:hand" width="14%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_scope%>
                    </td>
                    <td style="cursor:hand" width="13%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_levelname%>
                    </td>
                    <td style="cursor:hand" width="15%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_searchlevelname%>
                    </td>
                    <td style="cursor:hand" width="29%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%
                        if(preperson_name.length()>0&&prepersonuuid.length()>0){
                         String pernarr[]=preperson_name.split(",");
                         String peridarr[]=prepersonuuid.split(",");
                         if(pernarr.length==peridarr.length){
                         for(int i=0;i<pernarr.length;i++)
                         {%>
                           <%=pernarr[i]%>
                           <img src="<%=request.getContextPath()%>/images/delPerson.gif" onClick="delperson('<%=prepp_id%>','<%=peridarr[i]%>')" alt="删除" title="删除" style="cursor: hand">     
                       <%
                           if(i<pernarr.length-1)
                           {%>
                             <%=","%>
                         <%} 
                        }
                        }
                       }
                     %>
                    </td>
              </tr>
              <%
                  prepp_id=pp_id;
                  prepp_levelname=pp_levelname;
                  prepp_searchlevelname=pp_searchlevelname;
                  prepp_level=pp_level;
                  prepp_searchlevel=pp_searchlevel;
                  prepersonuuid=personuuid;
                  prepp_name=pp_name;
                  preperson_name=person_name;
                  prepp_scopeid=pp_scopeid;
                  prepp_scope=pp_scope;
                  prepp_type=pp_type;
                 }
              }
              %>
                   <tr onClick=edit('<%=prepp_name%>','<%=prepp_type%>','<%=prepp_scopeid%>','<%=prepp_level%>','<%=prepp_searchlevel%>','<%=prepp_id%>','<%=prepersonuuid%>','<%=preperson_name%>')>
			        <td width="5%" height="25" align="center" bgcolor="#FFFFFF" class="message_title" )>
                         <input type=checkbox name="mm" value=<%=prepp_id%> onclick="checkItem(this,'mmAll')" />       
                    </td>
                	<td style="cursor:hand" width="17%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_name%>
                    </td>
                    <td style="cursor:hand" width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%
                       if(prepp_type.equals("0"))
                         out.println("按职级");
                       else
                         out.println("特殊");
                     %>
                    </td>
                    <td style="cursor:hand" width="14%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_scope%>
                    </td>
                    <td style="cursor:hand" width="13%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_levelname%>
                    </td>
                    <td style="cursor:hand" width="15%" align="center" bgcolor="#FFFFFF" class="message_title">
                     <%=prepp_searchlevelname%>
                    </td>
                    <td style="cursor:hand" width="29%" align="center" bgcolor="#FFFFFF" class="message_title">
                       <%
                        if(preperson_name.length()>0&&prepersonuuid.length()>0){
                         String pernarr[]=preperson_name.split(",");
                         String peridarr[]=prepersonuuid.split(",");
                         if(pernarr.length==peridarr.length){
                         for(int i=0;i<pernarr.length;i++)
                         {%>
                           <%=pernarr[i]%>
                           <img src="<%=request.getContextPath()%>/images/delPerson.gif" onClick="delperson('<%=prepp_id%>','<%=peridarr[i]%>')" alt="删除" title="删除" style="cursor: hand">     
                       <%
                           if(i<pernarr.length-1)
                           {%>
                             <%=","%>
                         <%} 
                        }
                        }
                       }
                     %>
                    </td>
              </tr>
              <%} %>
          </table>
            <!--电话簿展示区-->
      </td>
        <td width="10"></td>
    </tr>
</table>

<!--检索条件区-->
</form>
<%   
   String tips=request.getParameter("tips");
   if(tips!=null&&"added".equals(tips))
   {
      out.println("<script>alert('添加成功')</script>");
      tips="";
   }
   else if(tips!=null&&"deleted".equals(tips))
   {
      out.println("<script>alert('删除成功')</script>");
      tips="";
   }
   else if(tips!=null&&"updated".equals(tips))
   {
      out.println("<script>alert('修改成功')</script>");
      tips="";
   }
%>
</body>
</html>





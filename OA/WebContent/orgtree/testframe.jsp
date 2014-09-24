<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.orgtree.handler.*"%>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.anylinkcss{ 
position:absolute; 
visibility: hidden; 
z-index: 100; 
} 
-->
</style>
<link href="../Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />


<script  language="javascript">
function changeStyle(id){//切换样式
	document.getElementById("homepagestyle").href = "../Style/css_"+id+".css";
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
	document.getElementById("homepagestyle").href = "../Style/css_"+style+".css";
}

initstyle();
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

<script language="javascript">
function change_view(obj_name)
{
var aa=document.getElementById(obj_name);
if(aa.style.display=="")
  {
    aa.style.display="none";
    ch.innerHTML="<a href=# onClick=change_view('menu')>显示组织结构树</a>";
  }
  else
  {
    ch.innerHTML="";
    aa.style.display="";
   }
}
function setmenu(url)
{
    window.top.frames["menufrm"].location.href=url;
}


</script>



<html>
<head>
<title>组织树</title>

<body>

<!--头-->
<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="537" class="top_logo" height="85"></td>
    <td class="top_back" height="85" width="121">&nbsp;</td>
    <td width="325" class="top_back">
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
        	  <td class="top_function" width="390" height="31"></td>
        	</tr>
            <tr>
              <td class="top_left" width="390" height="54"></td>
            </tr>
        </table>    
    </td>
    </tr>
    </table>
    </td>
    <td width="10" >&nbsp;</td>
  </tr>
</table>

<table width="1003" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr>
    	<td width="10"></td>
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="top_back">
            	<tr>
                	<td width="134" class="top_left_buttom" height="29"></td>
                  <td width="60%" class="top_back_buttom">
               	  <table width="98%">
                        <tr>
                            <td width="34%" height="20" class="message_zhuanti">2008年3月3日 星期一</td>
                          <td width="42%" align="right" class="content"><input name="searchfield2" type="text" class="biankuang" size="26" value="请输入检索关键字"/></td>
                          <td width="9%" class="message_zhuanti" style="cursor:hand">&gt;&gt;检索</td>
                          <td width="15%" class="content"><a href="searchList.html" target="_blank" class="message_zhuanti" style="text-decoration:none">&gt;&gt; 高级检索</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="点击选择页面色彩！" bgcolor="#FFFFFF">
                      <tr>
                        <td width="30"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="red" style="cursor:hand;" onClick="changeStyle('red')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#d0e9cb" style="cursor:hand;" onClick="changeStyle('lake')"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ffd4ad" onClick="changeStyle('orange')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#ddc99d" onClick="changeStyle('brown')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#afd5ae" onClick="changeStyle('green')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="15" height="15" bgcolor="#CCCCCC" onClick="changeStyle('grey')" style="cursor:hand;"></td>
                            </tr>
                        </table></td>
                        <td width="10"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
          </table>
        </td>
        <td width="10"></td>
    </tr>
</table>
<!--头-->

        <table width="100%" height="100%" border="0">
           <tr height=450>
             <td width="20%" id=menu>
                  <iframe name="menufrm" src="/oabase/servlet/MyOrgTreeServlet" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
             </td>
    
             <td width="80%" id=main align=left>
	            <iframe name="mainFrame" id="mainFrame" scrolling="auto" src="/oabase/servlet/MyGetUPhoneServlet" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
             </td>
           </tr>
        </table>

</body>
</html>







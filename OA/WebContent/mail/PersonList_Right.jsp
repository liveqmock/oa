 <%@ page contentType="text/html; charset=GBK" %>
  <%@ page pageEncoding="GBK" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@	page import="com.icss.oa.address.vo.AddressGroupinfoVO"%>
<%@	page import="com.icss.oa.address.vo.SysPersonVO"%>
<%@ page import="java.util.List"%>
 <%

	String path = request.getContextPath();
	List grouplist = (List)request.getAttribute("grouplist");
	List useringrouplist = (List)request.getAttribute("useringrouplist");
	List presentcontact = (List)request.getAttribute("presentcontact");
%>

<html>
<head>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

A:LINK{TEXT-DECORATION:NONE}
A:VISITED{TEXT-DECORATION:NONE}
A:ACTIVE{COLOR:#FF0000; TEXT-DECORATION:UNDERLINE}
A:HOVER {COLOR:#FF0000; TEXT-DECORATION:UNDERLINE}
-->
</style>
 <link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
 <script language="javascript">
function _updown(id,tr){
	 var obj=document.getElementById(id);
	 var visableobj=document.getElementById(tr);
   if(searchStr(obj.src,"mail_up.jpg")){
			 obj.src='<%=path%>/images/mail_down.jpg';
			visableobj.style.display="block";
	 }else{
		  obj.src='<%=path%>/images/mail_up.jpg';
		  visableobj.style.display="none";
	 }
	//frameautoheight();
}

function searchStr(str1,str2){   
   if(str1.search(str2)>=0)   
        return   true;      
        else   
        return   false;    
}   

function _addgroupuser(uuid,cname){
	var htmlstr= null ;
	if(!parent.checksame(uuid)){
		alert(cname+"已经在发送列表中存在！");
		return;
	}
	if(parent.checkmax()){
		if(parent.document.getElementById("sendto").className == "selectText"){
			parent.addPerson(cname, uuid, 'sendto', '0');
		}else if(parent.document.getElementById("sendcc").className == "selectText"){
			parent.addPerson(cname, uuid, 'sendcc', '1');
		}else if(parent.document.getElementById("sendbcc").className == "selectText"){
			parent.addPerson(cname, uuid, 'sendbcc', '2');
		}
	}
	parent.changepnum();
}

function personalgroup(){
	 window.top.location.href ="<%=path%>/mail/IndiGroupMain.jsp";
	//window.top.document.bodyfrm.location.href = "<%=path%>/servlet/ListGroupServlet";
	//window.top.document.leftfrm.location.href = "<%=path%>/servlet/IndiLeftServlet";
}

function changeStyle(id){//切换样式
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//切换样式时设置COOKIE
	var cookies = document.cookie;
	var setcookies = "";
	var lastcookies = "";
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	
	var deltime = new Date();
	daltime = exp.setTime (exp.getTime() - 1);    

	while(cookies.indexOf(";")>=0){
		var tempcookie = cookies.substring(0,cookies.indexOf(";"));
		cookies = cookies.substring(cookies.indexOf(";")+1);
		
		if(tempcookie.indexOf(name)>=0){
			//删除原来的COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//设置新的样式
	document.cookie = name+"="+value+";expires="+exp.toGMTString()+";path=/;domain=10.102.1.40";
}

function getCookie(name){
	var cook =document.cookie;
	//alert(cook);
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(";"));
		var value = "grey";
		if(cook1.indexOf(";")>0){ 
			value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";"));
		}else{
			value = cook1.substring(cook1.indexOf("=")+1);
		}
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+style+".css";
}

initstyle();
</script>


</head>
<form>
  <body>
 <div style="height:100px;">
 <table border="0" cellpadding="0" cellspacing="1" width="100%" align="center" bgcolor="#FFFFFF" class="table_bgcolor">
        <tr>
              <td class="block_title" align=center>个人通讯录&nbsp;&nbsp;&nbsp;<a href="#" style="cursor:hand" class="message_title" onclick="javascript:personalgroup()">[管理]</a></td>
				</tr>
				<tr>
					<td>
            	<table width="100%" height="400" border="0" align="center" cellpadding="0" cellspacing="0"  class="table_bgcolor">
               	  <tr>
                  	<td bgcolor="#FFFFFF" valign="top">
                  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        	<tr>
                            	<td height="20" width="30" align="right" valign="middle"><img id="contactImg" src="<%=path%>/images/mail_up.jpg" />&nbsp;</td>
                                <td class="message_title_bold"><a class="message_title_bold" style="text-decoration:none" href="javascript:_updown('contactImg','persentcontact')">最近联系人</a></td>
                            </tr>

							<tr>
							<td colspan="2" style="display:none" id="persentcontact">
							<table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
							<%
					
								int contactnum= presentcontact.size();
								for(int i=0;i<contactnum;i++){
									SysPersonVO sp = (SysPersonVO)presentcontact.get(i);
							%>
							<tr >
                   
                                <td class="message_title" style="cursor:hand">&nbsp;&nbsp;&nbsp;<span class="message_title" style="text-decoration:none" onClick="javascript:_addgroupuser('<%=sp.getPersonuuid()%>','<%=sp.getCnname()%>')"><%=sp.getCnname()%></span></td>
                            </tr>
							<%}%>
							</table>
						   </td>
						   </tr>
                           <%
								int groupnum = grouplist.size();
								for(int i=0;i<groupnum;i++){
								     AddressGroupVO ag = (AddressGroupVO)grouplist.get(i);
                           %>
                            <tr>
                            	<td height="20" width="30" align="right" valign="middle"><img id="kaoheImg<%=i%>" src="<%=path%>/images/mail_up.jpg" />&nbsp;</td>
                                <td class="table_title"><a href="javascript:_updown('kaoheImg<%=i%>','grouptr<%=i%>')" style="text-decoration:none" class="message_title_bold"><%=ag.getGroupname()%></a></td>
                            </tr>

							<tr>
							<td colspan="2" style="display:none" id="grouptr<%=i%>">
							<table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
							<%
								   List tempusers = (List)useringrouplist.get(i);
								   int usernum = tempusers.size();
						           for(int j=0;j<usernum;j++){
										SysPersonVO agi= (SysPersonVO)tempusers.get(j);
										
							%>
                            <tr >
                  
                                <td class="message_title" style="cursor:hand">&nbsp;&nbsp;&nbsp;<span style="text-decoration:none"  class="message_title" onClick="javascript:_addgroupuser('<%=agi.getPersonuuid()%>','<%=agi.getCnname()%>')"><%=agi.getCnname()%></span></td>
                            </tr>
						   <%}%>

                          </table>
						   </td>
						   </tr>
                           <%}%>
                        </table>
              
                        </td>
               	  </tr>
                </table>    
							</td>
					</tr>
      </table>
      </div>
      
    </form>
    </body>
  </html>
	  <script>

function frameautoheight(){
	parent.parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
	//parent.document.all("userlistfrm").style.height=document.body.scrollHeight;
}
//frameautoheight();
</script>
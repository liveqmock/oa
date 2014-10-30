<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.icss.oa.mail.handler.TimeUtil" %>
<%@	page import="com.icss.oa.filetransfer.handler.SendFileBean"%>
<%@	page import="com.icss.oa.filetransfer.handler.AttachFileBean"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.oa.filetransfer.handler.FileTransferHandler"%>
<%@ page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	String path = request.getContextPath();
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
	//SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
%>
<html>
<head>

<%

List showContent = (List)request.getAttribute("reList");

//元素为:文件数、未读文件数、文件所占总空间
List ReceContent = new ArrayList();
List SentContent = new ArrayList();
List DraftContent = new ArrayList();
List JunkContent = new ArrayList();
int sysTotalnum=0,sysnoReadnum=0;
long sysTotalmemory=0;
if(showContent.size()>0){
	
    ReceContent = (List)showContent.get(0);  //收件箱
    SentContent = (List)showContent.get(1);  //发件箱
    DraftContent = (List)showContent.get(2); //草稿箱
    JunkContent = (List)showContent.get(3);  //垃圾箱
    sysTotalnum = Integer.parseInt(ReceContent.get(0).toString()) + Integer.parseInt(SentContent.get(0).toString()) 
             + Integer.parseInt(DraftContent.get(0).toString()) + Integer.parseInt(JunkContent.get(0).toString());
    sysnoReadnum = Integer.parseInt(ReceContent.get(1).toString()) + Integer.parseInt(SentContent.get(1).toString()) 
              + Integer.parseInt(DraftContent.get(1).toString()) + Integer.parseInt(JunkContent.get(1).toString());
    sysTotalmemory = Long.parseLong(ReceContent.get(2).toString())+Long.parseLong(SentContent.get(2).toString())
                +Long.parseLong(DraftContent.get(2).toString())+Long.parseLong(JunkContent.get(2).toString());
}
//用户自定义文件夹处理
List userFolder = (List)request.getAttribute("userFolder");
Iterator userFolderItr = userFolder.iterator();
int userTotalNum = 0;
int userNoReadnum = 0;
long userTotalMemory = 0;
List oneList = new ArrayList();
for(int m=0;m<userFolder.size();m++){
    oneList = (List)userFolder.get(m);
    userTotalNum+=Integer.parseInt(oneList.get(0).toString());
    userNoReadnum+=Integer.parseInt(oneList.get(1).toString());
    userTotalMemory+=Long.parseLong(oneList.get(2).toString());
}

List userFolderName = (List)request.getAttribute("userFolderName");
Iterator userFolderNameItr = userFolderName.iterator();
long boxMemory = Long.parseLong((String)request.getAttribute("boxMemory"));
long boxPercent = Long.parseLong((String)request.getAttribute("boxPercent"));

FileTransferHandler fHandler = new FileTransferHandler();
FiletransferSetHandler handler = new FiletransferSetHandler();

int totalNum = sysTotalnum + userTotalNum;
int noReadnum = sysnoReadnum + userNoReadnum;
long totalmemo = sysTotalmemory + userTotalMemory;
String totalMemory = fHandler.getMailMemory(totalmemo);
double mailnum = CommUtil.getDivision(totalmemo*100, 1024*1024*boxMemory, 2);
String percent = Double.toString(mailnum);

%>


<title>文件夹目录列表</title>
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
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<script language="JavaScript" src="<%=path%>/include/common.js"></script>
<script language="JavaScript" src="<%=path%>/include/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

function fPopUpCalendarDlg(ctrlobj){
<!--
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=path%>/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
//-->
}

function _ForeverDelete(folder)
{
	var foldername = "";
	if(folder == "Rec"){
		foldername = "收件箱";
	}else if(folder == "Sent"){
		foldername = "发件箱";
	}else if(folder == "Draft"){
		foldername = "草稿箱";
	}else if(folder == "Junk"){
		foldername = "垃圾箱";
	}else{
		foldername = folder;
	}
    ok=confirm("确定要清空"+foldername+"吗？");
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder="+folder;
        return true;
    }else{
        return false;
    }
}


function _NewFolder()
{
    if(document.dofolder.foldername.value==""){
		alert("请输入您要新建的文件夹名！");
        return false;
    }else{
        //得到用户自定义的文件夹
		var nameArr=new Array(<%=userFolderName.size()%>);
		<%
			for(int x=0;x<userFolderName.size();x++){ %>
			    nameArr[<%=x%>] = "<%=handler.decodeBase64((String)userFolderName.get(x))%>";
		<%  }
		%>
		var que = 0;
		for(var i=0;i<<%=userFolderName.size()%>;i++){
			if(document.dofolder.foldername.value==nameArr[i]){
			    que = 1;
			    break;
			}
		}
		if(que==1){
		    alert("出现文件夹重名，请重新命名！");
			return false;
		}else{
            document.dofolder.action="<%=request.getContextPath()%>/servlet/NewFolderServlet";
            document.dofolder.submit();
			//parent.document.frames["leftfrm"].location.reload();
            return true;
        }
    }
}

function _FolderRename()
{
    if(document.dofolder.newfoldername.value==""){
		    alert("请输入新的文件夹名！");
        return false;
    }else{
        if(document.dofolder.newfoldername.value=="Inbox"||document.dofolder.newfoldername.value=="Sent"||
           document.dofolder.newfoldername.value=="Draft"||document.dofolder.newfoldername.value=="Junk"||
           document.dofolder.newfoldername.value=="system"){
             alert("文件夹名和系统文件夹名重复，请重新命名！");
             return false;
        }else{
            //得到用户自定义的文件夹
			var nameArr=new Array(<%=userFolderName.size()%>);
			<%for(int x=0;x<userFolderName.size();x++){ %>
			        nameArr[<%=x%>] = "<%=handler.decodeBase64((String)userFolderName.get(x))%>";
			<%}
			%>
			var que = 0;
			for(var i=0;i<<%=userFolderName.size()%>;i++){
			    if(document.dofolder.newfoldername.value==nameArr[i]){
			        que = 1;
			        break;
			    }
			}
			if(que==1){
			    alert("出现文件夹重名，请重新命名！");
			    return false;
			}
		}
	}
    if(document.dofolder.mytarget.value==""){
        alert("请选择一个自定义文件夹！");
        return false;
    }else{
        document.dofolder.action="<%=request.getContextPath()%>/servlet/RenameFolderServlet";
        document.dofolder.submit();

        return true;
    }
}

function _DelFolder(foldername)
{
   var userfolder = foldername;
   ok = confirm("确实要删除这个文件夹吗？");
   if(ok!="0"){
       var sta = document.location.href = "<%=request.getContextPath()%>/servlet/DelFolderServlet?folder="+userfolder;
       return true;
   }else{
       return false;
   }
      
}
function _DeleteMail(foldername)
{
    var userfolder = foldername;
    if(userfolder=="Junk"){
         type = "system";
         ok = confirm("确实要清空垃圾箱吗？");
    }else{
         type = "user";
         ok=confirm("确实要将这个文件夹中的邮件删除到垃圾箱吗？");
    }
    if(ok){
        document.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type="+type+"&folder="+userfolder;
        return true;
    }else{
        return false;
    }
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

<body>


<!--主要内容去区-->
<table width="788" border="0" cellspacing="0" cellpadding="0">
    <tr>
    	<td colspan="3" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    <tr>
	  <!--td>&nbsp;&nbsp;</td-->
      <td width="96" height="30" valign="middle" class="message_title">邮件总数：<span class="message_title_bold"><%=totalNum%></span></td>
      <td width="166" height="30" align="left" valign="middle" class="message_title">未读邮件：<span class="message_title_bold"><%=noReadnum%></span></td>

      <td width="448" height="30" align="center" valign="top">

      		 <iframe name="foldersizefrm" src="<%=path%>/servlet/FolderSizeServlet" width="100%" height="100%" scrolling="no" frameborder="0" framespacing="0"></iframe>
	   </td>
    </tr>

   <tr> 
      <!--td>&nbsp;&nbsp;</td-->
      <td colspan="3" valign="top">
      	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" class="table_bgcolor">
        <tr>
          <td width="32%" height="24" align="center" nowrap="nowrap" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">文件夹</td>
          <td width="21%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">文件数</td>
          <td width="20%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">未读文件</td>
          <td width="16%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">占用空间</td>
          <td width="11%" align="center" background="<%=path%>/images/2-title-05.jpg" bgcolor="#E0EDF8" class="block_title">操作</td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Inbox&search=no"  style="text-decoration:none;cursor:hand">收件箱</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?ReceContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?ReceContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(ReceContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="#" onClick="javascript:_ForeverDelete('Rec')" style="text-decoration:none;cursor:hand">[清空]</a></td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no"  style="text-decoration:none;cursor:hand">发件箱</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?SentContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?SentContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(SentContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="#" onClick="javascript:_ForeverDelete('Sent')" style="text-decoration:none;cursor:hand">[清空]</a></td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Draft&search=no"  style="text-decoration:none;cursor:hand">草稿箱</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?DraftContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?DraftContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(DraftContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="#" onClick="javascript:_ForeverDelete('Draft')" style="text-decoration:none;cursor:hand">[清空]</a></td>
        </tr>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Junk&search=no"  style="text-decoration:none;cursor:hand">垃圾箱</a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?JunkContent.get(0).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?JunkContent.get(1).toString():"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(JunkContent.get(2).toString())):"0"%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="#" onClick="javascript:_ForeverDelete('Junk')" style="text-decoration:none;cursor:hand">[清空]</a></td>
        </tr>
          <%
               if(userFolder.size()>0){
                  int i = 0;
                  while(userFolderItr.hasNext()){
                   //每次循环对一个自定义文件处理
                  List oneOfList = (List)userFolderItr.next(); 
                  String folderName = (String)userFolderName.get(i);
                  folderName = handler.decodeBase64(folderName);
                  i++;
          %>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=user&folder=<%=folderName%>&search=no"  style="text-decoration:none;cursor:hand"><%=folderName%></a></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=oneOfList.get(0).toString()%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=oneOfList.get(1).toString()%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><%=fHandler.getMailMemory(Long.parseLong(oneOfList.get(2).toString()))%></td>
          <%
                  //判断文件夹是否为空
                  if("0".equals(oneOfList.get(2).toString())){
          %>
          <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="#" style="text-decoration:none;cursor:hand" onClick="javascript:_DelFolder('<%=folderName%>')">[删除]</a></td>
		  <%}else{%>
		  <td align="center" bgcolor="#FFFFFF" class="message_title"><a href="#" style="text-decoration:none;cursor:hand" onClick="javascript:_DeleteMail('<%=folderName%>')">[清空]</a></td>
		  <%     }%>
        </tr>
		  <%
               } //while
          }
		  userFolderItr = userFolder.iterator();
          %>
        <tr>
          <td height="26" align="center" bgcolor="#FFFFFF" class="message_title_bold">总计</td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold"><%=totalNum%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold"><%=noReadnum%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold"><%=totalMemory%></td>
          <td align="center" bgcolor="#FFFFFF" class="message_title_bold">&nbsp;</td>
        </tr>
      </table>


	  </form>
      <br />
		<form name="dofolder" method="POST" action="">
      <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
      	<tr>
        	<td bgcolor="#FFFFFF" class="foot_message">在[根文件夹]里新建
        	  <input name="foldername" type="text" class="black-12" value="" size="10" />
       	    <img src="<%=path%>/images/button_xinjian.jpg" width="90" height="23" onClick="javascript:_NewFolder()" style="cursor:hand" title="新建文件夹"/> 将
       	    <select name="mytarget" id="select">
       	      <option value="">请选择</option>
			  	<%
					if(userFolder.size()>0){
						int i = 0;
						while(userFolderItr.hasNext()){
						//每次循环对一个自定义文件处理
						List oneOfList2 = (List)userFolderItr.next(); 
						String folderName2 = (String)userFolderName.get(i);
						folderName2 = handler.decodeBase64(folderName2);
						i++;
			  %>
       	      <option value="<%=folderName2%>"><%=folderName2%></option>
			  <%}}%>
     	      </select> 
       	    重命名为
       	    <input name="newfoldername" type="text" class="black-12" value="" size="10" />
       	    <img src="<%=path%>/images/button_rename.jpg" width="90" height="23" onClick="javascript:_FolderRename()" style="cursor:hand" title="重命名文件夹"/>       	    </td>
        </tr>
      </table>
	  </form>
      </td>
    </tr>
    
    
    <tr>
    	<td colspan="3" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
<script>
function search(path){
    var stime = document.all.startTime.value;
    var etime = document.all.endTime.value;
    if((stime==""&&etime!="")||(stime!=""&&etime=="")){
        alert("请您输入完整的查询时间！");
        return false;
    }else{
    	var sr = stime.split("-");
		var er = etime.split("-");
		sr[1]=sr[1]-1;    //系统默认月份是0~11,所以要减1
		er[1]=er[1]-1;
		var sd= new Date(sr[0],sr[1],sr[2]);
		var ed= new Date(er[0],er[1],er[2]);
		if(ed<=sd)       //截止时间小于等于开始时间,当为相等时表示只查某一天的归库量
		{
			alert("您选择的结束时间小于或等于开始时间!");
			return false;
		}else{
		    document.searchmail.action = path + "/servlet/SearchMailServlet";
	        document.searchmail.submit();
	        return true;
		}
    }
}

</script>
	  


</body>
</html>
<script language="javascript">
function frameautoheight(){
	parent.parent.document.all("bodyfrm").style.height=document.body.scrollHeight;
	//parent.document.all("userlistfrm").style.height=document.body.scrollHeight;
}
frameautoheight();
parent.leftfrm.location.reload();
</script>

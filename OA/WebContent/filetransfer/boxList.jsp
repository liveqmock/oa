<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@page import="java.util.*" %>
<%@page import="com.icss.oa.util.CommUtil"%>
<%@page import="com.icss.oa.filetransfer.handler.FileTransferHandler"%>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%>

<HTML><HEAD>
<title>文件夹目录列表</title>

<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>

<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module18");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>

</HEAD>
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

<BODY topmargin="10" leftmargin=""  background="<%=request.getContextPath()%>/images/bg-08.gif">
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="100%" height="24" align="center">
      您的文件夹里目前共<font class="tblcautionfont"><%=totalNum%></font>个文件，未读文件数为：<font  class="tblcautionfont"><%=noReadnum%></font>个</td>
    </tr>
    <tr>
      <td width="100%" height="24" align="center" class="before_title">
	  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="44%" align="right">文件夹空间占用情况(<%=totalMemory%>, <%=percent%>%): </td>
          <td width="100">
		  <table width="100" height="15"  cellpadding="0" border="0" cellspacing="0" style="border: #104a7b 1px solid; padding:1px; padding-right: 0px; padding-left: 0px;" >
            <tr>
              <td width="500">
                <% String bgColor = "";
                  if(mailnum>=boxPercent){
                      bgColor = "red";
                  }else{
                      bgColor = "#339933";
                  }
                %>
                <div style="height:15px; width:<%=percent%>%; font-size:3px; background-color:<%=bgColor%>"></div>
              </td>
            </tr>
          </table></td>
          <td width="42%">  <%=boxMemory%>M</td>
        </tr>
      </table>      </td>
    </tr>
</table>
<table>
<tr><td>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">文件夹列表</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images_new/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="20%" bgcolor="#FFFBEF" class="title-04"><div align="center">文件夹</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                      <td width="20%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">文件数</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="1" height="2"></td>
                      <td width="20%" bgcolor="#FFFBEF"><div align="center" class="title-04">未读文件</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="20%" bgcolor="#FFFBEF"><div align="center" class="title-04">占用空间</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="20%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">操作</div></td>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                      <td align="center" class="text-01"><a href="MessageListServlet?type=system&folder=Inbox&search=no" class="text">收件箱</a></td>
                      <td height="22" class="text-01" align="center"><%=showContent.size()>0?ReceContent.get(0).toString():"0"%></td>
                      <td  class="text-01"><div align="center"><%=showContent.size()>0?ReceContent.get(1).toString():"0"%></div></td>
                      <td><div align="center" class="text-01"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(ReceContent.get(2).toString())):"0"%></div></td>
                      <td height="22"><div align="center"></div></td>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                      <td align="center" class="text-01"><a href="MessageListServlet?type=system&folder=Sent&search=no" class="text">发件箱</a></td>
                      <td height="22" class="text-01" align="center"><%=showContent.size()>0?SentContent.get(0).toString():"0"%></td>
                      <td  class="text-01"><div align="center"><%=showContent.size()>0?SentContent.get(1).toString():"0"%></div></td>
                      <td><div align="center" class="text-01"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(SentContent.get(2).toString())):"0"%></div></td>
                      <td height="22"><div align="center"></div></td>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                      <td align="center" class="text-01"><a href="MessageListServlet?type=system&folder=Draft&search=no" class="text">草稿箱</a></td>
                      <td height="22" class="text-01" align="center"><%=showContent.size()>0?DraftContent.get(0).toString():"0"%></td>
                      <td  class="text-01"><div align="center"><%=showContent.size()>0?DraftContent.get(1).toString():"0"%></div></td>
                      <td><div align="center" class="text-01"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(DraftContent.get(2).toString())):"0"%></div></td>
                      <td height="22"><div align="center"></div></td>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                      <td align="center" class="text-01"><a href="MessageListServlet?type=system&folder=Junk&search=no" class="text">垃圾箱</a></td>
                      <td height="22" class="text-01" align="center"><%=showContent.size()>0?JunkContent.get(0).toString():"0"%></td>
                      <td  class="text-01"><div align="center"><%=showContent.size()>0?JunkContent.get(1).toString():"0"%></div></td>
                      <td><div align="center" class="text-01"><%=showContent.size()>0?fHandler.getMailMemory(Long.parseLong(JunkContent.get(2).toString())):"0"%></div></td>
                      <td height="22"><div align="center"><a href="#" onclick="_DeleteMail('Junk')">[清&nbsp;&nbsp;空]</a></div></td>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
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
                    <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                      <td align="center" class="text-01"><a href="MessageListServlet?type=user&folder=<%=folderName%>&search=no" class="text"><%=folderName%></a></td>
                      <td height="22" class="text-01" align="center"><%=oneOfList.get(0).toString()%></td>
                      <td  class="text-01"><div align="center"><%=oneOfList.get(1).toString()%></div></td>
                      <td><div align="center" class="text-01"><%=fHandler.getMailMemory(Long.parseLong(oneOfList.get(2).toString()))%></div></td>
                     <%
                        //判断文件夹是否为空
                        if("0".equals(oneOfList.get(2).toString())){
                     %>
                      <td height="22"><div align="center"><a href="#" onclick="_DelFolder('<%=folderName%>')">[删&nbsp;&nbsp;除]</a></div></td>
                     <%}else{%>
                      <td height="22"><div align="center"><a href="#" onclick="_DeleteMail('<%=folderName%>')">[清&nbsp;&nbsp;空]</a></div></td>
                     <%}%>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                    <%
                       } //while
                    }//if
                    %>
                    <tr bgColor=#D8EAF8 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D8EAF8';">
                      <td align="center" class="text-01"><b>总&nbsp;&nbsp;计</b></td>
                      <td height="22" class="text-01" align="center"><%=totalNum%></td>
                      <td  class="text-01"><div align="center"><%=noReadnum%></div></td>
                      <td><div align="center" class="text-01"><%=totalMemory%></div></td>
                      <td height="22"><div align="center"></div></td>
				    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr> 
                </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right">&nbsp;</div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table></center>
<br>
<table bgcolor="#EEEEEE" width="95%" align="center">
  <tr>
  <td nowrap>
<form name="dofolder" method="POST" action="">
  <table border="0" align="left" cellpadding="3" cellspacing="0">
    <tr>
      <td nowrap class="before_title">
        在
        <select name="target" size=1>
          <option value="." selected>根文件夹</option>
        </select>
          里新建
        <input maxLength='16' name='foldername' size='13'>
        <img src="<%=request.getContextPath()%>/images/filetransfer/botton-new.gif"  onclick="javascript:_NewFolder()" style="cursor:hand" title="新建文件夹"> 
	    </td>
    </tr>
  </table>
</form>
</td>
<td>
<form name="dofolder2" method="POST" action="">
  <table border="0" align="right" cellpadding="3" cellspacing="0">
    <tr>
      <td nowrap>
        将
        <select name="mytarget" size=1>
          <option value="">请选择</option>
          <%
               if(userFolderName.size()>0){
                 while(userFolderNameItr.hasNext()){
                   String folderName = (String)userFolderNameItr.next();
                   folderName = handler.decodeBase64(folderName);
                   out.print("<option value=\""+folderName+"\">"+folderName+"</option>");
                 }
               }
          %>
          </select>
        重命名为
        <input maxLength='16' name='foldername' size='13'>
        <img src="<%=request.getContextPath()%>/images/filetransfer/botton-rename.gif" onclick="javascript:_FolderRename()" style="cursor:hand" title="重命名文件夹">
      </td>
    </tr>
  </table>
</form>
</td>
</tr>
</table>
</td></tr>
</table>
<script language="JavaScript">
<!--
  
function _NewFolder()
{

    if(document.dofolder.target.value!="."){
        alert("您只能在根目录下新建文件夹！");
        return false;
    }
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
            window.top.leftFrame.location.reload();
            return true;
        }
    }
}

function _FolderRename()
{
  frm = document.dofolder2;
    if(frm.foldername.value==""){
		    alert("请输入新的文件夹名！");
        return false;
    }else{
        if(frm.foldername.value=="Inbox"||frm.foldername.value=="Sent"||
           frm.foldername.value=="Draft"||frm.foldername.value=="Junk"||
           frm.foldername.value=="system"){
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
			    if(frm.foldername.value==nameArr[i]){
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
    if(frm.mytarget.value==""){
        alert("请选择一个自定义文件夹！");
        return false;
    }else{
        frm.action="<%=request.getContextPath()%>/servlet/RenameFolderServlet";
        frm.submit();
        window.top.leftFrame.location.reload();
        return true;
    }
}

function _DelFolder(foldername)
{
   var userfolder = foldername;
   ok = confirm("确实要删除这个文件夹吗？");
   if(ok!="0"){
       document.location.href = "<%=request.getContextPath()%>/servlet/DelFolderServlet?folder="+userfolder;
       window.top.leftFrame.location.reload();
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

function _pop(){  
   var kk = <%= percent %>;
   if(parseInt(kk)>95){
   window.open('<%=request.getContextPath()%>/filetransfer/delmail.jsp','','width=600,height=100,toolbar=0,directories=0,status=0,menu=0,scrollbars=no,resizable=no,copyhistory=no,left=170,top=110');
   }
}

//-->
</script>
<script language="JavaScript">
	_pop();
	</script>
</BODY>
</HTML>

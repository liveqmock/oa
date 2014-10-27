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
	String time = TimeUtil.getCurrentDate("yyyy��MM��dd�� ")+TimeUtil.getCurrentWeek();
	//SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
%>


<%

List showContent = (List)request.getAttribute("reList");

//Ԫ��Ϊ:�ļ�����δ���ļ������ļ���ռ�ܿռ�
List ReceContent = new ArrayList();
List SentContent = new ArrayList();
List DraftContent = new ArrayList();
List JunkContent = new ArrayList();
int sysTotalnum=0,sysnoReadnum=0;
long sysTotalmemory=0;
if(showContent.size()>0){
	
    ReceContent = (List)showContent.get(0);  //�ռ���
    SentContent = (List)showContent.get(1);  //������
    DraftContent = (List)showContent.get(2); //�ݸ���
    JunkContent = (List)showContent.get(3);  //������
    sysTotalnum = Integer.parseInt(ReceContent.get(0).toString()) + Integer.parseInt(SentContent.get(0).toString()) 
             + Integer.parseInt(DraftContent.get(0).toString()) + Integer.parseInt(JunkContent.get(0).toString());
    sysnoReadnum = Integer.parseInt(ReceContent.get(1).toString()) + Integer.parseInt(SentContent.get(1).toString()) 
              + Integer.parseInt(DraftContent.get(1).toString()) + Integer.parseInt(JunkContent.get(1).toString());
    sysTotalmemory = Long.parseLong(ReceContent.get(2).toString())+Long.parseLong(SentContent.get(2).toString())
                +Long.parseLong(DraftContent.get(2).toString())+Long.parseLong(JunkContent.get(2).toString());
}
//�û��Զ����ļ��д���
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

<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" type="text/css" id="homepagestyle" />
<script language="JavaScript" src="<%=path%>/include/common.js"></script>
<script language="JavaScript" src="<%=path%>/include/My97DatePicker/WdatePicker.js"></script>

<script>
function search(path){

    if(document.getElementById('subject').value==""){alert('��������⣡');return false;}
    var stime = document.all.startTime.value;
    var etime = document.all.endTime.value;
    if((stime==""&&etime!="")||(stime!=""&&etime=="")){
        alert("�������������Ĳ�ѯʱ�䣡");
        return false;
    }else{
    	var sr = stime.split("-");
		var er = etime.split("-");
		sr[1]=sr[1]-1;    //ϵͳĬ���·���0~11,����Ҫ��1
		er[1]=er[1]-1;
		var sd= new Date(sr[0],sr[1],sr[2]);
		var ed= new Date(er[0],er[1],er[2]);
		if(ed<=sd)       //��ֹʱ��С�ڵ��ڿ�ʼʱ��,��Ϊ���ʱ��ʾֻ��ĳһ��Ĺ����
		{
			alert("��ѡ��Ľ���ʱ��С�ڻ���ڿ�ʼʱ��!");
			return false;
            }else{
		    document.searchmail.action = path + "/servlet/SearchMailServlet";
			document.searchmail.target = "bodyfrm";
	        document.searchmail.submit();
	        return true;
		}
    }
}

function _ForeverDelete(folder)
{
	var foldername = "";
	if(folder == "Rec"){
		foldername = "�ռ���";
	}else if(folder == "Sent"){
		foldername = "������";
	}else if(folder == "Draft"){
		foldername = "�ݸ���";
	}else if(folder == "Junk"){
		foldername = "������";
	}else{
		foldername = folder;
	}
    ok=confirm("ȷ��Ҫ���"+foldername+"��");
    if(ok){
        parent.document.bodyfrm.location.href = "<%=request.getContextPath()%>/servlet/DelMailServlet?type=system&folder="+folder;
        return true;
    }else{
        return false;
    }
}

function changeStyle(id){//�л���ʽ
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//�л���ʽʱ����COOKIE
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
			//ɾ��ԭ����COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//�����µ���ʽ
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
</head>
<body>
	<form name="searchmail" method="post" action="">
        <TABLE width="180" height="37" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
            <TR>
              <TD width="90" align="right" bgcolor="#FFFFFF">
              <table width="100%" cellspacing="5">
                <tr>
                <td width="36" align="right" style="cursor:hand;">
              <a href="<%=path%>/servlet/ShowNoReadServlet" target="bodyfrm" style="text-decoration:none"><img src="<%=path%>/images/mail_receive.JPG" width="26" height="26" border=0/></a></TD>
              <TD width="45" align="left" class="message_title_bold" style="cursor:hand;"><a href="<%=path%>/servlet/ShowNoReadServlet" class="message_title_bold"  target="bodyfrm">δ��<br />
                �ʼ�</a></TD>
              </tr>
              </table>
              <TD width="90" align="right" bgcolor="#FFFFFF">
              <table width="100%" cellspacing="5">
                <tr>
                  <td width="36" align="right" style="cursor:hand"><a href="<%=path%>/servlet/SendFileFileTransferServlet" target="bodyfrm" style="text-decoration:none"><img src="<%=path%>/images/mail_send.JPG" width="26" height="26" border="0" /></a></td>
                  <td width="45" align="left" class="blue-12" style="cursor:hand;"><a href="<%=path%>/servlet/SendFileFileTransferServlet"  target="bodyfrm" class="message_title_bold">д��</a></td>
                </tr>
              </table>
              </TD>
            </TR>
        </TABLE>

		        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>
        
        <table width="180" border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
          <tr>
            <td>
            <table width="100%" height="170" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="20" align="left"  bgcolor="#E0EDF8" class="block_title" colspan="3" target="bodyfrm"> �ļ���</td>
                </tr>
                <tr height="25">
                  <td bgcolor="#F8FCFF" width="25" >&nbsp;</td>
                  <td width="25" align="right" bgcolor="#F8FCFF">
                  <img src="<%=path%>/images/mail_inbox.JPG" width="15" height="16" />&nbsp;</td>
                  <td width="130" bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Inbox&search=no" target="bodyfrm" class="message_title" style="cursor:hand">�ռ���</a>&nbsp;<span class="grap2-12"><a href="#" onClick="javascript:_ForeverDelete('Rec')" class="message_title" style="cursor:hand"  >[���]</a></span>&nbsp;<span class="red2-12-b">(<%=showContent.size()>0?ReceContent.get(0).toString():"0"%>)</span></td>
                </tr>
                <tr height="25">
                  <td bgcolor="#F8FCFF" >&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF">
                  <img src="<%=path%>/images/mail_caogao.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Draft&search=no" class="message_title" style="cursor:hand" target="bodyfrm">�ݸ���</a>&nbsp;<span class="grap2-12"><a href="#" onClick="javascript:_ForeverDelete('Draft')" class="message_title" style="cursor:hand" >[���]</a></span>&nbsp;<span class="red2-12-b">(<%=showContent.size()>0?DraftContent.get(0).toString():"0"%>)</span></td>
                </tr>
                <tr height="25">
                  <td bgcolor="#F8FCFF">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_outbox.JPG" width="16" height="18" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Sent&search=no" class="message_title" style="cursor:hand" target="bodyfrm">������</a>&nbsp;<span class="grap2-12"><a href="#" onClick="javascript:_ForeverDelete('Sent')" class="message_title" style="cursor:hand">[���]</a></span>&nbsp;<span class="red2-12-b">(<%=showContent.size()>0?SentContent.get(0).toString():"0"%>)</span></td>
                </tr>
                <tr height="25">
                  <td bgcolor="#F8FCFF">&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF"><img src="<%=path%>/images/mail_laji.JPG" width="16" height="19" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=system&folder=Junk&search=no" class="message_title" style="cursor:hand" target="bodyfrm">������</a>&nbsp;<span class="grap2-12"><a href="#" onClick="javascript:_ForeverDelete('Junk')" class="message_title" style="cursor:hand" >[���]</a></span>&nbsp;<span class="red2-12-b">(<%=showContent.size()>0?JunkContent.get(0).toString():"0"%>)</span></td>
                </tr>
                <tr>
                  <td height="25" colspan="3" bgcolor="#E0EDF8" class="block_title">�Զ����ļ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=path%>/servlet/BoxListServlet" style="cursor:hand" class="message_title"  target="bodyfrm">[����]</a></td>
                </tr>
				<%
					if(userFolder.size()>0){
						int i = 0;
						while(userFolderItr.hasNext()){
						//ÿ��ѭ����һ���Զ����ļ�����
						List oneOfList1 = (List)userFolderItr.next(); 
						String folderName1 = (String)userFolderName.get(i);
						folderName1 = handler.decodeBase64(folderName1);
						i++;
				%>
                <tr height="25">
                  <td bgcolor="#F8FCFF" >&nbsp;</td>
                  <td align="right" bgcolor="#F8FCFF">
                  <img src="<%=path%>/images/mail_folder.JPG" width="19" height="17" />&nbsp;</td>
                  <td bgcolor="#FFFFFF" class="message_title"><a href="MessageListServlet?type=user&folder=<%=folderName1%>&search=no" class="message_title" style="cursor:hand" target="bodyfrm"><%=folderName1%></a></td>
                </tr>
				<%}} userFolderItr = userFolder.iterator();%>
            </table></td>
          </tr>
      </table>


        <table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td height="5"><img src="<%=path%>/images/kongbai.jpg" height="5" /></td>
            </tr>
        </table>


		
        <TABLE width="180" height="30" align="center" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
       	  <tr>
            <td bgcolor="#FFFFFF">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�����ļ���</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><select name="folder" >
                  	  <option value="0" >�����ļ���</option>
                      <option value="Inbox" >�ռ���</option>
                      <option value="Sent" >������</option>
                      <option value="Draft">�ݸ���</option>
                      <option value="Junk" >������</option>
                      <% while(userFolderNameItr.hasNext()){
                             String foldername4 = userFolderNameItr.next().toString();
                      %>
                      <option value="<%=handler.decodeBase64(foldername4)%>"><%=handler.decodeBase64(foldername4)%></option>
                      <%
                         }
                      %>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="60" height="30" align="right" bgcolor="#FFFFFF" class="message_title">�Ƿ��Ѷ�</td>
                  <td colspan="2" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="green-12" style="cursor:hand;"><select name="isRead" style="width:90px;">
                    <option value="0">ȫ��</option>
                    <option value="1">δ���ʼ�</option>
                    <option value="2">�Ѷ��ʼ�</option>
                  </select>                  </td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">����ʱ���</td>
                  
                  <td width="15" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="" style="cursor:hand;">
                  <input name="startTime" class="Wdate" type="text" onClick="WdatePicker()" size="12" value="" /></td>
                </tr>
                <tr>
                  <td width="65" height="30" align="right" bgcolor="#FFFFFF" class="message_title" nowrap="nowrap">��</td>
                  
                  <td width="15" align="left" nowrap="nowrap" bgcolor="#FFFFFF" class="" style="cursor:hand;">
                  <input name="endTime" class="Wdate" type="text" onClick="WdatePicker()" size="12" value=""/></td>
                </tr>
            </table>            
            </td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"  height="30">
            <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="100" align="right" bgcolor="#FFFFFF"><input id="subject" name="subject" type="text" class="biankuang-blue" size="10" value="" /></td>
                  <td width="80" align="center" nowrap="nowrap" bgcolor="#E0EDF8" class="message_title_bold" style="cursor:hand;" onClick="javascript:search('<%=path%>')" >��������</td>
                </tr>
            </table>            
            </td>
          </tr>
        </TABLE>
		

      </form>
      </body>
   </html>
     

<script language="javascript">
function myonload(){

var d = new Date();
var year = d.getFullYear();
var month = d.getMonth();
var day = d.getDate();
month = month + 1;
if(month>1){
	smonth = month-1
	document.searchmail.startTime.value= year+"-"+smonth+"-1";
	document.searchmail.endTime.value= year+"-"+month+"-"+day;
}else if(month==1){
    syear = year-1
	document.searchmail.startTime.value= syear+"-12-1";
	document.searchmail.endTime.value= year+"-"+month+"-"+day;
}
}
window.onload=myonload;

function frameautoheight(){
	parent.document.all("leftfrm").style.height=document.body.scrollHeight;
}
frameautoheight();
</script>

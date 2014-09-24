<%@page contentType="text/html; charset=GBK" %>
<%@page import="java.util.*"%>
<%@page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%
List userFolderName = (List)request.getAttribute("userFolderName");
Iterator userFolderItr =null;
if(userFolderName!=null){
    userFolderItr = userFolderName.iterator();
    }
%>

<HTML><HEAD><TITLE>文件传递</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/eyou_mainmenu.css" type="text/css">

<SCRIPT LANGUAGE="JavaScript">
<!--
USETEXTLINKS=1
iOE=new Array
nE=0
d=document

void function check_backspace() {
    if(this.event.keyCode == 8) {
        window.event.returnValue = false;
    };
};


function F(fD,hR){
this.desc=fD
this.hR=hR
this.id=-1
this.navObj=0
this.iI=0  
this.nI=0
this.iLN=0
this.isOpen=true
this.iconSrc="<%=request.getContextPath()%>/images/o.gif"
this.ch=new Array
this.nCh=0
this.initialize=iF
this.setState=sSF
this.aC=aC
this.cI=cEI
this.hide=hF
this.display=display
this.rO=dF
this.tH=tH
this.sE=fSE
this.oL=oFL}
function sSF(isOpen)
{this.isOpen=isOpen
pCI(this)}
function pCI(f)
{var i=0
if(f.isOpen){
if(f.nI)
if(f.iLN)f.nI.src="<%=request.getContextPath()%>/images/m.gif"
else f.nI.src="<%=request.getContextPath()%>/images/tree/Tminus.gif"
f.iI.src="<%=request.getContextPath()%>/images/o.gif"
for(i=0;i<f.nCh;i++)
f.ch[i].display()
}else{
if(f.nI)
if(f.iLN)f.nI.src="<%=request.getContextPath()%>/images/p.gif"
else f.nI.src="<%=request.getContextPath()%>/images/tree/Tplus.gif"
f.iI.src="<%=request.getContextPath()%>/images/c.gif"
for(i=0;i<f.nCh;i++)
f.ch[i].hide()
}}
function hF()
{if(this.navObj.style.display=="none")return
this.navObj.style.display="none"
this.setState(0)}
function iF(level,lN,lS)
{var j=0
var i=0
var nc
nc=this.nCh
this.cI()
var auxEv=""
auxEv="<a href='javascript:cON("+this.id+")'>"
if(level>0)if (lN)
{this.rO(lS+auxEv+"<img name='nodeIcon"+this.id+"' src='<%=request.getContextPath()%>/images/m.gif' border=0></a>")
lS=lS+"<img src='<%=request.getContextPath()%>/images/b.gif'>"
this.iLN=1}
else{
this.rO(lS+auxEv+"<img name='nodeIcon"+this.id+"' src='<%=request.getContextPath()%>/images/tree/Tminus.gif' border=0></a>")
lS=lS+"<img src='<%=request.getContextPath()%>/images/tree/I.gif'>"
this.iLN=0
}else this.rO("")
if(nc>0)
{level=level+1
for(i=0;i<this.nCh;i++)
{if(i==this.nCh-1)this.ch[i].initialize(level, 1, lS)
else this.ch[i].initialize(level, 0, lS)}}}
function dF(lS)
{d.write("<table id='f"+this.id+"' style='position:block;' border=0 cellspacing=0 cellpadding=0><tr><td>")
d.write(lS)
d.write("</td><td valign=middle nowrap>")
if(USETEXTLINKS)
{this.oL()
d.write(this.desc+"</a>")}
else d.write(this.desc)
d.write("</td></table>")
this.navObj=d.all["f"+this.id]
this.nI=d.all["nodeIcon"+this.id]
}
function oFL(){
if (this.hR){
//d.write("<a href='"+this.hR+"' target=\"mainFrame\" ")
d.write("<a href=javascript:_reloadMainFrame('"+this.hR+"',"+this.id+") ")
d.write("onClick='javascript:cOF("+this.id+")'")
d.write(">") 
}else d.write("<a>")}

function aC(cN) 
{this.ch[this.nCh]=cN 
this.nCh++ 
return cN}
function fSE() 
{var i=0
var se=this.nCh
for(i=0;i<this.nCh;i++){
if(this.ch[i].ch)
se=se+this.ch[i].sE()} 
return se}
function Item(iD,iL)
{
	this.desc=iD
	this.link=iL
	this.id=-1
	this.navObj=0
	this.iI=0
	this.iconSrc="<%=request.getContextPath()%>/images/d.gif"
	this.initialize=iI
	this.cI=cEI
	this.hide=hideItem
	this.display=display
	this.rO=drawItem
	this.tH=tH
}


function hideItem()
{
if(this.navObj.style.display=="none")return
this.navObj.style.display="none"
if(this.navObj.visibility=="hidden")return 
this.navObj.visibility="hidden"}

function iI(level,lN,lS) 
{this.cI() 
if(level>0)
if(lN)
{this.rO(lS+"<img src='<%=request.getContextPath()%>/images/tree/L.gif'>")
lS=lS+"<img src='<%=request.getContextPath()%>/images/b.gif'>"
}else{
this.rO(lS+"<img src='<%=request.getContextPath()%>/images/tree/T.gif'>") 
lS=lS+"<img src='<%=request.getContextPath()%>/images/left_img/I.gif'>" 
}else this.rO("")}
function drawItem(lS) 
{
//	alert(this.id);
d.write("<table id='item"+this.id+"' style='position:block;' border=0 cellspacing=0 cellpadding=0><tr><td>")
d.write(lS)
//d.write("<a href="+this.link+"></a></td><td valign=middle nowrap>") 
if(USETEXTLINKS){
	d.write("<a href=\"javascript:_reloadMainFrame( " + this.link + "," + this.id + " ) \">"+this.desc+"</a>");
} else {
	d.write(this.desc) 
}
d.write("</table>") 
this.navObj=d.all["item"+this.id] 
this.iI=d.all["itemIcon"+this.id] 
}
function display()
{this.navObj.style.display="block" 
}
function cEI()
{this.id=nE
iOE[nE]=this
nE++}
function tH()
{var h=this.navObj.clip.height
var i=0
if(this.isOpen)
for(i=0;i<this.nCh;i++)
h=h+this.ch[i].tH()
return h}
function cOF(fI) 
{var cld=iOE[fI]
if(!cld.isOpen)
cON(fI)
return
if(cld.isSelected)
return
}
function cON(fI)
{
	var cF=0
	var state=0
	cF=iOE[fI]
	state=cF.isOpen
cF.setState(!state)}

function ini()
{
fT.initialize(0, 1, "")
fT.display()
cON(0)
cON(0)}

function gFld(ds,hR)
{f=new F(ds,hR)
return f}

function gLnk(target,ds,lD,extra)
{
	fL=""
	if(target==0)
	{	
		//fL="'"+lD+"' target=\"mainFrame\"" + extra 
		fL="'"+lD+"' " + extra 
	}else{
		if (target==1)fL="'http://"+lD+"' target=\"mainFrame\"" + extra
		else fL="'http://"+lD+"' target=\"mainFrame\"" + extra}
	if(target==2)
		fL="'"+lD+"' " + extra
	linkItem=new Item(ds,fL)
	return linkItem
}
function insFld(pF,cF)
{return pF.aC(cF)} 
function insDoc(pF,document)
{pF.aC(document)}



fT= gFld("", "")
auxnoread_= insFld(fT,gFld("<img src=<%=request.getContextPath()%>/images/filetransfer/backup.gif border=0>&nbsp;未读文件", "<%=request.getContextPath()%>/servlet/ShowNoReadServlet"))
//insDoc(fT, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/backup.gif border=0>&nbsp;未读文件", "<%=request.getContextPath()%>/servlet/ShowNoReadServlet", ""))
insDoc(fT, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/find.gif border=0>&nbsp;查找文件", "<%=request.getContextPath()%>/servlet/SearchMailServlet", ""))
insDoc(fT, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/compose.gif border=0>&nbsp;发送文件", "<%=request.getContextPath()%>/servlet/SendFileFileTransferServlet", ""))
//auxnoread_= insFld(fT,gFld("<img src=<%=request.getContextPath()%>/images/filetransfer/backup.gif border=0>&nbsp;未读文件", "<%=request.getContextPath()%>/servlet/ShowNoReadServlet"))
//auxsearch_= insFld(fT,gFld("<img src=<%=request.getContextPath()%>/images/filetransfer/find.gif border=0>&nbsp;查找文件", "<%=request.getContextPath()%>/servlet/SearchMailServlet"))
//auxsend_= insFld(fT,gFld("<img src=<%=request.getContextPath()%>/images/filetransfer/compose.gif border=0>&nbsp;发送文件", "<%=request.getContextPath()%>/servlet/FileTransferServlet"))
aux__= insFld(fT, gFld("<img src=<%=request.getContextPath()%>/images/filetransfer/archives.gif border=0>&nbsp;文件夹", "<%=request.getContextPath()%>/servlet/BoxListServlet"))
insDoc(aux__, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/inbox.gif border=0>&nbsp;收件箱", "<%=request.getContextPath()%>/servlet/MessageListServlet?type=system&folder=Inbox&search=no", ""))
insDoc(aux__, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/Sent.gif border=0>&nbsp;发件箱", "<%=request.getContextPath()%>/servlet/MessageListServlet?type=system&folder=Sent&search=no", ""))
insDoc(aux__, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/Draft.gif border=0>&nbsp;草稿箱", "<%=request.getContextPath()%>/servlet/MessageListServlet?type=system&folder=Draft&search=no", ""))
insDoc(aux__, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/Junk.gif border=0>&nbsp;垃圾箱", "<%=request.getContextPath()%>/servlet/MessageListServlet?type=system&folder=Junk&search=no", ""))
<%
if(userFolderItr!=null){
FiletransferSetHandler handler = new FiletransferSetHandler();
while(userFolderItr.hasNext()){
   String foldername = userFolderItr.next().toString();
   foldername = handler.decodeBase64(foldername);
%>
insDoc(aux__, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/archives.gif border=0>&nbsp;<%=foldername%>", "<%=request.getContextPath()%>/servlet/MessageListServlet?type=user&folder=<%=foldername%>&search=no", ""))
<%
}
}
%>

insDoc(fT, gLnk(0, "<img src=<%=request.getContextPath()%>/images/filetransfer/compose.gif border=0>&nbsp;属性设置", "<%=request.getContextPath()%>/servlet/SetAttributeServlet", ""))

//-->
</SCRIPT>
<style type="text/css">
<!--
body {
	background-color: #336699;
}
-->
</style></HEAD>

<BODY topmargin="0" leftmargin="5"  background="<%=request.getContextPath()%>/images/bg-24.jpg" >
<br>
<%@ include file= "/include/intendWork.jsp" %>
<br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-01.gif" width="178" height="28"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath()%>/images/left_img/07.gif">
    <table width="156"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6" colspan="2"></td>
      </tr>
      <tr>
        <td width=20></td>
        <td colspan="2" class="left">
           <script>
            ini()
            cON(1)
           </script>
        </td>
      </tr>
    </table>
	</td> 
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/08.gif" width="178" height="7"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
</table>

</BODY>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
<!--
function _reloadMainFrame(url,fI){
	if(fI == 1 || fI ==4)
	{
		var cF=0
		var state=0
		cF=iOE[fI]
		state=cF.isOpen
		cF.setState(!state)
	}
    parent.parent.frames["mainFrame"].location = url;
}
//-->
</SCRIPT>
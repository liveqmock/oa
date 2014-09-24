var x0=0,y0=0,x1=0,y1=0;
var offx=6,offy=6;
var moveable=false;
var hover='#00CCFF',normal='#2781E2';//color;
var index=10000;//z-index;
//开始拖动;
function startDrag(obj)
{
 //锁定标题栏;
 obj.setCapture();
 //定义对象;
 var win = obj.parentNode;
 var sha = win.nextSibling;
 //记录鼠标和层位置;
 x0 = event.clientX;
 y0 = event.clientY;
 x1 = parseInt(win.style.left);
 y1 = parseInt(win.style.top);
 //记录颜色;
 normal = obj.style.backgroundColor;
 //改变风格;
 obj.style.backgroundColor = hover;
 win.style.borderColor = hover;
 obj.nextSibling.style.color = hover;
 sha.style.left = x1 + offx;
 sha.style.top  = y1 + offy;
 moveable = true;
}
//拖动;
function drag(obj)
{
 var win = obj.parentNode;
 var sha = win.nextSibling;
 if(moveable)
 {
  win.style.left = x1 + event.clientX - x0;
  win.style.top  = y1 + event.clientY - y0;
  sha.style.left = parseInt(win.style.left) + offx;
  sha.style.top  = parseInt(win.style.top) + offy;
 }
}
//停止拖动;
function stopDrag(obj)
{
 var win = obj.parentNode;
 var sha = win.nextSibling;
 win.style.borderColor = normal;
 obj.style.backgroundColor = normal;
 obj.nextSibling.style.color = normal;
 sha.style.left = obj.parentNode.style.left;
 sha.style.top  = obj.parentNode.style.top;
 //放开标题栏;
 obj.releaseCapture();
 moveable = false;
}
//获得焦点;
function getFocus(obj)
{
 index = index + 2;
 var idx = index;
 obj.style.zIndex=idx;
 obj.nextSibling.style.zIndex=idx-1;
}
function min(obj)
{
 var win = obj.parentNode.parentNode;
 var sha = win.nextSibling;
 var tit = obj.parentNode;
 var msg = tit.nextSibling;
 var flg = msg.style.display=="none";
 if(flg)
 {
  win.style.height  = parseInt(msg.style.height) + parseInt(tit.style.height) + 2*2;
  sha.style.height  = win.style.height;
  msg.style.display = "block";
  obj.innerHTML = "0";
 }
 else
 {
  win.style.height  = parseInt(tit.style.height) + 2*2;
  sha.style.height  = win.style.height;
  obj.innerHTML = "2";
  msg.style.display = "none";
 }
}
function cls(obj)
{
 var win = obj.parentNode.parentNode;
 var sha = win.nextSibling;
 win.style.visibility = "hidden";
 sha.style.visibility = "hidden";
}
//创建一个对象;
function xWin(id,w,h,l,t,tit,msg)
{
 index = index+2;
 this.id      = id;
 this.width   = w;
 this.height  = h;
 this.left    = l;
 this.top     = t;
 this.zIndex  = index;
 this.title   = tit;
 this.message = msg;
 this.obj     = null;
 this.bulid   = bulid;
 this.bulid();
}
//初始化;
function bulid()
{
 var str = ""
  + "<div id=xMsg" + this.id + " "
  + "style='"
  + "z-index:" + this.zIndex + ";"
  + "width:" + this.width + ";"
  + "height:" + this.height + ";"
  + "left:" + this.left + ";"
  + "top:" + this.top + ";"
  + "background-color:" + normal + ";"
  + "color:" + normal + ";"
  + "font-size:12px;"
  + "font-family:Verdana;"
  + "position:absolute;"
  + "cursor:default;"
  + "border:2px solid " + normal + ";"
  + "' "
  + "onmousedown='getFocus(this)'>"
   + "<div "
   + "style='"
   + "background-color:" + normal + ";"
   + "width:" + (this.width-2*2) + ";"
   + "height:20;"
   + "color:white;"
   + "' "
   + "onmousedown='startDrag(this)' "
   + "onmouseup='stopDrag(this)' "
   + "onmousemove='drag(this)' "
   + ">"
    + "<span style='width:" + (this.width-2*12-4) + ";padding-left:3px;'>" + this.title + "</span>"
    + "<span style='width:12;border-width:0px;color:white;font-family:webdings;' onclick='min(this)'>0</span>"
    + "<span style='width:12;border-width:0px;color:white;font-family:webdings;' onclick='cls(this)'>r</span>"
   + "</div>"
    + "<div style='"
    + "width:100%;"
    + "height:" + (this.height-20-4) + ";"
    + "background-color:white;"
    + "line-height:14px;"
    + "word-break:break-all;"
    + "padding:3px;"
    + "'>" + this.message + "</div>"
  + "</div>"
  + "<div style='"
  + "width:" + this.width + ";"
  + "height:" + this.height + ";"
  + "top:" + this.top + ";"
  + "left:" + this.left + ";"
  + "z-index:" + (this.zIndex-1) + ";"
  + "position:absolute;"
  + "background-color:black;"
  + "filter:alpha(opacity=40);"
  + "'>?</div>";
  //alert(str);
 document.body.insertAdjacentHTML("beforeEnd",str);
}

function createMsg(_arr){
    var i = 0;
    var msg = "<table width='100%' border='0' align='left' cellpadding='0' cellspacing='0'>";
    while(_list.next()){
        i++;
    }
    if(i<=5){
        while(_arr(i)){
            msg+="<tr><td><a href='./lanmu.html'>"+_arr(i)+"</a></td></tr>";
        }
    }
    else{
        while(_arr(i)){
            msg+="<tr><td><a href='./lanmu.html'>"+_arr(i)+"</a></td></tr>";
        }
    }
    msg+="</table>";
    return msg;
}

<!--
function initialize()
{
   var strlist =  "";
   var onestr=strlist.split(";");
   var a = new xWin("1",120,120,250,50,"职能介绍",createMsg(garvenlist));
   var b = new xWin("2",120,120,720,50,"管理职能部门",createMsg(garvenlist));
   var c = new xWin("3",120,120,250,220,"采编职能部门",createMsg(cblist));
   var d = new xWin("4",120,120,720,220,"社办报刊",createMsg(sblist));
   var e = new xWin("5",120,120,250,368,"国内分社",createMsg(gllist));
   var f = new xWin("6",120,120,720,368,"驻（境）外分社",createMsg(zwlist));

}

function showit(_index){

  switch(_index){
      case "1":  if(xMsg2.style.visibility = "visible")
                 {
                     var sha2 = xMsg2.nextSibling;
                     xMsg2.style.visibility = "hidden";
                     sha2.style.visibility = "hidden";
                 }
                 if(xMsg3.style.visibility = "visible")
                 {
                     var sha3 = xMsg3.nextSibling;
                     xMsg3.style.visibility = "hidden";
                     sha3.style.visibility = "hidden";
                 }
                 if(xMsg4.style.visibility = "visible")
                 {
                     var sha4 = xMsg4.nextSibling;
                     xMsg4.style.visibility = "hidden";
                     sha4.style.visibility = "hidden";
                 }
                 if(xMsg5.style.visibility = "visible")
                 {
                     var sha5 = xMsg5.nextSibling;
                     xMsg5.style.visibility = "hidden";
                     sha5.style.visibility = "hidden";
                 }
                 if(xMsg6.style.visibility = "visible")
                 {
                     var sha6 = xMsg6.nextSibling;
                     xMsg6.style.visibility = "hidden";
                     sha6.style.visibility = "hidden";
                 }
                 var sha1 = xMsg1.nextSibling;
                 xMsg1.style.visibility = "visible";
                 sha1.style.visibility = "visible";
                 break;
      case "2":  if(xMsg1.style.visibility = "visible")
                 {
                     var sha1 = xMsg1.nextSibling;
                     xMsg1.style.visibility = "hidden";
                     sha1.style.visibility = "hidden";
                 }
                 if(xMsg3.style.visibility = "visible")
                 {
                     var sha3 = xMsg3.nextSibling;
                     xMsg3.style.visibility = "hidden";
                     sha3.style.visibility = "hidden";
                 }
                 if(xMsg4.style.visibility = "visible")
                 {
                     var sha4 = xMsg4.nextSibling;
                     xMsg4.style.visibility = "hidden";
                     sha4.style.visibility = "hidden";
                 }
                 if(xMsg5.style.visibility = "visible")
                 {
                     var sha5 = xMsg5.nextSibling;
                     xMsg5.style.visibility = "hidden";
                     sha5.style.visibility = "hidden";
                 }
                 if(xMsg6.style.visibility = "visible")
                 {
                     var sha6 = xMsg6.nextSibling;
                     xMsg6.style.visibility = "hidden";
                     sha6.style.visibility = "hidden";
                 }
                 var sha2 = xMsg2.nextSibling;
                 xMsg2.style.visibility = "visible";
                 sha2.style.visibility = "visible";
                 break;
      case "3":  if(xMsg2.style.visibility = "visible")
                 {
                     var sha2 = xMsg2.nextSibling;
                     xMsg2.style.visibility = "hidden";
                     sha2.style.visibility = "hidden";
                 }
                 if(xMsg1.style.visibility = "visible")
                 {
                     var sha1 = xMsg1.nextSibling;
                     xMsg1.style.visibility = "hidden";
                     sha1.style.visibility = "hidden";
                 }
                 if(xMsg4.style.visibility = "visible")
                 {
                     var sha4 = xMsg4.nextSibling;
                     xMsg4.style.visibility = "hidden";
                     sha4.style.visibility = "hidden";
                 }
                 if(xMsg5.style.visibility = "visible")
                 {
                     var sha5 = xMsg5.nextSibling;
                     xMsg5.style.visibility = "hidden";
                     sha5.style.visibility = "hidden";
                 }
                 if(xMsg6.style.visibility = "visible")
                 {
                     var sha6 = xMsg6.nextSibling;
                     xMsg6.style.visibility = "hidden";
                     sha6.style.visibility = "hidden";
                 }
                 var sha3 = xMsg3.nextSibling;
                 xMsg3.style.visibility = "visible";
                 sha3.style.visibility = "visible";
                 break;
      case "4":  
      if(xMsg2.style.visibility = "visible")
                 {
                     var sha2 = xMsg2.nextSibling;
                     xMsg2.style.visibility = "hidden";
                     sha2.style.visibility = "hidden";
                 }
                 if(xMsg3.style.visibility = "visible")
                 {
                     var sha3 = xMsg3.nextSibling;
                     xMsg3.style.visibility = "hidden";
                     sha3.style.visibility = "hidden";
                 }
                 if(xMsg1.style.visibility = "visible")
                 {
                     var sha1 = xMsg1.nextSibling;
                     xMsg1.style.visibility = "hidden";
                     sha1.style.visibility = "hidden";
                 }
                 if(xMsg5.style.visibility = "visible")
                 {
                     var sha5 = xMsg5.nextSibling;
                     xMsg5.style.visibility = "hidden";
                     sha5.style.visibility = "hidden";
                 }
                 if(xMsg6.style.visibility = "visible")
                 {
                     var sha6 = xMsg6.nextSibling;
                     xMsg6.style.visibility = "hidden";
                     sha6.style.visibility = "hidden";
                 }
                 var sha4 = xMsg4.nextSibling;
                 xMsg4.style.visibility = "visible";
                 sha4.style.visibility = "visible";
                 break;
      case "5":  if(xMsg2.style.visibility = "visible")
                 {
                     var sha2 = xMsg2.nextSibling;
                     xMsg2.style.visibility = "hidden";
                     sha2.style.visibility = "hidden";
                 }
                 if(xMsg3.style.visibility = "visible")
                 {
                     var sha3 = xMsg3.nextSibling;
                     xMsg3.style.visibility = "hidden";
                     sha3.style.visibility = "hidden";
                 }
                 if(xMsg4.style.visibility = "visible")
                 {
                     var sha4 = xMsg4.nextSibling;
                     xMsg4.style.visibility = "hidden";
                     sha4.style.visibility = "hidden";
                 }
                 if(xMsg1.style.visibility = "visible")
                 {
                     var sha1 = xMsg1.nextSibling;
                     xMsg1.style.visibility = "hidden";
                     sha1.style.visibility = "hidden";
                 }
                 if(xMsg6.style.visibility = "visible")
                 {
                     var sha6 = xMsg6.nextSibling;
                     xMsg6.style.visibility = "hidden";
                     sha6.style.visibility = "hidden";
                 }
                 var sha5 = xMsg5.nextSibling;
                 xMsg5.style.visibility = "visible";
                 sha5.style.visibility = "visible";
                 break;
      case "6":  if(xMsg2.style.visibility = "visible")
                 {
                     var sha2 = xMsg2.nextSibling;
                     xMsg2.style.visibility = "hidden";
                     sha2.style.visibility = "hidden";
                 }
                 if(xMsg3.style.visibility = "visible")
                 {
                     var sha3 = xMsg3.nextSibling;
                     xMsg3.style.visibility = "hidden";
                     sha3.style.visibility = "hidden";
                 }
                 if(xMsg4.style.visibility = "visible")
                 {
                     var sha4 = xMsg4.nextSibling;
                     xMsg4.style.visibility = "hidden";
                     sha4.style.visibility = "hidden";
                 }
                 if(xMsg5.style.visibility = "visible")
                 {
                     var sha5 = xMsg5.nextSibling;
                     xMsg5.style.visibility = "hidden";
                     sha5.style.visibility = "hidden";
                 }
                 if(xMsg6.style.visibility = "visible")
                 {
                     var sha1 = xMsg1.nextSibling;
                     xMsg1.style.visibility = "hidden";
                     sha1.style.visibility = "hidden";
                 }
                 var sha6 = xMsg6.nextSibling;
                 xMsg6.style.visibility = "visible";
                 sha6.style.visibility = "visible";
                 break;
  }                                                                                
}

function cls(){

 var sha1 = xMsg1.nextSibling;
 xMsg1.style.visibility = "hidden";
 sha1.style.visibility = "hidden";
 
 var sha2 = xMsg2.nextSibling;
 xMsg2.style.visibility = "hidden";
 sha2.style.visibility = "hidden";
 
 var sha3 = xMsg3.nextSibling;
 xMsg3.style.visibility = "hidden";
 sha3.style.visibility = "hidden";
 
 var sha4 = xMsg4.nextSibling;
 xMsg4.style.visibility = "hidden";
 sha4.style.visibility = "hidden";
 
 var sha5 = xMsg5.nextSibling;
 xMsg5.style.visibility = "hidden";
 sha5.style.visibility = "hidden";
 
 var sha6 = xMsg6.nextSibling;
 xMsg6.style.visibility = "hidden";
 sha6.style.visibility = "hidden";
 
}
//-->





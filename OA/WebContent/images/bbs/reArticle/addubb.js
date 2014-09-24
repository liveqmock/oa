var text_enter_url      = "请输入连接地址";
var text_enter_txt      = "请输入连接说明";
var error_no_url        = "您必须输入地址";
var error_no_txt        = "您必须输入连接说明";
var text_enter_fen      = "请输入数值,如：100 ";
var error_no_fen        = "您必须输入数值,如：100";
var text_enter_aligntxt ="请您输入文本"
var text_no_aligntxt    ="您必须输入文本"
var text_no_align       ="参数必须填写"
var Quote = 0;
var Bold  = 0;
var Italic = 0;
var Underline = 0;
var Code = 0;
var fly = 0;
var move = 0;
var html=0 ;
function commentWrite(fyCode) {
document.form1.f3_content.value+=fyCode;
document.form1.f3_content.focus();
return;
}


function beauty() {
var FoundErrors = '';
var enterbeauty  =prompt(text_enter_fen,"100");
if (!enterbeauty) {
FoundErrors += "\n" + error_no_fen;
}
if (FoundErrors) {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[beauty="+enterbeauty+"][/beauty]";
commentWrite(ToAdd);
}

function money() {
var FoundErrors = '';
var entermoney  =prompt(text_enter_fen,"100");
if (!entermoney) {
FoundErrors += "\n" + error_no_fen;
}
if (FoundErrors) {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[money="+entermoney+"][/money]";
commentWrite(ToAdd);
}


function weiwang() {
var FoundErrors = '';
var enterweiwang  =prompt(text_enter_fen,"1");
if (!enterweiwang) {
FoundErrors += "\n" + error_no_fen;
}
if (FoundErrors) {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[weiwang="+enterweiwang+"][/weiwang]";
commentWrite(ToAdd);
}


function buy() {
var FoundErrors = '';
var enterweiwang  =prompt(text_enter_fen,"100");
if (!enterweiwang) {
FoundErrors += "\n" + error_no_fen;
}
if (FoundErrors) {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[buy="+enterweiwang+"][/buy]";
commentWrite(ToAdd);
}


function send() {
var FoundErrors = '';
var entersend  =prompt(text_enter_fen,"100");
if (!entersend) {
FoundErrors += "\n" + error_no_fen;
}
if (FoundErrors) {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[send="+entersend+"][/send]";
commentWrite(ToAdd);
}

function fen() {
var FoundErrors = '';
var enterfen  =prompt(text_enter_fen,"100");
if (!enterfen) {
FoundErrors += "\n" + error_no_fen;
}
if (FoundErrors) {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[fen="+enterfen+"][/fen]";
commentWrite(ToAdd);
}

function email() {
var emailAddress = prompt(text_enter_url,"");
if (!emailAddress) { alert(error_no_url); return; }
var ToAdd = "[EMAIL]"+emailAddress+"[/EMAIL]";
commentWrite(ToAdd);
}



function flash()  { 
	var FoundErrors = '';
		txt2=prompt("FLASH的宽度，高度","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[flash="+txt2+"]"+txt+"[/flash]";
commentWrite(ToAdd);
}



function showsize(size)  
{  
     	txt=prompt("大小 "+size,""); 
if (!txt) { alert(text_no_aligntxt); return; }
var ToAdd = "[size="+size+"]"+txt+"[/size]";
commentWrite(ToAdd);
}


function login(){  
	var ToAdd = "[login][/login]";
commentWrite(ToAdd);

}
function replyview(){  
var ToAdd = "[replyview][/replyview]";
commentWrite(ToAdd);
}	

function cpbbold() {
if (Bold == 0) {
ToAdd = "[B]";
document.form1.bold.value = " B*";
Bold = 1;
} else {
ToAdd = "[/B]";
document.form1.bold.value = " B ";
Bold = 0;
}
commentWrite(ToAdd);
}

function cpbunder() {
if (Underline == 0) {
ToAdd = "[U]";
document.form1.under.value = " U*";
Underline = 1;
} else {
ToAdd = "[/U]";
document.form1.under.value = " U ";
Underline = 0;
}
commentWrite(ToAdd);
}

function cpbitalic() {
if (Italic == 0) {
ToAdd = "[I]";
document.form1.italic.value = " I*";
Italic = 1;
} else {
ToAdd = "[/I]";
document.form1.italic.value = " I ";
Italic = 0;
}
commentWrite(ToAdd);
}

function cpbcode() {
if (Code == 0) {
ToAdd = "[CODE]";
document.form1.code.value = "代码*";
Code = 1;
} else {
ToAdd = "[/CODE]";
document.form1.code.value = "代码 ";
Code = 0;
}
commentWrite(ToAdd);
}

function cpbhtml() {
if (html == 0) {
ToAdd = "[html]";
document.form1.html.value = "HTML*";
html = 1;
} else {
ToAdd = "[/html]";
document.form1.html.value = "HTML ";
html = 0;
}
commentWrite(ToAdd);
}

function cpbquote() {
if (Quote == 0) {
ToAdd = "[QUOTE]";
document.form1.quote.value = "引用*";
Quote = 1;
} else {
ToAdd = "[/QUOTE]";
document.form1.quote.value = "引用 ";
Quote = 0;
}
commentWrite(ToAdd);
}

function cpbfly() {
if (fly == 0) {
ToAdd = "[FLY]";
document.form1.fly.value = " 飞*";
fly = 1;
} else {
ToAdd = "[/FLY]";
document.form1.fly.value = " 飞 ";
fly = 0;
}
commentWrite(ToAdd);
}

function cpbmove() {
if (move == 0) {
ToAdd = "[move]";
document.form1.move.value = " 移*";
move = 1;
} else {
ToAdd = "[/move]";
document.form1.move.value = " 移 ";
move = 0;
}
commentWrite(ToAdd);
}

function cpbcenter()  {  
	var FoundErrors = '';
		var txt2=prompt("对齐样式\n输入 'center' 表示居中, 'left' 表示左对齐, 'right' 表示右对齐.","center");               
		while ((txt2!="center") && (txt2!="left") && (txt2!="right")) {
			txt2=prompt("错误!\n类型只能输入 'center' 、 'left' 或者 'right'.","center");               
		}
		var txt=prompt(text_enter_aligntxt,"");  
		if (!txt)    {
FoundErrors += "\n" + text_no_aligntxt;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[align="+txt2+"]"+txt+"[/align]";
commentWrite(ToAdd);
}
		



function hyperlink()  { 
var FoundErrors = '';
var enterURL   = prompt(text_enter_url, "");
var enterTxT   = prompt(text_enter_txt, enterURL);
if (!enterURL)    {
FoundErrors += "\n" + error_no_url;
}
if (!enterTxT)    {
FoundErrors += "\n" + error_no_txt;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[URL="+enterURL+"]"+enterTxT+"[/URL]";
commentWrite(ToAdd);
}


function image()  {  
	var enterURL = prompt(text_enter_url,"");
if (!enterURL) { alert(error_no_url); return; }
var ToAdd = "[img]"+enterURL+"[/img]";
commentWrite(ToAdd);
}

function showcolor(color){  
     	txt=prompt("请输入文本,选择的颜色是: "+color,"");
if (!txt) { alert(text_no_aligntxt); return; }
var ToAdd = "[color="+color+"]"+txt+"[/color]";
commentWrite(ToAdd);
}



function showfont(font){                  
		txt=prompt("要设置字体的文字"+font,"");
if (!txt) { alert(text_no_aligntxt); return; }
var ToAdd = "[face="+font+"]"+txt+"[/face]";
commentWrite(ToAdd);
}



function cpbshadow() { 
	var FoundErrors = '';
		var txt2=prompt("文字的长度、颜色和边界大小","250,blue,1"); 		
var txt=prompt(text_enter_aligntxt,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + text_no_aligntxt;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[SHADOW="+txt2+"]"+txt+"[/SHADOW]";
commentWrite(ToAdd);
}
	
	

function rm()  { 
	var FoundErrors = '';
		txt2=prompt("视频的宽度，高度","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[rm="+txt2+"]"+txt+"[/rm]";
commentWrite(ToAdd);
}


function mp() { 
			var FoundErrors = '';
		txt2=prompt("视频的宽度，高度","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[mp="+txt2+"]"+txt+"[/mp]";
commentWrite(ToAdd);
}



function qt() { 
			var FoundErrors = '';
		txt2=prompt("视频的宽度，高度","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[qt="+txt2+"]"+txt+"[/qt]";
commentWrite(ToAdd);
}


function sk() {
		var FoundErrors = '';
		txt2=prompt("视频的宽度，高度","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[sk="+txt2+"]"+txt+"[/sk]";
commentWrite(ToAdd);
}


function cpbglow() 
{ 
	var FoundErrors = '';
		var txt2=prompt("文字的长度、颜色和边界大小","250,red,2"); 		
var txt=prompt(text_enter_aligntxt,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + text_no_aligntxt;
}
if (FoundErrors)  {
alert("错误！"+FoundErrors);
return;
}
var ToAdd = "[glow="+txt2+"]"+txt+"[/glow]";
commentWrite(ToAdd);
}
function download() {  
var enterURL = prompt(text_enter_url,"");
if (!enterURL) { alert(error_no_url); return; }
var ToAdd = "[download]"+enterURL+"[/download]";
commentWrite(ToAdd);
}

function frame()  {  
var enterURL = prompt(text_enter_url,"");
if (!enterURL) { alert(error_no_url); return; }
var ToAdd = "[frame]"+enterURL+"[/frame]";
commentWrite(ToAdd);
}

function sound() {  
var enterURL = prompt(text_enter_url,"");
if (!enterURL) { alert(error_no_url); return; }
var ToAdd = "[sound]"+enterURL+"[/sound]";
commentWrite(ToAdd);
}
//心情图片
function setsmiley(what) 
{ 
document.form1.f3_content.value = document.form1.f3_content.value+" "+what; 
document.form1.f3_content.focus(); 
} 
//动作
function dongzuo(addTitle) { 
ToAdd = addTitle; 
commentWrite(ToAdd);
return; }
function huati(addTitle) { 
ToAdd = addTitle; 
document.form1.f3_motif.value+=ToAdd;
document.form1.f3_motif.focus();
return; }


function up()
{
if(event.keyCode==13 && event.ctrlKey && !document.form1.Submit.disabled)
{
form1.submit();
document.form1.Submit.disabled=true;
}
//帖子长度
document.form1.Len.value =document.form1.f3_content.value.length;
}
//预览
function view()
{
document.preview.title.value=document.form1.f3_motif.value;
document.preview.content.value=document.form1.f3_content.value;
document.preview.submit()
}
function Show(divid) {
divid.filters.revealTrans.apply(); 
divid.style.visibility = "visible"; 
divid.filters.revealTrans.play(); 
}
function Hide(divid) {
divid.filters.revealTrans.apply();
divid.style.visibility = "hidden";
divid.filters.revealTrans.play();
}


var text_enter_url      = "���������ӵ�ַ";
var text_enter_txt      = "����������˵��";
var error_no_url        = "�����������ַ";
var error_no_txt        = "��������������˵��";
var text_enter_fen      = "��������ֵ,�磺100 ";
var error_no_fen        = "������������ֵ,�磺100";
var text_enter_aligntxt ="���������ı�"
var text_no_aligntxt    ="�����������ı�"
var text_no_align       ="����������д"
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
alert("����"+FoundErrors);
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
alert("����"+FoundErrors);
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
alert("����"+FoundErrors);
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
alert("����"+FoundErrors);
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
alert("����"+FoundErrors);
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
alert("����"+FoundErrors);
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
		txt2=prompt("FLASH�Ŀ�ȣ��߶�","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
return;
}
var ToAdd = "[flash="+txt2+"]"+txt+"[/flash]";
commentWrite(ToAdd);
}



function showsize(size)  
{  
     	txt=prompt("��С "+size,""); 
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
document.form1.code.value = "����*";
Code = 1;
} else {
ToAdd = "[/CODE]";
document.form1.code.value = "���� ";
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
document.form1.quote.value = "����*";
Quote = 1;
} else {
ToAdd = "[/QUOTE]";
document.form1.quote.value = "���� ";
Quote = 0;
}
commentWrite(ToAdd);
}

function cpbfly() {
if (fly == 0) {
ToAdd = "[FLY]";
document.form1.fly.value = " ��*";
fly = 1;
} else {
ToAdd = "[/FLY]";
document.form1.fly.value = " �� ";
fly = 0;
}
commentWrite(ToAdd);
}

function cpbmove() {
if (move == 0) {
ToAdd = "[move]";
document.form1.move.value = " ��*";
move = 1;
} else {
ToAdd = "[/move]";
document.form1.move.value = " �� ";
move = 0;
}
commentWrite(ToAdd);
}

function cpbcenter()  {  
	var FoundErrors = '';
		var txt2=prompt("������ʽ\n���� 'center' ��ʾ����, 'left' ��ʾ�����, 'right' ��ʾ�Ҷ���.","center");               
		while ((txt2!="center") && (txt2!="left") && (txt2!="right")) {
			txt2=prompt("����!\n����ֻ������ 'center' �� 'left' ���� 'right'.","center");               
		}
		var txt=prompt(text_enter_aligntxt,"");  
		if (!txt)    {
FoundErrors += "\n" + text_no_aligntxt;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
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
alert("����"+FoundErrors);
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
     	txt=prompt("�������ı�,ѡ�����ɫ��: "+color,"");
if (!txt) { alert(text_no_aligntxt); return; }
var ToAdd = "[color="+color+"]"+txt+"[/color]";
commentWrite(ToAdd);
}



function showfont(font){                  
		txt=prompt("Ҫ�������������"+font,"");
if (!txt) { alert(text_no_aligntxt); return; }
var ToAdd = "[face="+font+"]"+txt+"[/face]";
commentWrite(ToAdd);
}



function cpbshadow() { 
	var FoundErrors = '';
		var txt2=prompt("���ֵĳ��ȡ���ɫ�ͱ߽��С","250,blue,1"); 		
var txt=prompt(text_enter_aligntxt,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + text_no_aligntxt;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
return;
}
var ToAdd = "[SHADOW="+txt2+"]"+txt+"[/SHADOW]";
commentWrite(ToAdd);
}
	
	

function rm()  { 
	var FoundErrors = '';
		txt2=prompt("��Ƶ�Ŀ�ȣ��߶�","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
return;
}
var ToAdd = "[rm="+txt2+"]"+txt+"[/rm]";
commentWrite(ToAdd);
}


function mp() { 
			var FoundErrors = '';
		txt2=prompt("��Ƶ�Ŀ�ȣ��߶�","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
return;
}
var ToAdd = "[mp="+txt2+"]"+txt+"[/mp]";
commentWrite(ToAdd);
}



function qt() { 
			var FoundErrors = '';
		txt2=prompt("��Ƶ�Ŀ�ȣ��߶�","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
return;
}
var ToAdd = "[qt="+txt2+"]"+txt+"[/qt]";
commentWrite(ToAdd);
}


function sk() {
		var FoundErrors = '';
		txt2=prompt("��Ƶ�Ŀ�ȣ��߶�","300,200"); 			
var txt=prompt(text_enter_url,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + error_no_url;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
return;
}
var ToAdd = "[sk="+txt2+"]"+txt+"[/sk]";
commentWrite(ToAdd);
}


function cpbglow() 
{ 
	var FoundErrors = '';
		var txt2=prompt("���ֵĳ��ȡ���ɫ�ͱ߽��С","250,red,2"); 		
var txt=prompt(text_enter_aligntxt,"");  	
if (!txt2)    {
FoundErrors += "\n" + text_no_align;
}		
if (!txt)    {
FoundErrors += "\n" + text_no_aligntxt;
}
if (FoundErrors)  {
alert("����"+FoundErrors);
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
//����ͼƬ
function setsmiley(what) 
{ 
document.form1.f3_content.value = document.form1.f3_content.value+" "+what; 
document.form1.f3_content.focus(); 
} 
//����
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
//���ӳ���
document.form1.Len.value =document.form1.f3_content.value.length;
}
//Ԥ��
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


var intIndex=0;arrList = new Array();

//����ʼ�����б�ת��������
function dearray(str)//����array
{
 arrList = str.split(",");
 intIndex = arrList.length;
}

//��ʼ�������б���
function begin()
{
 init();
 dearray("asp,csdn,asp.net,php,jsp,dvbbs,baidu,92mk,123cha,hao123,google,3721,123456,popasp,alimama,aku,cool");
 downList(arrList,"wd");
}

function init()
{
 if(arrList.constructor!=Array)
 {
  alert("downList��ʼ��ʧ��:��һ������������!");
  return;
 }

 arrList.sort( function(a, b)
 {
  if(a.length>b.length)return 1;
  else if(a.length==b.length)return a.localeCompare(b);
  else return -1;
 }); 
}

function downList(arrList,objInputId)
{
 var objouter=document.getElementById("keysList"); //��ʾ��DIV����
 var objInput = document.getElementById(objInputId); //�ı������
 var selectedIndex=-1;
 var intTmp; //ѭ���õ�
 
 if (objInput==null)
 {
  alert("downList��ʼ��ʧ��:û���ҵ�"+objInputId+"�ı���");
  return;
 }
 //�ı���ʧȥ����
 objInput.onblur=function(){
  objouter.style.display="none";
 }
 //�ı��򰴼�̧��
 objInput.onkeyup=checkKeyCode;
 //�ı���õ�����
 objInput.onfocus=checkAndShow;

 //�жϰ��µİ���
 function checkKeyCode(evt)
 {
  evt = evt || window.event;
  var keyCode = window.event ? evt.keyCode : evt.which;
  //var keyCode = String.fromCharCode(key);
  if (keyCode==40||keyCode==38)
  {//����
   var isUp=false
   if(keyCode==40) isUp=true;
   chageSelection(isUp);
  }
  else if (keyCode==13)
  {//�س�
   outSelection(selectedIndex);
  }
  else
  {
   checkAndShow(evt);
  }
  divPosition(evt);
 }
 
 function checkAndShow(evt)
 {
  var strInput = objInput.value;
  if (strInput!="")
  {
   divPosition(evt);
   selectedIndex=-1;
   objouter.innerHTML ="";
   
   for (intTmp=0;intTmp<arrList.length;intTmp++)
   {
    if (arrList[intTmp].substr(0, strInput.length)==strInput)
    {
     addOption(arrList[intTmp]);
    }
   }
   objouter.style.display="";
  }
  else
  {
   objouter.style.display="none";
  }
 
  //�������б������ƥ����
  function addOption(value)
  {
   objouter.innerHTML +="<div onmouseover=this.className=\"sman_selectedStyle\";document.getElementById(\""+objInputId+"\").value=\"" + value + "\" onmouseout=this.className=\"\" onmousedown=document.getElementById(\""+objInputId+"\").value=\"" + value + "\">" + value + "</div>" 
  }
 
 }//end checkAndShow()
 
 function chageSelection(isUp)
 {
  if (objouter.style.display=="none")
  {
   objouter.style.display="";
  }
  else
  {
   if (isUp)
    selectedIndex++;
   else
    selectedIndex--;
  }
  
  var maxIndex = objouter.childNodes.length-1;
  if (selectedIndex<0){selectedIndex=0;}
  if (selectedIndex>maxIndex) {selectedIndex=maxIndex;}
  for (intTmp=0;intTmp<=maxIndex;intTmp++)
  {
   if (intTmp==selectedIndex)
   {
    objouter.childNodes[intTmp].className="sman_selectedStyle";
    //�����¼��ƶ�ʱ����ѡ�е��ı�д���ı�����
    document.getElementById(objInputId).value=objouter.childNodes[intTmp].innerHTML;
   }
   else
   {
    objouter.childNodes[intTmp].className="";
   }
  }
 }
 
 function outSelection(Index)
 {
  objInput.value = objouter.childNodes[Index].innerHTML;
  objouter.style.display="none";
 }
 
 //��ʾ�����б���
 function divPosition(evt)
 {
  var e = objInput;
  var ie = (document.all)? true:false
  //�����б����ڲ�ͬ������е�λ��
  if (ie)
  {
   var top = 0;
   var left = -2;
  }
  else
  {
   var top = 2;
   var left = 0;
  }
   
  while (e.offsetParent)
  {
   left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
   top += e.offsetTop + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);
   e = e.offsetParent;
  }
  
  left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
  top += e.offsetTop + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);
  objouter.style.top = (top + objInput.clientHeight) + "px";
  objouter.style.left = left + "px"; 
  objouter.style.width= objInput.clientWidth+1 + "px";
 }

}//end downList()


function getAbsoluteHeight(ob)
{
 return ob.offsetHeight;
}

function getAbsoluteWidth(ob)
{
 return ob.offsetWidth;
}

function getAbsoluteLeft(ob)
{
 var mendingLeft = ob .offsetLeft;
 while( ob != null && ob.offsetParent != null && ob.offsetParent.tagName != "BODY" )
 {
  mendingLeft += ob .offsetParent.offsetLeft;
  mendingOb = ob.offsetParent;
 }
 return mendingLeft;
}

function getAbsoluteTop(ob)
{
 var mendingTop = ob.offsetTop;
 while( ob != null && ob.offsetParent != null && ob.offsetParent.tagName != "BODY" )
 {
  mendingTop += ob.offsetParent.offsetTop;
  ob = ob.offsetParent;
 }
 return mendingTop;
}

Number.prototype.NaN0 = function()
{
 return isNaN(this)?0:this;
}

//���ֹ��
document.write("<div id=\"keysList\" style=\"position:absolute;display:none;background:#FFFFFF;border: 1px solid #CCCCCC;font-size:14px;cursor: default;\" onblur> </div>");
document.write("<style>.sman_selectedStyle{background-Color:#102681;color:#FFFFFF}</style>");


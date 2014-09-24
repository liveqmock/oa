/**
 *  质检机构部门树
 * 树形控制类
 * @author lancelot
 */
var TreeMenu=new function()
{   
    this._url=null;
    this.targeturl=null;
    
    //加入样式
    document.write('<style type="text/css">');
    document.write('.treeCheckBox{height:11px; width:11px;vertical-align:middle}');
    document.write('.treeImg{cursor:pointer;vertical-align:text-bottom;margin-right:2px}');
    document.write('</style>');
    
    //定义图标显示用数组
	this.icon=new Array();
	this.icon["member"]='/source/style/images/jccps/child.gif';
	this.icon["open"]='/source/style/images/jccps/opened.gif';
	this.icon["close"]='/source/style/images/jccps/closed.gif';	
	this.icon["lplus"]='/source/style/images/jccps/lplus.gif';
	this.icon["lminus"]='/source/style/images/jccps/lminus.gif';
	this.icon["Treeline"]='/source/style/images/jccps/Treeline.gif';
     
	/**
	 * 获取指定节点的子节点
	 * @param _parentId:指定节点的id
	 */ 
	this.getChildren=function(_parentId)
	{
	   // alert("id="+_parentId);
	  
	    if (this.alreadyGetChileren(_parentId))
		{
		  //  alert("kk");
		    var childContainer=document.getElementById(_parentId+"_subContainer");
		   
		    if (childContainer)
		    {
		     	//alert(childContainer.outerHTML);
		        childContainer.style.display=(childContainer.style.display=="none")?"":"none";
		        var _parentNode=document.getElementById(_parentId);
		        if (_parentNode.firstChild && _parentNode.firstChild.tagName=="IMG")
		        {
		            _parentNode.firstChild.src=(childContainer.style.display=="none")?this.icon["lplus"]:this.icon["lminus"];
		            
		        }
		    }
		    return;
		}
		//var _parentNewId = '';
		//alert("进入得到："+_parentId);
		//if(_parentId!='-2'&&_parentId.indexOf("_")<0){
	  	//	_parentNewId = _parentId+"_tree";
	  	//}
	  	//if(_parentId.indexOf("_")>0){
	  		//alert("父IDis"+_parentId);
	  	//	_parentNewId=_parentId;
	  	//	_parentId=_parentId.substr(0,_parentId.indexOf("_"));
	  		//alert(_parentId);
	  //	}
	  	//alert(_parentNewId+":"+_parentId);
		var processRequest=function(obj)
		{
		   // TreeMenu.addChildren(_parentNewId,_parentId,obj.responseXML);  
		    TreeMenu.addChildren(_parentId,obj.responseXML);          
		}
		var kkl=this._url+"&pId="+_parentId;
		//alert("_url"+kkl);
		Request.send(this._url+"&pId="+_parentId,"",processRequest,_parentId+"");			
	}	
	
	/**
	 * 根据获取的数据，设置指定节点的子节点
	 * @param _parentId:指定节点id
	 * @param _data:获取的数据
	 */
	this.addChildren=function(_parentId,_data)
	{   
		
		///if(_parentNewId ==''){
		//	_parentNewId =_parentId;
		//}
	    if (this.alreadyGetChileren(_parentId))
	    {	     
	        return;
	    }
	    var _parentNode=document.getElementById(_parentId);
	    
	    if (_parentNode.firstChild && _parentNode.firstChild.tagName=="IMG")
	    {
	        _parentNode.firstChild.src=this.icon["lminus"];
	    }	    	         
	    //子级容器，所有子级选项都放一个容器中
	    _nodeContainer=document.createElement("div");
		_nodeContainer.id=_parentId+"_subContainer";
		//子级容器放入父级容器
		_parentNode.appendChild(_nodeContainer);
		var _children=_data.getElementsByTagName("root")[0].childNodes;
		var _child=null;
		var _point=this;
		for(var i=0; i<_children.length; i++)
		{
			_child=_children[i];	
		
			_node=document.createElement("div");
			if (i!=_children.length-1)
			{
			    _node.style.cssText="padding-bottom:5px";
			}			
			_node.innerHTML="";
			_node.id=_child.getAttribute("id");
			var orgcode =_child.getAttribute("orgcode");
			var _idvalue = _node.id;
			if(orgcode!=null){
				_idvalue = orgcode;
			}
			//var _id = _child.getAttribute("id");
			//if(_id!='-2'&&_id.indexOf("_")<0){
			//	_node.id=_id+"_tree";
			//}
			
			//alert("_node.id==="+_node.id);
			//alert(_node.id);		
			//若节点存在下级节点
			if (_child.getAttribute("hasChildren")=="1")
			{
			   // alert("传值："+_node.id);
			    _node.innerHTML+='<img class="treeImg" onclick="TreeMenu.getChildren(\''+_node.id+'\')" src="'+this.icon["lplus"]+'"/>';
			    _node.innerHTML+='<span style="cursor:pointer;line-height:16px;height:16px" name="treeText"  onclick="TreeMenu.getChildren(\''+_node.id+'\');treeNodeChoosed(this,\''+_idvalue+'\',\''+_child.firstChild.data+'\',\''+_node.id+'\');">'+_child.firstChild.data+'</span>';			    
			   //alert(_node.outerHTML);
			}
			//否则节点不存在下级节点
			else if (_child.getAttribute("hasChildren")==0)
			{
			    _node.innerHTML+='<img class="treeImg" onclick="try{treeNodeChoosed(this.nextSibling);}catch(e){alert(e.message);}" src="'+this.icon["member"]+'" style="margin-left:14px"/>';
			    _node.innerHTML+='<span style="cursor:pointer;line-height:16px;height:16px" name="treeText"  onclick="treeNodeChoosed(this,\''+_idvalue+'\',\''+_child.firstChild.data+'\',\''+_node.id+'\');">'+_child.firstChild.data+'</span>';			    
			}
			//节点加入子级容器
			_nodeContainer.appendChild(_node);						
		}
		//alert(_node);
		_nodeContainer.style.cssText="border-left:0px solid #ccc;margin-left:7px;margin-top:5px;padding-left:10px";	    
	}
	
	/**
	 * 判断指定节点是否已经获取子节点
	 * @param _nodeId 指定节点id
	 * @return [boolean]true为已经获取，false为未获取
	 */
	this.alreadyGetChileren=function(_nodeId)
	{
	    
	    var obj=document.getElementById(_nodeId+"_subContainer");
	    if (obj)
	    {	       
	        return true;               
	    }
	    return false;	    
	}	
}

/**
 * 点击节点后动作
 */
function treeNodeChoosed(_obj,_value,_name,_uuid)
{
    //alert(_uuid);
    var choosedColor="lightblue";
    var unChoosedColor="white";
   // var dataValues = new Array();
    var hmpvalue = new Array(2);
    var nameSz = new Array();
	var valueSz = new Array();
   // dataValues["FCPDM"]=_value;
   // dataValues["FCPMC"]=_name;
   // nameSz[nameSz.length]=_name;
    //valueSz[valueSz.length]=_value;
    //alert(_value);
    //parent.setValue(_value);
    //setValue(_value,_name,_uuid);
    if (_obj.style.backgroundColor==choosedColor)
    {
        _obj.style.backgroundColor=unChoosedColor;           
    }
    else
    {
        //var allNodeText=document.getElementsByName("treeText");
        var allNodeText=document.getElementsByTagName("SPAN");
        for (var i=0; i<allNodeText.length; i++)
        {
            allNodeText[i].style.backgroundColor=unChoosedColor;
        }
        _obj.style.backgroundColor=choosedColor;
    }  
  //  hmpvalue[0]=nameSz;
  //	hmpvalue[1]=valueSz;
  
  //parent.frames["mainFrame"].location.href="test.jsp";
   //parent.frames["mainFrame"].location="test.jsp";
  // alert(window.top.frames["mainFrame"]); 
  var k_url=TreeMenu.targeturl+"?page=1&pageSize=15&departmentid="+_uuid;
 // alert(k_url);
   window.top.frames["mainFrame"].document.location = k_url;   
}


function convertAllModulsAct(obj,resnum,levelcode) {
  if (0 !='1'){	
  	return;
  }
  
  var rms =  document.getElementsByName("RoleModuls");
  var inputs = eval("res"+resnum).getElementsByTagName("INPUT");
  for(var b=0;b<inputs.length;b++){
      inputs[b].checked = obj.checked;
  }

  var pattarn = levelcode;
  while(pattarn.length>0){
      pattarn=pattarn.substring(0,pattarn.length-4);
	  
	  for(var a =0;a<rms.length;a++){
	      var str = rms[a].levelcode;
	      if(str.indexOf(pattarn)==0 && str.length<=pattarn.length){
	          rms[a].checked =true;
	      }
	  }
  }
}
function convertAllModul(t,id, obj) {
	var inputs;
	if (t==-1){
		id = "tree";
	}
	var _div = document.getElementById(id);
	if (_div==null){
		return;
	}
	inputs = _div.getElementsByTagName("INPUT");
  for(var b=0;b<inputs.length;b++){
      inputs[b].checked = obj.checked;
  }
}
function convertAllModuls(obj,appnum){
  var inputs = eval("app"+appnum).getElementsByTagName("INPUT");
  for(var b=0;b<inputs.length;b++){
      inputs[b].checked = obj.checked;
  }
}

//处理树是否被选中
function checkSubTree(resId, obj){
	var cur = obj;
	var inputs = eval(resId).getElementsByTagName("INPUT");
	//check parent
	while(true){
		var parentCheck = document.getElementById(cur.p);
		if(parentCheck != null){
			if(parentCheck.checked == false){
				parentCheck.checked = true;
			}
		}else{
			break;
		}
		cur = parentCheck;
	}
	
	level = resId.substring(1);
	var length = level.length;
	while(length > 4){
		pl = level.substring(0, length - 4);
		length = length - 4;
		var pv = document.getElementById(pl);
		if(pv != null){
			
		}
	}
	for(var b=0;b<inputs.length;b++){
  	  if(inputs[b].name=="RoleModuls" || inputs[b].name=="RoleModulsAct"){
  	    inputs[b].checked = obj.checked;
  	  }
  }
}
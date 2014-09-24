/*******************************
        NetFolderTree	
********************************/
function TreeView(obj,target){
	this.obj=obj;
	this.target=target?target:'_blank';
	this.root=new node(0);
	this.nodes=[]
	this.currentNode=null;
	this.html=""
	this.config={
		root		:'./../images/tree/web_sm.gif',
		open		:'./../images/tree/open.gif',
		close		:'./../images/tree/close.gif',
		file		:'./../images/tree/file_sm.gif',
		join		:'./../images/tree/T.gif',
		joinbottom		:'./../images/tree/L.gif',
		plus		:'./../images/tree/Tplus.gif',
		plusbottom		:'./../images/tree/plus_end.gif',
		minus		:'./../images/tree/Tminus.gif',
		minusbottom		:'./../images/tree/minus_end.gif',
		blank		:'./../images/tree/empty.gif',
		line		:'./../images/tree/I.gif'
	}
	for(i in this.config){var tem=this.config[i];this.config[i]=new Image();this.config[i].src=tem}
}

function node(id,pid,txt,link,title,target){
	this.id=id
	this.pid=pid
	this.txt=txt
	this.link=link
	this.title=title
	this.target=target
	this.indent=""
	this.lastNode=false;
	this.open=false;
	this.hasNode=false
}

TreeView.prototype.getP=function(nid){
		var nNode=this.nodes[nid]
		for(var i=0;i<this.nodes.length;i++)
			if(this.nodes[i].id==nNode.pid)return i
		return null
}

TreeView.prototype.add=function(id,pid,txt,link,title,target){
	var itemTxt=txt?txt:"New Item"
	var itemLink=link?link:''
	var itemTitle=title?title:itemTxt;
	var itemTarget=target?target:this.target;
	this.nodes[this.nodes.length]=new node(id,pid,itemTxt,itemLink,itemTitle,itemTarget)
	pid==this.root.id?this.nodes[this.nodes.length-1].open=true:''
}

TreeView.prototype.DrawTree=function(pNode){
	var str=""
	for(var i=0;i<this.nodes.length;i++){
		if(this.nodes[i].pid==pNode.id){
		str+=this.DrawNode(i)
		}
	}
	return str
}

TreeView.prototype.ChkPro=function(pNode){
	var last;
	for(var n=0;n<this.nodes.length;n++){
		if(this.nodes[n].pid==pNode.id)pNode.hasNode=true;
		if (this.nodes[n].pid == pNode.pid) last= this.nodes[n].id;
	}
	if (last==pNode.id) pNode.lastNode = true;
}

TreeView.prototype.DrawNode=function(nid){
	var str=""
	var nNode=this.nodes[nid]
	this.DrawLine(nNode,nNode)
	if(nNode.hasNode)
	nNode.indent+="<a  href='' onclick='"+this.obj+".Click("+nid+",this);return(false)'>"
	nNode.indent+=this.root.id!=nNode.pid?("<img style='BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px;width:16;height:16' align='absmiddle' src='"+(nNode.lastNode?(nNode.hasNode?this.config.plusbottom.src:this.config.joinbottom.src):(nNode.hasNode?this.config.plus.src:this.config.join.src))+"' id='icon"+this.obj+nid+"'>"):""
	nNode.indent+="<img style='BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px;width:16;height:16' id='folder"+this.obj+nid+"' align='absmiddle' src='"+(nNode.pid==this.root.id?this.config.root.src:(nNode.hasNode?(nNode.open?this.config.open.src:this.config.close.src):this.config.file.src))+"'>"+(nNode.hasNode?"</a>":"")
	str+="<div class='node'><nobr>"+nNode.indent+this.DrawLink(nid)+"</nobr></div>"
	if(nNode.hasNode){
	str+="<div id='Child"+this.obj+nid+"' style='display:"+(nNode.pid==this.root.id?'':'none')+"'>"
	str+=this.DrawTree(nNode)
	str+="</div>"
	}
	return str;
}


TreeView.prototype.DrawLine=function(nNode,tem){
	for(var i=1;i<this.nodes.length;i++){
		if(this.nodes[i].id==tem.pid){
		nNode.indent="<img style='BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px;width:16;height:16' align='absmiddle' src='"+(this.nodes[i].lastNode?this.config.blank.src:this.config.line.src)+"'>"+nNode.indent
		this.DrawLine(nNode,this.nodes[i])
		}
	}
}

TreeView.prototype.Click=function(nid,oLink){
	var nNode=this.nodes[nid]
	if(!nNode.hasNode)return;
	if(nNode.open)this.collapse(nid)
	else this.expand(nid)
	if(oLink)oLink.blur()
}

TreeView.prototype.selected=function(nid){
	if(this.currentNode==null)this.currentNode=nid;
	var current=document.getElementById('node'+this.obj+this.currentNode)
	var node=document.getElementById('node'+this.obj+nid)
	current.className='normal'
	node.className="selected"
	this.currentNode=nid
	this.OpenWin(nid)
}

TreeView.prototype.OpenWin=function(nid){
	var nNode=this.nodes[nid]
	if(!nNode.link||nNode.link=="#")return;
	window.open(nNode.link,nNode.target)
}


TreeView.prototype.expandAll=function(pid){
	for(i=0;i<this.nodes.length;i++)
	if(this.nodes[i].hasNode)this.expand(i)
}

TreeView.prototype.collapseAll=function(){
	for(i=0;i<this.nodes.length;i++)
	if(this.nodes[i].hasNode)this.collapse(i)
}

TreeView.prototype.expandTo=function(nid){
	if(this.getP(nid)!=null){
		if(this.nodes[nid].hasNode)this.expand(nid)
		this.expandTo(this.getP(nid))
	}
}

TreeView.prototype.expand=function(nid){
	var node=document.getElementById('Child'+this.obj+nid)
	var icon=document.getElementById('icon'+this.obj+nid)
	var nNode=this.nodes[nid]
	node.style.display=''
	if(icon)icon.src=(nNode.lastNode?this.config.minusbottom.src:this.config.minus.src)
	if(nNode.pid!=this.root.id)document.getElementById("folder"+this.obj+nid).src=this.config.open.src
	nNode.open=true
}

TreeView.prototype.collapse=function(nid){
	var node=document.getElementById('Child'+this.obj+nid)
	var icon=document.getElementById('icon'+this.obj+nid)
	var nNode=this.nodes[nid]
	node.style.display='none'
	if(icon)icon.src=(nNode.lastNode?this.config.plusbottom.src:this.config.plus.src)
	if(nNode.pid!=this.root.id)document.getElementById("folder"+this.obj+nid).src=this.config.close.src
	nNode.open=false
}

TreeView.prototype.DrawLink=function(nid){
	var nNode=this.nodes[nid]
	return "<span id='node"+this.obj+nid+"' class='normal' onmouseover='"+this.obj+".MouseOver(this)' onmouseout='"+this.obj+".MouseOut(this)' ondblclick='"+this.obj+".Click("+nid+")' onclick='"+this.obj+".selected("+nid+")'  title='"+nNode.title+"'>"+nNode.txt+"</span>"
}

TreeView.prototype.MouseOver=function(o){
	var nNode=document.getElementById("node"+this.obj+this.currentNode)
	if(nNode!=o)o.className='MouseOver'
}

TreeView.prototype.MouseOut=function(o){
	var nNode=document.getElementById("node"+this.obj+this.currentNode)
	o.className=nNode==o?'selected':'normal'
}

TreeView.prototype.toString=function(){
	var str=""
	for(var i=0;i<this.nodes.length;i++)this.ChkPro(this.nodes[i])
	str+=this.DrawTree(this.root)
	return str
}

TreeView.prototype.open=function(){
	var nid=QueryString("id")
	if(isNaN(nid))return;
	if(nid<0||isNaN(parseInt(nid))||nid>this.nodes.length-1)return;
	this.expandTo(nid)
	this.selected(nid)
}

TreeView.prototype.keepOpen=function(pid){
    for(i=0;i<this.nodes.length;i++)
        if(this.nodes[i].id == pid){ 
		    this.expandTo(i)
			if(this.currentNode==null)this.currentNode=i;
	        var current=document.getElementById('node'+this.obj+this.currentNode)
	        var node=document.getElementById('node'+this.obj+i)
	        current.className='normal'
	        node.className="selected"
	        this.currentNode=i
	    }
}

<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%@ page import="com.icss.oa.sync.vo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.sync.handler.UserSyncHandler"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="org.json.JSONException"%>
<%@ page import="org.json.JSONObject"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
List list=(List)request.getAttribute("userList");




String usercode=(String)request.getAttribute("usercode");
String name=(String)request.getAttribute("name");
String operatetype=(String)request.getAttribute("operatetype");
String audittype=(String)request.getAttribute("audittype");

if(usercode==null)usercode="";

if(name==null)name="";

if(operatetype==null)operatetype="";

if(audittype==null)audittype="";

%>
  
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>审批同步人员</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.anylinkcss{ 
position:absolute; 
visibility: hidden; 
z-index: 100; 
} 
-->


#ifr_Mask{
	opacity: 0;
	filter: alpha(opacity=0);
	border: none;
}

/*蒙板颜色及透明度*/
#div_Mask{
	background-color:#EDEDED;
	opacity:0.7;
	filter:alpha(opacity=30);
}

</style>
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.js"></script>


<script  language="javascript">


var MaskDialog = new maskDialog();


function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++){
     aa[i].checked = e.checked;
  }
  
  checkBoxSync();
}

function checkItem(e, allName)
{
  checkBoxSync();
  
  
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
	{
     if(!aa[i].checked) 
	  {
		all.checked = false;
		return;
	  }
    }
    all.checked = true;
  }
  
}


//当点击checkbox时，隐藏的那一个userInfos 要跟syncuser保持同步 userInfos中保存了同步信息的各项信息，用于验证优先级
function checkBoxSync()
{
	var aa = document.getElementsByName("syncuser");
	var bb = document.getElementsByName("userInfos");
	
	for(var i=0; i<aa.length; i++)
	{
	     if(aa[i].checked) 
		 {
			bb[i].checked = true;
		 }else{
		 	bb[i].checked = false;
		 }
    }
}




function edituser(userid)
{
	//alert(userid);
    //<input type=button onclick="window.open('连接')">
	window.open("/oabase/syncperson/edituser.jsp?operateid="+userid,"_blank","left=400,top=250,width=450,height=80,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
}

function submitcheck()
{
		  var aa = document.getElementsByName("syncuser");
		  var result=false;
			for (var i=0; i<aa.length; i++)
			{
                 if(aa[i].checked){
                 result=true;
				 }
			} 
			return result;

	}
	
function  doAction(url)   
{  
          frm.action=url;  
          frm.submit();   
 }
 
 function  doAction1(url)   
{  
	if(submitcheck()){
          frm.action=url;  
          frm.submit();   
          }else{
          alert('请选择审批的内容');
          }
 }


function resetcondition()
{
	document.getElementById("name").value = "";
	document.getElementById("usercode").value = "";
	document.getElementById("operatetype").value = "";
	document.getElementById("audittype").value = "0";
}










//显示蒙板效果
function maskDialog()
{
	// public properties start
	this.mainForm;
	this.top="100";
	// public properties end
	
	this.showStatus=false;
	if(typeof this.mainForm != "#ff0000" && this.mainForm != null)
	{
		this.mainForm.style.display="none";
	}
	
	if(window.onresize)
	{
		var evt=window.onresize;
		window.onresize=function(){evt(); MaskDialog.resize();};
	}else{
		window.onresize=function(){MaskDialog.resize();};
	}
	
	if(window.onscroll)
	{
		var evt=window.onscroll;
		window.onscroll=function(){evt(); MaskDialog.scroll();};
	}else{
		window.onscroll=function(){MaskDialog.scroll();};
	}
	
	//隐藏蒙板效果
	this.hide=function()
	{
		var mask=document.getElementById("div_Mask");
		if(typeof mask != "undefined" && mask!=null)
		{
			var body = document.getElementsByTagName("body")[0];
			body.removeChild(mask);
		}
		var ifr=document.getElementById("ifr_Mask");
		if(typeof ifr != "undefined" && ifr!=null)
		{
			var body = document.getElementsByTagName("body")[0];
			body.removeChild(ifr);
		}
		if(typeof this.mainForm != "undefined" && this.mainForm != null)
		{
			this.mainForm.style.display="none";
		}
		this.showStatus=false;
	};
	
	//显示蒙板效果
	this.show=function()
	{
		var body = document.getElementsByTagName("body")[0];
		var pageDimensions=this.getPageDimensions();
		var sheet = document.createElement("div");
		sheet.setAttribute("id","div_Mask");
		sheet.style.position="absolute";
		sheet.style.left="0px";
		sheet.style.top="0px";
		sheet.style.zIndex="999";
		sheet.style.width=pageDimensions[0] + "px";
		sheet.style.height=pageDimensions[1] + "px";
		var ifr = document.createElement("iframe");
		ifr.setAttribute("id","ifr_Mask");
		ifr.style.position="absolute";
		ifr.style.display="block";
		ifr.style.zIndex="998";
		ifr.width=pageDimensions[0];
		ifr.height=pageDimensions[1];
		ifr.scrolling="no";
		ifr.frameborder="0";
		ifr.style.left="0px";
		ifr.style.top="0px";
		body.appendChild(ifr);
		body.appendChild(sheet);
		if(typeof this.mainForm != "undefined" && this.mainForm != null)
		{
			this.mainForm.style.display="block";
			this.mainForm.style.zIndex="1000";
		}
		this.showStatus=true;
		this.scroll();
	};
	
	this.resize=function()
	{
		if(this.showStatus==false)
		{
			return;
		}
		var mask=document.getElementById("div_Mask");
		var ifr=document.getElementById("ifr_Mask");
		if(typeof mask != "undefined" && mask != null && typeof ifr != "undefined" && ifr != null )
		{
			var body = document.getElementsByTagName("body")[0];
			var pageDimensions=this.getPageDimensions();
			mask.style.width=pageDimensions[0] + "px";
			mask.style.height=pageDimensions[1] + "px";
			ifr.width=pageDimensions[0];
			ifr.height=pageDimensions[1];
			this.scroll();
		}
	};
	
	this.scroll=function()
	{
		if(this.showStatus==false)
		{
			return;
		}
		if(typeof this.mainForm != "undefined" && this.mainForm != null)
		{
			var pageDimensions=this.getPageDimensions();
			this.mainForm.style.position="absolute";
			this.mainForm.style.top=document.documentElement.scrollTop + "px";
			if(typeof this.mainForm.style.width == "undefined" || this.mainForm.style.width=="")
			{
				this.mainForm.style.width=pageDimensions[0] /2 + "px";
			}
			var newLeft= pageDimensions[0] / 2 - parseInt(this.mainForm.style.width,10) /2
			var newTop= document.documentElement.scrollTop + parseInt(this.top);
			this.mainForm.style.left= newLeft + "px";
			this.mainForm.style.top= newTop + "px";
		}
	};
	
	this.getPageDimensions=function()
	{
		var body = document.getElementsByTagName("body")[0];
		var bodyOffsetWidth = 0;
		var bodyOffsetHeight = 0;
		var bodyScrollWidth = 0;
		var bodyScrollHeight = 0;
		var pageDimensions = [0,0];
		pageDimensions[0]=document.documentElement.clientWidth;
		pageDimensions[1]=document.documentElement.clientHeight;
		bodyOffsetWidth=body.offsetWidth;
		bodyOffsetHeight=body.offsetHeight;
		bodyScrollWidth=body.scrollWidth;
		bodyScrollHeight=body.scrollHeight;
		if(bodyOffsetWidth > pageDimensions[0])
		{
			pageDimensions[0]=bodyOffsetWidth;
		}
		if(bodyOffsetHeight > pageDimensions[1])
		{
			pageDimensions[1]=bodyOffsetHeight;
		}
		if(bodyScrollWidth > pageDimensions[0])
		{
			pageDimensions[0]=bodyScrollWidth;
		}
		if(bodyScrollHeight > pageDimensions[1])
		{
			pageDimensions[1]=bodyScrollHeight;
		}
		return pageDimensions;
	};
	return true;
}










function auditPass()
{

	if(!submitcheck()){
		alert('请选择审批的内容');
    }

	$.ajax({
			type:"POST",
			data:$("#frm").serialize(),
			url:"<%=request.getContextPath()%>/servlet/AuditServlet?method=priorCheck",
			error:function(msg)
			{
				alert("操作失败，请重试！");
			},
		    success:function(data)
		    {
		    	//alert(data);
		    	
		    	rcdSet = eval("("+data+")");
		    	
		    	
		    	if(rcdSet.length>0){
			    	var aa = document.getElementsByName("syncuser");
			    	var bb = document.getElementsByName("userInfos");
			    	
			    	for(var i=0;i<rcdSet.length;i++){
			    		var rcd = rcdSet[i];
			    		var num = rcd.num;
			    		$("#userInfos"+num).attr("checked",false);
			    		$("#syncuser"+num).attr("checked",false);
			    	}
			    	
			    	var totalChecked = 0;
			    	for(var i=0;i<aa.length;i++){
			    		if(aa[i].checked){
			    			totalChecked++;
			    		}
			    	}
			    	
			    	var titleStr = "";
			    	if(totalChecked>0){
			    		titleStr = "您所选择的序号为如下编号的同步信息目前不能执行审批通过操作,您是否继续审核通过其它信息?";
			    		$("#btn1").show();
			    		$("#btn2").show();
			    		$("#btn3").hide();
			    	}else{
			    		titleStr = "您所选择的所有同步信息都不能执行审批通过操作";
			    		$("#btn1").hide();
			    		$("#btn2").hide();
			    		$("#btn3").show();
			    	}
			    	
			    	var html="";
			    	html+='<div style="font-size:15px;font-weight:bold;margin-left:5px;">'+titleStr+'</div>';
			    	
			    	for(var i=0;i<rcdSet.length;i++)
			    	{
			    		var rcd = rcdSet[i];
			    		html+='<div style="margin-left:25px;margin-top:3px;">';
			    		html+='<b>序号'+rcd.num+'</b>：'+rcd.msg;
			    		html+='</div>';
			    	}
			    	
			    	$("#hitDiv").html(html);
			    	
			    	MaskDialog.show();
		    	}else{
		    		//执行审批通过操作
		    		doAction1('/oabase/servlet/AuditServlet?method=audit');
		    	}
			}
	});
	
	
	//提交
	//doAction1('/oabase/servlet/AuditServlet?method=audit');
}






$(function(){
	MaskDialog.mainForm=document.getElementById("msg");
});




</script>

</head>

<body>
<jsp:include page= "/include/top.jsp" />







<form id="frm" name="frm" method="post" action="">



<div id="msg" style="background-color: white;display: none;margin-top:54px;width: 586px;height: 277px;font-family:Tahoma;border:5px solid #E9EEF2;">
		<div style="height:30px;text-align:left;font-size:20px;font-weight:bold;background-color:#F3F4F8;"> 
				系统提示
		</div> 
		<div id="hitDiv" style="text-align:left;height:202px;overflow-y:scroll;padding-top:10px;">
				<!-- 
				<div style="font-size:15px;font-weight:bold;margin-left:5px;">您所选择的序号为如下编号的同步信息目前不能执行审批通过操作,您是否继续审核通过其它信息：</div>
				<div style="margin-left:25px;margin-top:3px;">
					<b>序号1</b>：该人员存在多条同步信息，请按时间的先后顺序审批人员同步信息。
				</div>
				 -->
		</div>
		<div style="height:35px;background-color:#DDDDDD;text-align:center;vertical-align:middle;">
				<input type="button" id="btn1" value="是" style="margin-top:5px;width:80px;" onclick="doAction1('/oabase/servlet/AuditServlet?method=audit');" />
				<input type="button" id="btn2" value="否" style="margin-left:10px;margin-top:5px;width:80px;" onclick="MaskDialog.hide();" />
				<input type="button" id="btn3" value="关闭" style="margin-left:10px; margin-top:5px;width:80px;" onclick="MaskDialog.hide();" />
		</div>
</div>



<!--检索条件区-->
<table width="100%">
	<tr>
    	<td width="10"></td>
      <td width="30%" align="center" valign="top" id="orgtree" style="display:none">&nbsp;</td>
        <td width="90%">
        	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor">
            	<tr class="block_title">
                	<td colspan="4"><div style="margin-left:2px;">检索条件</div></td>
                </tr>
                <tr>
                  <td width="10%" bgcolor="#FFFFFF" class="table_title" align="right" >姓名</td>
                  <td width="40%" bgcolor="#FFFFFF">
	                  	<span class="content">
	                    	<input id="name" name="name" value="<%=name%>" type="text" class="" style="width:120px;margin-left:8px;" />（填写人员姓名，支持模糊查询）
	                  	</span>
                  </td>
                  <td width="10%" bgcolor="#FFFFFF" class="table_title" align="right">用户名</td>
                  <td width="40%" bgcolor="#FFFFFF"><label><input id="usercode" name="usercode" value="<%=usercode%>" type="text" id="usercode" style="width:120px;margin-left:8px;" /></label></td>
              </tr>
                <tr>
				    <td bgcolor="#FFFFFF" class="table_title" align="right">操作类型</td>
				    <td bgcolor="#FFFFFF">
				      <select id="operatetype" name="operatetype" style="width:126px;margin-left:8px;" >
                      <option value="">所有</option>
                      <option value="addUser" <%=operatetype.equals("addUser")?"selected":""%> >添加</option>
                      <option value="updUser" <%=operatetype.equals("updUser")?"selected":""%> >修改</option>
					  <option value="delUser" <%=operatetype.equals("delUser")?"selected":""%> >删除</option>
					  <!-- 
					  <option value="transfer">调转</option>
					   -->
                      </select>
                    </td>
                	<td bgcolor="#FFFFFF" class="table_title" align="right">审批结果</td>
                    <td bgcolor="#FFFFFF" class="message_title_bold">
						  <select id="audittype" name="audittype"  style="width:126px;margin-left:8px;">
		                      <option value="all" <%=audittype.equals("all")?"selected":""%> >所有</option>
		                      <option value="1" <%=audittype.equals("1")?"selected":""%> >通过</option>
		                      <option value="2" <%=audittype.equals("2")?"selected":""%> >未通过</option>
							  <option value="0" <%=(audittype.equals("0")||audittype.equals(""))?"selected":""%> >待审批</option>
	                      </select>
                    </td>
                </tr>
				<tr>
				    <td bgcolor="#FFFFFF" class="table_title" colspan="4" align="center">
				       <input type="button" name="search" value="查询" style="width:80px;" onclick="doAction('/oabase/servlet/GetSearchUserServlet')" />
				       <input type="button" name="search" value="重置" style="width:80px;" onclick="resetcondition();" />
			        </td>
				</tr>
            </table>
            <!--检索条件区-->
            
            
            
		
            <table border="0" cellpadding="0" cellspacing="0">
            	<tr><td height="5"></td></tr>
            </table>
            
            <!--审批用户列表-->
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
			 
              <tr>
			        <td width="5%" align="left" class="block_title" nowrap>
					 	<input type="checkbox" name="syncuserall" onclick="checkAll(this,'syncuser')" />全选
					</td>
					<td width="5%" align="center" class="block_title">序号</td>
                	<td width="6%" align="center" class="block_title">姓名</td>
                    <td width="24%" align="center" class="block_title">所属单位</td>
                    <td width="7%" align="center" class="block_title">用户名</td>
                    <td width="8%" align="center" class="block_title">手机</td>
                    <td width="10%" align="center" class="block_title">时间</td>
                    <td width="5%" align="center" class="block_title">类型</td>
                    <!-- 
				    <td width="6%" align="center" class="block_title">描述</td>
					 -->
					<td width="24%" align="center" class="block_title">更改字段</td>
					
			        <td width="5%" align="center" class="block_title">状态</td>

              </tr>
              
					        
              <% 
              int i=0;
              Connection conn = null;
              String JNDI="jdbc/ROEEE";
              conn = DBConnectionLocator.getInstance().getConnection(JNDI);
              UserSyncHandler handler=new UserSyncHandler(conn);
             try{
			 if(!list.isEmpty()&&list!=null){
				   //orgCnameList=(List)request.getAttribute("orgCnameList");
					
                   for(i=0;i<list.size();i++)
                   {
                    String operateType="";
					String orgCname="";
					String color="";
					String auditType="";
					String tempauditType="";
					
                    PersonSyncSearchVO person=(PersonSyncSearchVO)list.get(i);
                    String  tempType=person.getOperatetype();
                    
                    tempauditType=person.getApproved();
                    
                    String userid=person.getUsername();
					String alterfield=person.getUpdcontent();
					
					String origin="更改前";
					String purpose="更改后";
					 if(userid==null)
						    userid="无";
					 if(alterfield==null)
						 alterfield="-";
					 if(origin==null)
						 origin="-";
					 if(purpose==null)
						 purpose="-";

                     if(tempType.equals("addUser")){
                        operateType="添加";
                     }
                     else if(tempType.equals("updUser")){
                        operateType="修改";
                     }
                     else if(tempType.equals("delUser")){
                        operateType="删除";
                     }
                    
                    if(tempauditType.equals("1")){
                       auditType="通过";
                     }
                     else if(tempauditType.equals("2")){
                       auditType="未通过";
                	}else if(tempauditType.equals("0")){
                       auditType="<b>待审批</b>";
                    }
                     
              %>
              
              
                 <tr onmouseover="this.bgColor='#e2f2cd';" onmouseout="this.bgColor='#FFFFFF'"  bgcolor="#FFFFFF">
							<td  align="left" class="message_title">
							<%
								   String personname = "";
								   if(tempType.equals("addUser")||tempType.equals("delUser")){
								   	   personname=person.getTruename();
								   	   if(personname==null)personname="";
								   }else if(tempType.equals("updUser")){
								   	   //待审核状态
								   	   if(person.getApproved()!=null&&person.getApproved().equals("0")){
								   	   	   personname=person.getOldtruename();
								   	       if(personname==null)personname="";
								   	   }else{
								   	       personname=person.getOld_truename();
								   	       if(personname==null)personname="";
								   	   }
								   }
								   
								   
								   String oldFullOrgName="";
								   if(tempType.equals("addUser")||tempType.equals("delUser")){
										String orgcode = person.getGroupid();
										if(orgcode!=null&&!orgcode.equals("")){
											oldFullOrgName=handler.getFullOrgName(orgcode);
										 	if(oldFullOrgName==null){
										 		oldFullOrgName = "";
											}
									 	}
								   }else if(tempType.equals("updUser")){
										//待审核状态 显示当前R1中库中的值
										if(person.getApproved()!=null&&person.getApproved().equals("0")){
											String orgcode = person.getOldorgcode();
											if(orgcode!=null&&!orgcode.equals("")){
												oldFullOrgName=handler.getFullOrgName(orgcode);
											 	if(oldFullOrgName==null){
											 		oldFullOrgName = "";
												}
										 	}
										}else{//已经审核通过或不通过了，显示通过或不通过时R1中的值
											String orgcode = person.getOld_groupid();
											if(orgcode!=null&&!orgcode.equals("")){
												oldFullOrgName=handler.getFullOrgName(orgcode);
											 	if(oldFullOrgName==null){
											 		oldFullOrgName = "";
												}
										 	}
										}
								   }
								   
								   
								   
								   if(oldFullOrgName.equals("")&&person.getJsonstring()!=null&&!person.getJsonstring().equals("")){
								   		String jsonString = person.getJsonstring();
								        JSONObject jsonObj = new JSONObject(jsonString);
								        
								   	    JSONObject syncObj = new JSONObject();
										String command = jsonObj.getString("Command");
										syncObj = (JSONObject)jsonObj.get("User");
										
										if(syncObj.has("groupFullPath")){
									    	oldFullOrgName = ""+syncObj.getString("groupDisplayName");
									    }
								   }
								   
								   
								   
								   
								   
								   
								   String opratetime = StringUtil.escapeNull(person.getOpratetime());
								   
							%>
							
							
							
							
							<% 
							//只有待审核用户需要操作
							if(tempauditType.equals("0")){%>
								<input type="checkbox" id="syncuser<%=(i+1)%>" name="syncuser" value="<%=person.getId()%>" onclick="checkItem(this,'syncuserall')" />
								<input type="checkbox" id="userInfos<%=(i+1)%>" style="display:none;" name="userInfos" value="<%=(i+1)+"@"+person.getId()+"@"+person.getXinhuaid()+"@"+personname+"@"+oldFullOrgName+"@"+operateType+"@"+person.getGroupid()+"@"+opratetime%>" />
							<%}%>
							</td>
							<td align="center"><%=i+1%></td>
							<td align="center" class="message_title">
								<%=personname%>
							</td>
							<td align="left" class="message_title">
								<%=oldFullOrgName%>
							</td>
							<% 
							
							 //
							 //if((new Integer(0)).equals(person.getIsright()))
							 //{ 
							     // color="blue";
							// }
							   
                                
							 %>
						   	<td align="center" class="message_title">
								<font><%=userid%></font>
							</td>
						    <td align="center" class="message_title"><%=StringUtil.escapeNull(person.getMobilephone()) %></td>
						    <td align="center" class="message_title"><%=opratetime%></td>
						    <td align="center" class="message_title"><%=operateType %></td>
							<!-- <td  align="center" class="message_title"><%=""%></td> -->
                            <td  align="left" class="message_title">
                               <%
                               if(tempType.equals("updUser")){
                               		String ot = "";
                               		
                               		//如果是待审核，以正式库中的truename为 oldtruename
                               		if(person.getApproved()!=null&&person.getApproved().equals("0"))
                               		{
                               			ot=person.getOldtruename();
                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是person_sync表）中找审核时记录的old_truename
                               			ot=person.getOld_truename();
                               		}
                               		
	                                String tn=person.getTruename(); 
	                                
	                                
	                                if(ot==null)ot="无";
	                                if(tn==null)tn="无";
		                            if(!ot.equals(tn)){%>
	                               		更改字段:姓名&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=ot%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=tn%></font>
	                               		<br/>
	                               <%}
                               		
                               		String om = "";
                               		//如果是待审核，以正式库中的mobile为 oldmobile
                               		if(person.getApproved()!=null&&person.getApproved().equals("0"))
                               		{
                               			om=person.getOldmobile();
                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是person_sync表）中找审核时记录的old_mobilephone
                               			om= person.getOld_mobilephone();
                               		}
	                                String mb=person.getMobilephone();
	                                if(om==null)om="无";
	                                if(mb==null)mb="无";
	                               	if(!om.equals(mb)){%>
	                               		更改字段:手机&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=om%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=mb%></font>
	                               		<br/>
	                               <%}
	                               
	                               
	                               
	                               String oldOrgcode = "";
                               		//如果是待审核，以正式库中的mobile为 oldmobile
                               		if(person.getApproved()!=null&&person.getApproved().equals("0"))
                               		{
                               			oldOrgcode=person.getOldorgcode();
                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是person_sync表）中找审核时记录的old_mobilephone
                               			oldOrgcode=person.getOld_groupid();
                               		}
	                                String orgCode = person.getGroupid();
	                                
	                                if(oldOrgcode==null){
	                                	oldOrgcode="";
	                                }
	                                if(orgCode==null){
	                                	orgCode= "";
	                                }
	                                
	                                String newFullOrgName = "无";
	                                if(orgCode!=null&&!orgCode.equals("")){
	                                	newFullOrgName = handler.getFullOrgName(orgCode);
	                                }
	                                if(newFullOrgName==null){
	                                	newFullOrgName="";
	                                }
	                                
	                               	if(!oldOrgcode.equals(orgCode)){%>
	                               		更改字段:所属单位&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=oldFullOrgName%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=newFullOrgName%></font>
	                               		<br/>
	                               <%}
	                               
	                               
	                               
	                               String oldSex = "";
	                               //如果是待审核，以正式库中的mobile为 oldmobile
                               		if(person.getApproved()!=null&&person.getApproved().equals("0"))
                               		{
                               			oldSex=person.getOldsex();
                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是person_sync表）中找审核时记录的old_mobilephone
                               			oldSex=person.getOld_gender();
                               		}
                               		if(oldSex==null){
                               			oldSex="";
                               		}
                               		
                               		//R1:0-保密1-男 2-女
                               		if(oldSex.equals("0")){
                               			oldSex = "保密";
                               		}else if(oldSex.equals("1")){
                               			oldSex="男";
                               		}else if(oldSex.equals("2")){
                               			oldSex="女";
                               		}
                               		
	                                //ids:1-男，2-女，3-保密
	                                String sex=person.getGender();
	                                
	                                if(sex==null)sex="";
	                                
	                                if(sex.equals("3")){
                               			sex = "保密";
                               		}else if(sex.equals("1")){
                               			sex="男";
                               		}else if(sex.equals("2")){
                               			sex="女";
                               		}
	                                
	                                if(oldSex==null)oldSex="无";
	                                if(sex==null)sex="无";
	                               	if(!oldSex.equals(sex)){%>
	                               		更改字段:性别&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=oldSex%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=sex%></font>
	                               		<br/>
	                               <%}
	                               
	                           }
	                           
	                           
                               %>
							</td> 
                          	<td align="center" class="message_title"><%=auditType%></td>
					       </tr>
             
             
              <%
              } //end for
              }//end  if(!list.isEmpty()&&list==null)
              }catch(Exception e){
              	e.printStackTrace();
              }finally {
					if (conn != null)
					try {
						conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
              
              for(int j=i;j<15;j++){
              %>
              	 	<!-- 
              	 	<tr onmouseover="this.bgColor='#e2f2cd';" onmouseout="this.bgColor='#FFFFFF'"  bgcolor="#FFFFFF">
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
						<td  class="message_title">&nbsp;</td>
					</tr>
					 -->
              <%}%>
			 
				
			  
			  <tr>
                	<td colspan="10" height="20">
                	<%@ include file="../include/defaultPageScrollBar.jsp"%>
                	</td>
              </tr>
              
              
              <tr>
				  <td colspan="10" align='center'>
				  	<input type="button" value="审批通过" onclick="auditPass();"/> 
					 &nbsp;&nbsp;&nbsp;&nbsp;
				  	<input type="button" value="审批不通过" onclick="doAction1('<%=request.getContextPath()%>/servlet/AuditServlet?method=audit&type=no')"/> 
				  </td>
			  </tr>
         
          </table>
            <!--审批用户列表-->
            
      </td>
        <td width="10"></td>
    </tr>
</table>
<br/>
<br />

</form>
 
</body>

</html>

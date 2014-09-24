<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%@ page import="com.icss.oa.sync.vo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.oa.sync.handler.OrgSyncHandler"%>
<%@ page import="com.icss.common.log.ConnLog"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			List orglist = (List) request.getAttribute("orglist");
			
			String orgcode=(String)request.getAttribute("orgcode");
			String orgname=(String)request.getAttribute("orgname");
			String operatetype=(String)request.getAttribute("operatetype");
			String audittype=(String)request.getAttribute("audittype");
			
			if(orgcode==null)orgcode="";
			if(orgname==null)orgname="";
			if(operatetype==null)operatetype="";
			if(audittype==null)audittype="";
			
			Connection conn = null;
			try {
				conn = DBConnectionLocator.getInstance().getConnection("jdbc/ROEEE");
				OrgSyncHandler handler = new OrgSyncHandler(conn);
		%>

		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>审批同步组织</title>

		<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.js"></script>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.anylinkcss {
	position: absolute;
	visibility: hidden;
	z-index: 100;
}


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

//
-->
</style>

<script language="javascript">


var MaskDialog = new maskDialog();

//点击全选按钮后执行的事件
function checkAll(e, itemName)
{
	var aa = document.getElementsByName(itemName);
	for(var i=0; i<aa.length; i++)
	{
		aa[i].checked = e.checked;
	}
	//隐藏的checkbox要跟显示的同步
	checkBoxSync();
}



function checkItem(e, allName)
{
	//隐藏的checkbox要跟显示的同步
	checkBoxSync();
	 
	//得到全选按钮
	var all = document.getElementsByName(allName)[0];
	if(!e.checked)
	{
		all.checked = false;
	}else{
		var aa = document.getElementsByName(e.name);
		for(var i=0; i<aa.length; i++)
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



//当点击checkbox时，隐藏的那一个orgInfos 要跟syncorg保持同步 orgInfos中保存了同步信息的各项信息，用于验证优先级
function checkBoxSync()
{
	var aa = document.getElementsByName("syncorg");
	var bb = document.getElementsByName("orgInfos");
	
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



function submitcheck()
{
		  var aa = document.getElementsByName("syncorg");
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
	document.getElementById("orgcode").value = "";
	document.getElementById("orgname").value = "";
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





//审批通过之前
function prePass()
{

	if(!submitcheck()){
		 alert('请选择审批的内容');
    }
    
	$.ajax({
			type:"POST",
			data:$("#frm").serialize(),
			url:"<%=request.getContextPath()%>/servlet/OrgPassServlet?method=priorCheck",
			error:function(msg)
			{
				alert("操作失败，请重试！");
			},
		    success:function(data)
		    {
		    	//alert(data);
		    	
		    	rcdSet = eval("("+data+")");
		    	
		    	
		    	if(rcdSet.length>0){
			    	var aa = document.getElementsByName("syncorg");
			    	var bb = document.getElementsByName("orgInfos");
			    	
			    	 
			    	
			    	for(var i=0;i<rcdSet.length;i++){
			    		var rcd = rcdSet[i];
			    		var num = rcd.num;
			    		$("#syncorg"+num).attr("checked",false);
			    		$("#orgInfos"+num).attr("checked",false);
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
		    		doAction1('<%=request.getContextPath()%>/servlet/OrgPassServlet?method=audit');
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
		<jsp:include page="/include/top.jsp" />




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
				<input type="button" id="btn1" value="是" style="margin-top:5px;width:80px;" onclick="doAction1('<%=request.getContextPath()%>/servlet/OrgPassServlet?method=audit');" />
				<input type="button" id="btn2" value="否" style="margin-left:10px;margin-top:5px;width:80px;" onclick="MaskDialog.hide();" />
				<input type="button" id="btn3" value="关闭" style="margin-left:10px; margin-top:5px;width:80px;" onclick="MaskDialog.hide();" />
		</div>
</div>


			<table width="100%">
				<tr>
					<td width="5%"></td>
					<td width="90%">

						<!--检索条件区-->
						<table border="0" cellpadding="0" cellspacing="1" width="100%"
							class="table_bgcolor">
							<tr class="block_title">
								<td colspan="4">
									<div style="margin-left:2px;">检索条件</div>
								</td>
							</tr>
							<tr>
			                     <td width="10%" bgcolor="#FFFFFF" class="table_title" align="right" >组织名称</td>
			                     <td width="40%" bgcolor="#FFFFFF">
				                  	<span class="content">
				                    	<input id="orgname" name="orgname" value="<%=orgname%>" type="text" class="" style="width:120px;margin-left:8px;" />（填写组织名称，支持模糊查询）
				                  	</span>
			                     </td>
			                  <td width="10%" bgcolor="#FFFFFF" class="table_title" align="right">组织编码</td>
			                  <td width="40%" bgcolor="#FFFFFF"><label><input id="orgcode" name="orgcode" value="<%=orgcode%>" type="text" id="usercode" style="width:120px;margin-left:8px;" /></label></td>
			                </tr>
							<tr>
								<td bgcolor="#FFFFFF" class="table_title" align="right" width="10%">
									类型
								</td> 
								<td bgcolor="#FFFFFF" width="40%">
									<select name="operatetype" id="operatetype" style="width:126px;margin-left:8px;">
										<option value="">所有</option>
										<option value="addGroup" <%=operatetype.equals("addGroup")?"selected":""%> >新增</option>
										<option value="updGroup" <%=operatetype.equals("updGroup")?"selected":""%> >修改</option>
										<option value="delGroup" <%=operatetype.equals("delGroup")?"selected":""%> >删除</option>
									</select>
								</td>
								<td bgcolor="#FFFFFF" class="table_title" align="right" width="10%" >
									审批结果
								</td>
								<td bgcolor="#FFFFFF" class="message_title_bold" width="40%">
									<select name="audittype" id="audittype" style="width:126px;margin-left:8px;">
										<option value="all" <%=audittype.equals("all")?"selected":""%> >
											所有
										</option>
										<option value="1" <%=audittype.equals("1")?"selected":""%> >
											通过
										</option>
										<option value="2" <%=audittype.equals("2")?"selected":""%> >
											未通过
										</option>
										<option value="0" <%=(audittype.equals("0")||audittype.equals(""))?"selected":""%> >
											待审批
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" class="table_title" colspan="4" align="center">
									<input type="button" name="search" value="查询" style="width:80px;" onclick="doAction('/oabase/servlet/GetSyncOrgServlet')" />
									<input type="button" name="search" value="重置" style="width:80px;" onclick="resetcondition();" />
								</td>
							</tr>
						</table>
						<!--检索条件区-->

						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="5"></td>
							</tr>
						</table>

						<!--审批用户列表-->
						<table border="0" cellpadding="0" cellspacing="1"
							class="table_bgcolor" width="100%">

							<tr>
								<td width="5%" align="left" class="block_title">
									<input type="checkbox" name="syncorgall" onclick="checkAll(this,'syncorg')" />
									全选
								</td>
								<td width="6%" align="center" class="block_title">
									序号
								</td>
								<td width="10%" align="center" class="block_title">
									组织机构
								</td>
								<td width="10%" align="center" class="block_title">
									组织CODE
								</td>
								<td width="13%" align="center" class="block_title">
									上层组织
								</td>

								<td width="13%" align="center" class="block_title">
									时间
								</td>

								<td width="5%" align="center" class="block_title">
									操作类型
								</td>
								<td width="30%" align="center" class="block_title">
									更改字段
								</td>
								<td width="8%" align="center" class="block_title">
									状态
								</td>
							</tr>
							<%
								Iterator it = orglist.iterator();
									int i = 0;
									while (it.hasNext()) {
										OrgSyncSearchVO vo = (OrgSyncSearchVO) it.next();
										i++;
										String color = "#eff5e7";
										if (i % 2 == 1)
											color = "#FFFFFF";
										String parentorgname = handler.getOrgName(vo.getParentgroupcode());
										
										
										 String tempType=StringUtil.escapeNull(vo.getOperatetype());
                   						 String tempauditType=StringUtil.escapeNull(vo.getApproved());
                   						 String orgCode = StringUtil.escapeNull(vo.getGroupcode());
                   						 String orgName = StringUtil.escapeNull(vo.getGroupname());
                   						 String optTime = StringUtil.escapeNull(vo.getOpratetime());
                   						 
                   						 
                   						 String operateType="";
                   						 String auditType="";
										if(tempType.equals("addGroup")){
					                        operateType="添加";
					                     }
					                     else if(tempType.equals("updGroup")){
					                        operateType="修改";
					                     }
					                     else if(tempType.equals("delGroup")){
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
							<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
								onMouseOver="this.bgColor='#e2f2cd';">
								<td align="center" class="message_title">
									<%
										if (StringUtil.escapeNull(vo.getApproved()).equals("0")) {
									%>
									<input type="checkbox" id="syncorg<%=i%>" name="syncorg" value=<%=vo.getId()%> onclick="checkItem(this,'syncorgall')" />
									
									<input type="checkbox" style="display:none;" id="orgInfos<%=i%>" name="orgInfos" value="<%=i%>@<%=vo.getId()%>@<%=orgCode%>@<%=orgName%>@<%=tempType%>@<%=optTime%>" />
									
									<%
										}
									%>
								</td>
								<td align="center" class="message_title">
									<%=i%>
								</td>
								<td align="center" class="message_title">
									<b><%=vo.getGroupname()%></b>
								</td>
								<td align="center" class="message_title"><%=vo.getGroupcode()%>
								</td>
								<td align="center" class="message_title"><%=parentorgname%>
								</td>

								<td align="center" class="message_title"><%=StringUtil.escapeNull(vo.getOpratetime())%>
								</td>

								<td align="center" class="message_title"><%=operateType %></td>
								<td  align="left" class="message_title">
								
									<%if(tempType.equals("updGroup")){
		                               		
		                               		String old_orgcode = "";
		                               		//如果是待审核，以正式库中的orgcode为 old_orgcode
		                               		if(vo.getApproved()!=null&&vo.getApproved().equals("0"))
		                               		{
		                               			old_orgcode=vo.getOldgroupcode();
		                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是person_sync表）中找审核时记录的old_mobilephone
		                               			old_orgcode= vo.getOld_groupcode();
		                               		}
			                                String new_orgcode = vo.getGroupcode();
			                                
			                                if(old_orgcode==null)old_orgcode="无";
			                                if(new_orgcode==null)new_orgcode="无";
			                               	if(!old_orgcode.equals(new_orgcode)){%>
			                               		更改字段:组织编码&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=old_orgcode%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=new_orgcode%></font>
			                               		<br/>
			                               <%}
		                               		
		                               		
		                               		
		                               		String old_orgname = "";
		                               		//如果是待审核，以正式库中的组织名为 oldgroupname
		                               		if(vo.getApproved()!=null&&vo.getApproved().equals("0"))
		                               		{
		                               			old_orgname=vo.getOldgroupname();
		                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是org_sync表）中找审核时记录的old_groupname
		                               			old_orgname=vo.getOld_groupname();
		                               		}
		                               		
			                                String new_orgname = vo.getGroupname(); 
			                                
			                                
			                                if(old_orgname==null)old_orgname="无";
			                                if(new_orgname==null)new_orgname="无";
				                            if(!old_orgname.equals(new_orgname)){%>
			                               		更改字段:组织名称&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=old_orgname%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=new_orgname%></font>
			                               		<br/>
			                              <%}
			                               	 
			                               	 
			                               	String old_contact = "";
		                               		//如果是待审核，以正式库中的contact为 old_contact
		                               		if(vo.getApproved()!=null&&vo.getApproved().equals("0"))
		                               		{
		                               			old_contact=vo.getOldcontact();
		                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是org_sync表）中找审核时记录的old_contact
		                               			old_contact=vo.getOld_contact();
		                               		}
		                               		
			                                String new_contact = vo.getContact(); 
			                                
			                                
			                                if(old_contact==null)old_contact="无";
			                                if(new_contact==null)new_contact="无";
			                               
			                                if(!old_contact.equals(new_contact)){
			                                %>
			                                	更改字段:联系方式&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=old_contact%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=new_contact%></font>
			                               		<br/>
			                               <%}
			                               
			                               
			                               
			                               
			                                String old_serialindex = "";
		                               		//如果是待审核，以正式库中的组织名为 oldserialindex
		                               		if(vo.getApproved()!=null&&vo.getApproved().equals("0"))
		                               		{
		                               			old_serialindex=vo.getOldserialindex();
		                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是org_sync表）中找审核时记录的old_serialindex
		                               			old_serialindex=vo.getOld_serialindex();
		                               		}
		                               		
			                                String new_serialindex = vo.getSerialindex(); 
			                                
			                                
			                                if(old_serialindex==null)old_serialindex="无";
			                                if(new_serialindex==null)new_serialindex="无";
			                               
			                                if(!old_serialindex.equals(new_serialindex)){
			                                %>
			                                	更改字段:排序号&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=old_serialindex%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=new_serialindex%></font>
			                               		<br/>
			                               <%}
			                               
			                               
			                               
			                               
			                               String old_memo = "";
		                               		//如果是待审核，以正式库中的组织名为 oldmemo
		                               		if(vo.getApproved()!=null&&vo.getApproved().equals("0"))
		                               		{
		                               			old_memo=vo.getOldmemo();
		                               		}else{//如果是审核已通过或未通过，则从审核记录中（也就是org_sync表）中找审核时记录的old_memo
		                               			old_memo=vo.getOld_memo();
		                               		}
		                               		
			                                String new_memo = vo.getMemo();
			                                
			                                
			                                if(old_memo==null)old_memo="无";
			                                if(new_memo==null)new_memo="无";
			                                
			                                
			                                if(!old_memo.equals(new_memo)){
			                                %>
			                                	更改字段:备注&nbsp;原值:<font color="#94939c" style="border-bottom:1px solid black;"><%=old_memo%></font>&nbsp;新值:<font color="black" style="border-bottom:1px solid black;"><%=new_memo%></font>
			                               		<br/>
			                               <%}
			                           
			                           }%>
								</td>
								<td align="center" class="message_title"><%=auditType%></td>

							</tr>


							<%
								}//end
							%>

							<tr>
								<td colspan="9" height="20"><%@ include file="../include/defaultPageScrollBar.jsp"%></td>
							</tr>

							<tr>
								<td colspan='9' align='center'>
									<input type="button" value="审批通过" onclick="prePass();" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value="审批不通过" onclick="doAction1('<%=request.getContextPath()%>/servlet/OrgPassServlet?method=audit&type=no');" />
								</td>
							</tr>

						</table>
						<!--审批用户列表-->

					</td>
					<td width="5%"></td>
				</tr>
			</table>
			<br />
			<br />

		</form>

	</body>

</html>

<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
%>

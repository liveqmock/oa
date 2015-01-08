<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%@ page import="com.icss.oa.sync.vo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.sync.handler.UserSyncHandler"%>
<%@ page import="com.icss.oa.sync.handler.IdsSyncHandler"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="org.json.JSONException"%>
<%@ page import="org.json.JSONObject"%>

<%@page import="java.util.*"%>
<%@page import="com.icss.core.db.RecordSet"%>
<%@page import="com.icss.core.db.Record"%>
<%@ page import="com.icss.core.db.PagingInfo"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String path = request.getContextPath();

//��Աͬ����Ϣ�б�
RecordSet syncPersonList = (RecordSet)request.getAttribute("syncPersonList");
if(null==syncPersonList)
{
	syncPersonList = new RecordSet();
}

//��ҳ����
PagingInfo pagingInfo = (PagingInfo)request.getAttribute("pagingInfo");

int pageno = pagingInfo.getCurrentPageNo();

//��ѯ����Record
Record seaRecord = (Record)request.getAttribute("seaRecord");


%>
  
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>����ͬ����Ա</title>
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

/*�ɰ���ɫ��͸����*/
#div_Mask{
	background-color:#EDEDED;
	opacity:0.7;
	filter:alpha(opacity=50);
}

</style>
<link href="<%=path%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />



<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.js"></script>





<script  language="javascript">


var MaskDialog = new maskDialog();




//��ʾ�ɰ�Ч��
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
	
	//�����ɰ�Ч��
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
	
	//��ʾ�ɰ�Ч��
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


//���ȫѡ��ťִ�е��¼�
function checkAll(e, itemName)
{
	var aa = document.getElementsByName(itemName);
	for(var i=0; i<aa.length; i++){
	   aa[i].checked = e.checked;
	}
	
	//���ص�checkboxҪ����ʾ��ͬ��
	checkBoxSync();
}

//���ҳ����ʾ��ĳ��checkbox��ťִ�е��¼�
function checkItem(e, allName)
{
	checkBoxSync();
	var all = document.getElementsByName(allName)[0];
	if(!e.checked){
		all.checked = false;
	}else{
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


//�����checkboxʱ�����ص���һ��userInfos Ҫ��syncuser����ͬ�� userInfos�б�����ͬ����Ϣ�ĸ�����Ϣ��������֤���ȼ�
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
    //<input type=button onclick="window.open('����')">
	window.open("<%=path%>/syncpersonRecord/edituser.jsp?operateid="+userid,"_blank","left=400,top=250,width=450,height=80,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
}

function submitcheck()
{
	var aa = document.getElementsByName("syncuser");
	var result=false;
	for(var i=0; i<aa.length; i++)
	{
           if(aa[i].checked){
              result=true;
		}
	} 
	return result;
}
	



function resetcondition()
{
	$("#SEA_NAME").val("");
	$("#SEA_USERCODE").val("");
	$("#SEA_OPERATETYPE").val("");
	$("#SEA_AUDITTYPE").val("0");
}


//���鲻ͨ����,�û����"��ѯ��Ա�Ķ���ͬ����Ϣ"�����Ĳ�ѯ����
function goSearchFromCheck()
{
	$("#SEA_PERSON_SYNC_IDS").val(waitSearchIds);
	
	goSearch();
}



function goSearch()
{
	//�����ַ�У��
	
	
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~��@#������&*������|{}��������������'��������]") 
	
	if(pattern.test($("#SEA_NAME").val()))
	{
		alert("�������������ַ�!");
		$("#SEA_NAME").focus();
		return;
	}
	
	if(pattern.test($("#SEA_USERCODE").val()))
	{
		alert("�������������ַ�!");
		$("#SEA_USERCODE").focus();
		return;
	}
	
	
	
	$("#form1").attr("action","<%=path%>/servlet/IdsSyncServlet?method=syncPersonList");
	$("#form1").submit();
}



//��������Ա�ж���ͬ����Ϣ,������û�Ҫ��ѯ��Щ�û���ȫ��ͬ����Ϣ,���Ҫ���ж���ͬ����Ϣ����Աid��������
var waitSearchIds = "";


function auditPass()
{
	if(!submitcheck()){
		alert('��ѡ������������');
		return;
    }
	
	MaskDialog.mainForm=document.getElementById("procDiv");
	$("#procHit").html("���ڽ�����Ϣ�������ȼ�У��...");
	MaskDialog.show();
	
	
	$.ajax({
			type:"POST",
			data:$("#form1").serialize(),
			url:"<%=request.getContextPath()%>/servlet/IdsSyncServlet?method=personSyncPriorCheck",
			error:function(msg)
			{
				alert("����ʧ�ܣ������ԣ�");
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
			    		$("#syncuserall").attr("checked",false);
			    	}
			    	
			    	
			    	
			    	
			    	var totalChecked = 0;
			    	for(var i=0;i<aa.length;i++){
			    		if(aa[i].checked){
			    			totalChecked++;
			    		}
			    	}
			    	
			    	var titleStr = "";
			    	if(totalChecked>0){
			    		titleStr = "����ѡ������Ϊ���±�ŵ�ͬ����ϢĿǰ����ִ������ͨ������,���Ƿ�������ͨ��������Ϣ?";
			    		$("#btn1").show();
			    		$("#btn2").show();
			    		$("#btn3").hide();
			    	}else{
			    		titleStr = "����ѡ�������ͬ����Ϣ������ִ������ͨ������";
			    		$("#btn1").hide();
			    		$("#btn2").hide();
			    		$("#btn3").show();
			    	}
			    	
			    	
			    	
			    	
			    	
			    	var html="";
			    	html+='<div style="font-size:15px;font-weight:bold;margin-left:5px;">'+titleStr+'</div>';
			    	
			    	var searchBtnShowFlag = 0;
			    	
			    	for(var i=0;i<rcdSet.length;i++)
			    	{
			    		var rcd = rcdSet[i];
			    		html+='<div style="margin-left:25px;margin-top:3px;">';
			    		html+='<b>���'+rcd.num+'</b>��'+rcd.msg;
			    		html+='</div>';
			    		
			    		if(rcd.msg=="����Ա���ڶ���ͬ����Ϣ���밴ʱ����Ⱥ�˳��������Աͬ����Ϣ��"){
			    			searchBtnShowFlag = 1;
			    		}
			    		
			    		waitSearchIds +=(rcd.id+",");
			    	}
			    	
			    	
			    	if(searchBtnShowFlag==1){
			    		$("#btn4").show();
			    	}else{
			    		$("#btn4").hide();
			    	}
			    	
			    	
			    	
			    	
			    	//����procDiv
			    	$("#procHit").html("");
		    		MaskDialog.hide();
			    	
			    	$("#hitDiv").html(html);
			    	MaskDialog.mainForm=document.getElementById("msg");
			    	MaskDialog.show();
		    	}else{
		    		
		    		$("#procHit").html("��Ϣ�������ȼ�У��ͨ����");
		    		
		    		//ִ������ͨ������
		    		goAudit('yes');
		    	}
			}
	});
	
}





function auditPass2()
{
	if(!submitcheck()){
		alert('��ѡ������������');
		return;
    }
    
	//ִ������ͨ������
    goAudit('yes');
}








//�����ύ����̨������ͨ����ͨ��
function goAudit(type)   
{  
	if(!submitcheck()){
		alert('��ѡ������������');
		return;
	}
	
	MaskDialog.mainForm=document.getElementById("procDiv");
	$("#procHit").html($("#procHit").html()+"�����ύ���...");
	MaskDialog.show();
	
	$.ajax({
			type:"POST",
			data:$("#form1").serialize(),
			url:"<%=path%>/servlet/IdsSyncServlet?method=personSyncAudit&type="+type,
			error:function(msg)
			{
				alert("����ʧ�ܣ������ԣ�");
			},
		    success:function(data)
		    {
		    	//������ʾ
		    	$("#procHit").html("");
		    	MaskDialog.hide();
		    	
		    	alert(data);
		    	_goPage(<%=pageno%>);
			}
	});
    //$("#form1").attr("action",url);  
    //$("#form1").submit();   
}




$(function(){
	  //MaskDialog.mainForm=document.getElementById("msg");
});




</script>

</head>

<body>


<jsp:include page= "/include/top.jsp" />




<div id="msg" style="background-color: white;display:none;margin-top:54px;width: 586px;height: 277px;font-family:Tahoma;border:5px solid #E9EEF2;">
		<div style="height:30px;text-align:left;font-size:20px;font-weight:bold;background-color:#F3F4F8;"> 
				ϵͳ��ʾ
		</div> 
		<div id="hitDiv" style="text-align:left;height:202px;overflow-y:scroll;padding-top:10px;">
				<!-- 
				<div style="font-size:15px;font-weight:bold;margin-left:5px;">����ѡ������Ϊ���±�ŵ�ͬ����ϢĿǰ����ִ������ͨ������,���Ƿ�������ͨ��������Ϣ��</div>
				<div style="margin-left:25px;margin-top:3px;">
					<b>���1</b>������Ա���ڶ���ͬ����Ϣ���밴ʱ����Ⱥ�˳��������Աͬ����Ϣ��
				</div>
				 -->
		</div>
		<div style="height:35px;background-color:#DDDDDD;text-align:center;vertical-align:middle;">
				<input type="button" id="btn1" value="��" style="margin-top:5px;width:80px;" onclick="MaskDialog.hide();goAudit('yes');" />
				<input type="button" id="btn2" value="��" style="margin-left:10px;margin-top:5px;width:80px;" onclick="MaskDialog.hide();" />
				<input type="button" id="btn4" value="��ѯ��Ա�Ķ���ͬ����Ϣ" style="margin-left:10px; margin-top:5px;" onclick="MaskDialog.hide();goSearchFromCheck();" />
				<input type="button" id="btn3" value="�ر�" style="margin-left:10px; margin-top:5px;width:80px;" onclick="MaskDialog.hide();" />
		</div>
</div>


<div id="procDiv" style="display:none;margin-top:100px;width:300px;text-align:center;">
	<img src="<%=path%>/images/wait.gif" /><span id="procHit" style="font-weight:bold;"></span>
</div>



<form id="form1" name="form1" method="post" action="">
<table width="983" align="center" cellpadding="0" cellspacing="0" border="0">
	<tr><td></td></tr>
	<tr>
    	
        <td width="90%">
        	
        		<!--����������-->
	        	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor">
	            	<tr class="block_title">
	                	<td colspan="4"><div style="margin-left:2px;">��������</div></td>
	                </tr>
	                <tr>
	                  <td width="10%" bgcolor="#FFFFFF" class="table_title" align="right" >����</td>
	                  <td width="40%" bgcolor="#FFFFFF">
		                  	<span class="content">
		                  		<input id="SEA_PERSON_SYNC_IDS" name="SEA_PERSON_SYNC_IDS"  type="hidden" />
		                    	<input id="SEA_NAME" name="SEA_NAME"  value="<%=seaRecord.getString("NAME","")%>" type="text" class="" style="width:120px;margin-left:8px;" />����д��Ա������֧��ģ����ѯ��
		                  	</span>
	                  </td>
	                  <td width="10%" bgcolor="#FFFFFF" class="table_title" align="right">�û���</td>
	                  <td width="40%" bgcolor="#FFFFFF"><label><input id="SEA_USERCODE" name="SEA_USERCODE"   value="<%=seaRecord.getString("USERCODE","")%>" type="text" style="width:120px;margin-left:8px;" /></label></td>
	              </tr>
	                <tr>
					    <td bgcolor="#FFFFFF" class="table_title" align="right">��������</td>
					    <td bgcolor="#FFFFFF">
					      <select id="SEA_OPERATETYPE" name="SEA_OPERATETYPE" style="width:126px;margin-left:8px;" >
	                      <option value="">����</option>
	                      <option value="addUser" <%=seaRecord.getString("OPERATETYPE","").equals("addUser")?"selected":""%> >���</option>
	                      <option value="updUser" <%=seaRecord.getString("OPERATETYPE","").equals("updUser")?"selected":""%> >�޸�</option>
						  <option value="delUser" <%=seaRecord.getString("OPERATETYPE","").equals("delUser")?"selected":""%> >ɾ��</option>
						  <!-- 
						  <option value="transfer">��ת</option>
						   -->
	                      </select>
	                    </td>
	                	<td bgcolor="#FFFFFF" class="table_title" align="right">�������</td>
	                    <td bgcolor="#FFFFFF" class="message_title_bold">
							  <select id="SEA_AUDITTYPE" name="SEA_AUDITTYPE"  style="width:126px;margin-left:8px;">
			                      <option value="all" <%=seaRecord.getString("AUDITTYPE","").equals("all")?"selected":""%> >����</option>
			                      <option value="1" <%=seaRecord.getString("AUDITTYPE","").equals("1")?"selected":""%> >ͨ��</option>
			                      <option value="2" <%=seaRecord.getString("AUDITTYPE","").equals("2")?"selected":""%> >δͨ��</option>
								  <option value="0" <%=(seaRecord.getString("AUDITTYPE","").equals("0")||seaRecord.getString("AUDITTYPE","").equals(""))?"selected":""%> >������</option>
		                      </select>
	                    </td>
	                </tr>
					<tr>
					    <td bgcolor="#FFFFFF" class="table_title" colspan="4" align="center">
					       <input type="button" name="search" value="��ѯ" style="width:80px;" onclick="goSearch();" />
					       <input type="button" name="search" value="����" style="width:80px;" onclick="resetcondition();" />
				        </td>
					</tr>
	            </table>
            <!--����������-->
            
            
            <div style="height:5px;"></div>
            
            
            <!--�����û��б�-->
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
			 
              <tr>
			        <td width="5%" align="left" class="block_title" nowrap>
					 	<input type="checkbox" id="syncuserall" name="syncuserall" onclick="checkAll(this,'syncuser')" />ȫѡ
					</td>
					<td width="5%" align="center" class="block_title">���</td>
                	<td width="6%" align="center" class="block_title">����</td>
                    <td width="24%" align="center" class="block_title">������λ</td>
                    <td width="7%" align="center" class="block_title">�û���</td>
                    <td width="8%" align="center" class="block_title">�ֻ�</td>
                    <td width="10%" align="center" class="block_title">ʱ��</td>
                    <td width="5%" align="center" class="block_title">����</td>
                    <!-- 
				    <td width="6%" align="center" class="block_title">����</td>
					 -->
					<td width="24%" align="center" class="block_title">�����ֶ�</td>
			        <td width="5%" align="center" class="block_title">״̬</td>
              </tr>
              
					        
          <% 
             int i=0;
             Connection conn = null;
             String JNDI="jdbc/ROEEE";
             conn = DBConnectionLocator.getInstance().getConnection(JNDI);
             
             try{
             	 IdsSyncHandler handler=new IdsSyncHandler();
			     //orgCnameList=(List)request.getAttribute("orgCnameList");
                 for(i=0;i<syncPersonList.size();i++)
                 {
                    String operateType="";
					String orgCname="";
					String color="";
					String auditType="";
					String tempauditType="";
					
                    Record personRecord = syncPersonList.get(i);
                    String tempType = personRecord.getString("OPERATETYPE","");
                    tempauditType = personRecord.getString("APPROVED","");
                    String userid = personRecord.getString("USERNAME","");
					
					
                     if(tempType.equals("addUser")){
                        operateType="���";
                     }else if(tempType.equals("updUser")){
                        operateType="�޸�";
                     }else if(tempType.equals("delUser")){
                        operateType="ɾ��";
                     }
                    
                    if(tempauditType.equals("1")){
                       auditType="ͨ��";
                    }else if(tempauditType.equals("2")){
                       auditType="δͨ��";
                	}else if(tempauditType.equals("0")){
                       auditType="<b>������</b>";
                    }
              %>
              
              
                 <tr onmouseover="this.bgColor='#e2f2cd';" onmouseout="this.bgColor='#FFFFFF'"  bgcolor="#FFFFFF">
							<td  align="center" class="message_title">
							<%
								   String personname = "";
								   if(tempType.equals("addUser")||tempType.equals("delUser")){
								   	   personname = personRecord.getString("TRUENAME","��");
								   }else if(tempType.equals("updUser")){
								   	   //�����״̬
								   	   if(personRecord.getString("APPROVED","")!=null&&personRecord.getString("APPROVED","").equals("0")){
								   	   	   //personname=personRecord.getString("CNNAME","��");
								   	   	   personname = personRecord.getString("TRUENAME","��");
								   	   }else{
								   	       personname = personRecord.getString("OLD_TRUENAME","��");
								   	   }
								   }
								   
								   
								   String oldFullOrgName="��";
								   if(tempType.equals("addUser")||tempType.equals("delUser")){
										String orgcode = personRecord.getString("GROUPID","");
										
										//�����ɾ����Ա����,ids��������orgcodeΪ"EveryOne"
										if(orgcode!=null&&!orgcode.equals("")&&!orgcode.equals("EveryOne")){
											oldFullOrgName=handler.getFullOrgName(orgcode);
											if(oldFullOrgName.equals("")){
												oldFullOrgName="��";
											}
									 	}else{
									 		String t_orgcode = handler.getOrgCodeByUserid(userid);
									 		if(!t_orgcode.equals("")){
									 			oldFullOrgName = handler.getFullOrgName(t_orgcode);
									 		}
									 		
									 		if(oldFullOrgName.equals("")){
												oldFullOrgName="��";
											}
									 	}
								   }else if(tempType.equals("updUser")){
										//�����״̬ ��ʾ��ǰR1�п��е�ֵ
										if(personRecord.getString("APPROVED","")!=null&&personRecord.getString("APPROVED","").equals("0")){
											String orgcode = personRecord.getString("ORGCODE","");
											if(orgcode!=null&&!orgcode.equals("")){
												oldFullOrgName=handler.getFullOrgName(orgcode);
											 	if(oldFullOrgName==null){
											 		oldFullOrgName = "";
												}
												if(oldFullOrgName.equals("")){
													oldFullOrgName="��";
												}
										 	}
										}else{//�Ѿ����ͨ����ͨ���ˣ���ʾͨ����ͨ��ʱR1�е�ֵ,���Ǽ�¼��person_sync���е�ֵ
											String orgcode = personRecord.getString("OLD_GROUPID","");
											if(orgcode!=null&&!orgcode.equals("")){
												oldFullOrgName=handler.getFullOrgName(orgcode);
											 	if(oldFullOrgName==null){
											 		oldFullOrgName = "";
												}
												if(oldFullOrgName.equals("")){
													oldFullOrgName="��";
												}
										 	}
										}
								   }
								   
								   
								   //��¼ͬ��Json�д���������֯����
								   String displayOrgName = "";
								   if(!personRecord.getString("JSONSTRING","").equals("")){
								   		String jsonString = personRecord.getString("JSONSTRING","");
								        JSONObject jsonObj = new JSONObject(jsonString);
								        
								   	    JSONObject syncObj = new JSONObject();
										String command = jsonObj.getString("Command");
										syncObj = (JSONObject)jsonObj.get("User");
										
										if(syncObj.has("groupDisplayName")){
									    	displayOrgName = ""+syncObj.getString("groupDisplayName");
									    }
								   }
								   
								   
								   //��¼ͬ��Json�д���������Ա�Ƿ�ͣ����Ϣ
								   String actived = "";
								   if(!personRecord.getString("JSONSTRING","").equals("")){
								   		String jsonString = personRecord.getString("JSONSTRING","");
								        JSONObject jsonObj = new JSONObject(jsonString);
								        
								   	    JSONObject syncObj = new JSONObject();
										String command = jsonObj.getString("Command");
										syncObj = (JSONObject)jsonObj.get("User");
										
										if(syncObj.has("actived")){
									    	actived = ""+syncObj.getString("actived");
									    }
								   }
								   
								   if(displayOrgName.equals("����֯")){
								   	   //displayOrgName = "�»�ͨѶ��";
								   }
								   
								   String opratetime = StringUtil.escapeNull(personRecord.getString("OPRATETIME",""));
							 
							 
							//ֻ�д�����û���Ҫ����
							if(tempauditType.equals("0")){%>
								<input type="checkbox" id="syncuser<%=(i+1)%>" name="syncuser" value="<%=personRecord.getString("ID","")%>" onclick="checkItem(this,'syncuserall')" />
								<input type="checkbox" id="userInfos<%=(i+1)%>" style="display:none;" name="userInfos" value="<%=(i+1)+"@"+personRecord.getString("ID","")+"@"+personRecord.getString("XINHUAID","")+"@"+personname+"@"+displayOrgName+"@"+operateType+"@"+personRecord.getString("GROUPID","")+"@"+opratetime%>" />
							<%}%>
							</td>
							<td align="center"><%=i+1%></td>
							<td align="center" class="message_title">
								<%=personname%>
							</td>
							<td align="left" class="message_title">
								<%=oldFullOrgName.equals("")||oldFullOrgName.equals("��")?displayOrgName:oldFullOrgName%>
							</td>
						   	<td align="center" class="message_title">
								<font><%=userid%></font>
							</td>
						    <td align="center" class="message_title"><%=personRecord.getString("MOBILEPHONE","")%></td>
						    <td align="center" class="message_title"><%=opratetime%></td>
						    <td align="center" class="message_title"><%=operateType%></td>
                            <td align="left" class="message_title">
                            
                            
                            <%
                               //ֻ�����޸���Ա��Ϣʱ��Ҫ��ʾ���������
                               if(tempType.equals("updUser")){
                               		String ot = "";
                               		
                               		//����Ǵ���ˣ�����ʽ���е�truenameΪ oldtruename
                               		if(personRecord.getString("APPROVED","")!=null&&personRecord.getString("APPROVED","").equals("0"))
                               		{
                               			ot=personRecord.getString("CNNAME","��");
                               		}else{//����������ͨ����δͨ���������˼�¼�У�Ҳ����personRecord_sync���������ʱ��¼��old_truename
                               			ot=personRecord.getString("OLD_TRUENAME","��");
                               		}
                               		
	                                String tn=personRecord.getString("TRUENAME","��"); 
	                                
	                                
		                            if(!ot.equals(tn)){%>
	                               		�����ֶ�:����&nbsp;OA��ֵ:<font color="#94939c" style="border-bottom:1px solid black;"><%=ot%></font>&nbsp;��ֵ:<font color="black" style="border-bottom:1px solid black;"><%=tn%></font>
	                               		<br/>
	                               <%}
                               		
                               		String om = "";
                               		//����Ǵ���ˣ�����ʽ���е�mobileΪ oldmobile
                               		if(personRecord.getString("APPROVED","")!=null&&personRecord.getString("APPROVED","").equals("0"))
                               		{
                               			om=personRecord.getString("MOBILE","��");
                               		}else{//����������ͨ����δͨ���������˼�¼�У�Ҳ����personRecord_sync���������ʱ��¼��old_mobilephone
                               			om= personRecord.getString("OLD_MOBILEPHONE","��");
                               		}
	                                String mb=personRecord.getString("MOBILEPHONE","��");
	                               	if(!om.equals(mb)){%>
	                               		�����ֶ�:�ֻ�&nbsp;OA��ֵ:<font color="#94939c" style="border-bottom:1px solid black;"><%=om%></font>&nbsp;��ֵ:<font color="black" style="border-bottom:1px solid black;"><%=mb%></font>
	                               		<br/>
	                               <%}
	                               
	                               
	                               
	                                String oldOrgcode = "";
                               		//����Ǵ���ˣ�����ʽ���е�ORGCODEΪ oldOrgcode
                               		if(personRecord.getString("APPROVED","")!=null&&personRecord.getString("APPROVED","").equals("0"))
                               		{
                               			oldOrgcode=personRecord.getString("ORGCODE","");
                               		}else{//����������ͨ����δͨ���������˼�¼�У�Ҳ����personRecord_sync���������ʱ��¼��OLD_GROUPID
                               			oldOrgcode=personRecord.getString("OLD_GROUPID","");
                               		}
	                                String orgCode = personRecord.getString("GROUPID","");
	                                
	                                String newFullOrgName = "";
	                                
	                                if(orgCode != null && !orgCode.equals("")){
	                                	String[] orgArr = orgCode.split(";");
	                                	for (int j = 0; j < orgArr.length; j++)
	                                	{
	                                		String paths = handler.getFullOrgName(orgArr[j]);
	                                		if (paths != null && !"".equals(paths))
	                                		{
	                                			newFullOrgName += paths + ";";
	                                		}
	                                	}
		                                
		                                //���R1��û���ҵ�
		                                if(newFullOrgName.equals("")){
		                                	newFullOrgName="(��֯����:"+orgCode+" ��֯����:"+displayOrgName+" ע:OA���޸���֯)";
		                                }
	                                }
	                                
	                                
	                                
	                                
	                               	if(!personRecord.getString("JSONSTRING","").equals("")&&personRecord.getString("JSONSTRING","").indexOf("moveToGroup")!=-1&&!oldOrgcode.equals(orgCode)){%>
	                               		�����ֶ�:������λ&nbsp;OA��ֵ:<font color="#94939c" style="border-bottom:1px solid black;"><%=oldFullOrgName%></font>&nbsp;��ֵ:<font color="black" style="border-bottom:1px solid black;"><%=newFullOrgName%></font>
	                               		<br/>
	                               <%}
	                               
	                               
	                               
	                               String oldSex = "";
	                               //����Ǵ���ˣ�����ʽ���е�mobileΪ oldmobile
                               		if(personRecord.getString("APPROVED","")!=null&&personRecord.getString("APPROVED","").equals("0"))
                               		{
                               			oldSex=personRecord.getString("SEX","��");
                               		}else{//����������ͨ����δͨ���������˼�¼�У�Ҳ����personRecord_sync���������ʱ��¼��old_mobilephone
                               			oldSex=personRecord.getString("OLD_GENDER","��");
                               		}
                               		
                               		//R1:0-����1-�� 2-Ů
                               		if(oldSex.equals("0")){
                               			oldSex = "����";
                               		}else if(oldSex.equals("1")){
                               			oldSex="��";
                               		}else if(oldSex.equals("2")){
                               			oldSex="Ů";
                               		}else if(oldSex.equals("3")){
                               			oldSex="����";
                               		}
                               		
	                                //ids:1-�У�2-Ů��3-����
	                                String sex=personRecord.getString("GENDER","��");
	                                
	                                
	                                if(sex.equals("3")){
                               			sex = "����";
                               		}else if(sex.equals("1")){
                               			sex="��";
                               		}else if(sex.equals("2")){
                               			sex="Ů";
                               		}
	                                
	                               	if(!oldSex.equals(sex)){%>
	                               		�����ֶ�:�Ա�&nbsp;OA��ֵ:<font color="#94939c" style="border-bottom:1px solid black;"><%=oldSex%></font>&nbsp;��ֵ:<font color="black" style="border-bottom:1px solid black;"><%=sex%></font>
	                               		<br/>
	                               <%}
	                               
	                               
	                               if(actived.equals("FALSE")){%>
	                               		IDS�˺�״̬��<font color="red">ͣ��</font>
	                               		<br/>
	                               <%}else{
	                               		%>
	                               		IDS�˺�״̬������
	                               		<br/>
	                               <%}
	                               
	                               
	                           }
	                           
	                           
                               %>
							</td> 
                          	<td align="center" class="message_title"><%=auditType%></td>
					       </tr>
             
             
              <%
              	}//end for
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
          </table>
            <!--�����û��б�-->
            
      </td>
      
    </tr>
</table>
</form>



<table width="983" align="center" cellpadding="0" cellspacing="0" border="0">
	<tr>
    	
    	<td width="90%" class="table_bgcolor" align="right" style="font-size:12px;">
    		<jsp:include flush="true" page="/include/paging.jsp"></jsp:include>
    	</td>
    	
    </tr>
    <tr>
    	
    	<td width="90%" class="table_bgcolor" align="center">
    		<input type="button" value="����ͨ��(���ȼ�У��)" onclick="auditPass();"/> 
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="����ͨ��" onclick="auditPass2();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
		  	<input type="button" value="������ͨ��" onclick="goAudit('no')"/> 
    	</td>

    </tr>
</table>



 
</body>

</html>

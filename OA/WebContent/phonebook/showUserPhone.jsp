<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=GBK" language="java"  errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.orgtree.handler.*"%>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler"%>

<%@ page import="com.icss.oa.phonebook.vo.*"%>
<%@ page import="com.icss.resourceone.common.logininfo.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#movediv{
    width:600px;height:190px;position:absolute;border:1px solid #000;background:#EAEAEA;
    cursor:pointer;
    text-align:center;
    line-height:100px;
    left:100px;
    top:10px;
   }
.anylinkcss{ 
position:absolute; 
visibility: hidden; 
z-index: 100; 
} 
-->
</style>
<link href="../Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />

<script language="javascript">
 var _IsMousedown = 0;
   var _ClickLeft = 0;
   var _ClickTop = 0;
   function moveInit(divID,evt)
   {
    _IsMousedown = 1;
    if(getBrowserType() == "NSupport")
    {
     return;
    }
    var obj = getObjById(divID);
    if(getBrowserType() == "fox")
    {
     _ClickLeft = evt.pageX - parseInt(obj.style.left);
     _ClickTop = evt.pageY - parseInt(obj.style.top);
    }else{
     _ClickLeft = evt.x - parseInt(obj.style.left);
     _ClickTop = evt.y - parseInt(obj.style.top);
    }
   }
    function changeDiv(obj,url)
   {
		document.getElementById(obj).style.display="";
		document.getElementById(obj).innerHTML = "<iframe src='"+url+"'   width=100%   height=100% marginheight='0px' marginwidth='0px' frameborder='0'></iframe><input type=\"button\" onclick=\"this.parentElement.style.display='none'\" value=\"�ر�\" />";
   }	
   function Move(divID,evt)
   {
    if(_IsMousedown == 0)
    {
     return;
    }
    var objDiv = getObjById(divID);
    if(getBrowserType() == "fox")
    {
     objDiv.style.left = evt.pageX - _ClickLeft;
     objDiv.style.top = evt.pageY - _ClickTop;
    }
    else{
     objDiv.style.left = evt.x - _ClickLeft;
     objDiv.style.top = evt.y - _ClickTop;
    }
    
   }
   function stopMove()
   {
    _IsMousedown = 0;
   }
   function getObjById(id)
   {
    return document.getElementById(id);
   }
   function getBrowserType()
   {
    var browser=navigator.appName
    var b_version=navigator.appVersion
    var version=parseFloat(b_version)
    //alert(browser);
    if ((browser=="Netscape"))
    {
     return "fox";
    }
    else if(browser=="Microsoft Internet Explorer")
    {
     if(version>=4)
     {
      return "ie4+";
     }
     else
     {
      return "ie4-";
     }
    }
    else
    {
     return "NSupport";
    }
   }
function changeStyle(id){//�л���ʽ
	document.getElementById("homepagestyle").href = "/oabase/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//�л���ʽʱ����COOKIE
	var cookies = document.cookie;
	var setcookies = "";
	var lastcookies = "";
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	
	var deltime = new Date();
	daltime = exp.setTime (exp.getTime() - 1);    

	while(cookies.indexOf(";")>=0){
		var tempcookie = cookies.substring(0,cookies.indexOf(";"));
		cookies = cookies.substring(cookies.indexOf(";")+1);
		
		if(tempcookie.indexOf(name)>=0){
			//ɾ��ԭ����COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//�����µ���ʽ
	document.cookie = name+"="+value+";expires="+exp.toGMTString()+";path=/;domain=10.102.1.40";
}

function getCookie(name){
	var cook =document.cookie;
	//alert(cook);
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(";"));
		var value = "grey";
		if(cook1.indexOf(";")>0){ 
			value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";"));
		}else{
			value = cook1.substring(cook1.indexOf("=")+1);
		}
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "/oabase/Style/css_"+style+".css";
}

initstyle();

</script>
</head>

<body>
<%
   /*
    *�ж��Ƿ�Ϊdev,���ǣ������hrid
    *
    */
    String usertag=com.icss.j2ee.util.Globals.J2EE_USER_NAME;
	UserInfo user=(UserInfo)session.getAttribute(usertag);
%>

<form name="frm" method="post" action="/oabase/servlet/MySearchPhoneServlet">

<!--����������-->
<table width="100%">
	<tr>
    	<td width="10"></td>
      
  <td width="99%">
        	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor">
            	<tr class="block_title">
                	<td colspan="12">�绰����������</td>
                </tr>
                <tr>
                	<td width="10%" align="right" bgcolor="#FFFFFF" class="table_title">������</td>
                  <%
                     if("dev".equals(user.getUserID()))
                     {
                   %>
                  <td width="15%" bgcolor="#FFFFFF"><span class="content">
                    <input name="username" type="text" class="biankuang" size="10"/>
                   </span></td>
                  <td width="7%" align="right" bgcolor="#FFFFFF" class="table_title">Hrid��</td>
                  <td width="15%" bgcolor="#FFFFFF"><input name="hrid" type="text" id="hrid" size="10" /></td>
                  <% 
                     }
                     else
                     {
                  %>
                   <td width="37%" bgcolor="#FFFFFF"><span class="content">
                    <input name="username" type="text" class="biankuang" size="10"/>
                  ����д��Ա������֧��ģ����ѯ��</span></td>
                  <%}
                   %>
                    <td width="10%" align="right" bgcolor="#FFFFFF" class="table_title">���ţ�</td>
                  <td width="13%" bgcolor="#FFFFFF"><label>
                    <input name="orgname" type="text" id="orgname" size="12" />
                  </label></td>
                  <td width="7%" align="right" bgcolor="#FFFFFF" class="table_title">���ң�</td>
                  <td width="23%" bgcolor="#FFFFFF"><input name="deptname" type="text" id="deptname" size="12" /></td>
              </tr>
                <tr>
				    <td align="right" bgcolor="#FFFFFF" class="table_title">�绰���ͣ�</td>
				    <%
                     if("dev".equals(user.getUserID()))
                     {
                   %>
				    <td colspan="3" bgcolor="#FFFFFF">
				      <% 
                     }
                     else
                     {
                  %>
                  <td bgcolor="#FFFFFF">
                  <%
                     }
                  %>
				      <select name="functionname" id="functionname">
                      <option value="0">����</option>
                      <option value="1">���˹����绰</option>
                      <option value="2">ֵ��绰</option>
                      </select>                    </td>
				
                	<td align="right" bgcolor="#FFFFFF" class="table_title">�绰���룺</td>
                    <td colspan="3" bgcolor="#FFFFFF" class="message_title_bold"><label>
                      <input name="phonenum" type="text" id="phonenum" value="" size="12" /><span class="content">(֧��ģ����ѯ���������������͵绰)</span>
                    </label></td>
                </tr>
				<tr>
				    <td colspan="10" bgcolor="#FFFFFF" class="table_title" align="center">
			        <input type="submit" name="Submit" value="��ѯ" />&nbsp;
                    <input type="reset" name="reset" value="���ò�ѯ����" />
                    </td>
			    </tr>
            </table>
<!--����������-->
            <table border="0" cellpadding="0" cellspacing="0">
            	<tr><td height="5"></td></tr>
            </table>
            
            <!--�绰��չʾ��-->
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
              <tr>
                	<td width="15%" align="center" class="block_title">����</td>
                    <td width="25%" align="center" class="block_title">������λ</td>
                    <td width="10%" align="center" class="block_title">�칫�绰</td>
                    <td width="10%" align="center" class="block_title">����</td>
                    <td width="10%" align="center" class="block_title">�ֻ�</td>
                    <td width="10%" align="center" class="block_title">VPN</td>
                    <td width="10%" align="center" class="block_title">�����绰</td>
                    <td width="10%" align="center" class="block_title">��ͥ�绰</td>
              </tr>
      
               <%
                      List phoneList =(ArrayList)request.getAttribute("phoneList");
                      List searchList=(ArrayList)request.getAttribute("searchList");
                      List searchHrList=(ArrayList)request.getAttribute("searchHrList");
               		String searchpriv = (String)request.getAttribute("searchpriv");
					String specialpriv = (String)request.getAttribute("specialpriv");
                      if(searchHrList!=null)
                      {
				        Iterator it = searchHrList.iterator();
				    	while(it.hasNext())
				        {
				           String username="",orgname="",officephone="",faxphone="",mobilephone="",netphone="",homephone="",jobcode="",vpn="";
				           
				           PhoneInfoHrPersonSearchVO vo=(PhoneInfoHrPersonSearchVO)it.next();
						   String functionflag = vo.getFunction();
				           username=vo.getUsername();
						   jobcode=vo.getHrjobcode();
				           if(vo.getOrgname()!=null&&vo.getDeptname()!=null)
				             orgname=vo.getOrgname()+"->"+vo.getDeptname();
							if("2".equals(functionflag)){
							//���õ绰������ά��
							   officephone=vo.getOfficephone();
							   faxphone=vo.getFaxphone();
							   mobilephone=vo.getMobilephone();
							   netphone=vo.getNetphone();
							   vpn=vo.getHomephone();
							   
						   	}else{
							//��Ա�绰��������ϵͳ�л�ȡ
				               officephone=vo.getHrofficephone();
				               faxphone=vo.getHrfaxphone();
				               mobilephone=vo.getHrmobilephone();
				               netphone=vo.getHrnetphone();
				               homephone=vo.getHrhomephone();
							   vpn=vo.getHrvpn();
							}
				           
				           if(username==null||username.equals(""))
				             username=vo.getHrusername();
				           if(orgname==null||orgname.equals(""))
				           {
				               if(vo.getHrorgname()!=null&&vo.getHrdeptname()!=null)
				                  orgname=vo.getHrorgname()+"->"+vo.getHrdeptname();
				           }
				           
				           if(username==null||username.equals(""))
				               username="--";
				           if(orgname==null||orgname.equals(""))
				               orgname="--";
				           if(officephone==null||officephone.equals(""))
				               officephone="--";
						//���ε����쵼�ĵ绰��20080612 zhanggk
						   if("���쵼".equals(vo.getHrdeptname()))
						   		officephone="--";
				           if(faxphone==null||faxphone.equals(""))
				               faxphone="--";
				           if(mobilephone==null||mobilephone.equals(""))
				               mobilephone="--";
				           if(netphone==null||netphone.equals(""))
				               netphone="--";
				           if(homephone==null||homephone.equals(""))
				               homephone="--";
						   if(vpn==null||vpn.equals(""))
				               vpn="--";
						   
						   String orgcode = vo.getHrorgcode()==null?"":vo.getHrorgcode();
						   //System.out.println(username + "orgcode is "+orgcode);
						   if("0".equals(specialpriv)){
						   //���Բ鿴�����˵绰
						   //System.out.println("specialpriv is 0");
						   }else{
						   		if(!"".equals(orgcode) && specialpriv.indexOf(orgcode)>=0){
								//ӵ�иò鿴�ò���������Ա��Ϣ��Ȩ��
									//System.out.println("specialpriv is not 0");
								}else{
									//System.out.println("not in specialpriv");
								   if("".equals(searchpriv) && jobcode!=null){
									homephone = "--";
									mobilephone = "--";
								   }else{
								   
									   if(searchpriv.indexOf(","+jobcode)>=0 || jobcode==null){
										//��Ȩ�鿴
									   }else{
										homephone = "--";
										mobilephone = "--";
									   }
								   }
								}
						   }
						   
						   //���Բ鿴���쵼���ֻ����� add 2009-12-29
						   if(vo.getHrdeptcode()!=null && (vo.getHrdeptcode().indexOf("ZA")!=-1 || vo.getHrdeptcode().indexOf("ZB")!=-1)){
						   		mobilephone ="--";
						   }else{
						   		mobilephone=(vo.getHrmobilephone()==null||"".equals(vo.getHrmobilephone()))?"--":vo.getHrmobilephone();
						   }
						   
				          %>
				           <tr>
				             <td width="15%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <!--  <span onclick="changeDiv('movediv','<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUseruuid()%>')" style="cursor:hand;">
							    -->
								<span>
								<%=username%>
                                </span></td>
                             <td width="25%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%=orgname%>                             </td>
                             <td width="10%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%=officephone%>                             </td>
                             <td width="10%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%=faxphone%>                             </td>
                             <td width="10%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%=mobilephone%>                             </td>
                             <td width="10%" align="center" bgcolor="#FFFFFF" class="message_title"><%=vpn%></td>
                             <td width="10%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%=netphone%>                             </td>
                             <td width="10%" align="center" bgcolor="#FFFFFF" class="message_title">
                                --<%//=homephone%>                             </td>
					       </tr>
				       <%}
                      }
                      else if(phoneList!=null)
                      {
                          Iterator it = phoneList.iterator();
                          String orgname="";
                          if(request.getAttribute("orgName")!=null)
                             orgname=(String)request.getAttribute("orgName");
                          String temstr="";
                       
				          while(it.hasNext())
				          {
				             PhoneInfoSysPersonVO vo=(PhoneInfoSysPersonVO)it.next();
				          %>
				           <tr>
				             <td width="15%" align="center" bgcolor="#FFFFFF" class="message_title">
                              <!--   <span onclick="changeDiv('movediv','<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUseruuid()%>')" style="cursor:hand;">
							    -->
								<span>
							  <%=vo.getUsername()%></span>        
							
							  
							  </td>
                             <td width="25%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%=orgname%>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%if((temstr=vo.getOfficephone())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%if((temstr=vo.getFaxphone())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%if((temstr=vo.getMobilephone())!=null)
                                   //out.println(temstr);
								   out.println("--");
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%if((temstr=vo.getNetphone())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%if((temstr=vo.getHomephone())!=null)
                                   //out.println(temstr);
								   out.println("--");
                                 else 
                                   out.println("--");
                               %>                             </td>
					       </tr>
				        <%}
				    }
				    
				        if(searchList!=null){
				        Iterator it = searchList.iterator();
				    	while(it.hasNext())
				        {
				           PhoneVO vo=(PhoneVO)it.next();
				           String orgname ="";
				           if(vo.getOrgname()!=null&&!"".equals(vo.getOrgname()))
				             orgname=vo.getOrgname();
				           
				          if(vo.getDeptname()!=null&&!"".equals(vo.getDeptname()))
				             orgname=orgname+"->"+vo.getDeptname();

				    	   String temstr="";
				          %>
				           <tr>
				             <td width="15%" align="center" bgcolor="#FFFFFF" class="message_title">
                            <!-- <span onclick="changeDiv('movediv','<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUseruuid()%>')" style="cursor:hand;">
							 --> 
							 <span>
                                <%if((temstr=vo.getUsername())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("");
                               %>
                               </span>
                               </td>
                             <td width="25%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%=orgname%>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%if((temstr=vo.getOfficephone())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                               <%if((temstr=vo.getFaxphone())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%if((temstr=vo.getMobilephone())!=null)
                                   //out.println(temstr);
								   out.println("--");
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%if((temstr=vo.getNetphone())!=null)
                                   out.println(temstr);
                                 else 
                                   out.println("--");
                               %>                             </td>
                             <td width="12%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%if((temstr=vo.getHomephone())!=null)
                                   //out.println(temstr);
								   out.println("--");
                                 else 
                                   out.println("--");
                               %>                             </td>
					       </tr>
		          <%}
				   }
				   %>
			   <tr>
                	<td colspan="8" height="20"><%@ include file= "/include/defaultPageScrollBar.jsp" %></td>
              </tr>
          </table>
		   <div id="movediv"  style="left:150px;top:20px;display:none" onmousedown="moveInit('movediv',event);" onmousemove="Move('movediv',event)" onmouseup="stopMove()" onmouseout="stopMove()"><ifream></div>
        <!--�绰��չʾ��-->
      </td>
        <td width="10"></td>
    </tr>
</table>
<br>
<br>
<!--����������-->
</form>

<%
   String scrusername="",scrorgname="",scrfunctiontype="",scrphonenum="",scrdeptname="",hrid="";
   if(request.getAttribute("username")!=null)
      scrusername=(String)request.getAttribute("username");
      
   if(request.getAttribute("orgname")!=null)
      scrorgname=(String)request.getAttribute("orgname");
      
   if(request.getAttribute("deptname")!=null)
      scrdeptname=(String)request.getAttribute("deptname");
      
   if(request.getAttribute("functionname")!=null)
      scrfunctiontype=(String)request.getAttribute("functionname");
      
   if(request.getAttribute("phonenum")!=null)
      scrphonenum=(String)request.getAttribute("phonenum");
   if(request.getAttribute("hrid")!=null)
      hrid=(String)request.getAttribute("hrid");

%>
   <script>
      document.all.username.value='<%=scrusername%>'
      document.all.orgname.value='<%=scrorgname%>'
      document.all.deptname.value='<%=scrdeptname%>'
      document.all.phonenum.value='<%=scrphonenum%>'
      <%
         if("dev".equals(user.getUserID()))
         {
       %>
      document.all.hrid.value='<%=hrid%>'
      <%
         }
      %>
   </script>
 <%if(!"".equals(scrfunctiontype))
   {%>
     <script>
        document.all.functionname.value='<%=scrfunctiontype%>'
     </script>
 <%}%>
</body>
</html>
<script language="javascript">
function _showtree(){
	document.getElementById("orgtree").style.display="block";
}
function _hidetree(){
	document.getElementById("orgtree").style.display="none";
}
</script>








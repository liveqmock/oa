<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.orgtree.handler.*"%>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler"%>

<%@ page import="com.icss.oa.phonebook.vo.SysOrgVO"%>
<%@ page import="com.icss.oa.phonebook.vo.*"%>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler.*"%>


<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>��ӵ绰��¼</title>
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
</style>
<link href="../Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />


<script  language="javascript">
function changeStyle(id){//�л���ʽ
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
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
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+style+".css";
}

initstyle();
</script>

<script language="javascript">


function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}
function checkItem(e, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
     if(!aa[i].checked) return;
    all.checked = true;
  }
}

function edit(noteid,username,temfullname,orguuid,officeaddress,officephone,homephone,mobilephone,netphone,faxphone)
{
  document.all.noteids.value=noteid;
  document.all.username.value=username;
  document.all.officeaddress.value=officeaddress;
  document.all.officephone.value=officephone;
  document.all.homephone.value=homephone;
  document.all.mobilephone.value=mobilephone;
  document.all.netphone.value=netphone;
  document.all.faxphone.value=faxphone;
  document.all.orguuid.value=orguuid;
}


function addcheck()
{
  if(document.all.username.value=="")
    alert('�������û���');
  else if(document.all.orguuid.value=="-1")
    alert('��ѡ���û����ڵ�λ');
  else
  {
    frm.action="/oabase/servlet/MyAddPhoneServlet"
    frm.submit()
  }
}


function updatecheck()
{
  
  if(document.all.noteids.value=="")
    alert('�뵥������Ҫ�޸ĵĵ绰��¼��');
  else if(document.all.username.value=="")
    alert('�뵥������Ҫ�޸ĵĵ绰��¼��');
  else if(document.all.orguuid.value=="-1")
    alert('�뵥������Ҫ�޸ĵĵ绰��¼��');
  else
  { 
    frm.action="/oabase/servlet/MyUpdatePhoneServlet"
    frm.submit()
  }
}


function submi(str) 
{
  //alert("sdasd");
  if(str=="search")
  {
      frm.action="/oabase/servlet/ManageSearchPhoneServlet"
      frm.submit()
  }
  else if(str=="del")
  {
      //����ɾ��һ��
      if(document.all.mm.value>0)
      {
         var anote=document.all.mm.value
         frm.action="/oabase/servlet/MyDelPhoneServlet?anoteid="+anote
         if(confirm("��ȷ��Ҫɾ����ѡ��ĵ绰��¼��?"))
            frm.submit()
      }
      else
      {
         frm.action="/oabase/servlet/MyDelPhoneServlet"
         var aa = document.all.mm;
         var flag=false;
         for (var i=0;i<aa.length;i++)
         {
             if(aa[i].checked==true)
             {flag=true;}
         }
         if(flag==true)
         {
           if(confirm("��ȷ��Ҫɾ����ѡ��ļ�¼��?"))
             frm.submit()
         }
         else
            alert("�뵥����ิѡ��ѡ����Ҫɾ���ĵ绰��¼��");
      }
  }
}
</script>
</head>
<%
//var temstr="/oabase/servlet/ManageSearchPhoneServlet?type="+str;
//if(str=="search")
//{
 // frm.action="/oabase/servlet/ManageSearchPhoneServlet"
  //frm.submit()
//}
%>
<body>

<form name="frm" method="post" id="frm" action="/oabase/servlet/MyAddPhoneServlet">

<!--ͷ-->
<jsp:include page= "/include/top.jsp" />
<!--ͷ-->

<%
   String addtipstr="";
   String orguuid="";
   
   if(request.getParameter("addtipstr")!=null)
      addtipstr=(String)request.getParameter("addtipstr");
    
    if(request.getParameter("orguuid")!=null)
      orguuid=request.getParameter("orguuid");
%>





<table height="5" width="100%">
	<tr><td height="5"><img src="../images/kongbai.jpg" height="5"/></td></tr>
</table>

<!--    -->
<table border="0" cellpadding="0" cellspacing="1" width="99%" class="table_bgcolor" align="center">
  <tr class="block_title">
  
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td width="9%" align="center" height="26" bgcolor="#FFFFFF" class="table_title">   
     &#22995;&nbsp;&nbsp; &#21517;</td>
    <td width="23%" bgcolor="#FFFFFF" class="table_title">
      <input type="text" name="username" id="username" /></td>
    <td width="9%" align="center" bgcolor="#FFFFFF" class="table_title">&#21333; &nbsp;&nbsp; &#20301;<br /></td>
    <td width="25%" bgcolor="#FFFFFF" class="table_title">
       <select name="orguuid" id="orguuid" class="table_title">
       <option value="-1" selected>��ѡ���û����ڴ���</option>
       <% 
               List chuorgList=(ArrayList)request.getAttribute("chuorgList");
               request.setAttribute("chuorgList",chuorgList);
               Iterator it=chuorgList.iterator();
               int i=0;
				while(it.hasNext())
				{
				  SysOrgVO chuOrg=(SysOrgVO)it.next();
				  if(orguuid.equals(chuOrg.getOrguuid()))
				  {%>
				     <option value=<%=chuOrg.getOrguuid()%> selected><%=chuOrg.getCnname()%></option>
				<%}
				  else{
	 %>
				     <option value=<%=chuOrg.getOrguuid()%>><%=chuOrg.getCnname()%></option>
			  <%  }
			    }
%>
      </select>
      </td>
    <td width="9%" bgcolor="#FFFFFF" align="center" class="table_title">�칫��ַ</td>
    <td width="25%" bgcolor="#FFFFFF" class="table_title">
    <input type="text" name="officeaddress" id="officeaddress"/>
   
    </td>
  </tr>
  
  <tr>
    <td height="19" align="center" bgcolor="#FFFFFF" class="table_title">�칫�绰 <br /></td>
    <td height="19" bgcolor="#FFFFFF" class="table_title">
      <input type="text" name="officephone" id="officephone"/></td>
    <td bgcolor="#FFFFFF" align="center" class="table_title">VPN �绰</td>
    <td bgcolor="#FFFFFF" class="table_title">
      <input type="text" name="homephone" id="homephone"/></td>
    <td bgcolor="#FFFFFF" align="center" class="table_title">&#25163; &nbsp;&nbsp; &#26426;</td>
    <td bgcolor="#FFFFFF" class="table_title">
      <input type="text" name="mobilephone" id="mobilephone"/>
    </td>
  </tr>
  
  <tr>
    <td height="19" align="center" bgcolor="#FFFFFF" class="table_title">&#23485; &nbsp;&nbsp; &#24102; <br /></td>
    <td height="19" bgcolor="#FFFFFF" class="table_title"><input type="text" name="netphone" id="netphone"/></td>
    <td bgcolor="#FFFFFF" align="center" class="table_title">&#20256; &nbsp;&nbsp; &#30495;</td>
    <td bgcolor="#FFFFFF" class="table_title"><input type="text" name="faxphone" id="faxphone"/></td>
    <td bgcolor="#FFFFFF" class="table_title"><br /></td>
    <td bgcolor="#FFFFFF" class="table_title"> 
    <input type="hidden" name="noteids"  id="noteids"/>
    </td>
  </tr>
  
</table>

<table border="0"  cellpadding="0" cellspacing="0" class="table_bgcolor" width="99%" align="center">
    <tr>
     <td align="center" bgcolor="#FFFFFF" class="table_title">
       <input type="button"  name="submit1" onclick="addcheck()" value="���" />&nbsp;&nbsp;
	  <input type="button" name="Submit2" onclick="updatecheck()" value="�޸�" />&nbsp;&nbsp;
	  <input type="button" onclick="submi('del')" name="Submit3" value="ɾ��" />&nbsp;&nbsp;
	   <input type="reset" name="Submit" value="����" />&nbsp;&nbsp;
	  <input type="button" onclick="submi('search')" name="Submit4" value="��ѯ" />
	   </td>
    </tr>
</table>


 <!--�绰��չʾ��-->
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="99%" align="center">
              <tr>
                    <td width="5%" height="25" align="center" class="block_title">
                    <input type=checkbox name=mmAll onclick="checkAll(this, 'mm')">ȫѡ
                    
                </td>
                	<td width="13%" align="center" class="block_title">����</td>
                    <td width="20%" align="center" class="block_title">������λ</td>
                    <td width="20%" align="center" class="block_title">�칫��ַ</td>
                    <td width="9%" align="center" class="block_title">�칫�绰</td>
                    <td width="9%" align="center" class="block_title">����</td>
                    <td width="8%" align="center" class="block_title">�ֻ�</td>
                    <td width="8%" align="center" class="block_title">����绰</td>
                    <td width="8%" align="center" class="block_title">VPN�绰</td>
              </tr>
		          <%
		              List phoneList =(ArrayList)request.getAttribute("phoneList");
		              List fullorglist =(ArrayList)request.getAttribute("fullorglist");
		             
		           
                      PhoneHandler phonehandler=new PhoneHandler();
                      String orgName=(String)request.getAttribute("orgName");
                      try{
                      //out.println(phoneList.size());
                      if(phoneList!=null)
                      {
                          
                          Iterator it2 = phoneList.iterator();
                          Iterator fullnameit=fullorglist.iterator();
                          String temstr="";
				          while(it2.hasNext())
				          {
				             PhoneInfoVO vo=(PhoneInfoVO)it2.next();
				             String fullname=(String)fullnameit.next();
				             String noteid="",username="",orguuid2="",officeaddress="",officephone="",homephone="",mobilephone="",netphone="",faxphone="";
				             if(vo.getNoteid()!=null)
				               noteid=vo.getNoteid().toString();
				             if(vo.getUsername()!=null)
				               username=vo.getUsername();
				             if(vo.getOrguuid()!=null)
				               orguuid2=vo.getOrguuid();
				             if(vo.getOfficeaddress()!=null)
				               officeaddress=vo.getOfficeaddress();
				             if(vo.getOfficephone()!=null)
				               officephone=temstr=vo.getOfficephone();
				             if((temstr=vo.getFaxphone())!=null)
				               faxphone=vo.getFaxphone();
				             if((temstr=vo.getMobilephone())!=null)
				               mobilephone=vo.getMobilephone();
				             if((temstr=vo.getNetphone())!=null)
				               netphone=vo.getNetphone();
				             if((temstr=vo.getHomephone())!=null)
				               homephone=vo.getHomephone(); 
                  %>        
                           <tr onClick="edit('<%=noteid%>','<%=username%>','<%=fullname%>','<%=orguuid2%>','<%=officeaddress%>','<%=officephone%>','<%=homephone%>','<%=mobilephone%>','<%=netphone%>','<%=faxphone%>')">
                              <td width="5%" align="center" bgcolor="#FFFFFF">
                                <input type=checkbox name=mm value=<%=noteid%> onclick="checkItem(this, 'mmAll')">
                              </td>
				              <td style="cursor:hand" width="13%" align="center" bgcolor="#FFFFFF" class="message_title">
				                <%=username%>
                              </td>
                              <td style="cursor:hand" width="20%" align="center" bgcolor="#FFFFFF" class="message_title">
                                <%=fullname%>
                              </td>
                              <td style="cursor:hand" width="20%" align="center" bgcolor="#FFFFFF" class="message_title">
                                 <%=officeaddress%>
                              </td>
                              <td style="cursor:hand" width="9%" align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;
                                 <%=officephone%>
                              </td>
                              <td style="cursor:hand" width="9%" align="center" bgcolor="#FFFFFF" class="message_title">
                                 <%=faxphone%>
                              </td>
                              <td style="cursor:hand" width="8%" align="center" bgcolor="#FFFFFF" class="message_title">
                                 <%=mobilephone%>
                              </td>
                              <td style="cursor:hand" width="8%" align="center" bgcolor="#FFFFFF" class="message_title">
                                 <%=netphone%>
                              </td>
                              <td style="cursor:hand" width="8%" align="center" bgcolor="#FFFFFF" class="message_title">&nbsp;
                                 <%=homephone%>
                              </td>
              </tr>
                         <%}
                           }
                           }catch(Exception e){out.println(e);}
                         %>
			 
          </table>
            <!--�绰��չʾ��-->
<!--    -->
</form>

<%
   if("added".equals(addtipstr))
   {
      out.println("<script>alert('��ӳɹ�')</script>");
      addtipstr="";
   }
   else if("notadded".equals(addtipstr))
   {
      out.println("<script>alert('���ʧ��')</script>");
      addtipstr="";
   }
   else if("deleted".equals(addtipstr))
   {
      out.println("<script>alert('ɾ���ɹ�')</script>");
      addtipstr="";
   }
   else if("deleted".equals(addtipstr))
   {
      out.println("<script>alert('ɾ��ʧ��')</script>");
      addtipstr="";
   }
   else if("updated".equals(addtipstr))
   {
      out.println("<script>alert('�޸ĳɹ�')</script>");
      addtipstr="";
   }
   else if("notupdated".equals(addtipstr))
   {
      out.println("<script>alert('�޸�ʧ��')</script>");
      addtipstr="";
   }
   

%>
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
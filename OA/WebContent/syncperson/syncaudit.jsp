<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>

<%@ page import="com.icss.oa.sync.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
List list=(List)request.getAttribute("userList");
                 
List orgCnameList=(List)request.getAttribute("orgCnameList");
				
List plist = new ArrayList();
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
</style>
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />

<script  language="javascript">

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



 function edituser(userid)
	{
		//alert(userid);
        //<input type=button onclick="window.open('����')">
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
          alert('��ѡ������������');
          }
 }



</script>

</head>

<body>
<jsp:include page= "/include/top.jsp" />




<form name="frm" method="post" action="">



<!--����������-->
<table width="100%">
	<tr>
    	<td width="10"></td>
      <td width="30%" align="center" valign="top" id="orgtree" style="display:none">&nbsp;</td>
        <td width="90%">
        	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor">
            	<tr class="block_title">
                	<td colspan="10">��������</td>
                </tr>
                <tr>
                	<td width="10%" bgcolor="#FFFFFF" class="table_title">����</td>
                  <td width="40%" bgcolor="#FFFFFF"><span class="content">
                    <input name="name" type="text" class="biankuang" size="10"/>
                  ����д��Ա������֧��ģ����ѯ��</span></td>
                    <td width="10%" bgcolor="#FFFFFF" class="table_title">�û���</td>
                  <td width="40%" bgcolor="#FFFFFF"><label>
                    <input name="usercode" type="text" id="usercode" />
                  </label></td>
              </tr>
                <tr>
				    <td bgcolor="#FFFFFF" class="table_title">��������</td>
				    <td bgcolor="#FFFFFF">
				      <select name="operatetype" id="operatetype">
                      <option value="all">����</option>
                      <option value="new">���</option>
                      <option value="upd">�޸�</option>
					  					<option value="del">ɾ��</option>
					 						<option value="transfer">��ת</option>
                      </select>
                    </td>
				
                	<td bgcolor="#FFFFFF" class="table_title">�������</td>
                    <td bgcolor="#FFFFFF" class="message_title_bold">
					<select name="audittype" id="audittype">
                      <option value="10">����</option>
                      <option value="1">ͨ��</option>
                      <option value="-1">δͨ��</option>
					  <option value="9" selected>������</option>
                      </select></td>
                </tr>
				<tr>
				    <td bgcolor="#FFFFFF" class="table_title"></td>
				    <td bgcolor="#FFFFFF" class="table_title" colspan="4">
				      <input type="button" name="search" value="��ѯ" onclick="doAction('/oabase/servlet/GetSearchUserServlet')" />
			        </td>
				</tr>
            </table>
            <!--����������-->
		
            <table border="0" cellpadding="0" cellspacing="0">
            	<tr><td height="5"></td></tr>
            </table>
            
            <!--�����û��б�-->
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
			 
              <tr>
			        <td width="6%" align="left" class="block_title" nowrap>
					 <input type="checkbox" name="syncuserall" onclick="checkAll(this,'syncuser')" />ȫѡ</td>
                	<td width="6%" align="center" class="block_title">����</td>
                    <td width="15%" align="center" class="block_title">������λ</td>
                    <td width="12%" align="center" class="block_title">��������û���</td>
                    <td width="8%" align="center" class="block_title">�ֻ�</td>
                    <td width="10%" align="center" class="block_title">ʱ��</td>
                    
                    <td width="6%" align="center" class="block_title">����</td>
				    <td width="6%" align="center" class="block_title">����</td>

					<td width="8%" align="center" class="block_title">�����ֶ�</td>
					<td width="8%" align="center" class="block_title">����ǰ</td>
					<td width="8%" align="center" class="block_title">���ĺ�</td>

			        <td width="6%" align="center" class="block_title">״̬</td>

              </tr>
              
					        
              <% 
			 if(!list.isEmpty()&&list!=null){
					orgCnameList=(List)request.getAttribute("orgCnameList");
					
                   for(int i=0;i<list.size();i++)
                   {
                    String operateType="";
					String orgCname="";
					String color="";
					String  auditType="";
					int   tempauditType=9;
                    PersonTempVO person=(PersonTempVO)list.get(i);
                    String  tempType=person.getOperatetype();
                    tempauditType=person.getApproved().intValue();
                    String userid=person.getUserid();
					String  personname=person.getPersonname();
					String alterfield=person.getAlterfield();
					String origin=person.getOrigin();
					String purpose=person.getPurpose();
			
					 if(personname==null)
						   personname="��";
					 if(userid==null)
						    userid="��";
					 if(alterfield==null)
						 alterfield="-";
					 if(origin==null)
						 origin="-";
					 if(purpose==null)
						 purpose="-";

                     if(tempType.equals("new")){
                        operateType="���";
                     }
                     else if(tempType.equals("upd")){
                        operateType="�޸�";
                     }
                     else if(tempType.equals("del")){
                        operateType="ɾ��";
                     }
					 else{
						 operateType="��ת";
					 }
                    
                    if(tempauditType == 1){
                       auditType="ͨ��";
                     }
                     else if(tempauditType== -1){
                       auditType="δͨ��";
                	}else{
                       auditType="<b>������</b>";
                     }
                     
              %>
              
              
                 <tr onmouseover="this.bgColor='#e2f2cd';" onmouseout="this.bgColor='#FFFFFF'"  bgcolor="#FFFFFF">
				       
							<td  align="left" class="message_title">
							<% if (tempauditType==9){ 
								//����ͬһ��Ա��������
								String hrid=person.getHrid();
								if(plist.contains(hrid)){
									continue;
								}else{
									plist.add(hrid);
								}

								%>
							<input type="checkbox" name="syncuser" value=<%=person.getId()%> onclick="checkItem(this,'syncuserall')" />
							<% } %>
							</td>
							<td align="center" class="message_title"><%=personname %></td>
							<td align="center" class="message_title">
							<%
								 orgCname=(String)orgCnameList.get(i); 
							     if(orgCname!=null)
									 out.println(orgCname);
								 else
									 out.println("��");


							%>
							</td>
							<% 
							
							 if((new Integer(0)).equals(person.getIsright()))
							   { 
							      color="blue";
							   }
							   if((new Integer(1)).equals(person.getIsuniq())){
							     color="red";
							   }
                                
							 %>
						   	<td align="center" class="message_title">
                          <% if(tempType.equals("new")){%>
							<font color='<%=color%>'><%=userid %></font>
							<%}%>
							 
							 <%  if(tempType.equals("new")&& tempauditType==9){%>
							<input type="button" onclick=edituser('<%=person.getOperateid() %>') value="�޸��û���" />
							 <%}%>
							 
							</td>
						    <td align="center" class="message_title"><%=StringUtil.escapeNull(person.getMobilephone()) %></td>
						    <td align="center" class="message_title"><%=StringUtil.escapeNull(person.getTime()) %></td>
						    
						    <td align="center" class="message_title"><%=operateType %></td>
							 <td  align="center" class="message_title"><%=StringUtil.escapeNull(person.getDescribe())%></td>

                               <td  align="center" class="message_title">
							   <%=alterfield%></td> 
					 
							   <td  align="center" class="message_title"><%=origin%></td>
							   <td  align="center" class="message_title"><%=purpose%></td>

                           <td align="center" class="message_title"><%=auditType %></td>
							
					       </tr>
             
             
              <%
              } //end for
              }//end  if(!list.isEmpty()&&list==null)
              %>
				    
			  
			   <tr>
                	<td colspan="12" height="20"><%@ include file= "/include/defaultPageScrollBar.jsp" %></td>
              </tr>
              <tr>
			  <td colspan='12' align='center'>
			  <input type="button" value="����ͨ��" onclick="doAction1('/oabase/servlet/AuditServlet')"/> 
			 &nbsp;&nbsp;&nbsp;&nbsp;
			  <input type="button" value="������ͨ��" onclick="doAction1('/oabase/servlet/AuditServlet?type=no')"/> 
			  </td>
			  </tr>
         
          </table>
            <!--�����û��б�-->
            
      </td>
        <td width="10"></td>
    </tr>
</table>
<br/>
<br />

</form>
 
</body>

</html>

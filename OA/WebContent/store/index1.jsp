<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.store.vo.CatogaryVO"%>
<%@ page import="com.icss.store.vo.DeviceTypeVO"%>
<%@ page import="java.util.Map" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%
List listCatogary = (List)request.getAttribute("listCatogary");
Map mapDeviceType = (Map)request.getAttribute("mapDeviceType");
Map mapDeviceIn = (Map)request.getAttribute("mapDeviceIn");
Map mapDeviceTypeIn = (Map)request.getAttribute("mapDeviceTypeIn");
Map mapDeviceTypeWhere = (Map)request.getAttribute("mapDeviceTypeWhere");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.TOPSTYLE {
	color: #FFFFFF;
	font-size: 12px;
}
#movediv{
    width:600px;height:250px;position:absolute;border:1px solid #000;background:#EAEAEA;
    cursor:pointer;
    text-align:center;
    line-height:100px;
    left:100px;
    top:10px;
   }
-->
</style>
<link href="../style/css.css" id="homepagestyle" rel="stylesheet" type="text/css" />

<title>Ӧ�������豸����ϵͳ</title>
<style type="text/css">
<!--
.STYLE2 {
	font-size: 16px;
	font-weight: bold;
	color: #FF0000;
}
-->
</style>

<script language="javascript">
function _clearinfo(){
	if(frm.keyword.value=="�������豸�ؼ���"){
		frm.keyword.value="";
	}
}
</script>
</head>

<body>
<form name="frm" method="post">
<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="643" class="top_logo" height="84"></td>
    <td class="top_back" height="84" width="30">&nbsp;</td>
    <td width="310" class="top_back" height="84">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
              <td width="40" height="30">&nbsp;</td>
        	  <td width="32" align="right" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><img src="../images/top/top_home.png" border="0"/></td>
        	  <td width="34" align="center" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><span class="TOPSTYLE">��ҳ</span></a></td>
        	  <td width="32" align="right" valign="middle"><a href="/oabase/help/index.html" style="text-decoration:none" target="_blank"><img src="../images/top/top_help.png" border="0" /></a></td>
        	  <td width="34" align="center" valign="middle" class="TOPSTYLE"><a href="/oabase/help/index.html" style="text-decoration:none" class="TOPSTYLE" target="_blank">����</a></td>
        	  
              <td width="32" align="right" valign="middle"><span onClick="window.open('/oabase/user/ModifyPassword.jsp','�޸�����','width=640,height=200,toolbar=no,menubar=no,resizable=no,resizable=no,location=no,status=no')" style="text-decoration:none;cursor:hand;" target="_blank"><img src="../images/top/top_alter.png" border="0"/></span></td>
        	  <td width="60" align="center" valign="middle"><span onClick="window.open('/oabase/user/ModifyPassword.jsp','�޸�����','width=640,height=200,toolbar=no,menubar=no,resizable=no,resizable=no,location=no,status=no')" style="text-decoration:none;cursor:hand" target="_blank"><span class="TOPSTYLE">�޸�����</span></span></td>
              <td width="32" align="right" valign="middle"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none"><img src="../images/top/top_out.png" border="0" /></a></td>
        	  <td width="36" align="center" valign="middle" class="TOPSTYLE"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none" class="TOPSTYLE">�˳�</a></td>
        	  </tr>
            <tr><td colspan="9">&nbsp;</td></tr>
            <tr><td colspan="9" height="30">&nbsp;</td></tr>
        </table>
    </td>
    </tr>
    </table>
    </td>
    <td width="10" >&nbsp;</td>
  </tr>
</table>

<table width="1003" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr>
    	<td width="10"></td>
        <td>
        	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="top_back">
            	<tr>
                	<td width="134" height="29" valign="middle" class="top_left_buttom"><span class="message_zhuanti" id="showdate"></span></td>
                  <td width="60%" class="top_back_buttom">
               	  <table width="98%" border="0" cellpadding="0" cellspacing="0" height="29">
                        <tr>
                            <td width="48%" height="29" align="left" valign="middle" class="message_zhuanti">
                            <table border="0" cellpadding="0" cellspacing="0" width="99%">
                            	<tr>
                                	<td width="33%" align="right">
                                    <select name="type">
                                   	  <option value="0" selected>״̬</option>
                                        <option value="1">���</option>
                                        <option value="2">���</option>
                                    </select>                                  </td>
                                    <td width="22%" align="right">
                      <select name="type">
                                    	<option value="0" selected>��ѡ���豸����</option>
                                        <option value="1">��Я����</option>
                                        <option value="2">��������</option>
                                        <option value="3">ҿ�ǵ绰</option>
                                        <option value="4">¼����</option>
                                        <option value="5">��Я��ӡ��</option>
                                        <option value="6">��Я�����</option>
                                    </select>                                  
                                  </td>
                                  <td width="30%" align="center">
                                  	<input type="text" name="keyword" value="�������豸�ؼ���" onMouseDown="_clearinfo();"/>
                                  </td>
                                  <td width="15%">
                                  <input type="button" value="����" />
                                  </td>
                           	  </tr>
                            </table>
                            </td>
                      </tr>
                    </table>
                    
                  </td>
                  <td width="6%" class="top_back_buttom_right"></td>
                  <td width="21%" align="right" bgcolor="#FFFFFF">
                  <table border="0" cellpadding="0" cellspacing="0" align="right" title="���ѡ��ҳ��ɫ�ʣ�" bgcolor="#FFFFFF">
                      <tr>
                        <td width="30"></td>
                        <td>&nbsp;</td>
                        <td width="10"></td>
                        <td>&nbsp;</td>
                        <td width="10"></td>
                        <td>&nbsp;</td>
                        <td width="10"></td>
                        <td>&nbsp;</td>
                        <td width="10"></td>
                        <td>&nbsp;</td>
                        <td width="10"></td>
                        <td>&nbsp;</td>
                        <td width="10"></td>
                    </tr>
                    </table>
                  </td>
                </tr>
          </table>
        </td>
        <td width="10"></td>
    </tr>
</table>

<table width="1000" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="9" height="5"></td>
    <td colspan="3"></td>
    <td width="11"></td>
  </tr>
  
  
  <tr>
    <td width="9">&nbsp;</td>
    <td width="200" valign="top">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
		<!--
    	<tr>
        	<td height="30" background="../images/index_tiao_1.gif" class="white-13-b">&nbsp;�������</td>
        </tr>
        -->
        <tr>
        	<td align="center" valign="top">
            <table border="0" cellpadding="0" cellspacing="0" width="200" onMouseOut="_cancel(<%=listCatogary.size()%>)">
           	<%
			String titlebg = "catorytitle_green";
			int catcount = 0;
			int rowcount = 0;
			if(listCatogary!=null){
				catcount = listCatogary.size();
				for(int i=0;i<listCatogary.size();i++){
					CatogaryVO vo = (CatogaryVO)listCatogary.get(i);
					if(i==0){
			%>
            			<tr>
            <%	
					}
					if(i%2==0 && i!=0){
						rowcount++;
						CatogaryVO cvo1 = (CatogaryVO)listCatogary.get((rowcount-1)*2);
						CatogaryVO cvo2 = (CatogaryVO)listCatogary.get((rowcount-1)*2+1);
						String catname1 = cvo1.getClassname();
						String catname2 = cvo2.getClassname();
						List listDeviceType1 = (List)mapDeviceType.get(catname1);
						List listDeviceType2 = (List)mapDeviceType.get(catname2);		
			%>
            			</tr>
            <%
						if(listDeviceType1!=null){
			%>
            				<tr id="catory<%=(rowcount-1)*2+1%>" style="display:none"><td colspan="4" class="black-13">
            <%
							for(int m=0;m<listDeviceType1.size();m++){
								DeviceTypeVO dtvo = (DeviceTypeVO)listDeviceType1.get(m);
			%>
            					&nbsp;<%=dtvo.getDevicetype()%>(<%=dtvo.getDevicetypecount()%>)<br>
            <%
							}
			%>
            				</td></tr>
			<%
						}
			%>
            
            
            <%
						if(listDeviceType2!=null){
			%>
            				<tr id="catory<%=rowcount*2%>" style="display:none"><td colspan="4" class="black-13">
            <%
							for(int m=0;m<listDeviceType2.size();m++){
								DeviceTypeVO dtvo = (DeviceTypeVO)listDeviceType2.get(m);
			%>
            					&nbsp;<%=dtvo.getDevicetype()%>(<%=dtvo.getDevicetypecount()%>)<br>
            <%
							}
			%>
            				</td></tr>
			<%
						}
			%>
            
            
            
            
                        <tr><td height="1"></td></tr>
                        <tr>
            <%
					}
			%>
            		
                    <td width="100" height="26" align="center" class="<%=titlebg%>" id="catorytitle<%=i+1%>" onMouseOver="_changecatory('<%=catcount%>','<%=i+1%>')" style="cursor:hand;"><%=vo.getClassname()%></td>
                    <td width="1" bgcolor="#FFFFFF"></td>
            <%	
				}
			}
			rowcount++;
			List listDeviceEnd1 = null;
			List listDeviceEnd2 = null;
			if(catcount%2==1){
				CatogaryVO cvo1 = (CatogaryVO)listCatogary.get((rowcount-1)*2);
				String catname1 = cvo1.getClassname();
				listDeviceEnd1 = (List)mapDeviceType.get(catname1);
			%>
            	<td width="100" bgcolor="#FFFFFF"></td></tr>
            <%
			}else{
				CatogaryVO cvo1 = (CatogaryVO)listCatogary.get((rowcount-1)*2);
				String catname1 = cvo1.getClassname();
				listDeviceEnd1 = (List)mapDeviceType.get(catname1);
				CatogaryVO cvo2 = (CatogaryVO)listCatogary.get((rowcount-1)*2+1);
				String catname2 = cvo2.getClassname();
				listDeviceEnd2 = (List)mapDeviceType.get(catname2);
			}
			%>
            
            <%
						if(listDeviceEnd1!=null){
			%>
            				<tr id="catory<%=(rowcount-1)*2+1%>" style="display:none"><td colspan="4" class="black-13">
            <%
							for(int m=0;m<listDeviceEnd1.size();m++){
								DeviceTypeVO dtvo = (DeviceTypeVO)listDeviceEnd1.get(m);
			%>
            					&nbsp;<%=dtvo.getDevicetype()%>(<%=dtvo.getDevicetypecount()%>)<br>
            <%
							}
			%>
            				</td></tr>
			<%
						}
			%>
            
            
            <%
						if(listDeviceEnd2!=null){
			%>
            				<tr id="catory<%=rowcount*2%>" style="display:none"><td colspan="4" class="black-13">
            <%
							for(int m=0;m<listDeviceEnd2.size();m++){
								DeviceTypeVO dtvo = (DeviceTypeVO)listDeviceEnd2.get(m);
			%>
            					&nbsp;<%=dtvo.getDevicetype()%>(<%=dtvo.getDevicetypecount()%>)<br>
            <%
							}
			%>
            				</td></tr>
			<%
						}
			%>	
        
        </table>
        </td>
        </tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr><td height="10"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr>
        	<td height="30" background="../images/cut1_lc_title1.gif" class="white-13-b">&nbsp;�ʲ���������</td>
        </tr>
        <tr>
        	<td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#EFEFEF">
                <tr><td height="5" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
               	  <td class="black-13-1"><a href="rule/guanli/�����·����»����豸���𡢶�ʧ�⳥�취����֪ͨ�·�����2003���ƹ�����41��.doc"  style="text-decoration:none" class="black-13-1">�����·����»����豸���𡢶�ʧ�⳥�취����֪ͨ�·���(2003)�ƹ�����41��</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
                	<td class="black-13-1"><a href="rule/guanli/�淶�ͼ�ǿ��ҵ���Ź̶��ʲ�������ָ����� ���� �·��ģ�2000�������ֵ�375��.doc" style="text-decoration:none" class="black-13-1">�淶�ͼ�ǿ��ҵ���Ź̶��ʲ�������ָ����������·���(2000)�����ֵ�375��</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            	
                <tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
                	<td class="black-13-1"><a href="rule/guanli/��ҵ��λ�����ʲ��������а취�����������36�ţ�.doc" style="text-decoration:none" class="black-13-1">��ҵ��λ�����ʲ��������а취�����������36�ţ�</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            </table>
            </td>
        </tr>
    </table>
    
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr><td height="10"></td></tr>
    </table>
        
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr>
        	<td height="30" background="../images/cut1_lc_title1.gif" class="white-13-b">&nbsp;�ʲ����ù���</td>
        </tr>
        <tr>
        	<td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#EFEFEF">
                <tr><td height="5" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
               	  <td class="black-13-1"><a href="rule/peizhi/����ӡ���������豸�͵�Ѷ���ı������̡���֪ͨ�·�����2006���Ʒ�����494��.doc" style="text-decoration:none" class="black-13-1">����ӡ���������豸�͵�Ѷ���ı������̡���֪ͨ�·�����2006���Ʒ�����494��</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
                	<td class="black-13-1"><a href="rule/peizhi/����ת��������ͨ�š���Ӱ��������豸���������а취����֪ͨ(�·��ġ�2007������43��).doc" style="text-decoration:none" class="black-13-1">����ת��������ͨ�š���Ӱ��������豸���������а취����֪ͨ(�·��ġ�2007������43��)</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
                	<td class="black-13-1"><a href="rule/peizhi/�»����ش���ͻ���¼������豸�����취���Ӹĸ壩.doc" style="text-decoration:none" class="black-13-1">�»����ش���ͻ���¼������豸�����취���Ӹĸ壩</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            </table>
            </td>
        </tr>
    </table>
    
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr><td height="10"></td></tr>
    </table>
        
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr>
        	<td height="30" background="../images/cut1_lc_title1.gif" class="white-13-b">&nbsp;�ʲ����ù���</td>
        </tr>
        <tr>
        	<td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#EFEFEF">
                <tr><td height="5" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
               	  <td class="black-13-1"><a href="rule/chuzhi/����ӡ�����»�����ҵ���Ź̶��ʲ����ù���ʵʩ�취����֪ͨ�·��ģ�2000���ƹ����ֵ�160��.doc" style="text-decoration:none" class="black-13-1">����ӡ�����»�����ҵ���Ź̶��ʲ����ù���ʵʩ�취����֪ͨ�·��ģ�2000���ƹ����ֵ�160��</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            	<tr>
                	<td width="10" align="center"><img src="../images/col520_title_icon.gif" width="6" height="24" /></td>
                	<td class="black-13-1"><a href="rule/chuzhi/����ӡ�����»���ͨ�ž��豸���ð취����֪ͨ.doc" style="text-decoration:none" class="black-13-1">����ӡ�����»���ͨ�ž��豸���ð취����֪ͨ</a></td>
                </tr>
                <tr><td height="10" colspan="2"></td></tr>
            </table>
            </td>
        </tr>
    </table>
    
    </td>
    <td width="580" align="center" valign="top">
    <table border="0" cellpadding="0" cellspacing="0" width="570" align="center">
    	<tr>
       	  <td height="140">
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
            	<tr>
                	<td>
                
					</td>
                <!--
                	<td width="37%" height="140" align="center"><img src="../images/t43.jpg" width="168" height="140" /></td>
                    <td width="63%" align="left" class="black-13">
                    	��Я���� IBM T43<br />
                        2.0G/1G/80/ָ��/����/�߷�/����/����<br />
                        �豸������10̨<br />
                        ���������5̨<br />
                        ���������5̨<br />
                    </td>
                -->
            	</tr>
            </table>
          </td>
        </tr>
        <tr><td height="5"></td></tr>
        <tr>
        	<td>
            <table border="0" cellpadding="0" cellspacing="0" width="570" align="center">
            	<tr>
                	<td colspan="10">
                    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
                        	<tr>
                            	<td width="100" class="headtitle_red" align="center" style="cursor:hand;" id="titlecontent1" onMouseOver="_changecontent('1');">�豸����</td>
                                <td width="1" bgcolor="#FFFFFF"></td>
                                <td width="100" class="headtitle_orange" align="center" style="cursor:hand;" id="titlecontent2" onMouseOver="_changecontent('2');">�豸��ϸ</td>
                                <td width="1" bgcolor="#FFFFFF"></td>
                                <td width="100" class="headtitle_orange" align="center" style="cursor:hand;" id="titlecontent3" onMouseOver="_changecontent('3');">�ڿ����</td>
                                <td width="1" bgcolor="#FFFFFF"></td>
                                <td width="100" class="headtitle_orange" align="center" height="30" style="cursor:hand;" id="titlecontent4" onMouseOver="_changecontent('4');">�����ϸ</td>
                                <td width="1" bgcolor="#FFFFFF"></td>
                                <td width="167" background="../images/headtitle_bg.gif">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!--����-->
                <tr id="content1" style="display:block">
                	<td>
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                       	<%
						if(listCatogary!=null){
							for(int j=0;j<listCatogary.size();j++){
								CatogaryVO vo = (CatogaryVO)listCatogary.get(j);
								if(j==0){
						%>
									<tr>

						<%	
								}
								if(j%4==0 && j!=0){
						%>
									</tr>
									<tr><td colspan="10" height="5"></td></tr>
									<tr>
						<%
								}
						%>
                        <td width="140">
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" class="cat_bgcolor">
                        	<tr><td height="100" align="center" valign="middle"><span onclick="changeDiv('movediv','<%=request.getContextPath()%>/servlet/DeviceTypeListServlet?devicetypename=<%=vo.getClassname()%>')" style="cursor:hand;"><img src="<%=request.getContextPath()%>/images/device_<%=j+1%>.jpg" width="120" height="99" border="0" /></span></td>
                        	</tr>
                            <tr>
                            	<td class="black-12">
                                <%
								int incount = Integer.valueOf((String)mapDeviceIn.get(vo.getClassname())==null?"0":(String)mapDeviceIn.get(vo.getClassname())).intValue();
								int allcount = Integer.valueOf(vo.getDevicecount().toString()).intValue();
								int outcount = allcount-incount;
								%>
                                �豸���ƣ�<a href="<%=request.getContextPath()%>/servlet/DeviceTypeListServlet?devicetypename=<%=vo.getClassname()%>" class="black-12"><%=vo.getClassname()%></a><br />
                                �豸������<%=vo.getDevicecount()%><br />
                                ���������<%=outcount%><br />
                                ���������<%=incount%><br />                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="5"></td>
                    <%
						}
					}
					
					%>
                </table>
                </td>
            </tr>
			
            <!--����-->
            
            <!--�豸��ϸ-->
            <tr id="content2" style="display:none">
                <td>
                <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#CCCCCC">
                	<tr>
                    	<td width="18%" height="30" align="center" bgcolor="#999999" class="white-13-b">����</td>
                   	  <td width="24%" align="center" bgcolor="#999999" class="white-13-b">�ͺ�</td>
                      <td width="11%" align="center" bgcolor="#999999" class="white-13-b">�豸����</td>
                      <td width="11%" align="center" bgcolor="#999999" class="white-13-b">�����</td>
                    	<td width="11%" align="center" bgcolor="#999999" class="white-13-b">�����</td>
                    	<td width="25%" align="center" bgcolor="#999999" class="white-13-b">�ⷿλ��</td>
                    </tr>
                    <%
					int devicetypeincount = 0;
					int devicetypeoutcount = 0;
					int intdevicetypecount = 0;
					String inwhere = "";
					if(listCatogary!=null){
						for(int j=0;j<listCatogary.size();j++){
							CatogaryVO vo = (CatogaryVO)listCatogary.get(j);
							List listDevicetype=(List)mapDeviceType.get(vo.getClassname());
							int intTypecount = 1;
							String devicetypename="";
							String devicetypecount = "";
							if(listDevicetype!=null){
								intTypecount = listDevicetype.size();
								DeviceTypeVO tvo = (DeviceTypeVO)listDevicetype.get(0);
								devicetypename=tvo.getDevicetype();
								devicetypecount = tvo.getDevicetypecount()==null?"0":tvo.getDevicetypecount().toString();
								intdevicetypecount = Integer.valueOf(devicetypecount).intValue();
								
								devicetypeincount = Integer.valueOf(mapDeviceTypeIn.get(tvo.getDevicetype())==null?"0":(String)mapDeviceTypeIn.get(tvo.getDevicetype())).intValue();
								devicetypeoutcount = intdevicetypecount - devicetypeincount;
								inwhere = mapDeviceTypeWhere.get(devicetypename)==null?"":(String)mapDeviceTypeWhere.get(devicetypename);
							}
						%>
                    <tr>
                    	<td width="18%" rowspan="<%=intTypecount%>" align="center" class="black-13" bgcolor="#FFFFFF"><%=vo.getClassname()%></td>
                    	<td width="24%" height="25" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypename%></td>
                   	  <td width="11%" align="center" class="black-13" bgcolor="#FFFFFF"><%=intdevicetypecount%></td>
                      <td width="11%" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypeincount%></td>
                        <td width="11%" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypeoutcount%></td>
                    	<td width="25%" align="center" class="black-13" bgcolor="#FFFFFF"><%=inwhere%></td>
                    </tr>
                    <%
							if(listDevicetype!=null & listDevicetype.size()>1){
								for(int m=1;m<listDevicetype.size();m++){
									DeviceTypeVO tvo = (DeviceTypeVO)listDevicetype.get(m);
									devicetypename=tvo.getDevicetype();
									devicetypecount = tvo.getDevicetypecount().toString();
									intdevicetypecount = Integer.valueOf(devicetypecount).intValue();
									devicetypeincount = Integer.valueOf(mapDeviceTypeIn.get(tvo.getDevicetype())==null?"0":(String)mapDeviceTypeIn.get(tvo.getDevicetype())).intValue();
									devicetypeoutcount = intdevicetypecount - devicetypeincount;
									inwhere = mapDeviceTypeWhere.get(devicetypename)==null?"":(String)mapDeviceTypeWhere.get(devicetypename);
					%>
                                <tr>
                                    <td width="24%" height="25" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypename%></td>
                                  	<td width="11%" align="center" class="black-13" bgcolor="#FFFFFF"><%=intdevicetypecount%></td>
                                  	<td width="11%" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypeincount%></td>
                                    <td width="11%" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypeoutcount%></td>
                                    <td width="25%" align="center" class="black-13" bgcolor="#FFFFFF"><%=inwhere%></td>
                                </tr>
                    <%
								}
							}
						}
					}
					%>
                </table>
                </td>
            </tr>
            <!--�豸��ϸ-->
            
            <!--�ڿ�-->
            <tr id="content3" style="display:none">
                <td>
                <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#CCCCCC">
                	<tr>
                    	<td width="23%" height="30" align="center" bgcolor="#999999" class="white-13-b">����</td>
                    	<td width="30%" align="center" bgcolor="#999999" class="white-13-b">�ͺ�</td>
                    	<td width="11%" align="center" bgcolor="#999999" class="white-13-b">�ڿ���</td>
                    	<td width="36%" align="center" bgcolor="#999999" class="white-13-b">�ⷿλ��</td>
                    </tr>
                    <%
					if(listCatogary!=null){
						for(int j=0;j<listCatogary.size();j++){
							CatogaryVO vo = (CatogaryVO)listCatogary.get(j);
							List listDevicetype=(List)mapDeviceType.get(vo.getClassname());
							int intTypecount = 1;
							String devicetypename="";
							String devicetypecount = "";
							if(listDevicetype!=null){
								intTypecount = listDevicetype.size();
								DeviceTypeVO tvo = (DeviceTypeVO)listDevicetype.get(0);
								devicetypename=tvo.getDevicetype();
								devicetypecount = tvo.getDevicetypecount().toString();
							}
						%>
                    <tr>
                    	<td width="14%" rowspan="<%=intTypecount%>" align="center" class="black-13" bgcolor="#FFFFFF"><%=vo.getClassname()%></td>
                    	<td width="22%" height="25" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypename%></td>
                   	  	<td width="13%" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypecount%></td>
                    	<td width="26%" align="center" class="black-13" bgcolor="#FFFFFF">&nbsp;</td>
                    </tr>
                    <%
							if(listDevicetype!=null & listDevicetype.size()>1){
								for(int m=1;m<listDevicetype.size();m++){
									DeviceTypeVO tvo = (DeviceTypeVO)listDevicetype.get(m);
									devicetypename=tvo.getDevicetype();
									devicetypecount = tvo.getDevicetypecount().toString();
					%>
                                <tr>
                                    <td width="22%" height="25" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypename%></td>
                                  	<td width="13%" align="center" class="black-13" bgcolor="#FFFFFF"><%=devicetypecount%></td>
                                    <td width="26%" align="center" class="black-13" bgcolor="#FFFFFF">&nbsp;</td>
                                </tr>
                    <%
								}
							}
						}
					}
					%>
                    
                </table>
                </td>
            </tr>
            <!--�ڿ�-->
            
            <!--���-->
            <tr id="content4" style="display:none">
                <td>
                <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#CCCCCC">
                	<tr>
                    	<td width="17%" height="30" align="center" bgcolor="#999999" class="white-13-b">����</td>
                    	<td width="18%" align="center" bgcolor="#999999" class="white-13-b">�ͺ�</td>
                   	  	<td width="17%" align="center" bgcolor="#999999" class="white-13-b">�����</td>
                   	  	<td width="17%" align="center" bgcolor="#999999" class="white-13-b">���ʱ��</td>
                      	<td width="17%" align="center" bgcolor="#999999" class="white-13-b">Ԥ�ƹ黹ʱ��</td>
                	  	<td width="14%" align="center" bgcolor="#999999" class="white-13-b">��ϵ�绰</td>
                	</tr>
                   	<tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">��Я����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">IBM T60</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">��Я����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">IBM T60</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">��Я����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">IBM T60</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">��Я����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">IBM T60</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#f5d173" class="black-13">��Я����</td>
                    	<td width="18%" align="center" bgcolor="#f5d173" class="black-13">IBM T60</td>
                   	  	<td width="17%" align="center" bgcolor="#f5d173" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#f5d173" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#f5d173" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#f5d173" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
					<tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                    <tr>
                    	<td width="17%" height="25" align="center" bgcolor="#FFFFFF" class="black-13">�����</td>
                    	<td width="18%" align="center" bgcolor="#FFFFFF" class="black-13">NIKON D350</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">����</td>
                   	  	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-12</td>
                      	<td width="17%" align="center" bgcolor="#FFFFFF" class="black-13">2008-5-24</td>
                	  	<td width="14%" align="center" bgcolor="#FFFFFF" class="black-13">13800909001</td>
                	</tr>
                </table>
                </td>
            </tr>
            <!--���-->
            </table>
            </td>
        </tr>
    </table>
    </td>
    <td width="200" valign="top">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
   	  <tr>
        	<td background="../images/right_top.gif" width="155" height="110" style="background-repeat:no-repeat">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td class="black-13">
                    &nbsp;�û�����<input type="text" class="biankuang-blue" name="userid" size="10" />                </td>
            </tr>
            <tr><td height="10"></td></tr>
            <tr>
            	<td align="left" class="black-13" nowrap> 
                &nbsp;�ܡ��룺<input type="text" class="biankuang-blue" name="password" size="10" />&nbsp;<input type="button" value="��¼" /></td>
                </tr>
             </table>
          </td>
        </tr>
        <tr>
        	<td background="../images/headtitle_bg.gif" height="30" class="white-13-b">&nbsp;����������</td>
        </tr>
        
        <tr>
        	<td height="160" class="black-13">
            <marquee direction="up" height="160" width="98%" scrollamount="1" scrolldelay="2" onMouseOver=stop() onMouseOut=start() style="cursor:hand" align="middle">
            &nbsp;���������������4�� ����10��<br />
        	&nbsp;��ӡ�������2̨ ����5̨
            </marquee>
            </td>
        </tr>
        
        <tr>
        	<td background="../images/headtitle_bg.gif" height="30" class="white-13-b">&nbsp;�����ʱ����</td>
        </tr>
        <tr>
        	<td height="160" class="black-13">
            <marquee direction="up" height="160" width="98%" scrollamount="1" scrolldelay="2" onMouseOver=stop() onMouseOut=start() style="cursor:hand" align="middle">
            &nbsp;���� ���������� ���ڣ���<br />
            &nbsp;���� IBMT60 ���ڣ���
            </marquee>
            </td>
        </tr>
    </table>
    </td>
    
  </tr>
</table>

<table width="980" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr><td height="10"></td></tr>
</table>
<table width="980" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="grap-12">Tel:010-63073906 Copyright (C) 2008 ��Ȩ���С���<br />
      ������λ���»���Ʋƾ֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  </tr>
</table>


</form>
<div id="movediv"  style="position:absolute;left:240px;top:40px;display:none" onmousedown="moveInit('movediv',event);" onmousemove="Move('movediv',event)" onmouseup="stopMove()" onmouseout="stopMove()"></div>
</body>
</html>

<script language="javascript">
function _changecontent(id){
	for(i=1;i<5;i++){
		document.getElementById("content"+i).style.display="none";
		document.getElementById("titlecontent"+i).className="headtitle_orange";
	}
	document.getElementById("content"+id).style.display="block";
	document.getElementById("titlecontent"+id).className="headtitle_red";
}

function _changecatory(count,id){
	for(i=1;i<=count;i++){
		document.getElementById("catory"+i).style.display="none";
		document.getElementById("catorytitle"+i).className="catorytitle_green";
	}
	document.getElementById("catory"+id).style.display="block";
	document.getElementById("catorytitle"+id).className="catorytitle_blue";
}

function _cancel(count){
	for(i=1;i<=count;i++){
		document.getElementById("catory"+i).style.display="none";
		document.getElementById("catorytitle"+i).className="catorytitle_green";
	}
}
function changeDiv(obj,url)
   {
		document.getElementById(obj).style.display="";
		document.getElementById(obj).innerHTML = "<iframe src='"+url+"'   width=100%   height=100% marginheight='0px' marginwidth='0px' frameborder='0'></iframe><input type=\"button\" onclick=\"this.parentElement.style.display='none'\" value=\"�ر�\" />";
   }	
</script>
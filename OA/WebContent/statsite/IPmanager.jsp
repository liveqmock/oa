<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.statsite.vo.*"%>
<%@ page import="com.icss.oa.statsite.admin.TrimZero"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<LINK  href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
</style>
<title>常用站点</title>
</head>
<script language="JavaScript">
function _checkPage(){
<%
		int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
		int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
		if(curPageNum1>pageCount1){
			curPageNum1=pageCount1;
			String url=request.getContextPath()+"/servlet/ListWebPageServlet?_page_num="+curPageNum1;
%>
		this.location="<%=url%>";
<%
		}
%>
}
function IsCheck(){
	if(document.form_update.ipId.value  == "null"){
    	alert("请选择一条记录！");
		return false;
	}
	return true;
}
function IsFormItemEmpty(){
  	if(document.form_update.AdressName.value==""){
    	alert("请填写地址名称！");
    	return true;
    }
    if(document.form_update.EndIP.value==""){
    	alert("请填写结束的IP地址！");
    	return true;
    }
 	if(document.form_update.StartIP.value==""){
    	alert("请填写开始的IP地址");
    	return true;
    }
	return false;
} 
function _Add(){
	if(!IsFormItemEmpty()){
    	document.form_update.action="<%=request.getContextPath()%>/servlet/addIPServlet";
  		document.form_update.submit();
 	}
}
function _Update(){
	 if(IsCheck()){
     	if(!IsFormItemEmpty()){
			document.form_update.action="<%=request.getContextPath()%>/servlet/updateIPServlet";
			document.form_update.submit();
		}
    }
}
function _Delete(){
    if(IsCheck()){
		document.form_update.action="<%=request.getContextPath()%>/servlet/delIPServlet";
    	document.form_update.submit();
    }
}
function UpdateSubSystem(AdressName,StartIP,EndIP,IPDescrptor,ipID)
{
    	
	document.form_update.AdressName.value   = AdressName;
    document.form_update.StartIP.value   = StartIP;
    document.form_update.EndIP.value   = EndIP;
	document.form_update.IPDescrptor.value = IPDescrptor;
	document.form_update.ipId.value   = ipID;
	if(document.form_update.IPDescrptor.value=="null"){document.form_update.IPDescrptor.value="";}
}

function _addressQuery(url){
		document.QueryForm.action=url+"/servlet/addressQueryServlet";
	    document.QueryForm.submit();	
	}
function _ipQuery(url){

	        if(document.QueryForm.ip.value==""){
	    	alert("请填写IP!");
			    }else{
					document.QueryForm.action=url+"/servlet/IPQueryServlet";
				    document.QueryForm.submit();	
				    }
	}
</script>

<%  
    List list1 = (List)request.getAttribute("list1");
    if(list1==null){ list1 = (List)request.getAttribute("list"); }
	Iterator it1 = list1.iterator();
	%>

<%  
    List list2 = (List)request.getAttribute("list2");
    Iterator it2 =null;
    if(list2!=null){  
	it2 = list2.iterator();}
%>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif">
<script language="javascript">
_checkPage()
</script>
<FORM name=QueryForm method=post>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" id="search"  >
  <tr>
  
    <td width="" valign="top">
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
            <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">新华社内部网IP分布查询</td>
          <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src=<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%">
		    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
				  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="171" height="22" class="text-01" align="right">根据选择的地址查询：</td>
                      <td width="2" rowspan="4" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"></td>
                        <td width="319" bgcolor="F2F9FF" class="text-01"><select name="address">
                             <option value="all">全部IP地址</option>
                        <%   
                             String [] liststr = new String[500];
                             boolean flag = false;
                             int i1=0;
                             while(it2.hasNext()){  
                             						StatSiteIpcontentListVO vo = (StatSiteIpcontentListVO)it2.next();
                                                    liststr [i1]= vo.getIpcontent();
                                                    i1++;
                                                   %>
                            <% 
                             for(int j=0;j<i1-1;j++){ if(vo.getIpcontent().equals(liststr[j])){flag=true;}}
                             if(flag==false){%> 
                            <option value="<%=vo.getIpcontent()%>" <% if(vo.getIpcontent().equals(request.getParameter("address"))){out.print("selected");}%>><%=vo.getIpcontent()%></option>
                                            <%}
                                            flag=false;
                                            }%>
                          </select> 
						  <img onClick="javascript:_addressQuery('<%=request.getContextPath()%>')" src="<%=request.getContextPath()%>/images/botton-search_in.gif" align="absmiddle" style="cursor:hand"> 
                        </td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
					   <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                    <tr>
                      <td width="171" height="22" class="text-01" align="right">根据输入的IP查询：</td>
                        <td bgcolor="F2F9FF" class="text-01"><input name="ip" type="text" value="" size="29">
						<img onClick="javascript:_ipQuery('<%=request.getContextPath()%>')" src="<%=request.getContextPath()%>/images/botton-search_in.gif" align="absmiddle" style="cursor:hand"> &nbsp;请输入如：10.97.46.255  格式所示的IP</td>
                     
                      
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF"></td>
                    </tr>
                  </table>
				</td>
              </tr>
            </table>
		  </td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01" align="center">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></td>
        </tr>
      </table>
    </tr>
  </table>

</form>


<form name="form_list" action="" method="post">
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">新华社内部网IP分布列表</td>
      <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
   	<tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
		  	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
            	<tr>
                      <td width="5%" bgcolor="#FFFBEF"></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="5%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">序号</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="30%" bgcolor="#FFFBEF"><div align="center" class="title-04">地址名称</div></td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="30%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">IP地址</div></td>
					  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td width="30%" height="22" bgcolor="#FFFBEF"><div align="center" class="title-04">IP地址描述</div></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
	List list = (List)request.getAttribute("list");
	Iterator it = list.iterator();
	int i=0;
	while(it.hasNext()){
		++i;
		StatSiteIpcontentVO vo = (StatSiteIpcontentVO)it.next();
		if(i%2==1){
%>
                    <tr bgColor=#D6E7F7 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D6E7F7';">
<%
		}
		else{
%>
					<tr bgcolor="#F2F9FF"; onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';">
<%
		}
%>    	
						<%
						         StringBuffer stb_StartIP=new StringBuffer();
								 String st_StartIP = vo.getStartip().toString();
								 int num=st_StartIP.length();
								 TrimZero kk1 = new TrimZero();
								 
								 switch(num){
								      case 10:  kk1.setStr(st_StartIP.substring(0,1));
								                stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");								      			
								      			kk1.setStr(st_StartIP.substring(1,4));
								      			stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			kk1.setStr(st_StartIP.substring(4,7));
								                stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");								      			
								      			kk1.setStr(st_StartIP.substring(7,10));
								      			stb_StartIP.append(kk1.getStr());
								      				      			
								      			
								      			break;
								      case 11:  kk1.setStr(st_StartIP.substring(0,2));
								                stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			
								      			kk1.setStr(st_StartIP.substring(2,5));
								      			stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			
								      			kk1.setStr(st_StartIP.substring(5,8));
								      			stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			
								      			kk1.setStr(st_StartIP.substring(8,11));
								      			stb_StartIP.append(kk1.getStr());
								      			break;
								      			
								      case 12:  kk1.setStr(st_StartIP.substring(0,3));
								                stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			
								      			kk1.setStr(st_StartIP.substring(3,6));
								      			stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			
								      			kk1.setStr(st_StartIP.substring(6,9));
								      			stb_StartIP.append(kk1.getStr());
								      			stb_StartIP.append(".");
								      			
								      			kk1.setStr(st_StartIP.substring(9,12));
								      			stb_StartIP.append(kk1.getStr());
								      			break;
								         }%>
                              <% 
						         StringBuffer stb_EndIP=new StringBuffer();
								 String st_EndIP = vo.getEndip().toString();
								 int num1=st_EndIP.length();
								 TrimZero kk2 = new TrimZero();
								 
								 switch(num1){
								      case 10:  kk2.setStr(st_EndIP.substring(0,1));
								                stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");								      			
								      			kk2.setStr(st_EndIP.substring(1,4));
								      			stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(4,7));
								                stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");								      			
								      			kk2.setStr(st_EndIP.substring(7,10));
								      			stb_EndIP.append(kk2.getStr());
								      				      			
								      			
								      			break;
								      case 11:  kk2.setStr(st_EndIP.substring(0,2));
								                stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(2,5));
								      			stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(5,8));
								      			stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(8,11));
								      			stb_EndIP.append(kk2.getStr());
								      			break;
								      case 12:  kk2.setStr(st_EndIP.substring(0,3));
								                stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(3,6));
								      			stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(6,9));
								      			stb_EndIP.append(kk2.getStr());
								      			stb_EndIP.append(".");
								      			kk2.setStr(st_EndIP.substring(9,12));
								      			stb_EndIP.append(kk2.getStr());
								      			break;
								         }%>
						<td height="22" class="text-01" align="center"><input name="ipid" type="radio" value="radiobutton"
							 onClick='javascript:UpdateSubSystem("<%= vo.getIpcontent() %>","<%= stb_StartIP.toString()%>","<%= stb_EndIP.toString() %>",
							 "<%= vo.getIpmeno()%>","<%= vo.getId()%>")'></td>
                      	<td class="text-01" align="center"><%= vo.getId()%></td>
                      	<td class="text-01" align="center"><%= vo.getIpcontent()  %></td>
                      	<td class="text-01" align="center"><%= stb_StartIP.toString()%>--<%= stb_EndIP.toString() %></td>
                      	<td class="text-01" align="center"><%= vo.getIpmeno()==null?"":vo.getIpmeno()%></td>
                    </tr>
                    <tr>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>

<%
		}
%>
              </table>
			</td>
           </tr>
        </table></td>
        <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
      </tr>
  </table>
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="1%" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
        <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
        <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
      </tr>
  </table>
  </form>
  <form name="form_update" method="post">
  	<input name="ipId" type="hidden" value="null">
	<input name="_page_num" type="hidden" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          
      <td  background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05"> 
        添加/修改/删除 IP分布</td>
          <td width="20"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
  </table>
	  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="16%" align="right">地址名称：</td>
                  <td width="2" rowspan="7" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif" bgcolor="#FFFBEF"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                  <td width="100%" bgcolor="#F2F9FF"><input name="AdressName" type="text" class="txt3" size="26" maxlength="64"> 
                    *</td>
                  
                </tr>
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr> 
                  <td align="right" class="text-01">IP地址：</td>
                  <td bgcolor="#F2F9FF"  class="text-01"><input name="StartIP" type="text" class="txt3" size="26" maxlength="64"> 
                    *---<input name="EndIP" type="text" class="txt3" size="26" maxlength="64"> 
                    *&nbsp;请输入如：10.97.46.255 &nbsp;格式所示的IP</td>
					
                </tr>
                <tr> 
                  <td height="2" align="center" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td width="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                </tr>
                <tr > 
                  <td align="right">IP地址描述：</td>
                  <td bgcolor="#F2F9FF"  class="text-01"><textarea name="IPDescrptor" cols="50" rows="2" wrap="VIRTUAL" class="txt2" id="textarea"></textarea></td>
                  
                </tr>
                
                <tr> 
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  <td colspan="6" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                </tr>
              </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
  </table>
	   <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="10" height="21"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="175" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="12" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
  </table>
	  <div align="center"><br>
    <img src="<%=request.getContextPath()%>/images/botton-add.gif" border=0 style="cursor:hand" onClick="_Add()" >&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-update.gif" border=0 style="cursor:hand" onClick="_Update()">&nbsp;
    <img src="<%=request.getContextPath()%>/images/botton-delete.gif" border=0 style="cursor:hand"  onClick="_Delete()"><br>
  </div>

</form>
</body>
</html>

<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSAreaHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%
Integer areaId = new Integer(request.getParameter("areaid"));
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
    ConnLog.open("areaManagerlist.jsp");
    BBSAreaHandler handler = new BBSAreaHandler(conn);
    List alist = handler.findManagerListByAreaId(areaId);
    Iterator areaIterator = alist.iterator();
    BbsAreaccVO areaccVO = null;
    if(areaIterator.hasNext())
    {
	    //�õ���� ֻȡ��һ����¼
	    areaccVO = (BbsAreaccVO)areaIterator.next();
    }
    String typename = "";
    typename = handler.getAreaNameById(areaccVO.getAreaid())+"ר��������Ա�б�";
%>

<html>
<head>
<title>�»�����̳</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/bg-08.gif
topMargin=5>
<FORM name=form1 action=""  method=post>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
   
    <tr> 
      <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif"> 
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
            <td width="97%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05"><%=typename%></td>
            <td width="2%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
          </tr>
        </table>
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
            <td width="100%">
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg">
                  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                      <td width="10%" bgcolor="F2F9FF" height="22" class="text-01"><div align="right"><input type="checkbox" name="allchecked" value="checkall" onclick="updateCheckAll('perid')"></div></td>
                      <td width="90%" bgcolor="F2F9FF" class="text-01">&nbsp;&nbsp;<font color=red>ȫѡ</font></td>
                  </tr>
                  <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                  </tr>
                  </table>
                  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#FFFBEF"> 
                        <td width="10%"  height="22"><div align="center" class="title-04">ѡ��</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="40%" ><div align="center" class="title-04">��Ա</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="10%"  height="22"><div align="center" class="title-04">ѡ��</div></td>
                        <td width="0" rowspan="100"   valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                        <td width="40%" ><div align="center" class="title-04">��Ա</div></td>                        
					</tr>
                    <tr> 
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
                  <%
                     StringTokenizer namelist = null;
                     
                     
                     List userList = alist;
		             StringBuffer usernames = new StringBuffer();
		             String unames = "";
		             Iterator aIt = userList.iterator();
		             while(aIt.hasNext()){ 
			             BbsAreaccVO aVO = (BbsAreaccVO)aIt.next();
			             usernames.append(aVO.getUserid());
			             usernames.append(",");
		             }
		             if(usernames.length()>0){
		                 usernames.deleteCharAt((usernames.length()-1));
		                 unames = usernames.toString();
		             }
		             namelist = new StringTokenizer(unames,",");
                     
                     //��ʼ���
                     int i=0;
                     int j=0;
			         while (namelist.hasMoreTokens()) {
			             String ids = namelist.nextToken();       //�õ�ID
			             String pname = handler.getUserName(ids); //�õ�����
			             
			             if(i%2==0){
			                 if(j==0){
			                    j=1; %>
			                  <tr>
                                 <td width="10%" height="22" class="text-01"><div align="center"><input type="checkbox" name="perid" value="<%=ids%>"></div></td>
                                 <td width="40%" class="text-01">&nbsp;&nbsp;<%=pname%></td>
                           <%}else{
                                j=0;%>
                                 <td width="10%" height="22" class="text-01"><div align="center"><input type="checkbox" name="perid" value="<%=ids%>"></div></td>
                                 <td width="40%" class="text-01">&nbsp;&nbsp;<%=pname%></td>
                               </tr>
                               <tr>
                                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                               </tr>                             
			             <%   i++;
			                  }
			             }else{
			                  if(j==0){
			                      j=1;%>
			                   <tr>
                                  <td width="10%" bgcolor="F2F9FF" height="22" class="text-01"><div align="center"><input type="checkbox" name="perid" value="<%=ids%>"></div></td>
                                  <td width="40%" bgcolor="F2F9FF" class="text-01">&nbsp;&nbsp;<%=pname%></td>
                            <%}else{%>
                                  <td width="10%" bgcolor="F2F9FF" height="22" class="text-01"><div align="center"><input type="checkbox" name="perid" value="<%=ids%>"></div></td>
                                  <td width="40%" bgcolor="F2F9FF" class="text-01">&nbsp;&nbsp;<%=pname%></td>                              
                               <tr>
                               <tr>
                                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                  <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                               </tr> 
			             <%   j=0;
			                  i++;
			                  }
			             }%>
                     <%}   //while
                        if(j==1){
                            String colorstr = "";
                            if(i%2==1)  colorstr = "bgcolor=F2F9FF ";%>
                                 <td width="10%" height="22" <%=colorstr%>class="text-01"><div align="center">&nbsp;</div></td>
                                 <td width="40%" <%=colorstr%>class="text-01">&nbsp;</td>
                               </tr>
                             <tr>
                                 <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                 <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                                 <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                                 <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                             </tr> 
                     <% i++;}%>
                     </table>
                     <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                     <%if(i%2==0){
                  %>
                      <tr>
                        <td align="center" colspan="2" height="22" ><input class=bdtj type="button" name="delete" value="ɾ  ��" onClick="javascript:delete1('<%=request.getContextPath()%>')">&nbsp;&nbsp;&nbsp;&nbsp;<input class=bdtj type="button" name="back" value="��  ��" onClick="javascript:window.document.location.href='<%=request.getContextPath()%>/servlet/ShowSingleAreaServlet?areaId=<%=areaccVO.getAreaid().toString()%>'"></td>
                      </tr>
                   <%}else{%>
                      <tr>
                        <td align="center" bgcolor="F2F9FF" colspan="2" height="22" ><input class=bdtj type="button" name="delete" value="ɾ  ��" onClick="javascript:delete1('<%=request.getContextPath()%>')">&nbsp;&nbsp;&nbsp;&nbsp;<input class=bdtj type="button" name="back" value="��  ��" onClick="javascript:window.document.location.href='<%=request.getContextPath()%>/servlet/ShowSingleAreaServlet?areaId=<%=areaccVO.getAreaid().toString()%>'"></td>
                      </tr>
                   <%}%>                   
                      <tr>
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                      </tr>
                    </table>
                    </td>
                </tr>
              </table>
            </td>
            <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
          </tr>
        </table>
        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
            <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"></div></td>
            <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
          </tr>
        </table>
        <input type="hidden" name=areaId value=<%=areaccVO.getAreaid()%>>
       </td>
    </tr>		   
	
  </table>
</form>
</body>
</html>

<script language=javascript>
function delete1(url)
{
	var j;
	if(document.form1.perid == undefined){
		alert("û�п�ɾ������");		
	}
	else if( document.form1.perid.length == undefined){
        if(document.form1.perid.checked){
			document.form1.action=url+"/servlet/DelAreaManagerServlet";
			document.form1.submit();	
			return;
		}
	}
	else{
	    for(j=0;j<document.form1.perid.length;j++){
	        if(document.form1.perid[j].checked){
			    document.form1.action=url+"/servlet/DelAreaManagerServlet";
			    document.form1.submit();	
			    return;
			}
		}

	}

	alert("δѡ��ɾ���");
	
}

function updateCheckAll(checkname){
  var a = document.getElementsByName(checkname);
  var n = a.length;
  for (var i=0; i<n; i++)
  a[i].checked = window.event.srcElement.checked;
}
</script>

	<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("areaManagerlist.jsp");
					}
			} catch (Exception e) {
		    }
	}
%>

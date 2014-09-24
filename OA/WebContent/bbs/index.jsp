<!-- /bbs/index.jsp -->
<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%

Collection bcollection = (Collection)request.getAttribute("boardList");
Collection scollection = (Collection)request.getAttribute("subareaList");
Long time = (Long)request.getAttribute("serverTime");
List rightList =(List)request.getAttribute("rightList");
List managerList = (List)request.getAttribute("managerList");

BbsUserinfoVO userVO = (BbsUserinfoVO)request.getAttribute("userVO");
%>
<html>
<head>
<title>交流园地</title>

<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/bbs.css">
<SCRIPT src="<%=request.getContextPath()%>/include/move.js"></SCRIPT>
</head>

<body bgcolor="#ffffff" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">

<!-- <FORM name="form1" method="post"> -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
      <div align="center">
          <TABLE width="98%" border=0 cellPadding=1 cellSpacing=1 bgcolor="#62a1c2" class="text-01">
            <TBODY>
              <TR> 
                <TD width="70%" height=23 align=left bgColor=#f7f7f7 class="text-01" >
                  <img src="<%=request.getContextPath()%>/images/bbs/bbshome.gif" style="cursor:hand" onclick="javascript:_gohome('<%=request.getContextPath()%>');" title="到论坛首页">
                  <a href="<%=request.getContextPath()%>/servlet/ShowIndexServlet" title="到论坛首页">论坛首页</a>| 
                  欢迎<A title="点击查看个人信息" href="#"onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=userVO.getUserid()%>&currUserId=<%=userVO.getUserid()%>')" class="text-01"> 
                  <%=userVO.getTruename()%></A> 
                </TD>
                <TD width="30%" align=left bgColor=#f7f7f7 class="text-01">当前服务器时间：<%=CommUtil.getTime(time.longValue())%></TD>
              </TR>
            </TBODY>
          </TABLE>
        </div>
		<br>

        <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"> 
            <td width="51" height="22" nowrap background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><div align="left"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-25.gif" width="16" height="22"></div></td>
            
            <td height="22" width="90%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif" class="title-05"> 
                      
            <td width="51" nowrap background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-26.gif" class="title-05"></td>
          </tr>

        </table>      
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
     
		<tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <td width="5%" height="22" bgcolor="FBFBEE"></td>
                        <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td width="40%" bgcolor="FBFBEE" class="title-04" align="center">讨论区</td>
                        <td width="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" bgcolor="FBFBEE"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td width="10%" bgcolor="FBFBEE" class="title-04" align="center">主题数</td>
                        <td width="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" bgcolor="FBFBEE"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td width="10%" bgcolor="FBFBEE" class="title-04" align="center">总贴数</td>
                        <td width="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" bgcolor="FBFBEE"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td width="20%" bgcolor="FBFBEE" align="center" class="title-04">最后发贴</td>
                        <td width="2" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td width="15%" height="22" bgcolor="FBFBEE" align="center" class="title-04">版主</td>
                      </tr>
<%
		
                        Iterator AreaIterator = scollection.iterator();
while(AreaIterator.hasNext()){
	BbsAreaVO areaVO = (BbsAreaVO)AreaIterator.next();

%>
                      <tr> 
                        <td height="2" colspan="11" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                   
                      <tr > 
                        <td height="24" colspan="11" ><div align="left" class="title-04">&nbsp;&nbsp;≡ <%=areaVO.getAreaname()%> ≡</td>
                      </tr>
<%
		
Iterator BoardIterator = bcollection.iterator();
while(BoardIterator.hasNext()){
	BbsBoardVO vo = (BbsBoardVO)BoardIterator.next();
	String rightflag = "";
	if(vo.getAreaid().toString().equals(areaVO.getAreaid().toString()))  //此专区的讨论区
	{
	    if(rightList.size()==0){       //当前登录的人或分组未受限制
	    	if(vo.getPermit().equals("1"))
			{
				rightflag = "0";  //当前登录的人或分组不可进
			}//if
			else{
			    rightflag = "1";  //当前登录的人或分组可进
			}//else
	    }
	    else{
	        if(vo.getIslimited().equals("0")){     //无权限限制（未选择人或分组）
		        if(vo.getPermit().equals("1"))
				{
					rightflag = "0";  //当前登录的人或分组不可进
				}//if
			    else{
			        rightflag = "1";  //当前登录的人或分组可进
			    }//else
			}
		    else{                                      //选择了人或分组&&当前登录的人或分组受限制
		       for(int i=0;i<rightList.size( );i++){        //rightList 存放当前登录用户在BoardAcc中相关的记录
			       BbsBoardaccVO bvo = (BbsBoardaccVO)rightList.get(i);
			       if(vo.getBoardid().equals(bvo.getBoardid())){ //当前用户的受限板块ID:bvo.getBoardid()
			           if(vo.getPermit().equals("1"))
				       {
					       rightflag = "1";  //当前登录者可以进入
				       }//if
			           else{
			               rightflag = "0";
			           }//else
			           break;     //一个板块对一个用户只能有一次限制，所以找到相等的后就break
		           }//if
		           else{
			           if(vo.getPermit().equals("1"))
				       {
					       rightflag = "0";  //当前登录者可以进入
				       }//if
			           else{
			               rightflag = "1";
			           }//else
		           }
		       }//for
		   }//else
		}//else
%>
                      <tr> 
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td width="2" rowspan="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td width="2" rowspan="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td width="2" rowspan="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td width="2" rowspan="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                        <td width="2" rowspan="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                        <td height="2" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                      <tr bgcolor="F2F9FF"> 
                        <td width="40" nowrap> 
                          <% if(rightflag.equals("1")){%>
                           <img src="<%=request.getContextPath()%>/images/bbs/forumnew.gif" border=0> 
                          <%}else{%>
                          <a href="#" ><img title="您没有进入该板块的权限" src="<%=request.getContextPath()%>/images/bbs/forumlock.gif" border=0></a> 
                          <%}%>
                        </td>
                        <td width="40%">
                          <TABLE width="98%" align=center>
                            <TBODY>
                              <TR> 
                                <TD class=tdc rowSpan=2></TD>
                                <TD class=tdc align=left> 
                                  <% if(rightflag.equals("1")){%>
                                  <A class="text-01" title="进入<%=vo.getBoardname()%>板块" href="#"onClick="javascript:_topic('<%=request.getContextPath()%>','<%=vo.getBoardid().toString()%>')"><%=vo.getBoardname()%></A> 
                                  <%} else{%>
                                  <A title="您没有进入该板块的权限,请与管理员联系" href="#"><font color="#999999"><%=vo.getBoardname()%></font></A> 
                                  <%}//else
		  %>
                                </TD>
                                <% if(rightflag.equals("1")){%>
                                <TD class=tdc align=right width="20%"><A title=在『<%=vo.getBoardname()%>』论坛内发新主题 
           href="<%=request.getContextPath()%>/bbs/newArticle.jsp?boardId=<%=vo.getBoardid().toString()%>&boardName=<%=vo.getBoardname()%>&auditFlag=0"><IMG 
            src="<%=request.getContextPath()%>/images/bbs/quickpost.gif" border=0></A> 
                            <!--<A 
            href="#"><IMG 
            alt=在『<%=vo.getBoardname()%>』论坛内发新投票 src="<%=request.getContextPath()%>/images/bbs/quickvote.gif" 
            border=0></A--> <A 
            href="#"onClick="javascript:_primeTopic('<%=request.getContextPath()%>','<%=vo.getBoardid().toString()%>')"> 
                                  <IMG 
            alt=查看『<%=vo.getBoardname()%>』论坛精华帖 src="<%=request.getContextPath()%>/images/bbs/quickjh.gif" 
            border=0></A> </TD>
                                <%} else{%>
                                <TD class=tdc align=right width="20%"><A title=您没有在『<%=vo.getBoardname()%>』论坛内发新主题的权限 
            ><IMG 
            src="<%=request.getContextPath()%>/images/bbs/quickpost.gif" border=0></A> 
                                  <!--A 
            ><IMG 
            alt=您没有在『<%=vo.getBoardname()%>』论坛内发新投票的权限 src="<%=request.getContextPath()%>/images/bbs/quickvote.gif" 
            border=0></A--> <A> <IMG 
            alt=您没有查看『<%=vo.getBoardname()%>』论坛精华帖的权限 src="<%=request.getContextPath()%>/images/bbs/quickjh.gif" 
            border=0></A> </TD>
                                <%}%>
                              </TR>
                              <TR> 
                                <TD class="text-01" align=left width="100%" colSpan=2> 
                                  <%if(vo.getBoarddes() != null){%>
                                  <%=vo.getBoarddes()%> 
                                  <%}%>
                                </TD>
                              </TR>
                            </TBODY>
                          </TABLE>
                        <td width="10%"  class="text-01">
<div align="center"><%=vo.getTopicnum()%></div></td>
                        <td width="10%"  class="text-01">
<div align="center"><%=vo.getArticlenum()%></div></td>
                        <td width="20%" >
                        <div align="center" class="text-01"> 
                          <div align="left"> 
                            <%if(vo.getLastid() != null && (rightflag.equals("1"))){	  
	  %>
                            <IMG src="<%=request.getContextPath()%>/images/bbs/lasticon.gif" 
      border=0>主题： <A 
      title="查看帖子" 
      href="#"onClick="javascript:_article('<%=request.getContextPath()%>','<%=vo.getLastarticleid().toString()%>','<%=vo.getBoardid().toString()%>')" class="text-01"><%=vo.getLastarticlename()%> 
                            </A> <BR>
                            &nbsp;&nbsp;作者： <A  class="text-01" title="查看用户“<%=vo.getLastusername()%>”的资料"
          href="#"onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=vo.getLastid()%>&currUserId=<%=userVO.getUserid()%>')" ><%=vo.getLastusername()%> 
                            </A> <BR>
                            &nbsp;&nbsp;时间：<%=CommUtil.getTime(vo.getLasttime().longValue())%> 
                            <%}%>
                          </div></td>
                        <td width="15%"   >
                        <div align="center"> 
                            <%
			 		 
			 Iterator managerIterator = managerList.iterator();
		  
		  ManagerUserinfoVO managerVO = null;
while(managerIterator.hasNext()){ 
	 managerVO = (ManagerUserinfoVO)managerIterator.next();
	if(managerVO.getBoardid().equals(vo.getBoardid()))
	{
		%>
                            <A title=查看版主“<%=managerVO.getTruename()%>”的资料 
      href="#"onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=managerVO.getUserid().toString()%>&currUserId=<%=userVO.getUserid()%>')"  class="text-01"><%=managerVO.getTruename()%></a> 
                                <%
                                  if(managerVO.getUserid().equals(userVO.getUserid())){
                                %>
                                    <br><br>
                                    <a title=查看此板块设置 href="#" class="text-01" onClick="javascript:_openBoard('<%=request.getContextPath()%>/servlet/ShowSingleBoardServlet?boardId=<%=vo.getBoardid().toString()%>')">  <div align="center"><b>板块设置</div></a>
                                <%}%>
                            <%}
							}%>
                          </div></td>
                      </tr>
                      <%
	                  }	
					}%>
                      <%}%>
                      <tr> 
                        <td height="2" colspan="11" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                      </tr>
                    </table></td>
              </tr>
						
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
        </tr>
      </table>
      <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
          <td width="48%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"></td>
          <td width="49%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"></div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
      </table>      
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
  </tr>

</table>
<!--  </form>-->
</body>
</html>

<script language="JavaScript">

function _openUserMsg(url){
    window.open(url,"","");
}
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}

function _topic(url,boardId){
    window.location.href = url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
}

 function _article(url,topicId,boardId){
    window.location.href = url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1";
 }
 function _primeTopic(url,boardId){
    window.location.href = url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=1";
 }
 function _openBoard(url){
    window.open(url,"","width=550,height=350,left=170,top=110,scrollbars=yes");
 }
</script>
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<% 
   response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
   response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
   response.addHeader("Cache-Control", "post-check=0, pre-check=0");
   response.setHeader("Pragma", "no-cache");
%>

<%@ page import="cms.base.TimeUtil" %>
<%@ taglib uri="/WEB-INF/cms-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/cms-info.tld" prefix="cms"%>
<%
	//无页面请求参数
	//获得输出数据
	//String time = TimeUtil.getCurrentDate("yyyy年MM月dd日")+" "+TimeUtil.getCurrentWeek();
    int messagecount = 0;
%>
<script src="<%=request.getContextPath()%>/cms/include/DatePicker/WdatePicker.js"></script>
<cms:html>
<head>

<title>新华社办公信息系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

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
.title_tongzhi0{
	background-image:url('images/title_head_00.jpg');
	background-repeat:no-repeat;
}
.title_tongzhi1{
	background-image:url('images/title_head_01.jpg');
	background-repeat:no-repeat;
}
.title_tongzhi2{
	background-image:url('images/title_head_02.jpg');
	background-repeat:no-repeat;
}
.title_tongzhi3{
	background-image:url('images/title_head_03.jpg');
	background-repeat:no-repeat;
}
.title_mainmessage3{
	background-image:url('images/title_head_03.jpg');
	background-repeat:no-repeat;
}
a:link{
	color:#001122;
}
.title_light_04{
	background-image:url('images/title_light_04.jpg');
	font-size: 12px;
	color: #dc0100;
	line-height: 16px;
	font-weight: bold;
}
.title_dark_04{
	background-image:url('images/title_back_04.jpg');
	font-size: 12px;
	color: #dc0100;
	line-height: 16px;
}
.title_light_05{
	background-image:url('images/title_light_04.jpg');
	font-size: 12px;

	line-height: 16px;
	font-weight: bold;
}
.title_dark_05{
	background-image:url('images/title_back_04.jpg');
	font-size: 12px;
	
	line-height: 16px;
}
-->
</style>

<style>
#bg{background:#000000;opacity: 0.5;-moz-opacity:0.5; filter:alpha(opacity=50); width:100%; height:100%;position:absolute; top:0; left:0}
#info{height:0px; width:0px;top:50%; left:50%;position:absolute;  line-height:1.7}
#center{background:#fff;border:1px solid #217AC1; width:300px; height:100px; position:absolute; margin:-50px -150px;}
#center strong{ display:block; padding:2px 5px; background:#EBF4FC; color:#519FEE;}
#center p{padding:10px; text-align:center; color:#1C6FB8;}
.table_title_red{
	font-size: 12px;
	color: #dc0100;
	line-height: 24px;
	font-weight: bold;
}
</style>

<link href="<%=request.getContextPath()%>/Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />

<script  language="javascript">
var doit = true;
var zhineng = "zhineng";
var caibian = "caibian";
var shiye = "shiye";
var guonei = "guonei";
var guoji = "guoji";
function _delay(forwardurl){
	doit=true;
	setTimeout(forwardurl,500);
}

</script>
</head>

<cms:body>
<form name="frm" method="post">
<jsp:include page= "/include/top_index.jsp" />

<table width="1003" height="76" border="0" align="center" cellpadding="0" cellspacing="0">
  
  <tr>
    <td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td>
    <td valign="top"></td>
    <td></td>
    <td valign="top"></td>
    <td></td>
    <td valign="top"></td>
    <td width="5"></td>
  </tr>
  <tr>
  	<td width="10"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" width="10"></td>
    <td width="311" valign="top">
    	<table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        	
        
        	<tr>
        		
           	  <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tr>
                  <td colspan="11"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                </tr>
                <tr>
                  <td align="center" bgcolor="#FFFFFF"><cms:position code="7" id="pos7" type="picconv" displayMode="js" picsize="4" width="250" height="170"/>
				  </td>
                </tr>
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF">
							<tr>
							<td width="74%"></td>
							<td width="3%" align="right" valign="middle"><img src="images/more_plus.gif" width="9" height="9"></td>
							<td width="23%" height="20" align="left" nowrap="nowrap" class="foot_message">
								<cms:position code="7" type="linknoend" target="_blank" styleClass="foot_message"/>更多图片</a>
							</td>
							</tr>
						</table>
					</td>
				</tr>
                <tr>
                  <td colspan="11" align="center" class="table_title"></td>
                </tr>
                
              </table></td>
         </tr>
      </table>    
      
      <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2"><table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td align="left" bgcolor="#FFFFFF"  height="36" valign="bottom" class="title_tongzhi0" id="tongzhi">
              <table border="0" cellpadding="0" cellspacing="0">
              	<tr>
                    <td width="67" height="30" align="center" class="table_title" onMouseOver="changetongzhi(0);" style="cursor:hand;"> <cms:position target="_blank" code="8" id="pos8" style="text-decoration:none" type="linknoend" styleClass="table_title"/>
			<span class="table_title"><cms:position code="8" id="pos8" type="plain"/></span></a></td>
                    <td width="5">&nbsp;</td>
                    <td width="77" height="30" align="center" class="table_title" onMouseOver="changetongzhi(1);" style="cursor:hand">
                    	<cms:position target="_blank" code="11" id="pos11" style="text-decoration:none" type="linknoend" styleClass="table_title"/>
                    	<span class="table_title"><cms:position target="_blank" code="11" style="text-decoration:none" type="plain" styleClass="table_title"/></span>
                    	</a>
                    </td>
                   	<td width="5">&nbsp;</td>
                    <td width="77" height="30" align="center" class="table_title" onMouseOver="changetongzhi(2);" style="cursor:hand" nowrap>
                    	<cms:position target="_blank" code="10" id="pos10" type="linknoend" styleClass="table_title" style="text-decoration:none"/><span class="table_title">本部门通知</span></a>	
                    </td>
                    <td width="6">&nbsp;</td>
                    <td width="77" height="30" align="center" class="table_title" onMouseOver="changetongzhi(3);" style="cursor:hand" nowrap>
                    <cms:position target="_blank" code="56" id="pos56" style="text-decoration:none" type="linknoend" styleClass="table_title"/>
                    	<span class="table_title"><cms:position target="_blank" code="56" style="text-decoration:none" type="plain" styleClass="table_title"/></span>
                    	</a>
                    </td>
                </tr>
              </table>              </td>
              </tr>
            
            <tr id="tongzhi0" style="display:block">
              <td>
			  <IFRAME name="notice" ID="notice" width="308" FRAMEBORDER=0 scrolling="auto" SRC="/cms/filter/common/notice.jsp"></IFRAME>
			  </td>
            </tr>
            
            <!--本部门通知-->
            <tr id="tongzhi2" style="display:none">
			  <td><IFRAME name="deptnotice" ID="deptnotice" width="308" FRAMEBORDER=0 scrolling="auto" SRC="/cms/filter/common/deptnotice.jsp"></IFRAME></td>
            </tr>
            <!--督查通报-->
            <tr id="tongzhi3" style="display:none">
			  <td><IFRAME name="jctb" ID="jctb" width="308" FRAMEBORDER=0 scrolling="auto" SRC="/cms/filter/common/jctb.jsp"></IFRAME></td>
            </tr>
            
            <!--新华社发文-->
            <tr id="tongzhi1" style="display:none">
            	<td>
	            	<table width="308" border="0" cellpadding="0" cellspacing="0">
			<%
			messagecount=0;
			%>
	              	<cms:infolist name="pos11" perPageSize="6" titleLength="20" id="info11" infoType="public">
				<%
				messagecount++;
				%>
	                  <tr>
	                    <td width="79%"  align="left" class="message_title" height="24" nowrap>·<span class="function_title">[<%=department.length()>6?department.substring(0,6):department%>]</span><cms:info target="_blank" style="text-decoration:none" title="<%=infoTitle%>"/></td>
	                    <td width="21%"  align="left" class="message_title" height="24"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><span class="message_date"><cms:write name="info11" property="date" /></span></a></td>
	                  </tr>
	                </cms:infolist>
				<%
				 if(messagecount<6){
				 	for(int m=messagecount;m<6;m++){
				 %>
	             	<tr><td colspan="3" height="24"></td></tr>
	             		<%
					}
				 }
				 %>
	                  <tr>
	                    <td height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
	                  </tr>
	              </table>
            	</td>
            </tr>
            
        </table></td>
		
      </tr>
    </table>
      
      <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2"><table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td align="left" bgcolor="#FFFFFF"  height="36" valign="bottom" class="title_mainmessage0" id="messagemaintitle">
              <table border="0" cellpadding="0" cellspacing="0">
              	<tr>
                	<td width="60" height="30" align="center" class="table_title" onMouseOver="changemain('0');" style="cursor:hand;"><a href="/cms/filter/common/latestlist.jsp" target="_blank" style="text-decoration:none" class="table_title"><span class="table_title">最新信息</span></a></td>
                    <td width="12">&nbsp;</td>
                    <td width="60" height="30" align="center" class="table_title" onMouseOver="changemain('1');" style="cursor:hand;"><a href="/cms/filter/common/privatelist.jsp" target="_blank" style="text-decoration:none" class="table_title"><span class="table_title">个人信息</span></a></td>
                   	<td width="18">&nbsp;</td>
                    <td width="71" height="30" align="center" class="table_title" onMouseOver="changemain('2');" style="cursor:hand;"><cms:position target="_blank" code="9" id="pos9" style="text-decoration:none" styleClass="table_title" type="linknoend" />
					<span class="table_title"><cms:position code="9" id="pos9" type="plain" /></span>
					</a></td>
                    <td width="6">&nbsp;</td>
                    <td width="71" height="30" align="center" class="table_title" onMouseOver="changemain('3');" style="cursor:hand;">
								<cms:position target="_blank" code="55" id="pos55" style="text-decoration:none" styleClass="table_title" type="linknoend" />
					<span class="table_title"><cms:position code="55" id="pos55" type="plain" /></span>
					</a>
							</td>
                </tr>
              </table>              </td>
              </tr>
            
            <tr id="messagemaintitle0" style="display:block" >
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <%
				messagecount=0;
					  %>
              	<cms:infolist name="pos8" perPageSize="6" titleLength="20" id="info81" type="allSite" infoType="public">
				<%
			     messagecount++;
				 %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24" nowrap>·<span class="function_title">[<%=department.length()>6?department.substring(0,6):department%>]</span><cms:info target="_blank" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title" height="24"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><span class="message_date"><cms:write name="info81" property="date" /></span></a></td>
                  </tr>
                </cms:infolist>
				<%
			 if(messagecount<6){
			 	for(int m=messagecount;m<6;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table></td>
            </tr>
        

            <tr id="messagemaintitle1" style="display:none" >
              <td>
              <IFRAME name="privateinfo" ID="privateinfo" width="308" FRAMEBORDER=0 scrolling="auto" SRC="/cms/filter/common/privateinfo.jsp"></IFRAME>			  
              </td>
            </tr>
            
            <tr id="messagemaintitle2" style="display:none" >
              <td>
              <iframe name="importantinfo" id="importantinfo" width="308" frameborder=0 scrolling="auto" src="/cms/filter/common/importantinfo.jsp"></iframe>
              </td>
            </tr>
            
            <tr id="messagemaintitle3" style="display:none" >
              <td>
 	              <iframe name="canyuecailiao" id="canyuecailiao" width="308" frameborder=0 scrolling="auto" src="/cms/filter/common/canyuecailiao.jsp"></iframe>
              </td>
            </tr>
        </table></td>
      </tr>

    </table>
      
      
      <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
       	  <tr>
       	    <td bgcolor="#FFFFFF">
              	<table border="0" cellpadding="0" cellspacing="0" width="100%">
           	  <tr>
                    	<td height="30" colspan="2" class="block_title"><cms:position  target="_blank" code="13" id="pos13" style="text-decoration:none" styleClass="table_title" type="linknoend"/>社长办公会纪要</td>
                        <td align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
               	  </tr>
                  <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				  <%
				messagecount=0;
					  %>
                  <cms:infolist name="pos13" id="info13" type="channelTree" perPageSize="3" titleLength="28">       
				  <%
			     messagecount++;
				 %>   
                    <tr>
           	  			<td width="9%" align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
           		      <td width="70%" bgcolor="#FFFFFF" class="message_title"><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
            		    <td width="21%" bgcolor="#FFFFFF" class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info13" property="date" /></a></td>
                    </tr>
                  </cms:infolist>
				  <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  
                    <tr>
           	  			<td width="9%" align="right" bgcolor="#FFFFFF">&nbsp;</td>
           		      <td width="70%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
            		    <td align="right" width="21%" valign="middle" bgcolor="#FFFFFF" class="message_title"><cms:position code="13" id="pos13" type="linknoend" style="text-decoration:none" target="_blank" styleClass="message_title"/>more</a><span class="message_title"> <img src="<%=request.getContextPath()%>/Style/images/arrow.gif" width="9" height="9"></span></td>
                    </tr>
            </table>              </td>
            </tr>
      </table>
      
      <!--总经理室-->
      <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        <tr>
          <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td height="30" colspan="2" class="block_title"><cms:position code="15" id="pos15" type="linknoend" style="text-decoration:none" target="_blank" styleClass="table_title"/> <cms:position code="15" id="pos15" type="plain"   /> </a></td>
                <td align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
              </tr>
              <TR>
                  	<TD  colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				   <%
				messagecount=0;
					  %>
             <cms:infolist name="pos15" id="info15" type="channelTree" perPageSize="3" titleLength="28">        
			 <%
			     messagecount++;
				 %> 
              <tr>
                <td width="9%" align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
                <td width="61%" bgcolor="#FFFFFF" class="message_title"><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                <td bgcolor="#FFFFFF" class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info15" property="date" /></a></td>
              </tr>
             </cms:infolist>
			 <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
              <tr>
                <td width="9%" align="right" bgcolor="#FFFFFF">&nbsp;</td>
                <td width="70%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                <td align="right" width="21%" valign="middle" bgcolor="#FFFFFF" class="message_title"><cms:position code="15" id="pos15" type="linknoend" target="_blank" style="text-decoration:none" styleClass="message_title" /> more </a><span class="message_title"><img src="<%=request.getContextPath()%>/Style/images/arrow.gif" width="9" height="9"></span></td>
              </tr>
          </table></td>
        </tr>
      </table>
    <!--总经理室-->

<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        <tr>
          <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td height="30" colspan="2" class="block_title"><cms:position code="14" id="pos14" type="linknoend" style="text-decoration:none" target="_blank" styleClass="table_title"/><cms:position code="14" id="pos14" type="plain"  />     </a>           </td>
                <td align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
              </tr>
              <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                </TR>
            <%
				messagecount=0;
					  %>
              <cms:infolist name="pos14" id="info14" type="channelTree" perPageSize="3" titleLength="28">  
			  <%
			     messagecount++;
				 %> 
              <tr>
                <td width="9%" align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
                <td width="61%" bgcolor="#FFFFFF" class="message_title"><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                <td bgcolor="#FFFFFF" class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info14" property="date" /></a></td>
              </tr>
              </cms:infolist>
           <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
              <tr>
                <td width="9%" align="right" bgcolor="#FFFFFF">&nbsp;</td>
                <td width="70%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                <td align="right" width="21%" valign="middle" bgcolor="#FFFFFF" class="message_title"><cms:position code="14" id="pos14" type="linknoend" target="_blank" style="text-decoration:none" styleClass="message_title"/> more </a><span class="message_title"><img src="<%=request.getContextPath()%>/Style/images/arrow.gif" width="9" height="9"></span></td>
              
              </tr>
              
              
              
              
          </table></td>
        </tr>
      </table>  
    
    
    
    
    
    
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        <tr>
          <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td height="30" colspan="2" class="block_title"><a href="http://10.102.1.40:81/HrSys/InfoManage/index.aspx?mid=InfoModule_000185" target="_blank" style="text-decoration:none">社发稿人签发人管理动态</a>           </td>
                <td align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
              </tr>
              <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                </TR>
            <%
				messagecount=0;
					  %>
              <cms:infolist name="pos69" id="infolist69" type="channelTree" perPageSize="3" titleLength="28" channelId="10530">  
			  <%
			     messagecount++;
				 %> 
              <tr>
                <td width="9%" align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
                <td width="61%" bgcolor="#FFFFFF" class="message_title"><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                <td bgcolor="#FFFFFF" class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="infolist69" property="date" /></a></td>
              </tr>
              </cms:infolist>
           <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
              <tr>
                <td width="9%" align="right" bgcolor="#FFFFFF">&nbsp;</td>
                <td width="70%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                <td align="right" width="21%" valign="middle" bgcolor="#FFFFFF" class="message_title"><a href="http://10.102.1.61/cms/cms/website/XHSZBS/znbm/layout2/layout2_8.jsp?channelId=10530&siteId=3" target="_blank" style="text-decoration:none"/> more </a><span class="message_title"><img src="<%=request.getContextPath()%>/Style/images/arrow.gif" width="9" height="9"></span></td>
              
              </tr>
              
              
              
              
          </table></td>
        </tr>
      </table>  
    
    
    
    
   <!-- 
    
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        <tr>
          <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td height="30" colspan="3" class="block_title"><a href="http://10.102.1.40:81/HrSys/InfoManage/index.aspx?mid=InfoModule_000185" target="_blank" style="text-decoration:none">社发稿人签发人管理动态</a>           </td>
                <td align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
              </tr>
              <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="4"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                </TR>
            <%
				messagecount=0;
					  %>
              
              
    <tr>
      <td width="9%" align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
      <td width="30%" bgcolor="#FFFFFF" class="message_title" >
<a href="/cms/cms/website/XHSZBS/znbm/layout2/layout2_1.jsp?channelId=10450&siteId=3" target="_blank" style="text-decoration:none">管理规定</a>
      </td>
      <td width="9%" align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
      <td width="50%" bgcolor="#FFFFFF" class="message_title" >
<a href="/cms/cms/website/XHSZBS/znbm/layout2/layout2_1.jsp?channelId=10470&siteId=3" target="_blank" style="text-decoration:none">备案人员调整通知及批复</a>
      </td>
     
 
  </tr>
        
     <tr>
      <td  align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
      <td  bgcolor="#FFFFFF" class="message_title" >
<a href="/cms/cms/website/XHSZBS/znbm/layout2/layout2_1.jsp?channelId=17&siteId=3" target="_blank" style="text-decoration:none">常读材料</a>
      </td>
     <td  align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
      <td  bgcolor="#FFFFFF" class="message_title" >
<a href="http://10.102.1.40:81/HrSys/InfoManage/index.aspx?mid=InfoModule_000185" target="_blank" style="text-decoration:none">备案人员信息查询</a>
      </td>
     
  </tr>   
        
        
     
        
        
        
         <tr>
      <td  align="right" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/icon_read.gif" width="18" height="14"></td>
      <td  bgcolor="#FFFFFF" class="message_title" >
<a href="http://10.102.1.61/oabase/servlet/ShowTopicServlet?boardId=1557&primeFlag=0" target="_blank" style="text-decoration:none">业务论坛</a>
      </td>
      <td></td>
     <td></td>
  </tr>     
           
           
     
              
              
              
         
              
              
              
              
          </table></td>
        </tr>
      </table>  
    
    
    
    
    -->
    
    
    
    <!--记者证-->
	<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" bgcolor="#FFFFFF" width="100%">
        <tr>
            <td >
            <table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF" >
            	<tr>
                	<td colspan="2" class="block_title">
                	
                	<cms:position code="43" id="pos43" type="linknoend" style="text-decoration:none" target="_blank" styleClass="table_title_red"/><cms:position code="43" id="pos43" type="plain"  />     </a>
                	</td>
                </tr>
                <TR>
              		<TD colspan="2" background="<%=request.getContextPath()%>/Style/images/mid_02.gif"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            	</TR>
                <cms:infolist name="pos43" perPageSize="3" titleLength="28" id="info43">        
				  <tr>
					<td width="74%" height="24" align="left" class="message_title">·<cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"></a></span></td>
				    <!--<td width="26%" align="left" class="message_date"><cms:write name="info43" property="date" /></td>-->
				    <td width="26%" align="left" class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info43" property="date" /></a></td>
				    
				  </tr>
        		</cms:infolist>
              <tr>
                
                <td width="79%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
                <td align="right" width="21%" valign="middle" bgcolor="#FFFFFF" class="message_title"><cms:position code="43" id="pos43" type="linknoend" target="_blank" style="text-decoration:none" styleClass="message_title"/> more </a><span class="message_title"><img src="<%=request.getContextPath()%>/Style/images/arrow.gif" width="9" height="9"></span></td>
              </tr>
            </table>            </td>
        </tr>
    </table>
	<!--记者证-->
  
    
    
    
    <!--单条信息项目-->
      <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="1" width="308" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        <tr>
          <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td width="54%" height="26" class="block_title">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td width="46%" align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
              </tr>
              <TR>
                  	<TD  colspan="2"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
             
             <!--
             <tr>
              <td height="30" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat">
		<cms:position code="53"  type="linknoend" styleClass="foot_message"  style="text-decoration:none" target="_blank" />
               	国内分社宽带电话簿
               	</a>
               	</td>
                <td align="left" valign="middle" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat">
                 <cms:position code="54"  type="linknoend" styleClass="foot_message"  style="text-decoration:none" target="_blank" />
               	国外分社电话簿
               	</a>
               	</td>
              </tr>
              --> 
                  
              <tr>
              	<cms:position code="70" id="pos70" type="linknoend" />
              	<cms:infolist name="pos70" id="info70" type="channelTree" perPageSize="1" titleLength="20">
                <td height="30" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat">
                	<cms:info styleClass="foot_message" style="text-decoration:none" target="_blank" /></td>
                </cms:infolist>
                <cms:position code="71" id="pos71" type="linknoend" />
                <cms:infolist name="pos71" id="info71" type="channelTree" perPageSize="1" titleLength="28">
                <td align="left" valign="middle" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat">
                <cms:info styleClass="foot_message" style="text-decoration:none" target="_blank" /></td>
                </cms:infolist>
              </tr>
              <!--
              <tr>
              	
                <td height="30" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat">
								  <cms:position code="85"  type="linknoend" styleClass="foot_message"  style="text-decoration:none" target="_blank" />
               	新华社简介
               	</a>
               	</td>
                <td align="left" valign="middle" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat">
                 <cms:position code="86"  type="linknoend" styleClass="foot_message"  style="text-decoration:none" target="_blank" />
               	新华社主要职责
               	</a>
               	</td>
                </tr>
              -->
              <tr>
              
                <!--<td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30">
 								<cms:position code="87"  type="linknoend" styleClass="foot_message"  style="text-decoration:none" target="_blank" />
 								新华社机构设置
 								</a>
               	</td>-->
             	 <td height="30" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat"><cms:position code="83" id="pos83" target="_blank" type="linknoend" style="text-decoration:none" styleClass="message_title"/>新华社宣传品</td>

                <cms:position code="72" id="pos72" type="linknoend" />
                <cms:infolist name="pos72" id="info72" type="channelTree" perPageSize="1" titleLength="28">
                <td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30">
                <cms:info styleClass="foot_message" style="text-decoration:none" target="_blank" /></td>
                </cms:infolist>
              </tr>
              <tr>
              	<cms:position code="73" id="pos73" type="linknoend" />
              	<cms:infolist name="pos73" id="info73" type="channelTree" perPageSize="1" titleLength="28">
                <td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30">
                <cms:info styleClass="foot_message" style="text-decoration:none" target="_blank" /></td>
                </cms:infolist>
                <!--<td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30"><cms:position code="74" id="pos74" type="linknoend" target="_blank" style="text-decoration:none" styleClass="foot_message" />新华社管理制度系统</a></td>-->
              </tr>
		
		<!--
              <tr>
				 <td height="30" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat"><cms:position code="82" id="pos82" target="_blank" type="linknoend" styleClass="message_title" style="text-decoration:none"/>新书信息</td>
                </tr>
                -->
          </table></td>
        </tr>
      </table>
    <!--单条信息项目-->    
    	
    <!--个人办公室-->
      
	<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"></td></tr>
    	</table>
      <table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
        <tr>
          <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td width="100%" height="26" class="block_title" colspan="3">个人办公室</td>
                
              </tr>
              <TR>
                  	<TD  colspan="3"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
              <tr>
                <td width="33%" height="30" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat"><a href="/oabase/servlet/ScheduleMainServlet" class="foot_message" style="text-decoration:none" target="_blank">日程安排</a></td>
                <td width="33%" align="left" valign="middle" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat"><a href="/oabase/servlet/ShowJournalServlet" class="foot_message" style="text-decoration:none" target="_blank">日记</a></td>
                <td width="33%" align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30"><a href="/oabase/servlet/ShowAddrbookRootFolderServlet" style="text-decoration:none" class="foot_message" target="_blank">个人通讯录</td>
              </tr>
              <tr>
                <td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30"><a href="/oabase/servlet/ShowMemoServlet" style="text-decoration:none" class="foot_message" target="_blank">备忘录</a></td>
                <td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30"><a href="/oabase/html/wannianli.htm" style="text-decoration:none" class="foot_message" target="_blank">万年历</a></td>
                <td align="left"  bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30">&nbsp;&nbsp;&nbsp;&nbsp;</td>
              </tr>
              <!--
              <tr>
                <td align="left" background="<%=request.getContextPath()%>/Style/images/left_01.jpg" bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30"><a href="/oabase/servlet/ShowAddrbookRootFolderServlet" style="text-decoration:none" class="foot_message" target="_blank">个人通讯录</td>
                <td align="left"  bgcolor="#FFFFFF" class="foot_message" style="background-repeat:no-repeat" height="30">&nbsp;&nbsp;&nbsp;&nbsp;</td>
              </tr>
              -->
          </table>
		  </td>
        </tr>
      </table>
<!--个人办公室-->
    
    
    
    <!--单条信息项目-->
	<!--人才信息-->
	<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" bgcolor="#FFFFFF" width="100%">
        <tr>
            <td >
            <table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF">
            	<tr>
                	<td colspan="2" class="block_title">
                	<cms:position code="42" id="pos42" type="linknoend" style="text-decoration:none" target="_blank" styleClass="table_title"/>人才流动信息</a>
                	</td>
                </tr>
                <TR>
              		<TD colspan="2" background="<%=request.getContextPath()%>/Style/images/mid_02.gif"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            	</TR>
                <cms:infolist channelId="70" perPageSize="2" titleLength="28" id="info111">        
				  <tr>
					<td width="74%" height="24" align="left" class="message_title">·<cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"></a></span></td>
				    <td width="26%" align="left" class="message_date"><cms:write name="info111" property="date" /></td>
				    
				  </tr>
        		</cms:infolist>
				<tr><td colspan="2">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
            	<tr>
                	<td height="39" background="<%=request.getContextPath()%>/Style/images/right_02.jpg" class="foot_message" style="background-repeat:no-repeat">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/oabase/mail/Mail_Main_Frame.jsp?next=hr" style="text-decoration:none" class="foot_message">人才流动信箱</a>&nbsp;</td>
                
                	<td height="39" background="<%=request.getContextPath()%>/Style/images/right_down.jpg" class="foot_message" style="background-repeat:no-repeat">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/cms/website/RSJ/znbm/layout2/layout2_1.jsp?channelId=73&siteId=13" style="text-decoration:none" class="foot_message">登记表下载</a>　&nbsp;</td>
                </tr>
				</table>
				</td>
			</tr>
            </table>            </td>
        </tr>
    </table>
	<!--人才信息-->
    </td>
    <td width="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" width="5"></td>
    <td width="98%" valign="top" >
   <table border="0" cellpadding="0" cellspacing="1" width="448" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td height="50" colspan="11" align="left" valign="top" class="red-18-b"><img src="<%=request.getContextPath()%>/images/zzg.jpg" width="444" height="80" style="cursor:hand" onclick="javascript:window.open('<%=request.getContextPath()%>/cms/website/XHSZBS/znbm/gwf.jsp?siteId=3')"></td>
            </tr>
        </table></td>
      </tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table> 
   

<!--值班简报和交接班日记-->
    
    <table border="0" cellpadding="0" cellspacing="0" width="448">
    	<tr>
        	<td><IFRAME name="topchannel" ID="topchannel" width="448" onload="this.height=topchannel.document.body.scrollHeight+5" FRAMEBORDER=0 scrolling="auto" SRC="/cms/filter/common/topchannel11.jsp"></IFRAME></td>
        </tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="448">
    	<tr>
        	<td>
            <!--值班简报和交接班日记-->

    <table border="0" cellpadding="0" cellspacing="0" width="440" align="center">
    	<tr>
        	<td><table border="0" cellpadding="0" cellspacing="1" width="220" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
              <tr>
                <td bgcolor="#FFFFFF">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td height="26" colspan="2" class="block_title">
					  <cms:position target="_blank" code="2" id="pos2" style="text-decoration:none" type="linknoend" />
					  <span class="table_title"><cms:position code="2" id="pos2" type="plain" /></span>
					  </a>
					  </td>
                      <td width="31%" align="right" valign="middle" class="block_title_blank"><cms:position code="2" id="pos2" type="linknoend" target="_blank" /><img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0"></td>
                    </tr>
                    <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				  <%
					  messagecount=0;
					  %>
                   <cms:infolist name="pos2" id="info3" type="channelTree" perPageSize="3" titleLength="20">
				   <%
					  messagecount++;
					  %>
                    <tr>
                      <td colspan="2" align="left" class="message_title" height="24" nowrap="nowrap">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
                      <td class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info3" property="date" /></a></td>
                    </tr>
                  </cms:infolist>                 
                 <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
         <%
				}
			 }
			 %>
                    
                    
                </table></td>
              </tr>
            </table></td>
            <td width="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" width="5"></td>
            <td><table border="0" cellpadding="0" cellspacing="1" width="222" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
              <tr>
                <td bgcolor="#FFFFFF">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td height="26" colspan="2" class="block_title">
					  <cms:position target="_blank" code="4" id="pos4" style="text-decoration:none" type="linknoend" />
					  <span class="table_title"><cms:position code="4" id="pos4" type="plain" /></span>
					  </a>
					  </td>
                      <td width="31%" align="right" valign="middle" class="block_title_blank"><cms:position code="4" id="pos4" type="linknoend" target="_blank"/><img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0" ></td>
                    </tr>
                    <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				  <%
					  messagecount=0;
					  %>
                  <cms:infolist name="pos4" id="info4" type="channelTree" perPageSize="3" titleLength="20">
				  <%
					  messagecount++;
					  %>
                    <tr>
                      <td colspan="2" align="left" class="message_title" height="24" nowrap="nowrap">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
                      <td class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info4" property="date" /></a></td>
                    </tr>
                  </cms:infolist> 
                    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
         <%
				}
			 }
			 %>
                    
                </table></td>
              </tr>
            </table></td>
        </tr>
    </table>
    <!--值班简报和交接班日记-->
            </td>
        </tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="448">
    	<tr>
        	<td>
            <!--值班简报和交接班日记-->

    <table border="0" cellpadding="0" cellspacing="0" width="440" align="center">
    	<tr>
        	<td><table border="0" cellpadding="0" cellspacing="1" width="220" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
              <tr>
                <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td height="26" colspan="2" class="block_title">
					  <cms:position target="_blank" code="5" id="pos5" type="linknoend" style="text-decoration:none" />
					  <span class="table_title"><cms:position code="5" id="pos5" type="plain" /></span>
					  </a>
					  </td>
                      <td width="31%" align="right" valign="middle" class="block_title_blank"><cms:position code="5" id="pos5" type="linknoend" target="_blank" /><img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0" ></td>
                    </tr>
                    <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				   <%
					  messagecount=0;
					  %>
                  <cms:infolist name="pos5" id="info5" type="channelTree" perPageSize="3" titleLength="20">
				  <%
					  messagecount++;
					  %>
                    <tr>
                      <td colspan="2" align="left" class="message_title" height="24" nowrap="nowrap">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
                      <td class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info5" property="date" /></a></td>
                    </tr>
                  </cms:infolist> 
                    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
         <%
				}
			 }
			 %>
                    
                </table></td>
              </tr>
            </table></td>
            <td width="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" width="5"></td>
            <td><table border="0" cellpadding="0" cellspacing="1" width="222" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
              <tr>
                <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td height="26" colspan="2" class="block_title">
					  <cms:position target="_blank" code="6" id="pos6" style="text-decoration:none" type="linknoend" />
					  <span class="table_title"><cms:position code="6" id="pos6" type="plain" /></span>
					  </a>
					  </td>
                      <td width="31%" align="right" valign="middle" class="block_title_blank"><cms:position code="6" id="pos6" type="linknoend" target="_blank" /><img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0" ></td>
                    </tr>
                    <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				  <%
					  messagecount=0;
					  %>
                  <cms:infolist name="pos6" id="info6" type="channelTree" perPageSize="3" titleLength="20">
				   <%
					  messagecount++;
					  %>
                    <tr>
                      <td colspan="2" align="left" class="message_title" height="24" nowrap="nowrap">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
                      <td class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info6" property="date" /></a></td>
                    </tr>
                  </cms:infolist> 
                     <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
         <%
				}
			 }
			 %>
                    
                </table></td>
              </tr>
            </table></td>
        </tr>
    </table>
    <!--值班简报和交接班日记-->
            </td>
        </tr>
    </table>
    
    <!--职能部门-->
	
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
	
    <table border="0" cellpadding="0" cellspacing="1" width="448" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<td width="100%">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_light_01" height="24" onMouseOver="_delay('_changetitle(zhineng,14,1)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng1" >
					  <cms:site siteId="1" target="_blank" style="text-decoration:none" staticUrl="0"/>
					  </td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,2)')" onMouseOut="doit=false;" id="zhineng2" style="cursor:hand"><cms:site siteId="3" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,3)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng3"><cms:site siteId="11" target="_blank" style="text-decoration:none" staticUrl="0"/></td>        
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,4)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng4"><cms:site siteId="12" target="_blank" style="text-decoration:none" staticUrl="0"/></td>              
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,5)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng5"><cms:site siteId="13" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,6)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng6"><cms:site siteId="14" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,7)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng7"><cms:site siteId="15" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      
                      <td class="title_back_buttom">&nbsp;</td>
                    </tr>
                  </table>
                  
                  
                  <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="85" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_02" onMouseOver="_delay('_changetitle(zhineng,14,8)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng8"><cms:site siteId="16" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom"  bgcolor="#FFFFFF" class="title_dark_01" height="24" onMouseOver="_delay('_changetitle(zhineng,14,9)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng9"><cms:site target="_blank" siteId="18" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,10)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng10"><cms:site siteId="19" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,11)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng11"><cms:site siteId="40" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="105" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_04" onMouseOver="_delay('_changetitle(zhineng,14,12)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng12"><cms:site siteId="28" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(zhineng,14,13)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng13"><cms:site siteId="30" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      
                      <td width="84" align="center" valign="bottom"  bgcolor="#FFFFFF" class="title_dark_02" height="24" onMouseOver="_delay('_changetitle(zhineng,14,14)')" onMouseOut="doit=false;" style="cursor:hand" id="zhineng14"><cms:site siteId="32" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="52" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="52" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table> 
                
                </td>
            </tr>            
            <TR>
              <TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="7"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            </TR>
            <tr>
              <td colspan="6">
              <jsp:include page= "/common/indexcontent.jsp?poscode=16&titlename=zhineng&titlenumber=1&perpagecount=3&showstyle=block" />
	      <jsp:include page= "/common/indexcontent.jsp?poscode=17&titlename=zhineng&titlenumber=2&perpagecount=3&showstyle=none" />
	      <jsp:include page= "/common/indexcontent.jsp?poscode=18&titlename=zhineng&titlenumber=3&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=19&titlename=zhineng&titlenumber=4&perpagecount=3&showstyle=none" />
	      <jsp:include page= "/common/indexcontent.jsp?poscode=20&titlename=zhineng&titlenumber=5&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=21&titlename=zhineng&titlenumber=6&perpagecount=3&showstyle=none" />
  	      <jsp:include page= "/common/indexcontent.jsp?poscode=22&titlename=zhineng&titlenumber=7&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=23&titlename=zhineng&titlenumber=8&perpagecount=3&showstyle=none" />
              <!--<jsp:include page= "/common/indexcontent.jsp?poscode=24&titlename=zhineng&titlenumber=9&perpagecount=3&showstyle=none" /> -->
              <jsp:include page= "/common/indexcontent.jsp?poscode=25&titlename=zhineng&titlenumber=9&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=26&titlename=zhineng&titlenumber=10&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=27&titlename=zhineng&titlenumber=11&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=28&titlename=zhineng&titlenumber=12&perpagecount=3&showstyle=none" />
              <jsp:include page= "/common/indexcontent.jsp?poscode=91&titlename=zhineng&titlenumber=13&perpagecount=3&showstyle=none" />
              
              
	      <jsp:include page= "/common/indexcontent.jsp?poscode=94&titlename=zhineng&titlenumber=14&perpagecount=3&showstyle=none" />
              
             </td>
            </tr>
        </table>
        </td>
      </tr>
    </table>
    <!--职能部门-->
    
    <!--总社采编-->
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table> 
    <table border="0" cellpadding="0" cellspacing="1" width="448" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<td width="100%">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_light_01" height="24" onMouseOver="_delay('_changetitle(caibian,9,1)')" onMouseOut="doit=false;" id="caibian1"><cms:site siteId="4" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(caibian,9,2)')" onMouseOut="doit=false;" id="caibian2"><cms:site siteId="5" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(caibian,9,3)')" onMouseOut="doit=false;" id="caibian3"><cms:site siteId="6" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(caibian,9,4)')" onMouseOut="doit=false;" id="caibian4"><cms:site siteId="7" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(caibian,9,5)')" onMouseOut="doit=false;" id="caibian5"><cms:site siteId="8" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(caibian,9,6)')" onMouseOut="doit=false;" id="caibian6"><cms:site siteId="9" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(caibian,9,7)')" onMouseOut="doit=false;" id="caibian7"><cms:site siteId="29" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" height="24" onMouseOver="_delay('_changetitle(caibian,9,8)')" onMouseOut="doit=false;" id="caibian8"><cms:site target="_blank" siteId="65" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="87" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_02" onMouseOver="_delay('_changetitle(caibian,9,9)')" onMouseOut="doit=false;" id="caibian9"><cms:site siteId="41" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom"></td>
                      <td class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>
				                </td>
            </tr>            
            <TR>
              <TD colspan="7"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            </TR>
            <tr>
              <td colspan="6">
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_1" style="display:block">
 							<cms:position code="29" id="pos29" type="none" target="_blank"/>
							<%
					  messagecount=0;
					  %>
 							<cms:infolist name="pos29" id="info29" type="sameSite" perPageSize="3" titleLength="30">
							<%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info29" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_2" style="display:none">
              <cms:position code="30" id="pos30" type="none" target="_blank"/>
			  <%
					  messagecount=0;
					  %>
              <cms:infolist name="pos30" id="info30" type="sameSite" perPageSize="3" titleLength="30">
			  <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info30" property="date" /></a></span></td>
                  </tr>
               </cms:infolist>
			   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_3" style="display:none">
              <cms:position code="31" id="pos31" type="none" target="_blank"/>
			  <%
					  messagecount=0;
					  %>
              <cms:infolist name="pos31" id="info31" type="sameSite" perPageSize="3" titleLength="30">
			  <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info31" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_4" style="display:none">
               <cms:position code="32" id="pos32" type="none" target="_blank"/>
			    <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos32" id="info32" type="sameSite" perPageSize="3" titleLength="30">
			    <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info32" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_5" style="display:none">
               <cms:position code="33" id="pos33" type="none" target="_blank"/>
			    <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos33" id="info33" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info33" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_6" style="display:none">
               <cms:position code="34" id="pos34" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos34" id="info34" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info34" property="date" /></a></span></td>
                  </tr>
               </cms:infolist>
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_7" style="display:none">
               <cms:position code="35" id="pos35" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos35" id="info35" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info35" property="date" /></a></span></td>
                  </tr>
               </cms:infolist>
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>  
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>              
			  
			  
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_8" style="display:none">
               <cms:position code="95" id="pos95" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos95" id="info95" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info95" property="date" /></a></span></td>
                  </tr>
               </cms:infolist>
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>  
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" id="caibian_block_9" style="display:none">
               <cms:position code="96" id="pos96" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos96" id="info96" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info96" property="date" /></a></span></td>
                  </tr>
               </cms:infolist>
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>  
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  
			  
			  </td>
            </tr>
        </table></td>
      </tr>
    </table>
    <!--总社采编-->    
    
    <!--直属事业-->
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="1" width="448" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<td width="100%">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="100" align="center" valign="bottom" class="title_light_04" bgcolor="#FFFFFF" height="24" onMouseOver="_delay('_changetitle(shiye,22,1)')" onMouseOut="doit=false;" id="shiye1" style="cursor:hand;"><a href="/cms//cms/website/tzkggs/zssy/index.jsp?siteId=408" target="_blank" style="text-decoration:none">新华社投资控股</td>
                      <td width="85" align="center" valign="bottom" class="title_dark_02" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,2)')" onMouseOut="doit=false;" id="shiye2" style="cursor:hand;"><cms:site siteId="34" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(shiye,22,3)')" onMouseOut="doit=false;" id="shiye3" style="cursor:hand;"><cms:site siteId="35" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(shiye,22,4)')" onMouseOut="doit=false;" id="shiye4" style="cursor:hand;"><cms:site siteId="36" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(shiye,22,5)')" onMouseOut="doit=false;" id="shiye5" style="cursor:hand;"><cms:site siteId="37" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(shiye,22,6)')" onMouseOut="doit=false;" id="shiye6" style="cursor:hand;"><cms:site siteId="38" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
                    </table>
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="85" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_02" onMouseOver="_delay('_changetitle(shiye,22,7)')" onMouseOut="doit=false;" id="shiye7" style="cursor:hand;"><cms:site siteId="39" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" height="24"  onMouseOver="_delay('_changetitle(shiye,22,8)')" onMouseOut="doit=false;" id="shiye8" style="cursor:hand;"><cms:site siteId="61" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF"  onMouseOver="_delay('_changetitle(shiye,22,9)')" onMouseOut="doit=false;" id="shiye9" style="cursor:hand;"><cms:site siteId="62" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,10)')" onMouseOut="doit=false;" id="shiye10" style="cursor:hand;"><cms:site siteId="63" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,11)')" onMouseOut="doit=false;" id="shiye11" style="cursor:hand;"><cms:site siteId="64" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,12)')" onMouseOut="doit=false;" id="shiye12" style="cursor:hand;"><cms:site siteId="42" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>                
				
				
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                     <!-- <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,16,13)')" onMouseOut="doit=false;" id="shiye13" style="cursor:hand;"><cms:site siteId="46" style="text-decoration:none" staticUrl="0"/></td>-->
                      <td width="84" align="center" valign="bottom" class="title_dark_02" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,13)')" onMouseOut="doit=false;" id="shiye13" style="cursor:hand;"><cms:site siteId="43" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" height="24"  onMouseOver="_delay('_changetitle(shiye,22,14)')" onMouseOut="doit=false;" id="shiye14" style="cursor:hand;"><cms:site siteId="44" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF"  onMouseOver="_delay('_changetitle(shiye,22,15)')" onMouseOut="doit=false;" id="shiye15" style="cursor:hand;"><cms:site siteId="100" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,16)')" onMouseOut="doit=false;" id="shiye16" style="cursor:hand;"><cms:site siteId="101" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,17)')" onMouseOut="doit=false;" id="shiye17" style="cursor:hand;"><cms:site siteId="167" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <!--新华08-->
                      <td width="105" align="center" valign="bottom" class="title_dark_04" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,18)')" onMouseOut="doit=false;" id="shiye18" style="cursor:hand;"><cms:site siteId="246" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <!--图片中心-->

                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>
				
				
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                                                                                                                                                               
                      <td width="62" align="center" valign="bottom" class="title_dark_04" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(shiye,22,19)')" onMouseOut="doit=false;" id="shiye19" style="cursor:hand;"><a href="/cms//cms/website/tzkggs/zssy/index.jsp?siteId=267" target="_blank" style="text-decoration:none">图片中心</td>
                     <td width="85" align="center" valign="bottom" class="title_dark_02" bgcolor="#FFFFFF" height="24"  onMouseOver="_delay('_changetitle(shiye,22,20)')" onMouseOut="doit=false;" id="shiye20" style="cursor:hand;"><cms:site siteId="366" target="_blank" style="text-decoration:none" staticUrl="0"/></td>                                 
                     <td width="100" align="center" valign="bottom" class="title_dark_05" bgcolor="#FFFFFF" height="24"  onMouseOver="_delay('_changetitle(shiye,22,21)')" onMouseOut="doit=false;" id="shiye21" style="cursor:hand;"><a href="/cms//cms/website/CNC/zsqy/index.jsp?siteId=386" target="_blank" style="text-decoration:none">新华新闻电视网</a></td>  
          <td width="100" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_05" height="24"  onMouseOver="_delay('_changetitle(shiye,22,22)')" onMouseOut="doit=false;" style="cursor:hand" id="shiye22"><cms:site siteId="33" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                                 <td width="62" align="center" valign="bottom" class="title_back_buttom"></td>
                      <!--td width="62" align="center" valign="bottom" class="title_back_buttom"></td-->
                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>	
				
				
				</td>
            </tr>            
            <TR>
              <TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="7"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            </TR>
            <tr>
              <td colspan="6">
              <jsp:include page= "/common/indexcontent.jsp?poscode=67&titlename=shiye&titlenumber=1&perpagecount=3&showstyle=block" />
			  			<jsp:include page= "/common/indexcontent.jsp?poscode=36&titlename=shiye&titlenumber=2&perpagecount=3&showstyle=none" />
			  			<jsp:include page= "/common/indexcontent.jsp?poscode=37&titlename=shiye&titlenumber=3&perpagecount=3&showstyle=none" />
			  			<jsp:include page= "/common/indexcontent.jsp?poscode=38&titlename=shiye&titlenumber=4&perpagecount=3&showstyle=none" />  
							<jsp:include page= "/common/indexcontent.jsp?poscode=39&titlename=shiye&titlenumber=5&perpagecount=3&showstyle=none" />
							<jsp:include page= "/common/indexcontent.jsp?poscode=40&titlename=shiye&titlenumber=6&perpagecount=3&showstyle=none" />
							<jsp:include page= "/common/indexcontent.jsp?poscode=41&titlename=shiye&titlenumber=7&perpagecount=3&showstyle=none" />           
							<jsp:include page= "/common/indexcontent.jsp?poscode=42&titlename=shiye&titlenumber=8&perpagecount=3&showstyle=none" />
            	<jsp:include page= "/common/indexcontent.jsp?poscode=43&titlename=shiye&titlenumber=9&perpagecount=3&showstyle=none" /> 
							<jsp:include page= "/common/indexcontent.jsp?poscode=44&titlename=shiye&titlenumber=10&perpagecount=3&showstyle=none" />
            	<jsp:include page= "/common/indexcontent.jsp?poscode=45&titlename=shiye&titlenumber=11&perpagecount=3&showstyle=none" /> 
							<jsp:include page= "/common/indexcontent.jsp?poscode=46&titlename=shiye&titlenumber=12&perpagecount=3&showstyle=none" />
							<jsp:include page= "/common/indexcontent.jsp?poscode=100&titlename=shiye&titlenumber=13&perpagecount=3&showstyle=none" />              			
							<!--<jsp:include page= "/common/indexcontent.jsp?poscode=101&titlename=shiye&titlenumber=13&perpagecount=3&showstyle=none" />  -->            
							<jsp:include page= "/common/indexcontent.jsp?poscode=102&titlename=shiye&titlenumber=14&perpagecount=3&showstyle=none" />              
							<jsp:include page= "/common/indexcontent.jsp?poscode=97&titlename=shiye&titlenumber=15&perpagecount=3&showstyle=none" />              
							<jsp:include page= "/common/indexcontent.jsp?poscode=98&titlename=shiye&titlenumber=16&perpagecount=3&showstyle=none" />              
							<jsp:include page= "/common/indexcontent.jsp?poscode=99&titlename=shiye&titlenumber=17&perpagecount=3&showstyle=none" />  
							<jsp:include page= "/common/indexcontent.jsp?poscode=90&titlename=shiye&titlenumber=18&perpagecount=3&showstyle=none" />  
							<jsp:include page= "/common/indexcontent.jsp?poscode=89&titlename=shiye&titlenumber=19&perpagecount=3&showstyle=none" />                        
			  			<jsp:include page= "/common/indexcontent.jsp?poscode=110&titlename=shiye&titlenumber=20&perpagecount=3&showstyle=none" />  
			  			<jsp:include page= "/common/indexcontent.jsp?poscode=116&titlename=shiye&titlenumber=21&perpagecount=3&showstyle=none" /> 
			  			<jsp:include page= "/common/indexcontent.jsp?poscode=92&titlename=shiye&titlenumber=22&perpagecount=3&showstyle=none" />
              
			  			</td>
            </tr>
        </table>
		</td>
      </tr>
    </table>
    <!--直属事业-->
    <!--国内分社-->
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table> 
    <table border="0" cellpadding="0" cellspacing="1" width="448" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<td width="100%">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="62" align="center" valign="bottom" class="title_light_01" height="24" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(guonei,6,1)')" onMouseOut="doit=false;" id="guonei1" style="cursor:hand;">华北地区</td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(guonei,6,2)')" onMouseOut="doit=false;" id="guonei2" style="cursor:hand;">东北地区</td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(guonei,6,3)')" onMouseOut="doit=false;" id="guonei3" style="cursor:hand;">华东地区</td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(guonei,6,4)')" onMouseOut="doit=false;" id="guonei4" style="cursor:hand;">中南地区</td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(guonei,6,5)')" onMouseOut="doit=false;" id="guonei5" style="cursor:hand;">西南地区</td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" bgcolor="#FFFFFF" onMouseOver="_delay('_changetitle(guonei,6,6)')" onMouseOut="doit=false;" id="guonei6" style="cursor:hand;">西北地区</td>
                      <td align="left" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
				</table>                </td>
            </tr>
            <TR id="guonei_block_1" style="display:block">
              <TD colspan="7" class="second_title" height="24">&nbsp;&nbsp;&nbsp;&nbsp;
              <cms:site siteId="80" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> |&nbsp;<cms:site siteId="47" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="81" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="82" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="48" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/>              </TD>
            </TR>
            <TR id="guonei_block_2" style="display:none">
              <TD colspan="7" class="second_title" height="24">&nbsp;&nbsp;&nbsp;&nbsp;
             &nbsp;<cms:site siteId="49" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="50" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="51" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/>              </TD>
            </TR>
            <TR id="guonei_block_3" style="display:none">
              <TD colspan="7" class="second_title" height="24">&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;<cms:site siteId="83" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="84" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="85" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="86" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="52" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="87" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/>              </TD>
            </TR>  
            <TR id="guonei_block_4" style="display:none">
              <TD colspan="7" class="second_title" height="24">
              &nbsp;<cms:site siteId="88" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | <cms:site siteId="53" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> |<cms:site siteId="89" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | <cms:site siteId="90" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | <cms:site siteId="91" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | <cms:site siteId="92" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | <cms:site siteId="54" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/>              </TD>
            </TR>
            <TR id="guonei_block_5" style="display:none">
              <TD colspan="7" class="second_title" height="24">&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;<cms:site siteId="55" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="56" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="93" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="94" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="95" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/>              </TD>
            </TR>
            <TR id="guonei_block_6" style="display:none">
              <TD colspan="7" class="second_title" height="24">&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;<cms:site siteId="57" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="96" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="58" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="97" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/> | &nbsp;<cms:site siteId="59" target="_blank" styleClass="second_title" style="text-decoration:none" staticUrl="0"/></TD>
            </TR>       
            <TR>
              <TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="7"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            </TR>
            <tr>
              <td colspan="6">
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="display:block" id="guonei_cblock_1">
               <cms:position code="47" id="pos47" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist id="info47" type="siteGroup" siteGroupName="华北地区" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=department%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info47" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
                   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			 <table width="100%" border="0" cellpadding="0" cellspacing="0" style="display:none"  id="guonei_cblock_2">
               <cms:position code="48" id="pos48" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist id="info48" type="siteGroup" siteGroupName="东北地区" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=department%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info48" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
                   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="display:none"  id="guonei_cblock_3">
               <cms:position code="49" id="pos49" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist id="info49" type="siteGroup" siteGroupName="华东地区" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=department%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info49" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
                   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="display:none" id="guonei_cblock_4">
               <cms:position code="50" id="pos50" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist id="info50" type="siteGroup" siteGroupName="中南地区" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=department%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info50" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
                   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="display:none" id="guonei_cblock_5">
               <cms:position code="51" id="pos51" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist id="info51" type="siteGroup" siteGroupName="西南地区" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=department%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info51" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
                   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="display:none" id="guonei_cblock_6">
               <cms:position code="52" id="pos52" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist id="info52" type="siteGroup" siteGroupName="西北地区" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=department%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info52" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
                   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
			  </td>
            </tr>
        </table></td>
      </tr>
    </table>
    <!--国内分社-->
    
    <!--驻外分社-->
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="5"></td></tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="1" width="448" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF" colspan="2">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<td width="100%">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td width="62" align="center" valign="bottom" class="title_light_01" bgcolor="#FFFFFF" height="24" onMouseOver="_delay('_changetitle(guoji,7,1)')" onMouseOut="doit=false;" id="guoji1" style="cursor:hand;"><cms:site siteId="66" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(guoji,7,2)')" onMouseOut="doit=false;" id="guoji2" style="cursor:hand;"><cms:site siteId="67" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(guoji,7,3)')" onMouseOut="doit=false;" id="guoji3" style="cursor:hand;"><cms:site siteId="68" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(guoji,7,6)')" onMouseOut="doit=false;" id="guoji6" style="cursor:hand;"><cms:site siteId="71" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" class="title_dark_01" height="24"  onMouseOver="_delay('_changetitle(guoji,7,5)')" onMouseOut="doit=false;" id="guoji5" style="cursor:hand;"><cms:site siteId="70" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(guoji,7,4)')" onMouseOut="doit=false;" id="guoji4" style="cursor:hand;"><cms:site siteId="69" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <!--<td width="62" align="center" valign="bottom" class="title_dark_01" height="24"  onMouseOver="_delay('_changetitle(guoji,7,5)')" onMouseOut="doit=false;" id="guoji5" style="cursor:hand;"><cms:site siteId="70" target="_blank" style="text-decoration:none" staticUrl="0"/></td>-->
                      <!--<td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(guoji,7,6)')" onMouseOut="doit=false;" id="guoji6" style="cursor:hand;"><cms:site siteId="71" target="_blank" style="text-decoration:none" staticUrl="0"/></td>-->
                      <td width="62" align="center" valign="bottom" bgcolor="#FFFFFF" class="title_dark_01" onMouseOver="_delay('_changetitle(guoji,7,7)')" onMouseOut="doit=false;" id="guoji7" style="cursor:hand;"><cms:site siteId="72" target="_blank" style="text-decoration:none" staticUrl="0"/></td>
                      <td align="center" valign="bottom" bgcolor="#FFFFFF" class="title_back_buttom">&nbsp;</td>
                    </tr>
                    </table>                    </td>
        	</tr>            
            <TR>
              <TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="7"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            </TR>
            <tr>
              <td colspan="6">
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_1" style="display:block">
               <cms:position code="103" id="pos103" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos103" id="info103" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info103" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_2" style="display:none">
               <cms:position code="104" id="pos104" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos104" id="info104" type="sameSite" perPageSize="3" titleLength="30">
			    <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info104" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_3" style="display:none">
               <cms:position code="105" id="pos105" type="none" target="_blank"/>
			    <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos105" id="info105" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info105" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_4" style="display:none">
               <cms:position code="106" id="pos106" type="none" target="_blank"/>
			    <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos106" id="info106" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info106" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_5" style="display:none">
               <cms:position code="107" id="pos107" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos107" id="info107" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info107" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			    <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_6" style="display:none">
               <cms:position code="108" id="pos108" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos108" id="info108" type="sameSite" perPageSize="3" titleLength="30">
			   <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info108" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="guoji_block_7" style="display:none">
               <cms:position code="109" id="pos109" type="none" target="_blank"/>
			   <%
					  messagecount=0;
					  %>
               <cms:infolist name="pos109" id="info109" type="sameSite" perPageSize="3" titleLength="30">
			    <%
					  messagecount++;
					  %>
                  <tr>
                    <td width="79%"  align="left" class="message_title" height="24">·<span class="red-12">[<%=channelName%>]</span><cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                    <td width="21%"  align="left" class="message_title"><span class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info109" property="date" /></a></span></td>
                  </tr>
               </cms:infolist> 
			   <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %> 
                  <tr>
                    <td  height="11" colspan="2"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" height="11"></td>
                  </tr>
              </table>              </td>
            </tr>
        </table></td>
      </tr>
    </table>
    <!--驻外分社-->    </td>
    <td width="5"><img src="<%=request.getContextPath()%>/Style/images/kongbai.jpg" width="5"></td>
    <td valign="top" width="218" class="right_bgcolor">
	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td colspan="2" class="block_title" height="24">提示栏</td>
              <td width="62%" align="right" valign="bottom" class="block_title_blank">&nbsp;</td>
            </tr>
            <TR>
               	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
            </TR>
            <tr>
            	<td colspan="6" class="message_title">
                <IFRAME name="worktodoframe" ID="worktodoframe" width="98%" FRAMEBORDER=0 scrolling="auto" SRC="/oabase/servlet/WorkTodoListServlet"></IFRAME>
                </td>
            </tr>
            <tr>
              <td width="9%" align="right" bgcolor="#FFFFFF">&nbsp;</td>
              <td width="29%" bgcolor="#FFFFFF" class="message_title">&nbsp;</td>
              <td align="right" valign="middle" bgcolor="#FFFFFF" class="message_title"><a href="/oabase/servlet/AllIntendWorkServlet" style="text-decoration:none" class="message_title" target="_blank">快速整理提示栏</a><span class="message_title"></span></td>
            </tr>
        </table></td>
      </tr>
	  <tr><td height="5"></td></tr>
    </table>
    
      <!-- <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
           <tr>
              <td height="50" colspan="11" align="left" valign="top" class="red-18-b"><img src="<%=request.getContextPath()%>/images/cxzy.gif" width="216" height="39" style="cursor:hand" onclick="javascript:window.open('<cms:position code="12" type="url"/>')"></td>
            </tr>
            <!-- <tr>
              <td height="50" colspan="11" align="left" valign="top" class="red-18-b">
              <a href="http://10.102.1.61/cms//cms/website/JGDW/znbm/indexvoteMain.jsp?siteId=18">
			<img src="<%=request.getContextPath()%>/images/xrqn.jpg" width="216" height="25" border="0"></a>
              </td>
            </tr>
        </table></td>
      </tr>
    </table>-->
    
    
        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
           <tr>
              <td height="50" colspan="11" align="left" valign="top" class="red-18-b"><img src="http://10.102.1.61/cms//cms/website/JGDW/znbm/images/dqhdsj.jpg" width="216" height="54" style="cursor:hand" onclick="javascript:window.open('<cms:position code="58" type="url"/>')"></td>
            </tr>
        </table></td>
      </tr>
    </table>
<!--增加建社80周年筹办工作简报栏目>
<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="74%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="115" id="pos115" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="115" id="pos115" type="plain" target="_blank"/></span>
		</a>
		</td>
        <td width="26%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="115" id="pos115" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
      	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
      </TR>
		<%
		messagecount=0;
		%>
        <cms:infolist name="pos115" id="info115" perPageSize="2" titleLength="30">
		<%
		messagecount++;
		%>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="2" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>
<!--增加建社80周年筹办工作简报结束-->


      
    
    
    
    <table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5">
        <IFRAME name="functionframe" ID="functionframe" width="218" FRAMEBORDER=0 scrolling="auto" SRC="/common/function.jsp"></IFRAME>
        </td></tr>
    </table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr><td height="2"></td></tr>
    	<tr>
    	  <td height="26" align="center">
			<cms:position target="_blank" code="60" id="pos60" type="linknoend" style="text-decoration:none" />
			<img src="images/qjb.jpg" width="216" height="26" border="0"></a></td>
    	</tr>
		<tr><td height="3"></td></tr>
		
		
			<tr>
        <td height="30" colspan="11" align="left" valign="top" class="red-18-b">
        	<img src="<%=request.getContextPath()%>/images/cxzy.gif" width="216" height="30" style="cursor:hand" onclick="javascript:window.open('<cms:position code="12" type="url"/>')"></td>
      </tr>
		
    	<!--<tr>
    	  <td height="26" align="center">
			<a href="/cms/cms/website/JGDW/znbm/layout2/layout2_6.jsp?channelId=92&siteId=18">
			<img src="images/dwgzjb.jpg" width="216" height="26" border="0"></a></td>
    	</tr>-->
    	<tr><td height="3"></td></tr>



      <tr>
    	  <td height="26" align="center">
			<a href="/cms/cms/website/XWYJS/zssy/layout2/layout2_1.jsp?channelId=8370&siteId=39">
			<img src="images/xwyw1.bmp" width="216" height="25" border="0"></a></td>
    	</tr>

    	<tr><td height="3"></td></tr>


		<tr>
			<td height="26" valign="middle">
			<cms:position target="_blank" code="119" type="linknoend" />
			<img src="images/title_ncp.jpg" width="216" height="25" border="0"></a></td>
		</tr>
    	<tr><td height="3"></td></tr>
		<tr>
			<td height="26" valign="middle">
			<cms:position target="_blank" code="120" type="linknoend" />
			<img src="images/title_ckyw.jpg" width="216" height="25" border="0"></a></td>
		</tr>
    	<tr><td height="3"></td></tr>
			<tr>
    	  <td height="30" align="center">
			<a href="/oabase/mail/Mail_Main_Frame.jsp?next=dev" target="_blank">
			<img src="images/title_yjzj.jpg" width="216" height="30" border="0"></a></td>
    	</tr>
	   	<tr><td height="3"></td></tr>
    </table>
    
    
    <!--社办报刊-->
	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
		<tr>
			<td colspan="2" class="block_title" height="24">往日信息速查</td>
        </tr>
	  	<tr>
      		<td colspan="2" align="center">
      		<div id="datelist"></div>
      		</td>
      	</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"></td></tr>
    </table>
<!--右下角栏目发布位-->


<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="54%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="61" id="pos61" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="61" id="pos61" type="plain" target="_blank"/></span>
		</a>
		</td>
        <td width="46%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="61" id="pos61" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
              </TR>
			  <%
					  messagecount=0;
					  %> 
        <cms:infolist name="pos61" id="info61" perPageSize="2" titleLength="30"> 
		<%
					  messagecount++;
					  %>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="54%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="62" id="pos62" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="62" id="pos62" type="plain" target="_blank"/></span>
		</a>
		</td>
        <td width="46%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="62" id="pos62" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
      	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
      </TR>
		<%
		messagecount=0;
		%>
        <cms:infolist name="pos62" id="info62" perPageSize="2" titleLength="30">
		<%
		messagecount++;
		%>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>

<!--网络与安全栏目-->



<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="54%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="57" id="pos57" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="57" id="pos57" type="plain" target="_blank"/></span>
		</a>
		</td>
        <td width="46%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="57" id="pos57" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
              </TR>
			  <%
					  messagecount=0;
					  %> 
        <cms:infolist name="pos57" id="info57" perPageSize="2" titleLength="30"> 
		<%
					  messagecount++;
					  %>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>




<!--增加每周技术动态栏目-->
<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="54%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="19" id="pos19" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="19" id="pos19" type="plain" target="_blank"/></span>
		</a>
		</td>
        <td width="46%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="19" id="pos19" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
      	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
      </TR>
		<%
		messagecount=0;
		%>
        <cms:infolist name="pos19" id="info19" perPageSize="2" titleLength="29">
		<%
		messagecount++;
		%>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>
<!--增加每周技术动态栏目结束-->

<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="54%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="63" id="pos63" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="63" id="pos63" type="plain"/></span>
		</a>
		</td>
        <td width="46%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="63" id="pos63" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
              </TR>
			  <%
					  messagecount=0;
					  %>
        <cms:infolist name="pos63" id="info63" perPageSize="2" titleLength="30">
		<%
					  messagecount++;
					  %>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
      <tr>
        <td bgcolor="#FFFFFF">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="54%" height="24" align="left" bgcolor="#FFFFFF" class="block_title">
		<cms:position target="_blank" code="64" id="pos64" type="linknoend" style="text-decoration:none" />
		<span class="table_title"><cms:position code="64" id="pos64" type="plain"/></span>
		</a>
		</td>
        <td width="46%" align="right" bgcolor="#ffffff" class="block_title_blank">
		<cms:position target="_blank" code="64" id="pos64" type="linknoend" style="text-decoration:none" />
		<img src="<%=request.getContextPath()%>/Style/images/more.gif" width="29" height="5" border="0">
		</a>
		</td>
      </tr>
      <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
              </TR>
			  <%
					  messagecount=0;
					  %>
        <cms:infolist name="pos64" id="info64" perPageSize="2" titleLength="30">
		<%
					  messagecount++;
					  %>         
      <tr>
        <td height="24" colspan="12" align="left" class="message_title">·<cms:info target="_blank"  style="text-decoration:none" title="<%=infoTitle%>"/></td>
      </tr>
        </cms:infolist> 
       <%
			 if(messagecount<2){
			 	for(int m=messagecount;m<2;m++){
			 %>
             	<tr><td colspan="3" height="24"></td></tr>
             <%
				}
			 }
			 %>
     
    </table>    </td>
  </tr>
</table>

    
    <!-- 右下角-->

	<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td height="5"></td></tr>
    </table>
	<table border="0" cellpadding="0" cellspacing="1" width="100%" class="table_bgcolor" bgcolor="#FFFFFF">
    <tr>
    	<td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <!--
            	<tr>
                  <td width="20%" rowspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_22.jpg" width="40" height="37"></td>
                  <td width="80%" bgcolor="#FFFFFF" class="message_title">&nbsp;<a href="/cms/cms/manage/info/attachfile?infoId=115579" style="text-decoration:none" class="message_title">即时通讯安装包下载</a></td>
              </tr>
              
              <tr>
                  <td bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_18.jpg" width="100" height="13"></td>
              </tr>
              
              
                <tr>
                    <td height="10" colspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_08.jpg" width="165" height="7"></td>
                </tr>
				-->

                <tr>
                  <td width="20%" rowspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_10.jpg" width="40" height="37"></td>
                  <td width="80%" bgcolor="#FFFFFF" class="message_title">
                  &nbsp;<cms:position code="80" id="pos80" target="_blank" type="linknoend" style="text-decoration:none" styleClass="message_title"/>常用软件下载</a></td>
                  
              </tr>
               <!--  
                <tr>
                  <td bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_18.jpg" width="100" height="13"></td>
              </tr>
               
                <tr>
                    <td height="10" colspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_08.jpg" width="165" height="7"></td>
                </tr>
            
                <tr>
                  <td width="20%" rowspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_11.jpg" width="40" height="37"></td>
                  <td width="80%" bgcolor="#FFFFFF" class="message_title">&nbsp;<a href="/cms/index/tele/index.htm" style="text-decoration:none" class="message_title" target="_blank">国内电话区号</a></td>
              </tr>
                <tr>
                  <td bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_18.jpg" width="100" height="13"></td>
              </tr>
                <tr>
                    <td height="10" colspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_08.jpg" width="165" height="7"></td>
                </tr>
                <tr>
                  <td width="20%" rowspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_12.jpg" width="40" height="37"></td>
                  <td width="80%" bgcolor="#FFFFFF" class="message_title">&nbsp;<a href="/cms/index/tele/index02.htm" style="text-decoration:none" class="message_title" target="_blank">国际电话区号</a></td>
              </tr>
                <tr>
                  <td bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_18.jpg" width="100" height="13"></td>
              </tr>
                <tr>
                    <td height="10" colspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_08.jpg" width="165" height="7"></td>
                </tr>
                <tr>
                  <td width="20%" rowspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_13.jpg" width="40" height="37"></td>
                  <td width="80%" bgcolor="#FFFFFF" class="message_title">&nbsp;<a href="/cms/index/timezone/timezone.htm" style="text-decoration:none" class="message_title" target="_blank">世界主要城市时区图</a></td>
              </tr>
              
              -->
                
                 <tr>
                  <td bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_18.jpg" width="100" height="13"></td>
              </tr>
              
                <tr>
                    <td height="10" colspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_08.jpg" width="165" height="7"></td>
                </tr>


		<tr>
                  <td width="20%" rowspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_14.jpg" width="40" height="37"></td>
                  <td width="80%" bgcolor="#FFFFFF" class="message_title">&nbsp;<cms:position code="81" id="pos81" target="_blank" type="linknoend" styleClass="message_title" style="text-decoration:none"/>培训资料下载</a></td>
              </tr>
                
                <tr>
                  <td bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_18.jpg" width="100" height="13"></td>
              </tr>
              
              <tr>
                    <td height="10" colspan="2" bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/Style/images/right_08.jpg" width="165" height="7"></td>
              </tr>
              
              
              <!-- 删除最后的空 -->
              <!--
               <tr>
                    <td height="30" colspan="2" bgcolor="#FFFFFF"></td>
              </tr>
              -->
              
          </table>
	
            </td>
        </tr>
    </table>
    
        </td>
    <td width="5">&nbsp;</td>
</tr>
</table>


<table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
 <!--
  <tr>
    <td width="983" height="54"><div align="center" class="foot_message"><a href="http://10.103.8.88" target="_blank" class="foot_message" style="text-decoration:none">多媒体数据库系统</a>│<a href="http://10.103.8.52" target="_blank" class="foot_message" style="text-decoration:none">多媒体待编稿库系统</a>│<a href="http://10.106.1.13" target="_blank" class="foot_message" style="text-decoration:none">eNews系统</a>│<a href="http://10.101.101.201:8081" target="_blank" class="foot_message" style="text-decoration:none">总社新闻编辑系统</a>│<a href="http://10.100.3.40:8081" target="_blank" class="foot_message" style="text-decoration:none">国内分社多媒体编辑系统</a>│<a href="http://10.101.106.88/" target="_blank" class="foot_message" style="text-decoration:none">多媒体eNews系统</a>│<a href="http://10.102.2.12" target="_blank" class="foot_message" style="text-decoration:none">稿件采用查询系统</a>│<a href="http://10.103.9.80" target="_blank" class="foot_message" style="text-decoration:none">信息部多媒体采编系统</a><br />
    <a href="http://10.102.1.206:8080/ggjs/" target="_blank" class="foot_message" style="text-decoration:none">图书/数字图书系统</a>│<a href="http://10.99.100.50" target="_blank" class="foot_message" style="text-decoration:none">图片/图表编辑系统</a>│<a href="http://10.102.2.11" target="_blank" class="foot_message" style="text-decoration:none">营销管理系统</a>│<a href="http://10.102.2.221" target="_blank" class="foot_message" style="text-decoration:none">防病毒管理系统</a></div></td>
  </tr>
  
  -->
  <tr>
  <td height="52" bgcolor="#EFEFEF"><div align="center" class="content" ><font  color=red >技术值班电话:010-63072715</font>Copyright (C) 2008 版权所有　　<br />
    制作单位：新华社技术局　（浏览本网主页，建议将电脑显示屏的分辨率调为1024*768）<br />
    </div></td>
  </tr>
</table>

<span id="boxs"></span>

</form>

</cms:body>
</cms:html>

<script language="javascript">
function boxs(v){

 window.scrollTo(0,0);
 var bo = document.getElementsByTagName('body')[0];
 var ht = document.getElementsByTagName('html')[0];
 var boht = document.getElementById('boxs');    
 boht.innerHTML = '';
 bo.style.height='auto';
 bo.style.overflow='auto';
 ht.style.height='auto'; 
 if(v == 1){   
  bo.style.height='100%';
  bo.style.overflow='hidden';
  ht.style.height='100%';  
  boht.innerHTML = '<div id="bg"></div><div id="info"><div id="center"><strong>友情提醒：</strong><p>您的密码过于简单,为了保证您的帐号安全,请及时修改您的密码。 <br><a target="#" href="/oabase/user/ModifyPassword.jsp">修改</a>&nbsp;&nbsp;<a href="javascript:window.boxs(0);">关闭</a></p></div></div>';   
 }
} 

function changemain(id){
		document.getElementById("messagemaintitle").className="title_mainmessage"+id;
		for(i=0;i<4;i++){
			document.getElementById("messagemaintitle"+i).style.display="none";
		}
		document.getElementById("messagemaintitle"+id).style.display="block";
}
function changetongzhi(id){
	document.getElementById("tongzhi").className="title_tongzhi"+id;
	for(i=0;i<4;i++){
		document.getElementById("tongzhi"+i).style.display="none";
	}
	document.getElementById("tongzhi"+id).style.display="block";
}
function _changetitle(objname,objcount,objindex){
	if(doit){
		for(i=1;i<objcount+1;i++){
			var classname = document.getElementById(objname+i).className;
			var classindex = classname.substring(classname.lastIndexOf("_")+1);
			document.getElementById(objname+i).className="title_dark_"+classindex;
		}
		
		var classnameshow = document.getElementById(objname+objindex).className;
		var classindexshow = classnameshow.substring(classnameshow.lastIndexOf("_")+1);
		document.getElementById(objname+objindex).className = "title_light_"+classindexshow;
		
		for(i=1;i<objcount+1;i++){
			document.getElementById(objname+"_block_"+i).style.display="none";	
		}
		document.getElementById(objname+"_block_"+objindex).style.display="block";
		
		for(i=1;i<objcount+1;i++){
			if(document.getElementById(objname+"_cblock_"+i)!=null){
				document.getElementById(objname+"_cblock_"+i).style.display="none";
			}
		}
		if(document.getElementById(objname+"_cblock_"+objindex)!=null){
			document.getElementById(objname+"_cblock_"+objindex).style.display="block";
		}
	}
}

window.top.functionframe.location='/common/function.jsp';
window.top.worktodoframe.location='/oabase/servlet/WorkTodoListServlet';

WdatePicker({eCont:'datelist',maxDate:'%y-%M-%d',onpicked:function(dp){
window.open('<%=request.getContextPath()%>/filter/common/datelist.jsp?date='+dp.cal.getDateStr())}})
</script>

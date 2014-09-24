<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.regulation.vo.RegulationOrgVO"%>

<%
	Connection conn = null;
	List orglist = new ArrayList();
	try {
	conn = DBConnectionLocator.getInstance().getConnection(
				Globals.DATASOURCEJNDI);
	RegulationHandler handler = new RegulationHandler(conn);
	orglist = handler.getROrg();
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}
%>
<HTML>
<HEAD>
<TITLE>left</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="jqGrid/css/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" href="jqGrid/css/jquery-ui-1.7.2.custom.css">

<SCRIPT type="text/javascript" src="include/date/WdatePicker.js"></SCRIPT>
<SCRIPT type="text/javascript" src="jqGrid/js/jquery-1.3.2.min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="jqGrid/js/i18n/grid.locale-cn.js"></SCRIPT>
<SCRIPT type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"></SCRIPT>

<STYLE type=text/css>
BODY {
	font-family: 宋体;
	font-size: 12px;
	BACKGROUND-POSITION: right 50%;
	BACKGROUND-ATTACHMENT: fixed;
	BACKGROUND-IMAGE: url(include/frame_bg.gif);
	MARGIN: 0px;
	BACKGROUND-REPEAT: repeat-y
}

.AdvSearchArea {
	margin: 0 0 0 10px;
	padding: 0;
}

.AdvElement {
	color: #444444;
	display: block;
	height: 35px;
	line-height: 35px;
}

.Text {
	float: left;
	text-align: right;
	width: 85px;
}

.Text1 {
	float: left;
	text-align: right;
	width: 60px;
}

.Display {
	float: left;
	text-align: center;
	width: 25px;
}

.AdvInput {
	width: 180px;
}

.TimeSelect {
	float: left;
	text-align: right;
	width: 150px;
}
</STYLE>

</HEAD>
<BODY>
<form id="sform" action="" method="post">
<div class="AdvSearchArea">
<div class="AdvElement">
<div class="Text">关键字：</div>
<input id='key' class='AdvInput' name="key" type="text" class="text" />
</div>
<div class="AdvElement">
<div class="Text">部&nbsp;&nbsp;门：</div>
<select name='org' id=org class="AdvInput">
	<option value='allorg'>全部</option>
	<%
								if (orglist != null) {
										Iterator iter = orglist.iterator();
										int i = 0;
										while (iter.hasNext()) {
											++i;
											RegulationOrgVO svo = (RegulationOrgVO) iter.next();
							%>
								<option value="<%=svo.getOrguuid()%>"><%=svo.getOrgname()%></option>
							<%
								}
									}
							%>
</select></div>
<div class="AdvElement">
<div class="Text">范围：</div>
<select id='fanwei' style="float: left;" name="fanwei">
	<option value='all'>不限</option>
	<option value='title'>标题</option>
	<option value='content'>正文</option>
	<option value='rcNO'>发文号</option>
</select>

<div class="Text1">有效性：</div>
<select id='youxiao' class="width='50px'" name="youxiao">
	<option value='all'>不限</option>
	<option value='yes'>有效</option>
	<option value='no'>作废</option>
</select></div>
<div class="AdvElement">
<div>
<div style='text-align: right; width: 150px;'><INPUT type=radio
	name="time" value='all' CHECKED /> 所有时间</div>

<div class='TimeSelect'><INPUT type=radio name="time" value='only' />
精确时间</div>
<input id="timeo" name="timeo" class="Wdate" type="text"
	onfocus="WdatePicker({maxDate:'%y-%M-%d'})" size='13' />
<div style='text-align: right; width: 150px;'><INPUT type=radio
	name="time" value='from' /> 时&nbsp;间&nbsp;段</div>
<div style='float: left; text-align: right; width: 80px;'>从</div>
<input type="text" class="Wdate" id="timef" name="timef" size='10'
	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'timee\',{d:-1});}'})" /> 到
<input type="text" class="Wdate" id="timee" name="timee" size='10'
	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'timef\',{d:1});}'})" /></div>
</div>

</div>
<div align='center'>
<input type="reset" value="重置" />
 <input	type="submit" value="查询" />
 </div>
</form>

<hr />
<div>
<div class="AdvElement">
<table class="table1" id="table1">
</table>
<div id="pager1"></div>
</div>
</div>
</BODY>
<script type="text/javascript">
        $('#sform').submit
            (
	            function ()
                {
                	var a =$('input[@name=time][@checked]').val();
         			if( a=='only' ){
         				if($("#timeo").val()==""){
         				alert("请选择具体的时间");
         				return false;
         				}         				
         			}	
         			if( a=='from' ){
         				if($("#timef").val()=="" | $("#timee").val()==""){
         				alert("请选择具体的时间段");
         				return false;
                        }        
                        }
			           $('#table1').flexOptions({newp: 1}).flexReload();
			            return false;
		        }
            );		
</script>

<script type="text/javascript">
$(document).ready(function(){
  var mygrid = $("#table1").jqGrid({
      url:'/oabase/regulation/rlist1.jsp',
      datatype: "json",
      colNames:['序号', '标题', '部门'],
      colModel:[
                {name:'id',index:'id',width:70,sortable:false },
                {name:'title',index:'title', width:150,sortable:false },
                {name:'org',index:'org', width:100,sortable:false }
           ],
    caption:"查询结果：",
    multiselect: true,
    toolbar: [true,"bottom"], 
    ondblClickRow: function(id){top.main.location='content.jsp?id='+id;},
    loadComplete: function(){ 
    	var ids = $("#table1").jqGrid('getDataIDs');
	    $("#table1").jqGrid('setCaption',"查询结果： 搜索到"+ids.length+"条记录");
	   } 
});

$("#t_table1").css("text-align","right").append("<input type='button' value='下 载' style='height:20px'/>"); 
$("input","#t_table1").click(function(){ 
		var s; 
		s = $("#table1").jqGrid('getGridParam','selarrrow'); 
		if(s&&s.length>0){
			alert(s);
		}else{
			alert("请选择要下载的记录!");
			return ;
		}
	}); 


});
</script>
</HTML>

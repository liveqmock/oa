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
<SCRIPT type="text/javascript" src="jqGrid/js/i18n/grid.locale-en.js"></SCRIPT>
<SCRIPT type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"></SCRIPT>

<STYLE type=text/css>
BODY {	font-family: ����;
	font-size: 12px;
	BACKGROUND-POSITION: right 50%;
	BACKGROUND-ATTACHMENT: fixed;
	BACKGROUND-IMAGE: url(include/frame_bg.gif);
	MARGIN: 0px;
	BACKGROUND-REPEAT: repeat-y
}
.Ta{	font-family: ����;
	font-size: 13px;
line-height: 20px;
}
.AdvSearchArea {margin: 0 0 0 10px;	padding: 0;}
.AdvElement {color: #444444;display: block;min-height: 30px;line-height: 30px;}
.Text {float: left;text-align: right;width: 85px;}
.Text1 {float: left;text-align: right;width: 60px;}
.Display {float: left;	text-align: center;	width: 25px;}
.AdvInput {	width: 180px;}
.TimeSelect {float: right;text-align: left;	width: 150px;}
</STYLE>
<script type="text/javascript">
nowid=0;
$(document).ready(function(){
  var mygrid = $("#table1").jqGrid({
      url:'/oabase/regulation/rlist.jsp',
      datatype: "json",
      height:220,
      rowNum:10,
   	  rowList:[10,20,30],
   	  pager: '#pager2',
   	  sortname: 'id',
      viewrecords: true,
      sortorder: "desc",
      colNames:['���', '����', '����'],
      colModel:[
                {name:'id',index:'id',width:70,sortable:true , align:'center'},
                {name:'title',index:'title', width:152,sortable:false, align:'center',classes:'Ta'},
                {name:'org',index:'org', width:95,sortable:false, align:'center' }
           ],
    caption:"��ѯ�����",
    multiselect: true,
    toolbar: [true,"bottom"], 
    onSelectRow: function(id){nowid=id;top.main.location='content.jsp?id='+id; return false;},
    loadComplete: function(){ 
       	allids = $("#table1").jqGrid('getDataIDs');
	    $("#table1").jqGrid('setCaption',"��ѯ����� ������"+mygrid.getGridParam('records')+"����¼");
	   } 
});

//jQuery("#table1").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});

//��ť
$("#t_table1").css("text-align","right").append("<input id='pbutton' type='button' value='��һ��' style='height:20px'/> <input id='nbutton' type='button' value='��һ��' style='height:20px'/> <input id='dbutton' type='button' value='�� ��' style='height:20px'/>"); 
$("#dbutton").click(function(){ 
		var s; 
		s = $("#table1").jqGrid('getGridParam','selarrrow'); 
		if(s&&s.length>0){
			//alert(s);
			window.open("<%=request.getContextPath()%>/servlet/RegulationDownloadServlet?ids="+s);
		}else{
			alert("��ѡ��Ҫ���صļ�¼!");
			return ;
		}
	}); 

//��ѯ�ύ
 $('#query').click(function (){
         var a =$(':radio:checked').val();
         if( a=='only' ){
         	if($("#timeo").val()==""){
	         	alert("��ѡ������ʱ��");
    	     	return false;
         	}         				
         }	
         if( a=='from' ){
         		if($("#timef").val()=="" | $("#timee").val()==""){
        	 	alert("��ѡ������ʱ���");
         		return false;
            }        
         }
    //alert(prepareParams($('#sform').serializeArray()));
	$("#table1").jqGrid('setGridParam',{url:"/oabase/regulation/rlist.jsp?"+prepareParams($('#sform').serializeArray())}).trigger("reloadGrid");
    });		

		var prepareParams = function( a ) {
			var s = [ ];
			function add( key, value ){
				s[ s.length ] = key + '=' + value;
			};
			// If an array was passed in, assume that it is an array
			// of form elements
			if ( jQuery.isArray(a) || a.jquery )
				// Serialize the form elements
				jQuery.each( a, function(){
					add( this.name, this.value );
				});
	
			// Otherwise, assume that it's an object of key/value pairs
			else
				// Serialize the key/values
				for ( var j in a )
					// If the value is an array then the key names need to be repeated
					if ( jQuery.isArray(a[j]) )
						jQuery.each( a[j], function(){
							add( j, this );
						});
					else
						add( j, jQuery.isFunction(a[j]) ? a[j]() : a[j] );
	
			// Return the resulting serialization
			return s.join("&").replace(/%20/g, "+");
	}
	
	var nextR = function() { 
		var index =	jQuery.inArray(nowid,allids);
		var n =allids[index>allids.length-1?allids.length-1:index+1];
		jQuery("#table1").jqGrid('setSelection',n); 
	}
	var prviousR =	function() { 
		var index =	jQuery.inArray(nowid,allids);
		var n =allids[index==0?0:index-1];
		jQuery("#table1").jqGrid('setSelection',n); 
	}
	
	jQuery("#nbutton").click( function() {nextR()}); 
	jQuery("#pbutton").click( function() {prviousR()}); 
	
	
});
</script>

</HEAD>
<BODY>
<form id="sform" action="" method="post">
<div class="AdvSearchArea" style="margin-top:10px">
<div class="AdvElement">
<div class="Text">�ؼ��֣�</div>
<input id='key' class='AdvInput' name="key" type="text" class="text" />
</div>
<div class="AdvElement">
<div class="Text">��&nbsp;&nbsp;�ţ�</div>
<select name='org' id=org class="AdvInput">
	<option value='allorg'>ȫ��</option>
	<%
					if (orglist != null) {
						Iterator iter = orglist.iterator();
						while (iter.hasNext()) {
							RegulationOrgVO svo = (RegulationOrgVO) iter.next();
							%>
								<option value="<%=svo.getOrguuid()%>"><%=svo.getOrgname()%></option>
							<%
							}}
							%>
</select></div>
<div class="AdvElement">
<div class="Text">��&nbsp;&nbsp;Χ��</div>
<select id='fanwei' style="float: left;" name="fanwei">
	<option value='all'>����</option>
	<option value='title'>����</option>
	<option value='content'>����</option>
	<option value='rcNO'>���ĺ�</option>
</select>

<div class="Text1">��Ч�ԣ�</div>
<select id='youxiao' class="width='50px'" name="youxiao">
	<option value='all'>����</option>
	<option value='yes'>��Ч</option>
	<option value='no'>����</option>
</select></div>
<div class="AdvElement">
<div>
<div style='text-align: left; width: 120px;margin-left:40px'><INPUT type=radio name="time" value='all' CHECKED /> ����ʱ��</div>
<div  style=' text-align: left;width: 200px;margin-left:40px'><INPUT type=radio name="time" value='only' /> ��ȷʱ��&nbsp;<input id="timeo" name="timeo" class="Wdate" type="text"
	onfocus="WdatePicker({maxDate:'%y-%M-%d'})" size='13' /></div>


<div style='text-align: left; width: 120px;margin-left:40px'><INPUT type=radio
	name="time" value='from' /> ʱ&nbsp;��&nbsp;��</div>
<div style='float: left; text-align: right; width: 80px;'>��</div>
<input type="text" class="Wdate" id="timef" name="timef" size='12'
	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'timee\',{d:-1});}'})" /> ��
<input type="text" class="Wdate" id="timee" name="timee" size='12'
	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'timef\',{d:1});}'})" />
</div>
</div>
<div align='center'>
<input type="reset" value="����" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <input	type="button" id="query" value="��ѯ" />
 </div>
</form>
<hr/>
<div>
<div>
<table class="table1" id="table1">
</table>
<div id="pager2"></div>
</div>
</div>
</BODY>
</HTML>

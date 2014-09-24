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
<link rel="stylesheet" type="text/css" href="include/flexigrid.css">
<SCRIPT type="text/javascript" src="include/date/WdatePicker.js"></SCRIPT>
<SCRIPT type="text/javascript" src="include/jquery-1.2.6.pack.js"></SCRIPT>
<script type="text/javascript" src="include/flexigrid.pack.js"></script>
<STYLE type=text/css>
BODY {
	font-family: ����;
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
<script>
		
		$(document).ready(function()
		{
		
			
		});
		</script>
</HEAD>
<BODY>
<form id="sform" action="" method="post">
<div class="AdvSearchArea">
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
<div class="Text">��Χ��</div>
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
<div style='text-align: right; width: 150px;'><INPUT type=radio
	name="time" value='all' CHECKED /> ����ʱ��</div>

<div class='TimeSelect'><INPUT type=radio name="time" value='only' />
��ȷʱ��</div>
<input id="timeo" name="timeo" class="Wdate" type="text"
	onfocus="WdatePicker({maxDate:'%y-%M-%d'})" size='13' />
<div style='text-align: right; width: 150px;'><INPUT type=radio
	name="time" value='from' /> ʱ&nbsp;��&nbsp;��</div>
<div style='float: left; text-align: right; width: 80px;'>��</div>
<input type="text" class="Wdate" id="timef" name="timef" size='10'
	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'timee\',{d:-1});}'})" /> ��
<input type="text" class="Wdate" id="timee" name="timee" size='10'
	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'timef\',{d:1});}'})" /></div>
</div>

</div>
<div align='center'>
<input type="reset" value="����" />
 <input	type="submit" value="��ѯ" />
 </div>
</form>

<hr />
<div>
<div class="AdvElement">
<div class='txt'>
	��ѯ�����
</div>
<table class="table1" id="table1">
</table>
</div>
</div>
</BODY>
<script type="text/javascript">

			$("#table1").flexigrid
			(
			{
            url: '/oabase/regulation/rlist.jsp',
			dataType: 'json',
			colModel : [
				{display: '���', name : 'id', width : 40, sortable : false, align: 'center',process: procMe},
				{display: '����', name : 'title', width : 200, sortable : false, align: 'left',process: procMe},
				{display: '����', name : 'org', width : 80, sortable : false, align: 'left',process: procMe}
				],
			searchitems : [
				{display: '����', name : 'title'},
				{display: '����', name : 'org', isdefault: true}
				],
			//sortname: "iso",
			//sortorder: "asc",
			//usepager: true,
			title: '��ѯ���:',
			title: false,//�Ƿ��������
			//useRp: true,
			//rp: 15,
			usepager: false, //�Ƿ��ҳ
			nowrap: true, //�Ƿ񲻻���
            showTableToggleBtn: false,
			//showToggleBtn: false, //show or hide column toggle popup
            singleSelect:true,
			//width: 300,
			width: 'auto',
			resizable: false, //�Ƿ������
			onSubmit: addFormData,
			height: 260
			}
			);

        //This function adds paramaters to the post of flexigrid. You can add a verification as well by return to false if you don't want flexigrid to submit			
        function addFormData()
        {
	        //passing a form object to serializeArray will get the valid data from all the objects, but, if the you pass a non-form object, you have to specify the input elements that the data will come from
            //var dt = $('#sform').serializeArray();
				
            var dt = $('#sform').map(function(){
        			return jQuery.nodeName(this, "form") ?
        				jQuery.makeArray(this.elements) : this;
        		})
        		.filter(function(){
        			return this.name && !this.disabled &&
        				(this.checked || /select|textarea/i.test(this.nodeName) ||
        					/text|hidden|password/i.test(this.type));
        		})
        		.map(function(i, elem){
        			var val = jQuery(this).val();
        			return val == null ? null :
        				val.constructor == Array ?
        					jQuery.map( val, function(val, i){
        						return {name: elem.name, value: escape(val)};
        					}) :
        					{name: elem.name, value: escape(val)};
        		}).get();
               
            $("#table1").flexOptions({params:dt});
            
            return true;
            }
	
        $('#sform').submit
            (
	            function ()
                {
                	var a =$('input[@name=time][@checked]').val();
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

			            $('#table1').flexOptions({newp: 1}).flexReload();
			            return false;
		            }
            );		

        function procMe(celDiv,id)
        {
            $(celDiv).click(
                    function (){
                        //alert(id);
                        top.main.location='content.jsp?id='+id;
               });
            } 
</script>
</HTML>

<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator"%>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException"%>
<%@ page import="com.icss.common.log.ConnLog"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler"%>
<%@ page import="com.icss.regulation.vo.RegulationVO"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.regulation.vo.RegulationOrgVO"%>

<%
	Integer id = request.getParameter("id") == null||"".equals(request.getParameter("id")) ? new Integer(-1)
			: new Integer(request.getParameter("id"));
			
	String orguuid =(String) request.getParameter("orguuid")==null?"no":(String)request.getParameter("orguuid");
	Connection conn = null;
	RegulationVO vo = new RegulationVO();
	List orglist = new ArrayList();
	
	try {
	conn = DBConnectionLocator.getInstance().getConnection(
				Globals.DATASOURCEJNDI);
	RegulationHandler handler = new RegulationHandler(conn);
	if (id.intValue() != -1) {
			vo = handler.getRegulation(id);
	}

		if("no".equals(orguuid)){
			orglist = handler.getROrg();
		}else{
			RegulationOrgVO svo1 = new RegulationOrgVO();
			svo1.setOrguuid(orguuid);
			svo1.setOrgname(handler.getROrgName(orguuid));
			orglist.add(svo1);
		}
		ConnLog.open("edit.jsp");
%>

<HTML>
	<HEAD>
		<TITLE>管理制度系统</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<link
			href="<%=request.getContextPath()%>/regulation/include/normal.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			id=homepagestyle rel="stylesheet" type="text/css" />

		<SCRIPT type="text/javascript"
			src="<%=request.getContextPath()%>/regulation/include/date/WdatePicker.js"></SCRIPT>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/fckeditor/fckeditor.js"></script>

		<STYLE type=text/css>
BODY {
	font-family: 宋体;
	font-size: 14px;
	}
.panel {
	PADDING-RIGHT: 10px;
	PADDING-LEFT: 10px;
	PADDING-BOTTOM: 10px;
	MARGIN: 10px;
	PADDING-TOP: 10px;
}

.da {
	PADDING-RIGHT: 10px;
	PADDING-LEFT: 50px;
	PADDING-BOTTOM: 0px;
	MARGIN: 5px;
	WIDTH: 780px;
}

.panel UL {
	list-style-type: none;
	MARGIN: 0px 0px 20px
}

.panel UL LI {
	CLEAR: both;
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 5px;
	MARGIN: 5px 0px;
	PADDING-TOP: 5px;
}

.panel LABEL {
	DISPLAY: block;
	FLOAT: left;
	WIDTH: 100px;
	TEXT-ALIGN: right
}

.panel SPAN {
	DISPLAY: block;
	FLOAT: left;
	WIDTH: 100px;
	TEXT-ALIGN: right
}

.panel TEXTAREA {
	DISPLAY: block;
	FLOAT: left;
}

.panel INPUT {
	FLOAT: left;
	VERTICAL-ALIGN: top;
}

.panel SELECT {
	FLOAT: left;
	width:120px;
}

</STYLE>

		<script type="text/javascript">
	function _check(){
		
		if(document.form1.title.value==""){
			alert('请填写标题');
			return false;
		}
			
		if(document.form1.org.value==""){
			alert('请选择部门');
			return false;
		}
		if(document.form1.creattime.value==""){
			alert('请选择制定时间');
			return false;
		}
		
		return true;
	}
	
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	
	function _save(){
		if(_check()){
		document.form1.action="<%=request.getContextPath()%>/servlet/AddRegulationServlet";
	 	document.form1.submit();
	 	}
	}
	
	function _back(){
		document.form1.action="<%=request.getContextPath()%>/servlet/AllRegulationServlet";
		document.form1.submit();
	}
</script>
	</HEAD>
	
	<BODY bgColor='#FBFBEA'>
		<form name=form1 action="" method="post">
		<jsp:include page="/include/top.jsp" />
			<input type=hidden name='id'
				value=<%=vo.getId() == null ? "" : vo.getId().toString()%>>
			<div class=panel id=top>
				<ul>
					<li>
						<label for=title>
							标题：
						</label>
						<input type='text' name='title' id='title' style="width:500px"
							value=<%=StringUtil.escapeNull(vo.getTitle())%>>
					</li>
					<li>
						<label for=org>
							部门：
						</label>
						<select name='org' id=org>
							
							<%
								if (orglist != null) {
										Iterator iter = orglist.iterator();
										int i = 0;
										while (iter.hasNext()) {
											++i;
											RegulationOrgVO svo = (RegulationOrgVO) iter.next();
							%>
							<option value="<%=svo.getOrguuid()%>" <%if(svo.getOrguuid().equals(vo.getOrg())){ %>selected <%} %>><%=svo.getOrgname()%>
							</option>

							<%
								}
									}
							%>
						</select>
						<span> 制定时间： </span>
						<input name='creattime' id="creattime" class="Wdate" type="text"
							onfocus="WdatePicker({maxDate:'%y-%M-%d'})" size='12'
							value=<%=vo.getCreateTime() == null ? "" : vo
						.getCreateTime().toString()%>>
						<span> 修改时间： </span>
						<input name='edittime' id="edittime" class="Wdate" type="text"
							onfocus="WdatePicker({maxDate:'%y-%M-%d'})" size='12'
							value=<%=vo.getEditTime() == null ? "" : vo.getEditTime()
						.toString()%>>
						<span> 有效: </span>
						<input type=checkbox name='ck' value='youxiao'
							<%if(new Integer(1).equals(vo.getFlag())){}else{ %> checked
							<%} %>>
					</li>
					<li>
						<label for=recordNo>
							发文号：
						</label>
						<input type='text' name='recordNo' id='recordNo' style="width:500px"
							value=<%=StringUtil.escapeNull(vo.getRecordNo())%>>
					</li>
					
					<li>
						<label for=memo>
							备注：
						</label>
						<textarea rows="2" cols="60" name='memo' id=memo><%=StringUtil.escapeNull(vo.getMemo())%></textarea>
						<img
							src="<%=request.getContextPath()%>/regulation/include/save.gif"
							style="cursor:hand"	onclick='_save()'>
						<img
							src="<%=request.getContextPath()%>/regulation/include/back.gif"
							style="cursor:hand" onclick='_back()'>
							
						
					</li>
				</ul>
			</div>

			<div class="da">
			<textarea name="content" cols="80" rows="4">
				<%=StringUtil.escapeNull(vo.getContent())%>
			</textarea>
				<script type="text/javascript">
			var oFCKeditor = new FCKeditor('content') ;
			oFCKeditor.BasePath	= "<%=request.getContextPath()%>/fckeditor/";
			oFCKeditor.Height = 350;
			oFCKeditor.ToolbarSet = "Regulation"; 
			oFCKeditor.ReplaceTextarea();
			</script>
			</div>
		</form>
	</BODY>
</HTML>

<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();

	} finally {
		try {
			if (conn != null) {
				conn.close();
				ConnLog.close("edit.jsp");
			}
		} catch (Exception e) {
		}
	}
%>


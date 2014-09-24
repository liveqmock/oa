<%@ page contentType="text/html; charset=GBK" language="java"
	errorPage=""%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.oa.tq.vo.TqOrgpersonVO"%>

<html>
	<head>
		<title>组织树</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/tq/js/xtree.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/tq/js/xmlextras.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/tq/js/xloadtree.js"></script>
		<link href="<%=request.getContextPath()%>/include/style.css"
			rel=stylesheet />
		<link type="text/css" rel="stylesheet"
			href="<%=request.getContextPath()%>/orgtree/css/xtree.css" />
		<object id="TraCQJump1"
			classid="clsid:ABD696D5-BEBF-4332-B8D2-2B44BD23B58B">
			<param name="_Version" value="65536">
			<param name="_ExtentX" value="2646">
			<param name="_ExtentY" value="1323">
			<param name="_StockProps" value="0">
		</object>
			
		<style type="text/css">
			body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			}
			</style>
	</head>
	<script language="javascript"> 

		//双击事件函数
		function doubleClick(hwnd,uid,name) 
		{ 
		TraCQJump1.LButtonDBClick(hwnd,uid,name);	
		} 
		//右键菜单函数
		function rightClick(hwnd,uin,nickna) 
		{ 
		    if(event.button == 2) 
		    {
		      TraCQJump1.RButtonClick(hwnd,uin,nickna);   
		    }
		} 
	</script>

	<body oncontextmenu="return false" onselectstart="return false"
		oncopy="return false" oncut="return false" onpaste="return false">
		
			<%
				//得到客户端传递的窗口句柄 hwnd 
				//网页中不能对句柄 hwnd 做任何处理，
				//在调用客户端控件函数时将原值返回即可。
				String hwnd = (String) request.getAttribute("hwnd");
			%>
			<div style="overflow: auto; height: 370; width: 100%">
				<BR/>
								<table>
					<tr>
						<td width="0">
							&nbsp;
						</td>
						<td bgcolor="#FFFFFF">

	<script type="text/javascript">
	/// XP Look
	webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/orgtree/images/root.gif";
	webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/orgtree/images/root.gif";
	webFXTreeConfig.folderIcon		= "<%=request.getContextPath()%>/orgtree/images/close.gif";
	webFXTreeConfig.openFolderIcon	= "<%=request.getContextPath()%>/orgtree/images/open.gif";
	webFXTreeConfig.fileIcon		= "<%=request.getContextPath()%>/orgtree/images/open.gif";
	webFXTreeConfig.lMinusIcon		= "<%=request.getContextPath()%>/orgtree/images/Lminus.png";
	webFXTreeConfig.lPlusIcon		= "<%=request.getContextPath()%>/orgtree/images/Lplus.png";
	webFXTreeConfig.tMinusIcon		= "<%=request.getContextPath()%>/orgtree/images/Tminus.png";
	webFXTreeConfig.tPlusIcon		= "<%=request.getContextPath()%>/orgtree/images/Tplus.png";
	webFXTreeConfig.iIcon			= "<%=request.getContextPath()%>/orgtree/images/I.png";
	webFXTreeConfig.lIcon			= "<%=request.getContextPath()%>/orgtree/images/L.png";
	webFXTreeConfig.tIcon			= "<%=request.getContextPath()%>/orgtree/images/T.png";
	webFXTreeConfig.blankIcon	    = "<%=request.getContextPath()%>/orgtree/images/blank.png";
	
	<%
	com.icss.oa.address.vo.SysOrgVO topDepVO=(com.icss.oa.address.vo.SysOrgVO)request.getAttribute("topDepVO");
	%>
		var dtree = new WebFXTree("<%=topDepVO.getCnname()%>","");
	<%
	List userlist = (List) session.getAttribute("userlist");
	
	List deplist = (List)request.getAttribute("deplist");
	if(deplist!=null){
		Iterator it1 = deplist.iterator();
		while(it1.hasNext()){
			TqOrgpersonVO vo = (TqOrgpersonVO)it1.next();
			String name = vo.getCnname();
			Integer tqid = vo.getTqid() == null ? new Integer(0) : vo.getTqid();
			if (userlist != null && userlist.contains(tqid.toString())) {
			%>
			dtree.add(new WebFXTreeItem("<%=vo.getCnname()%>","javascript:doubleClick(<%=hwnd%>,<%=tqid%>,'<%=name%>')","","<%=request.getContextPath()%>/images/tq/msn_online.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			<%}else{%>
			//dtree.add(new WebFXTreeItem("<%=vo.getCnname()%>","javascript:doubleClick(<%=hwnd%>,<%=tqid%>,'<%=name%>')","","<%=request.getContextPath()%>/images/tq/msn_offline.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			<%
			}
			}
			
			Iterator it2 = deplist.iterator();
			while(it2.hasNext()){
			TqOrgpersonVO vo = (TqOrgpersonVO)it2.next();
			String name = vo.getCnname();
			Integer tqid = vo.getTqid() == null ? new Integer(0) : vo.getTqid();
			if (userlist != null && userlist.contains(tqid.toString())) {
			%>
			//dtree.add(new WebFXTreeItem("<%=vo.getCnname()%>","javascript:doubleClick(<%=hwnd%>,<%=tqid%>,'<%=name%>')","","<%=request.getContextPath()%>/images/tq/msn_online.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			<%}else{%>
			dtree.add(new WebFXTreeItem("<%=vo.getCnname()%>","javascript:doubleClick(<%=hwnd%>,<%=tqid%>,'<%=name%>')","","<%=request.getContextPath()%>/images/tq/msn_offline.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			<%
			}
			}
		}
%>
document.write(dtree);
dtree.collapseAll();

</script>
						</td>
						<td width="12">
							&nbsp;
						</td>
					</tr>

				</table>
				
				<table>
					<tr>
						<td width="0">
							&nbsp;
						</td>
						<td bgcolor="#FFFFFF">

	<script type="text/javascript">
	/// XP Look
	webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/orgtree/images/root.gif";
	webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/orgtree/images/root.gif";
	webFXTreeConfig.folderIcon		= "<%=request.getContextPath()%>/orgtree/images/close.gif";
	webFXTreeConfig.openFolderIcon	= "<%=request.getContextPath()%>/orgtree/images/open.gif";
	webFXTreeConfig.fileIcon		= "<%=request.getContextPath()%>/orgtree/images/open.gif";
	webFXTreeConfig.lMinusIcon		= "<%=request.getContextPath()%>/orgtree/images/Lminus.png";
	webFXTreeConfig.lPlusIcon		= "<%=request.getContextPath()%>/orgtree/images/Lplus.png";
	webFXTreeConfig.tMinusIcon		= "<%=request.getContextPath()%>/orgtree/images/Tminus.png";
	webFXTreeConfig.tPlusIcon		= "<%=request.getContextPath()%>/orgtree/images/Tplus.png";
	webFXTreeConfig.iIcon			= "<%=request.getContextPath()%>/orgtree/images/I.png";
	webFXTreeConfig.lIcon			= "<%=request.getContextPath()%>/orgtree/images/L.png";
	webFXTreeConfig.tIcon			= "<%=request.getContextPath()%>/orgtree/images/T.png";
	webFXTreeConfig.blankIcon	    = "<%=request.getContextPath()%>/orgtree/images/blank.png";
	
	<%
	SysOrgVO topOrgVO=(SysOrgVO)request.getAttribute("topOrgVO");
	%>
	var tree = new WebFXTree("<%=topOrgVO.getCnname()%>","");
	<%
	List list = (List)request.getAttribute("list");
	if(list!=null){
		Iterator it = list.iterator();
		while(it.hasNext()){
			SysOrgTreeVO vo = (SysOrgTreeVO)it.next();
			if(!vo.getHasChild()){
			%>
					tree.add(new WebFXTreeItem("<%=vo.getVO().getCnname()%>","","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			<%
			}
			else{
			%>		
					tree.add(new WebFXLoadTreeItem("<%=vo.getVO().getCnname()%>","../servlet/OrgTreeFromXmlServlet.xml?orgId=<%=vo.getVO().getOrguuid()%>&hwnd=<%=hwnd%>","","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
				<%
				
				}
		}
	}
%>
document.write(tree);
</script>
						</td>
						<td width="12">
							&nbsp;
						</td>
					</tr>

				</table>
			</div>
	</body>
</html>
<%@ page contentType="text/html; charset=GBK" language="java" 	errorPage=""%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.oa.hr.vo.HRGroupVO"%>
<%
Collection indiCollection = (Collection)request.getAttribute("indiGroup");
Collection commomCollection = (Collection)request.getAttribute("commonGroup");
Iterator commonGroupIterator = commomCollection.iterator();
Iterator indiGroupIterator = indiCollection.iterator();

%>
<html>
	<head>
		<title>组织树</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
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
			<param name="_Version" value="65536" />
			<param name="_ExtentX" value="2646" />
			<param name="_ExtentY" value="1323" />
			<param name="_StockProps" value="0" />
		</object>
		<style type="text/css">
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
		</style>
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
</head>
	<body oncontextmenu="return false" onselectstart="return false"
		oncopy="return false" oncut="return false" onpaste="return false">
	
		<%
			//得到客户端传递的窗口句柄 hwnd 
			//网页中不能对句柄 hwnd 做任何处理，
			//在调用客户端控件函数时将原值返回即可。
			String hwnd = (String)request.getAttribute("hwnd");
		%>
		<div style ="overflow:auto;height:320;width:100%">
			<BR/>
			<table>
				<tr>
					<td width="13">
					</td>
					<td>

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
				
				var tree2= new WebFXTree("公共分组","");
				<%  
					while(commonGroupIterator.hasNext()){
						HRGroupVO vo = (HRGroupVO)commonGroupIterator.next();
				%>
					tree2.add(new WebFXLoadTreeItem("<%=vo.getOrgname()%>",
					
							"../servlet/GroupTreeXmlServlet.xml?ParentID=<%= vo.getGroupid()%>&hwnd=<%=hwnd%>","","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			<%
					
				   }
				%>

				document.write(tree2);
				
		</script>

					</td>
					</tr>
				<tr>
					<td></td>
					<td>
						&nbsp;
					</td>
				
				</tr>
			
				<tr>
					<td width="13">
						&nbsp;
					</td>
					<td>
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
				
				var tree1 = new WebFXTree("个人分组","");
				<%  
					while(indiGroupIterator.hasNext()){
						AddressGroupVO addressGroupVO = (AddressGroupVO)indiGroupIterator.next();
				%>
					tree1.add(new WebFXLoadTreeItem("<%=addressGroupVO.getGroupname()%>",
					
					"../servlet/IndiGroupXmlServlet.xml?groupid=<%=addressGroupVO.getId()%>&hwnd=<%=hwnd%>","","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
			 <%
				   }
				%>

				document.write(tree1);
				
		</script>
								
					</td>
					
				</tr>
				
			</table>
	</div>
		<br>
	</body>
</html>


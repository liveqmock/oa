<%
/**
��Ҫ�����
���ļ�����tree�ṹ�����ʼ��������_target��goUrl, TopSortName;
*/
%>

<%

String target = _target; //�붨��
if(target == null) target = "mainFrame";
String Url = goUrl;

String ParentSortID=request.getAttribute("ParentSortID")==null?Config.SORT_SUPER_NODEID:request.getAttribute("ParentSortID").toString();
SortTree sortTree = new SortTree();
try{
	sortTree.setShowTop(true);
	sortTree.setTarget(target);
	sortTree.setParentId(ParentSortID);
	sortTree.setOrgTopName(TopSortName);
	sortTree.setUrl(Url);
	sortTree.setInID("TreeList");
	sortTree.createTree(isLinked);
%>
<%=sortTree.getOutPutString()%>
<%
    } catch (Exception sqle) {
        // handle SQLException
    }
%>
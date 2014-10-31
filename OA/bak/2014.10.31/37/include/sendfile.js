/**
 *	��Ա����ҳ��Ļ����ѡ����˻�����֯
 * findperson.jsp
 */
function getSearchPerson(){
	var reSelectPersons = new Array();
	var selectPerson = false;
	//ȡ�����е�ѡ���
	var persons = document.getElementsByName("personid");
	for(var i=0;i<persons.length;i++){
		//�ж��Ƿ��Ǹ�ѡ��
		if (isCheckBox(persons[i])){
			//�ж��Ƿ���ѡ�е�
			if (persons[i].checked){
				//���ѡ��
				persons[i].checked = false;
				selectPerson = true;
				//ȡ�����ݴ�ŵ����ص���������
				try{
					var userValue = persons[i].value;
					var userValueArray = userValue.split("��");
					var reUsers = new Array();
					reUsers["value"] = userValueArray[0];
					reUsers["name"] = userValueArray[1];
					reUsers["department"] = userValueArray[2];
					reUsers["uuid"] = userValueArray[3];
					reUsers["type"] = "0";
					reSelectPersons[reSelectPersons.length] = reUsers;
				}catch(e){
				//ѡ������������⣬��������Ե�
					alert("�����쳣��" + e);
				}
			}
		}
	}
	//���û��ѡ�и�����ʾ
	if (!selectPerson){
		alert("��ѡ����Ա��");
		return;
	}
	//���ｫ���ݴ���ѡ����Ա�б��ķ���
	window.top.setPersons(reSelectPersons);
}

function getSelectOrg(){
	var reSelectOrgs = new Array();
	var selectOrg = false;
	//ȡ�����е�ѡ���
	var orgs = document.getElementsByName("orgid");
	for(var i=0;i<orgs.length;i++){
		//�ж��Ƿ��Ǹ�ѡ��
		if (isCheckBox(orgs[i])){
			//�ж��Ƿ���ѡ�е�
			if (orgs[i].checked){
				//���ѡ��
				orgs[i].checked = false;
				selectOrg = true;
				//ȡ�����ݴ�ŵ����ص���������
				try{
					var userValue = orgs[i].value;
					var userValueArray = userValue.split("��");
					var reUsers = new Array();
					reUsers["value"] = userValueArray[3];
					reUsers["name"] = userValueArray[1];
					reUsers["department"] = userValueArray[1];
					reUsers["uuid"] = userValueArray[3];
					reUsers["type"] = "1";
					reSelectOrgs[reSelectOrgs.length] = reUsers;
				}catch(e){
				//ѡ������������⣬��������Ե�
					alert("�����쳣��" + e);
				}
			}
		}
	}
	//���û��ѡ�и�����ʾ
	if (!selectOrg){
		alert("��ѡ����֯��");
		return;
	}
	//���ｫ���ݴ���ѡ����Ա�б��ķ���
	window.top.setPersons(reSelectOrgs);
}

function getSearchOrg(){
	var reSelectPersons = new Array();
	var selectPerson = false;
	//ȡ�����е�ѡ���
	var persons = document.getElementsByName("personid");
	for(var i=0;i<persons.length;i++){
		//�ж��Ƿ��Ǹ�ѡ��
		if (isCheckBox(persons[i])){
			//�ж��Ƿ���ѡ�е�
			if (persons[i].checked){
				//���ѡ��
				persons[i].checked = false;
				selectPerson = true;
				//ȡ�����ݴ�ŵ����ص���������
				try{
					var userValue = persons[i].value;
					var userValueArray = userValue.split("��");
					var reUsers = new Array();
					reUsers["value"] = userValueArray[0];
					reUsers["name"] = userValueArray[1];
					reUsers["department"] = userValueArray[1];
					reUsers["uuid"] = userValueArray[0];
					reUsers["type"] = document.all("isperson").value;
					reSelectPersons[reSelectPersons.length] = reUsers;
				}catch(e){
				//ѡ������������⣬��������Ե�
					alert("�����쳣��" + e);
				}
			}
		}
	}
	//���û��ѡ�и�����ʾ
	if (!selectPerson){
		if (document.all("isperson").value == "00"){
			alert("��ѡ�񹫹����飡");
		}else if (document.all("isperson").value == "01"){
			alert("��ѡ����˷��飡");
		}else{
			alert("��ѡ����飡");
		}
		return;
	}
	//���ｫ���ݴ���ѡ����Ա�б��ķ���
	window.top.setPersons(reSelectPersons);
}
/**
 * ��ѡ��������û��������ļ�����ҳ��
 * selectbyorg.jsp
 */
function setSelectUserByOrg() {
	var reArray = new Array();
	var alluserObj = document.getElementsByName("selectedperson")[0];
	for (var i=0;i<alluserObj.length;i++) {
		var reUserArray = new Array();
		reUserArray["value"] = alluserObj.options(i).value;
		reUserArray["name"] = alluserObj.options(i).text;
		reUserArray["department"] = alluserObj.options(i).getAttribute("department");
		reUserArray["uuid"] = alluserObj.options(i).getAttribute("uuid");
		reUserArray["type"] = alluserObj.options(i).getAttribute("personType");
		reArray[reArray.length] = reUserArray;
	}
	setAllData(reArray);
	return true;
}

/**
 * �ж��ƶ���Element�Ƿ��Ǹ�ѡ��
 */
function isCheckBox(ele){
	if (ele.tagName.toLowerCase() == "input" && ele.type.toLowerCase() == "checkbox"){
		return true;
	}else{
		return false;
	}
}

/**
 * ���������ݷ������ļ�����ҳ��
 */
function setAllData(persons){
	window.top.setSelect(persons);
	closeMe();
}

 /**
  * �ر��Լ�
  */
 function closeMe(){
 	window.top.close();
 }
//selected.jsp//

	/**
	 * �õ�table�ı��ⲿ��
	 */
	function getTableHead(row){
		var tableHtml = "<table id=\"personList\" width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n";
		tableHtml += "	<tr bgcolor=\"#FFFBEF\">\n"; 
        tableHtml += "		<td width=\"13%\"  height=\"22\"><div align=\"center\" class=\"title-04\">ѡ��</div></td>\n";
       // tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml += "		<td width=\"0\" rowspan=\"" + row + "\" valign=\"top\" background=\"" + urlPath + "/images/bg-24.gif\"><img src=\"" + urlPath + "/images/bg-24.gif\" width=\"2\" height=\"2\"></td>\n";
        tableHtml += "		<td width=\"12%\" ><div align=\"center\" class=\"title-04\">����</div></td>\n";
        tableHtml += "		<td width=\"0\" rowspan=\"" + row + "\" valign=\"top\" background=\"" + urlPath + "/images/bg-24.gif\"><img src=\"" + urlPath + "/images/bg-24.gif\" width=\"2\" height=\"2\"></td>\n";
        tableHtml += "		<td width=\"25%\" ><div align=\"center\" class=\"title-04\">����</div></td>\n";
        tableHtml += "		<td width=\"0\" rowspan=\"" + row + "\" valign=\"top\" background=\"" + urlPath + "/images/bg-24.gif\"><img src=\"" + urlPath + "/images/bg-24.gif\" width=\"2\" height=\"2\"></td>\n";
        tableHtml += "		<td width=\"13%\" ><div align=\"center\" class=\"title-04\">ѡ��</div></td>\n";
        tableHtml += "		<td width=\"0\" rowspan=\"" + row + "\" valign=\"top\" background=\"" + urlPath + "/images/bg-24.gif\"><img src=\"" + urlPath + "/images/bg-24.gif\" width=\"2\" height=\"2\"></td>\n";
        tableHtml += "		<td width=\"12%\" ><div align=\"center\" class=\"title-04\">����</div></td>\n";
       	tableHtml += "		<td width=\"0\" rowspan=\"" + row + "\" valign=\"top\" background=\"" + urlPath + "/images/bg-24.gif\"><img src=\"" + urlPath + "/images/bg-24.gif\" width=\"2\" height=\"2\"></td>\n";
        tableHtml += "		<td width=\"25%\" ><div align=\"center\" class=\"title-04\">����</div></td>\n";
		tableHtml += "	</tr>\n";
		return tableHtml;
	}
	/**
	 * �õ�Table�е�һ����
	 */
	function getLine(max){
		var tableHtml = "<tr>\n";
		if (max != null){
	        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
	        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
	        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
	        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
	        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
		}
        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml +="	<td background=\"" + urlPath + "/images/bg-13.gif\"></td>\n";
        tableHtml += "</tr>\n";                
        return tableHtml;

        return "";
	}
	/**
	 * ���û��Ž�table
	 */
	function setPersons(persons,select){
		var perList = getAllPerson(select);

		var row = persons.length + perList.length;
		var pan1 = (row/2);
		var pan2 = (row%2);
		if (pan2 != 0){
			pan1 += 0.5;
		}
		row = (pan1+1)*2;
		var tableHtml = getTableHead(row);
		tableHtml += getLine();
		if (pan1==0){
			setNonePerson();
			return;
		}

		var linObj = document.getElementsByName("rowLine");
		for (var i=0;i<linObj.length;i++){
			linObj.rowspan = row;
		}
		//ȡ�����е��˵�id��Ϊ������
		var allDataId = ",";
		for(var ii=0;ii<perList.length;ii++){
			allDataId += perList[ii]["value"] + ",";
		}
		var j = 0;
		for(var i=0;i<perList.length;i++){
			var name = perList[i]["name"];
			var value = perList[i]["value"];
			var department = perList[i]["department"];
			var uuid = perList[i]["uuid"];
			var type = perList[i]["type"];
			i++;
			var name1 = null;
			var value1 = null;
			var department1 = null;
			var uuid1 = null;
			var type1 = null;
			if (i<perList.length){
				name1 = perList[i]["name"];
				value1 = perList[i]["value"];
				department1 = perList[i]["department"];
				uuid1 = perList[i]["uuid"];
				type1 = perList[i]["type"];
			}else{
				
				while(true){
					if (persons.length>j){
						if (allDataId.indexOf("," + persons[j]["value"] + ",") == -1){
							name1 = persons[j]["name"];
							value1 = persons[j]["value"];
							department1 = persons[j]["department"];
							uuid1 = persons[j]["uuid"];
							type1 = persons[j]["type"];
							j++;
							break;
						}else{
							j++;
						}
					}else{
						break;
					}
				}
			}
			tableHtml += getTwoPerson(name,value,department,uuid,type,name1,value1,department1,uuid1,type1);
			if (color == color0){
				color = color1;
			}else{
				color = color0;
			}
			tableHtml += getLine();
		}
		for (;j<persons.length;j++){
				var name = null;
				var value = null;
				var department = null;
				var type = null;
			while(true){
				if (allDataId.indexOf("," + persons[j]["value"] + ",") == -1){	
					var name = persons[j]["name"];
					var value = persons[j]["value"];
					var department = persons[j]["department"];
					var uuid = persons[j]["uuid"];
					var type = persons[j]["type"];
					j++;
					break;
				}else{
					j++;
					if (j>=persons.length){
						break;
					}
				}
				
			}
			var name1 = null;
			var value1 = null;
			var department1 = null;
			var uuid1 = null;
			var type1 = null;
			if (j<persons.length){
				while(true){
					if (allDataId.indexOf("," + persons[j]["value"] + ",") == -1){	
						name1 = persons[j]["name"];
						value1 = persons[j]["value"];
						department1 = persons[j]["department"];
						uuid1 = persons[j]["uuid"];
						type1 = persons[j]["type"];
						break;
					}else{
						j++;
						if (j>=persons.length){
						break;
						}
					}
				}
			}
			if (value != null){
				tableHtml += getTwoPerson(name,value,department,uuid,type,name1,value1,department1,uuid1,type1);
				if (color == color0){
					color = color1;
				}else{
					color = color0;
				}
				tableHtml += getLine();
			}
		}
		tableHtml += getTableEnd();
		data.innerHTML = tableHtml;
		
	}
	/**
	 * �õ�һ��tr
	 */
	function getTwoPerson(name,value,department,uuid,type,name1,value1,department1,uuid1,type1){
		var tableHtml = "<tr bgcolor=\"" + color + "\" onMouseOut=\"this.bgColor='" + color + "';\" onMouseOver=\"this.bgColor='" + color2 + "';\">\n";
		tableHtml +="	<td align=\"center\" ><input type=\"checkbox\" name=\"personid\" value=\"" + value + "\"><input type=\"hidden\" name=\"cnname\" value=\"" + name + "\"><input type=\"hidden\" name=\"department\" value=\"" + department + "\"><input type=\"hidden\" name=\"uuid\" value=\"" + uuid + "\"><input type=\"hidden\" name=\"perType\" value=\"" + type + "\"></td>\n";
			//var typeImg = "person.gif";
		if (type == "00"){
			//typeImg = "commongroup.gif";
			tableHtml +="	<td align=\"center\" >���·���</td>\n";
		}else if (type == "01"){
			//typeImg = "indigroup.gif";
			tableHtml +="	<td align=\"center\" >���˷���</td>\n";
		}else if (type == "02"){
			//typeImg = "commongroup.gif";
			tableHtml +="	<td align=\"center\" >��������</td>\n";

			}else if (type == "1"){
				tableHtml +="	<td align=\"center\" >��֯</td>\n";
		}else{
			tableHtml +="	<td align=\"center\" ><div title=\"������֯��&#13;&#10;" + department + "\">��Ա</div></td>\n";
    	}
        tableHtml +="	<td height=\"22\" class=\"text-01\" align=\"center\">" + name + "</td>\n";
		if (value1 == null || value1 == ""){
			tableHtml +="<td></td>\n";
			tableHtml +="<td></td>\n";
			tableHtml +="<td class=\"text-01\"></td>\n";
		}else{
			tableHtml +="	<td align=\"center\" ><input type=\"checkbox\" name=\"personid\" value=\"" + value1 + "\"><input type=\"hidden\" name=\"cnname\" value=\"" + name1 + "\"><input type=\"hidden\" name=\"department\" value=\"" + department1 + "\"><input type=\"hidden\" name=\"uuid\" value=\"" + uuid1 + "\"><input type=\"hidden\" name=\"perType\" value=\"" + type1 + "\"></td>\n";
				//var typeImg1 = "person.gif";
			if (type1 == "00"){
				//typeImg1 = "commongroup.gif";
				tableHtml +="	<td align=\"center\" >��������</td>\n";
			}else if (type1 == "01"){
				//typeImg1 = "indigroup.gif";		
				tableHtml +="	<td align=\"center\" >���˷���</td>\n";
			}else if (type == "02"){
			//typeImg = "commongroup.gif";
			tableHtml +="	<td align=\"center\" >��������</td>\n";

			}else if (type1 == "1"){
				tableHtml +="	<td align=\"center\" >��֯</td>\n";
			}else{
				tableHtml +="	<td align=\"center\" ><div title=\"������֯��&#13;&#10;" + department1 + "\">��Ա</div></td>\n";
			}
	        tableHtml +="	<td height=\"22\" class=\"text-01\" align=\"center\">" + name1 + "</td>\n";
		}
        tableHtml +="</tr>\n";
        return tableHtml;
	}
	/**
	 * �õ�Table�Ľ���
	 */
	function getTableEnd(){
		return "</table>\n";
	}
	/**
	 * �õ�ҳ���������Ѿ�ѡ�����
	 */
	function getAllPerson(select){
		var perList = new Array();
		var personList = document.getElementsByName("personList");
		if (personList == null || personList.length==0){
			return perList ;
		}
		var personValues = personList[0].getElementsByTagName("td");
		for (var i=0;i<personValues.length;i++){
			if (select != null && select == true ){
				var personData = new Array();
				var isData = false;
				perOneObj = personValues[i].getElementsByTagName("input");
				for(var mm=0;mm<perOneObj.length;mm++){
					if (perOneObj[mm].name == "cnname"){
						personData["name"] = perOneObj[mm].value;
					}
					if (perOneObj[mm].name == "personid"){
						personData["value"] = perOneObj[mm].value;
						if (!perOneObj[mm].checked){
							isData = true;
						}
					}
					if (perOneObj[mm].name == "department"){
						personData["department"] = perOneObj[mm].value;
					}
					if (perOneObj[mm].name == "perType"){
						personData["type"] = perOneObj[mm].value;
					}
					if (perOneObj[mm].name == "uuid"){
						personData["uuid"] = perOneObj[mm].value;
					}
				}
				if (isData){
					perList[perList.length] = personData;
				}

			}else{
			
				var personData = new Array();
				var isData = false;
				perOneObj = personValues[i].getElementsByTagName("input");
				for(var mm=0;mm<perOneObj.length;mm++){
					
					if (perOneObj[mm].name == "cnname"){
						personData["name"] = perOneObj[mm].value;
					}
					if (perOneObj[mm].name == "personid"){
						personData["value"] = perOneObj[mm].value;
						isData = true;
					}
					if (perOneObj[mm].name == "department"){
						personData["department"] = perOneObj[mm].value;
					}
					if (perOneObj[mm].name == "uuid"){
						personData["uuid"] = perOneObj[mm].value;
					}
					if (perOneObj[mm].name == "perType"){
						personData["type"] = perOneObj[mm].value;
					}
				}
				if (isData){
					perList[perList.length] = personData;
				}
			}
		}
		return perList;
	}
	/**
	 * û��ѡ����Ա����ʾ
	 */
	function setNonePerson(){
		var tableHtml = getTableHead(2);
		tableHtml += getLine();
		tableHtml +="<tr><td align=\"center\" height=\"22\" colspan=\"12\"><p  class=\"title-04\">û��ѡ����Ա����</p></td></tr>\n";
		tableHtml += getLine(true);
		tableHtml += getTableEnd();
		data.innerHTML = tableHtml;
	}
	/**
	 * ɾ��ѡ�����
	 */
	 function removeSelect(){
		setPersons(new Array(),true);
	 }

	 /**
	  * �ύѡ�����
	  */
	 function selectToCommit(){
	 	window.top.setSelect(getAllPerson());
	 	closeMe();
	 }
	 /**
	  * �õ������������б��������˲������б�
	  * param persons �б��ַ��� ��aa��ss��
	  */
	 function getNoSelectPerson(persons){
		var perList = new Array();
		var personList = document.getElementsByName("personList");
		if (personList == null || personList.length==0){
			return perList ;
		}
		var personValues = personList[0].getElementsByTagName("td");
		for (var i=0;i<personValues.length;i++){
			var personData = new Array();
			var isData = false;
			perOneObj = personValues[i].getElementsByTagName("input");
			for(var mm=0;mm<perOneObj.length;mm++){
				
				if (perOneObj[mm].name == "cnname"){
					personData["name"] = perOneObj[mm].value;
				}
				if (perOneObj[mm].name == "personid"){
					var oneId = perOneObj[mm].value;
					personData["value"] = oneId;
					if (persons.indexOf("��" + oneId + "��") == -1){
						isData = true;
					}
				}
				if (perOneObj[mm].name == "department"){
					personData["department"] = perOneObj[mm].value;
				}
				if (perOneObj[mm].name == "uuid"){
					personData["uuid"] = perOneObj[mm].value;
				}
				if (perOneObj[mm].name == "perType"){
					personData["type"] = perOneObj[mm].value;
				}
			}
			if (isData){
				perList[perList.length] = personData;
			}
		}
		clearAndSetPersons(perList);
	}
	/**
	 * �����ǰ������Ȼ���û��Ž�table
	 */
	function clearAndSetPersons(perList){
		var row = perList.length;
		var pan1 = (row/2);
		var pan2 = (row%2);
		if (pan2 != 0){
			pan1 += 0.5;
		}
		row = (pan1+1)*2;
		var tableHtml = getTableHead(row);
		tableHtml += getLine();
		if (pan1==0){
			setNonePerson();
			return;
		}

		var linObj = document.getElementsByName("rowLine");
		for (var i=0;i<linObj.length;i++){
			linObj.rowspan = row;
		}
		//ȡ�����е��˵�id��Ϊ������
		var allDataId = ",";
		for(var ii=0;ii<perList.length;ii++){
			allDataId += perList[ii]["value"] + ",";
		}
		for(var i=0;i<perList.length;i++){
			var name = perList[i]["name"];
			var value = perList[i]["value"];
			var department = perList[i]["department"];
			var uuid = perList[i]["uuid"];
			var type = perList[i]["type"];
			i++;
			var name1 = null;
			var value1 = null;
			var department1 = null;
			var type1 = null;
			var uuid1 = null;
			if (i<perList.length){
				name1 = perList[i]["name"];
				value1 = perList[i]["value"];
				uuid1 = perList[i]["uuid"];
				department1 = perList[i]["department"];
				type1 = perList[i]["type"];
			}
			tableHtml += getTwoPerson(name,value,department,type,name1,value1,department1,type1);
			if (color == color0){
				color = color1;
			}else{
				color = color0;
			}
			tableHtml += getLine();
		}
		tableHtml += getTableEnd();
		data.innerHTML = tableHtml;
		
	}
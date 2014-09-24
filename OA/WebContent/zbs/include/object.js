/**
 * List����
 */
function List(){
	this.array = new Array();
	/**
	 * ����
	 * @return ����int
	 */
	this.size = function(){
		return this.array.length;
	}
	/**
	 * �õ���i��ֵ
	 * @param i index
	 * @return ֵ
	 */
	this.get = function(i){
		return this.array[i];
	}
	this.add = function(obj){}
}
/**
 * ArrayList����
 */
function ArrayList(){
	this.base = List;
	this.base();
	/**
	 * ���
	 * @param obj ����
	 */
	this.add = function(obj){
		this.array[this.size()] = obj;
	}
	/**
	 * ��ȥһ��ֵ
	 * @param index index
	 */
	this.remove = function (index){
		var tempArray = new Array();
		for (var i=0;i<this.array.length;i++){
			if (i != index){
				tempArray[tempArray.length] = this.array[i];
			}
		}
		this.keys = tempArray;
	}
	/**
	 * ת��Ϊ����
	 * @return ����
	 */	
	this.toArray = function(obj){
		return this.array;
	}
}
/**
 * Map����
 */
function Map(){
	this.values = new Array();
	this.keys = new Array();
	/**
	 * ��Map�е�Keyת��Ϊ����
	 * @return key������
	 */
	this.keyArray = function (){
		return this.keys;
	}
	/**
	 * �õ�Key��ֵ
	 * @param name key
	 * @return key��ֵ
	 */
	this.get = function(name){
		return this.values[name];
	}
	/**
	 * �õ���i��key
	 * @param i index
	 * @return key
	 */
	this.getKey = function(i){
		return this.keys[i];
	}
	/**
	 * ����
	 * @return ����int
	 */
	this.size = function (){
		return this.keys.length;
	}
	this.put = function (key,value){}
}
/**
 * HashMap����
 */
function HashMap(){
	this.base = Map;
	this.base();
	/**
	 * ����һ������
	 * @param key
	 * @param value
	 */
	this.put = function (key,value){
		this.values[key] = value;
		if (this.values[key]==null){
			this.keys[this.size()] = key;
		}
		this.values[key] = "";
	}
	/**
	 * ��ȥһ��key
	 * @param key key
	 */
	this.remove = function (key){
		var tempArray = new Array();
		for (var i=0;i<this.keys.length;i++){
			if (keys[i] != key){
				tempArray[tempArray.length] = keys[i];
			}
		}
		this.keys = tempArray;
	}
	/**
	 * ת��Ϊ�ַ���
	 * @param split �ָ�����Ĭ��Ϊ","
	 * @return �Էָ����ָ���ַ��� key=value,key=value
	 */
	this.toString = function(split){
		var temStr = "";
		if (split==null)
			split = ",";
		for (var i=0;i<this.size();i++){
			var tempValue = this.get(this.getKey(i));
			if (tempValue != ""){
				if (temStr == "")
					temStr = this.getKey(i) + "=" + this.get(this.getKey(i));
				else
					temStr += split + this.getKey(i) + "=" + this.get(this.getKey(i));
			}
		}
		return temStr;
	}
	/**
	 * ת��Ϊ�ַ���
	 * @param split �ָ�����Ĭ��Ϊ","
	 * @return �Էָ����ָ���ַ���key,key
	 */
	this.splitKey = function(split){
		var temStr = "";
		if (split==null)
			split = ",";
		for (var i=0;i<this.size();i++){
			var temKey = this.getKey(i);
			if (temKey != ""){
				if (temStr == "")
					temStr = this.getKey(i);
				else
					temStr += split + this.getKey(i);
			}
		}
		return temStr;
	}
	/**
	 * ת��Ϊ�ַ���
	 * @param split �ָ�����Ĭ��Ϊ","
	 * @return �Էָ����ָ���ַ���value,value
	 */
	this.splitValue = function(split){
		var temStr = "";
		if (split==null)
			split = ",";
		for (var i=0;i<this.size();i++){
			var tempValue = this.get(this.getKey(i));
			if (tempValue != ""){
				if (temStr == "")
					temStr = this.get(this.getKey(i));
				else
					temStr += split + this.get(this.getKey(i));
			}
		}
		return temStr;
	}
}

 /**
  * ��nullȥ��
  */
 function strNull(obj){
 	if (obj==null)
 		return "";
 	return obj;
 }
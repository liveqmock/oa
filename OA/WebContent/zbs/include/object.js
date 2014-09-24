/**
 * List对象
 */
function List(){
	this.array = new Array();
	/**
	 * 长度
	 * @return 长度int
	 */
	this.size = function(){
		return this.array.length;
	}
	/**
	 * 得到第i个值
	 * @param i index
	 * @return 值
	 */
	this.get = function(i){
		return this.array[i];
	}
	this.add = function(obj){}
}
/**
 * ArrayList对象
 */
function ArrayList(){
	this.base = List;
	this.base();
	/**
	 * 添加
	 * @param obj 对象
	 */
	this.add = function(obj){
		this.array[this.size()] = obj;
	}
	/**
	 * 除去一个值
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
	 * 转换为数组
	 * @return 数组
	 */	
	this.toArray = function(obj){
		return this.array;
	}
}
/**
 * Map对象
 */
function Map(){
	this.values = new Array();
	this.keys = new Array();
	/**
	 * 将Map中的Key转换为数组
	 * @return key的数组
	 */
	this.keyArray = function (){
		return this.keys;
	}
	/**
	 * 得到Key的值
	 * @param name key
	 * @return key的值
	 */
	this.get = function(name){
		return this.values[name];
	}
	/**
	 * 得到第i个key
	 * @param i index
	 * @return key
	 */
	this.getKey = function(i){
		return this.keys[i];
	}
	/**
	 * 长度
	 * @return 长度int
	 */
	this.size = function (){
		return this.keys.length;
	}
	this.put = function (key,value){}
}
/**
 * HashMap对象
 */
function HashMap(){
	this.base = Map;
	this.base();
	/**
	 * 加入一个对象
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
	 * 除去一个key
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
	 * 转换为字符串
	 * @param split 分隔符，默认为","
	 * @return 以分隔符分割的字符串 key=value,key=value
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
	 * 转换为字符串
	 * @param split 分隔符，默认为","
	 * @return 以分隔符分割的字符串key,key
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
	 * 转换为字符串
	 * @param split 分隔符，默认为","
	 * @return 以分隔符分割的字符串value,value
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
  * 将null去掉
  */
 function strNull(obj){
 	if (obj==null)
 		return "";
 	return obj;
 }
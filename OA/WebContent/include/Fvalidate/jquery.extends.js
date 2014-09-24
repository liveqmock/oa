jQuery.extend(jQuery.validator.messages, {  
    required: "必选字段",  
    remote: "请修正该字段",  
    email: "请输入正确格式的电子邮件",  
    url: "请输入合法的网址",  
    date: "请输入合法的日期",  
    dateISO: "请输入合法的日期 (ISO).",  
    number: "请输入合法的数字",  
    digits: "只能输入整数",  
    creditcard: "请输入合法的信用卡号",  
    equalTo: "请再次输入相同的值",  
    accept: "请输入拥有合法后缀名的字符串",  
    maxlength: jQuery.validator.format("允许的最大长度为 {0} 个字符"),  
    minlength: jQuery.validator.format("允许的最小长度为 {0} 个字符"),  
    rangelength: jQuery.validator.format("允许的长度为{0}和{1}之间"),  
    range: jQuery.validator.format("请输入介于 {0} 和 {1} 之间的值"),  
    max: jQuery.validator.format("请输入一个最大为 {0} 的值"),  
    min: jQuery.validator.format("请输入一个最小为 {0} 的值") 
});
    // 字符验证  
    jQuery.validator.addMethod("string", function(value, element) {  
        return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);  
    }, "不允许包含特殊符号!");
 
    // 只能输入中文  
    jQuery.validator.addMethod("stringCH", function(value, element) {  
        var length = value.length;  
        for(var i = 0; i < value.length; i++){  
            if(value.charCodeAt(i) > 127){  
                length++;  
            }  
        }  
        return this.optional(element) || /[^u4E00-u9FA5]/g.test(value);  
    }, "只能输入汉字,一个汉字占两具字节");  

    // 只能输入26个字母  
    jQuery.validator.addMethod("stringEN", function(value, element) {  
        var length = value.length;  
        for(var i = 0; i < value.length; i++){  
            if(value.charCodeAt(i) > 127){  
                length++;  
            }  
        }  
        alert(length);  
        return this.optional(element) || /^[A-Za-z]+$/g.test(value);  
    }, "只能输入字母");  

    //ip地址
    jQuery.validator.addMethod("ip", function(value, element) {  
		return this.optional(element) || (/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));  
	}, "请输入正确的IP地址!");
	
	//port
    jQuery.validator.addMethod("port", function(value, element) {  
		return this.optional(element) || (value < 65536 && value > 0);  
	}, "请输入正确的端口号!"); 
	
	// 邮政编码验证
	jQuery.validator.addMethod("postalcode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "邮政编码格式不对"); 
	
	// 手机号码验证
	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		//长度为11，以13，15，18开头的
		return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
	}, "手机号码格式不对"); 
	
	//字母数字
	jQuery.validator.addMethod("alnum", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	}, "只能包括英文字母和数字");
	
	//自然数
	jQuery.validator.addMethod("naturalnum", function(value, element) {
		return this.optional(element) || (/^[0-9]+$/.text(value) && (value > 0));
	}, "只能输入自然数");
	
	//身份证号码
	jQuery.validator.addMethod("idcardno", function(value, element) {
		return this.optional(element) || isIdCardNo(value);   
	}, "请正确输入身份证号码");
	
/**
 * 身份证号码验证
 *
 */
function isIdCardNo(num) {
 var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
 var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");
 var varArray = new Array();
 var intValue;
 var lngProduct = 0;
 var intCheckDigit;
 var intStrLen = num.length;
 var idNumber = num;
   // initialize
     if ((intStrLen != 15) && (intStrLen != 18)) {
         return false;
     }
     // check and set value
     for(i=0;i<intStrLen;i++) {
         varArray[i] = idNumber.charAt(i);
         if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
             return false;
         } else if (i < 17) {
             varArray[i] = varArray[i] * factorArr[i];
         }
     }
     
     if (intStrLen == 18) {
         //check date
         var date8 = idNumber.substring(6,14);
         if (isDate8(date8) == false) {
            return false;
         }
         // calculate the sum of the products
         for(i=0;i<17;i++) {
             lngProduct = lngProduct + varArray[i];
         }
         // calculate the check digit
         intCheckDigit = parityBit[lngProduct % 11];
         // check last digit
         if (varArray[17] != intCheckDigit) {
             return false;
         }
     }
     else{        //length is 15
         //check date
         var date6 = idNumber.substring(6,12);
         if (isDate6(date6) == false) {

             return false;
         }
     }
     return true;
     
 }
 $.template("optionTmpl",
        '<option value="${value}">${name}</option>');
$.template("checkboxTmpl",
        '<label><span>${label}</span><input type="checkbox" name="${name}" value="${value}"/></label>');
$.template("radioTmpl",
        '<label><span>${label}</span><input type="radio" name="${name}" value="${value}"/></label>');
$.template("notFound",
        '<div><h1>The data you wanted can\'t be found, please try again!</h1></div>');
$.template("btnTmpl",
        '<button type="button">${label}</button>');
$.template("textTmpl",
        '<label><span>${label}</span><input type="text" name="${name}" value="${value}"/></label>');
$.template("hiddenTmpl",
        '<input {{if id}} id="${id}" {{/if}} type="hidden" name="${name}" value="${value}"/>');
$.template("avatarTmpl",
        '{{if url}}<a href="{{= url}}">{{/if}}<dl><dt title="{{= email}}"></dt><dd>{{= username}}</dd></dl>{{if url}}</a>{{/if}}');
$.template("editBtnTmpl",
        '<button {{if id}}id="edit-{{= id}}"{{/if}} action="edit">Edit</button>');
$.template("delBtnTmpl",
        '<button {{if id}}id="delete-{{= id}}"{{/if}} action="del">Delete</button>');
$.template("addBtnTmpl",
        '<button {{if id}}id="add-{{= id}}"{{/if}} action="add">Add</button>');
$.template("listCItems",
        '<li><a href="#" action="topic" id="item-${id}">${title}</a><p>by ${author} at <span class="timestamp" title="${dateCreated}"></span></p></li>');
$.template("listItems",
        '<li><a href="#" action="topic" id="item-${id}">${title}</a>by ${author} at <span class="timestamp" title="${dateCreated}"></span></li>');
$.template("anchorTmpl",
        '<li><a href="#${id}">${label}</a></li>');
$.template("crumbItems",
        '<li><a href="#" {{if id}}id="${id}"{{/if}} {{if goto}}goto="${goto}"{{/if}} {{if action}}action="${action}"{{/if}} title="${description}">${label}</a></li>');
$.template("boxTmpl",
        '<div {{if id}}id="${id}"{{/if}}><h3>{{if wrap}}<strong>${label}</strong>{{else}}${label}{{/if}}{{if goto}}<a href="#" goto="${goto}">more ${String.toLowerCase(label)}</a>{{/if}}</h3>{{if tag}}<${tag}></${tag}>{{/if}}</div>');

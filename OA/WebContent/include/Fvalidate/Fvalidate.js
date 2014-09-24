/**
 * Fvalidate v1.0 - A validation plugin for jQuery
 * Copyright (C) 2010  Hasan Hameed
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Fvalidate v1.0.1
 * by Hasan Hameed - theculpritz@hotmail.com
 *
 * e.g. HTML
 * <form id="myform" name="myform">
 * <input name="userid" type="text" required="yes" maxlength="20" minlength="8" rule="alpha" />
 * <input name="conf-userid" type="text" required="yes" /><span class="err-msg">userid doesn't match</span>
 * <input name="address" type="text" required="yes" />
 * <input type="submit" value="Submit>
 * </form>
 *
 * e.g. Javascript - shiuld be inside document.ready jQuery function
 * $("#myform").Fvalidate();
 * $("input[name=conf-userid]").sameAs($("input[name=userid]"));
 * $("input[name=address]").rule(/^[a-z0-9-\s]*$/);
 */

(function($) {

	var _errors = [],
	//modified by liuyong
	//_rules = [],
	_sameAs = [];

	$.fn.Fvalidate = function(settings)
	{
		var methods = {

			/* Set error 
			 * @param string [form id], string [message]
			 */
			setError:	function(msg) {
				var name = $(this).attr("name"),
				fid = $(this).parents("form:last").attr("id");
				_errors[fid][name] = [];
				_errors[fid][name]['elem'] = this;
				_errors[fid][name]['msg'] = msg;
				return this.changeClass(options.focusClass,options.errorClass);
			},

			/* Remove error */
			removeError:	function() {
				var fid = $(this).parents("form:last").attr("id");
				delete _errors[fid][$(this).attr("name")];
				if(this.hasClass('inp-error'))
					return this.changeClass(options.errorClass,options.focusClass);
			},

			/* showErrors - Show errors */
			showErrors:	function() {
				if(options.alertErrors){
					var s = '输入有误，请您确认后重新输入:\n', fid = $(this).attr("id");
					for(x in _errors[fid])
						s += "\n " + _errors[fid][x]['msg']+'.';
					alert(s);
				}
				return this;
			},

			/* countErrors - Get no. of errors */
			countErrors:	function() {
				var c = 0,x;
				for(x in _errors[$(this).attr("id")])
					c++;
				return c;
			},

			/* rule - adds new rule for jquery element(s) to 'rule' attribute
			 * @param regex or 'alpha','numeric','email'
			 */
			rule:	function(mask) {
				this.each(function(){
					$(this).attr('rule',mask);
				});
				return this;
			},

			/* sameAs - Check for same field values
			 * @param single jquery element
			 */
			sameAs:	function(elem) {
				_sameAs[$(this).attr('name')] = elem;
				return this;
			},

			changeClass:	function(from,to) {
				return this.removeClass(from).addClass(to);
			}
		};

		$.each(methods, function(i) {
			$.fn[i] = this;
		});
		var defaults = {
			live:false,                                                          /* 设置校验方式 true 为及时校验，false为点击提交按钮校验*/
			elements:null,                                                    /* 设置要校验的页面元素 */
			alertErrors:	true,												/* alert error messages */
			attrForError:	"error",											/* 无效值错误提示 */
			attrForLabel:	"label",											/* 输入框的名称 */
			focusClass:		"inp-focus",										/* 输入框获取焦点时样式 */
			errorClass: 	"inp-error",										/* 输入框出错时的样式 */
			errorMsgClass:	"err-msg"											/* 错误提示消息样式 ，如果你这样用的话<span class="err-msg">userid doesn't match</span>*/
		};
		var options = $.extend(defaults, settings);
		var $form = $(this);
		var formId = $form.attr("id");
		var $fields=null;
		if(options.elements!=null){
			$fields=$(options.elements);	
		}else{
			$fields = $form.find("input, textarea, select").filter(":not(:submit)");	
		}

		_errors[formId] = [];
		//及时校验
		if(options.live){
			$fields.focus(function() {
				$(this).addClass(options.focusClass);
			});
			$fields.blur(function() {
				$(this).removeClass(options.focusClass);
			});
	
			$fields.keyup(function(){
				_validate(this);
			});
		}else{
			if(_validateForm()) {
				return true;
			}else{
				return false;
			};
		}
	
		/**
		 * _validate - Form validation on submit
		 * @return boolean
		 */
		function _validateForm(){
			$fields.each(function(){
				_validate(this);	/* Validate each field */
			});
			/* Focus first error field */
			if($form.countErrors()>0){
				$form.showErrors();
				var x;
				for(x in _errors[formId]){
					_errors[formId][x]['elem'].focus(); break;
				}
				return false;
			}
			return true;
		};

		/**
		 * _validate - Input field validation
		 */
		function _validate(obj)
		{			
			var $input = $(obj);
			var errorMsg = $input.attr(options.attrForError);
			var labelMsg = $input.attr(options.attrForLabel);
			var val = $input.val();
			/* required => yes */
			if($input.attr("required")==''){
				if($input.is(":checkbox") || $input.is(":radio"))
					if (!$input.attr('checked') || !$input.is(':checked')){
						$input.setError('['+labelMsg+']:'+'必须选择一项'); return false; /* on error */
					}
				if(val=='' || val==undefined){
					$input.setError('['+labelMsg+']:'+'不能为空'); return false;
				}
			}

			/* Check for rules */
			var mask = $input.attr("rule") || false;
			if(mask){
				switch(mask){
				case 'alpha':mask = /^[a-zA-Z\s]*$/; break;
				case 'number': mask = /^[0-9\s]*$/; break;
				case 'float': mask = /^[0-9-\.\s]*$/; break;
				case 'alphaAndNumber': mask = /^[a-zA-Z-0-9\s]*$/; break;
				case 'email': mask = /^\w+@.+\.\w+$/g; break;
				case 'url': mask=/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;break;
				case 'ip': mask=/^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/;break;
				case 'safeWord': mask=/^[\u4e00-\u9fa5a-zA-Z0-9-\s-\.-\_]+$/;break;
				/**如果有新的实用正则，就加在这啦,名字取好点就可以了**/
				}
				if(val!='' && !new RegExp(mask).test(val)){
					if(errorMsg=='' || errorMsg== 'undefined'){
						errorMsg="输入格式不正确";
					}
					$input.setError('['+labelMsg+']:'+errorMsg); return false;
				}
			}
	
			/* Check for min. length */
			var minlen = $input.attr("minlength") || 0;
			if(minlen){
				var thislen = val.length;
				if(minlen>thislen){
					$input.setError('['+labelMsg+']:'+'长度必须大于'+minlen); return false;
				}
			}

			/* Check for Same as value against another element */
			var elem = _sameAs[$input.attr("name")] || false;
			if(elem){
				if( elem.val()!==val ){
					$input.setError('['+labelMsg+']:'+'两次输入不一致');
					$input.nextAll('.'+options.errorMsgClass).slideDown(); return false;
				}
				$input.nextAll('.'+options.errorMsgClass).slideUp();
			}
			/* No Error */
			$input.removeError();
		};

		

		return $form;
	};
})(jQuery);
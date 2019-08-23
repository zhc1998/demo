/*
 * Copyright 2008-2019 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 * 
 * JavaScript - Common
 * Version: 5.0
 */

var shopxx = {
	base: "",
	locale: "zh_CN"
};

var setting = {
	priceScale: 2,
	priceRoundType: "roundHalfUp",
	currencySign: "��",
	currencyUnit: "Ԫ",
	uploadMaxSize: 10,
	uploadImageExtension: "",
	uploadMediaExtension: "",
	uploadFileExtension: ""
};

var messages = {
	"shop.dialog.ok": "ȷ ��",
	"shop.dialog.cancel": "ȡ ��",
	"shop.dialog.deleteConfirm": "��ȷ��Ҫɾ����",
	"shop.dialog.clearConfirm": "��ȷ��Ҫ�����"
};

// UUID
var uuidChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
function uuid() {
	var r;
	var uuid = [];
	uuid[8] = uuid[13] = uuid[18] = uuid[23] = "-";
	uuid[14] = "4";
	
	for (i = 0; i < 36; i++) {
		if (!uuid[i]) {
			r = 0 | Math.random() * 16;
			uuid[i] = uuidChars[(i == 19) ? (r & 0x3) | 0x8 : r];
		}
	}
	return uuid.join("");
}

// ���Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		if (options.path == null) {
			options.path = "/";
		}
		if (options.domain == null) {
			options.domain = "";
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires != null ? "; expires=" + options.expires.toUTCString() : "") + (options.path != "" ? "; path=" + options.path : "") + (options.domain != "" ? "; domain=" + options.domain : "") + (options.secure != null ? "; secure" : "");
	}
}

// ��ȡCookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// �Ƴ�Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// Htmlת��
function escapeHtml(str) {
	return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}

// �ַ�������
function abbreviate(str, width, ellipsis) {
	if ($.trim(str) == "" || width == null) {
		return str;
	}
	var i = 0;
	for (var strWidth = 0; i < str.length; i++) {
		strWidth = /^[\u4e00-\u9fa5\ufe30-\uffa0]$/.test(str.charAt(i)) ? strWidth + 2 : strWidth + 1;
		if (strWidth >= width) {
			break;
		}
	}
	return ellipsis != null && i < str.length - 1 ? str.substring(0, i) + ellipsis : str.substring(0, i);
}

// ���Ҹ�ʽ��
function currency(value, showSign, showUnit) {
	if (value != null) {
			var price = (Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2)).toFixed(2);
		if (showSign) {
			price = '��' + price;
		}
		if (showUnit) {
			price += 'Ԫ';
		}
		return price;
	}
}

// ������
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			}
		}
	}
}

(function($) {

	// �����
	$.alert = function() {
		var type = arguments.length >= 2 ? arguments[0] : null;
		var message = arguments.length >= 2 ? arguments[1] : arguments[0];
		var alertClass;
		switch(type) {
			case "success":
				alertClass = "alert-success";
				break;
			case "info":
				alertClass = "alert-info";
				break;
			case "warning":
				alertClass = "alert-warning";
				break;
			case "danger":
				alertClass = "alert-danger";
				break;
			default:
				alertClass = "alert-dark";
		}
		var $alert = $('<div class="growl animated fadeInDown alert' + (alertClass != null ? ' ' + alertClass : '') + ' alert-dismissible"><button class="close" type="button"><span>&times;<\/span><\/button>' + message + '<\/div>').appendTo("body");
		$alert.find("button.close").click(function() {
			$alert.remove();
		});
		setTimeout(function() {
			$alert.remove();
		}, 3000);
	}
	
	// �ض����¼ҳ��
	$.redirectLogin = function(redirectUrl) {
		var loginUrl = "/member/login";
		if ($.trim(redirectUrl) != "") {
			var redirectToken = uuid();
			addCookie("redirectToken", redirectToken);
			loginUrl += "?redirectUrl=" + encodeURIComponent(redirectUrl) + "&redirectToken=" + encodeURIComponent(redirectToken);
		}
		location.href = loginUrl;
	}

})(jQuery);

// ��֤��ͼƬ
(function($) {

	var methods = {
		init: function(options) {
			return this.each(function() {
				var element = this;
				var $element = $(element);
				var captchaId = uuid();
				
				var defaults = {
					captchaIdParameterName: "captchaId",
					imgClass: "captchaImage",
					imgSrc: function(captchaIdParameterName, captchaId) {
						return "/common/captcha/image?" + settings.captchaIdParameterName + '=' + captchaId + '&timestamp=' + new Date().getTime();
					},
					imgTitle: "���������֤��",
					imgPlacement: function($captchaImage) {
						var $element = $(this);
						$inputGroupBtn = $element.nextAll(".input-group-btn");
						if ($inputGroupBtn.size() > 0) {
							$captchaImage.appendTo($inputGroupBtn);
						} else {
							$captchaImage.insertAfter($element);
						}
					}
				}
				settings = $.extend({}, defaults, options);
				
				var refresh = function(clearValue) {
					if (clearValue) {
						$element.val("");
					}
					$captchaImage.attr("src", $.isFunction(settings.imgSrc) ? settings.imgSrc.call(element, settings.captchaIdParameterName, captchaId) : settings.imgSrc);
				}
				$element.data("refresh", refresh);
				
				var $captchaId = $('<input name="' + settings.captchaIdParameterName + '" type="hidden" value="' + captchaId + '">').insertAfter($element);
				var $captchaImage = $('<img' + (settings.imgClass != null ? ' class="' + settings.imgClass + '"' : '') + ' src="' + ($.isFunction(settings.imgSrc) ? settings.imgSrc.call(element, settings.captchaIdParameterName, captchaId) : settings.imgSrc) + '"' + (settings.imgTitle != null ? ' title="' + settings.imgTitle + '"' : '') + '>');
				if ($.isFunction(settings.imgPlacement)) {
					settings.imgPlacement.call(element, $captchaImage);
				}
				$captchaImage.click(function() {
					refresh(true);
				});
			});
		},
		refresh: function(options) {
			return this.each(function() {
				var element = this;
				var $element = $(element);
				
				var refresh = $element.data("refresh");
				if (refresh != null) {
					refresh(options);
				}
			});
		}
	};
	
	$.fn.captchaImage = function() {
		var method = arguments[0];
		
		if (methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof(method) == "object" || !method) {
			method = methods.init;
		}
		return method.apply(this, arguments);
	}

})(jQuery);

$().ready(function() {

	var $window = $(window);
	var $goTop = $('<div class="goTop"><\/div>').appendTo("body");
	var $top = $('<a href="javascript:;">&nbsp;<\/a>').appendTo($goTop);
	var $addFavorite = $('<a href="javascript:;">&nbsp;<\/a>').appendTo($goTop);
	
	// ���ض���
	$window.scroll(function() {
		if ($window.scrollTop() > 100) {
			$goTop.fadeIn();
		} else {
			$goTop.fadeOut();
		}
	});
	
	// ���ض���
	$top.click(function() {
		$("body, html").animate({scrollTop: 0});
	});
	
	// ����ղ�
	$addFavorite.click(function() {
		var title = document.title;
		var url = document.url;
		if (document.all) {
			window.external.addFavorite(url, title);
		} else if (window.sidebar && window.sidebar.addPanel) {
			window.sidebar.addPanel(title, url, "");
		} else {
			alert("��ʹ�� Ctrl+D �������");
		}
	});
	
	/*// ���ﳵ��Ϣ
	var cartInfo
	setInterval(function() {
		if (cartInfo == null || cartInfo.tag != getCookie("cartTag")) {
			$.ajax({
				url: "/cart/info",
				type: "GET",
				dataType: "json",
				success: function(data) {
					cartInfo = data;
					if (cartInfo.tag != null) {
						addCookie("cartTag", cartInfo.tag);
					} else {
						removeCookie("cartTag");
					}
					$window.trigger("cartInfoLoad", [cartInfo]);
				}
			});
		}
	}, 500);*/
	
	// AJAXȫ������
	$.ajaxSetup({
		traditional: true,
		statusCode: {
			401: function(xhr, textStatus, errorThrown) {
				var data = $.parseJSON(xhr.responseText);
				if (data.message != null) {
					$.alert("danger", data.message);
				}
				setTimeout(function() {
					$.redirectLogin(location.href);
				}, 3000);
			},
			403: function(xhr, textStatus, errorThrown) {
				var data = $.parseJSON(xhr.responseText);
				if (data.message != null) {
					$.alert("danger", data.message);
				}
			},
			422: function(xhr, textStatus, errorThrown) {
				var data = $.parseJSON(xhr.responseText);
				if (data.message != null) {
					$.alert("warning", data.message);
				}
			}
		}
	});

	// AJAXȫ������
	$(document).ajaxSuccess(function(event, xhr, settings, data) {
		if (data.message != null) {
			$.alert(data.message);
		}
	});
	
	// CSRF����
	$("form").submit(function() {
		var $element = $(this);
		if (!/^(GET|HEAD|TRACE|OPTIONS)$/i.test($element.attr("method")) && $element.find("input[name='csrfToken']").size() == 0) {
			var csrfToken = getCookie("csrfToken");
			if (csrfToken != null) {
				$element.append('<input name="csrfToken" type="hidden" value="' + csrfToken + '">');
			}
		}
	});
	
	// CSRF����
	$(document).ajaxSend(function(event, xhr, settings) {
		if (!settings.crossDomain && !/^(GET|HEAD|TRACE|OPTIONS)$/i.test(settings.type)) {
			var csrfToken = getCookie("csrfToken");
			if (csrfToken != null) {
				xhr.setRequestHeader("X-Csrf-Token", csrfToken);
			}
		}
	});

});

// ��֤
if ($.validator != null) {

	$.extend($.validator.messages, {
		required: '����',
		email: 'E-mail��ʽ����',
		url: '��ַ��ʽ����',
		date: '���ڸ�ʽ����',
		dateISO: '���ڸ�ʽ����',
		pointcard: '���ÿ���ʽ����',
		number: 'ֻ������������',
		digits: 'ֻ�����������������',
		minlength: $.validator.format('���Ȳ�����С��{0}'),
		maxlength: $.validator.format('���Ȳ��������{0}'),
		rangelength: $.validator.format('���ȱ�����{0}-{1}֮��'),
		min: $.validator.format('������С��{0}'),
		max: $.validator.format('���������{0}'),
		range: $.validator.format('������{0}-{1}֮��'),
		accept: '�����׺����',
		equalTo: '�������벻һ��',
		remote: '�������',
		integer: 'ֻ������������',
		positive: 'ֻ������������',
		negative: 'ֻ�������븺��',
		decimal: '��ֵ����������Χ',
		pattern: '��ʽ����',
		extension: '�ļ���ʽ����'
	});
	
	$.validator.setDefaults({
		errorClass: "fieldError",
		ignore: ".ignore",
		ignoreTitle: true,
		errorPlacement: function($error, $element) {
			var $fieldSet = $element.closest("span.fieldSet");
			if ($fieldSet.size() > 0) {
				$error.appendTo($fieldSet);
			} else {
				$error.insertAfter($element);
			}
		},
		submitHandler: function(form) {
			$(form).find("input:submit").prop("disabled", true);
			form.submit();
		}
	});

}
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
	"admin.dialog.ok": "ȷ ��",
	"admin.dialog.cancel": "ȡ ��",
	"admin.dialog.deleteConfirm": "��ȷ��Ҫɾ����",
	"admin.dialog.clearConfirm": "��ȷ��Ҫ�����"
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

	var zIndex = 100;
	
	// ��Ϣ��
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}
		
		if (message.type == null || message.content == null) {
			return false;
		}
		
		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + escapeHtml(message.type) + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}
		
		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();
		
		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	};
	
	// �Ի���
	$.dialog = function(options) {
		var settings = {
			width: 320,
			height: "auto",
			modal: true,
			ok: 'ȷ ��',
			cancel: 'ȡ ��',
			onShow: null,
			onClose: null,
			onOk: null,
			onCancel: null
		};
		$.extend(settings, options);
		
		if (settings.content == null) {
			return false;
		}
		
		var $dialog = $('<div class="xxDialog"><\/div>');
		var $dialogTitle;
		var $dialogClose = $('<div class="dialogClose"><\/div>').appendTo($dialog);
		var $dialogContent;
		var $dialogBottom;
		var $dialogOk;
		var $dialogCancel;
		var $dialogOverlay;
		if (settings.title != null) {
			$dialogTitle = $('<div class="dialogTitle"><\/div>').appendTo($dialog);
		}
		if (settings.type != null) {
			$dialogContent = $('<div class="dialogContent dialog' + escapeHtml(settings.type) + 'Icon"><\/div>').appendTo($dialog);
		} else {
			$dialogContent = $('<div class="dialogContent"><\/div>').appendTo($dialog);
		}
		if (settings.ok != null || settings.cancel != null) {
			$dialogBottom = $('<div class="dialogBottom"><\/div>').appendTo($dialog);
		}
		if (settings.ok != null) {
			$dialogOk = $('<input type="button" class="button" value="' + escapeHtml(settings.ok) + '" \/>').appendTo($dialogBottom);
		}
		if (settings.cancel != null) {
			$dialogCancel = $('<input type="button" class="button" value="' + escapeHtml(settings.cancel) + '" \/>').appendTo($dialogBottom);
		}
		if (!window.XMLHttpRequest) {
			$dialog.append('<iframe class="dialogIframe"><\/iframe>');
		}
		$dialog.appendTo("body");
		if (settings.modal) {
			$dialogOverlay = $('<div class="dialogOverlay"><\/div>').insertAfter($dialog);
		}
		
		var dragStart = {};
		var dragging = false;
		if (settings.title != null) {
			$dialogTitle.text(settings.title);
		}
		$dialogContent.html(settings.content);
		$dialog.css({"width": settings.width, "height": settings.height, "margin-left": - parseInt(settings.width / 2), "z-index": zIndex ++});
		dialogShow();
		
		if ($dialogTitle != null) {
			$dialogTitle.mousedown(function(event) {
				$dialog.css({"z-index": zIndex ++});
				var offset = $dialog.offset();
				dragStart.pageX = event.pageX;
				dragStart.pageY = event.pageY;
				dragStart.left = offset.left;
				dragStart.top = offset.top;
				dragging = true;
				return false;
			}).mouseup(function() {
				dragging = false;
			});
			
			$(document).mousemove(function(event) {
				if (dragging) {
					$dialog.offset({"left": dragStart.left + event.pageX - dragStart.pageX, "top": dragStart.top + event.pageY - dragStart.pageY});
					return false;
				}
			}).mouseup(function() {
				dragging = false;
			});
		}
		
		if ($dialogClose != null) {
			$dialogClose.click(function() {
				dialogClose();
				return false;
			});
		}
		
		if ($dialogOk != null) {
			$dialogOk.click(function() {
				if (settings.onOk && typeof settings.onOk == "function") {
					if (settings.onOk($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		if ($dialogCancel != null) {
			$dialogCancel.click(function() {
				if (settings.onCancel && typeof settings.onCancel == "function") {
					if (settings.onCancel($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		function dialogShow() {
			if (settings.onShow && typeof settings.onShow == "function") {
				if (settings.onShow($dialog) != false) {
					$dialog.show();
					$dialogOverlay.show();
				}
			} else {
				$dialog.show();
				$dialogOverlay.show();
			}
		}
		
		function dialogClose() {
			if (settings.onClose && typeof settings.onClose == "function") {
				if (settings.onClose($dialog) != false) {
					$dialogOverlay.remove();
					$dialog.remove();
				}
			} else {
				$dialogOverlay.remove();
				$dialog.remove();
			}
		}
		return $dialog;
	};
	
	$.fn.extend({
		
		// �ļ��ϴ�
		uploader: function(options) {
			var settings = {
				url: '/admin/file/upload',
				fileType: "image",
				fileName: "file",
				data: {},
				maxSize: 10,
				extensions: null,
				before: null,
				complete: null
			};
			$.extend(settings, options);
			
			if (settings.extensions == null) {
				switch(settings.fileType) {
					case "media":
						settings.extensions = '';
						break;
					case "file":
						settings.extensions = '';
						break;
					default:
						settings.extensions = '';
				}
			}
			
			var $progressBar = $('<div class="progressBar"><\/div>').appendTo("body");
			return this.each(function() {
				var element = this;
				var $element = $(element);
				
				var webUploader = WebUploader.create({
					swf: '/resources/admin/flash/webuploader.swf',
					server: settings.url + (settings.url.indexOf('?') < 0 ? '?' : '&') + 'fileType=' + settings.fileType + '&csrfToken=' + getCookie("csrfToken"),
					pick: {
						id: element,
						multiple: false
					},
					fileVal: settings.fileName,
					formData: settings.data,
					fileSingleSizeLimit: settings.maxSize * 1024 * 1024,
					accept: {
						extensions: settings.extensions
					},
					fileNumLimit: 1,
					auto: true
				}).on('beforeFileQueued', function(file) {
					if ($.isFunction(settings.before) && settings.before.call(element, file) === false) {
						return false;
					}
					if ($.trim(settings.extensions) == '') {
						this.trigger('error', 'Q_TYPE_DENIED');
						return false;
					}
					this.reset();
					$progressBar.show();
				}).on('uploadProgress', function(file, percentage) {
					$progressBar.width(percentage * 100 + '%');
				}).on('uploadAccept', function(file, data) {
					$progressBar.fadeOut("slow", function() {
						$progressBar.width(0);
					});
					if (data.message.type != 'success') {
						$.message(data.message);
						return false;
					}
					$element.prev("input:text").val(data.url);
					if ($.isFunction(settings.complete)) {
						settings.complete.call(element, file, data);
					}
				}).on('error', function(type) {
					switch(type) {
						case "F_EXCEED_SIZE":
							$.message("warn", "�ϴ��ļ���С��������");
							break;
						case "Q_TYPE_DENIED":
							$.message("warn", "�ϴ��ļ���ʽ����ȷ");
							break;
						default:
							$.message("warn", "�ϴ��ļ����ִ���");
					}
				});
				
				$element.mouseover(function() {
					webUploader.refresh();
				});
			});
		},
		
		// �༭��
		editor: function(options) {
			window.UEDITOR_CONFIG = {
				UEDITOR_HOME_URL: '/resources/admin/ueditor/',
				serverUrl: '/admin/file/upload',
				imageActionName: "uploadImage",
				imageFieldName: "file",
				imageMaxSize: 10485760,
				imageAllowFiles: [],
				imageUrlPrefix: "",
				imagePathFormat: "",
				imageCompressEnable: false,
				imageCompressBorder: 1600,
				imageInsertAlign: "none",
				videoActionName: "uploadMedia",
				videoFieldName: "file",
				videoMaxSize: 10485760,
				videoAllowFiles: [],
				videoUrlPrefix: "",
				videoPathFormat: "",
				fileActionName: "uploadFile",
				fileFieldName: "file",
				fileMaxSize: 10485760,
				fileAllowFiles: [],
				fileUrlPrefix: "",
				filePathFormat: "",
				toolbars: [[
					'fullscreen', 'source', '|',
					'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',
					'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
					'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
					'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
					'directionalityltr', 'directionalityrtl', 'indent', '|',
					'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
					'touppercase', 'tolowercase', '|',
					'link', 'unlink', 'anchor', '|',
					'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
					'insertimage', 'insertvideo', 'attachment', 'map', 'insertframe', 'pagebreak', '|',
					'horizontal', 'date', 'time', 'spechars', '|',
					'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
					'print', 'preview', 'searchreplace', 'drafts'
				]],
				lang: 'zh_CN',
				iframeCssUrl: null,
				pageBreakTag: 'shopxx_page_break_tag',
				wordCount: false
			};
			
			UE.Editor.prototype.getActionUrl = function(action) {
				var serverUrl = this.getOpt('serverUrl');
				switch(action) {
					case "uploadImage":
						return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=image';
					case "uploadMedia":
						return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=media';
					case "uploadFile":
						return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=file';
				}
				return null;
			};
			
			UE.Editor.prototype.loadServerConfig = function() {
				this._serverConfigLoaded = true;
			};
			
			return this.each(function() {
				var element = this;
				var $element = $(element);
				
				UE.getEditor($element.attr("id"), options).ready(function() {
					this.execCommand("serverparam", {
						csrfToken: getCookie("csrfToken")
					});
				});
			});
		}
	
	});

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
					imgClass: "captcha-image",
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
				
				var $captchaId = $('<input type="hidden" name="' + settings.captchaIdParameterName + '" value="' + captchaId + '" \/>').insertAfter($element);
				var $captchaImage = $('<img' + (settings.imgClass != null ? ' class="' + settings.imgClass + '"' : '') + ' src="' + ($.isFunction(settings.imgSrc) ? settings.imgSrc.call(element, settings.captchaIdParameterName, captchaId) : settings.imgSrc) + '"' + (settings.imgTitle != null ? ' title="' + settings.imgTitle + '"' : '') + ' \/>');
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

	// AJAXȫ������
	$.ajaxSetup({
		traditional: true,
		statusCode: {
			401: function(xhr, textStatus, errorThrown) {
				var data = $.parseJSON(xhr.responseText);
				if (data.message != null) {
					$.message("warn", data.message);
				}
				setTimeout(function() {
					location.reload(true);
				}, 3000);
			},
			403: function(xhr, textStatus, errorThrown) {
				var data = $.parseJSON(xhr.responseText);
				if (data.message != null) {
					$.message("warn", data.message);
				}
			},
			422: function(xhr, textStatus, errorThrown) {
				var data = $.parseJSON(xhr.responseText);
				if (data.message != null) {
					$.message("warn", data.message);
				}
			}
		}
	});
	
	// CSRF����
	$("form").submit(function() {
		var $element = $(this);
		if (!/^(GET|HEAD|TRACE|OPTIONS)$/i.test($element.attr("method")) && $element.find("input[name='csrfToken']").size() == 0) {
			var csrfToken = getCookie("csrfToken");
			if (csrfToken != null) {
				$element.append('<input type="hidden" name="csrfToken" value="' + csrfToken + '" \/>');
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
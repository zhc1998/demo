/*
 * Copyright 2008-2019 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 * 
 * JavaScript - Input
 * Version: 5.0
 */

$().ready( function() {

	if ($.tools != null) {
		var $tab = $("#tab");
		var $title = $("#inputForm :input[title], #inputForm label[title]");
		
		// TabЧ��
		$tab.tabs("table.tabContent, div.tabContent", {
			tabs: "input"
		});
		
		// ����ʾ
		$title.tooltip({
			position: "bottom right",
			effect: "fade"
		});
	}

});
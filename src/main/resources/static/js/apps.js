// angularjs params
var app = angular.module('AppsApp', []);
app.controller('AppsController', function($scope, $http) {
	$scope.listAppInfo = {};
	$scope.listAppInfoHelper = {};
	$scope.pageLimit = 5;
	$scope.pageLength = 10;
	
});

$(document).ready(function() {
	
	getAppsInfo(1);
	$("#li_chevron_left").val("<");
	$("#li_chevron_right").val(">");
	$("#li_chevron_last").val(">>")
	$("#li_chevron_first").val("<<");
	$('#a_current_page').keydown(function(e) {
		if (e.keyCode == 13) {
			pageJump();
		}
	});
	
	
	$('#input_search_key_table').keydown(function(e) {
		if (e.keyCode == 13) {
			pageSearch();
		}
	});
});


function pageJump() {
	var tagPage = new Number($('#a_current_page').val());
	getAppsInfo(tagPage);

}

function pageFirst() {
	if ($('#a_current_page').val() == '1') {
		Materialize.toast("当前已经是第一页", 4000, 'blue rounded');
		return;
	}
	var tagPage = 1;
	getAppsInfo(tagPage);
}
function pageLast() {
	var tagPage = -1;
	getAppsInfo(tagPage);
}
function pageNext() {
	var tagPage = 1 + new Number($('#a_current_page').val());
	getAppsInfo(tagPage);
}

function pagePrevious() {
	var tagPage = new Number($('#a_current_page').val()) - 1;
	if (tagPage <= 0) {
		Materialize.toast("当前已经是第一页", 4000, 'blue rounded');
		return;
	}
	getAppsInfo(tagPage);
}

function pageSearch() {
	getAppsInfo(1);
}
function getAppsInfo(tagPage) {
	var url = "../appinfo/queryByPage";
	var condition = $('#input_search_key_table').val();
	var data = "tagPage=" + tagPage;
	data += "&condition=" + condition;
	$
			.ajax({
				url : url,
				method : "GET",
				async : true,
				data : data,
				dataType : "html"
			})
			.done(
					function(responseText) {
						if (responseText != undefined) {
							try {
								var resParam = null;
								var resParam = JSON.parse(responseText); // get
								// list
							} catch (e) {
								Materialize.toast("返回数据解析失败", 4000,
										'red rounded');
							}

							if (resParam != undefined
									&& resParam.data != undefined) {
								if (resParam.header.code == commonConstraits.resCode_success) {
									var scope = angular.element($('body'))
											.scope();
									scope.listAppInfo = resParam.data; // get
									while(scope.listAppInfo.length < scope.pageLength){
										scope.listAppInfo[scope.listAppInfo.length] = {};
									}
									// list
									if (resParam.bundle != '-1') {
										$('#a_current_page').val(
												resParam.bundle[0]);
										$('#a_last_page').val(
												resParam.bundle[1]);
									}
									scope.$apply();
								} else {
									Materialize.toast(resParam.header.des,
											4000, 'red rounded');
								}
							} else {
								Materialize.toast("服务器数据异常", 4000,
										'red rounded');
							}
						}
					}).fail(
					function(jqXHR, textStatus) {
						// Materialize.toast(message, displayLength, className,
						// completeCallback);
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

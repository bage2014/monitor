// angularjs params
var app = angular.module('SummarizeApp', []);
app.controller('SummarizeController', function($scope, $http) {
	$scope.listTbodyThreads = [];
	$scope.listSessions = [];
	$scope.listProcesses = [];
	$scope.memoryInformations = {}; // 
	$scope.cpu = {}; // 
	$scope.databaseInformations = {};
	$scope.listsql = {};
	$scope.listLatestSql = {};
	$scope.pageLimit = 5;
	$scope.currentAppInfo = {};
	$scope.appid = 1;
	
	$scope.currenthits = 1;
	$scope.currentsystemErrors = 1;
	$scope.currentScore = 80.0;
	
	$scope.appinfo = {};
	
});

var appid = 1;

// var latestEChartSessionDiv = null;
var latestEChartHttpDiv = null;
var latestEChartHttpMaxId = 0;
var latestEChartHttpErrors = [];
var latestEChartHttpReq = [];
var latestEChartHttpTimes = [];

var latestEChartMemoryDiv = null;
var latestEChartMemoryMaxId = 0;
var latestEChartJavaUsedMemorys = [];
var latestEChartsedMemorys = [];
var latestEChartFreeSwapSpaces = [];
var latestEChartQueryTimes = [];

var latestEChartJdbcDiv = null;
var latestEChartJdbcMaxId = 0;
var latestEChartQueryTime = [];
var latestEChartUsedConnectionCounts = [];
var latestEChartActiveConnectionCount = [];

var latestEChartMemoryCurrentDiv = null;

// var latestEChartSqlDiv = null; // 已经用table代替

var latestEChartMemoryNowDiv = null;

var latestEChartHttpNowDiv = null;
var latestEChartJdbcNowDiv = null;


var latestEChartSessionNowDiv = null;

var latestEChartResponseDiv = null;
var topEChartSessionDiv = null;
var getTopSessionInfoAjaxReq = null;

var currentTab_id = null;

var sessionSortType = "DESC";
var sessionDurationType = "latestHour";

var durationTypeObject = {
		'最近一年' : 'latestYear',
		'最近一个月' : 'latestMonth',
		'最近一天' : 'latestDay',
		'最近一小时' : 'latestHour',
		'latestYear' : '最近一年' ,
		'latestMonth' : '最近一个月',
		'latestDay' : '最近一天',
		'latestHour' : '最近一小时' 
};


$(document).ready(
		function() {
			// 获取APPID
			appid = common_utils_getURLParam('appid');
			var scope = angular.element($('body')).scope();
			scope.cpu = appid;
			scope.$apply();

			$(".tab_div").hide();
			$("#div_basic").show();

			// 分类
			topEChartSessionDiv = echarts.init(document
					.getElementById('div_topn_session'));

			latestEChartHttpDiv = echarts.init(document
					.getElementById('summarize_http_div'));

			latestEChartMemoryDiv = echarts.init(document
					.getElementById('summarize_memory_div'));

			latestEChartJdbcDiv = echarts.init(document
					.getElementById('summarize_jdbc_div'));

			latestEChartResponseDiv = echarts.init(document
					.getElementById('summarize_response_now_div'));

			latestEChartMemoryNowDiv = echarts.init(document
					.getElementById('summarize_memory_now_div'));

//			latestEChartJdbcNowDiv = echarts.init(document
//					.getElementById('summarize_jdbc_now_div'));

			// 概况

			// 初始化分类信息
			initSessionEchart();
			initHttpEchart();
			initMemoryEchart();
			initJdbcEchart();
			initSqlTable();

			getAppInfo();
			
			getJVMPart();
			getCurrentAppInfo();
			getTopSessionInfo();

		});

function customMenu(currentTabId){
	currentTab_id = "div_" + currentTabId;
	$('#index_modal_customMenu').modal('open');
}

function customTimeOK(){
	
	if(currentTab_id == undefined){
		return ;
	}
	
	var isCustom=$('input:radio[name="isCustom"]:checked').val();
	var startDate = '';
	var stopDate = '';
	var startTime = '';
	var stopTime = '';
	var newDurationType = '';
	
	if('TRUE' == isCustom){
		// 自定义时间
		startDate = $('#startDate').val();
		stopDate = $('#stopDate').val();
		startTime = $('#startTime').val() == '' ? '00:00:00' : $('#startTime')
				.val()
				+ ':00';
		stopTime = $('#stopTime').val() == '' ? '00:00:00' : $('#stopTime')
				.val()
				+ ':00';
	}else{
		// 不是自定义
		newDurationType=$('input:radio[name="durationType"]:checked').val();
	}

	if (currentTab_id.indexOf('session') > 0) {
		if('TRUE' == isCustom){
			$('#p_session_durationTime').text(
					startDate + ' ' + startTime + ' --> ' + stopDate + ' '
							+ stopTime);
		}else{
			$('#p_session_durationTime').text(durationTypeObject[newDurationType]);
		}
		if (getTopSessionInfoAjaxReq != null) {
			getTopSessionInfoAjaxReq.abort();
		}
		getTopSessionInfo();
	}
		
}

function setSessionSortType(){
	sessionSortType = sessionSortType == 'DESC' ? 'ASC' : 'DESC';
	if (getTopSessionInfoAjaxReq != null) {
		getTopSessionInfoAjaxReq.abort();
	}
	getTopSessionInfo();
}

function setSessionDurationType(newDurationType){
	sessionDurationType = newDurationType;
	$('#p_session_durationTime').text('');
	if (getTopSessionInfoAjaxReq != null) {
		getTopSessionInfoAjaxReq.abort();
	}
	getTopSessionInfo();
}


function getTopSessionInfo() {
	topEChartSessionDiv.showLoading();
	// 获取参数
	var startTime = '';
	var stopTime = '';
	if ($('#p_session_durationTime').text().indexOf(' --> ') > 0) {
		var durationTime = $('#p_session_durationTime').text().split(' --> ');
		startTime = durationTime[0].trim();
		stopTime = durationTime[1].trim();
	}else{
		// 获取数据
		sessionDurationType = durationTypeObject[$('#p_session_durationTime').text()];
	}
	
	var url = "../actionstatistics/topN";
	var data = "durationType=" + sessionDurationType;
	data += "&sortType=" + sessionSortType;
	data += "&startTime=" + startTime;
	data += "&stopTime=" + stopTime;
	data += "&appid=" + appid;

	getTopSessionInfoAjaxReq = $
			.ajax({
				url : url,
				method : "GET",
				async : true,
				data : data,
				dataType : "html"
			})
			.done(
					function(responseText) {
						topEChartSessionDiv.hideLoading();
						if (responseText != undefined) {
							var resParam = null;
							try {
								resParam = JSON.parse(responseText);
							} catch (e) {
								Materialize.toast("返回数据解析失败", 4000,
										'red rounded');
							}
							if (resParam != undefined
									&& resParam.data != undefined) {
								if (resParam.header.code == commonConstraits.resCode_success) {
									topEChartSessionDiv
											.setOption(getTopSessionOption(resParam.data));
									window.onresize = topEChartSessionDiv.resize;
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
						topEChartSessionDiv.hideLoading();
						// Materialize.toast(message, displayLength, className,
						// completeCallback);
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function getTopSessionOption(list) {
	var xLabels = [];
	var yLabels = [];
	var datas = [];
	for (var j = 0; j < list.length; j++) {
		var i = list.length - 1 - j;
		yLabels[j] = list[i].pgmNam;
		datas[j] = common_utils_toFixed(list[i].accessCount,2);
	}
	var option = {
		backgroundColor : commonConstraits.baseBackgroundColor,
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			top : '0%',
			containLabel : true
		},
		yAxis : [ {
			type : 'category',
			data : yLabels,
			axisTick : {
				alignWithLabel : true
			},
			axisLine : {
				lineStyle : {
					color : commonConstraits.baseLabelColor,
				}
			}
		} ],
		xAxis : [ {
			type : 'value',
			textStyle : {
				color : 'white' // color of value
			},
			axisLine : {
				lineStyle : {
					color : commonConstraits.baseLabelColor,
				}
			}
		} ],
		series : [ {
			name : '访问次数',
			type : 'bar',
			data : datas,
			label : {
				normal : {
					show : true,
					position : 'insideRight',
					textStyle : {
						color : 'white' // color of value
					}
				}
			},
			itemStyle : {
				normal : {
					barBorderRadius : 8,
					color : new echarts.graphic.LinearGradient(0, 0, 1, 0, [ {
						offset : 0,
						color : 'lightYellow' // 0% 处的颜色

					}, {
						offset : 1,
						color : '#ffc107' // 100% 处的颜色
					} ], false)
				}
			}
		} ]
	};
	return option;
}

////////////////////
function setSession(userCount) {

	var strUserCount = userCount + '';
	var pointPosi = strUserCount.charAt(".");
	var n = 8;
	var pointIndex = -1;
	for (var i = 0; i < n; i++) {
		var currentChar = strUserCount.charAt(i);
		if (currentChar == '.') {
			pointIndex = i;
			break;
		}
	}
	for (var i = 0; i < n; i++) {
		var currentChar = strUserCount.charAt(i);
		if (i < pointIndex) {
			if (i < strUserCount.length) {
				currentChar = strUserCount.charAt(pointIndex - 1 - i);
			} else {
				currentChar = '0';
			}
		} else {
			currentChar = '0';
		}
		$("#a_session" + (n - i)).text(currentChar);
	}

}
function select_tab(tab_id) {
	if (tab_id != null && tab_id != undefined) {
		currentTab_id = tab_id;
		$(".tab_div").hide();
		$("#" + tab_id).show();
		$('.collection a').attr('class', 'collection-item');
		$('#a_' + tab_id).attr('class', 'collection-item active');

		if (tab_id.indexOf('basic') > 0) {// 相当于 contains

		}
		if (tab_id.indexOf('top_n') > 0) {

		}

	}
}

function queryLatestHttp() {
	var url = "../httpstatistics/queryLatest";
	var data = "maxId=" + latestEChartHttpMaxId + "&appId=" + appid;
	$.ajax({
		url : url,
		method : "POST",
		data : data,
		async : true,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var list = JSON.parse(responseText); // get
				if (list.length > 0) {
					for (var i = 0; i < list.length; i++) {

						if (list[i].id > latestEChartHttpMaxId) {
							latestEChartHttpMaxId = list[i].id;
						}

						latestEChartHttpErrors.shift();
						latestEChartHttpErrors.push(list[i].systemErrors);

						latestEChartHttpReq.shift();
						latestEChartHttpReq.push(list[i].hits);

						latestEChartHttpTimes.shift();
						latestEChartHttpTimes.push(list[i].queryTime);
					}
					
					latestEChartHttpDiv.setOption({
						xAxis : [ {
							data : latestEChartHttpTimes
						} ],
						series : [ {
							data : latestEChartHttpErrors
						}, {
							data : latestEChartHttpReq
						} ]
					});
					
					////////////////////
					var value = (list[0].hits - list[0].systemErrors) / list[0].hits * 100;
					
					var scope = angular.element($('body')).scope();
					scope.currenthits = list[0].hits; 
					scope.currentsystemErrors = list[0].systemErrors;
					scope.currentScore = common_utils_toFixed((list[0].hits - list[0].systemErrors)/list[0].hits * 100,1);
					scope.$apply();
										
					value = common_utils_toFixed(value,2);
					var valueName = "差";
					if(value > 0.8){
						valueName = "优";
					}else if(value > 0.6){
						valueName = "良";
					}
					latestEChartResponseDiv.setOption(getResponseNowOption(value,valueName));

				}
				setTimeout("queryLatestHttp()", 1000);
			} catch (e) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			}
		}
	}).fail(function(jqXHR, textStatus) {
		Materialize.toast("返回数据解析失败", 4000, 'red rounded');
	});
}

function queryLatestMemory() {
	var url = "../memorystatistics/queryLatest";
	var data = "maxId=" + latestEChartMemoryMaxId + "&appId=" + appid;
	//alert(data);
	$.ajax({
		url : url,
		method : "POST",
		data : data,
		async : true,
		dataType : "html"
	}).done(
			function(responseText) {
				if (responseText != undefined) {
					try {
						var list = JSON.parse(responseText); // get
						if (list.length > 0) {
							for (var i = 0; i < list.length; i++) {

								if (list[i].id > latestEChartMemoryMaxId) {
									latestEChartMemoryMaxId = list[i].id;
								}

								latestEChartJavaUsedMemorys.shift();
								latestEChartJavaUsedMemorys
										.push(list[i].usedMemory / 1000);

								latestEChartsedMemorys.shift();
								latestEChartsedMemorys
										.push(list[i].freePhysicalMemory);

								latestEChartFreeSwapSpaces.shift();
								latestEChartFreeSwapSpaces
										.push(list[i].freeSwapSpace);

								latestEChartQueryTimes.shift();
								latestEChartQueryTimes.push(list[i].queryTime);
							}

							latestEChartMemoryDiv.setOption({
								xAxis : [ {
									data : latestEChartQueryTimes
								} ],
								series : [ {
									data : latestEChartJavaUsedMemorys
								}, {
									data : latestEChartsedMemorys
								}, {
									data : latestEChartFreeSwapSpaces
								} ]
							});
							
							latestEChartMemoryNowDiv.setOption(getMemoryNowOption(list[0]), false);
							
						}
						setTimeout("queryLatestMemory()", 1000);
					} catch (e) {
						Materialize.toast("返回数据解析失败", 4000, 'red rounded');
					}
				}
			}).fail(function(jqXHR, textStatus) {
		Materialize.toast("返回数据解析失败", 4000, 'red rounded');
	});
}

function queryLatestJdbc() {
	var url = "../jdbcstatistics/queryLatest";
	var data = "maxId=" + latestEChartMemoryMaxId + "&appId=" + appid;
	$.ajax({
		url : url,
		method : "POST",
		data : data,
		async : true,
		dataType : "html"
	}).done(
			function(responseText) {
				if (responseText != undefined) {
					try {
						var list = JSON.parse(responseText); // get
						if (list.length > 0) {
							for (var i = 0; i < list.length; i++) {
								
								if (list[i].id > latestEChartJdbcMaxId) {
									latestEChartJdbcMaxId = list[i].id;
								}
									
								latestEChartUsedConnectionCounts.shift();
								latestEChartUsedConnectionCounts
								.push(list[i].usedConnectionCount);
								
								latestEChartActiveConnectionCount.shift();
								latestEChartActiveConnectionCount
								.push(list[i].activeConnectionCount);
								
								latestEChartQueryTime.shift();
								latestEChartQueryTime.push(list[i].queryTime.substring(11,
										list[i].queryTime.length - 3));
							}
							latestEChartJdbcDiv.setOption({
								xAxis : [ {
									data : latestEChartQueryTime
								} ],
								series : [ {
									data : latestEChartUsedConnectionCounts
								}, {
									data : latestEChartActiveConnectionCount
								}]
							});
							///////////////////////////// 
							//latestEChartJdbcNowDiv.setOption(getJdbcNowOption(list[0]), true);
						}
						setTimeout("queryLatestJdbc()", 1000);
					} catch (e) {
						Materialize.toast("返回数据解析失败", 4000, 'red rounded');
					}
				}
			}).fail(function(jqXHR, textStatus) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			});
}

function queryLatestSession() {
	var url = "../sessionstatistics/latestOne";
	var data = "appid=" + appid;
	$.ajax({
		url : url,
		method : "POST",
		data : data,
		async : true,
		dataType : "html"
	}).done(
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
							setSession(resParam.data.userCount);
							setTimeout("queryLatestSession()", 1000);
						} else {
							Materialize.toast(resParam.header.des,
									4000, 'red rounded');
						}
					} else {
						Materialize.toast("服务器数据异常", 4000,
								'red rounded');
					}
					
				}
			}).fail(function(jqXHR, textStatus) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			});
}

function getJVMPart() {
	var url = "../monitoring?format=json";
	url += '&part=jvm';
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get
				// root
				// obj
				var list = resultObj.list; // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.cpu = list[0]; // get list
					scope.$apply();
					tips = 50;

				} else {
					Materialize.toast("返回数据解析失败", 4000, 'red rounded');
				}
			} catch (e) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			}
		}
	}).fail(function(jqXHR, textStatus) {
		Materialize.toast("返回数据解析失败", 4000, 'red rounded');
	});
}

function getAppInfo() {
	var url = "../appstatistics/queryOne";
	url += '?appid='+appid;
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				
				var resultObj = JSON.parse(responseText); // get
				
				// root
				// obj
				if (resultObj != undefined) {
					var scope = angular.element($('body')).scope();
					scope.appinfo = resultObj.data[0]; // get list
					scope.$apply();
				} else {
					Materialize.toast("返回数据解析失败", 4000, 'red rounded');
				}
			} catch (e) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			}
		}
	}).fail(function(jqXHR, textStatus) {
		Materialize.toast("返回数据解析失败", 4000, 'red rounded');
	});
}

function getCurrentAppInfo() {
	var url = "../appinfo/queryById";
	var data = 'appid=' + appid;
	$.ajax({
		url : url,
		method : "GET",
		async : true,
		data : data,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get
				var scope = angular.element($('body')).scope();
				scope.currentAppInfo = resultObj; // get list
				scope.$apply();
			} catch (e) {
			}
		}
	}).fail(function(jqXHR, textStatus) {
		Materialize.toast("返回数据解析失败", 4000, 'red rounded');
	});
}

function initSqlTable() {
	// latestEChartSqlDiv.showLoading();
	var url = "../sqlstatistics/latest";
	var data = "appid=" + appid;
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
						// latestEChartSqlDiv.hideLoading();
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
									scope.listLatestSql = resParam.data;
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
						// latestEChartSessionDiv.hideLoading();
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function initJdbcEchart() {
	latestEChartJdbcDiv.showLoading();
	var url = "../jdbcstatistics/latest";
	var data = "appid=" + appid;
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
						latestEChartJdbcDiv.hideLoading();
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
									
									latestEChartJdbcDiv
											.setOption(getLatestJdbcOption(resParam.data));
									window.onresize = latestEChartJdbcDiv.resize;
									setTimeout("queryLatestJdbc()", 1000);

									//latestEChartJdbcNowDiv.setOption(getJdbcNowOption(resParam.data[0]), true);
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
						latestEChartJdbcDiv.hideLoading();
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function initSessionEchart() {
	var url = "../sessionstatistics/latestOne";
	var data = "appid=" + appid;
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
									setSession(resParam.data.userCount);
									setTimeout("queryLatestSession()", 1000);
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
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function initHttpEchart() {

	latestEChartHttpDiv.showLoading();
	var url = "../httpstatistics/latest";
	var data = "appid=" + appid;
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
						latestEChartHttpDiv.hideLoading();
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
									setTimeout("queryLatestHttp()", 1000);
									latestEChartHttpDiv
											.setOption(getLatestHttpOption(resParam.data));
									
									if(resParam.data.length > 0){
										var value = (resParam.data[0].hits - resParam.data[0].systemErrors) / resParam.data[0].hits * 100;
										value = common_utils_toFixed(value,2);
										var valueName = "差";
										if(value > 0.8){
											valueName = "优";
										}else if(value > 0.6){
											valueName = "良";
										}
										latestEChartResponseDiv.setOption(getResponseNowOption(value,valueName));
									}
									
									
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
						latestEChartHttpDiv.hideLoading();
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function initSQLEchart() {

	var myChart = echarts.init(document.getElementById('summarize_sql_div'));
	var option = getSQLOption();
	myChart.setOption(option);
}

function initActionEchart() {
	var myChart = echarts.init(document.getElementById('summarize_action_div'));
	var option = getActionOption();
	myChart.setOption(option);

}

function initMemoryEchart() {

	latestEChartMemoryDiv.showLoading();
	var url = "../memorystatistics/latest";
	var data = "appid=" + appid;
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
						latestEChartMemoryDiv.hideLoading();
						if (responseText != undefined) {
							try {
								var resParam = null;
								var resParam = JSON.parse(responseText); // get
								setTimeout("queryLatestMemory()", 1000);
								// list
							} catch (e) {
								Materialize.toast("返回数据解析失败", 4000,
										'red rounded');
							}

							if (resParam != undefined
									&& resParam.data != undefined) {
								if (resParam.header.code == commonConstraits.resCode_success) {
									latestEChartMemoryDiv
											.setOption(getLatestMemoryOption(resParam.data));
								
									latestEChartMemoryNowDiv.setOption(getMemoryNowOption(resParam.data[0]), true);
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
						latestEChartMemoryDiv.hideLoading();
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function getLatestHttpOption(list) {

	// var titles = [ 'Http 错误', 'Spring 错误', 'Spring 请求', 'HTTP 请求' ];
	// var titles = [ 'Http 错误', 'HTTP 请求','AJAX GET 请求', 'AJAX POST 请求','AJAX
	// 请求' ];
	var titles = [ 'Http 错误', 'HTTP 请求' ];

	for (var i = 0; i < list.length; i++) {

		if (list[i].id > latestEChartHttpMaxId) {
			latestEChartHttpMaxId = list[i].id;
		}
		latestEChartHttpErrors[i] = list[i].systemErrors;
		latestEChartHttpReq[i] = list[i].hits;
		latestEChartHttpTimes[i] = list[i].queryTime;

	}
	var option = {
		backgroundColor : '#404a59',
		title : {
			text : '',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color : '#F1F1F3'
			},
			left : '6%'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#57617B'
				}
			}
		},
		legend : {
			icon : 'rect',
			itemWidth : 14,
			itemHeight : 5,
			itemGap : 13,
			data : titles,
			right : '4%',
			textStyle : {
				fontSize : 12,
				color : '#F1F1F3'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			data : latestEChartHttpTimes
		} ],
		yAxis : [ {
			type : 'value',
			name : '单位(次)',
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			},
			splitLine : {
				lineStyle : {
					color : '#57617B'
				}
			}
		} ],
		series : [ {
			name : titles[0],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(137, 189, 27, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(137, 189, 27, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(137,189,27)',
					borderColor : 'rgba(137,189,2,0.27)',
					borderWidth : 12

				}
			},
			data : latestEChartHttpErrors
		}, {
			name : titles[1],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(0, 136, 212, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(0, 136, 212, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(0,136,212)',
					borderColor : 'rgba(0,136,212,0.2)',
					borderWidth : 12

				}
			},
			data : latestEChartHttpReq
		} ]
	};
	return option;
}

function getHttpOption() {
	var xData = function() {
		var data = [];
		for (var i = 1; i < 15; i++) {
			data.push(i + "");
		}
		return data;
	}();

	option = {
		// backgroundColor: "",
		"title" : {
			"text" : "HTTP 请求情况",
			// "subtext": "BY MICVS",
			x : "4%",

			textStyle : {
				// color: '#fff',
				fontSize : '22'
			},
			subtextStyle : {
			// color: '#90979c',
			// fontSize: '16',

			},
		},
		"tooltip" : {
			"trigger" : "axis",
			"axisPointer" : {
				"type" : "shadow",
				textStyle : {
					color : "#fff"
				}

			},
		},
		"grid" : {
			"borderWidth" : 0,
			"top" : 110,
			"bottom" : 95,
			textStyle : {
				color : "#fff"
			}
		},
		"legend" : {
			x : '4%',
			top : '11%',
			textStyle : {
				color : '#90979c',
			},
			"data" : [ '错误数', '正常数', '总' ]
		},

		"calculable" : true,
		"xAxis" : [ {
			"type" : "category",
			"axisLine" : {
				lineStyle : {
					color : '#90979c'
				}
			},
			"splitLine" : {
				"show" : false
			},
			"axisTick" : {
				"show" : false
			},
			"splitArea" : {
				"show" : false
			},
			"axisLabel" : {
				"interval" : 0,

			},
			"data" : xData,
		} ],
		"yAxis" : [ {
			"type" : "value",
			"splitLine" : {
				"show" : false
			},
			"axisLine" : {
				lineStyle : {
					color : '#90979c'
				}
			},
			"axisTick" : {
				"show" : false
			},
			"axisLabel" : {
				"interval" : 0,

			},
			"splitArea" : {
				"show" : false
			},

		} ],
		"dataZoom" : [
				{
					"show" : true,
					"height" : 30,
					"xAxisIndex" : [ 0 ],
					bottom : 30,
					"start" : 10,
					"end" : 80,
					handleIcon : 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
					handleSize : '110%',
					handleStyle : {
						color : "#d3dee5",

					},
					textStyle : {
						color : "#fff"
					},
					borderColor : "#90979c"

				}, {
					"type" : "inside",
					"show" : true,
					"height" : 15,
					"start" : 1,
					"end" : 35
				} ],
		"series" : [
				{
					"name" : "错误数",
					"type" : "bar",
					"stack" : "总量",
					"barMaxWidth" : 35,
					"barGap" : "10%",
					"itemStyle" : {
						"normal" : {
							"color" : "rgba(255,144,128,1)",
							"label" : {
								"show" : true,
								"textStyle" : {
									"color" : "#fff"
								},
								"position" : "insideTop",
								formatter : function(p) {
									return p.value > 0 ? (p.value) : '';
								}
							}
						}
					},
					"data" : [ 198.66, 330.81, 151.95, 160.12, 222.56, 229.05,
							128.53, 250.91, 224.47, 473.99, 126.85, 260.50 ],
				},

				{
					"name" : "正常数",
					"type" : "bar",
					"stack" : "总量",
					"itemStyle" : {
						"normal" : {
							"color" : "rgba(0,191,183,1)",
							"barBorderRadius" : 0,
							"label" : {
								"show" : true,
								"position" : "top",
								formatter : function(p) {
									return p.value > 0 ? (p.value) : '';
								}
							}
						}
					},
					"data" : [ 82.89, 67.54, 62.07, 59.43, 67.02, 67.09, 35.66,
							71.78, 81.61, 78.85, 79.12, 72.30 ]
				},
				{
					"name" : "总",
					"type" : "line",
					"stack" : "总量",
					symbolSize : 20,
					symbol : 'circle',
					"itemStyle" : {
						"normal" : {
							"color" : "rgba(252,230,48,1)",
							"barBorderRadius" : 0,
							"label" : {
								"show" : true,
								"position" : "top",
								formatter : function(p) {
									return p.value > 0 ? (p.value) : '';
								}
							}
						}
					},
					"data" : [ 281.55, 398.35, 214.02, 219.55, 289.57, 296.14,
							164.18, 322.69, 306.08, 552.84, 205.97, 332.79 ]
				}, ]
	}
	return option;

}
function getSessionOption() {
	var pathSymbols = {
		reindeer : 'path://M-22.788,24.521c2.08-0.986,3.611-3.905,4.984-5.892 c-2.686,2.782-5.047,5.884-9.102,7.312c-0.992,0.005-0.25-2.016,0.34-2.362l1.852-0.41c0.564-0.218,0.785-0.842,0.902-1.347 c2.133-0.727,4.91-4.129,6.031-6.194c1.748-0.7,4.443-0.679,5.734-2.293c1.176-1.468,0.393-3.992,1.215-6.557 c0.24-0.754,0.574-1.581,1.008-2.293c-0.611,0.011-1.348-0.061-1.959-0.608c-1.391-1.245-0.785-2.086-1.297-3.313 c1.684,0.744,2.5,2.584,4.426,2.586C-8.46,3.012-8.255,2.901-8.04,2.824c6.031-1.952,15.182-0.165,19.498-3.937 c1.15-3.933-1.24-9.846-1.229-9.938c0.008-0.062-1.314-0.004-1.803-0.258c-1.119-0.771-6.531-3.75-0.17-3.33 c0.314-0.045,0.943,0.259,1.439,0.435c-0.289-1.694-0.92-0.144-3.311-1.946c0,0-1.1-0.855-1.764-1.98 c-0.836-1.09-2.01-2.825-2.992-4.031c-1.523-2.476,1.367,0.709,1.816,1.108c1.768,1.704,1.844,3.281,3.232,3.983 c0.195,0.203,1.453,0.164,0.926-0.468c-0.525-0.632-1.367-1.278-1.775-2.341c-0.293-0.703-1.311-2.326-1.566-2.711 c-0.256-0.384-0.959-1.718-1.67-2.351c-1.047-1.187-0.268-0.902,0.521-0.07c0.789,0.834,1.537,1.821,1.672,2.023 c0.135,0.203,1.584,2.521,1.725,2.387c0.102-0.259-0.035-0.428-0.158-0.852c-0.125-0.423-0.912-2.032-0.961-2.083 c-0.357-0.852-0.566-1.908-0.598-3.333c0.4-2.375,0.648-2.486,0.549-0.705c0.014,1.143,0.031,2.215,0.602,3.247 c0.807,1.496,1.764,4.064,1.836,4.474c0.561,3.176,2.904,1.749,2.281-0.126c-0.068-0.446-0.109-2.014-0.287-2.862 c-0.18-0.849-0.219-1.688-0.113-3.056c0.066-1.389,0.232-2.055,0.277-2.299c0.285-1.023,0.4-1.088,0.408,0.135 c-0.059,0.399-0.131,1.687-0.125,2.655c0.064,0.642-0.043,1.768,0.172,2.486c0.654,1.928-0.027,3.496,1,3.514 c1.805-0.424,2.428-1.218,2.428-2.346c-0.086-0.704-0.121-0.843-0.031-1.193c0.221-0.568,0.359-0.67,0.312-0.076 c-0.055,0.287,0.031,0.533,0.082,0.794c0.264,1.197,0.912,0.114,1.283-0.782c0.15-0.238,0.539-2.154,0.545-2.522 c-0.023-0.617,0.285-0.645,0.309,0.01c0.064,0.422-0.248,2.646-0.205,2.334c-0.338,1.24-1.105,3.402-3.379,4.712 c-0.389,0.12-1.186,1.286-3.328,2.178c0,0,1.729,0.321,3.156,0.246c1.102-0.19,3.707-0.027,4.654,0.269 c1.752,0.494,1.531-0.053,4.084,0.164c2.26-0.4,2.154,2.391-1.496,3.68c-2.549,1.405-3.107,1.475-2.293,2.984 c3.484,7.906,2.865,13.183,2.193,16.466c2.41,0.271,5.732-0.62,7.301,0.725c0.506,0.333,0.648,1.866-0.457,2.86 c-4.105,2.745-9.283,7.022-13.904,7.662c-0.977-0.194,0.156-2.025,0.803-2.247l1.898-0.03c0.596-0.101,0.936-0.669,1.152-1.139 c3.16-0.404,5.045-3.775,8.246-4.818c-4.035-0.718-9.588,3.981-12.162,1.051c-5.043,1.423-11.449,1.84-15.895,1.111 c-3.105,2.687-7.934,4.021-12.115,5.866c-3.271,3.511-5.188,8.086-9.967,10.414c-0.986,0.119-0.48-1.974,0.066-2.385l1.795-0.618 C-22.995,25.682-22.849,25.035-22.788,24.521z',
		plane : 'path://M1.112,32.559l2.998,1.205l-2.882,2.268l-2.215-0.012L1.112,32.559z M37.803,23.96 c0.158-0.838,0.5-1.509,0.961-1.904c-0.096-0.037-0.205-0.071-0.344-0.071c-0.777-0.005-2.068-0.009-3.047-0.009 c-0.633,0-1.217,0.066-1.754,0.18l2.199,1.804H37.803z M39.738,23.036c-0.111,0-0.377,0.325-0.537,0.924h1.076 C40.115,23.361,39.854,23.036,39.738,23.036z M39.934,39.867c-0.166,0-0.674,0.705-0.674,1.986s0.506,1.986,0.674,1.986 s0.672-0.705,0.672-1.986S40.102,39.867,39.934,39.867z M38.963,38.889c-0.098-0.038-0.209-0.07-0.348-0.073 c-0.082,0-0.174,0-0.268-0.001l-7.127,4.671c0.879,0.821,2.42,1.417,4.348,1.417c0.979,0,2.27-0.006,3.047-0.01 c0.139,0,0.25-0.034,0.348-0.072c-0.646-0.555-1.07-1.643-1.07-2.967C37.891,40.529,38.316,39.441,38.963,38.889z M32.713,23.96 l-12.37-10.116l-4.693-0.004c0,0,4,8.222,4.827,10.121H32.713z M59.311,32.374c-0.248,2.104-5.305,3.172-8.018,3.172H39.629 l-25.325,16.61L9.607,52.16c0,0,6.687-8.479,7.95-10.207c1.17-1.6,3.019-3.699,3.027-6.407h-2.138 c-5.839,0-13.816-3.789-18.472-5.583c-2.818-1.085-2.396-4.04-0.031-4.04h0.039l-3.299-11.371h3.617c0,0,4.352,5.696,5.846,7.5 c2,2.416,4.503,3.678,8.228,3.87h30.727c2.17,0,4.311,0.417,6.252,1.046c3.49,1.175,5.863,2.7,7.199,4.027 C59.145,31.584,59.352,32.025,59.311,32.374z M22.069,30.408c0-0.815-0.661-1.475-1.469-1.475c-0.812,0-1.471,0.66-1.471,1.475 s0.658,1.475,1.471,1.475C21.408,31.883,22.069,31.224,22.069,30.408z M27.06,30.408c0-0.815-0.656-1.478-1.466-1.478 c-0.812,0-1.471,0.662-1.471,1.478s0.658,1.477,1.471,1.477C26.404,31.885,27.06,31.224,27.06,30.408z M32.055,30.408 c0-0.815-0.66-1.475-1.469-1.475c-0.808,0-1.466,0.66-1.466,1.475s0.658,1.475,1.466,1.475 C31.398,31.883,32.055,31.224,32.055,30.408z M37.049,30.408c0-0.815-0.658-1.478-1.467-1.478c-0.812,0-1.469,0.662-1.469,1.478 s0.656,1.477,1.469,1.477C36.389,31.885,37.049,31.224,37.049,30.408z M42.039,30.408c0-0.815-0.656-1.478-1.465-1.478 c-0.811,0-1.469,0.662-1.469,1.478s0.658,1.477,1.469,1.477C41.383,31.885,42.039,31.224,42.039,30.408z M55.479,30.565 c-0.701-0.436-1.568-0.896-2.627-1.347c-0.613,0.289-1.551,0.476-2.73,0.476c-1.527,0-1.639,2.263,0.164,2.316 C52.389,32.074,54.627,31.373,55.479,30.565z',
		train : 'path://M67.335,33.596L67.335,33.596c-0.002-1.39-1.153-3.183-3.328-4.218h-9.096v-2.07h5.371 c-4.939-2.07-11.199-4.141-14.89-4.141H19.72v12.421v5.176h38.373c4.033,0,8.457-1.035,9.142-5.176h-0.027 c0.076-0.367,0.129-0.751,0.129-1.165L67.335,33.596L67.335,33.596z M27.999,30.413h-3.105v-4.141h3.105V30.413z M35.245,30.413 h-3.104v-4.141h3.104V30.413z M42.491,30.413h-3.104v-4.141h3.104V30.413z M49.736,30.413h-3.104v-4.141h3.104V30.413z  M14.544,40.764c1.143,0,2.07-0.927,2.07-2.07V35.59V25.237c0-1.145-0.928-2.07-2.07-2.07H-9.265c-1.143,0-2.068,0.926-2.068,2.07 v10.351v3.105c0,1.144,0.926,2.07,2.068,2.07H14.544L14.544,40.764z M8.333,26.272h3.105v4.141H8.333V26.272z M1.087,26.272h3.105 v4.141H1.087V26.272z M-6.159,26.272h3.105v4.141h-3.105V26.272z M-9.265,41.798h69.352v1.035H-9.265V41.798z',
		ship : 'path://M16.678,17.086h9.854l-2.703,5.912c5.596,2.428,11.155,5.575,16.711,8.607c3.387,1.847,6.967,3.75,10.541,5.375 v-6.16l-4.197-2.763v-5.318L33.064,12.197h-11.48L20.43,15.24h-4.533l-1.266,3.286l0.781,0.345L16.678,17.086z M49.6,31.84 l0.047,1.273L27.438,20.998l0.799-1.734L49.6,31.84z M33.031,15.1l12.889,8.82l0.027,0.769L32.551,16.1L33.031,15.1z M22.377,14.045 h9.846l-1.539,3.365l-2.287-1.498h1.371l0.721-1.352h-2.023l-0.553,1.037l-0.541-0.357h-0.34l0.359-0.684h-2.025l-0.361,0.684 h-3.473L22.377,14.045z M23.695,20.678l-0.004,0.004h0.004V20.678z M24.828,18.199h-2.031l-0.719,1.358h2.029L24.828,18.199z  M40.385,34.227c-12.85-7.009-25.729-14.667-38.971-12.527c1.26,8.809,9.08,16.201,8.213,24.328 c-0.553,4.062-3.111,0.828-3.303,7.137c15.799,0,32.379,0,48.166,0l0.066-4.195l1.477-7.23 C50.842,39.812,45.393,36.961,40.385,34.227z M13.99,35.954c-1.213,0-2.195-1.353-2.195-3.035c0-1.665,0.98-3.017,2.195-3.017 c1.219,0,2.195,1.352,2.195,3.017C16.186,34.604,15.213,35.954,13.99,35.954z M23.691,20.682h-2.02l-0.588,1.351h2.023 L23.691,20.682z M19.697,18.199l-0.721,1.358h2.025l0.727-1.358H19.697z',
		car : 'path://M49.592,40.883c-0.053,0.354-0.139,0.697-0.268,0.963c-0.232,0.475-0.455,0.519-1.334,0.475 c-1.135-0.053-2.764,0-4.484,0.068c0,0.476,0.018,0.697,0.018,0.697c0.111,1.299,0.697,1.342,0.931,1.342h3.7 c0.326,0,0.628,0,0.861-0.154c0.301-0.196,0.43-0.772,0.543-1.78c0.017-0.146,0.025-0.336,0.033-0.56v-0.01 c0-0.068,0.008-0.154,0.008-0.25V41.58l0,0C49.6,41.348,49.6,41.09,49.592,40.883L49.592,40.883z M6.057,40.883 c0.053,0.354,0.137,0.697,0.268,0.963c0.23,0.475,0.455,0.519,1.334,0.475c1.137-0.053,2.762,0,4.484,0.068 c0,0.476-0.018,0.697-0.018,0.697c-0.111,1.299-0.697,1.342-0.93,1.342h-3.7c-0.328,0-0.602,0-0.861-0.154 c-0.309-0.18-0.43-0.772-0.541-1.78c-0.018-0.146-0.027-0.336-0.035-0.56v-0.01c0-0.068-0.008-0.154-0.008-0.25V41.58l0,0 C6.057,41.348,6.057,41.09,6.057,40.883L6.057,40.883z M49.867,32.766c0-2.642-0.344-5.224-0.482-5.507 c-0.104-0.207-0.766-0.749-2.271-1.773c-1.522-1.042-1.487-0.887-1.766-1.566c0.25-0.078,0.492-0.224,0.639-0.241 c0.326-0.034,0.345,0.274,1.023,0.274c0.68,0,2.152-0.18,2.453-0.48c0.301-0.303,0.396-0.405,0.396-0.672 c0-0.268-0.156-0.818-0.447-1.146c-0.293-0.327-1.541-0.49-2.273-0.585c-0.729-0.095-0.834,0-1.022,0.121 c-0.304,0.189-0.32,1.919-0.32,1.919l-0.713,0.018c-0.465-1.146-1.11-3.452-2.117-5.269c-1.103-1.979-2.256-2.599-2.737-2.754 c-0.474-0.146-0.904-0.249-4.131-0.576c-3.298-0.344-5.922-0.388-8.262-0.388c-2.342,0-4.967,0.052-8.264,0.388 c-3.229,0.336-3.66,0.43-4.133,0.576s-1.633,0.775-2.736,2.754c-1.006,1.816-1.652,4.123-2.117,5.269L9.87,23.109 c0,0-0.008-1.729-0.318-1.919c-0.189-0.121-0.293-0.225-1.023-0.121c-0.732,0.104-1.98,0.258-2.273,0.585 c-0.293,0.327-0.447,0.878-0.447,1.146c0,0.267,0.094,0.379,0.396,0.672c0.301,0.301,1.773,0.48,2.453,0.48 c0.68,0,0.697-0.309,1.023-0.274c0.146,0.018,0.396,0.163,0.637,0.241c-0.283,0.68-0.24,0.524-1.764,1.566 c-1.506,1.033-2.178,1.566-2.271,1.773c-0.139,0.283-0.482,2.865-0.482,5.508s0.189,5.02,0.189,5.86c0,0.354,0,0.976,0.076,1.565 c0.053,0.354,0.129,0.697,0.268,0.966c0.232,0.473,0.447,0.516,1.334,0.473c1.137-0.051,2.779,0,4.477,0.07 c1.135,0.043,2.297,0.086,3.33,0.11c2.582,0.051,1.826-0.379,2.928-0.36c1.102,0.016,5.447,0.196,9.424,0.196 c3.976,0,8.332-0.182,9.423-0.196c1.102-0.019,0.346,0.411,2.926,0.36c1.033-0.018,2.195-0.067,3.332-0.11 c1.695-0.062,3.348-0.121,4.477-0.07c0.886,0.043,1.103,0,1.332-0.473c0.132-0.269,0.218-0.611,0.269-0.966 c0.086-0.592,0.078-1.213,0.078-1.565C49.678,37.793,49.867,35.408,49.867,32.766L49.867,32.766z M13.219,19.735 c0.412-0.964,1.652-2.9,2.256-3.244c0.145-0.087,1.426-0.491,4.637-0.706c2.953-0.198,6.217-0.276,7.73-0.276 c1.513,0,4.777,0.078,7.729,0.276c3.201,0.215,4.502,0.611,4.639,0.706c0.775,0.533,1.842,2.28,2.256,3.244 c0.412,0.965,0.965,2.858,0.861,3.116c-0.104,0.258,0.104,0.388-1.291,0.275c-1.387-0.103-10.088-0.216-14.185-0.216 c-4.088,0-12.789,0.113-14.184,0.216c-1.395,0.104-1.188-0.018-1.291-0.275C12.254,22.593,12.805,20.708,13.219,19.735 L13.219,19.735z M16.385,30.511c-0.619,0.155-0.988,0.491-1.764,0.482c-0.775,0-2.867-0.353-3.314-0.371 c-0.447-0.017-0.842,0.302-1.076,0.362c-0.23,0.06-0.688-0.104-1.377-0.318c-0.688-0.216-1.092-0.155-1.316-1.094 c-0.232-0.93,0-2.264,0-2.264c1.488-0.068,2.928,0.069,5.621,0.826c2.693,0.758,4.191,2.213,4.191,2.213 S17.004,30.357,16.385,30.511L16.385,30.511z M36.629,37.293c-1.23,0.164-6.386,0.207-8.794,0.207c-2.412,0-7.566-0.051-8.799-0.207 c-1.256-0.164-2.891-1.67-1.764-2.865c1.523-1.627,1.24-1.576,4.701-2.023C24.967,32.018,27.239,32,27.834,32 c0.584,0,2.865,0.025,5.859,0.404c3.461,0.447,3.178,0.396,4.699,2.022C39.521,35.623,37.887,37.129,36.629,37.293L36.629,37.293z  M48.129,29.582c-0.232,0.93-0.629,0.878-1.318,1.093c-0.688,0.216-1.145,0.371-1.377,0.319c-0.231-0.053-0.627-0.371-1.074-0.361 c-0.448,0.018-2.539,0.37-3.313,0.37c-0.772,0-1.146-0.328-1.764-0.481c-0.621-0.154-0.966-0.154-0.966-0.154 s1.49-1.464,4.191-2.213c2.693-0.758,4.131-0.895,5.621-0.826C48.129,27.309,48.361,28.643,48.129,29.582L48.129,29.582z'
	};

	var labelSetting = {
		normal : {
			show : true,
			position : 'outside',
			offset : [ 10, 0 ],
			textStyle : {
				fontSize : 16
			}
		}
	};

	option = {
		title : {
			text : 'Vehicles in X City'
		},
		legend : {
			data : [ '2015', '2016' ]
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'shadow'
			}
		},
		grid : {
			containLabel : true,
			left : 20
		},
		yAxis : {
			data : [ 'reindeer', 'ship', 'plane', 'train', 'car' ],
			inverse : true,
			axisLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				margin : 30,
				textStyle : {
					fontSize : 14
				}
			},
			axisPointer : {
				label : {
					show : true,
					margin : 30
				}
			}
		},
		xAxis : {
			splitLine : {
				show : false
			},
			axisLabel : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLine : {
				show : false
			}
		},
		series : [ {
			name : '2015',
			type : 'pictorialBar',
			label : labelSetting,
			symbolRepeat : true,
			symbolSize : [ '80%', '60%' ],
			barCategoryGap : '40%',
			data : [ {
				value : 157,
				symbol : pathSymbols.reindeer
			}, {
				value : 21,
				symbol : pathSymbols.ship
			}, {
				value : 66,
				symbol : pathSymbols.plane
			}, {
				value : 78,
				symbol : pathSymbols.train
			}, {
				value : 123,
				symbol : pathSymbols.car
			} ]
		}, {
			name : '2016',
			type : 'pictorialBar',
			barGap : '10%',
			label : labelSetting,
			symbolRepeat : true,
			symbolSize : [ '80%', '60%' ],
			data : [ {
				value : 184,
				symbol : pathSymbols.reindeer
			}, {
				value : 29,
				symbol : pathSymbols.ship
			}, {
				value : 73,
				symbol : pathSymbols.plane
			}, {
				value : 91,
				symbol : pathSymbols.train
			}, {
				value : 95,
				symbol : pathSymbols.car
			} ]
		} ]
	};

	return option;

}
function getActionOption() {
	var pathSymbols = {
		reindeer : 'path://M-22.788,24.521c2.08-0.986,3.611-3.905,4.984-5.892 c-2.686,2.782-5.047,5.884-9.102,7.312c-0.992,0.005-0.25-2.016,0.34-2.362l1.852-0.41c0.564-0.218,0.785-0.842,0.902-1.347 c2.133-0.727,4.91-4.129,6.031-6.194c1.748-0.7,4.443-0.679,5.734-2.293c1.176-1.468,0.393-3.992,1.215-6.557 c0.24-0.754,0.574-1.581,1.008-2.293c-0.611,0.011-1.348-0.061-1.959-0.608c-1.391-1.245-0.785-2.086-1.297-3.313 c1.684,0.744,2.5,2.584,4.426,2.586C-8.46,3.012-8.255,2.901-8.04,2.824c6.031-1.952,15.182-0.165,19.498-3.937 c1.15-3.933-1.24-9.846-1.229-9.938c0.008-0.062-1.314-0.004-1.803-0.258c-1.119-0.771-6.531-3.75-0.17-3.33 c0.314-0.045,0.943,0.259,1.439,0.435c-0.289-1.694-0.92-0.144-3.311-1.946c0,0-1.1-0.855-1.764-1.98 c-0.836-1.09-2.01-2.825-2.992-4.031c-1.523-2.476,1.367,0.709,1.816,1.108c1.768,1.704,1.844,3.281,3.232,3.983 c0.195,0.203,1.453,0.164,0.926-0.468c-0.525-0.632-1.367-1.278-1.775-2.341c-0.293-0.703-1.311-2.326-1.566-2.711 c-0.256-0.384-0.959-1.718-1.67-2.351c-1.047-1.187-0.268-0.902,0.521-0.07c0.789,0.834,1.537,1.821,1.672,2.023 c0.135,0.203,1.584,2.521,1.725,2.387c0.102-0.259-0.035-0.428-0.158-0.852c-0.125-0.423-0.912-2.032-0.961-2.083 c-0.357-0.852-0.566-1.908-0.598-3.333c0.4-2.375,0.648-2.486,0.549-0.705c0.014,1.143,0.031,2.215,0.602,3.247 c0.807,1.496,1.764,4.064,1.836,4.474c0.561,3.176,2.904,1.749,2.281-0.126c-0.068-0.446-0.109-2.014-0.287-2.862 c-0.18-0.849-0.219-1.688-0.113-3.056c0.066-1.389,0.232-2.055,0.277-2.299c0.285-1.023,0.4-1.088,0.408,0.135 c-0.059,0.399-0.131,1.687-0.125,2.655c0.064,0.642-0.043,1.768,0.172,2.486c0.654,1.928-0.027,3.496,1,3.514 c1.805-0.424,2.428-1.218,2.428-2.346c-0.086-0.704-0.121-0.843-0.031-1.193c0.221-0.568,0.359-0.67,0.312-0.076 c-0.055,0.287,0.031,0.533,0.082,0.794c0.264,1.197,0.912,0.114,1.283-0.782c0.15-0.238,0.539-2.154,0.545-2.522 c-0.023-0.617,0.285-0.645,0.309,0.01c0.064,0.422-0.248,2.646-0.205,2.334c-0.338,1.24-1.105,3.402-3.379,4.712 c-0.389,0.12-1.186,1.286-3.328,2.178c0,0,1.729,0.321,3.156,0.246c1.102-0.19,3.707-0.027,4.654,0.269 c1.752,0.494,1.531-0.053,4.084,0.164c2.26-0.4,2.154,2.391-1.496,3.68c-2.549,1.405-3.107,1.475-2.293,2.984 c3.484,7.906,2.865,13.183,2.193,16.466c2.41,0.271,5.732-0.62,7.301,0.725c0.506,0.333,0.648,1.866-0.457,2.86 c-4.105,2.745-9.283,7.022-13.904,7.662c-0.977-0.194,0.156-2.025,0.803-2.247l1.898-0.03c0.596-0.101,0.936-0.669,1.152-1.139 c3.16-0.404,5.045-3.775,8.246-4.818c-4.035-0.718-9.588,3.981-12.162,1.051c-5.043,1.423-11.449,1.84-15.895,1.111 c-3.105,2.687-7.934,4.021-12.115,5.866c-3.271,3.511-5.188,8.086-9.967,10.414c-0.986,0.119-0.48-1.974,0.066-2.385l1.795-0.618 C-22.995,25.682-22.849,25.035-22.788,24.521z',
		plane : 'path://M1.112,32.559l2.998,1.205l-2.882,2.268l-2.215-0.012L1.112,32.559z M37.803,23.96 c0.158-0.838,0.5-1.509,0.961-1.904c-0.096-0.037-0.205-0.071-0.344-0.071c-0.777-0.005-2.068-0.009-3.047-0.009 c-0.633,0-1.217,0.066-1.754,0.18l2.199,1.804H37.803z M39.738,23.036c-0.111,0-0.377,0.325-0.537,0.924h1.076 C40.115,23.361,39.854,23.036,39.738,23.036z M39.934,39.867c-0.166,0-0.674,0.705-0.674,1.986s0.506,1.986,0.674,1.986 s0.672-0.705,0.672-1.986S40.102,39.867,39.934,39.867z M38.963,38.889c-0.098-0.038-0.209-0.07-0.348-0.073 c-0.082,0-0.174,0-0.268-0.001l-7.127,4.671c0.879,0.821,2.42,1.417,4.348,1.417c0.979,0,2.27-0.006,3.047-0.01 c0.139,0,0.25-0.034,0.348-0.072c-0.646-0.555-1.07-1.643-1.07-2.967C37.891,40.529,38.316,39.441,38.963,38.889z M32.713,23.96 l-12.37-10.116l-4.693-0.004c0,0,4,8.222,4.827,10.121H32.713z M59.311,32.374c-0.248,2.104-5.305,3.172-8.018,3.172H39.629 l-25.325,16.61L9.607,52.16c0,0,6.687-8.479,7.95-10.207c1.17-1.6,3.019-3.699,3.027-6.407h-2.138 c-5.839,0-13.816-3.789-18.472-5.583c-2.818-1.085-2.396-4.04-0.031-4.04h0.039l-3.299-11.371h3.617c0,0,4.352,5.696,5.846,7.5 c2,2.416,4.503,3.678,8.228,3.87h30.727c2.17,0,4.311,0.417,6.252,1.046c3.49,1.175,5.863,2.7,7.199,4.027 C59.145,31.584,59.352,32.025,59.311,32.374z M22.069,30.408c0-0.815-0.661-1.475-1.469-1.475c-0.812,0-1.471,0.66-1.471,1.475 s0.658,1.475,1.471,1.475C21.408,31.883,22.069,31.224,22.069,30.408z M27.06,30.408c0-0.815-0.656-1.478-1.466-1.478 c-0.812,0-1.471,0.662-1.471,1.478s0.658,1.477,1.471,1.477C26.404,31.885,27.06,31.224,27.06,30.408z M32.055,30.408 c0-0.815-0.66-1.475-1.469-1.475c-0.808,0-1.466,0.66-1.466,1.475s0.658,1.475,1.466,1.475 C31.398,31.883,32.055,31.224,32.055,30.408z M37.049,30.408c0-0.815-0.658-1.478-1.467-1.478c-0.812,0-1.469,0.662-1.469,1.478 s0.656,1.477,1.469,1.477C36.389,31.885,37.049,31.224,37.049,30.408z M42.039,30.408c0-0.815-0.656-1.478-1.465-1.478 c-0.811,0-1.469,0.662-1.469,1.478s0.658,1.477,1.469,1.477C41.383,31.885,42.039,31.224,42.039,30.408z M55.479,30.565 c-0.701-0.436-1.568-0.896-2.627-1.347c-0.613,0.289-1.551,0.476-2.73,0.476c-1.527,0-1.639,2.263,0.164,2.316 C52.389,32.074,54.627,31.373,55.479,30.565z',
		rocket : 'path://M-244.396,44.399c0,0,0.47-2.931-2.427-6.512c2.819-8.221,3.21-15.709,3.21-15.709s5.795,1.383,5.795,7.325C-237.818,39.679-244.396,44.399-244.396,44.399z M-260.371,40.827c0,0-3.881-12.946-3.881-18.319c0-2.416,0.262-4.566,0.669-6.517h17.684c0.411,1.952,0.675,4.104,0.675,6.519c0,5.291-3.87,18.317-3.87,18.317H-260.371z M-254.745,18.951c-1.99,0-3.603,1.676-3.603,3.744c0,2.068,1.612,3.744,3.603,3.744c1.988,0,3.602-1.676,3.602-3.744S-252.757,18.951-254.745,18.951z M-255.521,2.228v-5.098h1.402v4.969c1.603,1.213,5.941,5.069,7.901,12.5h-17.05C-261.373,7.373-257.245,3.558-255.521,2.228zM-265.07,44.399c0,0-6.577-4.721-6.577-14.896c0-5.942,5.794-7.325,5.794-7.325s0.393,7.488,3.211,15.708C-265.539,41.469-265.07,44.399-265.07,44.399z M-252.36,45.15l-1.176-1.22L-254.789,48l-1.487-4.069l-1.019,2.116l-1.488-3.826h8.067L-252.36,45.15z',
		train : 'path://M67.335,33.596L67.335,33.596c-0.002-1.39-1.153-3.183-3.328-4.218h-9.096v-2.07h5.371 c-4.939-2.07-11.199-4.141-14.89-4.141H19.72v12.421v5.176h38.373c4.033,0,8.457-1.035,9.142-5.176h-0.027 c0.076-0.367,0.129-0.751,0.129-1.165L67.335,33.596L67.335,33.596z M27.999,30.413h-3.105v-4.141h3.105V30.413z M35.245,30.413 h-3.104v-4.141h3.104V30.413z M42.491,30.413h-3.104v-4.141h3.104V30.413z M49.736,30.413h-3.104v-4.141h3.104V30.413z  M14.544,40.764c1.143,0,2.07-0.927,2.07-2.07V35.59V25.237c0-1.145-0.928-2.07-2.07-2.07H-9.265c-1.143,0-2.068,0.926-2.068,2.07 v10.351v3.105c0,1.144,0.926,2.07,2.068,2.07H14.544L14.544,40.764z M8.333,26.272h3.105v4.141H8.333V26.272z M1.087,26.272h3.105 v4.141H1.087V26.272z M-6.159,26.272h3.105v4.141h-3.105V26.272z M-9.265,41.798h69.352v1.035H-9.265V41.798z',
		ship : 'path://M16.678,17.086h9.854l-2.703,5.912c5.596,2.428,11.155,5.575,16.711,8.607c3.387,1.847,6.967,3.75,10.541,5.375 v-6.16l-4.197-2.763v-5.318L33.064,12.197h-11.48L20.43,15.24h-4.533l-1.266,3.286l0.781,0.345L16.678,17.086z M49.6,31.84 l0.047,1.273L27.438,20.998l0.799-1.734L49.6,31.84z M33.031,15.1l12.889,8.82l0.027,0.769L32.551,16.1L33.031,15.1z M22.377,14.045 h9.846l-1.539,3.365l-2.287-1.498h1.371l0.721-1.352h-2.023l-0.553,1.037l-0.541-0.357h-0.34l0.359-0.684h-2.025l-0.361,0.684 h-3.473L22.377,14.045z M23.695,20.678l-0.004,0.004h0.004V20.678z M24.828,18.199h-2.031l-0.719,1.358h2.029L24.828,18.199z  M40.385,34.227c-12.85-7.009-25.729-14.667-38.971-12.527c1.26,8.809,9.08,16.201,8.213,24.328 c-0.553,4.062-3.111,0.828-3.303,7.137c15.799,0,32.379,0,48.166,0l0.066-4.195l1.477-7.23 C50.842,39.812,45.393,36.961,40.385,34.227z M13.99,35.954c-1.213,0-2.195-1.353-2.195-3.035c0-1.665,0.98-3.017,2.195-3.017 c1.219,0,2.195,1.352,2.195,3.017C16.186,34.604,15.213,35.954,13.99,35.954z M23.691,20.682h-2.02l-0.588,1.351h2.023 L23.691,20.682z M19.697,18.199l-0.721,1.358h2.025l0.727-1.358H19.697z',
		car : 'path://M49.592,40.883c-0.053,0.354-0.139,0.697-0.268,0.963c-0.232,0.475-0.455,0.519-1.334,0.475 c-1.135-0.053-2.764,0-4.484,0.068c0,0.476,0.018,0.697,0.018,0.697c0.111,1.299,0.697,1.342,0.931,1.342h3.7 c0.326,0,0.628,0,0.861-0.154c0.301-0.196,0.43-0.772,0.543-1.78c0.017-0.146,0.025-0.336,0.033-0.56v-0.01 c0-0.068,0.008-0.154,0.008-0.25V41.58l0,0C49.6,41.348,49.6,41.09,49.592,40.883L49.592,40.883z M6.057,40.883 c0.053,0.354,0.137,0.697,0.268,0.963c0.23,0.475,0.455,0.519,1.334,0.475c1.137-0.053,2.762,0,4.484,0.068 c0,0.476-0.018,0.697-0.018,0.697c-0.111,1.299-0.697,1.342-0.93,1.342h-3.7c-0.328,0-0.602,0-0.861-0.154 c-0.309-0.18-0.43-0.772-0.541-1.78c-0.018-0.146-0.027-0.336-0.035-0.56v-0.01c0-0.068-0.008-0.154-0.008-0.25V41.58l0,0 C6.057,41.348,6.057,41.09,6.057,40.883L6.057,40.883z M49.867,32.766c0-2.642-0.344-5.224-0.482-5.507 c-0.104-0.207-0.766-0.749-2.271-1.773c-1.522-1.042-1.487-0.887-1.766-1.566c0.25-0.078,0.492-0.224,0.639-0.241 c0.326-0.034,0.345,0.274,1.023,0.274c0.68,0,2.152-0.18,2.453-0.48c0.301-0.303,0.396-0.405,0.396-0.672 c0-0.268-0.156-0.818-0.447-1.146c-0.293-0.327-1.541-0.49-2.273-0.585c-0.729-0.095-0.834,0-1.022,0.121 c-0.304,0.189-0.32,1.919-0.32,1.919l-0.713,0.018c-0.465-1.146-1.11-3.452-2.117-5.269c-1.103-1.979-2.256-2.599-2.737-2.754 c-0.474-0.146-0.904-0.249-4.131-0.576c-3.298-0.344-5.922-0.388-8.262-0.388c-2.342,0-4.967,0.052-8.264,0.388 c-3.229,0.336-3.66,0.43-4.133,0.576s-1.633,0.775-2.736,2.754c-1.006,1.816-1.652,4.123-2.117,5.269L9.87,23.109 c0,0-0.008-1.729-0.318-1.919c-0.189-0.121-0.293-0.225-1.023-0.121c-0.732,0.104-1.98,0.258-2.273,0.585 c-0.293,0.327-0.447,0.878-0.447,1.146c0,0.267,0.094,0.379,0.396,0.672c0.301,0.301,1.773,0.48,2.453,0.48 c0.68,0,0.697-0.309,1.023-0.274c0.146,0.018,0.396,0.163,0.637,0.241c-0.283,0.68-0.24,0.524-1.764,1.566 c-1.506,1.033-2.178,1.566-2.271,1.773c-0.139,0.283-0.482,2.865-0.482,5.508s0.189,5.02,0.189,5.86c0,0.354,0,0.976,0.076,1.565 c0.053,0.354,0.129,0.697,0.268,0.966c0.232,0.473,0.447,0.516,1.334,0.473c1.137-0.051,2.779,0,4.477,0.07 c1.135,0.043,2.297,0.086,3.33,0.11c2.582,0.051,1.826-0.379,2.928-0.36c1.102,0.016,5.447,0.196,9.424,0.196 c3.976,0,8.332-0.182,9.423-0.196c1.102-0.019,0.346,0.411,2.926,0.36c1.033-0.018,2.195-0.067,3.332-0.11 c1.695-0.062,3.348-0.121,4.477-0.07c0.886,0.043,1.103,0,1.332-0.473c0.132-0.269,0.218-0.611,0.269-0.966 c0.086-0.592,0.078-1.213,0.078-1.565C49.678,37.793,49.867,35.408,49.867,32.766L49.867,32.766z M13.219,19.735 c0.412-0.964,1.652-2.9,2.256-3.244c0.145-0.087,1.426-0.491,4.637-0.706c2.953-0.198,6.217-0.276,7.73-0.276 c1.513,0,4.777,0.078,7.729,0.276c3.201,0.215,4.502,0.611,4.639,0.706c0.775,0.533,1.842,2.28,2.256,3.244 c0.412,0.965,0.965,2.858,0.861,3.116c-0.104,0.258,0.104,0.388-1.291,0.275c-1.387-0.103-10.088-0.216-14.185-0.216 c-4.088,0-12.789,0.113-14.184,0.216c-1.395,0.104-1.188-0.018-1.291-0.275C12.254,22.593,12.805,20.708,13.219,19.735 L13.219,19.735z M16.385,30.511c-0.619,0.155-0.988,0.491-1.764,0.482c-0.775,0-2.867-0.353-3.314-0.371 c-0.447-0.017-0.842,0.302-1.076,0.362c-0.23,0.06-0.688-0.104-1.377-0.318c-0.688-0.216-1.092-0.155-1.316-1.094 c-0.232-0.93,0-2.264,0-2.264c1.488-0.068,2.928,0.069,5.621,0.826c2.693,0.758,4.191,2.213,4.191,2.213 S17.004,30.357,16.385,30.511L16.385,30.511z M36.629,37.293c-1.23,0.164-6.386,0.207-8.794,0.207c-2.412,0-7.566-0.051-8.799-0.207 c-1.256-0.164-2.891-1.67-1.764-2.865c1.523-1.627,1.24-1.576,4.701-2.023C24.967,32.018,27.239,32,27.834,32 c0.584,0,2.865,0.025,5.859,0.404c3.461,0.447,3.178,0.396,4.699,2.022C39.521,35.623,37.887,37.129,36.629,37.293L36.629,37.293z  M48.129,29.582c-0.232,0.93-0.629,0.878-1.318,1.093c-0.688,0.216-1.145,0.371-1.377,0.319c-0.231-0.053-0.627-0.371-1.074-0.361 c-0.448,0.018-2.539,0.37-3.313,0.37c-0.772,0-1.146-0.328-1.764-0.481c-0.621-0.154-0.966-0.154-0.966-0.154 s1.49-1.464,4.191-2.213c2.693-0.758,4.131-0.895,5.621-0.826C48.129,27.309,48.361,28.643,48.129,29.582L48.129,29.582z',
		run : 'path://M13.676,32.955c0.919-0.031,1.843-0.008,2.767-0.008v0.007c0.827,0,1.659,0.041,2.486-0.019 c0.417-0.028,1.118,0.325,1.14-0.545c0.014-0.637-0.156-1.279-0.873-1.367c-1.919-0.241-3.858-0.233-5.774,0.019 c-0.465,0.062-0.998,0.442-0.832,1.069C12.715,32.602,13.045,32.977,13.676,32.955z M14.108,29.013 c1.47-0.007,2.96-0.122,4.414,0.035c1.792,0.192,3.1-0.412,4.273-2.105c-3.044,0-5.882,0.014-8.719-0.01 c-0.768-0.005-1.495,0.118-1.461,1C12.642,28.731,13.329,29.014,14.108,29.013z M23.678,36.593c-0.666-0.69-1.258-1.497-2.483-1.448 c-2.341,0.095-4.689,0.051-7.035,0.012c-0.834-0.014-1.599,0.177-1.569,1.066c0.031,0.854,0.812,1.062,1.636,1.043 c1.425-0.033,2.852-0.01,4.278-0.01v-0.01c1.562,0,3.126,0.008,4.691-0.005C23.614,37.239,24.233,37.174,23.678,36.593z  M32.234,42.292h-0.002c-1.075,0.793-2.589,0.345-3.821,1.048c-0.359,0.193-0.663,0.465-0.899,0.799 c-1.068,1.623-2.052,3.301-3.117,4.928c-0.625,0.961-0.386,1.805,0.409,2.395c0.844,0.628,1.874,0.617,2.548-0.299 c1.912-2.573,3.761-5.197,5.621-7.814C33.484,42.619,33.032,42.387,32.234,42.292z M43.527,28.401 c-0.688-1.575-2.012-0.831-3.121-0.895c-1.047-0.058-2.119,1.128-3.002,0.345c-0.768-0.677-1.213-1.804-1.562-2.813 c-0.45-1.305-1.495-2.225-2.329-3.583c2.953,1.139,4.729,0.077,5.592-1.322c0.99-1.61,0.718-3.725-0.627-4.967 c-1.362-1.255-3.414-1.445-4.927-0.452c-1.933,1.268-2.206,2.893-0.899,6.11c-2.098-0.659-3.835-1.654-5.682-2.383 c-0.735-0.291-1.437-1.017-2.293-0.666c-2.263,0.927-4.522,1.885-6.723,2.95c-1.357,0.658-1.649,1.593-1.076,2.638 c0.462,0.851,1.643,1.126,2.806,0.617c0.993-0.433,1.994-0.857,2.951-1.374c1.599-0.86,3.044-0.873,4.604,0.214 c1.017,0.707,0.873,1.137,0.123,1.849c-1.701,1.615-3.516,3.12-4.933,5.006c-1.042,1.388-0.993,2.817,0.255,4.011 c1.538,1.471,3.148,2.869,4.708,4.315c0.485,0.444,0.907,0.896-0.227,1.104c-1.523,0.285-3.021,0.694-4.538,1.006 c-1.109,0.225-2.02,1.259-1.83,2.16c0.223,1.07,1.548,1.756,2.687,1.487c3.003-0.712,6.008-1.413,9.032-2.044 c1.549-0.324,2.273-1.869,1.344-3.115c-0.868-1.156-1.801-2.267-2.639-3.445c-1.964-2.762-1.95-2.771,0.528-5.189 c1.394-1.357,1.379-1.351,2.437,0.417c0.461,0.769,0.854,1.703,1.99,1.613c2.238-0.181,4.407-0.755,6.564-1.331 C43.557,30.447,43.88,29.206,43.527,28.401z',
		walk : 'path://M29.902,23.275c1.86,0,3.368-1.506,3.368-3.365c0-1.859-1.508-3.365-3.368-3.365 c-1.857,0-3.365,1.506-3.365,3.365C26.537,21.769,28.045,23.275,29.902,23.275z M36.867,30.74c-1.666-0.467-3.799-1.6-4.732-4.199 c-0.932-2.6-3.131-2.998-4.797-2.998s-7.098,3.894-7.098,3.894c-1.133,1.001-2.1,6.502-0.967,6.769 c1.133,0.269,1.266-1.533,1.934-3.599c0.666-2.065,3.797-3.466,3.797-3.466s0.201,2.467-0.398,3.866 c-0.599,1.399-1.133,2.866-1.467,6.198s-1.6,3.665-3.799,6.266c-2.199,2.598-0.6,3.797,0.398,3.664 c1.002-0.133,5.865-5.598,6.398-6.998c0.533-1.397,0.668-3.732,0.668-3.732s0,0,2.199,1.867c2.199,1.865,2.332,4.6,2.998,7.73 s2.332,0.934,2.332-0.467c0-1.401,0.269-5.465-1-7.064c-1.265-1.6-3.73-3.465-3.73-5.265s1.199-3.732,1.199-3.732 c0.332,1.667,3.335,3.065,5.599,3.399C38.668,33.206,38.533,31.207,36.867,30.74z'
	};

	var option = {
		title : {
			text : "用户常用操作"
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'none'
			},
			formatter : function(params) {
				return params[0].name + ': ' + params[0].value;
			}
		},
		xAxis : {
			data : [ '驯鹿', '火箭', '飞机', '高铁', '轮船', '汽车', '跑步', '步行', ],
			axisTick : {
				show : false
			},
			axisLine : {
				show : false
			},
			axisLabel : {
				textStyle : {
					color : '#e54035'
				}
			}
		},
		yAxis : {
			splitLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLine : {
				show : false
			},
			axisLabel : {
				show : false
			}
		},
		color : [ '#e54035' ],
		series : [
				{
					name : 'hill',
					type : 'pictorialBar',
					barCategoryGap : '-130%',
					// symbol: 'path://M0,10 L10,10 L5,0 L0,10 z',
					symbol : 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
					itemStyle : {
						normal : {
							opacity : 0.5
						},
						emphasis : {
							opacity : 1
						}
					},
					data : [ 123, 60, 25, 18, 12, 9, 2, 1 ],
					z : 10
				}, {
					name : 'glyph',
					type : 'pictorialBar',
					barGap : '-100%',
					symbolPosition : 'end',
					symbolSize : 50,
					symbolOffset : [ 0, '-120%' ],
					data : [ {
						value : 123,
						symbol : pathSymbols.reindeer,
						symbolSize : [ 60, 60 ]
					}, {
						value : 60,
						symbol : pathSymbols.rocket,
						symbolSize : [ 50, 60 ]
					}, {
						value : 25,
						symbol : pathSymbols.plane,
						symbolSize : [ 65, 35 ]
					}, {
						value : 18,
						symbol : pathSymbols.train,
						symbolSize : [ 50, 30 ]
					}, {
						value : 12,
						symbol : pathSymbols.ship,
						symbolSize : [ 50, 35 ]
					}, {
						value : 9,
						symbol : pathSymbols.car,
						symbolSize : [ 40, 30 ]
					}, {
						value : 2,
						symbol : pathSymbols.run,
						symbolSize : [ 40, 50 ]
					}, {
						value : 1,
						symbol : pathSymbols.walk,
						symbolSize : [ 40, 50 ]
					} ]
				} ]
	};
	return option;
}

function getLatestMemoryOption(list) {

	// var titles = [ 'Http 错误', 'Spring 错误', 'Spring 请求', 'HTTP 请求' ];
	// var titles = [ 'Http 错误', 'HTTP 请求','AJAX GET 请求', 'AJAX POST 请求','AJAX
	// 请求' ];
	var titles = [ 'Java使用内存', '使用内存', '交换内存' ];

	for (var i = 0; i < list.length; i++) {

		if (list[i].id > latestEChartMemoryMaxId) {
			latestEChartMemoryMaxId = list[i].id;
		}

		latestEChartJavaUsedMemorys[i] = list[i].usedMemory / 1000;
		latestEChartsedMemorys[i] = list[i].freePhysicalMemory;
		latestEChartFreeSwapSpaces[i] = list[i].freeSwapSpace;
		latestEChartQueryTimes[i] = list[i].queryTime;
	}

	var option = {
		backgroundColor : '#394056',
		title : {
			text : '',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color : '#F1F1F3'
			},
			left : '6%'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#57617B'
				}
			}
		},
		legend : {
			icon : 'rect',
			itemWidth : 14,
			itemHeight : 5,
			itemGap : 13,
			data : titles,
			right : '4%',
			textStyle : {
				fontSize : 12,
				color : '#F1F1F3'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			data : latestEChartQueryTimes
		} ],
		yAxis : [ {
			type : 'value',
			name : '单位(次)',
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			},
			splitLine : {
				lineStyle : {
					color : '#57617B'
				}
			}
		} ],
		series : [ {
			name : titles[0],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(137, 189, 27, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(137, 189, 27, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(137,189,27)',
					borderColor : 'rgba(137,189,2,0.27)',
					borderWidth : 12

				}
			},

			data : latestEChartJavaUsedMemorys
		}, {
			name : titles[1],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(0, 136, 212, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(0, 136, 212, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(0,136,212)',
					borderColor : 'rgba(0,136,212,0.2)',
					borderWidth : 12

				}
			},
			data : latestEChartsedMemorys
		}, {
			name : titles[2],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(219, 50, 51, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(219, 50, 51, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {

					color : 'rgb(219,50,51)',
					borderColor : 'rgba(219,50,51,0.2)',
					borderWidth : 12
				}
			},
			data : latestEChartFreeSwapSpaces
		} ]
	};
	return option;
}

function getLatestJdbcOption(list) {
	
	var titles = [ '活跃连接数', '已使用连接数' ];
	for (var i = 0; i < list.length; i++) {
		
		if(list[i].id > latestEChartJdbcMaxId){
			latestEChartJdbcMaxId = list[i].id;
		}
		
		latestEChartUsedConnectionCounts[i] = list[i].usedConnectionCount;
		latestEChartActiveConnectionCount[i] = list[i].activeConnectionCount;
		latestEChartQueryTime[i] = list[i].queryTime.substring(11,
				list[i].queryTime.length - 3);
	}
	
	var option = {
		backgroundColor : '#394056',
		title : {
			text : '',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 16,
				color : '#F1F1F3'
			},
			left : '6%'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#57617B'
				}
			}
		},
		legend : {
			icon : 'rect',
			itemWidth : 14,
			itemHeight : 5,
			itemGap : 13,
			data : titles,
			right : '4%',
			textStyle : {
				fontSize : 12,
				color : '#F1F1F3'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			data : latestEChartQueryTime
		} ],
		yAxis : [ {
			type : 'value',
			name : '单位(次)',
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			},
			splitLine : {
				lineStyle : {
					color : '#57617B'
				}
			}
		} ],
		series : [ {
			name : titles[0],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(137, 189, 27, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(137, 189, 27, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(137,189,27)',
					borderColor : 'rgba(137,189,2,0.27)',
					borderWidth : 12

				}
			},
			data : latestEChartUsedConnectionCounts
		}, {
			name : titles[1],
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(0, 136, 212, 0.3)'
					}, {
						offset : 0.8,
						color : 'rgba(0, 136, 212, 0)'
					} ], false),
					shadowColor : 'rgba(0, 0, 0, 0.1)',
					shadowBlur : 10
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(0,136,212)',
					borderColor : 'rgba(0,136,212,0.2)',
					borderWidth : 12

				}
			},
			data : latestEChartActiveConnectionCount
		} ]
	};
	return option;
}

function getSessionNowOption(userCount) {
	var spirit = 'image://data:image/png;base64,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';
	var maxData = 0;
	var data = [ userCount ];
	var title = [ '共 ' + userCount + ' 人 ' ];
	maxData = userCount + userCount * 0.2;
	var option = {
		title : {
			text : '在线用户'
		},
		tooltip : {},
		xAxis : {
			max : maxData,
			splitLine : {
				show : false
			},
			offset : 10,
			axisLine : {
				lineStyle : {
					color : '#999'
				}
			},
			axisLabel : {
				margin : 10
			}
		},
		yAxis : {
			data : title,
			inverse : true,
			axisTick : {
				show : false
			},
			axisLine : {
				show : false
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					color : '#999',
					fontSize : 16
				}
			}
		},
		grid : {
			top : 'center',
			height : 200,
			left : 70,
			right : 100
		},
		series : [ {
			// current data
			type : 'pictorialBar',
			symbol : spirit,
			symbolRepeat : 'fixed',
			symbolMargin : '5%',
			symbolClip : true,
			symbolSize : 30,
			symbolBoundingData : maxData,
			data : data,
			markLine : {
				symbol : 'none',
				label : {
					normal : {
						formatter : 'max: {c}',
						position : 'start'
					}
				},
				lineStyle : {
					normal : {
						color : 'green',
						type : 'dotted',
						opacity : 0.2,
						width : 2
					}
				},
				data : [ {
					type : 'max'
				} ]
			},
			z : 10
		}, {
			// full data
			type : 'pictorialBar',
			itemStyle : {
				normal : {
					opacity : 0.2
				}
			},
			label : {
				normal : {
					show : true,
					formatter : function(params) {
						return params.value;
						// return (params.value / maxData * 100)
						// .toFixed(1)
						// + ' %';
					},
					position : 'outside',
					offset : [ 10, 0 ],
					textStyle : {
						color : 'green',
						fontSize : 18
					}
				}
			},
			animationDuration : 0,
			symbolRepeat : 'fixed',
			symbolMargin : '5%',
			symbol : spirit,
			symbolSize : 30,
			symbolBoundingData : maxData,
			data : [ 891, 1220, 660, 1670 ],
			z : 5
		} ]
	};
	return option;
}
function getJdbcNowOption(obj) {
	
	var javaUsedMemory = obj.usedConnectionCount;
	var javaMaxMemory = obj.maxConnectionCount;

	var color1 = 'green';
	if (javaUsedMemory / javaMaxMemory >= 0.9) {
		color1 = 'red';
	} else {
		if (javaUsedMemory / javaMaxMemory >= 0.7) {
			color1 = 'yellow';
		}
	}

	var option = {
		series : [ {
			center : [ '40.0%', '50%' ],
			radius : [ '60%', '75%' ],
			hoverAnimation : false,
			type : 'pie',
			labelLine : {
				normal : {
					show : false
				}
			},
			data : [
					{
						value : javaUsedMemory,
						name : common_utils_toFixed(javaUsedMemory * 100
								/ javaMaxMemory, 1)
								+ ' %',
						label : {

							normal : {
								position : 'center',
								show : true,
								textStyle : {
									fontSize : '16',
									color : color1
								}
							}
						},
						itemStyle : {
							normal : {
								color : color1
							}
						}
					}, {
						value : javaMaxMemory - javaUsedMemory,
						itemStyle : {
							normal : {
								color : '#BEBEBE'
							}
						}
					} ]
		} ]
	};
	return option;
}
function getHttpNowOption() {
	var option = {
		title : {
			text : 'Http请求',
			subtext : '',
			left : 'center',
			padding : [ 10, 0 ]
		// ,
		// link : 'http://www.baidu.com'
		},
		backgroundColor : '',
		tooltip : {
			formatter : '<div style="text-align: center;">业务指标</div>{b} : {c}'
		},
		series : [ {
			axisLine : {
				show : true,
				lineStyle : {
					color : [ [ 0.166, '#c23531' ], [ 0.5, '#EFC631' ],
							[ 0.835, '#63869e' ], [ 1, '#91c7ae' ] ],
					width : 30
				}
			},
			axisTick : {
				show : true
			},
			axisLabel : {
				distance : 6,
				textStyle : {
					color : 'auto'
				}
			},
			itemStyle : {
				normal : {
					color : 'auto'
				}
			},
			radius : '45%',
			pointer : {
				width : 10
			},
			title : {
				offsetCenter : [ 0, '90%' ]
			},
			detail : {
				textStyle : {
					fontWeight : 'bolder',
					fontSize : 23,
					color : 'black'
				},
				offsetCenter : [ 0, '30%' ],
				formatter : '{value}'
			},
			min : 0,
			max : 120,
			name : 'Http请求',
			type : 'gauge',
			show : false,
			splitNumber : 12,

			data : [ {
				value : '33',
				name : '最新Http请求信息'
			} ]
		} ]
	};
	return option;
}

function getResponseNowOption(value,valueName) {

	var option = {
		"series" : [ {
			"center" : [ "45%", "50%" ],
			"radius" : [ "70%", "80%" ],
			"clockWise" : false,
			"hoverAnimation" : false,
			"type" : "pie",
			"itemStyle" : {
				"normal" : {
					"label" : {
						"show" : true,
						"textStyle" : {
							"fontSize" : 15,
							"fontWeight" : "bold"
						},
						"position" : "center"
					},
					"labelLine" : {
						"show" : false
					},
					"color" : "#5886f0",
					"borderColor" : "#5886f0",
					"borderWidth" : 15
				},
				"emphasis" : {
					"label" : {
						"textStyle" : {
							"fontSize" : 15,
							"fontWeight" : "bold"
						}
					},
					"color" : "#5886f0",
					"borderColor" : "#5886f0",
					"borderWidth" : 25
				}
			},
			"data" : [ {
				"value" : value,
				"name" : valueName
			}, {
				"name" : " ",
				"value" : 47.3,
				"itemStyle" : {
					"normal" : {
						"label" : {
							"show" : false
						},
						"labelLine" : {
							"show" : false
						},
						"color" : "#5886f0",
						"borderColor" : "#5886f0",
						"borderWidth" : 0
					},
					"emphasis" : {
						"color" : "#5886f0",
						"borderColor" : "#5886f0",
						"borderWidth" : 0
					}
				}
			} ]
		} ]
	};
	return option;

}

function getMemoryNowOption(obj) {
	
	var javaUsedMemory = obj.usedMemory / 1000;
	var javaMaxMemory = obj.maxMemory / 1000;
	
	var usedMemory = obj.freePhysicalMemory;
	var maxMemory = obj.totalPhysicalMemory;
	
	var freeSwapMemory = obj.freeSwapSpace;
	var maxSwapMemory = obj.totalSwapSpace;
	
	var color1 = 'green';
	if (javaUsedMemory / javaMaxMemory >= 0.9) {
		color1 = 'red';
	} else {
		if (javaUsedMemory / javaMaxMemory >= 0.7) {
			color1 = 'yellow';
		}
	}

	var color2 = 'green';
	if (usedMemory / maxMemory >= 0.9) {
		color2 = 'red';
	} else {
		if (usedMemory / maxMemory >= 0.7) {
			color2 = 'yellow';
		}
	}

	var color3 = 'green';
	if (freeSwapMemory / maxSwapMemory >= 0.9) {
		color3 = 'red';
	} else {
		if (freeSwapMemory / maxSwapMemory >= 0.7) {
			color3 = 'yellow';
		}
	}

	var option = {
		series : [
				{
					center : [ '15.0%', '50%' ],
					radius : [ '60%', '75%' ],
					hoverAnimation : false,
					type : 'pie',
					labelLine : {
						normal : {
							show : false
						}
					},
					data : [
							{
								value : javaUsedMemory,
								name : common_utils_toFixed(javaUsedMemory
										* 100 / javaMaxMemory, 1)
										+ ' %',
								label : {

									normal : {
										position : 'center',
										show : true,
										textStyle : {
											fontSize : '16',
											color : color1
										}
									}
								},
								itemStyle : {
									normal : {
										color : color1
									}
								}
							}, {
								value : javaMaxMemory - javaUsedMemory,
								itemStyle : {
									normal : {
										color : '#BEBEBE'
									}
								}
							} ]
				},
				{
					center : [ '45.0%', '50%' ],
					radius : [ '60%', '75%' ],
					hoverAnimation : false,
					type : 'pie',
					labelLine : {
						normal : {
							show : false
						}
					},
					data : [
							{
								value : usedMemory,
								name : common_utils_toFixed(usedMemory * 100
										/ maxMemory, 1)
										+ ' %',
								label : {
									normal : {
										position : 'center',
										show : true,
										textStyle : {
											fontSize : '16',
											color : color2
										}
									}
								},
								itemStyle : {
									normal : {
										color : color2
									}
								}
							}, {
								value : maxMemory - usedMemory,
								itemStyle : {
									normal : {
										color : '#BEBEBE'
									}
								}
							} ]
				},
				{
					center : [ '75%', '50%' ],
					radius : [ '60%', '75%' ],
					hoverAnimation : false,
					type : 'pie',
					labelLine : {
						normal : {
							show : false
						}
					},
					data : [
							{
								value : freeSwapMemory,
								name : common_utils_toFixed(freeSwapMemory
										* 100 / maxSwapMemory, 1)
										+ ' %',
								label : {
									normal : {
										position : 'center',
										show : true,
										textStyle : {
											fontSize : '16',
											color : color3
										}
									}
								},
								itemStyle : {
									normal : {
										color : color3
									}
								}
							}, {
								value : maxSwapMemory - freeSwapMemory,
								itemStyle : {
									normal : {
										color : '#BEBEBE'
									}
								}
							} ]
				} ]
	};
	return option;
}

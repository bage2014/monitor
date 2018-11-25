// angularjs params
var app = angular.module('PanelApp', []);	
app.controller('PanelController', function($scope, $http) {
	$scope.listTbodyThreads = [];
	$scope.listSessions = [];
	$scope.listProcesses = [];
	$scope.memoryInformations = {};	// 
	$scope.cpu = {};	// 
	$scope.databaseInformations = {};
	$scope.listsql = {};
	$scope.pageLimit = 5;
});

var eChartMemory = null;
var dataMemory = [];



$(document).ready(function() {
	initThreadsTbody();
	initSessionsTbody();
	initSessionTbody();
	initProcessesTbody();
	getJVMPart();
	getDatabasePart();

	// init
	initMemoryEchart();
	
});

function getDatabasePart() {
	var url = "../monitoring?format=json";
	url += '&part=database';
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get root obj
				var scope = angular.element($('body')).scope();
				scope.databaseInformations = resultObj.databaseInformations; // get list
				scope.$apply();

			} catch (e) {
				$("#header_a_globalTips").html("getDatabasePart 返回数据解析失败");
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("getDatabasePart Request failed: " + textStatus);
			});
}


function initSessionTbody(){
	var url = "../monitoring?format=json&part=session";
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get root obj
				var list = resultObj.list; // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.listsql = list[1]; // get list
					scope.$apply();
				} else {
					// $(".alert").alert()
					$("#header_a_globalTips").html("initSessionTbody 返回数据为空");
				}
			} catch (e) {
				$("#header_a_globalTips").html("initSessionTbody 返回数据解析失败");
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("initSessionTbody Request failed: " + textStatus);
			});

}

function initSessionsTbody(){
	var url = "../monitoring?format=json&part=sessions";
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get root obj
				var list = resultObj.list; // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.listSessions = list; // get list
					scope.$apply();
				} else {
					// $(".alert").alert()
					$("#header_a_globalTips").html("initSessionsTbody 返回数据为空");
				}
			} catch (e) {
				$("#header_a_globalTips").html("initSessionsTbody 返回数据解析失败");
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("initSessionsTbody Request failed: " + textStatus);
			});
	
}

function initProcessesTbody(){
	var url = "../monitoring?format=json&part=processes";
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get root obj
				var list = resultObj.list; // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.listProcesses = list; // get list
					scope.$apply();
				} else {
					$("#header_a_globalTips").html(" initProcessesTbody返回数据为空");
				}
			} catch (e) {
				$("#header_a_globalTips").html("initProcessesTbody 返回数据解析失败");
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("initProcessesTbody Request failed: " + textStatus);
			});
	
}
function initThreadsTbody(){
	var url = "../monitoring?format=json&part=threads";
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var resultObj = JSON.parse(responseText); // get root obj
				var list = resultObj.list; // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.listTbodyThreads = list; // get list
					scope.$apply();
				} else {
					$("#header_a_globalTips").html("initThreadsTbody 返回数据为空");
				}
			} catch (e) {
				$("#header_a_globalTips").html("initThreadsTbody 返回数据解析失败");
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("initThreadsTbody Request failed: " + textStatus);
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
				var resultObj = JSON.parse(responseText); // get root obj
				var list = resultObj.list; // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.memoryInformations = list[0].memoryInformations; // get list
					scope.cpu = list[0]; // get list
					scope.$apply();
					//parseMemoryInformations(list[0].memoryInformations); // list[0]
				} else {
					$("#header_a_globalTips").html("getJVMPart 返回数据为空");
				}
			} catch (e) {
				$("#header_a_globalTips").html("getJVMPart 返回数据解析失败");
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("getJVMPart Request failed: " + textStatus);
			});
}

// 解析 memoryInformations 信息
function parseMemoryInformations(memoryInformations) {
	var usedMemory = memoryInformations.usedMemory;
	var maxMemory = memoryInformations.maxMemory;
	var usedPermGen = memoryInformations.usedPermGen;
	// TODO
	var dataPairs = common_utils_sizeFormat_twoParam(usedMemory, maxMemory);
	//alert(JSON.stringify(dataPairs));
	common_utils_debug(dataPairs[0][0]);	
	common_utils_debug(dataPairs[0][1]);
	common_utils_debug(dataPairs[1][0]);
	common_utils_debug(dataPairs[1][1]);	
	
	//setTimeout("getJVMPart()", 20000);

}

function queryLatest(maxId) {
	var url = "../memoryinfo/latest";
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		data : "maxId=" + maxId,
		dataType : "html"
	}).done(function(responseText) {		
		if (responseText != undefined) {
			try {
				var list = JSON.parse(responseText); // get root obj
				if (list != undefined && list.length > 0) {
					setMemoryEchartData(list,maxId);
				} else {
					$("#header_a_globalTips").html("getJVMPart 返回数据为空");
				}
			} catch (e) {
				$("#header_a_globalTips").html("getJVMPart 返回数据解析失败");
			}
		}
	}).fail(			
			function(jqXHR, textStatus) {
				$("#header_a_globalTips").html("getJVMPart Request failed: " + textStatus);
			});	
}


function initMemoryEchart(){
	eChartMemory = echarts.init(document.getElementById('index_div_memory'));
	eChartMemory.showLoading();
	var url = "../memoryinfo/init";
	$.ajax({
		url : url,
		method : "GET",
		// async : false,
		dataType : "html"
	}).done(function(responseText) {		
		if (responseText != undefined) {
			try {
				var list = JSON.parse(responseText); // get root obj
				if (list != undefined && list.length > 0) {
					initMemoryEchartData(list);
				} else {
					eChartMemory.hideLoading();
					$("#header_a_globalTips").html("getJVMPart 返回数据为空");
				}
			} catch (e) {
				eChartMemory.hideLoading();
				$("#header_a_globalTips").html("getJVMPart 返回数据解析失败");
			}
		}
	}).fail(			
			function(jqXHR, textStatus) {
				eChartMemory.hideLoading();
				$("#header_a_globalTips").html("getJVMPart Request failed: " + textStatus);
			});	
	
}

function setMemoryEchartData(list,maxId){
	var length = list.length;
	if(length > 0){
		maxId = list[0].id;
		for (var i = 0; i < length; i++) {
			dataMemory.shift();
			dataMemory.push({
				name:list[length - 1 - i].querytime,
				value:[list[length - 1 - i].querytime,common_utils_sizeFormat_MB(list[i].usedMemory)]
			});
		}
		eChartMemory.setOption({
			series : [ {
				data : dataMemory
			} ]
		});
	}
	setTimeout("queryLatest("+maxId+")", 10000);
}

function initMemoryEchartData(list){	
	
	var length = list.length;
	var maxId = length > 0 ? list[0].id : 0;
	for (var i = 0; i < length; i++) {
		dataMemory.push({
			name:list[length - 1 - i].querytime,
			value:[list[length - 1 - i].querytime,common_utils_sizeFormat_MB(list[i].usedMemory)]
		});
	}	
	var optionMemory = {
			title : {
				text : 'Memory Info'
			},
			tooltip : {
				trigger : 'axis',
				formatter : function(params) {
					params = params[0];
					var date = new Date(params.name);
					return date.getHours() + ':' + date.getMinutes() + ':'
					+ date.getSeconds() + ' : ' + params.value[1] + " MB";
				},
				axisPointer : {
					animation : false
				}
			},
			xAxis : {
				type : 'time',
				splitLine : {
					show : false
				}
			},
			yAxis : {
				type : 'value',
				boundaryGap : [ 0, '100%' ],
				splitLine : {
					show : false
				}
			},
			series : [ {
				name : 'random data',
				type : 'line',
				showSymbol : false,
				hoverAnimation : false,
				data : dataMemory,
				markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            }
			} ]
	};
	eChartMemory.hideLoading();
	eChartMemory.setOption(optionMemory);
	setTimeout("queryLatest("+maxId+")", 10000);
}

/**
 * 解析 threads 信息
 * 
 * @param threads
 * @returns
 */
function parseThreads(threads) {
	for (var i = 0, length = threads.length; i < length; i++) {
		var thread = threads[i];
		var name = thread.name;
		var id = thread.id;
		var priority = thread.priority;
		var daemon = thread.daemon;
		var state = thread.state;
		var cpuTimeMillis = thread.cpuTimeMillis;
		// TODO
	}
	// if(threads.length > 0){
	// alert(threads[0].name);
	// }
}

/**
 * 解析 sessions 信息
 * 
 * @param sessions
 * @returns
 */
function parseSessions(sessions) {
	for (var i = 0, length = sessions.length; i < length; i++) {
		var session = sessions[i];
		var id = session.id;
		var lastAccess = session.lastAccess;
		var age = session.age;
		var expirationDate = session.expirationDate;
		var attributeCount = session.attributeCount;
		var serializable = session.serializable;
		var country = session.country;
		var remoteAddr = session.remoteAddr;
		var userAgent = session.userAgent;
		var serializedSize = session.serializedSize;
		// TODO
	}
	// if(sessions.length > 0){
	// alert(sessions[0].serializedSize);
	// }
}

/**
 * 解析 processes 信息
 * 
 * @param processes
 * @returns
 */
function parseProcesses(processes) {
	for (var i = 0, length = processes.length; i < length; i++) {
		var process = processes[i];
		var user = process.user;
		var pid = process.pid;
		var cpuPercentage = process.cpuPercentage;
		var memPercentage = process.memPercentage;
		var vsz = process.vsz;
		var rss = process.rss;
		var cpuTime = process.cpuTime;
		var command = process.command;
		// TODO
	}
	// if(processes.length > 0){
	// alert(processes[0].user);
	// }
}

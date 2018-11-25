// 
var app = angular.module('IndexApp', []);
app.controller('IndexController', function($scope, $http) {
	$scope.listAppInfo = {};
	$scope.listAppInfoHelper = {};
	$scope.listHttpInfo = {};
	$scope.listAppInfoForMap = {};
	$scope.listAppInfoForMapCurrent = {};
	$scope.pageLength = 10;

});
var topEChartMemoryDiv = null;
var topEChartHttpDiv = null;
var topEChartSqlDiv = null;
var topEChartSessionDiv = null;
var topEChartJdbcDiv = null;

var getTopAppInfoAjaxReq = null;
var getTopMemoryInfoAjaxReq = null;
var getTopHttpInfoAjaxReq = null;
var getTopSqlInfoAjaxReq = null;
var getTopSessionInfoAjaxReq = null;
var getTopJdbcInfoAjaxReq = null;
var getAppsInfoAjaxReq = null;

var currentTab_id = null;

// latestHour latestDay latestMonth latestYear
var sessionSortType = "DESC";
var sessionDurationType = "latestYear";
var memorySortType = "DESC";
var memoryDurationType = "latestYear";
var httpSortType = "DESC";
var httpDurationType = "latestYear";
var jdbcSortType = "DESC";
var jdbcDurationType = "latestYear";

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
var ecConfig = echarts.config;  
$(document).ready(
		function() {
			
			topEChartSessionDiv = echarts.init(document
					.getElementById('div_topn_session'));
			topEChartMemoryDiv = echarts.init(document
					.getElementById('div_topn_memory'));
			topEChartHttpDiv = echarts.init(document
					.getElementById('div_topn_http'));
			topEChartJdbcDiv = echarts.init(document
					.getElementById('div_topn_jdbc'));
				
			// ///////////////////////////////////////////
			getTopSessionInfo();
			getTopMemoryInfo();
			getTopHttpInfo();
			getTopJdbcInfo();
			$(".tab_div").hide();
			$("#div_basic").show();
			
			getAppsInfo(1);
			getStatisticsAppsInfo();
			
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

function getStatisticsAppsInfo(){
	
	var url = "../appstatistics/queryAll";
	var data = 'condition='+$('#input_search_key_sta').val();
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
									
									var scope = angular.element($('body')).scope();
									scope.listAppInfoForMap = resParam.data; // get
									setDataForAppMap(resParam.data);
									setAppByType(resParam.data);
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

function select_tab(tab_id) {
	if (tab_id != null && tab_id != undefined) {
		$(".tab_div").hide();
		$("#" + tab_id).show();
		$('.collection a').attr('class', 'collection-item');
		$('#a_' + tab_id).attr('class', 'collection-item active');

	}
}


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

	// 真正去拿数据
	if (currentTab_id.indexOf('http') > 0) {// 相当于 contains
		if('TRUE' == isCustom){
			alert(startDate + ' ' + startTime + ' --> ' + stopDate + ' '
					+ stopTime);
			$('#p_http_durationTime').text(
					startDate + ' ' + startTime + ' --> ' + stopDate + ' '
							+ stopTime);
		}else{
			sessionDurationType = newDurationType;
			$('#p_http_durationTime').text(durationTypeObject[newDurationType]);
		}
		
		if (getTopHttpInfoAjaxReq != null) {
			getTopHttpInfoAjaxReq.abort();
		}
		getTopHttpInfo();
	}
	if (currentTab_id.indexOf('memory') > 0) {
		if('TRUE' == isCustom){
			$('#p_memory_durationTime').text(
					startDate + ' ' + startTime + ' --> ' + stopDate + ' '
							+ stopTime);
		}else{
			$('#p_memory_durationTime').text(durationTypeObject[newDurationType]);
		}
		if (getTopMemoryInfoAjaxReq != null) {
			getTopMemoryInfoAjaxReq.abort();
		}
		getTopMemoryInfo();
	}
	if (currentTab_id.indexOf('sql') > 0) {
		if('TRUE' == isCustom){
			$('#p_sql_durationTime').text(
					startDate + ' ' + startTime + ' --> ' + stopDate + ' '
							+ stopTime);
		}else{
			$('#p_sql_durationTime').text(durationTypeObject[newDurationType]);
		}
		if (getTopSqlInfoAjaxReq != null) {
			getTopSqlInfoAjaxReq.abort();
		}
		getTopSqlInfo();
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
	if (currentTab_id.indexOf('jdbc') > 0) {
		if('TRUE' == isCustom){
			$('#p_jdbc_durationTime').text(
					startDate + ' ' + startTime + ' --> ' + stopDate + ' '
							+ stopTime);
		}else{
			$('#p_jdbc_durationTime').text(durationTypeObject[newDurationType]);
		}
		if (getTopJdbcInfoAjaxReq != null) {
			getTopJdbcInfoAjaxReq.abort();
		}
		getTopJdbcInfo();
	}
	
}
function setSessionDurationType(newDurationType){
	sessionDurationType = newDurationType;
	$('#p_session_durationTime').text('');
	if (getTopSessionInfoAjaxReq != null) {
		getTopSessionInfoAjaxReq.abort();
	}
	getTopSessionInfo();
}
function setMemoryDurationType(newDurationType){
	memoryDurationType = newDurationType;
	$('#p_memory_durationTime').text('');
	if (getTopMemoryInfoAjaxReq != null) {
		getTopMemoryInfoAjaxReq.abort();
	}
	getTopMemoryInfo();
}
function setHttpDurationType(newDurationType){
	httpDurationType = newDurationType;
	$('#p_http_durationTime').text('');
	if (getTopHttpInfoAjaxReq != null) {
		getTopHttpInfoAjaxReq.abort();
	}
	getTopHttpInfo();
}
function setJdbcDurationType(newDurationType){
	jdbcDurationType = newDurationType;
	$('#p_jdbc_durationTime').text('');
	if (getTopJdbcInfoAjaxReq != null) {
		getTopJdbcInfoAjaxReq.abort();
	}
	getTopJdbcInfo();
}

// //////////
function setSessionSortType(){
	sessionSortType = sessionSortType == 'DESC' ? 'ASC' : 'DESC';
	if (getTopSessionInfoAjaxReq != null) {
		getTopSessionInfoAjaxReq.abort();
	}
	getTopSessionInfo();
}
function setMemorySortType(){
	memorySortType = memorySortType == 'DESC' ? 'ASC' : 'DESC';
	if (getTopMemoryInfoAjaxReq != null) {
		getTopMemoryInfoAjaxReq.abort();
	}
	getTopMemoryInfo();
}
function setHttpSortType(){
	httpSortType = httpSortType == 'DESC' ? 'ASC' : 'DESC';
	if (getTopHttpInfoAjaxReq != null) {
		getTopHttpInfoAjaxReq.abort();
	}
	getTopHttpInfo();
}
function setJdbcSortType(){
	jdbcSortType = jdbcSortType == 'DESC' ? 'ASC' : 'DESC';
	if (getTopJdbcInfoAjaxReq != null) {
		getTopJdbcInfoAjaxReq.abort();
	}
	getTopJdbcInfo();
}

function setAppByType(list){
	
	var count1 = 0;
	var count2 = 0;
	var count3 = 0;
	var data = list;
	for (var i = 0; i < data.length; i++) {
		var geoCoord = [ data[i].lat, data[i].lan ];
		if(data[i].amemory <= commonConstraits.memoryStopped
				|| data[i].ahttp <= commonConstraits.httpStopped
				|| data[i].ajdbc <= commonConstraits.jdbcStopped
				|| data[i].assesion <= commonConstraits.sessionStopped
				|| data[i].asql <= commonConstraits.sqlStopped
				){
			count1 ++;
		}else if(data[i].amemory > commonConstraits.memoryWarn
				&& data[i].ahttp > commonConstraits.httpWarn
				&& data[i].ajdbc > commonConstraits.jdbcWarn
				&& data[i].asession > commonConstraits.sessionWarn
				&& data[i].asql > commonConstraits.sqlWarn 
				){
			count3 ++;
		}else{
			count2 ++;
		}
	}
		
	var datas = [count3,count2,count1];
	var titles = ['良好','警告','停止'];
	var colors = ['#03a9f4', '#ffeb3b', '#f44336'];
	var option = {
		    backgroundColor: commonConstraits.baseBackgroundColor,
			title : {
				text : ''
			},
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)",

		    },
		    legend: {
		        orient: 'vertical',
		        x: 'right',
		        itemWidth: 14,
		        itemHeight: 14,
		        align: 'left',
		    
		        data:titles,
		            textStyle: {
		            color: '#fff'
		        }
		    },
		    series: [
		        {
		            name:'访问来源',
		            type:'pie',
		            hoverAnimation: false,
		            legendHoverLink:false,
		            radius: ['40%', '42%'],
		            color: colors,
		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                },
		               
		            },
		            tooltip: {
		               show:false,
		               
		               
		            },
		            data:[
		                {value:datas[0], name:''},
		                {value:datas[1], name:''},
		                {value:datas[2], name:''}
		            ]
		        },
		        {
		            name:'访问来源',
		            type:'pie',
		            radius: ['42%', '55%'],
		            color: colors,
		            label: {
		                normal: {
		                    formatter: '{b}\n{d}%'
		                },
		          
		            },
		            data:[
		            	{value:datas[0], name:titles[0]},
		                {value:datas[1], name:titles[1]},
		                {value:datas[2], name:titles[2]}
		         
		            ]
		        }
		    ]
		};
	
	var appMap = echarts.init(document.getElementById('div_apps_types'));
	appMap.setOption(option);
}
function setDataForAppMap(list) {
	var data = list;
	var convertData1 = function(data) {//停止
		var res = [];
		for (var i = 0; i < data.length; i++) {
			var geoCoord = [ data[i].lat, data[i].lan ];
				
			if(data[i].amemory <= commonConstraits.memoryStopped
					|| data[i].ahttp <= commonConstraits.httpStopped
					|| data[i].ajdbc <= commonConstraits.jdbcStopped
					|| data[i].asession <= commonConstraits.sessionStopped
					|| data[i].asql <= commonConstraits.sqlStopped
					){
				if (geoCoord) {
					var vag = data[i].amemory + data[i].ahttp  + data[i].ajdbc + data[i].asession + data[i].asql;
					res.push({
						name : data[i].appdesc,
						value : geoCoord.concat(common_utils_toFixed(vag / 5,2))
					});
				}
			}
		}
		return res;
	};
	var convertData2 = function(data) {//警告
		var res = [];
		for (var i = 0; i < data.length; i++) {
			var geoCoord = [ data[i].lat, data[i].lan ];
			
			if(data[i].amemory <= commonConstraits.memoryStopped
					|| data[i].ahttp <= commonConstraits.httpStopped
					|| data[i].ajdbc <= commonConstraits.jdbcStopped
					|| data[i].assesion <= commonConstraits.sessionStopped
					|| data[i].asql <= commonConstraits.sqlStopped
					){
				continue;
			}
			if(data[i].amemory > commonConstraits.memoryWarn
					&& data[i].ahttp > commonConstraits.httpWarn
					&& data[i].ajdbc > commonConstraits.jdbcWarn
					&& data[i].asession > commonConstraits.sessionWarn
					&& data[i].asql > commonConstraits.sqlWarn){
				continue;
			}
			if (geoCoord) {
				var vag = data[i].amemory + data[i].ahttp  + data[i].ajdbc + data[i].asession + data[i].asql;
				res.push({
					name : data[i].appdesc,
					value : geoCoord.concat(common_utils_toFixed(vag / 5,2))
				});
			}
		}
		return res;
	};
	var convertData3 = function(data) {// 良好
		var res = [];
		for (var i = 0; i < data.length; i++) {
			var geoCoord = [ data[i].lat, data[i].lan ];
			if(data[i].amemory > commonConstraits.memoryWarn
					&& data[i].ahttp > commonConstraits.httpWarn
					&& data[i].ajdbc > commonConstraits.jdbcWarn
					&& data[i].asession > commonConstraits.sessionWarn
					&& data[i].asql > commonConstraits.sqlWarn 
					){
				if (geoCoord) {
					var vag = data[i].amemory + data[i].ahttp  + data[i].ajdbc + data[i].asession + data[i].asql;
					res.push({
						name : data[i].appdesc,
						value : geoCoord.concat(common_utils_toFixed(vag / 5,2))
					});
				}
			}
		}
		return res;
	};
	var option = {
		backgroundColor : '#404a59',
//		tooltip : {
//			trigger : 'item'
//		},
		 tooltip: {
		        trigger: 'item',
		        formatter: function (params) {
		            return '健康度( ' + params.name +' ) : ' + params.value[2];
		        }
		    },
		title: {
	        text: '应用列表',
	        left: 'left',
	        textStyle: {
	            color: '#fff'
	        }
	    },
		geo : {
			map : 'china',
			zoom : 1.2,
			label : {
				emphasis : {
					show : false
				}
			},
			roam : true,
			itemStyle : {
				normal : {
					areaColor : '#323c48',
					borderColor : '#111'
				},
				emphasis : {
					areaColor : '#2a333d'
				}
			}
		},
		series : [
			{
				name : '停止',
				type : 'scatter',
				coordinateSystem : 'geo',
				data : convertData1(data.sort(function(a, b) {
					return b.value - a.value;
				})),

				rippleEffect : {
					brushType : 'stroke'
				},
				hoverAnimation : true,
				label : {
					normal : {
						formatter : '{b}',
						position : 'right',
						show : true
					}
				},
				itemStyle : {
					normal : {
						color : '#f44336'
					}
				}
			},
			{
				name : '警告',
				type : 'effectScatter',
				coordinateSystem : 'geo',
				data : convertData2(data.sort(function(a, b) {
					return b.value - a.value;
				})),

				rippleEffect : {
					brushType : 'stroke'
				},
				hoverAnimation : true,
				label : {
					normal : {
						formatter : '{b}',
						position : 'right',
						show : true
					}
				},
				itemStyle : {
					normal : {
						color : '#f4e925',
						shadowBlur : 10,
						shadowColor : '#333'
					}
				},
				zlevel : 1
			},
		{
			name : '良好',
			type : 'effectScatter',
			coordinateSystem : 'geo',
			data : convertData3(data.sort(function(a, b) {
				return b.value - a.value;
			})),

			rippleEffect : {
				brushType : 'stroke'
			},
			hoverAnimation : true,
			label : {
				normal : {
					formatter : '{b}',
					position : 'right',
					show : true
				}
			},
			itemStyle : {
				normal : {
					color : '#03a9f4',
					shadowBlur : 10,
					shadowColor : '#333'
				}
			},
			zlevel : 1
		} ]
	};
	var appMap = echarts.init(document.getElementById('div_apps_map'));
	appMap.setOption(option);
	appMap.on("click", function (param) {
		var newList = [];
		if(param.data != undefined){
			var scope = angular.element($('body')).scope();
			var datas = scope.listAppInfoForMap; // get
			for(var i = 0;i < datas.length;i ++){
				if(datas[i].lat == param.value[0] && datas[i].lan == param.value[1]){
					newList[newList.length] = datas[i];
				}
			}
			scope.listAppInfoForMapCurrent = newList;
			scope.$apply();
			$('#index_modal_jumpToDetail').modal('open');
		}

//	    var mes = '【' + param.type + '】';
//	    if (typeof param.seriesIndex != 'undefined') {
//	        mes += '  seriesIndex : ' + param.seriesIndex;
//	        mes += '  dataIndex : ' + param.dataIndex;
//	    }
//	    alert(mes);
	});
}

function customDatetime(currentTabId ) {
	currentTab_id = currentTabId;
	$('#index_modal_customDatetime').modal('open');
}

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

function getTopSqlInfo() {
	topEChartSqlDiv.showLoading();
	// 获取参数
	var startTime = '';
	var stopTime = '';
	if ($('#p_sql_durationTime').text().indexOf(' --> ') > 0) {
		var durationTime = $('#p_sql_durationTime').text().split(' --> ');
		startTime = durationTime[0].trim();
		stopTime = durationTime[1].trim();
	}else{
		sqlDurationType = durationTypeObject[$('#p_sql_durationTime').text()];
	}

	var url = "../sqlstatistics/topN";
	var data = "durationType=" + sqlDurationType;
	data += "&sortType=" + memorySortType;
	data += "&startTime=" + startTime;
	data += "&stopTime=" + stopTime;

	getTopSqlInfoAjaxReq = $
			.ajax({
				url : url,
				method : "GET",
				async : true,
				data : data,
				dataType : "html"
			})
			.done(
					function(responseText) {
						topEChartSqlDiv.hideLoading();
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
									topEChartSqlDiv
											.setOption(getTopSqlOption(resParam.data));
									window.onresize = topEChartSqlDiv.resize;
								} else {
									Materialize.toast(resParam.header.des,
											4000, 'red rounded');
								}
							} else {
								Materialize.toast("服务器数据异常", 4000,
										'red rounded');
							}

						} else {
							Materialize.toast("返回数据为空", 4000, 'red rounded');
						}

					}).fail(
					function(jqXHR, textStatus) {
						topEChartSqlDiv.hideLoading();
						// Materialize.toast(message, displayLength, className,
						// completeCallback);
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function getTopJdbcInfo() {
	topEChartJdbcDiv.showLoading();
	// 获取参数
	var memoryDurationType = $("#jdbcDurationType option:selected").val(); // 获取选中的项
	var memorySortType = $("#jdbcSortType option:selected").val(); // 获取选中的项
	var startTime = '';
	var stopTime = '';
	if ($('#p_jdbc_durationTime').text().indexOf(' --> ') > 0) {
		var durationTime = $('#p_jdbc_durationTime').text().split(' --> ');
		startTime = durationTime[0].trim();
		stopTime = durationTime[1].trim();
	}

	var url = "../sessionstatistics/topN";
	var data = "durationType=" + jdbcDurationType;
	data += "&sortType=" + jdbcSortType;
	data += "&startTime=" + startTime;
	data += "&stopTime=" + stopTime;

	var url = "../jdbcstatistics/topN";
	getTopJdbcInfoAjaxReq = $
			.ajax({
				url : url,
				method : "GET",
				async : true,
				data : data,
				dataType : "html"
			})
			.done(
					function(responseText) {
						topEChartJdbcDiv.hideLoading();
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
									topEChartJdbcDiv
											.setOption(getTopJdbcOption(resParam.data));
									window.onresize = topEChartJdbcDiv.resize;
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
						topEChartJdbcDiv.hideLoading();
						// Materialize.toast(message, displayLength, className,
						// completeCallback);
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

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
	
	var url = "../sessionstatistics/topN";
	var data = "durationType=" + sessionDurationType;
	data += "&sortType=" + sessionSortType;
	data += "&startTime=" + startTime;
	data += "&stopTime=" + stopTime;

	getTopSessionInfoAjaxReq = $
			.ajax({
				url : url,
				method : "GET",
				async : true,
				data : data,
				dataType : "html"
			})
			.done(
					function(responseText) {+
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

function getTopMemoryInfo() {
	topEChartMemoryDiv.showLoading();

	// 获取参数
	var startTime = '';
	var stopTime = '';
	if ($('#p_memory_durationTime').text().indexOf(' --> ') > 0) {
		var durationTime = $('#p_memory_durationTime').text().split(' --> ');
		startTime = durationTime[0].trim();
		stopTime = durationTime[1].trim();
	}else{
		// 获取数据
		memoryDurationType = durationTypeObject[$('#p_memory_durationTime').text()];
	}

	var url = "../memorystatistics/topN";
	var data = "durationType=" + memoryDurationType;
	data += "&sortType=" + memorySortType;
	data += "&startTime=" + startTime;
	data += "&stopTime=" + stopTime;

	getTopMemoryInfoAjaxReq = $
			.ajax({
				url : url,
				method : "GET",
				data : data,
				async : true,
				dataType : "html"
			})
			.done(
					function(responseText) {
						topEChartMemoryDiv.hideLoading();
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
									topEChartMemoryDiv
											.setOption(getTopMemoryOption(resParam.data));
									
									window.onresize = topEChartMemoryDiv.resize;
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
						topEChartMemoryDiv.hideLoading();
						Materialize.toast(jqXHR.status + "Request failed: "
								+ textStatus, 4000, 'red rounded');
					});
}

function getTopHttpInfo() {
	topEChartHttpDiv.showLoading();
	// 获取参数
	var durationType = $("#httpDurationType option:selected").val(); // 获取选中的项
	var sortType = $("#httpSortType option:selected").val(); // 获取选中的项
	var startTime = '';
	var stopTime = '';
	if ($('#p_http_durationTime').text().indexOf(' --> ') > 0) {
		var durationTime = $('#p_http_durationTime').text().split(' --> ');
		startTime = durationTime[0].trim();
		stopTime = durationTime[1].trim();
	}else{
		httpDurationType = durationTypeObject[$('#p_http_durationTime').text()];
	}

	var url = "../httpstatistics/topN";
	var data = "durationType=" + httpDurationType;
	data += "&sortType=" + httpSortType;
	data += "&startTime=" + startTime;
	data += "&stopTime=" + stopTime;
	getTopHttpInfoAjaxReq = $
			.ajax({
				url : url,
				method : "GET",
				async : true,
				data : data,
				dataType : "html"
			})
			.done(
					function(responseText) {
						topEChartHttpDiv.hideLoading();
						if (responseText != undefined) {
							var resParam = null;
							try {
								resParam = JSON.parse(responseText); // get
							} catch (e) {
								Materialize.toast("返回数据解析失败", 4000,
										'red rounded');
							}
							if (resParam != undefined
									&& resParam.data != undefined) {
								if (resParam.header.code == commonConstraits.resCode_success) {
									topEChartHttpDiv
											.setOption(getTopHttpOption(resParam.data));
									window.onresize = topEChartHttpDiv.resize;
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
						topEChartHttpDiv.hideLoading();
						// Materialize.toast(message, displayLength, className,
						// completeCallback);
						Materialize.toast("Request failed: " + textStatus,
								4000, 'red rounded');
					});

}

function getListAppInfo() {
	var url = "../appinfo/init";
	$.ajax({
		url : url,
		method : "GET",
		async : true,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				// alert("" + responseText);
				var list = JSON.parse(responseText); // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.listAppInfo = list; // get list
					scope.$apply();
				} else {
					Materialize.toast("返回数据为空", 4000, 'red rounded');
				}
			} catch (e) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				// Materialize.toast(message, displayLength, className,
				// completeCallback);
				Materialize.toast("Request failed: " + textStatus, 4000,
						'red rounded');
			});

}

function getTopAppInfo() {
	var url = "../appinfo/topN";
	var selected = $("#appSortType option:selected"); // 获取选中的项
	getTopAppInfoAjaxReq = $.ajax({
		url : url,
		method : "GET",
		data : "appSortType=" + selected.val(),
		async : true,
		dataType : "html"
	}).done(function(responseText) {
		if (responseText != undefined) {
			try {
				var list = JSON.parse(responseText); // get list
				if (list != undefined && list.length > 0) {
					var scope = angular.element($('body')).scope();
					scope.listAppInfo = list; // get list
					scope.$apply();
				} else {
					Materialize.toast("返回数据为空", 4000, 'red rounded');
				}
			} catch (e) {
				Materialize.toast("返回数据解析失败", 4000, 'red rounded');
			}
		}
	}).fail(
			function(jqXHR, textStatus) {
				// Materialize.toast(message, displayLength, className,
				// completeCallback);
				Materialize.toast("Request failed: " + textStatus, 4000,
						'red rounded');
			});
}

function getTopJdbcOption(list) {
	var xLabels = [];
	var yLabels = [];
	var datas = [];
	for (var j = 0; j < list.length; j++) {
		var i = list.length - 1 - j;
		yLabels[j] = list[i].appdesc;
		datas[j] = common_utils_toFixed(list[i].usedConnectionCount,2);
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
			name : 'DB连接',
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

function getTopSessionOption(list) {
	var xLabels = [];
	var yLabels = [];
	var datas = [];
	for (var j = 0; j < list.length; j++) {
		var i = list.length - 1 - j;
		yLabels[j] = list[i].appdesc;
		datas[j] = common_utils_toFixed(list[i].userCount,2);
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
			name : '在线用户',
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

function getTopSqlOption(list) {
	var appNames = [];
	var datas = [];
	var charts = {};
	for (var i = list.length - 1; i >= 0; i--) {
		appNames[i] = list[i].appdesc;
		datas[i] = common_utils_toFixed(list[i].durationsSum,2);
		charts[appNames[i]] = datas[i];
	}
	var builderJson = {
		"charts" : charts,
		"ie" : 9743
	};
	var waterMarkText = 'SQL';
	var canvas = document.createElement('canvas');
	var ctx = canvas.getContext('2d');
	canvas.width = canvas.height = 100;
	ctx.textAlign = 'center';
	ctx.textBaseline = 'middle';
	ctx.globalAlpha = 0.08;
	ctx.font = '20px Microsoft Yahei';
	ctx.translate(50, 50);
	ctx.rotate(-Math.PI / 4);
	ctx.fillText(waterMarkText, 0, 0);

	var option = {
		backgroundColor : {
			type : 'pattern',
			image : canvas,
			repeat : 'repeat'
		},
		tooltip : {},
		title : [ {
			text : 'Top N',
			x : '20%',
			textAlign : 'center'
		} ],
		grid : [ {
			x : '15%',
			y : '10%',
			width : '80%',
			height : '80%'
		} ],

		xAxis : [ {
			type : 'value',
			max : builderJson.all,
			splitLine : {
				show : false
			}
		} ],
		yAxis : [ {
			type : 'category',
			data : Object.keys(builderJson.charts),
			axisLabel : {
				interval : 0,
				rotate : 30
			},
			splitLine : {
				show : false
			}
		} ],
		series : [ {
			type : 'bar',
			stack : 'chart',
			z : 3,
			label : {
				normal : {
					position : 'right',
					show : true
				}
			},
			data : Object.keys(builderJson.charts).map(function(key) {
				return builderJson.charts[key];
			})
		}, {
			type : 'bar',
			stack : 'chart',
			silent : true,
			itemStyle : {
				normal : {
					color : '#ccc'
				}
			},
			data : Object.keys(builderJson.charts).map(function(key) {
				return builderJson.all - builderJson.charts[key];
			})
		} ]
	}
	return option;
}

function getTopMemoryOption(list) {

	var xLabels = [];
	var yLabels = [];
	var datas = [];
	for (var j = 0; j < list.length; j++) {
		var i = list.length - 1 - j;
		yLabels[j] = list[i].appdesc;
		datas[j] = common_utils_toFixed(list[i].usedMemory / 1000 / 1000,2);
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
			name : '內存使用',
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
						color : 'lightBlue' // 0% 处的颜色

					}, {
						offset : 1,
						color : '#3398DB' // 100% 处的颜色
					} ], false)
				}
			}
		} ]
	};
	return option;
}

function getTopHttpOption(list) {

	var xLabels = [];
	var yLabels = [];
	var datas = [];
	for (var j = 0; j < list.length; j++) {
		var i = list.length - 1 - j;
		yLabels[j] = list[i].appdesc;
		datas[j] = common_utils_toFixed(list[i].hits,2);
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
			name : 'http请求',
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
						color : 'lightBlue' // 0% 处的颜色

					}, {
						offset : 1,
						color : '#3398DB' // 100% 处的颜色
					} ], false)
				}
			}
		} ]
	};
	return option;
}

function getTopOption() {

	var builderJson = {
		"charts" : {
			"app1" : 305,
			"app2" : 442,
			"app3" : 590,
			"app4" : 662,
			"app5" : 760

		},
		"ie" : 9743
	};

	var waterMarkText = 'ECHARTS';

	var canvas = document.createElement('canvas');
	var ctx = canvas.getContext('2d');
	canvas.width = canvas.height = 100;
	ctx.textAlign = 'center';
	ctx.textBaseline = 'middle';
	ctx.globalAlpha = 0.08;
	ctx.font = '20px Microsoft Yahei';
	ctx.translate(50, 50);
	ctx.rotate(-Math.PI / 4);
	ctx.fillText(waterMarkText, 0, 0);

	var option = {
		backgroundColor : {
			type : 'pattern',
			image : canvas,
			repeat : 'repeat'
		},
		tooltip : {},
		title : [ {
			text : 'Top N',
			x : '20%',
			textAlign : 'center'
		} ],
		grid : [ {
			x : '15%',
			y : '10%',
			width : '80%',
			height : '80%'
		} ],

		xAxis : [ {
			type : 'value',
			max : builderJson.all,
			splitLine : {
				show : false
			}
		} ],
		yAxis : [ {
			type : 'category',
			data : Object.keys(builderJson.charts),
			axisLabel : {
				interval : 0,
				rotate : 30
			},
			splitLine : {
				show : false
			}
		} ],
		series : [ {
			type : 'bar',
			stack : 'chart',
			z : 3,
			label : {
				normal : {
					position : 'right',
					show : true
				}
			},
			data : Object.keys(builderJson.charts).map(function(key) {
				return builderJson.charts[key];
			})
		}, {
			type : 'bar',
			stack : 'chart',
			silent : true,
			itemStyle : {
				normal : {
					color : '#eee'
				}
			},
			data : Object.keys(builderJson.charts).map(function(key) {
				return builderJson.all - builderJson.charts[key];
			})
		} ]
	}
	return option;
}
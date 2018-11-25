

// 保留n位小数
function common_utils_toFixed(num,n){
	return num.toFixed(n);
}

function common_utils_getURLParam(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null){
    	return  unescape(r[2]); 
     }
     return '';
}
// 获取随机数
function common_utils_nextInt(n) {
	return Math.round(Math.random() * n);
}
// 传入一个 kb
function common_utils_sizeFormat_oneParam(bytes) {
	var dataPair = [];
	if (bytes <= 0) {
		dataPair[0] = 0;
		dataPair[1] = 'KB';
	} else {
		var k = 1024;
		var unit = [ 'B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB' ];
		var i = Math.floor(Math.log(bytes) / Math.log(k));
		dataPair[0] = (bytes / Math.pow(k, i)).toPrecision(3);
		dataPair[1] = unit[i];
	}
	return dataPair;
}
//传入一个 kb
function common_utils_sizeFormat_twoParam(bytes1,bytes2) {
	
	var dataPairs =[[],[]];
	if (bytes1 <= 0) {
		var dataPair = common_utils_sizeFormat_oneParam(bytes2);
		dataPairs[0][0] = 0;
		dataPairs[0][1] = dataPair[1];
		dataPairs[1][0] = dataPair[0];
		dataPairs[1][1] = dataPair[1];
	}else if (bytes2 <= 0) {
		var dataPair = common_utils_sizeFormat_oneParam(bytes1);
		dataPairs[0][0] = dataPair[0];
		dataPairs[0][1] = dataPair[1];
		dataPairs[1][0] = 0;
		dataPairs[1][1] = dataPair[1];
	}else if(bytes1 < bytes2){
		// 单位以 bytes1 为准
		var k = 1024;
		var unit1 = [ 'B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB' ];
		var i1 = Math.floor(Math.log(bytes1) / Math.log(k));
		dataPairs[0][0] = (bytes1 / Math.pow(k, i1)).toPrecision(3);
		dataPairs[0][1] = unit1[i1];
		
		dataPairs[1][0] = (bytes2 / Math.pow(k, i1)).toPrecision(3);
		dataPairs[1][1] = unit1[i1];
		
	}else {
		// 单位以 bytes2 为准
		var k = 1024;
		var unit2 = [ 'B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB' ];
		var i2 = Math.floor(Math.log(bytes2) / Math.log(k));
		dataPairs[0][0] = (bytes1 / Math.pow(k, i2)).toPrecision(3);
		dataPairs[0][1] = unit2[i2];
		
		dataPairs[1][0] = (bytes2 / Math.pow(k, i2)).toPrecision(3);
		dataPairs[1][1] = unit3[i2];
	}
	return dataPairs;
}

function common_utils_debug(input) {
	console.debug(input);
}

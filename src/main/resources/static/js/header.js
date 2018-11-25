function signout() {
	$('#index_modal_signout').modal('open');
}

function themeSetting() {
	$('#index_modal_theme').modal('open');
}
function themeSettingOK() {
	var url = "../index/themeSetting";
	var theme = $("input[name='theme']:checked").val();
	var data = "theme=" + theme;
	$.ajax({
		url : url,
		method : "POST",
		dataType : "html",
		data : data
	}).done(function(responseText) {
		var code = JSON.parse(responseText).header.code;
		if ("200" == code) {
			window.location.reload();
		} else {
			Materialize.toast("风格设置失败", 4000, 'red rounded');
		}
	}).fail(
			function(jqXHR, textStatus) {
				Materialize.toast("Request failed: " + jqXHR.status + ",,"
						+ textStatus, 4000, 'red rounded');
			});
}

function languageSetting() {
	$('#index_modal_language').modal('open');
}
function languageSettingOK() {
	var url = "../index/languageSetting";
	var language = $("input[name='language']:checked").val();
	var data = "language=" + language;
	$.ajax({
		url : url,
		method : "POST",
		dataType : "html",
		data : data
	}).done(function(responseText) {
		var code = JSON.parse(responseText).header.code;
		if ("200" == code) {
			window.location.reload();
		} else {
			Materialize.toast("语言设置失败", 4000, 'red rounded');
		}
	}).fail(
			function(jqXHR, textStatus) {
				Materialize.toast("Request failed: " + jqXHR.status + ",,"
						+ textStatus, 4000, 'red rounded');
			});
}

function signOut() {
	var url = "@{/user/logout}";
	$.ajax({
		url : url,
		method : "POST",
		dataType : "html"
	}).done(function(responseText) {
		Materialize.toast("OK: ", 4000, 'blue rounded');
	}).fail(
			function(jqXHR, textStatus) {
				Materialize.toast("Request failed: " + jqXHR.status + ",,"
						+ textStatus, 4000, 'red rounded');
			});
}
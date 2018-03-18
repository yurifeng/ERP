function change() {
	//由于浏览器具有缓存,所以,相同URL访问时会优先使用缓存
	//后面添加时间戳来动态改变URL
	document.getElementById("token").src = "/erp/token#" + new Date().getTime();
}

$(function() {

	$('.page-container form').submit(function() {
		var username = $(this).find('.username').val();
		var password = $(this).find('.password').val();
		if (username == '') {
			$(this).find('.error').fadeOut('fast', function() {
				$(this).css('top', '27px');
			});
			$(this).find('.error').fadeIn('fast', function() {
				$(this).parent().find('.username').focus();
			});
			return false;
		}
		if (password == '') {
			$(this).find('.error').fadeOut('fast', function() {
				$(this).css('top', '96px');
			});
			$(this).find('.error').fadeIn('fast', function() {
				$(this).parent().find('.password').focus();
			});
			return false;
		}
	});

	$('.page-container form .username, .page-container form .password').keyup(
			function() {
				$(this).parent().find('.error').fadeOut('fast');
			});
});

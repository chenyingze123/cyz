$(function() {
	// 设置text需要验证
	$('input[type=text]').validatebox();
	// 自定义validator.js
	// 扩展easyui表单的验证
	$.extend($.fn.validatebox.defaults.rules, {

		// 1.验证汉字
		CHS : {
			validator : function(value) {
				return /(?=[\u4e00-\u9fa5])^[\u0391-\uFFE5]+$/.test(value);
			},
			message : '只能输入汉字'
		
		},
		// 2.验证e-mail
		mail : {
			validator : function(value) {
				return /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
						.test(value);
			},
			message : '格式错误'
		},

		// 3.移动手机号码验证
		mobile : {// value值为文本框中的值
			validator : function(value) {
				var reg = /^1[3|4|5|7|8|9]\d{9}$/;
				return reg.test(value);
			},
			message : '输入手机号码格式不准确.'
		},

		// 6.验证用户名
		username: {// 验证用户名
			validator : function(value) {
				return /^[a-zA-Z0-9_]{6,15}$/i.test(value);
			},
			message : '用户名不合法（6-16字节，允许字母数字下划线）'
		},
		// 验证密码
		pwds: {// 验证用户名
			validator : function(value) {
				return /^[a-zA-Z0-9]{6,15}$/i.test(value);
			},
			message : '密码不合法（6-16字节，允许字母数字）'
		},
		//7.验证地址
		address: {
			validator : function(value) {
				return /(?=[\u4e00-\u9fa5])^[\u0391-\uFFE5]+$/.test(value);
			},
			message : '请选择地址'
		},

		// 8.验证是否包含非法字符
		unnormal : {// 验证是否包含空格和非法字符
			validator : function(value) {
				return /.+/i.test(value);
			},
			message : '输入值不能为空和包含其他非法字符'
		},
		// 9.验证姓名
		realname : {// 验证是否包含空格和非法字符
			validator : function(value) {
				return  /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(value);
			},
			message : '姓名至少为两个中文字符！'
		},

	});
});

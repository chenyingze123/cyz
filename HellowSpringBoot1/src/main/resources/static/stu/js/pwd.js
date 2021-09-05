
$(function(){
			$('.validatebox-text').bind('blur', function(){
				$(this).validatebox('enableValidation').validatebox('validate');
			});
		})
//设置text需要验证
$('input[type=text]').validatebox();
$('input[type=password]').validatebox();
//自定义validator.js
//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
	/*equalTo: { validator: function (value, param) { return $(param[0]).val() == value; },
		message: '密码不一致' },*/
//验证密码格式
pwd: {
	validator : function(value) {
		return /^[a-zA-Z0-9]{6,15}$/i.test(value);
	},
	message : '密码格式错误（6-16字节，允许字母数字）'
},

	}

);

function pwdEdit(){
	 if ($("#pwdedit").form('enableValidation').form('validate')){
		 var oldPassword = $("#oldpwd").val();
		 var newPassword = $("#newpwd").val();
		 var rePassword = $("#repwd").val();
		 if(oldPassword != newPassword){
			 if(newPassword == rePassword){
				 $.post("/editpwd",{"oldPassword":oldPassword,"newPassword":newPassword,},function(data){
						if(data == 'old error'){
							$.messager.alert("提示信息","原密码错误！","warning");
						}else{
							
							$.messager.alert("提示信息","修改成功！");
							//$('#pwdedit').form('clear');
							
						}
					});
			 }else{
					//两次输入不一致，弹出错误提示
					$.messager.alert("提示信息","两次密码输入不一致！","warning");
				}
			 
		 }else{
			 $.messager.alert("提示信息","新密码不能为原密码！","warning");
		 }
		 

		
	 } else {
			$.messager.alert('操作提示', '存在校验项未通过！', "warning");
		}	
	
	}

	

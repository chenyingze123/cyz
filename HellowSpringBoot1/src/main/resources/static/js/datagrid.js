/*var Sex =[{"sexId":1,"sexName":"男"},{"sexId":0,"sexName":"女"}];*/

$(function(){
			$('.validatebox-text').bind('blur', function(){
				$(this).validatebox('enableValidation').validatebox('validate');
			});
		})
function openEdit() {
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择一条要编辑的数据！");
		return;
	}
	var row = selectedRows[0];
	$("#dlgedit").dialog("open").dialog("setTitle", "修改信息");
	$("#fmedit").form("load", row);
	//url = "../stuSave?stuId=" + row.stuId;
}
function savefm() {
	/*var myform = document.getElementById("fm");   //获得form表单对象
    for(var i=0;i<myform.length;i++){               //循环form表单
          if(myform.elements[i].value==""){          //判断每一个元素是否为空
                //alert("用户不能为空！");
                $.messager.alert('操作提示', '用户信息不能为！', "warning");
                return false;
                 }}*/
    var val=$('input:radio[name="sex"]:checked').val();
    if(val==null){
        //alert("请选择性别!");
    	$.messager.alert('操作提示', '请选择性别!', "warning");
        return false;
    }                           
   
    if ($("#fm").form('enableValidation').form('validate')) {
		$.ajax({
			url : "/add",
			type : "POST",
			data : $('#fm').serialize(),
			/*
			 * onSubmit : function() { return $(this).form("validate"); },
			 */
			success : function(res) {
				
				if (res === "success") {
					$("" + ".easyui-datagrid").datagrid("reload");
					$.messager.alert("系统提示", "保存成功");
					$("#dlg").dialog("close");
					$('#dlg').form('clear');
					
					
					
				}if (res === "error1"){
					$.messager.alert('系统提示', '账号已存在!', "warning");
					//$.messager.alert("系统提示", "账号已存在");
				}
				if (res === "error"){
					$.messager.alert("系统提示", "系统异常");
				}
			},
			error : function() {
				 $.messager.show({
                     title : '提示',
                     msg : '系统错误，请联系管理员------'
                 });
				console.log("ajax error");
				$("#dlg").dialog("close");
				
			}
		})
	} else {
		$.messager.alert('操作提示', '存在校验项未通过！', "warning");
	}
        
};
function saveedit() {                            
    if ($("#fmedit").form('enableValidation').form('validate')) {
		$.ajax({
			url : "/edit",
			type : "POST",
			data : $('#fmedit').serialize(),
			/*
			 * onSubmit : function() { return $(this).form("validate"); },
			 */
			success : function(res) {
				
				if (res === "success") {
					$("" + ".easyui-datagrid").datagrid("reload");
					$.messager.alert("系统提示", "修改成功");
					$("#dlgedit").dialog("close");
					$('#dlgedit').form('clear');
					
					
					
				}
				if (res === "error"){
					$.messager.alert("系统提示", "系统异常");
				}
			},
			error : function() {
				 $.messager.show({
                     title : '提示',
                     msg : '系统错误，请联系管理员------'
                 });
				console.log("ajax error");
				$("#dlgedit").dialog("close");
				
			}
		})
	} else {
		$.messager.alert('操作提示', '存在校验项未通过！', "warning");
	}
        
};
function deleteRows() {
	var rows = $('#dg').datagrid('getChecked');
	console.log(rows);
	if(rows.length==0){
		$.messager.alert('操作提示', '请至少选择一行删除!', "warning");
	}
	 $.messager.confirm("操作提示", "您确定要执行删除操作吗？", function (res) {
         if (res) {
             //alert("确定");
        	 for (var i = rows.length - 1; i >= 0; i--) {
 				var rowIndex = $('#dg').datagrid('getRowIndex', rows[i]);
 				console.log(rowIndex);
 				var id = rows[i].id;
 				$.ajax({
 					type : "GET",
 					url : "/delete?id=" + id,
 					async : false,
 					success : function(data) {

 						$('#dg').datagrid('deleteRow', rowIndex);
 						
 					},
 					error : function(data) {
 						alert("删除失败");
 					}
 				});

 			}

         }
         else {
             //alert("取消");
         }
     });
	

			
	    

};

function getChanges(){
	var rows = $('#dg').datagrid('getChanges');
	//$.messager.alert('操作提示', '请选择性别!', "warning");
	$.messager.alert('操作提示',rows.length+' rows are changed!');
};
function accept(){
	if (endEditing()){
		$('#dg').datagrid('acceptChanges');
	}
};
function reject(){
	$('#dg').datagrid('rejectChanges');
	editIndex = undefined;
};

function acceptrows() {
	var rows = $('#dg').datagrid('getChecked');
	console.log(rows);
	if(rows.length==0){
		$.messager.alert('操作提示', '请选择要保存的行!', "warning");
	}
	for (var i = rows.length - 1; i >= 0; i--) {
		 if (endEditing()){
		//var rowIndex = $('#dg').datagrid('getRowIndex', rows[i]);
		//console.log(rowIndex);
		var id =  rows[i].id;
        var username =  rows[i].username;
        var password =  rows[i].password;
        var realName =  rows[i].realName;
        var sex =  rows[i].sex;
        var telephone =  rows[i].telephone;
        var address =  rows[i].address;
        var mail =  rows[i].mail; 
        $.ajax({
            url:"/edit",
            type:"post",
            //dataType:"json",
            data:{
            	mail:mail,
            	address:address,
            	telephone:telephone,
            	sex:sex,
            	realName:realName,
            	password:password,
            	username:username,
            	id:id,
                
            },
            
            success:function(res){
                if(res == "success"){
                    $('#dg').datagrid('load', {});
                }else{
                    $.messager.show({
                        title : '提示',
                        msg : '操作失败'
                    });
                }
            },
            error:function(){
                    $.messager.show({
                        title : '提示',
                        msg : '系统错误'
                    });
            	 //$('#dg').datagrid('load', {});
            }
        });
        }
		

	}
};
function acceptrow(){
	var rows = $('#dg').datagrid('getChecked');
	console.log(rows);
    if (endEditing()){
        var row = $('#dg').datagrid('getSelected');
        var id = row.id;
        var username = row.username;
        var realName = row.realName;
        var sex = row.sex;
        var telephone = row.telephone;
        var address = row.address;
        var mail = row.mail; 
        
        $.ajax({
            url:"/edit",
            type:"post",
            //dataType:"json",
            data:{
            	mail:mail,
            	address:address,
            	telephone:telephone,
            	sex:sex,
            	realName:realName,
            	username:username,
            	id:id,
                
            },
            
            success:function(res){
                if(res == "success"){
                    $.messager.show({
                        title : '提示',
                        msg : '操作成功'
                    });
                    $('#dg').datagrid('load', {});
                }else{
                    $.messager.show({
                        title : '提示',
                        msg : '操作失败'
                    });
                }
            },
            error:function(){
                    $.messager.show({
                        title : '提示',
                        msg : '系统错误'
                    });
            	 //$('#dg').datagrid('load', {});
            }
        });
    }
};

function searchWebSite() {
    $("#dg").datagrid("load", {
        "userLike": $("#s_name").val(),
        "password": $("#s_word").val()
    });
}

/*function unitformatter(value, rowData, rowIndex) {
	
	var Address =[{"addressId":1,"addressName":"宁波"},{"addressId":2,"addressName":"上海"},{"addressId":3,"addressName":"北京"},{"addressId":4,"addressName":"四川"},{"addressId":5,"addressName":"南京"},{"addressId":6,"addressName":"广州"},{"addressId":7,"addressName":"佛山"},{"addressId":8,"addressName":"奉化"},{"addressId":9,"addressName":"溪口"},{"addressId":10,"addressName":"西安"},{"addressId":11,"addressName":"绍兴"},{"addressId":14,"addressName":"乌镇"}];
	
	if (value == 0) {
        return;
    }
 
    for (var i = 0; i < Address.length; i++) {
        if (Address[i].addressId == value) {
            return Address[i].addressName;
        }
    }
}*/


/*function sexformatter(value, rowData, rowIndex) {
	
	var Sex =[{"sexId":1,"sexName":"男"},{"sexId":0,"sexName":"女"}];
    for (var i = 0; i < Sex.length; i++) {
        if (Sex[i].sexId == value) {
            return sexName[i].sexName;
        }
    }
}*/

function downloadfile(){
        window.location.href="/AddressExcelDownloads";
    }

function downloadfilePdf(){
        window.location.href="/AddressPdfDownloads.pdf";
    }

function downloadTest(){
        window.location.href="/test.pdf";
    }
$(function(){
			$('.validatebox-text').bind('blur', function(){
				$(this).validatebox('enableValidation').validatebox('validate');
			});
		})

function savefm() {
	/*var myform = document.getElementById("fm");   //获得form表单对象
    for(var i=0;i<myform.length;i++){               //循环form表单
          if(myform.elements[i].value==""){          //判断每一个元素是否为空
                //alert("用户不能为空！");
                $.messager.alert('操作提示', '用户信息不能为！', "warning");
                return false;
                 }}*/
   /* var val=$('input:radio[name="sex"]:checked').val();
    if(val==null){
        //alert("请选择性别!");
    	$.messager.alert('操作提示', '请选择性别!', "warning");
        return false;
    }  */                         
   
    if ($("#fm").form('enableValidation').form('validate')) {
		$.ajax({
			url : "/address/add",
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
					$.messager.alert('系统提示', '地址已存在!', "warning");
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
function deleteRows() {
	var rows = $('#dg').datagrid('getChecked');
	console.log(rows);
	if(rows.length==0){
		$.messager.alert('操作提示', '请至少选择一行删除!', "warning");
	}
	for (var i = rows.length - 1; i >= 0; i--) {
		var rowIndex = $('#dg').datagrid('getRowIndex', rows[i]);
		console.log(rowIndex);
		var addressId = rows[i].addressId;
		$.ajax({
			type : "GET",
			url : "/address/delete?addressId=" + addressId,
			async : false,
			success : function(data) {

				$('#dg').datagrid('deleteRow', rowIndex);
			},
			error : function(data) {
				
				alert("删除失败");
			}
		});

	}
};
function openEdit() {
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择一条要编辑的数据！");
		return;
	}
	var row = selectedRows[0];
	
	$("#fmedit").form("load", row);
	$("#dlgedit").dialog("open").dialog("setTitle", "编辑学生类型信息");
	//url = "../stuSave?stuId=" + row.stuId;
}

function openTest() {
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择一条要编辑的数据！");
		return;
	}
	var row = selectedRows[0];
	//$("#dlgedit").dialog("open").dialog("setTitle", "编辑学生类型信息");
	$("#fmedit").form("load", row);
	$.ajax({
		url : "/address/openTest",
		type : "POST",
		data : $('#fmedit').serialize(),
		/*
		 * onSubmit : function() { return $(this).form("validate"); },
		 */
		success : function(res) {
			
			if (res === "success") {
				$("#dlgedit").dialog("open").dialog("setTitle", "编辑学生类型信息");
					
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
	
	
};
function saveedit() {   
	var selectedRows = $("#dg").datagrid('getSelections');
	var addressId =  selectedRows[0].addressId;
    var addressName =  selectedRows[0].addressName;
    $.ajax({
        url:"/address/editfind",
        type:"post",
        //dataType:"json",
        data:{
        	
        	addressName:addressName,
        	addressId:addressId,
            
        },
      success : function(res) {
			
			if (res === "success") {
				 if ($("#fmedit").form('enableValidation').form('validate')) {
						$.ajax({
							url : "/address/edit",
							type : "POST",
							data : $('#fmedit').serialize(),
							/*
							 * onSubmit : function() { return $(this).form("validate"); },
							 */
				            success : function(res) {
								
								if (res === "success") {
									$("" + ".easyui-datagrid").datagrid("reload");
									$.messager.alert("系统提示", "保存成功");
									$("#dlgedit").dialog("close");
									$('#dlgedit').form('clear');
									
									
									
								}if (res === "error1"){
									$.messager.alert('系统提示', '地址已存在!', "warning");
									//$.messager.alert("系统提示", "账号已存在");
								}
								if (res === "error"){
									$.messager.alert("系统提示", "系统异常");
								}
							},
							error : function() {
								 $.messager.alert({
				                     title : '提示',
				                     msg : '系统错误，请联系管理员------'
				                 });
								console.log("ajax error");
								$("#dlgedit").dialog("close");
								
							}
						}
						)
					} else {
						$.messager.alert('操作提示', '存在校验项未通过！', "warning");
						
					}
				

			}
			if (res === "error"){
				$.messager.alert('系统提示', '原地址已被更改,请刷新!', "warning");
				//$.messager.alert("系统提示", "账号已存在");
			}
		},
		error : function() {
			 $.messager.alert({
                 title : '提示',
                 msg : '系统错误，请联系管理员------'
             });
			console.log("ajax error");
			$("#dlgedit").dialog("close");
			
		}
    });
   
        
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
		var addressId =  rows[i].addressId;
        var addressName =  rows[i].addressName;
      
        $.ajax({
            url:"/address/edittest",
            type:"post",
            //dataType:"json",
            data:{
            	
            	addressName:addressName,
            	addressId:addressId,
                
            },
            
          success : function(res) {
				
				if (res === "success") {
					$("" + ".easyui-datagrid").datagrid("reload");
					
	
				}
				if (res === "error"){
					$.messager.alert("系统提示", "系统异常");
				}
				
				if (res === "error1"){
					$.messager.alert('系统提示', '地址已存在!', "warning");
					//$.messager.alert("系统提示", "账号已存在");
				}
			},
			error : function() {
				 $.messager.alert({
                     title : '提示',
                     msg : '系统错误，请联系管理员------'
                 });
				console.log("ajax error");
				$("#dlgedit").dialog("close");
				
			}
        });
        }
		

	}
};
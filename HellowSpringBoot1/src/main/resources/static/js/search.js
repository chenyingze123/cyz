/**
 * 
 */
/*function doSearch(){
			$('#dg').datagrid('load',{
				//"realName": $('#realName').val(),
				"username": $('#username').val()
			});
		}

$("#standardQueryBtn").click(function(){
	
	$("#dg").datagrid("load",{
		"standard.name":$("#username").val(),
	
	});

});*/
	
$(function(){
			var dg = $('#dg').datagrid({
				filterBtnIconCls:'icon-filter'
			});
			dg.datagrid('enableFilter',
					[{
			                field:'sex',
			                type:'combobox',
			                options:{
			                    panelHeight:'auto',
			                    data:[{value:'',text:'All'},{value:'男',text:'男'},{value:'女',text:'女'}],
			                    onChange:function(value){
			                        if (value == ''){
			                            dg.datagrid('removeFilterRule', 'sex');
			                        } else {
			                            dg.datagrid('addFilterRule', {
			                                field: 'sex',
			                                op: 'equal',
			                                value: value
			                            });
			                        }
			                        dg.datagrid('doFilter');
			                    }
						}
						},
						{
							field:'password',
							type:'textbox',
							options:{precision:1},
							op:['equal','notequal']
						}
			]);
			
		});
		

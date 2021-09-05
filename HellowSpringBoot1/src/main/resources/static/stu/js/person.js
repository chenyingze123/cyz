/**
 * 
 */

/*var row = {"id":218,"username":"123456","password":"123456","realName":"陈思思","sex":"1","telephone":"13777268325","address":"4","mail":"4189652@qq.com"};*/

var rows = [];
var Sex =[{"sexId":1,"sexName":"男"},{"sexId":0,"sexName":"女"}];
/*var rows = [
	        { "name": "帐号", "group": "帐号信息", "value": "","field": "username", "editor": "text" },
	        { "name": "姓名", "group": "帐号信息", "value": "", "field": "realName", "editor": "text" },
	        { "name": "性别", "group": "帐号信息", "value": "", "field": "sex", "editor": "text" },
	        { "name": "联系电话","group": "其它",  "value": "", "field": "telephone", "editor": "text" },
	        { "name": "地址",    "group": "其它",  "value": "","field": "address", "editor": { "type": 'combobox', "options": { "valueField": 'addressId', "textField": 'addressName', "url": '/address/list', "required": true}} },
	        { "name": "移动电话", "group": "其它", "value": "", "field": "mobilePhone", "editor": { "type": 'validatebox', "options": { "required": true}} },
	        { "name": "集团短号", "group": "其它", "editor": "text", "value": "", "field": "mobilePhoneShort" },
	        { "name": "第二联系方式", "group": "其它", "value": "", "field": "phoneSecond", "editor": { "type": 'validatebox', "options": { "required": true}} },
	        { "name": "备注", "group": "其它", "editor": "text", "value": "", "field": "remark" }
	    ];*/


/*$(function(){
			$('.validatebox-text').bind('blur', function(){
				$(this).validatebox('enableValidation').validatebox('validate');
			});
		})
*/

function openPerson() {
	
	/*var s = '';
	var rows = $('#pg').propertygrid('getChanges');
	for(var i=0; i<rows.length; i++){
		s += rows[i].name  + rows[i].value + ',';
	}
	alert(s)
	console.log( $('#fms').serialize())*/
	// $('#fmedit').serialize();
	
	/*var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择一条要编辑的数据！");
		return;
	}
	var row = selectedRows[0];*/
	//var row = rows[0];
	//$("#dlgedit").dialog("open").dialog("setTitle", "编辑信息");
	//$("#fmedit").form("load", row);
	//url = "../stuSave?stuId=" + row.stuId;
	//console.log( $('#fms').serialize())
//	var row = $('#fms').serialize();
//	console.log(row);
	$.ajax({
		 async : false,
		 cache : false,
		 type : 'POST',
		 url : '/person',
		 dataType : "json",
		 error : function() {
		  alert('加载失败 ');
		 },
		 success : function(data) {
		 /**重点：前台接收到返回值，直接处理就行*/
		    var onePerson = data;
			 //alert(onePerson.username);
		    rows.push( { "name": "帐号", "group": "帐号信息", "value": onePerson.username,"field": "username" });
		    rows.push({ "name": "姓名", "group": "帐号信息", "value": onePerson.realName, "field": "realName","editor":{"type":'validatebox',"options":{"validType":'CHS',"required":true}} });
		    rows.push({ "name": "性别", "group": "帐号信息", "value": onePerson.sex, "field": "sex","editor": { "type": 'combobox', "options": { "valueField": 'sexName', "textField": 'sexName', "data": Sex, "required": true,"panelHeight":'65px',"editable":false}}});
		    rows.push({ "name": "联系电话","group": "其它",  "value": onePerson.telephone, "field": "telephone", "editor":{"type":'validatebox',"options":{"validType":'mobile',"required":true}} });
		    rows.push({ "name": "地址",    "group": "其它",  "value": onePerson.addressName,"field": "address", "editor": { "type": 'combobox', "options": { "valueField": 'addressName', "textField": 'addressName', "url": '/address/list', "required": true,"panelHeight":'200px'}} });
		    rows.push( { "name": "E-mail", "group": "其它",   "value": onePerson.mail, "field": "mail", "editor": { "type": 'validatebox', "options": { "validType":'mail',"required": true}} } );
		    $('#pg').propertygrid('loadData', rows);
		 }
		});
	
	
};

function save(){
	var s = '';
	var row = $('#pg').propertygrid('getRows');
	/*for(var i=0; i<row.length; i++){
		s += row[i].name + ":" + row[i].value + ',';
	}
	alert(s)*/
	var username =  row[0].value;
    var realName =  row[1].value;
    var sex =  row[2].value;
    var telephone =  row[3].value;
    var addressName =  row[4].value;
    var mail =  row[5].value; 
   /* var sex1 = "男";
    var sex0 = "女";*/
    
    
    $.ajax({
        url:"/personEdit",
        type:"post",
        //dataType:"json",
        data:{
        	mail:mail,
        	addressName:addressName,
        	telephone:telephone,
        	sex:sex,
        	realName:realName,
        	username:username
            
        },
        
        success:function(res){
            if(res == "success"){
                //$('#dg').datagrid('load', {});
            	$.messager.alert('操作提示', '保存成功');
            }else{
                $.messager.alert({
                    title : '提示',
                    msg : '操作失败'
                });
            }
        },
        error:function(){
                $.messager.alert({
                    title : '提示',
                    msg : '系统错误'
                });
        	 //$('#dg').datagrid('load', {});
        }
    });
    
};


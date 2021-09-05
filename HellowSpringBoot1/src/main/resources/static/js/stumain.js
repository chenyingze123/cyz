

/*function addTab(title, url){
	if ($('#tt').tabs('exists', title)){
		$('#tt').tabs('select', title);

	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#tt').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
};*/

/*function addTab(title, url) {
    if ($('#tt').tabs('exists', title)) {
        $('#tt').tabs('select', title);
        var selTab = $('#tt').tabs('getSelected');
        var url = $(selTab.panel('options').content).attr('src');     
        $('#tt').tabs('update', {
            tab: selTab,
            options: {
                content:createFrame(url)
            }
        })

    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%"></iframe>';
        $('#tt').tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    }
}*/

$(function(){
	//document.getElementById("id^='_easyui_tree_'").style.display="none";
	//$("[id^='_easyui_tree_']").attr("style","display:none;");
	// 数据
	
	var treeData=[{
		 id:1,
		text:"数据操作",
		//state:"closed",
		
		children:[{
			 id:12,
			text:"修改密码",
			attributes:{
				url:"/pwd"
			}
		},{
			 id:13,
				text:"个人信息",
				attributes:{
					url:"/per"
				}
			}]
	}];
	
	var treeData2=[{
		 id:2,
		text:"用户信息",
		//state:"closed",
		
		children:[
			{
			 id:21,
				text:"学生清单",
				attributes:{
					url:"/stuList"
				}
			},{
			 id:22,
			text:"教师清单",
			attributes:{
				url:"/TeacherList"
			}
		},{
			 id:23,
				text:"辅导员清单",
				attributes:{
					url:"/InstructorList"
				}
			},{
				 id:24,
					text:"管理员清单",
					attributes:{
						url:"/index"
					}
				}]
	}];
	var treeData3=[{
		 id:3,
		text:"用户信息",
		//state:"closed",
		
		children:[/*{
								 id:31,
									text:"学院管理",
									attributes:{
										url:"/college"
									}
								},*//*{
									 id:32,
										text:"专业管理",
										attributes:{
											url:"/major"
										}
									},*/{
										 id:33,
											text:"课程管理",
											attributes:{
												url:"/stuCourse"
											}
										},{
											 id:34,
												text:"请假管理",
												attributes:{
													url:"/stu/stuNote"
												}
											}]
	}];
	
	
	// 实例化树菜单
	$("#tree").tree({
		data:treeData,
		lines:false,
		
		
		
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	
	// 实例化树菜单
	$("#tree2").tree({
		data:treeData2,
		lines:false,
		
		
		
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	// 实例化树菜单
	$("#tree3").tree({
		data:treeData3,
		lines:false,
		
		
		
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	
	// 新增Tab
	function openTab(text,url){
		if($("#tt").tabs('exists',text)){
			$("#tt").tabs('select',text);

			var current_tab = $('#tt').tabs('getSelected');
			$('#tt').tabs('update',{
			     tab:current_tab,
			     options : {
			          content : '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
				  //或者 href : '';
			     }
			});
		
		}else{
			var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
			$("#tt").tabs('add',{
				title:text,
				closable:true,
				content:content
			});
		}
	}
});

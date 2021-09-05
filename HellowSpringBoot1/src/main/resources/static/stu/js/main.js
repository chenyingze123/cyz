

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
	// 数据
	var treeData=[{
		 id:1,
		text:"数据操作",
		//state:"closed",
		
		children:[{
			 id:11,
			text:"数据表格",
			attributes:{
				url:"/index"
			}
		},{
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
			},{
				 id:14,
					text:"地址管理",
					attributes:{
						url:"/address"
					}
				}]
	}];
	
	var treeData2=[{
		 id:2,
		text:"用户信息",
		//state:"closed",
		
		children:[{
			 id:21,
			text:"学生清单",
			attributes:{
				url:"/stuList"
			}
		},{
			 id:22,
			text:"教师清单",
			attributes:{
				url:"/pwd"
			}
		},{
			 id:23,
				text:"辅导员清单",
				attributes:{
					url:"/per"
				}
			},{
				 id:24,
					text:"管理员清单",
					attributes:{
						url:"/address"
					}
				},{
					 id:25,
						text:"学生请假",
						attributes:{
							url:"stu/stuNote"
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

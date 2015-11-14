/**
 * 页面的初始化事件注册
 */
$(function(){
	//导航栏的点击事件注册
	$(".nav>li").each(function(index,el){
		$(el).click(function(){
			$(".nav>.active").removeClass("active");
			$(el).addClass("active");
			$(el).children("ul").children("li").removeClass("active");
			$($(el).children("ul").children("li")[0]).addClass("active").click();
		});
	});
	//导航栏左侧的点击事件注册
	$(".nav>li>ul").each(function(index,el){
		$(el).children("li").click(function(e){
			//阻止事件冒泡
			e.stopPropagation();
			$(this).siblings(".active").removeClass("active");
			$(this).addClass("active");
			var breadStr = "<li class=\""+$(el).siblings("a").attr("class")+" text-gray\">"+$(el).siblings("a").html()+"</li>";
			breadStr += "<li class=\"text-gray\">"+$(this).children("a").html()+"</li>";
			$(".bread").html(breadStr);
			
		});
	});
	//初始化时触发一个点击事件
	$($(".nav>li")[0]).addClass("active").click();
});
/**
 * 中间内容显示ajax
 * @param target
 */
function show(target){
	switch (target) {
		case "webBaseInfo":
			$.ajax({url:"admin/webBaseInfo?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "teacherRegisterManger":
			$.ajax({url:"admin/teacherRegisterManger?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "studentRegisterManger":
			$.ajax({url:"admin/studentRegisterManger?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "personalInfo":
			$.ajax({url:"user/personalInfo?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "modifyPassword":
			$.ajax({url:"user/modifyPassword?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "newsManager":
			$.ajax({url:"admin/newsManager/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "bbsBlockManager":
			$.ajax({url:"bbs/bbsBlockManager?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "addBbsBlock":
			$.ajax({url:"bbs/addBbsBlock?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "notifyManager":
			$.ajax({url:"admin/notifyManager/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "myNewsComments":
			$.ajax({url:"user/myNewsComments/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "publishNews":
			window.open("info/publishNews?date="+new Date().getTime(), "_blank");
			break;
		case "publishNotification":
			window.open("info/publishNotification?date="+new Date().getTime(), "_blank");
			break;
		case "newUser":
			$.ajax({url:"user/newUser?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "mytie":
			$.ajax({url:"bbs/myTie/mv/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "mytieFeedback":
			$.ajax({url:"bbs/myTieComments/mv/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "addCourse":
			window.open("course/addCourse?date="+new Date().getTime(), "_blank");
			break;
		case "courseManager":
			$.ajax({url:"course/courseManager/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "courseClassManager":
			$.ajax({url:"course/courseClassManager/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "publishCourseClass":
			window.open("course/publishCourseClass?date="+new Date().getTime(), "_blank");
			break;
		case "publishCourseClassDetail":
			window.open("course/publishCourseClassDetail?date="+new Date().getTime(), "_blank");
			break;
		case "classDetailManager":
			$.ajax({url:"course/classDetailManager/1/10?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		default:
			break;
	}
}
function ifreamResize(){
	try{
		$("mainiframe").height($("#mainiframe")[0].$(body).height());
	}catch(e){}
}
/**
 * 删除信息
 * @returns
 */
function deleteInfo(id,souresType,title){
	var teacherOperatorDialog = dialog({
		    content: "你确定要删除<strong>"+title+"</strong>吗？",
		    okValue: '确定',
		    width:400,
		    ok: function () {
		        $.ajax({url:"info/delete/"+id+"/"+souresType+"?date="+new Date().getTime(),success:function(data){
		        	if(data.success){
		        		var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
		        		if(souresType=="notification"){
		        			show("notifyManager");
		        		}else{
		        			show("newsManager");
		        		}
		        		setTimeout(
		        				function(){
		        					msgDialog.close();
		        				}
		        				,2000);
		        	}else{
		        		dialog({title:"操作失败",content:data.msg, width:400,}).show();
		        	}
		        }});
		        teacherOperatorDialog.close();
		        return false;
		    },
		    cancelValue: '取消',
		    cancel: function () {}
	}).showModal();
	
}

/**
 * 编辑信息
 * @returns
 */
function editInfo(id,souresType){
	window.open("info/edit/"+id+"/"+souresType+"?date="+new Date().getTime(), "_blank");
}
/**
 * 分页显示
 * @returns
 */
function showPage(currentPage,pageSize,souresType){
	switch (souresType) {
		case 'notification':
			$.ajax({url:"admin/notifyManager/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case 'news':
			$.ajax({url:"admin/newsManager/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case 'myNewsComments':
			$.ajax({url:"user/myNewsComments/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "mytie":
			$.ajax({url:"bbs/myTie/mv/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "mytieFeedback":
			$.ajax({url:"bbs/myTieComments/mv/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "course":
			$.ajax({url:"course/courseManager/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "courseClass":
			$.ajax({url:"course/courseClassManager/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		case "classDetail":
			$.ajax({url:"course/classDetailManager/"+currentPage+"/"+pageSize+"?date="+new Date().getTime(),success:function(data){
				$(".admin").html(data);
			}});
			break;
		default:
			break;
	}
}
function confirmTeacher(id,RealName,loginName){
	teacherOperator("你确定要认证：<strong>"+RealName+"("+loginName+")</strong>的账号吗?"
					,"admin/confirmTeacher/"+id
					,showOperatorInfo
		);
}
function refuseTeacher(id,RealName,loginName){
	teacherOperator("你确定要拒绝：<strong>"+RealName+"("+loginName+")</strong>的账号吗?"
			,"admin/refuseTeacher/"+id
			,showOperatorInfo
	);
}
function stopUser(id,RealName,loginName){
	teacherOperator("你确定要停用：<strong>"+RealName+"("+loginName+")</strong>的账号吗?"
			,"admin/stopUser/"+id
			,showOperatorInfo
	);
}
function restartUser(id,RealName,loginName){
	teacherOperator("你确定要重新请用：<strong>"+RealName+"("+loginName+")</strong>的账号吗?"
			,"admin/restartUser/"+id
			,showOperatorInfo
	);
}
function resetPassword(id,RealName,loginName){
	teacherOperator("你确定要重置：<strong>"+RealName+"("+loginName+")</strong>账号的密码为：123456?"
			,"admin/resetPassword/"+id
			,showOperatorInfo
	);
}
/**
 * 教师通用操作回调
 * @param data
 */
function showOperatorInfo(data){
	if(data.success){
		var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
		if($("#teacherRegisterManger").hasClass("active")){
			show("teacherRegisterManger");
		}else{
			show("studentRegisterManger");
		}
		setTimeout(
				function(){
    				msgDialog.close();
    			}
				,2000);
	}else{
		var msgDialog = dialog({title:"操作失败",content:data.msg, width:400,}).show();
		setTimeout(
				function(){
					msgDialog.close();
				}
				,2000);
	}
}
/**
 * 教师操作通用方法
 * @param contentStr 弹出款的信息
 * @param operatorUrl 操作的url
 * @param callback	操作的回调函数
 */
function teacherOperator(contentStr,operatorUrl,callback){
	var teacherOperatorDialog = dialog({
		    content: "<p class=\"text-center\">"+contentStr+"</p>",
		    okValue: '确定',
		    width:400,
		    ok: function () {
		    	this.content('处理中,请稍等......');
		    	$.ajax({url:operatorUrl+"?date="+new Date().getTime(),type:"post",success:function(data){
		    		callback(data);
		    		teacherOperatorDialog.close();
		    	}});
		        return false;
		    },
		    cancelValue: '取消',
		    cancel: function () {}
		});
		teacherOperatorDialog.showModal();
}

function  modifyPersonalInfo(){
	$("textarea[placeholder], input[placeholder]").each(function(index, element){
		$showplaceholder($(this));
	});
	if($("#personalInfo>.check-error").length>0) return false;
	$.ajax({url:'user/personalInfo',data : $('#personalInfo').serialize(),type : "POST",success : function(data) {
		if(data.success){
			var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
			show("personalInfo");
			setTimeout(
					function(){
	    				msgDialog.close();
	    			}
					,2000);
		}else{
			dialog({title:"操作失败",content:data.msg, width:300,okValue: '确定',ok: function(){}}).show();
		}
	}});
	return false;
}
function  modifyPassword(){
	$("textarea, input").each(function(index, element){
		$(element).blur();
	});
	if($("#modifyPassword>.check-error").length>0) return false;
	$.ajax({url:'user/modifyPassword',data : $('#modifyPassword').serialize(),type : "POST",success : function(data) {
		if(data.success){
			var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
			show("modifyPassword");
			setTimeout(
					function(){
						msgDialog.close();
					}
					,2000);
		}else{
			dialog({title:"操作失败",content:data.msg, width:300,okValue: '确定',ok: function(){}}).show();
		}
	}});
	return false;
}

function addbb(){
	$.ajax({url:"bbs/addBbsBlock?date="+new Date().getTime(),data:$("#bbsBlock").serialize(),type:"post",success:function(data){
			if(data.success){
				var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
				show("addBbsBlock");
				setTimeout(
						function(){
							msgDialog.close();
						}
						,2000);
			}else{
				dialog({title:"操作失败",content:data.msg, width:300,okValue: '确定',ok: function(){}}).show();
			}
	}});
	return false;
}

function editCourse(id){
	window.open("course/editCourse/"+id+"?date="+new Date().getTime(), "_blank");
}
function editCourseClasss(id){
	window.open("course/editCourseClass/"+id+"?date="+new Date().getTime(), "_blank");
}
function editClasssDetail(id){
	window.open("course/editClassDetail/"+id+"?date="+new Date().getTime(), "_blank");
}
function bbsAjax(id,type,op){
	switch (op) {
	case "hidden":
		$.ajax({url:'bbs/hidden/'+id+'/'+type
				,type : "POST"
				,success : function(data) {
					if(data.success){
						var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
						if("tie"==type){
							show("mytie");
						}else{
							show("mytieFeedback");
						}
						setTimeout(
								function(){
									msgDialog.close();
								}
								,2000);
					}else{
						dialog({title:"操作失败",content:data.msg, width:300,okValue: '确定',ok: function(){}}).show();
					}
				}
		});
		break;
	case "edit":
		window.open("course/edit/"+id+"/"+type+"?date="+new Date().getTime(), "_blank");
		break;
	case "show":
		$.ajax({url:'bbs/restart/'+id+'/'+type
			,type : "POST"
			,success : function(data) {
				if(data.success){
					var msgDialog = dialog({title:"操作成功",content:data.msg, width:400,}).show();
					if("tie"==type){
						show("mytie");
					}else{
						show("mytieFeedback");
					}
					setTimeout(
							function(){
								msgDialog.close();
							}
							,2000);
				}else{
					dialog({title:"操作失败",content:data.msg, width:300,okValue: '确定',ok: function(){}}).show();
				}
			}
	});

	default:
		break;
	}
	return false;
}
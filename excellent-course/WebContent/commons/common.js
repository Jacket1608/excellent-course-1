function showPersonInfo(id){
	$.ajax({url:basePath+"/user/getPersonalInfo?date="+new Date().getTime(),data:"id="+id,success:function(data){
			if(data.success){
				var successOperatorDialog =dialog({
					content:(function(){
								var html='<div class="text-center">';
								html+='<div class="media media-x clearfix">';
								html=html+'<img src="'+basePath+'/'+data.user.headImage+'" height="100px;" style="margin:0px auto;" width="100px;" class="radius img-responsive">';
								html+='<div class="media-body">';
								html=html+' <strong>'+(data.user.nickName==null?"":data.user.nickName)+'</strong>';
								html+='<ul class="list-group list-striped">';
								html=html+'<li>真实姓名：'+(data.user.realName==null?"":data.user.realName)+'</li>';
								html=html+'<li>昵称：'+(data.user.nickName==null?"":data.user.nickName)+'</li>';
								html=html+'<li>登录名：'+(data.user.loginName==null?"":data.user.loginName)+'</li>';
								html+='</ul>';
								html+='</div></div>';
								html+='</div>';
								return html;
							})
					, width:400
					,okValue: '确定'
				    ,ok: function () {
				    	successOperatorDialog.close();
				    }}).show();
			}else{
				var failOperatorDialog =dialog({title:"打开失败"
					,content:data.msg
					, width:400
					,okValue: '确定'
				    ,ok: function () {
				    	failOperatorDialog.close();
				    }}).show();
			}
	}});
}
$(function() {
	/**
	 * 静态的导航出现,至顶出现
	 */
	$(window).scroll(function() {
		if ($(window).scrollTop() > 150) {
			$(".fixed_head").show();
		} else {
			$(".fixed_head").hide();
		}
		/**
		 * 导航栏出现
		 */
		if ($(window).scrollTop() > 10) {
			$(".topdown").show();
		} else {
			$(".topdown").hide();
		}
	});
	/**
	 * 滚动条至底
	 */
	$(".topdown>.toDown").click(function() {
		$(window).scrollTop($(document).height());
	});
	/**
	 * 滚动条至顶
	 */
	$(".topdown>.toTop").click(function() {
		$(window).scrollTop(0);
	});
	/**
	 * 科目右边的点击事件
	 */
	/*$("#coursenav>ul li").addClass("swing-hover");*/
	$("#coursenav>ul li").each(function(index,em){
		$(em).click(function(){
			$("#coursenav>ul li").removeClass("active");
			$(em).addClass("active");
		});
	});
});

function showInfoMore(type){
	switch (type) {
	case "newsmore":
		$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
			,data:"type="+type+"&currentPage=1&pageSize=20"
			,success:function(data){
				$(".x9").html(data);
			}
		});
		break;
	case "notificationmore":
		$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
			,data:"type="+type+"&currentPage=1&pageSize=20"
			,success:function(data){
				$(".x9").html(data);
			}
		});
		break;
	default:
		break;
	}
	
}
function  showPage(current,pageSize,souresType){
	switch (souresType) {
		case "news":
			$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
				,data:"type="+souresType+"&currentPage="+current+"&pageSize="+pageSize
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "newsmore":
			$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
				,data:"type="+souresType+"&currentPage="+current+"&pageSize="+pageSize
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "notification":
			$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
				,data:"type="+souresType+"&currentPage="+current+"&pageSize="+pageSize
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "notificationmore":
			$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
				,data:"type="+souresType+"&currentPage="+current+"&pageSize="+pageSize
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "attach":
			$.ajax({url:basePath+"/attach/page/"+current+"/"+pageSize+"?date="+new Date().getTime()
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "coursemore":
			$.ajax({url:basePath+"/course/pager/"+current+"/"+pageSize+"/"+souresType+"?date="+new Date().getTime()
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "classDetailPager":
			$.ajax({url:basePath+"/course/pager/"+current+"/"+pageSize+"/"+souresType+"?date="+new Date().getTime()
				,data:"courseClassId="+$("[name='courseClassId']").val()
				,success:function(data){
					$("#classDetailDiiv").html(data);
				}
			});
			break;
		case "classDetail":
			$.ajax({url:basePath+"/course/pager/"+current+"/"+pageSize+"/"+souresType+"?date="+new Date().getTime()
				,data:"courseClassId="+$("[name='courseClassId']").val()
				,success:function(data){
					$(".x9").html(data);
				}
			});
			break;
		case "bbsContentPager":
			$.ajax({url:basePath+"/bbs/pager/"+current+"/"+pageSize+"/"+souresType+"?date="+new Date().getTime()
				,data:"blockId="+$("[name='blockId']").val()
				,success:function(data){
					$("#bbsContents").html(data);
				}
			});
			break;
		case "contentComments":
			$.ajax({url:basePath+"/bbs/pager/"+current+"/"+pageSize+"/"+souresType+"?date="+new Date().getTime()
				,data:"bcontentId="+$("[name='bcontentId']").val()
				,success:function(data){
					$("#bbsContent").html(data);
				}
			});
			break;
	
		default:
			break;
	}
	
}

function newsComment(){
	var content = $("#newsCommentsContent").val();
	if(content.length>0){
		$.ajax({url:basePath+"/info/ajax?date="+new Date().getTime()
			,data:"type=commitNewsComments&content="+encodeURI(content)+"&newsid="+$("[name='newsid']").val()
			,success:function(data){
				if(data.success){
					$("#newsCommentsContent").val("");
					var html = '<blockquote class="border-main">';
						html = 	html+'<div class="media media-x"><a class="float-left" href="javascript:void(0)"><img src="'+basePath+'/'+data.headImage+'" width="64px" height="64px;" class="radius" alt="..."></a>';
						html = 	html+'  <div class="media-body">';
						html = 	html+'   <p>评论人：'+data.userName+'&ensp;&ensp;&ensp;&ensp;时间：'+data.time+'</p><p>';
						html = 	html+data.content;
						html = 	html+'</P> </div>';
						html = 	html+'</div>';
						html = 	html+'</blockquote>';
					$("#newsCommentsblock").append(html);
				}else{
					dialog({title:"评论失败",content:data.msg, width:400,}).show();
				}
			}
		});
	}
	return false;
}
function classDetailComment(){
	var content = $("#classDetailCommentsContent").val();
	if(content.length>0){
		$.ajax({url:basePath+"/course/addClassDetailComments?date="+new Date().getTime()
			,data:"type=classDetailCommentsContent&content="+encodeURI(content)+"&classDetailid="+$("[name='classDetailid']").val()
			,type:"post"
			,success:function(data){
				if(data.success){
					$("#classDetailCommentsContent").val("");
					var html = '<blockquote class="border-main">';
					html =  html+'<div class="media media-x"><a class="float-left" href="javascript:void(0)"><img src="'+basePath+'/'+data.headImage+'" width="64px" height="64px;" class="radius" alt="..."></a>';
					html = 	html+'  <div class="media-body">';
					html = 	html+'   <p>评论人：'+data.userName+'&ensp;&ensp;&ensp;&ensp;时间：'+data.time+'</p><p>';
					html = 	html+data.content;
					html = 	html+'</P> </div>';
					html = 	html+'</div>';
					html = 	html+'</blockquote>';
					$("#classDetailComments").append(html);
				}else{
					dialog({title:"评论失败",content:data.msg, width:400,}).show();
				}
			}
		});
	}
	return false;
}

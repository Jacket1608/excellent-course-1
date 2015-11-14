/**
 * 公用主键的js事件处理函数
 */
$(function(){
	/**
	 * 斑马线table
	 */
	$(".bm_table").each(function(index,el){
		$(el).children("tbody").children("tr").each(function(index,el2){
			if($(el2).children("th").length>0){
				$(el2).addClass("thead");
			}
			if(index%2!=0){
				$(el2).addClass("even");
			}
		});
	});
	/**
	 * 页签的hover,效果
	 */
	$(".tab").each(function(index,el){
		$(el).children("div").children("span").each(function(index,el){
				$(el).hover(function(){
					if(!$(el).hasClass("current")){
						$(el).siblings(".current").removeClass("current");
						$(el).parent("div").siblings("div").children(".current").removeClass("current");
						$(el).addClass("current");
						$($(el).parent("div").siblings("div").children("div")[index]).addClass("current");
					}
				});
		});
	});
	/**
	 * 页签的click,效果
	 */
	$(".ctab").each(function(index,el){
		var liwidth = $(el).children("div").width();
		$(el).children("div").children("ul").width(liwidth*2);
		$(el).children("div").children("ul").children("li").width(liwidth);
		$(el).children("div").children("ul").children("li").height($(el).height()-$(el).children("div").height());
		$(el).children("div").children("span").each(function(index,el){
			$(el).click(function(){
				if(!$(el).hasClass("current")){
					$(el).siblings(".current").removeClass("current");
					$(el).addClass("current");
					$(el).parent("div").siblings("div").children("ul").animate({"margin-left":(-(index)*liwidth)+"px"},1000);
				}
			});
		});
	});
	/**
	 * 图片轮播, 静态
	 */
	$(".playpic>ul>ul>li").each(function(index,el){
		$(el).hover(function(){
			$(el).parent("ul").children("li").removeClass("current");
			$(el).parent("ul").siblings("li").removeClass("current");
			$($(el).parent("ul").siblings("li")[$(el).parent("ul").siblings("li").length-index-1]).addClass("current");
			$(el).addClass("current");
			$(el).parent("ul")[0].currentid = $(el).parent("ul").siblings("li").length-index-1;
		});
	});
	/**
	 * 自动轮播
	 */
	$(".playpic").each(function(index,el){
		$(".playpic").children("ul")[0].currentid = 0;
//		$(el).append("<span class=\"currentid\">0</span>")
		setInterval(function(){
			debugger;
			var lis = $(el).children("ul").children("li");
			$(el).children("ul")[0].currentid = (++($(el).children("ul")[0].currentid)%lis.length);
			$(el).children("ul").children("li").removeClass("current");
			$(el).children("ul").children("ul").children("li").removeClass("current");
			$($(el).children("ul").children("li")[$(el).children("ul")[0].currentid]).addClass("current");
			$($(el).children("ul").children("ul").children("li")[lis.length-$(el).children("ul")[0].currentid-1]).addClass("current");
			
		},2000);
	});
});
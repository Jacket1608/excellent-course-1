$(function(){
	//邮箱
	$("[name='email']").blur(function(){
		vildateEmail();
		
	});
	//用户名
	$("[name='loginname']").blur(function(){
		vildateLoginName();
	});
	//密码
	$("[name='password']").blur(function(){
		vildatePassword();
	});
});
function vildateEmail(){
	if($("[name='email']").length==0) return true;
	if(/^(\w)+@(\w)+(\.\w{2,3}){1,2}$/ig.test($("[name='email']").val())){
		$("span.eemail").html("");
		return true;
	}else{
		$("span.eemail").html("邮箱地址有误");
		return false;
	}
}
function vildateLoginName(){
	if($("[name='loginname']").val().length>0){
		if(/^\w+$/ig.test($("[name='loginname']").val())){
			$("span.ename").html("");
			return true;
		}else{
			$("span.ename").html("登录名只能是英文和数字的组合");
			return false;
		}
	}else{
		$("span.ename").html("登录名不能为空");
		return false;
	}
}
function vildatePassword(){
	if($("[name='password']").val().length>0){
		$("span.epassword").html("");
		return true;
	}else{
		$("span.epassword").html("密码不能为空");
		return false;
	}
}
function register(){
	if(vildatePassword()&vildateLoginName()&vildateEmail()){
		return true;
	}else{
		return false;
	}
}
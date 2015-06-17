<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>用户登录</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/commons/jquery-1.11.1.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/login/modernizr.js"></script>
		<script src="${pageContext.request.contextPath }/static/login/login.js"></script>
		<link href="${pageContext.request.contextPath }/static/login/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/static/login/pnf.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="content">
			<div id="register_box">
				<div id="head">
					<h1>精品课程登录</h1>
					<div class="tag">精品课程一个高端知识的集散地</div>
				</div>
				<div id="main_box">
					<form class="register" method="post" onsubmit="return register()">
						<h1>登录</h1>
						<br />
						<h2>username<span class="ename error">${eLoginName }</span></h2>
						<div class="text">
							<img src="${pageContext.request.contextPath }/commons/img/login/username.png" alt="username" />
							<input type="text" name="loginname" placeholder="loginname" value="${loginname }"/><br />
						</div>
						<h2>password<span class="epassword error">${ePassword }</span></h2>
						<div class="text">
							<img src="${pageContext.request.contextPath }/commons/img/login/password.png" alt="password" />
							<input type="password" name="password" placeholder="Password" value="${password }"/><br />
						</div>	
						
						<input type="submit" value="登          录" />
						<br />				
						<div class="login">
							如果没有账号&ensp;你可以
							<a href="${pageContext.request.contextPath }/register">注册</a>
						</div>
					</form>
					<div class="right_box">
						<div id="benefits">
							<h1>精品课堂</h1><br />
							
							<ul>
								<li>
									<div class="he">更快速的知识更新</div>
									<span>优秀的教学团队</span>
								</li>
								<li>
									<div class="he">社区知识共同爆发</div>
									<span>知识社区你可以分享或看他人的学习分享</span>
								</li>
								<li>
									<div class="he">完全免费</div>
									<span>网站的全部资源是完全免费的</span>
								</li>
							</ul>
						</div>
						<br /><br /><br />
						
						<div id="facebook-con">
							<h1>分享到你的朋友圈</h1>
							<br />
						</div>
						<div class="fbl">
							<br />
							<div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/button.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><a class="bshareDiv" onclick="javascript:return false;"></a><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
							<br />
							<span>	
								还不赶紧 向你的朋友<em>炫耀</em> 一下
							</span>
						</div>
					</div>
				</div>
				<div id="footer_box">
					网站 <a href="${pageContext.request.contextPath }/index" target="_blank" title="首页">首页</a> 
				</div>
			</div>
		</div>
	</body>
</html>
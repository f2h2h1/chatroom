<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>聊天工具登录界面</title>

<link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
<link rel="stylesheet" href="css/style.css" media="screen"
	type="text/css" />
</head>

<body class="login-page">
	<div id="app" class="login-form">
		<div class="login-content">
			<form method="post" role="form" id="form_login"
				v-bind:action="action">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon"></div>
						<input type="email" class="form-control" name="email" id="email"
							placeholder="邮箱" autocomplete="off" />
					</div>
				</div>
				<div v-if="register" class="form-group">
					<div class="input-group">
						<div class="input-group-addon"></div>
						<input type="text" class="form-control" name="username"
							id="username" placeholder="用户名" autocomplete="off" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon"></div>
						<input type="password" class="form-control" name="password"
							id="password" placeholder="密码" autocomplete="off" />
					</div>
				</div>
				<div v-if="register" class="form-group">
					<div class="input-group">
						<div class="input-group-addon"></div>
						<input type="password" class="form-control" name="passwordb"
							id="password2" placeholder="确认密码" autocomplete="off" />
					</div>
				</div>
				<div v-if="!register" class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-login">
						<center>登 录</center>
					</button>
				</div>
				<div v-if="!register" class="form-group">
					<button v-on:click="greet" type="button"
						class="btn btn-primary btn-block btn-login">
						<center>注 册</center>
					</button>
				</div>
				<div v-if="register" class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-login">
						<center>完 成 注 册</center>
					</button>
				</div>
				<div v-if="register" class="form-group">
					<button v-on:click="greet" type="submit"
						class="btn btn-primary btn-block btn-login">
						<center>返 回</center>
					</button>
				</div>
			</form>
			<div id="footer" class="container">
				<nav class="navbar navbar-fixed-bottom">
					<div class="navbar-inner navbar-content-center">
						<p class="text-muted credit" style="padding: 10px; margin: 0px">&copy;
							All rights reserved.</p>
						<p class="text-muted credit" style="padding: 0px;">
							<a href="http://www.miitbeian.gov.cn/" target="_blank">粤ICP备15022995号-2</a>
						</p>
					</div>
				</nav>
			</div>
		</div>
	</div>

	<script src="js/vue.min.js"></script>
	<script>
		var app = new Vue({
			el : '#app',
			data : {
				register : false,
				action: '/TestLogin1/login',
			},
			methods : {
				greet : function(event) {
					console.log(1);
					this.register = !this.register;
					if (this.action == "/TestLogin1/login")
						this.action = "/TestLogin1/register";
					else
						this.action = "/TestLogin1/login";
				}
			}
		})
	</script>
</body>

</html>
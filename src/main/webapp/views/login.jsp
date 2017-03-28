<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<title></title>
	<jsp:include page="../common/common.jsp"></jsp:include>
</head>

<!-- BEGIN BODY -->

<body class="login">
	<!-- canvas begin -->
		<canvas id="Mycanvas"></canvas>
	<!-- canvas end-->
	<!-- BEGIN LOGO -->

	<div class="logo animated bounceInLeft">
		<!-- <img src="../images/kouliang.png"> -->
		<p style="color:#686969;font-size:50px;text-align:center;">济南物流管理
		</p>
	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content animated bounceInRight">

		<!-- BEGIN LOGIN FORM -->

		<form class="form-vertical login-form" id="form_login">

			<!-- <h3 class="form-title">登录你的账号</h3> -->

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">用户名</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i>

						<input id="UserName" class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="userName"/>

					</div>

				</div>

			</div>

			<div class="control-group normal">

				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input id="UserPass" class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="passwd"/>

					</div>

				</div>

			</div>

			<div class="form-actions">

				<!-- <label class="checkbox">

					<input type="checkbox" name="remember" value="1"/> 自动登录

				</label> -->
				
				<button id="btn_login" class="btn green btn-block">登录 
					<i class="icon-user"></i>
				</button>
			</div>

			<!-- <div class="forget-password">
			
				<h4>忘记密码</h4>
			
				<p>
			
					不用担心，点击 <a href="javascript:;" class="" id="forget-password">这里</a>
			
					重新设置你的密码
			
				</p>
			
			</div>
			 -->

		</form>

		<!-- END LOGIN FORM -->        

	</div>

	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->

	<div class="copyright animated bounceInUp">

		2016 &copy; Design by elemenT

	</div>
	<script src="/js/libs/jquery/jquery-1.10.1.min.js"></script>
	<script src="/js/libs/jquery/jquery.validate.min.js"></script>
	<script src="/js/libs/jquery/progressbar.js"></script>
	<script src="/js/libs/jquery/toast.js"></script>
	<script src="/js/libs/bootstrap/bootstrap.min.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/login.js"></script>
	<script>

			//定义画布宽高和生成点的个数
			var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 35;
			
			var canvas = document.getElementById('Mycanvas');
			canvas.width = WIDTH,
			canvas.height = HEIGHT;
			var context = canvas.getContext('2d');
			context.strokeStyle = 'rgba(0,0,0,0.02)',
			context.strokeWidth = 1,
			context.fillStyle = 'rgba(0,0,0,0.05)';
			var circleArr = [];

			//线条：开始xy坐标，结束xy坐标，线条透明度
			function Line (x, y, _x, _y, o) {
				this.beginX = x,
				this.beginY = y,
				this.closeX = _x,
				this.closeY = _y,
				this.o = o;
			}
			//点：圆心xy坐标，半径，每帧移动xy的距离
			function Circle (x, y, r, moveX, moveY) {
				this.x = x,
				this.y = y,
				this.r = r,
				this.moveX = moveX,
				this.moveY = moveY;
			}
			//生成max和min之间的随机数
			function num (max, _min) {
				var min = arguments[1] || 0;
				return Math.floor(Math.random()*(max-min+1)+min);
			}
			// 绘制原点
			function drawCricle (cxt, x, y, r, moveX, moveY) {
				var circle = new Circle(x, y, r, moveX, moveY)
				cxt.beginPath()
				cxt.arc(circle.x, circle.y, circle.r, 0, 2*Math.PI)
				cxt.closePath()
				cxt.fill();
				return circle;
			}
			//绘制线条
			function drawLine (cxt, x, y, _x, _y, o) {
				var line = new Line(x, y, _x, _y, o)
				cxt.beginPath()
				cxt.strokeStyle = 'rgba(0,0,0,'+ o +')'
				cxt.moveTo(line.beginX, line.beginY)
				cxt.lineTo(line.closeX, line.closeY)
				cxt.closePath()
				cxt.stroke();

			}
			//初始化生成原点
			function init () {
				circleArr = [];
				for (var i = 0; i < POINT; i++) {
					circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(20, 2), num(10, -10)/40, num(10, -10)/40));
				}
				draw();
			}

			//每帧绘制
			function draw () {
				context.clearRect(0,0,canvas.width, canvas.height);
				for (var i = 0; i < POINT; i++) {
					drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
				}
				for (var i = 0; i < POINT; i++) {
					for (var j = 0; j < POINT; j++) {
						if (i + j < POINT) {
							var A = Math.abs(circleArr[i+j].x - circleArr[i].x),
								B = Math.abs(circleArr[i+j].y - circleArr[i].y);
							var lineLength = Math.sqrt(A*A + B*B);
							var C = 1/lineLength*7-0.009;
							var lineOpacity = C > 0.03 ? 0.03 : C;
							if (lineOpacity > 0) {
								drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i+j].x, circleArr[i+j].y, lineOpacity);
							}
						}
					}
				}
			}

			//调用执行
			window.onload = function () {
				init();
				setInterval(function () {
					for (var i = 0; i < POINT; i++) {
						var cir = circleArr[i];
						cir.x += cir.moveX;
						cir.y += cir.moveY;
						if (cir.x > WIDTH) cir.x = 0;
						else if (cir.x < 0) cir.x = WIDTH;
						if (cir.y > HEIGHT) cir.y = 0;
						else if (cir.y < 0) cir.y = HEIGHT;
						
					}
					draw();
				}, 16);
			};
		</script>
</body>

<!-- END BODY -->

</html>
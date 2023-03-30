<template>
	<view class="login-container">
		<!-- Title -->
		<view class="title">
			Melancholy-Chat
		</view>
		<!-- login|register -->
		<view class="login-action">
			<button type="default" @click="regLoginModel()">注册</button>
			<button type="default" @click="tz()">登录</button>
			<a href="#" class="forget-pwd">Forgot Password?</a>
		</view>
		<!-- login-model -->
		<view :class="loginClassName">
			<view class="login-reg-switch">
				<text :class="tableLoginClass" @click="switchTab(1)">登录</text>
				<text :class="tableRegClass" @click="switchTab(2)">注册</text>
			</view>
			<view class="input-item">
				<input type="text" :adjust-position="false" placeholder="username">
			</view>
			<view class="input-item">
				<input type="password" @focus="getCaptcha()" :adjust-position="false" placeholder="password">
			</view>
			<view class="input-item input-code">
				<input type="text" :adjust-position="false" placeholder="code">
				<view class="code-img">
					<img :src="Captcha" alt="" @click="getCaptcha()">
				</view>
			</view>
			<view class="input-item">
				<button @click="tz()">登录</button>
			</view>
		</view>
	</view>
</template>

<script>
	//使用自定义api
	import request from '../../api/login/login.js';

	export default {
		data() {
			return {
				loginRegModel: false,
				loginClassName: 'login-model-init',
				Captcha: "",
				//登录样式
				tableLoginClass:"switch-login switch-login-active",
				//注册样式
				tableRegClass:"switch-reg",
			}
		},
		onLoad() {
		},
		methods: {
			switchTab(index) {
				//选中设给谁设定样式
				if (index === 2) {
					this.tableLoginClass = "switch-login";
					this.tableRegClass = "switch-reg switch-login-active";
					return;

				}
				this.tableRegClass = "switch-reg";
				this.tableLoginClass = "switch-login switch-login-active";
			},
			tz() {
				uni.switchTab({
					url: '/pages/index/index'
				})
			},
			getCaptcha() {
				//获取验证码
				request.getCaptcha()
					.then(res => {
						//设置图片
						this.Captcha = res.image;
						//保存Key
					});
			},
			regLoginModel() {
				//点击后为注册框注册animation
				this.loginRegModel = true;
				this.loginClassName = '';
				this.loginClassName = "login-model";
			},
			//用户进行登录
			user_login() {
				// 获取用户的信息
				uni.getLocale({
					type: 'wgs84',
					success: function(res) {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
					}
				})
				// uni.login({
				// 	success: (res) => {
				// 		if (res.code) {
				// 			console.log(res.code);
				// 			//访问后台地址进行登录
				// 			request.userLogin({
				// 				code: res.code
				// 			});
				// 			//判断用户信息是否完善。

				// 		}
				// 	}
				// });
			}
		}
	}
</script>

<style>
	.input-code .code-img>img {}

	.input-code .code-img {}

	.input-code .verify-code>input {}

	.input-code {
		display: flex;
	}

	/* 登录 */
	.input-item>button {
		border-radius: 30px;
		background-color: #666666;
		color: #fff;
	}

	.input-item>input:first-child {
		margin-bottom: 1.2rem;
	}

	.input-item>input {
		height: 4.5rem;
		padding: 0 1.2rem 0 1.2rem;
		background-color: #eee;
		border-radius: 30px;
	}

	.login-reg-switch .switch-login,
	.login-reg-switch .switch-reg {
		display: inline-block;
		color: #c5c5c5;
		font-size: 1.3rem;
		font-weight: 700;
		margin-bottom: 1.2rem;
		position: relative;
	}

	.login-reg-switch .switch-reg {
		margin-left: 1.2rem;
	}

	/* 设置active选中效果 */
	.login-reg-switch .switch-login-active::after {
		display: block;
		content: "";
		width: 70%;
		height: 2px;
		background-color: #593cfb;
		position: absolute;
		bottom: -0.2rem;
		left: 0;
		border-radius: 30px;
	}

	.login-reg-switch .switch-login-active {
		font-size: 1.6rem;
		color: #000;
	}

	.input-item {}

	/* login */
	.login-container {
		width: 100%;
		height: 100vh;
		position: relative;
		background-image: url("../../static/bk.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		z-index: 1;
	}

	.login-container .title {
		display: block;
		width: 20rem;
		margin: 0 auto;
		padding-top: 20rem;
		color: #fff;
		font-size: 2.5rem;
		font-weight: 800;
		z-index: 1;
	}

	.login-container .login-action {
		width: 100%;
		position: absolute;
		bottom: 0;
		padding: 5.5rem 0;
		text-align: center;
	}

	.login-container .login-action>button {
		width: 80%;
		color: #fff;
		font-weight: 600;
	}

	.login-container .login-action>button:not(:last-child) {
		margin-bottom: 1rem;
	}

	.login-container .login-action>button:nth-child(1) {
		background-color: #593cfb;
	}

	.login-container .login-action>button:nth-child(2) {
		color: #000;
		background-color: #fff;
	}

	.login-container .login-action .forget-pwd {
		font-size: 1.2rem;
		color: #c5c5c5;
		text-decoration: none;
	}

	.login-container .login-model-init {
		width: 100%;
		position: fixed;
		display: none;
	}

	.login-container .login-model {
		width: 100%;
		height: 45rem;
		position: fixed;
		bottom: 0rem;
		background-color: #fff;
		border-radius: 3rem 3rem 0 0;
		animation: loginModel-in 0.6s;
		padding: 2rem 1.2rem 4rem 1.2rem;
		box-sizing: border-box;
	}

	/* 定义动画 */
	@keyframes loginModel-in {
		from {
			bottom: -45rem;
		}

		to {
			bottom: 0rem;
		}
	}

	/* 定义动画 */
	@keyframes loginModel-out {
		from {
			bottom: 0rem;
		}

		to {
			bottom: -45rem;
		}
	}
</style>
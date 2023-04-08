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
		</view>
		<!-- login-model -->
		<view :class="loginClassName">
			<view class="login-box" v-if="isLoginBox">
				<view class="login-reg-switch">
					<text :class="tableLoginClass" @click="switchTab(1)">登录</text>
					<text :class="tableRegClass" @click="switchTab(2)">注册</text>
				</view>
				<view class="input-item">
					<i class="ri-user-5-line input-code-icon"></i>
					<input type="text" v-model="form.username" :adjust-position="false" placeholder="username">
				</view>
				<view class="input-item">
					<i class="ri-key-2-line input-code-icon"></i>
					<input type="password" v-model="form.password" @focus="getRemoteCaptcha()" :adjust-position="false"
						placeholder="password">
				</view>
				<view class="input-item input-code">
					<i class="ri-code-s-slash-line input-code-icon"></i>
					<input type="text" :adjust-position="false" v-model="form.verifycode" placeholder="verifycode">
					<view class="code-img">
						<img :src="Captcha" alt="" >
					</view>
				</view>
				<view class="input-item">
					<button @click="login()">登录</button>
				</view>
				<view class="input-item forget-tips">
					<a href="javascript:;">忘记密码?</a>
				</view>
				<view class="input-item login-type">
					<a href="javascript:;">
						<i class="ri-wechat-fill"></i>
					</a>
					<a href="javascript:;">
						<i class="ri-qq-fill"></i>
					</a>
					<a href="javascript:;">
						<i class="ri-github-fill"></i>
					</a>
				</view>
			</view>
			<view class="register-box" v-else>
				<view class="login-reg-switch">
					<text :class="tableLoginClass" @click="switchTab(1)">登录</text>
					<text :class="tableRegClass" @click="switchTab(2)">注册</text>
				</view>
				<view class="input-item">
					<i class="ri-user-5-line input-code-icon"></i>
					<input type="text" v-model="form.nickName" :adjust-position="false" placeholder="nickName">
				</view>
				<view class="input-item">
					<i class="ri-user-5-line input-code-icon"></i>
					<input type="text" v-model="form.username" :adjust-position="false" placeholder="username">
				</view>
				<!-- 	<view class="input-item">
					<i class="ri-mail-line input-code-icon"></i>
					<input type="text" :value="form.email" :adjust-position="false" placeholder="email">
				</view> -->
				<view class="input-item">
					<i class="ri-key-2-line input-code-icon"></i>
					<input type="password" v-model="form.password" :adjust-position="false"
						placeholder="password">
				</view>
				<!-- <view class="input-item input-code">
					<i class="ri-code-s-slash-line input-code-icon"></i>
					<input type="text" :adjust-position="false" :value="form.verifycode" placeholder="verifycode">
					<view class="code-img get-code">
						<a href="javascript:;">获取验证码</a>
					</view>
				</view> -->
				<view class="input-item">
					<button @click="register()">注册</button>
				</view>
				<!-- <view class="input-item forget-tips">
					<a href="javascript:;">忘记密码?</a>
				</view> -->
				<!-- 	<view class="input-item login-type">
					<a href="javascript:;">
						<i class="ri-wechat-fill"></i>
					</a>
					<a href="javascript:;">
						<i class="ri-qq-fill"></i>
					</a>
					<a href="javascript:;">
						<i class="ri-github-fill"></i>
					</a>
				</view> -->
			</view>
		</view>
	</view>
</template>

<script>
	//使用自定义api
	import api from '../../api/login/login.js';
	export default {
		data() {
			return {
				//表单内容
				form: {
					nickName: '',
					username: '',
					password: '',
					resPassword: '',
					email: '',
					verifycode: '',
					key: ''
				},
				//页面属性
				isLoginBox: true,
				loginRegModel: false,
				loginClassName: 'login-model-init',
				Captcha: "",
				//登录样式
				tableLoginClass: "switch-login switch-login-active",
				//注册样式
				tableRegClass: "switch-reg",
			}
		},
		onLoad() {},
		methods: {
			tz(){
				uni.switchTab({
					url:"/pages/index/index"
				})
			},
			switchTab(index) {
				//判断是否为注册或者登录页面显示
				this.isLoginBox = index == 2 ? false : true;
				//选中设给谁设定样式
				if (index === 2) {
					this.tableLoginClass = "switch-login";
					this.tableRegClass = "switch-reg switch-login-active";
					return;

				}
				this.tableRegClass = "switch-reg";
				this.tableLoginClass = "switch-login switch-login-active";
			},
			register() {
				api.register({
					data: {
						'nickName':this.form.nickName,
						'username':this.form.username,
						'password':this.form.password,
					}
				}).then(res => {
					console.log(res);
					uni.showToast({
						title: res.msg,
						duration: 1000
					})
				})
			},
			login() {
				api.userLogin({
					data: this.form
				}).then(res => {
					uni.showToast({
						title: res.msg,
						duration: 1000
					})
					uni.switchTab({
						url: '/pages/index/index'
					})
				})
			},
			getRemoteCaptcha() {
				console.log("发送请求");
				//获取验证码
				api.getCaptcha({})
					.then(res => {
						console.log(res);
						//设置图片
						this.Captcha = res.image;
						//保存Key
						this.form.key = res.key;
					}).catch(err=>{
						console.log(err);
					});
			},
			regLoginModel() {
				//点击后为注册框注册animation
				this.loginRegModel = true;
				this.loginClassName = '';
				this.loginClassName = "login-model";
			}
		}
	}
</script>

<style>
	.login-model .login-type>a:nth-child(3)>i {
		color: #000000;
	}

	.login-model .login-type>a:nth-child(2)>i {
		color: #4b99fb;
	}

	.login-model .login-type>a:nth-child(1)>i {
		color: #34d234;
	}

	.login-model .login-type>a>i {
		font-size: 2.6rem;
	}

	.login-model .login-type>a {
		width: 4.8rem;
		height: 4.8rem;
		line-height: 4.8rem;
		text-decoration: none;
		display: inline-block;
		border-radius: 50%;
		background-color: #fff;
		text-align: center;
		box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, .5);
	}

	.login-model .login-type {
		display: flex;
		justify-content: space-around;
		margin-top: 7.2rem;
	}

	.input-code .code-img.get-code>a {
		font-size: 1.2rem;
	}

	.input-code .code-img.get-code {
		text-align: center;
	}

	.input-code .code-img>img,
	.input-code .code-img.get-code {
		height: 4.5rem;
		line-height: 4.5rem;
		text-align: center;
	}

	.input-code .code-img {}

	.input-code>input {
		margin-right: 1.2rem;
	}

	.input-code {
		display: flex;
		box-sizing: border-box;
	}

	/* 登录 */
	.input-item.forget-tips>a {
		text-decoration: none;
		font-size: 1.2rem;
	}

	.input-item.forget-tips {
		text-align: right;
	}

	.input-item>button {
		border-radius: 1.2rem;
		background-color: #4371ff;
		color: #fff;
	}

	.input-item>input:first-child {
		margin-bottom: 1.2rem;
	}

	.input-item .input-code-icon {
		position: absolute;
		top: 50%;
		left: 3.7rem;
		transform: translate(-50%, -50%);
		font-size: 1.8rem;
	}

	.input-item>input::after {
		display: block;
		content: "";
		width: 0.1rem;
		height: 1.8rem;
		background-color: #ccc;
		position: absolute;
		top: 50%;
		left: 5.2rem;
		transform: translate(0, -50%);
	}

	.input-item>input {
		height: 4.5rem;
		padding: 0 1.2rem 0 4.6rem;
		background-color: #eee;
		border-radius: 1rem;
		box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, .5);
	}

	.input-item {
		padding: 0 1.2rem;
		position: relative;
		margin-bottom: 1.2rem;
	}

	.login-reg-switch .switch-login,
	.login-reg-switch .switch-reg {
		display: inline-block;
		color: #c5c5c5;
		font-size: 1.3rem;
		font-weight: 700;
		margin-bottom: 2rem;
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
		background-color: #f0fbf8;
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
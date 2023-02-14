<template>
	<view>
		<!-- 解决navigationStyle状态为custom问题。 -->
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>

		<!-- 状态栏 -->
		<view class="box">
			<uni-nav-bar height="50px" title="授权登录" />
		</view>

		<!-- 弹窗层 -->
		<view class="applet-mask">
			<view class="Applet-info">
				<!-- 小程序logo。 -->
				<img src="http://q2.qlogo.cn/headimg_dl?dst_uin=2369668922&spec=100" alt="">
				<!-- 小程序介绍。 -->
				<p class="Applet-desc">如需正常使用小程序的功能，请点击下方授权登录按钮，打开授权弹窗，并点击允许。</p>
				<!-- 授权登录按钮。 -->
				<button type="primary" class="authorities" size="mini" @click="user_login">授权登录</button>
			</view>
		</view>

		<!-- 头像上传、设置昵称、设置性别 -->
		<view class="user-info">
			<form @submit="formSubmit" @reset="formReset">
				<view class="user-avatar">
					<button class="avatar-wrapper" open-type="chooseAvatar" @chooseavatar="onChooseAvatar">
						<image class="avatar" :src="avatarUrl"></image>
					</button>
				</view>
				<view class="user-nickname">
					<input type="nickname" class="weui-input" placeholder="请输入昵称" />
				</view>
				<view class="user-sex">
					<radio-group class="sexRadio" @change="sexChange"  v-for="(item, index) in sex" :key="item.value">
						<radio color="#2DCF8C" :value="item.value + ''" :checked="index + 1 === gender" />
						<b>{{item.label}}</b>
					</radio-group>
				</view>
				<view class="submit">
					<button type="primary" size="mini">提交</button>
				</view>
			</form>
		</view>
	</view>
</template>

<script>
	//使用自定义api
	import request from '../../api/login/login.js';

	export default {
		data() {
			return {
				avatarUrl: 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0',
				gender: '',
				sex: [{
						value: 1,
						label: '男'
					},
					{
						value: 2,
						label: '女'
					},
					{
						value: 3,
						label: '其他'
					}
				],

			}
		},
		onLoad() {
			console.log();
		},
		methods: {
			sexChange(evt) { //性别选择
				for (let i = 0; i < this.sex.length; i++) {
					if (this.sex[i].value == evt.target.value) {
						this.gender = i + 1; //把获取到的值赋值给 this.cityForm.gender
						console.log(this.gender)
						break;
					}
				}
			},
			onGetUserInfo(e) {
				console.log(e);
			},
			onChooseAvatar(e) {
				const {
					avatarUrl
				} = e.detail
				this.avatarUrl = avatarUrl;
			},
			formSubmit: function(e) {
				console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
				var formdata = e.detail.value
				uni.showModal({
					content: '表单数据内容：' + JSON.stringify(formdata),
					showCancel: false
				});
			},
			formReset: function(e) {
				console.log(e);
				console.log('清空数据')
			},
			//获取用户基础信息
			getUserProfile() {
				uni.getUserProfile({
					desc: '获取用户基础信息',
					success: (res) => {
						console.log(res);
					}
				})
			},
			//用户进行登录
			user_login() {
				// 获取用户的信息
				uni.login({
					success: (res) => {
						if (res.code) {
							console.log(res.code);
							//访问后台地址进行登录
							request.userLogin({
								code: res.code
							});
						}
					}
				});
			}
		}
	}
</script>

<style>
	.user-info .submit {
		margin-top: 15px;
	}
	.user-info .user-sex>radio-group:not(:first-child){
		margin-left: 10px;
	}
	.user-info .user-sex {
		display: flex;
		font-size: 12px;
		justify-content: flex-start;
		margin-top: 12px;
	}

	.user-info {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		text-align: center;
	}

	.user-info .user-avatar .avatar-wrapper {
		padding: 0;
		width: 56px !important;
		border-radius: 8px;
	}

	.user-info .user-avatar .avatar-wrapper>image {
		display: block;
		width: 56px;
		height: 56px;
	}

	.user-info .user-nickname {
		margin-top: 15px;
	}

	.user-info .user-nickname .weui-input {
		border: 1px solid #efefef;
		width: 230px;
		height: 35px;
		padding: 5px 10px;
		border-radius: 5px;
		font-size: 12px;
		box-sizing: border-box;
	}

	.box {
		height: 50px;
	}

	.applet-mask {
		position: absolute;
		width: 100vw;
		height: 100vh;
		top: 0;
		left: 0;
		z-index: 99;
		background-color: rgba(0, 0, 0, 0.5);
	}

	.Applet-info {
		position: absolute;
		top: 50%;
		left: 50%;
		width: 60%;
		padding: 10px 15px;
		border-radius: 8px;
		transform: translate(-50%, -50%);
		text-align: center;
		background-color: #fff;
		z-index: 100;
	}

	.Applet-info>img {
		width: 65px;
		height: 64px;
		display: block;
		margin: 0 auto;
		border-radius: 50%;
	}

	.Applet-info .Applet-desc {
		font-size: 13px;
		margin-top: 12px;
		color: #ccc9c7;
	}

	.Applet-info .authorities {
		margin-top: 12px;
	}
</style>

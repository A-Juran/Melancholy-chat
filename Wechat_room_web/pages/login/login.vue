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

		<view class="Applet-info">
			<!-- 小程序logo。 -->
			<img src="http://q2.qlogo.cn/headimg_dl?dst_uin=2369668922&spec=100" alt="">
			<!-- 小程序介绍。 -->
			<p class="Applet-desc">如需正常使用小程序的功能，请点击下方授权登录按钮，打开授权弹窗，并点击允许。</p>
			<!-- 授权登录按钮。 -->
			<button type="primary" class="authorities" size="mini" @click="user_login">授权登录</button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			user_login() {
				// 获取用户的信息
				uni.login({
					success: (res) => {
						if(res.code){
							console.log(res.code);
						}
					}
				});
			}
		}
	}
</script>

<style>
	.box {
		height: 50px;
	}

	.Applet-info {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		text-align: center;
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

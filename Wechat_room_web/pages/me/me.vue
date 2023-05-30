<template>
	<view class="container">
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>
		<!-- 头像展示 -->
		<view class="user-info" :style="{'background-image': bk}">
			<view class="user-avatar">
			</view>
			<view class="user-basic-info">
				<text class="user-name">{{currentUser.username}}</text>
				<text class="user-introduce">极霸矛，湘阿痕响啊。</text>
			</view>
			<!-- setting -->
			<view class="setting">
				<view class="event">
					<view class="tips">
						<i class="ri-folder-2-line"></i>
						修改资料
					</view>
					<i class="next ri-arrow-drop-right-line"></i>
				</view>
				<view class="event">
					<view class="tips">
						<i class="ri-lock-password-line"></i>
						修改密码
					</view>
					<i class="next ri-arrow-drop-right-line"></i>
				</view>
				<!-- 分割线 -->
				<view class="split-line">

				</view>
				<view class="event">
					<view class="tips">
						<i class="ri-question-line"></i>
						使用帮助
					</view>
					<i class="next ri-arrow-drop-right-line"></i>
				</view>
				<view class="event">
					<view class="tips">
						<i class="ri-bookmark-2-line"></i>
						法律条款
					</view>
					<i class="next ri-arrow-drop-right-line"></i>
				</view>
				<view class="split-line">

				</view>
				<view class="event">
					<view class="tips">
						<i class="ri-chat-smile-3-line"></i>
						意见反馈
					</view>
					<i class="next ri-arrow-drop-right-line"></i>
				</view>
				<view class="event" @click="logout">
					<view class="tips">
						<i class="ri-logout-box-line"></i>
						退出
					</view>
					<i class="next ri-arrow-drop-right-line"></i>
				</view>
			</view>
		</view>
		<!-- copyright -->
		<view class="copyright">
			<i class="ri-copyright-line"></i>
			<text>Juran(QQ：21171326/12540701)</text>
		</view>
		<!-- 自定义TabBar -->
		<Tabbar :current="'3'"></Tabbar>
	</view>
</template>

<script>
	import Tabbar from '../common/tabbar.vue'
	export default {
		components: {
			Tabbar
		},
		data() {
			return {
				bk: '',
				statusbarHeight: 0,
				userinfo: {
					name: 'JuRan'
				},
				extraIcon: {
					color: '#4cd964',
					size: '18',
					type: 'gear-filled'
				}
			}
		},
		onReady() {},
		onLoad() {
			//加载用户信息
			this.$store.dispatch('loadCurrentUserInfo');
			this.bk = 'url("' + this.$store.getters.getCurrentUser.avatarUrl +'")'
		},
		onShow() {
			uni.hideTabBar({
				animation: false
			})
		},
		computed:{
			currentUser(){
				return this.$store.getters.getCurrentUser;
			}
		},
		methods: {
			logout() {
				//清除用户信息
				uni.clearStorageSync();
				//设置全局状态
				this.$store.commit("SET_TOKEN", '');
				this.$store.commit("SET_CURRENT_USER", {});
				//跳转页面
				uni.navigateTo({
					url: '/pages/login/login'
				})
			}
		}
	}
</script>

<style>
	/* copyright */
	.copyright>i {
		vertical-align: middle;
	}

	.copyright>text,
	.copyright>i {
		color: #a6a6a6;
	}

	.copyright {
		width: 100%;
		position: absolute;
		bottom: 9.5rem;
		text-align: center;
		color: #fff;
	}

	/* userinfo setting */
	.user-info .setting .split-line {
		border-bottom: 1px solid rgb(237, 237, 237);
	}

	.user-info .setting .event>i {
		font-size: 1.8rem;
		color: rgb(201, 205, 216);
	}

	.user-info .setting .event .tips>i {
		font-size: 1.8rem;
		margin-right: .8rem;
		vertical-align: middle;
	}

	.user-info .setting .event .tips {
		height: 4.5rem;
		line-height: 4.5rem;
	}

	.user-info .setting .event {
		width: 100%;
		display: flex;
		justify-content: space-between;
		font-size: 1.3rem;
		height: 4.5rem;
		line-height: 4.5rem;
	}

	.user-info .setting {
		width: 300px;
		height: 500px;
		background: #fff;
		position: absolute;
		top: 22rem;
		right: 0;
		border-radius: 2.6rem 1.2rem 0 3.2rem;
		padding: 1rem 1.2rem 0 2rem;
		box-sizing: border-box;
	}

	/* 个人信息 */
	.user-info .user-basic-info .user-name {
		font-size: 1.8rem;
		font-weight: bold;
	}

	.user-info .user-basic-info .user-introduce {
		font-size: 1.6rem;
	}

	.user-info .user-basic-info .user-name,
	.user-info .user-basic-info .user-introduce {
		display: block;
		color: #fff;
	}

	.user-info .user-basic-info {
		position: absolute;
		left: 0;
		top: 60%;
		transform: translate(0, -50%);
		padding-left: 1.2rem;
	}

	.user-info .user-avatar {
		width: 100%;
		height: 100%;
		background-image: linear-gradient(180deg,
				rgba(255, 149, 28, 0) 80%,
				rgba(246, 247, 247, 1));
		z-index: 99;
	}

	.user-info {
		position: relative;
		width: 100%;
		height: 30rem;
		background-size: cover;
		background-repeat: no-repeat;
		background-position: center center;
		border: none;
	}
</style>
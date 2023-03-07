<template>
	<view class="container">
		
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>
		
<!-- 		<view class="box-bg">
			<uni-nav-bar height="50px" title="我" />
		</view> -->
		
		<view class="user-background-wall">
			<!-- 熊 -->
			<view class="run-bear">
			</view>
			<!-- 介绍 -->
			<text class="user-background-wall-desc">
				"以前笑孔乙己，到头来成了孔乙己。"（{{userinfo.name}}）
			</text>
		</view>
		<!-- 用户头像 -->
		<view class="current-user-info">
			<image src="http://q2.qlogo.cn/headimg_dl?dst_uin=21171326&spec=100" mode=""></image>
		</view>
		<!-- 用户个人信息设置 -->
		<view class="user-info-card">
			<uni-section title="用户信息设置" type="line">
				<uni-list>
					<uni-list-item :show-extra-icon="true" showArrow :extra-icon="extraIcon" title="个人信息" />
				</uni-list>
			</uni-section>
			<uni-card>
				<text>自然赋于人们的不调和还很多,人们自己萎缩堕落退步的也还很多,然而生命决不因此回头。</text>
			</uni-card>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userinfo: {
					name: 'JuRan'
				},
				extraIcon: {
					color: '#4cd964',
					size: '22',
					type: 'gear-filled'
				}
			}
		},
		methods: {

		}
	}
</script>

<style>
	/* 背景图片 */
	.user-background-wall {
		width: 100%;
		height: 9.6618rem;
		background: url("@/static/user-info/user-background.png") no-repeat;
		background-position: left bottom;
		background-size: 100% auto;
		background-color: #3b3d4b;
		z-index: -999;
		/*  */
		position: relative;
		overflow: hidden;
		margin: 0 auto;
	}

	/* 背景熊 */
	.user-background-wall .run-bear {
		width: 200px;
		height: 5rem;
		position: absolute;
		bottom: 0;
		left: 30%;
		transform: translateX(-50%);
		background: url("@/static/user-info/xiong.png") no-repeat;
		animation: run .6s steps(8) infinite;
	}

	.user-background-wall .user-background-wall-desc {
		position: absolute;
		top: 20%;
		left: 50%;
		transform: translate(-50%, -50%);
		font-size: 0.5797rem;
		color: #fff;
		white-space: nowrap;
	}

	.current-user-info {
		position: relative;
		width: 100%;
		text-align: center;
	}

	.current-user-info>image {
		position: absolute;
		right: 20%;
		top: -3.8rem;

		display: block;
		width: 2.9917rem;
		height: 2.9917rem;
		border-radius: 50%;
		margin: 0 auto;
		margin-bottom: 5px;
	}

	.current-user-info>text {
		font-size: 0.8rem;
	}

	/* 设置熊跑动动画 */
	@keyframes run {
		0% {}

		100% {
			background-position: -1600px 0;
		}
	}
</style>

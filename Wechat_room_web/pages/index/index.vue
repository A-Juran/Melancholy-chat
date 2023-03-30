<template>
	<view class="chat-body">
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>
		<view class="chat-tabbar">
			<view class="text">
				Message
			</view>
			<view class="search">
				<i class=""></i>
				<input type="text" @focus="searchFocus(true)" @blur="searchFocus(false)" :class="searchClass" placeholder="Search">
			</view>
		</view>
		<!-- 群列表 -->
		<view class="chat-list">
			<!-- 我的聊天群列表 -->
			<view class="chat-list-item" @click="toCommiuncation()">
				<!-- 群头像、基础信息 -->
				<view class="chat-object-info">
					<image src="http://q2.qlogo.cn/headimg_dl?dst_uin=21171326&spec=100" mode=""></image>
					<view class="chat-object-bease-info">
						<p>Mr.Fan</p>
						<text>So</text>
					</view>
				</view>
				<!-- 聊天时间 -->
				<view class="chat-info-time">
					2023-01-01
				</view>
			</view>
			<!-- 我的聊天群列表 -->
			<view class="chat-list-item">
				<!-- 群头像、基础信息 -->
				<view class="chat-object-info">
					<image src="http://q2.qlogo.cn/headimg_dl?dst_uin=2369668922&spec=100" mode=""></image>
					<view class="chat-object-bease-info">
						<p>Mr.H</p>
						<text>Ok</text>
					</view>
				</view>
				<!-- 聊天时间 -->
				<view class="chat-info-time">
					2023-01-01
				</view>
			</view>
			<!-- 我的聊天群列表 -->
			<view class="chat-list-item">
				<!-- 群头像、基础信息 -->
				<view class="chat-object-info">
					<image src="http://q2.qlogo.cn/headimg_dl?dst_uin=12540701&spec=100" mode=""></image>
					<view class="chat-object-bease-info">
						<p>Su Shan</p>
						<text>Just Do it</text>
					</view>
				</view>
				<!-- 聊天时间 -->
				<view class="chat-info-time">
					2023-01-01
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				search: '',
				searchClass:"input"
			}
		},
		onLoad() {

		},
		methods: {
			searchFocus(status){
				if(status){
					this.searchClass = "";
					this.searchClass = "searchInput";
					return;
				}
				this.searchClass = "input";
			},
			toCommiuncation() {
				uni.navigateTo({
					url: "/pages/index/commiuncation/commiuncation"
				})
			}
		}
	}
</script>

<style>
	.chat-tabbar .search .searchInput{
		height: 4rem;
		background-color: #f4eded;
		border-radius: 3rem;
		padding: 0 0 0 3rem;
		animation: 0.3s searchAnimationIn;
	}
	.chat-tabbar .search .input {
		width: 10rem;
		height: 4rem;
		background-color: #f4eded;
		border-radius: 3rem;
		padding: 0 0 0 3rem;
		animation: 0.3s searchAnimationOut;
	}

	.chat-tabbar .search {
		padding: 0 .3px;
		box-sizing: border-box;
	}

	.chat-tabbar .text {
		font-size: 3rem;
		font-weight: 700;
		vertical-align: middle;
	}

	.chat-tabbar {
		width: 100%;
		height: 4.5rem;
		line-height: 4.5rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: .8rem 0.8rem;
		box-sizing: border-box;
	}

	.chat-body {
		width: 100%;
		padding: 0px 0px 8px 0px;
		box-sizing: border-box;
	}

	.chat-header {
		position: relative;
		width: 100%;
	}

	.chat-header,
	.chat-body .chat-list {
		width: 100%;
		padding: 1.6rem 1.6rem 0 1.6rem;
		box-sizing: border-box;
	}

	.chat-header {
		padding: 0;
	}

	.chat-body .chat-list .chat-list-item {
		display: flex;
		justify-content: space-between;
		padding: 8px 0;
		box-sizing: border-box;
		position: relative;
		/* 动画 */
		/* animation: name duration timing-function delay iteration-count direction fill-mode; */
		animation: chatItem 1s;

	}

	.chat-body .chat-list .chat-list-item:not(:last-child) {
		box-shadow: 0px 0.3px 0px rgba(0, 0, 0, .1);
	}

	.chat-body .chat-list .chat-list-item .chat-object-info {
		height: 100%;
		display: flex;
		align-items: center;
	}

	.chat-body .chat-list .chat-list-item .chat-object-info>image {
		display: inline-block;
		width: 4.5rem;
		height: 4.5rem;
		border-radius: 50%;
		margin-right: 10px;
	}

	.chat-body .chat-list .chat-list-item .chat-object-info .chat-object-bease-info {
		font-size: 0.5797rem;
	}

	.chat-body .chat-list .chat-list-item .chat-object-info .chat-object-bease-info>p {
		font-size: 1.2rem;
		font-weight: bold;
	}

	.chat-body .chat-list .chat-list-item .chat-object-info .chat-object-bease-info>text {
		display: block;
		width: 7.2463rem;
		font-size: 1rem;
		font-weight: bold;
		overflow: hidden;
		text-overflow: ellipsis;
		-webkit-line-clamp: 1;
		white-space: nowrap;
		color: #ccc;
	}

	.chat-body .chat-list .chat-list-item .chat-info-time {
		font-size: 0.5797rem;
		line-height: 2.8985rem;
		text-align: center;
		color: #ccc;
	}
	
	/* 动画-帧动画 */
	@keyframes searchAnimationOut {
		from {
			width: 16rem;
		}
	
		to {
			width: 10rem;
		}
	}
	
	/* 动画-帧动画 */
	@keyframes searchAnimationIn {
		from {
			width: 10rem;
		}
	
		to {
			width: 16rem;
		}
	}

	/* 动画-帧动画 */
	@keyframes chatItem {
		from {
			top: 100px;
		}

		to {
			top: 0px;
		}
	}
</style>
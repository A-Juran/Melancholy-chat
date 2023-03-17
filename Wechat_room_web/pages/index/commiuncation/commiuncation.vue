<template>
	<view class="chat-body">
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>

		<view class="box">
			<uni-nav-bar height="50px" title="聊天" />
		</view>
		<view class="user-commiuncation">
			<!-- 好友信息 -->
			<view class="other-msg">
				<!-- 头像 -->
				<view class="avatar">
					<img src="http://q1.qlogo.cn/g?b=qq&nk=12540701&s=100" alt="" srcset="">
				</view>
				<!-- 信息内容 -->
				<view class="msg-content">
					hello,I'm Juran
				</view>
			</view>
			<!-- 自我发送 -->
			<view class="mine-msg">
				<!-- 信息内容 -->
				<view class="msg-content">
					hello juran I'm 你好刘同学.
				</view>
				<!-- 头像 -->
				<view class="avatar">
					<img src="http://q1.qlogo.cn/g?b=qq&nk=21171326&s=100" alt="" srcset="">
				</view>
			</view>
		</view>
		<!-- 发送信息框 -->
		<view class="message-send">
			<view class="msg-input">
				<uni-easyinput :Styles="styles" suffixIcon="paperplane-filled">
				</uni-easyinput>
			</view>
			<view class="msg-other">
				<uni-icons type="star" size="25"></uni-icons>
				<uni-icons type="fire" size="25"></uni-icons>
				<uni-icons type="staff" size="25"></uni-icons>
				<uni-icons type="color" size="25"></uni-icons>
				<uni-icons type="gift" size="25"></uni-icons>
				<uni-icons type="plusempty" size="25"></uni-icons>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				styles: {
					color: '#ffffff',
					borderColor: "#ffffff"
				}
			}
		},
		methods: {

		}
	}
</script>

<style>
	.chat-body {
		width: 100%;
		padding: 8px 10px;
		box-sizing: border-box;
	}

	/* 信息发送 */
	.message-send {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		padding: 8px 6px;
		font-size: 12px;
		box-sizing: border-box;
		background-color: #f5f5f5;
	}

	/deep/ .message-send.is-input-border,
	.uni-easyinput,
	.uni-input-wrapper,
	.uni-easyinput__content-input,
	.uni-easyinput__content,
	.is-input-border {
		border: none;
		border-radius: 50px;
	}

	/deep/ .message-send .is-input-border {
		border: none;
	}

	/deep/ .message-send .uni-easyinput__content {
		background-color: #fff;
		outline: none;
		border-radius: 50px;
		padding: 0 8px;
		box-sizing: border-box;
	}

	/* 聊天内容样式 */
	.user-commiuncation {
		margin: 10px 0px;
	}

	.user-commiuncation .other-msg {
		position: relative;
	}

	.user-commiuncation .mine-msg {
		position: relative;
		right: 0;
		text-align: right;
	}

	.user-commiuncation .other-msg .avatar,
	.user-commiuncation .mine-msg .avatar {
		display: inline-block;
	}

	.user-commiuncation .other-msg .avatar img,
	.user-commiuncation .mine-msg .avatar img {
		width: 2.3188rem;
		height: 2.3188rem;
		border-radius: 50%;
	}

	.user-commiuncation .other-msg .msg-content,
	.user-commiuncation .mine-msg .msg-content {
		display: inline-block;
		background-color: #0199fe;
		border-radius: 10px;
		margin-left: 8px;
		padding: 6px 6px 10px 6px;
		color: #fff;
		font-size: 0.7246rem;
	}



	/* 倒三角 */
	/* .user-commiuncation .other-msg .msg-content::before {
		content: '';
		display: 'block';
		border: 20px solid red;
		left: -20px;
		top: 5px;
		position: absolute;
		z-index: -1;
		border-radius: 5px;
		border-color: #78DDF8 transparent transparent transparent;
	} */



	/* 输入样式 */
	.message-send .msg-input {
		margin-bottom: 5px;
		box-sizing: border-box;
	}

	.message-send .msg-other {
		display: flex;
		justify-content: space-between;
		box-sizing: border-box;
		padding: 0 5px;
	}
</style>

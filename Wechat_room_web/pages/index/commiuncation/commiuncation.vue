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
				hello
			</view>
			<!-- 自我发送 -->
			<view class="mine-msg">
				hello
			</view>
		</view>
		<!-- 发送信息框 -->
		<view class="message-send">
			<view class="msg-input">
				<uni-easyinput :Styles="styles" suffixIcon="paperplane-filled" placeholder="input msg"></uni-easyinput>
			</view>
			<view class="msg-other">
				<uni-icons type="star" size="30"></uni-icons>
				<uni-icons type="fire" size="30"></uni-icons>
				<uni-icons type="staff" size="30"></uni-icons>
				<uni-icons type="color" size="30"></uni-icons>
				<uni-icons type="gift" size="30"></uni-icons>
				<uni-icons type="plusempty" size="30"></uni-icons>
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

	/deep/ .message-send.is-input-border,.uni-easyinput,
	.uni-input-wrapper,.uni-easyinput__content-input,
	.uni-easyinput__content,.is-input-border {
		border: none;
		border-radius: 50px;
	}
	/deep/ .message-send .is-input-border{
		border: none;
	}

	/deep/ .message-send .uni-easyinput__content {
		background-color: #fff;
		outline: none;
		border-radius: 50px;
		padding: 0 8px;
		box-sizing: border-box;
	}



	.message-send .msg-input {
		margin-bottom: 5px;
		box-sizing: border-box;
	}

	.message-send .msg-other {
		display: flex;
		justify-content: space-between;
		box-sizing: border-box;
	}
</style>

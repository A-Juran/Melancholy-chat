<template>
	<view class="chat-body">
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>

		<view class="box">
			<uni-nav-bar height="3rem" title="聊天" />
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
		<view class="message-send" :style="{bottom:inputBottom + 'px'}">
			<view class="msg-input">
				<!-- <uni-easyinput :Styles="styles" @focus="getInputFocus" @blur="eventHandler"
					suffixIcon="paperplane-filled">
				</uni-easyinput> -->
				<input type="text" @focus="getInputFocus" :adjust-position="false">
				<button type="primary" plain="true">发送</button>
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
				},
				headerTop: 0,
				inputBottom: 0
			}
		},
		methods: {
			getInputFocus(e) {
				if (e.detail.height) {
					this.inputBottom = e.detail.height;
				}
			},
		},
		mounted() {
			//监听键盘状态
			uni.onKeyboardHeightChange(res => {
				if (res.height == 0)
					this.inputBottom = 0
			})
		}
	}
</script>

<style>
	.chat-body {
		width: 100%;
		padding: 8px 10px;
		box-sizing: border-box;
	}

	/deep/ .box .uni-nav-bar {
		font-size: 1.5rem;
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
		z-index: 666;
		transition: .3s ease-in-out;
	}

	/* 	/deep/ .message-send.is-input-border,
	.uni-easyinput,
	.uni-input-wrapper,
	.uni-easyinput__content-input,
	.uni-easyinput__content,
	.is-input-border {
		border: none;
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
	} */

	/* 聊天内容样式 */
	.user-commiuncation {
		margin: 10px 0px;
		flex-grow: 1;
		overflow-y: auto;
	}

	.user-commiuncation .other-msg {}

	.user-commiuncation .mine-msg {
		right: 0;
		text-align: right;
	}

	.user-commiuncation .other-msg .avatar,
	.user-commiuncation .mine-msg .avatar {
		display: inline-block;
	}

	.user-commiuncation .other-msg .avatar img,
	.user-commiuncation .mine-msg .avatar img {
		width: 3.5rem;
		height: 3.5rem;
		border-radius: 50%;
	}

	.user-commiuncation .other-msg .msg-content,
	.user-commiuncation .mine-msg .msg-content {
		display: inline-block;
		background-color: #0199fe;
		border-radius: 10px;
		padding: 0.7rem 1rem 1.3rem 1rem;
		color: #fff;
		font-size: 1.2rem;
		transition: .3s ease-in-out;
	}

	.user-commiuncation .other-msg {
		position: relative;
		left: 0;
		animation: msgLeftRight .5s;
	}

	.user-commiuncation .mine-msg {
		position: relative;
		right: 0;
		animation: msgRightLeft .5s;
	}

	.user-commiuncation .other-msg .msg-content {
		margin-left: .5rem;
	}

	.user-commiuncation .mine-msg .msg-content {
		margin-right: .5rem;

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
		padding: 0 0rem;
		display: flex;
		justify-content: space-between;
	}

	.message-send .msg-input>input {
		width: 31rem;
		background-color: #fff;
		outline: none;
		border-radius: 50px;
		padding: 1rem 1rem;
		font-size: 1rem;
	}

	.message-send .msg-input>button {
		width: 6.5rem;
		font-size: 1.2rem;
		text-align: center;
		border-radius: 5rem;
		padding: 0;
		color: #fff;
		background-color: #76c3f4;
		object-fit: contain;
		border: 0;
		margin: 0;
		font-weight: 600;
	}

	.message-send .msg-other {
		display: flex;
		justify-content: space-between;
		box-sizing: border-box;
		padding: 0 2rem;
		font-weight: 600;
	}
	
	
	/* 从左往右的帧动画。 */
	@keyframes msgLeftRight {
		from {
			left: -200px;
		}
	
		to {
			lef: 0;
		}
	}
	
	@keyframes msgRightLeft {
		from {
			right: -200px;
		}
	
		to {
			right: 0;
		}
	}
</style>

<template>
	<view class="chat-body">
		<Header tabbarName="Contacts" />
		<!-- 添加按钮用户、加入群 -->
		<view class="chat-user-group-add-icon">
			<uni-fab ref="fab" :pattern="pattern" :content="content" :horizontal="horizontal" :vertical="vertical"
				:direction="direction" @trigger="trigger" @fabClick="fabClick" />
		</view>
		<!-- 通知信息/群/聊天室 -->
		<view class="user-chat-gourp-notify">
			<a href="javascript:;" @click="tabIndex($event,0)">
				<i class="ri-user-2-fill"></i>
			</a>
			<a href="javascript:;" @click="tabIndex($event,1)">
				<i class="ri-group-fill"></i>
			</a>
			<a href="javascript:;" @click="tabIndex($event,2)">
				<i class="ri-chat-smile-3-fill"></i>
			</a>
			<a href="javascript:;" @click="tabIndex($event,3)">
				<i class="ri-notification-fill"></i>
			</a>
			<view class="tab" v-bind:style="{left:left+'px'}"></view>
		</view>
		<!-- 消息列表 -->
		<view class="uni-margin-wrap">
			<swiper class="swiper" :indicator-dots="swiper.indicatorDots" circular :current="swiper.current"
				:disable-touch="swiper.touch">
				<swiper-item>
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
					</view>
				</swiper-item>
				<swiper-item>
					群组
				</swiper-item>
				<swiper-item>
					聊天室
				</swiper-item>
				<swiper-item>
					消息通知
				</swiper-item>
			</swiper>
		</view>

	</view>
</template>

<script>
	import Header from '../common/Header.vue';
	export default {
		components: {
			Header
		},
		data() {
			return {
				swiper: {
					indicatorDots: false,
					current: 0,
					touch: true

				},
				left: 0,
				title: 'uni-fab',
				directionStr: '垂直',
				horizontal: 'left',
				vertical: 'bottom',
				direction: 'horizontal',
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#007AFF',
					iconColor: '#fff'
				},
				is_color_type: false,
				content: [{
						iconPath: '',
						selectedIconPath: '',
						text: '添加',
						active: false
					},
					{
						iconPath: '',
						selectedIconPath: '',
						text: '创建群',
						active: false
					}
				]
			}
		},
		onReady() {
			//初始化tab切换tabindex的位置。
			const query = uni.createSelectorQuery().in(this);
			query.select('.user-chat-gourp-notify>a').boundingClientRect(data => {
				this.left = data.left;
			}).exec();
		},
		onBackPress() {
			if (this.$refs.fab.isShow) {
				this.$refs.fab.close()
				return true
			}
			return false
		},
		methods: {
			tabIndex(e, index) {
				this.left = e.currentTarget.offsetLeft;
				this.swiper.current = index;
				console.log(e);
			},
			trigger(e) {
				console.log(e)
				this.content[e.index].active = !e.item.active
				uni.showModal({
					title: '提示',
					content: `您${this.content[e.index].active ? '选中了' : '取消了'}${e.item.text}`,
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定')
						} else if (res.cancel) {
							console.log('用户点击取消')
						}
					}
				})
			},
			fabClick() {
				uni.showToast({
					title: '点击了悬浮按钮',
					icon: 'none'
				})
			},
			switchBtn(hor, ver) {
				if (hor === 0) {
					this.direction = this.direction === 'horizontal' ? 'vertical' : 'horizontal'
					this.directionStr = this.direction === 'horizontal' ? '垂直' : '水平'
				} else {
					this.horizontal = hor
					this.vertical = ver
				}
				this.$forceUpdate()
			},
			switchColor() {
				this.is_color_type = !this.is_color_type
				if (this.is_color_type) {
					this.pattern.iconColor = '#aaa'
					this.pattern.buttonColor = '#fff'
				} else {
					this.pattern.iconColor = '#fff'
					this.pattern.buttonColor = '#007AFF'
				}
			}
		}
	}
</script>

<style>
	.user-chat-gourp-notify .tab {
		position: absolute;
		width: 5rem;
		height: 5rem;
		background-color: #eee;
		left: 10%;
		z-index: -1;
		border-radius: 50%;
		transition: .5s ease-in-out;
	}

	/* user-chat-gourp-notify */
	.user-chat-gourp-notify>a>i {
		font-size: 2.0rem;
	}

	.user-chat-gourp-notify>a {
		display: inline-block;
		width: 5rem;
		height: 5rem;
		line-height: 5rem;
		text-align: center;
		color: #000;
		/* background-color: #eee; */
	}

	.user-chat-gourp-notify {
		position: relative;
		display: flex;
		padding: 0 1.2rem;
		justify-content: space-around;
		margin: 1.2rem 0;
	}

	/* header */
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

	.uni-fab,
	.uni-fab__content--other-platform {
		box-shadow: none !important;
	}
</style>
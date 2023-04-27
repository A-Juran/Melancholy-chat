<template>
	<view class="chat-body">
		<Header tabbarName="Contacts" />
		<!-- 添加按钮用户、加入群 -->
		<!-- <view class="chat-user-group-add-icon">
			<uni-fab ref="fab" :pattern="pattern" :content="content" :horizontal="horizontal" :vertical="vertical"
				:direction="direction" @trigger="trigger" @fabClick="fabClick" />
		</view> -->
		<!-- 通知信息/群/聊天室 -->
		<view class="user-chat-gourp-notify">
			<a href="javascript:;" @click="tabIndex($event,0)">
				<i class="ri-user-2-fill"></i>
				<text>Friend</text>
			</a>
			<a href="javascript:;" @click="tabIndex($event,1)">
				<i class="ri-group-fill"></i>
				<text>Group</text>
			</a>
			<a href="javascript:;" @click="tabIndex($event,2)">
				<i class="ri-chat-smile-3-fill"></i>
				<text>ChatRoom</text>
			</a>
			<a href="javascript:;" @click="tabIndex($event,3)">
				<i class="ri-notification-fill"></i>
				<text>Message</text>
			</a>
			<view class="tab" v-bind:style="{left:left+'px'}"></view>
		</view>
		<!-- 消息列表 -->
		<view class="uni-margin-wrap">
			<swiper class="swiper" :indicator-dots="swiper.indicatorDots" circular :current="swiper.current"
				:disable-touch="swiper.touch">
				<swiper-item>
					<scroll-view scroll-y="true" class="scroll friend-scroll">
						<!-- 好友信息展示页 -->
						<view class="chat-list">
							<!-- 我的聊天群列表 -->
							<view class="chat-list-item" @click="toCommiuncation()" v-for="frined in list.friend"
								:key="frined.id">
								<!-- 群头像、基础信息 -->
								<view class="chat-object-info">
									<!-- 头像 -->
									<image :src="frined.avatarUrl" mode="">
									</image>
									<!-- 基础信息 -->
									<view class="chat-object-bease-info">
										<p>{{frined.nickName}}</p>
										<text>{{frined.sign}}</text>
									</view>
									<!-- 在线状态 -->
									<view
										:class="frined.onlineStatus ? friendObject.online_status : friendObject.online_status_off">
									</view>
								</view>
								<!-- 聊天时间 -->
								<view class="chat-info-time">
									<!-- 2023-01-01 -->
								</view>
							</view>
						</view>
						<!-- 按字幕排序列表 -->
						<ul class="select-category">
							<li class="select-category-item" :id="item" :key="item" v-for="item in categoryList">
								{{item.toUpperCase()}}
							</li>
						</ul>
					</scroll-view>
				</swiper-item>
				<swiper-item>
					<scroll-view scroll-y="true" class="scroll">
						<!-- 好友信息展示页 -->
						<view class="chat-list">
							<!-- 我的聊天群列表 -->
							<view class="chat-list-item" @click="toCommiuncation()" v-for="frined in list.friend"
								:key="frined.id">
								<!-- 群头像、基础信息 -->
								<view class="chat-object-info">
									<!-- 头像 -->
									<image :src="frined.avatarUrl" mode="">
									</image>
									<!-- 基础信息 -->
									<view class="chat-object-bease-info">
										<p>{{frined.nickName}}</p>
										<text>{{frined.sign}}</text>
									</view>
									<!-- 在线状态 -->
									<view
										:class="frined.onlineStatus ? friendObject.online_status : friendObject.online_status_off">
									</view>
								</view>
								<!-- 聊天时间 -->
								<view class="chat-info-time">
									<!-- 2023-01-01 -->
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item>
					<scroll-view scroll-y="true" class="scroll">
						<!-- 好友信息展示页 -->
						<view class="chat-list">
							<!-- 我的聊天群列表 -->
							<view class="chat-list-item" @click="toCommiuncation()" v-for="frined in list.friend"
								:key="frined.id">
								<!-- 群头像、基础信息 -->
								<view class="chat-object-info">
									<!-- 头像 -->
									<image :src="frined.avatarUrl" mode="">
									</image>
									<!-- 基础信息 -->
									<view class="chat-object-bease-info">
										<p>{{frined.nickName}}</p>
										<text>{{frined.sign}}</text>
									</view>
									<!-- 在线状态 -->
									<view
										:class="frined.onlineStatus ? friendObject.online_status : friendObject.online_status_off">
									</view>
								</view>
								<!-- 聊天时间 -->
								<view class="chat-info-time">
									<!-- 2023-01-01 -->
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item>
					<scroll-view scroll-y="true" class="scroll">
						<!-- 好友信息展示页 -->
						<view class="chat-list">
							<!-- 我的聊天群列表 -->
							<view class="chat-list-item" @click="toCommiuncation()" v-for="frined in list.friend"
								:key="frined.id">
								<!-- 群头像、基础信息 -->
								<view class="chat-object-info">
									<!-- 头像 -->
									<image :src="frined.avatarUrl" mode="">
									</image>
									<!-- 基础信息 -->
									<view class="chat-object-bease-info">
										<p>{{frined.nickName}}</p>
										<text>{{frined.sign}}</text>
									</view>
									<!-- 在线状态 -->
									<view
										:class="frined.onlineStatus ? friendObject.online_status : friendObject.online_status_off">
									</view>
								</view>
								<!-- 聊天时间 -->
								<view class="chat-info-time">
									<!-- 2023-01-01 -->
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
		<!-- 自定义TabBar -->
		<Tabbar :current="'1'"></Tabbar>
	</view>
</template>

<script>
	import Header from '../common/Header.vue'
	import Tabbar from '../common/tabbar.vue'
	export default {
		components: {
			Header,
			Tabbar
		},
		data() {
			return {
				categoryList: [
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z', '#'
				],
				list: {
					friend: [{
							id: 21171326,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=21171326&spec=100',
							nickName: 'Mr.C',
							sign: '金钱总是万能的，好工具总是属于钱包最厚的人。',
							onlineStatus: true
						}, {
							id: 2369668922,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=2369668922&spec=100',
							nickName: 'Mr.L',
							sign: '极霸矛，湘阿痕响啊。',
							onlineStatus: false
						},
						{
							id: 12540701,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=12540701&spec=100',
							nickName: 'Mr.C',
							sign: '没有BUG的代码是不完美的！',
							onlineStatus: true
						},
						{
							id: 1626962639,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=1626962639&spec=100',
							nickName: 'Mr.H',
							sign: '极霸矛，湘阿痕响啊。',
							onlineStatus: false
						},
						{
							id: 2729027821,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=2729027821&spec=100',
							nickName: 'Mr.C',
							sign: '迎着风，拥抱彩虹！',
							onlineStatus: true
						},
						{
							id: 2940088098,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=2940088098&spec=100',
							nickName: 'Mr.Hr',
							sign: '人生三大错觉，我能反杀，下一发能出金，她喜欢我。',
							onlineStatus: true
						},
						{
							id: 1279502572,
							avatarUrl: 'http://q2.qlogo.cn/headimg_dl?dst_uin=1279502572&spec=100',
							nickName: 'Mr.LZ',
							sign: '每个人的心里，都有一个忘不记，却无法拥抱珍惜的人。',
							onlineStatus: false
						}
					]
				},
				friendObject: {
					online_status: 'online_status',
					online_status_off: 'online_status_off'
				},
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
		onShow() {
			uni.hideTabBar({
				animation: false
			})
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
	.friend-scroll .select-category .select-category-item {
		list-style-type: none;
		color: #ccc;
	}

	.friend-scroll .select-category {
		position: absolute;
		right: .8rem;
		top: 1.2rem;
	}

	.friend-scroll {
		position: relative;
	}

	/* 未进行计算的用户信息滑动内容 */
	.swiper {
		height: calc(100vh - 150px);
	}

	.scroll {
		height: 100%;
	}

	/* other */
	.user-chat-gourp-notify .tab {
		position: absolute;
		width: 5rem;
		height: 0.1rem;
		background-color: #eee;
		left: 10%;
		bottom: 0;
		z-index: -1;
		/* border-radius: 50%; */
		transition: .5s ease-in-out;
	}

	/* user-chat-gourp-notify */
	.user-chat-gourp-notify>a>text {
		display: block;
		font-size: 1rem;
	}

	.user-chat-gourp-notify>a>i {
		font-size: 2.0rem;
	}

	.user-chat-gourp-notify>a {
		display: inline-block;
		width: 5rem;
		height: 5rem;
		/* line-height: 5rem; */
		text-align: center;
		color: #000;
		/* background-color: #eee; */
	}

	.user-chat-gourp-notify {
		position: relative;
		display: flex;
		padding: 0 1.2rem;
		justify-content: space-around;
		margin: 1.2rem 0 .5rem 0;
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
		/* box-shadow: 0px 0.3px 0px rgba(0, 0, 0, .1); */
	}

	.chat-body .chat-list .chat-list-item .chat-object-info {
		height: 100%;
		display: flex;
		align-items: center;
		position: relative;
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
		/* width: 10.2463rem; */
		font-size: 1rem;
		font-weight: bold;
		overflow: hidden;
		text-overflow: ellipsis;
		-webkit-line-clamp: 1;
		white-space: nowrap;
		color: #ccc;
	}

	/* 在线状态 */
	.chat-body .chat-list .chat-list-item .chat-object-info .online_status {
		width: .7rem;
		height: .7rem;
		position: absolute;
		box-shadow: 0px 0px 0px 2px rgba(255, 255, 255, 1.0);
		background: #57d785;
		border-radius: 50%;
		left: 3.2rem;
		bottom: 0;
	}

	/* 离线状态 */
	.chat-body .chat-list .chat-list-item .chat-object-info .online_status_off {
		width: .7rem;
		height: .7rem;
		position: absolute;
		box-shadow: 0px 0px 0px 2px rgba(255, 255, 255, 1.0);
		background: #d5d5d5;
		border-radius: 50%;
		left: 3.2rem;
		bottom: 0;
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
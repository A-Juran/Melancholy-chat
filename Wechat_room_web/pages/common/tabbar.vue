<template>
	<!-- 底部导航 -->
	<view class="tabbar" :style="{'padding-bottom': paddingBottomHeight + 'rpx'}">
		<view class="tabbar-item" v-for="(item, index) in list" :key="index" @click="tabbarChange(item.pagePath)">
			<image class="item-img1" :src="item.selectedIconPath" v-if="current == index"></image>
			<image class="item-img1" :src="item.iconPath" v-else></image>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			current: String
		},
		data() {
			return {
				paddingBottomHeight: 0, //苹果X以上手机底部适配高度
				list: [{
					"pagePath": "/pages/index/index",
					"iconPath": "/static/tabBar/message.png",
					"selectedIconPath": "/static/tabBar/message_Selected.png"
				}, {
					"pagePath": "/pages/list/list",
					"iconPath": "/static/tabBar/list.png",
					"selectedIconPath": "/static/tabBar/liset_Selected.png"
				}, {
					"pagePath": "/pages/search/Search",
					"iconPath": "/static/tabBar/search.png",
					"selectedIconPath": "/static/tabBar/search_Selected.png"
				}, {
					"pagePath": "/pages/me/me",
					"iconPath": "/static/tabBar/me.png",
					"selectedIconPath": "/static/tabBar/me_Selected.png"
				}]
			};
		},
		created() {
			let that = this;
			uni.getSystemInfo({
				success: function(res) {
					let model = ['X', 'XR', 'XS', '11', '12', '13', '14', '15'];
					model.forEach(item => {
						//适配iphoneX以上的底部，给tabbar一定高度的padding-bottom
						if (res.model.indexOf(item) != -1 && res.model.indexOf('iPhone') != -1) {
							that.paddingBottomHeight = 40;
						}
					})
				}
			});
		},
		watch: {

		},
		methods: {
			tabbarChange(path) {
				uni.switchTab({
					url: path
				})
			}
		}
	};
</script>

<style scoped>
	.tabbarActive {
		color: #79D5AD !important;
	}

	.tabbar {
		position: fixed;
		bottom: 1.5rem;
		width: 100%;
		display: flex;
		justify-content: space-around;
		align-items: center;
		height: 5rem;
		border-radius: 3rem 3rem 3rem 3rem;
		background-color: #fff;
		padding: 0 30rpx;
		z-index: 99;
		box-sizing: border-box;
	}

	.tabbar-item {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		height: 100rpx;
	}

	/* 选中前 */
	.item-img1 {
		width: 2rem;
		height: 2rem;
	}
</style>
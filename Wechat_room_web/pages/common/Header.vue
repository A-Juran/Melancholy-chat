<template>
	<view>
		<view class="status_bar">
			<!-- 这里是状态栏 -->
		</view>
		<view class="chat-tabbar">
			<view class="text">
				{{tabbarName}}
			</view>
			<view class="search" v-show="Inputstatus">
				<i class=""></i>
				<input type="text" @focus="searchFocus(true)" @blur="searchFocus(false)" :class="searchClass"
					placeholder="Search">
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			tabbarName: {
				type: String,
			},
			Inputstatus: {
				default: true,
				type: Boolean,
				require: false
			}
		},
		name: 'Header',
		data() {
			return {
				search: '',
				searchClass: "input"
			}
		},
		methods: {
			searchFocus(status) {
				if (status) {
					this.searchClass = "";
					this.searchClass = "searchInput";
					return;
				}
				this.searchClass = "input";
			},

		}
	}
</script>

<style>
	.chat-tabbar .search .searchInput {
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
</style>
<template>
	<view class="container">
		<view class="user-bk-wall">

		</view>
		<view class="current-user-info">
			<image src="http://q2.qlogo.cn/headimg_dl?dst_uin=21171326&spec=100" mode=""></image>
			<text>居然</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style>
	.user-bk-wall {
		width: 100%;
		height: 130px;
		/* background-color: black; */
		z-index: -999;
	}

	.current-user-info {
		width: 100%;
		text-align: center;
	}

	.current-user-info>image {
		display: block;
		width: 2.9917rem;
		height: 2.9917rem;
		border-radius: 50%;
		position: relative;
		margin: 0 auto;
		margin-bottom: 5px;
	}
	
	.current-user-info>text{
		font-size: 0.8rem;
	}
</style>

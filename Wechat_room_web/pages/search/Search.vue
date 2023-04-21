<template>
	<view>
		<Header tabbarName="search" :Inputstatus='false'/>
		<!-- 好友/群搜索 -->
		<view class="search-container">
			<!-- 好友\群搜索 -->
			<uni-search-bar radius='30'   placeholder="好友\群搜索" cancelButton="none" bgColor="#EEEEEE" @confirm="search" />
		</view>
	</view>
</template>

<script>
	import Header from '../common/Header.vue';
	export default {
		components:{
			Header
		},
		data() {
			return {
				Contacts:"Contacts"
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.search-container{
		width: 100%;
		padding: 1.6rem 1.6rem 0 1.6rem;
		box-sizing: border-box;
	}

</style>

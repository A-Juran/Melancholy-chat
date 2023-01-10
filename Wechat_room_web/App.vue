<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/* 隐藏所有页面的 navigationBar */
	.uni-app--showtopwindow uni-page-head {
		display: none;
	}
</style>

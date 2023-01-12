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
	html,body {
		font-size: 20.7px;
	}
	
	/* 解决当navigationStyle设为custom或titleNView设为false时，原生导航栏不显示 */
	.status_bar {
		height: var(--status-bar-height);
		width: 100%;
	}
	
	@media screen and (min-width:430px) {
		html,body {
			font-size: 23px;
		}
	}
</style>

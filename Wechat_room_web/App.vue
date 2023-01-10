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
	
	@media screen and (min-width:430px) {
		html,body {
			font-size: 23px;
		}
	}
</style>

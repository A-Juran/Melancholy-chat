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
	@import url('./static/fonts/iconfont.css');
	html,body {
		font-size: 10px;
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
	/* iconfont */
	@font-face {
	  font-family: 'iconfont';
	  src: url('./static/fonts/iconfont.woff2?t=1679052190641') format('woff2'),
	       url('./static/fonts/iconfont.woff?t=1679052190641') format('woff'),
	       url('./static/fonts/iconfont.ttf?t=1679052190641') format('truetype');
	}
	.iconfont {
	  font-family: "iconfont" !important;
	  font-size: 16px;
	  font-style: normal;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	}
	/* tabBar设置图标及字体大小 */
	/* .uni-tabbar .uni-tabbar__item .uni-tabbar__bd div.uni-tabbar__icon{
		width: 15px!important;
		height: 15px!important;
	}
	.uni-tabbar .uni-tabbar__item .uni-tabbar__bd div.uni-tabbar__label{
		font-size: 13px!important;
	} */
	
	.box-bg > * {
		font-size: 1rem;
	}
	
</style>

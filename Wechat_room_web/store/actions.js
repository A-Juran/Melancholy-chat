const actions = {
	loadCurrentUserInfo({commit}) {
		let currentUser = uni.getStorageSync("currentUser");
		//加载用户信息
		commit('SET_CURRENT_USER', currentUser)
	}
}

export default actions;
const mutations = {
	SET_USERNAME(state, newValue) {
		state.username = newValue;
	},
	SET_CURRENT_USER(state, userInfo) {
		uni.setStorageSync("currentUser",userInfo)
		state.currentUser = userInfo;
	},
	SET_TOKEN(state, token) {
		state.token = token;
	}
}
export default mutations;
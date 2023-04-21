const mutations = {
	SET_USERNAME(state, newValue) {
		state.username = newValue;
	},
	SET_TOKEN(state, token) {
		state.token = token;
	}
}
export default mutations;
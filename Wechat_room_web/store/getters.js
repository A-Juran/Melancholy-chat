const getters = {
	getCurrentUser: (state) => {
		return state.currentUser;
	},
	GET_TOKEN: (state) => {
		return state.token;
	}
}
export default getters;
const request = (config) => {
	const token = uni.getStorageSync("token");
	//配置公共请求地址
	const BASE_URL = "http://192.168.101.5:8080";
	//设置请求header
	let header = {};
	switch (config.method) {
		case 'GET':
			header = {
				'Content-Type': 'application/json;charset=UTF-8',
				'token': token
			}
			break;
		case 'POST':
			header = {
				'Content-Type': 'application/json;charset=UTF-8',
				'token': token
			}
			break;
		default:
			break;
	}
	return new Promise((resolve, reject) => {
		//请求提示。
		uni.showLoading({
			title: "请求中……"
		})
		//发送请求
		uni.request({
			url: BASE_URL + config.url,
			method: config.method || "GET",
			data: config.data.data || {},
			headers: header,
			success: res => {
				uni.hideLoading();
				//成功或存储token
				if (res.data.token) {
					uni.setStorageSync(res.data.token);
				}
				resolve(res.data);
			},
			fail: error => {
				uni.hideLoading();
				console.log(error);
				reject(error);
			}
		})

	})
}
export default request;
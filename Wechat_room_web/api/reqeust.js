const request = (config) => {
	//配置公共请求地址
	const BASE_URL = "http://localhost:8080";
	//设置请求header
	let header
	if (config.method == "post") {
		header['content-type'] = "application/json"
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
			data: config.data || {},
			headers: header,
			success: res => {
				uni.hideLoading();
				resolve(res.data);
			},
			fail: error => {
				reject(error);
			}

		})

	})
}

export default request;

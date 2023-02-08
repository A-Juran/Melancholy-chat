const request = (config) => {
	//配置公共请求地址
	const BASE_URL = "127.0.0.1:8080/";
	return new Promise((resolve,reject) =>{
		//请求提示。
		uni.showLoading({
			title:"请求中……"
		})
		//发送请求
		uni.request({
			url:BASE_URL + config.url,
			method:config.method || "GET",
			data:config.data || {},
			success : res =>{
				uni.hideLoading();
				resolve(res.data);
			},
			fail : error =>{
				reject(error);
			}
			
		})
		
	})
}

export default request;

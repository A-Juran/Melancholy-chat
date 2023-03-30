import request from '../reqeust.js'

//进行授权登录。
const userLogin = (data) =>{
	let resData = request({
		method: "POST", // 请求方式
		url: '/user/login', // 请求url
		data: data // 参数
	});
	return resData;
};


//获取验证码
const getCaptcha = () =>{
	let resData = request({
		method: "GET", // 请求方式
		url: '/user/captcha', // 请求url
	});
	return resData;
};


export default {
	userLogin,
	getCaptcha
}

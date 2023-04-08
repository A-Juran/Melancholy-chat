import request from '../reqeust.js'

//注册
const register = (data) =>{
	let resData = request({
		method: "POST", // 请求方式
		url: '/user/register', // 请求url
		data: data // 参数
	});
	return resData;
};

//登录
const userLogin = (data) =>{
	let resData = request({
		method: "POST", // 请求方式
		url: '/user/login', // 请求url
		data: data // 参数
	});
	return resData;
};


//获取验证码
const getCaptcha = (data) =>{
	let resData = request({
		method: "GET", // 请求方式
		url: '/user/captcha', // 请求url,
		data: data // 参数
	});
	return resData;
};


export default {
	register,
	userLogin,
	getCaptcha
}

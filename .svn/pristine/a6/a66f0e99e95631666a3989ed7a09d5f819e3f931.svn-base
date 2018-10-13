var regular = {};
//验证手机号码
regular.isPhone = function (phone){
  var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
  return reg.test(phone);
}

// 验证邮箱
regular.isEmail = function (email){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	return reg.test(email);
}
// 验证座机号
regular.isMobile = function isPhone(mobile) {
	var reg = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	return reg.test(mobile);
}
// 验证手机座机号
regular.isMobilePhone = function (phone) {
	var reg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
	return reg.test(phone);
}
// 验证密码
regular.isPass = function isPass(pass) {
	var reg = /^[a-zA-Z0-9]{6,12}$/;
	return reg.test(pass);
}
// 验证银行卡账号
regular.isbankCard = function (cart) {
	var reg = /^([1-9]{1})(\d{14}|\d{18})$/;
	return reg.test(cart);
}
// 验证身份证号
regular.isIdCart = function (cart) {
	var reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	return reg.test(cart);  
}

//验证图片格式
regular.isImg = function (img) {
	var reg = /\.(png|jpg|bmp|gif|jpeg|tif|PNG|JPG|BMP|GIF|JPEG|TIF)$/;
	return reg.test(img);  
}
//验证用户名（字母和数字，1-20）
regular.isName = function (name) {
	var reg = /^[a-zA-Z0-9]{1,20}$/;
	return reg.test(name);  
}
//验证商家名称（中英文数字，2-20）
regular.isSellerName = function (sellerName) {
	var reg = /^[\u4e00-\u9fa5a-zA-Z0-9]{2,20}$/;
	return reg.test(sellerName);  
}
//验证商家详细地址（去除特殊符号，<=50）
regular.isAddress = function (address) {
	var reg = /^[\u4e00-\u9fa5a-zA-Z0-9]{1,50}$/;
	return reg.test(address);  
}
//验证法人代表（中文，2-10）
regular.isPerson = function (person) {
	var reg = /[\u4e00-\u9fa5]{2,10}/;
	return reg.test(person);  
}




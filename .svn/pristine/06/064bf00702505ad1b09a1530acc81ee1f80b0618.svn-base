function isUserLogin() {
	var isLogin = false;
	var h5Id = window.java_h5id == null ? null : window.java_h5id.getH5ID();
	var requrl = domain+"/isuserlogin.html";
	if(h5Id !=null && h5Id.length > 0){
		requrl += "?sessionId="+h5Id ;
	}
	$.ajax({
		type:"POST",
		url:requrl,
		async : false,
		success:function(data){
			if(data.success){
				if (data.data) {
					isLogin = true;
				} else {
					isLogin = false;
				}
			}else{
				isLogin = false;
			}
		},
		error:function(){
			isLogin = false;
		}
	});
	return isLogin;
}

//验证手机号码
function isMobile(mobile){
    var re = /(^1[3|4|5|6|7|8|9][0-9]{9}$)/;
    return re.test(mobile);
}

// 验证邮箱
function isEmail(email){
	// 验证邮箱
	var flag = false;
	var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if(re.test(email)==false){
	}else{
	   flag = true;
	}
	return flag;
}

// 验证座机号
function isPhone(phone) {
	var re = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	return re.test(phone);
}
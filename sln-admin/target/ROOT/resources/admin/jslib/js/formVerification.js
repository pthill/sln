
$.extend($.fn.validatebox.defaults.rules, {
    idcard: {// 验证身份证
        validator: function (value) {
            return /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少6个字符.'
    },
    card:{
        validator:function (value) {
            return /^([1-9]{1})(\d{14}|\d{18})$/i.test(value);
        },
        message:'请输入正确的银行卡号'
    },
    length: { validator: function (value, param) {
        var len = $.trim(value).length;
        return len >= param[0] && len <= param[1];
    },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /\d{3}-\d{8}|\d{4}-\d{7,8}/i.test(value);
        },
        message: '格式不正确'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    qq: {// 验证QQ,从10000开始
        validator: function (value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    integer: {// 验证整数 可正负数
        validator: function (value) {
            return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },

    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    unnormal: {// 验证是否包含空格和非法字符
        validator: function (value) {
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    username: {// 验证用户名
        validator: function (value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{2,15}$/i.test(value);
        },
        message: '格式不正确（字母开头，允许3-16字节，允许字母数字下划线）'
    },
    faxno: {// 验证传真
        validator: function (value) {
            return /\d{3}-\d{8}|\d{4}-\{7,8}/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    ip: {// 验证IP地址
        validator: function (value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '可以是中文和英文'
    },
    code:{
        validator: function (value) {
            return  /^[\u4e00-\u9fa5a-zA-Z\d,，。]+/i.test(value);

        },
        message: '除特殊符号均可输入'
    },
    input:{
        validator: function (value) {
            return /[^\x00-\xff]/i.test(value) | /^\w+[\w\s]+\w+/i.test(value) | /^([+]?[0-9])|([-]?[0-9])+\d*/i.test(value);
        },
        message: '可以是中文和英文和数字'
    },
    date: {
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },
    msn: {
        validator: function (value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    },
    comparewith: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
            	var p=$("#"+param[0]).val();
                return parseInt(p)>= parseInt(value);
            } else {
                return false;
            }
        },
        message: '后者不能大于前者！'
    },
    
    compareto: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
            	console.log(value);
            	var p=$("#"+param[0]).val();
                return parseInt(p)<= parseInt(value);
            } else {
                return false;
            }
        },
        message: '后者不能小于前者！'
    },
    
    rangvalue: {//限制输入范围是0-5
    	validator: function (value){
            return /^[0-5]$/i.test(value);
        },
        message: '请输入0-5之间的数字！'
    }
});
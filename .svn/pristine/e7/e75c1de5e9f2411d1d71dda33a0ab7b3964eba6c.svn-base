package com.sln.core;

/**
 * TODO：sln 核心配置类，商城启动时首先配置此配置文件<br/>
 * 其中支付有打印的一些报文，用于替换正式账号之后调试使用，调试通过之后建议去掉<br/>
 * 配置常量类，主要配置有支付方式、短信通道、快递100、邮箱等等
 *                       
 * @Filename: SlnConfig.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class SlnConfig {

    // -----------邮箱配置start-------------
    /** 邮箱服务提供商host，根据使用的邮箱服务供应商对应填写 */
    public static final String MAIL_SERVER_HOST          = "113.96.232.106";//113.96.232.106//smtp.exmail.qq.com
    /** 邮箱服务提供商port，根据使用的邮箱服务供应商对应填写 */
    public static final String MAIL_SERVER_PORT          = "465";
    /** 发送邮件的邮箱地址 */
    public static final String SEND_EMAIL_NAME           = "mingxin.yang@slooong.com";
    /** 发送邮件的邮箱密码 */
    public static final String SEND_EMAIL_PASSWORD       = "YANGye822114";

    // -----------邮箱配置end---------------

    // -----------JAVA定时器执行配置start-------------
    //是web项目的定时器，主要对首页进行静态化更新的，可以根据自己的业务逻辑进行调整
    /** java定时器第一次执行时间（毫秒） */
    public static final long   TIME_DELAY                = 60000L;
    /** java定时器执行间隔时间（毫秒） */
    public static final long   TIME_PERIOD               = 600000L;

    public static final long   ACCESS_TOKEN_DELAY        = 0;
    public static final long   ACCESS_TOKEN_PERIOD       = 1000 * 60 * 60 * 2;
    // -----------JAVA定时器执行配置end---------------

    /** 快递100授权key */
    public static String       KUAIDI100_KEY             = "989f7ca4007d02b0";

    /** 国信互联短信发送URL(向短信供应商申请) 其中URL中包括用户名和密码 */
    public static String       SEND_SMS_URL              = "";
    
    ///////一卡通支付平台接口 start///////
    
    /** 系统注册码(支付平台分配，海核淘) */
    //public final static String ECARDPAY_THIRDSYSTEM = "mobileby";
    
    /** 系统注册码(支付平台分配，海核云谷) */
    public final static String ECARDPAY_THIRDSYSTEM = "hhyg";
    
    /** 订单类型 */
    public final static String ECARDPAY_ORDERTYPE_PC = "pc";
    
    public final static String ECARDPAY_ORDERTYPE_PHONE = "phone";

    /** 向量  iv向量位8字节的16进制0 */
    public static final byte[] ECARDPAY_DES_IV = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    
    /** 3des key 密钥(支付平台分配，海核淘) */
    //public static final String ECARDPAY_DES_KEY = "qJzGEh6hESZDVJeCnFPGuxzaiB7NLQM5";
    
    /** 3des key 密钥(支付平台分配，海核云谷) */
    public static final String ECARDPAY_DES_KEY = "YjAyNjZjN2FjNjAzNGFmOWE1Zjg0OTg4";
    
    /** 私钥(支付平台分配，海核淘) */
    //public static final String ECARDPAY_RSA_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOKXc9mFdLC49ln0zihEBFULK82NXo6DDad0T7W4jO3hICwjlKVbCncQvft6z74VZ3veUtfviHk1jGvNSj7Is9zUTR6ojDowA0+136YAVj5KwDYV2Mk33WI2iuov4crNgbMyffJDwFuI1a1fcvr3ayZNFxWyKZ542bb8R04B12ulAgMBAAECgYAwiEnq/DerJmK1j8acPz1CTds68p2fHpjNFg+Al5+vz7lJWvGanS5XpEFc3MgkKYd5s3vA/nAXrg1+hYDyg6BqM+lI6OHRZxPynaeQQeOiMDdTvR+pZ3b8uSx386riD1DM5d1oGRg9tMvJxQVCQU0bLrxViv2+KJf44dT3yqLJ3QJBAOwmsjpZi1BT+13IREjmdwEuTZRf7Ksffa1SvNyI+EoIpHZrM6V1w6vhRmJsENWe75qvJYy3hhem3i77O6z84oMCQQD1ow/fPTNxtM5qQKxMux3BEixp/j23ib+MIkoswmd+ARGtUEKqwnjXJWoXneXfWhMQTTJhBb5REwOEjOmv8IC3AkB09dlyMuVgHKgz07uWS6cHS7Ka2UOzoX4yePcXVzN6H3utNv02ZvRJzeJ5XsKbuwM7HqI/ZqogTsJejIoK7JkXAkAODxoudctG+8lArZju/1qxnT+rhWC0645qD+Bc9XeE77y6Rbi7G0xdTAfpeCEbCoXCzhhPE0wUSdlOsd4CMuq7AkBUD8WlEup0Ypa7oXTPGIdCOPZ+gxIWfEKBMAvi1paik+HIRfBdeaIPG1PyXrYDg8QWcZ3IrzMqLd9+jZ7XdYUF";
    
    /** 私钥(支付平台分配，海核云谷) */
    public static final String ECARDPAY_RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANqOF1QwotgF8cmXb9dfkXZRuXm0Tmp2to2daJvjCPmvFZSG5LGnZKpev5ZEAKy62Cei9AhvuGSYXxTuKYWnFn6wMEFU9TMh5VGs3aAjiJugEusTsfCbRRDYywPJ00Zb/gawpceep+lrb89Roye/5jhIGm27+VqXy3VLIpXUfOF5AgMBAAECgYBWv8GYxDlO34UqTDazWRamzSd+nEfn0DkDmvAzt/kPCiTZsHVD4rp9OWB4Z4ORDBTSHscYNigNncFX0HSgvUKayJpu/XlLF3ZFqbGDg7HLypsZJorh1NWIeOTzyzIZLJbQ+cPPgIPB0AqUnLNCTkkOEVBghoncKt+/nUhjfp+LUQJBAPhaoUUgFqmPqI8ArBrFeSpr6KGsniK6lUexx8/Fs+dcbUXOMeFQha9gufXQTK6Repkc3MXVE6DEwscr8/d0b8UCQQDhSJq4DkN2o2o7RFfclPbwDIOxn9Xuzy5qytUOQhTOSPQoW1s9eL+GmPPiyZ/ll9C5A6tdySj9vRwP+X+Oh3IlAkEAu36IhAxOr6JSKevjAq8U447l6LDODf/41VCFPfnO8RsZL/sUMzs8QgPvQIIKcjxIZzGgBwbvM494US+Als/j1QJARFWIkMHK1ua0p2uQlxkcWi4BmFRaSQjRFJWX0K5cr0HTxZYIc/n3ZnVorObpuHO3XRdu2JDW/ThqlbHhbjNJfQJBAOG+F8RNfxQD+/PCgG5qzM/UyYUhLJbgls0IxOlYzVx9Zkvfd0JI7Iscyv2LijQ83UCuHW7GoWW6i9W7VUamKyA=";
    
    /** 密钥算法  */
    public static final String ECARDPAY_KEY_ALGORITHM="DESede";
    
    /** RSA算法  */
    public static final String ECARDPAY_RSA_ALGORITHM="RSA";

    /** 加密/解密算法/工作模式/填充方式  */
    public static final String ECARDPAY_CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";
    
    /** 签名算法 */
    public static final String ECARDPAY_SIGN_ALGORITHMS = "SHA1WithRSA";
    
    /** 字符编码 项目统一使用UTF-8 */
    public static final String ENCODE = "UTF-8";
    
    ///////一卡通支付平台接口 end///////

    ///////阿里大于短信服务 start///////

    /** appkey */
    public final static String ALIDAYU_SMS_APPKEY                      = "24701838";
    /** secret */
    public final static String ALIDAYU_SMS_SECRET                      = "838c2f72fe378a20cb546405c4d42cb5";
    /** 短信签名 */
    public final static String ALIDAYU_SMS_SIGNNAME                    = "海核云谷";
    /** 福利积分新用户发送短信模板 */
    public final static String ALIDAYU_SMS_MEMBER_NEWWELFARESEND = "SMS_124210010";
    /** 福利积分老用户发送短信模板 */
    public final static String ALIDAYU_SMS_MEMBER_OLDWELFARESEND = "SMS_124205010";

    /** 用户注册短信模板 */
    //验证码${code}，您正在注册成为${product}用户，感谢您的支持！
    public final static String ALIDAYU_SMS_USER_REGISTER_TEMPLATECODE  = "SMS_9626518";
    /** 重置密码短信模板 */
    //您正在重置${product}的登录密码，新密码为${code}，感谢您的支持！
    public final static String ALIDAYU_SMS_RESET_PASSWORD_TEMPLATECODE = "SMS_122000002";
    /** 绑定手机 */
    //验证码${code}，您正在绑定${product}的手机号码，感谢您的支持！
    public final static String ALIDAYU_SMS_RESET_BINDING_TEMPLATECODE = "SMS_121945007";
    /** 忘记密码*/
    //验证码${code}(切勿将验证码告知他人)，请在页面中输入完成验证。感谢您的支持！
    public final static String ALIDAYU_SMS_FORGET_PASSWORD_TEMPLATECODE="SMS_124485012";

    
    
    ///////阿里大于短信服务 end///////

    ///////alipay start////////////
    /**
     * 支付宝，显示订单名称，PC和Mobile公用，一般设置商城的名称
     */
    public final static String ALIPAY_ALL_SUBJECT        = "sln";

    /**
     * HTML5 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
     */
    public static String       ALIPAY_MOBILE_PARTNER     = "2088121746953956";

    /**
     * HTML5 支付宝商户的私钥 HTML5支付采用RSA加密方式
     */
    public static String       ALIPAY_MOBILE_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKh6HkfwcsHIz69VUYPLbZS9z3IBkIAFL5BdMEvXybWDwzm2eFD6a7nvinqfzTyWLzBHvisBMyqR0a+zOBe3Q7Pdb6eNK5kYc4loIraSxn0PvqeduSa2KLtqyeg12ndA0+9RpMM0xAPhhVGFbsA1z1DCi1o+j1i3DgyiDXQPMuzjAgMBAAECgYACzKyuI1e00qJo1nEZUIsMmq7UxSPijLOCoZnI2NgYNQ1MazTKm66ok0toyDj/1bsJAVgunMF95phmZAL9meT9FCPP6tG/tMGVMAh7BdakaN8rc7ccw2Zo8CUbizGHXIazosF6g53Wl4dy97+fECBdUE82k04UpoCS1AkCUfoQUQJBAPXVk7ajaMqu46JF04d1UaiVWSBTDswKjFGrs0YnEkr2A3VBK6AxjQwzjVBj/pPV8VmiLp+ogRyBWnHidNO1nAkCQQCvcaFxDoeiVA7xbiboIlSn6B0mhbdpfU+kvl49Qm0s3Dza86Gqli0q4M/qJhHahwVzmb23qnAIR0U0M9iu0pSLAkBtKuYIsfJEJ3vgwN9ZhQi5M3E7wIaOp5R+ZCAdZBZkgXExrbogzkBTjcUQUQdpQRWHd1T7A4oqTWsLrcOxjDF5AkBvQndoNBetyPLlBr7jrYnUJW3/FQN4gYB1cEhhPvPvZAevrptSjAQ+0ezoh5YA6Gl7ov7eL9b2WQQ5E7wROff7AkA5R2sHOdqYAwfWMUxJzFIZ0ZYe5JsWt5vn7dfIJ3DLWIWllctHJl4i5Xg4+z+PdQ8Sv33O04qTBGHu89G/d7hy";

    /**
     * HTML5 支付宝的公钥，无需修改该值， HTML5支付采用RSA加密方式
     */
    public static String       ALIPAY_MOBILE_PUBLIC_KEY  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    /**
     * PC 支付宝：合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    public static String       ALIPAY_PC_PARTNER         = "2088121746953956";

    /**
     * PC 支付宝，收款支付宝账号，一般情况下收款账号就是签约账号
     */
    public static String       ALIPAY_PC_SELLER_EMAIL    = "wangpeng@sln.com";

    /**
     * PC 支付宝，商户的私钥，PC支付采用MD5 方式
     */
    public static String       ALIPAY_PC_KEY             = "kng63xz4dfhew7j61vzwul007ogzaplb";

    ///////alipay end///////////

    ////////银联支付 start 根据业务需求acp_sdk.properties中key 和 value 值进行替换/////////////
    /**
     * 银联支付，用户ID
     */
    public final static String UNIONPAY_MERID            = "777290058126056";

    /**
     * 银联支付，请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节  
     */
    public final static String UNIONPAY_REQRESERVED      = "海核云谷";

    /** 配置文件中的前台URL常量. 银联支付中对应的文件：acpsdk.frontTransUrl */
    public static final String SDK_FRONT_URL             = "https://101.231.204.80:5000/gateway/api/frontTransReq.do";
    /** 配置文件中的后台URL常量. 银联支付中对应的文件：acpsdk.backTransUrl*/
    public static final String SDK_BACK_URL              = "https://101.231.204.80:5000/gateway/api/backTransReq.do";
    /** 配置文件中的单笔交易查询URL常量. 银联支付中对应的文件：acpsdk.singleQueryUrl*/
    public static final String SDK_SIGNQ_URL             = "https://101.231.204.80:5000/gateway/api/queryTrans.do";
    /** 配置文件中的批量交易查询URL常量. 银联支付中对应的文件：acpsdk.batchQueryUrl*/
    public static final String SDK_BATQ_URL              = "acpsdk.batchQueryUrl";
    /** 配置文件中的批量交易URL常量. 银联支付中对应的文件：acpsdk.batchTransUrl*/
    public static final String SDK_BATTRANS_URL          = "https://101.231.204.80:5000/gateway/api/batchTransReq.do";
    /** 配置文件中的文件类交易URL常量. 银联支付中对应的文件：acpsdk.fileTransUrl*/
    public static final String SDK_FILETRANS_URL         = "https://101.231.204.80:9080/";
    /** 配置文件中的有卡交易URL常量. 银联支付中对应的文件：acpsdk.cardTransUrl*/
    public static final String SDK_CARD_URL              = "acpsdk.cardTransUrl";
    /** 配置文件中的app交易URL常量. 银联支付中对应的文件：acpsdk.appTransUrl*/
    public static final String SDK_APP_URL               = "acpsdk.appTransUrl";

    /** 配置文件中签名证书路径常量. 银联支付中对应的文件：acpsdk.signCert.path*/
    //    public static final String SDK_SIGNCERT_PATH         = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/700000000000001_acp.pfx";
    public static final String SDK_SIGNCERT_PATH         = "C:/Users/Administrator/Desktop/assets/700000000000001_acp.pfx";
    /** 配置文件中签名证书密码常量. 银联支付中对应的文件：acpsdk.signCert.pwd*/
    public static final String SDK_SIGNCERT_PWD          = "000000";
    /** 配置文件中签名证书类型常量. 银联支付中对应的文件：acpsdk.signCert.type*/
    public static final String SDK_SIGNCERT_TYPE         = "PKCS12";
    /** 配置文件中密码加密证书路径常量. 银联支付中对应的文件：acpsdk.encryptCert.path*/
    //    public static final String SDK_ENCRYPTCERT_PATH      = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/acp_test_enc.cer";
    public static final String SDK_ENCRYPTCERT_PATH      = "C:/Users/Administrator/Desktop/assets/acp_test_enc.cer";
    /** 配置文件中磁道加密证书路径常量. 银联支付中对应的文件：acpsdk.encryptTrackCert.path*/
    public static final String SDK_ENCRYPTTRACKCERT_PATH = "acpsdk.encryptTrackCert.path";
    /** 配置文件中验证签名证书目录常量. 银联支付中对应的文件：acpsdk.validateCert.dir*/
    //    public static final String SDK_VALIDATECERT_DIR      = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/";
    public static final String SDK_VALIDATECERT_DIR      = "C:/Users/Administrator/Desktop/assets/";

    /** 配置文件中是否加密cvn2常量. 银联支付中对应的文件：acpsdk.cvn2.enc*/
    public static final String SDK_CVN_ENC               = "acpsdk.cvn2.enc";
    /** 配置文件中是否加密cvn2有效期常量. 银联支付中对应的文件：acpsdk.date.enc*/
    public static final String SDK_DATE_ENC              = "acpsdk.date.enc";
    /** 配置文件中是否加密卡号常量. 银联支付中对应的文件：acpsdk.pan.enc*/
    public static final String SDK_PAN_ENC               = "acpsdk.pan.enc";
    /** 配置文件中证书使用模式 银联支付中对应的文件：true*/
    public static final String SDK_SINGLEMODE            = "true";

    ////////银联支付 end//////////////

    //////////微信支付 start////////////////////
    /**
     * 微信支付显示的订单名称
     */
    public static final String WEIXIN_ORDER_NAME         = "sln订单";

    //////////微信支付 end/////////////////////

    //------------------------短信发送相关参数bg--------------------------//
    /**
     * 默认固定线程数
     */
    public static final int    DEFAULT_RUN_THREAD_NUM    = 2;

    /**
     * 用户每日最多获取的短信验证码数量
     */
    public static final int    SMS_MAX_GIVEN_NUM         = 5;

    public static final String SMS_URL                   = "http://dx.ipyy.net/smsJson.aspx";
    public static final String SMS_VERIFY_CODE           = "您好，您已成功注册。您的验证码是@【海核云谷】";

    //------------------------短信发送相关参数ed--------------------------//

    //-------------------------h5微信支付 bg-------------------------//
    /** 开发者测试验证token */
    public static final String WXPAY_VALIDATE_TOKEN      = "sln";
    /** 微信支付商户号 */
    public static final String WXPAY_APPID               = "wx42a59bbf508e718d";
    /** 应用密钥 */
    public static final String WXPAY_APPSECRET           = "5db52c22bc7a3d115e2c43b668053fcf";
    /** 商户号 */
    public static final String WXPAY_PARTNER             = "1338338101";
    /** 商户支付密钥Key。审核通过后，在微信发送的邮件中查看 */
    public static final String WXPAY_PARTNER_KEY         = "HDJMTOwTMu9eijTA9A3rguLkzSJWU6Lp";
    /**网页授权地址，无需修改*/
    public static final String WXPAY_OAUTH2_URL          = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    /**获取网页授权access-token地址,无需修改*/
    public static final String WXPAY_OAUTH2_TOKEN        = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**创建订单,微信内部操作,无需修改*/
    public static final String WXPAY_CREATE_ORDER_URL    = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**微信访问token,无需修改*/
    public static final String WXPAY_ACCESS_TOKEN        = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    /**获取微信用户身份信息*/
    public static final String WXPAY_USER_INFO           = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    /**
     * 应用授权作用域,有两种:
     * 1、snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * 2、snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    public static final String WXPAY_SCOPE_BASE          = "snsapi_base";

    public static final String WXPAY_SCOPE_USERINFO      = "snsapi_userinfo";
    /**重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节*/
    public static final String WXPAY_STATE               = "ejs";
    /**支付成功后,由公共号发送此消息给用户*/
    public static final String WXPAY_PAYSUCCESS_MESSAGE  = "您的订单:[ORERID]已经支付成功！欢迎关注我们，祝您生活愉快SUFFIX";
    //-------------------------h5微信支付 ed-------------------------//
    
    
}

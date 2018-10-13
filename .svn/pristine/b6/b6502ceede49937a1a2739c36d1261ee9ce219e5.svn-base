package com.sln.core.pay.util;

import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;

import com.sln.core.SlnConfig;

/**
 * TripleDES(3DES)加密解密工具类
 * 对称加密算法：DESede/CBC/PKCS5Padding, iv向量位8字节的16进制0
 * c#端 和 Java端 保持一致的因素有三个 ：1.密钥 （kingsfykj) 2.偏移量 （IV） 3.输入
 * @Filename: TripleDES.java
 * @Version: 1.0
 * @Author: zhangmin
 * @Email: manction@qq.com
 *
 */
public class TripleDES {
    
    /**
     * 日志对象
     */
    private static Logger log = Logger.getLogger(TripleDES.class);
    
    /**
     * 设置向量
     */
    private static AlgorithmParameterSpec iv = new IvParameterSpec(SlnConfig.ECARDPAY_DES_IV);
    
    /**
     * 
     * 构建一个空的<code>TripleDES.java</code>
     */
    public TripleDES() {
    }
    
    /** 
     * base64编码 
     * @param input 
     * @return 
     * @throws Exception 
     */  
    public static String encodeBase64(byte[] input) throws Exception {  
        Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod = clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
        Object retObj = mainMethod.invoke(null, new Object[] { input });  
        return (String) retObj;  
    }
    
    /**
     * Base64解码
     * 
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] decodeBase64(String input) throws Exception{    
        Class<?> clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");    
        Method mainMethod= clazz.getMethod("decode", String.class);    
        mainMethod.setAccessible(true);    
         Object retObj=mainMethod.invoke(null, input);    
         return (byte[])retObj;    
    }
    
    /**
     * 3DES加密
     * @param data
     * @return
     */
     public static String encrypt(String data) {
         try {
             DESedeKeySpec keySpec = new DESedeKeySpec(decodeBase64(SlnConfig.ECARDPAY_DES_KEY));// 设置密钥参数
             SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SlnConfig.ECARDPAY_KEY_ALGORITHM);// 获得密钥工厂
             Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象
             Cipher enCipher = Cipher.getInstance(SlnConfig.ECARDPAY_CIPHER_ALGORITHM);// 得到加密对象Cipher
             enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
             byte[] pasByte = enCipher.doFinal(data.getBytes(SlnConfig.ENCODE));
             return encodeBase64(pasByte);
         } catch (Exception e) {
             log.warn(e.getMessage());
             return null;
         }
     }

     /**
      * 3DES解密
      * @param data
      * @return
      */
     public static String decrypt(String data){
         try {
             DESedeKeySpec keySpec = new DESedeKeySpec(decodeBase64(SlnConfig.ECARDPAY_DES_KEY));// 设置密钥参数
             SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SlnConfig.ECARDPAY_KEY_ALGORITHM);// 获得密钥工厂
             Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象
             Cipher deCipher = Cipher.getInstance(SlnConfig.ECARDPAY_CIPHER_ALGORITHM);
             deCipher.init(Cipher.DECRYPT_MODE, key, iv);
             byte[] pasByte = deCipher.doFinal(decodeBase64(data));
             return new String(pasByte, SlnConfig.ENCODE);
         } catch (Exception e) {
             log.warn(e.getMessage());
             return null;
         }
     }
    
    /**
     * 私钥签名
     * @param data
     * @return
     */
    public static String Sign(String data) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decodeBase64(SlnConfig.ECARDPAY_RSA_PRIVATE_KEY));
            KeyFactory keyf = KeyFactory.getInstance(SlnConfig.ECARDPAY_RSA_ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SlnConfig.ECARDPAY_SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(data.getBytes(SlnConfig.ENCODE));
            byte[] signed = signature.sign();
            return encodeBase64(signed);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            //Security.addProvider(new com.sun.crypto.provider.SunJCE());
            String test = "account=3&orderdesc=苹果7&thirdorderid=201611232047010&thirdsystem=inpay&toaccount=1000000&tranamt=111";
            System.out.println("加密前的字符：" + test);
            String encStr = TripleDES.encrypt(test);
            System.out.println("加密后的字符：" + encStr);
            System.out.println("解密后的字符：" + TripleDES.decrypt(encStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

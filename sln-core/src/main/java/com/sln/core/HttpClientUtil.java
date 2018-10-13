package com.sln.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.sln.core.exception.BusinessException;

/**
 * http请求工具类
 *                       
 * @Filename: HttpClientUtil.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class HttpClientUtil {
	

    public static String sendGet(String url) {
        HttpGet get = null;
        CloseableHttpResponse resp = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            get = new HttpGet(url);
            resp = client.execute(get);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                HttpEntity entity = resp.getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                return content;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resp != null) {
                    resp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post请求json
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String sendJsonPost(String url, String json) throws Exception {
        return sendPost(url, json, "application/x-www-form-urlencoded");
    }

    //    public static String sendJsonPost(String url, String content) {
    //        //        return sendPost(url, content, "application/json");
    //        return sendPost(url, content, "application/x-www-form-urlencoded");
    //    }

    public static String sendPost(String url, String content, String type) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            System.out.println(content);
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-type", type);
            StringEntity entity = new StringEntity(content, ContentType.create(type, "UTF-8"));
            // StringEntity entity = new StringEntity(content);
            post.setEntity(entity);
            resp = client.execute(post);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                String str = EntityUtils.toString(resp.getEntity(), "utf-8");
                return str;
            }
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null)
                    client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (resp != null)
                    resp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
	 * https协议Post请求url
	 * @param url url
	 * @param data 参数map
	 * @return
	 */
	public static String httpPostData(String url, Map<String, Object> map) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse resp = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			HttpPost httpPost = new HttpPost(url);

			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				StringEntity entity = new UrlEncodedFormEntity(list);
				httpPost.setEntity(entity);
			}
			resp = httpClient.execute(httpPost);
			if (resp != null) {
				HttpEntity resEntity = resp.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} finally {
			try {
				if (httpClient != null)
					httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (resp != null)
					resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * https协议Post请求url
	 * @param url 地址
	 * @param data 参数
	 * @return同步间的数据抓取
	 */
	public static String httpPostData(String url, String data) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse resp = null;
		String result = null;
		try {
			httpClient = SSLClient.createSSLClientDefault();
			HttpPost httpPost = new HttpPost(url);

			// 设置参数
			StringEntity entity = new StringEntity(data);
			httpPost.setEntity(entity);
			resp = httpClient.execute(httpPost);
			if (resp != null) {
				HttpEntity resEntity = resp.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "utf-8");
					System.out.println("返回结果：" + result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} finally {
			try {
				if (httpClient != null)
					httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (resp != null)
					resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * http post请求，使用Request请求
	 * @param url URL对象的地址参数
	 * @param postParam 请求参数拼接的字符串，格式如name=hulongqiao&age=18&sex=1
	 * @return
	 */
	public static String httpPostRequest(URL url, String postParam) {
		OutputStream outputstream = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URLConnection httpurlconnection = url.openConnection();
			httpurlconnection.setConnectTimeout(10 * 1000);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setUseCaches(false);
			OutputStreamWriter out = new OutputStreamWriter(httpurlconnection.getOutputStream(), "UTF-8");
			out.write(postParam);
			out.flush();
			
			in = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (outputstream != null) {
				try {
					outputstream.close();
				} catch (IOException e) {
					outputstream = null;
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					in = null;
				}
			}
		}
		return result.toString();
	}

	/**
	 * http post请求，使用Request请求
	 * @param url 字符串地址参数
	 * @param postParam 请求参数拼接的字符串，格式如name=hulongqiao&age=18&sex=1
	 * @return
	 */
	public static String httpPostRequest(String url, String data) {
		String result = null;
		try {
			URL requrl = new URL(url);
			return httpPostRequest(requrl, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

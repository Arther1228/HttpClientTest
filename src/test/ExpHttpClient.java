package test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class ExpHttpClient {
	/** 本地测试 */
	//private final static String HTTP_URL = ValidateUtils.getValue("HTTP_URL").toString().trim();
	private final static String HTTP_URL ="http://112.29.132.2:7003/ability/online/v1";
	/** 生产测试 */
	// private final static String HTTP_URL="http://XXXX:8080/demo/webservice/";

	/** 连接超时时间 */
	private final static int CONN_TIMEOUT = 50000;
	/** 请求超时时间 */
	private final static int REQUEST_TIMEOUT = 50000;

	private final static String RETURN_FOMATE = "json";// 支持json、xml

	/**
	 * 请求服务器入口
	 * 
	 * @param methodName
	 *            方法名
	 * @param map
	 *            上送输入参数集合
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String doPost(String methodName, OnlineRequest req) {
		String url = HTTP_URL + methodName;

		String result = null;
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT); // 连接超时 
																								// 设置
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Connection" , "Keep-Alive") ;
		postMethod.addRequestHeader("Content-Type","application/json");

		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, REQUEST_TIMEOUT); // post请求超时
																							// 设置
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		try {
			RequestEntity requestEntity = new StringRequestEntity(req.toString(),"application/json","UTF-8");
			postMethod.setRequestEntity(requestEntity);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {// 如果请求返回的不是成功，则进行处理
				return new String(postMethod.getResponseBodyAsString());
			} else {
				result = new String(postMethod.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 请求服务器入口
	 * 
	 * @param methodName
	 *            方法名
	 * @param map
	 *            上送输入参数集合
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String doPost2(String methodName, Map<String, String> map) {
		String url = HTTP_URL + methodName;

		String result = null;
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT); // 连接超时 
																								// 设置
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Connection" , "Keep-Alive") ;

		postMethod.setRequestBody(this.getPostParams(map));
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, REQUEST_TIMEOUT); // post请求超时
																							// 设置
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {// 如果请求返回的不是成功，则进行处理
				return new String(postMethod.getResponseBodyAsString());
			} else {
				result = new String(postMethod.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	private NameValuePair[] getPostParams(Map<String, String> map) {
		map.put("_type", RETURN_FOMATE);
		NameValuePair[] data = new NameValuePair[map.size()];
		Iterator<String> it = map.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			data[i] = new NameValuePair(key, value);
			i++;
		}
		return data;
	}
}
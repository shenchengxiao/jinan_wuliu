/**
 * 
 */
package com.manager.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@SuppressWarnings("deprecation")
public class URLConnUtil {

	private static final Logger logger = LoggerFactory.getLogger(URLConnUtil.class);

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final int DEFAULT_CONN_TIMEOUT = 10000;
	public static final int DEFAULT_SO_TIMEOUT = 10000;
	public static final boolean DEFAULT_IF_LOGGING = true;
	public static final String[] DEFAULT_EXCLUDE_PARAMS = null;
	private static final String EXCLUDE_SPLIT = "*";
	private static final String SPLIT = "##";

	public static final Map<String, String> EXCEPTION_MAP = new HashMap<String, String>();

	static {
		String UNSUPPORT_ENCODING_EXCEPTION = "E1";
		String CLIENT_PROTOCOL_EXCEPTION = "E2";
		String PARSE_EXCEPTION = "E3";
		String IO_EXCEPTION = "E4";
		String URI_SYNTAX_EXCEPTION = "E5";
		String EXCEPTION = "E6";
		
		EXCEPTION_MAP.put("UNSUPPORT_ENCODING_EXCEPTION",
				UNSUPPORT_ENCODING_EXCEPTION);
		EXCEPTION_MAP.put("CLIENT_PROTOCOL_EXCEPTION",
				CLIENT_PROTOCOL_EXCEPTION);
		EXCEPTION_MAP.put("PARSE_EXCEPTION", PARSE_EXCEPTION);
		EXCEPTION_MAP.put("IO_EXCEPTION", IO_EXCEPTION);
		EXCEPTION_MAP.put("URI_SYNTAX_EXCEPTION", URI_SYNTAX_EXCEPTION);
		EXCEPTION_MAP.put("EXCEPTION", EXCEPTION);
	}

	/**
	 * http get 无参数，默认连接时间，默认打日志
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, null, null, DEFAULT_CONN_TIMEOUT,
				DEFAULT_SO_TIMEOUT, DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
	}

	/**
	 * http get 无参数，默认打日志
	 * 
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doGet(String url, int connTimeOut, int soTimeOut) {
		return doGet(url, null, null, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
	}
	public static String doGet(String url, int connTimeOut, int soTimeOut,String contentEncode) {
		return doGet(url, null, null, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,contentEncode);
	}
	/**
	 * http get 有参数，默认打日志
	 * 
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doGet(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut) {
		return doGet(url, params, null, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
	}
	
	public static String doGet(String url, List<NameValuePair> params, List<NameValuePair> headers,
			int connTimeOut, int soTimeOut) {
		return doGet(url, params, headers, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
	}

    public static String doGet(String url, List<NameValuePair> headers) {
        return doGet(url, null, headers);
    }

    public static String doGet(String url, List<NameValuePair> params, List<NameValuePair> headers) {
        return doGet(url, params, headers, DEFAULT_CONN_TIMEOUT, DEFAULT_SO_TIMEOUT, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
    }
	
	/**
	 * http get 有参数，是否打日志
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @return
	 */
	public static String doGet(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging) {
		return doGet(url, params, null, connTimeOut, soTimeOut, ifLogging, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
	}
	
	public static String doGet(String url, List<NameValuePair> params, List<NameValuePair> headers,
			int connTimeOut, int soTimeOut, boolean ifLogging) {
		return doGet(url, params, headers, connTimeOut, soTimeOut, ifLogging, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8);
	}
	
	/**
	 * 获取图片资源
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static byte[] doGetImg(String url, int connTimeOut, int soTimeOut) {

		String queryURL = "";
		int statusCode = 200;
		HttpGet httpget = null;
		HttpEntity entity = null;
		byte[] byteArray = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {
			httpget = new HttpGet(url);
			queryURL = httpget.getURI().toString();
			// 发送请求
			HttpResponse httpresp = httpclient.execute(httpget);
			// 获取返回数据
			entity = httpresp.getEntity();
			byteArray = EntityUtils.toByteArray(entity);
			// 获取状态码
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			logger.info("queryURL: " + queryURL);
		} catch (UnsupportedEncodingException e) {
			logger.error("doGetImg error! statusCode: " + statusCode, e);
			return null;
		} catch (ClientProtocolException e) {
			logger.error("doGetImg error! statusCode: " + statusCode, e);
			return null;
		} catch (ParseException e) {
			logger.error("doGetImg error! statusCode: " + statusCode, e);
			return null;
		} catch (IOException e) {
			logger.error("doGetImg error! statusCode: " + statusCode, e);
			return null;
		} catch (Exception e) {
			logger.error("doGetImg error! statusCode: " + statusCode, e);
			return null;
		} finally {
			// 关闭资源
			if (httpget != null) {
				httpget.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return byteArray;
	}

	/**
	 * http get 有参数，可设置是否打印Log，日志过滤敏感参数
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @return
	 */
	public static String doGet(String url, List<NameValuePair> params, List<NameValuePair> headers,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String contentEncode) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpGet httpget = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {
			httpget = new HttpGet(url);
			
			// 设置请求头
			if (headers != null && headers.size() > 0) {
				for (Iterator<NameValuePair> it = headers.iterator(); it.hasNext();) {
					NameValuePair header = (NameValuePair) it.next();
					httpget.setHeader(header.getName(), header.getValue());
				}
			}
			
			// 设置参数we
			if (params != null && params.size() > 0) {
				httpget.setURI(new URI(httpget.getURI().toString()
						+ "?"
						+ EntityUtils.toString(new UrlEncodedFormEntity(params,
								HTTP.UTF_8))));
			}
			queryURL = httpget.getURI().toString();
			// 发送请求
			HttpResponse httpresp = httpclient.execute(httpget);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,contentEncode);
			entity.consumeContent();
			// 获取状态码
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (URISyntaxException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("URI_SYNTAX_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httpget != null) {
				httpget.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(GET, excludeSensitiveParams(excludeParams, queryURL),
				url, System.currentTimeMillis() - startTime, statusCode,
				respContent, ifLogging));
		return respContent;
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param header
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doGet(String url, List<NameValuePair> params,
			Map<String,String> header,int connTimeOut, int soTimeOut) {
		return doGet(url, params, header,connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS);
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param header
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @return
	 */
	public static String doGet(String url, List<NameValuePair> params,
			Map<String,String> header,int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpGet httpget = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {
			httpget = new HttpGet(url);
			// 设置参数
			if (params != null && params.size() > 0) {
				httpget.setURI(new URI(httpget.getURI().toString()
						+ "?"
						+ EntityUtils.toString(new UrlEncodedFormEntity(params,
								HTTP.UTF_8))));
			}
			// 设置header
			if(header != null && header.size() >0){
				for(String key : header.keySet()){
					httpget.addHeader(key, header.get(key));
				}
			}
			queryURL = httpget.getURI().toString();
			// 发送请求
			HttpResponse httpresp = httpclient.execute(httpget);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,HTTP.UTF_8);
			entity.consumeContent();
			// 获取状态码
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (URISyntaxException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("URI_SYNTAX_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httpget != null) {
				httpget.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(GET, excludeSensitiveParams(excludeParams, queryURL),
				url, System.currentTimeMillis() - startTime, statusCode,
				respContent, ifLogging));
		return respContent;
	}

	/**
	 * http post 无参数，默认连接时间，默认打日志
	 * 
	 * @param url
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url, new ArrayList<NameValuePair>(), null, DEFAULT_CONN_TIMEOUT,
				DEFAULT_SO_TIMEOUT, DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}

	/**
	 * http post 无参数，默认打日志
	 * 
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doPost(String url, int connTimeOut, int soTimeOut) {
		return doPost(url, new ArrayList<NameValuePair>(), null, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}

	/**
	 * http post 有参数，默认打日志
	 * 
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut) {
		return doPost(url, params, null, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	public static String doPost(String url, List<NameValuePair> params, List<NameValuePair> headers,
			int connTimeOut, int soTimeOut) {
		return doPost(url, params, headers, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	public static String doPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, String reqCharset,String resCharset) {
		return doPost(url, params, null, connTimeOut, soTimeOut, true, DEFAULT_EXCLUDE_PARAMS,reqCharset,resCharset);
	}
	
	/**
	 * http post 有参数，是否打日志
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @return
	 */
	public static String doPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging) {
		return doPost(url, params, null, connTimeOut, soTimeOut, ifLogging, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	public static String doPost(String url, List<NameValuePair> params, List<NameValuePair> headers,
			int connTimeOut, int soTimeOut, boolean ifLogging) {
		return doPost(url, params, headers, connTimeOut, soTimeOut, ifLogging, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	public static String doPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging ,String reqCharset,String resCharset) {
		return doPost(url, params, null, connTimeOut, soTimeOut, ifLogging, DEFAULT_EXCLUDE_PARAMS,reqCharset,resCharset);
	}

    public static String doPost(String url, List<NameValuePair> params) {
        return doPost(url, params, null, DEFAULT_CONN_TIMEOUT, DEFAULT_SO_TIMEOUT, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
    }

    public static String doPost(String url, List<NameValuePair> params, List<NameValuePair> headers) {
        return doPost(url, params, headers, DEFAULT_CONN_TIMEOUT, DEFAULT_SO_TIMEOUT, true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
    }

	/**
	 * http post 有参数，可设置是否打印Log，日志过滤敏感参数
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @return
	 */
	public static String doPost(String url, List<NameValuePair> params, List<NameValuePair> headers,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String reqCharset,String resCharset) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {
			httppost = new HttpPost(url);
			
			// 设置请求头
			if (headers != null && headers.size() > 0) {
				for (Iterator<NameValuePair> it = headers.iterator(); it.hasNext();) {
					NameValuePair header = (NameValuePair) it.next();
					httppost.setHeader(header.getName(), header.getValue());
				}
			}
			
			// 设置参数
			if (params != null && params.size() > 0) {
				httppost.setEntity(new UrlEncodedFormEntity(params, reqCharset));
			}
			
			queryURL = httppost.getURI().toString();
			
			if (ifHasParam(queryURL)) {
				queryURL = queryURL + prepareParamsForUrl(params, true);
			} else {
				queryURL = queryURL + "?" + prepareParamsForUrl(params, false);
			}
			
			HttpResponse httpresp = httpclient.execute(httppost);
			
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,resCharset);
			entity.consumeContent();
			
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(POST,
				excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
				statusCode, respContent, ifLogging));
		
		return respContent;
	}
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doPost(String url, String content, int connTimeOut, int soTimeOut) {
		return doPost(url, content, connTimeOut, soTimeOut, 
				true, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8 , HTTP.UTF_8);
	}
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @param reqCharset
	 * @param resCharset
	 * @return
	 */
	public static String doPost(String url, String content, int connTimeOut, int soTimeOut, 
			boolean ifLogging, String[] excludeParams,String reqCharset,String resCharset) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {
			httppost = new HttpPost(url);

			// 设置内容
			if (content != null && content.length() > 0) {
				httppost.setEntity(new StringEntity(content, reqCharset));
			}
			queryURL = httppost.getURI().toString();
			
			HttpResponse httpresp = httpclient.execute(httppost);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,resCharset);
			entity.consumeContent();
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(POST,
				excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
				statusCode, respContent, ifLogging));
		return respContent;
	}

	/**
	 * https get 无参数，默认连接时间，默认打日志
	 * 
	 * @param url
	 * @return
	 */
	public static String doHttpsGet(String url) {
		return doHttpsGet(url, null, DEFAULT_CONN_TIMEOUT,
				DEFAULT_SO_TIMEOUT, DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}

	/**
	 * https get 无参数，默认打日志
	 * 
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doHttpsGet(String url, int connTimeOut, int soTimeOut) {
		return doHttpsGet(url, null, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}

	/**
	 * https get 有参数，默认打日志
	 * 
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doHttpsGet(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut) {
		return doHttpsGet(url, params, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	public static String doHttpsGet(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut,String reqEncode,String resDecode) {
		return doHttpsGet(url, params, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,reqEncode,resDecode);
	}
	/**
	 * https get 有参数，是否打日志
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @return
	 */
	public static String doHttpsGet(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging) {
		return doHttpsGet(url, params, connTimeOut, soTimeOut,
				ifLogging, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param header
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doHttpsGet(String url, List<NameValuePair> params, 
			Map<String, String> header, int connTimeOut, int soTimeOut) {
		return doHttpsGet(url, params, header, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	/**
	 * 加入header
	 * @param url
	 * @param params
	 * @param header
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @param reqEncode
	 * @param resDecode
	 * @return
	 */
	public static String doHttpsGet(String url, List<NameValuePair> params, Map<String, String> header,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String reqEncode,String resDecode) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpGet httpget = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			socketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", socketFactory, 443);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			httpget = new HttpGet(url);
			// 设置参数
			if (params != null && params.size() > 0) {
				httpget.setURI(new URI(httpget.getURI().toString()
						+ "?"
						+ EntityUtils.toString(new UrlEncodedFormEntity(params,
								reqEncode))));
			}
			// 设置header
			if(header != null && header.size() >0){
				for(String key : header.keySet()){
					httpget.addHeader(key, header.get(key));
				}
			}
			queryURL = httpget.getURI().toString();
			// 发送请求
			HttpResponse httpresp = httpclient.execute(httpget);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,resDecode);
			entity.consumeContent();
			// 获取状态码
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (URISyntaxException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("URI_SYNTAX_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httpget != null) {
				httpget.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(GET, excludeSensitiveParams(excludeParams, queryURL),
				url, System.currentTimeMillis() - startTime, statusCode,
				respContent, ifLogging));
		return respContent;
	}
	
	/**
	 * https get 有参数，可设置是否打印Log，日志过滤敏感参数
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @return
	 */
	public static String doHttpsGet(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String reqEncode,String resDecode) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpGet httpget = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			socketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", socketFactory, 443);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			httpget = new HttpGet(url);
			// 设置参数
			if (params != null && params.size() > 0) {
				httpget.setURI(new URI(httpget.getURI().toString()
						+ "?"
						+ EntityUtils.toString(new UrlEncodedFormEntity(params,
								reqEncode))));
			}
			queryURL = httpget.getURI().toString();
			// 发送请求
			HttpResponse httpresp = httpclient.execute(httpget);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,resDecode);
			entity.consumeContent();
			// 获取状态码
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (URISyntaxException e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("URI_SYNTAX_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(GET,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httpget != null) {
				httpget.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(GET, excludeSensitiveParams(excludeParams, queryURL),
				url, System.currentTimeMillis() - startTime, statusCode,
				respContent, ifLogging));
		return respContent;
	}

	/**
	 * https post 无参数，默认连接时间，默认打日志
	 * 
	 * @param url
	 * @return
	 */
	public static String doHttpsPost(String url) {
		return doHttpsPost(url, null, DEFAULT_CONN_TIMEOUT,
				DEFAULT_SO_TIMEOUT, DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}

	/**
	 * https post 无参数，默认打日志
	 * 
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doHttpsPost(String url, int connTimeOut, int soTimeOut) {
		return doHttpsPost(url, null, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}

	/**
	 * https post 有参数，默认打日志
	 * 
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public static String doHttpsPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut) {
		return doHttpsPost(url, params, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	public static String doHttpsPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut,String reqCharset,String resCharset) {
		return doHttpsPost(url, params, connTimeOut, soTimeOut,
				DEFAULT_IF_LOGGING, DEFAULT_EXCLUDE_PARAMS,reqCharset,resCharset);
	}
	
	/**
	 * https post 有参数，是否打日志
	 * @param url
	 * @param params
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @return
	 */
	public static String doHttpsPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging) {
		return doHttpsPost(url, params, connTimeOut, soTimeOut,
				ifLogging, DEFAULT_EXCLUDE_PARAMS,HTTP.UTF_8,HTTP.UTF_8);
	}
	
	/**
	 * https post 有参数，可设置是否打印Log，日志过滤敏感参数
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @return
	 */
	public static String doHttpsPost(String url, HttpEntity entity,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String resCharset) {
		long startTime = System.currentTimeMillis();
		
		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity resEntity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			socketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", socketFactory, 443);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			httppost = new HttpPost(url);
			
			 // 设置参数
            httppost.setEntity(entity);
            queryURL = httppost.getURI().toString();
            HttpResponse httpresp = httpclient.execute(httppost);
			
			// 获取返回数据
            resEntity = httpresp.getEntity();
			respContent = EntityUtils.toString(resEntity,resCharset);
			resEntity.consumeContent();
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(POST,
				excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
				statusCode, respContent, ifLogging));
		return respContent;
	}
	
	/**
	 * 有Header
	 * @param url
	 * @param entity
	 * @param header
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param ifLogging
	 * @param excludeParams
	 * @param resCharset
	 * @return
	 */
	public static String doHttpsPost(String url, HttpEntity entity, Map<String,String> header,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String resCharset) {
		long startTime = System.currentTimeMillis();
		
		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity resEntity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			socketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", socketFactory, 443);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			httppost = new HttpPost(url);
			
			 // 设置参数
            httppost.setEntity(entity);
            // 设置header
 			if(header != null && header.size() >0){
 				for(String key : header.keySet()){
 					httppost.addHeader(key, header.get(key));
 				}
 			}
 			
            queryURL = httppost.getURI().toString();
            HttpResponse httpresp = httpclient.execute(httppost);
            
			// 获取返回数据
            resEntity = httpresp.getEntity();
			respContent = EntityUtils.toString(resEntity,resCharset);
			resEntity.consumeContent();
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(POST,
				excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
				statusCode, respContent, ifLogging));
		return respContent;
	}
	
	public static String doHttpsPost(String url, List<NameValuePair> params,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String reqCharset,String resCharset) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			socketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", socketFactory, 443);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			httppost = new HttpPost(url);
			// 设置参数
			if (params != null && params.size() > 0) {
				httppost.setEntity(new UrlEncodedFormEntity(params, reqCharset));
			}
			queryURL = httppost.getURI().toString();
			if (ifHasParam(queryURL)) {
				queryURL = queryURL + prepareParamsForUrl(params, true);
			} else {
				queryURL = queryURL + "?" + prepareParamsForUrl(params, false);
			}
			HttpResponse httpresp = httpclient.execute(httppost);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,resCharset);
			entity.consumeContent();
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(POST,
				excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
				statusCode, respContent, ifLogging));
		return respContent;
	}
	
	public static String doHttpsPost(String url, List<NameValuePair> params, Map<String,String> header,
			int connTimeOut, int soTimeOut, boolean ifLogging, String[] excludeParams,String reqCharset,String resCharset) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			socketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", socketFactory, 443);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			httppost = new HttpPost(url);
			// 设置参数
			if (params != null && params.size() > 0) {
				httppost.setEntity(new UrlEncodedFormEntity(params, reqCharset));
			}
			// 设置header
 			if(header != null && header.size() >0){
 				for(String key : header.keySet()){
 					httppost.addHeader(key, header.get(key));
 				}
 			}
			queryURL = httppost.getURI().toString();
			if (ifHasParam(queryURL)) {
				queryURL = queryURL + prepareParamsForUrl(params, true);
			} else {
				queryURL = queryURL + "?" + prepareParamsForUrl(params, false);
			}
			HttpResponse httpresp = httpclient.execute(httppost);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity,resCharset);
			entity.consumeContent();
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}
			
		} catch (UnsupportedEncodingException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("UNSUPPORT_ENCODING_EXCEPTION");
		} catch (ClientProtocolException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("CLIENT_PROTOCOL_EXCEPTION");
		} catch (ParseException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("PARSE_EXCEPTION");
		} catch (IOException e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("IO_EXCEPTION");
		} catch (Exception e) {
			logger.error(prepareHttpLogContent(POST,
					excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
					statusCode, respContent, ifLogging), e);
			return EXCEPTION_MAP.get("EXCEPTION");
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		logger.info(prepareHttpLogContent(POST,
				excludeSensitiveParams(excludeParams, queryURL), url, System.currentTimeMillis() - startTime,
				statusCode, respContent, ifLogging));
		return respContent;
	}
	
	/**
	 * 过滤敏感字符
	 * @param excludeParams
	 * @param queryURL
	 * @return
	 */
	private static String excludeSensitiveParams(String[] excludeParams,
			String queryURL) {
		if (excludeParams != null && excludeParams.length > 0) {
			try {
				for (int i = 0; i < excludeParams.length; i++) {
					String excludeParam = StringUtils.trimToEmpty(excludeParams[i]);
					if (StringUtils.isBlank(excludeParam)) {
						continue;
					}
					int a = queryURL.indexOf(excludeParam + "=");
					if (a > 0) {
						String excludeValue = "";						
						int b = queryURL.indexOf("&", a);						
						if (b > 0) {
							excludeValue = queryURL.substring(a + excludeParam.length() + 1, b);
							if (excludeValue != null && excludeValue.length() > 0) {
								excludeValue = excludeValue.replaceAll("\\S", EXCLUDE_SPLIT);
								queryURL = queryURL.substring(0, a + excludeParam.length() + 1) + excludeValue + queryURL.substring(b, queryURL.length());
							}
						} else {
							excludeValue = queryURL.substring(a + excludeParam.length() + 1, queryURL.length());
							if (excludeValue != null && excludeValue.length() > 0) {
								excludeValue = excludeValue.replaceAll("\\S", EXCLUDE_SPLIT);
								queryURL = queryURL.substring(0, a + excludeParam.length() + 1) + excludeValue;
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("excludeSensitiveParams error!", e);
				return queryURL;
			}
		}
		return queryURL;
	}
	
	
	
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			String value) {
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
			params.add(new BasicNameValuePair(name, value));
		}
	}

    public static void addParamWithBlank(List<NameValuePair> params, String name, String value) {
        params.add(new BasicNameValuePair(name, StringUtils.defaultIfBlank(value, StringUtils.EMPTY)));
    }

	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			int value) {
		addParam(params, name, String.valueOf(value));
	}
	
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			long value) {
		addParam(params, name, String.valueOf(value));
	}
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			boolean value) {
		addParam(params, name, String.valueOf(value));
	}
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			double value) {
		addParam(params, name, String.valueOf(value));
	}
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			float value) {
		addParam(params, name, String.valueOf(value));
	}
	
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			short value) {
		addParam(params, name, String.valueOf(value));
	}
	
	/**
	 * 增加参数值
	 * 
	 * @param params
	 * @param name
	 * @param value
	 */
	public static void addParam(List<NameValuePair> params, String name,
			byte value) {
		addParam(params, name, String.valueOf(value));
	}

	/**
	 * 新增Cookie
	 * 
	 * @param cookies
	 * @param name
	 * @param value
	 * @param domian
	 * @param outOfDay
	 */
	public static void addCookie(List<BasicClientCookie2> cookies, String name,
			String value, String domian, int outOfDay) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, outOfDay);
		BasicClientCookie2 cookie = new BasicClientCookie2(name, value);
		cookie.setDomain(domian);
		cookie.setExpiryDate(cal.getTime());
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setVersion(1);
		cookie.setAttribute("version", "1");
		cookie.setAttribute("domain", domian);
		cookies.add(cookie);
	}

	/**
	 * 组装需要打印的URL参数信息
	 * 
	 * @param params
	 * @return
	 */
	public static String prepareParamsForUrl(List<NameValuePair> params,
			boolean ifHasParam) {
		if (params != null && params.size() > 0) {
			StringBuffer paramsStr = new StringBuffer();
			for (NameValuePair param : params) {
				paramsStr.append("&").append(param.getName()).append("=")
						.append(param.getValue());
			}
			if (ifHasParam) {
				return StringUtils.isNotBlank(paramsStr.toString()) ? paramsStr
						.toString().trim() : "";
			} else {
				return StringUtils.isNotBlank(paramsStr.toString()) ? paramsStr
						.toString().trim().substring(1) : "";
			}
		}
		return "";
	}

	/**
	 * 是否url后面添加了参数
	 * 
	 * @param url
	 * @return
	 */
	public static boolean ifHasParam(String url) {
		if (StringUtils.isNotBlank(url)) {
			if (url.indexOf("?") > 0) {
				return true;
			}
		}
		return false;
	}

    public static String isRequestError(String content) {
        Iterator<Map.Entry<String, String>> iterator = EXCEPTION_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            if(item.getValue().equals(content)) {
                return item.getKey();
            }

        }

        return null;
    }
	
	/**
	 * 组装请求参数
	 * @param params
	 * @param method
	 * @return
	 */
	public static String prepareParams(Map<String, String> params, String method) {
		StringBuffer paramsSB = new StringBuffer("");
		if (params != null && params.size() > 0) {
			String key = "";
			String value = "";
			for (Iterator<String> it = params.keySet().iterator(); it.hasNext(); ) {
				key = it.next();
				value = params.get(key);
				if ("GET".equalsIgnoreCase(method)) {
					if (paramsSB.length() == 0) {
						paramsSB.append("?").append(key).append("=").append(value);
					} else {
						paramsSB.append("&").append(key).append("=").append(value);
					}
				} else if ("POST".equalsIgnoreCase(method)) {
					if (paramsSB.length() == 0) {
						paramsSB.append(key).append("=").append(value);
					} else {
						paramsSB.append("&").append(key).append("=").append(value);
					}
				}
			}
			return paramsSB.toString();
		} else {
			return paramsSB.toString();
		}
	}
	
	public static String prepareHttpLogContent(String method, String queryURL, String url,
			long responseTime, int httpStatusCode, String result,
			boolean ifLogging) {

		StringBuffer sb = new StringBuffer();
		sb.append(SPLIT).append(method).append(SPLIT).append(responseTime).append("ms").append(SPLIT).append(httpStatusCode);
		if (ifLogging) {
			sb.append(SPLIT).append(queryURL).append(SPLIT).append(escapeEmpty(result));
		} else {
			sb.append(SPLIT).append(url);
		}
		return sb.toString();
	}
	
	public static String escapeEmpty(String str) {
		if (StringUtils.isNotEmpty(str)) {
			str = str.replace("\r", "");
			str = str.replace("\n", "");
			str = str.replace("\t", "");
		}
		return str;
	}

	public static String doPost(String url, String content,List<NameValuePair> headers) {
		return doPost(url, content, headers, 3000, 3000, true, null, HTTP.UTF_8, HTTP.UTF_8);
	}

	public static String doPost(String url, String content,List<NameValuePair> headers, int connTimeOut, int soTimeOut,
								boolean ifLogging, String[] excludeParams, String reqCharset, String resCharset) {
		long startTime = System.currentTimeMillis();

		String queryURL = "";
		int statusCode = 200;
		HttpPost httppost = null;
		String respContent = null;
		HttpEntity entity = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpParams httpparams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpparams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpparams, soTimeOut);

		try {
			httppost = new HttpPost(url);

			// 设置请求头
			if (headers != null && headers.size() > 0) {
				for (Iterator<NameValuePair> it = headers.iterator(); it.hasNext();) {
					NameValuePair header = (NameValuePair) it.next();
					httppost.setHeader(header.getName(), header.getValue());
				}
			}


			// 设置内容
			if (content != null && content.length() > 0) {
				httppost.setEntity(new StringEntity(content, reqCharset));
			}
			queryURL = httppost.getURI().toString();

			HttpResponse httpresp = httpclient.execute(httppost);
			// 获取返回数据
			entity = httpresp.getEntity();
			respContent = EntityUtils.toString(entity, resCharset);
			entity.consumeContent();
			StatusLine sl = httpresp.getStatusLine();
			if (sl != null) {
				statusCode = sl.getStatusCode();
			}

		} catch (UnsupportedEncodingException e) {
			logger.error("请求 url:"+queryURL, e);
		} catch (ClientProtocolException e) {
			logger.error("请求 url:"+queryURL, e);
		} catch (ParseException e) {
			logger.error("请求 url:"+queryURL, e);
		} catch (IOException e) {
			logger.error("请求 url:"+queryURL, e);
		} catch (Throwable e) {
			logger.error("请求 url:"+queryURL, e);
		} finally {
			// 关闭资源
			if (httppost != null) {
				httppost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return respContent;
	}

	public static void main(String[] args) {
		URLConnUtil.doGetImg("http://wenwenwopic.b0.upaiyun.com/pic/2013-04-30/13e5aeaacdb_082734_136732538528920120811_144043.jpg!small", 10000, 10000);
	}
	
}

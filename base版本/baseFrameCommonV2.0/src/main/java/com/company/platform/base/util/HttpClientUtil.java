package com.company.platform.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

/**
 * http客户端工具类
 */
public class HttpClientUtil {
	
	private static Logger log = Logger.getLogger(HttpClientUtil.class.getName());
	
	public static final String GET = "get";
	
	public static final String POST = "post";
	
	public static String httpRequest(String url, Map<String, String> data, String method) throws Exception {
		String res = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		if("post".equals(method)) {
			HttpPost httppost = new HttpPost(url);
			
			if(data != null && !data.isEmpty()) {
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramHandle(data), "UTF-8");
				
				httppost.setEntity(uefEntity);
			}
			
			CloseableHttpResponse response = httpclient.execute(httppost);

			HttpEntity entity = response.getEntity();
			
			res = EntityUtils.toString(entity, "UTF-8");
			
			httppost.releaseConnection();
		} else {
			StringBuilder sb = new StringBuilder("");

	        if (null != data && !data.isEmpty()) {
	            int i = 0;
	            for (String key : data.keySet()) {
	                if (i == 0) {
	                    sb.append("?");
	                } else {
	                    sb.append("&");
	                }
	                sb.append(key).append("=").append(data.get(key));
	                i++;
	            }
	        }
			
			HttpGet httpget = new HttpGet(url + sb.toString());
			
			CloseableHttpResponse response = httpclient.execute(httpget);
			
			HttpEntity entity = response.getEntity();
			
			res = EntityUtils.toString(entity, "UTF-8");
			
			httpget.releaseConnection();
		}
		
		return res;
	}
	
	public static String httpsRequest(String url, Map<String, String> data, String method) throws Exception {
		String res = null;

		if("post".equals(method)) {
			 CloseableHttpClient httpClient = createSSLClientDefault();
		     HttpPost post = new HttpPost(url);

		     CloseableHttpResponse response = null;

		     if (null != data && !data.isEmpty()) {		            
		            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramHandle(data), "UTF-8");;
		            post.setEntity(uefEntity);
		     }

		     try {
		            response = httpClient.execute(post);

		            if (response.getStatusLine().getStatusCode() == 200) {
		                HttpEntity entity = response.getEntity();
		                if (null != entity) {
		                    res = EntityUtils.toString(entity, "UTF-8");
		                }
		            }
		     } catch (IOException ex) {
		    	 log.error(ex);
		     } finally {
		     	if (null != response) {
		                try {
		                    EntityUtils.consume(response.getEntity());
		                } catch (IOException ex) {
		                	log.error(ex);
		                }
		     	}
		     }
		} else {
			StringBuilder sb = new StringBuilder("");

	        if (null != data && !data.isEmpty()) {
	            int i = 0;
	            for (String key : data.keySet()) {
	                if (i == 0) {
	                    sb.append("?");
	                } else {
	                    sb.append("&");
	                }
	                sb.append(key).append("=").append(data.get(key));
	                i++;
	            }
	        }

	        CloseableHttpClient httpClient = createSSLClientDefault();

	        CloseableHttpResponse response = null;
	        
	        log.info(url + sb.toString());
	        HttpGet get = new HttpGet(url + sb.toString());

	        try {
	            response = httpClient.execute(get);

	            if (response.getStatusLine().getStatusCode() == 200) {
	                HttpEntity entity = response.getEntity();
	                if (null != entity) {
	                    res = EntityUtils.toString(entity, "UTF-8");
	                }
	            }
	        } catch (IOException ex) {
	        	log.error(ex);
	        } finally {
	            if (null != response) {
	                try {
	                    EntityUtils.consume(response.getEntity());
	                } catch (IOException ex) {
	                	log.error(ex);
	                }
	            }
	        }
		}

		return res;
	}
	
	
	
	private static List<NameValuePair> paramHandle(Map<String, String> param) {
		Set<String> keyNames = param.keySet();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for(String keyName : keyNames) {
			BasicNameValuePair pair = new BasicNameValuePair(keyName, param.get(keyName));
			list.add(pair);
		}
		return list;
	}
	
	private static CloseableHttpClient createSSLClientDefault() {

        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String string){
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyStoreException ex) {
        	log.error(ex);
        } catch (NoSuchAlgorithmException ex) {
        	log.error(ex);
        } catch (KeyManagementException ex) {
        	log.error(ex);
        }

        return HttpClients.createDefault();
    }

}


package com.sbicolending.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;



public class HttpClient {

	private static final Logger LOGGER = BaseLogger.getLogger(HttpClient.class);

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String responseBody = null;
		int value = 0;
		char valueChar;

		try {

			br = new BufferedReader(new InputStreamReader(is));

			while ((value = br.read()) != -1) {
				valueChar = (char) value;
				sb.append(valueChar);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		responseBody = sb.toString();

		return sb.toString();
	}



	public static Map handlePOSTWebRequest(String urlOverHttps, String requestData, Map<String, String> headers)
			throws Exception {
		int responseCode;
		CloseableHttpClient httpClient = null;
		String responseString = null;
		Map rtnMap = new HashMap();

		try {
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] certificate, String authType) {
					return true;
				}

			};
			SSLContext sslContext = SSLContexts.custom().useTLS().build();

			SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext,
					new String[] { "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
			LOGGER.info("#################URL#######################" + urlOverHttps);
			LOGGER.info("#################requestData#######################" + requestData);
			HttpPost httpPost = new HttpPost(urlOverHttps);
			StringEntity entity = new StringEntity(requestData);
			httpPost.setEntity(entity);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}

			HttpResponse response = httpClient.execute(httpPost);

			responseCode = response.getStatusLine().getStatusCode();
			LOGGER.info("#################responseCode#######################" + responseCode
					+ "#################URL#######################" + urlOverHttps);

			responseString = getStringFromInputStream(response.getEntity().getContent());

			if (responseCode == 200 || responseCode == 201) {
				LOGGER.info("response code OK: " + responseCode);

				rtnMap.put("responseCode", responseCode + "");
				rtnMap.put("responseString", responseString);
			}

			if (responseCode != 200 && responseCode != 201) {

				LOGGER.error("response code not OK: " + responseCode);
				LOGGER.error("response message not OK: " + responseString);

				rtnMap.put("responseCode", responseCode + "");
				rtnMap.put("responseString", responseString);
			}
		} catch (NoResponseException exception) {
			LOGGER.error("##################NoResponseException##################");
			LOGGER.error("Exception msg: ", exception);
			throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
		} catch (IOException exception) {
			LOGGER.error("##################IOException##################");
			LOGGER.error("Exception msg: ", exception);
			throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
		} catch (Exception exception) {
			LOGGER.error("##################Exception##################");
			LOGGER.error("Exception msg: ", exception);
			throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return rtnMap;
	}


	public static Map handleGETWebRequest(String urlOverHttps, Map<String, String> headers) throws Exception {
		int responseCode;
		CloseableHttpClient httpClient = null;
		String responseString = null;
		Map rtnMap = new HashMap();

		try {
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] certificate, String authType) {
					return true;
				}

			};
			SSLContext sslContext = SSLContexts.custom().useTLS().build();

			SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext,
					new String[] { "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
			LOGGER.info("#################URL#######################" + urlOverHttps);
			HttpGet httpGet = new HttpGet(urlOverHttps);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGet.setHeader(entry.getKey(), entry.getValue());
			}

			HttpResponse response = httpClient.execute(httpGet);
			responseCode = response.getStatusLine().getStatusCode();
			LOGGER.info("#################responseCode#######################" + responseCode
					+ "#################URL#######################" + urlOverHttps);

			responseString = getStringFromInputStream(response.getEntity().getContent());

			if (responseCode == 200) {

				rtnMap.put("responseCode", responseCode + "");
				rtnMap.put("responseString", responseString);
			}
			if (responseCode != 200 && responseCode != 202) {
				LOGGER.error("response code not OK: " + responseCode);
				LOGGER.error("response message not OK: " + responseString);

				rtnMap.put("responseCode", responseCode + "");
				rtnMap.put("responseString", responseString);
			}
		} catch (NoResponseException exception) {
			LOGGER.error("##################NoResponseException##################");
			LOGGER.error(exception.getMessage());
			throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
		} catch (IOException exception) {
			LOGGER.error("##################IOException##################");
			LOGGER.error(exception.getMessage());
			throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
		} catch (Exception exception) {
			LOGGER.error("##################Exception##################");
			LOGGER.error(exception.getMessage());
			throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}

		return rtnMap;
	}



	public static Map<String, String> handlePUTWebRequest(String insertCsvUrl,String requestData, Map<String, String> header) throws Exception {
		int responseCode;
		CloseableHttpClient httpClient = null;
		String responseString = null;
		Map rtnMap = new HashMap();

		try {
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] certificate, String authType) {
					return true;
				}
			};
			SSLContext sslContext = SSLContexts.custom().useTLS().build();

			SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext,
					new String[] { "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
			LOGGER.info("#################URL#######################" + insertCsvUrl);
			HttpPut httpPut = new HttpPut(insertCsvUrl);

			StringEntity entity = new StringEntity(requestData);
			httpPut.setEntity(entity);

			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPut.setHeader(entry.getKey(), entry.getValue());
			}

			HttpResponse response = httpClient.execute(httpPut);
			responseCode = response.getStatusLine().getStatusCode();
			responseString = getStringFromInputStream(response.getEntity().getContent());

			if (responseCode == 200) {

				rtnMap.put("responseCode", responseCode + "");
				rtnMap.put("responseString", responseString);
			}
			if (responseCode != 200 && responseCode != 202) {
				LOGGER.error("response code not OK: " + responseCode);
				LOGGER.error("response message not OK: " + responseString);

				rtnMap.put("responseCode", responseCode + "");
				rtnMap.put("responseString", responseString);
			}
		} catch (NoResponseException exception) {
			LOGGER.error("##################NoResponseException##################");
			LOGGER.error("Exception msg: ", exception);
			throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
		} catch (IOException exception) {
			LOGGER.error("##################IOException##################");
			LOGGER.error("Exception msg: ", exception);
			throw new NoResponseException("00", "Url: " + insertCsvUrl + " " + exception.getMessage());
		} catch (Exception exception) {
			LOGGER.error("##################Exception##################");
			LOGGER.error("Exception msg: ", exception);
			throw new NoResponseException("00", "Url: " + insertCsvUrl + " " + exception.getMessage());
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}

		return rtnMap;
	}

}

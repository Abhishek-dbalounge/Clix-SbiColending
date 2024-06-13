package com.sbicolending.utils;

import io.micrometer.common.util.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoHttpClient {

    private static final Logger LOGGER = BaseLogger.getLogger(HttpClient.class);
    private static final String POST_METHOD = "POST";
    private static final String GET_METHOD = "GET";

    @SuppressWarnings("deprecation")
    public static Map handleWebRequestApplicationXML(String urlOverHttps, String requestType, String requestData)
            throws Exception {
        int responseCode;
        DefaultHttpClient httpClient = null;
        String responseString = null;
        Map rtnMap = new HashMap();

        try {

            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] certificate, String authType) {
                    return true;
                }

            };
            @SuppressWarnings("deprecation")
            SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            @SuppressWarnings("deprecation")
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, (SchemeSocketFactory) sf));
            ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);
            httpClient = new DefaultHttpClient(ccm);

            // String urlOverHttps = "https://103.14.161.142:443/requestDetail";
            HttpPost httpPost = new HttpPost(urlOverHttps);

            LOGGER.info("#################URL#######################" + urlOverHttps);
            LOGGER.info("#################requestData#######################" + requestData);
            StringEntity entity = new StringEntity(requestData);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/xml");
            httpPost.setHeader("Content-Type", "application/xml");
            /*
             * httpPost.setHeader("Accept", "text/plain");
             * httpPost.setHeader("Content-Type","text/plain");
             */
            HttpResponse response = httpClient.execute(httpPost);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
            LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + urlOverHttps);
            }

        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        // return responseString;
        return rtnMap;
    }

    /**
     * @param serverURL
     * @param jsonString
     * @param username
     * @param password
     * @return HashMap
     * @throws Exception
     */

    @SuppressWarnings("deprecation")
    public static Map handleHttpRequestJSON(String serverURL, String jsonString, String username, String password)
            throws Exception {
        int responseCode = 0;
        HttpPost request = null;
        DefaultHttpClient httpClient = null;
        HttpResponse response = null;
        String responseString = null;
        Map rtnMap = new HashMap();
        try {

            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] certificate, String authType) {
                    return true;
                }

            };
            @SuppressWarnings("deprecation")
            SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            @SuppressWarnings("deprecation")
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, (SchemeSocketFactory) sf));
            ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);
            httpClient = new DefaultHttpClient(ccm);

            request = new HttpPost(serverURL);
            LOGGER.info("#################URL#######################" + serverURL);
            LOGGER.info("#################requestData#######################" + jsonString);

            byte[] encodedPassword = (username + ":" + password).getBytes();

            // BASE64Encoder encoder = new BASE64Encoder();
            // request.setHeader("Authorization", "Basic " +
            // encoder.encode(encodedPassword));
            request.setHeader("Authorization", "Basic " + Base64.encodeBase64String(encodedPassword));

            request.setHeader("content-type", "application/json");
            request.setHeader("Accept", "application/json");
            StringEntity params = new StringEntity(jsonString);
            request.setEntity(params);
            response = httpClient.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());
                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }

        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + serverURL + " " + exception.getMessage());

        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + serverURL + " " + exception.getMessage());
        } finally {

            if (request != null) {
                request.releaseConnection();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }

        return rtnMap;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String responseBody = null;
        int value = 0;
        char valueChar;

        try {

            br = new BufferedReader(new InputStreamReader(is));

            while ((value = br.read()) != -1) {
                // converts int to character
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
        /*
         * LOGGER.
         * debug("################### Response Received - START1 ###################");
         * LOGGER.debug("|||" + responseBody + "|||"); LOGGER.
         * debug("################### Response Received - END1   ###################");
         */

        return sb.toString();

    }

    public static Map handleWebRequest(String urlOverHttps, String requestType, String requestData, String contentType)
            throws Exception {
        int responseCode;
        DefaultHttpClient httpClient = null;
        String responseString = null;
        Map rtnMap = new HashMap();

        try {

            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] certificate, String authType) {
                    return true;
                }

            };
            @SuppressWarnings("deprecation")
            SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            @SuppressWarnings("deprecation")
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, (SchemeSocketFactory) sf));
            ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);
            httpClient = new DefaultHttpClient(ccm);


            HttpPost httpPost = new HttpPost(urlOverHttps);

            LOGGER.info("#################URL#######################" + urlOverHttps);
            LOGGER.info("#################requestData#######################" + requestData);
            StringEntity entity = new StringEntity(requestData);
            httpPost.setEntity(entity);
//		    		    httpPost.setHeader("Accept", "application/xml");
//		    		    httpPost.setHeader("Content-Type","application/xml");
            httpPost.setHeader("Accept", contentType);
            httpPost.setHeader("Content-Type", contentType);
            HttpResponse response = httpClient.execute(httpPost);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode
                    + "#################URL#######################" + urlOverHttps);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
            LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + urlOverHttps);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        // return responseString;
        return rtnMap;
    }

    /**
     * Handle web services request
     *
     * @param serverURL   Web service URL
     * @param requestType Get/Post
     * @param requestData Request data in case of Post request else null
     * @return
     * @throws Exception
     */
    public static String handleHttpRequest(String serverURL, String requestType, String requestData) throws Exception {
        int responseCode;
        InputStream inputStream = null;
        String serverResponseMsg = null;
        LOGGER.info("##################serverURL:" + serverURL);
        LOGGER.info("##################requestType:" + requestType);
        LOGGER.info("##################requestData:" + requestData);
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        URLConnection conn = null;
        try {
            url = new URL(serverURL);
            conn = url.openConnection();
            LOGGER.info("##################connectionOpened##################");
            httpURLConnection = (HttpURLConnection) conn;
            httpURLConnection.setDoInput(true);

            if (requestType.equalsIgnoreCase(POST_METHOD)) {

                httpURLConnection.setRequestMethod(POST_METHOD);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/xml");
                httpURLConnection.setRequestProperty("Accept", "application/xml");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outStreamWriter = new OutputStreamWriter(outputStream);
                outStreamWriter.write(requestData);
                outStreamWriter.flush();
                outStreamWriter.close();
            } else {

                httpURLConnection.setRequestMethod(GET_METHOD);
                httpURLConnection.connect();
            }

            responseCode = httpURLConnection.getResponseCode();
            LOGGER.info("##################responseCode##################" + responseCode);
            if (responseCode == 200) {

                inputStream = httpURLConnection.getInputStream();
                serverResponseMsg = readInputStream(inputStream);
            }
            if (StringUtils.isBlank(serverResponseMsg)) {
                LOGGER.error("response code not OK: " + responseCode);
                // throw new NoResponseException(responseCode+"", "response code not OK or Null:
                // "+ responseCode +"Url: "+ url);
            }
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            // throw new NoResponseException(exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            // throw new NoResponseException(exception.getMessage());
        }
        LOGGER.info("##################serverResponseMsg###########:" + serverResponseMsg);
        return serverResponseMsg;
    }

    /**
     * Read InputStream
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    private static String readInputStream(InputStream inputStream) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String subMessage = null;
        try {
            InputStreamReader inStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inStreamReader);

            while ((subMessage = bufferReader.readLine()) != null) {
                stringBuilder.append(subMessage);
            }
        } catch (IOException exception) {

        } catch (Exception exception) {

        }
        return stringBuilder.toString();
    }

    public static Map handleWebRequestApplicationXMLThroughProxy(String urlOverHttps, String requestData,
                                                                 String proxyHost, int proxyPort) throws Exception {
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
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
            HttpPost httpPost = new HttpPost(urlOverHttps);
            if (StringUtils.isNotBlank(proxyHost)) {
                HttpHost proxy = new HttpHost(proxyHost, proxyPort);
                RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
                httpPost.setConfig(requestConfig);
            }
            LOGGER.info("#################URL#######################" + urlOverHttps);
            LOGGER.info("#################requestData#######################" + requestData);
            StringEntity entity = new StringEntity(requestData);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/xml");
            httpPost.setHeader("Content-Type", "application/xml");
            HttpResponse response = httpClient.execute(httpPost);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
            LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + urlOverHttps);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return rtnMap;
    }

    public static Map handleWebRequestApplicationJSONThroughProxy(String urlOverHttps, String requestData,
                                                                  String proxyHost, int proxyPort, Map<String, String> header) throws Exception {
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
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom().setSSLSocketFactory(f).build();
            HttpPost httpPost = new HttpPost(urlOverHttps);
            if (StringUtils.isNotBlank(proxyHost)) {
                HttpHost proxy = new HttpHost(proxyHost, proxyPort);
                RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
                httpPost.setConfig(requestConfig);
            }
            LOGGER.info("#################URL#######################" + urlOverHttps);
            LOGGER.info("#################requestData#######################" + requestData);
            StringEntity entity = new StringEntity(requestData);
            httpPost.setEntity(entity);

            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            HttpResponse response = httpClient.execute(httpPost);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
            LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + urlOverHttps);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return rtnMap;
    }

    @SuppressWarnings("deprecation")

    public static Map<String, String> handleWebRequestWithContentType(String urlOverHttps, String requestType,
                                                                      String requestData, String contentType) throws Exception {
        int responseCode;
        DefaultHttpClient httpClient = null;
        String responseString = null;
        Map<String, String> rtnMap = new HashMap<String, String>();
        try {

            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] certificate, String authType) {

                    return true;

                }

            };

            @SuppressWarnings("deprecation")
            SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,

                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            @SuppressWarnings("deprecation")
            SchemeRegistry registry = new SchemeRegistry();

            registry.register(new Scheme("https", 443, (SchemeSocketFactory) sf));

            ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);

            if (urlOverHttps.startsWith("http:")) {
                httpClient = new DefaultHttpClient();
            } else {
                httpClient = new DefaultHttpClient(ccm);
            }

            HttpPost httpPost = new HttpPost(urlOverHttps);

            LOGGER.info("#################URL#######################" + urlOverHttps);

            LOGGER.info("#################requestData#######################" + requestData);

            StringEntity entity = new StringEntity(requestData);

            httpPost.setEntity(entity);

            httpPost.setHeader("Accept", "application/xml");
            httpPost.setHeader("Content-Type", "application/xml");

            if ("JSON".equalsIgnoreCase(contentType)) {
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-Type", "application/json");
            }

            HttpResponse response = httpClient.execute(httpPost);

            responseCode = response.getStatusLine().getStatusCode();

            LOGGER.info("#################responseCode#######################" + responseCode);

            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");

                rtnMap.put("responseString", responseString);

            }

            LOGGER.info("#################responseString#######################" + responseString);

            if (responseCode != 200 && responseCode != 202) {

                LOGGER.error("response code not OK: " + responseCode);

                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + urlOverHttps);

            }

        } catch (NoResponseException exception) {

            LOGGER.info("##################NoResponseException##################");

            LOGGER.error(exception.getMessage());

            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());

        } catch (IOException exception) {

            LOGGER.info("##################IOException##################");

            LOGGER.error(exception.getMessage());

            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());

        } catch (Exception exception) {

            LOGGER.info("##################Exception##################");

            LOGGER.error(exception.getMessage());

            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());

        } finally {

            if (httpClient != null) {

                httpClient.close();

            }

        }

        // return responseString;

        return rtnMap;

    }

    public static Map<String, String> handleApiWebRequest(String urlOverHttps, Map<String, String> requestMap)
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
            HttpPost httpPost = new HttpPost(urlOverHttps);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

            for (Map.Entry<String, String> entry : requestMap.entrySet()) {

                urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);

            HttpResponse response = httpClient.execute(httpPost);

            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode
                    + "#################URL#######################" + urlOverHttps);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
            LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + urlOverHttps);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        return rtnMap;
    }

    public static Map handlePOSTWebRequest(String urlOverHttps, String requestData, Map<String, String> headers)
            throws Exception {
        int responseCode;
        CloseableHttpClient httpClient = null;//HttpClients.createDefault();
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
                LOGGER.error("response code OK: " + responseCode);

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
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
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
            //LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                LOGGER.error("response message not OK: " + responseString);

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error(exception.getMessage());
            throw new NoResponseException("00", "Url: " + urlOverHttps + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        return rtnMap;
    }

    //==========Abishek===========================
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
            //LOGGER.info("#################requestData#######################" + binaryFile);
            HttpPut httpPut = new HttpPut(insertCsvUrl);

            //ByteArrayEntity entity = new ByteArrayEntity(binaryFile);

            //httpPut.setEntity(entity);

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
            //LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                LOGGER.error("response message not OK: " + responseString);

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + insertCsvUrl + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + insertCsvUrl + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        return rtnMap;
    }
    //====================================


    public static Map<String, String> handlePUTWebRequest(String insertCsvUrl, byte[] binaryFile,
                                                          Map<String, String> header) throws Exception {
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
            LOGGER.info("#################requestData#######################" + binaryFile);
            HttpPut httpPut = new HttpPut(insertCsvUrl);

            ByteArrayEntity entity = new ByteArrayEntity(binaryFile);

            httpPut.setEntity(entity);

            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }

            HttpResponse response = httpClient.execute(httpPut);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode
                    + "#################URL#######################" + insertCsvUrl);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }
            if (responseCode == 201) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }

            //LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 201) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + insertCsvUrl);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + insertCsvUrl + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + insertCsvUrl + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        return rtnMap;
    }

    public static Map<String, String> handlePATCHWebRequest(String updateJobStatusUrl, String requestString,
                                                            Map<String, String> header) throws Exception {
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
            LOGGER.info("#################URL#######################" + updateJobStatusUrl);
            LOGGER.info("#################requestData#######################" + updateJobStatusUrl);
            HttpPatch httpPatch = new HttpPatch(updateJobStatusUrl);

            StringEntity entity = new StringEntity(requestString);

            httpPatch.setEntity(entity);

            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPatch.setHeader(entry.getKey(), entry.getValue());
            }

            HttpParams params = new BasicHttpParams();
            httpPatch.setParams(params);

            HttpResponse response = httpClient.execute(httpPatch);
            responseCode = response.getStatusLine().getStatusCode();
            LOGGER.info("#################responseCode#######################" + responseCode
                    + "#################URL#######################" + updateJobStatusUrl);
            if (responseCode == 200) {

                responseString = getStringFromInputStream(response.getEntity().getContent());

                rtnMap.put("responseCode", responseCode + "");
                rtnMap.put("responseString", responseString);
            }

            //LOGGER.info("#################responseString#######################" + responseString);
            if (responseCode != 200 && responseCode != 202) {
                LOGGER.error("response code not OK: " + responseCode);
                throw new NoResponseException(responseCode + "",
                        "response code not OK or Null: " + responseCode + "Url: " + updateJobStatusUrl);
            }
        } catch (NoResponseException exception) {
            LOGGER.info("##################NoResponseException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException(exception.getErrorCode(), exception.getErrorMsg());
        } catch (IOException exception) {
            LOGGER.info("##################IOException##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + updateJobStatusUrl + " " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.info("##################Exception##################");
            LOGGER.error("Exception msg: ", exception);
            throw new NoResponseException("00", "Url: " + updateJobStatusUrl + " " + exception.getMessage());
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }

        return rtnMap;
    }
}

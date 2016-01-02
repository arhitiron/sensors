package com.antyron.services;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author atyron
 */
@Component
public class HTTPServiceImpl implements HTTPService {
    public static final String SN = "sn";
    public static final String CN = "cn";
    public static final String LOCALE = "locale";
    public static final String CALLER = "caller";
    public static final String SN_VALUE = "C02G8416DRJM";
    public static final String USER_AGENT_KEY = "User-Agent";
    public static final String GET = "GET";
    private final String USER_AGENT = "Mozilla/5.0";
    private static final Logger logger = Logger.getLogger(HTTPServiceImpl.class);

    @Override
    public void sendGet(Map<String, String> data, String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(GET);
        con.setRequestProperty(USER_AGENT_KEY, USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }

    @Override
    public void sendPost(Map<String, String> data, String url) throws IOException {
        logger.info("Sending request to url " + url);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader(USER_AGENT_KEY, USER_AGENT);
        List<NameValuePair> urlParameters = getNameValuePairs();
        urlParameters.addAll(data.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList()));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        System.out.println(urlParameters);
        client.execute(post);
    }

    private List<NameValuePair> getNameValuePairs() {
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(SN, SN_VALUE));
        urlParameters.add(new BasicNameValuePair(CN, StringUtils.EMPTY));
        urlParameters.add(new BasicNameValuePair(LOCALE, StringUtils.EMPTY));
        urlParameters.add(new BasicNameValuePair(CALLER, StringUtils.EMPTY));
        return urlParameters;
    }
}

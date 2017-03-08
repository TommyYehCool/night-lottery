package com.ingeniousinc.lottery.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ingeniousinc.lottery.util.exception.HttpUtilException;

public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private final static HttpClient httpClient 
		= HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
	
	private static final int RETRY_TIMES = 5;
	private static final int RETRY_INTERVAL_IN_MILLI_SEC = 5 * 1000;

	private static final int CONNECTION_TIMEOUT = 5 * 1000;
	private static final String CONNECTION_USER_AGENT = "Mozilla/5.0";
	
	protected static Element getExpectedElement(String expectedCssQuery, String name, String url) throws Exception {
		return getExpectedElement(expectedCssQuery, name, url, null);
	}

	protected static Element getExpectedElement(String expectedCssQuery, String name, String url, Map<String, String> cookie) {
		Document doc = null;
		Element expectedElm = null;
		
		int currentTryTimes = 1;
		boolean isFailed = false;

		while (currentTryTimes <= RETRY_TIMES) {
			try {
				if (cookie == null) {
					doc = getDocument(name, url);
				}
				else {
					doc = getDocument(name, url, cookie);
				}
			} catch (Exception e) {
				logger.warn("Exception raised while trying to get document, retry to get again, current try times: <{}>, msg: <{}>", currentTryTimes, e.getMessage(), e);
				currentTryTimes++;
				continue;
			}
			
			expectedElm = doc.select(expectedCssQuery).first();
			
			// 若沒找到想要的 tag 代表失敗
			isFailed = expectedElm == null;

			if (!isFailed) {
				// 成功找到想要的 tag 代表成功, 離開迴圈
				break;
			}
			
			logger.warn("The document is unexpected format, current try times: <{}>, will retry to get document after {} secs...", currentTryTimes, RETRY_INTERVAL_IN_MILLI_SEC / 1000);
			
			currentTryTimes++;
			
			try {
				Thread.sleep(RETRY_INTERVAL_IN_MILLI_SEC);
			} catch (InterruptedException e) {}
		}
		
		if (isFailed) {
			throw new HttpUtilException("After try " + currentTryTimes + " times, the document format is still unexpected, so give up, content: " + doc);
		}
		
		return expectedElm;
	}
	
	protected static Elements getExpectedElements(String expectedCssQuery, String name, String url) throws Exception {
		return getExpectedElements(expectedCssQuery, name, url, null);
	}

	protected static Elements getExpectedElements(String expectedCssQuery, String name, String url, Map<String, String> cookie) {
		Document doc = null;
		Elements expectedElms = null;
		
		int currentTryTimes = 1;
		boolean isFailed = false;

		while (currentTryTimes <= RETRY_TIMES) {
			try {
				if (cookie == null) {
					doc = getDocument(name, url);
				}
				else {
					doc = getDocument(name, url, cookie);
				}
			} catch (Exception e) {
				logger.warn("Exception raised while trying to get document, retry to get again, current try times: <{}>, msg: <{}>", currentTryTimes, e.getMessage(), e);
				currentTryTimes++;
				continue;
			}
			
			expectedElms = doc.select(expectedCssQuery);
			
			// 若沒找到想要的 tag 代表失敗
			isFailed = expectedElms == null || expectedElms.size() == 0;

			if (!isFailed) {
				// 成功找到想要的 tag 代表成功, 離開迴圈
				break;
			}
			
			logger.warn("The document is unexpected format, current try times: <{}>, will retry to get document after {} secs...", currentTryTimes, RETRY_INTERVAL_IN_MILLI_SEC / 1000);
			
			currentTryTimes++;
			
			try {
				Thread.sleep(RETRY_INTERVAL_IN_MILLI_SEC);
			} catch (InterruptedException e) {}
		}
		
		if (isFailed) {
			throw new HttpUtilException("After try " + currentTryTimes + " times, the document format is still unexpected, so give up, content: " + doc);
		}
		
		return expectedElms;
	}

	private static Document getDocument(String name, String url) throws Exception {
		return getDocument(name, url, null);
	}

	private static Document getDocument(String name, String url, Map<String, String> cookie) throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info(">>>>> {} [{}] Try to get document", name, url);
		Connection connection = Jsoup.connect(url);
		connection.timeout(CONNECTION_TIMEOUT);
		connection.userAgent(CONNECTION_USER_AGENT);
		if (cookie != null) {
			connection.cookies(cookie);
		}
		Document doc = connection.get();
		logger.info("<<<<< {} [{}] Got document succeed, time-spent: <{} ms>", name, url, System.currentTimeMillis() - startTime);
		return doc;
	}
	
	protected static String sendGetRequest(String name, String url) {
		HttpGet get = new HttpGet(url);
		
		logger.info(">>>>> {} [{}] Try to send HTTP GET request to get data", name, url);
		
		try {
			long startTime = System.currentTimeMillis();
			
			HttpResponse response = httpClient.execute(get);
			
			long timeSpent = System.currentTimeMillis() - startTime;
			
			int httpStatusCode = response.getStatusLine().getStatusCode();
			
			String responseData = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			
			logger.info("<<<<< {} [{}] Got response succeed with HTTP status code: <{}>, time-spent: <{} ms>, response:{}", name, url, httpStatusCode, timeSpent, responseData);
			
			return responseData;
			
		} catch (IOException e) {
			throw new HttpUtilException("IOException raised while sending HTTP GET request to url: [" + url + "]", e);
		}
	}
}

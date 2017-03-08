package com.ingeniousinc.lottery.util.lottery;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ingeniousinc.lottery.util.HttpUtil;

public class GetLotteryNoUtil extends HttpUtil {

	@SuppressWarnings("unchecked")
	public static void getSSCFromCaipiaokong(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.dt";

		// 這邊塞 cookie 就不會被判斷未登入或請先註冊
		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url, createCookieForCaipiaokong());
		
		Element tbodyOfResult = tableOfResult.select("tbody").first();
		Elements trsOfResult = tbodyOfResult.select("tr");

		for (int i = 1; i < trsOfResult.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();

			Element trOfResult = trsOfResult.get(i);
			Elements tdsOfResult = trOfResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();

				Method method = null;
				switch (j) {
					case 0:
						String issueNo = data.substring(1, data.length() - 1);
						if (clz.getName().equals("com.ingeniousinc.entity.lotteryno.XJSSC")) {
							if (issueNo.length() != 11) {
								issueNo = issueNo.substring(0, issueNo.length() - 2) + "0" + issueNo.substring(issueNo.length() - 2, issueNo.length());
							}
						}
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						method.invoke(instance, data);
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getSSCFromIcaile(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.today";

		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url);
		
		Element tbodyOfResult = tableOfResult.select("tbody").first();
		Elements trsOfResult = tbodyOfResult.select("tr");
		
		for (int i = 1; i < trsOfResult.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();

			Element trOfResult = trsOfResult.get(i);
			Elements tdsOfResult = trOfResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();
				
				Method method = null;
				switch (j) {
					case 0:
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter());
						method.invoke(instance, data);
						break;
						
					case 2:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						
						Elements lotteryNos = tdOfResult.select("em");
						for (Element lotteryNo : lotteryNos) {
							String no = lotteryNo.text();
							method.invoke(instance, no);
						}
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void get11xuan5FromCaipiaokong(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.dt";

		// 這邊塞 cookie 就不會被判斷未登入或請先註冊
		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url, createCookieForCaipiaokong());
		
		Element tbodyOfResult = tableOfResult.select("tbody").first();
		Elements trsOfResult = tbodyOfResult.select("tr");
		
		for (int i = 1; i < trsOfResult.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();

			Element trOfResult = trsOfResult.get(i);
			Elements tdsOfResult = trOfResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();

				Method method = null;
				switch (j) {
					case 0:
						String issueNo = data.substring(1, data.length() - 1);
						if (clz.getName().equals("com.ingeniousinc.entity.lotteryno.GD11XUAN5")) {
							issueNo = "20" + issueNo;
						}
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter()); 
						method.invoke(instance, data);
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void get11xuan5FromIcaile(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.today";

		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url);
		
		Element tbodyOfResult = tableOfResult.select("tbody").first();
		Elements trsOfResult = tbodyOfResult.select("tr");
		
		for (int i = 1; i < trsOfResult.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Element trOfResult = trsOfResult.get(i);
			Elements tdsOfResult = trOfResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();
				
				Method method = null;
				switch (j) {
					case 0:
						String issueNo = "20" + data;
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 2:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						
						Elements lotteryNos = tdOfResult.select("em");
						for (Element lotteryNo : lotteryNos) {
							String no = lotteryNo.text();
							method.invoke(instance, no);
						}
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public static void get11xuan5FromTrendBaidu(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "div.chart_table_wrapper";

		Element divOfResults = getExpectedElement(expectedCssQuery, name, url);
		
		Element tableOfResults = divOfResults.select("table.chart_table").first();
		Element tbodyOfResults = tableOfResults.select("tbody").first();
		Elements trsOfResults = tbodyOfResults.select("tr");
				
		allDatasLoop:
		for (int i = 0; i < trsOfResults.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Element trOrResult = trsOfResults.get(i);
			Elements tdsOfResult = trOrResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();
				
				Method method = null;
				switch (j) {
					case 0:
						if (data.equals("总次数")) {
							break allDatasLoop;
						}
						break;
				
					case 1:
						String issueNo = data;
						if (clz.getName().equals("com.ingeniousinc.entity.lotteryno.GD11XUAN5")) {
							issueNo = "20" + issueNo;
						}
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter());
						method.invoke(instance, issueNo);
						break;
						
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						method.invoke(instance, data);
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getXJSSC_A(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.kj_tab";

		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url);
		
		Element tbodyOfResult = tableOfResult.select("tbody").first();
		Elements trsOfResult = tbodyOfResult.select("tr");
		
		allDatasLoop:
		for (Element trOfResult : trsOfResult) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Elements tdsOfResult = trOfResult.select("td");
			for (int i = 0; i < tdsOfResult.size(); i++) {
				Element tdOfResult = tdsOfResult.get(i);
				String data = tdOfResult.text();
				if (data.equals("--")) {
					break allDatasLoop;
				}
	
				Method method = null;
				switch (i) {
					case 0:
						String issueNo = data;
						if (issueNo.length() != 11) {
							issueNo = data.substring(0, data.length() - 2) + "0" + data.substring(data.length() - 2, data.length());
						}
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 1:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter()); 
						
						String[] splitLotteryNos = data.split(",");
						for (String lotteryNo : splitLotteryNos) {
							method.invoke(instance, lotteryNo);
						}
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getCQSSC_A(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "div.ssc25";

		Element divOfResult = getExpectedElement(expectedCssQuery, name, url);
		
		Elements ulsOfResult = divOfResult.select("ul");
		
		for (int i = 1; i < ulsOfResult.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Element ulOfResult = ulsOfResult.get(i); 
			Elements lisOfResult = ulOfResult.select("li");
			for (int j = 0; j < lisOfResult.size(); j++) {
				Element lsOfResult = lisOfResult.get(j);
				String data = lsOfResult.text();
				
				Method method = null;
				switch (j) {
					case 0:
						String issueNo = "20" + data;
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 1:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						
						String[] lotteryNos = data.split("-");
						for (String lotteryNo : lotteryNos) {
							method.invoke(instance, lotteryNo);
						}
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getSD11XUAN5_A(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table[bgcolor=\"#006599\"]";

		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url);
		
		Elements trsOfResult = tableOfResult.select("tr");
		
		for (int i = 2; i < trsOfResult.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Element trOfResult = trsOfResult.get(i);
			Elements tdsOfResult = trOfResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();
				
				Method method = null;
				switch (j) {
					case 0:
						String issueNo = "20" + data;
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					default:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						method.invoke(instance, data);
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getGD11XUAN5_A(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.dataTable";

		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url);
		
		Element tbodyOfResult = tableOfResult.select("tbody[id=\"cpdata\"]").first();
		Elements trsOfResults = tbodyOfResult.select("tr");
		
		for (int i = 0; i < trsOfResults.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Element trOrResult = trsOfResults.get(i);
			Elements tdsOfResult = trOrResult.select("td");
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				String data = tdOfResult.text();
				
				Method method = null;
				switch (j) {
					case 0:
						String issueNo = "20" + data;
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
						method.invoke(instance, data);
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getJX11XUAN5_A(Class<?> clz, String name, String url, Object resultList) throws Exception {
		String expectedCssQuery = "table.kj-detail-table";

		Element tableOfResult = getExpectedElement(expectedCssQuery, name, url);

		Element tbodyOfResult = tableOfResult.select("tbody").first();
		Elements trsOfResults = tbodyOfResult.select("tr");

		for (int i = 1; i < trsOfResults.size(); i++) {
			Class<?> cls = Class.forName(clz.getName());
			Object instance = cls.newInstance();
			
			Element trOfResult = trsOfResults.get(i);
			Elements tdsOfResult = trOfResult.select("td");
			
			for (int j = 0; j < tdsOfResult.size(); j++) {
				Element tdOfResult = tdsOfResult.get(j);
				
				Method method = null;
				switch (j) {
					case 0:
						String data = tdOfResult.text();
						String[] split = data.split(" ");
						String issueNo = "20" + split[0];
						method = cls.getDeclaredMethod("setIssueNo", getStringParameter()); 
						method.invoke(instance, issueNo);
						break;
						
					case 1:
						Elements iOfResults = tdOfResult.select("i");
						for (Element iOfResult : iOfResults) {
							method = cls.getDeclaredMethod("addLotteryNo", getStringParameter());
							method.invoke(instance, iOfResult.text());
						}
						break;
				}
			}
			((List<Object>) resultList).add(instance);
		}
	}

	/**
	 * Caipiaokong 這個網站常常連過去後, 會回傳請註冊或登錄的網頁, 所以塞個 cookie 給他, 不過 cookie 要常換
	 */
	private static Map<String, String> createCookieForCaipiaokong() {
		Map<String, String> cookie = new HashMap<String, String>();
		cookie.put("caipiaokong_4891_saltkey", "dmbgCWDB");
		cookie.put("caipiaokong_4891_lastvisit", "1478594642");
		cookie.put("aliyungf_tc", "AQAAAB3dfx9KzQkAH7P7PD2ROyFYtBWp");
		cookie.put("acw_tc", "AQAAAOuTH1gX4AkAH7P7PFvxSlKSVCTp");
		cookie.put("caipiaokong_4891_caipiaokong_MXj", "1");
		cookie.put("caipiaokong_4891_lastact", "1479092303%09index.php%09jxsyxw");
		cookie.put("Hm_lvt_1fa650cb7d8eae53d0e6fbd8aec3eb67", "1478598245,1478741515,1479091481");
		cookie.put("Hm_lpvt_1fa650cb7d8eae53d0e6fbd8aec3eb67", "1479092304");
		return cookie;
	}

	private static Class<?>[] getStringParameter() {
		Class<?>[] paramString = new Class[1];
		paramString[0] = String.class;
		return paramString;
	}
}	

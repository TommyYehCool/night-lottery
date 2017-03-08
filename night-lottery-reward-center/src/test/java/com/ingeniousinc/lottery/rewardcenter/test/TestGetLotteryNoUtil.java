package com.ingeniousinc.lottery.rewardcenter.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ingeniousinc.entity.lotteryno.CQSSC;
import com.ingeniousinc.entity.lotteryno.GD11XUAN5;
import com.ingeniousinc.entity.lotteryno.JX11XUAN5;
import com.ingeniousinc.entity.lotteryno.SD11XUAN5;
import com.ingeniousinc.entity.lotteryno.TJSSC;
import com.ingeniousinc.entity.lotteryno.XJSSC;
import com.ingeniousinc.lottery.util.lottery.GetLotteryNoUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGetLotteryNoUtil {
	
	private Logger logger = LoggerFactory.getLogger(TestGetLotteryNoUtil.class);
	
	// 01
	private final String NAME_TJSSC_C = "天津時時彩_C";
	private final String URL_TJSSC_C = "http://www.caipiaokong.com/lottery/tjssc.html";
	
	// 02
	private final String NAME_TJSSC_D = "天津時時彩_D";
	private final String URL_TJSSC_D = "http://pub.icaile.com/tjssckjjg.php";

	// 03
	private final String NAME_XJSSC_A = "新疆時時彩_A";
	private final String URL_XJSSC_A = "http://www.xjflcp.com/game/sscAnnounce";
	
	// 04
	private final String NAME_XJSSC_C = "新疆時時彩_C";
	private final String URL_XJSSC_C = "http://www.caipiaokong.com/lottery/xjssc.html";
	
	// 05
	private final String NAME_XJSSC_D = "新疆時時彩_D";
	private final String URL_XJSSC_D = "http://pub.icaile.com/xjssckjjg.php";
	
	// 06
	private final String NAME_CQSSC_A = "重慶時時彩_A";
	private final String URL_CQSSC_A = "http://www.cqcp.net/game/ssc/";
	
	// 07
	private final String NAME_CQSSC_C = "重慶時時彩_C";
	private final String URL_CQSSC_C = "http://www.caipiaokong.com/lottery/cqssc.html";
	
	// 08
	private final String NAME_CQSSC_D = "重慶時時彩_D";
	private final String URL_CQSSC_D = "http://pub.icaile.com/cqssckjjg.php";
	
	// 09
	private final String NAME_SD11XUAN5_A = "山東11選5_A";
	private final String URL_SD11XUAN5_A = "http://www.sdticai.com/find/find_syxw.asp";
	
	// 10
	private final String NAME_SD11XUAN5_C = "山東11選5_C";
	private final String URL_SD11XUAN5_C = "http://www.caipiaokong.com/lottery/sdsyydj.html";
	
	// 11
	private final String NAME_SD11XUAN5_D = "山東11選5_D";
	private final String URL_SD11XUAN5_D = "http://pub.icaile.com/sd11x5kjjg.php";
	
	// 12
	private final String NAME_GD11XUAN5_A = "廣東11選5_A";
	private final String URL_GD11XUAN5_A = "http://trend.caipiao.163.com/gd11xuan5/";
	
	// 13
	private final String NAME_GD11XUAN5_B = "廣東11選5_B";
	private final String URL_GD11XUAN5_B = "http://trend.baidu.lecai.com/gd11x5/";
	
	// 14
	private final String NAME_GD11XUAN5_C = "廣東11選5_C";
	private final String URL_GD11XUAN5_C = "http://www.caipiaokong.com/lottery/gdsyxw.html";
	
	// 15
	private final String NAME_GD11XUAN5_D = "廣東11選5_D";
	private final String URL_GD11XUAN5_D = "http://pub.icaile.com/gd11x5kjjg.php#from=zx";
	
	// 16
	private final String NAME_JX11XUAN5_A = "江西11選5_A";
	private final String URL_JX11XUAN5_A = "http://fx.cp2y.com/jx11x5kj/";
	
	// 17
	private final String NAME_JX11XUAN5_B = "江西11選5_B";
	private final String URL_JX11XUAN5_B = "http://trend.baidu.lecai.com/jx11x5/";
	
	// 18
	private final String NAME_JX11XUAN5_C = "江西11選5_C";
	private final String URL_JX11XUAN5_C = "http://www.caipiaokong.com/lottery/jxsyxw.html";
	
	// 19
	private final String NAME_JX11XUAN5_D = "江西11選5_D";
	private final String URL_JX11XUAN5_D = "http://pub.icaile.com/jx11x5kjjg.php";

	@Test
	public void test_01_getTJSSC_C() throws Exception {
		logger.info(">>>>> Starting test_01_getTJSSC_C");
		List<TJSSC> tjsscs = new ArrayList<>();
		GetLotteryNoUtil.getSSCFromCaipiaokong(TJSSC.class, NAME_TJSSC_C, URL_TJSSC_C, tjsscs);
		for (TJSSC tjssc : tjsscs) {
			logger.info(tjssc.toString());
		}
		logger.info("<<<<< test_01_getTJSSC_C done");
	}
	
	@Test
	public void test_02_getTJSSC_D() throws Exception {
		logger.info(">>>>> Starting test_02_getTJSSC_D");
		List<TJSSC> tjsscs = new ArrayList<>();
		GetLotteryNoUtil.getSSCFromIcaile(TJSSC.class, NAME_TJSSC_D, URL_TJSSC_D, tjsscs);
		for (TJSSC tjssc : tjsscs) {
			logger.info(tjssc.toString());
		}
		logger.info("<<<<< test_02_getTJSSC_D done");
	}
	
	@Test
	public void test_03_getXJSSC_A() throws Exception {
		logger.info(">>>>> Starting test_03_getXJSSC_A");
		List<XJSSC> xjsscs = new ArrayList<>();
		GetLotteryNoUtil.getXJSSC_A(XJSSC.class, NAME_XJSSC_A, URL_XJSSC_A, xjsscs);
		for (XJSSC xjssc : xjsscs) {
			logger.info(xjssc.toString());
		}
		logger.info("<<<<< test_03_getXJSSC_A done");
	}
	
	@Test
	public void test_04_getXJSSC_C() throws Exception {
		logger.info(">>>>> Starting test_04_getXJSSC_C");
		List<XJSSC> xjsscs = new ArrayList<>();
		GetLotteryNoUtil.getSSCFromCaipiaokong(XJSSC.class, NAME_XJSSC_C, URL_XJSSC_C, xjsscs);
		for (XJSSC xjssc : xjsscs) {
			logger.info(xjssc.toString());
		}
		logger.info("<<<<< test_04_getXJSSC_C done");
	}
	
	@Test
	public void test_05_getXJSSC_D() throws Exception {
		logger.info(">>>>> Starting test_05_getXJSSC_D");
		List<XJSSC> xjsscs = new ArrayList<>();
		GetLotteryNoUtil.getSSCFromIcaile(XJSSC.class, NAME_XJSSC_D, URL_XJSSC_D, xjsscs);
		for (XJSSC xjssc : xjsscs) {
			logger.info(xjssc.toString());
		}
		logger.info("<<<<< test_05_getXJSSC_D done");
	}
	
	@Test
	public void test_06_getCQSSC_A() throws Exception {
		logger.info(">>>>> Starting test_06_getCQSSC_A");
		List<CQSSC> cqsscs = new ArrayList<>();
		GetLotteryNoUtil.getCQSSC_A(CQSSC.class, NAME_CQSSC_A, URL_CQSSC_A, cqsscs);
		for (CQSSC cqssc : cqsscs) {
			logger.info(cqssc.toString());
		}
		logger.info("<<<<< test_06_getCQSSC_A done");
	}
	
	@Test
	public void test_07_getCQSSC_C() throws Exception {
		logger.info(">>>>> Starting test_07_getCQSSC_C");
		List<CQSSC> cqsscs = new ArrayList<>();
		GetLotteryNoUtil.getSSCFromCaipiaokong(CQSSC.class, NAME_CQSSC_C, URL_CQSSC_C, cqsscs);
		for (CQSSC cqssc : cqsscs) {
			logger.info(cqssc.toString());
		}
		logger.info("<<<<< test_07_getCQSSC_C done");
	}
	
	@Test
	public void test_08_getCQSSC_D() throws Exception {
		logger.info(">>>>> Starting test_08_getCQSSC_D");
		List<CQSSC> cqsscs = new ArrayList<>();
		GetLotteryNoUtil.getSSCFromIcaile(CQSSC.class, NAME_CQSSC_D, URL_CQSSC_D, cqsscs);
		for (CQSSC cqssc : cqsscs) {
			logger.info(cqssc.toString());
		}
		logger.info("<<<<< test_08_getCQSSC_D done");
	}
	
	@Test
	public void test_09_getSD11XUAN5_A() throws Exception {
		logger.info(">>>>> Starting test_09_getSD11XUAN5_A");
		List<SD11XUAN5> sd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.getSD11XUAN5_A(SD11XUAN5.class, NAME_SD11XUAN5_A, URL_SD11XUAN5_A, sd11xuan5s);
		for (SD11XUAN5 sd11xuan5 : sd11xuan5s) {
			logger.info(sd11xuan5.toString());
		}
		logger.info("<<<<< test_09_getSD11XUAN5_A done");
	}
	
	@Test
	public void test_10_getSD11XUAN5_C() throws Exception {
		logger.info(">>>>> Starting test_10_getSD11XUAN5_C");
		List<SD11XUAN5> sd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromCaipiaokong(SD11XUAN5.class, NAME_SD11XUAN5_C, URL_SD11XUAN5_C, sd11xuan5s);
		for (SD11XUAN5 sd11xuan5 : sd11xuan5s) {
			logger.info(sd11xuan5.toString());
		}
		logger.info("<<<<< test_10_getSD11XUAN5_C done");
	}
	
	@Test
	public void test_11_getSD11XUAN5_D() throws Exception {
		logger.info(">>>>> Starting test_11_getSD11XUAN5_D");
		List<SD11XUAN5> sd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromIcaile(SD11XUAN5.class, NAME_SD11XUAN5_D, URL_SD11XUAN5_D, sd11xuan5s);
		for (SD11XUAN5 sd11xuan5 : sd11xuan5s) {
			logger.info(sd11xuan5.toString());
		}
		logger.info("<<<<< test_11_getSD11XUAN5_D done");
	}
	
	@Test
	public void test_12_getGD11XUAN5_A() throws Exception {
		logger.info(">>>>> Starting test_12_getGD11XUAN5_A");
		List<GD11XUAN5> gd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.getGD11XUAN5_A(GD11XUAN5.class, NAME_GD11XUAN5_A, URL_GD11XUAN5_A, gd11xuan5s);
		for (GD11XUAN5 gd11xuan5 : gd11xuan5s) {
			logger.info(gd11xuan5.toString());
		}
		logger.info("<<<<< test_12_getGD11XUAN5_A done");
	}
	
	@Test
	public void test_13_getGD11XUAN5_B() throws Exception {
		logger.info(">>>>> Starting test_13_getGD11XUAN5_B");
		List<GD11XUAN5> gd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromTrendBaidu(GD11XUAN5.class, NAME_GD11XUAN5_B, URL_GD11XUAN5_B, gd11xuan5s);
		for (GD11XUAN5 gd11xuan5 : gd11xuan5s) {
			logger.info(gd11xuan5.toString());
		}
		logger.info("<<<<< test_13_getGD11XUAN5_B done");
	}
	
	@Test
	public void test_14_getGD11XUAN5_C() throws Exception {
		logger.info(">>>>> Starting test_14_getGD11XUAN5_C");
		List<GD11XUAN5> gd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromCaipiaokong(GD11XUAN5.class, NAME_GD11XUAN5_C, URL_GD11XUAN5_C, gd11xuan5s);
		for (GD11XUAN5 gd11xuan5 : gd11xuan5s) {
			logger.info(gd11xuan5.toString());
		}
		logger.info("<<<<< test_14_getGD11XUAN5_C done");
	}
	
	@Test
	public void test_15_getGD11XUAN5_D() throws Exception {
		logger.info(">>>>> Starting test_15_getGD11XUAN5_D");
		List<GD11XUAN5> gd11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromIcaile(GD11XUAN5.class, NAME_GD11XUAN5_D, URL_GD11XUAN5_D, gd11xuan5s);
		for (GD11XUAN5 gd11xuan5 : gd11xuan5s) {
			logger.info(gd11xuan5.toString());
		}
		logger.info("<<<<< test_15_getGD11XUAN5_D done");
	}
	
	@Test
	public void test_16_getJX11XUAN5_A() throws Exception {
		logger.info(">>>>> Starting test_16_getJX11XUAN5_A");
		List<JX11XUAN5> jx11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.getJX11XUAN5_A(JX11XUAN5.class, NAME_JX11XUAN5_A, URL_JX11XUAN5_A, jx11xuan5s);
		for (JX11XUAN5 jx11xuan5 : jx11xuan5s) {
			logger.info(jx11xuan5.toString());
		}
		logger.info("<<<<< test_16_getJX11XUAN5_A done");
	}
	
	@Test
	public void test_17_getJX11XUAN5_B() throws Exception {
		logger.info(">>>>> Starting test_17_getJX11XUAN5_B");
		List<JX11XUAN5> jx11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromTrendBaidu(JX11XUAN5.class, NAME_JX11XUAN5_B, URL_JX11XUAN5_B, jx11xuan5s);
		for (JX11XUAN5 jx11xuan5 : jx11xuan5s) {
			logger.info(jx11xuan5.toString());
		}
		logger.info("<<<<< test_17_getJX11XUAN5_B done");
	}
	
	@Test
	public void test_18_getJX11XUAN5_C() throws Exception {
		logger.info(">>>>> Starting test_18_getJX11XUAN5_C");
		List<JX11XUAN5> jx11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromCaipiaokong(JX11XUAN5.class, NAME_JX11XUAN5_C, URL_JX11XUAN5_C, jx11xuan5s);
		for (JX11XUAN5 jx11xuan5 : jx11xuan5s) {
			logger.info(jx11xuan5.toString());
		}
		logger.info("<<<<< test_18_getJX11XUAN5_C done");
	}
	
	@Test
	public void test_19_getJX11XUAN5_D() throws Exception {
		logger.info(">>>>> Starting test_19_getJX11XUAN5_D");
		List<JX11XUAN5> jx11xuan5s = new ArrayList<>();
		GetLotteryNoUtil.get11xuan5FromIcaile(JX11XUAN5.class, NAME_JX11XUAN5_D, URL_JX11XUAN5_D, jx11xuan5s);
		for (JX11XUAN5 jx11xuan5 : jx11xuan5s) {
			logger.info(jx11xuan5.toString());
		}
		logger.info("<<<<< test_19_getJX11XUAN5_D done");
	}
}

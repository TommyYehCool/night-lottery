package com.ingeniousinc.lottery.rewardcenter.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJava {

	@Test
	public void testDate() {
		long date = 1486427400000L;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(dateFormat.format(cal.getTime()));
	}
}

package com.ingeniousinc.entity.lotteryno;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class GD11XUAN5 {
	/**
	 * 期號
	 */
	private String issueNo;
	/**
	 * 開獎號碼
	 */
	private List<String> lotteryNos;
	
	public void addLotteryNo(String lotteryNo) {
		if (lotteryNos == null) {
			lotteryNos = new ArrayList<String>();
		}
		lotteryNos.add(lotteryNo);
	}
	
	public String getLotteryNos() {
		return StringUtils.join(lotteryNos, "|");
	}
}

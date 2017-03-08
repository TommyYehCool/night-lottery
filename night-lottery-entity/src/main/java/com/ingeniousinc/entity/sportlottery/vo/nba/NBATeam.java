package com.ingeniousinc.entity.sportlottery.vo.nba;

import lombok.Data;

@Data
public class NBATeam {
	/**
	 * ID
	 */
	private Integer teamId;
	/**
	 * 簡稱
	 */
	private String abbr;
	/**
	 * 城市-中文
	 */
	private String cityCh;
	/**
	 * 城市-英文
	 */
	private String cityEn;
	/**
	 * 代碼
	 */
	private String code;
	/**
	 * 聯盟-中文
	 */
	private String conferenceCh;
	/**
	 * 聯盟-英文
	 */
	private String conferenceEn;
	/**
	 * 組別-中文
	 */
	private String divisionCh;
	/**
	 * 組別-英文
	 */
	private String divisionEn;
	/**
	 * 隊名-中文
	 */
	private String nameCh;
	/**
	 * 隊名-英文
	 */
	private String nameEn;
}

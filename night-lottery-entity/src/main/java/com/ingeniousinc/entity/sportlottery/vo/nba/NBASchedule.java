package com.ingeniousinc.entity.sportlottery.vo.nba;

import lombok.Data;

@Data
public class NBASchedule {
	/**
	 * 場次編號
	 */
	private Integer gameId;
	/**
	 * 場次時間 (timeInMillis)
	 */
	private Long gameTimeInMillis;
	/**
	 * 場次時間
	 */
	private String gameTime;
	/**
	 * 主隊 ID
	 */
	private Integer homeTeamId;
	/**
	 * 主隊簡稱
	 */
	private String homeTeamAbbr;
	/**
	 * 主隊城市-中文
	 */
	private String homeTeamCityCh;
	/**
	 * 主隊城市-英文
	 */
	private String homeTeamCityEn;
	/**
	 * 主隊名稱-中文
	 */
	private String homeTeamNameCh;
	/**
	 * 主隊名稱-英文
	 */
	private String homeTeamNameEn;
	/**
	 * 客隊 ID
	 */
	private Integer awayTeamId;
	/**
	 * 客隊簡稱
	 */
	private String awayTeamAbbr;
	/**
	 * 客隊城市-中文
	 */
	private String awayTeamCityCh;
	/**
	 * 客隊城市-英文
	 */
	private String awayTeamCityEn;
	/**
	 * 客隊名稱-中文
	 */
	private String awayTeamNameCh;
	/**
	 * 客隊名稱-英文
	 */
	private String awayTeamNameEn;
}

package com.ingeniousinc.entity.sportlottery.vo.nba;

import lombok.Data;

@Data
public class NBAGame {
	/**
	 * 場次 ID
	 */
	private Integer gameId;
	/**
	 * 場次日期
	 */
	private String gameDate;
	/**
	 * 主隊簡稱
	 */
	private String homeTeamAbbr;
	/**
	 * 主隊名稱
	 */
	private String homeTeamName;
	/**
	 * 客隊簡稱
	 */
	private String awayTeamAbbr;
	/**
	 * 客隊名稱
	 */
	private String awayTeamName;
	/**
	 * 主隊第一節比分
	 */
	private Integer homeTeam1stScores;
	/**
	 * 主隊第二節比分
	 */
	private Integer homeTeam2ndScores;
	/**
	 * 主隊第三節比分
	 */
	private Integer homeTeam3rdScores;
	/**
	 * 主隊第四節比分
	 */
	private Integer homeTeam4thScores;
	/**
	 * 客隊第一節比分
	 */
	private Integer awayTeam1stScores;
	/**
	 * 客隊第二節比分
	 */
	private Integer awayTeam2ndScores;
	/**
	 * 客隊第三節比分
	 */
	private Integer awayTeam3rdScores;
	/**
	 * 客隊第四節比分
	 */
	private Integer awayTeam4thScores;
	/**
	 * 主隊四節比分加總
	 */
	private Integer homeTeamScoresSum;
	/**
	 * 客隊四節比分加總
	 */
	private Integer awayTeamScoresSum;
	/**
	 * 兩隊比分加總
	 */
	private Integer totalScoresSum;
}
package com.ingeniousinc.lottery.util.nba;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingeniousinc.entity.sportlottery.json.nba.NBADateGamesFromNBATw;
import com.ingeniousinc.entity.sportlottery.json.nba.NBAGamesFromTaiwanSportLottery;
import com.ingeniousinc.entity.sportlottery.json.nba.NBASchedulesFromNBATw;
import com.ingeniousinc.entity.sportlottery.json.nba.NBATeamsFromNBATw;
import com.ingeniousinc.entity.sportlottery.json.nba.NBATodayGamesFromNBAData;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBAGame;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBASchedule;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBATeam;
import com.ingeniousinc.lottery.util.HttpUtil;
import com.ingeniousinc.lottery.util.exception.HttpUtilException;

public class GetNBAInformationUtil extends HttpUtil {
	// ------- 球隊資料 from NBA Taiwan -------
	private static final String NAME_NBA_TEAMS_FROM_NBA_TAIWAN = "球隊資料 - NBA Taiwan";
	private static final String URL_NBA_TEAMS_FROM_NBA_TAIWAN = "http://tw.global.nba.com/stats2/league/divisionteamlist.json?locale=zh_TW";
	
	// ------- 賽程資料 from NBA Taiwan -------
	private static final String NAME_NBA_SCHEDULES_FROM_NBA_TAIWAN = "賽程資訊 - NBA Taiwan";
	private static final String URL_NBA_SCHEDULES_FROM_NBA_TAIWAN = "https://tw.global.nba.com/stats2/season/schedule.json?countryCode=TW&locale=zh_TW&days=7";
	
	// ------- 今日賽事結果 from NBA Taiwan -------
	private static final String NAME_NBA_TODAY_GAMES_FROM_NBA_DATA = "今日賽事結果 - NBA Data";
	private static final String URL_NBA_TODAY_GAMES_FROM_NBA_DATA = "http://data.nba.com/data/5s/v2015/json/mobile_teams/nba/2016/scores/00_todays_scores.json";
	
	// ------- 指定日期賽事結果 from NBA Taiwan -------
	private static final String NAME_NBA_DATE_GAMES_FROM_NBA_TAIWAN = "指定日期賽事結果 - NBA Taiwan";
	private static final String URL_NBA_DATE_GAMES_FROM_NBA_TAIWAN = "http://tw.global.nba.com/stats2/scores/daily.json?countryCode=TW&locale=zh_TW&gameDate={0}";

	// ------- 今日賽事結果 from 玩運彩 -------
	private static final String NAME_NBA_GAME_PLAY_SPORT = "今日賽事結果 - 玩運彩";
	private static final String URL_NBA_GAME_PLAY_SPORT = "https://www.playsport.cc/livescore.php?aid=3";
	
	// ------- 今日賽事結果 from 台灣運彩 -------
	private static final String NAME_NBA_GAME_SPORT_LOTTERY_TW = "今日賽事結果 - 台灣運彩";
	private static final String URL_NBA_GAME_SPORT_LOTTERY_TW = "https://www.sportslottery.com.tw/web/services/rs/betting/results/15102/1.json?sportId=s-442&categoryId=c-2942&tournamentId=t-4102&locale=tw&brandId=defaultBrand&channelId=1";
	
	public static List<NBATeam> fetchNBATeamsFromNBATaiwan() {
		String jsonData = sendGetRequest(NAME_NBA_TEAMS_FROM_NBA_TAIWAN, URL_NBA_TEAMS_FROM_NBA_TAIWAN);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NBATeamsFromNBATw resp = mapper.readValue(jsonData, NBATeamsFromNBATw.class);
			
			return resp.getTeams();
			
		} catch (IOException e) {
			throw new HttpUtilException("IOException raised while parsing json data to NBATeamsFromNBATw, url: [" + URL_NBA_TEAMS_FROM_NBA_TAIWAN + "]", e);
		}
	}

	public static List<NBASchedule> fetchNBASchedulesFromNBATaiwan() {
		String jsonData = sendGetRequest(NAME_NBA_SCHEDULES_FROM_NBA_TAIWAN, URL_NBA_SCHEDULES_FROM_NBA_TAIWAN);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NBASchedulesFromNBATw resp = mapper.readValue(jsonData, NBASchedulesFromNBATw.class);
			
			return resp.getSchedules();
			
		} catch (IOException e) {
			throw new HttpUtilException("IOException raised while parsing json data to NBASchedulesFromNBATw, url: [" + URL_NBA_SCHEDULES_FROM_NBA_TAIWAN + "]", e);
		}
	}

	public static List<NBAGame> fetchNBATodayGamesFromNBAData() {
		String jsonData = sendGetRequest(NAME_NBA_TODAY_GAMES_FROM_NBA_DATA, URL_NBA_TODAY_GAMES_FROM_NBA_DATA);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NBATodayGamesFromNBAData resp = mapper.readValue(jsonData, NBATodayGamesFromNBAData.class);
			
			return resp.getGames();
			
		} catch (IOException e) {
			throw new HttpUtilException("IOException raised while parsing json data to NBATodayGamesFromNBAData, url: [" + URL_NBA_TODAY_GAMES_FROM_NBA_DATA + "]", e);
		}
	}
	
	public static List<NBAGame> fetchNBADateGamesFromNBATaiwan(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String strDate = dateFormat.format(date);
				
		String requestUrl = MessageFormat.format(URL_NBA_DATE_GAMES_FROM_NBA_TAIWAN, strDate);
		
		String jsonData = sendGetRequest(NAME_NBA_DATE_GAMES_FROM_NBA_TAIWAN, requestUrl);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NBADateGamesFromNBATw resp = mapper.readValue(jsonData, NBADateGamesFromNBATw.class);
			
			return resp.getGames();
			
		} catch (IOException e) {
			throw new HttpUtilException("IOException raised while parsing json data to NBADateGamesFromNBATw, url: [" + requestUrl + "]", e);
		}
	}

	public static List<NBAGame> fetchNBAGamesFromPlaySport() throws Exception {
		List<NBAGame> nbaGames = new ArrayList<>();
		
		String expectedCssQuery = "div.st";

		Elements elms = getExpectedElements(expectedCssQuery, NAME_NBA_GAME_PLAY_SPORT, URL_NBA_GAME_PLAY_SPORT);
		
		for (int i = 0; i < elms.size(); i++) {
			Element elm = elms.get(i);
			
			Elements trs = elm.select("tr");
			
			NBAGame game = new NBAGame();
			
			// 懶得抓了直接抓今天的日期
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			game.setGameDate(dateFormat.format(Calendar.getInstance().getTime()));
			
			for (int j = 1; j < trs.size(); j++) {
				Element tr = trs.get(j);
				
				Element th = tr.select("th").first();
				
				Elements tds = tr.select("td");
				switch (j) {
					// 客隊
					case 1:
						game.setAwayTeamName(th.text());
						
						for (int k = 0; k < tds.size(); k++) {
							Element td = tds.get(k);
							switch (k) {
								case 0:
									Integer awayTeamScoresSum = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setAwayTeamScoresSum(awayTeamScoresSum);
									break;
	
								case 1:
									Integer awayTeam1stQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setAwayTeam1stScores(awayTeam1stQuarterScore);
									break;
									
								case 2:
									Integer awayTeam2ndQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setAwayTeam2ndScores(awayTeam2ndQuarterScore);
									break;
									
								case 3:
									Integer awayTeam3rdQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setAwayTeam3rdScores(awayTeam3rdQuarterScore);
									break;
									
								case 4:
									Integer awayTeam4thQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setAwayTeam4thScores(awayTeam4thQuarterScore);
									break;
									
								case 6:
									Integer totalScoresSum = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setTotalScoresSum(totalScoresSum);
									break;
	
								default:
									break;
							}
						}
						break;
				
					// 主隊
					case 2:
						game.setHomeTeamName(th.text());
						
						for (int k = 0; k < tds.size(); k++) {
							Element td = tds.get(k);
							switch (k) {
								case 0:
									Integer homeTeamScoresSum = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setHomeTeamScoresSum(homeTeamScoresSum);
									break;

								case 1:
									Integer homeTeam1stQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setHomeTeam1stScores(homeTeam1stQuarterScore);
									break;
									
								case 2:
									Integer homeTeam2ndQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setHomeTeam2ndScores(homeTeam2ndQuarterScore);
									break;
									
								case 3:
									Integer homeTeam3rdQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setHomeTeam3rdScores(homeTeam3rdQuarterScore);
									break;
									
								case 4:
									Integer homeTeam4thQuarterScore = !StringUtils.isEmpty(td.text()) ? Integer.parseInt(td.text()) : 0;
									game.setHomeTeam4thScores(homeTeam4thQuarterScore);
									break;
								
								default:
									break;
							}
						}
						break;
				}
			}
			nbaGames.add(game);
		}
		return nbaGames;
	}

	public static List<NBAGame> fetchNBAGamesFromSportLotteryTw() {
		String jsonData = sendGetRequest(NAME_NBA_GAME_SPORT_LOTTERY_TW, URL_NBA_GAME_SPORT_LOTTERY_TW);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NBAGamesFromTaiwanSportLottery resp = mapper.readValue(jsonData, NBAGamesFromTaiwanSportLottery.class);

			return resp.getGames();
			
		} catch (IOException e) {
			throw new HttpUtilException("IOException raised while parsing json data to NBAGamesFromTaiwanSportLottery, url: [" + URL_NBA_GAME_SPORT_LOTTERY_TW + "]", e);
		}
	}
}

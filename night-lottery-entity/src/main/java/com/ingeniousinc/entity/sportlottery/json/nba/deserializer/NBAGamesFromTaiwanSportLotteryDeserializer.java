package com.ingeniousinc.entity.sportlottery.json.nba.deserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ingeniousinc.entity.sportlottery.json.nba.NBAGamesFromTaiwanSportLottery;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBAGame;

public class NBAGamesFromTaiwanSportLotteryDeserializer extends JsonDeserializer<NBAGamesFromTaiwanSportLottery> {
	
	private final Logger logger = LoggerFactory.getLogger(NBAGamesFromTaiwanSportLotteryDeserializer.class);
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	public NBAGamesFromTaiwanSportLottery deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		NBAGamesFromTaiwanSportLottery resp = new NBAGamesFromTaiwanSportLottery();
		
		ObjectCodec oc = jp.getCodec();
		JsonNode rootNode = oc.readTree(jp);

		// 對應 code 及隊名
		JsonNode resourcesNode = rootNode.get("lexicon").get("resources");
		
		// 所有比賽資訊
		JsonNode betGameResultNodes = rootNode.get("betGameResults");
		
		Iterator<JsonNode> itGameResultNodes = betGameResultNodes.iterator();
		
		while (itGameResultNodes.hasNext()) {
			JsonNode gameResultNode = itGameResultNodes.next();
			
			// ----- 解析 JSON 資料 -----
			// 取得 id
			String id = gameResultNode.get("id").asText();

			// Data: 取得比賽日期
			long lGameDate = gameResultNode.get("d").asLong();
			Calendar calGameDate = Calendar.getInstance();
			calGameDate.setTimeInMillis(lGameDate);
			String gameDate = dateFormat.format(calGameDate.getTime());
			
			Calendar calToday = Calendar.getInstance();
			String today = dateFormat.format(calToday.getTime());
			
			// 濾掉不是今天的比賽
			if (!gameDate.equals(today)) {
				logger.warn("The game is not play at today, field \"id\": <{}>, field \"d\": <{}>, formatted-game-date: <{}>", id, lGameDate, gameDate);
				continue;
			}
			
			// 取得主隊名稱
			String homeCode = gameResultNode.get("h").asText();
			if (homeCode.equals("null")) {
				logger.warn("The home team code is null, field \"id\": <{}>, field \"d\": <{}>, formatted-game-date: <{}>", id, lGameDate, gameDate);
				continue;
			}
			String homeTeamName = resourcesNode.get(homeCode).asText();
			
			// 取得主隊各節分數
			JsonNode homeTeamScoresNode = gameResultNode.get("hpp");

			String strHomeTeam1stScore = homeTeamScoresNode.get("1").asText();
			Integer homeTeam1stScores = !strHomeTeam1stScore.isEmpty() ? Integer.parseInt(strHomeTeam1stScore) : 0;

			String strHomeTeam2ndScore = homeTeamScoresNode.get("2").asText();
			Integer homeTeam2ndScores = !strHomeTeam2ndScore.isEmpty() ? Integer.parseInt(strHomeTeam2ndScore) : 0;

			String strHomeTeam3rdScore = homeTeamScoresNode.get("3").asText();
			Integer homeTeam3rdScores = !strHomeTeam3rdScore.isEmpty() ? Integer.parseInt(strHomeTeam3rdScore) : 0;

			String strHomeTeam4thScore = homeTeamScoresNode.get("4").asText();
			Integer homeTeam4thScores = !strHomeTeam4thScore.isEmpty() ? Integer.parseInt(strHomeTeam4thScore) : 0;
			
			Integer homeTeamScoresSum = homeTeam1stScores + homeTeam2ndScores + homeTeam3rdScores + homeTeam4thScores; 
			
			// 取得客隊名稱
			String awayTeamCode = gameResultNode.get("a").asText();
			if (awayTeamCode.equals("null")) {
				logger.warn("The away team code is null, field \"id\": <{}>, field \"d\": <{}>, formatted-game-date: <{}>", id, lGameDate, gameDate);
				continue;
			}
			String awayTeamName = resourcesNode.get(awayTeamCode).asText();
			
			// 取得客隊各節分數
			JsonNode awayTeamScoresNode = gameResultNode.get("vsp");
			
			String strAwayTeam1stScore = awayTeamScoresNode.get("1").asText();
			Integer awayTeam1stScores = !strAwayTeam1stScore.isEmpty() ? Integer.parseInt(strAwayTeam1stScore) : 0;

			String strAwayTeam2ndScore = awayTeamScoresNode.get("2").asText();
			Integer awayTeam2ndScores = !strAwayTeam2ndScore.isEmpty() ? Integer.parseInt(strAwayTeam2ndScore) : 0;

			String strAwayTeam3rdScore = awayTeamScoresNode.get("3").asText();
			Integer awayTeam3rdScores = !strAwayTeam3rdScore.isEmpty() ? Integer.parseInt(strAwayTeam3rdScore) : 0;

			String strAwayTeam4thScore = awayTeamScoresNode.get("4").asText();
			Integer awayTeam4thScores = !strAwayTeam4thScore.isEmpty() ? Integer.parseInt(strAwayTeam4thScore) : 0;
			
			Integer awayTeamScoresSum = awayTeam1stScores + awayTeam2ndScores + awayTeam3rdScores + awayTeam4thScores;
			
			// 取得兩隊總得分加總
			Integer totalScoresSum = homeTeamScoresSum + awayTeamScoresSum;
			
			// ----- 儲存每場比賽資訊 -----
			NBAGame nbaGame = new NBAGame();
			
			// ----- 將資料塞入物件 -----
			nbaGame.setGameDate(gameDate);
			
			nbaGame.setHomeTeamName(homeTeamName);
			nbaGame.setHomeTeam1stScores(homeTeam1stScores);
			nbaGame.setHomeTeam2ndScores(homeTeam2ndScores);
			nbaGame.setHomeTeam3rdScores(homeTeam3rdScores);
			nbaGame.setHomeTeam4thScores(homeTeam4thScores);
			nbaGame.setHomeTeamScoresSum(homeTeamScoresSum);
			
			nbaGame.setAwayTeamName(awayTeamName);
			nbaGame.setAwayTeam1stScores(awayTeam1stScores);
			nbaGame.setAwayTeam2ndScores(awayTeam2ndScores);
			nbaGame.setAwayTeam3rdScores(awayTeam3rdScores);
			nbaGame.setAwayTeam4thScores(awayTeam4thScores);
			nbaGame.setAwayTeamScoresSum(awayTeamScoresSum);
			
			nbaGame.setTotalScoresSum(totalScoresSum);
			
			resp.addNBAGame(nbaGame);
		}
		return resp;
	}

}

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
import com.ingeniousinc.entity.sportlottery.json.nba.NBASchedulesFromNBATw;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBASchedule;

public class NBASchedulesFromNBATwDeserializer extends JsonDeserializer<NBASchedulesFromNBATw> {
	
	private final Logger logger = LoggerFactory.getLogger(NBASchedulesFromNBATwDeserializer.class);
	
	// 處理每一天的日期
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	// 處理每一場比賽時間
	private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public NBASchedulesFromNBATw deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// 暫存整包 json 回應
		NBASchedulesFromNBATw resp = new NBASchedulesFromNBATw();

		// use to process time
		Calendar cal = Calendar.getInstance();

		// 取出 root node
		ObjectCodec oc = jp.getCodec();
		JsonNode rootNode = oc.readTree(jp);
		
		// 取出每個日期資訊
		JsonNode datesNode = rootNode.get("payload").get("dates");

		Iterator<JsonNode> itDateNodes = datesNode.iterator();
		
		while (itDateNodes.hasNext()) {
			// 每天的資訊
			JsonNode dateNode = itDateNodes.next();
			
			// Data: 當天有幾場比賽
			String gameCount = dateNode.get("gameCount").asText();

			// Data: 哪一天
			long dateUtcMillis = dateNode.get("utcMillis").asLong();
			cal.setTimeInMillis(dateUtcMillis);
			String date = dateFormat.format(cal.getTime());
			
			logger.info(">>>>> Date: {}, Game Count: {} <<<<<", date, gameCount);
			
			// 取出當天所有比賽資訊
			JsonNode gameNodes = dateNode.get("games");
			
			Iterator<JsonNode> itGameNodes = gameNodes.iterator();
			
			while (itGameNodes.hasNext()) {
				// 當天各場比賽資訊
				JsonNode gameNode = itGameNodes.next();
				
				// ----- Node: profile -----
				JsonNode profileNode = gameNode.get("profile");
				
				// Data: 場次 ID
				Integer gameId = profileNode.get("gameId").asInt();

				// Data: 主場隊伍 ID
				Integer homeTeamId = profileNode.get("homeTeamId").asInt();

				// Data: 客場隊伍 ID 
				Integer awayTeamId = profileNode.get("awayTeamId").asInt();

				// Data: 場次時間
				Long gameTimeInMillis = profileNode.get("utcMillis").asLong();
				cal.setTimeInMillis(gameTimeInMillis);
				String gameTime = dateTimeFormat.format(cal.getTime());
				
				// ----- Node: homeTeam -----
				JsonNode homeTeamNode = gameNode.get("homeTeam");
				JsonNode homeTeamProfileNode = homeTeamNode.get("profile");
				
				// Data: 主隊相關資訊
				String homeTeamAbbr = homeTeamProfileNode.get("abbr").asText();
				String homeTeamCityCh = homeTeamProfileNode.get("city").asText();
				String homeTeamCityEn = homeTeamProfileNode.get("cityEn").asText();
				String homeTeamNameCh = homeTeamProfileNode.get("name").asText();
				String homeTeamNameEn = homeTeamProfileNode.get("nameEn").asText();
				
				// ----- Node: awayTeam -----
				JsonNode awayTeamNode = gameNode.get("awayTeam");
				JsonNode awayTeamProfileNode = awayTeamNode.get("profile");
				
				// Data: 客隊相關資訊
				String awayTeamAbbr = awayTeamProfileNode.get("abbr").asText();
				String awayTeamCityCh = awayTeamProfileNode.get("city").asText();
				String awayTeamCityEn = awayTeamProfileNode.get("cityEn").asText();
				String awayTeamNameCh = awayTeamProfileNode.get("name").asText();
				String awayTeamNameEn = awayTeamProfileNode.get("nameEn").asText();
				
				// ----- 儲存每場比賽資訊 -----
				NBASchedule schedule = new NBASchedule();
				
				// ----- 將資料塞進物件 -----
				schedule.setGameId(gameId);
				schedule.setGameTimeInMillis(gameTimeInMillis);
				schedule.setGameTime(gameTime);
				schedule.setHomeTeamId(homeTeamId);
				schedule.setAwayTeamId(awayTeamId);
				schedule.setHomeTeamAbbr(homeTeamAbbr);
				schedule.setHomeTeamCityCh(homeTeamCityCh);
				schedule.setHomeTeamCityEn(homeTeamCityEn);
				schedule.setHomeTeamNameCh(homeTeamNameCh);
				schedule.setHomeTeamNameEn(homeTeamNameEn);
				schedule.setAwayTeamAbbr(awayTeamAbbr);
				schedule.setAwayTeamCityCh(awayTeamCityCh);
				schedule.setAwayTeamCityEn(awayTeamCityEn);
				schedule.setAwayTeamNameCh(awayTeamNameCh);
				schedule.setAwayTeamNameEn(awayTeamNameEn);
				
				resp.addNBASchedule(schedule);
			}
		}
		return resp;
	}

}

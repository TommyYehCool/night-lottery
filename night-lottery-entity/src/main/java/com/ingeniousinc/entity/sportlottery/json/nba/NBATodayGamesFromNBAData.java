package com.ingeniousinc.entity.sportlottery.json.nba;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ingeniousinc.entity.sportlottery.json.nba.deserializer.NBATodayGamesFromNBADataDeserializer;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBAGame;

import lombok.Data;

@Data
@JsonDeserialize(using = NBATodayGamesFromNBADataDeserializer.class)
public class NBATodayGamesFromNBAData {
	
	private List<NBAGame> games = new ArrayList<>();
	
	public void addNBAGame(NBAGame game) {
		games.add(game);
	}
}

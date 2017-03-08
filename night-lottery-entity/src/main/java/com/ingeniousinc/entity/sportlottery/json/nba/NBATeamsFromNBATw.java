package com.ingeniousinc.entity.sportlottery.json.nba;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ingeniousinc.entity.sportlottery.json.nba.deserializer.NBATeamsFromNBATwDeserializer;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBATeam;

import lombok.Data;

@Data
@JsonDeserialize(using = NBATeamsFromNBATwDeserializer.class)
public class NBATeamsFromNBATw {

	private List<NBATeam> teams = new ArrayList<>();
	
	public void addNBATeam(NBATeam team) {
		teams.add(team);
	}
}

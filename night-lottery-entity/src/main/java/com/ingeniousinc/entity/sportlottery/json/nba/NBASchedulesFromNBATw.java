package com.ingeniousinc.entity.sportlottery.json.nba;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ingeniousinc.entity.sportlottery.json.nba.deserializer.NBASchedulesFromNBATwDeserializer;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBASchedule;

import lombok.Data;

@Data
@JsonDeserialize(using = NBASchedulesFromNBATwDeserializer.class)
public class NBASchedulesFromNBATw {

	private List<NBASchedule> schedules = new ArrayList<>();
	
	public void addNBASchedule(NBASchedule schedule) {
		schedules.add(schedule);
	}
}

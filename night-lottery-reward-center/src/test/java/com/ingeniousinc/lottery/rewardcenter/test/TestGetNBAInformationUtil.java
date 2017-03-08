package com.ingeniousinc.lottery.rewardcenter.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ingeniousinc.entity.sportlottery.vo.nba.NBAGame;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBASchedule;
import com.ingeniousinc.entity.sportlottery.vo.nba.NBATeam;
import com.ingeniousinc.lottery.util.nba.GetNBAInformationUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGetNBAInformationUtil {

	private Logger logger = LoggerFactory.getLogger(TestGetNBAInformationUtil.class);
	
	@Test
	public void test_01_fetchNBATeamsFromNBATaiwan() throws Exception {
		logger.info(">>>>> Starting test_01_fetchNBATeamsFromNBATaiwan");
		List<NBATeam> teams = GetNBAInformationUtil.fetchNBATeamsFromNBATaiwan(); 
		for (NBATeam team : teams) {
			logger.info(team.toString());
		}
		logger.info("<<<<< test_01_fetchNBATeamsFromNBATaiwan done");
	}
	
	@Test
	public void test_02_fetchNBASchedulesFromNBATaiwan() throws Exception {
		logger.info(">>>>> Starting test_02_fetchNBASchedulesFromNBATaiwan");
		List<NBASchedule> schedules = GetNBAInformationUtil.fetchNBASchedulesFromNBATaiwan();
		for (NBASchedule schedule : schedules) {
			logger.info(schedule.toString());
		}
		logger.info("<<<<< test_02_fetchNBASchedulesFromNBATaiwan done");
	}
	
	@Test
	public void test_03_fetchNBATodayGamesFromNBAData() throws Exception {
		logger.info(">>>>> Starting test_03_fetchNBATodayGamesFromNBAData");
		List<NBAGame> games = GetNBAInformationUtil.fetchNBATodayGamesFromNBAData();
		for (NBAGame game : games) {
			logger.info(game.toString());
		}
		logger.info("<<<<< test_03_fetchNBATodayGamesFromNBAData done");
	}
	
	@Test
	public void test_04_fetchNBADateGamesFromNBATaiwan() throws Exception {
		String testingDate = "2017-2-12";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(testingDate);
		
		logger.info(">>>>> Starting test_04_fetchNBADateGamesFromNBATaiwan");
		List<NBAGame> games = GetNBAInformationUtil.fetchNBADateGamesFromNBATaiwan(date);
		for (NBAGame game : games) {
			logger.info(game.toString());
		}
		logger.info("<<<<< test_04_fetchNBADateGamesFromNBATaiwan done");
	}

	@Test
	public void test_05_fetchNBAGamesFromPlaySport() throws Exception {
		logger.info(">>>>> Starting test_05_fetchNBAGamesFromPlaySport");
		List<NBAGame> games = GetNBAInformationUtil.fetchNBAGamesFromPlaySport();
		for (NBAGame game : games) {
			logger.info(game.toString());
		}
		logger.info("<<<<< test_05_fetchNBAGamesFromPlaySport done");
	}
	
	@Test
	public void test_06_fetchNBAGamesFromSportLotteryTw() throws Exception {
		logger.info(">>>>> Starting test_06_fetchNBAGamesFromSportLotteryTw");
		List<NBAGame> games = GetNBAInformationUtil.fetchNBAGamesFromSportLotteryTw();
		for (NBAGame game : games) {
			logger.info(game.toString());
		}
		logger.info("<<<<< test_06_fetchNBAGamesFromSportLotteryTw done");
	}
}

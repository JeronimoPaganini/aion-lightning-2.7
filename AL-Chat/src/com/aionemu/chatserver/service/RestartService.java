package com.aionemu.chatserver.service;

import com.aionemu.chatserver.ShutdownHook;
import com.aionemu.chatserver.configs.Config;
import com.aionemu.chatserver.model.RestartFrequency;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestartService {

	private static final Logger log = LoggerFactory.getLogger(RestartService.class);

	private static final RestartService instance = new RestartService();

	private RestartService() {
		RestartFrequency rf;
		try {
			rf = RestartFrequency.valueOf(Config.CHATSERVER_RESTART_FREQUENCY);
		}
		catch (Exception e) {
			log.warn("Could not find stated RestartFrequency. Using NEVER as default value!");
			rf = RestartFrequency.NEVER;
		}
		setTimer(rf);
	}

	private void setTimer(RestartFrequency frequency) {
		String[] time = getRestartTime();
		int hour = Integer.parseInt(time[0]);
		int minute = Integer.parseInt(time[1]);

		Calendar calendar = Calendar.getInstance();
		calendar.set(11, hour);
		calendar.set(12, minute);
		calendar.set(13, 0);
		boolean isMissed = calendar.getTimeInMillis() < System.currentTimeMillis();

		switch (frequency) {
			case NEVER:
				return;
			case DAILY:
				if (isMissed)
					calendar.add(6, 1);
				break;
			case WEEKLY:
				calendar.add(3, 1);
				break;
			case MONTHLY:
				calendar.add(2, 1);
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {
				RestartService.log.info("Restart task is triggered - restarting chatserver!");
				ShutdownHook.setRestartOnly(true);
				ShutdownHook.getInstance().start();
			}
		}, calendar.getTime());

		log.info("Scheduled next restart for " + calendar.getTime().toString());
	}

	private String[] getRestartTime() {
		String[] time;
		if ((time = Config.CHATSERVER_RESTART_TIME.split(":")).length != 2) {
			log.warn("You did not state a valid restart time. Using 5:00 AM as default value!");
			return new String[] { "5", "0" };
		}
		return time;
	}

	public static RestartService getInstance() {
		return instance;
	}
}

package com.aionemu.chatserver;

import com.aionemu.chatserver.network.netty.NettyServer;
import com.aionemu.chatserver.service.GameServerService;

public class ShutdownHook extends Thread {

	private static final ShutdownHook instance = new ShutdownHook();

	private static boolean restartOnly = false;

	public static ShutdownHook getInstance() {
		return instance;
	}

	public static void setRestartOnly(boolean restart) {
		restartOnly = restart;
	}

	public void run() {
		NettyServer.getInstance().shutdownAll();
		GameServerService.getInstance().setOffline();

		if (restartOnly)
			Runtime.getRuntime().halt(2);
		else
			Runtime.getRuntime().halt(0);
	}
}

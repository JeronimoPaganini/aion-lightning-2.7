package com.aionemu.chatserver.model;

public enum RestartFrequency {
	NEVER(0),
	DAILY(1),
	WEEKLY(2),
	MONTHLY(3);

	private int id;

	private RestartFrequency(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}
}

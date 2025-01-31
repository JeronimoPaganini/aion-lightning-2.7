package com.aionl.slf4j.filters;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class CraftFilter extends Filter<ILoggingEvent> {

	public FilterReply decide(ILoggingEvent loggingEvent) {
		Object message = loggingEvent.getMessage();

		if (((String) message).startsWith("[CRAFT]")) {
			return FilterReply.ACCEPT;
		}

		return FilterReply.DENY;
	}
}

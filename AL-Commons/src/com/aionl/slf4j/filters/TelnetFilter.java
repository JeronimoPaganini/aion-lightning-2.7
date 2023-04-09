/*
 * This file is part of EasyGaming-Development <http://easy-gaming.de>.
 *
 * EG-DEV <http://www.easy-gaming.de> is free software: you
 * can  redistribute  it and/or modify it under the terms
 * of  the GNU General Public License as published by the
 * Free Software Foundation, version 3 of the License.
 *
 * EG-DEV <http://www.easy-gaming.de> is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without  even  the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See  the  GNU General Public License for more details.
 * You  should  have  received  a copy of the GNU General
 * Public License along with EG-DEV <http://www.easy-gaming.de>.
 * If not,see <http://www.gnu.org/licenses/>.
 */
package com.aionl.slf4j.filters;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Log4J filter that looks if there is telnet log present in the logging event and accepts event if present.
 * Otherwise it blocks filtering.
 * 
 * @author Divinity
 */
public class TelnetFilter extends Filter
{
    /**
     * Decides what to do with logging event.<br>
     * This method accepts only log events that contain exceptions.
     * 
     * @param loggingEvent
     *          log event that is going to be filterd.
     * @return {@link org.apache.log4j.spi.Filter#ACCEPT} if telnet, {@link org.apache.log4j.spi.Filter#DENY} otherwise
     */
    @Override
    public int decide(LoggingEvent loggingEvent)
    {
        Object message = loggingEvent.getMessage();

        if (((String) message).startsWith("[TELNET"))
        {
            return ACCEPT;
        }

        return DENY;
    }
}

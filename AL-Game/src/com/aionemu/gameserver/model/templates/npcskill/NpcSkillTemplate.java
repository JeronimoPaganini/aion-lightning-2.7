/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.model.templates.npcskill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author AionChs Master
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "npcskill")
public class NpcSkillTemplate {

	@XmlAttribute(name = "id")
	protected int id;
	@XmlAttribute(name = "skillid")
	protected int skillid;
	@XmlAttribute(name = "skilllevel")
	protected int skilllevel;
	@XmlAttribute(name = "probability")
	protected int probability;
	@XmlAttribute(name = "abouthp")
	protected boolean abouthp;
	@XmlAttribute(name = "minhp")
	protected int minhp;
	@XmlAttribute(name = "maxhp")
	protected int maxhp;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the skillid
	 */
	public int getSkillid() {
		return skillid;
	}

	/**
	 * @return the skilllevel
	 */
	public int getSkillLevel() {
		return skilllevel;
	}

	/**
	 * @return the probability
	 */
	public int getProbability() {
		return probability;
	}

	/**
	 * @return the abouthp
	 */
	public boolean isAbooutHp() {
		return abouthp;
	}

	/**
	 * @return the minhp
	 */
	public int getMinhp() {
		return minhp;
	}

	/**
	 * @return the maxhp
	 */
	public int getMaxhp() {
		return maxhp;
	}
}

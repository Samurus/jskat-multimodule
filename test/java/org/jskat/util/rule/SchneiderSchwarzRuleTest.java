/**
 * JSkat - A skat program written in Java
 * by Jan Schäfer and Markus J. Luzius
 *
 * Version 0.11.0-SNAPSHOT
 * Copyright (C) 2012-03-13
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jskat.util.rule;

import static org.junit.Assert.assertTrue;

import org.jskat.AbstractJSkatTest;
import org.jskat.data.GameAnnouncement;
import org.jskat.data.GameAnnouncement.GameAnnouncementFactory;
import org.jskat.data.SkatGameData;
import org.jskat.util.GameType;
import org.jskat.util.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests schneider and schwarz rules
 */
public class SchneiderSchwarzRuleTest extends AbstractJSkatTest {

	private SkatGameData data;
	private GameAnnouncementFactory factory;

	private static SuitGrandRules clubRules = (SuitGrandRules) SkatRuleFactory.getSkatRules(GameType.CLUBS);

	/**
	 * @see BeforeClass
	 */
	@Before
	public void setUpBeforeClass() {

		data = new SkatGameData();
		factory = GameAnnouncement.getFactory();
		factory.setGameType(GameType.CLUBS);
		data.setDeclarer(Player.FOREHAND);
	}

	/**
	 * Test case 000 for schneider rule
	 */
	@Test
	public void testSchneider000() {

		data.setDeclarerPickedUpSkat(true);
		data.setAnnouncement(factory.getAnnouncement());
		assertTrue(clubRules.isSchneider(data));
	}

	/**
	 * Test case 000 for schwarz rule
	 */
	@Test
	public void testSchwarz000() {

		data.setAnnouncement(factory.getAnnouncement());
		assertTrue(clubRules.isSchwarz(data));
	}

	/**
	 * Test for casting null rules into suit/grand rules
	 */
	@Test(expected = ClassCastException.class)
	public void testCast001() {

		data.setAnnouncement(factory.getAnnouncement());
		SuitGrandRules nullRules = (SuitGrandRules) SkatRuleFactory.getSkatRules(GameType.NULL);
	}

	/**
	 * Test for casting ramsch rules into suit/grand rules
	 */
	@Test(expected = ClassCastException.class)
	public void testCast002() {

		data.setAnnouncement(factory.getAnnouncement());
		SuitGrandRules nullRules = (SuitGrandRules) SkatRuleFactory.getSkatRules(GameType.RAMSCH);
	}
}

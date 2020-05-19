/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class Genealogy {

	private Map<Ftile, Ftile> myFatherIs = new HashMap<Ftile, Ftile>();
	private final Ftile root;

	public Genealogy(Ftile root) {
		this.root = root;
		process(root);
		// System.err.println("myFatherIs=" + myFatherIs);
	}

	private void process(Ftile current) {
		final Collection<Ftile> children = current.getMyChildren();
		// System.err.println("current=" + current);
		// System.err.println("children=" + children);
		for (Ftile child : children) {
			setMyFather(child, current);
			process(child);
		}
	}

	public Ftile getMyFather(Ftile me) {
		return myFatherIs.get(me);
	}

	private void setMyFather(Ftile child, Ftile father) {
		if (myFatherIs.containsKey(child)) {
			throw new IllegalArgumentException();
		}
		myFatherIs.put(child, father);
	}

	public UTranslate getTranslate(Ftile child, StringBounder stringBounder) {
		Ftile current = child;
		UTranslate result = new UTranslate();
		while (current != root) {
			final Ftile father = getMyFather(current);
			final UTranslate tr = father.getTranslateFor(current, stringBounder);
//			System.err.println("Father=" + father);
//			System.err.println("current=" + current);
//			System.err.println("TR=" + tr);
			result = tr.compose(result);
			current = father;
		}
		return result;
	}

}

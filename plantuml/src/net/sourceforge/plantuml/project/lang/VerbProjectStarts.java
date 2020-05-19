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
package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexOptional;
import net.sourceforge.plantuml.command.regex.RegexOr;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.time.Day;

public class VerbProjectStarts implements VerbPattern {

	public Collection<ComplementPattern> getComplements() {
		return Arrays.<ComplementPattern> asList(new ComplementDate());
	}

	public IRegex toRegexOld() {
		return new RegexLeaf("starts[%s]*(the[%s]*|on[%s]*)*");
	}

	public IRegex toRegex() {
		return new RegexConcat(new RegexLeaf("start"), //
				new RegexOptional(new RegexLeaf("s")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexOr(//
						new RegexLeaf("on"),//
						new RegexLeaf("for"),//
						new RegexLeaf("the"),//
						new RegexLeaf("at") //
				)) //
		);
	}

	public Verb getVerb(final GanttDiagram project, RegexResult arg) {
		return new Verb() {
			public CommandExecutionResult execute(Subject subject, Complement complement) {
				final Day start = (Day) complement;
				assert project == subject;
				project.setStartingDate(start);
				return CommandExecutionResult.ok();
			}

		};
	}
}

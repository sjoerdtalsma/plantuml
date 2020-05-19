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
package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;

public class CommandLinkAnchor extends SingleLineCommand2<SequenceDiagram> {

	public CommandLinkAnchor() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLinkAnchor.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("ANCHOR1", "\\{([\\p{L}0-9_]+)\\}"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LINK", "\\<-\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("ANCHOR2", "\\{([\\p{L}0-9_]+)\\}"), //
				RegexLeaf.spaceZeroOrMore(), new RegexLeaf("MESSAGE", "(?::[%s]*(.*))?"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final String anchor1 = arg.get("ANCHOR1", 0);
		final String anchor2 = arg.get("ANCHOR2", 0);
		final String message = arg.get("MESSAGE", 0);
		return diagram.linkAnchor(anchor1, anchor2, message);
	}

}

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
 */
package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.FunctionsSet;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionType;
import net.sourceforge.plantuml.tim.TLineType;
import net.sourceforge.plantuml.tim.TMemory;

public class CodeIteratorReturnFunction extends AbstractCodeIterator {

	private final FunctionsSet functionsSet;

	private final TContext context;
	private final TMemory memory;
	private final List<StringLocated> logs;

	public CodeIteratorReturnFunction(CodeIterator source, TContext context, TMemory memory, FunctionsSet functionsSet,
			List<StringLocated> logs) {
		super(source);
		this.context = context;
		this.functionsSet = functionsSet;
		this.logs = logs;
		this.memory = memory;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			final StringLocated result = source.peek();
			if (result == null) {
				return null;
			}

			if (functionsSet.pendingFunction() != null
					&& functionsSet.pendingFunction().getFunctionType() == TFunctionType.RETURN_FUNCTION) {
				logs.add(result);
				if (result.getType() == TLineType.END_FUNCTION) {
					if (functionsSet.pendingFunction().doesContainReturn() == false) {
						throw EaterExceptionLocated.located(
								"This function does not have any !return directive. Declare it as a procedure instead ?",
								result);
					}
					functionsSet.executeEndfunction();
				} else {
					functionsSet.pendingFunction().addBody(result);
				}
				next();
				continue;
			}

			if (result.getType() == TLineType.DECLARE_RETURN_FUNCTION) {
				logs.add(result);
				functionsSet.executeDeclareReturnFunction(context, memory, result);
				next();
				continue;
			}

			return result;
		}
	}

}

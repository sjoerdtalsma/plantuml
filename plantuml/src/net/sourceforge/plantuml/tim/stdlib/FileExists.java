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
package net.sourceforge.plantuml.tim.stdlib;

import java.io.File;
import java.util.List;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;

public class FileExists extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%file_exists", 1);
	}

	public boolean canCover(int nbArg) {
		return nbArg == 1;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> args) throws EaterException {
		if (OptionFlags.ALLOW_INCLUDE == false) {
			return TValue.fromBoolean(false);
		}
		final String path = args.get(0).toString();
		return TValue.fromBoolean(fileExists(path));
	}

	private boolean fileExists(String path) {
		final File f = new File(path);
		return f.exists();
	}
}

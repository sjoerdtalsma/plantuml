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
package net.sourceforge.plantuml.ugraphic.eps;

import net.sourceforge.plantuml.eps.EpsGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.UClip;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.ugraphic.UImage;
import net.sourceforge.plantuml.ugraphic.UParam;
import net.sourceforge.plantuml.ugraphic.UShape;
import net.sourceforge.plantuml.ugraphic.color.ColorMapper;

public class DriverImageEps implements UDriver<EpsGraphics> {

	private final ClipContainer clipContainer;

	public DriverImageEps(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UShape ushape, double x, double y, ColorMapper mapper, UParam param, EpsGraphics eps) {

		final UImage shape = (UImage) ushape;

		final UClip clip = clipContainer.getClip();
		if (clip != null) {
			if (clip.isInside(x, y) == false) {
				return;
			}
			if (clip.isInside(x + shape.getWidth(), y + shape.getHeight()) == false) {
				return;
			}
		}

		eps.drawImage(shape.muteTransparentColor(mapper.toColor(param.getBackcolor())).getImage(), x, y);
	}

}

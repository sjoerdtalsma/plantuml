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
package net.sourceforge.plantuml.graphic;

import java.awt.geom.Point2D;

class CoordinateChange {

	private final double x1;
	private final double y1;
	private final double x2;
	private final double y2;

	private final double vect_u_x;
	private final double vect_u_y;
	private final double vect_v_x;
	private final double vect_v_y;
	private final double len;

	public CoordinateChange(Point2D p1, Point2D p2) {
		this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	public CoordinateChange(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.len = Point2D.distance(x1, y1, x2, y2);
		if (this.len == 0) {
			throw new IllegalArgumentException();
		}

		this.vect_u_x = (x2 - x1) / len;
		this.vect_u_y = (y2 - y1) / len;

		this.vect_v_x = -this.vect_u_y;
		this.vect_v_y = this.vect_u_x;

	}

	public Point2D getTrueCoordinate(double a, double b) {
		final double x = a * vect_u_x + b * vect_v_x;
		final double y = a * vect_u_y + b * vect_v_y;
		return new Point2D.Double(x1 + x, y1 + y);
	}

	public final double getLength() {
		return len;
	}

}
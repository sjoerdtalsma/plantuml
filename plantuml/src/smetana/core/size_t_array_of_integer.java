/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of Smetana.
 * Smetana is a partial translation of Graphviz/Dot sources from C to Java.
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * This translation is distributed under the same Licence as the original C program.
 * 
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC
 * LICENSE ("AGREEMENT"). [Eclipse Public License - v 1.0]
 * 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES
 * RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 * 
 * You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package smetana.core;

import smetana.core.amiga.StarArrayOfInteger;

/**
 * "Pseudo size" of a C structure. In C, this is the actual size of the structure. In Java, this is an indication to
 * know which structure we are going to allocate.
 * 
 * @author Arnaud Roques
 * 
 */
public class size_t_array_of_integer implements size_t {

	final private int nb;

	public size_t_array_of_integer(int nb) {
		this.nb = nb;
	}

	public int getInternalNb() {
		return nb;
	}

	public boolean isZero() {
		return nb == 0;
	}

	public __ptr__ malloc() {
		return new StarArrayOfInteger(__array_of_integer_impl__.mallocInteger(nb));
	}

	public size_t negate() {
		throw new UnsupportedOperationException();
	}

	public size_t plus(int length) {
		throw new UnsupportedOperationException();
	}

	public boolean isStrictPositive() {
		throw new UnsupportedOperationException();
	}

	public boolean isStrictNegative() {
		throw new UnsupportedOperationException();
	}

	public __ptr__ realloc(Object old) {
		((StarArrayOfInteger) old).realloc(nb);
		return (__ptr__) old;
	}

}

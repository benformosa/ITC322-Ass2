import java.lang.Math;

/**
 * A representation of a Monomial of a single mathematical variable. A Monomial
 * is a constant times a variable raised to the power of an exponent.
 */
public class Monomial implements Cloneable {
	// assume that the coefficient will always be an integer
	private int coefficient;
	// an exponent in a polynomial will always be an integer
	private int exponent;

	/**
	 * Constructs a Monomial
	 * 
	 * @param c
	 *            the coefficient of the Monomial
	 * @param e
	 *            the exponent of the Monomial
	 */
	public Monomial(int c, int e) {
		coefficient = c;
		exponent = e;
	}

	/**
	 * Returns a copy of this Monomial.
	 * 
	 * @return a copy of this Monomial
	 */
	@Override
	public Monomial clone() {
		return new Monomial(this.coefficient, this.exponent);
	}

	/**
	 * Return the derivative of this Monomial.
	 * 
	 * @return the derivative of this Monomial
	 */
	public Monomial differentiate() {
		return new Monomial((this.coefficient * this.exponent),
				(this.exponent - 1));
	}

	/**
	 * Test if this Monomial is the same as a given object. Based on Eclipse
	 * automatically generated method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Monomial)) {
			return false;
		}
		// equal if coefficient and exponent are equal
		Monomial other = (Monomial) obj;
		if (coefficient != other.coefficient) {
			return false;
		}
		if (exponent != other.exponent) {
			return false;
		}
		return true;
	}

	/**
	 * Return the coefficient of this Monomial
	 * 
	 * @return the coefficient of this Monomial
	 */
	public int getCoefficient() {
		return this.coefficient;
	}

	/**
	 * Return the exponent of this Monomial.
	 * 
	 * @return the exponent of this Monomial
	 */
	public int getExponent() {
		return this.exponent;
	}

	/**
	 * Returns the hash code value for this Monomial. Eclipse automatically
	 * generated method.
	 * 
	 * @return the hash code value for this Monomial
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coefficient;
		result = prime * result + exponent;
		return result;
	}

	/**
	 * Set the coefficient of this Monomial.
	 * 
	 * @param c
	 *            new coefficient of this Monomial
	 */
	public void setCoefficient(int c) {
		this.coefficient = c;
	}

	/**
	 * Set the exponent of this Monomial.
	 * 
	 * @param e
	 *            new exponent of this Monomial
	 */
	public void setExponent(int e) {
		this.exponent = e;
	}

	/**
	 * Return an array representing the Mononmial.
	 * 
	 * @return array Array with two elements; the coefficient and the exponent.
	 */
	public int[] toArray() {
		int[] array = new int[2];

		array[0] = this.coefficient;
		array[1] = this.exponent;

		return array;
	}

	/**
	 * Returns a String containing a simply formatted representation of this
	 * Monomial.
	 * 
	 * @return a representation of this Monomial, in the form: <pre><i>c</i> <i>e</i></pre>
	 */
	public String toFile() {
		return String.format("%d %d", this.coefficient, this.exponent);
	}

	/**
	 * Returns a String containing a nicely formatted representation of this
	 * Monomial.
	 * 
	 * @return a representation of this Monomial, in the form: <pre><i>c</i>x^<i>e</i></pre>
	 */
	@Override
	public String toString() {
		String s = "";

		// 0*x == 0
		if (coefficient == 0) {
			return "0";
		}

		// cx^0 == c
		if (exponent == 0) {
			return "" + coefficient;
		}

		// cx^e == cx^e
		if (Math.abs(coefficient) > 1) {
			s += coefficient;
		}

		// -1x^e == -x^e
		if (coefficient == -1) {
			s += "-";
		}

		if (exponent != 1) {
			s += "x^" + exponent;
		} else {
			// cx^1 = cx
			s += "x";
		}

		return s;
	}
}

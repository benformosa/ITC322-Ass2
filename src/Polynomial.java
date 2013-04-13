import java.io.*;
import java.lang.Integer;
import java.math.BigInteger;
import java.util.Iterator;

/**
 * A representation of a polynomial as a linked list. Each term in the
 * polynomial is a PolyNode.
 */
public class Polynomial implements Cloneable, Iterable<PolyNode> {
	private PolyNode first;

	/**
	 * Constructs a Polynomial with no terms.
	 */
	public Polynomial() {
		this.first = null;
	}

	/**
	 * Constructs a Polynomial from Standard Input.
	 * 
	 * @param flag
	 *            a Boolean of any value. Used only to differentiate this
	 *            constructor from the empty Polynomial constructor
	 */
	public Polynomial(boolean flag) throws IOException {
		this(new InputStreamReader(System.in));
	}

	/**
	 * Constructs a Polynomial with the terms from a two dimensional array.
	 * 
	 * @param terms
	 *            array of arrays of integers. The inner array has two elements;
	 *            the coefficient and the exponent.
	 */
	public Polynomial(int[][] terms) {
		this();
		for (int i = 0; i < terms.length; i++) {
			this.insert(new Monomial(terms[i][0], terms[i][1]));
		}
	}

	/**
	 * Constructs a Polynomial with terms read from a Reader. Each term is read
	 * from a single line, with the coefficient and exponent separated with a
	 * space. An empty line or end of the reader will end the Polynomial
	 * 
	 * @param r
	 *            a Reader
	 */
	public Polynomial(Reader r) throws IOException, NumberFormatException {
		this();
		BufferedReader br = new BufferedReader(r);
		String line;

		// loop until an empty line or EOF
		while ((line = br.readLine()) != null && !("".equals(line))) {
			if (line.contains(" ")) {
				this.insert(new Monomial(Integer.parseInt(line.split(" ")[0]),
						Integer.parseInt(line.split(" ")[1])));
				// if a line doesn't contain a space, assume that only the
				// coefficient is given and the exponent is zero
			} else {
				this.insert(new Monomial(Integer.parseInt(line), 0));
			}
		}
	}

	/**
	 * Constructs a Polynomial from a file.
	 * 
	 * @param filename
	 *            file from which to read terms
	 */
	public Polynomial(String filename) throws FileNotFoundException,
			IOException {
		this(new FileReader(filename));
	}

	/**
	 * Returns the sum of an integer and this Polynomial.
	 * 
	 * @param b
	 *            the integer to add to this Polynomial
	 * @return the sum of b and this Polynomial
	 */
	public Polynomial add(int b) {
		Polynomial temp = this.clone();
		// insert a new Monomial with an exponent of zero
		temp.insert(new Monomial(b, 0));
		return temp;
	}

	/**
	 * Returns the sum of a Monomial and this Polynomial.
	 * 
	 * @param m
	 *            the Monomial to add to this Polynomial
	 * @return the sum of m and this Polynomial
	 */
	public Polynomial add(Monomial m) {
		Polynomial temp = this.clone();
		// insert the Monomial
		temp.insert(m);
		return temp;
	}

	/**
	 * Returns the sum of a Polynomial and this Polynomial.
	 * 
	 * @param p
	 *            Polynomial to add
	 * @return sum of p and this Polynomial
	 */
	public Polynomial add(Polynomial p) {
		Polynomial temp = this.clone();
		// insert the Polynomial
		temp.insert(p);
		return temp;
	}

	/**
	 * Returns an copy of this Polynomial
	 * 
	 * @return a copy of this Polynomial
	 */
	@Override
	public Polynomial clone() {
		Polynomial temp = new Polynomial();

		// insert all the terms of this Polynomial
		for (PolyNode i : this) {
			temp.insert(i);
		}
		return temp;
	}

	/**
	 * Returns the derivative of this Polynomial.
	 * 
	 * @return the derivative of this Polynomial
	 */
	public Polynomial differentiate() {
		Polynomial temp = new Polynomial();

		for (PolyNode i : this) {
			temp.insert(i.differentiate());
		}
		return temp;
	}

	/**
	 * Test if this Polynomial is the same as a given object. Based on Eclipse
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
		if (!(obj instanceof Polynomial)) {
			return false;
		}
		Polynomial other = (Polynomial) obj;

		// must be same length to be equal
		if (other.getLength() != this.getLength()) {
			return false;
		}

		// iterate over each Polynomial and compare each term
		this.sort();
		other.sort();

		for (PolyNode i : this) {
			for (PolyNode j : other) {
				if (!i.equals(j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the degree of this polynomial, that is, the highest exponent of
	 * all the terms in this Polynomial.
	 * 
	 * @return the degree of this polynomial
	 */
	public int getDegree() {
		int degree = 0;

		// iterate over the Polynomial
		for (PolyNode i : this) {
			// if this exponent is greater than the highest so far, set this as
			// the new highest
			if (i.getExponent() > degree) {
				degree = i.getExponent();
			}
		}
		return degree;
	}

	/**
	 * Returns the first PolyNode in this Polynomial.
	 * 
	 * @return the first PolyNode in this Polynomial
	 */
	public PolyNode getFirst() {
		return this.first;
	}

	/**
	 * Returns the number of terms in this Polynomial.
	 * 
	 * @return the number of terms in this Polynomial
	 */
	@SuppressWarnings("unused")
	public int getLength() {
		int length = 0;

		for (PolyNode i : this) {
			length++;
		}
		return length;
	}

	/**
	 * Returns the hash code value for this Polynomial
	 * 
	 * @return the hash code value for this Polynomial
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		// combine the hash codes of each term
		for (PolyNode i : this) {
			result = prime * result + ((i == null) ? 0 : i.hashCode());
		}
		return result;
	}

	/**
	 * Inserts a Monomial into this Polynomial. If a term exists in this
	 * Polynomial, the given Monomial is added mathematically.
	 * 
	 * @param m
	 *            Monomial to insert
	 */
	public void insert(Monomial m) {
		int e = m.getExponent();

		// if the coefficient is zero, this term is ignored
		if (m.getCoefficient() == 0) {
			return;
		}

		// search this Polynomial for a term with the same exponent. If one
		// exists, add m to the term.
		for (PolyNode i : this) {
			if (i.getExponent() == e) {
				i.setCoefficient(i.getCoefficient() + m.getCoefficient());
				return;
			}
		}

		// if no term with the same exponent exists, create a new PolyNode
		PolyNode n = new PolyNode(m);
		n.setNext(this.first);
		this.first = n;
	}

	/**
	 * Inserts a Polynomial into this Polynomial.
	 * 
	 * @param p
	 *            Polynomial to insert
	 */
	public void insert(Polynomial p) {
		// iterate over p, insert each term into this Polynomial
		for (PolyNode i : p) {
			this.insert(i);
		}
	}

	/**
	 * Returns an Iterator over the terms of this Polynomial
	 * 
	 * @return an Iterator over the terms of this Polynomial
	 */
	@Override
	public Iterator<PolyNode> iterator() {
		return new PolynomialIterator<PolyNode>(this);
	}

	/**
	 * Returns the product of this polynomial and a constant.
	 * 
	 * @param b
	 *            constant to multiply this polynomial by
	 * @return this Monomial multiplied by b
	 */
	public Polynomial multiply(int b) {
		// create an empty Polynomial
		Polynomial temp = new Polynomial();

		for (PolyNode i : this) {
			// insert each term of this Polynomial multiplied by b
			temp.insert(new Monomial(i.getCoefficient() * b, i.getExponent()));
		}
		return temp;
	}

	/**
	 * Returns the product of this Polynomial and a Monomial.
	 * 
	 * @param m
	 *            monomial to multiply this Polynomial by
	 * @return this Polynomial multiplied by m
	 */
	public Polynomial multiply(Monomial m) {
		Polynomial temp = new Polynomial();

		for (PolyNode i : this) {
			// insert each term of this Polynomial, multiplying the coefficients
			// and adding exponents
			temp.insert(new Monomial(i.getCoefficient() * m.getCoefficient(), i
					.getExponent() + m.getExponent()));
		}
		return temp;
	}

	/**
	 * Returns the product of this Polynomial and another Polynomial.
	 * 
	 * @param p
	 *            Polynomial to multiply this Polynomial by
	 * @return this Polynomial multiplied by p
	 */
	public Polynomial multiply(Polynomial p) {
		Polynomial temp = new Polynomial();

		// create a new, empty Polynomial and insert each term of p multiplied
		// by this Polynomial
		for (PolyNode i : this) {
			temp.insert(p.multiply(i));
		}
		return temp;
	}

	/**
	 * Solve this polynomial for the given value of x
	 * 
	 * @param x
	 *            value to give x in this polynomial
	 * @return calculated value of this polynomial
	 */
	public long solve(long x) {
		long solution = 0;

		for (PolyNode i : this) {
			// the solution doesn't actually need to be stored as a
			// BigInteger, but the solution cannot be stored as a
			// double if it is too large.
			// this line calculates the solution to a term using the
			// BigInteger.pow method.
			solution += (new BigInteger("" + i.getCoefficient())
					.multiply((new BigInteger("" + x)).pow(i.getExponent()))
					.longValue());

			// the following line is probably sufficient, but fails when the
			// solution of a term is greater than can be stored in a double

			// solution += i.getCoefficient() * Math.pow(x, i.getExponent());
		}
		return solution;
	}

	/**
	 * Sort this Polynomial by exponent for proper display
	 */
	public void sort() {
		// convert this to an array
		int[][] array = this.toArray();

		// sort the array, defining a Comparator to compare the exponents
		java.util.Arrays.sort(array, new java.util.Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return Long.compare(a[1], b[1]);
			}
		});

		// convert the array back into a Polynomial
		Polynomial sorted = new Polynomial(array);
		this.first = sorted.getFirst();
	}

	/**
	 * Returns an array representing the Polynomial.
	 * 
	 * @return an array of arrays of integers. The inner array has two elements;
	 *         the coefficient and the exponent.
	 */
	public int[][] toArray() {
		int length = getLength();
		int[][] array = new int[length][length];
		int j = 0;

		// iterate over the Polynomial and fill in the array
		for (PolyNode i : this) {
			array[j] = i.toArray();
			j++;
		}
		return array;
	}

	/**
	 * Returns a String containing a simply formatted representation of this
	 * Polynomial.
	 * 
	 * @return a representation of this Monomial, in the form:
	 * <pre>
	 * <i>c1</i> <i>e1</i>
	 * <i>c2</i> <i>e2</i>
	 * ...
	 * </pre>
	 */
	public String toFile() {
		// get the system specified line separator. Seems to only be needed for
		// file output, not standard output
		String eol = System.getProperty("line.separator");
		String s = "";

		this.sort();
		for (PolyNode i : this) {
			String newline = "";

			// the first term doesn't need a newline
			if (i != this.first) {
				newline = eol;
			}
			s = s + newline + i.toFile();
		}
		return s;
	}

	/**
	 * Returns a String containing a nicely formatted representation of this
	 * Polynomial.
	 * 
	 * @return a representation of this Polynomial, in the form:
	 *         <pre><i>c1</i>x^<i>e1</i> + <i>c2</i>x^<i>e2</i> + ...</pre>
	 */
	@Override
	public String toString() {
		// an empty Polynomial should print as 0
		if (this.equals(new Polynomial())) {
			return "0";
		}

		// first, sort the Polynomial
		this.sort();

		String s = new String("");

		for (PolyNode i : this) {
			String operator = "";

			// the first term will never be preceded by anything
			if (i != this.first) {
				if (i.getCoefficient() >= 0) {
					// precede each element with a +, if the coefficient is
					// positive
					operator = " + ";
				} else {
					// as the negative sign is part of Monomial.toString, just
					// use a space
					operator = " ";
				}
			}
			s = s + operator + i.toString();
		}
		return s;
	}
}
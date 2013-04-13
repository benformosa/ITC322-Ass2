/**
 * A single node of a Polynomial, representing one term.
 */
public class PolyNode extends Monomial {
	private PolyNode next;

	/**
	 * Construct a PolyNode.
	 * 
	 * @param c
	 *            the coefficient of the Monomial
	 * @param e
	 *            the exponent of the Monomial
	 */
	public PolyNode(int c, int e) {
		super(c, e);
	}

	/**
	 * Construct a PolyNode, given a Monomial.
	 * 
	 * @param m
	 *            Monomial to construct PolyNode from
	 */
	public PolyNode(Monomial m) {
		super(m.getCoefficient(), m.getExponent());
	}

	/**
	 * Test if this PolyNode is the same as a given object. Eclipse
	 * automatically generated method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		//two equal PolyNodes are also equal Monomials
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof PolyNode)) {
			return false;
		}
		PolyNode other = (PolyNode) obj;
		if (next == null) {
			if (other.next != null) {
				return false;
			}
		} else if (!next.equals(other.next)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the next PolyNode in the Polynomial
	 * 
	 * @return the next PolyNode in the Polynomial
	 */
	public PolyNode getNext() {
		return next;
	}

	/**
	 * Returns the hash code value for this PolyNode. Eclipse automatically
	 * generated method.
	 * 
	 * @return the hash code value for this PolyNode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		return result;
	}

	/**
	 * Sets the next PolyNode in the Polynomial
	 * 
	 * @param n
	 *            the next PolyNode in the Polynomial
	 */
	public void setNext(PolyNode n) {
		this.next = n;
	}
}

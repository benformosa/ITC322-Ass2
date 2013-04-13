import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates over terms in Polynomial.
 * 
 * @param <T>
 *            Type of item in Polynomial. Only PolyNode is supported.
 */
public class PolynomialIterator<T> implements Iterator<PolyNode> {
	// create a new PolyNode
	private PolyNode node = new PolyNode(0, 0);

	/**
	 * Create a PolynomialIterator for a given Polynomial
	 * 
	 * @param p
	 *            Polynomial to create Iterator for
	 */
	public PolynomialIterator(Polynomial p) {
		// points the PolyNode at the first term in the Polynomial
		this.node.setNext(p.getFirst());
	}

	/**
	 * Returns true if there is another term in the Polynomial
	 * 
	 * @return true if there is another term in the Polynomial
	 */
	@Override
	public boolean hasNext() {
		if (node.getNext() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the next PolyNode in the Polynomial
	 * 
	 * @return the next PolyNode in the Polynomial
	 */
	@Override
	public PolyNode next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		node = node.getNext();
		return node;
	}

	/**
	 * Removing the current PolyNode from the Polynomial is not supported
	 * 
	 * @throws java.util.UnsupportedOperationException This exception is thrown every time this method is called
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException(
				"cannot remove terms from Polynomial");
	}
}

import java.io.*;

/**
 * Runs a suite of tests to demonstrate the functionality of the class
 * Polynomial, and associated classes.
 */
public class PolynomialTester {
	/**
	 * Runs a suite of tests of the Polynomial class.
	 * Expects test1.txt and test2.txt in the same directory, both being text file representation of polynomials.
	 * 
	 * @param args No arguments used
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		 System.out.println("test Monomial.toString() special cases.\n" +
		 "Three lines will be printed, the monomial being tested, the output, and true or false depending on if it matches the correct output."
		 );
		 
		 //0*0 
		 Monomial m = new Monomial(0, 0); System.out.println("0x^0:");
		 System.out.println(m); System.out.println(m.toString().equals("0"));
		 System.out.println();
		 
		 //1*1
		 m = new Monomial(1, 0); System.out.println("1x^0:");
		 System.out.println(m); System.out.println(m.toString().equals("1"));
		 System.out.println();
		 
		 //-1*1
		 m = new Monomial(-1, 0); System.out.println("-1x^0:");
		 System.out.println(m); System.out.println(m.toString().equals("-1"));
		 System.out.println();
		 
		 //2*1
		 m = new Monomial(2, 0); System.out.println("2x^0:");
		 System.out.println(m); System.out.println(m.toString().equals("2"));
		 System.out.println();
		 
		 //0*x
		 m = new Monomial(0, 1); System.out.println("0x^1:");
		 System.out.println(m); System.out.println(m.toString().equals("0"));
		 System.out.println();
		 
		 //0*x^-1
		 m = new Monomial(0, -1); System.out.println("0x^-1:");
		 System.out.println(m); System.out.println(m.toString().equals("0"));
		 System.out.println();
		 
		 //1*1
		 m = new Monomial(1, 1); System.out.println("1x^1:");
		 System.out.println(m); System.out.println(m.toString().equals("x"));
		 System.out.println();
		 
		 //1*x^-1
		 m = new Monomial(1, -1); System.out.println("1x^-1:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("x^-1"));
		 System.out.println();
		 
		 //1*x^2
		 m = new Monomial(1, 2); System.out.println("1x^2:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("x^2")); System.out.println();
		 
		 //-1*x^2
		 m = new Monomial(-1, 2); System.out.println("-1x^2:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("-x^2"));
		 System.out.println();
		 
		 //2*x^2
		 m = new Monomial(2, 2); System.out.println("2x^2:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("2x^2"));
		 System.out.println();
		 
		 //1*x^-2
		 m = new Monomial(1, -2); System.out.println("1x^-2:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("x^-2"));
		 System.out.println();
		 
		 //-1*x^-2
		 m = new Monomial(-1, -2); System.out.println("-1x^-2:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("-x^-2"));
		 System.out.println();
		 
		 //2*x^-2
		 m = new Monomial(2, -2); System.out.println("2x^-2:");
		 System.out.println(m);
		 System.out.println(m.toString().equals("2x^-2"));
		 System.out.println();
		 
		// load the two provided polynomials

		// read from file
		System.out.println("loading polynomial test1 from file test1.txt");
		Polynomial test1 = new Polynomial("test1.txt");

		System.out.println("test1");
		System.out.println(test1.toString());

		// read from input
		System.out
				.println("create polynomial from stdin. Please type the following, or blank to load from file:\n"
						+ "7 9\n"
						+ "2 8\n"
						+ "5 6\n"
						+ "2 5\n"
						+ "2 3\n"
						+ "9 2\n"
						+ "-7 1\n");
		Polynomial test2 = new Polynomial(true);

		// if test2 is empty, load the polynomial from file
		if (test2.equals(new Polynomial())) {
			test2 = new Polynomial("test2.txt");
		}

		Polynomial p;
		System.out.println("test2");
		System.out.println(test2.toString());

		System.out.println("\nnumber of terms in test1");
		System.out.println(test1.getLength());

		System.out.println("highest exponent in test1");
		System.out.println(test1.getDegree());

		System.out.println("print empty polynomial");
		System.out.println(new Polynomial());

		System.out.println("\nAddition:");
		// add constant
		System.out.println("test1 + 5");
		System.out.println(test1.add(5));

		// add monomial with degree not present in polynomial
		System.out.println("test1 - 12x");
		System.out.println(test1.add(new Monomial(-12, 1)));

		// add monomial with degree present in polynomial
		System.out.println("test1 + 3x^5");
		System.out.println(test1.add(new Monomial(3, 5)));

		// cancel out a term
		System.out.println("test1 + 4x^4");
		System.out.println(test1.add(new Monomial(4, 4)));

		// add two polynomials
		System.out.println("test1 + test2");
		p = test1.add(test2);
		System.out.println(p);
		// print true if the result is correct according to WolframAlpha
		System.out.println(p.toString().equals(
				"19x^9 + 2x^8 + 10x^6 + 15x^5 -4x^4 + 4x^3 + 20x^2 -7x -6"));

		System.out.println("test1 - test2");
		p = test1.add(test2.multiply(-1));
		System.out.println(p);
		System.out.println(p.toString().equals(
				"5x^9 -2x^8 + 11x^5 -4x^4 + 2x^2 + 7x -6"));

		// example from task definition
		System.out.println("(27x^3 + 15x^2 + 12x) + (18x^2 + 10x + 8)");
		p = new Polynomial(new int[][] { { 27, 3 }, { 15, 2 }, { 12, 1 } })
				.add(new Polynomial(new int[][] { { 18, 2 }, { 10, 1 },	{ 8, 0 } }));
		System.out.println(p);
		System.out.println(p.toString().equals("27x^3 + 33x^2 + 22x + 8"));

		System.out.println("\nMultiplication:");
		// multiply by constant
		System.out.println("test1 * 5");
		p = test1.multiply(5);
		System.out.println(p);
		System.out.println(p.toString().equals(
				"60x^9 + 25x^6 + 65x^5 -20x^4 + 10x^3 + 55x^2 -30"));

		// multiply by -1
		System.out.println("test1 * -1");
		p = test1.multiply(-1);
		System.out.println(p);
		System.out.println(p.toString().equals(
				"-12x^9 -5x^6 -13x^5 + 4x^4 -2x^3 -11x^2 + 6"));

		// multiply by monomial
		System.out.println("test1 * 3x^4");
		p = test1.multiply(new Monomial(3, 4));
		System.out.println(p);
		System.out.println(p.toString().equals(
				"36x^13 + 15x^10 + 39x^9 -12x^8 + 6x^7 + 33x^6 -18x^4"));

		// multiply by polynomial
		System.out.println("test1 * test2");
		p = test1.multiply(test2);
		System.out.println(p);
		System.out
				.println(p
						.toString()
						.equals("84x^18 + 24x^17 + 95x^15 + 125x^14 -2x^13 + 55x^12 + 264x^11 -56x^10 -30x^9 + 118x^8 + 96x^7 -153x^6 + 56x^5 + 85x^4 -89x^3 -54x^2 + 42x"));

		System.out.println("\nDifferentiation:");
		System.out.println("Differential of test1");
		p = test1.differentiate();
		System.out.println(p);
		System.out.println(p.toString().equals(
				"108x^8 + 30x^5 + 65x^4 -16x^3 + 6x^2 + 22x"));

		System.out.println("Differential of test2");
		p = test2.differentiate();
		System.out.println(p);
		System.out.println(p.toString().equals(
				"63x^8 + 16x^7 + 30x^5 + 10x^4 + 6x^2 + 18x -7"));

		System.out.println("\nSolve for x");
		System.out.println("test1, given x = 2");
		long y = test1.solve(2);
		System.out.println(y);
		System.out.println(y == 6870);

		System.out.println("test2, given x = 9");
		y = test2.solve(9);
		System.out.println(y);
		long z = 2800814292L;
		System.out.println(y == z);
	}
}

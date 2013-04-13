Ben Formosa
Student No. 11429074

ICT322 Assessment 1

Manifest:
README.txt	- this file
src/Monomial.java	- class representing a single term in a polynomial
src/PolyNode.java	- subclass of Monomial, a single node in a Polynomial
src/Polynomial.java	- class representing a polynomial as a linked list of PolyNodes
src/PolynomialIterator.java - Iterator for Polynomial
src/PolynomialTester.java	- testing suite for Polynomial and associated classes.
bin/		- directory for compiled classes
.classpath	- eclipse classpath file
.project	- eclipse project file
.settings/	- eclipse settings directory
test1.txt, test2.txt	- text file representation of a polynomial, for test input

Building and Running:
#Build javadoc:
	javadoc -d doc src/*
#Compile:
	javac -sourcepath src -d bin src/*.java
#Run:
	java -cp bin PolynomialTester
	#Follow the instructions in PolynomialTester
	
Explanation:
I created a base class, Monomial, to represent a single term in a polynomial. The coefficient and exponent are stored as ints.
This class contains basic functions that can be applied to a single term, such as a toString and differentiate.

To use this as an element in a linked list, I created a subclass, PolyNode, with additional data, the next PolyNode in the list.
Being a subclass allows using all the methods of Monomial.

The class Polynomial has one member variable, first, which is the first PolyNode in the list.
I've defined several constructors for various usages, including constructors which accept an array of arrays of integers to define all the terms in the polynomial, a string containing a filename to read and one that accepts a boolean.
public Polynomial(Boolean n) throws IOException is one I was unsure of how to implement. The specification calls for a constructor which accepts interactive input, which this does, but the way I've differentiated this from the no-arguments constructor is by passing a boolean value which is ignored.
This seems to be a bit of a kludge, but so does accepting interactive input in a constructor. I could have also created a method to read interactive input as an alternative.

The way that the linked list works is that a Polynomial object is created, with the variable first set to null. When a term is to be added, a new PolyNode is inserted.
The variable first is copied to the variable next in the new PolyNode, then first is set to the new PolyNode. Java passes references, so to set next to a reference to the object, we can just set it with the = operator.
In order to simplify iterating over the Polynomial, I've implemented Iterator, allowing the use of a for-each loop over a Polynomials

The interactive and file input constructors both pass a Reader to another constructor which reads lines from that Reader and uses the insert method to put together a Polynomial.
The insert method will create a new PolyNode, unless one exists with the same exponent, in which case it will simply add the coefficients.
Please note that the insert method is the InsertTerm method as required in the task definition.

The getDegree method does a simple linear search over the whole Polynomial, checking if each exponent is higher than the highest so far.

The add methods create a clone of the Polynomial and then inserts the object given as the argument.
I implemented the Cloneable interface to simplify the add method.

The multiply methods create a new empty Polynomial and populate it with the product of the Polynomial and the argument.

The solve method iterates over the Polynomial, solving each term and adding the results together. I've used the Math.pow() method to find exponents.
This method caused me some issues as it can result in very large numbers. I used the BigInteger class to get accurate exponentiation. Math.pow takes and returns doubles, which give inaccurate results for very large numbers.

In order to properly display the Polynomial as a string, I first had to ensure that it was sorted by exponent.
I investigated implementing Comparable in Monomial, but settled for converting the Polynomial to an int[][], sorting it using Array.sort, specifying my own comparator, then converting back to a Polynomial. The Polynomial is not sorted at all times, it needs to be explicitly sorted. I've made sure to perform a sort before printing the Polynomial.
Printing the Polynomial has several special cases to consider, such as the coefficients and exponents -1, 0, 1. This is addressed using multiple if statements to test for each case.

To test the functionality of Polynomial, I created PolynomialTester.java. This class runs through a list of operations, displaying the output of each, and for more complex operations, comparing the output with a known correct value.

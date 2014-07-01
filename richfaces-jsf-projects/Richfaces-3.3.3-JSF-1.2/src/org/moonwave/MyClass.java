package org.moonwave;

public class MyClass {

	public int multiply(int x, int y) {
	    // the following is just an example
	    if (x > 999) {
	      throw new IllegalArgumentException("X should be less than 1000");
	    }
	    return x * y;
	}

	public int power(int a, int b) throws Exception{
		if (a < -20) {
			throw new Exception();
		}
		return a * b;
	}
}

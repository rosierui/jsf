package org.moonwave;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class MathTest {

	
	@BeforeClass 
	public static void beforeClass() {
		System.out.println("beforeClass");		
	}

	@AfterClass 
	public static void afterClass() {
	
		System.out.println("afterClass");		
	}
	
	@Before 
	public void beforMethod() {
		System.out.println("beforMethod");		
	}
	
	@After 
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@Ignore 
	@Test
	public void testMethodIgnore() {
		System.out.println("testMethodIgnore");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrown() {
	    MyClass tester = new MyClass();
	    tester.multiply(900, 5);
	}
	  
	@Test
	public void testMultiply() {
		   // MyClass is tested
		   MyClass tester = new MyClass();
		   
		   // check if multiply(10,5) returns 50
		   assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
		   
		
	}

	/**
	 * 5.5. Testing exception
		The @Test (expected = Exception.class) annotation is limited as it can 
		only test for one exception. To test exceptions you can use 
		the following test pattern.
		
		try {
		   mustThrowException(); 
		   fail();
		} catch (Exception e) {
		   // expected
		   // could also check for message of exception, etc.
		} 

		http://www.vogella.com/tutorials/JUnit/article.html
		
	 * @throws Exception
	 */
	@Test(expected=Exception.class)
//	@Test
	public void testPower() throws Exception {
		   // MyClass is tested
		   MyClass tester = new MyClass();
		   tester.power(-1, 5);
		   //fail(); // see above
		   // check if multiply(10,5) returns 50
//		   assertEquals("10 x 5 must be 50", 50, tester.power(10, 5));
		   
		
	}
}

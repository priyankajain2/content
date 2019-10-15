package junitlearning;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JunitPracticeTest {
	private JunitPractice junit;
	@BeforeEach
	void doIt() {
		 junit = new JunitPractice();
	}
	@Test
	void testAddNumbernegative() {
			
			assertNotEquals(2, junit.addNumber(10,10));
	}
	@Test
	void testAddNumberpositive() {
		
		assertEquals(20, junit.addNumber(10,10));
}


}

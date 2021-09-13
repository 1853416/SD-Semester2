package org.jacoco.examples.maven.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {

	@Test
	void test() {
		assertTrue(isValidEmail("name@email.com"));
	}
}
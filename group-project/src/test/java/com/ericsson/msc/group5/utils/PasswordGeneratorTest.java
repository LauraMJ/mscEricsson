package com.ericsson.msc.group5.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordGeneratorTest {

	@Test
	public void testGenerate() {
		String password = "password";
		
		assertEquals(PasswordGenerator.generate(password), "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=");
	}

}

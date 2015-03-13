package com.ericsson.msc.group5.utils;

import org.jboss.security.auth.spi.Util;
/**
 * Password Generator containing password generating methods.
 */
public class PasswordGenerator {
	public static void main(String[] args) {
		String password = "admin";
		System.out.println(PasswordGenerator.generate(password));
	}

	public static String generate(String password) {
		return Util.createPasswordHash("SHA-256", "BASE64", null, null,
				password);
	}
}
package com.ericsson.msc.group5.services.ejb;

import org.jboss.security.auth.spi.Util;
import com.ericsson.msc.group5.services.PasswordGeneratorService;

/**
 * Password Generator containing password generating methods.
 */
public class PasswordGeneratorServiceEJB implements PasswordGeneratorService {

	@Override
	public String generate(String password) {
		return Util.createPasswordHash("SHA-256", "BASE64", null, null,
				password);
	}
}
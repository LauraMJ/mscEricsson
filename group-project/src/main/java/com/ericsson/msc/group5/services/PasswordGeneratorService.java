package com.ericsson.msc.group5.services;

import javax.ejb.Local;

@Local
public interface PasswordGeneratorService {

	public String generate(String password);
}

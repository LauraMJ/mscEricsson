package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.msc.group5.entities.Country;

@Local
public interface CountryService {

	public Collection <Country> getManagedCountry();

	public void addCountry(Collection <Country> countries);
}

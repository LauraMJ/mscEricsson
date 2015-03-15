package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.jpa.JPACountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;
import com.ericsson.msc.group5.services.CountryCodeNetworkCodeService;
import com.ericsson.msc.group5.services.ejb.CountryCodeNetworkCodeServiceEJB;

@RunWith(Arquillian.class)
public class CountryCodeNetworkCodeServiceEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(CountryCodeNetworkCodeCK.class.getPackage())
				.addClasses(CountryCodeNetworkCodeCK.class, CountryCodeNetworkCodeServiceEJB.class, CountryCodeNetworkCodeService.class, CountryCodeNetworkCodeDAO.class, JPACountryCodeNetworkCodeDAO.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	CountryCodeNetworkCodeService service;
	
	@Test
	public void addCountryCodeNetworkCodeTest(){
		Country country = new Country(1, "country");
		CountryCodeNetworkCodeCK ck = new CountryCodeNetworkCodeCK(country, 1);
		CountryCodeNetworkCode ccncck = new CountryCodeNetworkCode(ck, "one");
		
		Country countryTwo = new Country(2, "countryTwo");
		CountryCodeNetworkCodeCK ckTwo = new CountryCodeNetworkCodeCK(countryTwo, 2);
		CountryCodeNetworkCode ccncckTwo = new CountryCodeNetworkCode(ckTwo, "two");
		
		Country countryThree = new Country(3, "countryThree");
		CountryCodeNetworkCodeCK ckThree = new CountryCodeNetworkCodeCK(countryThree, 3);
		CountryCodeNetworkCode ccncckThree = new CountryCodeNetworkCode(ckThree, "Three");
		
		CountryCodeNetworkCode [] ccncArray = {ccncck, ccncckTwo, ccncckThree};
		
		Collection <CountryCodeNetworkCode> ccnc = new ArrayList <>();
		for (CountryCodeNetworkCode c : ccncArray) {
			ccnc.add(c);
		}
		
		service.addCountryCodeNetworkCode(ccnc);
		
		Collection <CountryCodeNetworkCode> retrievedCountryNetworkCodes = service.getCountryCodeNetworkCode();
		
		for (CountryCodeNetworkCode c : retrievedCountryNetworkCodes) {
			assertTrue("An object failed to be retrieved", ccnc.contains(c));
		}
		
	}
}
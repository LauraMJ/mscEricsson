package com.ericsson.msc.group5.services.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import com.ericsson.msc.group5.services.UserService;

@Startup
@Singleton
public class AdminAccountCreatorEJB {

	@EJB
	UserService userService;

	@PostConstruct
	public void init() {
		userService.addUser("administrator", "admin", "administrator");
	}
}

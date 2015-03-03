package com.ericsson.msc.group5.users;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import com.ericsson.msc.group5.dataAccessLayer.UserDAO;

@Stateless
@Local
@Path("/user-service")
public class UserService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO dao;
}

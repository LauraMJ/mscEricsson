package com.ericsson.msc.group5.services.ejb;

import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.services.FailureClassService;

@Stateless
@Local
public class FailureClassServiceEJB implements FailureClassService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private FailureClassDAO dao;

	@Override
	public Collection <FailureClass> getFailureClasses() {
		return dao.getAllFailureClasses();
	}

	@Override
	public void addFailureClasses(Collection <FailureClass> failureClasses) {
		dao.batchInsertFailureClasses(failureClasses);
	}
}

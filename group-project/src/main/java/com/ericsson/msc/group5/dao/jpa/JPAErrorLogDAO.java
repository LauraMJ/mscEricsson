package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.ErrorLogDAO;
import com.ericsson.msc.group5.entities.ErrorLog;

public class JPAErrorLogDAO implements ErrorLogDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <ErrorLog> getAllErrorLogs() {
		return em.createNamedQuery("findAllErrorLogs").getResultList();
	}

	@Override
	public void insertErrorLog(ErrorLog errorLog) {
		em.persist(errorLog);
	}

	@Override
	public void batchInsertErrorLog(Collection <ErrorLog> errorLogList) {
		for(ErrorLog errorLog : errorLogList)
			em.persist(errorLog);
	}

	@Override
	public void deleteErrorLogs() {
		em.createNamedQuery("deleteAllErrorLogs").getResultList();
	}
}

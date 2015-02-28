package com.ericsson.msc.group5.dao.jpa;

import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.DAOFactory;
import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;


public class JPADAOFactory implements DAOFactory{

	@Override
	public CountryCodeNetworkCodeDAO getCountryCodeNetworkCodeDAO() {
		return new JPACountryCodeNetworkCodeDAO();
	}

	@Override
	public EventCauseDAO getEventCauseDAO() {
		return new JPAEventCauseDAO();
	}

	@Override
	public FailureClassDAO getFailureClassDAO() {
		return new JPAFailureClassDAO();
	}

	@Override
	public FailureTraceDAO getFailureTraceDAO() {
		return new JPAFailureTraceDAO();
	}

	@Override
	public UserEquipmentDAO getUserEqipmentDAO() {
		return new JPAUserEquipmentDAO();
	}
}

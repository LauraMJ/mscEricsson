package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;

@Stateless
@Local
public class UserEquipmentServiceEJB implements UserEquipmentService{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserEquipmentDAO dao;
	
	@Override
	public Collection<UserEquipment> getTypeAllocationCode() {
		return null;
	}

	@Override
	public void addTypeAllocationCode(Collection<UserEquipment> allocationCodes) {
		dao.batchInsertUserEquipment(allocationCodes);
		
	}

}

package com.ericsson.msc.group5.services.ejb;

import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.services.UserEquipmentService;

@Stateless
@Local
public class UserEquipmentServiceEJB implements UserEquipmentService{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserEquipmentDAO dao;
	
	@Override
	public Collection<UserEquipment> getUserEquipment() {
		return dao.getAllUserEquipment();
	}
	
	@Override
	public void addUserEquipment(Collection<UserEquipment> userEquipments) {
		dao.batchInsertUserEquipment(userEquipments);
	}
	

}

package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;

public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List <UserEquipment> getAllUserEquipment() {
		return em.createNamedQuery("findAllUserEquipment").getResultList();
	}

	@Override
	public UserEquipment getUserEquipment(int typeAllocationCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUserEquipment(UserEquipment userEquipment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserEquipment(UserEquipment userEquipment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserEquipment(UserEquipment userEquipment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchInsertUserEquipment(List <UserEquipment> userEquipmentList) {
		// TODO Auto-generated method stub
		
	}

}

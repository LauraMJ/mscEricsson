package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;

public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <UserEquipment> getAllUserEquipment() {
		return em.createNamedQuery("findAllUserEquipment").getResultList();
	}

	@Override
	public UserEquipment getUserEquipment(int typeAllocationCode) {
		return em.find(UserEquipment.class, typeAllocationCode);
	}

	@Override
	public void insertUserEquipment(UserEquipment userEquipment) {
		em.persist(userEquipment);
	}

	@Override
	public void batchInsertUserEquipment(Collection <UserEquipment> userEquipmentList) {
		for(UserEquipment userEquipment : userEquipmentList)
			em.persist(userEquipment);
	}
}

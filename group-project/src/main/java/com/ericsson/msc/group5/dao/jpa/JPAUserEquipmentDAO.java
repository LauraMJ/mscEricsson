package com.ericsson.msc.group5.dao.jpa;

import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;

public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	public UserEquipment getManagedUserEquipment(int tac) {
		EntityManager em = PersistenceUtil.createEM();
		UserEquipment ue = em
				.createQuery(
						"select ue from " + UserEquipment.class.getName()
								+ " ue where ue.typeAllocationCode = :tac",
						UserEquipment.class).setParameter("tac", tac)
				.getSingleResult();
		System.out.println("hi found");
		em.close();
		return ue;
	}
}

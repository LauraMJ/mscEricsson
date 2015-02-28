package com.ericsson.msc.group5.dao.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;

@Local
@Stateless
public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	@PersistenceContext
	private EntityManager em;

	public UserEquipment getManagedUserEquipment(int tac) {
		EntityManager em = PersistenceUtil.createEM();
		UserEquipment ue = em
				.createQuery(
						"select ue from " + UserEquipment.class.getName()
								+ " ue where ue.typeAllocationCode = :tac",
						UserEquipment.class).setParameter("tac", tac)
				.getSingleResult();
		em.close();
		return ue;
	}
}

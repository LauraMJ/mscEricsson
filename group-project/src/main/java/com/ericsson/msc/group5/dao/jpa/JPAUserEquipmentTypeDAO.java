package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserEquipmentTypeDAO;
import com.ericsson.msc.group5.entities.UserEquipmentType;

@Local
@Stateless
public class JPAUserEquipmentTypeDAO implements UserEquipmentTypeDAO {

	@PersistenceContext
	private EntityManager em;

	public UserEquipmentType getManagedUserEquipmentType(String ueType) {
		EntityManager em = PersistenceUtil.createEM();
		List <UserEquipmentType> userEqiupentTypeList = em
				.createQuery(
						"select ueT from " + UserEquipmentType.class.getName()
								+ " ueT where ueT.userEquipmentType = :uetype",
						UserEquipmentType.class).setParameter("uetype", ueType)
				.getResultList();
		if (userEqiupentTypeList.isEmpty()) {
			UserEquipmentType userEquipmentType = new UserEquipmentType();
			userEquipmentType.setUserEquipmentType(ueType);
			PersistenceUtil.persist(userEquipmentType);
			em.close();
			return userEquipmentType;
		}
		em.close();
		return userEqiupentTypeList.get(0);
	}
}

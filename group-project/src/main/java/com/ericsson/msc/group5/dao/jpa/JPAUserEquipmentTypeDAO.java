package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.UserEquipmentTypeDAO;
import com.ericsson.msc.group5.entities.UserEquipmentType;

public class JPAUserEquipmentTypeDAO implements UserEquipmentTypeDAO {

	public UserEquipmentType getManagedUserEquipmentType(String ueType) {
		EntityManager em = PersistenceUtil.createEM();
		List <UserEquipmentType> userEqiupentTypeList = em
				.createQuery(
						"select ueT from " + UserEquipmentType.class.getName()
								+ " ueT where ueT.userEquipmentType = :uetype",
						UserEquipmentType.class).setParameter("uetype", ueType)
				.getResultList();
		if (userEqiupentTypeList.isEmpty()) {
			System.out.println("ue not found");
			UserEquipmentType userEquipmentType = new UserEquipmentType();
			userEquipmentType.setUserEquipmentType(ueType);
			PersistenceUtil.persist(userEquipmentType);
			em.close();
			return userEquipmentType;
		}
		System.out.println("ue found");
		em.close();
		return userEqiupentTypeList.get(0);
	}
}

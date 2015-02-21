package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.InputModeDAO;
import com.ericsson.msc.group5.entities.InputMode;

public class JPAInputModeDAO implements InputModeDAO {

	public InputMode getManagedInputMode(String inputMode) {
		EntityManager em = PersistenceUtil.createEM();
		List <InputMode> inputModeList = em
				.createQuery(
						"select im from " + InputMode.class.getName()
								+ " im where im.inputMode = :imode",
						InputMode.class).setParameter("imode", inputMode)
				.getResultList();
		if (inputModeList.isEmpty()) {
			System.out.println("im not found");
			InputMode inputModeObject = new InputMode();
			inputModeObject.setInputMode(inputMode);
			PersistenceUtil.persist(inputModeObject);
			em.close();
			return inputModeObject;
		}
		System.out.println("im found");
		em.close();
		return inputModeList.get(0);
	}
}

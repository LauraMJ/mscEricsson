package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.InputModeDAO;
import com.ericsson.msc.group5.entities.InputMode;

@Local
@Stateless
public class JPAInputModeDAO implements InputModeDAO {

	@PersistenceContext
	private EntityManager em;

	public InputMode getManagedInputMode(String inputMode) {
		EntityManager em = PersistenceUtil.createEM();
		List <InputMode> inputModeList = em
				.createQuery(
						"select im from " + InputMode.class.getName()
								+ " im where im.inputMode = :imode",
						InputMode.class).setParameter("imode", inputMode)
				.getResultList();
		if (inputModeList.isEmpty()) {
			InputMode inputModeObject = new InputMode();
			inputModeObject.setInputMode(inputMode);
			PersistenceUtil.persist(inputModeObject);
			em.close();
			return inputModeObject;
		}
		em.close();
		return inputModeList.get(0);
	}
}

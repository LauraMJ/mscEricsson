package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.EventCauseDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;

public class JPAEventCauseDAO implements EventCauseDAO {

	public EventCause getMangedEventCause(int causeCode, int eventId,
			String description) {
		EntityManager em = PersistenceUtil.createEM();
		List <EventCause> ecList = em
				.createQuery(
						"select ec from "
								+ EventCause.class.getName()
								+ " ec where ec.causeCodeEventIdCK.causeCode = :causeCode AND ec.causeCodeEventIdCK.eventId = :eventId",
						EventCause.class).setParameter("causeCode", causeCode)
				.setParameter("eventId", eventId).getResultList();
		if (ecList.isEmpty()) {
			System.out.println("ec not found");
			EventCause ec = new EventCause(
					new EventCauseCK(causeCode, eventId), description);
			PersistenceUtil.persist(ec);
			em.close();
			return ec;
		}
		System.out.println("ec not found");
		em.close();
		return ecList.get(0);
	}
}

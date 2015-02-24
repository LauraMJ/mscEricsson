package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dataAccessLayer.EventCauseDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;

@Local
@Stateless
public class JPAEventCauseDAO implements EventCauseDAO {

	@PersistenceContext
	private EntityManager em;

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
			EventCause ec = new EventCause(
					new EventCauseCK(causeCode, eventId), description);
			PersistenceUtil.persist(ec);
			em.close();
			return ec;
		}
		em.close();
		return ecList.get(0);
	}
}

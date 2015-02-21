package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.HierInfoDAO;
import com.ericsson.msc.group5.entities.HierInfo;

public class JPAHierInfoDAO implements HierInfoDAO {

	public HierInfo getManagedHierInfo(long hier3Id, long hier32Id,
			long hier321Id) {
		EntityManager em = PersistenceUtil.createEM();
		List <HierInfo> hiList = em
				.createQuery(
						"select hi from "
								+ HierInfo.class.getName()
								+ " hi where hi.hier3Id = :hier3Id AND hi.hier32Id = :hier32Id AND hi.hier321Id = :hier321Id",
						HierInfo.class)
				.setParameter("hier3Id", Long.toString(hier3Id))
				.setParameter("hier32Id", Long.toString(hier32Id))
				.setParameter("hier321Id", Long.toString(hier321Id))
				.getResultList();
		if (hiList.isEmpty()) {
			System.out.println("hi not found");
			HierInfo hi = new HierInfo();
			hi.setHier3Id(Long.toString(hier3Id));
			hi.setHier32Id(Long.toString(hier32Id));
			hi.setHier321Id(Long.toString(hier321Id));

			PersistenceUtil.persist(hi);
			em.close();
			return hi;
		}
		System.out.println("hi not found");
		em.close();
		return hiList.get(0);
	}
}

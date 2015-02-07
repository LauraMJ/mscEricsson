package persistence;

import entity.AccessCapability;
import entity.CellInfo;
import entity.FailureClass;

public class PersistenceTest {

	public static void main(String[] args) {
		new PersistenceTest();
	}

	public PersistenceTest() {
		PersistenceUtil.persist(new AccessCapability("TEST"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new CellInfo(25, 12, "213421", "2341325", "TEST"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new FailureClass(4, "hello world"));
		System.out.println("Entity saved");
	}
}

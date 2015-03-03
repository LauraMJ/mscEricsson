/*package com.ericsson.msc.group5.dao.test;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import com.ericsson.msc.group5.dataIO.DataImport;

public class runWithWeld {

	Weld weld = new Weld();
	WeldContainer container;

	private static final String EXCEL_SHEET_LOCATION = "C:\\Users\\Siobhan\\Dropbox\\MyFiles\\DIT\\DT340A\\FinalProject.xls";

	public static void main(String [] args) {
		new runWithWeld();
	}

	public runWithWeld() {
		System.out.println("Starting up");
		container = new Weld().initialize();
		if (container != null) {
			System.out.println("Weld not null");
		}
		DataImport service = container.instance().select(DataImport.class)
				.get();
		if (service != null) {
			System.out.println("Service not null");
		}

		service.begin(EXCEL_SHEET_LOCATION);
		weld.shutdown();
		System.out.println("finito");
	}
	// @Before
	// public void setUp() throws Exception {
	//
	// System.out.println("Starting up");
	// weld = new Weld().initialize();
	// if (weld != null) {
	// System.out.println("Weld not null");
	// }
	// service = weld.instance().select(DataImport.class).get();
	// if (service != null) {
	// System.out.println("Service not null");
	// }
	//
	// WeldContainer weld = new Weld().initialize();
	//
	// }

	// @After
	// public void tearDown() throws Exception {
	// }
	//
	// @Test
	// public void test() {

	// List catalogue = service.getCatalog();
	// CompactDisc c = (DataImport) catalogue.get(0);
	// System.out.println(c.getTitle());
	// assertTrue(c.getTitle().equals("Bob Dole"));

}
*/

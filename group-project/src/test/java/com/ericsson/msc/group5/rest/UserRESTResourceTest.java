package com.ericsson.msc.group5.rest;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

@RunWith(Arquillian.class)
public class UserRESTResourceTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "test.war")

				.addPackages(true, "com.ericsson.msc.group5.dao", "com.ericsson.msc.group5.dao.jpa", "com.ericsson.msc.group5.entities",
						"com.ericsson.msc.group5.rest", "com.ericsson.msc.group5.services", "com.ericsson.msc.group5.services.ejb",
						"com.ericsson.msc.group5.servlet", "com.ericsson.msc.group5.utils").addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.setWebXML(new File("src/main/webapp/WEB-INF/web.xml")).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		File [] libs;

		libs = Maven.resolver().resolve("com.jayway.restassured:rest-assured:2.4.0").withTransitivity().as(File.class);
		archive.addAsLibraries(libs);

		libs = Maven.resolver().resolve("org.apache.poi:poi:3.11").withTransitivity().as(File.class);
		archive.addAsLibraries(libs);

		return archive;
	}

	@Before
	public void setUp() throws Exception {
		RestAssured.config = RestAssuredConfig.config().logConfig(new LogConfig(System.out, true));
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "group-project/rest";
		RestAssured.port = 8080;
	}

	@Test
	public void basicAuthentication() throws Exception {
		given().auth().basic("administrator", "admin").expect().statusCode(200).when().get("/get/userrole");
	}

	@Test
	public void testGetUsername() {
		expect().statusCode(200).body("username", equalTo("administartor")).when().get("/get/userrole");
	}

	@Test
	public void testAddUser() {
		// fail("Not yet implemented");
	}

	@Test
	public void testAddDefaultAdminAccount() {
		// fail("Not yet implemented");
	}

	// @Test
	// public void testGetUsername() {
	// Response res = get("/get/userrole");
	// assertEquals(200, res.getStatusCode());
	// String json = res.asString();
	// JsonPath jp = new JsonPath(json);
	// assertEquals("user", jp.get("username"));
	// }

	@Test
	public void testGetUserrole() {
		Response res = get("/get/userrole");
		assertEquals(200, res.getStatusCode());
		String json = res.asString();
		JsonPath jp = new JsonPath(json);
		assertEquals("administartor", jp.get("role"));
	}
}

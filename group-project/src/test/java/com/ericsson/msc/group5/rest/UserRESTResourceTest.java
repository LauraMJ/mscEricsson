package com.ericsson.msc.group5.rest;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class UserRESTResourceTest {

	@Test
	public void testGetUsername() {
		expect().statusCode(200)
				.body("username", equalTo("administartor")).when().get("/get/userrole");
	}
	
	@Test
	public void testAddUser() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddDefaultAdminAccount() {
		//fail("Not yet implemented");
	}

//	@Test
//	public void testGetUsername() {
//		Response res = get("/get/userrole");
//		assertEquals(200, res.getStatusCode());
//		String json = res.asString();
//		JsonPath jp = new JsonPath(json);
//		assertEquals("user", jp.get("username"));
//	}

	@Test
	public void testGetUserrole() {
	Response res = get("/get/userrole");
	assertEquals(200, res.getStatusCode());
	String json = res.asString();
	JsonPath jp = new JsonPath(json);
	assertEquals("administartor", jp.get("role"));
	
	
	}

}

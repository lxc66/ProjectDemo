package com.jzsoft.demo.webservice.impl;

import javax.jws.WebService;

import com.jzsoft.demo.model.TestObject;
import com.jzsoft.demo.webservice.ITestWebService;
@WebService(endpointInterface = "com.jzsoft.demo.webservice.ITestWebService")
public class TestWebServiceImpl implements ITestWebService {

	@Override
	public TestObject getTestObject(String id) {
		TestObject testObject = new TestObject();
		testObject.setId(id);
		testObject.setName("小强");
		return testObject;
	}

}

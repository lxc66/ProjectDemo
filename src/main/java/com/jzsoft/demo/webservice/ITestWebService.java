package com.jzsoft.demo.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.jzsoft.demo.model.TestObject;

@WebService
public interface ITestWebService {
	TestObject getTestObject(@WebParam(name="id") String id);
}

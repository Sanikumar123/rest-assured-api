package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utility {
	
	static RequestSpecification req;
	ResponseSpecification res;
	
	public RequestSpecification getRequestSpecification() throws IOException
	{
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req =new RequestSpecBuilder().setBaseUri(getGlobalValue("BASE_URI"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addFilter(new AllureRestAssuredFilter())
				.build();
		return req;
		}
		return req;
	}
	
	public ResponseSpecification getResponseSpecification()  
	{
		res = new ResponseSpecBuilder().expectContentType("application/json; charset=utf-8").expectStatusCode(200).build();
		return res;
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\Global.properties");
		prop.load(fin);
		return prop.getProperty(key);
	}

}

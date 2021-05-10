package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {	
	
	public static RequestSpecification request;
	
	public RequestSpecification requestSpecification() throws IOException {	
		if(request==null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			request = new RequestSpecBuilder().setBaseUri(globalVariable("base_uri"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return request;
		}
		return request;
		
	}
	
	public static String globalVariable(String key) throws IOException {
			Properties prop = new Properties();
			FileInputStream input = new FileInputStream("src/test/java/resources/global.properties");
			prop.load(input);
			return prop.getProperty(key);	
	}
	
	public String getJsonPath(Response res, String key) {
		String resp = res.asString();
		JsonPath jsonObj = new JsonPath(resp);
		return jsonObj.get(key).toString();		
	}

}
